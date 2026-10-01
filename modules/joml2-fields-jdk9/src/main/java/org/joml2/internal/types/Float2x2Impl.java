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
 * Generated implementation of {@link Float2x2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float2x2Impl implements Float2x2 {
    public float m00;
    public float m10;
    public float m01;
    public float m11;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float2x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x2BbOpsUnsafe()
            : new Float2x2BbOpsApi();
        static final Float2x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x2RawOpsUnsafe()
            : new Float2x2RawOpsApi();
    }

    public Float2x2Impl() {
        m00 = 1;
        m11 = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float2x2Impl(float m00, float m01, float m10, float m11) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.properties = determineProperties();
    }

    public Float2x2Impl(Float2x2R src) {
        Float2x2Impl srcImpl = (Float2x2Impl) src;
        this.m00 = srcImpl.m00;
        this.m10 = srcImpl.m10;
        this.m01 = srcImpl.m01;
        this.m11 = srcImpl.m11;
        this.properties = ((Float2x2Impl) src).properties;
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
     * The bits read this 2x2 matrix homogeneously, as a 1D transform whose last row is
     * {@code (0, 1)} and whose {@code m01} is the translation: a 2D rotation held in a 2x2 matrix
     * gets no bits at all.
     * <p>
     * This is a pure query: it does not update this matrix's cached property bits.
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.m10 != 0 || this.m11 != 1) return 0;
        if (this.m00 != 1) return 1;
        if (this.m01 != 0) return 7;
        return 15;
    }

    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. */
    @Override public boolean isIdentity() { return (this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY; }

    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. */
    @Override public boolean isTranslation() { return (this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION; }

    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. */
    @Override public boolean isOrthogonal() { return (this.properties & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL; }

    /** {@return whether this matrix is known to be affine} O(1) read of the cached property bits; conservative. */
    @Override public boolean isAffine() { return (this.properties & Joml.BIT_AFFINE) == Joml.BIT_AFFINE; }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getColumn} and {@code getRow}; reached only through them.
     */
    private Float2 getColumn_identity(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
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
            case 1: _idxSw2 = this.m01; _idxSw3 = 1.0f; break;
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
    private Float2 getColumn_affine(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw4 = this.m00; _idxSw5 = 0.0f; break;
            case 1: _idxSw4 = this.m01; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw4;
        d.y = _idxSw5;
        return d;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_general(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw6 = this.m00; _idxSw7 = this.m10; break;
            case 1: _idxSw6 = this.m01; _idxSw7 = this.m11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw6;
        d.y = _idxSw7;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getColumn_general(col, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getColumn_translation(col, dest);
        return getColumn_affine(col, dest);
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
        float _idxSw8;
        float _idxSw9;
        switch (col) {
            case 0: _idxSw8 = this.m00; _idxSw9 = this.m10; break;
            case 1: _idxSw8 = this.m01; _idxSw9 = this.m11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw8;
        d.y = _idxSw9;
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
    private Float2 getRow_translation(int row, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw2;
        float _idxSw3;
        switch (row) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = this.m01; break;
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw2;
        d.y = _idxSw3;
        return d;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float2 getRow_affine(int row, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw4 = this.m00; _idxSw5 = this.m01; break;
            case 1: _idxSw4 = 0.0f; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw4;
        d.y = _idxSw5;
        return d;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float2 getRow_general(int row, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw6;
        float _idxSw7;
        switch (row) {
            case 0: _idxSw6 = this.m00; _idxSw7 = this.m01; break;
            case 1: _idxSw6 = this.m10; _idxSw7 = this.m11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw6;
        d.y = _idxSw7;
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
    public Float2 getRow(int row, @Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getRow_general(row, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getRow_translation(row, dest);
        return getRow_affine(row, dest);
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
        Double2Impl d = (Double2Impl) dest;
        float _idxSw8;
        float _idxSw9;
        switch (row) {
            case 0: _idxSw8 = this.m00; _idxSw9 = this.m01; break;
            case 1: _idxSw8 = this.m10; _idxSw9 = this.m11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw8;
        d.y = _idxSw9;
        return d;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float2x2 cofactor_translation(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = -this.m01;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float2x2 cofactor_affine(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = 1.0f;
        d.m10 = -this.m01;
        d.m01 = 0.0f;
        d.m11 = _rd0;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float2x2 cofactor_general(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = this.m11;
        d.m10 = -this.m01;
        d.m01 = -_rd1;
        d.m11 = _rd0;
        d.properties = 0;
        return d;
    }

    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 cofactor(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
        return cofactor_affine(dest);
    }

    /**
     * Compute the cofactor matrix of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 cofactor() {
        if (Joml.RETURN_NEW) return cofactor(Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m10 = -this.m01;
            this.m01 = 0.0f;
            this.properties = 0;
            return this;
        }
        return cofactor_affine(this);
    }

    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 cofactor(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m11;
        d.m10 = -this.m01;
        d.m01 = -this.m10;
        d.m11 = this.m00;
        d.properties = 0;
        return d;
    }

    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
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
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.m11, this.m11, java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01))));
        } else {
            return (float) java.lang.Math.sqrt(((this.m11) * (this.m11) + (((this.m10) * (this.m10) + (((this.m00) * (this.m00) + (this.m01 * this.m01)))))));
        }
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_translation(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = -this.m01;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0_inv);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0_inv);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_self_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m01 = -(this.m01 * _t0_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_self_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m01 = -(this.m01 * _t0_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_general_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        d.m00 = this.m11 * _t5_inv;
        d.m10 = -(this.m10 * _t5_inv);
        d.m01 = -(this.m01 * _t5_inv);
        d.m11 = _rd0 * _t5_inv;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_general_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        d.m00 = this.m11 * _t5_inv;
        d.m10 = -(this.m10 * _t5_inv);
        d.m01 = -(this.m01 * _t5_inv);
        d.m11 = _rd0 * _t5_inv;
        d.properties = 0;
        return d;
    }

    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 invert(@Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.properties = Joml.BIT_IDENTITY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.properties = Joml.BIT_IDENTITY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_mulAdd(dest);
        }
    }

    /**
     * Invert this matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 invert() {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return invert(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m01 = -this.m01;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_affine_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m01 = -this.m01;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_affine_self_mulAdd(this);
        }
    }

    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 invert(@Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t1 = -(this.m01 * this.m10);
            float _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_fma(dest);
            float _t5_inv = 1.0f / _t5;
            d.m00 = this.m11 * _t5_inv;
            d.m10 = -(this.m10 * _t5_inv);
            d.m01 = -(this.m01 * _t5_inv);
            d.m11 = this.m00 * _t5_inv;
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t1 = -(this.m01 * this.m10);
            float _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
            float _t5_inv = 1.0f / _t5;
            d.m00 = this.m11 * _t5_inv;
            d.m10 = -(this.m10 * _t5_inv);
            d.m01 = -(this.m01 * _t5_inv);
            d.m11 = this.m00 * _t5_inv;
            d.properties = 0;
            return d;
        }
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_translation(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1_inv = 1.0f / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0) * _t1_inv;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_orthogonal(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2_inv = 1.0f / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0) * _t2_inv;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_affine(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m00, this.m01, this.m00);
        float _t2_inv = 1.0f / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0) * _t2_inv;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_general_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_general_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_fma(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_fma(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal(dest);
        return invert_degenerate_affine(dest);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_mulAdd(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_mulAdd(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal(dest);
        return invert_degenerate_affine(dest);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_fma(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_mulAdd(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code invertProduct_general}: computes and stores it. Shared by 4
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_s7bc7076_c0_fma(Float2x2Impl _dst, float _t28, float _t29, float _t32, float _t22, float _t23, float _t31, float _t35, float _t27) {
        _dst.m00 = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        _dst.m10 = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
    }

    /**
     * Private column 1 of {@code invertProduct_general}: computes and stores it. Shared by 4
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_s7bc7076_c1_fma(Float2x2Impl _dst, float _t28, float _t24, float _t34, float _t30, float _t22, float _t31, float _t33, float _t27) {
        _dst.m01 = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        _dst.m11 = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
    }

    /** Private tail of {@code invertProduct_general}; reached only through it. */
    private void invertProduct_general_s7bc7076_tail_mulAdd(Float2x2Impl _dst, float _r5, float _t10, float _r7, float _t11, float _r6, float _r4, float _r0, float _t10_inv, float _r3, float _t11_inv, float _r2, float _r1) {
        float _t22 = _r5 / _t10;
        float _t23 = _r7 / _t11;
        float _t24 = _r6 / _t11;
        float _t27 = _r4 / _t10;
        float _t28 = -(_r0 * _t10_inv);
        float _t29 = -(_r3 * _t11_inv);
        float _t30 = -(_r2 * _t11_inv);
        float _t31 = -(_r1 * _t10_inv);
        {
            float _t32 = _t22 * _t23;
            float _t35 = _t29 * _t27;
            _dst.m00 = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
            _dst.m10 = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        }
        {
            float _t34 = _t30 * _t22;
            float _t33 = _t27 * _t24;
            _dst.m01 = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
            _dst.m11 = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        }
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = otherImpl.m01;
        float _r1 = otherImpl.m10;
        float _r2 = this.m01;
        float _r3 = this.m10;
        float _r4 = otherImpl.m00;
        float _r5 = otherImpl.m11;
        float _r6 = this.m00;
        float _r7 = this.m11;
        float _t2 = -(_r0 * _r1);
        float _t3 = -(_r2 * _r3);
        float _t10 = java.lang.Math.fma(_r4, _r5, _t2) - java.lang.Math.fma(_r0, _r1, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11 = java.lang.Math.fma(_r6, _r7, _t3) - java.lang.Math.fma(_r2, _r3, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        {
            float _t10_inv = 1.0f / _t10;
            float _t11_inv = 1.0f / _t11;
            float _t22 = _r5 / _t10;
            float _t23 = _r7 / _t11;
            float _t24 = _r6 / _t11;
            float _t27 = _r4 / _t10;
            float _t28 = -(_r0 * _t10_inv);
            float _t29 = -(_r3 * _t11_inv);
            float _t30 = -(_r2 * _t11_inv);
            float _t31 = -(_r1 * _t10_inv);
            invertProduct_general_s7bc7076_c0_fma(d, _t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27);
            invertProduct_general_s7bc7076_c1_fma(d, _t28, _t24, _t30 * _t22, _t30, _t22, _t31, _t27 * _t24, _t27);
        }
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = otherImpl.m01;
        float _r1 = otherImpl.m10;
        float _r2 = this.m01;
        float _r3 = this.m10;
        float _r4 = otherImpl.m00;
        float _r5 = otherImpl.m11;
        float _r6 = this.m00;
        float _r7 = this.m11;
        float _t2 = -(_r0 * _r1);
        float _t3 = -(_r2 * _r3);
        float _t10 = ((_r4) * (_r5) + (_t2)) - ((_r0) * (_r1) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11 = ((_r6) * (_r7) + (_t3)) - ((_r2) * (_r3) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        invertProduct_general_s7bc7076_tail_mulAdd(d, _r5, _t10, _r7, _t11, _r6, _r4, _r0, 1.0f / _t10, _r3, 1.0f / _t11, _r2, _r1);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(otherImpl.m01 * otherImpl.m10);
        float _t5 = java.lang.Math.fma(otherImpl.m00, otherImpl.m11, _t1) - java.lang.Math.fma(otherImpl.m01, otherImpl.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = otherImpl.m00;
        d.m00 = otherImpl.m11 / _t5;
        d.m10 = -(otherImpl.m10 * _t5_inv);
        d.m01 = -(otherImpl.m01 * _t5_inv);
        d.m11 = _rd0 / _t5;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(otherImpl.m01 * otherImpl.m10);
        float _t5 = ((otherImpl.m00) * (otherImpl.m11) + (_t1)) - ((otherImpl.m01) * (otherImpl.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = otherImpl.m00;
        d.m00 = otherImpl.m11 / _t5;
        d.m10 = -(otherImpl.m10 * _t5_inv);
        d.m01 = -(otherImpl.m01 * _t5_inv);
        d.m11 = _rd0 / _t5;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -this.m01;
        float _t3 = -(otherImpl.m01 * otherImpl.m10);
        float _t7 = java.lang.Math.fma(otherImpl.m00, otherImpl.m11, _t3) - java.lang.Math.fma(otherImpl.m01, otherImpl.m10, _t3);
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t10 = otherImpl.m11 / _t7;
        float _t12 = -(otherImpl.m10 * _t7_inv);
        float _rd0 = otherImpl.m00;
        d.m00 = _t10;
        d.m10 = _t12;
        d.m01 = java.lang.Math.fma(_t1, _t10, -(otherImpl.m01 * _t7_inv));
        d.m11 = java.lang.Math.fma(_t1, _t12, _rd0 / _t7);
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -this.m01;
        float _t3 = -(otherImpl.m01 * otherImpl.m10);
        float _t7 = ((otherImpl.m00) * (otherImpl.m11) + (_t3)) - ((otherImpl.m01) * (otherImpl.m10) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t10 = otherImpl.m11 / _t7;
        float _t12 = -(otherImpl.m10 * _t7_inv);
        float _rd0 = otherImpl.m00;
        d.m00 = _t10;
        d.m10 = _t12;
        d.m01 = ((_t1) * (_t10) - (otherImpl.m01 * _t7_inv));
        d.m11 = ((_t1) * (_t12) + (_rd0 / _t7));
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = -(otherImpl.m01 * otherImpl.m10);
        float _t4 = 1.0f / _t1;
        float _t9 = -(this.m01 / _t1);
        float _t11 = java.lang.Math.fma(otherImpl.m00, otherImpl.m11, _t2) - java.lang.Math.fma(otherImpl.m01, otherImpl.m10, _t2);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t14 = otherImpl.m11 / _t11;
        float _t16 = -(otherImpl.m10 * _t11_inv);
        float _rd0 = otherImpl.m00;
        d.m00 = _t4 * _t14;
        d.m10 = _t16 * _t4;
        d.m01 = java.lang.Math.fma(_t9, _t14, -(otherImpl.m01 * _t11_inv));
        d.m11 = java.lang.Math.fma(_t16, _t9, _rd0 / _t11);
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = -(otherImpl.m01 * otherImpl.m10);
        float _t4 = 1.0f / _t1;
        float _t9 = -(this.m01 / _t1);
        float _t11 = ((otherImpl.m00) * (otherImpl.m11) + (_t2)) - ((otherImpl.m01) * (otherImpl.m10) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t14 = otherImpl.m11 / _t11;
        float _t16 = -(otherImpl.m10 * _t11_inv);
        float _rd0 = otherImpl.m00;
        d.m00 = _t4 * _t14;
        d.m10 = _t16 * _t4;
        d.m01 = ((_t9) * (_t14) - (otherImpl.m01 * _t11_inv));
        d.m11 = ((_t16) * (_t9) + (_rd0 / _t11));
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x2 invertProduct_identity_identity(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_translation(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = -((Float2x2Impl) other).m01;
        d.m11 = 1.0f;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -(otherImpl.m01 / _t0);
        d.m11 = 1.0f;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -(otherImpl.m01 / _t0);
        d.m11 = 1.0f;
        d.properties = ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_identity(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = -this.m01;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_translation(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = -((Float2x2Impl) other).m01 - this.m01;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = 1.0f / _t0;
        d.m00 = _t2;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-this.m01, _t2, -(otherImpl.m01 / _t0));
        d.m11 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = 1.0f / _t0;
        d.m00 = _t2;
        d.m10 = 0.0f;
        d.m01 = ((-this.m01) * (_t2) - (otherImpl.m01 / _t0));
        d.m11 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 / _t0);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 / _t0);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -((Float2x2Impl) other).m01 - this.m01 / _t0;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        d.m00 = 1.0f / _t0;
        d.m10 = 0.0f;
        d.m01 = -((Float2x2Impl) other).m01 - this.m01 / _t0;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t3 = 1.0f / _t0;
        d.m00 = _t3 * (1.0f / _t1);
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 / _t1), _t3, -(otherImpl.m01 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t3 = 1.0f / _t0;
        d.m00 = _t3 * (1.0f / _t1);
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 / _t1)) * (_t3) - (otherImpl.m01 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        d.m00 = this.m11 / _t5;
        d.m10 = -(this.m10 * _t5_inv);
        d.m01 = -(this.m01 * _t5_inv);
        d.m11 = _rd0 / _t5;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        d.m00 = this.m11 / _t5;
        d.m10 = -(this.m10 * _t5_inv);
        d.m01 = -(this.m01 * _t5_inv);
        d.m11 = _rd0 / _t5;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -((Float2x2Impl) other).m01;
        float _t3 = -(this.m01 * this.m10);
        float _t7 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t11 = this.m00 / _t7;
        float _t12 = -(this.m10 * _t7_inv);
        d.m00 = java.lang.Math.fma(_t1, _t12, this.m11 / _t7);
        d.m10 = _t12;
        d.m01 = java.lang.Math.fma(_t1, _t11, -(this.m01 * _t7_inv));
        d.m11 = _t11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -((Float2x2Impl) other).m01;
        float _t3 = -(this.m01 * this.m10);
        float _t7 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t11 = this.m00 / _t7;
        float _t12 = -(this.m10 * _t7_inv);
        d.m00 = ((_t1) * (_t12) + (this.m11 / _t7));
        d.m10 = _t12;
        d.m01 = ((_t1) * (_t11) - (this.m01 * _t7_inv));
        d.m11 = _t11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = -(this.m01 * this.m10);
        float _t5 = 1.0f / _t1;
        float _t7 = -(otherImpl.m01 / _t1);
        float _t11 = java.lang.Math.fma(this.m00, this.m11, _t2) - java.lang.Math.fma(this.m01, this.m10, _t2);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t17 = this.m11 / _t11;
        float _t18 = this.m00 / _t11;
        float _t20 = -(this.m10 * _t11_inv);
        float _t21 = -(this.m01 * _t11_inv);
        float _t22 = _t5 * _t17;
        float _t23 = _t21 * _t5;
        d.m00 = java.lang.Math.fma(_t7, _t20, _t22) + java.lang.Math.fma(_t5, _t17, -_t22);
        d.m10 = _t20;
        d.m01 = java.lang.Math.fma(_t7, _t18, _t23) + java.lang.Math.fma(_t21, _t5, -_t23);
        d.m11 = _t18;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = otherImpl.m00;
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = -(this.m01 * this.m10);
        float _t5 = 1.0f / _t1;
        float _t7 = -(otherImpl.m01 / _t1);
        float _t11 = ((this.m00) * (this.m11) + (_t2)) - ((this.m01) * (this.m10) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t17 = this.m11 / _t11;
        float _t18 = this.m00 / _t11;
        float _t20 = -(this.m10 * _t11_inv);
        float _t21 = -(this.m01 * _t11_inv);
        float _t22 = _t5 * _t17;
        float _t23 = _t21 * _t5;
        d.m00 = ((_t7) * (_t20) + (_t22)) + ((_t5) * (_t17) - (_t22));
        d.m10 = _t20;
        d.m01 = ((_t7) * (_t18) + (_t23)) + ((_t21) * (_t5) - (_t23));
        d.m11 = _t18;
        d.properties = 0;
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
    public Float2x2 invertProduct(Float2x2R other, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(other, dest);
        return invertProduct_mulAdd(other, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_fma(Float2x2R other, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma(other, dest);
            return invertProduct_general_affine_fma(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_affine_fma(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_fma(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s8aa2765e_1_fma(other, dest, q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd(other, dest);
            return invertProduct_general_affine_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_affine_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_mulAdd(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s8aa2765e_1_mulAdd(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_s8aa2765e_1_fma(Float2x2R other, Float2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma(other, dest);
        return invertProduct_affine_affine_fma(other, dest);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_s8aa2765e_1_mulAdd(Float2x2R other, Float2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd(other, dest);
        return invertProduct_affine_affine_mulAdd(other, dest);
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
    @Mutated public Float2x2 invertProduct(Float2x2R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.float2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(other);
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma(other, this);
            return invertProduct_general_affine_fma(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_affine_fma(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_fma(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_fma(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_sf1dc882e_1_fma(other, q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_mulAdd(Float2x2R other) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd(other, this);
            return invertProduct_general_affine_mulAdd(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_affine_mulAdd(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_mulAdd(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_sf1dc882e_1_mulAdd(other, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_sf1dc882e_1_fma(Float2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma(other, this);
        return invertProduct_affine_affine_fma(other, this);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_sf1dc882e_1_mulAdd(Float2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd(other, this);
        return invertProduct_affine_affine_mulAdd(other, this);
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
    public Double2x2 invertProduct(Float2x2R other, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(m00, m01, m10, m11, dest);
        Double2x2Impl d = (Double2x2Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r2 = this.m00;
        float _r3 = this.m11;
        float _t2 = -(m01 * m10);
        float _t3 = -(_r0 * _r1);
        float _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t11 = java.lang.Math.fma(_r2, _r3, _t3) - java.lang.Math.fma(_r0, _r1, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        invertProduct_s4b3e3c47_tail_fma(d, _r1, 1.0f / _t11, _r0, m10, _t10_inv, m11 / _t10, _r3 / _t11, m00 / _t10, _r2 / _t11, -(m01 * _t10_inv));
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s5626c092_tail_mulAdd(Float2x2Impl _dst, float _r1, float _t11_inv, float _r0, float m10, float _t10_inv, float _t22, float _t23, float _t27, float _t24, float _t28) {
        float _t29 = -(_r1 * _t11_inv);
        float _t30 = -(_r0 * _t11_inv);
        float _t31 = -(m10 * _t10_inv);
        {
            float _t32 = _t22 * _t23;
            float _t35 = _t29 * _t27;
            _dst.m00 = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
            _dst.m10 = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        }
        {
            float _t34 = _t30 * _t22;
            float _t33 = _t27 * _t24;
            _dst.m01 = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
            _dst.m11 = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        }
    }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of the
     * product, which can be as large as their product.
     * <p>
     * Valid input: the product of this matrix and {@code (m00, m01, m10, m11)} must be invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 invertProduct(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(m00, m01, m10, m11, dest);
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r2 = this.m00;
        float _r3 = this.m11;
        float _t2 = -(m01 * m10);
        float _t3 = -(_r0 * _r1);
        float _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t11 = java.lang.Math.fma(_r2, _r3, _t3) - java.lang.Math.fma(_r0, _r1, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        {
            float _t11_inv = 1.0f / _t11;
            float _t22 = m11 / _t10;
            float _t23 = _r3 / _t11;
            float _t27 = m00 / _t10;
            float _t24 = _r2 / _t11;
            float _t28 = -(m01 * _t10_inv);
            float _t29 = -(_r1 * _t11_inv);
            float _t30 = -(_r0 * _t11_inv);
            float _t31 = -(m10 * _t10_inv);
            invertProduct_general_s7bc7076_c0_fma(d, _t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27);
            invertProduct_general_s7bc7076_c1_fma(d, _t28, _t24, _t30 * _t22, _t30, _t22, _t31, _t27 * _t24, _t27);
        }
        d.properties = 0;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_mulAdd(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r2 = this.m00;
        float _r3 = this.m11;
        float _t2 = -(m01 * m10);
        float _t3 = -(_r0 * _r1);
        float _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t11 = ((_r2) * (_r3) + (_t3)) - ((_r0) * (_r1) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        invertProduct_s5626c092_tail_mulAdd(d, _r1, 1.0f / _t11, _r0, m10, _t10_inv, m11 / _t10, _r3 / _t11, m00 / _t10, _r2 / _t11, -(m01 * _t10_inv));
        d.properties = 0;
        return d;
    }

    /**
     * Private column 0 of {@code invertProduct}: computes and stores it. Shared by 2 identical
     * private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_s4b3e3c47_c0_fma(Double2x2Impl _dst, float _t28, float _t29, float _t32, float _t22, float _t23, float _t31, float _t35, float _t27) {
        _dst.m00 = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        _dst.m10 = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
    }

    /**
     * Private column 1 of {@code invertProduct}: computes and stores it. Shared by 2 identical
     * private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_s4b3e3c47_c1_fma(Double2x2Impl _dst, float _t28, float _t24, float _t34, float _t30, float _t22, float _t31, float _t33, float _t27) {
        _dst.m01 = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        _dst.m11 = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s4b3e3c47_tail_fma(Double2x2Impl _dst, float _r1, float _t11_inv, float _r0, float m10, float _t10_inv, float _t22, float _t23, float _t27, float _t24, float _t28) {
        float _t29 = -(_r1 * _t11_inv);
        float _t30 = -(_r0 * _t11_inv);
        float _t31 = -(m10 * _t10_inv);
        invertProduct_s4b3e3c47_c0_fma(_dst, _t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27);
        invertProduct_s4b3e3c47_c1_fma(_dst, _t28, _t24, _t30 * _t22, _t30, _t22, _t31, _t27 * _t24, _t27);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private void invertProduct_s4b3e3c47_tail_mulAdd(Double2x2Impl _dst, float _r1, float _t11_inv, float _r0, float m10, float _t10_inv, float _t22, float _t23, float _t27, float _t24, float _t28) {
        float _t29 = -(_r1 * _t11_inv);
        float _t30 = -(_r0 * _t11_inv);
        float _t31 = -(m10 * _t10_inv);
        {
            float _t32 = _t22 * _t23;
            float _t35 = _t29 * _t27;
            _dst.m00 = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
            _dst.m10 = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        }
        {
            float _t34 = _t30 * _t22;
            float _t33 = _t27 * _t24;
            _dst.m01 = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
            _dst.m11 = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        }
    }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) and store the result in {@code dest}.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of the
     * product, which can be as large as their product.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the product of this matrix and {@code (m00, m01, m10, m11)} must be invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 invertProduct(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(m00, m01, m10, m11, dest);
        Double2x2Impl d = (Double2x2Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r2 = this.m00;
        float _r3 = this.m11;
        float _t2 = -(m01 * m10);
        float _t3 = -(_r0 * _r1);
        float _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t11 = java.lang.Math.fma(_r2, _r3, _t3) - java.lang.Math.fma(_r0, _r1, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        invertProduct_s4b3e3c47_tail_fma(d, _r1, 1.0f / _t11, _r0, m10, _t10_inv, m11 / _t10, _r3 / _t11, m00 / _t10, _r2 / _t11, -(m01 * _t10_inv));
        d.properties = 0;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _r0 = this.m01;
        float _r1 = this.m10;
        float _r2 = this.m00;
        float _r3 = this.m11;
        float _t2 = -(m01 * m10);
        float _t3 = -(_r0 * _r1);
        float _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t11 = ((_r2) * (_r3) + (_t3)) - ((_r0) * (_r1) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        invertProduct_s4b3e3c47_tail_mulAdd(d, _r1, 1.0f / _t11, _r0, m10, _t10_inv, m11 / _t10, _r3 / _t11, m00 / _t10, _r2 / _t11, -(m01 * _t10_inv));
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private void invertProduct_degenerate_general_s7bc7076_tail_fma(Float2x2Impl _dst, float _t17, float _t18, float _t31, float _t19, float _t16, float _t14, float _t0, float _t36, float _t3, float _t2, float _t13, float _t1, float _t12, float _t36_inv, float _t15) {
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        invertProduct_general_s7bc7076_c0_fma(_dst, _t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53);
        invertProduct_general_s7bc7076_c1_fma(_dst, _t54, _t50, _t56 * _t48, _t56, _t48, _t57, _t53 * _t50, _t53);
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private void invertProduct_degenerate_general_s7bc7076_tail_mulAdd(Float2x2Impl _dst, float _t17, float _t18, float _t31, float _t19, float _t16, float _t14, float _t0, float _t36, float _t3, float _t2, float _t13, float _t1, float _t12, float _t36_inv, float _t15) {
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        {
            float _t32 = _t48 * _t49;
            float _t35 = _t55 * _t53;
            _dst.m00 = ((_t54) * (_t55) + (_t32)) + ((_t48) * (_t49) - (_t32));
            _dst.m10 = ((_t57) * (_t49) + (_t35)) + ((_t55) * (_t53) - (_t35));
        }
        {
            float _t34 = _t56 * _t48;
            float _t33 = _t53 * _t50;
            _dst.m01 = ((_t54) * (_t50) + (_t34)) + ((_t56) * (_t48) - (_t34));
            _dst.m11 = ((_t57) * (_t56) + (_t33)) + ((_t53) * (_t50) - (_t33));
        }
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = otherImpl.m00;
        float _r1 = otherImpl.m01;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _t0 = unitScale(_r0, _r1, _r0);
        float _t1 = unitScale(_r2, _r3, _r2);
        float _t2 = unitScale(_r4, _r5, _r4);
        float _t3 = unitScale(_r6, _r7, _r6);
        float _t12 = _r1 * _t0;
        float _t13 = _r0 * _t0;
        float _t14 = _r3 * _t1;
        float _t15 = _r2 * _t1;
        float _t16 = _r4 * _t2;
        float _t19 = _r7 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        invertProduct_degenerate_general_s7bc7076_tail_fma(d, _r6 * _t3, _r5 * _t2, -(_t19 * _t16), _t19, _t16, _t14, _t0, _t36, _t3, _t2, _t13, _t1, _t12, 1.0f / _t36, _t15);
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = otherImpl.m00;
        float _r1 = otherImpl.m01;
        float _r2 = otherImpl.m10;
        float _r3 = otherImpl.m11;
        float _r4 = this.m10;
        float _r5 = this.m11;
        float _r6 = this.m00;
        float _r7 = this.m01;
        float _t0 = unitScale(_r0, _r1, _r0);
        float _t1 = unitScale(_r2, _r3, _r2);
        float _t2 = unitScale(_r4, _r5, _r4);
        float _t3 = unitScale(_r6, _r7, _r6);
        float _t12 = _r1 * _t0;
        float _t13 = _r0 * _t0;
        float _t14 = _r3 * _t1;
        float _t15 = _r2 * _t1;
        float _t16 = _r4 * _t2;
        float _t19 = _r7 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        invertProduct_degenerate_general_s7bc7076_tail_mulAdd(d, _r6 * _t3, _r5 * _t2, -(_t19 * _t16), _t19, _t16, _t14, _t0, _t36, _t3, _t2, _t13, _t1, _t12, 1.0f / _t36, _t15);
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t6 = otherImpl.m11 * _t0;
        float _t7 = otherImpl.m00 * _t1;
        float _t8 = otherImpl.m01 * _t1;
        float _t9 = otherImpl.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        float _t14_inv = 1.0f / _t14;
        d.m00 = _t6 * _t1 / _t14;
        d.m10 = -(_t9 * _t1 * _t14_inv);
        d.m01 = -(_t8 * _t0 * _t14_inv);
        d.m11 = _t7 * _t0 / _t14;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t6 = otherImpl.m11 * _t0;
        float _t7 = otherImpl.m00 * _t1;
        float _t8 = otherImpl.m01 * _t1;
        float _t9 = otherImpl.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        float _t14_inv = 1.0f / _t14;
        d.m00 = _t6 * _t1 / _t14;
        d.m10 = -(_t9 * _t1 * _t14_inv);
        d.m01 = -(_t8 * _t0 * _t14_inv);
        d.m11 = _t7 * _t0 / _t14;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t3 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t10 = otherImpl.m11 * _t2;
        float _t11 = otherImpl.m00 * _t3;
        float _t12 = otherImpl.m01 * _t3;
        float _t13 = otherImpl.m10 * _t2;
        float _t15 = _t0 / _t0;
        float _t18 = -(this.m01 * _t0 / _t0);
        float _t22 = -(_t12 * _t13);
        float _t25 = java.lang.Math.fma(_t11, _t10, _t22) - java.lang.Math.fma(_t12, _t13, _t22);
        float _t25_inv = 1.0f / _t25;
        float _t28 = _t10 * _t3 / _t25;
        float _t30 = -(_t13 * _t3 * _t25_inv);
        d.m00 = _t28 * _t15;
        d.m10 = _t30 * _t15;
        d.m01 = java.lang.Math.fma(_t18, _t28, -(_t12 * _t2 * _t25_inv));
        d.m11 = java.lang.Math.fma(_t30, _t18, _t11 * _t2 / _t25);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t3 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t10 = otherImpl.m11 * _t2;
        float _t11 = otherImpl.m00 * _t3;
        float _t12 = otherImpl.m01 * _t3;
        float _t13 = otherImpl.m10 * _t2;
        float _t15 = _t0 / _t0;
        float _t18 = -(this.m01 * _t0 / _t0);
        float _t22 = -(_t12 * _t13);
        float _t25 = ((_t11) * (_t10) + (_t22)) - ((_t12) * (_t13) + (_t22));
        float _t25_inv = 1.0f / _t25;
        float _t28 = _t10 * _t3 / _t25;
        float _t30 = -(_t13 * _t3 * _t25_inv);
        d.m00 = _t28 * _t15;
        d.m10 = _t30 * _t15;
        d.m01 = ((_t18) * (_t28) - (_t12 * _t2 * _t25_inv));
        d.m11 = ((_t30) * (_t18) + (_t11 * _t2 / _t25));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t5 = this.m00 * _t0;
        float _t11 = otherImpl.m11 * _t1;
        float _t12 = otherImpl.m00 * _t2;
        float _t13 = otherImpl.m01 * _t2;
        float _t14 = otherImpl.m10 * _t1;
        float _t16 = _t0 / _t5;
        float _t21 = -(this.m01 * _t0 / _t5);
        float _t23 = -(_t13 * _t14);
        float _t26 = java.lang.Math.fma(_t12, _t11, _t23) - java.lang.Math.fma(_t13, _t14, _t23);
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t11 * _t2 / _t26;
        float _t31 = -(_t14 * _t2 * _t26_inv);
        d.m00 = _t29 * _t16;
        d.m10 = _t31 * _t16;
        d.m01 = java.lang.Math.fma(_t21, _t29, -(_t13 * _t1 * _t26_inv));
        d.m11 = java.lang.Math.fma(_t31, _t21, _t12 * _t1 / _t26);
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t5 = this.m00 * _t0;
        float _t11 = otherImpl.m11 * _t1;
        float _t12 = otherImpl.m00 * _t2;
        float _t13 = otherImpl.m01 * _t2;
        float _t14 = otherImpl.m10 * _t1;
        float _t16 = _t0 / _t5;
        float _t21 = -(this.m01 * _t0 / _t5);
        float _t23 = -(_t13 * _t14);
        float _t26 = ((_t12) * (_t11) + (_t23)) - ((_t13) * (_t14) + (_t23));
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t11 * _t2 / _t26;
        float _t31 = -(_t14 * _t2 * _t26_inv);
        d.m00 = _t29 * _t16;
        d.m10 = _t31 * _t16;
        d.m01 = ((_t21) * (_t29) - (_t13 * _t1 * _t26_inv));
        d.m11 = ((_t31) * (_t21) + (_t12 * _t1 / _t26));
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t2 = unitScale(this.m00, this.m01, this.m00);
        float _t9 = otherImpl.m11 * _t0;
        float _t10 = otherImpl.m00 * _t1;
        float _t11 = otherImpl.m01 * _t1;
        float _t12 = otherImpl.m10 * _t0;
        float _t13 = this.m00 * _t2;
        float _t18 = _t2 / _t13;
        float _t21 = -(_t11 * _t12);
        float _t23 = -(this.m01 * _t2 / _t13);
        float _t26 = java.lang.Math.fma(_t10, _t9, _t21) - java.lang.Math.fma(_t11, _t12, _t21);
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t9 * _t1 / _t26;
        float _t31 = -(_t12 * _t1 * _t26_inv);
        d.m00 = _t29 * _t18;
        d.m10 = _t31 * _t18;
        d.m01 = java.lang.Math.fma(_t23, _t29, -(_t11 * _t0 * _t26_inv));
        d.m11 = java.lang.Math.fma(_t31, _t23, _t10 * _t0 / _t26);
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m10, otherImpl.m11, otherImpl.m10);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t2 = unitScale(this.m00, this.m01, this.m00);
        float _t9 = otherImpl.m11 * _t0;
        float _t10 = otherImpl.m00 * _t1;
        float _t11 = otherImpl.m01 * _t1;
        float _t12 = otherImpl.m10 * _t0;
        float _t13 = this.m00 * _t2;
        float _t18 = _t2 / _t13;
        float _t21 = -(_t11 * _t12);
        float _t23 = -(this.m01 * _t2 / _t13);
        float _t26 = ((_t10) * (_t9) + (_t21)) - ((_t11) * (_t12) + (_t21));
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t9 * _t1 / _t26;
        float _t31 = -(_t12 * _t1 * _t26_inv);
        d.m00 = _t29 * _t18;
        d.m10 = _t31 * _t18;
        d.m01 = ((_t23) * (_t29) - (_t11 * _t0 * _t26_inv));
        d.m11 = ((_t31) * (_t23) + (_t10 * _t0 / _t26));
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_translation(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        d.m00 = _t0 / _t0;
        d.m10 = 0.0f;
        d.m01 = -(otherImpl.m01 * _t0 / _t0);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_affine(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t2 = otherImpl.m00 * _t0;
        d.m00 = _t0 / _t2;
        d.m10 = 0.0f;
        d.m01 = -(otherImpl.m01 * _t0 / _t2);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_identity(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        d.m00 = _t0 / _t0;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0 / _t0);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t1 = unitScale(1.0f, this.m01, 1.0f);
        float _t5 = _t0 / _t0;
        d.m00 = _t5 * (_t1 / _t1);
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t1 / _t1), _t5, -(otherImpl.m01 * _t0 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t1 = unitScale(1.0f, this.m01, 1.0f);
        float _t5 = _t0 / _t0;
        d.m00 = _t5 * (_t1 / _t1);
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 * _t1 / _t1)) * (_t5) - (otherImpl.m01 * _t0 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t4 = otherImpl.m00 * _t2;
        float _t6 = _t2 / _t4;
        d.m00 = _t0 / _t0 * _t6;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t0 / _t0), _t6, -(otherImpl.m01 * _t2 / _t4));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t4 = otherImpl.m00 * _t2;
        float _t6 = _t2 / _t4;
        d.m00 = _t0 / _t0 * _t6;
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 * _t0 / _t0)) * (_t6) - (otherImpl.m01 * _t2 / _t4));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_identity(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2 = this.m00 * _t0;
        d.m00 = _t0 / _t2;
        d.m10 = 0.0f;
        d.m01 = -(this.m01 * _t0 / _t2);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t1 = unitScale(1.0f, this.m01, 1.0f);
        float _t4 = this.m00 * _t1;
        float _t6 = _t0 / _t0;
        d.m00 = _t6 * (_t1 / _t4);
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t1 / _t4), _t6, -(otherImpl.m01 * _t0 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t1 = unitScale(1.0f, this.m01, 1.0f);
        float _t4 = this.m00 * _t1;
        float _t6 = _t0 / _t0;
        d.m00 = _t6 * (_t1 / _t4);
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 * _t1 / _t4)) * (_t6) - (otherImpl.m01 * _t0 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t3 = this.m00 * _t0;
        float _t5 = otherImpl.m00 * _t1;
        float _t7 = _t1 / _t5;
        d.m00 = _t0 / _t3 * _t7;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t0 / _t3), _t7, -(otherImpl.m01 * _t1 / _t5));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t3 = this.m00 * _t0;
        float _t5 = otherImpl.m00 * _t1;
        float _t7 = _t1 / _t5;
        d.m00 = _t0 / _t3 * _t7;
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 * _t0 / _t3)) * (_t7) - (otherImpl.m01 * _t1 / _t5));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t2 = unitScale(this.m00, this.m01, this.m00);
        float _t4 = this.m00 * _t2;
        float _t6 = _t0 / _t0;
        d.m00 = _t6 * (_t2 / _t4);
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t2 / _t4), _t6, -(otherImpl.m01 * _t0 / _t0));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t4 = otherImpl.m00 * _t0;
        float _t5 = this.m00 * _t1;
        float _t7 = _t0 / _t4;
        d.m00 = _t7 * (_t1 / _t5);
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(-(this.m01 * _t1 / _t5), _t7, -(otherImpl.m01 * _t0 / _t4));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t4 = otherImpl.m00 * _t0;
        float _t5 = this.m00 * _t1;
        float _t7 = _t0 / _t4;
        d.m00 = _t7 * (_t1 / _t5);
        d.m10 = 0.0f;
        d.m01 = ((-(this.m01 * _t1 / _t5)) * (_t7) - (otherImpl.m01 * _t0 / _t4));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_identity_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        float _t14_inv = 1.0f / _t14;
        d.m00 = _t6 * _t1 / _t14;
        d.m10 = -(_t9 * _t1 * _t14_inv);
        d.m01 = -(_t8 * _t0 * _t14_inv);
        d.m11 = _t7 * _t0 / _t14;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_identity_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        float _t14_inv = 1.0f / _t14;
        d.m00 = _t6 * _t1 / _t14;
        d.m10 = -(_t9 * _t1 * _t14_inv);
        d.m01 = -(_t8 * _t0 * _t14_inv);
        d.m11 = _t7 * _t0 / _t14;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t2 = unitScale(this.m10, this.m11, this.m10);
        float _t3 = unitScale(this.m00, this.m01, this.m00);
        float _t10 = this.m10 * _t2;
        float _t11 = this.m00 * _t3;
        float _t12 = this.m11 * _t2;
        float _t13 = this.m01 * _t3;
        float _t15 = _t0 / _t0;
        float _t18 = -(otherImpl.m01 * _t0 / _t0);
        float _t23 = -(_t13 * _t10);
        float _t26 = java.lang.Math.fma(_t11, _t12, _t23) - java.lang.Math.fma(_t13, _t10, _t23);
        float _t26_inv = 1.0f / _t26;
        float _t31 = _t12 * _t3 / _t26;
        float _t32 = _t11 * _t2 / _t26;
        float _t33 = -(_t10 * _t3 * _t26_inv);
        float _t34 = _t31 * _t15;
        float _t35 = _t18 * _t32;
        d.m00 = java.lang.Math.fma(_t33, _t18, _t34) + java.lang.Math.fma(_t31, _t15, -_t34);
        d.m10 = _t33;
        d.m01 = java.lang.Math.fma(-(_t13 * _t2 * _t26_inv), _t15, _t35) + java.lang.Math.fma(_t18, _t32, -_t35);
        d.m11 = _t32;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
        float _t2 = unitScale(this.m10, this.m11, this.m10);
        float _t3 = unitScale(this.m00, this.m01, this.m00);
        float _t10 = this.m10 * _t2;
        float _t11 = this.m00 * _t3;
        float _t12 = this.m11 * _t2;
        float _t13 = this.m01 * _t3;
        float _t15 = _t0 / _t0;
        float _t18 = -(otherImpl.m01 * _t0 / _t0);
        float _t23 = -(_t13 * _t10);
        float _t26 = ((_t11) * (_t12) + (_t23)) - ((_t13) * (_t10) + (_t23));
        float _t26_inv = 1.0f / _t26;
        float _t31 = _t12 * _t3 / _t26;
        float _t32 = _t11 * _t2 / _t26;
        float _t33 = -(_t10 * _t3 * _t26_inv);
        float _t34 = _t31 * _t15;
        float _t35 = _t18 * _t32;
        d.m00 = ((_t33) * (_t18) + (_t34)) + ((_t31) * (_t15) - (_t34));
        d.m10 = _t33;
        d.m01 = ((_t35) - (_t13 * _t2 * _t26_inv) * (_t15)) + ((_t18) * (_t32) - (_t35));
        d.m11 = _t32;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t9 = this.m10 * _t0;
        float _t10 = this.m00 * _t1;
        float _t11 = this.m11 * _t0;
        float _t12 = this.m01 * _t1;
        float _t14 = otherImpl.m00 * _t2;
        float _t19 = _t2 / _t14;
        float _t22 = -(_t12 * _t9);
        float _t24 = -(otherImpl.m01 * _t2 / _t14);
        float _t27 = java.lang.Math.fma(_t10, _t11, _t22) - java.lang.Math.fma(_t12, _t9, _t22);
        float _t27_inv = 1.0f / _t27;
        float _t32 = _t11 * _t1 / _t27;
        float _t33 = _t10 * _t0 / _t27;
        float _t34 = -(_t9 * _t1 * _t27_inv);
        float _t35 = _t32 * _t19;
        float _t36 = _t24 * _t33;
        d.m00 = java.lang.Math.fma(_t34, _t24, _t35) + java.lang.Math.fma(_t32, _t19, -_t35);
        d.m10 = _t34;
        d.m01 = java.lang.Math.fma(-(_t12 * _t0 * _t27_inv), _t19, _t36) + java.lang.Math.fma(_t24, _t33, -_t36);
        d.m11 = _t33;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t2 = unitScale(otherImpl.m00, otherImpl.m01, otherImpl.m00);
        float _t9 = this.m10 * _t0;
        float _t10 = this.m00 * _t1;
        float _t11 = this.m11 * _t0;
        float _t12 = this.m01 * _t1;
        float _t14 = otherImpl.m00 * _t2;
        float _t19 = _t2 / _t14;
        float _t22 = -(_t12 * _t9);
        float _t24 = -(otherImpl.m01 * _t2 / _t14);
        float _t27 = ((_t10) * (_t11) + (_t22)) - ((_t12) * (_t9) + (_t22));
        float _t27_inv = 1.0f / _t27;
        float _t32 = _t11 * _t1 / _t27;
        float _t33 = _t10 * _t0 / _t27;
        float _t34 = -(_t9 * _t1 * _t27_inv);
        float _t35 = _t32 * _t19;
        float _t36 = _t24 * _t33;
        d.m00 = ((_t34) * (_t24) + (_t35)) + ((_t32) * (_t19) - (_t35));
        d.m10 = _t34;
        d.m01 = ((_t36) - (_t12 * _t0 * _t27_inv) * (_t19)) + ((_t24) * (_t33) - (_t36));
        d.m11 = _t33;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_fma(Float2x2R other, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_fma(dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_fma(other, dest);
            return invertProduct_degenerate_general_affine_fma(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other, dest);
            return invertProduct_degenerate_identity_affine(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_fma(other, dest);
            return invertProduct_degenerate_translation_affine_fma(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_fma(other, dest);
            return invertProduct_degenerate_orthogonal_affine_fma(other, dest);
        }
        return invertProduct_degenerate_se38c19b_1_fma(other, dest, q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_mulAdd(dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_mulAdd(other, dest);
            return invertProduct_degenerate_general_affine_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other, dest);
            return invertProduct_degenerate_identity_affine(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_mulAdd(other, dest);
            return invertProduct_degenerate_translation_affine_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_mulAdd(other, dest);
            return invertProduct_degenerate_orthogonal_affine_mulAdd(other, dest);
        }
        return invertProduct_degenerate_se38c19b_1_mulAdd(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_se38c19b_1_fma(Float2x2R other, Float2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _t0 = unitScale(this.m00, this.m01, this.m00);
            float _t2 = this.m00 * _t0;
            d.m00 = _t0 / _t2;
            d.m10 = 0.0f;
            d.m01 = -(this.m01 * _t0 / _t2);
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return d;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_fma(other, dest);
        return invertProduct_degenerate_affine_affine_fma(other, dest);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_se38c19b_1_mulAdd(Float2x2R other, Float2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _t0 = unitScale(this.m00, this.m01, this.m00);
            float _t2 = this.m00 * _t0;
            d.m00 = _t0 / _t2;
            d.m10 = 0.0f;
            d.m01 = -(this.m01 * _t0 / _t2);
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return d;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x2Impl otherImpl = (Float2x2Impl) other;
            Float2x2Impl d = (Float2x2Impl) dest;
            float _t0 = unitScale(1.0f, otherImpl.m01, 1.0f);
            float _t2 = unitScale(this.m00, this.m01, this.m00);
            float _t4 = this.m00 * _t2;
            float _t6 = _t0 / _t0;
            d.m00 = _t6 * (_t2 / _t4);
            d.m10 = 0.0f;
            d.m01 = ((-(this.m01 * _t2 / _t4)) * (_t6) - (otherImpl.m01 * _t0 / _t0));
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return d;
        }
        return invertProduct_degenerate_affine_affine_mulAdd(other, dest);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s5626c092_tail_fma(Float2x2Impl _dst, float _t18, float _t3, float _t37, float _t17, float _t2, float _t13, float _t1, float _t36, float _t12, float _t36_inv, float _t16, float _t37_inv, float _t19, float _t15, float _t0, float _t48) {
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        invertProduct_general_s7bc7076_c0_fma(_dst, _t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53);
        invertProduct_general_s7bc7076_c1_fma(_dst, _t54, _t50, _t56 * _t48, _t56, _t48, _t57, _t53 * _t50, _t53);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s5626c092_tail_mulAdd(Float2x2Impl _dst, float _t18, float _t3, float _t37, float _t17, float _t2, float _t13, float _t1, float _t36, float _t12, float _t36_inv, float _t16, float _t37_inv, float _t19, float _t15, float _t0, float _t48) {
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        {
            float _t32 = _t48 * _t49;
            float _t35 = _t55 * _t53;
            _dst.m00 = ((_t54) * (_t55) + (_t32)) + ((_t48) * (_t49) - (_t32));
            _dst.m10 = ((_t57) * (_t49) + (_t35)) + ((_t55) * (_t53) - (_t35));
        }
        {
            float _t34 = _t56 * _t48;
            float _t33 = _t53 * _t50;
            _dst.m01 = ((_t54) * (_t50) + (_t34)) + ((_t56) * (_t48) - (_t34));
            _dst.m11 = ((_t57) * (_t56) + (_t33)) + ((_t53) * (_t50) - (_t33));
        }
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_fma(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = _r0 * _t2;
        float _t17 = _r2 * _t3;
        float _t18 = _r1 * _t2;
        float _t19 = _r3 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        invertProduct_degenerate_s5626c092_tail_fma(d, _t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0f / _t36, _t16, 1.0f / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36);
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_mulAdd(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = _r0 * _t2;
        float _t17 = _r2 * _t3;
        float _t18 = _r1 * _t2;
        float _t19 = _r3 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        invertProduct_degenerate_s5626c092_tail_mulAdd(d, _t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0f / _t36, _t16, 1.0f / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36);
        d.properties = 0;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s4b3e3c47_tail_fma(Double2x2Impl _dst, float _t18, float _t3, float _t37, float _t17, float _t2, float _t13, float _t1, float _t36, float _t12, float _t36_inv, float _t16, float _t37_inv, float _t19, float _t15, float _t0, float _t48) {
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        invertProduct_s4b3e3c47_c0_fma(_dst, _t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53);
        invertProduct_s4b3e3c47_c1_fma(_dst, _t54, _t50, _t56 * _t48, _t56, _t48, _t57, _t53 * _t50, _t53);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private void invertProduct_degenerate_s4b3e3c47_tail_mulAdd(Double2x2Impl _dst, float _t18, float _t3, float _t37, float _t17, float _t2, float _t13, float _t1, float _t36, float _t12, float _t36_inv, float _t16, float _t37_inv, float _t19, float _t15, float _t0, float _t48) {
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        {
            float _t32 = _t48 * _t49;
            float _t35 = _t55 * _t53;
            _dst.m00 = ((_t54) * (_t55) + (_t32)) + ((_t48) * (_t49) - (_t32));
            _dst.m10 = ((_t57) * (_t49) + (_t35)) + ((_t55) * (_t53) - (_t35));
        }
        {
            float _t34 = _t56 * _t48;
            float _t33 = _t53 * _t50;
            _dst.m01 = ((_t54) * (_t50) + (_t34)) + ((_t56) * (_t48) - (_t34));
            _dst.m11 = ((_t57) * (_t56) + (_t33)) + ((_t53) * (_t50) - (_t33));
        }
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = _r0 * _t2;
        float _t17 = _r2 * _t3;
        float _t18 = _r1 * _t2;
        float _t19 = _r3 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        invertProduct_degenerate_s4b3e3c47_tail_fma(d, _t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0f / _t36, _t16, 1.0f / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36);
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _r0 = this.m10;
        float _r1 = this.m11;
        float _r2 = this.m00;
        float _r3 = this.m01;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(_r0, _r1, _r0);
        float _t3 = unitScale(_r2, _r3, _r2);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = _r0 * _t2;
        float _t17 = _r2 * _t3;
        float _t18 = _r1 * _t2;
        float _t19 = _r3 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        invertProduct_degenerate_s4b3e3c47_tail_mulAdd(d, _t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0f / _t36, _t16, 1.0f / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_self_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_self_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_general_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = this.m11 * _t5_inv;
        d.m10 = -(this.m01 * _t5_inv);
        d.m01 = -(_rd1 * _t5_inv);
        d.m11 = _rd0 * _t5_inv;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_general_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t1 = -(this.m01 * this.m10);
        float _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t5_inv = 1.0f / _t5;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = this.m11 * _t5_inv;
        d.m10 = -(this.m01 * _t5_inv);
        d.m01 = -(_rd1 * _t5_inv);
        d.m11 = _rd0 * _t5_inv;
        d.properties = 0;
        return d;
    }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 normal(@Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.properties = Joml.BIT_IDENTITY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = 1.0f;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = 1.0f;
                d.properties = Joml.BIT_IDENTITY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_mulAdd(dest);
        }
    }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 normal() {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return normal(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m10 = -this.m01;
                this.m01 = 0.0f;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return normal(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m10 = -this.m01;
                this.m01 = 0.0f;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_mulAdd(this);
        }
    }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse, and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 normal(@Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t1 = -(this.m01 * this.m10);
            float _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_fma(dest);
            float _t5_inv = 1.0f / _t5;
            d.m00 = this.m11 * _t5_inv;
            d.m10 = -(this.m01 * _t5_inv);
            d.m01 = -(this.m10 * _t5_inv);
            d.m11 = this.m00 * _t5_inv;
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t1 = -(this.m01 * this.m10);
            float _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
            float _t5_inv = 1.0f / _t5;
            d.m00 = this.m11 * _t5_inv;
            d.m10 = -(this.m01 * _t5_inv);
            d.m01 = -(this.m10 * _t5_inv);
            d.m11 = this.m00 * _t5_inv;
            d.properties = 0;
            return d;
        }
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_translation(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t1_inv = 1.0f / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = -(this.m01 * _t0) * _t1_inv;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_orthogonal(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(1.0f, this.m01, 1.0f);
        float _t2_inv = 1.0f / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = -(this.m01 * _t0) * _t2_inv;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_affine(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m00, this.m01, this.m00);
        float _t2_inv = 1.0f / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = -(this.m01 * _t0) * _t2_inv;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_general_fma(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t8 * _sp1);
        d.m01 = -(_t9 * _sp0);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_general_mulAdd(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t8 * _sp1);
        d.m01 = -(_t9 * _sp0);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_fma(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_fma(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal(dest);
        return normal_degenerate_affine(dest);
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_mulAdd(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_mulAdd(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal(dest);
        return normal_degenerate_affine(dest);
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_fma(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t8 * _sp1);
        d.m01 = -(_t9 * _sp0);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_mulAdd(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        float _t0 = unitScale(this.m10, this.m11, this.m10);
        float _t1 = unitScale(this.m00, this.m01, this.m00);
        float _t6 = this.m11 * _t0;
        float _t7 = this.m00 * _t1;
        float _t8 = this.m01 * _t1;
        float _t9 = this.m10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t8 * _sp1);
        d.m01 = -(_t9 * _sp0);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }

    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public float trace() {
        return this.m00 + this.m11;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_affine(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m01 = 0.0f;
        d.m11 = 1.0f;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_general(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m10;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m01 = _rd0;
        d.m11 = this.m11;
        d.properties = 0;
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
    public Float2x2 transpose(@Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = 1.0f;
            d.m10 = this.m01;
            d.m01 = 0.0f;
            d.m11 = 1.0f;
            d.properties = 0;
            return d;
        }
        return transpose_affine(dest);
    }

    /**
     * Transpose this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 transpose() {
        if (Joml.RETURN_NEW) return transpose(Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            float _rd0 = this.m10;
            this.m10 = this.m01;
            this.m01 = _rd0;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        this.m10 = this.m01;
        this.m01 = 0.0f;
        this.properties = 0;
        return this;
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
    public Double2x2 transpose(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.properties = 0;
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
    public Float2x2 add(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = otherImpl.m00 + this.m00;
        d.m10 = otherImpl.m10 + this.m10;
        d.m01 = otherImpl.m01 + this.m01;
        d.m11 = otherImpl.m11 + this.m11;
        d.properties = 0;
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
    public Double2x2 add(Float2x2R other, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) to this matrix and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 add(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) to this matrix and store the result
     * in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 add(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_translation_fma(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_mulAdd(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0f;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_affine_fma(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = 0.0f;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_mulAdd(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = 0.0f;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_general_fma(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_mulAdd(float scalar, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.properties = 0;
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
    public Float2x2 mul(float scalar, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(scalar, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = scalar;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = scalar;
                d.properties = 0;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(scalar, dest);
            return mul_affine_fma(scalar, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(scalar, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2x2Impl d = (Float2x2Impl) dest;
                d.m00 = scalar;
                d.m10 = 0.0f;
                d.m01 = 0.0f;
                d.m11 = scalar;
                d.properties = 0;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_mulAdd(scalar, dest);
            return mul_affine_mulAdd(scalar, dest);
        }
    }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 mul(float scalar) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = scalar;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m00 = scalar;
                this.m01 = scalar * this.m01;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
            {
                this.m00 = scalar * this.m00;
                this.m01 = scalar * this.m01;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.m00 = scalar;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.m00 = scalar;
                this.m01 = scalar * this.m01;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
            {
                this.m00 = scalar * this.m00;
                this.m01 = scalar * this.m01;
                this.m11 = scalar;
                this.properties = 0;
                return this;
            }
        }
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
    public Double2x2 mul(float scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.properties = 0;
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
    public Float2x2 negate(@Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.properties = 0;
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
    public Double2x2 negate(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.properties = 0;
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
    public Float2x2 sub(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 - otherImpl.m00;
        d.m10 = this.m10 - otherImpl.m10;
        d.m01 = this.m01 - otherImpl.m01;
        d.m11 = this.m11 - otherImpl.m11;
        d.properties = 0;
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
    public Double2x2 sub(Float2x2R other, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.properties = 0;
        return d;
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}) from this matrix and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 sub(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.properties = 0;
        return d;
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}) from this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 sub(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.properties = 0;
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
    @Mutated public Float2x2 set(Float2x2R v) {
        Float2x2Impl vImpl = (Float2x2Impl) v;
        this.m00 = vImpl.m00;
        this.m10 = vImpl.m10;
        this.m01 = vImpl.m01;
        this.m11 = vImpl.m11;
        this.properties = ((Float2x2Impl) v).properties;
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
     * @return this
     */
    @Mutated public Float2x2 set(float m00, float m01, float m10, float m11) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.properties = determineProperties();
        return this;
    }

    /**
     * Set this matrix to the given 2x3 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated public Float2x2 set(Float2x3R m) {
        Float2x3Impl mImpl = (Float2x3Impl) m;
        this.m00 = mImpl.m00;
        this.m10 = mImpl.m10;
        this.m01 = mImpl.m01;
        this.m11 = mImpl.m11;
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
    @Mutated public Float2x2 set(Float3x3R m) {
        Float3x3Impl mImpl = (Float3x3Impl) m;
        this.m00 = mImpl.m00;
        this.m10 = mImpl.m10;
        this.m01 = mImpl.m01;
        this.m11 = mImpl.m11;
        this.properties = determineProperties();
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
    public Double2x2 toDouble(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.properties = this.properties;
        return d;
    }

    /**
     * Extend this matrix to a 2x3 matrix with a zero translation column and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 to2x3(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
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
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x3Impl d = (Float2x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m01 = this.m01;
            d.m11 = 1.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = 0.0f;
        d.m01 = this.m01;
        d.m11 = 1.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Extend this matrix to a 2x3 matrix with a zero translation column and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 to2x3(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.properties = Joml.BIT_AFFINE;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float3x3Impl d = (Float3x3Impl) dest;
            d.m00 = this.m00;
            d.m10 = this.m10;
            d.m20 = 0.0f;
            d.m01 = this.m01;
            d.m11 = this.m11;
            d.m21 = 0.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.m22 = 1.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
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
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float3x3Impl d = (Float3x3Impl) dest;
            d.m00 = 1.0f;
            d.m10 = 0.0f;
            d.m20 = 0.0f;
            d.m01 = this.m01;
            d.m11 = 1.0f;
            d.m21 = 0.0f;
            d.m02 = 0.0f;
            d.m12 = 0.0f;
            d.m22 = 1.0f;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        Float3x3Impl d = (Float3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = 0.0f;
        d.m20 = 0.0f;
        d.m01 = this.m01;
        d.m11 = 1.0f;
        d.m21 = 0.0f;
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
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
        d.m02 = 0.0f;
        d.m12 = 0.0f;
        d.m22 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix, storing them in
     * {@code lower}, {@code diagonal} and {@code upper} respectively.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, so a matrix
     * whose {@code m00} is zero cannot be decomposed by it (the factors are then not finite).
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @param lower will hold the unit lower-triangular factor
     * @param diagonal will hold the diagonal factor
     * @param upper will hold the unit upper-triangular factor
     * @return this
     */
    public Float2x2 decomposeLDU(@Mutated Float2x2 lower, @Mutated Float2x2 diagonal, @Mutated Float2x2 upper) {
        if (!org.joml2.internal.FmaSwitch.ON) return decomposeLDU_mulAdd(lower, diagonal, upper);
        Float2x2Impl d0 = (Float2x2Impl) lower;
        Float2x2Impl d1 = (Float2x2Impl) diagonal;
        Float2x2Impl d2 = (Float2x2Impl) upper;
        float _rcp0 = 1.0f / this.m00;
        float _sp0 = this.m10 * _rcp0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m11;
        d0.m00 = 1.0f;
        d0.m10 = _sp0;
        d0.m01 = 0.0f;
        d0.m11 = 1.0f;
        d1.m00 = _rd0;
        d1.m10 = 0.0f;
        d1.m01 = 0.0f;
        d1.m11 = java.lang.Math.fma(-_rd1, _sp0, _rd2);
        d2.m00 = 1.0f;
        d2.m10 = 0.0f;
        d2.m01 = _rd1 * _rcp0;
        d2.m11 = 1.0f;
        d0.properties = 0;
        d1.properties = 0;
        d2.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /** {@code decomposeLDU} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 decomposeLDU_mulAdd(@Mutated Float2x2 lower, @Mutated Float2x2 diagonal, @Mutated Float2x2 upper) {
        Float2x2Impl d0 = (Float2x2Impl) lower;
        Float2x2Impl d1 = (Float2x2Impl) diagonal;
        Float2x2Impl d2 = (Float2x2Impl) upper;
        float _rcp0 = 1.0f / this.m00;
        float _sp0 = this.m10 * _rcp0;
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        float _rd2 = this.m11;
        d0.m00 = 1.0f;
        d0.m10 = _sp0;
        d0.m01 = 0.0f;
        d0.m11 = 1.0f;
        d1.m00 = _rd0;
        d1.m10 = 0.0f;
        d1.m01 = 0.0f;
        d1.m11 = ((_rd2) - (_rd1) * (_sp0));
        d2.m00 = 1.0f;
        d2.m10 = 0.0f;
        d2.m01 = _rd1 * _rcp0;
        d2.m11 = 1.0f;
        d0.properties = 0;
        d1.properties = 0;
        d2.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix, storing them in
     * {@code lower}, {@code diagonal} and {@code upper} respectively.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, so a matrix
     * whose {@code m00} is zero cannot be decomposed by it (the factors are then not finite).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @param lower will hold the unit lower-triangular factor
     * @param diagonal will hold the diagonal factor
     * @param upper will hold the unit upper-triangular factor
     * @return this
     */
    public Float2x2 decomposeLDU(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        if (!org.joml2.internal.FmaSwitch.ON) return decomposeLDU_mulAdd(lower, diagonal, upper);
        Double2x2Impl d0 = (Double2x2Impl) lower;
        Double2x2Impl d1 = (Double2x2Impl) diagonal;
        Double2x2Impl d2 = (Double2x2Impl) upper;
        float _rcp0 = 1.0f / this.m00;
        float _sp0 = this.m10 * _rcp0;
        d0.m00 = 1.0f;
        d0.m10 = _sp0;
        d0.m01 = 0.0f;
        d0.m11 = 1.0f;
        d1.m00 = this.m00;
        d1.m10 = 0.0f;
        d1.m01 = 0.0f;
        d1.m11 = java.lang.Math.fma(-this.m01, _sp0, this.m11);
        d2.m00 = 1.0f;
        d2.m10 = 0.0f;
        d2.m01 = this.m01 * _rcp0;
        d2.m11 = 1.0f;
        d0.properties = 0;
        d1.properties = 0;
        d2.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /** {@code decomposeLDU} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 decomposeLDU_mulAdd(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        Double2x2Impl d0 = (Double2x2Impl) lower;
        Double2x2Impl d1 = (Double2x2Impl) diagonal;
        Double2x2Impl d2 = (Double2x2Impl) upper;
        float _rcp0 = 1.0f / this.m00;
        float _sp0 = this.m10 * _rcp0;
        d0.m00 = 1.0f;
        d0.m10 = _sp0;
        d0.m01 = 0.0f;
        d0.m11 = 1.0f;
        d1.m00 = this.m00;
        d1.m10 = 0.0f;
        d1.m01 = 0.0f;
        d1.m11 = ((this.m11) - (this.m01) * (_sp0));
        d2.m00 = 1.0f;
        d2.m10 = 0.0f;
        d2.m01 = this.m01 * _rcp0;
        d2.m11 = 1.0f;
        d0.properties = 0;
        d1.properties = 0;
        d2.properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float2x2 makeIdentity() {
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
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
    public Float2x2 lerp(Float2x2R other, float t, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, otherImpl.m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, otherImpl.m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, otherImpl.m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, otherImpl.m11 - this.m11, this.m11);
            d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x2Impl) other).properties) >> 1);
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = ((t) * (otherImpl.m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (otherImpl.m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (otherImpl.m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (otherImpl.m11 - this.m11) + (this.m11));
            d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x2Impl) other).properties) >> 1);
            return d;
        }
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
    public Double2x2 lerp(Float2x2R other, float t, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 lerp(float m00, float m01, float m10, float m11, float t, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (1e8 towards 1 ends at 0).
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
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 lerp(float m00, float m01, float m10, float m11, float t, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_general_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = this.m00;
        float _rd3 = this.m10;
        d.m00 = java.lang.Math.fma(_rd0, _rd2, rightImpl.m10 * this.m01);
        d.m10 = java.lang.Math.fma(_rd0, _rd3, rightImpl.m10 * this.m11);
        d.m01 = java.lang.Math.fma(_rd1, _rd2, rightImpl.m11 * this.m01);
        d.m11 = java.lang.Math.fma(_rd1, _rd3, rightImpl.m11 * this.m11);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = this.m00;
        float _rd3 = this.m10;
        d.m00 = ((_rd0) * (_rd2) + (rightImpl.m10 * this.m01));
        d.m10 = ((_rd0) * (_rd3) + (rightImpl.m10 * this.m11));
        d.m01 = ((_rd1) * (_rd2) + (rightImpl.m11 * this.m01));
        d.m11 = ((_rd1) * (_rd3) + (rightImpl.m11 * this.m11));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_translation_fma(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = java.lang.Math.fma(rightImpl.m10, this.m01, rightImpl.m00);
        d.m10 = rightImpl.m10;
        d.m01 = java.lang.Math.fma(rightImpl.m11, this.m01, rightImpl.m01);
        d.m11 = rightImpl.m11;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_mulAdd(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = ((rightImpl.m10) * (this.m01) + (rightImpl.m00));
        d.m10 = rightImpl.m10;
        d.m01 = ((rightImpl.m11) * (this.m01) + (rightImpl.m01));
        d.m11 = rightImpl.m11;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m01);
        d.m10 = rightImpl.m10;
        d.m01 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m01);
        d.m11 = rightImpl.m11;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m01));
        d.m10 = rightImpl.m10;
        d.m01 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m01));
        d.m11 = rightImpl.m11;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Float2x2 mul_translation_translation(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = 1.0f;
        d.m10 = 0.0f;
        d.m01 = ((Float2x2Impl) right).m01 + this.m01;
        d.m11 = 1.0f;
        d.properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_affine(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = rightImpl.m00;
        d.m10 = 0.0f;
        d.m01 = rightImpl.m01 + this.m01;
        d.m11 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_translation_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = _rd0;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(((Float2x2Impl) right).m01, _rd0, this.m01);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = rightImpl.m00 * _rd0;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(rightImpl.m01, _rd0, this.m01);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        d.m00 = rightImpl.m00 * _rd0;
        d.m10 = 0.0f;
        d.m01 = ((rightImpl.m01) * (_rd0) + (this.m01));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_translation_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = ((Float2x2Impl) right).m01;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = java.lang.Math.fma(_rd2, _rd0, this.m01);
        d.m11 = java.lang.Math.fma(_rd2, _rd1, this.m11);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_translation_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        float _rd2 = ((Float2x2Impl) right).m01;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = ((_rd2) * (_rd0) + (this.m01));
        d.m11 = ((_rd2) * (_rd1) + (this.m11));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = this.m00;
        float _rd3 = this.m10;
        d.m00 = _rd0 * _rd2;
        d.m10 = _rd0 * _rd3;
        d.m01 = java.lang.Math.fma(_rd1, _rd2, this.m01);
        d.m11 = java.lang.Math.fma(_rd1, _rd3, this.m11);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = rightImpl.m00;
        float _rd1 = rightImpl.m01;
        float _rd2 = this.m00;
        float _rd3 = this.m10;
        d.m00 = _rd0 * _rd2;
        d.m10 = _rd0 * _rd3;
        d.m01 = ((_rd1) * (_rd2) + (this.m01));
        d.m11 = ((_rd1) * (_rd3) + (this.m11));
        d.properties = 0;
        return d;
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 mul(Float2x2R right, @Mutated Float2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        int p = this.properties;
        int q = ((Float2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(right, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_fma(right, dest);
            return mul_general_affine_fma(right, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation_affine(right, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation_affine(right, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_fma(right, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation_fma(right, dest);
        return mul_affine_affine_fma(right, dest);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 mul_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(right, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_mulAdd(right, dest);
            return mul_general_affine_mulAdd(right, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation_affine(right, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation_affine(right, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_mulAdd(right, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _rd0 = this.m00;
            d.m00 = _rd0;
            d.m10 = 0.0f;
            d.m01 = ((((Float2x2Impl) right).m01) * (_rd0) + (this.m01));
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
            return d;
        }
        return mul_affine_affine_mulAdd(right, dest);
    }

    /**
     * Multiply this matrix by {@code right}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x2 mul(Float2x2R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.float2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right);
        int p = this.properties;
        int q = ((Float2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(right, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_fma(right, this);
            return mul_general_affine_fma(right, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation_affine(right, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation_affine(right, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_fma(right, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation_fma(right, this);
        return mul_affine_affine_fma(right, this);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 mul_mulAdd(Float2x2R right) {
        int p = this.properties;
        int q = ((Float2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(right, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_mulAdd(right, this);
            return mul_general_affine_mulAdd(right, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation_affine(right, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation_affine(right, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_mulAdd(right, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float _rd0 = this.m00;
            this.m00 = _rd0;
            this.m10 = 0.0f;
            this.m01 = ((((Float2x2Impl) right).m01) * (_rd0) + (this.m01));
            this.m11 = 1.0f;
            this.properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
            return this;
        }
        return mul_affine_affine_mulAdd(right, this);
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
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
    public Double2x2 mul(Float2x2R right, @Mutated Double2x2 dest) {
        Float2x2Impl rightImpl = (Float2x2Impl) right;
        float m00 = rightImpl.m00;
        float m01 = rightImpl.m01;
        float m10 = rightImpl.m10;
        float m11 = rightImpl.m11;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m10 * this.m01);
            d.m10 = java.lang.Math.fma(m00, this.m10, m10 * this.m11);
            d.m01 = java.lang.Math.fma(m01, this.m00, m11 * this.m01);
            d.m11 = java.lang.Math.fma(m01, this.m10, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m10 * this.m01));
            d.m10 = ((m00) * (this.m10) + (m10 * this.m11));
            d.m01 = ((m01) * (this.m00) + (m11 * this.m01));
            d.m11 = ((m01) * (this.m10) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m10}, {@code m11}) and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 mul(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m10;
            d.m00 = java.lang.Math.fma(m00, _rd0, m10 * this.m01);
            d.m10 = java.lang.Math.fma(m00, _rd1, m10 * this.m11);
            d.m01 = java.lang.Math.fma(m01, _rd0, m11 * this.m01);
            d.m11 = java.lang.Math.fma(m01, _rd1, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m10;
            d.m00 = ((m00) * (_rd0) + (m10 * this.m01));
            d.m10 = ((m00) * (_rd1) + (m10 * this.m11));
            d.m01 = ((m01) * (_rd0) + (m11 * this.m01));
            d.m11 = ((m01) * (_rd1) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m10}, {@code m11}) and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 mul(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m10 * this.m01);
            d.m10 = java.lang.Math.fma(m00, this.m10, m10 * this.m11);
            d.m01 = java.lang.Math.fma(m01, this.m00, m11 * this.m01);
            d.m11 = java.lang.Math.fma(m01, this.m10, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m10 * this.m01));
            d.m10 = ((m00) * (this.m10) + (m10 * this.m11));
            d.m01 = ((m01) * (this.m00) + (m11 * this.m01));
            d.m11 = ((m01) * (this.m10) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m00;
        float _rd3 = this.m01;
        d.m00 = java.lang.Math.fma(_rd0, _rd2, otherImpl.m01 * this.m10);
        d.m10 = java.lang.Math.fma(_rd1, _rd2, otherImpl.m11 * this.m10);
        d.m01 = java.lang.Math.fma(_rd0, _rd3, otherImpl.m01 * this.m11);
        d.m11 = java.lang.Math.fma(_rd1, _rd3, otherImpl.m11 * this.m11);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m00;
        float _rd3 = this.m01;
        d.m00 = ((_rd0) * (_rd2) + (otherImpl.m01 * this.m10));
        d.m10 = ((_rd1) * (_rd2) + (otherImpl.m11 * this.m10));
        d.m01 = ((_rd0) * (_rd3) + (otherImpl.m01 * this.m11));
        d.m11 = ((_rd1) * (_rd3) + (otherImpl.m11 * this.m11));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m01;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = java.lang.Math.fma(_rd0, _rd2, otherImpl.m01);
        d.m11 = java.lang.Math.fma(_rd1, _rd2, otherImpl.m11);
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m01;
        d.m00 = _rd0;
        d.m10 = _rd1;
        d.m01 = ((_rd0) * (_rd2) + (otherImpl.m01));
        d.m11 = ((_rd1) * (_rd2) + (otherImpl.m11));
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m00;
        float _rd3 = this.m01;
        d.m00 = _rd0 * _rd2;
        d.m10 = _rd1 * _rd2;
        d.m01 = java.lang.Math.fma(_rd0, _rd3, otherImpl.m01);
        d.m11 = java.lang.Math.fma(_rd1, _rd3, otherImpl.m11);
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        float _rd1 = otherImpl.m10;
        float _rd2 = this.m00;
        float _rd3 = this.m01;
        d.m00 = _rd0 * _rd2;
        d.m10 = _rd1 * _rd2;
        d.m01 = ((_rd0) * (_rd3) + (otherImpl.m01));
        d.m11 = ((_rd1) * (_rd3) + (otherImpl.m11));
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = _rd0;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(_rd0, this.m01, otherImpl.m01);
        d.m11 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = _rd0;
        d.m10 = 0.0f;
        d.m01 = ((_rd0) * (this.m01) + (otherImpl.m01));
        d.m11 = 1.0f;
        d.properties = _props;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = _rd0 * this.m00;
        d.m10 = 0.0f;
        d.m01 = java.lang.Math.fma(_rd0, this.m01, otherImpl.m01);
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = _rd0 * this.m00;
        d.m10 = 0.0f;
        d.m01 = ((_rd0) * (this.m01) + (otherImpl.m01));
        d.m11 = 1.0f;
        d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = java.lang.Math.fma(otherImpl.m01, this.m10, this.m00);
        d.m10 = this.m10;
        d.m01 = java.lang.Math.fma(otherImpl.m01, this.m11, this.m01);
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = ((otherImpl.m01) * (this.m10) + (this.m00));
        d.m10 = this.m10;
        d.m01 = ((otherImpl.m01) * (this.m11) + (this.m01));
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = java.lang.Math.fma(_rd0, this.m00, otherImpl.m01 * this.m10);
        d.m10 = this.m10;
        d.m01 = java.lang.Math.fma(_rd0, this.m01, otherImpl.m01 * this.m11);
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        Float2x2Impl d = (Float2x2Impl) dest;
        float _rd0 = otherImpl.m00;
        d.m00 = ((_rd0) * (this.m00) + (otherImpl.m01 * this.m10));
        d.m10 = this.m10;
        d.m01 = ((_rd0) * (this.m01) + (otherImpl.m01 * this.m11));
        d.m11 = this.m11;
        d.properties = 0;
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
    public Float2x2 preMul(Float2x2R other, @Mutated Float2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_fma(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_fma(other, dest);
            return preMul_general_affine_fma(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            return preMul_translation_affine_fma(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            return preMul_translation_affine_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_fma(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00;
            d.m10 = 0.0f;
            d.m01 = ((Float2x2Impl) other).m01 + this.m01;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return d;
        }
        return preMul_affine_affine_fma(other, dest);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 preMul_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_mulAdd(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_mulAdd(other, dest);
            return preMul_general_affine_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            return preMul_translation_affine_mulAdd(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            return preMul_translation_affine_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00;
            d.m10 = 0.0f;
            d.m01 = ((Float2x2Impl) other).m01 + this.m01;
            d.m11 = 1.0f;
            d.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return d;
        }
        return preMul_affine_affine_mulAdd(other, dest);
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
    @Mutated public Float2x2 preMul(Float2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.float2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other);
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_fma(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_fma(other, this);
            return preMul_general_affine_fma(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            return preMul_translation_affine_fma(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            return preMul_translation_affine_fma(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_fma(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m00 = this.m00;
            this.m10 = 0.0f;
            this.m01 = ((Float2x2Impl) other).m01 + this.m01;
            this.m11 = 1.0f;
            this.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return this;
        }
        return preMul_affine_affine_fma(other, this);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 preMul_mulAdd(Float2x2R other) {
        int p = this.properties;
        int q = ((Float2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_mulAdd(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_mulAdd(other, this);
            return preMul_general_affine_mulAdd(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            return preMul_translation_affine_mulAdd(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            return preMul_translation_affine_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_mulAdd(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            this.m00 = this.m00;
            this.m10 = 0.0f;
            this.m01 = ((Float2x2Impl) other).m01 + this.m01;
            this.m11 = 1.0f;
            this.properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return this;
        }
        return preMul_affine_affine_mulAdd(other, this);
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
    public Double2x2 preMul(Float2x2R other, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m01 * this.m10);
            d.m10 = java.lang.Math.fma(m10, this.m00, m11 * this.m10);
            d.m01 = java.lang.Math.fma(m00, this.m01, m01 * this.m11);
            d.m11 = java.lang.Math.fma(m10, this.m01, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m01 * this.m10));
            d.m10 = ((m10) * (this.m00) + (m11 * this.m10));
            d.m01 = ((m00) * (this.m01) + (m01 * this.m11));
            d.m11 = ((m10) * (this.m01) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m10}, {@code m11}) onto
     * this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 preMul(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m01;
            d.m00 = java.lang.Math.fma(m00, _rd0, m01 * this.m10);
            d.m10 = java.lang.Math.fma(m10, _rd0, m11 * this.m10);
            d.m01 = java.lang.Math.fma(m00, _rd1, m01 * this.m11);
            d.m11 = java.lang.Math.fma(m10, _rd1, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _rd0 = this.m00;
            float _rd1 = this.m01;
            d.m00 = ((m00) * (_rd0) + (m01 * this.m10));
            d.m10 = ((m10) * (_rd0) + (m11 * this.m10));
            d.m01 = ((m00) * (_rd1) + (m01 * this.m11));
            d.m11 = ((m10) * (_rd1) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m10}, {@code m11}) onto
     * this matrix and store the result in {@code dest}.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 preMul(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(m00, this.m00, m01 * this.m10);
            d.m10 = java.lang.Math.fma(m10, this.m00, m11 * this.m10);
            d.m01 = java.lang.Math.fma(m00, this.m01, m01 * this.m11);
            d.m11 = java.lang.Math.fma(m10, this.m01, m11 * this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((m00) * (this.m00) + (m01 * this.m10));
            d.m10 = ((m10) * (this.m00) + (m11 * this.m10));
            d.m01 = ((m00) * (this.m01) + (m01 * this.m11));
            d.m11 = ((m10) * (this.m01) + (m11 * this.m11));
            d.properties = 0;
            return d;
        }
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
    public Float2x2 addScaled(Float2x2R other, float weight, @Mutated Float2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, otherImpl.m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, otherImpl.m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, otherImpl.m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, otherImpl.m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = ((weight) * (otherImpl.m00) + (this.m00));
            d.m10 = ((weight) * (otherImpl.m10) + (this.m10));
            d.m01 = ((weight) * (otherImpl.m01) + (this.m01));
            d.m11 = ((weight) * (otherImpl.m11) + (this.m11));
            d.properties = 0;
            return d;
        }
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
    public Double2x2 addScaled(Float2x2R other, float weight, @Mutated Double2x2 dest) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        float m00 = otherImpl.m00;
        float m01 = otherImpl.m01;
        float m10 = otherImpl.m10;
        float m11 = otherImpl.m11;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) scaled by {@code weight} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11}) by
     *        before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float2x2 addScaled(float m00, float m01, float m10, float m11, float weight, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) scaled by {@code weight} to this
     * matrix and store the result in {@code dest}.
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
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11}) by
     *        before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 addScaled(float m00, float m01, float m10, float m11, float weight, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.properties = 0;
            return d;
        }
    }

    /**
     * Set this matrix to the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column vector (left operand)
     * @param row the row vector (right operand)
     * @return this
     */
    @Mutated public Float2x2 makeOuterProduct(Float2R col, Float2R row) {
        Float2Impl colImpl = (Float2Impl) col;
        Float2Impl rowImpl = (Float2Impl) row;
        float colX = colImpl.x;
        float colY = colImpl.y;
        float rowX = rowImpl.x;
        float rowY = rowImpl.y;
        this.m00 = colX * rowX;
        this.m10 = colY * rowX;
        this.m01 = colX * rowY;
        this.m11 = colY * rowY;
        this.properties = 0;
        return this;
    }

    /**
     * Set this matrix to the outer product of ({@code colX}, {@code colY}) and ({@code rowX},
     * {@code rowY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param colX the {@code x} component of the vector {@code (colX, colY)}
     * @param colY the {@code y} component of the vector {@code (colX, colY)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY)}
     * @return this
     */
    @Mutated public Float2x2 makeOuterProduct(float colX, float colY, float rowX, float rowY) {
        this.m00 = colX * rowX;
        this.m10 = colY * rowX;
        this.m01 = colX * rowY;
        this.m11 = colY * rowY;
        this.properties = 0;
        return this;
    }

    /**
     * Set this matrix to a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public Float2x2 makeRotation(float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        this.m00 = _t0;
        this.m10 = _t1;
        this.m01 = -_t1;
        this.m11 = _t0;
        this.properties = 0;
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
    @Mutated public Float2x2 makeScaling(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        this.m00 = vX;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = vY;
        this.properties = 0;
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
    @Mutated public Float2x2 makeScaling(float vX, float vY) {
        this.m00 = vX;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = vY;
        this.properties = 0;
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
    @Mutated public Float2x2 makeScaling(float s) {
        this.m00 = s;
        this.m10 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = s;
        this.properties = 0;
        return this;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_translation_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m01;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = java.lang.Math.fma(_rd0, _t0, -_t1);
        d.m11 = java.lang.Math.fma(_rd0, _t1, _t0);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_translation_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m01;
        d.m00 = _t0;
        d.m10 = _t1;
        d.m01 = ((_rd0) * (_t0) - (_t1));
        d.m11 = ((_rd0) * (_t1) + (_t0));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_affine_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        d.m00 = _rd0 * _t0;
        d.m10 = _rd0 * _t1;
        d.m01 = java.lang.Math.fma(_rd1, _t0, -_t1);
        d.m11 = java.lang.Math.fma(_rd1, _t1, _t0);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_affine_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        d.m00 = _rd0 * _t0;
        d.m10 = _rd0 * _t1;
        d.m01 = ((_rd1) * (_t0) - (_t1));
        d.m11 = ((_rd1) * (_t1) + (_t0));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_general_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        d.m00 = java.lang.Math.fma(_rd0, _t0, -(this.m10 * _t1));
        d.m10 = java.lang.Math.fma(_rd0, _t1, this.m10 * _t0);
        d.m01 = java.lang.Math.fma(_rd1, _t0, -(this.m11 * _t1));
        d.m11 = java.lang.Math.fma(_rd1, _t1, this.m11 * _t0);
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_general_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m01;
        d.m00 = ((_rd0) * (_t0) - (this.m10 * _t1));
        d.m10 = ((_rd0) * (_t1) + (this.m10 * _t0));
        d.m01 = ((_rd1) * (_t0) - (this.m11 * _t1));
        d.m11 = ((_rd1) * (_t1) + (this.m11 * _t0));
        d.properties = 0;
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
    public Float2x2 preRotate(float angle, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? preRotate_general_fma(angle, dest) : preRotate_general_mulAdd(angle, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = _t0;
            d.m10 = _t1;
            d.m01 = -_t1;
            d.m11 = _t0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? preRotate_translation_fma(angle, dest) : preRotate_translation_mulAdd(angle, dest));
        return (org.joml2.internal.FmaSwitch.ON ? preRotate_affine_fma(angle, dest) : preRotate_affine_mulAdd(angle, dest));
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
    @Mutated public Float2x2 preRotate(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_fma(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_translation_fma(angle, this);
            return preRotate_affine_fma(angle, this);
        } else {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_mulAdd(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_translation_mulAdd(angle, this);
            return preRotate_affine_mulAdd(angle, this);
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
    public Double2x2 preRotate(float angle, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = java.lang.Math.fma(this.m00, _t0, -(this.m10 * _t1));
            d.m10 = java.lang.Math.fma(this.m00, _t1, this.m10 * _t0);
            d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m11 * _t1));
            d.m11 = java.lang.Math.fma(this.m01, _t1, this.m11 * _t0);
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = ((this.m00) * (_t0) - (this.m10 * _t1));
            d.m10 = ((this.m00) * (_t1) + (this.m10 * _t0));
            d.m01 = ((this.m01) * (_t0) - (this.m11 * _t1));
            d.m11 = ((this.m01) * (_t1) + (this.m11 * _t0));
            d.properties = 0;
            return d;
        }
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
    public Float2x2 preScale(Float2R v, @Mutated Float2x2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vY;
            d.m01 = this.m01 * vX;
            d.m11 = this.m11 * vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = this.m01 * vX;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0f;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
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
    public Double2x2 preScale(Float2R v, @Mutated Double2x2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.properties = 0;
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
    @Mutated public Float2x2 preScale(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vY;
            this.m01 = this.m01 * vX;
            this.m11 = this.m11 * vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = vX;
            this.m01 = this.m01 * vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        this.m00 = this.m00 * vX;
        this.m01 = this.m01 * vX;
        this.m11 = vY;
        this.properties = 0;
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
    public Float2x2 preScale(float vX, float vY, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vY;
            d.m01 = this.m01 * vX;
            d.m11 = this.m11 * vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = this.m01 * vX;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0f;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
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
    @Mutated public Float2x2 preScale(float vX, float vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vY;
            this.m01 = this.m01 * vX;
            this.m11 = this.m11 * vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = vX;
            this.m01 = this.m01 * vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        this.m00 = this.m00 * vX;
        this.m01 = this.m01 * vX;
        this.m11 = vY;
        this.properties = 0;
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
    public Double2x2 preScale(float vX, float vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.properties = 0;
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
    public Float2x2 preScale(float s, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(s, dest);
        return mul_affine_fma(s, dest);
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
    @Mutated public Float2x2 preScale(float s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = s;
            this.m11 = s;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = s;
            this.m01 = s * this.m01;
            this.m11 = s;
            this.properties = 0;
            return this;
        }
        this.m00 = s * this.m00;
        this.m01 = s * this.m01;
        this.m11 = s;
        this.properties = 0;
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
    public Double2x2 preScale(float s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_translation_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        d.m00 = java.lang.Math.fma(this.m01, _t0, _t1);
        d.m10 = _t0;
        d.m01 = java.lang.Math.fma(this.m01, _t1, -_t0);
        d.m11 = _t1;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_translation_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        d.m00 = ((this.m01) * (_t0) + (_t1));
        d.m10 = _t0;
        d.m01 = ((this.m01) * (_t1) - (_t0));
        d.m11 = _t1;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_affine_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        d.m00 = java.lang.Math.fma(_rd0, _t0, this.m01 * _t1);
        d.m10 = _t1;
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(_rd0 * _t1));
        d.m11 = _t0;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_affine_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        d.m00 = ((_rd0) * (_t0) + (this.m01 * _t1));
        d.m10 = _t1;
        d.m01 = ((this.m01) * (_t0) - (_rd0 * _t1));
        d.m11 = _t0;
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_general_fma(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = java.lang.Math.fma(_rd0, _t0, this.m01 * _t1);
        d.m10 = java.lang.Math.fma(_rd1, _t0, this.m11 * _t1);
        d.m01 = java.lang.Math.fma(this.m01, _t0, -(_rd0 * _t1));
        d.m11 = java.lang.Math.fma(this.m11, _t0, -(_rd1 * _t1));
        d.properties = 0;
        return d;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_general_mulAdd(float angle, @Mutated Float2x2 dest) {
        Float2x2Impl d = (Float2x2Impl) dest;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _rd0 = this.m00;
        float _rd1 = this.m10;
        d.m00 = ((_rd0) * (_t0) + (this.m01 * _t1));
        d.m10 = ((_rd1) * (_t0) + (this.m11 * _t1));
        d.m01 = ((this.m01) * (_t0) - (_rd0 * _t1));
        d.m11 = ((this.m11) * (_t0) - (_rd1 * _t1));
        d.properties = 0;
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
    public Float2x2 rotate(float angle, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? rotate_general_fma(angle, dest) : rotate_general_mulAdd(angle, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = _t0;
            d.m10 = _t1;
            d.m01 = -_t1;
            d.m11 = _t0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? rotate_translation_fma(angle, dest) : rotate_translation_mulAdd(angle, dest));
        return (org.joml2.internal.FmaSwitch.ON ? rotate_affine_fma(angle, dest) : rotate_affine_mulAdd(angle, dest));
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
    @Mutated public Float2x2 rotate(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_fma(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_translation_fma(angle, this);
            return rotate_affine_fma(angle, this);
        } else {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_mulAdd(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                this.m00 = _t0;
                this.m10 = _t1;
                this.m01 = -_t1;
                this.m11 = _t0;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_translation_mulAdd(angle, this);
            return rotate_affine_mulAdd(angle, this);
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
    public Double2x2 rotate(float angle, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = java.lang.Math.fma(this.m00, _t0, this.m01 * _t1);
            d.m10 = java.lang.Math.fma(this.m10, _t0, this.m11 * _t1);
            d.m01 = java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1));
            d.m11 = java.lang.Math.fma(this.m11, _t0, -(this.m10 * _t1));
            d.properties = 0;
            return d;
        } else {
            Double2x2Impl d = (Double2x2Impl) dest;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            d.m00 = ((this.m00) * (_t0) + (this.m01 * _t1));
            d.m10 = ((this.m10) * (_t0) + (this.m11 * _t1));
            d.m01 = ((this.m01) * (_t0) - (this.m00 * _t1));
            d.m11 = ((this.m11) * (_t0) - (this.m10 * _t1));
            d.properties = 0;
            return d;
        }
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
    public Float2x2 scale(Float2R v, @Mutated Float2x2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vX;
            d.m01 = this.m01 * vY;
            d.m11 = this.m11 * vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = this.m01 * vY;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0f;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
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
    public Double2x2 scale(Float2R v, @Mutated Double2x2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.properties = 0;
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
    @Mutated public Float2x2 scale(Float2R v) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vX;
            this.m01 = this.m01 * vY;
            this.m11 = this.m11 * vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = vX;
            this.m01 = this.m01 * vY;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        this.m00 = this.m00 * vX;
        this.m01 = this.m01 * vY;
        this.m11 = vY;
        this.properties = 0;
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
    public Float2x2 scale(float vX, float vY, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = this.m00 * vX;
            d.m10 = this.m10 * vX;
            d.m01 = this.m01 * vY;
            d.m11 = this.m11 * vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = vX;
            d.m10 = 0.0f;
            d.m01 = this.m01 * vY;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0f;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
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
    @Mutated public Float2x2 scale(float vX, float vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            this.m00 = this.m00 * vX;
            this.m10 = this.m10 * vX;
            this.m01 = this.m01 * vY;
            this.m11 = this.m11 * vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = vX;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = vX;
            this.m01 = this.m01 * vY;
            this.m11 = vY;
            this.properties = 0;
            return this;
        }
        this.m00 = this.m00 * vX;
        this.m01 = this.m01 * vY;
        this.m11 = vY;
        this.properties = 0;
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
    public Double2x2 scale(float vX, float vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.properties = 0;
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
    public Float2x2 scale(float s, @Mutated Float2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Float2x2Impl d = (Float2x2Impl) dest;
            d.m00 = s;
            d.m10 = 0.0f;
            d.m01 = 0.0f;
            d.m11 = s;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(s, dest);
        return mul_affine_fma(s, dest);
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
    public Double2x2 scale(float s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.properties = 0;
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
    @Mutated public Float2x2 scale(float s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.m00 = s;
            this.m11 = s;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            this.m00 = s;
            this.m01 = s * this.m01;
            this.m11 = s;
            this.properties = 0;
            return this;
        }
        this.m00 = s * this.m00;
        this.m01 = s * this.m01;
        this.m11 = s;
        this.properties = 0;
        return this;
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
    public Float2 mul(Float2R v, @Mutated Float2 dest) {
        Float2Impl vImpl = (Float2Impl) v;
        float vX = vImpl.x;
        float vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                Float2Impl d = (Float2Impl) dest;
                d.x = java.lang.Math.fma(this.m01, vY, vX);
                d.y = vY;
                return d;
            }
            Float2Impl d = (Float2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = vY;
            return d;
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                Float2Impl d = (Float2Impl) dest;
                d.x = ((this.m01) * (vY) + (vX));
                d.y = vY;
                return d;
            }
            Float2Impl d = (Float2Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = vY;
            return d;
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
    public Double2 mul(Float2R v, @Mutated Double2 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2 mul_general_fma(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
        d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
        return d;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = ((this.m00) * (vX) + (this.m01 * vY));
        d.y = ((this.m10) * (vX) + (this.m11 * vY));
        return d;
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
    public Float2 mul(float vX, float vY, @Mutated Float2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                Float2Impl d = (Float2Impl) dest;
                d.x = java.lang.Math.fma(this.m01, vY, vX);
                d.y = vY;
                return d;
            }
            Float2Impl d = (Float2Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = vY;
            return d;
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                Float2Impl d = (Float2Impl) dest;
                d.x = vX;
                d.y = vY;
                return d;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                Float2Impl d = (Float2Impl) dest;
                d.x = ((this.m01) * (vY) + (vX));
                d.y = vY;
                return d;
            }
            Float2Impl d = (Float2Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = vY;
            return d;
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
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(float vX, float vY, @Mutated Double2 dest) {
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

    public float m00() { return this.m00; }
    public float m01() { return this.m01; }
    public float m10() { return this.m10; }
    public float m11() { return this.m11; }

    @Override public String toString() {
        return "Float2x2(\n    " + this.m00 + ", " + this.m01 + "\n    " + this.m10 + ", " + this.m11 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float2x2Impl)) return false;
        Float2x2Impl o = (Float2x2Impl) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m10)
            && Float.isFinite(m11);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m10)
            || Float.isNaN(m11);
    }

    @Override public boolean equalsEpsilon(Float2x2R other, float epsilon) {
        Float2x2Impl otherImpl = (Float2x2Impl) other;
        return java.lang.Math.abs(m00 - otherImpl.m00) <= epsilon
            && java.lang.Math.abs(m01 - otherImpl.m01) <= epsilon
            && java.lang.Math.abs(m10 - otherImpl.m10) <= epsilon
            && java.lang.Math.abs(m11 - otherImpl.m11) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public Float2x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeCM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public Float2x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public Float2x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    @Mutated public Float2x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadCM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[offset + 2];
        this.m11 = (float) src[offset + 3];
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public Float2x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeCMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Float2x2 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Float2x2 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }

    @Mutated public Float2x2 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public Float2x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeRM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public Float2x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public Float2x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    @Mutated public Float2x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadRM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m10 = (float) src[offset + 2];
        this.m11 = (float) src[offset + 3];
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    @Mutated public Float2x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeRMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Float2x2 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float2x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float2x2 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Float2x2 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }

    @Mutated public Float2x2 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
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
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    @Mutated public Float2x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadCMRelative(FloatBuffer buf, int stride) {
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
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    @Mutated public Float2x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Float2x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    @Mutated public Float2x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
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
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    @Mutated public Float2x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeCMDouble(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeCMDoubleAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeCMDoubleRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    @Mutated public Float2x2 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Float2x2 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float2x2 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
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

    @Mutated public Float2x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadRMRelative(FloatBuffer buf, int stride) {
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

    @Mutated public Float2x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Float2x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    @Mutated public Float2x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    @Mutated public Float2x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m10 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
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

    @Mutated public Float2x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadRMRelative(DoubleBuffer buf, int stride) {
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

    @Mutated public Float2x2 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float2x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float2x2 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Float2x2 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float2x2 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
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

    public Float2x2 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0;
        dest[offset + 6] = 0.0;
        dest[offset + 7] = 0.0;
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

    public Float2x2 storeCM3x3DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3DoubleUnsafe(this, address);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0f;
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

    public Float2x2 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = 0.0;
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

    public Float2x2 storeRM3x3DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3DoubleUnsafe(this, address);
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
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
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

    public Float2x2 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
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
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
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

    public Float2x2 storeCM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4DoubleUnsafe(this, address);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
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

    public Float2x2 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = 0.0;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = 0.0;
        dest[offset + 7] = 0.0;
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

    public Float2x2 storeRM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4DoubleUnsafe(this, address);
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
