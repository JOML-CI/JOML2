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
import org.joml2.internal.unsafe.HeapSegments;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float2x2} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float2x2Impl implements Float2x2 {
    public float[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float2x2SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x2SegOpsUnsafe()
            : new Float2x2SegOpsMS();
        static final Float2x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x2BbOpsUnsafe()
            : new Float2x2BbOpsApi();
        static final Float2x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float2x2RawOpsUnsafe()
            : new Float2x2RawOpsApi();
    }

    public Float2x2Impl() {
        data = new float[4];
        data[0] = 1;
        data[3] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float2x2Impl(float m00, float m01, float m10, float m11) {
        float[] dd = this.data = new float[4];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
        this.properties = determineProperties();
    }

    public Float2x2Impl(Float2x2R src) {
        Float2x2Impl s = (Float2x2Impl) src;
        this.data = s.data.clone();
        this.properties = s.properties;
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
        if (this.data[1] != 0 || this.data[3] != 1) return 0;
        if (this.data[0] != 1) return 1;
        if (this.data[2] != 0) return 7;
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
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        return dest;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_translation(int col, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw2;
        float _idxSw3;
        switch (col) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = 0.0f; break;
            case 1: _idxSw2 = sd[2]; _idxSw3 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw2;
        dd[1] = _idxSw3;
        return dest;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_affine(int col, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = 0.0f; break;
            case 1: _idxSw4 = sd[2]; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw4;
        dd[1] = _idxSw5;
        return dest;
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float2 getColumn_general(int col, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw6 = sd[0]; _idxSw7 = sd[1]; break;
            case 1: _idxSw6 = sd[2]; _idxSw7 = sd[3]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw6;
        dd[1] = _idxSw7;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        float _idxSw8;
        float _idxSw9;
        switch (col) {
            case 0: _idxSw8 = sd[0]; _idxSw9 = sd[1]; break;
            case 1: _idxSw8 = sd[2]; _idxSw9 = sd[3]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw8;
        dd[1] = _idxSw9;
        return dest;
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
        float[] sd = this.data;
        return Math.atan2(sd[1], sd[0]);
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float2 getRow_translation(int row, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw2;
        float _idxSw3;
        switch (row) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = sd[2]; break;
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw2;
        dd[1] = _idxSw3;
        return dest;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float2 getRow_affine(int row, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = sd[2]; break;
            case 1: _idxSw4 = 0.0f; _idxSw5 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw4;
        dd[1] = _idxSw5;
        return dest;
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float2 getRow_general(int row, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw6;
        float _idxSw7;
        switch (row) {
            case 0: _idxSw6 = sd[0]; _idxSw7 = sd[2]; break;
            case 1: _idxSw6 = sd[1]; _idxSw7 = sd[3]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw6;
        dd[1] = _idxSw7;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        float _idxSw8;
        float _idxSw9;
        switch (row) {
            case 0: _idxSw8 = sd[0]; _idxSw9 = sd[2]; break;
            case 1: _idxSw8 = sd[1]; _idxSw9 = sd[3]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw8;
        dd[1] = _idxSw9;
        return dest;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float2x2 cofactor_translation(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = -sd[2];
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float2x2 cofactor_affine(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = -sd[2];
        dd[2] = 0.0f;
        dd[3] = sd[0];
        dd[0] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float2x2 cofactor_general(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = sd[3];
        float _buf1 = -sd[2];
        dd[2] = -sd[1];
        dd[3] = sd[0];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
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
            float[] dd = this.data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] dd = this.data;
            dd[1] = -this.data[2];
            dd[2] = 0.0f;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = -sd[2];
        dd[2] = -sd[1];
        dd[3] = sd[0];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
            float[] sd = this.data;
            float _t1 = -(sd[2] * sd[1]);
            return java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        } else {
            float[] sd = this.data;
            float _t1 = -(sd[2] * sd[1]);
            return ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
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
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]))));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (((sd[1]) * (sd[1]) + (((sd[0]) * (sd[0]) + (sd[2] * sd[2])))))));
        }
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_translation(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = -sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_self_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_affine_self_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_general_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x2 invert_general_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
                float[] dd = ((Float2x2Impl) dest).data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x2Impl) dest).data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
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
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.data[2] = -this.data[2];
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_affine_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.data[2] = -this.data[2];
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t1 = -(sd[2] * sd[1]);
            float _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_fma(dest);
            float _t5_inv = 1.0f / _t5;
            dd[0] = sd[3] * _t5_inv;
            dd[1] = -(sd[1] * _t5_inv);
            dd[2] = -(sd[2] * _t5_inv);
            dd[3] = sd[0] * _t5_inv;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t1 = -(sd[2] * sd[1]);
            float _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
            float _t5_inv = 1.0f / _t5;
            dd[0] = sd[3] * _t5_inv;
            dd[1] = -(sd[1] * _t5_inv);
            dd[2] = -(sd[2] * _t5_inv);
            dd[3] = sd[0] * _t5_inv;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_translation(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1_inv = 1.0f / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0) * _t1_inv;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_orthogonal(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2_inv = 1.0f / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_affine(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[0], sd[2], sd[0]);
        float _t2_inv = 1.0f / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_general_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 invert_degenerate_general_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_mulAdd(@Mutated Double2x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t2 = -(otherData[2] * otherData[1]);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = otherData[3] / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = otherData[0] / _t10;
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_s52455c91_1_fma(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t2 = -(otherData[2] * otherData[1]);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = otherData[3] / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = otherData[0] / _t10;
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_s52455c91_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_general_s52455c91_1_fma(Float2x2 dest, float[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35) {
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        dd[1] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
        dd[2] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        dd[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_general_s52455c91_1_mulAdd(Float2x2 dest, float[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35) {
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        dd[1] = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        dd[2] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
        dd[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(otherData[2] * otherData[1]);
        float _t5 = java.lang.Math.fma(otherData[0], otherData[3], _t1) - java.lang.Math.fma(otherData[2], otherData[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = otherData[0] / _t5;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(otherData[2] * otherData[1]);
        float _t5 = ((otherData[0]) * (otherData[3]) + (_t1)) - ((otherData[2]) * (otherData[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = otherData[0] / _t5;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -this.data[2];
        float _t3 = -(otherData[2] * otherData[1]);
        float _t7 = java.lang.Math.fma(otherData[0], otherData[3], _t3) - java.lang.Math.fma(otherData[2], otherData[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t10 = otherData[3] / _t7;
        float _t12 = -(otherData[1] * _t7_inv);
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t10, -(otherData[2] * _t7_inv));
        dd[3] = java.lang.Math.fma(_t1, _t12, otherData[0] / _t7);
        dd[0] = _t10;
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -this.data[2];
        float _t3 = -(otherData[2] * otherData[1]);
        float _t7 = ((otherData[0]) * (otherData[3]) + (_t3)) - ((otherData[2]) * (otherData[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t10 = otherData[3] / _t7;
        float _t12 = -(otherData[1] * _t7_inv);
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t10) - (otherData[2] * _t7_inv));
        dd[3] = ((_t1) * (_t12) + (otherData[0] / _t7));
        dd[0] = _t10;
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = -(otherData[2] * otherData[1]);
        float _t4 = 1.0f / _t1;
        float _t9 = -(sd[2] / _t1);
        float _t11 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t14 = otherData[3] / _t11;
        float _t16 = -(otherData[1] * _t11_inv);
        dd[1] = _t16 * _t4;
        dd[2] = java.lang.Math.fma(_t9, _t14, -(otherData[2] * _t11_inv));
        dd[3] = java.lang.Math.fma(_t16, _t9, otherData[0] / _t11);
        dd[0] = _t4 * _t14;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = -(otherData[2] * otherData[1]);
        float _t4 = 1.0f / _t1;
        float _t9 = -(sd[2] / _t1);
        float _t11 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t14 = otherData[3] / _t11;
        float _t16 = -(otherData[1] * _t11_inv);
        dd[1] = _t16 * _t4;
        dd[2] = ((_t9) * (_t14) - (otherData[2] * _t11_inv));
        dd[3] = ((_t16) * (_t9) + (otherData[0] / _t11));
        dd[0] = _t4 * _t14;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x2 invertProduct_identity_identity(Float2x2R other, @Mutated Float2x2 dest) {
        float[] dd = ((Float2x2Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_translation(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = -otherData[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_identity_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_identity(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = -sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_translation(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = -otherData[2] - sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = 1.0f / _t0;
        dd[0] = _t2;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-sd[2], _t2, -(otherData[2] / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = 1.0f / _t0;
        dd[0] = _t2;
        dd[1] = 0.0f;
        dd[2] = ((-sd[2]) * (_t2) - (otherData[2] / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0f / _t0;
        dd[1] = 0.0f;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t3 = 1.0f / _t0;
        dd[0] = _t3 * (1.0f / _t1);
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] / _t1), _t3, -(otherData[2] / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t3 = 1.0f / _t0;
        dd[0] = _t3 * (1.0f / _t1);
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] / _t1)) * (_t3) - (otherData[2] / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] / _t5;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] / _t5;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -((Float2x2Impl) other).data[2];
        float _t3 = -(sd[2] * sd[1]);
        float _t7 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t11 = sd[0] / _t7;
        float _t12 = -(sd[1] * _t7_inv);
        dd[0] = java.lang.Math.fma(_t1, _t12, sd[3] / _t7);
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t11, -(sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -((Float2x2Impl) other).data[2];
        float _t3 = -(sd[2] * sd[1]);
        float _t7 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _t11 = sd[0] / _t7;
        float _t12 = -(sd[1] * _t7_inv);
        dd[0] = ((_t1) * (_t12) + (sd[3] / _t7));
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t11) - (sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t2 = -(sd[2] * sd[1]);
        float _t5 = 1.0f / _t1;
        float _t7 = -(otherData[2] / _t1);
        float _t11 = java.lang.Math.fma(sd[0], sd[3], _t2) - java.lang.Math.fma(sd[2], sd[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t17 = sd[3] / _t11;
        float _t18 = sd[0] / _t11;
        float _t20 = -(sd[1] * _t11_inv);
        float _t21 = -(sd[2] * _t11_inv);
        float _t22 = _t5 * _t17;
        float _t23 = _t21 * _t5;
        dd[0] = java.lang.Math.fma(_t7, _t20, _t22) + java.lang.Math.fma(_t5, _t17, -_t22);
        dd[1] = _t20;
        dd[2] = java.lang.Math.fma(_t7, _t18, _t23) + java.lang.Math.fma(_t21, _t5, -_t23);
        dd[3] = _t18;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x2 invertProduct_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 1.1754944E-38f && java.lang.Math.abs(_t1) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t2 = -(sd[2] * sd[1]);
        float _t5 = 1.0f / _t1;
        float _t7 = -(otherData[2] / _t1);
        float _t11 = ((sd[0]) * (sd[3]) + (_t2)) - ((sd[2]) * (sd[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t11_inv = 1.0f / _t11;
        float _t17 = sd[3] / _t11;
        float _t18 = sd[0] / _t11;
        float _t20 = -(sd[1] * _t11_inv);
        float _t21 = -(sd[2] * _t11_inv);
        float _t22 = _t5 * _t17;
        float _t23 = _t21 * _t5;
        dd[0] = ((_t7) * (_t20) + (_t22)) + ((_t5) * (_t17) - (_t22));
        dd[1] = _t20;
        dd[2] = ((_t7) * (_t18) + (_t23)) + ((_t21) * (_t5) - (_t23));
        dd[3] = _t18;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(m00, m01, m10, m11, dest);
        return invertProduct_mulAdd(m00, m01, m10, m11, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(m00, m01, m10, m11, dest);
        return invertProduct_mulAdd(m00, m01, m10, m11, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_fma(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t2 = -(m01 * m10);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = m11 / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = m00 / _t10;
        float _t28 = -(m01 * _t10_inv);
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        float _t32 = _t22 * _t23;
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        return invertProduct_s432afb6_1_fma(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 invertProduct_mulAdd(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t2 = -(m01 * m10);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = m11 / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = m00 / _t10;
        float _t28 = -(m01 * _t10_inv);
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        float _t32 = _t22 * _t23;
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        return invertProduct_s432afb6_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_s432afb6_1_fma(Float2x2 dest, float[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t33, float _t34, float _t35) {
        dd[1] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
        dd[2] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        dd[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_s432afb6_1_mulAdd(Float2x2 dest, float[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t33, float _t34, float _t35) {
        dd[1] = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        dd[2] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
        dd[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(m00, m01, m10, m11, dest);
        return invertProduct_mulAdd(m00, m01, m10, m11, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_fma(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t2 = -(m01 * m10);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = m11 / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = m00 / _t10;
        float _t28 = -(m01 * _t10_inv);
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        float _t32 = _t22 * _t23;
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        return invertProduct_s6ddff709_1_fma(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t2 = -(m01 * m10);
        float _t3 = -(sd[2] * sd[1]);
        float _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t10_inv = 1.0f / _t10;
        float _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        float _t11_inv = 1.0f / _t11;
        float _t22 = m11 / _t10;
        float _t23 = sd[3] / _t11;
        float _t24 = sd[0] / _t11;
        float _t27 = m00 / _t10;
        float _t28 = -(m01 * _t10_inv);
        float _t29 = -(sd[1] * _t11_inv);
        float _t30 = -(sd[2] * _t11_inv);
        float _t32 = _t22 * _t23;
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        return invertProduct_s6ddff709_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s6ddff709_1_fma(Double2x2 dest, double[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t33, float _t34, float _t35) {
        dd[1] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
        dd[2] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        dd[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s6ddff709_1_mulAdd(Double2x2 dest, double[] dd, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t33, float _t34, float _t35) {
        dd[1] = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        dd[2] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
        dd[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = otherData[2] * _t0;
        float _t13 = otherData[0] * _t0;
        float _t14 = otherData[3] * _t1;
        float _t15 = otherData[1] * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t36_inv = 1.0f / _t36;
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s5060e3ec_1_fma(dest, ((Float2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = otherData[2] * _t0;
        float _t13 = otherData[0] * _t0;
        float _t14 = otherData[3] * _t1;
        float _t15 = otherData[1] * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t36_inv = 1.0f / _t36;
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s5060e3ec_1_mulAdd(dest, ((Float2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_general_s5060e3ec_1_fma(Float2x2 dest, float[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58) {
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dd[0] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dd[1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dd[2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dd[3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_general_s5060e3ec_1_mulAdd(Float2x2 dest, float[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58) {
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dd[0] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dd[1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dd[2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dd[3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t6 = otherData[3] * _t0;
        float _t7 = otherData[0] * _t1;
        float _t8 = otherData[2] * _t1;
        float _t9 = otherData[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        float _t14_inv = 1.0f / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t6 = otherData[3] * _t0;
        float _t7 = otherData[0] * _t1;
        float _t8 = otherData[2] * _t1;
        float _t9 = otherData[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        float _t14_inv = 1.0f / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t10 = otherData[3] * _t2;
        float _t11 = otherData[0] * _t3;
        float _t12 = otherData[2] * _t3;
        float _t13 = otherData[1] * _t2;
        float _t15 = _t0 / _t0;
        float _t18 = -(sd[2] * _t0 / _t0);
        float _t22 = -(_t12 * _t13);
        float _t25 = java.lang.Math.fma(_t11, _t10, _t22) - java.lang.Math.fma(_t12, _t13, _t22);
        float _t25_inv = 1.0f / _t25;
        float _t28 = _t10 * _t3 / _t25;
        float _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = java.lang.Math.fma(_t18, _t28, -(_t12 * _t2 * _t25_inv));
        dd[3] = java.lang.Math.fma(_t30, _t18, _t11 * _t2 / _t25);
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t10 = otherData[3] * _t2;
        float _t11 = otherData[0] * _t3;
        float _t12 = otherData[2] * _t3;
        float _t13 = otherData[1] * _t2;
        float _t15 = _t0 / _t0;
        float _t18 = -(sd[2] * _t0 / _t0);
        float _t22 = -(_t12 * _t13);
        float _t25 = ((_t11) * (_t10) + (_t22)) - ((_t12) * (_t13) + (_t22));
        float _t25_inv = 1.0f / _t25;
        float _t28 = _t10 * _t3 / _t25;
        float _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = ((_t18) * (_t28) - (_t12 * _t2 * _t25_inv));
        dd[3] = ((_t30) * (_t18) + (_t11 * _t2 / _t25));
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t5 = sd[0] * _t0;
        float _t11 = otherData[3] * _t1;
        float _t12 = otherData[0] * _t2;
        float _t13 = otherData[2] * _t2;
        float _t14 = otherData[1] * _t1;
        float _t16 = _t0 / _t5;
        float _t21 = -(sd[2] * _t0 / _t5);
        float _t23 = -(_t13 * _t14);
        float _t26 = java.lang.Math.fma(_t12, _t11, _t23) - java.lang.Math.fma(_t13, _t14, _t23);
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t11 * _t2 / _t26;
        float _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = java.lang.Math.fma(_t21, _t29, -(_t13 * _t1 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t21, _t12 * _t1 / _t26);
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t5 = sd[0] * _t0;
        float _t11 = otherData[3] * _t1;
        float _t12 = otherData[0] * _t2;
        float _t13 = otherData[2] * _t2;
        float _t14 = otherData[1] * _t1;
        float _t16 = _t0 / _t5;
        float _t21 = -(sd[2] * _t0 / _t5);
        float _t23 = -(_t13 * _t14);
        float _t26 = ((_t12) * (_t11) + (_t23)) - ((_t13) * (_t14) + (_t23));
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t11 * _t2 / _t26;
        float _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = ((_t21) * (_t29) - (_t13 * _t1 * _t26_inv));
        dd[3] = ((_t31) * (_t21) + (_t12 * _t1 / _t26));
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t2 = unitScale(sd[0], sd[2], sd[0]);
        float _t9 = otherData[3] * _t0;
        float _t10 = otherData[0] * _t1;
        float _t11 = otherData[2] * _t1;
        float _t12 = otherData[1] * _t0;
        float _t13 = sd[0] * _t2;
        float _t18 = _t2 / _t13;
        float _t21 = -(_t11 * _t12);
        float _t23 = -(sd[2] * _t2 / _t13);
        float _t26 = java.lang.Math.fma(_t10, _t9, _t21) - java.lang.Math.fma(_t11, _t12, _t21);
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t9 * _t1 / _t26;
        float _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = java.lang.Math.fma(_t23, _t29, -(_t11 * _t0 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t23, _t10 * _t0 / _t26);
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t2 = unitScale(sd[0], sd[2], sd[0]);
        float _t9 = otherData[3] * _t0;
        float _t10 = otherData[0] * _t1;
        float _t11 = otherData[2] * _t1;
        float _t12 = otherData[1] * _t0;
        float _t13 = sd[0] * _t2;
        float _t18 = _t2 / _t13;
        float _t21 = -(_t11 * _t12);
        float _t23 = -(sd[2] * _t2 / _t13);
        float _t26 = ((_t10) * (_t9) + (_t21)) - ((_t11) * (_t12) + (_t21));
        float _t26_inv = 1.0f / _t26;
        float _t29 = _t9 * _t1 / _t26;
        float _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = ((_t23) * (_t29) - (_t11 * _t0 * _t26_inv));
        dd[3] = ((_t31) * (_t23) + (_t10 * _t0 / _t26));
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_translation(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0f;
        dd[2] = -(otherData[2] * _t0 / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_identity_affine(Float2x2R other, @Mutated Float2x2 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t2 = otherData[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0f;
        dd[2] = -(otherData[2] * _t0 / _t2);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_identity(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0 / _t0);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t1 = unitScale(1.0f, sd[2], 1.0f);
        float _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t1), _t5, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t1 = unitScale(1.0f, sd[2], 1.0f);
        float _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] * _t1 / _t1)) * (_t5) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t4 = otherData[0] * _t2;
        float _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t0), _t6, -(otherData[2] * _t2 / _t4));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t4 = otherData[0] * _t2;
        float _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] * _t0 / _t0)) * (_t6) - (otherData[2] * _t2 / _t4));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_identity(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2 = sd[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0f;
        dd[2] = -(sd[2] * _t0 / _t2);
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t1 = unitScale(1.0f, sd[2], 1.0f);
        float _t4 = sd[0] * _t1;
        float _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t1 = unitScale(1.0f, sd[2], 1.0f);
        float _t4 = sd[0] * _t1;
        float _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] * _t1 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t3 = sd[0] * _t0;
        float _t5 = otherData[0] * _t1;
        float _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t3), _t7, -(otherData[2] * _t1 / _t5));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_orthogonal_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t3 = sd[0] * _t0;
        float _t5 = otherData[0] * _t1;
        float _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] * _t0 / _t3)) * (_t7) - (otherData[2] * _t1 / _t5));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t2 = unitScale(sd[0], sd[2], sd[0]);
        float _t4 = sd[0] * _t2;
        float _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t2 / _t4);
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t2 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t4 = otherData[0] * _t0;
        float _t5 = sd[0] * _t1;
        float _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t5), _t7, -(otherData[2] * _t0 / _t4));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t4 = otherData[0] * _t0;
        float _t5 = sd[0] * _t1;
        float _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0f;
        dd[2] = ((-(sd[2] * _t1 / _t5)) * (_t7) - (otherData[2] * _t0 / _t4));
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_identity_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        float _t14_inv = 1.0f / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_identity_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        float _t14_inv = 1.0f / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t10 = sd[1] * _t2;
        float _t11 = sd[0] * _t3;
        float _t12 = sd[3] * _t2;
        float _t13 = sd[2] * _t3;
        float _t15 = _t0 / _t0;
        float _t18 = -(otherData[2] * _t0 / _t0);
        float _t23 = -(_t13 * _t10);
        float _t26 = java.lang.Math.fma(_t11, _t12, _t23) - java.lang.Math.fma(_t13, _t10, _t23);
        float _t26_inv = 1.0f / _t26;
        float _t31 = _t12 * _t3 / _t26;
        float _t32 = _t11 * _t2 / _t26;
        float _t33 = -(_t10 * _t3 * _t26_inv);
        float _t34 = _t31 * _t15;
        float _t35 = _t18 * _t32;
        dd[0] = java.lang.Math.fma(_t33, _t18, _t34) + java.lang.Math.fma(_t31, _t15, -_t34);
        dd[1] = _t33;
        dd[2] = java.lang.Math.fma(-(_t13 * _t2 * _t26_inv), _t15, _t35) + java.lang.Math.fma(_t18, _t32, -_t35);
        dd[3] = _t32;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, otherData[2], 1.0f);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t10 = sd[1] * _t2;
        float _t11 = sd[0] * _t3;
        float _t12 = sd[3] * _t2;
        float _t13 = sd[2] * _t3;
        float _t15 = _t0 / _t0;
        float _t18 = -(otherData[2] * _t0 / _t0);
        float _t23 = -(_t13 * _t10);
        float _t26 = ((_t11) * (_t12) + (_t23)) - ((_t13) * (_t10) + (_t23));
        float _t26_inv = 1.0f / _t26;
        float _t31 = _t12 * _t3 / _t26;
        float _t32 = _t11 * _t2 / _t26;
        float _t33 = -(_t10 * _t3 * _t26_inv);
        float _t34 = _t31 * _t15;
        float _t35 = _t18 * _t32;
        dd[0] = ((_t33) * (_t18) + (_t34)) + ((_t31) * (_t15) - (_t34));
        dd[1] = _t33;
        dd[2] = ((_t35) - (_t13 * _t2 * _t26_inv) * (_t15)) + ((_t18) * (_t32) - (_t35));
        dd[3] = _t32;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t9 = sd[1] * _t0;
        float _t10 = sd[0] * _t1;
        float _t11 = sd[3] * _t0;
        float _t12 = sd[2] * _t1;
        float _t14 = otherData[0] * _t2;
        float _t19 = _t2 / _t14;
        float _t22 = -(_t12 * _t9);
        float _t24 = -(otherData[2] * _t2 / _t14);
        float _t27 = java.lang.Math.fma(_t10, _t11, _t22) - java.lang.Math.fma(_t12, _t9, _t22);
        float _t27_inv = 1.0f / _t27;
        float _t32 = _t11 * _t1 / _t27;
        float _t33 = _t10 * _t0 / _t27;
        float _t34 = -(_t9 * _t1 * _t27_inv);
        float _t35 = _t32 * _t19;
        float _t36 = _t24 * _t33;
        dd[0] = java.lang.Math.fma(_t34, _t24, _t35) + java.lang.Math.fma(_t32, _t19, -_t35);
        dd[1] = _t34;
        dd[2] = java.lang.Math.fma(-(_t12 * _t0 * _t27_inv), _t19, _t36) + java.lang.Math.fma(_t24, _t33, -_t36);
        dd[3] = _t33;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t9 = sd[1] * _t0;
        float _t10 = sd[0] * _t1;
        float _t11 = sd[3] * _t0;
        float _t12 = sd[2] * _t1;
        float _t14 = otherData[0] * _t2;
        float _t19 = _t2 / _t14;
        float _t22 = -(_t12 * _t9);
        float _t24 = -(otherData[2] * _t2 / _t14);
        float _t27 = ((_t10) * (_t11) + (_t22)) - ((_t12) * (_t9) + (_t22));
        float _t27_inv = 1.0f / _t27;
        float _t32 = _t11 * _t1 / _t27;
        float _t33 = _t10 * _t0 / _t27;
        float _t34 = -(_t9 * _t1 * _t27_inv);
        float _t35 = _t32 * _t19;
        float _t36 = _t24 * _t33;
        dd[0] = ((_t34) * (_t24) + (_t35)) + ((_t32) * (_t19) - (_t35));
        dd[1] = _t34;
        dd[2] = ((_t36) - (_t12 * _t0 * _t27_inv) * (_t19)) + ((_t24) * (_t33) - (_t36));
        dd[3] = _t33;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _t0 = unitScale(sd[0], sd[2], sd[0]);
            float _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0f;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0f;
            ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_fma(other, dest);
        return invertProduct_degenerate_affine_affine_fma(other, dest);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_se38c19b_1_mulAdd(Float2x2R other, Float2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _t0 = unitScale(sd[0], sd[2], sd[0]);
            float _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0f;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0f;
            ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _t0 = unitScale(1.0f, otherData[2], 1.0f);
            float _t2 = unitScale(sd[0], sd[2], sd[0]);
            float _t4 = sd[0] * _t2;
            float _t6 = _t0 / _t0;
            dd[0] = _t6 * (_t2 / _t4);
            dd[1] = 0.0f;
            dd[2] = ((-(sd[2] * _t2 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
            dd[3] = 1.0f;
            ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
            return dest;
        }
        return invertProduct_degenerate_affine_affine_mulAdd(other, dest);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_fma(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t36_inv = 1.0f / _t36;
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_s6f691169_1_fma(dest, ((Float2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x2 invertProduct_degenerate_mulAdd(float m00, float m01, float m10, float m11, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t36_inv = 1.0f / _t36;
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_s6f691169_1_mulAdd(dest, ((Float2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_s6f691169_1_fma(Float2x2 dest, float[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60) {
        float _t61 = _t55 * _t53;
        dd[0] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dd[1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dd[2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dd[3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x2 invertProduct_degenerate_s6f691169_1_mulAdd(Float2x2 dest, float[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60) {
        float _t61 = _t55 * _t53;
        dd[0] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dd[1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dd[2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dd[3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t36_inv = 1.0f / _t36;
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_s10071870_1_fma(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(float m00, float m01, float m10, float m11, @Mutated Double2x2 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t12 = m01 * _t0;
        float _t13 = m00 * _t0;
        float _t14 = m11 * _t1;
        float _t15 = m10 * _t1;
        float _t16 = sd[1] * _t2;
        float _t17 = sd[0] * _t3;
        float _t18 = sd[3] * _t2;
        float _t19 = sd[2] * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t36_inv = 1.0f / _t36;
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_s10071870_1_mulAdd(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_degenerate_s10071870_1_fma(Double2x2 dest, double[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60) {
        float _t61 = _t55 * _t53;
        dd[0] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dd[1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dd[2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dd[3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_degenerate_s10071870_1_mulAdd(Double2x2 dest, double[] dd, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60) {
        float _t61 = _t55 * _t53;
        dd[0] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dd[1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dd[2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dd[3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_self_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_affine_self_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 1.1754944E-38f && java.lang.Math.abs(_t0) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t0_inv = 1.0f / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_general_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_fma(dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] * _t5_inv;
        float _buf1 = -(sd[2] * _t5_inv);
        dd[2] = -(sd[1] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float2x2 normal_general_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t1 = -(sd[2] * sd[1]);
        float _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = sd[3] * _t5_inv;
        float _buf1 = -(sd[2] * _t5_inv);
        dd[2] = -(sd[1] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
                float[] dd = ((Float2x2Impl) dest).data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x2Impl) dest).data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
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
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0f;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return normal(Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0f;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t1 = -(sd[2] * sd[1]);
            float _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_fma(dest);
            float _t5_inv = 1.0f / _t5;
            dd[0] = sd[3] * _t5_inv;
            dd[1] = -(sd[2] * _t5_inv);
            dd[2] = -(sd[1] * _t5_inv);
            dd[3] = sd[0] * _t5_inv;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t1 = -(sd[2] * sd[1]);
            float _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
            if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return normal_degenerate_mulAdd(dest);
            float _t5_inv = 1.0f / _t5;
            dd[0] = sd[3] * _t5_inv;
            dd[1] = -(sd[2] * _t5_inv);
            dd[2] = -(sd[1] * _t5_inv);
            dd[3] = sd[0] * _t5_inv;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_translation(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t1_inv = 1.0f / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = -(sd[2] * _t0) * _t1_inv;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_orthogonal(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(1.0f, sd[2], 1.0f);
        float _t2_inv = 1.0f / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_affine(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[0], sd[2], sd[0]);
        float _t2_inv = 1.0f / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_general_fma(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x2 normal_degenerate_general_mulAdd(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_mulAdd(@Mutated Double2x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t6 = sd[3] * _t0;
        float _t7 = sd[0] * _t1;
        float _t8 = sd[2] * _t1;
        float _t9 = sd[1] * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public float trace() {
        float[] sd = this.data;
        return sd[0] + sd[3];
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_translation(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = sd[2];
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_affine(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = 0.0f;
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_general(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0];
        float _buf0 = sd[2];
        dd[2] = sd[1];
        dd[3] = sd[3];
        dd[1] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Float2x2 transpose_general_self(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = sd[2];
        dd[2] = sd[1];
        dd[1] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return transpose_translation(dest);
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general_self(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        float[] dd = this.data;
        dd[1] = this.data[2];
        dd[2] = 0.0f;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = sd[1];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Float2x2 add(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 4; _i += _lanes) {
            FloatVector.fromArray(_sp, otherData, _i).add(FloatVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 4; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_translation_fma(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_mulAdd(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_self(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_affine_fma(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = 0.0f;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_mulAdd(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = 0.0f;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_self(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_fma(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_mulAdd(float scalar, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        ((Float2x2Impl) dest).properties = 0;
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
    public Float2x2 mul(float scalar, @Mutated Float2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(scalar, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x2Impl) dest).data;
                dd[0] = scalar;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = scalar;
                ((Float2x2Impl) dest).properties = 0;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(scalar, dest);
            return mul_affine_fma(scalar, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(scalar, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x2Impl) dest).data;
                dd[0] = scalar;
                dd[1] = 0.0f;
                dd[2] = 0.0f;
                dd[3] = scalar;
                ((Float2x2Impl) dest).properties = 0;
                return dest;
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
                float[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_self(scalar, this);
            return mul_affine_self(scalar, this);
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_self(scalar, this);
            return mul_affine_self(scalar, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Float2x2 negate(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Float2x2 sub(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 4; _i += _lanes) {
            FloatVector.fromArray(_sp, sd, _i).sub(FloatVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 4; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Float2x2 set(Float2x2R v) {
        float[] dd = this.data;
        float[] vData = ((Float2x2Impl) v).data;
        FloatVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
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
        float[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
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
        float[] dd = this.data;
        float[] mData = ((Float2x3Impl) m).data;
        FloatVector.fromArray(COL_SPECIES, mData, 0).intoArray(dd, 0);
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
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[3];
        dd[3] = mData[4];
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = this.properties;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_translation(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = sd[2];
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_affine(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0f;
        dd[2] = sd[2];
        dd[3] = 1.0f;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Float2x3 to2x3_general(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return to2x3_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            System.arraycopy(DATA_1, 0, dd, 0, 6);
            ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to2x3_translation(dest);
        return to2x3_affine(dest);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Float3x3 to3x3_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sd[2];
        VEC_3.intoArray(dd, 4);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Float3x3 to3x3_affine(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sd[2];
        VEC_3.intoArray(dd, 4);
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Float3x3 to3x3_general(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        VEC_4.intoArray(dd, 5);
        ((Float3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return to3x3_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float3x3Impl) dest).data;
            System.arraycopy(DATA_2, 0, dd, 0, 9);
            ((Float3x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to3x3_translation(dest);
        return to3x3_affine(dest);
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
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 1.0f;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] lowerData = ((Float2x2Impl) lower).data;
        float[] diagonalData = ((Float2x2Impl) diagonal).data;
        float[] upperData = ((Float2x2Impl) upper).data;
        float _rcp0 = 1.0f / sd[0];
        float _sp0 = sd[1] * _rcp0;
        lowerData[1] = _sp0;
        diagonalData[0] = sd[0];
        lowerData[0] = 1.0f;
        diagonalData[1] = 0.0f;
        diagonalData[3] = java.lang.Math.fma(-sd[2], _sp0, sd[3]);
        lowerData[3] = 1.0f;
        upperData[0] = 1.0f;
        upperData[1] = 0.0f;
        upperData[2] = sd[2] * _rcp0;
        lowerData[2] = 0.0f;
        diagonalData[2] = 0.0f;
        upperData[3] = 1.0f;
        ((Float2x2Impl) lower).properties = 0;
        ((Float2x2Impl) diagonal).properties = 0;
        ((Float2x2Impl) upper).properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /** {@code decomposeLDU} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 decomposeLDU_mulAdd(@Mutated Float2x2 lower, @Mutated Float2x2 diagonal, @Mutated Float2x2 upper) {
        float[] sd = this.data;
        float[] lowerData = ((Float2x2Impl) lower).data;
        float[] diagonalData = ((Float2x2Impl) diagonal).data;
        float[] upperData = ((Float2x2Impl) upper).data;
        float _rcp0 = 1.0f / sd[0];
        float _sp0 = sd[1] * _rcp0;
        lowerData[1] = _sp0;
        diagonalData[0] = sd[0];
        lowerData[0] = 1.0f;
        diagonalData[1] = 0.0f;
        diagonalData[3] = ((sd[3]) - (sd[2]) * (_sp0));
        lowerData[3] = 1.0f;
        upperData[0] = 1.0f;
        upperData[1] = 0.0f;
        upperData[2] = sd[2] * _rcp0;
        lowerData[2] = 0.0f;
        diagonalData[2] = 0.0f;
        upperData[3] = 1.0f;
        ((Float2x2Impl) lower).properties = 0;
        ((Float2x2Impl) diagonal).properties = 0;
        ((Float2x2Impl) upper).properties = Joml.BIT_TRANSLATION;
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
        float[] sd = this.data;
        double[] lowerData = ((Double2x2Impl) lower).data;
        double[] diagonalData = ((Double2x2Impl) diagonal).data;
        double[] upperData = ((Double2x2Impl) upper).data;
        float _rcp0 = 1.0f / sd[0];
        float _sp0 = sd[1] * _rcp0;
        lowerData[0] = 1.0f;
        lowerData[1] = _sp0;
        lowerData[2] = 0.0f;
        lowerData[3] = 1.0f;
        diagonalData[0] = sd[0];
        diagonalData[1] = 0.0f;
        diagonalData[2] = 0.0f;
        diagonalData[3] = java.lang.Math.fma(-sd[2], _sp0, sd[3]);
        upperData[0] = 1.0f;
        upperData[1] = 0.0f;
        upperData[2] = sd[2] * _rcp0;
        upperData[3] = 1.0f;
        ((Double2x2Impl) lower).properties = 0;
        ((Double2x2Impl) diagonal).properties = 0;
        ((Double2x2Impl) upper).properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /** {@code decomposeLDU} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x2 decomposeLDU_mulAdd(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        float[] sd = this.data;
        double[] lowerData = ((Double2x2Impl) lower).data;
        double[] diagonalData = ((Double2x2Impl) diagonal).data;
        double[] upperData = ((Double2x2Impl) upper).data;
        float _rcp0 = 1.0f / sd[0];
        float _sp0 = sd[1] * _rcp0;
        lowerData[0] = 1.0f;
        lowerData[1] = _sp0;
        lowerData[2] = 0.0f;
        lowerData[3] = 1.0f;
        diagonalData[0] = sd[0];
        diagonalData[1] = 0.0f;
        diagonalData[2] = 0.0f;
        diagonalData[3] = ((sd[3]) - (sd[2]) * (_sp0));
        upperData[0] = 1.0f;
        upperData[1] = 0.0f;
        upperData[2] = sd[2] * _rcp0;
        upperData[3] = 1.0f;
        ((Double2x2Impl) lower).properties = 0;
        ((Double2x2Impl) diagonal).properties = 0;
        ((Double2x2Impl) upper).properties = Joml.BIT_TRANSLATION;
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
        float[] dd = this.data;
        FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherData[0] - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherData[1] - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherData[2] - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherData[3] - sd[3], sd[3]);
            ((Float2x2Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x2Impl) other).properties) >> 1);
            return dest;
        } else {
            float[] sd = this.data;
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = ((t) * (otherData[0] - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherData[1] - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherData[2] - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherData[3] - sd[3]) + (sd[3]));
            ((Float2x2Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x2Impl) other).properties) >> 1);
            return dest;
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
            dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
            dd[2] = ((t) * (m01 - sd[2]) + (sd[2]));
            dd[3] = ((t) * (m11 - sd[3]) + (sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
            dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
            dd[2] = ((t) * (m01 - sd[2]) + (sd[2]));
            dd[3] = ((t) * (m11 - sd[3]) + (sd[3]));
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
            dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
            dd[2] = ((t) * (m01 - sd[2]) + (sd[2]));
            dd[3] = ((t) * (m11 - sd[3]) + (sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        float _buf1 = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[0] = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        float _buf1 = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[0] = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_translation_fma(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[1], sd[2], rightData[0]);
        dd[2] = java.lang.Math.fma(rightData[3], sd[2], rightData[2]);
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_mulAdd(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = ((rightData[1]) * (sd[2]) + (rightData[0]));
        dd[2] = ((rightData[3]) * (sd[2]) + (rightData[2]));
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2x2 mul_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        dd[2] = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_translation(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = rightData[2] + sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_translation_affine(Float2x2R right, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = 0.0f;
        dd[2] = rightData[2] + sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_translation_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[3] = 1.0f;
        dd[0] = sd[0];
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = rightData[0] * sd[0];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_affine_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = rightData[0] * sd[0];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_translation_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = sd[0];
        dd[1] = sd[1];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_translation_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = ((rightData[2]) * (sd[1]) + (sd[3]));
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = sd[0];
        dd[1] = sd[1];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_affine_fma(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], sd[3]);
        dd[1] = rightData[0] * sd[1];
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = rightData[0] * sd[0];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x2 mul_general_affine_mulAdd(Float2x2R right, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = ((rightData[2]) * (sd[1]) + (sd[3]));
        dd[1] = rightData[0] * sd[1];
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = rightData[0] * sd[0];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] sd = this.data;
            float[] rightData = ((Float2x2Impl) right).data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[1] = 0.0f;
            dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
            dd[3] = 1.0f;
            dd[0] = sd[0];
            ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) right).properties;
            return dest;
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
            float[] sd = this.data;
            float[] rightData = ((Float2x2Impl) right).data;
            float[] dd = this.data;
            dd[1] = 0.0f;
            dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
            dd[3] = 1.0f;
            dd[0] = sd[0];
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
        float[] rightData = ((Float2x2Impl) right).data;
        float m00 = rightData[0];
        float m01 = rightData[2];
        float m10 = rightData[1];
        float m11 = rightData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
            dd[1] = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
            dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
            dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((m00) * (sd[0]) + (m10 * sd[2]));
            dd[1] = ((m00) * (sd[1]) + (m10 * sd[3]));
            dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
            dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _buf0 = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
            float _buf1 = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
            dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
            dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
            dd[0] = _buf0;
            dd[1] = _buf1;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _buf0 = ((m00) * (sd[0]) + (m10 * sd[2]));
            float _buf1 = ((m00) * (sd[1]) + (m10 * sd[3]));
            dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
            dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
            dd[0] = _buf0;
            dd[1] = _buf1;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
            dd[1] = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
            dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
            dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((m00) * (sd[0]) + (m10 * sd[2]));
            dd[1] = ((m00) * (sd[1]) + (m10 * sd[3]));
            dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
            dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[0] = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = ((otherData[1]) * (sd[0]) + (otherData[3] * sd[1]));
        float _buf1 = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[0] = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3] * sd[3]));
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3]);
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3]));
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3]);
        dd[1] = otherData[1] * sd[0];
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0] * sd[0];
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3]));
        dd[1] = otherData[1] * sd[0];
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0] * sd[0];
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_translation(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = otherData[2] + sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_affine_fma(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[3] = 1.0f;
        dd[0] = otherData[0];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_translation_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[3] = 1.0f;
        dd[0] = otherData[0];
        ((Float2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_translation(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0f;
        dd[2] = otherData[2] + sd[2];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0] * sd[0];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_affine_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[1] = 0.0f;
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0] * sd[0];
        dd[3] = 1.0f;
        ((Float2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_translation_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[2], sd[1], sd[0]);
        dd[2] = java.lang.Math.fma(otherData[2], sd[3], sd[2]);
        dd[1] = sd[1];
        dd[3] = sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_translation_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = ((otherData[2]) * (sd[1]) + (sd[0]));
        dd[2] = ((otherData[2]) * (sd[3]) + (sd[2]));
        dd[1] = sd[1];
        dd[3] = sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_affine_fma(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x2 preMul_general_affine_mulAdd(Float2x2R other, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation_affine_fma(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation_affine_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_fma(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, dest);
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation_affine_mulAdd(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation_affine_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, dest);
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation_affine_fma(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation_affine_fma(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_fma(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, this);
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
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation_affine_mulAdd(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation_affine_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_mulAdd(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, this);
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
            dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
            dd[2] = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
            dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((m00) * (sd[0]) + (m01 * sd[1]));
            dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
            dd[2] = ((m00) * (sd[2]) + (m01 * sd[3]));
            dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _buf0 = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
            dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
            float _buf1 = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
            dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
            dd[0] = _buf0;
            dd[2] = _buf1;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            float _buf0 = ((m00) * (sd[0]) + (m01 * sd[1]));
            dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
            float _buf1 = ((m00) * (sd[2]) + (m01 * sd[3]));
            dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
            dd[0] = _buf0;
            dd[2] = _buf1;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
            dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
            dd[2] = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
            dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((m00) * (sd[0]) + (m01 * sd[1]));
            dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
            dd[2] = ((m00) * (sd[2]) + (m01 * sd[3]));
            dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherData[0], sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherData[1], sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherData[2], sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherData[3], sd[3]);
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = ((weight) * (otherData[0]) + (sd[0]));
            dd[1] = ((weight) * (otherData[1]) + (sd[1]));
            dd[2] = ((weight) * (otherData[2]) + (sd[2]));
            dd[3] = ((weight) * (otherData[3]) + (sd[3]));
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
        float[] otherData = ((Float2x2Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m10 = otherData[1];
        float m11 = otherData[3];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
        float[] colData = ((Float2Impl) col).data;
        float[] rowData = ((Float2Impl) row).data;
        float colX = colData[0];
        float colY = colData[1];
        float rowX = rowData[0];
        float rowY = rowData[1];
        float[] dd = this.data;
        dd[0] = colX * rowX;
        dd[1] = colY * rowX;
        dd[2] = colX * rowY;
        dd[3] = colY * rowY;
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
        float[] dd = this.data;
        dd[0] = colX * rowX;
        dd[1] = colY * rowX;
        dd[2] = colX * rowY;
        dd[3] = colY * rowY;
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
        float[] dd = this.data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
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
        float[] vData = ((Float2Impl) v).data;
        float vY = vData[1];
        float[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
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
        float[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
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
        float[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        this.properties = 0;
        return this;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_translation_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[3] = java.lang.Math.fma(sd[2], _t1, _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -_t1);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_translation_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[3] = ((sd[2]) * (_t1) + (_t0));
        dd[2] = ((sd[2]) * (_t0) - (_t1));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_affine_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[1] = sd[0] * _t1;
        dd[0] = sd[0] * _t0;
        dd[3] = java.lang.Math.fma(sd[2], _t1, _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -_t1);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_affine_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[1] = sd[0] * _t1;
        dd[0] = sd[0] * _t0;
        dd[3] = ((sd[2]) * (_t1) + (_t0));
        dd[2] = ((sd[2]) * (_t0) - (_t1));
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_general_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        float _buf1 = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        dd[0] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x2 preRotate_general_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        float _buf1 = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
            dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
            dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
            dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = ((sd[0]) * (_t0) - (sd[1] * _t1));
            dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
            dd[2] = ((sd[2]) * (_t0) - (sd[3] * _t1));
            dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = sd[2] * vX;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0f;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(vX, vY, this);
        return preScale_affine_self(vX, vY, this);
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_translation_self(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = vX;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_affine_self(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_general(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = sd[2] * vX;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0f;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(vX, vY, this);
        return preScale_affine_self(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_translation_self(float s, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = s;
        dd[2] = s * sd[2];
        dd[3] = s;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_affine_self(float s, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[2] = s * sd[2];
        dd[3] = s;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x2 preScale_general(float s, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = s;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(s, this);
        return preScale_affine_self(s, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_translation_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        dd[0] = java.lang.Math.fma(sd[2], _t0, _t1);
        dd[1] = _t0;
        dd[2] = java.lang.Math.fma(sd[2], _t1, -_t0);
        dd[3] = _t1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_translation_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        dd[0] = ((sd[2]) * (_t0) + (_t1));
        dd[1] = _t0;
        dd[2] = ((sd[2]) * (_t1) - (_t0));
        dd[3] = _t1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_affine_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        dd[1] = _t1;
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = _t0;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_affine_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        dd[1] = _t1;
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = _t0;
        dd[0] = _buf0;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_general_fma(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        float _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x2 rotate_general_mulAdd(float angle, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        float _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
            float[] dd = ((Float2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
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
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
            dd[1] = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
            dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
            dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = ((sd[0]) * (_t0) + (sd[2] * _t1));
            dd[1] = ((sd[1]) * (_t0) + (sd[3] * _t1));
            dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
            dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
            ((Double2x2Impl) dest).properties = 0;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = sd[2] * vY;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0f;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return scale_translation_self(vX, vY, this);
        return scale_affine_self(vX, vY, this);
    }

    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float2x2 scale_translation_self(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = vX;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float2x2 scale_affine_self(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float2x2 scale_general(float vX, float vY, @Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0f;
            dd[2] = sd[2] * vY;
            dd[3] = vY;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0f;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return scale_translation_self(vX, vY, this);
        return scale_affine_self(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = s;
            ((Float2x2Impl) dest).properties = 0;
            return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
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
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(s, this);
        return preScale_affine_self(s, this);
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = java.lang.Math.fma(this.data[2], vY, vX);
                dd[1] = vY;
                return dest;
            }
            return mul_affine_fma(vX, vY, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = ((this.data[2]) * (vY) + (vX));
                dd[1] = vY;
                return dest;
            }
            return mul_affine_mulAdd(vX, vY, dest);
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[3] * vY));
            return dest;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preScale}; reached only through them.
     */
    private Float2 mul_affine_fma(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_affine_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_fma(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
        dd[1] = ((sd[1]) * (vX) + (sd[3] * vY));
        return dest;
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
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = java.lang.Math.fma(this.data[2], vY, vX);
                dd[1] = vY;
                return dest;
            }
            return mul_affine_fma(vX, vY, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = ((this.data[2]) * (vY) + (vX));
                dd[1] = vY;
                return dest;
            }
            return mul_affine_mulAdd(vX, vY, dest);
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
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[3] * vY));
            return dest;
        }
    }

    public float m00() { return data[0]; }
    public float m01() { return data[2]; }
    public float m10() { return data[1]; }
    public float m11() { return data[3]; }

    @Override public String toString() {
        return "Float2x2(\n    " + this.data[0] + ", " + this.data[2] + "\n    " + this.data[1] + ", " + this.data[3] + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float2x2Impl)) return false;
        Float2x2Impl o = (Float2x2Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(Float2x2R other, float epsilon) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadCM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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

    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }

    @Mutated public Float2x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }

    @Mutated public Float2x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadCM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
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

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }

    @Mutated public Float2x2 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src); }

    @Mutated public Float2x2 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }

    private float[] storeRM_distinct(float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[1];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    private float[] storeRM_aliased(float[] dest, int offset) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t1;
        dest[offset + 3] = t3;
        return dest;
    }

    @Mutated public Float2x2 loadRM(float[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }

    private Float2x2 loadRM_distinct(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }

    private Float2x2 loadRM_aliased(float[] src, int offset) {
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[1] = t2;
        d[3] = t3;
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

    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    @Mutated public Float2x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }

    @Mutated public Float2x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[1];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadRM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[2] = (float) src[offset + 1];
        this.data[1] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
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

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }

    @Mutated public Float2x2 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src); }

    @Mutated public Float2x2 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[_p1];
        this.data[3] = src[_p1 + 1];
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

    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    @Mutated public Float2x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }

    @Mutated public Float2x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[_p1];
        this.data[3] = (float) src[_p1 + 1];
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

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }

    @Mutated public Float2x2 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src, stride); }

    @Mutated public Float2x2 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }

    private float[] storeRM_distinct(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    private float[] storeRM_aliased(float[] dest, int offset, int stride) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        int _p1 = offset + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[_p1] = t1;
        dest[_p1 + 1] = t3;
        return dest;
    }

    @Mutated public Float2x2 loadRM(float[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }

    private Float2x2 loadRM_distinct(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[_p1];
        this.data[3] = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }

    private Float2x2 loadRM_aliased(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        float t1 = src[offset + 1];
        float t2 = src[_p1];
        float t3 = src[_p1 + 1];
        float[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[1] = t2;
        d[3] = t3;
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

    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    @Mutated public Float2x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }

    @Mutated public Float2x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    @Mutated public Float2x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = (float) src[offset];
        this.data[2] = (float) src[offset + 1];
        this.data[1] = (float) src[_p1];
        this.data[3] = (float) src[_p1 + 1];
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

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }

    @Mutated public Float2x2 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src, stride); }

    @Mutated public Float2x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.data[2];
        dest[offset + 4] = this.data[3];
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

    public MemorySegment storeCM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }

    public MemorySegment storeCM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.data[2];
        dest[offset + 4] = this.data[3];
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

    public MemorySegment storeCM3x3Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3Double(this, 0L, dest); }

    public MemorySegment storeCM3x3Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Double(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
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

    public MemorySegment storeRM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3(this, 0L, dest); }

    public MemorySegment storeRM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
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

    public MemorySegment storeRM3x3Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3Double(this, 0L, dest); }

    public MemorySegment storeRM3x3Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3Double(this, offset, dest);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[2];
        dest[offset + 5] = this.data[3];
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

    public MemorySegment storeCM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    public MemorySegment storeCM4x4(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.data[2];
        dest[offset + 5] = this.data[3];
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

    public MemorySegment storeCM4x4Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Double(this, 0L, dest); }

    public MemorySegment storeCM4x4Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Double(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[3];
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

    public MemorySegment storeRM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }

    public MemorySegment storeRM4x4(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[3];
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

    public MemorySegment storeRM4x4Double(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Double(this, 0L, dest); }

    public MemorySegment storeRM4x4Double(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Double(this, offset, dest);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_3 = FloatVector.fromArray(COL_SPECIES, new float[] {1.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final FloatVector VEC_4 = FloatVector.fromArray(COL_SPECIES, new float[] {0.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_1 = new float[] {1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
    private static final float[] DATA_2 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

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
