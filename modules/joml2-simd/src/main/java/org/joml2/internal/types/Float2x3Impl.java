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
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[4], sd[4], java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]))))));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (((sd[1]) * (sd[1]) + (((sd[4]) * (sd[4]) + (((sd[0]) * (sd[0]) + (sd[2] * sd[2])))))))))));
        }
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
    private Float2x3 invert_orthogonal_fma(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[1] * sd[5];
        float _t3 = -sd[3] * sd[5];
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = java.lang.Math.fma(sd[4], -sd[3], _t2) - java.lang.Math.fma(sd[1], sd[5], _t2);
        dd[5] = java.lang.Math.fma(sd[4], sd[1], _t3) - java.lang.Math.fma(sd[3], sd[5], _t3);
        dd[1] = -sd[1];
        dd[3] = sd[3];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_mulAdd(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[1] * sd[5];
        float _t3 = -sd[3] * sd[5];
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = ((_t2) - (sd[4]) * (sd[3])) - ((sd[1]) * (sd[5]) + (_t2));
        dd[5] = ((sd[4]) * (sd[1]) + (_t3)) - ((sd[3]) * (sd[5]) + (_t3));
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
    private Float2x3 invert_orthogonal_self_fma(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[1] * sd[5];
        float _t3 = -sd[3] * sd[5];
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = java.lang.Math.fma(sd[4], -sd[3], _t2) - java.lang.Math.fma(sd[1], sd[5], _t2);
        dd[5] = java.lang.Math.fma(sd[4], sd[1], _t3) - java.lang.Math.fma(sd[3], sd[5], _t3);
        dd[1] = -sd[1];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_orthogonal_self_mulAdd(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[1] * sd[5];
        float _t3 = -sd[3] * sd[5];
        dd[0] = sd[3];
        dd[2] = sd[1];
        float _buf0 = ((_t2) - (sd[4]) * (sd[3])) - ((sd[1]) * (sd[5]) + (_t2));
        dd[5] = ((sd[4]) * (sd[1]) + (_t3)) - ((sd[3]) * (sd[5]) + (_t3));
        dd[1] = -sd[1];
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_general_fma(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] * _t9_inv;
        float _buf1 = (java.lang.Math.fma(sd[2], sd[5], _t4) - java.lang.Math.fma(sd[4], sd[3], _t4)) * _t9_inv;
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] * _t9_inv;
        dd[5] = (java.lang.Math.fma(sd[4], sd[1], _t5) - java.lang.Math.fma(sd[0], sd[5], _t5)) * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float2x3 invert_general_mulAdd(@Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] * _t9_inv;
        float _buf1 = (((sd[2]) * (sd[5]) + (_t4)) - ((sd[4]) * (sd[3]) + (_t4))) * _t9_inv;
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] * _t9_inv;
        dd[5] = (((sd[4]) * (sd[1]) + (_t5)) - ((sd[0]) * (sd[5]) + (_t5))) * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return invert_general_fma(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
            return invert_orthogonal_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return invert_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
            return invert_orthogonal_mulAdd(dest);
        }
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
                float[] dd = this.data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
            return invert_orthogonal_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return invert_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
            return invert_orthogonal_self_mulAdd(this);
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_fma(dest);
        float _t9_inv = 1.0f / _t9;
        dd[0] = sd[3] * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] * _t9_inv;
        dd[4] = (java.lang.Math.fma(sd[2], sd[5], _t4) - java.lang.Math.fma(sd[4], sd[3], _t4)) * _t9_inv;
        dd[5] = (java.lang.Math.fma(sd[4], sd[1], _t5) - java.lang.Math.fma(sd[0], sd[5], _t5)) * _t9_inv;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invert_mulAdd(@Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invert_degenerate_mulAdd(dest);
        float _t9_inv = 1.0f / _t9;
        dd[0] = sd[3] * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] * _t9_inv;
        dd[4] = (((sd[2]) * (sd[5]) + (_t4)) - ((sd[4]) * (sd[3]) + (_t4))) * _t9_inv;
        dd[5] = (((sd[4]) * (sd[1]) + (_t5)) - ((sd[0]) * (sd[5]) + (_t5))) * _t9_inv;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_orthogonal_general_fma(@Mutated Float2x3 dest, int _props) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        dd[5] = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float2x3 invert_degenerate_orthogonal_general_mulAdd(@Mutated Float2x3 dest, int _props) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        dd[5] = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
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
            float[] dd = ((Float2x3Impl) dest).data;
            System.arraycopy(DATA_0, 0, dd, 0, 6);
            ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        return invert_translation(dest);
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
            float[] dd = ((Float2x3Impl) dest).data;
            System.arraycopy(DATA_0, 0, dd, 0, 6);
            ((Float2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        return invert_translation(dest);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_fma(@Mutated Double2x3 dest) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        dd[5] = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_mulAdd(@Mutated Double2x3 dest) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        dd[5] = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t6 = -(otherData[2] * otherData[1]);
        return invertProduct_general_sfde21b89_1_fma(other, dest, sd, otherData, ((Float2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(otherData[2] * otherData[5]), -(otherData[4] * otherData[1]), java.lang.Math.fma(otherData[0], otherData[3], _t6) - java.lang.Math.fma(otherData[2], otherData[1], _t6));
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t6 = -(otherData[2] * otherData[1]);
        return invertProduct_general_sfde21b89_1_mulAdd(other, dest, sd, otherData, ((Float2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(otherData[2] * otherData[5]), -(otherData[4] * otherData[1]), ((otherData[0]) * (otherData[3]) + (_t6)) - ((otherData[2]) * (otherData[1]) + (_t6)));
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_1_fma(Float2x3R other, Float2x3 dest, float[] sd, float[] otherData, float[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = java.lang.Math.fma(sd[0], sd[3], _t7) - java.lang.Math.fma(sd[2], sd[1], _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = otherData[3] / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = otherData[0] / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_general_sfde21b89_2_fma(other, dest, otherData, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(otherData[2] * _t24_inv), _t43, _t44, -(otherData[1] * _t24_inv), -((java.lang.Math.fma(sd[0], sd[5], _t8) - java.lang.Math.fma(sd[4], sd[1], _t8)) * _t25_inv), -((java.lang.Math.fma(sd[4], sd[3], _t9) - java.lang.Math.fma(sd[2], sd[5], _t9)) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 2 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_1_mulAdd(Float2x3R other, Float2x3 dest, float[] sd, float[] otherData, float[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = ((sd[0]) * (sd[3]) + (_t7)) - ((sd[2]) * (sd[1]) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = otherData[3] / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = otherData[0] / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_general_sfde21b89_2_mulAdd(other, dest, otherData, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(otherData[2] * _t24_inv), _t43, _t44, -(otherData[1] * _t24_inv), -((((sd[0]) * (sd[5]) + (_t8)) - ((sd[4]) * (sd[1]) + (_t8))) * _t25_inv), -((((sd[4]) * (sd[3]) + (_t9)) - ((sd[2]) * (sd[5]) + (_t9))) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_2_fma(Float2x3R other, Float2x3 dest, float[] otherData, float[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        float _buf0 = java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(otherData[4], otherData[3], _t10) - java.lang.Math.fma(otherData[2], otherData[5], _t10)) * _t24_inv)));
        dd[2] = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        dd[3] = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
        dd[5] = java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(otherData[0], otherData[5], _t11) - java.lang.Math.fma(otherData[4], otherData[1], _t11)) * _t24_inv)));
        dd[0] = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        dd[1] = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }

    /** Piece 3 of {@code invertProduct_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_general_sfde21b89_2_mulAdd(Float2x3R other, Float2x3 dest, float[] otherData, float[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        float _buf0 = ((_t42) * (_t50) + (((_t51) * (_t36) - ((((otherData[4]) * (otherData[3]) + (_t10)) - ((otherData[2]) * (otherData[5]) + (_t10))) * _t24_inv))));
        dd[2] = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
        dd[3] = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        dd[5] = ((_t45) * (_t51) + (((_t50) * (_t41) - ((((otherData[0]) * (otherData[5]) + (_t11)) - ((otherData[4]) * (otherData[1]) + (_t11))) * _t24_inv))));
        dd[0] = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
        dd[1] = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(otherData[2] * otherData[1]);
        float _t4 = -(otherData[2] * otherData[5]);
        float _t5 = -(otherData[4] * otherData[1]);
        float _t9 = java.lang.Math.fma(otherData[0], otherData[3], _t3) - java.lang.Math.fma(otherData[2], otherData[1], _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = otherData[3] / _t9;
        float _buf1 = -((java.lang.Math.fma(otherData[4], otherData[3], _t4) - java.lang.Math.fma(otherData[2], otherData[5], _t4)) * _t9_inv);
        dd[2] = -(otherData[2] * _t9_inv);
        dd[3] = otherData[0] / _t9;
        dd[5] = -((java.lang.Math.fma(otherData[0], otherData[5], _t5) - java.lang.Math.fma(otherData[4], otherData[1], _t5)) * _t9_inv);
        dd[1] = -(otherData[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(otherData[2] * otherData[1]);
        float _t4 = -(otherData[2] * otherData[5]);
        float _t5 = -(otherData[4] * otherData[1]);
        float _t9 = ((otherData[0]) * (otherData[3]) + (_t3)) - ((otherData[2]) * (otherData[1]) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = otherData[3] / _t9;
        float _buf1 = -((((otherData[4]) * (otherData[3]) + (_t4)) - ((otherData[2]) * (otherData[5]) + (_t4))) * _t9_inv);
        dd[2] = -(otherData[2] * _t9_inv);
        dd[3] = otherData[0] / _t9;
        dd[5] = -((((otherData[0]) * (otherData[5]) + (_t5)) - ((otherData[4]) * (otherData[1]) + (_t5))) * _t9_inv);
        dd[1] = -(otherData[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[4];
        float _t3 = -sd[5];
        float _t7 = -(otherData[2] * otherData[1]);
        float _t8 = -(otherData[2] * otherData[5]);
        float _t9 = -(otherData[4] * otherData[1]);
        float _t13 = java.lang.Math.fma(otherData[0], otherData[3], _t7) - java.lang.Math.fma(otherData[2], otherData[1], _t7);
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t13_inv = 1.0f / _t13;
        float _t18 = otherData[3] / _t13;
        float _t21 = otherData[0] / _t13;
        float _t22 = -(otherData[2] * _t13_inv);
        float _t23 = -(otherData[1] * _t13_inv);
        float _buf0 = java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(_t3, _t22, -((java.lang.Math.fma(otherData[4], otherData[3], _t8) - java.lang.Math.fma(otherData[2], otherData[5], _t8)) * _t13_inv)));
        dd[2] = _t22;
        dd[3] = _t21;
        dd[5] = java.lang.Math.fma(_t2, _t23, java.lang.Math.fma(_t3, _t21, -((java.lang.Math.fma(otherData[0], otherData[5], _t9) - java.lang.Math.fma(otherData[4], otherData[1], _t9)) * _t13_inv)));
        dd[0] = _t18;
        dd[1] = _t23;
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -sd[4];
        float _t3 = -sd[5];
        float _t7 = -(otherData[2] * otherData[1]);
        float _t8 = -(otherData[2] * otherData[5]);
        float _t9 = -(otherData[4] * otherData[1]);
        float _t13 = ((otherData[0]) * (otherData[3]) + (_t7)) - ((otherData[2]) * (otherData[1]) + (_t7));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t13_inv = 1.0f / _t13;
        float _t18 = otherData[3] / _t13;
        float _t21 = otherData[0] / _t13;
        float _t22 = -(otherData[2] * _t13_inv);
        float _t23 = -(otherData[1] * _t13_inv);
        float _buf0 = ((_t2) * (_t18) + (((_t3) * (_t22) - ((((otherData[4]) * (otherData[3]) + (_t8)) - ((otherData[2]) * (otherData[5]) + (_t8))) * _t13_inv))));
        dd[2] = _t22;
        dd[3] = _t21;
        dd[5] = ((_t2) * (_t23) + (((_t3) * (_t21) - ((((otherData[0]) * (otherData[5]) + (_t9)) - ((otherData[4]) * (otherData[1]) + (_t9))) * _t13_inv))));
        dd[0] = _t18;
        dd[1] = _t23;
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t11 = -(otherData[2] * otherData[1]);
        float _t12 = -(sd[0] * sd[5]);
        float _t13 = -(sd[4] * sd[3]);
        return invertProduct_orthogonal_s4b20548_1_fma(other, dest, otherData, ((Float2x3Impl) dest).data, sd[3], sd[0], -sd[1], -sd[2], -(otherData[2] * otherData[5]), -(otherData[4] * otherData[1]), java.lang.Math.fma(sd[4], sd[1], _t12) - java.lang.Math.fma(sd[0], sd[5], _t12), java.lang.Math.fma(sd[2], sd[5], _t13) - java.lang.Math.fma(sd[4], sd[3], _t13), java.lang.Math.fma(otherData[0], otherData[3], _t11) - java.lang.Math.fma(otherData[2], otherData[1], _t11));
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t11 = -(otherData[2] * otherData[1]);
        float _t12 = -(sd[0] * sd[5]);
        float _t13 = -(sd[4] * sd[3]);
        return invertProduct_orthogonal_s4b20548_1_mulAdd(other, dest, otherData, ((Float2x3Impl) dest).data, sd[3], sd[0], -sd[1], -sd[2], -(otherData[2] * otherData[5]), -(otherData[4] * otherData[1]), ((sd[4]) * (sd[1]) + (_t12)) - ((sd[0]) * (sd[5]) + (_t12)), ((sd[2]) * (sd[5]) + (_t13)) - ((sd[4]) * (sd[3]) + (_t13)), ((otherData[0]) * (otherData[3]) + (_t11)) - ((otherData[2]) * (otherData[1]) + (_t11)));
    }

    /** Piece 2 of {@code invertProduct_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_orthogonal_s4b20548_1_fma(Float2x3R other, Float2x3 dest, float[] otherData, float[] dd, float _t0, float _t2, float _t4, float _t5, float _t14, float _t15, float _t25, float _t26, float _t27) {
        if (!(java.lang.Math.abs(_t27) > 1.1754944E-38f && java.lang.Math.abs(_t27) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t27_inv = 1.0f / _t27;
        float _t32 = otherData[3] / _t27;
        float _t35 = otherData[0] / _t27;
        float _t36 = -(otherData[2] * _t27_inv);
        float _t37 = -(otherData[1] * _t27_inv);
        float _t38 = _t5 * _t32;
        float _t39 = _t4 * _t35;
        float _t40 = _t4 * _t36;
        float _t41 = _t5 * _t37;
        float _buf0 = java.lang.Math.fma(_t36, _t25, java.lang.Math.fma(_t26, _t32, -((java.lang.Math.fma(otherData[4], otherData[3], _t14) - java.lang.Math.fma(otherData[2], otherData[5], _t14)) * _t27_inv)));
        dd[2] = java.lang.Math.fma(_t2, _t36, _t38) + java.lang.Math.fma(_t5, _t32, -_t38);
        dd[3] = java.lang.Math.fma(_t2, _t35, _t41) + java.lang.Math.fma(_t5, _t37, -_t41);
        dd[5] = java.lang.Math.fma(_t37, _t26, java.lang.Math.fma(_t25, _t35, -((java.lang.Math.fma(otherData[0], otherData[5], _t15) - java.lang.Math.fma(otherData[4], otherData[1], _t15)) * _t27_inv)));
        dd[0] = java.lang.Math.fma(_t0, _t32, _t40) + java.lang.Math.fma(_t4, _t36, -_t40);
        dd[1] = java.lang.Math.fma(_t0, _t37, _t39) + java.lang.Math.fma(_t4, _t35, -_t39);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_orthogonal}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_orthogonal_s4b20548_1_mulAdd(Float2x3R other, Float2x3 dest, float[] otherData, float[] dd, float _t0, float _t2, float _t4, float _t5, float _t14, float _t15, float _t25, float _t26, float _t27) {
        if (!(java.lang.Math.abs(_t27) > 1.1754944E-38f && java.lang.Math.abs(_t27) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t27_inv = 1.0f / _t27;
        float _t32 = otherData[3] / _t27;
        float _t35 = otherData[0] / _t27;
        float _t36 = -(otherData[2] * _t27_inv);
        float _t37 = -(otherData[1] * _t27_inv);
        float _t38 = _t5 * _t32;
        float _t39 = _t4 * _t35;
        float _t40 = _t4 * _t36;
        float _t41 = _t5 * _t37;
        float _buf0 = ((_t36) * (_t25) + (((_t26) * (_t32) - ((((otherData[4]) * (otherData[3]) + (_t14)) - ((otherData[2]) * (otherData[5]) + (_t14))) * _t27_inv))));
        dd[2] = ((_t2) * (_t36) + (_t38)) + ((_t5) * (_t32) - (_t38));
        dd[3] = ((_t2) * (_t35) + (_t41)) + ((_t5) * (_t37) - (_t41));
        dd[5] = ((_t37) * (_t26) + (((_t25) * (_t35) - ((((otherData[0]) * (otherData[5]) + (_t15)) - ((otherData[4]) * (otherData[1]) + (_t15))) * _t27_inv))));
        dd[0] = ((_t0) * (_t32) + (_t40)) + ((_t4) * (_t36) - (_t40));
        dd[1] = ((_t0) * (_t37) + (_t39)) + ((_t4) * (_t35) - (_t39));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
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
    private Float2x3 invertProduct_translation_translation(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        VEC_1.intoArray(dd, 0);
        dd[4] = -otherData[4] - sd[4];
        dd[5] = -otherData[5] - sd[5];
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -(sd[4] * sd[3]);
        float _t3 = -(sd[0] * sd[5]);
        float _buf0 = sd[3];
        float _buf1 = java.lang.Math.fma(sd[2], sd[5], _t2) - java.lang.Math.fma(sd[4], sd[3], _t2);
        dd[2] = -sd[2];
        dd[3] = sd[0];
        dd[5] = java.lang.Math.fma(sd[4], sd[1], _t3) - java.lang.Math.fma(sd[0], sd[5], _t3);
        dd[1] = -sd[1];
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -(sd[4] * sd[3]);
        float _t3 = -(sd[0] * sd[5]);
        float _buf0 = sd[3];
        float _buf1 = ((sd[2]) * (sd[5]) + (_t2)) - ((sd[4]) * (sd[3]) + (_t2));
        dd[2] = -sd[2];
        dd[3] = sd[0];
        dd[5] = ((sd[4]) * (sd[1]) + (_t3)) - ((sd[0]) * (sd[5]) + (_t3));
        dd[1] = -sd[1];
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -(sd[4] * sd[3]);
        float _t3 = -(sd[0] * sd[5]);
        float _buf0 = sd[3];
        float _buf1 = -otherData[4] + (java.lang.Math.fma(sd[2], sd[5], _t2) - java.lang.Math.fma(sd[4], sd[3], _t2));
        dd[2] = -sd[2];
        dd[3] = sd[0];
        dd[5] = -otherData[5] + (java.lang.Math.fma(sd[4], sd[1], _t3) - java.lang.Math.fma(sd[0], sd[5], _t3));
        dd[1] = -sd[1];
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_orthogonal_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = -(sd[4] * sd[3]);
        float _t3 = -(sd[0] * sd[5]);
        float _buf0 = sd[3];
        float _buf1 = -otherData[4] + (((sd[2]) * (sd[5]) + (_t2)) - ((sd[4]) * (sd[3]) + (_t2)));
        dd[2] = -sd[2];
        dd[3] = sd[0];
        dd[5] = -otherData[5] + (((sd[4]) * (sd[1]) + (_t3)) - ((sd[0]) * (sd[5]) + (_t3)));
        dd[1] = -sd[1];
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] / _t9;
        float _buf1 = -((java.lang.Math.fma(sd[4], sd[3], _t4) - java.lang.Math.fma(sd[2], sd[5], _t4)) * _t9_inv);
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] / _t9;
        dd[5] = -((java.lang.Math.fma(sd[0], sd[5], _t5) - java.lang.Math.fma(sd[4], sd[1], _t5)) * _t9_inv);
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] / _t9;
        float _buf1 = -((((sd[4]) * (sd[3]) + (_t4)) - ((sd[2]) * (sd[5]) + (_t4))) * _t9_inv);
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] / _t9;
        dd[5] = -((((sd[0]) * (sd[5]) + (_t5)) - ((sd[4]) * (sd[1]) + (_t5))) * _t9_inv);
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_fma(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] / _t9;
        float _buf1 = -otherData[4] - (java.lang.Math.fma(sd[4], sd[3], _t4) - java.lang.Math.fma(sd[2], sd[5], _t4)) * _t9_inv;
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] / _t9;
        dd[5] = -otherData[5] - (java.lang.Math.fma(sd[0], sd[5], _t5) - java.lang.Math.fma(sd[4], sd[1], _t5)) * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float2x3 invertProduct_general_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t3 = -(sd[2] * sd[1]);
        float _t4 = -(sd[2] * sd[5]);
        float _t5 = -(sd[4] * sd[1]);
        float _t9 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(other, dest);
        float _t9_inv = 1.0f / _t9;
        float _buf0 = sd[3] / _t9;
        float _buf1 = -otherData[4] - (((sd[4]) * (sd[3]) + (_t4)) - ((sd[2]) * (sd[5]) + (_t4))) * _t9_inv;
        dd[2] = -(sd[2] * _t9_inv);
        dd[3] = sd[0] / _t9;
        dd[5] = -otherData[5] - (((sd[0]) * (sd[5]) + (_t5)) - ((sd[4]) * (sd[1]) + (_t5))) * _t9_inv;
        dd[1] = -(sd[1] * _t9_inv);
        dd[0] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
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
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
                return dest;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = ((Float2x3Impl) dest).data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return dest;
            }
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
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
                return dest;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = ((Float2x3Impl) dest).data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return dest;
            }
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
                float[] dd = this.data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                this.properties = ((Float2x3Impl) other).properties;
                return this;
            }
            return invertProduct_identity_translation(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_fma(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = this.data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                this.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return this;
            }
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
                float[] dd = this.data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                this.properties = ((Float2x3Impl) other).properties;
                return this;
            }
            return invertProduct_identity_translation(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_translation_mulAdd(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = this.data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                this.properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return this;
            }
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
        float[] otherData = ((Float2x3Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m02 = otherData[4];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float m12 = otherData[5];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sc6650d83_1_fma(m00, m01, m02, m10, m11, m12, dest, sd, ((Double2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), java.lang.Math.fma(m00, m11, _t6) - java.lang.Math.fma(m01, m10, _t6));
        } else {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sc6650d83_1_mulAdd(m00, m01, m02, m10, m11, m12, dest, sd, ((Double2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), ((m00) * (m11) + (_t6)) - ((m01) * (m10) + (_t6)));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sd851cb36_1_fma(m00, m01, m02, m10, m11, m12, dest, sd, ((Float2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), java.lang.Math.fma(m00, m11, _t6) - java.lang.Math.fma(m01, m10, _t6));
        } else {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sd851cb36_1_mulAdd(m00, m01, m02, m10, m11, m12, dest, sd, ((Float2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), ((m00) * (m11) + (_t6)) - ((m01) * (m10) + (_t6)));
        }
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_sd851cb36_1_fma(float m00, float m01, float m02, float m10, float m11, float m12, Float2x3 dest, float[] sd, float[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = java.lang.Math.fma(sd[0], sd[3], _t7) - java.lang.Math.fma(sd[2], sd[1], _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = m00 / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_sd851cb36_2_fma(m00, m01, m02, m10, m11, m12, dest, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(m01 * _t24_inv), _t43, _t44, -(m10 * _t24_inv), -((java.lang.Math.fma(sd[0], sd[5], _t8) - java.lang.Math.fma(sd[4], sd[1], _t8)) * _t25_inv), -((java.lang.Math.fma(sd[4], sd[3], _t9) - java.lang.Math.fma(sd[2], sd[5], _t9)) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_sd851cb36_1_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, Float2x3 dest, float[] sd, float[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = ((sd[0]) * (sd[3]) + (_t7)) - ((sd[2]) * (sd[1]) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = m00 / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_sd851cb36_2_mulAdd(m00, m01, m02, m10, m11, m12, dest, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(m01 * _t24_inv), _t43, _t44, -(m10 * _t24_inv), -((((sd[0]) * (sd[5]) + (_t8)) - ((sd[4]) * (sd[1]) + (_t8))) * _t25_inv), -((((sd[4]) * (sd[3]) + (_t9)) - ((sd[2]) * (sd[5]) + (_t9))) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_sd851cb36_2_fma(float m00, float m01, float m02, float m10, float m11, float m12, Float2x3 dest, float[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        dd[0] = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        dd[1] = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
        dd[2] = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        dd[3] = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
        dd[4] = java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(m02, m11, _t10) - java.lang.Math.fma(m01, m12, _t10)) * _t24_inv)));
        dd[5] = java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(m00, m12, _t11) - java.lang.Math.fma(m02, m10, _t11)) * _t24_inv)));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** Piece 3 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_sd851cb36_2_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, Float2x3 dest, float[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        dd[0] = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
        dd[1] = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        dd[2] = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
        dd[3] = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        dd[4] = ((_t42) * (_t50) + (((_t51) * (_t36) - ((((m02) * (m11) + (_t10)) - ((m01) * (m12) + (_t10))) * _t24_inv))));
        dd[5] = ((_t45) * (_t51) + (((_t50) * (_t41) - ((((m00) * (m12) + (_t11)) - ((m02) * (m10) + (_t11))) * _t24_inv))));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sc6650d83_1_fma(m00, m01, m02, m10, m11, m12, dest, sd, ((Double2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), java.lang.Math.fma(m00, m11, _t6) - java.lang.Math.fma(m01, m10, _t6));
        } else {
            float[] sd = this.data;
            float _t6 = -(m01 * m10);
            return invertProduct_sc6650d83_1_mulAdd(m00, m01, m02, m10, m11, m12, dest, sd, ((Double2x3Impl) dest).data, -(sd[2] * sd[1]), -(sd[4] * sd[1]), -(sd[2] * sd[5]), -(m01 * m12), -(m02 * m10), ((m00) * (m11) + (_t6)) - ((m01) * (m10) + (_t6)));
        }
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_sc6650d83_1_fma(float m00, float m01, float m02, float m10, float m11, float m12, Double2x3 dest, float[] sd, double[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = java.lang.Math.fma(sd[0], sd[3], _t7) - java.lang.Math.fma(sd[2], sd[1], _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = m00 / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_sc6650d83_2_fma(m00, m01, m02, m10, m11, m12, dest, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(m01 * _t24_inv), _t43, _t44, -(m10 * _t24_inv), -((java.lang.Math.fma(sd[0], sd[5], _t8) - java.lang.Math.fma(sd[4], sd[1], _t8)) * _t25_inv), -((java.lang.Math.fma(sd[4], sd[3], _t9) - java.lang.Math.fma(sd[2], sd[5], _t9)) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_sc6650d83_1_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, Double2x3 dest, float[] sd, double[] dd, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t24_inv = 1.0f / _t24;
        float _t25 = ((sd[0]) * (sd[3]) + (_t7)) - ((sd[2]) * (sd[1]) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float _t25_inv = 1.0f / _t25;
        float _t36 = m11 / _t24;
        float _t37 = sd[3] / _t25;
        float _t38 = sd[0] / _t25;
        float _t41 = m00 / _t24;
        float _t43 = -(sd[1] * _t25_inv);
        float _t44 = -(sd[2] * _t25_inv);
        return invertProduct_sc6650d83_2_mulAdd(m00, m01, m02, m10, m11, m12, dest, dd, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, -(m01 * _t24_inv), _t43, _t44, -(m10 * _t24_inv), -((((sd[0]) * (sd[5]) + (_t8)) - ((sd[4]) * (sd[1]) + (_t8))) * _t25_inv), -((((sd[4]) * (sd[3]) + (_t9)) - ((sd[2]) * (sd[5]) + (_t9))) * _t25_inv), _t36 * _t37, _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_sc6650d83_2_fma(float m00, float m01, float m02, float m10, float m11, float m12, Double2x3 dest, double[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        dd[0] = java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52);
        dd[1] = java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55);
        dd[2] = java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54);
        dd[3] = java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53);
        dd[4] = java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(m02, m11, _t10) - java.lang.Math.fma(m01, m12, _t10)) * _t24_inv)));
        dd[5] = java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(m00, m12, _t11) - java.lang.Math.fma(m02, m10, _t11)) * _t24_inv)));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** Piece 3 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_sc6650d83_2_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, Double2x3 dest, double[] dd, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55) {
        dd[0] = ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52));
        dd[1] = ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55));
        dd[2] = ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54));
        dd[3] = ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53));
        dd[4] = ((_t42) * (_t50) + (((_t51) * (_t36) - ((((m02) * (m11) + (_t10)) - ((m01) * (m12) + (_t10))) * _t24_inv))));
        dd[5] = ((_t45) * (_t51) + (((_t50) * (_t41) - ((((m00) * (m12) + (_t11)) - ((m02) * (m10) + (_t11))) * _t24_inv))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_general_fma(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = otherData[2] * _t0;
        float _t17 = otherData[0] * _t0;
        float _t18 = otherData[3] * _t1;
        float _t19 = otherData[1] * _t1;
        float _t20 = sd[1] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = otherData[4] * _t0;
        float _t27 = otherData[5] * _t1;
        float _t42 = -(_t16 * _t19);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        return invertProduct_degenerate_general_s1fef5c72_1_fma(dest, _props, ((Float2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, sd[0] * _t3, sd[3] * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = otherData[2] * _t0;
        float _t17 = otherData[0] * _t0;
        float _t18 = otherData[3] * _t1;
        float _t19 = otherData[1] * _t1;
        float _t20 = sd[1] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = otherData[4] * _t0;
        float _t27 = otherData[5] * _t1;
        float _t42 = -(_t16 * _t19);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        return invertProduct_degenerate_general_s1fef5c72_1_mulAdd(dest, _props, ((Float2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, sd[0] * _t3, sd[3] * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_1_fma(Float2x3 dest, int _props, float[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv) {
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        dd[1] = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        dd[2] = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        return invertProduct_degenerate_general_s1fef5c72_2_fma(dest, _props, dd, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_1_mulAdd(Float2x3 dest, int _props, float[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv) {
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        dd[1] = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        dd[2] = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        return invertProduct_degenerate_general_s1fef5c72_2_mulAdd(dest, _props, dd, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72);
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_2_fma(Float2x3 dest, int _props, float[] dd, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87) {
        dd[3] = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        dd[4] = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        dd[5] = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /** Piece 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_general_s1fef5c72_2_mulAdd(Float2x3 dest, int _props, float[] dd, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87) {
        dd[3] = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        dd[4] = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        dd[5] = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_identity_fma(Float2x3R other, @Mutated Float2x3 dest) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv);
        dd[5] = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv);
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_identity_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv);
        dd[5] = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv);
        ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t4 = -sd[5];
        float _t5 = -sd[4];
        float _t6 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t7 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t14 = otherData[3] * _t6;
        float _t15 = otherData[0] * _t7;
        float _t16 = otherData[2] * _t7;
        float _t17 = otherData[1] * _t6;
        float _t18 = otherData[4] * _t7;
        float _t19 = otherData[5] * _t6;
        float _t27 = -(_t16 * _t17);
        float _t28 = -(_t16 * _t19);
        float _t32 = java.lang.Math.fma(_t15, _t14, _t27) - java.lang.Math.fma(_t16, _t17, _t27);
        float _t32_inv = 1.0f / _t32;
        float _t37 = _t14 * _t7 / _t32;
        float _t40 = _t15 * _t6 / _t32;
        float _t41 = -(_t16 * _t6 * _t32_inv);
        float _t42 = -(_t17 * _t7 * _t32_inv);
        dd[0] = _t37;
        dd[1] = _t42;
        dd[2] = _t41;
        dd[3] = _t40;
        dd[4] = java.lang.Math.fma(_t41, _t4, java.lang.Math.fma(_t5, _t37, -((java.lang.Math.fma(_t18, _t14, _t28) - java.lang.Math.fma(_t16, _t19, _t28)) * _t32_inv)));
        return invertProduct_degenerate_translation_s336cdc7f_1_fma(other, dest, dd, _t4, _t5, _t15, _t17, _t18, _t19, -(_t18 * _t17), _t32_inv, _t40, _t42);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t4 = -sd[5];
        float _t5 = -sd[4];
        float _t6 = unitScale(otherData[1], otherData[3], otherData[1]);
        float _t7 = unitScale(otherData[0], otherData[2], otherData[0]);
        float _t14 = otherData[3] * _t6;
        float _t15 = otherData[0] * _t7;
        float _t16 = otherData[2] * _t7;
        float _t17 = otherData[1] * _t6;
        float _t18 = otherData[4] * _t7;
        float _t19 = otherData[5] * _t6;
        float _t27 = -(_t16 * _t17);
        float _t28 = -(_t16 * _t19);
        float _t32 = ((_t15) * (_t14) + (_t27)) - ((_t16) * (_t17) + (_t27));
        float _t32_inv = 1.0f / _t32;
        float _t37 = _t14 * _t7 / _t32;
        float _t40 = _t15 * _t6 / _t32;
        float _t41 = -(_t16 * _t6 * _t32_inv);
        float _t42 = -(_t17 * _t7 * _t32_inv);
        dd[0] = _t37;
        dd[1] = _t42;
        dd[2] = _t41;
        dd[3] = _t40;
        dd[4] = ((_t41) * (_t4) + (((_t5) * (_t37) - ((((_t18) * (_t14) + (_t28)) - ((_t16) * (_t19) + (_t28))) * _t32_inv))));
        return invertProduct_degenerate_translation_s336cdc7f_1_mulAdd(other, dest, dd, _t4, _t5, _t15, _t17, _t18, _t19, -(_t18 * _t17), _t32_inv, _t40, _t42);
    }

    /** Piece 2 of {@code invertProduct_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_translation_s336cdc7f_1_fma(Float2x3R other, Float2x3 dest, float[] dd, float _t4, float _t5, float _t15, float _t17, float _t18, float _t19, float _t29, float _t32_inv, float _t40, float _t42) {
        dd[5] = java.lang.Math.fma(_t42, _t5, java.lang.Math.fma(_t4, _t40, -((java.lang.Math.fma(_t15, _t19, _t29) - java.lang.Math.fma(_t18, _t17, _t29)) * _t32_inv)));
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate_translation}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_translation_s336cdc7f_1_mulAdd(Float2x3R other, Float2x3 dest, float[] dd, float _t4, float _t5, float _t15, float _t17, float _t18, float _t19, float _t29, float _t32_inv, float _t40, float _t42) {
        dd[5] = ((_t42) * (_t5) + (((_t4) * (_t40) - ((((_t15) * (_t19) + (_t29)) - ((_t18) * (_t17) + (_t29))) * _t32_inv))));
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_identity_fma(@Mutated Float2x3 dest, int _props) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv);
        dd[5] = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_identity_mulAdd(@Mutated Float2x3 dest, int _props) {
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv);
        dd[5] = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv);
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_translation_fma(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t8 = sd[3] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[2] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[4] * _t1;
        float _t13 = sd[5] * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((java.lang.Math.fma(_t12, _t8, _t18) - java.lang.Math.fma(_t10, _t13, _t18)) * _t22_inv) - otherData[4];
        dd[5] = -((java.lang.Math.fma(_t9, _t13, _t19) - java.lang.Math.fma(_t12, _t11, _t19)) * _t22_inv) - otherData[5];
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_orthogonal_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = unitScale(sd[1], sd[3], sd[1]);
        float _t1 = unitScale(sd[0], sd[2], sd[0]);
        float _t8 = sd[3] * _t0;
        float _t9 = sd[0] * _t1;
        float _t10 = sd[2] * _t1;
        float _t11 = sd[1] * _t0;
        float _t12 = sd[4] * _t1;
        float _t13 = sd[5] * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22 = ((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t22_inv = 1.0f / _t22;
        dd[0] = _t8 * _t1 / _t22;
        dd[1] = -(_t11 * _t1 * _t22_inv);
        dd[2] = -(_t10 * _t0 * _t22_inv);
        dd[3] = _t9 * _t0 / _t22;
        dd[4] = -((((_t12) * (_t8) + (_t18)) - ((_t10) * (_t13) + (_t18))) * _t22_inv) - otherData[4];
        dd[5] = -((((_t9) * (_t13) + (_t19)) - ((_t12) * (_t11) + (_t19))) * _t22_inv) - otherData[5];
        ((Float2x3Impl) dest).properties = _props;
        return dest;
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
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
                return dest;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_fma(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = ((Float2x3Impl) dest).data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return dest;
            }
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
                float[] dd = ((Float2x3Impl) dest).data;
                System.arraycopy(DATA_0, 0, dd, 0, 6);
                ((Float2x3Impl) dest).properties = ((Float2x3Impl) other).properties;
                return dest;
            }
            return invertProduct_identity_translation(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_mulAdd(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] sd = this.data;
                float[] dd = ((Float2x3Impl) dest).data;
                VEC_1.intoArray(dd, 0);
                dd[4] = -sd[4];
                dd[5] = -sd[5];
                ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
                return dest;
            }
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
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = sd[1] * _t2;
        float _t21 = sd[0] * _t3;
        float _t22 = sd[3] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        return invertProduct_degenerate_sc754e14d_1_fma(dest, ((Float2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56, _t22 * _t3 / _t57);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float2x3 invertProduct_degenerate_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = sd[1] * _t2;
        float _t21 = sd[0] * _t3;
        float _t22 = sd[3] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        return invertProduct_degenerate_sc754e14d_1_mulAdd(dest, ((Float2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56, _t22 * _t3 / _t57);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_1_fma(Float2x3 dest, float[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70, float _t71) {
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv);
        float _t85 = -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t87 = _t75 * _t72;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        dd[1] = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        dd[2] = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        dd[3] = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        dd[4] = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        return invertProduct_degenerate_sc754e14d_2_fma(dest, dd, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t75, _t79, _t84, _t85);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_1_mulAdd(Float2x3 dest, float[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70, float _t71) {
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv);
        float _t85 = -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t87 = _t75 * _t72;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        dd[1] = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        dd[2] = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        dd[3] = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        dd[4] = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        dd[5] = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Float2x3 invertProduct_degenerate_sc754e14d_2_fma(Float2x3 dest, float[] dd, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t75, float _t79, float _t84, float _t85) {
        dd[5] = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_fma(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = sd[1] * _t2;
        float _t21 = sd[0] * _t3;
        float _t22 = sd[3] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        return invertProduct_degenerate_s6ae5eb0a_1_fma(dest, ((Double2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56, _t22 * _t3 / _t57);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        float _t0 = unitScale(m00, m01, m00);
        float _t1 = unitScale(m10, m11, m10);
        float _t2 = unitScale(sd[1], sd[3], sd[1]);
        float _t3 = unitScale(sd[0], sd[2], sd[0]);
        float _t16 = m01 * _t0;
        float _t17 = m00 * _t0;
        float _t18 = m11 * _t1;
        float _t19 = m10 * _t1;
        float _t20 = sd[1] * _t2;
        float _t21 = sd[0] * _t3;
        float _t22 = sd[3] * _t2;
        float _t23 = sd[2] * _t3;
        float _t24 = sd[5] * _t2;
        float _t25 = sd[4] * _t3;
        float _t26 = m02 * _t0;
        float _t27 = m12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        return invertProduct_degenerate_s6ae5eb0a_1_mulAdd(dest, ((Double2x3Impl) dest).data, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), _t56, 1.0f / _t56, _t57, 1.0f / _t57, _t18 * _t0 / _t56, _t22 * _t3 / _t57);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_1_fma(Double2x3 dest, double[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70, float _t71) {
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv);
        float _t85 = -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t87 = _t75 * _t72;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        dd[1] = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        dd[2] = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        dd[3] = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        dd[4] = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        return invertProduct_degenerate_s6ae5eb0a_2_fma(dest, dd, _t17, _t19, _t26, _t27, _t47, _t56_inv, _t75, _t79, _t84, _t85);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_1_mulAdd(Double2x3 dest, double[] dd, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t44, float _t45, float _t46, float _t47, float _t56, float _t56_inv, float _t57, float _t57_inv, float _t70, float _t71) {
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t84 = -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv);
        float _t85 = -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv);
        float _t86 = _t70 * _t71;
        float _t87 = _t75 * _t72;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dd[0] = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        dd[1] = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        dd[2] = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        dd[3] = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        dd[4] = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        dd[5] = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_s6ae5eb0a_2_fma(Double2x3 dest, double[] dd, float _t17, float _t19, float _t26, float _t27, float _t47, float _t56_inv, float _t75, float _t79, float _t84, float _t85) {
        dd[5] = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
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
        float[] otherData = ((Float2x3Impl) other).data;
        float m01 = otherData[2];
        float m02 = otherData[4];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float m12 = otherData[5];
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
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
    private Float2x3 mul_translation_fma(float scalar, @Mutated Float2x3 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_mulAdd(float scalar, @Mutated Float2x3 dest) {
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
    private Float2x3 mul_general_fma(float scalar, @Mutated Float2x3 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_mulAdd(float scalar, @Mutated Float2x3 dest) {
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? mul_general_fma(scalar, dest) : mul_general_mulAdd(scalar, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
                float[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return mul_translation_self(scalar, this);
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return mul_translation_self(scalar, this);
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
        float[] otherData = ((Float2x3Impl) other).data;
        float m01 = otherData[2];
        float m02 = otherData[4];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float m12 = otherData[5];
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
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
        float[] tData = ((Float2Impl) t).data;
        float tX = tData[0];
        float tY = tData[1];
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
            dd[4] = tX;
            dd[5] = tY;
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = tX;
            dd[5] = tY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = tX;
        dd[5] = tY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        float[] tData = ((Float2Impl) t).data;
        float tX = tData[0];
        float tY = tData[1];
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
        float[] tData = ((Float2Impl) t).data;
        float tX = tData[0];
        float tY = tData[1];
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        float[] dd = this.data;
        dd[4] = tX;
        dd[5] = tY;
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
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
            dd[4] = tX;
            dd[5] = tY;
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = tX;
            dd[5] = tY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = tX;
        dd[5] = tY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        float[] dd = this.data;
        dd[4] = tX;
        dd[5] = tY;
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
            float[] dd = ((Float3x3Impl) dest).data;
            System.arraycopy(DATA_4, 0, dd, 0, 9);
            ((Float3x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        return to3x3_translation(dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(other, t, dest);
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, otherData[5] - sd[5], sd[5]);
        ((Float2x3Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Float2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Float2x3Impl) other).properties) >> 1);
        return dest;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 lerp_mulAdd(Float2x3R other, float t, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = ((t) * (otherData[0] - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherData[1] - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherData[2] - sd[2]) + (sd[2]));
        dd[3] = ((t) * (otherData[3] - sd[3]) + (sd[3]));
        dd[4] = ((t) * (otherData[4] - sd[4]) + (sd[4]));
        dd[5] = ((t) * (otherData[5] - sd[5]) + (sd[5]));
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
        float[] otherData = ((Float2x3Impl) other).data;
        return lerp(otherData[0], otherData[2], otherData[4], otherData[1], otherData[3], otherData[5], t, dest);
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
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            dd[4] = java.lang.Math.fma(t, m02 - sd[4], sd[4]);
            dd[5] = java.lang.Math.fma(t, m12 - sd[5], sd[5]);
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
            dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
            dd[2] = ((t) * (m01 - sd[2]) + (sd[2]));
            dd[3] = ((t) * (m11 - sd[3]) + (sd[3]));
            dd[4] = ((t) * (m02 - sd[4]) + (sd[4]));
            dd[5] = ((t) * (m12 - sd[5]) + (sd[5]));
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            dd[4] = java.lang.Math.fma(t, m02 - sd[4], sd[4]);
            dd[5] = java.lang.Math.fma(t, m12 - sd[5], sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
            dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
            dd[2] = ((t) * (m01 - sd[2]) + (sd[2]));
            dd[3] = ((t) * (m11 - sd[3]) + (sd[3]));
            dd[4] = ((t) * (m02 - sd[4]) + (sd[4]));
            dd[5] = ((t) * (m12 - sd[5]) + (sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_fma(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        float _buf1 = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        float _buf2 = java.lang.Math.fma(rightData[4], sd[0], java.lang.Math.fma(rightData[5], sd[2], sd[4]));
        dd[0] = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[5] = java.lang.Math.fma(rightData[4], sd[1], java.lang.Math.fma(rightData[5], sd[3], sd[5]));
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
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
    private Float2x3 mul_general_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        float _buf1 = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        float _buf2 = ((rightData[4]) * (sd[0]) + (((rightData[5]) * (sd[2]) + (sd[4]))));
        dd[0] = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        dd[5] = ((rightData[4]) * (sd[1]) + (((rightData[5]) * (sd[3]) + (sd[5]))));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
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
    private Float2x3 mul_translation_fma(Float2x3R right, @Mutated Float2x3 dest) {
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
    private Float2x3 mul_translation_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
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
    private Float2x3 mul_orthogonal_fma(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[0] = java.lang.Math.fma(rightData[0], sd[3], rightData[1] * -sd[1]);
        float _buf1 = java.lang.Math.fma(rightData[2], sd[3], rightData[3] * -sd[1]);
        float _buf2 = java.lang.Math.fma(rightData[5], -sd[1], java.lang.Math.fma(rightData[4], sd[3], sd[4]));
        dd[5] = java.lang.Math.fma(rightData[4], sd[1], java.lang.Math.fma(rightData[5], sd[3], sd[5]));
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
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
    private Float2x3 mul_orthogonal_mulAdd(Float2x3R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        dd[0] = ((rightData[0]) * (sd[3]) - (rightData[1] * sd[1]));
        float _buf1 = ((rightData[2]) * (sd[3]) - (rightData[3] * sd[1]));
        float _buf2 = ((((rightData[4]) * (sd[3]) + (sd[4]))) - (rightData[5]) * (sd[1]));
        dd[5] = ((rightData[4]) * (sd[1]) + (((rightData[5]) * (sd[3]) + (sd[5]))));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
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
    private Float2x3 mul_orthogonal_translation_fma(Float2x3R right, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[4], sd[0], java.lang.Math.fma(rightData[5], sd[2], sd[4]));
        dd[5] = java.lang.Math.fma(rightData[4], sd[1], java.lang.Math.fma(rightData[5], sd[3], sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_translation_mulAdd(Float2x3R right, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float2x3Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((rightData[4]) * (sd[0]) + (((rightData[5]) * (sd[2]) + (sd[4]))));
        dd[5] = ((rightData[4]) * (sd[1]) + (((rightData[5]) * (sd[3]) + (sd[5]))));
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
        float[] rightData = ((Float2x3Impl) right).data;
        float m00 = rightData[0];
        float m01 = rightData[2];
        float m02 = rightData[4];
        float m10 = rightData[1];
        float m11 = rightData[3];
        float m12 = rightData[5];
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
        dd[1] = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
        dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
        dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
        dd[4] = java.lang.Math.fma(m02, sd[0], java.lang.Math.fma(m12, sd[2], sd[4]));
        dd[5] = java.lang.Math.fma(m02, sd[1], java.lang.Math.fma(m12, sd[3], sd[5]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
        float _buf1 = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
        dd[4] = java.lang.Math.fma(m02, sd[0], java.lang.Math.fma(m12, sd[2], sd[4]));
        dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
        dd[5] = java.lang.Math.fma(m02, sd[1], java.lang.Math.fma(m12, sd[3], sd[5]));
        dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 mul_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((m00) * (sd[0]) + (m10 * sd[2]));
        float _buf1 = ((m00) * (sd[1]) + (m10 * sd[3]));
        dd[4] = ((m02) * (sd[0]) + (((m12) * (sd[2]) + (sd[4]))));
        dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
        dd[5] = ((m02) * (sd[1]) + (((m12) * (sd[3]) + (sd[5]))));
        dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
        dd[1] = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
        dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
        dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
        dd[4] = java.lang.Math.fma(m02, sd[0], java.lang.Math.fma(m12, sd[2], sd[4]));
        dd[5] = java.lang.Math.fma(m02, sd[1], java.lang.Math.fma(m12, sd[3], sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 mul_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = ((m00) * (sd[0]) + (m10 * sd[2]));
        dd[1] = ((m00) * (sd[1]) + (m10 * sd[3]));
        dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
        dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
        dd[4] = ((m02) * (sd[0]) + (((m12) * (sd[2]) + (sd[4]))));
        dd[5] = ((m02) * (sd[1]) + (((m12) * (sd[3]) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    private Float2x3 mul_translation_fma(Float2x2R right, @Mutated Float2x3 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_translation_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_fma(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[0], sd[3], rightData[1] * -sd[1]);
        float _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[3], rightData[3] * -sd[1]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[1] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = ((rightData[0]) * (sd[3]) - (rightData[1] * sd[1]));
        float _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        dd[2] = ((rightData[2]) * (sd[3]) - (rightData[3] * sd[1]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
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
    private Float2x3 mul_orthogonal_self_fma(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[0], sd[3], rightData[1] * -sd[1]);
        float _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[3], rightData[3] * -sd[1]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_orthogonal_self_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = ((rightData[0]) * (sd[3]) - (rightData[1] * sd[1]));
        float _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        dd[2] = ((rightData[2]) * (sd[3]) - (rightData[3] * sd[1]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
        dd[1] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_fma(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        float _buf1 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        float _buf1 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        dd[2] = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
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
    private Float2x3 mul_general_self_fma(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        float _buf1 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Float2x3 mul_general_self_mulAdd(Float2x2R right, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        float _buf1 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        dd[2] = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
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
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? mul_general_fma(right, dest) : mul_general_mulAdd(right, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] rightData = ((Float2x2Impl) right).data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            ((Float2x3Impl) dest).properties = (((Float2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
            return dest;
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
                float[] rightData = ((Float2x2Impl) right).data;
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
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
                float[] rightData = ((Float2x2Impl) right).data;
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, rightData, 0).intoArray(dd, 0);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] rightData = ((Float2x2Impl) right).data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
            dd[1] = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
            dd[2] = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
            dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
            dd[4] = sd[4];
            dd[5] = sd[5];
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            float[] rightData = ((Float2x2Impl) right).data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
            dd[1] = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
            dd[2] = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
            dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
            dd[4] = sd[4];
            dd[5] = sd[5];
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_fma(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[2], sd[4], java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]));
        dd[1] = java.lang.Math.fma(rightData[2], sd[5], java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        float _buf1 = java.lang.Math.fma(rightData[5], sd[4], java.lang.Math.fma(rightData[3], sd[0], rightData[4] * sd[2]));
        dd[4] = java.lang.Math.fma(rightData[5], sd[5], java.lang.Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        float _buf2 = java.lang.Math.fma(rightData[8], sd[4], java.lang.Math.fma(rightData[6], sd[0], rightData[7] * sd[2]));
        dd[7] = java.lang.Math.fma(rightData[8], sd[5], java.lang.Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
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
    private Float3x3 mul_general_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = ((rightData[2]) * (sd[4]) + (((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]))));
        dd[1] = ((rightData[2]) * (sd[5]) + (((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]))));
        float _buf1 = ((rightData[5]) * (sd[4]) + (((rightData[3]) * (sd[0]) + (rightData[4] * sd[2]))));
        dd[4] = ((rightData[5]) * (sd[5]) + (((rightData[3]) * (sd[1]) + (rightData[4] * sd[3]))));
        float _buf2 = ((rightData[8]) * (sd[4]) + (((rightData[6]) * (sd[0]) + (rightData[7] * sd[2]))));
        dd[7] = ((rightData[8]) * (sd[5]) + (((rightData[6]) * (sd[1]) + (rightData[7] * sd[3]))));
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
    private Float3x3 mul_translation_fma(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[2], sd[4], rightData[0]);
        dd[1] = java.lang.Math.fma(rightData[2], sd[5], rightData[1]);
        dd[3] = java.lang.Math.fma(rightData[5], sd[4], rightData[3]);
        dd[4] = java.lang.Math.fma(rightData[5], sd[5], rightData[4]);
        dd[6] = java.lang.Math.fma(rightData[8], sd[4], rightData[6]);
        dd[7] = java.lang.Math.fma(rightData[8], sd[5], rightData[7]);
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
    private Float3x3 mul_translation_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = ((rightData[2]) * (sd[4]) + (rightData[0]));
        dd[1] = ((rightData[2]) * (sd[5]) + (rightData[1]));
        dd[3] = ((rightData[5]) * (sd[4]) + (rightData[3]));
        dd[4] = ((rightData[5]) * (sd[5]) + (rightData[4]));
        dd[6] = ((rightData[8]) * (sd[4]) + (rightData[6]));
        dd[7] = ((rightData[8]) * (sd[5]) + (rightData[7]));
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
    private Float3x3 mul_orthogonal_fma(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(rightData[2], sd[4], java.lang.Math.fma(rightData[0], sd[3], rightData[1] * -sd[1]));
        dd[1] = java.lang.Math.fma(rightData[2], sd[5], java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        float _buf1 = java.lang.Math.fma(rightData[5], sd[4], java.lang.Math.fma(rightData[3], sd[3], rightData[4] * -sd[1]));
        dd[4] = java.lang.Math.fma(rightData[5], sd[5], java.lang.Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        float _buf2 = java.lang.Math.fma(rightData[8], sd[4], java.lang.Math.fma(rightData[6], sd[3], rightData[7] * -sd[1]));
        dd[7] = java.lang.Math.fma(rightData[8], sd[5], java.lang.Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_mulAdd(Float3x3R right, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = ((rightData[2]) * (sd[4]) + (((rightData[0]) * (sd[3]) - (rightData[1] * sd[1]))));
        dd[1] = ((rightData[2]) * (sd[5]) + (((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]))));
        float _buf1 = ((rightData[5]) * (sd[4]) + (((rightData[3]) * (sd[3]) - (rightData[4] * sd[1]))));
        dd[4] = ((rightData[5]) * (sd[5]) + (((rightData[3]) * (sd[1]) + (rightData[4] * sd[3]))));
        float _buf2 = ((rightData[8]) * (sd[4]) + (((rightData[6]) * (sd[3]) - (rightData[7] * sd[1]))));
        dd[7] = ((rightData[8]) * (sd[5]) + (((rightData[6]) * (sd[1]) + (rightData[7] * sd[3]))));
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
    private Float3x3 mul_orthogonal_translation_fma(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        float _buf0 = java.lang.Math.fma(rightData[6], sd[0], java.lang.Math.fma(rightData[7], sd[2], sd[4]));
        dd[7] = java.lang.Math.fma(rightData[6], sd[1], java.lang.Math.fma(rightData[7], sd[3], sd[5]));
        dd[8] = 1.0f;
        dd[6] = _buf0;
        ((Float3x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation_mulAdd(Float3x3R right, @Mutated Float3x3 dest, int _props) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0f;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0f;
        float _buf0 = ((rightData[6]) * (sd[0]) + (((rightData[7]) * (sd[2]) + (sd[4]))));
        dd[7] = ((rightData[6]) * (sd[1]) + (((rightData[7]) * (sd[3]) + (sd[5]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[2], sd[4], java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]));
        dd[1] = java.lang.Math.fma(rightData[2], sd[5], java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]));
        dd[2] = rightData[2];
        dd[3] = java.lang.Math.fma(rightData[5], sd[4], java.lang.Math.fma(rightData[3], sd[0], rightData[4] * sd[2]));
        dd[4] = java.lang.Math.fma(rightData[5], sd[5], java.lang.Math.fma(rightData[3], sd[1], rightData[4] * sd[3]));
        dd[5] = rightData[5];
        dd[6] = java.lang.Math.fma(rightData[8], sd[4], java.lang.Math.fma(rightData[6], sd[0], rightData[7] * sd[2]));
        dd[7] = java.lang.Math.fma(rightData[8], sd[5], java.lang.Math.fma(rightData[6], sd[1], rightData[7] * sd[3]));
        dd[8] = rightData[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 mul_mulAdd(Float3x3R right, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = ((rightData[2]) * (sd[4]) + (((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]))));
        dd[1] = ((rightData[2]) * (sd[5]) + (((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]))));
        dd[2] = rightData[2];
        dd[3] = ((rightData[5]) * (sd[4]) + (((rightData[3]) * (sd[0]) + (rightData[4] * sd[2]))));
        dd[4] = ((rightData[5]) * (sd[5]) + (((rightData[3]) * (sd[1]) + (rightData[4] * sd[3]))));
        dd[5] = rightData[5];
        dd[6] = ((rightData[8]) * (sd[4]) + (((rightData[6]) * (sd[0]) + (rightData[7] * sd[2]))));
        dd[7] = ((rightData[8]) * (sd[5]) + (((rightData[6]) * (sd[1]) + (rightData[7] * sd[3]))));
        dd[8] = rightData[8];
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        float _buf2 = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[2], sd[5], otherData[4]));
        dd[0] = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[5]));
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
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
    private Float2x3 preMul_general_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((otherData[1]) * (sd[0]) + (otherData[3] * sd[1]));
        float _buf1 = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        float _buf2 = ((otherData[0]) * (sd[4]) + (((otherData[2]) * (sd[5]) + (otherData[4]))));
        dd[0] = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[5] = ((otherData[1]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[5]))));
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3] * sd[3]));
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
    private Float2x3 preMul_translation_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[2], sd[5], otherData[4]));
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[5]));
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[4]) + (((otherData[2]) * (sd[5]) + (otherData[4]))));
        dd[5] = ((otherData[1]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[5]))));
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_fma(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[3], otherData[2] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[1], sd[3], otherData[3] * sd[1]);
        float _buf2 = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[2], sd[5], otherData[4]));
        dd[2] = java.lang.Math.fma(otherData[2], sd[3], otherData[0] * -sd[1]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[5]));
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], otherData[1] * -sd[1]);
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
    private Float2x3 preMul_orthogonal_mulAdd(Float2x3R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x3Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[3]) + (otherData[2] * sd[1]));
        float _buf1 = ((otherData[1]) * (sd[3]) + (otherData[3] * sd[1]));
        float _buf2 = ((otherData[0]) * (sd[4]) + (((otherData[2]) * (sd[5]) + (otherData[4]))));
        dd[2] = ((otherData[2]) * (sd[3]) - (otherData[0] * sd[1]));
        dd[5] = ((otherData[1]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[5]))));
        dd[3] = ((otherData[3]) * (sd[3]) - (otherData[1] * sd[1]));
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
        float[] otherData = ((Float2x3Impl) other).data;
        return preMul(otherData[0], otherData[2], otherData[4], otherData[1], otherData[3], otherData[5], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
        dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
        float _buf1 = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
        dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
        float _buf2 = java.lang.Math.fma(m00, sd[4], java.lang.Math.fma(m01, sd[5], m02));
        dd[5] = java.lang.Math.fma(m10, sd[4], java.lang.Math.fma(m11, sd[5], m12));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float2x3 preMul_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((m00) * (sd[0]) + (m01 * sd[1]));
        dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
        float _buf1 = ((m00) * (sd[2]) + (m01 * sd[3]));
        dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
        float _buf2 = ((m00) * (sd[4]) + (((m01) * (sd[5]) + (m02))));
        dd[5] = ((m10) * (sd[4]) + (((m11) * (sd[5]) + (m12))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
            dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
            dd[2] = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
            dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
            dd[4] = java.lang.Math.fma(m00, sd[4], java.lang.Math.fma(m01, sd[5], m02));
            dd[5] = java.lang.Math.fma(m10, sd[4], java.lang.Math.fma(m11, sd[5], m12));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((m00) * (sd[0]) + (m01 * sd[1]));
            dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
            dd[2] = ((m00) * (sd[2]) + (m01 * sd[3]));
            dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
            dd[4] = ((m00) * (sd[4]) + (((m01) * (sd[5]) + (m02))));
            dd[5] = ((m10) * (sd[4]) + (((m11) * (sd[5]) + (m12))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_fma(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        float _buf0 = java.lang.Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_translation_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
        float _buf0 = ((otherData[0]) * (sd[4]) + (otherData[2] * sd[5]));
        dd[5] = ((otherData[1]) * (sd[4]) + (otherData[3] * sd[5]));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_fma(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[0], sd[3], otherData[2] * sd[1]);
        float _buf0 = java.lang.Math.fma(otherData[1], sd[3], otherData[3] * sd[1]);
        dd[2] = java.lang.Math.fma(otherData[2], sd[3], otherData[0] * -sd[1]);
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], otherData[1] * -sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_orthogonal_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = ((otherData[0]) * (sd[3]) + (otherData[2] * sd[1]));
        float _buf0 = ((otherData[1]) * (sd[3]) + (otherData[3] * sd[1]));
        dd[2] = ((otherData[2]) * (sd[3]) - (otherData[0] * sd[1]));
        dd[3] = ((otherData[3]) * (sd[3]) - (otherData[1] * sd[1]));
        float _buf1 = ((otherData[0]) * (sd[4]) + (otherData[2] * sd[5]));
        dd[5] = ((otherData[1]) * (sd[4]) + (otherData[3] * sd[5]));
        dd[1] = _buf0;
        dd[4] = _buf1;
        ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_fma(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = java.lang.Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        float _buf2 = java.lang.Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float2x3 preMul_general_mulAdd(Float2x2R other, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[1] = ((otherData[1]) * (sd[0]) + (otherData[3] * sd[1]));
        float _buf1 = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3] * sd[3]));
        float _buf2 = ((otherData[0]) * (sd[4]) + (otherData[2] * sd[5]));
        dd[5] = ((otherData[1]) * (sd[4]) + (otherData[3] * sd[5]));
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
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? preMul_general_fma(other, dest) : preMul_general_mulAdd(other, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] otherData = ((Float2x2Impl) other).data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            ((Float2x3Impl) dest).properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
            return dest;
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
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] otherData = ((Float2x2Impl) other).data;
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
                this.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_fma(other, this);
            return preMul_orthogonal_fma(other, this);
        } else {
            if (Joml.RETURN_NEW) return preMul(other, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preMul_general_mulAdd(other, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] otherData = ((Float2x2Impl) other).data;
                float[] dd = this.data;
                FloatVector.fromArray(COL_SPECIES, otherData, 0).intoArray(dd, 0);
                this.properties = (((Float2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
                return this;
            }
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[1] = java.lang.Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        dd[4] = java.lang.Math.fma(otherData[0], sd[4], otherData[2] * sd[5]);
        dd[5] = java.lang.Math.fma(otherData[1], sd[4], otherData[3] * sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 preMul_mulAdd(Float2x2R other, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[1] = ((otherData[1]) * (sd[0]) + (otherData[3] * sd[1]));
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3] * sd[3]));
        dd[4] = ((otherData[0]) * (sd[4]) + (otherData[2] * sd[5]));
        dd[5] = ((otherData[1]) * (sd[4]) + (otherData[3] * sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_fma(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        float _buf2 = java.lang.Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        dd[6] = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[6]));
        dd[3] = java.lang.Math.fma(otherData[0], sd[2], otherData[3] * sd[3]);
        dd[7] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[4], sd[5], otherData[7]));
        dd[4] = java.lang.Math.fma(otherData[1], sd[2], otherData[4] * sd[3]);
        dd[8] = java.lang.Math.fma(otherData[2], sd[4], java.lang.Math.fma(otherData[5], sd[5], otherData[8]));
        dd[5] = java.lang.Math.fma(otherData[2], sd[2], otherData[5] * sd[3]);
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
    private Float3x3 preMul_general_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[0]) + (otherData[3] * sd[1]));
        float _buf1 = ((otherData[1]) * (sd[0]) + (otherData[4] * sd[1]));
        float _buf2 = ((otherData[2]) * (sd[0]) + (otherData[5] * sd[1]));
        dd[6] = ((otherData[0]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[6]))));
        dd[3] = ((otherData[0]) * (sd[2]) + (otherData[3] * sd[3]));
        dd[7] = ((otherData[1]) * (sd[4]) + (((otherData[4]) * (sd[5]) + (otherData[7]))));
        dd[4] = ((otherData[1]) * (sd[2]) + (otherData[4] * sd[3]));
        dd[8] = ((otherData[2]) * (sd[4]) + (((otherData[5]) * (sd[5]) + (otherData[8]))));
        dd[5] = ((otherData[2]) * (sd[2]) + (otherData[5] * sd[3]));
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
    private Float3x3 preMul_translation_fma(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[6] = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[6]));
        dd[7] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[4], sd[5], otherData[7]));
        dd[8] = java.lang.Math.fma(otherData[2], sd[4], java.lang.Math.fma(otherData[5], sd[5], otherData[8]));
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
    private Float3x3 preMul_translation_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        dd[6] = ((otherData[0]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[6]))));
        dd[7] = ((otherData[1]) * (sd[4]) + (((otherData[4]) * (sd[5]) + (otherData[7]))));
        dd[8] = ((otherData[2]) * (sd[4]) + (((otherData[5]) * (sd[5]) + (otherData[8]))));
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
    private Float3x3 preMul_orthogonal_fma(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = java.lang.Math.fma(otherData[0], sd[3], otherData[3] * sd[1]);
        float _buf1 = java.lang.Math.fma(otherData[1], sd[3], otherData[4] * sd[1]);
        float _buf2 = java.lang.Math.fma(otherData[2], sd[3], otherData[5] * sd[1]);
        dd[6] = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[6]));
        dd[3] = java.lang.Math.fma(otherData[3], sd[3], otherData[0] * -sd[1]);
        dd[7] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[4], sd[5], otherData[7]));
        dd[4] = java.lang.Math.fma(otherData[4], sd[3], otherData[1] * -sd[1]);
        dd[8] = java.lang.Math.fma(otherData[2], sd[4], java.lang.Math.fma(otherData[5], sd[5], otherData[8]));
        dd[5] = java.lang.Math.fma(otherData[5], sd[3], otherData[2] * -sd[1]);
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
    private Float3x3 preMul_orthogonal_mulAdd(Float3x3R other, @Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _buf0 = ((otherData[0]) * (sd[3]) + (otherData[3] * sd[1]));
        float _buf1 = ((otherData[1]) * (sd[3]) + (otherData[4] * sd[1]));
        float _buf2 = ((otherData[2]) * (sd[3]) + (otherData[5] * sd[1]));
        dd[6] = ((otherData[0]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[6]))));
        dd[3] = ((otherData[3]) * (sd[3]) - (otherData[0] * sd[1]));
        dd[7] = ((otherData[1]) * (sd[4]) + (((otherData[4]) * (sd[5]) + (otherData[7]))));
        dd[4] = ((otherData[4]) * (sd[3]) - (otherData[1] * sd[1]));
        dd[8] = ((otherData[2]) * (sd[4]) + (((otherData[5]) * (sd[5]) + (otherData[8]))));
        dd[5] = ((otherData[5]) * (sd[3]) - (otherData[2] * sd[1]));
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
                float[] sd = this.data;
                float[] dd = ((Float3x3Impl) dest).data;
                VEC_5.intoArray(dd, 0);
                dd[4] = 1.0f;
                dd[5] = 0.0f;
                dd[6] = sd[4];
                dd[7] = sd[5];
                dd[8] = 1.0f;
                ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
                return dest;
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
                float[] sd = this.data;
                float[] dd = ((Float3x3Impl) dest).data;
                VEC_5.intoArray(dd, 0);
                dd[4] = 1.0f;
                dd[5] = 0.0f;
                dd[6] = sd[4];
                dd[7] = sd[5];
                dd[8] = 1.0f;
                ((Float3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Float3x3Impl) other).properties;
                return dest;
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[0], sd[0], otherData[3] * sd[1]);
        dd[1] = java.lang.Math.fma(otherData[1], sd[0], otherData[4] * sd[1]);
        dd[2] = java.lang.Math.fma(otherData[2], sd[0], otherData[5] * sd[1]);
        dd[3] = java.lang.Math.fma(otherData[0], sd[2], otherData[3] * sd[3]);
        dd[4] = java.lang.Math.fma(otherData[1], sd[2], otherData[4] * sd[3]);
        dd[5] = java.lang.Math.fma(otherData[2], sd[2], otherData[5] * sd[3]);
        dd[6] = java.lang.Math.fma(otherData[0], sd[4], java.lang.Math.fma(otherData[3], sd[5], otherData[6]));
        dd[7] = java.lang.Math.fma(otherData[1], sd[4], java.lang.Math.fma(otherData[4], sd[5], otherData[7]));
        dd[8] = java.lang.Math.fma(otherData[2], sd[4], java.lang.Math.fma(otherData[5], sd[5], otherData[8]));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 preMul_mulAdd(Float3x3R other, @Mutated Double3x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = ((otherData[0]) * (sd[0]) + (otherData[3] * sd[1]));
        dd[1] = ((otherData[1]) * (sd[0]) + (otherData[4] * sd[1]));
        dd[2] = ((otherData[2]) * (sd[0]) + (otherData[5] * sd[1]));
        dd[3] = ((otherData[0]) * (sd[2]) + (otherData[3] * sd[3]));
        dd[4] = ((otherData[1]) * (sd[2]) + (otherData[4] * sd[3]));
        dd[5] = ((otherData[2]) * (sd[2]) + (otherData[5] * sd[3]));
        dd[6] = ((otherData[0]) * (sd[4]) + (((otherData[3]) * (sd[5]) + (otherData[6]))));
        dd[7] = ((otherData[1]) * (sd[4]) + (((otherData[4]) * (sd[5]) + (otherData[7]))));
        dd[8] = ((otherData[2]) * (sd[4]) + (((otherData[5]) * (sd[5]) + (otherData[8]))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] otherData = ((Float2x3Impl) other).data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherData[0], sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherData[1], sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherData[2], sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherData[3], sd[3]);
            dd[4] = java.lang.Math.fma(weight, otherData[4], sd[4]);
            dd[5] = java.lang.Math.fma(weight, otherData[5], sd[5]);
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
            return dest;
        } else {
            float[] sd = this.data;
            float[] otherData = ((Float2x3Impl) other).data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = ((weight) * (otherData[0]) + (sd[0]));
            dd[1] = ((weight) * (otherData[1]) + (sd[1]));
            dd[2] = ((weight) * (otherData[2]) + (sd[2]));
            dd[3] = ((weight) * (otherData[3]) + (sd[3]));
            dd[4] = ((weight) * (otherData[4]) + (sd[4]));
            dd[5] = ((weight) * (otherData[5]) + (sd[5]));
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Float2x3Impl) other).properties;
            return dest;
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
        float[] otherData = ((Float2x3Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[2];
        float m02 = otherData[4];
        float m10 = otherData[1];
        float m11 = otherData[3];
        float m12 = otherData[5];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m02, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m12, sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            dd[4] = ((weight) * (m02) + (sd[4]));
            dd[5] = ((weight) * (m12) + (sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m02, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m12, sd[5]);
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            dd[4] = ((weight) * (m02) + (sd[4]));
            dd[5] = ((weight) * (m12) + (sd[5]));
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m02, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m12, sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m01) + (sd[2]));
            dd[3] = ((weight) * (m11) + (sd[3]));
            dd[4] = ((weight) * (m02) + (sd[4]));
            dd[5] = ((weight) * (m12) + (sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] dd = this.data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
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
    @Mutated public Float2x3 makeScaling(Float2R v) {
        float[] vData = ((Float2Impl) v).data;
        float vY = vData[1];
        float[] dd = this.data;
        dd[0] = vData[0];
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
    @Mutated public Float2x3 makeTranslation(Float2R v) {
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
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
     * @param left the x coordinate of the left edge of the view rectangle
     * @param right the x coordinate of the right edge of the view rectangle
     * @param bottom the y coordinate of the bottom edge of the view rectangle
     * @param top the y coordinate of the top edge of the view rectangle
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
        dd[4] = -(left + right) * _t0_inv;
        dd[5] = -(bottom + top) * _t1_inv;
        this.properties = Joml.BIT_AFFINE;
        return this;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_orthogonal_general_fma(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        float _buf1 = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        float _buf2 = java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0);
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_orthogonal_general_mulAdd(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        float _buf1 = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        float _buf2 = ((sd[4]) * (_t0) - (sd[5] * _t1));
        dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float2x3 preRotate_translation_fma(float angle, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        float _buf0 = java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                dd[4] = 0.0f;
                dd[5] = 0.0f;
                ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
                return dest;
            }
            return preRotate_translation_fma(angle, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                dd[4] = 0.0f;
                dd[5] = 0.0f;
                ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
                return dest;
            }
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            float _buf0 = ((sd[4]) * (_t0) - (sd[5] * _t1));
            dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0));
            dd[4] = _buf0;
            ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
            return dest;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
            return preRotate_translation_fma(angle, this);
        } else {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preRotate_orthogonal_general_mulAdd(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
            float[] sd = this.data;
            float[] dd = this.data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            float _buf0 = ((sd[4]) * (_t0) - (sd[5] * _t1));
            dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0));
            dd[4] = _buf0;
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
            dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
            dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
            dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
            dd[4] = java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
            dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = ((sd[0]) * (_t0) - (sd[1] * _t1));
            dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
            dd[2] = ((sd[2]) * (_t0) - (sd[3] * _t1));
            dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
            dd[4] = ((sd[4]) * (_t0) - (sd[5] * _t1));
            dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (!org.joml2.internal.FmaSwitch.ON) return preRotateAround_mulAdd(angle, pivotX, pivotY, dest);
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        dd[4] = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
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
    @Mutated public Float2x3 preRotateAround(float angle, Float2R pivot) {
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
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
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        dd[4] = java.lang.Math.fma(pivotX, _t6, pivotY * _t1);
        dd[5] = java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float2x3 preRotateAround_identity_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        dd[4] = ((pivotX) * (_t6) + (pivotY * _t1));
        dd[5] = ((pivotY) * (_t6) - (pivotX * _t1));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_translation_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        float _buf0 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_translation_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        float _buf0 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((sd[4]) * (_t0) - (sd[5] * _t1));
        dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
        dd[4] = _buf0;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_orthogonal_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        float _buf1 = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        float _buf2 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
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
    private Float2x3 preRotateAround_orthogonal_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        float _buf1 = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        float _buf2 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((sd[4]) * (_t0) - (sd[5] * _t1));
        dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
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
    private Float2x3 preRotateAround_general_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        float _buf1 = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        float _buf2 = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        dd[0] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float2x3 preRotateAround_general_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        float _buf1 = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        float _buf2 = ((pivotX) * (_t6) + (pivotY * _t1)) + ((sd[4]) * (_t0) - (sd[5] * _t1));
        dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        dd[4] = java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        dd[5] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code preRotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 preRotateAround_mulAdd(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        dd[2] = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        dd[4] = ((pivotX) * (_t6) + (pivotY * _t1)) + ((sd[4]) * (_t0) - (sd[5] * _t1));
        dd[5] = ((sd[4]) * (_t1) + (sd[5] * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1));
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
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
    @Mutated public Float2x3 preScale(Float2R v) {
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return preScale_translation_self(vX, vY, this);
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return preScale_translation_self(vX, vY, this);
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScale_general(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return preScale_translation_self(s, this);
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
            dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
            dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_fma(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            float[] sd = this.data;
            float[] dd = this.data;
            float _t0 = 1.0f - s;
            dd[0] = s;
            dd[3] = s;
            dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
            dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
        dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_translation_self_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
        dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            float[] sd = this.data;
            float[] dd = this.data;
            float _t0 = 1.0f - s;
            dd[0] = s;
            dd[3] = s;
            dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
            dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
            dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
            dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        float sX = sData[0];
        float sY = sData[1];
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        float sX = sData[0];
        float sY = sData[1];
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sX * sd[0];
            dd[1] = sY * sd[1];
            dd[2] = sX * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = java.lang.Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
            dd[5] = java.lang.Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sX * sd[0];
            dd[1] = sY * sd[1];
            dd[2] = sX * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = ((pivotX) * (1.0f - sX) + (sX * sd[4]));
            dd[5] = ((pivotY) * (1.0f - sY) + (sY * sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        float sX = sData[0];
        float sY = sData[1];
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = java.lang.Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float2x3 preScaleAround_general_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = ((pivotX) * (1.0f - sX) + (sX * sd[4]));
        dd[5] = ((pivotY) * (1.0f - sY) + (sY * sd[5]));
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
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sX * sd[0];
            dd[1] = sY * sd[1];
            dd[2] = sX * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = java.lang.Math.fma(pivotX, 1.0f - sX, sX * sd[4]);
            dd[5] = java.lang.Math.fma(pivotY, 1.0f - sY, sY * sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sX * sd[0];
            dd[1] = sY * sd[1];
            dd[2] = sX * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = ((pivotX) * (1.0f - sX) + (sX * sd[4]));
            dd[5] = ((pivotY) * (1.0f - sY) + (sY * sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = vX;
            dd[5] = vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
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
    @Mutated public Float2x3 preTranslate(Float2R v) {
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preTranslate_general_self(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        return preTranslate_orthogonal_self(vX, vY, this);
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
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = vX;
            dd[5] = vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return preTranslate_general_self(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[4] = vX;
            dd[5] = vY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        return preTranslate_orthogonal_self(vX, vY, this);
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
    private Float2x3 rotate_orthogonal_general_fma(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        float _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float2x3 rotate_orthogonal_general_mulAdd(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        float _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
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
    private Float2x3 rotate_orthogonal_general_self_fma(float angle, @Mutated Float2x3 dest, int _props) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        float _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
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
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return rotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                dd[4] = 0.0f;
                dd[5] = 0.0f;
                ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
                return dest;
            }
            return rotate_translation(angle, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return rotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2x3Impl) dest).data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                dd[4] = 0.0f;
                dd[5] = 0.0f;
                ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
                return dest;
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
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
                this.properties = Joml.BIT_ORTHOGONAL;
                return this;
            }
        } else {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
                int _props = (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE;
                float[] sd = this.data;
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
                float _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
                dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
                dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
                dd[0] = _buf0;
                dd[1] = _buf1;
                this.properties = _props;
                return this;
            }
            {
                float[] dd = this.data;
                float _t0 = Math.cosWithSin(angle);
                float _t1 = Math.sinWithCos(angle);
                dd[0] = _t0;
                dd[1] = _t1;
                dd[2] = -_t1;
                dd[3] = _t0;
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
            dd[1] = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
            dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
            dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
            dd[4] = sd[4];
            dd[5] = sd[5];
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dd[0] = ((sd[0]) * (_t0) + (sd[2] * _t1));
            dd[1] = ((sd[1]) * (_t0) + (sd[3] * _t1));
            dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
            dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
            dd[4] = sd[4];
            dd[5] = sd[5];
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAround_mulAdd(angle, pivotX, pivotY, dest);
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dd[0] = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        dd[1] = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[4] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(sd[2], _t11, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], _t10, java.lang.Math.fma(sd[3], _t11, sd[5]));
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
    @Mutated public Float2x3 rotateAround(float angle, Float2R pivot) {
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        dd[4] = java.lang.Math.fma(pivotX, _t6, java.lang.Math.fma(pivotY, _t1, sd[4]));
        dd[5] = java.lang.Math.fma(pivotY, _t6, java.lang.Math.fma(-pivotX, _t1, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_translation_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dd[0] = _t0;
        dd[1] = _t1;
        dd[2] = -_t1;
        dd[3] = _t0;
        dd[4] = ((pivotX) * (_t6) + (((pivotY) * (_t1) + (sd[4]))));
        dd[5] = ((pivotY) * (_t6) + (((sd[5]) - (pivotX) * (_t1))));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_orthogonal_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        float _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[4] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(sd[2], _t11, sd[4]));
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[5] = java.lang.Math.fma(sd[1], _t10, java.lang.Math.fma(sd[3], _t11, sd[5]));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_orthogonal_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        float _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[4] = ((sd[0]) * (_t10) + (((sd[2]) * (_t11) + (sd[4]))));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[5] = ((sd[1]) * (_t10) + (((sd[3]) * (_t11) + (sd[5]))));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_general_fma(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        float _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        float _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[4] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(sd[2], _t11, sd[4]));
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[5] = java.lang.Math.fma(sd[1], _t10, java.lang.Math.fma(sd[3], _t11, sd[5]));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float2x3 rotateAround_general_mulAdd(float angle, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        float _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        float _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[4] = ((sd[0]) * (_t10) + (((sd[2]) * (_t11) + (sd[4]))));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[5] = ((sd[1]) * (_t10) + (((sd[3]) * (_t11) + (sd[5]))));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
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
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dd[0] = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        dd[1] = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[4] = java.lang.Math.fma(sd[0], _t10, java.lang.Math.fma(sd[2], _t11, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], _t10, java.lang.Math.fma(sd[3], _t11, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code rotateAround} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 rotateAround_mulAdd(float angle, float pivotX, float pivotY, @Mutated Double2x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dd[0] = ((sd[0]) * (_t0) + (sd[2] * _t1));
        dd[1] = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
        dd[4] = ((sd[0]) * (_t10) + (((sd[2]) * (_t11) + (sd[4]))));
        dd[5] = ((sd[1]) * (_t10) + (((sd[3]) * (_t11) + (sd[5]))));
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
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
    @Mutated public Float2x3 scale(Float2R v) {
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.float2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return scale_general_self(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        float[] dd = this.data;
        dd[0] = vX;
        dd[3] = vY;
        this.properties = Joml.BIT_AFFINE;
        return this;
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return scale_general_self(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        float[] dd = this.data;
        dd[0] = vX;
        dd[3] = vY;
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) {
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return scale_general_self(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        float[] dd = this.data;
        dd[0] = s;
        dd[3] = s;
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = java.lang.Math.fma(sd[0], _t1, java.lang.Math.fma(sd[2], _t2, sd[4]));
            dd[5] = java.lang.Math.fma(sd[1], _t1, java.lang.Math.fma(sd[3], _t2, sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = ((sd[0]) * (_t1) + (((sd[2]) * (_t2) + (sd[4]))));
            dd[5] = ((sd[1]) * (_t1) + (((sd[3]) * (_t2) + (sd[5]))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] pivotData = ((Float2Impl) pivot).data;
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                float[] sd = this.data;
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = ((pivotX) * (_t0) + (sd[4]));
                dd[5] = ((pivotY) * (_t0) + (sd[5]));
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(pivotX, _t0, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, _t0, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = s;
        dd[4] = ((pivotX) * (_t0) + (sd[4]));
        dd[5] = ((pivotY) * (_t0) + (sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(pivotX, _t0, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, _t0, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_fma(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dd[4] = java.lang.Math.fma(sd[0], _t1, java.lang.Math.fma(sd[2], _t2, sd[4]));
        dd[0] = s * sd[0];
        dd[2] = s * sd[2];
        dd[5] = java.lang.Math.fma(sd[1], _t1, java.lang.Math.fma(sd[3], _t2, sd[5]));
        dd[1] = s * sd[1];
        dd[3] = s * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_mulAdd(float s, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dd[4] = ((sd[0]) * (_t1) + (((sd[2]) * (_t2) + (sd[4]))));
        dd[0] = s * sd[0];
        dd[2] = s * sd[2];
        dd[5] = ((sd[1]) * (_t1) + (((sd[3]) * (_t2) + (sd[5]))));
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = pivotX * _t0;
                dd[5] = pivotY * _t0;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                float[] sd = this.data;
                float[] dd = this.data;
                float _t0 = 1.0f - s;
                dd[0] = s;
                dd[3] = s;
                dd[4] = ((pivotX) * (_t0) + (sd[4]));
                dd[5] = ((pivotY) * (_t0) + (sd[5]));
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = java.lang.Math.fma(sd[0], _t1, java.lang.Math.fma(sd[2], _t2, sd[4]));
            dd[5] = java.lang.Math.fma(sd[1], _t1, java.lang.Math.fma(sd[3], _t2, sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t0 = 1.0f - s;
            float _t1 = pivotX * _t0;
            float _t2 = pivotY * _t0;
            dd[0] = s * sd[0];
            dd[1] = s * sd[1];
            dd[2] = s * sd[2];
            dd[3] = s * sd[3];
            dd[4] = ((sd[0]) * (_t1) + (((sd[2]) * (_t2) + (sd[4]))));
            dd[5] = ((sd[1]) * (_t1) + (((sd[3]) * (_t2) + (sd[5]))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        float sX = sData[0];
        float sY = sData[1];
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        return scaleAround(sData[0], sData[1], pivotData[0], pivotData[1], dest);
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
        float[] sData = ((Float2Impl) s).data;
        float[] pivotData = ((Float2Impl) pivot).data;
        float sX = sData[0];
        float sY = sData[1];
        float pivotX = pivotData[0];
        float pivotY = pivotData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
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
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sY;
        dd[4] = java.lang.Math.fma(pivotX, 1.0f - sX, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0f - sY, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = sY;
        dd[4] = ((pivotX) * (1.0f - sX) + (sd[4]));
        dd[5] = ((pivotY) * (1.0f - sY) + (sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = java.lang.Math.fma(pivotX, 1.0f - sX, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0f - sY, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_translation_self_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = ((pivotX) * (1.0f - sX) + (sd[4]));
        dd[5] = ((pivotY) * (1.0f - sY) + (sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_fma(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dd[4] = java.lang.Math.fma(sd[0], _t2, java.lang.Math.fma(sd[2], _t3, sd[4]));
        dd[0] = sX * sd[0];
        dd[2] = sY * sd[2];
        dd[5] = java.lang.Math.fma(sd[1], _t2, java.lang.Math.fma(sd[3], _t3, sd[5]));
        dd[1] = sX * sd[1];
        dd[3] = sY * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float2x3 scaleAround_orthogonal_mulAdd(float sX, float sY, float pivotX, float pivotY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dd[4] = ((sd[0]) * (_t2) + (((sd[2]) * (_t3) + (sd[4]))));
        dd[0] = sX * sd[0];
        dd[2] = sY * sd[2];
        dd[5] = ((sd[1]) * (_t2) + (((sd[3]) * (_t3) + (sd[5]))));
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[0] = sX;
                dd[3] = sY;
                dd[4] = pivotX * (1.0f - sX);
                dd[5] = pivotY * (1.0f - sY);
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t2 = pivotX * (1.0f - sX);
            float _t3 = pivotY * (1.0f - sY);
            dd[0] = sX * sd[0];
            dd[1] = sX * sd[1];
            dd[2] = sY * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = java.lang.Math.fma(sd[0], _t2, java.lang.Math.fma(sd[2], _t3, sd[4]));
            dd[5] = java.lang.Math.fma(sd[1], _t2, java.lang.Math.fma(sd[3], _t3, sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            float _t2 = pivotX * (1.0f - sX);
            float _t3 = pivotY * (1.0f - sY);
            dd[0] = sX * sd[0];
            dd[1] = sX * sd[1];
            dd[2] = sY * sd[2];
            dd[3] = sY * sd[3];
            dd[4] = ((sd[0]) * (_t2) + (((sd[2]) * (_t3) + (sd[4]))));
            dd[5] = ((sd[1]) * (_t2) + (((sd[3]) * (_t3) + (sd[5]))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = vX;
            dd[5] = vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = sd[3];
            dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
            dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = sd[3];
            dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
            dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_fma(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            return translate_orthogonal_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_mulAdd(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            return translate_orthogonal_self_mulAdd(vX, vY, this);
        }
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_fma(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_self_fma(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_orthogonal_self_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        ((Float2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_fma(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_self_fma(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Float2x3 translate_general_self_mulAdd(float vX, float vY, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
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
        if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = vX;
            dd[5] = vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] sd = this.data;
            float[] dd = ((Float2x3Impl) dest).data;
            VEC_1.intoArray(dd, 0);
            dd[4] = sd[4] + vX;
            dd[5] = sd[5] + vY;
            ((Float2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
            return dest;
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
                float[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            return translate_orthogonal_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.float2x3());
            int p = this.properties;
            if ((p & Joml.BIT_ORTHOGONAL) != Joml.BIT_ORTHOGONAL) return translate_general_self_mulAdd(vX, vY, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            return translate_orthogonal_self_mulAdd(vX, vY, this);
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
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = sd[3];
            dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
            dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = sd[3];
            dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
            dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = java.lang.Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = java.lang.Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = ((sd[4]) - (left + right) * (_t0_inv));
        dd[5] = ((sd[5]) - (bottom + top) * (_t1_inv));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_self_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = java.lang.Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = java.lang.Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Float2x3 view_translation_self_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = ((sd[4]) - (left + right) * (_t0_inv));
        dd[5] = ((sd[5]) - (bottom + top) * (_t1_inv));
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_orthogonal_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = java.lang.Math.fma(sd[2], -_sp3, java.lang.Math.fma(-sd[0], _sp2, sd[4]));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = java.lang.Math.fma(sd[3], -_sp3, java.lang.Math.fma(-sd[1], _sp2, sd[5]));
        dd[1] = _sp0 * sd[1];
        dd[3] = _sp1 * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_orthogonal_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = ((((sd[4]) - (sd[0]) * (_sp2))) - (sd[2]) * (_sp3));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = ((((sd[5]) - (sd[1]) * (_sp2))) - (sd[3]) * (_sp3));
        dd[1] = _sp0 * sd[1];
        dd[3] = _sp1 * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_general_fma(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = sd[4] + java.lang.Math.fma(sd[2], -_sp3, -(sd[0] * _sp2));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = sd[5] + java.lang.Math.fma(sd[3], -_sp3, -(sd[1] * _sp2));
        dd[1] = _sp0 * sd[1];
        dd[3] = _sp1 * sd[3];
        ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float2x3 view_general_mulAdd(float left, float right, float bottom, float top, @Mutated Float2x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dd[4] = sd[4] + ((sd[2]) * (-_sp3) - (sd[0] * _sp2));
        dd[0] = _sp0 * sd[0];
        dd[2] = _sp1 * sd[2];
        dd[5] = sd[5] + ((sd[3]) * (-_sp3) - (sd[1] * _sp2));
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
            float[] dd = ((Float2x3Impl) dest).data;
            float _t0_inv = 1.0f / (right - left);
            float _t1_inv = 1.0f / (top - bottom);
            dd[0] = _t0_inv + _t0_inv;
            dd[1] = 0.0f;
            dd[2] = 0.0f;
            dd[3] = _t1_inv + _t1_inv;
            dd[4] = -(left + right) * _t0_inv;
            dd[5] = -(bottom + top) * _t1_inv;
            ((Float2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
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
                float[] dd = this.data;
                float _t0_inv = 1.0f / (right - left);
                float _t1_inv = 1.0f / (top - bottom);
                dd[0] = _t0_inv + _t0_inv;
                dd[3] = _t1_inv + _t1_inv;
                dd[4] = -(left + right) * _t0_inv;
                dd[5] = -(bottom + top) * _t1_inv;
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
                float[] dd = this.data;
                float _t0_inv = 1.0f / (right - left);
                float _t1_inv = 1.0f / (top - bottom);
                dd[0] = _t0_inv + _t0_inv;
                dd[3] = _t1_inv + _t1_inv;
                dd[4] = -(left + right) * _t0_inv;
                dd[5] = -(bottom + top) * _t1_inv;
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
        dd[4] = sd[4] + java.lang.Math.fma(sd[2], -_sp3, -(sd[0] * _sp2));
        dd[5] = sd[5] + java.lang.Math.fma(sd[3], -_sp3, -(sd[1] * _sp2));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code view} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 view_mulAdd(float left, float right, float bottom, float top, @Mutated Double2x3 dest) {
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
        dd[4] = sd[4] + ((sd[2]) * (-_sp3) - (sd[0] * _sp2));
        dd[5] = sd[5] + ((sd[3]) * (-_sp3) - (sd[1] * _sp2));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_fma(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            return mul_translation_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[4], vZ, java.lang.Math.fma(sd[0], vX, sd[2] * vY));
            dd[1] = java.lang.Math.fma(sd[5], vZ, java.lang.Math.fma(sd[1], vX, sd[3] * vY));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[4]) * (vZ) + (((sd[0]) * (vX) + (sd[2] * vY))));
            dd[1] = ((sd[5]) * (vZ) + (((sd[1]) * (vX) + (sd[3] * vY))));
            return dest;
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_translation_fma(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[4], vZ, vX);
        dd[1] = java.lang.Math.fma(sd[5], vZ, vY);
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_translation_mulAdd(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[4]) * (vZ) + (vX));
        dd[1] = ((sd[5]) * (vZ) + (vY));
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_fma(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[4], vZ, java.lang.Math.fma(sd[0], vX, sd[2] * vY));
        dd[1] = java.lang.Math.fma(sd[5], vZ, java.lang.Math.fma(sd[1], vX, sd[3] * vY));
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float2 mul_general_mulAdd(float vX, float vY, float vZ, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[4]) * (vZ) + (((sd[0]) * (vX) + (sd[2] * vY))));
        dd[1] = ((sd[5]) * (vZ) + (((sd[1]) * (vX) + (sd[3] * vY))));
        return dest;
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
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            return mul_translation_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return mul_general_mulAdd(vX, vY, vZ, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                float[] dd = ((Float2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
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
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[4], vZ, java.lang.Math.fma(sd[0], vX, sd[2] * vY));
            dd[1] = java.lang.Math.fma(sd[5], vZ, java.lang.Math.fma(sd[1], vX, sd[3] * vY));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[4]) * (vZ) + (((sd[0]) * (vX) + (sd[2] * vY))));
            dd[1] = ((sd[5]) * (vZ) + (((sd[1]) * (vX) + (sd[3] * vY))));
            return dest;
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
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
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general_fma(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
        dd[1] = ((sd[1]) * (vX) + (sd[3] * vY));
        return dest;
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
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? transformPosition_general_fma(vX, vY, dest) : transformPosition_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        return transformPosition_translation(vX, vY, dest);
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
        float[] vData = ((Float2Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
            dd[1] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
            dd[1] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
            return dest;
        }
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
    private Float2 transformPosition_general_fma(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[1] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        return dest;
    }

    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general_mulAdd(float vX, float vY, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[1] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        return dest;
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
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) != Joml.BIT_TRANSLATION) return (org.joml2.internal.FmaSwitch.ON ? transformPosition_general_fma(vX, vY, dest) : transformPosition_general_mulAdd(vX, vY, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float[] dd = ((Float2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        return transformPosition_translation(vX, vY, dest);
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
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
            dd[1] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
            dd[1] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
            return dest;
        }
    }

    public float m00() { return data[0]; }
    public float m01() { return data[2]; }
    public float m02() { return data[4]; }
    public float m10() { return data[1]; }
    public float m11() { return data[3]; }
    public float m12() { return data[5]; }

    @Override public String toString() {
        return "Float2x3(\n    " + this.data[0] + ", " + this.data[2] + ", " + this.data[4] + "\n    " + this.data[1] + ", " + this.data[3] + ", " + this.data[5] + "\n)";
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
        float[] otherData = ((Float2x3Impl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon;
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

    @Mutated public Float2x3 loadCM(float[] src, int offset) {
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
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }

    @Mutated public Float2x3 loadCM(double[] src, int offset) {
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
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
        return dest;
    }

    @Mutated public Float2x3 loadRM(double[] src, int offset) {
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
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        dest[_p2] = this.data[4];
        dest[_p2 + 1] = this.data[5];
        return dest;
    }

    @Mutated public Float2x3 loadCM(float[] src, int offset, int stride) {
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
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        dest[_p2] = this.data[4];
        dest[_p2 + 1] = this.data[5];
        return dest;
    }

    @Mutated public Float2x3 loadCM(double[] src, int offset, int stride) {
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
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        dest[_p1 + 2] = this.data[5];
        return dest;
    }

    @Mutated public Float2x3 loadRM(double[] src, int offset, int stride) {
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

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_1 = FloatVector.fromArray(COL_SPECIES, new float[] {1.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final FloatVector VEC_5 = FloatVector.fromArray(COL_SPECIES, new float[] {1.0f, 0.0f, 0.0f, 0.0f}, 0);
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
