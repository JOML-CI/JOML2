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
 * Generated implementation of {@link Double2x3} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x3Impl implements Double2x3 {

    public double[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x3SegOpsUnsafe()
                        : new Double2x3SegOpsMS();
        static final Double2x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x3BbOpsUnsafe()
                        : new Double2x3BbOpsApi();
        static final Double2x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x3RawOpsUnsafe()
                        : new Double2x3RawOpsApi();
    }

    public Double2x3Impl() {
        data = new double[6];
        data[0] = 1;
        data[3] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Double2x3Impl(double m00, double m01, double m02, double m10, double m11, double m12) {
        double[] dd = this.data = new double[6];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
        dd[4] = m02;
        dd[5] = m12;
        this.properties = determineProperties();
    }

    public Double2x3Impl(Double2x3R src) {
        Double2x3Impl s = (Double2x3Impl) src;
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
    private Double2 getColumn_identity(int col, @Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
            case 2: _idxSw0 = 0.0; _idxSw1 = 0.0; break;
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
    private Double2 getColumn_translation(int col, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw2;
        double _idxSw3;
        switch (col) {
            case 0: _idxSw2 = 1.0; _idxSw3 = 0.0; break;
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; break;
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
    private Double2 getColumn_general(int col, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw4;
        double _idxSw5;
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
    public Double2 getColumn(int col, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getColumn_translation(col, dest);
        return getColumn_general(col, dest);
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
    public double getRotationAngle() {
        double[] sd = this.data;
        return Math.atan2(sd[1], sd[0]);
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_identity(int row, @Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_translation(int row, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw2;
        double _idxSw3;
        double _idxSw4;
        switch (row) {
            case 0: _idxSw2 = 1.0; _idxSw3 = 0.0; _idxSw4 = sd[4]; break;
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; _idxSw4 = sd[5]; break;
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
    private Double3 getRow_general(int row, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw5;
        double _idxSw6;
        double _idxSw7;
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
    public Double3 getRow(int row, @Mutated Double3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getRow_identity(row, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getRow_translation(row, dest);
        return getRow_general(row, dest);
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Double2 getTranslation_identity(@Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Double2 getTranslation_general(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
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
    public Double2 getTranslation(@Mutated Double2 dest) {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getTranslation_identity(dest);
        return getTranslation_general(dest);
    }


    /**
     * Compute the determinant of the linear part (the upper-left square block) of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of the linear part (the upper-left square block) of this matrix
     */
    public double determinant() {
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        } else {
            double[] sd = this.data;
            return ((sd[0]) * (sd[3]) - (sd[2] * sd[1]));
        }
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
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[4], sd[4], java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]))))));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (((sd[1]) * (sd[1]) + (((sd[4]) * (sd[4]) + (((sd[0]) * (sd[0]) + (sd[2] * sd[2])))))))))));
        }
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Double2x3 invert_identity(@Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Double2x3 invert_translation(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties.
     * Shared by 2 identical private paths of {@code invert}; reached only through it.
     */
    private Double2x3 invert_translation_self(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_orthogonal_fma(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = java.lang.Math.fma(-_rd2, _rd1, -(_rd0 * _rd3));
        dd[5] = java.lang.Math.fma(_rd2, _rd0, -(_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_orthogonal_mulAdd(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = ((-_rd2) * (_rd1) - (_rd0 * _rd3));
        dd[5] = ((_rd2) * (_rd0) - (_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_orthogonal_self_fma(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[4] = java.lang.Math.fma(-_rd2, _rd1, -(_rd0 * _rd3));
        dd[5] = java.lang.Math.fma(_rd2, _rd0, -(_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_orthogonal_self_mulAdd(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[4] = ((-_rd2) * (_rd1) - (_rd0 * _rd3));
        dd[5] = ((_rd2) * (_rd0) - (_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_general_fma(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = java.lang.Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate_fma(dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(java.lang.Math.fma(_rd4, _rd3, -(_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(java.lang.Math.fma(_rd0, _rd5, -(_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_general_mulAdd(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = ((sd[0]) * (sd[3]) - (sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate_mulAdd(dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(((_rd4) * (_rd3) - (_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(((_rd0) * (_rd5) - (_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 invert(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? invert_orthogonal_fma(dest) : invert_orthogonal_mulAdd(dest));
        return (Math.useFma() ? invert_general_fma(dest) : invert_general_mulAdd(dest));
    }


    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x3 invert() {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return invert(Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self_fma(this);
            return invert_general_fma(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation_self(this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal_self_mulAdd(this);
            return invert_general_mulAdd(this);
        }
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN). Shared by the identical private paths of {@code invert} and {@code invertProduct};
     * reached only through them.
     */
    private Double2x3 invert_degenerate_orthogonal_general_fma(@Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t8 = sd[3] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[2] * _t1;
        double _t11 = sd[1] * _t0;
        double _t12 = sd[4] * _t1;
        double _t13 = sd[5] * _t0;
        double _t16_inv = 1.0 / java.lang.Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dd[5] = -(java.lang.Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN). Shared by the identical private paths of {@code invert} and {@code invertProduct};
     * reached only through them.
     */
    private Double2x3 invert_degenerate_orthogonal_general_mulAdd(@Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t8 = sd[3] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[2] * _t1;
        double _t11 = sd[1] * _t0;
        double _t12 = sd[4] * _t1;
        double _t13 = sd[5] * _t0;
        double _t16_inv = 1.0 / ((_t9) * (_t8) - (_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(((_t12) * (_t8) - (_t10 * _t13)) * _t16_inv);
        dd[5] = -(((_t9) * (_t13) - (_t12 * _t11)) * _t16_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_fma(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        return invert_degenerate_orthogonal_general_fma(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_mulAdd(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        return invert_degenerate_orthogonal_general_mulAdd(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_fma(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = java.lang.Math.fma(otherData[2], sd[1], otherData[3] * sd[3]);
        double _t7 = java.lang.Math.fma(otherData[0], sd[0], otherData[1] * sd[2]);
        double _t8 = java.lang.Math.fma(otherData[0], sd[1], otherData[1] * sd[3]);
        double _t9 = java.lang.Math.fma(otherData[2], sd[0], otherData[3] * sd[2]);
        double _t10 = java.lang.Math.fma(otherData[4], sd[0], java.lang.Math.fma(otherData[5], sd[2], sd[4]));
        double _t11 = java.lang.Math.fma(otherData[4], sd[1], java.lang.Math.fma(otherData[5], sd[3], sd[5]));
        double _t15 = java.lang.Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t15_inv = 1.0 / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(java.lang.Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dd[5] = -(java.lang.Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_mulAdd(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = ((otherData[2]) * (sd[1]) + (otherData[3] * sd[3]));
        double _t7 = ((otherData[0]) * (sd[0]) + (otherData[1] * sd[2]));
        double _t8 = ((otherData[0]) * (sd[1]) + (otherData[1] * sd[3]));
        double _t9 = ((otherData[2]) * (sd[0]) + (otherData[3] * sd[2]));
        double _t10 = ((otherData[4]) * (sd[0]) + (((otherData[5]) * (sd[2]) + (sd[4]))));
        double _t11 = ((otherData[4]) * (sd[1]) + (((otherData[5]) * (sd[3]) + (sd[5]))));
        double _t15 = ((_t7) * (_t6) - (_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t15_inv = 1.0 / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(((_t10) * (_t6) - (_t11 * _t9)) * _t15_inv);
        dd[5] = -(((_t11) * (_t7) - (_t10 * _t8)) * _t15_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_identity_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = java.lang.Math.fma(otherData[0], otherData[3], -(otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(java.lang.Math.fma(_rd4, _rd3, -(_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(java.lang.Math.fma(_rd0, _rd5, -(_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_identity_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = ((otherData[0]) * (otherData[3]) - (otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(((_rd4) * (_rd3) - (_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(((_rd0) * (_rd5) - (_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_translation_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t1 = otherData[4] + sd[4];
        double _t2 = otherData[5] + sd[5];
        double _t5 = java.lang.Math.fma(otherData[0], otherData[3], -(otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        dd[0] = _rd3 * _t5_inv;
        dd[1] = -(_rd1 * _t5_inv);
        dd[2] = -(_rd2 * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        dd[4] = -(java.lang.Math.fma(_rd3, _t1, -(_rd2 * _t2)) * _t5_inv);
        dd[5] = -(java.lang.Math.fma(_rd0, _t2, -(_rd1 * _t1)) * _t5_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t1 = otherData[4] + sd[4];
        double _t2 = otherData[5] + sd[5];
        double _t5 = ((otherData[0]) * (otherData[3]) - (otherData[2] * otherData[1]));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        dd[0] = _rd3 * _t5_inv;
        dd[1] = -(_rd1 * _t5_inv);
        dd[2] = -(_rd2 * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        dd[4] = -(((_rd3) * (_t1) - (_rd2 * _t2)) * _t5_inv);
        dd[5] = -(((_rd0) * (_t2) - (_rd1 * _t1)) * _t5_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_identity_identity(Double2x3R other, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_identity_translation(Double2x3R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = -otherData[4];
        dd[5] = -otherData[5];
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_translation_identity(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_translation_translation(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = -(otherData[4] + sd[4]);
        dd[5] = -(otherData[5] + sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_identity_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = java.lang.Math.fma(-_rd2, _rd1, -(_rd0 * _rd3));
        dd[5] = java.lang.Math.fma(_rd2, _rd0, -(_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_identity_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = -_rd0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = ((-_rd2) * (_rd1) - (_rd0 * _rd3));
        dd[5] = ((_rd2) * (_rd0) - (_rd1 * _rd3));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_translation_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = -sd[1];
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = _t0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = java.lang.Math.fma(_t0, _rd3, java.lang.Math.fma(-_rd2, _rd1, -otherData[4]));
        dd[5] = java.lang.Math.fma(_rd2, _rd0, java.lang.Math.fma(-_rd1, _rd3, -otherData[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = -sd[1];
        double _rd0 = sd[1];
        double _rd1 = sd[3];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = _rd1;
        dd[1] = _t0;
        dd[2] = _rd0;
        dd[3] = _rd1;
        dd[4] = ((_t0) * (_rd3) + (((-_rd2) * (_rd1) - (otherData[4]))));
        dd[5] = ((_rd2) * (_rd0) + (((-_rd1) * (_rd3) - (otherData[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_identity_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = java.lang.Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(java.lang.Math.fma(_rd4, _rd3, -(_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(java.lang.Math.fma(_rd0, _rd5, -(_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_identity_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t3 = ((sd[0]) * (sd[3]) - (sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd3 * _t3_inv;
        dd[1] = -(_rd1 * _t3_inv);
        dd[2] = -(_rd2 * _t3_inv);
        dd[3] = _rd0 * _t3_inv;
        dd[4] = -(((_rd4) * (_rd3) - (_rd2 * _rd5)) * _t3_inv);
        dd[5] = -(((_rd0) * (_rd5) - (_rd4 * _rd1)) * _t3_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_translation_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t5 = java.lang.Math.fma(otherData[4], sd[0], java.lang.Math.fma(otherData[5], sd[2], sd[4]));
        double _t6 = java.lang.Math.fma(otherData[4], sd[1], java.lang.Math.fma(otherData[5], sd[3], sd[5]));
        double _t7 = java.lang.Math.fma(sd[0], sd[3], -(sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd3 * _t7_inv;
        dd[1] = -(_rd1 * _t7_inv);
        dd[2] = -(_rd2 * _t7_inv);
        dd[3] = _rd0 * _t7_inv;
        dd[4] = -(java.lang.Math.fma(_rd3, _t5, -(_rd2 * _t6)) * _t7_inv);
        dd[5] = -(java.lang.Math.fma(_rd0, _t6, -(_rd1 * _t5)) * _t7_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t5 = ((otherData[4]) * (sd[0]) + (((otherData[5]) * (sd[2]) + (sd[4]))));
        double _t6 = ((otherData[4]) * (sd[1]) + (((otherData[5]) * (sd[3]) + (sd[5]))));
        double _t7 = ((sd[0]) * (sd[3]) - (sd[2] * sd[1]));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd3 * _t7_inv;
        dd[1] = -(_rd1 * _t7_inv);
        dd[2] = -(_rd2 * _t7_inv);
        dd[3] = _rd0 * _t7_inv;
        dd[4] = -(((_rd3) * (_t5) - (_rd2 * _t6)) * _t7_inv);
        dd[5] = -(((_rd0) * (_t6) - (_rd1 * _t5)) * _t7_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 invertProduct(Double2x3R other, @Mutated Double2x3 dest) {
        if (Math.useFma()) return invertProduct_fma(other, dest);
        return invertProduct_mulAdd(other, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_fma(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_fma(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_fma(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_fma(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation_fma(other, dest);
            return invertProduct_general_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma(other, dest);
        return invertProduct_general_fma(other, dest, Joml.BIT_AFFINE & q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_mulAdd(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation_mulAdd(other, dest);
            return invertProduct_general_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd(other, dest);
        return invertProduct_general_mulAdd(other, dest, Joml.BIT_AFFINE & q);
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
    @Mutated public Double2x3 invertProduct(Double2x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double2x3());
        if (Math.useFma()) return invertProduct_fma(other);
        return invertProduct_mulAdd(other);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_fma(Double2x3R other) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_fma(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_fma(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_fma(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation_fma(other, this);
            return invertProduct_general_fma(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma(other, this);
        return invertProduct_general_fma(other, this, Joml.BIT_AFFINE & q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_mulAdd(Double2x3R other) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_mulAdd(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_mulAdd(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity_mulAdd(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation_mulAdd(other, this);
            return invertProduct_general_mulAdd(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd(other, this);
        return invertProduct_general_mulAdd(other, this, Joml.BIT_AFFINE & q);
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
    public Double2x3 invertProduct(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        if (Math.useFma()) return invertProduct_fma(m00, m01, m02, m10, m11, m12, dest);
        return invertProduct_mulAdd(m00, m01, m02, m10, m11, m12, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_fma(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
        double _t7 = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
        double _t8 = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
        double _t9 = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
        double _t10 = java.lang.Math.fma(m02, sd[0], java.lang.Math.fma(m12, sd[2], sd[4]));
        double _t11 = java.lang.Math.fma(m02, sd[1], java.lang.Math.fma(m12, sd[3], sd[5]));
        double _t15 = java.lang.Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate_fma(m00, m01, m02, m10, m11, m12, dest);
        double _t15_inv = 1.0 / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(java.lang.Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dd[5] = -(java.lang.Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x3 invertProduct_mulAdd(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = ((m01) * (sd[1]) + (m11 * sd[3]));
        double _t7 = ((m00) * (sd[0]) + (m10 * sd[2]));
        double _t8 = ((m00) * (sd[1]) + (m10 * sd[3]));
        double _t9 = ((m01) * (sd[0]) + (m11 * sd[2]));
        double _t10 = ((m02) * (sd[0]) + (((m12) * (sd[2]) + (sd[4]))));
        double _t11 = ((m02) * (sd[1]) + (((m12) * (sd[3]) + (sd[5]))));
        double _t15 = ((_t7) * (_t6) - (_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(m00, m01, m02, m10, m11, m12, dest);
        double _t15_inv = 1.0 / _t15;
        dd[0] = _t6 * _t15_inv;
        dd[1] = -(_t8 * _t15_inv);
        dd[2] = -(_t9 * _t15_inv);
        dd[3] = _t7 * _t15_inv;
        dd[4] = -(((_t10) * (_t6) - (_t11 * _t9)) * _t15_inv);
        dd[5] = -(((_t11) * (_t7) - (_t10 * _t8)) * _t15_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_general_fma(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double _t6 = java.lang.Math.fma(otherData[2], sd[1], otherData[3] * sd[3]);
        double _t7 = java.lang.Math.fma(otherData[0], sd[1], otherData[1] * sd[3]);
        double _t8 = java.lang.Math.fma(otherData[0], sd[0], otherData[1] * sd[2]);
        double _t9 = java.lang.Math.fma(otherData[2], sd[0], otherData[3] * sd[2]);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t28_inv = 1.0 / java.lang.Math.fma(_t19, _t18, -(_t20 * _t21));
        return invertProduct_degenerate_general_s29739476_1_fma(dest, _props, ((Double2x3Impl) dest).data, _t18, _t19, _t20, _t21, java.lang.Math.fma(otherData[4], sd[0], java.lang.Math.fma(otherData[5], sd[2], sd[4])) * _t13, java.lang.Math.fma(otherData[4], sd[1], java.lang.Math.fma(otherData[5], sd[3], sd[5])) * _t12, _t28_inv, _t12 * _t28_inv, _t13 * _t28_inv);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_general_mulAdd(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double _t6 = ((otherData[2]) * (sd[1]) + (otherData[3] * sd[3]));
        double _t7 = ((otherData[0]) * (sd[1]) + (otherData[1] * sd[3]));
        double _t8 = ((otherData[0]) * (sd[0]) + (otherData[1] * sd[2]));
        double _t9 = ((otherData[2]) * (sd[0]) + (otherData[3] * sd[2]));
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t28_inv = 1.0 / ((_t19) * (_t18) - (_t20 * _t21));
        return invertProduct_degenerate_general_s29739476_1_mulAdd(dest, _props, ((Double2x3Impl) dest).data, _t18, _t19, _t20, _t21, ((otherData[4]) * (sd[0]) + (((otherData[5]) * (sd[2]) + (sd[4])))) * _t13, ((otherData[4]) * (sd[1]) + (((otherData[5]) * (sd[3]) + (sd[5])))) * _t12, _t28_inv, _t12 * _t28_inv, _t13 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_general_s29739476_1_fma(Double2x3 dest, int _props, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t24, double _t25, double _t28_inv, double _sp1, double _sp0) {
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dd[5] = -(java.lang.Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private Double2x3 invertProduct_degenerate_general_s29739476_1_mulAdd(Double2x3 dest, int _props, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t24, double _t25, double _t28_inv, double _sp1, double _sp0) {
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(((_t24) * (_t18) - (_t25 * _t21)) * _t28_inv);
        dd[5] = -(((_t25) * (_t19) - (_t24 * _t20)) * _t28_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_identity_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t8 = otherData[3] * _t0;
        double _t9 = otherData[0] * _t1;
        double _t10 = otherData[2] * _t1;
        double _t11 = otherData[1] * _t0;
        double _t12 = otherData[4] * _t1;
        double _t13 = otherData[5] * _t0;
        double _t16_inv = 1.0 / java.lang.Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dd[5] = -(java.lang.Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_identity_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t8 = otherData[3] * _t0;
        double _t9 = otherData[0] * _t1;
        double _t10 = otherData[2] * _t1;
        double _t11 = otherData[1] * _t0;
        double _t12 = otherData[4] * _t1;
        double _t13 = otherData[5] * _t0;
        double _t16_inv = 1.0 / ((_t9) * (_t8) - (_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(((_t12) * (_t8) - (_t10 * _t13)) * _t16_inv);
        dd[5] = -(((_t9) * (_t13) - (_t12 * _t11)) * _t16_inv);
        ((Double2x3Impl) dest).properties = ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_translation_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t8 = otherData[3] * _t2;
        double _t9 = otherData[0] * _t3;
        double _t10 = otherData[2] * _t3;
        double _t11 = otherData[1] * _t2;
        double _t14 = (otherData[4] + sd[4]) * _t3;
        double _t15 = (otherData[5] + sd[5]) * _t2;
        double _t18_inv = 1.0 / java.lang.Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t2 * _t18_inv;
        double _sp0 = _t3 * _t18_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t8, _t14, -(_t10 * _t15)) * _t18_inv);
        dd[5] = -(java.lang.Math.fma(_t9, _t15, -(_t11 * _t14)) * _t18_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t8 = otherData[3] * _t2;
        double _t9 = otherData[0] * _t3;
        double _t10 = otherData[2] * _t3;
        double _t11 = otherData[1] * _t2;
        double _t14 = (otherData[4] + sd[4]) * _t3;
        double _t15 = (otherData[5] + sd[5]) * _t2;
        double _t18_inv = 1.0 / ((_t9) * (_t8) - (_t10 * _t11));
        double _sp1 = _t2 * _t18_inv;
        double _sp0 = _t3 * _t18_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(((_t8) * (_t14) - (_t10 * _t15)) * _t18_inv);
        dd[5] = -(((_t9) * (_t15) - (_t11 * _t14)) * _t18_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_orthogonal_translation_fma(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t8 = sd[3] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[2] * _t1;
        double _t11 = sd[1] * _t0;
        double _t16 = java.lang.Math.fma(otherData[4], sd[0], java.lang.Math.fma(otherData[5], sd[2], sd[4])) * _t1;
        double _t17 = java.lang.Math.fma(otherData[4], sd[1], java.lang.Math.fma(otherData[5], sd[3], sd[5])) * _t0;
        double _t20_inv = 1.0 / java.lang.Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t20_inv;
        double _sp0 = _t1 * _t20_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t8, _t16, -(_t10 * _t17)) * _t20_inv);
        dd[5] = -(java.lang.Math.fma(_t9, _t17, -(_t11 * _t16)) * _t20_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_orthogonal_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t8 = sd[3] * _t0;
        double _t9 = sd[0] * _t1;
        double _t10 = sd[2] * _t1;
        double _t11 = sd[1] * _t0;
        double _t16 = ((otherData[4]) * (sd[0]) + (((otherData[5]) * (sd[2]) + (sd[4])))) * _t1;
        double _t17 = ((otherData[4]) * (sd[1]) + (((otherData[5]) * (sd[3]) + (sd[5])))) * _t0;
        double _t20_inv = 1.0 / ((_t9) * (_t8) - (_t10 * _t11));
        double _sp1 = _t0 * _t20_inv;
        double _sp0 = _t1 * _t20_inv;
        dd[0] = _t8 * _sp0;
        dd[1] = -(_t11 * _sp0);
        dd[2] = -(_t10 * _sp1);
        dd[3] = _t9 * _sp1;
        dd[4] = -(((_t8) * (_t16) - (_t10 * _t17)) * _t20_inv);
        dd[5] = -(((_t9) * (_t17) - (_t11 * _t16)) * _t20_inv);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_fma(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_degenerate_identity_fma(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_degenerate_translation_fma(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general_fma(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_general_fma(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general_fma(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_fma(other, dest, Joml.BIT_AFFINE & q);
        return invertProduct_degenerate_general_fma(other, dest, Joml.BIT_AFFINE & q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_degenerate_identity_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_degenerate_translation_mulAdd(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general_mulAdd(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_general_mulAdd(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_orthogonal_general_mulAdd(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_mulAdd(other, dest, Joml.BIT_AFFINE & q);
        return invertProduct_degenerate_general_mulAdd(other, dest, Joml.BIT_AFFINE & q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_fma(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
        double _t7 = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
        double _t8 = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
        double _t9 = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t24 = java.lang.Math.fma(m02, sd[0], java.lang.Math.fma(m12, sd[2], sd[4])) * _t13;
        double _t25 = java.lang.Math.fma(m02, sd[1], java.lang.Math.fma(m12, sd[3], sd[5])) * _t12;
        double _t28_inv = 1.0 / java.lang.Math.fma(_t19, _t18, -(_t20 * _t21));
        double _sp1 = _t12 * _t28_inv;
        double _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(java.lang.Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dd[5] = -(java.lang.Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_mulAdd(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t6 = ((m01) * (sd[1]) + (m11 * sd[3]));
        double _t7 = ((m00) * (sd[1]) + (m10 * sd[3]));
        double _t8 = ((m00) * (sd[0]) + (m10 * sd[2]));
        double _t9 = ((m01) * (sd[0]) + (m11 * sd[2]));
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t24 = ((m02) * (sd[0]) + (((m12) * (sd[2]) + (sd[4])))) * _t13;
        double _t25 = ((m02) * (sd[1]) + (((m12) * (sd[3]) + (sd[5])))) * _t12;
        double _t28_inv = 1.0 / ((_t19) * (_t18) - (_t20 * _t21));
        double _sp1 = _t12 * _t28_inv;
        double _sp0 = _t13 * _t28_inv;
        dd[0] = _t18 * _sp0;
        dd[1] = -(_t20 * _sp0);
        dd[2] = -(_t21 * _sp1);
        dd[3] = _t19 * _sp1;
        dd[4] = -(((_t24) * (_t18) - (_t25 * _t21)) * _t28_inv);
        dd[5] = -(((_t25) * (_t19) - (_t24 * _t20)) * _t28_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_identity(@Mutated Double3x2 dest) {
        double[] dd = ((Double3x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_translation(@Mutated Double3x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = sd[4];
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = sd[5];
        return dest;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_general(@Mutated Double3x2 dest) {
        double[] sd = this.data;
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
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 transpose(@Mutated Double3x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transpose_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transpose_translation(dest);
        return transpose_general(dest);
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
    public Double2x3 add(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 add(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    private Double2x3 mul_identity(double scalar, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = scalar;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_fma(double scalar, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = scalar;
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_mulAdd(double scalar, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = scalar;
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_self(double scalar, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = scalar;
        dd[3] = scalar;
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_fma(double scalar, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_mulAdd(double scalar, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    public Double2x3 mul(double scalar, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(scalar, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? mul_translation_fma(scalar, dest) : mul_translation_mulAdd(scalar, dest));
        return (Math.useFma() ? mul_general_fma(scalar, dest) : mul_general_mulAdd(scalar, dest));
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
    @Mutated public Double2x3 mul(double scalar) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
            return mul_general_fma(scalar, this);
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = Joml.BIT_AFFINE;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
            return mul_general_mulAdd(scalar, this);
        }
    }


    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 negate(@Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    public Double2x3 sub(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        dd[4] = sd[4] - otherData[4];
        dd[5] = sd[5] - otherData[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 sub(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    @Mutated public Double2x3 set(Double2x3R v) {
        double[] dd = this.data;
        double[] vData = ((Double2x3Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        dd[4] = vData[4];
        dd[5] = vData[5];
        this.properties = ((Double2x3Impl) v).properties;
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
    @Mutated public Double2x3 set(double m00, double m01, double m02, double m10, double m11, double m12) {
        double[] dd = this.data;
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
    @Mutated public Double2x3 set(Double2x2R m) {
        double[] dd = this.data;
        double[] mData = ((Double2x2Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[2];
        dd[3] = mData[3];
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    @Mutated public Double2x3 set(Double3x3R m) {
        double[] dd = this.data;
        double[] mData = ((Double3x3Impl) m).data;
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
    public Double2x3 withTranslation(Double2R t, @Mutated Double2x3 dest) {
        double tX = t.x();
        double tY = t.y();
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
     * @param t the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    public @Mutated Double2x3 withTranslation(Double2R t) {
        double tX = t.x();
        double tY = t.y();
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        double[] dd = this.data;
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
    private Double2x3 withTranslation_identity(double tX, double tY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = tX;
        dd[5] = tY;
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double2x3 withTranslation_orthogonal(double tX, double tY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = tX;
        dd[5] = tY;
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double2x3 withTranslation_general(double tX, double tY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x3 withTranslation(double tX, double tY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 withTranslation(double tX, double tY) {
        if (Joml.RETURN_NEW) return withTranslation(tX, tY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_TRANSLATION;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] dd = this.data;
            dd[4] = tX;
            dd[5] = tY;
            this.properties = Joml.BIT_ORTHOGONAL;
            return this;
        }
        double[] dd = this.data;
        dd[4] = tX;
        dd[5] = tY;
        this.properties = Joml.BIT_AFFINE;
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
    public Float2x3 toFloat(@Mutated Float2x3 dest) {
        double[] sd = this.data;
        float[] dd = ((Float2x3Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        ((Float2x3Impl) dest).properties = this.properties;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_identity(@Mutated Double2x2 dest) {
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_general(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 to2x2(@Mutated Double2x2 dest) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x2_identity(dest);
        return to2x2_general(dest);
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code to3x3} and {@code mul}; reached only through them.
     */
    private Double3x3 to3x3_orthogonal_general(@Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_identity(@Mutated Double3x3 dest) {
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_IDENTITY;
        return dest;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_translation(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION;
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
    public Double3x3 to3x3(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to3x3_translation(dest);
        return to3x3_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double2x3 makeIdentity() {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    public Double2x3 lerp(Double2x3R other, double t, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        ((Double2x3Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double2x3Impl) other).properties) >> 1);
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
    public Double2x3 lerp(double m00, double m01, double m02, double m10, double m11, double m12, double t, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
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
            double[] sd = this.data;
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
    private Double2x3 mul_general_fma(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = sd[0];
        double _rd7 = sd[1];
        double _rd8 = sd[2];
        double _rd9 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd6, _rd1 * _rd8);
        dd[1] = java.lang.Math.fma(_rd0, _rd7, _rd1 * _rd9);
        dd[2] = java.lang.Math.fma(_rd2, _rd6, _rd3 * _rd8);
        dd[3] = java.lang.Math.fma(_rd2, _rd7, _rd3 * _rd9);
        dd[4] = java.lang.Math.fma(_rd4, _rd6, java.lang.Math.fma(_rd5, _rd8, sd[4]));
        dd[5] = java.lang.Math.fma(_rd4, _rd7, java.lang.Math.fma(_rd5, _rd9, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_mulAdd(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = sd[0];
        double _rd7 = sd[1];
        double _rd8 = sd[2];
        double _rd9 = sd[3];
        dd[0] = ((_rd0) * (_rd6) + (_rd1 * _rd8));
        dd[1] = ((_rd0) * (_rd7) + (_rd1 * _rd9));
        dd[2] = ((_rd2) * (_rd6) + (_rd3 * _rd8));
        dd[3] = ((_rd2) * (_rd7) + (_rd3 * _rd9));
        dd[4] = ((_rd4) * (_rd6) + (((_rd5) * (_rd8) + (sd[4]))));
        dd[5] = ((_rd4) * (_rd7) + (((_rd5) * (_rd9) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_fma(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        dd[4] = rightData[4] + sd[4];
        dd[5] = rightData[5] + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_mulAdd(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        dd[4] = rightData[4] + sd[4];
        dd[5] = rightData[5] + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_fma(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = sd[1];
        double _rd7 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd7, -(_rd1 * _rd6));
        dd[1] = java.lang.Math.fma(_rd0, _rd6, _rd1 * _rd7);
        dd[2] = java.lang.Math.fma(_rd2, _rd7, -(_rd3 * _rd6));
        dd[3] = java.lang.Math.fma(_rd2, _rd6, _rd3 * _rd7);
        dd[4] = java.lang.Math.fma(-_rd5, _rd6, java.lang.Math.fma(_rd4, _rd7, sd[4]));
        dd[5] = java.lang.Math.fma(_rd4, _rd6, java.lang.Math.fma(_rd5, _rd7, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_mulAdd(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = sd[1];
        double _rd7 = sd[3];
        dd[0] = ((_rd0) * (_rd7) - (_rd1 * _rd6));
        dd[1] = ((_rd0) * (_rd6) + (_rd1 * _rd7));
        dd[2] = ((_rd2) * (_rd7) - (_rd3 * _rd6));
        dd[3] = ((_rd2) * (_rd6) + (_rd3 * _rd7));
        dd[4] = ((-_rd5) * (_rd6) + (((_rd4) * (_rd7) + (sd[4]))));
        dd[5] = ((_rd4) * (_rd6) + (((_rd5) * (_rd7) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_translation(Double2x3R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = rightData[4] + sd[4];
        dd[5] = rightData[5] + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_translation_fma(Double2x3R right, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = java.lang.Math.fma(_rd4, _rd0, java.lang.Math.fma(_rd5, _rd2, sd[4]));
        dd[5] = java.lang.Math.fma(_rd4, _rd1, java.lang.Math.fma(_rd5, _rd3, sd[5]));
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_translation_mulAdd(Double2x3R right, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x3Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = ((_rd4) * (_rd0) + (((_rd5) * (_rd2) + (sd[4]))));
        dd[5] = ((_rd4) * (_rd1) + (((_rd5) * (_rd3) + (sd[5]))));
        ((Double2x3Impl) dest).properties = _props;
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
    public Double2x3 mul(Double2x3R right, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
            int q = ((Double2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
                return mul_translation_fma(right, dest);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, dest, Joml.BIT_ORTHOGONAL & q);
                return mul_orthogonal_fma(right, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, dest, Joml.BIT_AFFINE & q);
            return mul_general_fma(right, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
            int q = ((Double2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
                return mul_translation_mulAdd(right, dest);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_ORTHOGONAL & q);
                return mul_orthogonal_mulAdd(right, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_AFFINE & q);
            return mul_general_mulAdd(right, dest);
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
    @Mutated public Double2x3 mul(Double2x3R right) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
            int q = ((Double2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
                return mul_translation_fma(right, this);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, this, Joml.BIT_ORTHOGONAL & q);
                return mul_orthogonal_fma(right, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_fma(right, this, Joml.BIT_AFFINE & q);
            return mul_general_fma(right, this);
        } else {
            if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
            int q = ((Double2x3Impl) right).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
                return mul_translation_mulAdd(right, this);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, this, Joml.BIT_ORTHOGONAL & q);
                return mul_orthogonal_mulAdd(right, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation_mulAdd(right, this, Joml.BIT_AFFINE & q);
            return mul_general_mulAdd(right, this);
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
    public Double2x3 mul(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = Math.fma(m00, _rd0, m10 * _rd2);
        dd[1] = Math.fma(m00, _rd1, m10 * _rd3);
        dd[2] = Math.fma(m01, _rd0, m11 * _rd2);
        dd[3] = Math.fma(m01, _rd1, m11 * _rd3);
        dd[4] = Math.fma(m02, _rd0, Math.fma(m12, _rd2, sd[4]));
        dd[5] = Math.fma(m02, _rd1, Math.fma(m12, _rd3, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_identity(Double2x2R right, @Mutated Double2x3 dest) {
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_identity_self(Double2x2R right, @Mutated Double2x3 dest) {
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_fma(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_mulAdd(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_self(Double2x2R right, @Mutated Double2x3 dest) {
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = rightData[1];
        dd[2] = rightData[2];
        dd[3] = rightData[3];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_fma(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4));
        dd[1] = java.lang.Math.fma(_rd0, _rd4, _rd1 * _rd5);
        dd[2] = java.lang.Math.fma(_rd2, _rd5, -(_rd3 * _rd4));
        dd[3] = java.lang.Math.fma(_rd2, _rd4, _rd3 * _rd5);
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_mulAdd(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        dd[0] = ((_rd0) * (_rd5) - (_rd1 * _rd4));
        dd[1] = ((_rd0) * (_rd4) + (_rd1 * _rd5));
        dd[2] = ((_rd2) * (_rd5) - (_rd3 * _rd4));
        dd[3] = ((_rd2) * (_rd4) + (_rd3 * _rd5));
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_self_fma(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd5, -(_rd1 * _rd4));
        dd[1] = java.lang.Math.fma(_rd0, _rd4, _rd1 * _rd5);
        dd[2] = java.lang.Math.fma(_rd2, _rd5, -(_rd3 * _rd4));
        dd[3] = java.lang.Math.fma(_rd2, _rd4, _rd3 * _rd5);
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_self_mulAdd(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        dd[0] = ((_rd0) * (_rd5) - (_rd1 * _rd4));
        dd[1] = ((_rd0) * (_rd4) + (_rd1 * _rd5));
        dd[2] = ((_rd2) * (_rd5) - (_rd3 * _rd4));
        dd[3] = ((_rd2) * (_rd4) + (_rd3 * _rd5));
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_fma(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd4, _rd1 * _rd6);
        dd[1] = java.lang.Math.fma(_rd0, _rd5, _rd1 * _rd7);
        dd[2] = java.lang.Math.fma(_rd2, _rd4, _rd3 * _rd6);
        dd[3] = java.lang.Math.fma(_rd2, _rd5, _rd3 * _rd7);
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_mulAdd(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        dd[0] = ((_rd0) * (_rd4) + (_rd1 * _rd6));
        dd[1] = ((_rd0) * (_rd5) + (_rd1 * _rd7));
        dd[2] = ((_rd2) * (_rd4) + (_rd3 * _rd6));
        dd[3] = ((_rd2) * (_rd5) + (_rd3 * _rd7));
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_self_fma(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _rd4, _rd1 * _rd6);
        dd[1] = java.lang.Math.fma(_rd0, _rd5, _rd1 * _rd7);
        dd[2] = java.lang.Math.fma(_rd2, _rd4, _rd3 * _rd6);
        dd[3] = java.lang.Math.fma(_rd2, _rd5, _rd3 * _rd7);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general_self_mulAdd(Double2x2R right, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        dd[0] = ((_rd0) * (_rd4) + (_rd1 * _rd6));
        dd[1] = ((_rd0) * (_rd5) + (_rd1 * _rd7));
        dd[2] = ((_rd2) * (_rd4) + (_rd3 * _rd6));
        dd[3] = ((_rd2) * (_rd5) + (_rd3 * _rd7));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 mul(Double2x2R right, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(right, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? mul_translation_fma(right, dest) : mul_translation_mulAdd(right, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? mul_orthogonal_fma(right, dest) : mul_orthogonal_mulAdd(right, dest));
        return (Math.useFma() ? mul_general_fma(right, dest) : mul_general_mulAdd(right, dest));
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
    @Mutated public Double2x3 mul(Double2x2R right) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(right, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal_self_fma(right, this);
            return mul_general_self_fma(right, this);
        } else {
            if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(right, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal_self_mulAdd(right, this);
            return mul_general_self_mulAdd(right, this);
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_fma(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[0];
        double _rd10 = sd[1];
        double _rd11 = sd[2];
        double _rd12 = sd[3];
        double _rd13 = sd[4];
        double _rd14 = sd[5];
        dd[0] = java.lang.Math.fma(_rd2, _rd13, java.lang.Math.fma(_rd0, _rd9, _rd1 * _rd11));
        dd[1] = java.lang.Math.fma(_rd2, _rd14, java.lang.Math.fma(_rd0, _rd10, _rd1 * _rd12));
        dd[2] = _rd2;
        dd[3] = java.lang.Math.fma(_rd5, _rd13, java.lang.Math.fma(_rd3, _rd9, _rd4 * _rd11));
        dd[4] = java.lang.Math.fma(_rd5, _rd14, java.lang.Math.fma(_rd3, _rd10, _rd4 * _rd12));
        dd[5] = _rd5;
        dd[6] = java.lang.Math.fma(_rd8, _rd13, java.lang.Math.fma(_rd6, _rd9, _rd7 * _rd11));
        dd[7] = java.lang.Math.fma(_rd8, _rd14, java.lang.Math.fma(_rd6, _rd10, _rd7 * _rd12));
        dd[8] = _rd8;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_mulAdd(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[0];
        double _rd10 = sd[1];
        double _rd11 = sd[2];
        double _rd12 = sd[3];
        double _rd13 = sd[4];
        double _rd14 = sd[5];
        dd[0] = ((_rd2) * (_rd13) + (((_rd0) * (_rd9) + (_rd1 * _rd11))));
        dd[1] = ((_rd2) * (_rd14) + (((_rd0) * (_rd10) + (_rd1 * _rd12))));
        dd[2] = _rd2;
        dd[3] = ((_rd5) * (_rd13) + (((_rd3) * (_rd9) + (_rd4 * _rd11))));
        dd[4] = ((_rd5) * (_rd14) + (((_rd3) * (_rd10) + (_rd4 * _rd12))));
        dd[5] = _rd5;
        dd[6] = ((_rd8) * (_rd13) + (((_rd6) * (_rd9) + (_rd7 * _rd11))));
        dd[7] = ((_rd8) * (_rd14) + (((_rd6) * (_rd10) + (_rd7 * _rd12))));
        dd[8] = _rd8;
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_fma(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[2];
        double _rd1 = rightData[5];
        double _rd2 = rightData[8];
        double _rd3 = sd[4];
        double _rd4 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd3, rightData[0]);
        dd[1] = java.lang.Math.fma(_rd0, _rd4, rightData[1]);
        dd[2] = _rd0;
        dd[3] = java.lang.Math.fma(_rd1, _rd3, rightData[3]);
        dd[4] = java.lang.Math.fma(_rd1, _rd4, rightData[4]);
        dd[5] = _rd1;
        dd[6] = java.lang.Math.fma(_rd2, _rd3, rightData[6]);
        dd[7] = java.lang.Math.fma(_rd2, _rd4, rightData[7]);
        dd[8] = _rd2;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_mulAdd(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[2];
        double _rd1 = rightData[5];
        double _rd2 = rightData[8];
        double _rd3 = sd[4];
        double _rd4 = sd[5];
        dd[0] = ((_rd0) * (_rd3) + (rightData[0]));
        dd[1] = ((_rd0) * (_rd4) + (rightData[1]));
        dd[2] = _rd0;
        dd[3] = ((_rd1) * (_rd3) + (rightData[3]));
        dd[4] = ((_rd1) * (_rd4) + (rightData[4]));
        dd[5] = _rd1;
        dd[6] = ((_rd2) * (_rd3) + (rightData[6]));
        dd[7] = ((_rd2) * (_rd4) + (rightData[7]));
        dd[8] = _rd2;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_fma(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[1];
        double _rd10 = sd[3];
        double _rd11 = sd[4];
        double _rd12 = sd[5];
        dd[0] = java.lang.Math.fma(_rd2, _rd11, java.lang.Math.fma(_rd0, _rd10, -(_rd1 * _rd9)));
        dd[1] = java.lang.Math.fma(_rd2, _rd12, java.lang.Math.fma(_rd0, _rd9, _rd1 * _rd10));
        dd[2] = _rd2;
        dd[3] = java.lang.Math.fma(_rd5, _rd11, java.lang.Math.fma(_rd3, _rd10, -(_rd4 * _rd9)));
        dd[4] = java.lang.Math.fma(_rd5, _rd12, java.lang.Math.fma(_rd3, _rd9, _rd4 * _rd10));
        dd[5] = _rd5;
        dd[6] = java.lang.Math.fma(_rd8, _rd11, java.lang.Math.fma(_rd6, _rd10, -(_rd7 * _rd9)));
        dd[7] = java.lang.Math.fma(_rd8, _rd12, java.lang.Math.fma(_rd6, _rd9, _rd7 * _rd10));
        dd[8] = _rd8;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_mulAdd(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[1];
        double _rd10 = sd[3];
        double _rd11 = sd[4];
        double _rd12 = sd[5];
        dd[0] = ((_rd2) * (_rd11) + (((_rd0) * (_rd10) - (_rd1 * _rd9))));
        dd[1] = ((_rd2) * (_rd12) + (((_rd0) * (_rd9) + (_rd1 * _rd10))));
        dd[2] = _rd2;
        dd[3] = ((_rd5) * (_rd11) + (((_rd3) * (_rd10) - (_rd4 * _rd9))));
        dd[4] = ((_rd5) * (_rd12) + (((_rd3) * (_rd9) + (_rd4 * _rd10))));
        dd[5] = _rd5;
        dd[6] = ((_rd8) * (_rd11) + (((_rd6) * (_rd10) - (_rd7 * _rd9))));
        dd[7] = ((_rd8) * (_rd12) + (((_rd6) * (_rd9) + (_rd7 * _rd10))));
        dd[8] = _rd8;
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double3x3 mul_translation_identity(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = sd[4];
        dd[7] = sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double3x3R right, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = rightData[6] + sd[4];
        dd[7] = rightData[7] + sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation_fma(Double3x3R right, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = rightData[6];
        double _rd5 = rightData[7];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = 0.0;
        dd[3] = _rd2;
        dd[4] = _rd3;
        dd[5] = 0.0;
        dd[6] = java.lang.Math.fma(_rd4, _rd0, java.lang.Math.fma(_rd5, _rd2, sd[4]));
        dd[7] = java.lang.Math.fma(_rd4, _rd1, java.lang.Math.fma(_rd5, _rd3, sd[5]));
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation_mulAdd(Double3x3R right, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = rightData[6];
        double _rd5 = rightData[7];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = 0.0;
        dd[3] = _rd2;
        dd[4] = _rd3;
        dd[5] = 0.0;
        dd[6] = ((_rd4) * (_rd0) + (((_rd5) * (_rd2) + (sd[4]))));
        dd[7] = ((_rd4) * (_rd1) + (((_rd5) * (_rd3) + (sd[5]))));
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
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
    public Double3x3 mul(Double3x3R right, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Double3x3Impl) right).properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_translation_identity(right, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return (Math.useFma() ? mul_translation_fma(right, dest) : mul_translation_mulAdd(right, dest));
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? mul_orthogonal_translation_fma(right, dest, Joml.BIT_ORTHOGONAL & q) : mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_ORTHOGONAL & q));
            return (Math.useFma() ? mul_orthogonal_fma(right, dest) : mul_orthogonal_mulAdd(right, dest));
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? mul_orthogonal_translation_fma(right, dest, Joml.BIT_AFFINE & q) : mul_orthogonal_translation_mulAdd(right, dest, Joml.BIT_AFFINE & q));
        return (Math.useFma() ? mul_general_fma(right, dest) : mul_general_mulAdd(right, dest));
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd4, _rd2 * _rd5);
        dd[1] = java.lang.Math.fma(_rd1, _rd4, _rd3 * _rd5);
        dd[2] = java.lang.Math.fma(_rd0, _rd6, _rd2 * _rd7);
        dd[3] = java.lang.Math.fma(_rd1, _rd6, _rd3 * _rd7);
        dd[4] = java.lang.Math.fma(_rd0, _rd8, java.lang.Math.fma(_rd2, _rd9, otherData[4]));
        dd[5] = java.lang.Math.fma(_rd1, _rd8, java.lang.Math.fma(_rd3, _rd9, otherData[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = ((_rd0) * (_rd4) + (_rd2 * _rd5));
        dd[1] = ((_rd1) * (_rd4) + (_rd3 * _rd5));
        dd[2] = ((_rd0) * (_rd6) + (_rd2 * _rd7));
        dd[3] = ((_rd1) * (_rd6) + (_rd3 * _rd7));
        dd[4] = ((_rd0) * (_rd8) + (((_rd2) * (_rd9) + (otherData[4]))));
        dd[5] = ((_rd1) * (_rd8) + (((_rd3) * (_rd9) + (otherData[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = java.lang.Math.fma(_rd0, _rd4, java.lang.Math.fma(_rd2, _rd5, otherData[4]));
        dd[5] = java.lang.Math.fma(_rd1, _rd4, java.lang.Math.fma(_rd3, _rd5, otherData[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = ((_rd0) * (_rd4) + (((_rd2) * (_rd5) + (otherData[4]))));
        dd[5] = ((_rd1) * (_rd4) + (((_rd3) * (_rd5) + (otherData[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_fma(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd5, _rd2 * _rd4);
        dd[1] = java.lang.Math.fma(_rd1, _rd5, _rd3 * _rd4);
        dd[2] = java.lang.Math.fma(_rd2, _rd5, -(_rd0 * _rd4));
        dd[3] = java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd4));
        dd[4] = java.lang.Math.fma(_rd0, _rd6, java.lang.Math.fma(_rd2, _rd7, otherData[4]));
        dd[5] = java.lang.Math.fma(_rd1, _rd6, java.lang.Math.fma(_rd3, _rd7, otherData[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_mulAdd(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = ((_rd0) * (_rd5) + (_rd2 * _rd4));
        dd[1] = ((_rd1) * (_rd5) + (_rd3 * _rd4));
        dd[2] = ((_rd2) * (_rd5) - (_rd0 * _rd4));
        dd[3] = ((_rd3) * (_rd5) - (_rd1 * _rd4));
        dd[4] = ((_rd0) * (_rd6) + (((_rd2) * (_rd7) + (otherData[4]))));
        dd[5] = ((_rd1) * (_rd6) + (((_rd3) * (_rd7) + (otherData[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_translation(Double2x3R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_translation(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x3Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        ((Double2x3Impl) dest).properties = _props;
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
    public Double2x3 preMul(Double2x3R other, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
            int q = ((Double2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
                return preMul_translation_fma(other, dest);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
                return preMul_orthogonal_fma(other, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
            return preMul_general_fma(other, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
            int q = ((Double2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
                return preMul_translation_mulAdd(other, dest);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
                return preMul_orthogonal_mulAdd(other, dest);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
            return preMul_general_mulAdd(other, dest);
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
    @Mutated public Double2x3 preMul(Double2x3R other) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
            int q = ((Double2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
                return preMul_translation_fma(other, this);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
                return preMul_orthogonal_fma(other, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
            return preMul_general_fma(other, this);
        } else {
            if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
            int q = ((Double2x3Impl) other).properties;
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, this);
                return preMul_translation_mulAdd(other, this);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_ORTHOGONAL & q);
                return preMul_orthogonal_mulAdd(other, this);
            }
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, this, Joml.BIT_AFFINE & q);
            return preMul_general_mulAdd(other, this);
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
    public Double2x3 preMul(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = Math.fma(m00, _rd0, m01 * _rd1);
        dd[1] = Math.fma(m10, _rd0, m11 * _rd1);
        dd[2] = Math.fma(m00, _rd2, m01 * _rd3);
        dd[3] = Math.fma(m10, _rd2, m11 * _rd3);
        dd[4] = Math.fma(m00, _rd4, Math.fma(m01, _rd5, m02));
        dd[5] = Math.fma(m10, _rd4, Math.fma(m11, _rd5, m12));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_identity(Double2x2R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = otherData[2];
        dd[3] = otherData[3];
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preMul}, specialized by runtime matrix properties;
     * reached only through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_identity_self(Double2x2R other, @Mutated Double2x3 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        dd[2] = otherData[2];
        dd[3] = otherData[3];
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_fma(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = java.lang.Math.fma(_rd0, _rd4, _rd2 * _rd5);
        dd[5] = java.lang.Math.fma(_rd1, _rd4, _rd3 * _rd5);
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_mulAdd(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = ((_rd0) * (_rd4) + (_rd2 * _rd5));
        dd[5] = ((_rd1) * (_rd4) + (_rd3 * _rd5));
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_fma(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd5, _rd2 * _rd4);
        dd[1] = java.lang.Math.fma(_rd1, _rd5, _rd3 * _rd4);
        dd[2] = java.lang.Math.fma(_rd2, _rd5, -(_rd0 * _rd4));
        dd[3] = java.lang.Math.fma(_rd3, _rd5, -(_rd1 * _rd4));
        dd[4] = java.lang.Math.fma(_rd0, _rd6, _rd2 * _rd7);
        dd[5] = java.lang.Math.fma(_rd1, _rd6, _rd3 * _rd7);
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_mulAdd(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[1];
        double _rd5 = sd[3];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = ((_rd0) * (_rd5) + (_rd2 * _rd4));
        dd[1] = ((_rd1) * (_rd5) + (_rd3 * _rd4));
        dd[2] = ((_rd2) * (_rd5) - (_rd0 * _rd4));
        dd[3] = ((_rd3) * (_rd5) - (_rd1 * _rd4));
        dd[4] = ((_rd0) * (_rd6) + (_rd2 * _rd7));
        dd[5] = ((_rd1) * (_rd6) + (_rd3 * _rd7));
        ((Double2x3Impl) dest).properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general_fma(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd4, _rd2 * _rd5);
        dd[1] = java.lang.Math.fma(_rd1, _rd4, _rd3 * _rd5);
        dd[2] = java.lang.Math.fma(_rd0, _rd6, _rd2 * _rd7);
        dd[3] = java.lang.Math.fma(_rd1, _rd6, _rd3 * _rd7);
        dd[4] = java.lang.Math.fma(_rd0, _rd8, _rd2 * _rd9);
        dd[5] = java.lang.Math.fma(_rd1, _rd8, _rd3 * _rd9);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general_mulAdd(Double2x2R other, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = ((_rd0) * (_rd4) + (_rd2 * _rd5));
        dd[1] = ((_rd1) * (_rd4) + (_rd3 * _rd5));
        dd[2] = ((_rd0) * (_rd6) + (_rd2 * _rd7));
        dd[3] = ((_rd1) * (_rd6) + (_rd3 * _rd7));
        dd[4] = ((_rd0) * (_rd8) + (_rd2 * _rd9));
        dd[5] = ((_rd1) * (_rd8) + (_rd3 * _rd9));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 preMul(Double2x2R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity(other, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? preMul_translation_fma(other, dest) : preMul_translation_mulAdd(other, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? preMul_orthogonal_fma(other, dest) : preMul_orthogonal_mulAdd(other, dest));
        return (Math.useFma() ? preMul_general_fma(other, dest) : preMul_general_mulAdd(other, dest));
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
    @Mutated public Double2x3 preMul(Double2x2R other) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity_self(other, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_fma(other, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal_fma(other, this);
            return preMul_general_fma(other, this);
        } else {
            if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity_self(other, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_mulAdd(other, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal_mulAdd(other, this);
            return preMul_general_mulAdd(other, this);
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_fma(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[0];
        double _rd7 = sd[1];
        double _rd8 = sd[2];
        double _rd9 = sd[3];
        double _rd10 = sd[4];
        double _rd11 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd6, _rd3 * _rd7);
        dd[1] = java.lang.Math.fma(_rd1, _rd6, _rd4 * _rd7);
        dd[2] = java.lang.Math.fma(_rd2, _rd6, _rd5 * _rd7);
        dd[3] = java.lang.Math.fma(_rd0, _rd8, _rd3 * _rd9);
        dd[4] = java.lang.Math.fma(_rd1, _rd8, _rd4 * _rd9);
        dd[5] = java.lang.Math.fma(_rd2, _rd8, _rd5 * _rd9);
        dd[6] = java.lang.Math.fma(_rd0, _rd10, java.lang.Math.fma(_rd3, _rd11, otherData[6]));
        dd[7] = java.lang.Math.fma(_rd1, _rd10, java.lang.Math.fma(_rd4, _rd11, otherData[7]));
        dd[8] = java.lang.Math.fma(_rd2, _rd10, java.lang.Math.fma(_rd5, _rd11, otherData[8]));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_mulAdd(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[0];
        double _rd7 = sd[1];
        double _rd8 = sd[2];
        double _rd9 = sd[3];
        double _rd10 = sd[4];
        double _rd11 = sd[5];
        dd[0] = ((_rd0) * (_rd6) + (_rd3 * _rd7));
        dd[1] = ((_rd1) * (_rd6) + (_rd4 * _rd7));
        dd[2] = ((_rd2) * (_rd6) + (_rd5 * _rd7));
        dd[3] = ((_rd0) * (_rd8) + (_rd3 * _rd9));
        dd[4] = ((_rd1) * (_rd8) + (_rd4 * _rd9));
        dd[5] = ((_rd2) * (_rd8) + (_rd5 * _rd9));
        dd[6] = ((_rd0) * (_rd10) + (((_rd3) * (_rd11) + (otherData[6]))));
        dd[7] = ((_rd1) * (_rd10) + (((_rd4) * (_rd11) + (otherData[7]))));
        dd[8] = ((_rd2) * (_rd10) + (((_rd5) * (_rd11) + (otherData[8]))));
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_fma(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = _rd4;
        dd[5] = _rd5;
        dd[6] = java.lang.Math.fma(_rd0, _rd6, java.lang.Math.fma(_rd3, _rd7, otherData[6]));
        dd[7] = java.lang.Math.fma(_rd1, _rd6, java.lang.Math.fma(_rd4, _rd7, otherData[7]));
        dd[8] = java.lang.Math.fma(_rd2, _rd6, java.lang.Math.fma(_rd5, _rd7, otherData[8]));
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_mulAdd(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[4];
        double _rd7 = sd[5];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = _rd4;
        dd[5] = _rd5;
        dd[6] = ((_rd0) * (_rd6) + (((_rd3) * (_rd7) + (otherData[6]))));
        dd[7] = ((_rd1) * (_rd6) + (((_rd4) * (_rd7) + (otherData[7]))));
        dd[8] = ((_rd2) * (_rd6) + (((_rd5) * (_rd7) + (otherData[8]))));
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_fma(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[1];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _rd7, _rd3 * _rd6);
        dd[1] = java.lang.Math.fma(_rd1, _rd7, _rd4 * _rd6);
        dd[2] = java.lang.Math.fma(_rd2, _rd7, _rd5 * _rd6);
        dd[3] = java.lang.Math.fma(_rd3, _rd7, -(_rd0 * _rd6));
        dd[4] = java.lang.Math.fma(_rd4, _rd7, -(_rd1 * _rd6));
        dd[5] = java.lang.Math.fma(_rd5, _rd7, -(_rd2 * _rd6));
        dd[6] = java.lang.Math.fma(_rd0, _rd8, java.lang.Math.fma(_rd3, _rd9, otherData[6]));
        dd[7] = java.lang.Math.fma(_rd1, _rd8, java.lang.Math.fma(_rd4, _rd9, otherData[7]));
        dd[8] = java.lang.Math.fma(_rd2, _rd8, java.lang.Math.fma(_rd5, _rd9, otherData[8]));
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_mulAdd(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = sd[1];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        dd[0] = ((_rd0) * (_rd7) + (_rd3 * _rd6));
        dd[1] = ((_rd1) * (_rd7) + (_rd4 * _rd6));
        dd[2] = ((_rd2) * (_rd7) + (_rd5 * _rd6));
        dd[3] = ((_rd3) * (_rd7) - (_rd0 * _rd6));
        dd[4] = ((_rd4) * (_rd7) - (_rd1 * _rd6));
        dd[5] = ((_rd5) * (_rd7) - (_rd2 * _rd6));
        dd[6] = ((_rd0) * (_rd8) + (((_rd3) * (_rd9) + (otherData[6]))));
        dd[7] = ((_rd1) * (_rd8) + (((_rd4) * (_rd9) + (otherData[7]))));
        dd[8] = ((_rd2) * (_rd8) + (((_rd5) * (_rd9) + (otherData[8]))));
        ((Double3x3Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double3x3R other, @Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = otherData[6] + sd[4];
        dd[7] = otherData[7] + sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return dest;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double3x3Impl) other).data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[2];
        dd[4] = sd[3];
        dd[5] = 0.0;
        dd[6] = otherData[6] + sd[4];
        dd[7] = otherData[7] + sd[5];
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = _props;
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
    public Double3x3 preMul(Double3x3R other, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Double3x3Impl) other).properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other, dest);
            return (Math.useFma() ? preMul_translation_fma(other, dest) : preMul_translation_mulAdd(other, dest));
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return (Math.useFma() ? preMul_orthogonal_fma(other, dest) : preMul_orthogonal_mulAdd(other, dest));
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to3x3_orthogonal_general(dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return (Math.useFma() ? preMul_general_fma(other, dest) : preMul_general_mulAdd(other, dest));
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
    public Double2x3 addScaled(Double2x3R other, double weight, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] otherData = ((Double2x3Impl) other).data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherData[0], sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherData[1], sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherData[2], sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherData[3], sd[3]);
            dd[4] = java.lang.Math.fma(weight, otherData[4], sd[4]);
            dd[5] = java.lang.Math.fma(weight, otherData[5], sd[5]);
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
            return dest;
        } else {
            double[] sd = this.data;
            double[] otherData = ((Double2x3Impl) other).data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = ((weight) * (otherData[0]) + (sd[0]));
            dd[1] = ((weight) * (otherData[1]) + (sd[1]));
            dd[2] = ((weight) * (otherData[2]) + (sd[2]));
            dd[3] = ((weight) * (otherData[3]) + (sd[3]));
            dd[4] = ((weight) * (otherData[4]) + (sd[4]));
            dd[5] = ((weight) * (otherData[5]) + (sd[5]));
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 addScaled(double m00, double m01, double m02, double m10, double m11, double m12, double weight, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
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
            double[] sd = this.data;
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
    @Mutated public Double2x3 makeRotation(double angle) {
        double[] dd = this.data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    public @Mutated Double2x3 makeScaling(Double2R v) {
        double vY = v.y();
        double[] dd = this.data;
        dd[0] = v.x();
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = vY;
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    @Mutated public Double2x3 makeScaling(double vX, double vY) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = vY;
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    @Mutated public Double2x3 makeScaling(double s) {
        double[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = 0.0;
        dd[5] = 0.0;
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
    public @Mutated Double2x3 makeTranslation(Double2R v) {
        double vX = v.x();
        double vY = v.y();
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
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
    @Mutated public Double2x3 makeTranslation(double vX, double vY) {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
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
    @Mutated public Double2x3 makeView(double left, double right, double bottom, double top) {
        double[] dd = this.data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
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
    private Double2x3 preRotate_orthogonal_general_fma(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _t1, -(_rd1 * _t0));
        dd[1] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t1);
        dd[2] = java.lang.Math.fma(_rd2, _t1, -(_rd3 * _t0));
        dd[3] = java.lang.Math.fma(_rd2, _t0, _rd3 * _t1);
        dd[4] = java.lang.Math.fma(_rd4, _t1, -(_rd5 * _t0));
        dd[5] = java.lang.Math.fma(_rd4, _t0, _rd5 * _t1);
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x3 preRotate_orthogonal_general_mulAdd(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = ((_rd0) * (_t1) - (_rd1 * _t0));
        dd[1] = ((_rd0) * (_t0) + (_rd1 * _t1));
        dd[2] = ((_rd2) * (_t1) - (_rd3 * _t0));
        dd[3] = ((_rd2) * (_t0) + (_rd3 * _t1));
        dd[4] = ((_rd4) * (_t1) - (_rd5 * _t0));
        dd[5] = ((_rd4) * (_t0) + (_rd5 * _t1));
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Double2x3 preRotate_identity(double angle, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Double2x3 preRotate_identity_self(double angle, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x3 preRotate_translation_fma(double angle, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[4];
        double _rd1 = sd[5];
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = java.lang.Math.fma(_rd0, _t1, -(_rd1 * _t0));
        dd[5] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t1);
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x3 preRotate_translation_mulAdd(double angle, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[4];
        double _rd1 = sd[5];
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = ((_rd0) * (_t1) - (_rd1 * _t0));
        dd[5] = ((_rd0) * (_t0) + (_rd1 * _t1));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
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
    public Double2x3 preRotate(double angle, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_fma(angle, dest);
            return preRotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_mulAdd(angle, dest);
            return preRotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
    @Mutated public Double2x3 preRotate(double angle) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_fma(angle, this);
            return preRotate_orthogonal_general_fma(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        } else {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation_mulAdd(angle, this);
            return preRotate_orthogonal_general_mulAdd(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
    public Double2x3 preRotateAround(double angle, Double2R pivot, @Mutated Double2x3 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (Math.useFma() ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? preRotateAround_translation_fma(angle, pivotX, pivotY, dest) : preRotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? preRotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
        return (Math.useFma() ? preRotateAround_general_fma(angle, pivotX, pivotY, dest) : preRotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 preRotateAround(double angle, Double2R pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
            return preRotateAround_general_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
            return preRotateAround_general_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Double2x3 preRotateAround_identity_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = java.lang.Math.fma(pivotX, _t5, pivotY * _t0);
        dd[5] = java.lang.Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Double2x3 preRotateAround_identity_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = ((pivotX) * (_t5) + (pivotY * _t0));
        dd[5] = ((pivotY) * (_t5) - (pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_translation_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[4];
        double _rd1 = sd[5];
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = java.lang.Math.fma(pivotX, _t5, pivotY * _t0) + java.lang.Math.fma(_rd0, _t2, -(_rd1 * _t0));
        dd[5] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t2) + java.lang.Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_translation_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[4];
        double _rd1 = sd[5];
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = ((pivotX) * (_t5) + (pivotY * _t0)) + ((_rd0) * (_t2) - (_rd1 * _t0));
        dd[5] = ((_rd0) * (_t0) + (_rd1 * _t2)) + ((pivotY) * (_t5) - (pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_orthogonal_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _t2, -(_rd1 * _t0));
        dd[1] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t2);
        dd[2] = java.lang.Math.fma(_rd2, _t2, -(_rd3 * _t0));
        dd[3] = java.lang.Math.fma(_rd2, _t0, _rd3 * _t2);
        dd[4] = java.lang.Math.fma(pivotX, _t5, pivotY * _t0) + java.lang.Math.fma(_rd4, _t2, -(_rd5 * _t0));
        dd[5] = java.lang.Math.fma(_rd4, _t0, _rd5 * _t2) + java.lang.Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_orthogonal_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = ((_rd0) * (_t2) - (_rd1 * _t0));
        dd[1] = ((_rd0) * (_t0) + (_rd1 * _t2));
        dd[2] = ((_rd2) * (_t2) - (_rd3 * _t0));
        dd[3] = ((_rd2) * (_t0) + (_rd3 * _t2));
        dd[4] = ((pivotX) * (_t5) + (pivotY * _t0)) + ((_rd4) * (_t2) - (_rd5 * _t0));
        dd[5] = ((_rd4) * (_t0) + (_rd5 * _t2)) + ((pivotY) * (_t5) - (pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_general_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = java.lang.Math.fma(_rd0, _t2, -(_rd1 * _t0));
        dd[1] = java.lang.Math.fma(_rd0, _t0, _rd1 * _t2);
        dd[2] = java.lang.Math.fma(_rd2, _t2, -(_rd3 * _t0));
        dd[3] = java.lang.Math.fma(_rd2, _t0, _rd3 * _t2);
        dd[4] = java.lang.Math.fma(pivotX, _t5, pivotY * _t0) + java.lang.Math.fma(_rd4, _t2, -(_rd5 * _t0));
        dd[5] = java.lang.Math.fma(_rd4, _t0, _rd5 * _t2) + java.lang.Math.fma(pivotY, _t5, -(pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_general_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = ((_rd0) * (_t2) - (_rd1 * _t0));
        dd[1] = ((_rd0) * (_t0) + (_rd1 * _t2));
        dd[2] = ((_rd2) * (_t2) - (_rd3 * _t0));
        dd[3] = ((_rd2) * (_t0) + (_rd3 * _t2));
        dd[4] = ((pivotX) * (_t5) + (pivotY * _t0)) + ((_rd4) * (_t2) - (_rd5 * _t0));
        dd[5] = ((_rd4) * (_t0) + (_rd5 * _t2)) + ((pivotY) * (_t5) - (pivotX * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 preRotateAround(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (Math.useFma() ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? preRotateAround_translation_fma(angle, pivotX, pivotY, dest) : preRotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? preRotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
        return (Math.useFma() ? preRotateAround_general_fma(angle, pivotX, pivotY, dest) : preRotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 preRotateAround(double angle, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
            return preRotateAround_general_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
            return preRotateAround_general_mulAdd(angle, pivotX, pivotY, this);
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
    public Double2x3 preScale(Double2R v, @Mutated Double2x3 dest) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation(vX, vY, dest);
        return preScale_general(vX, vY, dest);
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
    public @Mutated Double2x3 preScale(Double2R v) {
        double vX = v.x();
        double vY = v.y();
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x3 preScale_identity(double vX, double vY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = vY;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation_self(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = vX;
        dd[3] = vY;
        dd[4] = sd[4] * vX;
        dd[5] = sd[5] * vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_general(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    public Double2x3 preScale(double vX, double vY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preScale(double vX, double vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x3 preScale_identity(double s, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation_self(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s;
        dd[3] = s;
        dd[4] = s * sd[4];
        dd[5] = s * sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_general(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    public Double2x3 preScale(double s, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preScale(double s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(s, this);
        return preScale_general(s, this);
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
    public Double2x3 preScaleAround(double s, Double2R pivot, @Mutated Double2x3 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? preScaleAround_translation_fma(s, pivotX, pivotY, dest) : preScaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (Math.useFma() ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 preScaleAround(double s, Double2R pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return preScaleAround_general_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self_mulAdd(s, pivotX, pivotY, this);
            return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
        }
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = pivotX * _t0;
        dd[5] = pivotY * _t0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity_self(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = pivotX * _t0;
        dd[5] = pivotY * _t0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
        dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation_self_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation_self_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
        dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = java.lang.Math.fma(s, sd[4], pivotX * _t0);
        dd[5] = java.lang.Math.fma(s, sd[5], pivotY * _t0);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        dd[4] = ((s) * (sd[4]) + (pivotX * _t0));
        dd[5] = ((s) * (sd[5]) + (pivotY * _t0));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 preScaleAround(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? preScaleAround_translation_fma(s, pivotX, pivotY, dest) : preScaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (Math.useFma() ? preScaleAround_general_fma(s, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(s, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 preScaleAround(double s, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return preScaleAround_general_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self_mulAdd(s, pivotX, pivotY, this);
            return preScaleAround_general_mulAdd(s, pivotX, pivotY, this);
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
    public Double2x3 preScaleAround(Double2R s, Double2R pivot, @Mutated Double2x3 dest) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        return (Math.useFma() ? preScaleAround_general_fma(sX, sY, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 preScaleAround(Double2R s, Double2R pivot) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            return preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sY;
        dd[4] = pivotX * (1.0 - sX);
        dd[5] = pivotY * (1.0 - sY);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = pivotX * (1.0 - sX);
        dd[5] = pivotY * (1.0 - sY);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general_fma(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = java.lang.Math.fma(pivotX, 1.0 - sX, sX * sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0 - sY, sY * sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general_mulAdd(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX * sd[0];
        dd[1] = sY * sd[1];
        dd[2] = sX * sd[2];
        dd[3] = sY * sd[3];
        dd[4] = ((pivotX) * (1.0 - sX) + (sX * sd[4]));
        dd[5] = ((pivotY) * (1.0 - sY) + (sY * sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 preScaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        return (Math.useFma() ? preScaleAround_general_fma(sX, sY, pivotX, pivotY, dest) : preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 preScaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            return preScaleAround_general_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            return preScaleAround_general_mulAdd(sX, sY, pivotX, pivotY, this);
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
    public Double2x3 preTranslate(Double2R v, @Mutated Double2x3 dest) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY, dest);
        return preTranslate_general(vX, vY, dest);
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
    public @Mutated Double2x3 preTranslate(Double2R v) {
        double vX = v.x();
        double vY = v.y();
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x3 preTranslate_translation(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Double2x3 preTranslate_translation_self(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_orthogonal(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_orthogonal_self(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_general(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties; reached only through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_general_self(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = sd[4] + vX;
        dd[5] = sd[5] + vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 preTranslate(double vX, double vY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preTranslate(double vX, double vY) {
        if (Joml.RETURN_NEW) return preTranslate(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_orthogonal_general_fma(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _t1, _rd2 * _t0);
        dd[1] = java.lang.Math.fma(_rd1, _t1, _rd3 * _t0);
        dd[2] = java.lang.Math.fma(_rd2, _t1, -(_rd0 * _t0));
        dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd1 * _t0));
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_orthogonal_general_mulAdd(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = ((_rd0) * (_t1) + (_rd2 * _t0));
        dd[1] = ((_rd1) * (_t1) + (_rd3 * _t0));
        dd[2] = ((_rd2) * (_t1) - (_rd0 * _t0));
        dd[3] = ((_rd3) * (_t1) - (_rd1 * _t0));
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_orthogonal_general_self_fma(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _t1, _rd2 * _t0);
        dd[1] = java.lang.Math.fma(_rd1, _t1, _rd3 * _t0);
        dd[2] = java.lang.Math.fma(_rd2, _t1, -(_rd0 * _t0));
        dd[3] = java.lang.Math.fma(_rd3, _t1, -(_rd1 * _t0));
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code rotate}, specialized by runtime matrix properties;
     * reached only through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_orthogonal_general_self_mulAdd(double angle, @Mutated Double2x3 dest, int _props) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = ((_rd0) * (_t1) + (_rd2 * _t0));
        dd[1] = ((_rd1) * (_t1) + (_rd3 * _t0));
        dd[2] = ((_rd2) * (_t1) - (_rd0 * _t0));
        dd[3] = ((_rd3) * (_t1) - (_rd1 * _t0));
        ((Double2x3Impl) dest).properties = _props;
        return dest;
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_translation(double angle, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dd[0] = _t1;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t1;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
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
    public Double2x3 rotate(double angle, @Mutated Double2x3 dest) {
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle, dest);
            return rotate_orthogonal_general_fma(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity(angle, dest);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle, dest);
            return rotate_orthogonal_general_mulAdd(angle, dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
    @Mutated public Double2x3 rotate(double angle) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_identity_self(angle, this);
            return rotate_orthogonal_general_self_fma(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
        } else {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_identity_self(angle, this);
            return rotate_orthogonal_general_self_mulAdd(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
    public Double2x3 rotateAround(double angle, Double2R pivot, @Mutated Double2x3 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (Math.useFma() ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? rotateAround_translation_fma(angle, pivotX, pivotY, dest) : rotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? rotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
        return (Math.useFma() ? rotateAround_general_fma(angle, pivotX, pivotY, dest) : rotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 rotateAround(double angle, Double2R pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
            return rotateAround_general_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
            return rotateAround_general_mulAdd(angle, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_translation_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = java.lang.Math.fma(pivotX, _t5, java.lang.Math.fma(pivotY, _t0, sd[4]));
        dd[5] = java.lang.Math.fma(pivotY, _t5, java.lang.Math.fma(-pivotX, _t0, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_translation_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dd[0] = _t2;
        dd[1] = _t0;
        dd[2] = -_t0;
        dd[3] = _t2;
        dd[4] = ((pivotX) * (_t5) + (((pivotY) * (_t0) + (sd[4]))));
        dd[5] = ((pivotY) * (_t5) + (((-pivotX) * (_t0) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_orthogonal_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = java.lang.Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = java.lang.Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _t2, _rd2 * _t0);
        dd[1] = java.lang.Math.fma(_rd1, _t2, _rd3 * _t0);
        dd[2] = java.lang.Math.fma(_rd2, _t2, -(_rd0 * _t0));
        dd[3] = java.lang.Math.fma(_rd3, _t2, -(_rd1 * _t0));
        dd[4] = java.lang.Math.fma(_rd0, _t9, java.lang.Math.fma(_rd2, _t10, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, _t9, java.lang.Math.fma(_rd3, _t10, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_orthogonal_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = ((pivotX) * (_t8) + (pivotY * _t0));
        double _t10 = ((pivotY) * (_t8) - (pivotX * _t0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = ((_rd0) * (_t2) + (_rd2 * _t0));
        dd[1] = ((_rd1) * (_t2) + (_rd3 * _t0));
        dd[2] = ((_rd2) * (_t2) - (_rd0 * _t0));
        dd[3] = ((_rd3) * (_t2) - (_rd1 * _t0));
        dd[4] = ((_rd0) * (_t9) + (((_rd2) * (_t10) + (sd[4]))));
        dd[5] = ((_rd1) * (_t9) + (((_rd3) * (_t10) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_general_fma(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = java.lang.Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = java.lang.Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = java.lang.Math.fma(_rd0, _t2, _rd2 * _t0);
        dd[1] = java.lang.Math.fma(_rd1, _t2, _rd3 * _t0);
        dd[2] = java.lang.Math.fma(_rd2, _t2, -(_rd0 * _t0));
        dd[3] = java.lang.Math.fma(_rd3, _t2, -(_rd1 * _t0));
        dd[4] = java.lang.Math.fma(_rd0, _t9, java.lang.Math.fma(_rd2, _t10, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, _t9, java.lang.Math.fma(_rd3, _t10, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_general_mulAdd(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = ((pivotX) * (_t8) + (pivotY * _t0));
        double _t10 = ((pivotY) * (_t8) - (pivotX * _t0));
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = ((_rd0) * (_t2) + (_rd2 * _t0));
        dd[1] = ((_rd1) * (_t2) + (_rd3 * _t0));
        dd[2] = ((_rd2) * (_t2) - (_rd0 * _t0));
        dd[3] = ((_rd3) * (_t2) - (_rd1 * _t0));
        dd[4] = ((_rd0) * (_t9) + (((_rd2) * (_t10) + (sd[4]))));
        dd[5] = ((_rd1) * (_t9) + (((_rd3) * (_t10) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 rotateAround(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return (Math.useFma() ? preRotateAround_identity_fma(angle, pivotX, pivotY, dest) : preRotateAround_identity_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? rotateAround_translation_fma(angle, pivotX, pivotY, dest) : rotateAround_translation_mulAdd(angle, pivotX, pivotY, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? rotateAround_orthogonal_fma(angle, pivotX, pivotY, dest) : rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, dest));
        return (Math.useFma() ? rotateAround_general_fma(angle, pivotX, pivotY, dest) : rotateAround_general_mulAdd(angle, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 rotateAround(double angle, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_fma(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal_fma(angle, pivotX, pivotY, this);
            return rotateAround_general_fma(angle, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation_mulAdd(angle, pivotX, pivotY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal_mulAdd(angle, pivotX, pivotY, this);
            return rotateAround_general_mulAdd(angle, pivotX, pivotY, this);
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
    public Double2x3 scale(Double2R v, @Mutated Double2x3 dest) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation(vX, vY, dest);
        return scale_general(vX, vY, dest);
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
    public @Mutated Double2x3 scale(Double2R v) {
        double vX = v.x();
        double vY = v.y();
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = this.data;
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
    private Double2x3 scale_translation(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = vY;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_general(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
    private Double2x3 scale_general_self(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 scale(double vX, double vY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 scale(double vX, double vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = this.data;
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
    private Double2x3 scale_translation(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = sd[4];
        dd[5] = sd[5];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_general(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
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
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x3 scale_general_self(double s, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 scale(double s, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 scale(double s) {
        if (Joml.RETURN_NEW) return scale(s, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = Joml.BIT_AFFINE;
            return this;
        }
        return scale_general_self(s, this);
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
    public Double2x3 scaleAround(double s, Double2R pivot, @Mutated Double2x3 dest) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? scaleAround_translation_fma(s, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (Math.useFma() ? scaleAround_orthogonal_fma(s, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 scaleAround(double s, Double2R pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(pivotX, _t0, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, _t0, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        dd[4] = ((pivotX) * (_t0) + (sd[4]));
        dd[5] = ((pivotY) * (_t0) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = java.lang.Math.fma(pivotX, _t0, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, _t0, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        dd[0] = s;
        dd[3] = s;
        dd[4] = ((pivotX) * (_t0) + (sd[4]));
        dd[5] = ((pivotY) * (_t0) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal_fma(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = s * _rd0;
        dd[1] = s * _rd1;
        dd[2] = s * _rd2;
        dd[3] = s * _rd3;
        dd[4] = java.lang.Math.fma(_rd0, _t1, java.lang.Math.fma(_rd2, _t2, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, _t1, java.lang.Math.fma(_rd3, _t2, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal_mulAdd(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = s * _rd0;
        dd[1] = s * _rd1;
        dd[2] = s * _rd2;
        dd[3] = s * _rd3;
        dd[4] = ((_rd0) * (_t1) + (((_rd2) * (_t2) + (sd[4]))));
        dd[5] = ((_rd1) * (_t1) + (((_rd3) * (_t2) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 scaleAround(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(s, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? scaleAround_translation_fma(s, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(s, pivotX, pivotY, dest));
        return (Math.useFma() ? scaleAround_orthogonal_fma(s, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 scaleAround(double s, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(s, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(s, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(s, pivotX, pivotY, this);
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
    public Double2x3 scaleAround(Double2R s, Double2R pivot, @Mutated Double2x3 dest) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? scaleAround_translation_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(sX, sY, pivotX, pivotY, dest));
        return (Math.useFma() ? scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, dest));
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
    public @Mutated Double2x3 scaleAround(Double2R s, Double2R pivot) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, this);
        }
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_fma(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sY;
        dd[4] = java.lang.Math.fma(pivotX, 1.0 - sX, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0 - sY, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_mulAdd(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sY;
        dd[4] = ((pivotX) * (1.0 - sX) + (sd[4]));
        dd[5] = ((pivotY) * (1.0 - sY) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self_fma(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = java.lang.Math.fma(pivotX, 1.0 - sX, sd[4]);
        dd[5] = java.lang.Math.fma(pivotY, 1.0 - sY, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self_mulAdd(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sX;
        dd[3] = sY;
        dd[4] = ((pivotX) * (1.0 - sX) + (sd[4]));
        dd[5] = ((pivotY) * (1.0 - sY) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal_fma(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = sX * _rd0;
        dd[1] = sX * _rd1;
        dd[2] = sY * _rd2;
        dd[3] = sY * _rd3;
        dd[4] = java.lang.Math.fma(_rd0, _t2, java.lang.Math.fma(_rd2, _t3, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, _t2, java.lang.Math.fma(_rd3, _t3, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal_mulAdd(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = sX * _rd0;
        dd[1] = sX * _rd1;
        dd[2] = sY * _rd2;
        dd[3] = sY * _rd3;
        dd[4] = ((_rd0) * (_t2) + (((_rd2) * (_t3) + (sd[4]))));
        dd[5] = ((_rd1) * (_t2) + (((_rd3) * (_t3) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 scaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? scaleAround_translation_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_translation_mulAdd(sX, sY, pivotX, pivotY, dest));
        return (Math.useFma() ? scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, dest) : scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, dest));
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
    @Mutated public Double2x3 scaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_fma(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_fma(sX, sY, pivotX, pivotY, this);
        } else {
            if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self_mulAdd(sX, sY, pivotX, pivotY, this);
            return scaleAround_orthogonal_mulAdd(sX, sY, pivotX, pivotY, this);
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
    public Double2x3 translate(Double2R v, @Mutated Double2x3 dest) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? translate_orthogonal_fma(vX, vY, dest) : translate_orthogonal_mulAdd(vX, vY, dest));
        return (Math.useFma() ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
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
    public @Mutated Double2x3 translate(Double2R v) {
        double vX = v.x();
        double vY = v.y();
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self_fma(vX, vY, this);
            return translate_general_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self_mulAdd(vX, vY, this);
            return translate_general_self_mulAdd(vX, vY, this);
        }
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_orthogonal_fma(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = java.lang.Math.fma(_rd0, vX, java.lang.Math.fma(_rd2, vY, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, vX, java.lang.Math.fma(_rd3, vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_orthogonal_mulAdd(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = ((_rd0) * (vX) + (((_rd2) * (vY) + (sd[4]))));
        dd[5] = ((_rd1) * (vX) + (((_rd3) * (vY) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_orthogonal_self_fma(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_orthogonal_self_mulAdd(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_general_fma(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = java.lang.Math.fma(_rd0, vX, java.lang.Math.fma(_rd2, vY, sd[4]));
        dd[5] = java.lang.Math.fma(_rd1, vX, java.lang.Math.fma(_rd3, vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_general_mulAdd(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _rd0;
        dd[1] = _rd1;
        dd[2] = _rd2;
        dd[3] = _rd3;
        dd[4] = ((_rd0) * (vX) + (((_rd2) * (vY) + (sd[4]))));
        dd[5] = ((_rd1) * (vX) + (((_rd3) * (vY) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_general_self_fma(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[5] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code translate}, specialized by runtime matrix
     * properties; reached only through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_general_self_mulAdd(double vX, double vY, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[4] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[5] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 translate(double vX, double vY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return withTranslation_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? translate_orthogonal_fma(vX, vY, dest) : translate_orthogonal_mulAdd(vX, vY, dest));
        return (Math.useFma() ? translate_general_fma(vX, vY, dest) : translate_general_mulAdd(vX, vY, dest));
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
    @Mutated public Double2x3 translate(double vX, double vY) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self_fma(vX, vY, this);
            return translate_general_self_fma(vX, vY, this);
        } else {
            if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[4] = vX;
                dd[5] = vY;
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal_self_mulAdd(vX, vY, this);
            return translate_general_self_mulAdd(vX, vY, this);
        }
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_identity(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = -((left + right) * _t0_inv);
        dd[5] = -((bottom + top) * _t1_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double2x3 view_identity_self(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = -((left + right) * _t0_inv);
        dd[5] = -((bottom + top) * _t1_inv);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_translation_fma(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = java.lang.Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = java.lang.Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_translation_mulAdd(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = ((-(left + right)) * (_t0_inv) + (sd[4]));
        dd[5] = ((-(bottom + top)) * (_t1_inv) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double2x3 view_translation_self_fma(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = java.lang.Math.fma(-(left + right), _t0_inv, sd[4]);
        dd[5] = java.lang.Math.fma(-(bottom + top), _t1_inv, sd[5]);
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double2x3 view_translation_self_mulAdd(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dd[0] = _t0_inv + _t0_inv;
        dd[3] = _t1_inv + _t1_inv;
        dd[4] = ((-(left + right)) * (_t0_inv) + (sd[4]));
        dd[5] = ((-(bottom + top)) * (_t1_inv) + (sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_orthogonal_fma(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _sp0 * _rd0;
        dd[1] = _sp0 * _rd1;
        dd[2] = _sp1 * _rd2;
        dd[3] = _sp1 * _rd3;
        dd[4] = java.lang.Math.fma(-_rd2, _sp3, java.lang.Math.fma(-_rd0, _sp2, sd[4]));
        dd[5] = java.lang.Math.fma(-_rd3, _sp3, java.lang.Math.fma(-_rd1, _sp2, sd[5]));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_orthogonal_mulAdd(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _sp0 * _rd0;
        dd[1] = _sp0 * _rd1;
        dd[2] = _sp1 * _rd2;
        dd[3] = _sp1 * _rd3;
        dd[4] = ((-_rd2) * (_sp3) + (((-_rd0) * (_sp2) + (sd[4]))));
        dd[5] = ((-_rd3) * (_sp3) + (((-_rd1) * (_sp2) + (sd[5]))));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_general_fma(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _sp0 * _rd0;
        dd[1] = _sp0 * _rd1;
        dd[2] = _sp1 * _rd2;
        dd[3] = _sp1 * _rd3;
        dd[4] = sd[4] + java.lang.Math.fma(-_rd2, _sp3, -(_rd0 * _sp2));
        dd[5] = sd[5] + java.lang.Math.fma(-_rd3, _sp3, -(_rd1 * _sp2));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_general_mulAdd(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        dd[0] = _sp0 * _rd0;
        dd[1] = _sp0 * _rd1;
        dd[2] = _sp1 * _rd2;
        dd[3] = _sp1 * _rd3;
        dd[4] = sd[4] + ((-_rd2) * (_sp3) - (_rd0 * _sp2));
        dd[5] = sd[5] + ((-_rd3) * (_sp3) - (_rd1 * _sp2));
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 view(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity(left, right, bottom, top, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return (Math.useFma() ? view_translation_fma(left, right, bottom, top, dest) : view_translation_mulAdd(left, right, bottom, top, dest));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (Math.useFma() ? view_orthogonal_fma(left, right, bottom, top, dest) : view_orthogonal_mulAdd(left, right, bottom, top, dest));
        return (Math.useFma() ? view_general_fma(left, right, bottom, top, dest) : view_general_mulAdd(left, right, bottom, top, dest));
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
    @Mutated public Double2x3 view(double left, double right, double bottom, double top) {
        if (Math.useFma()) {
            if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self_fma(left, right, bottom, top, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal_fma(left, right, bottom, top, this);
            return view_general_fma(left, right, bottom, top, this);
        } else {
            if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.double2x3());
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self_mulAdd(left, right, bottom, top, this);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal_mulAdd(left, right, bottom, top, this);
            return view_general_mulAdd(left, right, bottom, top, this);
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
    public Double2 mul(Double3R v, @Mutated Double2 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_fma(vX, vY, vZ, dest);
            return mul_general_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_mulAdd(vX, vY, vZ, dest);
            return mul_general_mulAdd(vX, vY, vZ, dest);
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_translation_fma(double vX, double vY, double vZ, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[4], vZ, vX);
        dd[1] = java.lang.Math.fma(sd[5], vZ, vY);
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_translation_mulAdd(double vX, double vY, double vZ, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((sd[4]) * (vZ) + (vX));
        dd[1] = ((sd[5]) * (vZ) + (vY));
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general_fma(double vX, double vY, double vZ, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[4], vZ, java.lang.Math.fma(sd[0], vX, sd[2] * vY));
        dd[1] = java.lang.Math.fma(sd[5], vZ, java.lang.Math.fma(sd[1], vX, sd[3] * vY));
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general_mulAdd(double vX, double vY, double vZ, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((sd[4]) * (vZ) + (((sd[0]) * (vX) + (sd[2] * vY))));
        dd[1] = ((sd[5]) * (vZ) + (((sd[1]) * (vX) + (sd[3] * vY))));
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
    public Double2 mul(double vX, double vY, double vZ, @Mutated Double2 dest) {
        if (Math.useFma()) {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_fma(vX, vY, vZ, dest);
            return mul_general_fma(vX, vY, vZ, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_mulAdd(vX, vY, vZ, dest);
            return mul_general_mulAdd(vX, vY, vZ, dest);
        }
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
    public Double2 transformDirection(Double2R v, @Mutated Double2 dest) {
        double vX = v.x();
        double vY = v.y();
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        return (Math.useFma() ? transformDirection_general_fma(vX, vY, dest) : transformDirection_general_mulAdd(vX, vY, dest));
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Double2 transformDirection_general_fma(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }

    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Double2 transformDirection_general_mulAdd(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
        dd[1] = ((sd[1]) * (vX) + (sd[3] * vY));
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
    public Double2 transformDirection(double vX, double vY, @Mutated Double2 dest) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        return (Math.useFma() ? transformDirection_general_fma(vX, vY, dest) : transformDirection_general_mulAdd(vX, vY, dest));
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
    public Double2 transformPosition(Double2R v, @Mutated Double2 dest) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformPosition_translation(vX, vY, dest);
        return (Math.useFma() ? transformPosition_general_fma(vX, vY, dest) : transformPosition_general_mulAdd(vX, vY, dest));
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_translation(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[4] + vX;
        dd[1] = sd[5] + vY;
        return dest;
    }

    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_general_fma(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, java.lang.Math.fma(sd[2], vY, sd[4]));
        dd[1] = java.lang.Math.fma(sd[1], vX, java.lang.Math.fma(sd[3], vY, sd[5]));
        return dest;
    }

    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_general_mulAdd(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (((sd[2]) * (vY) + (sd[4]))));
        dd[1] = ((sd[1]) * (vX) + (((sd[3]) * (vY) + (sd[5]))));
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
    public Double2 transformPosition(double vX, double vY, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = vX;
            dd[1] = vY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformPosition_translation(vX, vY, dest);
        return (Math.useFma() ? transformPosition_general_fma(vX, vY, dest) : transformPosition_general_mulAdd(vX, vY, dest));
    }

    public double m00() { return data[0]; }
    public double m01() { return data[2]; }
    public double m02() { return data[4]; }
    public double m10() { return data[1]; }
    public double m11() { return data[3]; }
    public double m12() { return data[5]; }

    @Override public String toString() {
        return "Double2x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x3Impl)) return false;
        Double2x3Impl o = (Double2x3Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5]);
    }

    @Override public boolean equalsEpsilon(Double2x3R other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.m00()) <= epsilon
            && java.lang.Math.abs(data[2] - other.m01()) <= epsilon
            && java.lang.Math.abs(data[4] - other.m02()) <= epsilon
            && java.lang.Math.abs(data[1] - other.m10()) <= epsilon
            && java.lang.Math.abs(data[3] - other.m11()) <= epsilon
            && java.lang.Math.abs(data[5] - other.m12()) <= epsilon;
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
    public @Mutated Double2x3 loadCM(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
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
    @Mutated public Double2x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadCMRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Double2x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Double2x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double2x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double2x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double2x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Double2x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Double2x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        return dest;
    }
    public @Mutated Double2x3 loadCM(float[] src, int offset) {
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
    @Mutated public Double2x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Double2x3 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }
    public Double2x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double2x3 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Double2x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double2x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    @Mutated public Double2x3 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }
    public Double2x3 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private double[] storeRM_distinct(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[3];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t4;
        dest[offset + 3] = t1;
        dest[offset + 4] = t3;
        dest[offset + 5] = t5;
        return dest;
    }
    @Mutated public Double2x3 loadRM(double[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Double2x3 loadRM_distinct(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[4] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[3] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    private Double2x3 loadRM_aliased(double[] src, int offset) {
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double t4 = src[offset + 4];
        double t5 = src[offset + 5];
        double[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[4] = t2;
        d[1] = t3;
        d[3] = t4;
        d[5] = t5;
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
    @Mutated public Double2x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadRMRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Double2x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Double2x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double2x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double2x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double2x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Double2x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Double2x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = (float) this.data[4];
        dest[offset + 3] = (float) this.data[1];
        dest[offset + 4] = (float) this.data[3];
        dest[offset + 5] = (float) this.data[5];
        return dest;
    }
    public @Mutated Double2x3 loadRM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[4] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[3] = src[offset + 4];
        this.data[5] = src[offset + 5];
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
    @Mutated public Double2x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Double2x3 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }
    public Double2x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double2x3 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Double2x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double2x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    @Mutated public Double2x3 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }
    public Double2x3 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
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
    public @Mutated Double2x3 loadCM(double[] src, int offset, int stride) {
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
    @Mutated public Double2x3 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadCMRelative(DoubleBuffer buf, int stride) {
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
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }
    public Double2x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }
    public Double2x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Double2x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Double2x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[_p1] = (float) this.data[2];
        dest[_p1 + 1] = (float) this.data[3];
        dest[_p2] = (float) this.data[4];
        dest[_p2 + 1] = (float) this.data[5];
        return dest;
    }
    public @Mutated Double2x3 loadCM(float[] src, int offset, int stride) {
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
    @Mutated public Double2x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }
    public Double2x3 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }
    public Double2x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    @Mutated public Double2x3 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }
    public Double2x3 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }
    private double[] storeRM_distinct(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[4];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        dest[_p1 + 2] = this.data[5];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset, int stride) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        int _p1 = offset + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t4;
        dest[_p1] = t1;
        dest[_p1 + 1] = t3;
        dest[_p1 + 2] = t5;
        return dest;
    }
    @Mutated public Double2x3 loadRM(double[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Double2x3 loadRM_distinct(double[] src, int offset, int stride) {
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
    private Double2x3 loadRM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[_p1];
        double t4 = src[_p1 + 1];
        double t5 = src[_p1 + 2];
        double[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[4] = t2;
        d[1] = t3;
        d[3] = t4;
        d[5] = t5;
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
    @Mutated public Double2x3 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadRMRelative(DoubleBuffer buf, int stride) {
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
    public Double2x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }
    public Double2x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Double2x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Double2x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = (float) this.data[4];
        dest[_p1] = (float) this.data[1];
        dest[_p1 + 1] = (float) this.data[3];
        dest[_p1 + 2] = (float) this.data[5];
        return dest;
    }
    public @Mutated Double2x3 loadRM(float[] src, int offset, int stride) {
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
    @Mutated public Double2x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadRMRelative(FloatBuffer buf, int stride) {
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
    public Double2x3 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }
    public Double2x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    @Mutated public Double2x3 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }
    public Double2x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
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
    public ByteBuffer storeCM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }
    public Double2x3 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }
    public MemorySegment storeCM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }
    public MemorySegment storeCM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.data[2];
        dest[offset + 4] = (float) this.data[3];
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = (float) this.data[4];
        dest[offset + 7] = (float) this.data[5];
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
    public ByteBuffer storeCM3x3Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }
    public Double2x3 storeCM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3FloatUnsafe(this, address);
    }
    public MemorySegment storeCM3x3Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3Float(this, 0L, dest); }
    public MemorySegment storeCM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Float(this, offset, dest);
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
    public ByteBuffer storeRM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }
    public Double2x3 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }
    public MemorySegment storeRM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3(this, 0L, dest); }
    public MemorySegment storeRM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = (float) this.data[4];
        dest[offset + 3] = (float) this.data[1];
        dest[offset + 4] = (float) this.data[3];
        dest[offset + 5] = (float) this.data[5];
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
    public ByteBuffer storeRM3x3Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }
    public Double2x3 storeRM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3FloatUnsafe(this, address);
    }
    public MemorySegment storeRM3x3Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3Float(this, 0L, dest); }
    public MemorySegment storeRM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3Float(this, offset, dest);
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
    public ByteBuffer storeCM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }
    public Double2x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }
    public MemorySegment storeCM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.data[2];
        dest[offset + 5] = (float) this.data[3];
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = 0.0f;
        dest[offset + 9] = 0.0f;
        dest[offset + 10] = 1.0f;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = (float) this.data[4];
        dest[offset + 13] = (float) this.data[5];
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
    public ByteBuffer storeCM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Double2x3 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }
    public MemorySegment storeCM4x4Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Float(this, 0L, dest); }
    public MemorySegment storeCM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Float(this, offset, dest);
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
    public ByteBuffer storeRM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }
    public Double2x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }
    public MemorySegment storeRM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.data[4];
        dest[offset + 4] = (float) this.data[1];
        dest[offset + 5] = (float) this.data[3];
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = (float) this.data[5];
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
    public ByteBuffer storeRM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Double2x3 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }
    public MemorySegment storeRM4x4Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Float(this, 0L, dest); }
    public MemorySegment storeRM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Float(this, offset, dest);
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
