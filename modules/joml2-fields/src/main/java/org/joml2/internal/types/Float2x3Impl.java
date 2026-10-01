// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float2x3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float2x3Impl implements Float2x3 {
    public float m00;
    public float m10;
    public float m01;
    public float m11;
    public float m02;
    public float m12;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float2x3SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x3SegOpsUnsafe()
            : new Float2x3SegOpsMS();
        static final Float2x3BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x3BbOpsUnsafe()
            : new Float2x3BbOpsApi();
        static final Float2x3RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x3RawOpsUnsafe()
            : new Float2x3RawOpsApi();
    }

    public Float2x3Impl() {
        m00 = 1;
        m11 = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float2x3Impl(float m00, float m01, float m02, float m10, float m11, float m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        this.properties = determineProperties();
    }

    public Float2x3Impl(Float2x3R src) {
        Float2x3Impl srcImpl = (Float2x3Impl) src;
        this.m00 = srcImpl.m00;
        this.m10 = srcImpl.m10;
        this.m01 = srcImpl.m01;
        this.m11 = srcImpl.m11;
        this.m02 = srcImpl.m02;
        this.m12 = srcImpl.m12;
        this.properties = ((Float2x3Impl) src).properties;
    }

    /**
     * Numerically determine the structural properties of this matrix (identity, translation,
     * affinity) and return them as property bits.
     * <p>
     * The comparison is exact: an element counts as {@code 0} or {@code 1} only when it is exactly
     * that value (as by {@code ==}), with no tolerance. A {@code double} element {@code 1 + 1e-8}
     * is therefore not an identity element, while the {@code float} literal {@code 1 + 1e-8f}
     * already rounds to {@code 1.0f} and is.
     * <p>
     * Only identity, translation and affine are inferred (the identity and a pure translation carry
     * the orthogonal bit they imply); a general rotation block is never recognised as orthogonal. A
     * rotation loaded from a buffer or set from scalars therefore takes the affine dispatch arms
     * until it is rebuilt through a {@code make*} factory, which sets the bits from what it
     * constructs.
     * <p>
     * This is a pure query: it does not update this matrix's cached property bits.
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.m00 != 1 || this.m01 != 0 || this.m10 != 0 || this.m11 != 1) return 1;
        if (this.m02 != 0 || this.m12 != 0) return 7;
        return 15;
    }

    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. */
    @Override public boolean isIdentity() { return (this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY; }

    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. */
    @Override public boolean isTranslation() { return (this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION; }

    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. */
    @Override public boolean isOrthogonal() { return (this.properties & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL; }

    /** {@return whether this matrix is affine} Always {@code true} for this shape. */
    @Override public boolean isAffine() { return true; }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_identity(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
            case 2: _idxSw0 = 0.0f; _idxSw1 = 0.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        return d;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_translation(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw2;
        float _idxSw3;
        switch (col) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = 0.0f; break;
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; break;
            case 2: _idxSw2 = this.m02; _idxSw3 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw2;
        d.y = _idxSw3;
        return d;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_general(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw4 = this.m00; _idxSw5 = this.m10; break;
            case 1: _idxSw4 = this.m01; _idxSw5 = this.m11; break;
            case 2: _idxSw4 = this.m02; _idxSw5 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw4;
        d.y = _idxSw5;
        return d;
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
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return getColumn_general(col, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        return getColumn_translation(col, dest);
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
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw6 = this.m00; _idxSw7 = this.m10; break;
            case 1: _idxSw6 = this.m01; _idxSw7 = this.m11; break;
            case 2: _idxSw6 = this.m02; _idxSw7 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw6;
        d.y = _idxSw7;
        return d;
    }

    /**
     * Compute the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; it is still
     * recovered under a uniform scale, or a scale applied before the rotation ({@code M = R * S})
     * whose x factor is positive.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; it is still
     *        recovered under a uniform scale, or a scale applied before the rotation
     *        ({@code M = R * S}) whose x factor is positive
     */
    public float getRotationAngle() {
        return Math.atan2(this.m10, this.m00);
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_identity(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = 0.0f;
        return d;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_translation(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw2;
        float _idxSw3;
        float _idxSw4;
        switch (row) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = 0.0f; _idxSw4 = this.m02; break;
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; _idxSw4 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw2;
        d.y = _idxSw3;
        d.z = _idxSw4;
        return d;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_general(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw5;
        float _idxSw6;
        float _idxSw7;
        switch (row) {
            case 0: _idxSw5 = this.m00; _idxSw6 = this.m01; _idxSw7 = this.m02; break;
            case 1: _idxSw5 = this.m10; _idxSw6 = this.m11; _idxSw7 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw5;
        d.y = _idxSw6;
        d.z = _idxSw7;
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
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return getRow_general(row, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getRow_identity(row, dest);
        return getRow_translation(row, dest);
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
        float _idxSw8;
        float _idxSw9;
        float _idxSw10;
        switch (row) {
            case 0: _idxSw8 = this.m00; _idxSw9 = this.m01; _idxSw10 = this.m02; break;
            case 1: _idxSw8 = this.m10; _idxSw9 = this.m11; _idxSw10 = this.m12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw8;
        d.y = _idxSw9;
        d.z = _idxSw10;
        return d;
    }

    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_general(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.m02;
        d.y = this.m12;
        return d;
    }

    /**
     * Get the translation of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2 getTranslation(@Mutated Float2 dest) {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2Impl d = (Float2Impl) dest;
            d.x = 0.0f;
            d.y = 0.0f;
            return d;
        }
        return getTranslation_general(dest);
    }

    /**
     * Get the translation of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 getTranslation(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.m02;
        d.y = this.m12;
        return d;
    }

    /**
     * Compute the determinant of the linear part (the upper-left square block) of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of the linear part (the upper-left square block) of this matrix
     */
    public float determinant() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t1 = -(this.m01 * this.m10);
            return java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        } else {
            float _t1 = -(this.m01 * this.m10);
            return ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        }
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
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.m12, this.m12, java.lang.Math.fma(this.m11, this.m11, java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m02, this.m02, java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01))))));
        } else {
            return (float) java.lang.Math.sqrt(((this.m12) * (this.m12) + (((this.m11) * (this.m11) + (((this.m10) * (this.m10) + (((this.m02) * (this.m02) + (((this.m00) * (this.m00) + (this.m01 * this.m01)))))))))));
        }
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_fma(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -this.m10 * this.m12;
        float _t3 = -this.m11 * this.m12;
        float _rd0 = this.m10;
        float _rd1 = this.m11;
        float _rd2 = this.m02;
        d.m00 = _rd1;
        d.m10 = -_rd0;
        d.m01 = _rd0;
        d.m11 = _rd1;
        d.m02 = java.lang.Math.fma(_rd2, -_rd1, _t2) - java.lang.Math.fma(_rd0, this.m12, _t2);
        d.m12 = java.lang.Math.fma(_rd2, _rd0, _t3) - java.lang.Math.fma(_rd1, this.m12, _t3);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_mulAdd(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -this.m10 * this.m12;
        float _t3 = -this.m11 * this.m12;
        float _rd0 = this.m10;
        float _rd1 = this.m11;
        float _rd2 = this.m02;
        d.m00 = _rd1;
        d.m10 = -_rd0;
        d.m01 = _rd0;
        d.m11 = _rd1;
        d.m02 = ((_t2) - (_rd2) * (_rd1)) - ((_rd0) * (this.m12) + (_t2));
        d.m12 = ((_rd2) * (_rd0) + (_t3)) - ((_rd1) * (this.m12) + (_t3));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_self_fma(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -this.m10 * this.m12;
        float _t3 = -this.m11 * this.m12;
        float _rd0 = this.m10;
        float _rd1 = this.m02;
        d.m00 = this.m11;
        d.m10 = -_rd0;
        d.m01 = _rd0;
        d.m02 = java.lang.Math.fma(_rd1, -this.m11, _t2) - java.lang.Math.fma(_rd0, this.m12, _t2);
        d.m12 = java.lang.Math.fma(_rd1, _rd0, _t3) - java.lang.Math.fma(this.m11, this.m12, _t3);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_general_fma(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 * _t9_inv;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 * _t9_inv;
        d.m02 = (java.lang.Math.fma(_rd2, this.m12, _t4) - java.lang.Math.fma(_rd4, _rd3, _t4)) * _t9_inv;
        d.m12 = (java.lang.Math.fma(_rd4, _rd1, _t5) - java.lang.Math.fma(_rd0, this.m12, _t5)) * _t9_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_general_mulAdd(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 * _t9_inv;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 * _t9_inv;
        d.m02 = (((_rd2) * (this.m12) + (_t4)) - ((_rd4) * (_rd3) + (_t4))) * _t9_inv;
        d.m12 = (((_rd4) * (_rd1) + (_t5)) - ((_rd0) * (this.m12) + (_t5))) * _t9_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix,
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 invert(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? invert_general_fma(dest) : invert_general_mulAdd(dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = -this.m02;
            d.m12 = -this.m12;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? invert_orthogonal_fma(dest) : invert_orthogonal_mulAdd(dest));
    }

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 invert() {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return invert(Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return invert_general_fma(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = -this.m02;
                this.m12 = -this.m12;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_orthogonal_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return invert_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = -this.m02;
                this.m12 = -this.m12;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            float _t2 = -this.m10 * this.m12;
            float _t3 = -this.m11 * this.m12;
            float _rd0 = this.m10;
            float _rd1 = this.m02;
            this.m00 = this.m11;
            this.m10 = -_rd0;
            this.m01 = _rd0;
            this.m02 = ((_t2) - (_rd1) * (this.m11)) - ((_rd0) * (this.m12) + (_t2));
            this.m12 = ((_rd1) * (_rd0) + (_t3)) - ((this.m11) * (this.m12) + (_t3));
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
    }

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix,
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 invert(@Mutated Double2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t9_inv = 1.0f / _t9;
        d.m00 = this.m11 * _t9_inv;
        d.m10 = -(this.m10 * _t9_inv);
        d.m01 = -(this.m01 * _t9_inv);
        d.m11 = this.m00 * _t9_inv;
        d.m02 = (java.lang.Math.fma(this.m01, this.m12, _t4) - java.lang.Math.fma(this.m02, this.m11, _t4)) * _t9_inv;
        d.m12 = (java.lang.Math.fma(this.m02, this.m10, _t5) - java.lang.Math.fma(this.m00, this.m12, _t5)) * _t9_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invert_mulAdd(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t9_inv = 1.0f / _t9;
        d.m00 = this.m11 * _t9_inv;
        d.m10 = -(this.m10 * _t9_inv);
        d.m01 = -(this.m01 * _t9_inv);
        d.m11 = this.m00 * _t9_inv;
        d.m02 = (((this.m01) * (this.m12) + (_t4)) - ((this.m02) * (this.m11) + (_t4))) * _t9_inv;
        d.m12 = (((this.m02) * (this.m10) + (_t5)) - ((this.m00) * (this.m12) + (_t5))) * _t9_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_orthogonal_general_fma(@Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        d.m12 = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_orthogonal_general_mulAdd(@Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        d.m12 = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_fma(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invert_degenerate_orthogonal_general_fma(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_mulAdd(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invert_degenerate_orthogonal_general_mulAdd(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_fma(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        d.m12 = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_mulAdd(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        d.m12 = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float _r0 = otherImpl.m01;
        float _r1 = otherImpl.m10;
        float _r2 = this.m01;
        float _r3 = this.m10;
        float _r8 = otherImpl.m00;
        float _r9 = otherImpl.m11;
        float _r10 = this.m00;
        float _r11 = this.m11;
        float _t6 = -(_r0 * _r1);
        float _t7 = -(_r2 * _r3);
        float _t24 = java.lang.Math.fma(_r8, _r9, _t6) - java.lang.Math.fma(_r0, _r1, _t6);
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t25 = java.lang.Math.fma(_r10, _r11, _t7) - java.lang.Math.fma(_r2, _r3, _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = otherImpl.m12;
        float _r7 = otherImpl.m02;
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        return invertProduct_general_sfde21b89_1_fma(other, (Float2x3Impl) dest, _r0, _r1, _r2, _r3, _r8, _r9, _r10, _r11, _r4, _r5, _r6, _r7, -(_r4 * _r3), -(_r2 * _r5), -(_r0 * _r6), -(_r7 * _r1), _t24_inv, _t25_inv, _r9 / _t24, _r11 / _t25, _r10 / _t25, _r8 / _t24, -(_r0 * _t24_inv), -(_r3 * _t25_inv));
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float _r0 = otherImpl.m01;
        float _r1 = otherImpl.m10;
        float _r2 = this.m01;
        float _r3 = this.m10;
        float _r8 = otherImpl.m00;
        float _r9 = otherImpl.m11;
        float _r10 = this.m00;
        float _r11 = this.m11;
        float _t6 = -(_r0 * _r1);
        float _t7 = -(_r2 * _r3);
        float _t24 = ((_r8) * (_r9) + (_t6)) - ((_r0) * (_r1) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t25 = ((_r10) * (_r11) + (_t7)) - ((_r2) * (_r3) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _r4 = this.m02;
        float _r5 = this.m12;
        float _r6 = otherImpl.m12;
        float _r7 = otherImpl.m02;
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        return invertProduct_general_sfde21b89_1_mulAdd(other, (Float2x3Impl) dest, _r0, _r1, _r2, _r3, _r8, _r9, _r10, _r11, _r4, _r5, _r6, _r7, -(_r4 * _r3), -(_r2 * _r5), -(_r0 * _r6), -(_r7 * _r1), _t24_inv, _t25_inv, _r9 / _t24, _r11 / _t25, _r10 / _t25, _r8 / _t24, -(_r0 * _t24_inv), -(_r3 * _t25_inv));
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_1_fma(Float2x3R other, Float2x3Impl d, float _r0, float _r1, float _r2, float _r3, float _r8, float _r9, float _r10, float _r11, float _r4, float _r5, float _r6, float _r7, float _t8, float _t9, float _t10, float _t11, float _t24_inv, float _t25_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43) {
        float _t44 = -(_r2 * _t25_inv);
        float _t45 = -(_r1 * _t24_inv);
        float _t50 = -((java.lang.Math.fma(_r10, _r5, _t8) - java.lang.Math.fma(_r4, _r3, _t8)) * _t25_inv);
        float _t51 = -((java.lang.Math.fma(_r4, _r11, _t9) - java.lang.Math.fma(_r2, _r5, _t9)) * _t25_inv);
        float _t52 = _t36 * _t37;
        float _t53 = _t41 * _t38;
        float _t54 = _t44 * _t36;
        float _t55 = _t43 * _t41;
        d.m00 = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        d.m01 = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        d.m02 = java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_r7, _r9, _t10) - java.lang.Math.fma(_r0, _r6, _t10)) * _t24_inv)));
        d.m10 = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
        d.m11 = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
        d.m12 = java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_r8, _r6, _t11) - java.lang.Math.fma(_r7, _r1, _t11)) * _t24_inv)));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_1_mulAdd(Float2x3R other, Float2x3Impl d, float _r0, float _r1, float _r2, float _r3, float _r8, float _r9, float _r10, float _r11, float _r4, float _r5, float _r6, float _r7, float _t8, float _t9, float _t10, float _t11, float _t24_inv, float _t25_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43) {
        float _t44 = -(_r2 * _t25_inv);
        float _t45 = -(_r1 * _t24_inv);
        float _t50 = -((((_r10) * (_r5) + (_t8)) - ((_r4) * (_r3) + (_t8))) * _t25_inv);
        float _t51 = -((((_r4) * (_r11) + (_t9)) - ((_r2) * (_r5) + (_t9))) * _t25_inv);
        float _t52 = _t36 * _t37;
        float _t53 = _t41 * _t38;
        float _t54 = _t44 * _t36;
        float _t55 = _t43 * _t41;
        d.m00 = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
        d.m01 = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
        d.m02 = ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_r7) * (_r9) + (_t10)) - ((_r0) * (_r6) + (_t10))) * _t24_inv))));
        d.m10 = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        d.m11 = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        d.m12 = ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_r8) * (_r6) + (_t11)) - ((_r7) * (_r1) + (_t11))) * _t24_inv))));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(otherImpl.m01 * otherImpl.m10);
        float _t4 = -(otherImpl.m01 * otherImpl.m12);
        float _t5 = -(otherImpl.m02 * otherImpl.m10);
        float _t9 = java.lang.Math.fma(otherImpl.m00, otherImpl.m11, _t3) - java.lang.Math.fma(otherImpl.m01, otherImpl.m10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = otherImpl.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -((java.lang.Math.fma(_rd4, _rd3, _t4) - java.lang.Math.fma(_rd2, otherImpl.m12, _t4)) * _t9_inv);
        d.m12 = -((java.lang.Math.fma(_rd0, otherImpl.m12, _t5) - java.lang.Math.fma(_rd4, _rd1, _t5)) * _t9_inv);
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(otherImpl.m01 * otherImpl.m10);
        float _t4 = -(otherImpl.m01 * otherImpl.m12);
        float _t5 = -(otherImpl.m02 * otherImpl.m10);
        float _t9 = ((otherImpl.m00) * (otherImpl.m11) + (_t3)) - ((otherImpl.m01) * (otherImpl.m10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = otherImpl.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -((((_rd4) * (_rd3) + (_t4)) - ((_rd2) * (otherImpl.m12) + (_t4))) * _t9_inv);
        d.m12 = -((((_rd0) * (otherImpl.m12) + (_t5)) - ((_rd4) * (_rd1) + (_t5))) * _t9_inv);
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }

    /** Private column 2 of {@code invertProduct_translation}: computes and stores it; reached only through it. */
    private void invertProduct_translation_s11a188f4_c2_fma(Float2x3Impl _dst, float _t2, float _t18, float _t3, float _t22, float _r5, float _r7, float _t8, float _r2, float _r4, float _t13_inv, float _t23, float _t21, float _r6, float _t9, float _r3) {
        _dst.m02 = java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(_t3, _t22, -((java.lang.Math.fma(_r5, _r7, _t8) - java.lang.Math.fma(_r2, _r4, _t8)) * _t13_inv)));
        _dst.m12 = java.lang.Math.fma(_t2, _t23, java.lang.Math.fma(_t3, _t21, -((java.lang.Math.fma(_r6, _r4, _t9) - java.lang.Math.fma(_r5, _r3, _t9)) * _t13_inv)));
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r2 = otherImpl.m01;
        float _r3 = otherImpl.m10;
        float _r6 = otherImpl.m00;
        float _r7 = otherImpl.m11;
        float _t7 = -(_r2 * _r3);
        float _t13 = java.lang.Math.fma(_r6, _r7, _t7) - java.lang.Math.fma(_r2, _r3, _t7);
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _r4 = otherImpl.m12;
        float _r5 = otherImpl.m02;
        float _t13_inv = 1.0f / _t13;
        {
            float _t18 = _r7 / _t13;
            float _t22 = -(_r2 * _t13_inv);
            float _t21 = _r6 / _t13;
            float _t23 = -(_r3 * _t13_inv);
            d.m00 = _t18;
            d.m10 = _t23;
            d.m01 = _t22;
            d.m11 = _t21;
            invertProduct_translation_s11a188f4_c2_fma(d, -_r0, _t18, -_r1, _t22, _r5, _r7, -(_r2 * _r4), _r2, _r4, _t13_inv, _t23, _t21, _r6, -(_r5 * _r3), _r3);
        }
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r2 = otherImpl.m01;
        float _r3 = otherImpl.m10;
        float _r6 = otherImpl.m00;
        float _r7 = otherImpl.m11;
        float _t7 = -(_r2 * _r3);
        float _t13 = ((_r6) * (_r7) + (_t7)) - ((_r2) * (_r3) + (_t7));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _r0 = this.m02;
        float _r1 = this.m12;
        float _r4 = otherImpl.m12;
        float _r5 = otherImpl.m02;
        float _t13_inv = 1.0f / _t13;
        {
            float _t18 = _r7 / _t13;
            float _t22 = -(_r2 * _t13_inv);
            float _t2 = -_r0;
            float _t3 = -_r1;
            float _t8 = -(_r2 * _r4);
            float _t21 = _r6 / _t13;
            float _t9 = -(_r5 * _r3);
            float _t23 = -(_r3 * _t13_inv);
            d.m00 = _t18;
            d.m10 = _t23;
            d.m01 = _t22;
            d.m11 = _t21;
            d.m02 = ((_t2) * (_t18) + (((_t3) * (_t22) - ((((_r5) * (_r7) + (_t8)) - ((_r2) * (_r4) + (_t8))) * _t13_inv))));
            d.m12 = ((_t2) * (_t23) + (((_t3) * (_t21) - ((((_r6) * (_r4) + (_t9)) - ((_r5) * (_r3) + (_t9))) * _t13_inv))));
        }
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /** Private column 0 of {@code invertProduct_orthogonal}: computes and stores it; reached only through it. */
    private void invertProduct_orthogonal_s11a188f4_c0_fma(Float2x3Impl _dst, float _t0, float _t32, float _t40, float _t4, float _t36, float _t37, float _t39, float _t35) {
        _dst.m00 = java.lang.Math.fma(_t0, _t32, _t40) + java.lang.Math.fma(_t4, _t36, -_t40);
        _dst.m10 = java.lang.Math.fma(_t0, _t37, _t39) + java.lang.Math.fma(_t4, _t35, -_t39);
    }

    /** Private column 1 of {@code invertProduct_orthogonal}: computes and stores it; reached only through it. */
    private void invertProduct_orthogonal_s11a188f4_c1_fma(Float2x3Impl _dst, float _t2, float _t36, float _t38, float _t5, float _t32, float _t35, float _t41, float _t37) {
        _dst.m01 = java.lang.Math.fma(_t2, _t36, _t38) + java.lang.Math.fma(_t5, _t32, -_t38);
        _dst.m11 = java.lang.Math.fma(_t2, _t35, _t41) + java.lang.Math.fma(_t5, _t37, -_t41);
    }

    /**
     * Private column 2 of {@code invertProduct_orthogonal}: computes and stores it. Shared by 3
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_orthogonal_s11a188f4_c2_fma(Float2x3Impl _dst, float _t36, float _t25, float _t26, float _t32, float _r9, float _r11, float _t14, float _r4, float _r8, float _t27_inv, float _t37, float _t35, float _r10, float _t15, float _r5) {
        _dst.m02 = java.lang.Math.fma(_t36, _t25, java.lang.Math.fma(_t26, _t32, -((java.lang.Math.fma(_r9, _r11, _t14) - java.lang.Math.fma(_r4, _r8, _t14)) * _t27_inv)));
        _dst.m12 = java.lang.Math.fma(_t37, _t26, java.lang.Math.fma(_t25, _t35, -((java.lang.Math.fma(_r10, _r8, _t15) - java.lang.Math.fma(_r9, _r5, _t15)) * _t27_inv)));
    }

    /**
     * Private column 2 of {@code invertProduct_orthogonal}: computes and stores it. Shared by 3
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_orthogonal_s11a188f4_c2_mulAdd(Float2x3Impl _dst, float _t36, float _t25, float _t26, float _t32, float _r9, float _r11, float _t14, float _r4, float _r8, float _t27_inv, float _t37, float _t35, float _r10, float _t15, float _r5) {
        _dst.m02 = ((_t36) * (_t25) + (((_t26) * (_t32) - ((((_r9) * (_r11) + (_t14)) - ((_r4) * (_r8) + (_t14))) * _t27_inv))));
        _dst.m12 = ((_t37) * (_t26) + (((_t25) * (_t35) - ((((_r10) * (_r8) + (_t15)) - ((_r9) * (_r5) + (_t15))) * _t27_inv))));
    }

    /** Private tail of {@code invertProduct_orthogonal}; reached only through it. */
    private void invertProduct_orthogonal_s11a188f4_tail_fma(Float2x3Impl _dst, float _r7, float _r0, float _r4, float _r8, float _r9, float _r5, float _r2, float _t12, float _r1, float _r6, float _r3, float _t27, float _r11, float _r10, float _t5, float _t4, float _t0, float _t2) {
        float _t13 = -(_r7 * _r0);
        float _t27_inv = 1.0f / _t27;
        float _t32 = _r11 / _t27;
        float _t35 = _r10 / _t27;
        float _t36 = -(_r4 * _t27_inv);
        float _t37 = -(_r5 * _t27_inv);
        invertProduct_orthogonal_s11a188f4_c0_fma(_dst, _t0, _t32, _t4 * _t36, _t4, _t36, _t37, _t4 * _t35, _t35);
        invertProduct_orthogonal_s11a188f4_c1_fma(_dst, _t2, _t36, _t5 * _t32, _t5, _t32, _t35, _t5 * _t37, _t37);
        invertProduct_orthogonal_s11a188f4_c2_fma(_dst, _t36, java.lang.Math.fma(_r7, _r2, _t12) - java.lang.Math.fma(_r1, _r6, _t12), java.lang.Math.fma(_r3, _r6, _t13) - java.lang.Math.fma(_r7, _r0, _t13), _t32, _r9, _r11, -(_r4 * _r8), _r4, _r8, _t27_inv, _t37, _t35, _r10, -(_r9 * _r5), _r5);
    }

    /** Private tail of {@code invertProduct_orthogonal}; reached only through it. */
    private void invertProduct_orthogonal_s11a188f4_tail_mulAdd(Float2x3Impl _dst, float _r7, float _r0, float _r4, float _r8, float _r9, float _r5, float _r2, float _t12, float _r1, float _r6, float _r3, float _t27, float _r11, float _r10, float _t5, float _t4, float _t0, float _t2) {
        float _t13 = -(_r7 * _r0);
        float _t27_inv = 1.0f / _t27;
        float _t32 = _r11 / _t27;
        float _t35 = _r10 / _t27;
        float _t36 = -(_r4 * _t27_inv);
        float _t37 = -(_r5 * _t27_inv);
        {
            float _t40 = _t4 * _t36;
            float _t39 = _t4 * _t35;
            _dst.m00 = ((_t0) * (_t32) + (_t40)) + ((_t4) * (_t36) - (_t40));
            _dst.m10 = ((_t0) * (_t37) + (_t39)) + ((_t4) * (_t35) - (_t39));
        }
        {
            float _t38 = _t5 * _t32;
            float _t41 = _t5 * _t37;
            _dst.m01 = ((_t2) * (_t36) + (_t38)) + ((_t5) * (_t32) - (_t38));
            _dst.m11 = ((_t2) * (_t35) + (_t41)) + ((_t5) * (_t37) - (_t41));
        }
        invertProduct_orthogonal_s11a188f4_c2_mulAdd(_dst, _t36, ((_r7) * (_r2) + (_t12)) - ((_r1) * (_r6) + (_t12)), ((_r3) * (_r6) + (_t13)) - ((_r7) * (_r0) + (_t13)), _t32, _r9, _r11, -(_r4 * _r8), _r4, _r8, _t27_inv, _t37, _t35, _r10, -(_r9 * _r5), _r5);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r4 = otherImpl.m01;
        float _r5 = otherImpl.m10;
        float _r10 = otherImpl.m00;
        float _r11 = otherImpl.m11;
        float _t11 = -(_r4 * _r5);
        float _t27 = java.lang.Math.fma(_r10, _r11, _t11) - java.lang.Math.fma(_r4, _r5, _t11);
        if (!(java.lang.Math.abs(_t27) > 1.1754944E-38f && java.lang.Math.abs(_t27) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _r0 = this.m11;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m01;
        float _r6 = this.m12;
        float _r7 = this.m02;
        float _r8 = otherImpl.m12;
        float _r9 = otherImpl.m02;
        invertProduct_orthogonal_s11a188f4_tail_fma(d, _r7, _r0, _r4, _r8, _r9, _r5, _r2, -(_r1 * _r6), _r1, _r6, _r3, _t27, _r11, _r10, -_r3, -_r2, _r0, _r1);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r4 = otherImpl.m01;
        float _r5 = otherImpl.m10;
        float _r10 = otherImpl.m00;
        float _r11 = otherImpl.m11;
        float _t11 = -(_r4 * _r5);
        float _t27 = ((_r10) * (_r11) + (_t11)) - ((_r4) * (_r5) + (_t11));
        if (!(java.lang.Math.abs(_t27) > 1.1754944E-38f && java.lang.Math.abs(_t27) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _r0 = this.m11;
        float _r1 = this.m00;
        float _r2 = this.m10;
        float _r3 = this.m01;
        float _r6 = this.m12;
        float _r7 = this.m02;
        float _r8 = otherImpl.m12;
        float _r9 = otherImpl.m02;
        invertProduct_orthogonal_s11a188f4_tail_mulAdd(d, _r7, _r0, _r4, _r8, _r9, _r5, _r2, -(_r1 * _r6), _r1, _r6, _r3, _t27, _r11, _r10, -_r3, -_r2, _r0, _r1);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_identity_translation(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = -otherImpl.m02;
        d.m12 = -otherImpl.m12;
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_translation_identity(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_translation_translation(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = -otherImpl.m02 - this.m02;
        d.m12 = -otherImpl.m12 - this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -(this.m02 * this.m11);
        float _t3 = -(this.m00 * this.m12);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3;
        d.m10 = -_rd1;
        d.m01 = -_rd2;
        d.m11 = _rd0;
        d.m02 = java.lang.Math.fma(_rd2, this.m12, _t2) - java.lang.Math.fma(_rd4, _rd3, _t2);
        d.m12 = java.lang.Math.fma(_rd4, _rd1, _t3) - java.lang.Math.fma(_rd0, this.m12, _t3);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -(this.m02 * this.m11);
        float _t3 = -(this.m00 * this.m12);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3;
        d.m10 = -_rd1;
        d.m01 = -_rd2;
        d.m11 = _rd0;
        d.m02 = ((_rd2) * (this.m12) + (_t2)) - ((_rd4) * (_rd3) + (_t2));
        d.m12 = ((_rd4) * (_rd1) + (_t3)) - ((_rd0) * (this.m12) + (_t3));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -(this.m02 * this.m11);
        float _t3 = -(this.m00 * this.m12);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3;
        d.m10 = -_rd1;
        d.m01 = -_rd2;
        d.m11 = _rd0;
        d.m02 = -otherImpl.m02 + (java.lang.Math.fma(_rd2, this.m12, _t2) - java.lang.Math.fma(_rd4, _rd3, _t2));
        d.m12 = -otherImpl.m12 + (java.lang.Math.fma(_rd4, _rd1, _t3) - java.lang.Math.fma(_rd0, this.m12, _t3));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = -(this.m02 * this.m11);
        float _t3 = -(this.m00 * this.m12);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3;
        d.m10 = -_rd1;
        d.m01 = -_rd2;
        d.m11 = _rd0;
        d.m02 = -otherImpl.m02 + (((_rd2) * (this.m12) + (_t2)) - ((_rd4) * (_rd3) + (_t2)));
        d.m12 = -otherImpl.m12 + (((_rd4) * (_rd1) + (_t3)) - ((_rd0) * (this.m12) + (_t3)));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -((java.lang.Math.fma(_rd4, _rd3, _t4) - java.lang.Math.fma(_rd2, this.m12, _t4)) * _t9_inv);
        d.m12 = -((java.lang.Math.fma(_rd0, this.m12, _t5) - java.lang.Math.fma(_rd4, _rd1, _t5)) * _t9_inv);
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -((((_rd4) * (_rd3) + (_t4)) - ((_rd2) * (this.m12) + (_t4))) * _t9_inv);
        d.m12 = -((((_rd0) * (this.m12) + (_t5)) - ((_rd4) * (_rd1) + (_t5))) * _t9_inv);
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -otherImpl.m02 - (java.lang.Math.fma(_rd4, _rd3, _t4) - java.lang.Math.fma(_rd2, this.m12, _t4)) * _t9_inv;
        d.m12 = -otherImpl.m12 - (java.lang.Math.fma(_rd0, this.m12, _t5) - java.lang.Math.fma(_rd4, _rd1, _t5)) * _t9_inv;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t3 = -(this.m01 * this.m10);
        float _t4 = -(this.m01 * this.m12);
        float _t5 = -(this.m02 * this.m10);
        float _t9 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = this.m02;
        d.m00 = _rd3 / _t9;
        d.m10 = -(_rd1 * _t9_inv);
        d.m01 = -(_rd2 * _t9_inv);
        d.m11 = _rd0 / _t9;
        d.m02 = -otherImpl.m02 - (((_rd4) * (_rd3) + (_t4)) - ((_rd2) * (this.m12) + (_t4))) * _t9_inv;
        d.m12 = -otherImpl.m12 - (((_rd0) * (this.m12) + (_t5)) - ((_rd4) * (_rd1) + (_t5))) * _t9_inv;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}, and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of
     * {@code this * other}, which can be as large as their product.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 invertProduct(Float2x3R other, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(other, dest);
        return invertProduct_mulAdd(other, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 invertProduct_fma(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_general_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, dest);
            return invertProduct_general_translation_fma(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_identity_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = ((Float2x3Impl) other).properties;
                return d;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            return invertProduct_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_orthogonal_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_fma(other, dest);
        return invertProduct_orthogonal_translation_fma(other, dest);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 invertProduct_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_general_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, dest);
            return invertProduct_general_translation_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = ((Float2x3Impl) other).properties;
                return d;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            return invertProduct_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_orthogonal_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_mulAdd(other, dest);
        return invertProduct_orthogonal_translation_mulAdd(other, dest);
    }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of
     * {@code this * other}, which can be as large as their product.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 invertProduct(Float2x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.float2x3());
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(other);
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_general_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, this);
            return invertProduct_general_translation_fma(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_identity_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = 1.0f;
                this.m10 = 0.0f;
                this.m01 = 0.0f;
                this.m11 = 1.0f;
                this.m02 = 0.0f;
                this.m12 = 0.0f;
                this.properties = ((Float2x3Impl) other).properties;
                return this;
            }
            return invertProduct_identity_translation(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            return invertProduct_translation_translation(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_orthogonal_fma(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_fma(other, this);
        return invertProduct_orthogonal_translation_fma(other, this);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 invertProduct_mulAdd(Float2x3R other) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_general_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, this);
            return invertProduct_general_translation_mulAdd(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_identity_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = 1.0f;
                this.m10 = 0.0f;
                this.m01 = 0.0f;
                this.m11 = 1.0f;
                this.m02 = 0.0f;
                this.m12 = 0.0f;
                this.properties = ((Float2x3Impl) other).properties;
                return this;
            }
            return invertProduct_identity_translation(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            return invertProduct_translation_translation(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_orthogonal_mulAdd(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_mulAdd(other, this);
        return invertProduct_orthogonal_translation_mulAdd(other, this);
    }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}, and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of
     * {@code this * other}, which can be as large as their product.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 invertProduct(Float2x3R other, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        return invertProduct(otherImpl.m00, otherImpl.m01, otherImpl.m02, otherImpl.m10, otherImpl.m11, otherImpl.m12, dest);
    }

    /** Private column 0 of {@code invertProduct}: computes and stores it; reached only through it. */
    private void invertProduct_s6d0a8bce_c0_fma(Float2x3Impl _dst, float _t42, float _t43, float _t52, float _t36, float _t37, float _t45, float _t55, float _t41) {
        _dst.m00 = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        _dst.m10 = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
    }

    /** Private column 1 of {@code invertProduct}: computes and stores it; reached only through it. */
    private void invertProduct_s6d0a8bce_c1_fma(Float2x3Impl _dst, float _t42, float _t38, float _t54, float _t44, float _t36, float _t45, float _t53, float _t41) {
        _dst.m01 = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        _dst.m11 = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s6d0a8bce_tail_fma(Float2x3Impl _dst, float m02, float m10, float _t24, float _t25, float m11, float _r5, float _r4, float m00, float m01, float _r1, float _r0, float _r3, float _t8, float _r2, float _t9, float _t10, float m12) {
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = _r5 / _t25;
        float _t38 = _r4 / _t25;
        float _t41 = m00 / _t24;
        float _t42 = -(m01 * _t24_inv);
        float _t43 = -(_r1 * _t25_inv);
        float _t44 = -(_r0 * _t25_inv);
        float _t45 = -(m10 * _t24_inv);
        invertProduct_s6d0a8bce_c0_fma(_dst, _t42, _t43, _t36 * _t37, _t36, _t37, _t45, _t43 * _t41, _t41);
        invertProduct_s6d0a8bce_c1_fma(_dst, _t42, _t38, _t44 * _t36, _t44, _t36, _t45, _t41 * _t38, _t41);
        invertProduct_orthogonal_s11a188f4_c2_fma(_dst, _t42, -((java.lang.Math.fma(_r4, _r3, _t8) - java.lang.Math.fma(_r2, _r1, _t8)) * _t25_inv), -((java.lang.Math.fma(_r2, _r5, _t9) - java.lang.Math.fma(_r0, _r3, _t9)) * _t25_inv), _t36, m02, m11, _t10, m01, m12, _t24_inv, _t45, _t41, m00, -(m02 * m10), m10);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s6d0a8bce_tail_mulAdd(Float2x3Impl _dst, float m02, float m10, float _t24, float _t25, float m11, float _r5, float _r4, float m00, float m01, float _r1, float _r0, float _r3, float _t8, float _r2, float _t9, float _t10, float m12) {
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = _r5 / _t25;
        float _t38 = _r4 / _t25;
        float _t41 = m00 / _t24;
        float _t42 = -(m01 * _t24_inv);
        float _t43 = -(_r1 * _t25_inv);
        float _t44 = -(_r0 * _t25_inv);
        float _t45 = -(m10 * _t24_inv);
        {
            float _t52 = _t36 * _t37;
            float _t55 = _t43 * _t41;
            _dst.m00 = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
            _dst.m10 = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        }
        {
            float _t54 = _t44 * _t36;
            float _t53 = _t41 * _t38;
            _dst.m01 = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
            _dst.m11 = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        }
        invertProduct_orthogonal_s11a188f4_c2_mulAdd(_dst, _t42, -((((_r4) * (_r3) + (_t8)) - ((_r2) * (_r1) + (_t8))) * _t25_inv), -((((_r2) * (_r5) + (_t9)) - ((_r0) * (_r3) + (_t9))) * _t25_inv), _t36, m02, m11, _t10, m01, m12, _t24_inv, _t45, _t41, m00, -(m02 * m10), m10);
    }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of the
     * product, which can be as large as their product.
     * <p>
     * Valid input: the product of this matrix and {@code (m00, m01, m02, m10, m11, m12)} must be
     * invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r4 = this.m00;
        float _r5 = this.m11;
        float _t6 = -(m01 * m10);
        float _t7 = -(_r0 * _r1);
        float _t24 = java.lang.Math.fma(m00, m11, _t6) - java.lang.Math.fma(m01, m10, _t6);
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t25 = java.lang.Math.fma(_r4, _r5, _t7) - java.lang.Math.fma(_r0, _r1, _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _r2 = this.m02;
        float _r3 = this.m12;
        invertProduct_s6d0a8bce_tail_fma(d, m02, m10, _t24, _t25, m11, _r5, _r4, m00, m01, _r1, _r0, _r3, -(_r2 * _r1), _r2, -(_r0 * _r3), -(m01 * m12), m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 invertProduct_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r4 = this.m00;
        float _r5 = this.m11;
        float _t6 = -(m01 * m10);
        float _t7 = -(_r0 * _r1);
        float _t24 = ((m00) * (m11) + (_t6)) - ((m01) * (m10) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t25 = ((_r4) * (_r5) + (_t7)) - ((_r0) * (_r1) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _r2 = this.m02;
        float _r3 = this.m12;
        invertProduct_s6d0a8bce_tail_mulAdd(d, m02, m10, _t24, _t25, m11, _r5, _r4, m00, m01, _r1, _r0, _r3, -(_r2 * _r1), _r2, -(_r0 * _r3), -(m01 * m12), m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Private column 0 of {@code invertProduct}: computes and stores it; reached only through it. */
    private void invertProduct_s5da275e9_c0_fma(Double2x3Impl _dst, float _t42, float _t43, float _t52, float _t36, float _t37, float _t45, float _t55, float _t41) {
        _dst.m00 = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        _dst.m10 = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
    }

    /** Private column 1 of {@code invertProduct}: computes and stores it; reached only through it. */
    private void invertProduct_s5da275e9_c1_fma(Double2x3Impl _dst, float _t42, float _t38, float _t54, float _t44, float _t36, float _t45, float _t53, float _t41) {
        _dst.m01 = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        _dst.m11 = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
    }

    /** Private column 2 of {@code invertProduct}: computes and stores it; reached only through it. */
    private void invertProduct_s5da275e9_c2_fma(Double2x3Impl _dst, float _t42, float _t50, float _t51, float _t36, float m02, float m11, float _t10, float m01, float m12, float _t24_inv, float _t45, float _t41, float m00, float _t11, float m10) {
        _dst.m02 = java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(m02, m11, _t10) - java.lang.Math.fma(m01, m12, _t10)) * _t24_inv)));
        _dst.m12 = java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(m00, m12, _t11) - java.lang.Math.fma(m02, m10, _t11)) * _t24_inv)));
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s5da275e9_tail_fma(Double2x3Impl _dst, float m02, float m10, float _t24, float _t25, float m11, float _r5, float _r4, float m00, float m01, float _r1, float _r0, float _r3, float _t8, float _r2, float _t9, float _t10, float m12) {
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = _r5 / _t25;
        float _t38 = _r4 / _t25;
        float _t41 = m00 / _t24;
        float _t42 = -(m01 * _t24_inv);
        float _t43 = -(_r1 * _t25_inv);
        float _t44 = -(_r0 * _t25_inv);
        float _t45 = -(m10 * _t24_inv);
        invertProduct_s5da275e9_c0_fma(_dst, _t42, _t43, _t36 * _t37, _t36, _t37, _t45, _t43 * _t41, _t41);
        invertProduct_s5da275e9_c1_fma(_dst, _t42, _t38, _t44 * _t36, _t44, _t36, _t45, _t41 * _t38, _t41);
        invertProduct_s5da275e9_c2_fma(_dst, _t42, -((java.lang.Math.fma(_r4, _r3, _t8) - java.lang.Math.fma(_r2, _r1, _t8)) * _t25_inv), -((java.lang.Math.fma(_r2, _r5, _t9) - java.lang.Math.fma(_r0, _r3, _t9)) * _t25_inv), _t36, m02, m11, _t10, m01, m12, _t24_inv, _t45, _t41, m00, -(m02 * m10), m10);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s5da275e9_tail_mulAdd(Double2x3Impl _dst, float m02, float m10, float _t24, float _t25, float m11, float _r5, float _r4, float m00, float m01, float _r1, float _r0, float _r3, float _t8, float _r2, float _t9, float _t10, float m12) {
        float _t24_inv = 1.0f / _t24;
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = _r5 / _t25;
        float _t38 = _r4 / _t25;
        float _t41 = m00 / _t24;
        float _t42 = -(m01 * _t24_inv);
        float _t43 = -(_r1 * _t25_inv);
        float _t44 = -(_r0 * _t25_inv);
        float _t45 = -(m10 * _t24_inv);
        {
            float _t52 = _t36 * _t37;
            float _t55 = _t43 * _t41;
            _dst.m00 = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
            _dst.m10 = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        }
        {
            float _t54 = _t44 * _t36;
            float _t53 = _t41 * _t38;
            _dst.m01 = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
            _dst.m11 = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        }
        {
            float _t50 = -((((_r4) * (_r3) + (_t8)) - ((_r2) * (_r1) + (_t8))) * _t25_inv);
            float _t51 = -((((_r2) * (_r5) + (_t9)) - ((_r0) * (_r3) + (_t9))) * _t25_inv);
            float _t11 = -(m02 * m10);
            _dst.m02 = ((_t42) * (_t50) + (((_t51) * (_t36) - ((((m02) * (m11) + (_t10)) - ((m01) * (m12) + (_t10))) * _t24_inv))));
            _dst.m12 = ((_t45) * (_t51) + (((_t50) * (_t41) - ((((m00) * (m12) + (_t11)) - ((m02) * (m10) + (_t11))) * _t24_inv))));
        }
    }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of the
     * product, which can be as large as their product.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the product of this matrix and {@code (m00, m01, m02, m10, m11, m12)} must be
     * invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r4 = this.m00;
        float _r5 = this.m11;
        float _t6 = -(m01 * m10);
        float _t7 = -(_r0 * _r1);
        float _t24 = java.lang.Math.fma(m00, m11, _t6) - java.lang.Math.fma(m01, m10, _t6);
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t25 = java.lang.Math.fma(_r4, _r5, _t7) - java.lang.Math.fma(_r0, _r1, _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _r2 = this.m02;
        float _r3 = this.m12;
        invertProduct_s5da275e9_tail_fma(d, m02, m10, _t24, _t25, m11, _r5, _r4, m00, m01, _r1, _r0, _r3, -(_r2 * _r1), _r2, -(_r0 * _r3), -(m01 * m12), m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r4 = this.m00;
        float _r5 = this.m11;
        float _t6 = -(m01 * m10);
        float _t7 = -(_r0 * _r1);
        float _t24 = ((m00) * (m11) + (_t6)) - ((m01) * (m10) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t25 = ((_r4) * (_r5) + (_t7)) - ((_r0) * (_r1) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _r2 = this.m02;
        float _r3 = this.m12;
        invertProduct_s5da275e9_tail_mulAdd(d, m02, m10, _t24, _t25, m11, _r5, _r4, m00, m01, _r1, _r0, _r3, -(_r2 * _r1), _r2, -(_r0 * _r3), -(m01 * m12), m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_general_fma(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float _r0 = otherImpl.m00;
        float _r1 = otherImpl.m01;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m12;
        float _r9 = this.m02;
        float _r10 = otherImpl.m02;
        float _r11 = otherImpl.m12;
        float _t0 = unitScale(_r0, _r1, _r0);
        float _t1 = unitScale(_r2, _r3, _r2);
        float _t2 = unitScale(_r4, _r5, _r4);
        float _t3 = unitScale(_r6, _r7, _r6);
        float _t16 = _r1 * _t0;
        float _t17 = _r0 * _t0;
        float _t18 = _r3 * _t1;
        float _t19 = _r2 * _t1;
        float _t20 = _r4 * _t2;
        float _t23 = _r7 * _t3;
        float _t24 = _r8 * _t2;
        float _t25 = _r9 * _t3;
        float _t26 = _r10 * _t0;
        float _t27 = _r11 * _t1;
        float _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_general_s1fef5c72_1_fma(_props, (Float2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _r6 * _t3, _r5 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42));
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float _r0 = otherImpl.m00;
        float _r1 = otherImpl.m01;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _r8 = this.m12;
        float _r9 = this.m02;
        float _r10 = otherImpl.m02;
        float _r11 = otherImpl.m12;
        float _t0 = unitScale(_r0, _r1, _r0);
        float _t1 = unitScale(_r2, _r3, _r2);
        float _t2 = unitScale(_r4, _r5, _r4);
        float _t3 = unitScale(_r6, _r7, _r6);
        float _t16 = _r1 * _t0;
        float _t17 = _r0 * _t0;
        float _t18 = _r3 * _t1;
        float _t19 = _r2 * _t1;
        float _t20 = _r4 * _t2;
        float _t23 = _r7 * _t3;
        float _t24 = _r8 * _t2;
        float _t25 = _r9 * _t3;
        float _t26 = _r10 * _t0;
        float _t27 = _r11 * _t1;
        float _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_general_s1fef5c72_1_mulAdd(_props, (Float2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _r6 * _t3, _r5 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_1_fma(int _props, Float2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56) {
        float _t56_inv = 1.0f / _t56;
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t84 = -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv);
        float _t85 = -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        d.m00 = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        d.m01 = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        d.m02 = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        return invertProduct_degenerate_general_s1fef5c72_2_fma(_props, d, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t71, _t72, _t75, _t77, _t78, -(_t19 * _t0 * _t56_inv), _t84, _t85, _t75 * _t72, _t77 * _t75);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_1_mulAdd(int _props, Float2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56) {
        float _t56_inv = 1.0f / _t56;
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t84 = -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv);
        float _t85 = -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        d.m00 = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        d.m01 = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        d.m02 = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        return invertProduct_degenerate_general_s1fef5c72_2_mulAdd(_props, d, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t71, _t72, _t75, _t77, _t78, -(_t19 * _t0 * _t56_inv), _t84, _t85, _t75 * _t72, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_2_fma(int _props, Float2x3Impl d, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t71, float _t72, float _t75, float _t77, float _t78, float _t79, float _t84, float _t85, float _t87, float _t89) {
        d.m10 = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        d.m11 = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        d.m12 = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        d.properties = _props;
        return d;
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_2_mulAdd(int _props, Float2x3Impl d, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t71, float _t72, float _t75, float _t77, float _t78, float _t79, float _t84, float _t85, float _t87, float _t89) {
        d.m10 = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        d.m11 = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        d.m12 = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t8 = otherImpl.m11 * _t0;
        float _t9 = otherImpl.m00 * _t1;
        float _t10 = otherImpl.m01 * _t1;
        float _t11 = otherImpl.m10 * _t0;
        float _t12 = otherImpl.m02 * _t1;
        float _t13 = otherImpl.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv);
        d.m12 = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv);
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t8 = otherImpl.m11 * _t0;
        float _t9 = otherImpl.m00 * _t1;
        float _t10 = otherImpl.m01 * _t1;
        float _t11 = otherImpl.m10 * _t0;
        float _t12 = otherImpl.m02 * _t1;
        float _t13 = otherImpl.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv);
        d.m12 = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv);
        d.properties = ((Float2x3Impl) other).properties;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_translation}; reached only through it. */
    private void invertProduct_degenerate_translation_s11a188f4_tail_mulAdd(Float2x3Impl _dst, float _t15, float _t6, float _t32, float _t16, float _t32_inv, float _t17, float _t7, float _t37, float _t4, float _t5, float _t18, float _t14, float _t28, float _t19, float _t29) {
        float _t40 = _t15 * _t6 / _t32;
        float _t41 = -(_t16 * _t6 * _t32_inv);
        float _t42 = -(_t17 * _t7 * _t32_inv);
        _dst.m00 = _t37;
        _dst.m10 = _t42;
        _dst.m01 = _t41;
        _dst.m11 = _t40;
        invertProduct_orthogonal_s11a188f4_c2_mulAdd(_dst, _t41, _t4, _t5, _t37, _t18, _t14, _t28, _t16, _t19, _t32_inv, _t42, _t40, _t15, _t29, _t17);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r0 = this.m12;
        float _r1 = this.m02;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = otherImpl.m00;
        float _r5 = otherImpl.m01;
        float _r6 = otherImpl.m02;
        float _r7 = otherImpl.m12;
        float _t6 = unitScale(_r2, _r3, _r2);
        float _t7 = unitScale(_r4, _r5, _r4);
        float _t14 = _r3 * _t6;
        float _t15 = _r4 * _t7;
        float _t16 = _r5 * _t7;
        float _t17 = _r2 * _t6;
        float _t18 = _r6 * _t7;
        float _t19 = _r7 * _t6;
        float _t27 = -(_t16 * _t17);
        float _t32 = java.lang.Math.fma(_t15, _t14, _t27) - java.lang.Math.fma(_t16, _t17, _t27);
        {
            float _t32_inv = 1.0f / _t32;
            float _t37 = _t14 * _t7 / _t32;
            float _t40 = _t15 * _t6 / _t32;
            float _t41 = -(_t16 * _t6 * _t32_inv);
            float _t42 = -(_t17 * _t7 * _t32_inv);
            d.m00 = _t37;
            d.m10 = _t42;
            d.m01 = _t41;
            d.m11 = _t40;
            invertProduct_orthogonal_s11a188f4_c2_fma(d, _t41, -_r0, -_r1, _t37, _t18, _t14, -(_t16 * _t19), _t16, _t19, _t32_inv, _t42, _t40, _t15, -(_t18 * _t17), _t17);
        }
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _r0 = this.m12;
        float _r1 = this.m02;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = otherImpl.m00;
        float _r5 = otherImpl.m01;
        float _r6 = otherImpl.m02;
        float _r7 = otherImpl.m12;
        float _t6 = unitScale(_r2, _r3, _r2);
        float _t7 = unitScale(_r4, _r5, _r4);
        float _t14 = _r3 * _t6;
        float _t15 = _r4 * _t7;
        float _t16 = _r5 * _t7;
        float _t17 = _r2 * _t6;
        float _t18 = _r6 * _t7;
        float _t19 = _r7 * _t6;
        float _t27 = -(_t16 * _t17);
        float _t32 = ((_t15) * (_t14) + (_t27)) - ((_t16) * (_t17) + (_t27));
        invertProduct_degenerate_translation_s11a188f4_tail_mulAdd(d, _t15, _t6, _t32, _t16, 1.0f / _t32, _t17, _t7, _t14 * _t7 / _t32, -_r0, -_r1, _t18, _t14, -(_t16 * _t19), _t19, -(_t18 * _t17));
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_identity_fma(@Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv);
        d.m12 = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv);
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_identity_mulAdd(@Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv);
        d.m12 = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv);
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_translation_fma(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv) - otherImpl.m02;
        d.m12 = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv) - otherImpl.m12;
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m02 * _t1;
        float _t13 = this.m12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        d.m00 = _t8 * _t1 / _t22;
        d.m10 = -(_t11 * _t1 * _t22_inv);
        d.m01 = -(_t10 * _t0 * _t22_inv);
        d.m11 = _t9 * _t0 / _t22;
        d.m02 = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv) - otherImpl.m02;
        d.m12 = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv) - otherImpl.m12;
        d.properties = _props;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_fma(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_fma(other, dest, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_fma(dest, Joml.BIT_AFFINE & q);
            return invertProduct_degenerate_orthogonal_translation_fma(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = ((Float2x3Impl) other).properties;
                return d;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            return invertProduct_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_fma(dest, Joml.BIT_ORTHOGONAL & q);
        return invertProduct_degenerate_orthogonal_translation_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_mulAdd(other, dest, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_mulAdd(dest, Joml.BIT_AFFINE & q);
            return invertProduct_degenerate_orthogonal_translation_mulAdd(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = ((Float2x3Impl) other).properties;
                return d;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            return invertProduct_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_mulAdd(dest, Joml.BIT_ORTHOGONAL & q);
        return invertProduct_degenerate_orthogonal_translation_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_fma(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _r4 = this.m12;
        float _r5 = this.m02;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = _r0 * _t2;
        float _t21 = _r2 * _t3;
        float _t22 = _r1 * _t2;
        float _t23 = _r3 * _t3;
        float _t24 = _r4 * _t2;
        float _t25 = _r5 * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        return invertProduct_degenerate_sc754e14d_1_fma((Float2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _r4 = this.m12;
        float _r5 = this.m02;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = _r0 * _t2;
        float _t21 = _r2 * _t3;
        float _t22 = _r1 * _t2;
        float _t23 = _r3 * _t3;
        float _t24 = _r4 * _t2;
        float _t25 = _r5 * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        return invertProduct_degenerate_sc754e14d_1_mulAdd((Float2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_1_fma(Float2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70) {
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv);
        float _t85 = -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        d.m00 = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        d.m01 = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        d.m02 = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        d.m10 = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        return invertProduct_degenerate_sc754e14d_2_fma(d, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t72, _t75, _t78, _t79, _t84, _t85, _t75 * _t72);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_1_mulAdd(Float2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70) {
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv);
        float _t85 = -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        d.m00 = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        d.m01 = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        d.m02 = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        d.m10 = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        float _t87 = _t75 * _t72;
        d.m11 = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        d.m12 = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_2_fma(Float2x3Impl d, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t72, float _t75, float _t78, float _t79, float _t84, float _t85, float _t87) {
        d.m11 = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        d.m12 = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_fma(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _r4 = this.m12;
        float _r5 = this.m02;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = _r0 * _t2;
        float _t21 = _r2 * _t3;
        float _t22 = _r1 * _t2;
        float _t23 = _r3 * _t3;
        float _t24 = _r4 * _t2;
        float _t25 = _r5 * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        return invertProduct_degenerate_s6ae5eb0a_1_fma((Double2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _r4 = this.m12;
        float _r5 = this.m02;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = _r0 * _t2;
        float _t21 = _r2 * _t3;
        float _t22 = _r1 * _t2;
        float _t23 = _r3 * _t3;
        float _t24 = _r4 * _t2;
        float _t25 = _r5 * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        return invertProduct_degenerate_s6ae5eb0a_1_mulAdd((Double2x3Impl) dest, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_1_fma(Double2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70) {
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv);
        float _t85 = -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        d.m00 = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        d.m01 = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        d.m02 = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        d.m10 = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        return invertProduct_degenerate_s6ae5eb0a_2_fma(d, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t72, _t75, _t78, _t79, _t84, _t85, _t75 * _t72);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_1_mulAdd(Double2x3Impl d, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70) {
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv);
        float _t85 = -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        d.m00 = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        d.m01 = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        d.m02 = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        d.m10 = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        float _t87 = _t75 * _t72;
        d.m11 = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        d.m12 = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_2_fma(Double2x3Impl d, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t72, float _t75, float _t78, float _t79, float _t84, float _t85, float _t87) {
        d.m11 = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        d.m12 = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_identity(@Mutated Float3x2 dest) {
        Float3x2Impl d = (Float3x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        return d;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_translation(@Mutated Float3x2 dest) {
        Float3x2Impl d = (Float3x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = this.m02;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = this.m12;
        return d;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_general(@Mutated Float3x2 dest) {
        Float3x2Impl d = (Float3x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m20 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        return d;
    }

    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x2 transpose(@Mutated Float3x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return transpose_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transpose_identity(dest);
        return transpose_translation(dest);
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
    public Double3x2 transpose(@Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m20 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
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
    public Float2x3 add(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = otherImpl.m00 + this.m00;
        d.m10 = otherImpl.m10 + this.m10;
        d.m01 = otherImpl.m01 + this.m01;
        d.m11 = otherImpl.m11 + this.m11;
        d.m02 = otherImpl.m02 + this.m02;
        d.m12 = otherImpl.m12 + this.m12;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
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
    public Double2x3 add(Float2x3R other, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m02 = otherImpl.m02;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        float m12 = otherImpl.m12;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) to this
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
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 add(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) to this
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
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 add(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_fma(float scalar, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = scalar;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_mulAdd(float scalar, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = scalar;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_fma(float scalar, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_mulAdd(float scalar, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 mul(float scalar, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? mul_general_fma(scalar, dest) : mul_general_mulAdd(scalar, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = scalar;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = scalar;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? mul_translation_fma(scalar, dest) : mul_translation_mulAdd(scalar, dest));
    }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 mul(float scalar) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_fma(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = scalar;
                this.m11 = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            {
                this.m00 = scalar;
                this.m11 = scalar;
                this.m02 = scalar * this.m02;
                this.m12 = scalar * this.m12;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = scalar;
                this.m11 = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            {
                this.m00 = scalar;
                this.m11 = scalar;
                this.m02 = scalar * this.m02;
                this.m12 = scalar * this.m12;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
        }
    }

    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
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
    public Double2x3 mul(float scalar, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
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
    public Float2x3 negate(@Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_AFFINE;
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
    public Double2x3 negate(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_AFFINE;
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
    public Float2x3 sub(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00 - otherImpl.m00;
        d.m10 = this.m10 - otherImpl.m10;
        d.m01 = this.m01 - otherImpl.m01;
        d.m11 = this.m11 - otherImpl.m11;
        d.m02 = this.m02 - otherImpl.m02;
        d.m12 = this.m12 - otherImpl.m12;
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
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
    public Double2x3 sub(Float2x3R other, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m02 = otherImpl.m02;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        float m12 = otherImpl.m12;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) from
     * this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) from
     * this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.properties = Joml.BIT_AFFINE;
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
    @Mutated public Float2x3 set(Float2x3R v) {
        Float2x3Impl vImpl = (Float2x3Impl) v;
        this.m00 = vImpl.m00;
        this.m10 = vImpl.m10;
        this.m01 = vImpl.m01;
        this.m11 = vImpl.m11;
        this.m02 = vImpl.m02;
        this.m12 = vImpl.m12;
        this.properties = ((Float2x3Impl) v).properties;
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
     * @return this
     */
    @Mutated public Float2x3 set(float m00, float m01, float m02, float m10, float m11, float m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        this.properties = determineProperties();
        return this;
    }

    /**
     * Set this matrix to the given 2x2 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Float2x3 set(Float2x2R m) {
        Float2x2Impl mImpl = (Float2x2Impl) m;
        this.m00 = mImpl.m00;
        this.m10 = mImpl.m10;
        this.m01 = mImpl.m01;
        this.m11 = mImpl.m11;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = determineProperties();
        return this;
    }

    /**
     * Set this matrix to the given 3x3 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Float2x3 set(Float3x3R m) {
        Float3x3Impl mImpl = (Float3x3Impl) m;
        this.m00 = mImpl.m00;
        this.m10 = mImpl.m10;
        this.m01 = mImpl.m01;
        this.m11 = mImpl.m11;
        this.m02 = mImpl.m02;
        this.m12 = mImpl.m12;
        this.properties = determineProperties();
        return this;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 withTranslation(Float2R t, @Mutated Float2x3 dest) {
        Float2Impl tImpl = (Float2Impl) t;
        float tX = tImpl.x;
        float tY = tImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 withTranslation(Float2R t, @Mutated Double2x3 dest) {
        Float2Impl tImpl = (Float2Impl) t;
        float tX = tImpl.x;
        float tY = tImpl.y;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 withTranslation(Float2R t) {
        Float2Impl tImpl = (Float2Impl) t;
        float tX = tImpl.x;
        float tY = tImpl.y;
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            this.m02 = tX;
            this.m12 = tY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m02 = tX;
            this.m12 = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        this.m02 = tX;
        this.m12 = tY;
        this.properties = Joml.BIT_ORTHOGONAL;
        return this;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 withTranslation(float tX, float tY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 withTranslation(float tX, float tY) {
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            this.m02 = tX;
            this.m12 = tY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m02 = tX;
            this.m12 = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        this.m02 = tX;
        this.m12 = tY;
        this.properties = Joml.BIT_ORTHOGONAL;
        return this;
    }

    /**
     * Replace the translation of this matrix, keeping every other element.
     * <p>
     * Only the first two elements of the last column change, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this replaces the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 withTranslation(float tX, float tY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Convert this matrix to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 toDouble(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = this.properties;
        return d;
    }

    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_identity(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }

    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_general(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 to2x2(@Mutated Float2x2 dest) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x2_identity(dest);
        return to2x2_general(dest);
    }

    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 to2x2(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code to3x3} and {@code mul}; reached only through them.
     */
    private Float3x3 to3x3_orthogonal_general(@Mutated Float3x3 dest, int _props) {
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 to3x3(@Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return to3x3_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float3x3Impl d = (Float3x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m20 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m21 = 0.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.m22 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }

    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity, and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 to3x3(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float2x3 makeIdentity() {
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = Joml.BIT_IDENTITY;
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
    public Float2x3 lerp(Float2x3R other, float t, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(other, t, dest);
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = java.lang.Math.fma(t, otherImpl.m00 - this.m00, this.m00);
        d.m10 = java.lang.Math.fma(t, otherImpl.m10 - this.m10, this.m10);
        d.m01 = java.lang.Math.fma(t, otherImpl.m01 - this.m01, this.m01);
        d.m11 = java.lang.Math.fma(t, otherImpl.m11 - this.m11, this.m11);
        d.m02 = java.lang.Math.fma(t, otherImpl.m02 - this.m02, this.m02);
        d.m12 = java.lang.Math.fma(t, otherImpl.m12 - this.m12, this.m12);
        d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x3Impl) other).properties) >> 1);
        return d;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 lerp_mulAdd(Float2x3R other, float t, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = ((t) * (otherImpl.m00 - this.m00) + (this.m00));
        d.m10 = ((t) * (otherImpl.m10 - this.m10) + (this.m10));
        d.m01 = ((t) * (otherImpl.m01 - this.m01) + (this.m01));
        d.m11 = ((t) * (otherImpl.m11 - this.m11) + (this.m11));
        d.m02 = ((t) * (otherImpl.m02 - this.m02) + (this.m02));
        d.m12 = ((t) * (otherImpl.m12 - this.m12) + (this.m12));
        d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x3Impl) other).properties) >> 1);
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
    public Double2x3 lerp(Float2x3R other, float t, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        return lerp(otherImpl.m00, otherImpl.m01, otherImpl.m02, otherImpl.m10, otherImpl.m11, otherImpl.m12, t, dest);
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float t, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.m02 = java.lang.Math.fma(t, m02 - this.m02, this.m02);
            d.m12 = java.lang.Math.fma(t, m12 - this.m12, this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.m02 = ((t) * (m02 - this.m02) + (this.m02));
            d.m12 = ((t) * (m12 - this.m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (1e8 towards 1 ends at 0).
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
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float t, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.m02 = java.lang.Math.fma(t, m02 - this.m02, this.m02);
            d.m12 = java.lang.Math.fma(t, m12 - this.m12, this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.m02 = ((t) * (m02 - this.m02) + (this.m02));
            d.m12 = ((t) * (m12 - this.m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_fma(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        float _rd3 = this.m00;
        float _rd4 = this.m10;
        float _rd5 = this.m01;
        float _rd6 = this.m11;
        d.m00 = java.lang.Math.fma(_rd0, _rd3, rightImpl.m10 * _rd5);
        d.m10 = java.lang.Math.fma(_rd0, _rd4, rightImpl.m10 * _rd6);
        d.m01 = java.lang.Math.fma(_rd1, _rd3, rightImpl.m11 * _rd5);
        d.m11 = java.lang.Math.fma(_rd1, _rd4, rightImpl.m11 * _rd6);
        d.m02 = java.lang.Math.fma(_rd2, _rd3, java.lang.Math.fma(rightImpl.m12, _rd5, this.m02));
        d.m12 = java.lang.Math.fma(_rd2, _rd4, java.lang.Math.fma(rightImpl.m12, _rd6, this.m12));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        float _rd3 = this.m00;
        float _rd4 = this.m10;
        float _rd5 = this.m01;
        float _rd6 = this.m11;
        d.m00 = ((_rd0) * (_rd3) + (rightImpl.m10 * _rd5));
        d.m10 = ((_rd0) * (_rd4) + (rightImpl.m10 * _rd6));
        d.m01 = ((_rd1) * (_rd3) + (rightImpl.m11 * _rd5));
        d.m11 = ((_rd1) * (_rd4) + (rightImpl.m11 * _rd6));
        d.m02 = ((_rd2) * (_rd3) + (((rightImpl.m12) * (_rd5) + (this.m02))));
        d.m12 = ((_rd2) * (_rd4) + (((rightImpl.m12) * (_rd6) + (this.m12))));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_fma(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = rightImpl.m10;
        d.m01 = rightImpl.m01;
        d.m11 = rightImpl.m11;
        d.m02 = rightImpl.m02 + this.m02;
        d.m12 = rightImpl.m12 + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = rightImpl.m10;
        d.m01 = rightImpl.m01;
        d.m11 = rightImpl.m11;
        d.m02 = rightImpl.m02 + this.m02;
        d.m12 = rightImpl.m12 + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_fma(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        float _rd3 = this.m10;
        float _rd4 = this.m11;
        d.m00 = java.lang.Math.fma(_rd0, _rd4, rightImpl.m10 * -_rd3);
        d.m10 = java.lang.Math.fma(_rd0, _rd3, rightImpl.m10 * _rd4);
        d.m01 = java.lang.Math.fma(_rd1, _rd4, rightImpl.m11 * -_rd3);
        d.m11 = java.lang.Math.fma(_rd1, _rd3, rightImpl.m11 * _rd4);
        d.m02 = java.lang.Math.fma(rightImpl.m12, -_rd3, java.lang.Math.fma(_rd2, _rd4, this.m02));
        d.m12 = java.lang.Math.fma(_rd2, _rd3, java.lang.Math.fma(rightImpl.m12, _rd4, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        float _rd3 = this.m10;
        float _rd4 = this.m11;
        d.m00 = ((_rd0) * (_rd4) - (rightImpl.m10 * _rd3));
        d.m10 = ((_rd0) * (_rd3) + (rightImpl.m10 * _rd4));
        d.m01 = ((_rd1) * (_rd4) - (rightImpl.m11 * _rd3));
        d.m11 = ((_rd1) * (_rd3) + (rightImpl.m11 * _rd4));
        d.m02 = ((((_rd2) * (_rd4) + (this.m02))) - (rightImpl.m12) * (_rd3));
        d.m12 = ((_rd2) * (_rd3) + (((rightImpl.m12) * (_rd4) + (this.m12))));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_translation(Float2x3R right, @Mutated Float2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = rightImpl.m02 + this.m02;
        d.m12 = rightImpl.m12 + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_translation_fma(Float2x3R right, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = rightImpl.m02;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = java.lang.Math.fma(_rd4, _rd0, java.lang.Math.fma(rightImpl.m12, _rd2, this.m02));
        d.m12 = java.lang.Math.fma(_rd4, _rd1, java.lang.Math.fma(rightImpl.m12, _rd3, this.m12));
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_translation_mulAdd(Float2x3R right, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        float _rd4 = rightImpl.m02;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = ((_rd4) * (_rd0) + (((rightImpl.m12) * (_rd2) + (this.m02))));
        d.m12 = ((_rd4) * (_rd1) + (((rightImpl.m12) * (_rd3) + (this.m12))));
        d.properties = _props;
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
    public Float2x3 mul(Float2x3R right, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            int q = ((Float2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, dest, Joml.BIT_AFFINE & q);
                return mul_general_fma(right, dest);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
                return mul_translation_fma(right, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal_fma(right, dest);
        } else {
            int p = this.properties;
            int q = ((Float2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_AFFINE & q);
                return mul_general_mulAdd(right, dest);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
                return mul_translation_mulAdd(right, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal_mulAdd(right, dest);
        }
    }

    /**
     * Multiply this matrix by {@code right}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 mul(Float2x3R right) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
            int p = this.properties;
            int q = ((Float2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, this, Joml.BIT_AFFINE & q);
                return mul_general_fma(right, this);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
                return mul_translation_fma(right, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, this, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal_fma(right, this);
        } else {
            if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
            int p = this.properties;
            int q = ((Float2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, this, Joml.BIT_AFFINE & q);
                return mul_general_mulAdd(right, this);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
                return mul_translation_mulAdd(right, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, this, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal_mulAdd(right, this);
        }
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
    public Double2x3 mul(Float2x3R right, @Mutated Double2x3 dest) {
        Float2x3Impl rightImpl = (Float2x3Impl) right;
        return mul(rightImpl.m00, rightImpl.m01, rightImpl.m02, rightImpl.m10, rightImpl.m11, rightImpl.m12, dest);
    }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}) and store the result in {@code dest}.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 mul(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m10;
            float _rd2 = this.m01;
            float _rd3 = this.m11;
            d.m00 = java.lang.Math.fma(m00, _rd0, m10 * _rd2);
            d.m10 = java.lang.Math.fma(m00, _rd1, m10 * _rd3);
            d.m01 = java.lang.Math.fma(m01, _rd0, m11 * _rd2);
            d.m11 = java.lang.Math.fma(m01, _rd1, m11 * _rd3);
            d.m02 = java.lang.Math.fma(m02, _rd0, java.lang.Math.fma(m12, _rd2, this.m02));
            d.m12 = java.lang.Math.fma(m02, _rd1, java.lang.Math.fma(m12, _rd3, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m10;
            float _rd2 = this.m01;
            float _rd3 = this.m11;
            d.m00 = ((m00) * (_rd0) + (m10 * _rd2));
            d.m10 = ((m00) * (_rd1) + (m10 * _rd3));
            d.m01 = ((m01) * (_rd0) + (m11 * _rd2));
            d.m11 = ((m01) * (_rd1) + (m11 * _rd3));
            d.m02 = ((m02) * (_rd0) + (((m12) * (_rd2) + (this.m02))));
            d.m12 = ((m02) * (_rd1) + (((m12) * (_rd3) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}) and store the result in {@code dest}.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 mul(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m10 * this.m01);
            d.m10 = java.lang.Math.fma(m00, this.m10, m10 * this.m11);
            d.m01 = java.lang.Math.fma(m01, this.m00, m11 * this.m01);
            d.m11 = java.lang.Math.fma(m01, this.m10, m11 * this.m11);
            d.m02 = java.lang.Math.fma(m02, this.m00, java.lang.Math.fma(m12, this.m01, this.m02));
            d.m12 = java.lang.Math.fma(m02, this.m10, java.lang.Math.fma(m12, this.m11, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m10 * this.m01));
            d.m10 = ((m00) * (this.m10) + (m10 * this.m11));
            d.m01 = ((m01) * (this.m00) + (m11 * this.m01));
            d.m11 = ((m01) * (this.m10) + (m11 * this.m11));
            d.m02 = ((m02) * (this.m00) + (((m12) * (this.m01) + (this.m02))));
            d.m12 = ((m02) * (this.m10) + (((m12) * (this.m11) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code mul} and {@code preMul}; reached only through
     * them.
     */
    private Float2x3 mul_identity_self(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = rightImpl.m10;
        d.m01 = rightImpl.m01;
        d.m11 = rightImpl.m11;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_fma(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = rightImpl.m10;
        d.m01 = rightImpl.m01;
        d.m11 = rightImpl.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = rightImpl.m10;
        d.m01 = rightImpl.m01;
        d.m11 = rightImpl.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_fma(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        d.m00 = java.lang.Math.fma(rightImpl.m00, this.m11, rightImpl.m10 * -_rd0);
        d.m10 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m11);
        d.m01 = java.lang.Math.fma(rightImpl.m01, this.m11, rightImpl.m11 * -_rd0);
        d.m11 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        d.m00 = ((rightImpl.m00) * (this.m11) - (rightImpl.m10 * _rd0));
        d.m10 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m11));
        d.m01 = ((rightImpl.m01) * (this.m11) - (rightImpl.m11 * _rd0));
        d.m11 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m11));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_self_fma(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        d.m00 = java.lang.Math.fma(rightImpl.m00, this.m11, rightImpl.m10 * -_rd0);
        d.m10 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m11);
        d.m01 = java.lang.Math.fma(rightImpl.m01, this.m11, rightImpl.m11 * -_rd0);
        d.m11 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m11);
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_self_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        d.m00 = ((rightImpl.m00) * (this.m11) - (rightImpl.m10 * _rd0));
        d.m10 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m11));
        d.m01 = ((rightImpl.m01) * (this.m11) - (rightImpl.m11 * _rd0));
        d.m11 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m11));
        d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_fma(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m01);
        d.m10 = java.lang.Math.fma(rightImpl.m00, _rd1, rightImpl.m10 * this.m11);
        d.m01 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m01);
        d.m11 = java.lang.Math.fma(rightImpl.m01, _rd1, rightImpl.m11 * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m01));
        d.m10 = ((rightImpl.m00) * (_rd1) + (rightImpl.m10 * this.m11));
        d.m01 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m01));
        d.m11 = ((rightImpl.m01) * (_rd1) + (rightImpl.m11 * this.m11));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_self_fma(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m01);
        d.m10 = java.lang.Math.fma(rightImpl.m00, _rd1, rightImpl.m10 * this.m11);
        d.m01 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m01);
        d.m11 = java.lang.Math.fma(rightImpl.m01, _rd1, rightImpl.m11 * this.m11);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_self_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m01));
        d.m10 = ((rightImpl.m00) * (_rd1) + (rightImpl.m10 * this.m11));
        d.m01 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m01));
        d.m11 = ((rightImpl.m01) * (_rd1) + (rightImpl.m11 * this.m11));
        d.properties = Joml.BIT_AFFINE;
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
    public Float2x3 mul(Float2x2R right, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? mul_general_fma(right, dest) : mul_general_mulAdd(right, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl rightImpl = (Float2x2Impl) right;
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = rightImpl.m00;
            d.m10 = rightImpl.m10;
            d.m01 = rightImpl.m01;
            d.m11 = rightImpl.m11;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? mul_translation_fma(right, dest) : mul_translation_mulAdd(right, dest));
        return (org.joml2.internal.FmaSwitch.ON ? mul_orthogonal_fma(right, dest) : mul_orthogonal_mulAdd(right, dest));
    }

    /**
     * Multiply this matrix by {@code right}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 mul(Float2x2R right) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return mul_general_self_fma(right, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                Float2x2Impl rightImpl = (Float2x2Impl) right;
                this.m00 = rightImpl.m00;
                this.m10 = rightImpl.m10;
                this.m01 = rightImpl.m01;
                this.m11 = rightImpl.m11;
                this.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
                return this;
            }
            return mul_orthogonal_self_fma(right, this);
        } else {
            if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return mul_general_self_mulAdd(right, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                Float2x2Impl rightImpl = (Float2x2Impl) right;
                this.m00 = rightImpl.m00;
                this.m10 = rightImpl.m10;
                this.m01 = rightImpl.m01;
                this.m11 = rightImpl.m11;
                this.properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
                return this;
            }
            return mul_orthogonal_self_mulAdd(right, this);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 mul(Float2x2R right, @Mutated Double2x3 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(rightImpl.m00, this.m00, rightImpl.m10 * this.m01);
            d.m10 = java.lang.Math.fma(rightImpl.m00, this.m10, rightImpl.m10 * this.m11);
            d.m01 = java.lang.Math.fma(rightImpl.m01, this.m00, rightImpl.m11 * this.m01);
            d.m11 = java.lang.Math.fma(rightImpl.m01, this.m10, rightImpl.m11 * this.m11);
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((rightImpl.m00) * (this.m00) + (rightImpl.m10 * this.m01));
            d.m10 = ((rightImpl.m00) * (this.m10) + (rightImpl.m10 * this.m11));
            d.m01 = ((rightImpl.m01) * (this.m00) + (rightImpl.m11 * this.m01));
            d.m11 = ((rightImpl.m01) * (this.m10) + (rightImpl.m11 * this.m11));
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_fma(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        d.m00 = java.lang.Math.fma(rightImpl.m20, this.m02, java.lang.Math.fma(_rd0, this.m00, rightImpl.m10 * this.m01));
        d.m10 = java.lang.Math.fma(rightImpl.m20, this.m12, java.lang.Math.fma(_rd0, this.m10, rightImpl.m10 * this.m11));
        d.m20 = rightImpl.m20;
        d.m01 = java.lang.Math.fma(rightImpl.m21, this.m02, java.lang.Math.fma(_rd1, this.m00, rightImpl.m11 * this.m01));
        d.m11 = java.lang.Math.fma(rightImpl.m21, this.m12, java.lang.Math.fma(_rd1, this.m10, rightImpl.m11 * this.m11));
        d.m21 = rightImpl.m21;
        d.m02 = java.lang.Math.fma(rightImpl.m22, this.m02, java.lang.Math.fma(_rd2, this.m00, rightImpl.m12 * this.m01));
        d.m12 = java.lang.Math.fma(rightImpl.m22, this.m12, java.lang.Math.fma(_rd2, this.m10, rightImpl.m12 * this.m11));
        d.m22 = rightImpl.m22;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        d.m00 = ((rightImpl.m20) * (this.m02) + (((_rd0) * (this.m00) + (rightImpl.m10 * this.m01))));
        d.m10 = ((rightImpl.m20) * (this.m12) + (((_rd0) * (this.m10) + (rightImpl.m10 * this.m11))));
        d.m20 = rightImpl.m20;
        d.m01 = ((rightImpl.m21) * (this.m02) + (((_rd1) * (this.m00) + (rightImpl.m11 * this.m01))));
        d.m11 = ((rightImpl.m21) * (this.m12) + (((_rd1) * (this.m10) + (rightImpl.m11 * this.m11))));
        d.m21 = rightImpl.m21;
        d.m02 = ((rightImpl.m22) * (this.m02) + (((_rd2) * (this.m00) + (rightImpl.m12 * this.m01))));
        d.m12 = ((rightImpl.m22) * (this.m12) + (((_rd2) * (this.m10) + (rightImpl.m12 * this.m11))));
        d.m22 = rightImpl.m22;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_fma(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = java.lang.Math.fma(rightImpl.m20, this.m02, rightImpl.m00);
        d.m10 = java.lang.Math.fma(rightImpl.m20, this.m12, rightImpl.m10);
        d.m20 = rightImpl.m20;
        d.m01 = java.lang.Math.fma(rightImpl.m21, this.m02, rightImpl.m01);
        d.m11 = java.lang.Math.fma(rightImpl.m21, this.m12, rightImpl.m11);
        d.m21 = rightImpl.m21;
        d.m02 = java.lang.Math.fma(rightImpl.m22, this.m02, rightImpl.m02);
        d.m12 = java.lang.Math.fma(rightImpl.m22, this.m12, rightImpl.m12);
        d.m22 = rightImpl.m22;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = ((rightImpl.m20) * (this.m02) + (rightImpl.m00));
        d.m10 = ((rightImpl.m20) * (this.m12) + (rightImpl.m10));
        d.m20 = rightImpl.m20;
        d.m01 = ((rightImpl.m21) * (this.m02) + (rightImpl.m01));
        d.m11 = ((rightImpl.m21) * (this.m12) + (rightImpl.m11));
        d.m21 = rightImpl.m21;
        d.m02 = ((rightImpl.m22) * (this.m02) + (rightImpl.m02));
        d.m12 = ((rightImpl.m22) * (this.m12) + (rightImpl.m12));
        d.m22 = rightImpl.m22;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_fma(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        d.m00 = java.lang.Math.fma(rightImpl.m20, this.m02, java.lang.Math.fma(_rd0, this.m11, rightImpl.m10 * -this.m10));
        d.m10 = java.lang.Math.fma(rightImpl.m20, this.m12, java.lang.Math.fma(_rd0, this.m10, rightImpl.m10 * this.m11));
        d.m20 = rightImpl.m20;
        d.m01 = java.lang.Math.fma(rightImpl.m21, this.m02, java.lang.Math.fma(_rd1, this.m11, rightImpl.m11 * -this.m10));
        d.m11 = java.lang.Math.fma(rightImpl.m21, this.m12, java.lang.Math.fma(_rd1, this.m10, rightImpl.m11 * this.m11));
        d.m21 = rightImpl.m21;
        d.m02 = java.lang.Math.fma(rightImpl.m22, this.m02, java.lang.Math.fma(_rd2, this.m11, rightImpl.m12 * -this.m10));
        d.m12 = java.lang.Math.fma(rightImpl.m22, this.m12, java.lang.Math.fma(_rd2, this.m10, rightImpl.m12 * this.m11));
        d.m22 = rightImpl.m22;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = rightImpl.m02;
        d.m00 = ((rightImpl.m20) * (this.m02) + (((_rd0) * (this.m11) - (rightImpl.m10 * this.m10))));
        d.m10 = ((rightImpl.m20) * (this.m12) + (((_rd0) * (this.m10) + (rightImpl.m10 * this.m11))));
        d.m20 = rightImpl.m20;
        d.m01 = ((rightImpl.m21) * (this.m02) + (((_rd1) * (this.m11) - (rightImpl.m11 * this.m10))));
        d.m11 = ((rightImpl.m21) * (this.m12) + (((_rd1) * (this.m10) + (rightImpl.m11 * this.m11))));
        d.m21 = rightImpl.m21;
        d.m02 = ((rightImpl.m22) * (this.m02) + (((_rd2) * (this.m11) - (rightImpl.m12 * this.m10))));
        d.m12 = ((rightImpl.m22) * (this.m12) + (((_rd2) * (this.m10) + (rightImpl.m12 * this.m11))));
        d.m22 = rightImpl.m22;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float3x3R right, @Mutated Float3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = rightImpl.m02 + this.m02;
        d.m12 = rightImpl.m12 + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation_fma(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m02;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = java.lang.Math.fma(_rd0, this.m00, java.lang.Math.fma(rightImpl.m12, this.m01, this.m02));
        d.m12 = java.lang.Math.fma(_rd0, this.m10, java.lang.Math.fma(rightImpl.m12, this.m11, this.m12));
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation_mulAdd(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = rightImpl.m02;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = ((_rd0) * (this.m00) + (((rightImpl.m12) * (this.m01) + (this.m02))));
        d.m12 = ((_rd0) * (this.m10) + (((rightImpl.m12) * (this.m11) + (this.m12))));
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Multiply this matrix by the given matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 mul(Float3x3R right, @Mutated Float3x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        int p = this.properties;
        int q = ((Float3x3Impl) right).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_fma(right, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
            return mul_orthogonal_translation_fma(right, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_translation_fma(right, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float3x3Impl d = (Float3x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m20 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m21 = 0.0f;
                d.m02 = this.m02;
                d.m12 = this.m12;
                d.m22 = 1.0f;
                d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
                return d;
            }
            return mul_translation_translation(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_orthogonal_fma(right, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
        return mul_orthogonal_translation_fma(right, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 mul_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float3x3Impl) right).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(right, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
            return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_translation_mulAdd(right, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float3x3Impl d = (Float3x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m20 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m21 = 0.0f;
                d.m02 = this.m02;
                d.m12 = this.m12;
                d.m22 = 1.0f;
                d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
                return d;
            }
            return mul_translation_translation(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_orthogonal_mulAdd(right, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
        return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /**
     * Multiply this matrix by the given matrix and store the result in {@code dest}.
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
    public Double3x3 mul(Float3x3R right, @Mutated Double3x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 mul_fma(Float3x3R right, @Mutated Double3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = java.lang.Math.fma(rightImpl.m20, this.m02, java.lang.Math.fma(rightImpl.m00, this.m00, rightImpl.m10 * this.m01));
        d.m10 = java.lang.Math.fma(rightImpl.m20, this.m12, java.lang.Math.fma(rightImpl.m00, this.m10, rightImpl.m10 * this.m11));
        d.m20 = rightImpl.m20;
        d.m01 = java.lang.Math.fma(rightImpl.m21, this.m02, java.lang.Math.fma(rightImpl.m01, this.m00, rightImpl.m11 * this.m01));
        d.m11 = java.lang.Math.fma(rightImpl.m21, this.m12, java.lang.Math.fma(rightImpl.m01, this.m10, rightImpl.m11 * this.m11));
        d.m21 = rightImpl.m21;
        d.m02 = java.lang.Math.fma(rightImpl.m22, this.m02, java.lang.Math.fma(rightImpl.m02, this.m00, rightImpl.m12 * this.m01));
        d.m12 = java.lang.Math.fma(rightImpl.m22, this.m12, java.lang.Math.fma(rightImpl.m02, this.m10, rightImpl.m12 * this.m11));
        d.m22 = rightImpl.m22;
        d.properties = 0;
        return d;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 mul_mulAdd(Float3x3R right, @Mutated Double3x3 dest) {
        Float3x3Impl rightImpl = (Float3x3Impl) right;
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = ((rightImpl.m20) * (this.m02) + (((rightImpl.m00) * (this.m00) + (rightImpl.m10 * this.m01))));
        d.m10 = ((rightImpl.m20) * (this.m12) + (((rightImpl.m00) * (this.m10) + (rightImpl.m10 * this.m11))));
        d.m20 = rightImpl.m20;
        d.m01 = ((rightImpl.m21) * (this.m02) + (((rightImpl.m01) * (this.m00) + (rightImpl.m11 * this.m01))));
        d.m11 = ((rightImpl.m21) * (this.m12) + (((rightImpl.m01) * (this.m10) + (rightImpl.m11 * this.m11))));
        d.m21 = rightImpl.m21;
        d.m02 = ((rightImpl.m22) * (this.m02) + (((rightImpl.m02) * (this.m00) + (rightImpl.m12 * this.m01))));
        d.m12 = ((rightImpl.m22) * (this.m12) + (((rightImpl.m02) * (this.m10) + (rightImpl.m12 * this.m11))));
        d.m22 = rightImpl.m22;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m00;
        float _rd5 = this.m01;
        float _rd6 = this.m02;
        d.m00 = java.lang.Math.fma(_rd0, _rd4, _rd2 * this.m10);
        d.m10 = java.lang.Math.fma(_rd1, _rd4, _rd3 * this.m10);
        d.m01 = java.lang.Math.fma(_rd0, _rd5, _rd2 * this.m11);
        d.m11 = java.lang.Math.fma(_rd1, _rd5, _rd3 * this.m11);
        d.m02 = java.lang.Math.fma(_rd0, _rd6, java.lang.Math.fma(_rd2, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, _rd6, java.lang.Math.fma(_rd3, this.m12, otherImpl.m12));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m00;
        float _rd5 = this.m01;
        float _rd6 = this.m02;
        d.m00 = ((_rd0) * (_rd4) + (_rd2 * this.m10));
        d.m10 = ((_rd1) * (_rd4) + (_rd3 * this.m10));
        d.m01 = ((_rd0) * (_rd5) + (_rd2 * this.m11));
        d.m11 = ((_rd1) * (_rd5) + (_rd3 * this.m11));
        d.m02 = ((_rd0) * (_rd6) + (((_rd2) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (_rd6) + (((_rd3) * (this.m12) + (otherImpl.m12))));
        d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m02;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = java.lang.Math.fma(_rd0, _rd4, java.lang.Math.fma(_rd2, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, _rd4, java.lang.Math.fma(_rd3, this.m12, otherImpl.m12));
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m02;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = ((_rd0) * (_rd4) + (((_rd2) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (_rd4) + (((_rd3) * (this.m12) + (otherImpl.m12))));
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_fma(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m10;
        float _rd5 = this.m02;
        d.m00 = java.lang.Math.fma(_rd0, this.m11, _rd2 * _rd4);
        d.m10 = java.lang.Math.fma(_rd1, this.m11, _rd3 * _rd4);
        d.m01 = java.lang.Math.fma(_rd2, this.m11, _rd0 * -_rd4);
        d.m11 = java.lang.Math.fma(_rd3, this.m11, _rd1 * -_rd4);
        d.m02 = java.lang.Math.fma(_rd0, _rd5, java.lang.Math.fma(_rd2, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, _rd5, java.lang.Math.fma(_rd3, this.m12, otherImpl.m12));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m01;
        float _rd3 = otherImpl.m11;
        float _rd4 = this.m10;
        float _rd5 = this.m02;
        d.m00 = ((_rd0) * (this.m11) + (_rd2 * _rd4));
        d.m10 = ((_rd1) * (this.m11) + (_rd3 * _rd4));
        d.m01 = ((_rd2) * (this.m11) - (_rd0 * _rd4));
        d.m11 = ((_rd3) * (this.m11) - (_rd1 * _rd4));
        d.m02 = ((_rd0) * (_rd5) + (((_rd2) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (_rd5) + (((_rd3) * (this.m12) + (otherImpl.m12))));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_translation(Float2x3R other, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m02 = otherImpl.m02 + this.m02;
        d.m12 = otherImpl.m12 + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_translation(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = otherImpl.m02 + this.m02;
        d.m12 = otherImpl.m12 + this.m12;
        d.properties = _props;
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
    public Float2x3 preMul(Float2x3R other, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            int q = ((Float2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
                return preMul_general_fma(other, dest);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
                return preMul_translation_fma(other, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal_fma(other, dest);
        } else {
            int p = this.properties;
            int q = ((Float2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
                return preMul_general_mulAdd(other, dest);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
                return preMul_translation_mulAdd(other, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal_mulAdd(other, dest);
        }
    }

    /**
     * Pre-multiply the transformation {@code other} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preMul(Float2x3R other) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
            int p = this.properties;
            int q = ((Float2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
                return preMul_general_fma(other, this);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
                return preMul_translation_fma(other, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal_fma(other, this);
        } else {
            if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
            int p = this.properties;
            int q = ((Float2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
                return preMul_general_mulAdd(other, this);
            }
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
                return preMul_translation_mulAdd(other, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal_mulAdd(other, this);
        }
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
    public Double2x3 preMul(Float2x3R other, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        return preMul(otherImpl.m00, otherImpl.m01, otherImpl.m02, otherImpl.m10, otherImpl.m11, otherImpl.m12, dest);
    }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     * {@code m11}, {@code m12}) onto this matrix and store the result in {@code dest}.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m01;
            float _rd2 = this.m02;
            d.m00 = java.lang.Math.fma(m00, _rd0, m01 * this.m10);
            d.m10 = java.lang.Math.fma(m10, _rd0, m11 * this.m10);
            d.m01 = java.lang.Math.fma(m00, _rd1, m01 * this.m11);
            d.m11 = java.lang.Math.fma(m10, _rd1, m11 * this.m11);
            d.m02 = java.lang.Math.fma(m00, _rd2, java.lang.Math.fma(m01, this.m12, m02));
            d.m12 = java.lang.Math.fma(m10, _rd2, java.lang.Math.fma(m11, this.m12, m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m01;
            float _rd2 = this.m02;
            d.m00 = ((m00) * (_rd0) + (m01 * this.m10));
            d.m10 = ((m10) * (_rd0) + (m11 * this.m10));
            d.m01 = ((m00) * (_rd1) + (m01 * this.m11));
            d.m11 = ((m10) * (_rd1) + (m11 * this.m11));
            d.m02 = ((m00) * (_rd2) + (((m01) * (this.m12) + (m02))));
            d.m12 = ((m10) * (_rd2) + (((m11) * (this.m12) + (m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     * {@code m11}, {@code m12}) onto this matrix and store the result in {@code dest}.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m01 * this.m10);
            d.m10 = java.lang.Math.fma(m10, this.m00, m11 * this.m10);
            d.m01 = java.lang.Math.fma(m00, this.m01, m01 * this.m11);
            d.m11 = java.lang.Math.fma(m10, this.m01, m11 * this.m11);
            d.m02 = java.lang.Math.fma(m00, this.m02, java.lang.Math.fma(m01, this.m12, m02));
            d.m12 = java.lang.Math.fma(m10, this.m02, java.lang.Math.fma(m11, this.m12, m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m01 * this.m10));
            d.m10 = ((m10) * (this.m00) + (m11 * this.m10));
            d.m01 = ((m00) * (this.m01) + (m01 * this.m11));
            d.m11 = ((m10) * (this.m01) + (m11 * this.m11));
            d.m02 = ((m00) * (this.m02) + (((m01) * (this.m12) + (m02))));
            d.m12 = ((m10) * (this.m02) + (((m11) * (this.m12) + (m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_fma(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m02;
        d.m00 = otherImpl.m00;
        d.m10 = otherImpl.m10;
        d.m01 = otherImpl.m01;
        d.m11 = otherImpl.m11;
        d.m02 = java.lang.Math.fma(otherImpl.m00, _rd0, otherImpl.m01 * this.m12);
        d.m12 = java.lang.Math.fma(otherImpl.m10, _rd0, otherImpl.m11 * this.m12);
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m02;
        d.m00 = otherImpl.m00;
        d.m10 = otherImpl.m10;
        d.m01 = otherImpl.m01;
        d.m11 = otherImpl.m11;
        d.m02 = ((otherImpl.m00) * (_rd0) + (otherImpl.m01 * this.m12));
        d.m12 = ((otherImpl.m10) * (_rd0) + (otherImpl.m11 * this.m12));
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_fma(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        float _rd1 = this.m02;
        d.m00 = java.lang.Math.fma(otherImpl.m00, this.m11, otherImpl.m01 * _rd0);
        d.m10 = java.lang.Math.fma(otherImpl.m10, this.m11, otherImpl.m11 * _rd0);
        d.m01 = java.lang.Math.fma(otherImpl.m01, this.m11, otherImpl.m00 * -_rd0);
        d.m11 = java.lang.Math.fma(otherImpl.m11, this.m11, otherImpl.m10 * -_rd0);
        d.m02 = java.lang.Math.fma(otherImpl.m00, _rd1, otherImpl.m01 * this.m12);
        d.m12 = java.lang.Math.fma(otherImpl.m10, _rd1, otherImpl.m11 * this.m12);
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m10;
        float _rd1 = this.m02;
        d.m00 = ((otherImpl.m00) * (this.m11) + (otherImpl.m01 * _rd0));
        d.m10 = ((otherImpl.m10) * (this.m11) + (otherImpl.m11 * _rd0));
        d.m01 = ((otherImpl.m01) * (this.m11) - (otherImpl.m00 * _rd0));
        d.m11 = ((otherImpl.m11) * (this.m11) - (otherImpl.m10 * _rd0));
        d.m02 = ((otherImpl.m00) * (_rd1) + (otherImpl.m01 * this.m12));
        d.m12 = ((otherImpl.m10) * (_rd1) + (otherImpl.m11 * this.m12));
        d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_fma(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = java.lang.Math.fma(otherImpl.m00, _rd0, otherImpl.m01 * this.m10);
        d.m10 = java.lang.Math.fma(otherImpl.m10, _rd0, otherImpl.m11 * this.m10);
        d.m01 = java.lang.Math.fma(otherImpl.m00, _rd1, otherImpl.m01 * this.m11);
        d.m11 = java.lang.Math.fma(otherImpl.m10, _rd1, otherImpl.m11 * this.m11);
        d.m02 = java.lang.Math.fma(otherImpl.m00, _rd2, otherImpl.m01 * this.m12);
        d.m12 = java.lang.Math.fma(otherImpl.m10, _rd2, otherImpl.m11 * this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = ((otherImpl.m00) * (_rd0) + (otherImpl.m01 * this.m10));
        d.m10 = ((otherImpl.m10) * (_rd0) + (otherImpl.m11 * this.m10));
        d.m01 = ((otherImpl.m00) * (_rd1) + (otherImpl.m01 * this.m11));
        d.m11 = ((otherImpl.m10) * (_rd1) + (otherImpl.m11 * this.m11));
        d.m02 = ((otherImpl.m00) * (_rd2) + (otherImpl.m01 * this.m12));
        d.m12 = ((otherImpl.m10) * (_rd2) + (otherImpl.m11 * this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preMul(Float2x2R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? preMul_general_fma(other, dest) : preMul_general_mulAdd(other, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl rightImpl = (Float2x2Impl) other;
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = rightImpl.m00;
            d.m10 = rightImpl.m10;
            d.m01 = rightImpl.m01;
            d.m11 = rightImpl.m11;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preMul_translation_fma(other, dest) : preMul_translation_mulAdd(other, dest));
        return (org.joml2.internal.FmaSwitch.ON ? preMul_orthogonal_fma(other, dest) : preMul_orthogonal_mulAdd(other, dest));
    }

    /**
     * Pre-multiply {@code other} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preMul(Float2x2R other) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preMul_general_fma(other, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(other, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_fma(other, this);
            return preMul_orthogonal_fma(other, this);
        } else {
            if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preMul_general_mulAdd(other, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(other, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_mulAdd(other, this);
            return preMul_orthogonal_mulAdd(other, this);
        }
    }

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
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
    public Double2x3 preMul(Float2x2R other, @Mutated Double2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = java.lang.Math.fma(otherImpl.m00, this.m00, otherImpl.m01 * this.m10);
        d.m10 = java.lang.Math.fma(otherImpl.m10, this.m00, otherImpl.m11 * this.m10);
        d.m01 = java.lang.Math.fma(otherImpl.m00, this.m01, otherImpl.m01 * this.m11);
        d.m11 = java.lang.Math.fma(otherImpl.m10, this.m01, otherImpl.m11 * this.m11);
        d.m02 = java.lang.Math.fma(otherImpl.m00, this.m02, otherImpl.m01 * this.m12);
        d.m12 = java.lang.Math.fma(otherImpl.m10, this.m02, otherImpl.m11 * this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 preMul_mulAdd(Float2x2R other, @Mutated Double2x3 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = ((otherImpl.m00) * (this.m00) + (otherImpl.m01 * this.m10));
        d.m10 = ((otherImpl.m10) * (this.m00) + (otherImpl.m11 * this.m10));
        d.m01 = ((otherImpl.m00) * (this.m01) + (otherImpl.m01 * this.m11));
        d.m11 = ((otherImpl.m10) * (this.m01) + (otherImpl.m11 * this.m11));
        d.m02 = ((otherImpl.m00) * (this.m02) + (otherImpl.m01 * this.m12));
        d.m12 = ((otherImpl.m10) * (this.m02) + (otherImpl.m11 * this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_fma(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = java.lang.Math.fma(_rd0, this.m00, _rd3 * this.m10);
        d.m10 = java.lang.Math.fma(_rd1, this.m00, _rd4 * this.m10);
        d.m20 = java.lang.Math.fma(_rd2, this.m00, _rd5 * this.m10);
        d.m01 = java.lang.Math.fma(_rd0, this.m01, _rd3 * this.m11);
        d.m11 = java.lang.Math.fma(_rd1, this.m01, _rd4 * this.m11);
        d.m21 = java.lang.Math.fma(_rd2, this.m01, _rd5 * this.m11);
        d.m02 = java.lang.Math.fma(_rd0, this.m02, java.lang.Math.fma(_rd3, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, this.m02, java.lang.Math.fma(_rd4, this.m12, otherImpl.m12));
        d.m22 = java.lang.Math.fma(_rd2, this.m02, java.lang.Math.fma(_rd5, this.m12, otherImpl.m22));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = ((_rd0) * (this.m00) + (_rd3 * this.m10));
        d.m10 = ((_rd1) * (this.m00) + (_rd4 * this.m10));
        d.m20 = ((_rd2) * (this.m00) + (_rd5 * this.m10));
        d.m01 = ((_rd0) * (this.m01) + (_rd3 * this.m11));
        d.m11 = ((_rd1) * (this.m01) + (_rd4 * this.m11));
        d.m21 = ((_rd2) * (this.m01) + (_rd5 * this.m11));
        d.m02 = ((_rd0) * (this.m02) + (((_rd3) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (this.m02) + (((_rd4) * (this.m12) + (otherImpl.m12))));
        d.m22 = ((_rd2) * (this.m02) + (((_rd5) * (this.m12) + (otherImpl.m22))));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_fma(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m20 = _rd2;
        d.m01 = _rd3;
        d.m11 = _rd4;
        d.m21 = _rd5;
        d.m02 = java.lang.Math.fma(_rd0, this.m02, java.lang.Math.fma(_rd3, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, this.m02, java.lang.Math.fma(_rd4, this.m12, otherImpl.m12));
        d.m22 = java.lang.Math.fma(_rd2, this.m02, java.lang.Math.fma(_rd5, this.m12, otherImpl.m22));
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m20 = _rd2;
        d.m01 = _rd3;
        d.m11 = _rd4;
        d.m21 = _rd5;
        d.m02 = ((_rd0) * (this.m02) + (((_rd3) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (this.m02) + (((_rd4) * (this.m12) + (otherImpl.m12))));
        d.m22 = ((_rd2) * (this.m02) + (((_rd5) * (this.m12) + (otherImpl.m22))));
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_fma(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = java.lang.Math.fma(_rd0, this.m11, _rd3 * this.m10);
        d.m10 = java.lang.Math.fma(_rd1, this.m11, _rd4 * this.m10);
        d.m20 = java.lang.Math.fma(_rd2, this.m11, _rd5 * this.m10);
        d.m01 = java.lang.Math.fma(_rd3, this.m11, _rd0 * -this.m10);
        d.m11 = java.lang.Math.fma(_rd4, this.m11, _rd1 * -this.m10);
        d.m21 = java.lang.Math.fma(_rd5, this.m11, _rd2 * -this.m10);
        d.m02 = java.lang.Math.fma(_rd0, this.m02, java.lang.Math.fma(_rd3, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(_rd1, this.m02, java.lang.Math.fma(_rd4, this.m12, otherImpl.m12));
        d.m22 = java.lang.Math.fma(_rd2, this.m02, java.lang.Math.fma(_rd5, this.m12, otherImpl.m22));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = otherImpl.m20;
        float _rd3 = otherImpl.m01;
        float _rd4 = otherImpl.m11;
        float _rd5 = otherImpl.m21;
        d.m00 = ((_rd0) * (this.m11) + (_rd3 * this.m10));
        d.m10 = ((_rd1) * (this.m11) + (_rd4 * this.m10));
        d.m20 = ((_rd2) * (this.m11) + (_rd5 * this.m10));
        d.m01 = ((_rd3) * (this.m11) - (_rd0 * this.m10));
        d.m11 = ((_rd4) * (this.m11) - (_rd1 * this.m10));
        d.m21 = ((_rd5) * (this.m11) - (_rd2 * this.m10));
        d.m02 = ((_rd0) * (this.m02) + (((_rd3) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((_rd1) * (this.m02) + (((_rd4) * (this.m12) + (otherImpl.m12))));
        d.m22 = ((_rd2) * (this.m02) + (((_rd5) * (this.m12) + (otherImpl.m22))));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = otherImpl.m02 + this.m02;
        d.m12 = otherImpl.m12 + this.m12;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0f;
        d.m02 = otherImpl.m02 + this.m02;
        d.m12 = otherImpl.m12 + this.m12;
        d.m22 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Pre-multiply the given matrix onto this matrix, i.e. compute {@code other * this}, and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 preMul(Float3x3R other, @Mutated Float3x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_general_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
            return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float3x3Impl d = (Float3x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m20 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m21 = 0.0f;
                d.m02 = this.m02;
                d.m12 = this.m12;
                d.m22 = 1.0f;
                d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
                return d;
            }
            return preMul_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_orthogonal_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
        return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 preMul_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        int q = ((Float3x3Impl) other).properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_general_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
            return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float3x3Impl d = (Float3x3Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m20 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.m21 = 0.0f;
                d.m02 = this.m02;
                d.m12 = this.m12;
                d.m22 = 1.0f;
                d.properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
                return d;
            }
            return preMul_translation_translation(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preMul_orthogonal_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
        return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
    }

    /**
     * Pre-multiply the given matrix onto this matrix, i.e. compute {@code other * this}, and store
     * the result in {@code dest}.
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
    public Double3x3 preMul(Float3x3R other, @Mutated Double3x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 preMul_fma(Float3x3R other, @Mutated Double3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = java.lang.Math.fma(otherImpl.m00, this.m00, otherImpl.m01 * this.m10);
        d.m10 = java.lang.Math.fma(otherImpl.m10, this.m00, otherImpl.m11 * this.m10);
        d.m20 = java.lang.Math.fma(otherImpl.m20, this.m00, otherImpl.m21 * this.m10);
        d.m01 = java.lang.Math.fma(otherImpl.m00, this.m01, otherImpl.m01 * this.m11);
        d.m11 = java.lang.Math.fma(otherImpl.m10, this.m01, otherImpl.m11 * this.m11);
        d.m21 = java.lang.Math.fma(otherImpl.m20, this.m01, otherImpl.m21 * this.m11);
        d.m02 = java.lang.Math.fma(otherImpl.m00, this.m02, java.lang.Math.fma(otherImpl.m01, this.m12, otherImpl.m02));
        d.m12 = java.lang.Math.fma(otherImpl.m10, this.m02, java.lang.Math.fma(otherImpl.m11, this.m12, otherImpl.m12));
        d.m22 = java.lang.Math.fma(otherImpl.m20, this.m02, java.lang.Math.fma(otherImpl.m21, this.m12, otherImpl.m22));
        d.properties = 0;
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 preMul_mulAdd(Float3x3R other, @Mutated Double3x3 dest) {
        Float3x3Impl otherImpl = (Float3x3Impl) other;
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = ((otherImpl.m00) * (this.m00) + (otherImpl.m01 * this.m10));
        d.m10 = ((otherImpl.m10) * (this.m00) + (otherImpl.m11 * this.m10));
        d.m20 = ((otherImpl.m20) * (this.m00) + (otherImpl.m21 * this.m10));
        d.m01 = ((otherImpl.m00) * (this.m01) + (otherImpl.m01 * this.m11));
        d.m11 = ((otherImpl.m10) * (this.m01) + (otherImpl.m11 * this.m11));
        d.m21 = ((otherImpl.m20) * (this.m01) + (otherImpl.m21 * this.m11));
        d.m02 = ((otherImpl.m00) * (this.m02) + (((otherImpl.m01) * (this.m12) + (otherImpl.m02))));
        d.m12 = ((otherImpl.m10) * (this.m02) + (((otherImpl.m11) * (this.m12) + (otherImpl.m12))));
        d.m22 = ((otherImpl.m20) * (this.m02) + (((otherImpl.m21) * (this.m12) + (otherImpl.m22))));
        d.properties = 0;
        return d;
    }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 addScaled(Float2x3R other, float weight, @Mutated Float2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = java.lang.Math.fma(weight, otherImpl.m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, otherImpl.m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, otherImpl.m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, otherImpl.m11, this.m11);
            d.m02 = java.lang.Math.fma(weight, otherImpl.m02, this.m02);
            d.m12 = java.lang.Math.fma(weight, otherImpl.m12, this.m12);
            d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
            return d;
        } else {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = ((weight) * (otherImpl.m00) + (this.m00));
            d.m10 = ((weight) * (otherImpl.m10) + (this.m10));
            d.m01 = ((weight) * (otherImpl.m01) + (this.m01));
            d.m11 = ((weight) * (otherImpl.m11) + (this.m11));
            d.m02 = ((weight) * (otherImpl.m02) + (this.m02));
            d.m12 = ((weight) * (otherImpl.m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
            return d;
        }
    }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
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
    public Double2x3 addScaled(Float2x3R other, float weight, @Mutated Double2x3 dest) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m02 = otherImpl.m02;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        float m12 = otherImpl.m12;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m02 = java.lang.Math.fma(weight, m02, this.m02);
            d.m12 = java.lang.Math.fma(weight, m12, this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m02 = ((weight) * (m02) + (this.m02));
            d.m12 = ((weight) * (m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) scaled by
     * {@code weight} to this matrix and store the result in {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float weight, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m02 = java.lang.Math.fma(weight, m02, this.m02);
            d.m12 = java.lang.Math.fma(weight, m12, this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m02 = ((weight) * (m02) + (this.m02));
            d.m12 = ((weight) * (m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) scaled by
     * {@code weight} to this matrix and store the result in {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
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
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float weight, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m02 = java.lang.Math.fma(weight, m02, this.m02);
            d.m12 = java.lang.Math.fma(weight, m12, this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m02 = ((weight) * (m02) + (this.m02));
            d.m12 = ((weight) * (m12) + (this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Set this matrix to a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Float2x3 makeRotation(float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        this.m00 = _t0;
        this.m10 = _t1;
        this.m01 = -_t1;
        this.m11 = _t0;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = Joml.BIT_ORTHOGONAL;
        return this;
    }

    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this
     */
    @Mutated public Float2x3 makeScaling(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        this.m00 = vX;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = vY;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Set this matrix to a scaling transformation that scales by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this
     */
    @Mutated public Float2x3 makeScaling(float vX, float vY) {
        this.m00 = vX;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = vY;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Set this matrix to a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this
     */
    @Mutated public Float2x3 makeScaling(float s) {
        this.m00 = s;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = s;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this
     */
    @Mutated public Float2x3 makeTranslation(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m02 = vX;
        this.m12 = vY;
        this.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /**
     * Set this matrix to a translation transformation that translates by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return this
     */
    @Mutated public Float2x3 makeTranslation(float vX, float vY) {
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m02 = vX;
        this.m12 = vY;
        this.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /**
     * Set this matrix to the 2D view transformation that maps the rectangle
     * {@code [left, right] x [bottom, top]} onto {@code [-1, +1] x [-1, +1]}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the x coordinate of the left edge of the view rectangle
     * @param right the x coordinate of the right edge of the view rectangle
     * @param bottom the y coordinate of the bottom edge of the view rectangle
     * @param top the y coordinate of the top edge of the view rectangle
     * @return this
     */
    @Mutated public Float2x3 makeView(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        this.m00 = _t0_inv + _t0_inv;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = _t1_inv + _t1_inv;
        this.m02 = -(left + right) * _t0_inv;
        this.m12 = -(bottom + top) * _t1_inv;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_orthogonal_general_fma(float angle, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = java.lang.Math.fma(_rd0, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(_rd0, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(_rd1, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(_rd1, _t1, this.m11 * _t0);
        d.m02 = java.lang.Math.fma(_rd2, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(_rd2, _t1, this.m12 * _t0);
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_orthogonal_general_mulAdd(float angle, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = ((_rd0) * (_t0) - (this.m10 * _t1));
        d.m10 = ((_rd0) * (_t1) + (this.m10 * _t0));
        d.m01 = ((_rd1) * (_t0) - (this.m11 * _t1));
        d.m11 = ((_rd1) * (_t1) + (this.m11 * _t0));
        d.m02 = ((_rd2) * (_t0) - (this.m12 * _t1));
        d.m12 = ((_rd2) * (_t1) + (this.m12 * _t0));
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_translation_fma(float angle, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m02;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = java.lang.Math.fma(_rd0, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(_rd0, _t1, this.m12 * _t0);
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preRotate(float angle, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                d.m00 = _t0;
                d.m10 = _t1;
                d.m01 = -_t1;
                d.m11 = _t0;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = Joml.BIT_ORTHOGONAL;
                return d;
            }
            return preRotate_translation_fma(angle, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                d.m00 = _t0;
                d.m10 = _t1;
                d.m01 = -_t1;
                d.m11 = _t0;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = Joml.BIT_ORTHOGONAL;
                return d;
            }
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.m02;
            d.m00 = _t0;
            d.m10 = _t1;
            d.m01 = -_t1;
            d.m11 = _t0;
            d.m02 = ((_rd0) * (_t0) - (this.m12 * _t1));
            d.m12 = ((_rd0) * (_t1) + (this.m12 * _t0));
            d.properties = Joml.BIT_ORTHOGONAL;
            return d;
        }
    }

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preRotate(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_fma(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
            return preRotate_translation_fma(angle, this);
        } else {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_mulAdd(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _rd0 = this.m02;
            this.m00 = _t0;
            this.m10 = _t1;
            this.m01 = -_t1;
            this.m11 = _t0;
            this.m02 = ((_rd0) * (_t0) - (this.m12 * _t1));
            this.m12 = ((_rd0) * (_t1) + (this.m12 * _t0));
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
    }

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preRotate(float angle, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = java.lang.Math.fma(this.m00, _t0, -(this.m10 * _t1));
            d.m10 = java.lang.Math.fma(this.m00, _t1, this.m10 * _t0);
            d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m11 * _t1));
            d.m11 = java.lang.Math.fma(this.m01, _t1, this.m11 * _t0);
            d.m02 = java.lang.Math.fma(this.m02, _t0, -(this.m12 * _t1));
            d.m12 = java.lang.Math.fma(this.m02, _t1, this.m12 * _t0);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = ((this.m00) * (_t0) - (this.m10 * _t1));
            d.m10 = ((this.m00) * (_t1) + (this.m10 * _t0));
            d.m01 = ((this.m01) * (_t0) - (this.m11 * _t1));
            d.m11 = ((this.m01) * (_t1) + (this.m11 * _t0));
            d.m02 = ((this.m02) * (_t0) - (this.m12 * _t1));
            d.m12 = ((this.m02) * (_t1) + (this.m12 * _t0));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preRotateAround(float angle, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_general_fma(angle, pivotX, pivotY, dest) : preRotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_translation_fma(angle, pivotX, pivotY, dest) : preRotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preRotateAround(float angle, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (!org.joml2.internal.FmaSwitch.ON) return preRotateAround_mulAdd(angle, pivotX, pivotY, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = java.lang.Math.fma(this.m00, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(this.m00, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(this.m01, _t1, this.m11 * _t0);
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(this.m02, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(this.m02, _t1, this.m12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preRotateAround(float angle, Float2R pivot) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preRotateAround_general_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_fma(angle, pivotX, pivotY, this);
            return preRotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preRotateAround_general_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            return preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float2x3 preRotateAround_identity_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1);
        d.m12 = java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float2x3 preRotateAround_identity_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = ((pivotX) * (_t6) + (pivotY * _t1));
        d.m12 = ((pivotY) * (_t6) - (pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_translation_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m02;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_rd0, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(_rd0, _t1, this.m12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_translation_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m02;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((_rd0) * (_t0) - (this.m12 * _t1));
        d.m12 = ((_rd0) * (_t1) + (this.m12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_orthogonal_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = java.lang.Math.fma(_rd0, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(_rd0, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(_rd1, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(_rd1, _t1, this.m11 * _t0);
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_rd2, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(_rd2, _t1, this.m12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_orthogonal_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = ((_rd0) * (_t0) - (this.m10 * _t1));
        d.m10 = ((_rd0) * (_t1) + (this.m10 * _t0));
        d.m01 = ((_rd1) * (_t0) - (this.m11 * _t1));
        d.m11 = ((_rd1) * (_t1) + (this.m11 * _t0));
        d.m02 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((_rd2) * (_t0) - (this.m12 * _t1));
        d.m12 = ((_rd2) * (_t1) + (this.m12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_general_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = java.lang.Math.fma(_rd0, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(_rd0, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(_rd1, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(_rd1, _t1, this.m11 * _t0);
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_rd2, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(_rd2, _t1, this.m12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_general_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m02;
        d.m00 = ((_rd0) * (_t0) - (this.m10 * _t1));
        d.m10 = ((_rd0) * (_t1) + (this.m10 * _t0));
        d.m01 = ((_rd1) * (_t0) - (this.m11 * _t1));
        d.m11 = ((_rd1) * (_t1) + (this.m11 * _t0));
        d.m02 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((_rd2) * (_t0) - (this.m12 * _t1));
        d.m12 = ((_rd2) * (_t1) + (this.m12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preRotateAround(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_general_fma(angle, pivotX, pivotY, dest) : preRotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_translation_fma(angle, pivotX, pivotY, dest) : preRotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preRotateAround(float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preRotateAround_general_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_fma(angle, pivotX, pivotY, this);
            return preRotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preRotateAround_general_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            return preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preRotateAround(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preRotateAround_mulAdd(angle, pivotX, pivotY, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = java.lang.Math.fma(this.m00, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(this.m00, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(this.m01, _t1, this.m11 * _t0);
        d.m02 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(this.m02, _t0, -(this.m12 * _t1));
        d.m12 = java.lang.Math.fma(this.m02, _t1, this.m12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code preRotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 preRotateAround_mulAdd(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = ((this.m00) * (_t0) - (this.m10 * _t1));
        d.m10 = ((this.m00) * (_t1) + (this.m10 * _t0));
        d.m01 = ((this.m01) * (_t0) - (this.m11 * _t1));
        d.m11 = ((this.m01) * (_t1) + (this.m11 * _t0));
        d.m02 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((this.m02) * (_t0) - (this.m12 * _t1));
        d.m12 = ((this.m02) * (_t1) + (this.m12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScale(Float2R v, @Mutated Float2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vY;
            d.m01 = this.m01 * vX;
            d.m11 = this.m11 * vY;
            d.m02 = this.m02 * vX;
            d.m12 = this.m12 * vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScale(Float2R v, @Mutated Double2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScale(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vY;
            this.m01 = this.m01 * vX;
            this.m11 = this.m11 * vY;
            this.m02 = this.m02 * vX;
            this.m12 = this.m12 * vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = vX;
        this.m11 = vY;
        this.m02 = this.m02 * vX;
        this.m12 = this.m12 * vY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScale(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vY;
            d.m01 = this.m01 * vX;
            d.m11 = this.m11 * vY;
            d.m02 = this.m02 * vX;
            d.m12 = this.m12 * vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScale(float vX, float vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vY;
            this.m01 = this.m01 * vX;
            this.m11 = this.m11 * vY;
            this.m02 = this.m02 * vX;
            this.m12 = this.m12 * vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = vX;
        this.m11 = vY;
        this.m02 = this.m02 * vX;
        this.m12 = this.m12 * vY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
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
    public Double2x3 preScale(float vX, float vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScale(float s, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = s * this.m02;
            d.m12 = s * this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScale(float s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = s * this.m00;
            this.m10 = s * this.m10;
            this.m01 = s * this.m01;
            this.m11 = s * this.m11;
            this.m02 = s * this.m02;
            this.m12 = s * this.m12;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = s;
            this.m11 = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = s;
        this.m11 = s;
        this.m02 = s * this.m02;
        this.m12 = s * this.m12;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScale(float s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScaleAround(float s, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = pivotX * _t0;
            d.m12 = pivotY * _t0;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_translation_fma(s, pivotX, pivotY, dest) : preScaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScaleAround(float s, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = java.lang.Math.fma(s, this.m02, pivotX * _t0);
            d.m12 = java.lang.Math.fma(s, this.m12, pivotY * _t0);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = ((s) * (this.m02) + (pivotX * _t0));
            d.m12 = ((s) * (this.m12) + (pivotY * _t0));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScaleAround(float s, Float2R pivot) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_fma(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            float _t0 = 1.0f - s;
            this.m00 = s;
            this.m11 = s;
            this.m02 = ((s) * (this.m02) + (pivotX * _t0));
            this.m12 = ((s) * (this.m12) + (pivotY * _t0));
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = java.lang.Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = java.lang.Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = ((s) * (this.m02) + (pivotX * _t0));
        d.m12 = ((s) * (this.m12) + (pivotY * _t0));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_self_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = java.lang.Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = java.lang.Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = java.lang.Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = java.lang.Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = ((s) * (this.m02) + (pivotX * _t0));
        d.m12 = ((s) * (this.m12) + (pivotY * _t0));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY})
     * onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScaleAround(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = pivotX * _t0;
            d.m12 = pivotY * _t0;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_translation_fma(s, pivotX, pivotY, dest) : preScaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY})
     * onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScaleAround(float s, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_fma(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            float _t0 = 1.0f - s;
            this.m00 = s;
            this.m11 = s;
            this.m02 = ((s) * (this.m02) + (pivotX * _t0));
            this.m12 = ((s) * (this.m12) + (pivotY * _t0));
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY})
     * onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScaleAround(float s, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = java.lang.Math.fma(s, this.m02, pivotX * _t0);
            d.m12 = java.lang.Math.fma(s, this.m12, pivotY * _t0);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = ((s) * (this.m02) + (pivotX * _t0));
            d.m12 = ((s) * (this.m12) + (pivotY * _t0));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScaleAround(Float2R s, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float sX = sImpl.x;
        float sY = sImpl.y;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = sX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = sY;
            d.m02 = pivotX * (1.0f - sX);
            d.m12 = pivotY * (1.0f - sY);
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(sX, sY, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScaleAround(Float2R s, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float sX = sImpl.x;
        float sY = sImpl.y;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = sX * this.m00;
            d.m10 = sY * this.m10;
            d.m01 = sX * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = java.lang.Math.fma(pivotX, 1.0f - sX, sX * this.m02);
            d.m12 = java.lang.Math.fma(pivotY, 1.0f - sY, sY * this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = sX * this.m00;
            d.m10 = sY * this.m10;
            d.m01 = sX * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = ((pivotX) * (1.0f - sX) + (sX * this.m02));
            d.m12 = ((pivotY) * (1.0f - sY) + (sY * this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScaleAround(Float2R s, Float2R pivot) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float sX = sImpl.x;
        float sY = sImpl.y;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX * this.m00;
        d.m10 = sY * this.m10;
        d.m01 = sX * this.m01;
        d.m11 = sY * this.m11;
        d.m02 = java.lang.Math.fma(pivotX, 1.0f - sX, sX * this.m02);
        d.m12 = java.lang.Math.fma(pivotY, 1.0f - sY, sY * this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX * this.m00;
        d.m10 = sY * this.m10;
        d.m01 = sX * this.m01;
        d.m11 = sY * this.m11;
        d.m02 = ((pivotX) * (1.0f - sX) + (sX * this.m02));
        d.m12 = ((pivotY) * (1.0f - sY) + (sY * this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preScaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = sX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = sY;
            d.m02 = pivotX * (1.0f - sX);
            d.m12 = pivotY * (1.0f - sY);
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(sX, sY, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, dest));
    }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preScaleAround(float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preScaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = sX * this.m00;
            d.m10 = sY * this.m10;
            d.m01 = sX * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = java.lang.Math.fma(pivotX, 1.0f - sX, sX * this.m02);
            d.m12 = java.lang.Math.fma(pivotY, 1.0f - sY, sY * this.m12);
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = sX * this.m00;
            d.m10 = sY * this.m10;
            d.m01 = sX * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = ((pivotX) * (1.0f - sX) + (sX * this.m02));
            d.m12 = ((pivotY) * (1.0f - sY) + (sY * this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Pre-multiply a translation by {@code v} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preTranslate(Float2R v, @Mutated Float2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Pre-multiply a translation by {@code v} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 preTranslate(Float2R v, @Mutated Double2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Pre-multiply a translation by {@code v} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preTranslate(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            this.m02 = this.m02 + vX;
            this.m12 = this.m12 + vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m02 = vX;
            this.m12 = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m02 = this.m02 + vX;
            this.m12 = this.m12 + vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        this.m02 = this.m02 + vX;
        this.m12 = this.m12 + vY;
        this.properties = Joml.BIT_ORTHOGONAL;
        return this;
    }

    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix and store the result
     * in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 preTranslate(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 preTranslate(float vX, float vY) {
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            this.m02 = this.m02 + vX;
            this.m12 = this.m12 + vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m02 = vX;
            this.m12 = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m02 = this.m02 + vX;
            this.m12 = this.m12 + vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        this.m02 = this.m02 + vX;
        this.m12 = this.m12 + vY;
        this.properties = Joml.BIT_ORTHOGONAL;
        return this;
    }

    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix and store the result
     * in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
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
    public Double2x3 preTranslate(float vX, float vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general_fma(float angle, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = java.lang.Math.fma(_rd0, _t0, this.m01 * _t1);
        d.m10 = java.lang.Math.fma(_rd1, _t0, this.m11 * _t1);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(_rd0 * _t1));
        d.m11 = java.lang.Math.fma(this.m11, _t0, -(_rd1 * _t1));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general_mulAdd(float angle, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = ((_rd0) * (_t0) + (this.m01 * _t1));
        d.m10 = ((_rd1) * (_t0) + (this.m11 * _t1));
        d.m01 = ((this.m01) * (_t0) - (_rd0 * _t1));
        d.m11 = ((this.m11) * (_t0) - (_rd1 * _t1));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = _props;
        return d;
    }

    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general_self_fma(float angle, @Mutated Float2x3 dest, int _props) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = java.lang.Math.fma(_rd0, _t0, this.m01 * _t1);
        d.m10 = java.lang.Math.fma(_rd1, _t0, this.m11 * _t1);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(_rd0 * _t1));
        d.m11 = java.lang.Math.fma(this.m11, _t0, -(_rd1 * _t1));
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_translation(float angle, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Apply a rotation by {@code angle} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 rotate(float angle, @Mutated Float2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return rotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                d.m00 = _t0;
                d.m10 = _t1;
                d.m01 = -_t1;
                d.m11 = _t0;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = Joml.BIT_ORTHOGONAL;
                return d;
            }
            return rotate_translation(angle, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return rotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x3Impl d = (Float2x3Impl) dest;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                d.m00 = _t0;
                d.m10 = _t1;
                d.m01 = -_t1;
                d.m11 = _t0;
                d.m02 = 0.0f;
                d.m12 = 0.0f;
                d.properties = Joml.BIT_ORTHOGONAL;
                return d;
            }
            return rotate_translation(angle, dest);
        }
    }

    /**
     * Apply a rotation by {@code angle} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 rotate(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return rotate_orthogonal_general_self_fma(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
        } else {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
                int _props = (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                float _rd0 = this.m00;
                float _rd1 = this.m10;
                this.m00 = ((_rd0) * (_t0) + (this.m01 * _t1));
                this.m10 = ((_rd1) * (_t0) + (this.m11 * _t1));
                this.m01 = ((this.m01) * (_t0) - (_rd0 * _t1));
                this.m11 = ((this.m11) * (_t0) - (_rd1 * _t1));
                this.properties = _props;
                return this;
            }
            {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
        }
    }

    /**
     * Apply a rotation by {@code angle} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 rotate(float angle, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = java.lang.Math.fma(this.m00, _t0, this.m01 * _t1);
            d.m10 = java.lang.Math.fma(this.m10, _t0, this.m11 * _t1);
            d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1));
            d.m11 = java.lang.Math.fma(this.m11, _t0, -(this.m10 * _t1));
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = ((this.m00) * (_t0) + (this.m01 * _t1));
            d.m10 = ((this.m10) * (_t0) + (this.m11 * _t1));
            d.m01 = ((this.m01) * (_t0) - (this.m00 * _t1));
            d.m11 = ((this.m11) * (_t0) - (this.m10 * _t1));
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 rotateAround(float angle, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? rotateAround_general_fma(angle, pivotX, pivotY, dest) : rotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? rotateAround_translation_fma(angle, pivotX, pivotY, dest) : rotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? rotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
    }

    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 rotateAround(float angle, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAround_mulAdd(angle, pivotX, pivotY, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        d.m00 = java.lang.Math.fma(this.m00, _t0, this.m01 * _t1);
        d.m10 = java.lang.Math.fma(this.m10, _t0, this.m11 * _t1);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1));
        d.m11 = java.lang.Math.fma(this.m11, _t0, -(this.m10 * _t1));
        d.m02 = java.lang.Math.fma(this.m00, _t10, java.lang.Math.fma(this.m01, _t11, this.m02));
        d.m12 = java.lang.Math.fma(this.m10, _t10, java.lang.Math.fma(this.m11, _t11, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 rotateAround(float angle, Float2R pivot) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return rotateAround_general_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_fma(angle, pivotX, pivotY, this);
            return rotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return rotateAround_general_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            return rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_translation_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = java.lang.Math.fma(pivotX, _t6, java.lang.Math.fma(pivotY, _t1, this.m02));
        d.m12 = java.lang.Math.fma(pivotY, _t6, java.lang.Math.fma(-pivotX, _t1, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_translation_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = -_t1;
        d.m11 = _t0;
        d.m02 = ((pivotX) * (_t6) + (((pivotY) * (_t1) + (this.m02))));
        d.m12 = ((pivotY) * (_t6) + (((this.m12) - (pivotX) * (_t1))));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_orthogonal_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = java.lang.Math.fma(_rd0, _t0, _rd2 * _t1);
        d.m10 = java.lang.Math.fma(_rd1, _t0, _rd3 * _t1);
        d.m01 = java.lang.Math.fma(_rd2, _t0, -(_rd0 * _t1));
        d.m11 = java.lang.Math.fma(_rd3, _t0, -(_rd1 * _t1));
        d.m02 = java.lang.Math.fma(_rd0, _t10, java.lang.Math.fma(_rd2, _t11, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, _t10, java.lang.Math.fma(_rd3, _t11, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_orthogonal_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = ((_rd0) * (_t0) + (_rd2 * _t1));
        d.m10 = ((_rd1) * (_t0) + (_rd3 * _t1));
        d.m01 = ((_rd2) * (_t0) - (_rd0 * _t1));
        d.m11 = ((_rd3) * (_t0) - (_rd1 * _t1));
        d.m02 = ((_rd0) * (_t10) + (((_rd2) * (_t11) + (this.m02))));
        d.m12 = ((_rd1) * (_t10) + (((_rd3) * (_t11) + (this.m12))));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_general_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = java.lang.Math.fma(_rd0, _t0, _rd2 * _t1);
        d.m10 = java.lang.Math.fma(_rd1, _t0, _rd3 * _t1);
        d.m01 = java.lang.Math.fma(_rd2, _t0, -(_rd0 * _t1));
        d.m11 = java.lang.Math.fma(_rd3, _t0, -(_rd1 * _t1));
        d.m02 = java.lang.Math.fma(_rd0, _t10, java.lang.Math.fma(_rd2, _t11, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, _t10, java.lang.Math.fma(_rd3, _t11, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_general_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = ((_rd0) * (_t0) + (_rd2 * _t1));
        d.m10 = ((_rd1) * (_t0) + (_rd3 * _t1));
        d.m01 = ((_rd2) * (_t0) - (_rd0 * _t1));
        d.m11 = ((_rd3) * (_t0) - (_rd1 * _t1));
        d.m02 = ((_rd0) * (_t10) + (((_rd2) * (_t11) + (this.m02))));
        d.m12 = ((_rd1) * (_t10) + (((_rd3) * (_t11) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 rotateAround(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? rotateAround_general_fma(angle, pivotX, pivotY, dest) : rotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (org.joml2.internal.FmaSwitch.ON ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? rotateAround_translation_fma(angle, pivotX, pivotY, dest) : rotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? rotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
    }

    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 rotateAround(float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return rotateAround_general_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_fma(angle, pivotX, pivotY, this);
            return rotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return rotateAround_general_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            return rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 rotateAround(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAround_mulAdd(angle, pivotX, pivotY, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        d.m00 = java.lang.Math.fma(this.m00, _t0, this.m01 * _t1);
        d.m10 = java.lang.Math.fma(this.m10, _t0, this.m11 * _t1);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1));
        d.m11 = java.lang.Math.fma(this.m11, _t0, -(this.m10 * _t1));
        d.m02 = java.lang.Math.fma(this.m00, _t10, java.lang.Math.fma(this.m01, _t11, this.m02));
        d.m12 = java.lang.Math.fma(this.m10, _t10, java.lang.Math.fma(this.m11, _t11, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code rotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 rotateAround_mulAdd(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        d.m00 = ((this.m00) * (_t0) + (this.m01 * _t1));
        d.m10 = ((this.m10) * (_t0) + (this.m11 * _t1));
        d.m01 = ((this.m01) * (_t0) - (this.m00 * _t1));
        d.m11 = ((this.m11) * (_t0) - (this.m10 * _t1));
        d.m02 = ((this.m00) * (_t10) + (((this.m01) * (_t11) + (this.m02))));
        d.m12 = ((this.m10) * (_t10) + (((this.m11) * (_t11) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scale(Float2R v, @Mutated Float2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vX;
            d.m01 = this.m01 * vY;
            d.m11 = this.m11 * vY;
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scale(Float2R v, @Mutated Double2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code v} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scale(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vX;
            this.m01 = this.m01 * vY;
            this.m11 = this.m11 * vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = vX;
        this.m11 = vY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scale(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vX;
            d.m01 = this.m01 * vY;
            d.m11 = this.m11 * vY;
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scale(float vX, float vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vX;
            this.m01 = this.m01 * vY;
            this.m11 = this.m11 * vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = vX;
        this.m11 = vY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
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
    public Double2x3 scale(float vX, float vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code s} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scale(float s, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = this.m02;
            d.m12 = this.m12;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code s} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scale(float s) {
        if (Joml.RETURN_NEW) return scale(s, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
            this.m00 = s * this.m00;
            this.m10 = s * this.m10;
            this.m01 = s * this.m01;
            this.m11 = s * this.m11;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = s;
            this.m11 = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        this.m00 = s;
        this.m11 = s;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Apply a scaling by {@code s} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scale(float s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scaleAround(float s, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = pivotX * _t0;
            d.m12 = pivotY * _t0;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? scaleAround_translation_fma(s, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? scaleAround_orthogonal_fma(s, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, dest));
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scaleAround(float s, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = java.lang.Math.fma(this.m00, _t1, java.lang.Math.fma(this.m01, _t2, this.m02));
            d.m12 = java.lang.Math.fma(this.m10, _t1, java.lang.Math.fma(this.m11, _t2, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = ((this.m00) * (_t1) + (((this.m01) * (_t2) + (this.m02))));
            d.m12 = ((this.m10) * (_t1) + (((this.m11) * (_t2) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scaleAround(float s, Float2R pivot) {
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = ((pivotX) * (_t0) + (this.m02));
                this.m12 = ((pivotY) * (_t0) + (this.m12));
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = java.lang.Math.fma(pivotX, _t0, this.m02);
        d.m12 = java.lang.Math.fma(pivotY, _t0, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = s;
        d.m02 = ((pivotX) * (_t0) + (this.m02));
        d.m12 = ((pivotY) * (_t0) + (this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = java.lang.Math.fma(pivotX, _t0, this.m02);
        d.m12 = java.lang.Math.fma(pivotY, _t0, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = s * _rd0;
        d.m10 = s * _rd1;
        d.m01 = s * _rd2;
        d.m11 = s * _rd3;
        d.m02 = java.lang.Math.fma(_rd0, _t1, java.lang.Math.fma(_rd2, _t2, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, _t1, java.lang.Math.fma(_rd3, _t2, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = s * _rd0;
        d.m10 = s * _rd1;
        d.m01 = s * _rd2;
        d.m11 = s * _rd3;
        d.m02 = ((_rd0) * (_t1) + (((_rd2) * (_t2) + (this.m02))));
        d.m12 = ((_rd1) * (_t1) + (((_rd3) * (_t2) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY}) to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scaleAround(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0 = 1.0f - s;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.m02 = pivotX * _t0;
            d.m12 = pivotY * _t0;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? scaleAround_translation_fma(s, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? scaleAround_orthogonal_fma(s, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, dest));
    }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY}) to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scaleAround(float s, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = pivotX * _t0;
                this.m12 = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                float _t0 = 1.0f - s;
                this.m00 = s;
                this.m11 = s;
                this.m02 = ((pivotX) * (_t0) + (this.m02));
                this.m12 = ((pivotY) * (_t0) + (this.m12));
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, this);
        }
    }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY}) to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scaleAround(float s, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = java.lang.Math.fma(this.m00, _t1, java.lang.Math.fma(this.m01, _t2, this.m02));
            d.m12 = java.lang.Math.fma(this.m10, _t1, java.lang.Math.fma(this.m11, _t2, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            d.m00 = s * this.m00;
            d.m10 = s * this.m10;
            d.m01 = s * this.m01;
            d.m11 = s * this.m11;
            d.m02 = ((this.m00) * (_t1) + (((this.m01) * (_t2) + (this.m02))));
            d.m12 = ((this.m10) * (_t1) + (((this.m11) * (_t2) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scaleAround(Float2R s, Float2R pivot, @Mutated Float2x3 dest) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float sX = sImpl.x;
        float sY = sImpl.y;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = sX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = sY;
            d.m02 = pivotX * (1.0f - sX);
            d.m12 = pivotY * (1.0f - sY);
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? scaleAround_translation_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(sX, sY, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, dest));
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scaleAround(Float2R s, Float2R pivot, @Mutated Double2x3 dest) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        return scaleAround(sImpl.x, sImpl.y, pivotImpl.x, pivotImpl.y, dest);
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scaleAround(Float2R s, Float2R pivot) {
        Float2Impl sImpl = (Float2Impl) s;
        Float2Impl pivotImpl = (Float2Impl) pivot;
        float sX = sImpl.x;
        float sY = sImpl.y;
        float pivotX = pivotImpl.x;
        float pivotY = pivotImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = sY;
        d.m02 = java.lang.Math.fma(pivotX, 1.0f - sX, this.m02);
        d.m12 = java.lang.Math.fma(pivotY, 1.0f - sY, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = sY;
        d.m02 = ((pivotX) * (1.0f - sX) + (this.m02));
        d.m12 = ((pivotY) * (1.0f - sY) + (this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = java.lang.Math.fma(pivotX, 1.0f - sX, this.m02);
        d.m12 = java.lang.Math.fma(pivotY, 1.0f - sY, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = ((pivotX) * (1.0f - sX) + (this.m02));
        d.m12 = ((pivotY) * (1.0f - sY) + (this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = sX * _rd0;
        d.m10 = sX * _rd1;
        d.m01 = sY * _rd2;
        d.m11 = sY * _rd3;
        d.m02 = java.lang.Math.fma(_rd0, _t2, java.lang.Math.fma(_rd2, _t3, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, _t2, java.lang.Math.fma(_rd3, _t3, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = sX * _rd0;
        d.m10 = sX * _rd1;
        d.m01 = sY * _rd2;
        d.m11 = sY * _rd3;
        d.m02 = ((_rd0) * (_t2) + (((_rd2) * (_t3) + (this.m02))));
        d.m12 = ((_rd1) * (_t2) + (((_rd3) * (_t3) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 scaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = sX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = sY;
            d.m02 = pivotX * (1.0f - sX);
            d.m12 = pivotY * (1.0f - sY);
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? scaleAround_translation_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(sX, sY, pivotX, pivotY, dest));
        return (org.joml2.internal.FmaSwitch.ON ? scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, dest));
    }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 scaleAround(float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = sX;
                this.m11 = sY;
                this.m02 = pivotX * (1.0f - sX);
                this.m12 = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 scaleAround(float sX, float sY, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t2 = pivotX * (1.0f - sX);
            float _t3 = pivotY * (1.0f - sY);
            d.m00 = sX * this.m00;
            d.m10 = sX * this.m10;
            d.m01 = sY * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = java.lang.Math.fma(this.m00, _t2, java.lang.Math.fma(this.m01, _t3, this.m02));
            d.m12 = java.lang.Math.fma(this.m10, _t2, java.lang.Math.fma(this.m11, _t3, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            float _t2 = pivotX * (1.0f - sX);
            float _t3 = pivotY * (1.0f - sY);
            d.m00 = sX * this.m00;
            d.m10 = sX * this.m10;
            d.m01 = sY * this.m01;
            d.m11 = sY * this.m11;
            d.m02 = ((this.m00) * (_t2) + (((this.m01) * (_t3) + (this.m02))));
            d.m12 = ((this.m10) * (_t2) + (((this.m11) * (_t3) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Apply a translation by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 translate(Float2R v, @Mutated Float2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? translate_orthogonal_fma(vX, vY, dest) : translate_orthogonal_mulAdd(vX, vY, dest));
    }

    /**
     * Apply a translation by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 translate(Float2R v, @Mutated Double2x3 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
            d.m12 = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            d.m12 = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Apply a translation by {@code v} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 translate(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_fma(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m02 = vX;
                this.m12 = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = this.m02 + vX;
                this.m12 = this.m12 + vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return translate_orthogonal_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_mulAdd(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m02 = vX;
                this.m12 = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = this.m02 + vX;
                this.m12 = this.m12 + vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            this.m02 = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            this.m12 = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_fma(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = java.lang.Math.fma(_rd0, vX, java.lang.Math.fma(_rd2, vY, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, vX, java.lang.Math.fma(_rd3, vY, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = ((_rd0) * (vX) + (((_rd2) * (vY) + (this.m02))));
        d.m12 = ((_rd1) * (vX) + (((_rd3) * (vY) + (this.m12))));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_self_fma(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m02 = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
        d.m12 = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_fma(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = java.lang.Math.fma(_rd0, vX, java.lang.Math.fma(_rd2, vY, this.m02));
        d.m12 = java.lang.Math.fma(_rd1, vX, java.lang.Math.fma(_rd3, vY, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = _rd2;
        d.m11 = _rd3;
        d.m02 = ((_rd0) * (vX) + (((_rd2) * (vY) + (this.m02))));
        d.m12 = ((_rd1) * (vX) + (((_rd3) * (vY) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_self_fma(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m02 = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
        d.m12 = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_self_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m02 = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
        d.m12 = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 translate(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.m02 = this.m02 + vX;
            d.m12 = this.m12 + vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        return (org.joml2.internal.FmaSwitch.ON ? translate_orthogonal_fma(vX, vY, dest) : translate_orthogonal_mulAdd(vX, vY, dest));
    }

    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 translate(float vX, float vY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_fma(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m02 = vX;
                this.m12 = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = this.m02 + vX;
                this.m12 = this.m12 + vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return translate_orthogonal_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_mulAdd(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m02 = vX;
                this.m12 = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                this.m02 = this.m02 + vX;
                this.m12 = this.m12 + vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            this.m02 = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            this.m12 = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
    }

    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 translate(float vX, float vY, @Mutated Double2x3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
            d.m12 = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
            d.properties = Joml.BIT_AFFINE;
            return d;
        } else {
            Double2x3Impl d = (Double2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            d.m12 = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = java.lang.Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = java.lang.Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = ((this.m02) - (left + right) * (_t0_inv));
        d.m12 = ((this.m12) - (bottom + top) * (_t1_inv));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_self_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = java.lang.Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = java.lang.Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_self_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = ((this.m02) - (left + right) * (_t0_inv));
        d.m12 = ((this.m12) - (bottom + top) * (_t1_inv));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_orthogonal_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _sp0 * _rd0;
        d.m10 = _sp0 * _rd1;
        d.m01 = _sp1 * _rd2;
        d.m11 = _sp1 * _rd3;
        d.m02 = java.lang.Math.fma(_rd2, -_sp3, java.lang.Math.fma(-_rd0, _sp2, this.m02));
        d.m12 = java.lang.Math.fma(_rd3, -_sp3, java.lang.Math.fma(-_rd1, _sp2, this.m12));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_orthogonal_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _sp0 * _rd0;
        d.m10 = _sp0 * _rd1;
        d.m01 = _sp1 * _rd2;
        d.m11 = _sp1 * _rd3;
        d.m02 = ((((this.m02) - (_rd0) * (_sp2))) - (_rd2) * (_sp3));
        d.m12 = ((((this.m12) - (_rd1) * (_sp2))) - (_rd3) * (_sp3));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_general_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _sp0 * _rd0;
        d.m10 = _sp0 * _rd1;
        d.m01 = _sp1 * _rd2;
        d.m11 = _sp1 * _rd3;
        d.m02 = this.m02 + java.lang.Math.fma(_rd2, -_sp3, -(_rd0 * _sp2));
        d.m12 = this.m12 + java.lang.Math.fma(_rd3, -_sp3, -(_rd1 * _sp2));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_general_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        Float2x3Impl d = (Float2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = this.m01;
        float _rd3 = this.m11;
        d.m00 = _sp0 * _rd0;
        d.m10 = _sp0 * _rd1;
        d.m01 = _sp1 * _rd2;
        d.m11 = _sp1 * _rd3;
        d.m02 = this.m02 + ((_rd2) * (-_sp3) - (_rd0 * _sp2));
        d.m12 = this.m12 + ((_rd3) * (-_sp3) - (_rd1 * _sp2));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the x coordinate of the left edge of the view rectangle
     * @param right the x coordinate of the right edge of the view rectangle
     * @param bottom the y coordinate of the bottom edge of the view rectangle
     * @param top the y coordinate of the top edge of the view rectangle
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 view(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? view_general_fma(left, right, bottom, top, dest) : view_general_mulAdd(left, right, bottom, top, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x3Impl d = (Float2x3Impl) dest;
            float _t0_inv = 1.0f / (right - left);
            float _t1_inv = 1.0f / (top - bottom);
            d.m00 = _t0_inv + _t0_inv;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = _t1_inv + _t1_inv;
            d.m02 = -(left + right) * _t0_inv;
            d.m12 = -(bottom + top) * _t1_inv;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? view_translation_fma(left, right, bottom, top, dest) : view_translation_mulAdd(left, right, bottom, top, dest));
        return (org.joml2.internal.FmaSwitch.ON ? view_orthogonal_fma(left, right, bottom, top, dest) : view_orthogonal_mulAdd(left, right, bottom, top, dest));
    }

    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the x coordinate of the left edge of the view rectangle
     * @param right the x coordinate of the right edge of the view rectangle
     * @param bottom the y coordinate of the bottom edge of the view rectangle
     * @param top the y coordinate of the top edge of the view rectangle
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 view(float left, float right, float bottom, float top) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return view_general_fma(left, right, bottom, top, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0_inv = 1.0f / (right - left);
                float _t1_inv = 1.0f / (top - bottom);
                this.m00 = _t0_inv + _t0_inv;
                this.m11 = _t1_inv + _t1_inv;
                this.m02 = -(left + right) * _t0_inv;
                this.m12 = -(bottom + top) * _t1_inv;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self_fma(left, right, bottom, top, this);
            return view_orthogonal_fma(left, right, bottom, top, this);
        } else {
            if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return view_general_mulAdd(left, right, bottom, top, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0_inv = 1.0f / (right - left);
                float _t1_inv = 1.0f / (top - bottom);
                this.m00 = _t0_inv + _t0_inv;
                this.m11 = _t1_inv + _t1_inv;
                this.m02 = -(left + right) * _t0_inv;
                this.m12 = -(bottom + top) * _t1_inv;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self_mulAdd(left, right, bottom, top, this);
            return view_orthogonal_mulAdd(left, right, bottom, top, this);
        }
    }

    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the x coordinate of the left edge of the view rectangle
     * @param right the x coordinate of the right edge of the view rectangle
     * @param bottom the y coordinate of the bottom edge of the view rectangle
     * @param top the y coordinate of the top edge of the view rectangle
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 view(float left, float right, float bottom, float top, @Mutated Double2x3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return view_mulAdd(left, right, bottom, top, dest);
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        d.m00 = _sp0 * this.m00;
        d.m10 = _sp0 * this.m10;
        d.m01 = _sp1 * this.m01;
        d.m11 = _sp1 * this.m11;
        d.m02 = this.m02 + java.lang.Math.fma(this.m01, -_sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + java.lang.Math.fma(this.m11, -_sp3, -(this.m10 * _sp2));
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** {@code view} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 view_mulAdd(float left, float right, float bottom, float top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        d.m00 = _sp0 * this.m00;
        d.m10 = _sp0 * this.m10;
        d.m01 = _sp1 * this.m01;
        d.m11 = _sp1 * this.m11;
        d.m02 = this.m02 + ((this.m01) * (-_sp3) - (this.m00 * _sp2));
        d.m12 = this.m12 + ((this.m11) * (-_sp3) - (this.m10 * _sp2));
        d.properties = Joml.BIT_AFFINE;
        return d;
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
    public Float2 mul(Float3R v, @Mutated Float2 dest) {
        Float3Impl vImpl = (Float3Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_fma(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            return mul_translation_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            return mul_translation_mulAdd(vX, vY, vZ, dest);
        }
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
    public Double2 mul(Float3R v, @Mutated Double2 dest) {
        Float3Impl vImpl = (Float3Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        float vZ = vImpl.z;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            return d;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_translation_fma(float vX, float vY, float vZ, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = java.lang.Math.fma(this.m02, vZ, vX);
        d.y = java.lang.Math.fma(this.m12, vZ, vY);
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_translation_mulAdd(float vX, float vY, float vZ, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = ((this.m02) * (vZ) + (vX));
        d.y = ((this.m12) * (vZ) + (vY));
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_fma(float vX, float vY, float vZ, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
        d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_mulAdd(float vX, float vY, float vZ, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
        d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
        return d;
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
    public Float2 mul(float vX, float vY, float vZ, @Mutated Float2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_fma(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            return mul_translation_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            return mul_translation_mulAdd(vX, vY, vZ, dest);
        }
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
    public Double2 mul(float vX, float vY, float vZ, @Mutated Double2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            return d;
        }
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation, and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformDirection(Float2R v, @Mutated Float2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return (org.joml2.internal.FmaSwitch.ON ? transformDirection_general_fma(vX, vY, dest) : transformDirection_general_mulAdd(vX, vY, dest));
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation, and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformDirection(Float2R v, @Mutated Double2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            return d;
        }
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code transformDirection} and {@code transformPosition};
     * reached only through them.
     */
    private Float2 transformDirection_identity(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = vX;
        d.y = vY;
        return d;
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general_fma(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
        d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
        return d;
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = ((this.m00) * (vX) + (this.m01 * vY));
        d.y = ((this.m10) * (vX) + (this.m11 * vY));
        return d;
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation, and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformDirection(float vX, float vY, @Mutated Float2 dest) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return (org.joml2.internal.FmaSwitch.ON ? transformDirection_general_fma(vX, vY, dest) : transformDirection_general_mulAdd(vX, vY, dest));
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation, and store the result
     * in {@code dest}.
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
    public Double2 transformDirection(float vX, float vY, @Mutated Double2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            return d;
        }
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformPosition(Float2R v, @Mutated Float2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return transformPosition_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
            Float2Impl d = (Float2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return transformPosition_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
            Float2Impl d = (Float2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
        }
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double2 transformPosition(Float2R v, @Mutated Double2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
            d.y = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            d.y = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            return d;
        }
    }

    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general_fma(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
        d.y = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
        return d;
    }

    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
        d.y = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
        return d;
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformPosition(float vX, float vY, @Mutated Float2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return transformPosition_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
            Float2Impl d = (Float2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return transformPosition_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
            Float2Impl d = (Float2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
        }
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
    public Double2 transformPosition(float vX, float vY, @Mutated Double2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, java.lang.Math.fma(this.m01, vY, this.m02));
            d.y = java.lang.Math.fma(this.m10, vX, java.lang.Math.fma(this.m11, vY, this.m12));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m00) * (vX) + (((this.m01) * (vY) + (this.m02))));
            d.y = ((this.m10) * (vX) + (((this.m11) * (vY) + (this.m12))));
            return d;
        }
    }

    public float m00() { return this.m00; }
    public float m01() { return this.m01; }
    public float m02() { return this.m02; }
    public float m10() { return this.m10; }
    public float m11() { return this.m11; }
    public float m12() { return this.m12; }

    @Override public String toString() {
        return "Float2x3(\n    " + this.m00 + ", " + this.m01 + ", " + this.m02 + "\n    " + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float2x3Impl)) return false;
        Float2x3Impl o = (Float2x3Impl) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m02) == Float.floatToIntBits(o.m02)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m12) == Float.floatToIntBits(o.m12);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m02);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m12);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m02)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m12);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m02)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m12);
    }

    @Override public boolean equalsEpsilon(Float2x3R other, float epsilon) {
        Float2x3Impl otherImpl = (Float2x3Impl) other;
        return java.lang.Math.abs(m00 - otherImpl.m00) <= epsilon
            && java.lang.Math.abs(m01 - otherImpl.m01) <= epsilon
            && java.lang.Math.abs(m02 - otherImpl.m02) <= epsilon
            && java.lang.Math.abs(m10 - otherImpl.m10) <= epsilon
            && java.lang.Math.abs(m11 - otherImpl.m11) <= epsilon
            && java.lang.Math.abs(m12 - otherImpl.m12) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer storeCM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    @Mutated public Float2x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return this;
    }

    public Float2x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    @Mutated public Float2x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }

    @Mutated public Float2x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }

    @Mutated public Float2x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadCM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[offset + 2];
        this.m11 = (float) src[offset + 3];
        this.m02 = (float) src[offset + 4];
        this.m12 = (float) src[offset + 5];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer storeCMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    @Mutated public Float2x3 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return this;
    }

    public Float2x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }

    @Mutated public Float2x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }

    @Mutated public Float2x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src); }

    @Mutated public Float2x3 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer storeRM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    @Mutated public Float2x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return this;
    }

    public Float2x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    @Mutated public Float2x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    @Mutated public Float2x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }

    @Mutated public Float2x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadRM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[offset + 3];
        this.m11 = (float) src[offset + 4];
        this.m12 = (float) src[offset + 5];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer storeRMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    @Mutated public Float2x3 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float2x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return this;
    }

    public Float2x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }

    @Mutated public Float2x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }

    @Mutated public Float2x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src); }

    @Mutated public Float2x3 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m02 = src[_p2];
        this.m12 = src[_p2 + 1];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadCMRelative(FloatBuffer buf, int stride) {
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

    @Mutated public Float2x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
        return this;
    }

    public Float2x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    @Mutated public Float2x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    @Mutated public Float2x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }

    @Mutated public Float2x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m02 = (float) src[_p2];
        this.m12 = (float) src[_p2 + 1];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadCMRelative(DoubleBuffer buf, int stride) {
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

    @Mutated public Float2x3 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
        return this;
    }

    public Float2x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float2x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }

    @Mutated public Float2x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src, stride); }

    @Mutated public Float2x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m12 = src[_p1 + 2];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeRM(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    @Mutated public Float2x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Float2x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    @Mutated public Float2x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    @Mutated public Float2x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }

    @Mutated public Float2x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        return dest;
    }

    @Mutated public Float2x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m12 = (float) src[_p1 + 2];
        this.properties = determineProperties();
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

    @Mutated public Float2x3 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeRMDouble(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMDoubleAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMDoubleRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    @Mutated public Float2x3 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Float2x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float2x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }

    @Mutated public Float2x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src, stride); }

    @Mutated public Float2x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = this.m02;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = 1.0f;
        return dest;
    }

    public FloatBuffer storeCM3x3(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    public FloatBuffer storeCM3x3Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    public FloatBuffer storeCM3x3Relative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    public ByteBuffer storeCM3x3(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM3x3Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    public ByteBuffer storeCM3x3Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    public Float2x3 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }

    public MemorySegment storeCM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }

    public MemorySegment storeCM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0;
        dest[offset + 6] = this.m02;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = 1.0;
        return dest;
    }

    public DoubleBuffer storeCM3x3(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeCM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    public DoubleBuffer storeCM3x3Relative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    public ByteBuffer storeCM3x3Double(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3DoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM3x3DoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3DoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeCM3x3DoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3DoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    public Float2x3 storeCM3x3DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3DoubleUnsafe(this, address);
    }

    public MemorySegment storeCM3x3Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3Double(this, 0L, dest); }

    public MemorySegment storeCM3x3Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Double(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = 1.0f;
        return dest;
    }

    public FloatBuffer storeRM3x3(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    public FloatBuffer storeRM3x3Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    public FloatBuffer storeRM3x3Relative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    public ByteBuffer storeRM3x3(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM3x3Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    public ByteBuffer storeRM3x3Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    public Float2x3 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }

    public MemorySegment storeRM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3(this, 0L, dest); }

    public MemorySegment storeRM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = 0.0;
        dest[offset + 7] = 0.0;
        dest[offset + 8] = 1.0;
        return dest;
    }

    public DoubleBuffer storeRM3x3(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeRM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    public DoubleBuffer storeRM3x3Relative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    public ByteBuffer storeRM3x3Double(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3DoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM3x3DoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3DoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeRM3x3DoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3DoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    public Float2x3 storeRM3x3DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3DoubleUnsafe(this, address);
    }

    public MemorySegment storeRM3x3Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3Double(this, 0L, dest); }

    public MemorySegment storeRM3x3Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3Double(this, offset, dest);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = 0.0f;
        dest[offset + 9] = 0.0f;
        dest[offset + 10] = 1.0f;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = this.m02;
        dest[offset + 13] = this.m12;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    public FloatBuffer storeCM4x4(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    public FloatBuffer storeCM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    public FloatBuffer storeCM4x4Relative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    public ByteBuffer storeCM4x4(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM4x4Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    public ByteBuffer storeCM4x4Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    public Float2x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    public MemorySegment storeCM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    public MemorySegment storeCM4x4(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0;
        dest[offset + 7] = 0.0;
        dest[offset + 8] = 0.0;
        dest[offset + 9] = 0.0;
        dest[offset + 10] = 1.0;
        dest[offset + 11] = 0.0;
        dest[offset + 12] = this.m02;
        dest[offset + 13] = this.m12;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }

    public DoubleBuffer storeCM4x4(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeCM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    public DoubleBuffer storeCM4x4Relative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    public ByteBuffer storeCM4x4Double(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM4x4DoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeCM4x4DoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    public Float2x3 storeCM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4DoubleUnsafe(this, address);
    }

    public MemorySegment storeCM4x4Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Double(this, 0L, dest); }

    public MemorySegment storeCM4x4Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Double(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.m02;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = 0.0f;
        dest[offset + 9] = 0.0f;
        dest[offset + 10] = 1.0f;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    public FloatBuffer storeRM4x4(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    public FloatBuffer storeRM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    public FloatBuffer storeRM4x4Relative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    public ByteBuffer storeRM4x4(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM4x4Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    public ByteBuffer storeRM4x4Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    public Float2x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    public MemorySegment storeRM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }

    public MemorySegment storeRM4x4(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.m02;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = 0.0;
        dest[offset + 9] = 0.0;
        dest[offset + 10] = 1.0;
        dest[offset + 11] = 0.0;
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }

    public DoubleBuffer storeRM4x4(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    public DoubleBuffer storeRM4x4Relative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    public ByteBuffer storeRM4x4Double(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM4x4DoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeRM4x4DoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    public Float2x3 storeRM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4DoubleUnsafe(this, address);
    }

    public MemorySegment storeRM4x4Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Double(this, 0L, dest); }

    public MemorySegment storeRM4x4Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Double(this, offset, dest);
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
