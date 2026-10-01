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
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float2x3} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float2x3Impl implements Float2x3 {

    public float[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float2x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float2x3SegOpsUnsafe()
                        : new Float2x3SegOpsMS();
        static final Float2x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float2x3BbOpsUnsafe()
                        : new Float2x3BbOpsApi();
        static final Float2x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float2x3RawOpsUnsafe()
                        : new Float2x3RawOpsApi();
    }

    public Float2x3Impl() {
        data = new float[6];
        data[0] = 1;
        data[3] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Float2x3Impl(float m00, float m01, float m02, float m10, float m11, float m12) {
        float[] dd = this.data = new float[6];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
        dd[4] = m02;
        dd[5] = m12;
        this.properties = determineProperties();
    }

    public Float2x3Impl(Float2x3R src) {
        Float2x3Impl s = (Float2x3Impl) src;
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
     * This is a pure query: it does not update this matrix's cached property bits.
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.data[0] != 1 || this.data[2] != 0 || this.data[1] != 0 || this.data[3] != 1) return 1;
        if (this.data[4] != 0 || this.data[5] != 0) return 7;
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
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
            case 2: _idxSw0 = 0.0f; _idxSw1 = 0.0f; break;
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
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; break;
            case 2: _idxSw2 = sd[4]; _idxSw3 = sd[5]; break;
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
    private Float2 getColumn_general(int col, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw4;
        float _idxSw5;
        switch (col) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = sd[1]; break;
            case 1: _idxSw4 = sd[2]; _idxSw5 = sd[3]; break;
            case 2: _idxSw4 = sd[4]; _idxSw5 = sd[5]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw4;
        dd[1] = _idxSw5;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getColumn_translation(col, dest);
        return getColumn_general(col, dest);
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
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw6 = sd[0]; _idxSw7 = sd[1]; break;
            case 1: _idxSw6 = sd[2]; _idxSw7 = sd[3]; break;
            case 2: _idxSw6 = sd[4]; _idxSw7 = sd[5]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw6;
        dd[1] = _idxSw7;
        return dest;
    }


    /**
     * Compute the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; for a matrix
     * carrying scale the rotation angle is still recovered as long as the X-axis scale is positive.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; for a matrix
     *        carrying scale the rotation angle is still recovered as long as the X-axis scale is
     *        positive
     */
    public float getRotationAngle() {
        float[] sd = this.data;
        return Math.atan2(sd[1], sd[0]);
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_identity(int row, @Mutated Float3 dest) {
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = 1.0f; _idxSw1 = 0.0f; break;
            case 1: _idxSw0 = 0.0f; _idxSw1 = 1.0f; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_translation(int row, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw2;
        float _idxSw3;
        float _idxSw4;
        switch (row) {
            case 0: _idxSw2 = 1.0f; _idxSw3 = 0.0f; _idxSw4 = sd[4]; break;
            case 1: _idxSw2 = 0.0f; _idxSw3 = 1.0f; _idxSw4 = sd[5]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw2;
        dd[1] = _idxSw3;
        dd[2] = _idxSw4;
        return dest;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_general(int row, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw5;
        float _idxSw6;
        float _idxSw7;
        switch (row) {
            case 0: _idxSw5 = sd[0]; _idxSw6 = sd[2]; _idxSw7 = sd[4]; break;
            case 1: _idxSw5 = sd[1]; _idxSw6 = sd[3]; _idxSw7 = sd[5]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw5;
        dd[1] = _idxSw6;
        dd[2] = _idxSw7;
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
    public Float3 getRow(int row, @Mutated Float3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getRow_identity(row, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getRow_translation(row, dest);
        return getRow_general(row, dest);
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _idxSw8;
        float _idxSw9;
        float _idxSw10;
        switch (row) {
            case 0: _idxSw8 = sd[0]; _idxSw9 = sd[2]; _idxSw10 = sd[4]; break;
            case 1: _idxSw8 = sd[1]; _idxSw9 = sd[3]; _idxSw10 = sd[5]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw8;
        dd[1] = _idxSw9;
        dd[2] = _idxSw10;
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_identity(@Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Float2 getTranslation_general(@Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[4];
        dd[1] = sd[5];
        return dest;
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
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getTranslation_identity(dest);
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[4];
        dd[1] = sd[5];
        return dest;
    }


    /**
     * Compute the determinant of the linear part (the upper-left square block) of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of the linear part (the upper-left square block) of this matrix
     */
    public float determinant() {
        float[] sd = this.data;
        return Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public float frobeniusNorm() {
        float[] sd = this.data;
        return (float) java.lang.Math.sqrt(Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], Math.fma(sd[1], sd[1], Math.fma(sd[4], sd[4], Math.fma(sd[0], sd[0], sd[2] * sd[2]))))));
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Float2x3 invert_translation(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties.
     * Shared by 2 identical private paths of {@code invert}; reached only through it.
     */
    private Float2x3 invert_translation_self(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = Math.fma(-sd[4], sd[3], -(sd[1] * sd[5]));
        dd[5] = Math.fma(sd[4], sd[1], -(sd[3] * sd[5]));
        dd[1] = -sd[1];
        dd[3] = sd[3];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_self(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = Math.fma(-sd[4], sd[3], -(sd[1] * sd[5]));
        dd[5] = Math.fma(sd[4], sd[1], -(sd[3] * sd[5]));
        dd[1] = -sd[1];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_general(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[3] * _t3_inv;
        float _buf1 = -(Math.fma(sd[4], sd[3], -(sd[2] * sd[5])) * _t3_inv);
        dd[2] = -(sd[2] * _t3_inv);
        dd[3] = sd[0] * _t3_inv;
        dd[5] = -(Math.fma(sd[0], sd[5], -(sd[4] * sd[1])) * _t3_inv);
        dd[1] = -(sd[1] * _t3_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 invert(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            System.arraycopy(DATA_0, 0, dd, 0, 6);
            ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal(dest);
        return invert_general(dest);
    }


    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 invert() {
        if (Joml.RETURN_NEW) return invert(Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            System.arraycopy(DATA_0, 0, dd, 0, 6);
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self(this);
        return invert_general(this);
    }


    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invert_degenerate(dest);
        float _t3_inv = 1.0f / _t3;
        dd[0] = sd[3] * _t3_inv;
        dd[1] = -(sd[1] * _t3_inv);
        dd[2] = -(sd[2] * _t3_inv);
        dd[3] = sd[0] * _t3_inv;
        dd[4] = -(Math.fma(sd[4], sd[3], -(sd[2] * sd[5])) * _t3_inv);
        dd[5] = -(Math.fma(sd[0], sd[5], -(sd[4] * sd[1])) * _t3_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN). Shared by the identical private paths of {@code invert} and {@code invertProduct};
     * reached only through them.
     */
    private Float2x3 invert_degenerate_orthogonal_general(@Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t8 = sd[3] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[2] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[4] * _t1;
        float _t13 = sd[5] * _t0;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dd[5] = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate(@Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            System.arraycopy(DATA_0, 0, dd, 0, 6);
            ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        return invert_degenerate_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate(@Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t8 = sd[3] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[2] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[4] * _t1;
        float _t13 = sd[5] * _t0;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dd[5] = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t6 = Math.fma(otherData[2], sd[1], otherData[3] * sd[3]);
        float _t7 = Math.fma(otherData[0], sd[0], otherData[1] * sd[2]);
        float _t8 = Math.fma(otherData[0], sd[1], otherData[1] * sd[3]);
        float _t9 = Math.fma(otherData[2], sd[0], otherData[3] * sd[2]);
        float _t10 = Math.fma(otherData[4], sd[0], Math.fma(otherData[5], sd[2], sd[4]));
        float _t11 = Math.fma(otherData[4], sd[1], Math.fma(otherData[5], sd[3], sd[5]));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t15_inv = 1.0f / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dd[5] = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = Math.fma(otherData[0], otherData[3], -(otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = otherData[3] * _t3_inv;
        float _buf1 = -(Math.fma(otherData[4], otherData[3], -(otherData[2] * otherData[5])) * _t3_inv);
        dd[2] = -(otherData[2] * _t3_inv);
        dd[3] = otherData[0] * _t3_inv;
        dd[5] = -(Math.fma(otherData[0], otherData[5], -(otherData[4] * otherData[1])) * _t3_inv);
        dd[1] = -(otherData[1] * _t3_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t1 = otherData[4] + sd[4];
        float _t2 = otherData[5] + sd[5];
        float _t5 = Math.fma(otherData[0], otherData[3], -(otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t5_inv = 1.0f / _t5;
        float _buf0 = otherData[3] * _t5_inv;
        dd[4] = -(Math.fma(otherData[3], _t1, -(otherData[2] * _t2)) * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = otherData[0] * _t5_inv;
        dd[5] = -(Math.fma(otherData[0], _t2, -(otherData[1] * _t1)) * _t5_inv);
        dd[1] = -(otherData[1] * _t5_inv);
        dd[0] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_identity_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        System.arraycopy(DATA_0, 0, dd, 0, 6);
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_identity_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = -otherData[4];
        dd[5] = -otherData[5];
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_translation_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Float2x3 invertProduct_translation_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = -(otherData[4] + sd[4]);
        dd[5] = -(otherData[5] + sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = Math.fma(-sd[4], sd[3], -(sd[1] * sd[5]));
        dd[5] = Math.fma(sd[4], sd[1], -(sd[3] * sd[5]));
        dd[1] = -sd[1];
        dd[3] = sd[3];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = -sd[1];
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = Math.fma(_t0, sd[5], Math.fma(-sd[4], sd[3], -otherData[4]));
        dd[5] = Math.fma(sd[4], sd[1], Math.fma(-sd[3], sd[5], -otherData[5]));
        dd[1] = _t0;
        dd[3] = sd[3];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t3_inv = 1.0f / _t3;
        float _buf0 = sd[3] * _t3_inv;
        float _buf1 = -(Math.fma(sd[4], sd[3], -(sd[2] * sd[5])) * _t3_inv);
        dd[2] = -(sd[2] * _t3_inv);
        dd[3] = sd[0] * _t3_inv;
        dd[5] = -(Math.fma(sd[0], sd[5], -(sd[4] * sd[1])) * _t3_inv);
        dd[1] = -(sd[1] * _t3_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t5 = Math.fma(otherData[4], sd[0], Math.fma(otherData[5], sd[2], sd[4]));
        float _t6 = Math.fma(otherData[4], sd[1], Math.fma(otherData[5], sd[3], sd[5]));
        float _t7 = Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate(other, dest);
        float _t7_inv = 1.0f / _t7;
        float _buf0 = sd[3] * _t7_inv;
        dd[4] = -(Math.fma(sd[3], _t5, -(sd[2] * _t6)) * _t7_inv);
        dd[2] = -(sd[2] * _t7_inv);
        dd[3] = sd[0] * _t7_inv;
        dd[5] = -(Math.fma(sd[0], _t6, -(sd[1] * _t5)) * _t7_inv);
        dd[1] = -(sd[1] * _t7_inv);
        dd[0] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 invertProduct(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, dest);
            return invertProduct_general(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, dest);
        return invertProduct_general(other, dest, Joml.BIT_AFFINE & q);
    }


    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 invertProduct(Float2x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.float2x3());
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, this);
            return invertProduct_general(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, this);
        return invertProduct_general(other, this, Joml.BIT_AFFINE & q);
    }


    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
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
        return invertProduct(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), dest);
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t6 = Math.fma(m01, sd[1], m11 * sd[3]);
        float _t7 = Math.fma(m00, sd[0], m10 * sd[2]);
        float _t8 = Math.fma(m00, sd[1], m10 * sd[3]);
        float _t9 = Math.fma(m01, sd[0], m11 * sd[2]);
        float _t10 = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4]));
        float _t11 = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5]));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, dest);
        float _t15_inv = 1.0f / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dd[5] = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t6 = Math.fma(m01, sd[1], m11 * sd[3]);
        float _t7 = Math.fma(m00, sd[0], m10 * sd[2]);
        float _t8 = Math.fma(m00, sd[1], m10 * sd[3]);
        float _t9 = Math.fma(m01, sd[0], m11 * sd[2]);
        float _t10 = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4]));
        float _t11 = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5]));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, dest);
        float _t15_inv = 1.0f / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dd[5] = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_general(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t6 = Math.fma(otherData[2], sd[1], otherData[3] * sd[3]);
        float _t7 = Math.fma(otherData[0], sd[1], otherData[1] * sd[3]);
        float _t8 = Math.fma(otherData[0], sd[0], otherData[1] * sd[2]);
        float _t9 = Math.fma(otherData[2], sd[0], otherData[3] * sd[2]);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        return invertProduct_degenerate_general_s1fef5c72_1(dest, _props, ((Float2x3Impl) dest).data, _t18, _t19, _t20, _t21, Math.fma(otherData[4], sd[0], Math.fma(otherData[5], sd[2], sd[4])) * _t13, Math.fma(otherData[4], sd[1], Math.fma(otherData[5], sd[3], sd[5])) * _t12, _t28_inv, _t12 * _t28_inv, _t13 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_1(Float2x3 dest, int _props, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t24, float _t25, float _t28_inv, float _sp1, float _sp0) {
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dd[5] = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_identity(Float2x3R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t8 = otherData[3] * _t0;
        float _t9 = otherData[0] * _t1;
        float _t10 = otherData[2] * _t1;
        float _t11 = otherData[1] * _t0;
        float _t12 = otherData[4] * _t1;
        float _t13 = otherData[5] * _t0;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dd[5] = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t8 = otherData[3] * _t2;
        float _t9 = otherData[0] * _t3;
        float _t10 = otherData[2] * _t3;
        float _t11 = otherData[1] * _t2;
        float _t14 = (otherData[4] + sd[4]) * _t3;
        float _t15 = (otherData[5] + sd[5]) * _t2;
        float _t18_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t2 * _t18_inv;
        float _sp0 = _t3 * _t18_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(Math.fma(_t8, _t14, -(_t10 * _t15)) * _t18_inv);
        dd[5] = -(Math.fma(_t9, _t15, -(_t11 * _t14)) * _t18_inv);
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_translation(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t8 = sd[3] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[2] * _t1;
        float _t11 = sd[1] * _t0;
        float _t16 = Math.fma(otherData[4], sd[0], Math.fma(otherData[5], sd[2], sd[4])) * _t1;
        float _t17 = Math.fma(otherData[4], sd[1], Math.fma(otherData[5], sd[3], sd[5])) * _t0;
        float _t20_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t20_inv;
        float _sp0 = _t1 * _t20_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(Math.fma(_t8, _t16, -(_t10 * _t17)) * _t20_inv);
        dd[5] = -(Math.fma(_t9, _t17, -(_t11 * _t16)) * _t20_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        int q = ((Float2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_degenerate_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_degenerate_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_general(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return invertProduct_degenerate_general(other, dest, Joml.BIT_AFFINE & q);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t6 = Math.fma(m01, sd[1], m11 * sd[3]);
        float _t7 = Math.fma(m00, sd[1], m10 * sd[3]);
        float _t8 = Math.fma(m00, sd[0], m10 * sd[2]);
        float _t9 = Math.fma(m01, sd[0], m11 * sd[2]);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t24 = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4])) * _t13;
        float _t25 = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5])) * _t12;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp1 = _t12 * _t28_inv;
        float _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dd[5] = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t6 = Math.fma(m01, sd[1], m11 * sd[3]);
        float _t7 = Math.fma(m00, sd[1], m10 * sd[3]);
        float _t8 = Math.fma(m00, sd[0], m10 * sd[2]);
        float _t9 = Math.fma(m01, sd[0], m11 * sd[2]);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t24 = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4])) * _t13;
        float _t25 = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5])) * _t12;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp1 = _t12 * _t28_inv;
        float _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dd[5] = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_identity(@Mutated Float3x2 dest) {
        float[] dd = ((Float3x2Impl) dest).data;
        System.arraycopy(DATA_2, 0, dd, 0, 6);
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_translation(@Mutated Float3x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x2Impl) dest).data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = sd[4];
        dd[3] = 0.0f;
        dd[4] = 1.0f;
        dd[5] = sd[5];
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x2 transpose_general(@Mutated Float3x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = sd[4];
        dd[3] = sd[1];
        dd[4] = sd[3];
        dd[5] = sd[5];
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
    public Float3x2 transpose(@Mutated Float3x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transpose_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transpose_translation(dest);
        return transpose_general(dest);
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
        float[] sd = this.data;
        double[] dd = ((Double3x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = sd[4];
        dd[3] = sd[1];
        dd[4] = sd[3];
        dd[5] = sd[5];
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
    public Float2x3 add(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 6; _i += _lanes) {
            FloatVector.fromArray(_sp, otherData, _i).add(FloatVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 6; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
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
    public Double2x3 add(Float2x3R other, @Mutated Double2x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = other.m00() + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        dd[4] = m02 + sd[4];
        dd[5] = m12 + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        dd[4] = m02 + sd[4];
        dd[5] = m12 + sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        dd[4] = m02 + sd[4];
        dd[5] = m12 + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_identity(float scalar, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = scalar;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation(float scalar, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = scalar;
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_self(float scalar, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = scalar;
        dd[3] = scalar;
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general(float scalar, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(scalar, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(scalar, dest);
        return mul_general(scalar, dest);
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
        if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = scalar;
            dd[3] = scalar;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
        return mul_general(scalar, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 negate(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 negate(@Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 sub(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 6; _i += _lanes) {
            FloatVector.fromArray(_sp, sd, _i).sub(FloatVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 6; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
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
    public Double2x3 sub(Float2x3R other, @Mutated Double2x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] - other.m00();
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        dd[4] = sd[4] - m02;
        dd[5] = sd[5] - m12;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        dd[4] = sd[4] - m02;
        dd[5] = sd[5] - m12;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m01;
        dd[3] = sd[3] - m11;
        dd[4] = sd[4] - m02;
        dd[5] = sd[5] - m12;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    @Mutated public Float2x3 set(Float2x3R v) {
        float[] dd = this.data;
        float[] vData = ((Float2x3Impl) v).data;
        FloatVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
        dd[4] = vData[4];
        dd[5] = vData[5];
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
        float[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
        dd[4] = m02;
        dd[5] = m12;
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
        float[] dd = this.data;
        float[] mData = ((Float2x2Impl) m).data;
        FloatVector.fromArray(COL_SPECIES, mData, 0).intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
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
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[3];
        dd[3] = mData[4];
        dd[4] = mData[6];
        dd[5] = mData[7];
        this.properties = determineProperties();
        return this;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
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
        float tX = t.x();
        float tY = t.y();
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return withTranslation_identity(tX, tY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return withTranslation_orthogonal(tX, tY, dest);
        return withTranslation_general(tX, tY, dest);
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
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
        float tX = t.x();
        float tY = t.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = tX;
        dd[5] = tY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    public @Mutated Float2x3 withTranslation(Float2R t) {
        float tX = t.x();
        float tY = t.y();
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        float[] dd = this.data;
        dd[4] = tX;
        dd[5] = tY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code withTranslation}, {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float2x3 withTranslation_identity(float tX, float tY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = tX;
        dd[5] = tY;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float2x3 withTranslation_orthogonal(float tX, float tY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = tX;
        dd[5] = tY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float2x3 withTranslation_general(float tX, float tY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = tX;
        dd[5] = tY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
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
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return withTranslation_identity(tX, tY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return withTranslation_orthogonal(tX, tY, dest);
        return withTranslation_general(tX, tY, dest);
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
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
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        float[] dd = this.data;
        dd[4] = tX;
        dd[5] = tY;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = tX;
        dd[5] = tY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = this.properties;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_identity(@Mutated Float2x2 dest) {
        float[] dd = ((Float2x2Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, DATA_3, 0).intoArray(dd, 0);
        ((Float2x2Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_general(@Mutated Float2x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x2Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code to3x3} and {@code mul}; reached only through them.
     */
    private Float3x3 to3x3_orthogonal_general(@Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Float3x3 to3x3_translation(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        VEC_5.intoArray(dd, 0);
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 to3x3(@Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float3x3Impl) dest).data;
            System.arraycopy(DATA_4, 0, dd, 0, 9);
            ((Float3x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to3x3_translation(dest);
        return to3x3_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity and store the
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
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0f;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float2x3 makeIdentity() {
        float[] dd = this.data;
        System.arraycopy(DATA_0, 0, dd, 0, 6);
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
    public Float2x3 lerp(Float2x3R other, float t, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        ((Float2x3Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x3Impl) other).properties) >> 1);
        return dest;
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
    public Double2x3 lerp(Float2x3R other, float t, @Mutated Double2x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(t, other.m00() - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m01 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m11 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m02 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m12 - sd[5], sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m01 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m11 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m02 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m12 - sd[5], sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m01 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m11 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m02 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m12 - sd[5], sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        float _buf1 = Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        float _buf2 = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[2], sd[4]));
        dd[0] = Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[5] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[3], sd[5]));
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
        dd[4] = rightData[4] + sd[4];
        dd[5] = rightData[5] + sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[0] = Math.fma(rightData[0], sd[3], -(rightData[1] * sd[1]));
        float _buf1 = Math.fma(rightData[2], sd[3], -(rightData[3] * sd[1]));
        float _buf2 = Math.fma(-rightData[5], sd[1], Math.fma(rightData[4], sd[3], sd[4]));
        dd[5] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[3], sd[5]));
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_translation(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = rightData[4] + sd[4];
        dd[5] = rightData[5] + sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_translation(Float2x3R right, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[4], sd[0], Math.fma(rightData[5], sd[2], sd[4]));
        dd[5] = Math.fma(rightData[4], sd[1], Math.fma(rightData[5], sd[3], sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Float2x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation(right, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_AFFINE & q);
        return mul_general(right, dest);
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
        if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        int q = ((Float2x3Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            return mul_translation(right, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, this, Joml.BIT_AFFINE & q);
        return mul_general(right, this);
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
        float m00 = right.m00();
        float m01 = right.m01();
        float m02 = right.m02();
        float m10 = right.m10();
        float m11 = right.m11();
        float m12 = right.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(m00, sd[0], m10 * sd[2]);
        dd[1] = Math.fma(m00, sd[1], m10 * sd[3]);
        dd[2] = Math.fma(m01, sd[0], m11 * sd[2]);
        dd[3] = Math.fma(m01, sd[1], m11 * sd[3]);
        dd[4] = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4]));
        dd[5] = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(m00, sd[0], m10 * sd[2]);
        float _buf1 = Math.fma(m00, sd[1], m10 * sd[3]);
        dd[4] = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4]));
        dd[2] = Math.fma(m01, sd[0], m11 * sd[2]);
        dd[5] = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5]));
        dd[3] = Math.fma(m01, sd[1], m11 * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(m00, sd[0], m10 * sd[2]);
        dd[1] = Math.fma(m00, sd[1], m10 * sd[3]);
        dd[2] = Math.fma(m01, sd[0], m11 * sd[2]);
        dd[3] = Math.fma(m01, sd[1], m11 * sd[3]);
        dd[4] = Math.fma(m02, sd[0], Math.fma(m12, sd[2], sd[4]));
        dd[5] = Math.fma(m02, sd[1], Math.fma(m12, sd[3], sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_identity(Float2x2R right, @Mutated Float2x3 dest) {
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_identity_self(Float2x2R right, @Mutated Float2x3 dest) {
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_self(Float2x2R right, @Mutated Float2x3 dest) {
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[3], -(rightData[1] * sd[1]));
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = Math.fma(rightData[2], sd[3], -(rightData[3] * sd[1]));
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[1] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_self(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[3], -(rightData[1] * sd[1]));
        float _buf0 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = Math.fma(rightData[2], sd[3], -(rightData[3] * sd[1]));
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_self(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 mul(Float2x2R right, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(right, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(right, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal(right, dest);
        return mul_general(right, dest);
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
        if (Joml.RETURN_NEW) return mul(right, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(right, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal_self(right, this);
        return mul_general_self(right, this);
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
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[1] = Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[3] = Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[2], sd[4], Math.fma(rightData[0], sd[0], rightData[1] * sd[2]));
        dd[1] = Math.fma(rightData[2], sd[5], Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        float _buf1 = Math.fma(rightData[5], sd[4], Math.fma(rightData[3], sd[0], rightData[4] * sd[2]));
        dd[4] = Math.fma(rightData[5], sd[5], Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        float _buf2 = Math.fma(rightData[8], sd[4], Math.fma(rightData[6], sd[0], rightData[7] * sd[2]));
        dd[7] = Math.fma(rightData[8], sd[5], Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
        dd[2] = rightData[2];
        dd[5] = rightData[5];
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[2], sd[4], rightData[0]);
        dd[1] = Math.fma(rightData[2], sd[5], rightData[1]);
        dd[3] = Math.fma(rightData[5], sd[4], rightData[3]);
        dd[4] = Math.fma(rightData[5], sd[5], rightData[4]);
        dd[6] = Math.fma(rightData[8], sd[4], rightData[6]);
        dd[7] = Math.fma(rightData[8], sd[5], rightData[7]);
        dd[2] = rightData[2];
        dd[5] = rightData[5];
        dd[8] = rightData[8];
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(rightData[2], sd[4], Math.fma(rightData[0], sd[3], -(rightData[1] * sd[1])));
        dd[1] = Math.fma(rightData[2], sd[5], Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        float _buf1 = Math.fma(rightData[5], sd[4], Math.fma(rightData[3], sd[3], -(rightData[4] * sd[1])));
        dd[4] = Math.fma(rightData[5], sd[5], Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        float _buf2 = Math.fma(rightData[8], sd[4], Math.fma(rightData[6], sd[3], -(rightData[7] * sd[1])));
        dd[7] = Math.fma(rightData[8], sd[5], Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
        dd[2] = rightData[2];
        dd[5] = rightData[5];
        dd[8] = rightData[8];
        dd[0] = _buf0;
        dd[3] = _buf1;
        dd[6] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Float3x3 mul_translation_identity(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        VEC_5.intoArray(dd, 0);
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        VEC_5.intoArray(dd, 0);
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = rightData[6] + sd[4];
        dd[7] = rightData[7] + sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        float _buf0 = Math.fma(rightData[6], sd[0], Math.fma(rightData[7], sd[2], sd[4]));
        dd[7] = Math.fma(rightData[6], sd[1], Math.fma(rightData[7], sd[3], sd[5]));
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Float3x3Impl) right).properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_translation_identity(right, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation(right, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, dest);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_AFFINE & q);
        return mul_general(right, dest);
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
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(rightData[2], sd[4], Math.fma(rightData[0], sd[0], rightData[1] * sd[2]));
        dd[1] = Math.fma(rightData[2], sd[5], Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        dd[2] = rightData[2];
        dd[3] = Math.fma(rightData[5], sd[4], Math.fma(rightData[3], sd[0], rightData[4] * sd[2]));
        dd[4] = Math.fma(rightData[5], sd[5], Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        dd[5] = rightData[5];
        dd[6] = Math.fma(rightData[8], sd[4], Math.fma(rightData[6], sd[0], rightData[7] * sd[2]));
        dd[7] = Math.fma(rightData[8], sd[5], Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
        dd[8] = rightData[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        float _buf2 = Math.fma(otherData[0], sd[4], Math.fma(otherData[2], sd[5], otherData[4]));
        dd[0] = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[5] = Math.fma(otherData[1], sd[4], Math.fma(otherData[3], sd[5], otherData[5]));
        dd[3] = Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[4], Math.fma(otherData[2], sd[5], otherData[4]));
        dd[5] = Math.fma(otherData[1], sd[4], Math.fma(otherData[3], sd[5], otherData[5]));
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[3], otherData[2] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[3], otherData[3] * sd[1]);
        float _buf2 = Math.fma(otherData[0], sd[4], Math.fma(otherData[2], sd[5], otherData[4]));
        dd[2] = Math.fma(otherData[2], sd[3], -(otherData[0] * sd[1]));
        dd[5] = Math.fma(otherData[1], sd[4], Math.fma(otherData[3], sd[5], otherData[5]));
        dd[3] = Math.fma(otherData[3], sd[3], -(otherData[1] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_translation(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        ((Float2x3Impl) dest).properties = _props;
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
    public Float2x3 preMul(Float2x3R other, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Float2x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return preMul_general(other, dest);
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
        if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        int q = ((Float2x3Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
            return preMul_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
        return preMul_general(other, this);
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
        float m00 = other.m00();
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(m00, sd[0], m01 * sd[1]);
        dd[1] = Math.fma(m10, sd[0], m11 * sd[1]);
        dd[2] = Math.fma(m00, sd[2], m01 * sd[3]);
        dd[3] = Math.fma(m10, sd[2], m11 * sd[3]);
        dd[4] = Math.fma(m00, sd[4], Math.fma(m01, sd[5], m02));
        dd[5] = Math.fma(m10, sd[4], Math.fma(m11, sd[5], m12));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(m00, sd[0], m01 * sd[1]);
        dd[1] = Math.fma(m10, sd[0], m11 * sd[1]);
        float _buf1 = Math.fma(m00, sd[2], m01 * sd[3]);
        dd[3] = Math.fma(m10, sd[2], m11 * sd[3]);
        float _buf2 = Math.fma(m00, sd[4], Math.fma(m01, sd[5], m02));
        dd[5] = Math.fma(m10, sd[4], Math.fma(m11, sd[5], m12));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(m00, sd[0], m01 * sd[1]);
        dd[1] = Math.fma(m10, sd[0], m11 * sd[1]);
        dd[2] = Math.fma(m00, sd[2], m01 * sd[3]);
        dd[3] = Math.fma(m10, sd[2], m11 * sd[3]);
        dd[4] = Math.fma(m00, sd[4], Math.fma(m01, sd[5], m02));
        dd[5] = Math.fma(m10, sd[4], Math.fma(m11, sd[5], m12));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_identity(Float2x2R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_identity_self(Float2x2R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        float _buf0 = Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[3], otherData[2] * sd[1]);
        float _buf0 = Math.fma(otherData[1], sd[3], otherData[3] * sd[1]);
        dd[2] = Math.fma(otherData[2], sd[3], -(otherData[0] * sd[1]));
        dd[3] = Math.fma(otherData[3], sd[3], -(otherData[1] * sd[1]));
        float _buf1 = Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[3] = Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        float _buf2 = Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity(other, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation(other, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal(other, dest);
        return preMul_general(other, dest);
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
        if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity_self(other, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation(other, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal(other, this);
        return preMul_general(other, this);
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
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[3] = Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        dd[4] = Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        float _buf2 = Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        dd[6] = Math.fma(otherData[0], sd[4], Math.fma(otherData[3], sd[5], otherData[6]));
        dd[3] = Math.fma(otherData[0], sd[2], otherData[3] * sd[3]);
        dd[7] = Math.fma(otherData[1], sd[4], Math.fma(otherData[4], sd[5], otherData[7]));
        dd[4] = Math.fma(otherData[1], sd[2], otherData[4] * sd[3]);
        dd[8] = Math.fma(otherData[2], sd[4], Math.fma(otherData[5], sd[5], otherData[8]));
        dd[5] = Math.fma(otherData[2], sd[2], otherData[5] * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[6] = Math.fma(otherData[0], sd[4], Math.fma(otherData[3], sd[5], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[4], Math.fma(otherData[4], sd[5], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[4], Math.fma(otherData[5], sd[5], otherData[8]));
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        dd[4] = otherData[4];
        dd[5] = otherData[5];
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = Math.fma(otherData[0], sd[3], otherData[3] * sd[1]);
        float _buf1 = Math.fma(otherData[1], sd[3], otherData[4] * sd[1]);
        float _buf2 = Math.fma(otherData[2], sd[3], otherData[5] * sd[1]);
        dd[6] = Math.fma(otherData[0], sd[4], Math.fma(otherData[3], sd[5], otherData[6]));
        dd[3] = Math.fma(otherData[3], sd[3], -(otherData[0] * sd[1]));
        dd[7] = Math.fma(otherData[1], sd[4], Math.fma(otherData[4], sd[5], otherData[7]));
        dd[4] = Math.fma(otherData[4], sd[3], -(otherData[1] * sd[1]));
        dd[8] = Math.fma(otherData[2], sd[4], Math.fma(otherData[5], sd[5], otherData[8]));
        dd[5] = Math.fma(otherData[5], sd[3], -(otherData[2] * sd[1]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        ((Float3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        VEC_5.intoArray(dd, 0);
        dd[4] = 1.0f;
        dd[5] = 0.0f;
        dd[6] = otherData[6] + sd[4];
        dd[7] = otherData[7] + sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float3x3R other, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        dd[6] = otherData[6] + sd[4];
        dd[7] = otherData[7] + sd[5];
        dd[8] = 1.0f;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Pre-multiply the given matrix onto this matrix, i.e. compute {@code other * this} and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Float3x3 preMul(Float3x3R other, @Mutated Float3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Float3x3Impl) other).properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return preMul_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, dest);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return preMul_general(other, dest);
    }


    /**
     * Pre-multiply the given matrix onto this matrix, i.e. compute {@code other * this} and store
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
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        dd[1] = Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        dd[2] = Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        dd[3] = Math.fma(otherData[0], sd[2], otherData[3] * sd[3]);
        dd[4] = Math.fma(otherData[1], sd[2], otherData[4] * sd[3]);
        dd[5] = Math.fma(otherData[2], sd[2], otherData[5] * sd[3]);
        dd[6] = Math.fma(otherData[0], sd[4], Math.fma(otherData[3], sd[5], otherData[6]));
        dd[7] = Math.fma(otherData[1], sd[4], Math.fma(otherData[4], sd[5], otherData[7]));
        dd[8] = Math.fma(otherData[2], sd[4], Math.fma(otherData[5], sd[5], otherData[8]));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(weight, otherData[0], sd[0]);
        dd[1] = Math.fma(weight, otherData[1], sd[1]);
        dd[2] = Math.fma(weight, otherData[2], sd[2]);
        dd[3] = Math.fma(weight, otherData[3], sd[3]);
        dd[4] = Math.fma(weight, otherData[4], sd[4]);
        dd[5] = Math.fma(weight, otherData[5], sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
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
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(weight, other.m00(), sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m01, sd[2]);
        dd[3] = Math.fma(weight, m11, sd[3]);
        dd[4] = Math.fma(weight, m02, sd[4]);
        dd[5] = Math.fma(weight, m12, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m01, sd[2]);
        dd[3] = Math.fma(weight, m11, sd[3]);
        dd[4] = Math.fma(weight, m02, sd[4]);
        dd[5] = Math.fma(weight, m12, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m01, sd[2]);
        dd[3] = Math.fma(weight, m11, sd[3]);
        dd[4] = Math.fma(weight, m02, sd[4]);
        dd[5] = Math.fma(weight, m12, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] dd = this.data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
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
    public @Mutated Float2x3 makeScaling(Float2R v) {
        float vY = v.y();
        float[] dd = this.data;
        dd[0] = v.x();
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
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
        float[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
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
        float[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
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
    public @Mutated Float2x3 makeTranslation(Float2R v) {
        float vX = v.x();
        float vY = v.y();
        float[] dd = this.data;
        VEC_1.intoArray(dd, 0);
        dd[4] = vX;
        dd[5] = vY;
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
        float[] dd = this.data;
        VEC_1.intoArray(dd, 0);
        dd[4] = vX;
        dd[5] = vY;
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
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return this
     */
    @Mutated public Float2x3 makeView(float left, float right, float bottom, float top) {
        float[] dd = this.data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = -((left + right) * _t0_inv);
        dd[5] = -((bottom + top) * _t1_inv);
        this.properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_orthogonal_general(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        float _buf1 = Math.fma(sd[2], _t1, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t1);
        float _buf2 = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Float2x3 preRotate_identity(float angle, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Float2x3 preRotate_identity_self(float angle, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_translation(float angle, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        float _buf0 = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
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
    public Float2x3 preRotate(float angle, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle, dest);
        return preRotate_orthogonal_general(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle, this);
        return preRotate_orthogonal_general(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = Math.fma(sd[0], _t1, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t1);
        dd[2] = Math.fma(sd[2], _t1, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t1);
        dd[4] = Math.fma(sd[4], _t1, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t1);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY, dest);
        return preRotateAround_general(angle, pivotX, pivotY, dest);
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        dd[2] = Math.fma(sd[2], _t2, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t2);
        dd[4] = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 preRotateAround(float angle, Float2R pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY, this);
        return preRotateAround_general(angle, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float2x3 preRotateAround_identity(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = Math.fma(pivotX, _t5, pivotY * _t0);
        dd[5] = Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        float _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_orthogonal(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        float _buf1 = Math.fma(sd[2], _t2, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t2);
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        float _buf0 = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        float _buf1 = Math.fma(sd[2], _t2, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t2);
        float _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY, dest);
        return preRotateAround_general(angle, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY, this);
        return preRotateAround_general(angle, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = Math.fma(sd[0], _t2, -(sd[1] * _t0));
        dd[1] = Math.fma(sd[0], _t0, sd[1] * _t2);
        dd[2] = Math.fma(sd[2], _t2, -(sd[3] * _t0));
        dd[3] = Math.fma(sd[2], _t0, sd[3] * _t2);
        dd[4] = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[5] = Math.fma(sd[4], _t0, sd[5] * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(vX, vY, dest);
        return preScale_general(vX, vY, dest);
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public @Mutated Float2x3 preScale(Float2R v) {
        float vX = v.x();
        float vY = v.y();
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(vX, vY, this);
        return preScale_general(vX, vY, this);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_identity(float vX, float vY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_translation(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_translation_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = vX;
        dd[3] = vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_general(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 preScale(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(vX, vY, dest);
        return preScale_general(vX, vY, dest);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(vX, vY, this);
        return preScale_general(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_identity(float s, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_translation(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_translation_self(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s;
        dd[3] = s;
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float2x3 preScale_general(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 preScale(float s, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(s, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(s, dest);
        return preScale_general(s, dest);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(s, this);
        return preScale_general(s, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY, dest);
        return preScaleAround_general(s, pivotX, pivotY, dest);
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = Math.fma(s, sd[5], pivotY * _t0);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 preScaleAround(float s, Float2R pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(s, pivotX, pivotY, this);
        return preScaleAround_general(s, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_identity(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = pivotX * _t0;
        dd[5] = pivotY * _t0;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_identity_self(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = pivotX * _t0;
        dd[5] = pivotY * _t0;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY, dest);
        return preScaleAround_general(s, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(s, pivotX, pivotY, this);
        return preScaleAround_general(s, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = Math.fma(s, sd[5], pivotY * _t0);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        return preScaleAround_general(sX, sY, pivotX, pivotY, dest);
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
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
        dd[5] = Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 preScaleAround(Float2R s, Float2R pivot) {
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        return preScaleAround_general(sX, sY, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_identity(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sY;
        dd[4] = pivotX * (1.0f - sX);
        dd[5] = pivotY * (1.0f - sY);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_identity_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = pivotX * (1.0f - sX);
        dd[5] = pivotY * (1.0f - sY);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
        dd[5] = Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        return preScaleAround_general(sX, sY, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        return preScaleAround_general(sX, sY, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
        dd[5] = Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY, dest);
        return preTranslate_general(vX, vY, dest);
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 preTranslate(Float2R v) {
        float vX = v.x();
        float vY = v.y();
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal_self(vX, vY, this);
        return preTranslate_general_self(vX, vY, this);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preTranslate} and {@code translate}; reached only through
     * them.
     */
    private Float2x3 preTranslate_translation(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Float2x3 preTranslate_translation_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float2x3 preTranslate_orthogonal(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Float2x3 preTranslate_orthogonal_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float2x3 preTranslate_general(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Float2x3 preTranslate_general_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY, dest);
        return preTranslate_general(vX, vY, dest);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal_self(vX, vY, this);
        return preTranslate_general_self(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[3] * _t0);
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        dd[3] = Math.fma(sd[3], _t1, -(sd[1] * _t0));
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general_self(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _buf0 = Math.fma(sd[0], _t1, sd[2] * _t0);
        float _buf1 = Math.fma(sd[1], _t1, sd[3] * _t0);
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        dd[3] = Math.fma(sd[3], _t1, -(sd[1] * _t0));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_translation(float angle, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
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
    public Float2x3 rotate(float angle, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle, dest);
        return rotate_orthogonal_general(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_identity_self(angle, this);
        return rotate_orthogonal_general_self(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = Math.fma(sd[0], _t1, sd[2] * _t0);
        dd[1] = Math.fma(sd[1], _t1, sd[3] * _t0);
        dd[2] = Math.fma(sd[2], _t1, -(sd[0] * _t0));
        dd[3] = Math.fma(sd[3], _t1, -(sd[1] * _t0));
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY, dest);
        return rotateAround_general(angle, pivotX, pivotY, dest);
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dd[0] = Math.fma(sd[0], _t2, sd[2] * _t0);
        dd[1] = Math.fma(sd[1], _t2, sd[3] * _t0);
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t0));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t0));
        dd[4] = Math.fma(sd[0], _t9, Math.fma(sd[2], _t10, sd[4]));
        dd[5] = Math.fma(sd[1], _t9, Math.fma(sd[3], _t10, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 rotateAround(float angle, Float2R pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY, this);
        return rotateAround_general(angle, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_translation(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, sd[4]));
        dd[5] = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_orthogonal(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[2] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t0);
        dd[4] = Math.fma(sd[0], _t9, Math.fma(sd[2], _t10, sd[4]));
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t0));
        dd[5] = Math.fma(sd[1], _t9, Math.fma(sd[3], _t10, sd[5]));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t0));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_general(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        float _buf0 = Math.fma(sd[0], _t2, sd[2] * _t0);
        float _buf1 = Math.fma(sd[1], _t2, sd[3] * _t0);
        dd[4] = Math.fma(sd[0], _t9, Math.fma(sd[2], _t10, sd[4]));
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t0));
        dd[5] = Math.fma(sd[1], _t9, Math.fma(sd[3], _t10, sd[5]));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t0));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY, dest);
        return rotateAround_general(angle, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY, this);
        return rotateAround_general(angle, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dd[0] = Math.fma(sd[0], _t2, sd[2] * _t0);
        dd[1] = Math.fma(sd[1], _t2, sd[3] * _t0);
        dd[2] = Math.fma(sd[2], _t2, -(sd[0] * _t0));
        dd[3] = Math.fma(sd[3], _t2, -(sd[1] * _t0));
        dd[4] = Math.fma(sd[0], _t9, Math.fma(sd[2], _t10, sd[4]));
        dd[5] = Math.fma(sd[1], _t9, Math.fma(sd[3], _t10, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(vX, vY, dest);
        return scale_general(vX, vY, dest);
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public @Mutated Float2x3 scale(Float2R v) {
        float vX = v.x();
        float vY = v.y();
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return scale_general_self(vX, vY, this);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float2x3 scale_translation(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = vY;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float2x3 scale_general(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float2x3 scale_general_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 scale(float vX, float vY, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(vX, vY, dest);
        return scale_general(vX, vY, dest);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return scale_general_self(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float2x3 scale_translation(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float2x3 scale_general(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Float2x3 scale_general_self(float s, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2x3 scale(float s, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(s, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(s, dest);
        return scale_general(s, dest);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return scale_general_self(s, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY, dest);
        return scaleAround_orthogonal(s, pivotX, pivotY, dest);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivot.x() * _t0;
        float _t2 = pivot.y() * _t0;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = Math.fma(sd[0], _t1, Math.fma(sd[2], _t2, sd[4]));
        dd[5] = Math.fma(sd[1], _t1, Math.fma(sd[3], _t2, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 scaleAround(float s, Float2R pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(s, pivotX, pivotY, this);
        return scaleAround_orthogonal(s, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = Math.fma(pivotX, _t0, sd[4]);
        dd[5] = Math.fma(pivotY, _t0, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = Math.fma(pivotX, _t0, sd[4]);
        dd[5] = Math.fma(pivotY, _t0, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dd[4] = Math.fma(sd[0], _t1, Math.fma(sd[2], _t2, sd[4]));
        dd[0] = s * sd[0];
        dd[2] = s * sd[2];
        dd[5] = Math.fma(sd[1], _t1, Math.fma(sd[3], _t2, sd[5]));
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY, dest);
        return scaleAround_orthogonal(s, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(s, pivotX, pivotY, this);
        return scaleAround_orthogonal(s, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = Math.fma(sd[0], _t1, Math.fma(sd[2], _t2, sd[4]));
        dd[5] = Math.fma(sd[1], _t1, Math.fma(sd[3], _t2, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY, dest);
        return scaleAround_orthogonal(sX, sY, pivotX, pivotY, dest);
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
        float sX = s.x();
        float sY = s.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t2 = pivot.x() * (1.0f - sX);
        float _t3 = pivot.y() * (1.0f - sY);
        dd[0] = sX * sd[0];
        dd[1] = sX * sd[1];
        dd[2] = sY * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = Math.fma(sd[0], _t2, Math.fma(sd[2], _t3, sd[4]));
        dd[5] = Math.fma(sd[1], _t2, Math.fma(sd[3], _t3, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 scaleAround(Float2R s, Float2R pivot) {
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        return scaleAround_orthogonal(sX, sY, pivotX, pivotY, this);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sY;
        dd[4] = Math.fma(pivotX, 1.0f - sX, sd[4]);
        dd[5] = Math.fma(pivotY, 1.0f - sY, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = Math.fma(pivotX, 1.0f - sX, sd[4]);
        dd[5] = Math.fma(pivotY, 1.0f - sY, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dd[4] = Math.fma(sd[0], _t2, Math.fma(sd[2], _t3, sd[4]));
        dd[0] = sX * sd[0];
        dd[2] = sY * sd[2];
        dd[5] = Math.fma(sd[1], _t2, Math.fma(sd[3], _t3, sd[5]));
        dd[1] = sX * sd[1];
        dd[3] = sY * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY, dest);
        return scaleAround_orthogonal(sX, sY, pivotX, pivotY, dest);
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
        if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        return scaleAround_orthogonal(sX, sY, pivotX, pivotY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dd[0] = sX * sd[0];
        dd[1] = sX * sd[1];
        dd[2] = sY * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = Math.fma(sd[0], _t2, Math.fma(sd[2], _t3, sd[4]));
        dd[5] = Math.fma(sd[1], _t2, Math.fma(sd[3], _t3, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY, dest);
        return translate_general(vX, vY, dest);
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
    public @Mutated Float2x3 translate(Float2R v) {
        float vX = v.x();
        float vY = v.y();
        if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self(vX, vY, this);
        return translate_general_self(vX, vY, this);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_self(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY, dest);
        return translate_general(vX, vY, dest);
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
        if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self(vX, vY, this);
        return translate_general_self(vX, vY, this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[5] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_identity(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = -((left + right) * _t0_inv);
        dd[5] = -((bottom + top) * _t1_inv);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_identity_self(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = -((left + right) * _t0_inv);
        dd[5] = -((bottom + top) * _t1_inv);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_translation(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_self(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_orthogonal(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = Math.fma(-sd[2], _sp3, Math.fma(-sd[0], _sp2, sd[4]));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = Math.fma(-sd[3], _sp3, Math.fma(-sd[1], _sp2, sd[5]));
        dd[1] = _sp0 * sd[1];
        dd[3] = _sp1 * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_general(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = sd[4] + Math.fma(-sd[2], _sp3, -(sd[0] * _sp2));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = sd[5] + Math.fma(-sd[3], _sp3, -(sd[1] * _sp2));
        dd[1] = _sp0 * sd[1];
        dd[3] = _sp1 * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @param dest will hold the result
     * @return dest
     */
    public Float2x3 view(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity(left, right, bottom, top, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation(left, right, bottom, top, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal(left, right, bottom, top, dest);
        return view_general(left, right, bottom, top, dest);
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
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Float2x3 view(float left, float right, float bottom, float top) {
        if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal(left, right, bottom, top, this);
        return view_general(left, right, bottom, top, this);
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
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 view(float left, float right, float bottom, float top, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[0] = _sp0 * sd[0];
        dd[1] = _sp0 * sd[1];
        dd[2] = _sp1 * sd[2];
        dd[3] = _sp1 * sd[3];
        dd[4] = sd[4] + Math.fma(-sd[2], _sp3, -(sd[0] * _sp2));
        dd[5] = sd[5] + Math.fma(-sd[3], _sp3, -(sd[1] * _sp2));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Float2 mul(Float3R v, @Mutated Float2 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(vX, vY, vZ, dest);
        return mul_general(vX, vY, vZ, dest);
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
    public Double2 mul(Float3R v, @Mutated Double2 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[4], vZ, Math.fma(sd[0], vX, sd[2] * vY));
        dd[1] = Math.fma(sd[5], vZ, Math.fma(sd[1], vX, sd[3] * vY));
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_translation(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = Math.fma(sd[4], vZ, vX);
        dd[1] = Math.fma(sd[5], vZ, vY);
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = Math.fma(sd[4], vZ, Math.fma(sd[0], vX, sd[2] * vY));
        dd[1] = Math.fma(sd[5], vZ, Math.fma(sd[1], vX, sd[3] * vY));
        return dest;
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
    public Float2 mul(float vX, float vY, float vZ, @Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(vX, vY, vZ, dest);
        return mul_general(vX, vY, vZ, dest);
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
    public Double2 mul(float vX, float vY, float vZ, @Mutated Double2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[4], vZ, Math.fma(sd[0], vX, sd[2] * vY));
        dd[1] = Math.fma(sd[5], vZ, Math.fma(sd[1], vX, sd[3] * vY));
        return dest;
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformDirection(Float2R v, @Mutated Float2 dest) {
        float vX = v.x();
        float vY = v.y();
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return transformDirection_general(vX, vY, dest);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code transformDirection} and {@code transformPosition};
     * reached only through them.
     */
    private Float2 transformDirection_identity(float vX, float vY, @Mutated Float2 dest) {
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = vX;
        dd[1] = vY;
        return dest;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
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
        return transformDirection_general(vX, vY, dest);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformPosition(Float2R v, @Mutated Float2 dest) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformPosition_translation(vX, vY, dest);
        return transformPosition_general(vX, vY, dest);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
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
        float vX = v.x();
        float vY = v.y();
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[1] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        return dest;
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_translation(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[4] + vX;
        dd[1] = sd[5] + vY;
        return dest;
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[1] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        return dest;
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float2 transformPosition(float vX, float vY, @Mutated Float2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformPosition_translation(vX, vY, dest);
        return transformPosition_general(vX, vY, dest);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, Math.fma(sd[2], vY, sd[4]));
        dd[1] = Math.fma(sd[1], vX, Math.fma(sd[3], vY, sd[5]));
        return dest;
    }

    public float m00() { return data[0]; }
    public float m01() { return data[2]; }
    public float m02() { return data[4]; }
    public float m10() { return data[1]; }
    public float m11() { return data[3]; }
    public float m12() { return data[5]; }

    @Override public String toString() {
        return "Float2x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float2x3Impl)) return false;
        Float2x3Impl o = (Float2x3Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3])
            && Float.isFinite(data[4])
            && Float.isFinite(data[5]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5]);
    }

    @Override public boolean equalsEpsilon(Float2x3R other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.m00()) <= epsilon
            && java.lang.Math.abs(data[2] - other.m01()) <= epsilon
            && java.lang.Math.abs(data[4] - other.m02()) <= epsilon
            && java.lang.Math.abs(data[1] - other.m10()) <= epsilon
            && java.lang.Math.abs(data[3] - other.m11()) <= epsilon
            && java.lang.Math.abs(data[5] - other.m12()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadCM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
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
    public ByteBuffer storeCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Float2x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Float2x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Float2x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Float2x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float2x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Float2x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Float2x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadCM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
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
    public ByteBuffer storeCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Float2x3 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float2x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Float2x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float2x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }
    public MemorySegment storeCMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }
    @Mutated public Float2x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src); }
    public Float2x3 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private float[] storeRM_distinct(float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    private float[] storeRM_aliased(float[] dest, int offset) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t4;
        dest[offset + 3] = t1;
        dest[offset + 4] = t3;
        dest[offset + 5] = t5;
        return dest;
    }
    @Mutated public Float2x3 loadRM(float[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Float2x3 loadRM_distinct(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[4] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[3] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    private Float2x3 loadRM_aliased(float[] src, int offset) {
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float t4 = src[offset + 4];
        float t5 = src[offset + 5];
        float[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[4] = t2;
        d[1] = t3;
        d[3] = t4;
        d[5] = t5;
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
    public ByteBuffer storeRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Float2x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Float2x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float2x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Float2x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float2x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Float2x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Float2x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadRM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[2] = (float) src[offset + 1];
        this.data[4] = (float) src[offset + 2];
        this.data[1] = (float) src[offset + 3];
        this.data[3] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
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
    public ByteBuffer storeRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Float2x3 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float2x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Float2x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float2x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }
    public MemorySegment storeRMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }
    @Mutated public Float2x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src); }
    public Float2x3 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        dest[_p2] = this.data[4];
        dest[_p2 + 1] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[_p1];
        this.data[3] = src[_p1 + 1];
        this.data[4] = src[_p2];
        this.data[5] = src[_p2 + 1];
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
    public Float2x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Float2x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float2x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }
    public Float2x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float2x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Float2x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Float2x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        dest[_p2] = this.data[4];
        dest[_p2 + 1] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[_p1];
        this.data[3] = (float) src[_p1 + 1];
        this.data[4] = (float) src[_p2];
        this.data[5] = (float) src[_p2 + 1];
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
    public Float2x3 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }
    public Float2x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float2x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeCMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }
    @Mutated public Float2x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src, stride); }
    public Float2x3 loadCMDouble(long offset, MemorySegment src, int stride) {
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
        dest[offset + 2] = this.data[4];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        dest[_p1 + 2] = this.data[5];
        return dest;
    }
    private float[] storeRM_aliased(float[] dest, int offset, int stride) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        int _p1 = offset + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t4;
        dest[_p1] = t1;
        dest[_p1 + 1] = t3;
        dest[_p1 + 2] = t5;
        return dest;
    }
    @Mutated public Float2x3 loadRM(float[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Float2x3 loadRM_distinct(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[4] = src[offset + 2];
        this.data[1] = src[_p1];
        this.data[3] = src[_p1 + 1];
        this.data[5] = src[_p1 + 2];
        this.properties = determineProperties();
        return this;
    }
    private Float2x3 loadRM_aliased(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[_p1];
        float t4 = src[_p1 + 1];
        float t5 = src[_p1 + 2];
        float[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[4] = t2;
        d[1] = t3;
        d[3] = t4;
        d[5] = t5;
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
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }
    public Float2x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Float2x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float2x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }
    public Float2x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float2x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Float2x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Float2x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        dest[_p1 + 2] = this.data[5];
        return dest;
    }
    public @Mutated Float2x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = (float) src[offset];
        this.data[2] = (float) src[offset + 1];
        this.data[4] = (float) src[offset + 2];
        this.data[1] = (float) src[_p1];
        this.data[3] = (float) src[_p1 + 1];
        this.data[5] = (float) src[_p1 + 2];
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
    public ByteBuffer storeRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }
    public Float2x3 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float2x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }
    public Float2x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float2x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeRMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }
    @Mutated public Float2x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src, stride); }
    public Float2x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.data[2];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = this.data[4];
        dest[offset + 7] = this.data[5];
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
    public ByteBuffer storeCM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3Relative(ByteBuffer buf) {
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
    public MemorySegment storeCM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.data[2];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = 0.0;
        dest[offset + 6] = this.data[4];
        dest[offset + 7] = this.data[5];
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
    public ByteBuffer storeCM3x3Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3DoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM3x3DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3DoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3DoubleRelative(ByteBuffer buf) {
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
    public MemorySegment storeCM3x3Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Double(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
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
    public ByteBuffer storeRM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3Relative(ByteBuffer buf) {
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
    public MemorySegment storeRM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
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
    public ByteBuffer storeRM3x3Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3DoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM3x3DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3DoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3DoubleRelative(ByteBuffer buf) {
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
    public MemorySegment storeRM3x3Double(long offset, MemorySegment dest) {
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
        dest[offset + 12] = this.data[4];
        dest[offset + 13] = this.data[5];
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
    public ByteBuffer storeCM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
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
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
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
        dest[offset + 12] = this.data[4];
        dest[offset + 13] = this.data[5];
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
    public ByteBuffer storeCM4x4Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4DoubleRelative(ByteBuffer buf) {
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
    public MemorySegment storeCM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Double(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = this.data[4];
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[3];
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = this.data[5];
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
    public ByteBuffer storeRM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
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
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = 0.0;
        dest[offset + 3] = this.data[4];
        dest[offset + 4] = this.data[1];
        dest[offset + 5] = this.data[3];
        dest[offset + 6] = 0.0;
        dest[offset + 7] = this.data[5];
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
    public ByteBuffer storeRM4x4Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4DoubleRelative(ByteBuffer buf) {
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
    public MemorySegment storeRM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Double(this, offset, dest);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_1 = FloatVector.fromArray(COL_SPECIES, new float[]{1.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final FloatVector VEC_5 = FloatVector.fromArray(COL_SPECIES, new float[]{1.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
    private static final float[] DATA_2 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final float[] DATA_3 = new float[] {1.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_4 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

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
