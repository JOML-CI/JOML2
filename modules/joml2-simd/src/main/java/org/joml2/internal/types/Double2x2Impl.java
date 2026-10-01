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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double2x2} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x2Impl implements Double2x2 {
    public double[] data;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x2SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double2x2SegOpsUnsafe()
            : new Double2x2SegOpsMS();
        static final Double2x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double2x2BbOpsUnsafe()
            : new Double2x2BbOpsApi();
        static final Double2x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double2x2RawOpsUnsafe()
            : new Double2x2RawOpsApi();
    }

    public Double2x2Impl() {
        data = new double[4];
        data[0] = 1;
        data[3] = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Double2x2Impl(double m00, double m01, double m10, double m11) {
        double[] dd = this.data = new double[4];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m01;
        dd[3] = m11;
        this.properties = determineProperties();
    }

    public Double2x2Impl(Double2x2R src) {
        Double2x2Impl s = (Double2x2Impl) src;
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
    private Double2 getColumn_identity(int col, @Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
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
            case 1: _idxSw2 = sd[2]; _idxSw3 = 1.0; break;
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
    private Double2 getColumn_affine(int col, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw4;
        double _idxSw5;
        switch (col) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = 0.0; break;
            case 1: _idxSw4 = sd[2]; _idxSw5 = 1.0; break;
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
    private Double2 getColumn_general(int col, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw6;
        double _idxSw7;
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
    public Double2 getColumn(int col, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getColumn_general(col, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getColumn_translation(col, dest);
        return getColumn_affine(col, dest);
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
    public double getRotationAngle() {
        double[] sd = this.data;
        return Math.atan2(sd[1], sd[0]);
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double2 getRow_translation(int row, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw2;
        double _idxSw3;
        switch (row) {
            case 0: _idxSw2 = 1.0; _idxSw3 = sd[2]; break;
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; break;
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
    private Double2 getRow_affine(int row, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw4;
        double _idxSw5;
        switch (row) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = sd[2]; break;
            case 1: _idxSw4 = 0.0; _idxSw5 = 1.0; break;
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
    private Double2 getRow_general(int row, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw6;
        double _idxSw7;
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
    public Double2 getRow(int row, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getRow_general(row, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getRow_translation(row, dest);
        return getRow_affine(row, dest);
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Double2x2 cofactor_translation(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = -sd[2];
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double2x2 cofactor_affine(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = -sd[2];
        dd[2] = 0.0;
        dd[3] = sd[0];
        dd[0] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double2x2 cofactor_general(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = sd[3];
        double _buf1 = -sd[2];
        dd[2] = -sd[1];
        dd[3] = sd[0];
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 cofactor(@Mutated Double2x2 dest) {
        if (COL_NARROW) return cofactor_narrow(dest);
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    @Mutated public Double2x2 cofactor() {
        if (COL_NARROW) return cofactor_narrow();
        if (Joml.RETURN_NEW) return cofactor(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] dd = this.data;
            dd[1] = -this.data[2];
            dd[2] = 0.0;
            this.properties = 0;
            return this;
        }
        return cofactor_affine(this);
    }

    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    public double determinant() {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t1 = -(sd[2] * sd[1]);
            return java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        } else {
            double[] sd = this.data;
            double _t1 = -(sd[2] * sd[1]);
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
    public double frobeniusNorm() {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[0], sd[0], sd[2] * sd[2]))));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[3]) * (sd[3]) + (((sd[1]) * (sd[1]) + (((sd[0]) * (sd[0]) + (sd[2] * sd[2])))))));
        }
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Double2x2 invert_translation(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = -sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_TRANSLATION;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_fma(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_mulAdd(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_self_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_fma(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_self_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_mulAdd(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_fma(dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_mulAdd(dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 invert(@Mutated Double2x2 dest) {
        if (COL_NARROW) return invert_narrow(dest);
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    @Mutated public Double2x2 invert() {
        if (COL_NARROW) return invert_narrow();
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return invert(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
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
            if (Joml.RETURN_NEW) return invert(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
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
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_translation(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1_inv = 1.0 / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t1_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_orthogonal(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_affine(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[0], sd[2], sd[0]);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_general_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
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
    private Double2x2 invert_degenerate_general_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
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
    private Double2x2 invert_degenerate_fma(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_fma(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    private Double2x2 invert_degenerate_mulAdd(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_mulAdd(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal(dest);
        return invert_degenerate_affine(dest);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(otherData[2] * otherData[1]);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = otherData[3] / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = otherData[0] / _t10;
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_sc6458a17_1_fma(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(otherData[2] * otherData[1]);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = otherData[3] / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = otherData[0] / _t10;
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_sc6458a17_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /**
     * Piece 2 of {@code invertProduct_general}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_general_sc6458a17_1_fma(Double2x2 dest, double[] dd, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35) {
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        dd[1] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
        dd[2] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        dd[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Piece 2 of {@code invertProduct_general}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_general_sc6458a17_1_mulAdd(Double2x2 dest, double[] dd, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35) {
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        dd[1] = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
        dd[2] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
        dd[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(otherData[2] * otherData[1]);
        double _t5 = java.lang.Math.fma(otherData[0], otherData[3], _t1) - java.lang.Math.fma(otherData[2], otherData[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = otherData[0] / _t5;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(otherData[2] * otherData[1]);
        double _t5 = ((otherData[0]) * (otherData[3]) + (_t1)) - ((otherData[2]) * (otherData[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = otherData[0] / _t5;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_fma(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -this.data[2];
        double _t3 = -(otherData[2] * otherData[1]);
        double _t7 = java.lang.Math.fma(otherData[0], otherData[3], _t3) - java.lang.Math.fma(otherData[2], otherData[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t10 = otherData[3] / _t7;
        double _t12 = -(otherData[1] * _t7_inv);
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t10, -(otherData[2] * _t7_inv));
        dd[3] = java.lang.Math.fma(_t1, _t12, otherData[0] / _t7);
        dd[0] = _t10;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -this.data[2];
        double _t3 = -(otherData[2] * otherData[1]);
        double _t7 = ((otherData[0]) * (otherData[3]) + (_t3)) - ((otherData[2]) * (otherData[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t10 = otherData[3] / _t7;
        double _t12 = -(otherData[1] * _t7_inv);
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t10) - (otherData[2] * _t7_inv));
        dd[3] = ((_t1) * (_t12) + (otherData[0] / _t7));
        dd[0] = _t10;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t2 = -(otherData[2] * otherData[1]);
        double _t4 = 1.0 / _t1;
        double _t9 = -(sd[2] / _t1);
        double _t11 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t14 = otherData[3] / _t11;
        double _t16 = -(otherData[1] * _t11_inv);
        dd[1] = _t16 * _t4;
        dd[2] = java.lang.Math.fma(_t9, _t14, -(otherData[2] * _t11_inv));
        dd[3] = java.lang.Math.fma(_t16, _t9, otherData[0] / _t11);
        dd[0] = _t4 * _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t2 = -(otherData[2] * otherData[1]);
        double _t4 = 1.0 / _t1;
        double _t9 = -(sd[2] / _t1);
        double _t11 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t14 = otherData[3] / _t11;
        double _t16 = -(otherData[1] * _t11_inv);
        dd[1] = _t16 * _t4;
        dd[2] = ((_t9) * (_t14) - (otherData[2] * _t11_inv));
        dd[3] = ((_t16) * (_t9) + (otherData[0] / _t11));
        dd[0] = _t4 * _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_identity_identity(Double2x2R other, @Mutated Double2x2 dest) {
        double[] dd = ((Double2x2Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_identity_translation(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = -otherData[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_translation_identity(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = -sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_translation_translation(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = -otherData[2] - sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine_fma(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t2 = 1.0 / _t0;
        dd[0] = _t2;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-sd[2], _t2, -(otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t2 = 1.0 / _t0;
        dd[0] = _t2;
        dd[1] = 0.0;
        dd[2] = ((-sd[2]) * (_t2) - (otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t3 = 1.0 / _t0;
        dd[0] = _t3 * (1.0 / _t1);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] / _t1), _t3, -(otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t3 = 1.0 / _t0;
        dd[0] = _t3 * (1.0 / _t1);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] / _t1)) * (_t3) - (otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] / _t5;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = sd[0] / _t5;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -((Double2x2Impl) other).data[2];
        double _t3 = -(sd[2] * sd[1]);
        double _t7 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t11 = sd[0] / _t7;
        double _t12 = -(sd[1] * _t7_inv);
        dd[0] = java.lang.Math.fma(_t1, _t12, sd[3] / _t7);
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t11, -(sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -((Double2x2Impl) other).data[2];
        double _t3 = -(sd[2] * sd[1]);
        double _t7 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t11 = sd[0] / _t7;
        double _t12 = -(sd[1] * _t7_inv);
        dd[0] = ((_t1) * (_t12) + (sd[3] / _t7));
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t11) - (sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t2 = -(sd[2] * sd[1]);
        double _t5 = 1.0 / _t1;
        double _t7 = -(otherData[2] / _t1);
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t2) - java.lang.Math.fma(sd[2], sd[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t17 = sd[3] / _t11;
        double _t18 = sd[0] / _t11;
        double _t20 = -(sd[1] * _t11_inv);
        double _t21 = -(sd[2] * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        dd[0] = java.lang.Math.fma(_t7, _t20, _t22) + java.lang.Math.fma(_t5, _t17, -_t22);
        dd[1] = _t20;
        dd[2] = java.lang.Math.fma(_t7, _t18, _t23) + java.lang.Math.fma(_t21, _t5, -_t23);
        dd[3] = _t18;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t2 = -(sd[2] * sd[1]);
        double _t5 = 1.0 / _t1;
        double _t7 = -(otherData[2] / _t1);
        double _t11 = ((sd[0]) * (sd[3]) + (_t2)) - ((sd[2]) * (sd[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t17 = sd[3] / _t11;
        double _t18 = sd[0] / _t11;
        double _t20 = -(sd[1] * _t11_inv);
        double _t21 = -(sd[2] * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        dd[0] = ((_t7) * (_t20) + (_t22)) + ((_t5) * (_t17) - (_t22));
        dd[1] = _t20;
        dd[2] = ((_t7) * (_t18) + (_t23)) + ((_t21) * (_t5) - (_t23));
        dd[3] = _t18;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 invertProduct(Double2x2R other, @Mutated Double2x2 dest) {
        if (COL_NARROW) return invertProduct_narrow(other, dest);
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(other, dest);
        return invertProduct_mulAdd(other, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_fma(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_s1fafb47c_1_fma(other, dest, q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_s1fafb47c_1_mulAdd(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s1fafb47c_1_fma(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma(other, dest);
        return invertProduct_affine_affine_fma(other, dest);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s1fafb47c_1_mulAdd(Double2x2R other, Double2x2 dest, int q) {
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
    @Mutated public Double2x2 invertProduct(Double2x2R other) {
        if (COL_NARROW) return invertProduct_narrow(other);
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd(other);
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_s73a40995_1_fma(other, q);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(Double2x2R other) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_s73a40995_1_mulAdd(other, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s73a40995_1_fma(Double2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma(other, this);
        return invertProduct_affine_affine_fma(other, this);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s73a40995_1_mulAdd(Double2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd(other, this);
        return invertProduct_affine_affine_mulAdd(other, this);
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
    public Double2x2 invertProduct(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        if (COL_NARROW) return invertProduct_narrow(m00, m01, m10, m11, dest);
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(m00, m01, m10, m11, dest);
        return invertProduct_mulAdd(m00, m01, m10, m11, dest);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_fma(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(m01 * m10);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(m00, m01, m10, m11, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        double _t32 = _t22 * _t23;
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        return invertProduct_se106928b_1_fma(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(m01 * m10);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        double _t32 = _t22 * _t23;
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        return invertProduct_se106928b_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /**
     * Piece 2 of {@code invertProduct}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_se106928b_1_fma(Double2x2 dest, double[] dd, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t33, double _t34, double _t35) {
        dd[1] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
        dd[2] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        dd[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Piece 2 of {@code invertProduct}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_se106928b_1_mulAdd(Double2x2 dest, double[] dd, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t33, double _t34, double _t35) {
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
    private Double2x2 invertProduct_degenerate_general_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t12 = otherData[2] * _t0;
        double _t13 = otherData[0] * _t0;
        double _t14 = otherData[3] * _t1;
        double _t15 = otherData[1] * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s4924f1ba_1_fma(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t12 = otherData[2] * _t0;
        double _t13 = otherData[0] * _t0;
        double _t14 = otherData[3] * _t1;
        double _t15 = otherData[1] * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s4924f1ba_1_mulAdd(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    /**
     * Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget. Shared
     * by 2 identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_degenerate_general_s4924f1ba_1_fma(Double2x2 dest, double[] dd, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58) {
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dd[0] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dd[1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dd[2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dd[3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Piece 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget. Shared
     * by 2 identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_degenerate_general_s4924f1ba_1_mulAdd(Double2x2 dest, double[] dd, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58) {
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dd[0] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dd[1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dd[2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dd[3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t6 = otherData[3] * _t0;
        double _t7 = otherData[0] * _t1;
        double _t8 = otherData[2] * _t1;
        double _t9 = otherData[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t6 = otherData[3] * _t0;
        double _t7 = otherData[0] * _t1;
        double _t8 = otherData[2] * _t1;
        double _t9 = otherData[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t10 = otherData[3] * _t2;
        double _t11 = otherData[0] * _t3;
        double _t12 = otherData[2] * _t3;
        double _t13 = otherData[1] * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(sd[2] * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = java.lang.Math.fma(_t11, _t10, _t22) - java.lang.Math.fma(_t12, _t13, _t22);
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = java.lang.Math.fma(_t18, _t28, -(_t12 * _t2 * _t25_inv));
        dd[3] = java.lang.Math.fma(_t30, _t18, _t11 * _t2 / _t25);
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t10 = otherData[3] * _t2;
        double _t11 = otherData[0] * _t3;
        double _t12 = otherData[2] * _t3;
        double _t13 = otherData[1] * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(sd[2] * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = ((_t11) * (_t10) + (_t22)) - ((_t12) * (_t13) + (_t22));
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = ((_t18) * (_t28) - (_t12 * _t2 * _t25_inv));
        dd[3] = ((_t30) * (_t18) + (_t11 * _t2 / _t25));
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t5 = sd[0] * _t0;
        double _t11 = otherData[3] * _t1;
        double _t12 = otherData[0] * _t2;
        double _t13 = otherData[2] * _t2;
        double _t14 = otherData[1] * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(sd[2] * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = java.lang.Math.fma(_t12, _t11, _t23) - java.lang.Math.fma(_t13, _t14, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = java.lang.Math.fma(_t21, _t29, -(_t13 * _t1 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t21, _t12 * _t1 / _t26);
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t5 = sd[0] * _t0;
        double _t11 = otherData[3] * _t1;
        double _t12 = otherData[0] * _t2;
        double _t13 = otherData[2] * _t2;
        double _t14 = otherData[1] * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(sd[2] * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = ((_t12) * (_t11) + (_t23)) - ((_t13) * (_t14) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = ((_t21) * (_t29) - (_t13 * _t1 * _t26_inv));
        dd[3] = ((_t31) * (_t21) + (_t12 * _t1 / _t26));
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t2 = unitScale(sd[0], sd[2], sd[0]);
        double _t9 = otherData[3] * _t0;
        double _t10 = otherData[0] * _t1;
        double _t11 = otherData[2] * _t1;
        double _t12 = otherData[1] * _t0;
        double _t13 = sd[0] * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(sd[2] * _t2 / _t13);
        double _t26 = java.lang.Math.fma(_t10, _t9, _t21) - java.lang.Math.fma(_t11, _t12, _t21);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = java.lang.Math.fma(_t23, _t29, -(_t11 * _t0 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t23, _t10 * _t0 / _t26);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t2 = unitScale(sd[0], sd[2], sd[0]);
        double _t9 = otherData[3] * _t0;
        double _t10 = otherData[0] * _t1;
        double _t11 = otherData[2] * _t1;
        double _t12 = otherData[1] * _t0;
        double _t13 = sd[0] * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(sd[2] * _t2 / _t13);
        double _t26 = ((_t10) * (_t9) + (_t21)) - ((_t11) * (_t12) + (_t21));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = ((_t23) * (_t29) - (_t11 * _t0 * _t26_inv));
        dd[3] = ((_t31) * (_t23) + (_t10 * _t0 / _t26));
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_translation(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] * _t0 / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_affine(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t2 = otherData[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] * _t0 / _t2);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_identity(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0 / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t1 = unitScale(1.0, sd[2], 1.0);
        double _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t1), _t5, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t1 = unitScale(1.0, sd[2], 1.0);
        double _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t1)) * (_t5) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t4 = otherData[0] * _t2;
        double _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t0), _t6, -(otherData[2] * _t2 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t4 = otherData[0] * _t2;
        double _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t0 / _t0)) * (_t6) - (otherData[2] * _t2 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_identity(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2 = sd[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0 / _t2);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t1 = unitScale(1.0, sd[2], 1.0);
        double _t4 = sd[0] * _t1;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t1 = unitScale(1.0, sd[2], 1.0);
        double _t4 = sd[0] * _t1;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t3 = sd[0] * _t0;
        double _t5 = otherData[0] * _t1;
        double _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t3), _t7, -(otherData[2] * _t1 / _t5));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t3 = sd[0] * _t0;
        double _t5 = otherData[0] * _t1;
        double _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t0 / _t3)) * (_t7) - (otherData[2] * _t1 / _t5));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t2 = unitScale(sd[0], sd[2], sd[0]);
        double _t4 = sd[0] * _t2;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t2 / _t4);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t2 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t4 = otherData[0] * _t0;
        double _t5 = sd[0] * _t1;
        double _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t5), _t7, -(otherData[2] * _t0 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t4 = otherData[0] * _t0;
        double _t5 = sd[0] * _t1;
        double _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t5)) * (_t7) - (otherData[2] * _t0 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t10 = sd[1] * _t2;
        double _t11 = sd[0] * _t3;
        double _t12 = sd[3] * _t2;
        double _t13 = sd[2] * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(otherData[2] * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = java.lang.Math.fma(_t11, _t12, _t23) - java.lang.Math.fma(_t13, _t10, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double _t33 = -(_t10 * _t3 * _t26_inv);
        double _t34 = _t31 * _t15;
        double _t35 = _t18 * _t32;
        dd[0] = java.lang.Math.fma(_t33, _t18, _t34) + java.lang.Math.fma(_t31, _t15, -_t34);
        dd[1] = _t33;
        dd[2] = java.lang.Math.fma(-(_t13 * _t2 * _t26_inv), _t15, _t35) + java.lang.Math.fma(_t18, _t32, -_t35);
        dd[3] = _t32;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, otherData[2], 1.0);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t10 = sd[1] * _t2;
        double _t11 = sd[0] * _t3;
        double _t12 = sd[3] * _t2;
        double _t13 = sd[2] * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(otherData[2] * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = ((_t11) * (_t12) + (_t23)) - ((_t13) * (_t10) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double _t33 = -(_t10 * _t3 * _t26_inv);
        double _t34 = _t31 * _t15;
        double _t35 = _t18 * _t32;
        dd[0] = ((_t33) * (_t18) + (_t34)) + ((_t31) * (_t15) - (_t34));
        dd[1] = _t33;
        dd[2] = ((_t35) - (_t13 * _t2 * _t26_inv) * (_t15)) + ((_t18) * (_t32) - (_t35));
        dd[3] = _t32;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t9 = sd[1] * _t0;
        double _t10 = sd[0] * _t1;
        double _t11 = sd[3] * _t0;
        double _t12 = sd[2] * _t1;
        double _t14 = otherData[0] * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(otherData[2] * _t2 / _t14);
        double _t27 = java.lang.Math.fma(_t10, _t11, _t22) - java.lang.Math.fma(_t12, _t9, _t22);
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double _t34 = -(_t9 * _t1 * _t27_inv);
        double _t35 = _t32 * _t19;
        double _t36 = _t24 * _t33;
        dd[0] = java.lang.Math.fma(_t34, _t24, _t35) + java.lang.Math.fma(_t32, _t19, -_t35);
        dd[1] = _t34;
        dd[2] = java.lang.Math.fma(-(_t12 * _t0 * _t27_inv), _t19, _t36) + java.lang.Math.fma(_t24, _t33, -_t36);
        dd[3] = _t33;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t2 = unitScale(otherData[0], otherData[2], otherData[0]);
        double _t9 = sd[1] * _t0;
        double _t10 = sd[0] * _t1;
        double _t11 = sd[3] * _t0;
        double _t12 = sd[2] * _t1;
        double _t14 = otherData[0] * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(otherData[2] * _t2 / _t14);
        double _t27 = ((_t10) * (_t11) + (_t22)) - ((_t12) * (_t9) + (_t22));
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double _t34 = -(_t9 * _t1 * _t27_inv);
        double _t35 = _t32 * _t19;
        double _t36 = _t24 * _t33;
        dd[0] = ((_t34) * (_t24) + (_t35)) + ((_t32) * (_t19) - (_t35));
        dd[1] = _t34;
        dd[2] = ((_t36) - (_t12 * _t0 * _t27_inv) * (_t19)) + ((_t24) * (_t33) - (_t36));
        dd[3] = _t33;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_degenerate_s10c579a1_1_fma(other, dest, q);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
        return invertProduct_degenerate_s10c579a1_1_mulAdd(other, dest, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_degenerate_s10c579a1_1_fma(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_fma(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale(sd[0], sd[2], sd[0]);
            double _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_fma(other, dest);
        return invertProduct_degenerate_affine_affine_fma(other, dest);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_degenerate_s10c579a1_1_mulAdd(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_mulAdd(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale(sd[0], sd[2], sd[0]);
            double _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale(1.0, otherData[2], 1.0);
            double _t2 = unitScale(sd[0], sd[2], sd[0]);
            double _t4 = sd[0] * _t2;
            double _t6 = _t0 / _t0;
            dd[0] = _t6 * (_t2 / _t4);
            dd[1] = 0.0;
            dd[2] = ((-(sd[2] * _t2 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        return invertProduct_degenerate_affine_affine_mulAdd(other, dest);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double _t0 = unitScale(m00, m01, m00);
        double _t1 = unitScale(m10, m11, m10);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_sa2cfbf8e_1_fma(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double _t0 = unitScale(m00, m01, m00);
        double _t1 = unitScale(m10, m11, m10);
        double _t2 = unitScale(sd[1], sd[3], sd[1]);
        double _t3 = unitScale(sd[0], sd[2], sd[0]);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_sa2cfbf8e_1_mulAdd(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    /**
     * Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_degenerate_sa2cfbf8e_1_fma(Double2x2 dest, double[] dd, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60) {
        double _t61 = _t55 * _t53;
        dd[0] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dd[1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dd[2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dd[3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_degenerate_sa2cfbf8e_1_mulAdd(Double2x2 dest, double[] dd, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60) {
        double _t61 = _t55 * _t53;
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
    private Double2x2 normal_affine_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_fma(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_mulAdd(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_self_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_fma(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_self_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_mulAdd(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_fma(dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] * _t5_inv;
        double _buf1 = -(sd[2] * _t5_inv);
        dd[2] = -(sd[1] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_mulAdd(dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = sd[3] * _t5_inv;
        double _buf1 = -(sd[2] * _t5_inv);
        dd[2] = -(sd[1] * _t5_inv);
        dd[3] = sd[0] * _t5_inv;
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 normal(@Mutated Double2x2 dest) {
        if (COL_NARROW) return normal_narrow(dest);
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_fma(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    @Mutated public Double2x2 normal() {
        if (COL_NARROW) return normal_narrow();
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return normal(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                double[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_fma(this);
        } else {
            if (Joml.RETURN_NEW) return normal(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                double[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_mulAdd(this);
        }
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_translation(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t1_inv = 1.0 / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = -(sd[2] * _t0) * _t1_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_orthogonal(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(1.0, sd[2], 1.0);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_affine(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[0], sd[2], sd[0]);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_general_fma(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
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
    private Double2x2 normal_degenerate_general_mulAdd(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale(sd[1], sd[3], sd[1]);
        double _t1 = unitScale(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
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
    private Double2x2 normal_degenerate_fma(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_fma(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    private Double2x2 normal_degenerate_mulAdd(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_mulAdd(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal(dest);
        return normal_degenerate_affine(dest);
    }

    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public double trace() {
        double[] sd = this.data;
        return sd[0] + sd[3];
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code transpose}; reached only through it.
     */
    private Double2x2 transpose_translation(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = sd[2];
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code transpose}; reached only through it.
     */
    private Double2x2 transpose_affine(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double2x2 transpose_general(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        double _buf0 = sd[2];
        dd[2] = sd[1];
        dd[3] = sd[3];
        dd[1] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code transpose}, specialized by runtime matrix
     * properties; reached only through the public {@code transpose} dispatcher.
     */
    private Double2x2 transpose_general_self(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = sd[2];
        dd[2] = sd[1];
        dd[1] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 transpose(@Mutated Double2x2 dest) {
        if (COL_NARROW) return transpose_narrow(dest);
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
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
    @Mutated public Double2x2 transpose() {
        if (COL_NARROW) return transpose_narrow();
        if (Joml.RETURN_NEW) return transpose(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general_self(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        double[] dd = this.data;
        dd[1] = this.data[2];
        dd[2] = 0.0;
        this.properties = 0;
        return this;
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
    public Double2x2 add(Double2x2R other, @Mutated Double2x2 dest) {
        if (COL_NARROW) return add_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 4; _i += _lanes) {
            DoubleVector.fromArray(_sp, otherData, _i).add(DoubleVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 4; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
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
    public Double2x2 add(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m01 + sd[2];
        dd[3] = m11 + sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_fma(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_mulAdd(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar;
        dd[1] = 0.0;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_self(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_fma(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = 0.0;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_mulAdd(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = 0.0;
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_self(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[2] = scalar * sd[2];
        dd[3] = scalar;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_fma(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_mulAdd(double scalar, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 mul(double scalar, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? mul_general_fma(scalar, dest) : mul_general_mulAdd(scalar, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = scalar;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = scalar;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return (org.joml2.internal.FmaSwitch.ON ? mul_translation_fma(scalar, dest) : mul_translation_mulAdd(scalar, dest));
        return (org.joml2.internal.FmaSwitch.ON ? mul_affine_fma(scalar, dest) : mul_affine_mulAdd(scalar, dest));
    }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x2 mul(double scalar) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                dd[0] = scalar;
                dd[3] = scalar;
                this.properties = 0;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_self(scalar, this);
            return mul_affine_self(scalar, this);
        } else {
            if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(scalar, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
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
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 negate(@Mutated Double2x2 dest) {
        double[] sd = this.data;
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
    public Double2x2 sub(Double2x2R other, @Mutated Double2x2 dest) {
        if (COL_NARROW) return sub_narrow(other, dest);
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 4; _i += _lanes) {
            DoubleVector.fromArray(_sp, sd, _i).sub(DoubleVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 4; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
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
    public Double2x2 sub(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
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
    @Mutated public Double2x2 set(Double2x2R v) {
        if (COL_NARROW) return set_narrow(v);
        double[] dd = this.data;
        double[] vData = ((Double2x2Impl) v).data;
        DoubleVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
        this.properties = ((Double2x2Impl) v).properties;
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
    @Mutated public Double2x2 set(double m00, double m01, double m10, double m11) {
        double[] dd = this.data;
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
    @Mutated public Double2x2 set(Double2x3R m) {
        if (COL_NARROW) return set_narrow(m);
        double[] dd = this.data;
        double[] mData = ((Double2x3Impl) m).data;
        DoubleVector.fromArray(COL_SPECIES, mData, 0).intoArray(dd, 0);
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
    @Mutated public Double2x2 set(Double3x3R m) {
        double[] dd = this.data;
        double[] mData = ((Double3x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[3];
        dd[3] = mData[4];
        this.properties = determineProperties();
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
    public Float2x2 toFloat(@Mutated Float2x2 dest) {
        double[] sd = this.data;
        float[] dd = ((Float2x2Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        ((Float2x2Impl) dest).properties = this.properties;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code to2x3}; reached only through it.
     */
    private Double2x3 to2x3_translation(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = sd[2];
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code to2x3}; reached only through it.
     */
    private Double2x3 to2x3_affine(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0;
        dd[2] = sd[2];
        dd[3] = 1.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_general(@Mutated Double2x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x3Impl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double2x3 to2x3(@Mutated Double2x3 dest) {
        if (COL_NARROW) return to2x3_narrow(dest);
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return to2x3_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x3Impl) dest).data;
            System.arraycopy(DATA_1, 0, dd, 0, 6);
            ((Double2x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to2x3_translation(dest);
        return to2x3_affine(dest);
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
        dd[3] = sd[2];
        VEC_3.intoArray(dd, 4);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_affine(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sd[2];
        VEC_3.intoArray(dd, 4);
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_general(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = 0.0;
        dd[3] = sd[2];
        dd[4] = sd[3];
        VEC_4.intoArray(dd, 5);
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double3x3 to3x3(@Mutated Double3x3 dest) {
        if (COL_NARROW) return to3x3_narrow(dest);
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return to3x3_general(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double3x3Impl) dest).data;
            System.arraycopy(DATA_2, 0, dd, 0, 9);
            ((Double3x3Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to3x3_translation(dest);
        return to3x3_affine(dest);
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
    public Double2x2 decomposeLDU(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        if (!org.joml2.internal.FmaSwitch.ON) return decomposeLDU_mulAdd(lower, diagonal, upper);
        double[] sd = this.data;
        double[] lowerData = ((Double2x2Impl) lower).data;
        double[] diagonalData = ((Double2x2Impl) diagonal).data;
        double[] upperData = ((Double2x2Impl) upper).data;
        double _rcp0 = 1.0 / sd[0];
        double _sp0 = sd[1] * _rcp0;
        lowerData[1] = _sp0;
        diagonalData[0] = sd[0];
        lowerData[0] = 1.0;
        diagonalData[1] = 0.0;
        diagonalData[3] = java.lang.Math.fma(-sd[2], _sp0, sd[3]);
        lowerData[3] = 1.0;
        upperData[0] = 1.0;
        upperData[1] = 0.0;
        upperData[2] = sd[2] * _rcp0;
        lowerData[2] = 0.0;
        diagonalData[2] = 0.0;
        upperData[3] = 1.0;
        ((Double2x2Impl) lower).properties = 0;
        ((Double2x2Impl) diagonal).properties = 0;
        ((Double2x2Impl) upper).properties = Joml.BIT_TRANSLATION;
        return this;
    }

    /** {@code decomposeLDU} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 decomposeLDU_mulAdd(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        double[] sd = this.data;
        double[] lowerData = ((Double2x2Impl) lower).data;
        double[] diagonalData = ((Double2x2Impl) diagonal).data;
        double[] upperData = ((Double2x2Impl) upper).data;
        double _rcp0 = 1.0 / sd[0];
        double _sp0 = sd[1] * _rcp0;
        lowerData[1] = _sp0;
        diagonalData[0] = sd[0];
        lowerData[0] = 1.0;
        diagonalData[1] = 0.0;
        diagonalData[3] = ((sd[3]) - (sd[2]) * (_sp0));
        lowerData[3] = 1.0;
        upperData[0] = 1.0;
        upperData[1] = 0.0;
        upperData[2] = sd[2] * _rcp0;
        lowerData[2] = 0.0;
        diagonalData[2] = 0.0;
        upperData[3] = 1.0;
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
    @Mutated public Double2x2 makeIdentity() {
        if (COL_NARROW) return makeIdentity_narrow();
        double[] dd = this.data;
        DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
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
    public Double2x2 lerp(Double2x2R other, double t, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, otherData[0] - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, otherData[1] - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, otherData[2] - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, otherData[3] - sd[3], sd[3]);
            ((Double2x2Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double2x2Impl) other).properties) >> 1);
            return dest;
        } else {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((t) * (otherData[0] - sd[0]) + (sd[0]));
            dd[1] = ((t) * (otherData[1] - sd[1]) + (sd[1]));
            dd[2] = ((t) * (otherData[2] - sd[2]) + (sd[2]));
            dd[3] = ((t) * (otherData[3] - sd[3]) + (sd[3]));
            ((Double2x2Impl) dest).properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double2x2Impl) other).properties) >> 1);
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
     * than the other one (1e17 towards 1 ends at 0).
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
    public Double2x2 lerp(double m00, double m01, double m10, double m11, double t, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
            dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
            dd[2] = java.lang.Math.fma(t, m01 - sd[2], sd[2]);
            dd[3] = java.lang.Math.fma(t, m11 - sd[3], sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            double[] sd = this.data;
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
    private Double2x2 mul_general_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[3]);
        double _buf1 = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[0] = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], rightData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = ((rightData[0]) * (sd[1]) + (rightData[1] * sd[3]));
        double _buf1 = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[0] = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        dd[3] = ((rightData[2]) * (sd[1]) + (rightData[3] * sd[3]));
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_fma(Double2x2R right, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[1], sd[2], rightData[0]);
        dd[2] = java.lang.Math.fma(rightData[3], sd[2], rightData[2]);
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_mulAdd(Double2x2R right, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = ((rightData[1]) * (sd[2]) + (rightData[0]));
        dd[2] = ((rightData[3]) * (sd[2]) + (rightData[2]));
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[2]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], rightData[3] * sd[2]);
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = ((rightData[0]) * (sd[0]) + (rightData[1] * sd[2]));
        dd[2] = ((rightData[2]) * (sd[0]) + (rightData[3] * sd[2]));
        dd[1] = rightData[1];
        dd[3] = rightData[3];
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_translation(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = rightData[2] + sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_affine(Double2x2R right, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = rightData[0];
        dd[1] = 0.0;
        dd[2] = rightData[2] + sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_translation_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[3] = 1.0;
        dd[0] = sd[0];
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = rightData[0] * sd[0];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = rightData[0] * sd[0];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], sd[3]);
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = sd[0];
        dd[1] = sd[1];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = ((rightData[2]) * (sd[1]) + (sd[3]));
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = sd[0];
        dd[1] = sd[1];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine_fma(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(rightData[2], sd[1], sd[3]);
        dd[1] = rightData[0] * sd[1];
        dd[2] = java.lang.Math.fma(rightData[2], sd[0], sd[2]);
        dd[0] = rightData[0] * sd[0];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = ((rightData[2]) * (sd[1]) + (sd[3]));
        dd[1] = rightData[0] * sd[1];
        dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
        dd[0] = rightData[0] * sd[0];
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 mul(Double2x2R right, @Mutated Double2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        int p = this.properties;
        int q = ((Double2x2Impl) right).properties;
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
    private Double2x2 mul_mulAdd(Double2x2R right, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) right).properties;
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
            double[] sd = this.data;
            double[] rightData = ((Double2x2Impl) right).data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[1] = 0.0;
            dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
            dd[3] = 1.0;
            dd[0] = sd[0];
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
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
    @Mutated public Double2x2 mul(Double2x2R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right);
        int p = this.properties;
        int q = ((Double2x2Impl) right).properties;
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
    private Double2x2 mul_mulAdd(Double2x2R right) {
        int p = this.properties;
        int q = ((Double2x2Impl) right).properties;
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
            double[] sd = this.data;
            double[] rightData = ((Double2x2Impl) right).data;
            double[] dd = this.data;
            dd[1] = 0.0;
            dd[2] = ((rightData[2]) * (sd[0]) + (sd[2]));
            dd[3] = 1.0;
            dd[0] = sd[0];
            this.properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
            return this;
        }
        return mul_affine_affine_mulAdd(right, this);
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
    public Double2x2 mul(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _buf0 = java.lang.Math.fma(m00, sd[0], m10 * sd[2]);
            double _buf1 = java.lang.Math.fma(m00, sd[1], m10 * sd[3]);
            dd[2] = java.lang.Math.fma(m01, sd[0], m11 * sd[2]);
            dd[3] = java.lang.Math.fma(m01, sd[1], m11 * sd[3]);
            dd[0] = _buf0;
            dd[1] = _buf1;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _buf0 = ((m00) * (sd[0]) + (m10 * sd[2]));
            double _buf1 = ((m00) * (sd[1]) + (m10 * sd[3]));
            dd[2] = ((m01) * (sd[0]) + (m11 * sd[2]));
            dd[3] = ((m01) * (sd[1]) + (m11 * sd[3]));
            dd[0] = _buf0;
            dd[1] = _buf1;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = java.lang.Math.fma(otherData[1], sd[0], otherData[3] * sd[1]);
        double _buf1 = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[0] = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3] * sd[3]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = ((otherData[1]) * (sd[0]) + (otherData[3] * sd[1]));
        double _buf1 = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[0] = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3] * sd[3]));
        dd[1] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_fma(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3]);
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3]));
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0];
        dd[1] = otherData[1];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = java.lang.Math.fma(otherData[1], sd[2], otherData[3]);
        dd[1] = otherData[1] * sd[0];
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0] * sd[0];
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[3] = ((otherData[1]) * (sd[2]) + (otherData[3]));
        dd[1] = otherData[1] * sd[0];
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0] * sd[0];
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_translation(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = otherData[2] + sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_affine_fma(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[3] = 1.0;
        dd[0] = otherData[0];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[3] = 1.0;
        dd[0] = otherData[0];
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_translation(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0;
        dd[2] = otherData[2] + sd[2];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2]);
        dd[0] = otherData[0] * sd[0];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[1] = 0.0;
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2]));
        dd[0] = otherData[0] * sd[0];
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[2], sd[1], sd[0]);
        dd[2] = java.lang.Math.fma(otherData[2], sd[3], sd[2]);
        dd[1] = sd[1];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = ((otherData[2]) * (sd[1]) + (sd[0]));
        dd[2] = ((otherData[2]) * (sd[3]) + (sd[2]));
        dd[1] = sd[1];
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine_fma(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = java.lang.Math.fma(otherData[0], sd[0], otherData[2] * sd[1]);
        dd[2] = java.lang.Math.fma(otherData[0], sd[2], otherData[2] * sd[3]);
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _buf0 = ((otherData[0]) * (sd[0]) + (otherData[2] * sd[1]));
        dd[2] = ((otherData[0]) * (sd[2]) + (otherData[2] * sd[3]));
        dd[1] = sd[1];
        dd[3] = sd[3];
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 preMul(Double2x2R other, @Mutated Double2x2 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
    private Double2x2 preMul_mulAdd(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
    @Mutated public Double2x2 preMul(Double2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other);
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
    private Double2x2 preMul_mulAdd(Double2x2R other) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
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
    public Double2x2 preMul(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _buf0 = java.lang.Math.fma(m00, sd[0], m01 * sd[1]);
            dd[1] = java.lang.Math.fma(m10, sd[0], m11 * sd[1]);
            double _buf1 = java.lang.Math.fma(m00, sd[2], m01 * sd[3]);
            dd[3] = java.lang.Math.fma(m10, sd[2], m11 * sd[3]);
            dd[0] = _buf0;
            dd[2] = _buf1;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _buf0 = ((m00) * (sd[0]) + (m01 * sd[1]));
            dd[1] = ((m10) * (sd[0]) + (m11 * sd[1]));
            double _buf1 = ((m00) * (sd[2]) + (m01 * sd[3]));
            dd[3] = ((m10) * (sd[2]) + (m11 * sd[3]));
            dd[0] = _buf0;
            dd[2] = _buf1;
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
    public Double2x2 addScaled(Double2x2R other, double weight, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, otherData[0], sd[0]);
            dd[1] = java.lang.Math.fma(weight, otherData[1], sd[1]);
            dd[2] = java.lang.Math.fma(weight, otherData[2], sd[2]);
            dd[3] = java.lang.Math.fma(weight, otherData[3], sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = ((weight) * (otherData[0]) + (sd[0]));
            dd[1] = ((weight) * (otherData[1]) + (sd[1]));
            dd[2] = ((weight) * (otherData[2]) + (sd[2]));
            dd[3] = ((weight) * (otherData[3]) + (sd[3]));
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
    public Double2x2 addScaled(double m00, double m01, double m10, double m11, double weight, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m01, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m11, sd[3]);
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        } else {
            double[] sd = this.data;
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
    @Mutated public Double2x2 makeOuterProduct(Double2R col, Double2R row) {
        double[] colData = ((Double2Impl) col).data;
        double[] rowData = ((Double2Impl) row).data;
        double colX = colData[0];
        double colY = colData[1];
        double rowX = rowData[0];
        double rowY = rowData[1];
        double[] dd = this.data;
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
    @Mutated public Double2x2 makeOuterProduct(double colX, double colY, double rowX, double rowY) {
        double[] dd = this.data;
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
    @Mutated public Double2x2 makeRotation(double angle) {
        double[] dd = this.data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
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
    @Mutated public Double2x2 makeScaling(Double2R v) {
        double[] vData = ((Double2Impl) v).data;
        double vY = vData[1];
        double[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = 0.0;
        dd[2] = 0.0;
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
    @Mutated public Double2x2 makeScaling(double vX, double vY) {
        double[] dd = this.data;
        dd[0] = vX;
        dd[1] = 0.0;
        dd[2] = 0.0;
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
    @Mutated public Double2x2 makeScaling(double s) {
        double[] dd = this.data;
        dd[0] = s;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = s;
        this.properties = 0;
        return this;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_translation_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[3] = java.lang.Math.fma(sd[2], _t1, _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -_t1);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_translation_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dd[0] = _t0;
        dd[1] = _t1;
        dd[3] = ((sd[2]) * (_t1) + (_t0));
        dd[2] = ((sd[2]) * (_t0) - (_t1));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_affine_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dd[1] = sd[0] * _t1;
        dd[0] = sd[0] * _t0;
        dd[3] = java.lang.Math.fma(sd[2], _t1, _t0);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -_t1);
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_affine_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dd[1] = sd[0] * _t1;
        dd[0] = sd[0] * _t0;
        dd[3] = ((sd[2]) * (_t1) + (_t0));
        dd[2] = ((sd[2]) * (_t0) - (_t1));
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_general_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = java.lang.Math.fma(sd[0], _t0, -(sd[1] * _t1));
        dd[1] = java.lang.Math.fma(sd[0], _t1, sd[1] * _t0);
        double _buf1 = java.lang.Math.fma(sd[2], _t0, -(sd[3] * _t1));
        dd[3] = java.lang.Math.fma(sd[2], _t1, sd[3] * _t0);
        dd[0] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_general_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = ((sd[0]) * (_t0) - (sd[1] * _t1));
        dd[1] = ((sd[0]) * (_t1) + (sd[1] * _t0));
        double _buf1 = ((sd[2]) * (_t0) - (sd[3] * _t1));
        dd[3] = ((sd[2]) * (_t1) + (sd[3] * _t0));
        dd[0] = _buf0;
        dd[2] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 preRotate(double angle, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? preRotate_general_fma(angle, dest) : preRotate_general_mulAdd(angle, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Double2x2 preRotate(double angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_fma(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
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
            if (Joml.RETURN_NEW) return preRotate(angle, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_mulAdd(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
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
    public Double2x2 preScale(Double2R v, @Mutated Double2x2 dest) {
        double[] vData = ((Double2Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = sd[2] * vX;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
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
    @Mutated public Double2x2 preScale(Double2R v) {
        double[] vData = ((Double2Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x2 preScale_translation_self(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = vX;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine_self(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_general(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vY;
        dd[2] = sd[2] * vX;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 preScale(double vX, double vY, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = sd[2] * vX;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0;
        dd[2] = sd[2] * vX;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Double2x2 preScale(double vX, double vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x2 preScale_translation_self(double s, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s;
        dd[2] = s * sd[2];
        dd[3] = s;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine_self(double s, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[2] = s * sd[2];
        dd[3] = s;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_general(double s, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = s * sd[1];
        dd[2] = s * sd[2];
        dd[3] = s * sd[3];
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 preScale(double s, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = s;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0;
            dd[2] = s * sd[2];
            dd[3] = s;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = 0.0;
        dd[2] = s * sd[2];
        dd[3] = s;
        ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Double2x2 preScale(double s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = s;
            dd[3] = s;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(s, this);
        return preScale_affine_self(s, this);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_translation_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        dd[0] = java.lang.Math.fma(sd[2], _t0, _t1);
        dd[1] = _t0;
        dd[2] = java.lang.Math.fma(sd[2], _t1, -_t0);
        dd[3] = _t1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_translation_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        dd[0] = ((sd[2]) * (_t0) + (_t1));
        dd[1] = _t0;
        dd[2] = ((sd[2]) * (_t1) - (_t0));
        dd[3] = _t1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_affine_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        dd[1] = _t1;
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = _t0;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_affine_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        dd[1] = _t1;
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = _t0;
        dd[0] = _buf0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_general_fma(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = java.lang.Math.fma(sd[0], _t0, sd[2] * _t1);
        double _buf1 = java.lang.Math.fma(sd[1], _t0, sd[3] * _t1);
        dd[2] = java.lang.Math.fma(sd[2], _t0, -(sd[0] * _t1));
        dd[3] = java.lang.Math.fma(sd[3], _t0, -(sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_general_mulAdd(double angle, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _buf0 = ((sd[0]) * (_t0) + (sd[2] * _t1));
        double _buf1 = ((sd[1]) * (_t0) + (sd[3] * _t1));
        dd[2] = ((sd[2]) * (_t0) - (sd[0] * _t1));
        dd[3] = ((sd[3]) * (_t0) - (sd[1] * _t1));
        dd[0] = _buf0;
        dd[1] = _buf1;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 rotate(double angle, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? rotate_general_fma(angle, dest) : rotate_general_mulAdd(angle, dest));
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dd[0] = _t0;
            dd[1] = _t1;
            dd[2] = -_t1;
            dd[3] = _t0;
            ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Double2x2 rotate(double angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return rotate(angle, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_fma(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
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
            if (Joml.RETURN_NEW) return rotate(angle, Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_mulAdd(angle, this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = this.data;
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
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
    public Double2x2 scale(Double2R v, @Mutated Double2x2 dest) {
        double[] vData = ((Double2Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = sd[2] * vY;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
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
    @Mutated public Double2x2 scale(Double2R v) {
        double[] vData = ((Double2Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    private Double2x2 scale_translation_self(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = vX;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x2 scale_affine_self(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x2 scale_general(double vX, double vY, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = sd[1] * vX;
        dd[2] = sd[2] * vY;
        dd[3] = sd[3] * vY;
        ((Double2x2Impl) dest).properties = 0;
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
    public Double2x2 scale(double vX, double vY, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = vX;
            dd[1] = 0.0;
            dd[2] = sd[2] * vY;
            dd[3] = vY;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] * vX;
        dd[1] = 0.0;
        dd[2] = sd[2] * vY;
        dd[3] = vY;
        ((Double2x2Impl) dest).properties = 0;
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
    @Mutated public Double2x2 scale(double vX, double vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return scale_general(vX, vY, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
            dd[0] = vX;
            dd[3] = vY;
            this.properties = 0;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return scale_translation_self(vX, vY, this);
        return scale_affine_self(vX, vY, this);
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
    public Double2x2 scale(double s, @Mutated Double2x2 dest) {
        int p_d2 = this.properties;
        if ((p_d2 & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, dest);
        if ((p_d2 & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = s;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        if ((p_d2 & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = s;
            dd[1] = 0.0;
            dd[2] = s * sd[2];
            dd[3] = s;
            ((Double2x2Impl) dest).properties = 0;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = s * sd[0];
        dd[1] = 0.0;
        dd[2] = s * sd[2];
        dd[3] = s;
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
    @Mutated public Double2x2 scale(double s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preScale_general(s, this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = this.data;
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
    public Double2 mul(Double2R v, @Mutated Double2 dest) {
        double[] vData = ((Double2Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(vX, vY, dest);
            return mul_affine_fma(vX, vY, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_mulAdd(vX, vY, dest);
            return mul_affine_mulAdd(vX, vY, dest);
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_translation_fma(double vX, double vY, @Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(this.data[2], vY, vX);
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_translation_mulAdd(double vX, double vY, @Mutated Double2 dest) {
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((this.data[2]) * (vY) + (vX));
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_affine_fma(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_affine_mulAdd(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = ((sd[0]) * (vX) + (sd[2] * vY));
        dd[1] = vY;
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general_fma(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.fma(sd[0], vX, sd[2] * vY);
        dd[1] = java.lang.Math.fma(sd[1], vX, sd[3] * vY);
        return dest;
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general_mulAdd(double vX, double vY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
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
    public Double2 mul(double vX, double vY, @Mutated Double2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_fma(vX, vY, dest);
            return mul_affine_fma(vX, vY, dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(vX, vY, dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2Impl) dest).data;
                dd[0] = vX;
                dd[1] = vY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_mulAdd(vX, vY, dest);
            return mul_affine_mulAdd(vX, vY, dest);
        }
    }

    public double m00() { return data[0]; }
    public double m01() { return data[2]; }
    public double m10() { return data[1]; }
    public double m11() { return data[3]; }

    @Override public String toString() {
        return "Double2x2(\n    " + this.data[0] + ", " + this.data[2] + "\n    " + this.data[1] + ", " + this.data[3] + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x2Impl)) return false;
        Double2x2Impl o = (Double2x2Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(Double2x2R other, double epsilon) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadCM(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
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

    @Mutated public Double2x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadCMRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Double2x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Double2x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    @Mutated public Double2x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }

    @Mutated public Double2x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }

    @Mutated public Double2x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadCM(float[] src, int offset) {
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

    @Mutated public Double2x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeCMFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public Double2x2 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public Double2x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }

    @Mutated public Double2x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }

    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }

    public MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }

    @Mutated public Double2x2 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }

    @Mutated public Double2x2 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }

    private double[] storeRM_distinct(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[2];
        dest[offset + 2] = this.data[1];
        dest[offset + 3] = this.data[3];
        return dest;
    }

    private double[] storeRM_aliased(double[] dest, int offset) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[offset + 2] = t1;
        dest[offset + 3] = t3;
        return dest;
    }

    @Mutated public Double2x2 loadRM(double[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }

    private Double2x2 loadRM_distinct(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }

    private Double2x2 loadRM_aliased(double[] src, int offset) {
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[1] = t2;
        d[3] = t3;
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

    @Mutated public Double2x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadRMRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Double2x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Double2x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    @Mutated public Double2x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    @Mutated public Double2x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }

    @Mutated public Double2x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = (float) this.data[1];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadRM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[offset + 2];
        this.data[3] = src[offset + 3];
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

    @Mutated public Double2x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeRMFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public Double2x2 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }

    @Mutated public Double2x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public Double2x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }

    @Mutated public Double2x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }

    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }

    public MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }

    @Mutated public Double2x2 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }

    @Mutated public Double2x2 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[_p1] = this.data[2];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[_p1];
        this.data[3] = src[_p1 + 1];
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

    @Mutated public Double2x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadCMRelative(DoubleBuffer buf, int stride) {
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

    @Mutated public Double2x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Double2x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    @Mutated public Double2x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    @Mutated public Double2x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }

    @Mutated public Double2x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[_p1] = (float) this.data[2];
        dest[_p1 + 1] = (float) this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadCM(float[] src, int offset, int stride) {
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

    @Mutated public Double2x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadCMRelative(FloatBuffer buf, int stride) {
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

    @Mutated public Double2x2 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Double2x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }

    @Mutated public Double2x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }

    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }

    public MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }

    @Mutated public Double2x2 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }

    @Mutated public Double2x2 loadCMFloat(long offset, MemorySegment src, int stride) {
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
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[3];
        return dest;
    }

    private double[] storeRM_aliased(double[] dest, int offset, int stride) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        int _p1 = offset + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t2;
        dest[_p1] = t1;
        dest[_p1 + 1] = t3;
        return dest;
    }

    @Mutated public Double2x2 loadRM(double[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }

    private Double2x2 loadRM_distinct(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[_p1];
        this.data[3] = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }

    private Double2x2 loadRM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double t1 = src[offset + 1];
        double t2 = src[_p1];
        double t3 = src[_p1 + 1];
        double[] d = this.data;
        d[0] = src[offset];
        d[2] = t1;
        d[1] = t2;
        d[3] = t3;
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

    @Mutated public Double2x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadRMRelative(DoubleBuffer buf, int stride) {
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
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    @Mutated public Double2x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Double2x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    @Mutated public Double2x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    @Mutated public Double2x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }

    @Mutated public Double2x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[_p1] = (float) this.data[1];
        dest[_p1 + 1] = (float) this.data[3];
        return dest;
    }

    @Mutated public Double2x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[2] = src[offset + 1];
        this.data[1] = src[_p1];
        this.data[3] = src[_p1 + 1];
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

    @Mutated public Double2x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeRMFloat(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    @Mutated public Double2x2 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }

    @Mutated public Double2x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Double2x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }

    @Mutated public Double2x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }

    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }

    public MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }

    @Mutated public Double2x2 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }

    @Mutated public Double2x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
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

    public ByteBuffer storeCM3x3(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM3x3Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    public ByteBuffer storeCM3x3Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    public Double2x2 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }

    public MemorySegment storeCM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }

    public MemorySegment storeCM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.data[2];
        dest[offset + 4] = (float) this.data[3];
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

    public ByteBuffer storeCM3x3Float(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM3x3FloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeCM3x3FloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    public Double2x2 storeCM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3FloatUnsafe(this, address);
    }

    public MemorySegment storeCM3x3Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3Float(this, 0L, dest); }

    public MemorySegment storeCM3x3Float(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Float(this, offset, dest);
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

    public ByteBuffer storeRM3x3(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM3x3Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    public ByteBuffer storeRM3x3Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    public Double2x2 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }

    public MemorySegment storeRM3x3(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3(this, 0L, dest); }

    public MemorySegment storeRM3x3(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.data[1];
        dest[offset + 4] = (float) this.data[3];
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

    public ByteBuffer storeRM3x3Float(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM3x3FloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeRM3x3FloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    public Double2x2 storeRM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3FloatUnsafe(this, address);
    }

    public MemorySegment storeRM3x3Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3Float(this, 0L, dest); }

    public MemorySegment storeRM3x3Float(long offset, @Mutated MemorySegment dest) {
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

    public ByteBuffer storeCM4x4(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM4x4Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    public ByteBuffer storeCM4x4Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    public Double2x2 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    public MemorySegment storeCM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    public MemorySegment storeCM4x4(long offset, @Mutated MemorySegment dest) {
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

    public ByteBuffer storeCM4x4Float(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCM4x4FloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeCM4x4FloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    public Double2x2 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }

    public MemorySegment storeCM4x4Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Float(this, 0L, dest); }

    public MemorySegment storeCM4x4Float(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Float(this, offset, dest);
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

    public ByteBuffer storeRM4x4(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM4x4Absolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    public ByteBuffer storeRM4x4Relative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    public Double2x2 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    public MemorySegment storeRM4x4(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }

    public MemorySegment storeRM4x4(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[2];
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.data[1];
        dest[offset + 5] = (float) this.data[3];
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

    public ByteBuffer storeRM4x4Float(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRM4x4FloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeRM4x4FloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    public Double2x2 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }

    public MemorySegment storeRM4x4Float(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Float(this, 0L, dest); }

    public MemorySegment storeRM4x4Float(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Float(this, offset, dest);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final DoubleVector VEC_3 = DoubleVector.fromArray(COL_SPECIES, new double[] {1.0, 0.0, 0.0, 0.0}, 0);
    private static final DoubleVector VEC_4 = DoubleVector.fromArray(COL_SPECIES, new double[] {0.0, 0.0, 0.0, 1.0}, 0);
    private static final double[] DATA_0 = new double[] {1.0, 0.0, 0.0, 1.0};
    private static final double[] DATA_1 = new double[] {1.0, 0.0, 0.0, 1.0, 0.0, 0.0};
    private static final double[] DATA_2 = new double[] {1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};

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

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private Double2x2 cofactor_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
        return cofactor_affine_narrow(dest);
    }

    private Double2x2 cofactor_narrow() {
        if (Joml.RETURN_NEW) return cofactor(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return cofactor_general_narrow(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] dd = this.data;
            dd[1] = -this.data[2];
            dd[2] = 0.0;
            this.properties = 0;
            return this;
        }
        return cofactor_affine_narrow(this);
    }

    private Double2x2 invert_narrow(@Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma_narrow(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                dd[0] = 1.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_fma_narrow(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd_narrow(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                dd[0] = 1.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
            return invert_affine_mulAdd_narrow(dest);
        }
    }

    private Double2x2 invert_narrow() {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return invert(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma_narrow(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.data[2] = -this.data[2];
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_affine_self_fma_narrow(this);
        } else {
            if (Joml.RETURN_NEW) return invert(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd_narrow(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                this.data[2] = -this.data[2];
                this.properties = Joml.BIT_TRANSLATION;
                return this;
            }
            return invert_affine_self_mulAdd_narrow(this);
        }
    }

    private Double2x2 normal_narrow(@Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma_narrow(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                dd[0] = 1.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_fma_narrow(dest);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd_narrow(dest);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double[] dd = ((Double2x2Impl) dest).data;
                dd[0] = 1.0;
                dd[1] = 0.0;
                dd[2] = 0.0;
                dd[3] = 1.0;
                ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
                return dest;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
            return normal_affine_mulAdd_narrow(dest);
        }
    }

    private Double2x2 normal_narrow() {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (Joml.RETURN_NEW) return normal(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma_narrow(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                double[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_fma_narrow(this);
        } else {
            if (Joml.RETURN_NEW) return normal(Joml.double2x2());
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd_narrow(this);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                this.properties = Joml.BIT_IDENTITY;
                return this;
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
                double[] dd = this.data;
                dd[1] = -this.data[2];
                dd[2] = 0.0;
                this.properties = 0;
                return this;
            }
            return normal_affine_self_mulAdd_narrow(this);
        }
    }

    private Double2x2 transpose_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return transpose_translation(dest);
        return transpose_affine(dest);
    }

    private Double2x2 transpose_narrow() {
        if (Joml.RETURN_NEW) return transpose(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return transpose_general_self_narrow(this);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            this.properties = Joml.BIT_IDENTITY;
            return this;
        }
        double[] dd = this.data;
        dd[1] = this.data[2];
        dd[2] = 0.0;
        this.properties = 0;
        return this;
    }

    private Double2x2 add_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 sub_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 set_narrow(Double2x2R v) {
        double[] dd = this.data;
        double[] vData = ((Double2x2Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        this.properties = ((Double2x2Impl) v).properties;
        return this;
    }

    private Double2x2 set_narrow(Double2x3R m) {
        double[] dd = this.data;
        double[] mData = ((Double2x3Impl) m).data;
        dd[0] = mData[0];
        dd[1] = mData[1];
        dd[2] = mData[2];
        dd[3] = mData[3];
        this.properties = determineProperties();
        return this;
    }

    private Double2x2 makeIdentity_narrow() {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        this.properties = Joml.BIT_IDENTITY;
        return this;
    }

    private Double2x2 invertProduct_narrow(Double2x2R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double2x2());
        if (!org.joml2.internal.FmaSwitch.ON) return invertProduct_mulAdd_narrow(other);
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_fma_narrow(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma_narrow(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma_narrow(other, this);
            return invertProduct_general_affine_fma_narrow(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_fma_narrow(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_affine_fma_narrow(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma_narrow(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_fma_narrow(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma_narrow(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_fma_narrow(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s73a40995_1_fma_narrow(other, q);
    }

    private Double2x2 invertProduct_narrow(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma_narrow(m00, m01, m10, m11, dest);
        return invertProduct_mulAdd_narrow(m00, m01, m10, m11, dest);
    }

    private Double2x3 to2x3_narrow(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            double[] sd = this.data;
            double[] dd = ((Double2x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = sd[3];
            dd[4] = 0.0;
            dd[5] = 0.0;
            ((Double2x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to2x3_translation(dest);
        return to2x3_affine(dest);
    }

    private Double3x3 to3x3_narrow(@Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) dest).data;
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = 0.0;
            dd[3] = sd[2];
            dd[4] = sd[3];
            dd[5] = 0.0;
            dd[6] = 0.0;
            dd[7] = 0.0;
            dd[8] = 1.0;
            ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
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
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double[] sd = this.data;
            double[] dd = ((Double3x3Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = sd[2];
            dd[4] = 1.0;
            dd[5] = 0.0;
            dd[6] = 0.0;
            dd[7] = 0.0;
            dd[8] = 1.0;
            ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
            return dest;
        }
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = sd[2];
        dd[4] = 1.0;
        dd[5] = 0.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 1.0;
        ((Double3x3Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 invertProduct_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma_narrow(other, dest);
        return invertProduct_mulAdd_narrow(other, dest);
    }

    private Double2x2 cofactor_general_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        dd[0] = sd[3];
        dd[1] = -sd[2];
        dd[2] = -_rd1;
        dd[3] = _rd0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 cofactor_affine_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _rd0 = sd[0];
        dd[0] = 1.0;
        dd[1] = -sd[2];
        dd[2] = 0.0;
        dd[3] = _rd0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invert_general_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_fma_narrow(dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        dd[0] = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invert_affine_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_fma_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 invert_general_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_mulAdd_narrow(dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        dd[0] = sd[3] * _t5_inv;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invert_affine_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_mulAdd_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0_inv);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 invert_affine_self_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_fma_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 invert_affine_self_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_mulAdd_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[2] = -(sd[2] * _t0_inv);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 normal_general_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_fma_narrow(dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        dd[0] = sd[3] * _t5_inv;
        dd[1] = -(sd[2] * _t5_inv);
        dd[2] = -(_rd1 * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_affine_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_fma_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_general_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_mulAdd_narrow(dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        dd[0] = sd[3] * _t5_inv;
        dd[1] = -(sd[2] * _t5_inv);
        dd[2] = -(_rd1 * _t5_inv);
        dd[3] = _rd0 * _t5_inv;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_affine_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_mulAdd_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_affine_self_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_fma_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_affine_self_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_mulAdd_narrow(dest);
        double _t0_inv = 1.0 / _t0;
        dd[0] = _t0_inv;
        dd[1] = -(sd[2] * _t0_inv);
        dd[2] = 0.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 transpose_general_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _rd0 = sd[1];
        dd[0] = sd[0];
        dd[1] = sd[2];
        dd[2] = _rd0;
        dd[3] = sd[3];
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 transpose_general_self_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _rd0 = sd[1];
        dd[1] = sd[2];
        dd[2] = _rd0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd_narrow(other, dest);
            return invertProduct_general_affine_mulAdd_narrow(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_affine_mulAdd_narrow(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd_narrow(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_mulAdd_narrow(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd_narrow(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_mulAdd_narrow(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s1fafb47c_1_mulAdd_narrow(other, dest, q);
    }

    private Double2x2 invertProduct_mulAdd_narrow(Double2x2R other) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_mulAdd_narrow(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd_narrow(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd_narrow(other, this);
            return invertProduct_general_affine_mulAdd_narrow(other, this);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_mulAdd_narrow(other, this);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            return invertProduct_identity_affine_mulAdd_narrow(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd_narrow(other, this, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_mulAdd_narrow(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd_narrow(other, this, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            return invertProduct_translation_affine_mulAdd_narrow(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s73a40995_1_mulAdd_narrow(other, q);
    }

    private Double2x2 invertProduct_mulAdd_narrow(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(m01 * m10);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(m00, m01, m10, m11, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(m00, m01, m10, m11, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        double _t32 = _t22 * _t23;
        dd[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        return invertProduct_se106928b_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    private Double2x2 invertProduct_general_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(otherData[2] * otherData[1]);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = otherData[3] / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = otherData[0] / _t10;
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_sc6458a17_1_fma(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    private Double2x2 invertProduct_general_identity_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = java.lang.Math.fma(sd[0], sd[3], _t1) - java.lang.Math.fma(sd[2], sd[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        dd[0] = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = _rd0 / _t5;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_general_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -((Double2x2Impl) other).data[2];
        double _t3 = -(sd[2] * sd[1]);
        double _t7 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t11 = sd[0] / _t7;
        double _t12 = -(sd[1] * _t7_inv);
        dd[0] = java.lang.Math.fma(_t1, _t12, sd[3] / _t7);
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t11, -(sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_general_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t2 = -(sd[2] * sd[1]);
        double _t5 = 1.0 / _t1;
        double _t7 = -(otherData[2] / _t1);
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t2) - java.lang.Math.fma(sd[2], sd[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t17 = sd[3] / _t11;
        double _t18 = sd[0] / _t11;
        double _t20 = -(sd[1] * _t11_inv);
        double _t21 = -(sd[2] * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        dd[0] = java.lang.Math.fma(_t7, _t20, _t22) + java.lang.Math.fma(_t5, _t17, -_t22);
        dd[1] = _t20;
        dd[2] = java.lang.Math.fma(_t7, _t18, _t23) + java.lang.Math.fma(_t21, _t5, -_t23);
        dd[3] = _t18;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_identity_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(otherData[2] * otherData[1]);
        double _t5 = java.lang.Math.fma(otherData[0], otherData[3], _t1) - java.lang.Math.fma(otherData[2], otherData[1], _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = otherData[0];
        dd[0] = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = _rd0 / _t5;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_identity_identity_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] dd = ((Double2x2Impl) dest).data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_identity_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -this.data[2];
        double _t3 = -(otherData[2] * otherData[1]);
        double _t7 = java.lang.Math.fma(otherData[0], otherData[3], _t3) - java.lang.Math.fma(otherData[2], otherData[1], _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t10 = otherData[3] / _t7;
        double _t12 = -(otherData[1] * _t7_inv);
        double _rd0 = otherData[0];
        dd[0] = _t10;
        dd[1] = _t12;
        dd[2] = java.lang.Math.fma(_t1, _t10, -(otherData[2] * _t7_inv));
        dd[3] = java.lang.Math.fma(_t1, _t12, _rd0 / _t7);
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    private Double2x2 invertProduct_translation_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t2 = 1.0 / _t0;
        dd[0] = _t2;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-sd[2], _t2, -(otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    private Double2x2 invertProduct_s73a40995_1_fma_narrow(Double2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma_narrow(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma_narrow(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma_narrow(other, this);
        return invertProduct_affine_affine_fma_narrow(other, this);
    }

    private Double2x2 invertProduct_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma_narrow(other, dest);
            return invertProduct_general_affine_fma_narrow(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            return invertProduct_identity_affine_fma_narrow(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma_narrow(other, dest, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_fma_narrow(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma_narrow(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            return invertProduct_translation_affine_fma_narrow(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s1fafb47c_1_fma_narrow(other, dest, q);
    }

    private Double2x2 invertProduct_fma_narrow(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(m01 * m10);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(m00, m01, m10, m11, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = java.lang.Math.fma(sd[0], sd[3], _t3) - java.lang.Math.fma(sd[2], sd[1], _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(m00, m01, m10, m11, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        double _t32 = _t22 * _t23;
        dd[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        return invertProduct_se106928b_1_fma(dest, dd, _t22, _t23, _t24, _t27, _t28, _t29, _t30, -(m10 * _t10_inv), _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    private Double2x2 invert_degenerate_fma_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_fma_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation_narrow(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal_narrow(dest);
        return invert_degenerate_affine_narrow(dest);
    }

    private Double2x2 invert_degenerate_mulAdd_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_mulAdd_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation_narrow(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal_narrow(dest);
        return invert_degenerate_affine_narrow(dest);
    }

    private Double2x2 normal_degenerate_fma_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_fma_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation_narrow(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal_narrow(dest);
        return normal_degenerate_affine_narrow(dest);
    }

    private Double2x2 normal_degenerate_mulAdd_narrow(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_mulAdd_narrow(dest);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] dd = ((Double2x2Impl) dest).data;
            dd[0] = 1.0;
            dd[1] = 0.0;
            dd[2] = 0.0;
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_IDENTITY;
            return dest;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation_narrow(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal_narrow(dest);
        return normal_degenerate_affine_narrow(dest);
    }

    private Double2x2 invertProduct_general_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t2 = -(otherData[2] * otherData[1]);
        double _t3 = -(sd[2] * sd[1]);
        double _t10 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t10_inv = 1.0 / _t10;
        double _t11 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t22 = otherData[3] / _t10;
        double _t23 = sd[3] / _t11;
        double _t24 = sd[0] / _t11;
        double _t27 = otherData[0] / _t10;
        double _t29 = -(sd[1] * _t11_inv);
        double _t30 = -(sd[2] * _t11_inv);
        return invertProduct_general_sc6458a17_1_mulAdd(dest, dd, _t22, _t23, _t24, _t27, -(otherData[2] * _t10_inv), _t29, _t30, -(otherData[1] * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    private Double2x2 invertProduct_general_identity_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(sd[2] * sd[1]);
        double _t5 = ((sd[0]) * (sd[3]) + (_t1)) - ((sd[2]) * (sd[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = sd[0];
        dd[0] = sd[3] / _t5;
        dd[1] = -(sd[1] * _t5_inv);
        dd[2] = -(sd[2] * _t5_inv);
        dd[3] = _rd0 / _t5;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_general_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -((Double2x2Impl) other).data[2];
        double _t3 = -(sd[2] * sd[1]);
        double _t7 = ((sd[0]) * (sd[3]) + (_t3)) - ((sd[2]) * (sd[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t11 = sd[0] / _t7;
        double _t12 = -(sd[1] * _t7_inv);
        dd[0] = ((_t1) * (_t12) + (sd[3] / _t7));
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t11) - (sd[2] * _t7_inv));
        dd[3] = _t11;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_general_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = otherData[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t2 = -(sd[2] * sd[1]);
        double _t5 = 1.0 / _t1;
        double _t7 = -(otherData[2] / _t1);
        double _t11 = ((sd[0]) * (sd[3]) + (_t2)) - ((sd[2]) * (sd[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t17 = sd[3] / _t11;
        double _t18 = sd[0] / _t11;
        double _t20 = -(sd[1] * _t11_inv);
        double _t21 = -(sd[2] * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        dd[0] = ((_t7) * (_t20) + (_t22)) + ((_t5) * (_t17) - (_t22));
        dd[1] = _t20;
        dd[2] = ((_t7) * (_t18) + (_t23)) + ((_t21) * (_t5) - (_t23));
        dd[3] = _t18;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_identity_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -(otherData[2] * otherData[1]);
        double _t5 = ((otherData[0]) * (otherData[3]) + (_t1)) - ((otherData[2]) * (otherData[1]) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _rd0 = otherData[0];
        dd[0] = otherData[3] / _t5;
        dd[1] = -(otherData[1] * _t5_inv);
        dd[2] = -(otherData[2] * _t5_inv);
        dd[3] = _rd0 / _t5;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_identity_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = -this.data[2];
        double _t3 = -(otherData[2] * otherData[1]);
        double _t7 = ((otherData[0]) * (otherData[3]) + (_t3)) - ((otherData[2]) * (otherData[1]) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _t10 = otherData[3] / _t7;
        double _t12 = -(otherData[1] * _t7_inv);
        double _rd0 = otherData[0];
        dd[0] = _t10;
        dd[1] = _t12;
        dd[2] = ((_t1) * (_t10) - (otherData[2] * _t7_inv));
        dd[3] = ((_t1) * (_t12) + (_rd0 / _t7));
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    private Double2x2 invertProduct_translation_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t2 = 1.0 / _t0;
        dd[0] = _t2;
        dd[1] = 0.0;
        dd[2] = ((-sd[2]) * (_t2) - (otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = _props;
        return dest;
    }

    private Double2x2 invertProduct_s1fafb47c_1_mulAdd_narrow(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd_narrow(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd_narrow(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd_narrow(other, dest);
        return invertProduct_affine_affine_mulAdd_narrow(other, dest);
    }

    private Double2x2 invertProduct_s73a40995_1_mulAdd_narrow(Double2x2R other, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd_narrow(other, this);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd_narrow(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd_narrow(other, this);
        return invertProduct_affine_affine_mulAdd_narrow(other, this);
    }

    private Double2x2 invertProduct_degenerate_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_mulAdd_narrow(dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_mulAdd_narrow(other, dest);
            return invertProduct_degenerate_general_affine_mulAdd_narrow(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation_narrow(other, dest);
            return invertProduct_degenerate_identity_affine_narrow(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_mulAdd_narrow(other, dest);
            return invertProduct_degenerate_translation_affine_mulAdd_narrow(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_mulAdd_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_mulAdd_narrow(other, dest);
            return invertProduct_degenerate_orthogonal_affine_mulAdd_narrow(other, dest);
        }
        return invertProduct_degenerate_s10c579a1_1_mulAdd_narrow(other, dest, q);
    }

    private Double2x2 invertProduct_degenerate_mulAdd_narrow(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double _t0 = unitScale_narrow(m00, m01, m00);
        double _t1 = unitScale_narrow(m10, m11, m10);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_sa2cfbf8e_1_mulAdd(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    private Double2x2 invertProduct_degenerate_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_fma_narrow(dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_fma_narrow(other, dest);
            return invertProduct_degenerate_general_affine_fma_narrow(other, dest);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation_narrow(other, dest);
            return invertProduct_degenerate_identity_affine_narrow(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_fma_narrow(other, dest);
            return invertProduct_degenerate_translation_affine_fma_narrow(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_fma_narrow(other, dest);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity_narrow(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_fma_narrow(other, dest);
            return invertProduct_degenerate_orthogonal_affine_fma_narrow(other, dest);
        }
        return invertProduct_degenerate_s10c579a1_1_fma_narrow(other, dest, q);
    }

    private Double2x2 invertProduct_degenerate_fma_narrow(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double _t0 = unitScale_narrow(m00, m01, m00);
        double _t1 = unitScale_narrow(m10, m11, m10);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t56 = -(_t19 * _t2 * _t37_inv);
        return invertProduct_degenerate_sa2cfbf8e_1_fma(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t50, _t53, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), _t56, -(_t15 * _t0 * _t36_inv), _t48 * _t49, _t53 * _t50, _t56 * _t48);
    }

    private Double2x2 invertProduct_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t2 = -(otherData[2] * otherData[1]);
        double _t4 = 1.0 / _t1;
        double _t9 = -(sd[2] / _t1);
        double _t11 = java.lang.Math.fma(otherData[0], otherData[3], _t2) - java.lang.Math.fma(otherData[2], otherData[1], _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t14 = otherData[3] / _t11;
        double _t16 = -(otherData[1] * _t11_inv);
        double _rd0 = otherData[0];
        dd[0] = _t4 * _t14;
        dd[1] = _t16 * _t4;
        dd[2] = java.lang.Math.fma(_t9, _t14, -(otherData[2] * _t11_inv));
        dd[3] = java.lang.Math.fma(_t16, _t9, _rd0 / _t11);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_identity_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma_narrow(other, dest);
        double _t3 = 1.0 / _t0;
        dd[0] = _t3 * (1.0 / _t1);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] / _t1), _t3, -(otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_s1fafb47c_1_fma_narrow(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma_narrow(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma_narrow(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma_narrow(other, dest);
        return invertProduct_affine_affine_fma_narrow(other, dest);
    }

    private Double2x2 invert_degenerate_general_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invert_degenerate_translation_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1_inv = 1.0 / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t1_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    private Double2x2 invert_degenerate_orthogonal_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    private Double2x2 invert_degenerate_affine_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0) * _t2_inv;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    private Double2x2 invert_degenerate_general_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t9 * _sp0);
        dd[2] = -(_t8 * _sp1);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_degenerate_general_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_degenerate_translation_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1_inv = 1.0 / _t0;
        dd[0] = _t0 * _t1_inv;
        dd[1] = -(sd[2] * _t0) * _t1_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_degenerate_orthogonal_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_degenerate_affine_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t2_inv = 1.0 / (sd[0] * _t0);
        dd[0] = _t0 * _t2_inv;
        dd[1] = -(sd[2] * _t0) * _t2_inv;
        dd[2] = 0.0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 normal_degenerate_general_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dd[0] = _t6 * _sp0;
        dd[1] = -(_t8 * _sp1);
        dd[2] = -(_t9 * _sp0);
        dd[3] = _t7 * _sp1;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t2 = -(otherData[2] * otherData[1]);
        double _t4 = 1.0 / _t1;
        double _t9 = -(sd[2] / _t1);
        double _t11 = ((otherData[0]) * (otherData[3]) + (_t2)) - ((otherData[2]) * (otherData[1]) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t11_inv = 1.0 / _t11;
        double _t14 = otherData[3] / _t11;
        double _t16 = -(otherData[1] * _t11_inv);
        double _rd0 = otherData[0];
        dd[0] = _t4 * _t14;
        dd[1] = _t16 * _t4;
        dd[2] = ((_t9) * (_t14) - (otherData[2] * _t11_inv));
        dd[3] = ((_t16) * (_t9) + (_rd0 / _t11));
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_identity_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = sd[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        dd[0] = 1.0 / _t0;
        dd[1] = 0.0;
        dd[2] = -otherData[2] - sd[2] / _t0;
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_affine_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = otherData[0];
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t1 = sd[0];
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd_narrow(other, dest);
        double _t3 = 1.0 / _t0;
        dd[0] = _t3 * (1.0 / _t1);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] / _t1)) * (_t3) - (otherData[2] / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_general_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double _t0 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t12 = otherData[2] * _t0;
        double _t13 = otherData[0] * _t0;
        double _t14 = otherData[3] * _t1;
        double _t15 = otherData[1] * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s4924f1ba_1_mulAdd(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    private Double2x2 invertProduct_degenerate_general_identity_mulAdd_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_general_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t10 = sd[1] * _t2;
        double _t11 = sd[0] * _t3;
        double _t12 = sd[3] * _t2;
        double _t13 = sd[2] * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(otherData[2] * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = ((_t11) * (_t12) + (_t23)) - ((_t13) * (_t10) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double _t33 = -(_t10 * _t3 * _t26_inv);
        double _t34 = _t31 * _t15;
        double _t35 = _t18 * _t32;
        dd[0] = ((_t33) * (_t18) + (_t34)) + ((_t31) * (_t15) - (_t34));
        dd[1] = _t33;
        dd[2] = ((_t35) - (_t13 * _t2 * _t26_inv) * (_t15)) + ((_t18) * (_t32) - (_t35));
        dd[3] = _t32;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_general_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t9 = sd[1] * _t0;
        double _t10 = sd[0] * _t1;
        double _t11 = sd[3] * _t0;
        double _t12 = sd[2] * _t1;
        double _t14 = otherData[0] * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(otherData[2] * _t2 / _t14);
        double _t27 = ((_t10) * (_t11) + (_t22)) - ((_t12) * (_t9) + (_t22));
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double _t34 = -(_t9 * _t1 * _t27_inv);
        double _t35 = _t32 * _t19;
        double _t36 = _t24 * _t33;
        dd[0] = ((_t34) * (_t24) + (_t35)) + ((_t32) * (_t19) - (_t35));
        dd[1] = _t34;
        dd[2] = ((_t36) - (_t12 * _t0 * _t27_inv) * (_t19)) + ((_t24) * (_t33) - (_t36));
        dd[3] = _t33;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_identity_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t6 = otherData[3] * _t0;
        double _t7 = otherData[0] * _t1;
        double _t8 = otherData[2] * _t1;
        double _t9 = otherData[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_identity_translation_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] * _t0 / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_identity_affine_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t2 = otherData[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0;
        dd[2] = -(otherData[2] * _t0 / _t2);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t10 = otherData[3] * _t2;
        double _t11 = otherData[0] * _t3;
        double _t12 = otherData[2] * _t3;
        double _t13 = otherData[1] * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(sd[2] * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = ((_t11) * (_t10) + (_t22)) - ((_t12) * (_t13) + (_t22));
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = ((_t18) * (_t28) - (_t12 * _t2 * _t25_inv));
        dd[3] = ((_t30) * (_t18) + (_t11 * _t2 / _t25));
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_identity_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        dd[0] = _t0 / _t0;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0 / _t0);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t1 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t1)) * (_t5) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t4 = otherData[0] * _t2;
        double _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t0 / _t0)) * (_t6) - (otherData[2] * _t2 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t5 = sd[0] * _t0;
        double _t11 = otherData[3] * _t1;
        double _t12 = otherData[0] * _t2;
        double _t13 = otherData[2] * _t2;
        double _t14 = otherData[1] * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(sd[2] * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = ((_t12) * (_t11) + (_t23)) - ((_t13) * (_t14) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = ((_t21) * (_t29) - (_t13 * _t1 * _t26_inv));
        dd[3] = ((_t31) * (_t21) + (_t12 * _t1 / _t26));
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_identity_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2 = sd[0] * _t0;
        dd[0] = _t0 / _t2;
        dd[1] = 0.0;
        dd[2] = -(sd[2] * _t0 / _t2);
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_translation_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t1 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t4 = sd[0] * _t1;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t3 = sd[0] * _t0;
        double _t5 = otherData[0] * _t1;
        double _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t0 / _t3)) * (_t7) - (otherData[2] * _t1 / _t5));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_s10c579a1_1_mulAdd_narrow(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_mulAdd_narrow(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale_narrow(sd[0], sd[2], sd[0]);
            double _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double[] sd = this.data;
            double[] otherData = ((Double2x2Impl) other).data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
            double _t2 = unitScale_narrow(sd[0], sd[2], sd[0]);
            double _t4 = sd[0] * _t2;
            double _t6 = _t0 / _t0;
            dd[0] = _t6 * (_t2 / _t4);
            dd[1] = 0.0;
            dd[2] = ((-(sd[2] * _t2 / _t4)) * (_t6) - (otherData[2] * _t0 / _t0));
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        return invertProduct_degenerate_affine_affine_mulAdd_narrow(other, dest);
    }

    private static float unitScale_narrow(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    private static double unitScale_narrow(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    private Double2x2 invertProduct_degenerate_general_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double _t0 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t12 = otherData[2] * _t0;
        double _t13 = otherData[0] * _t0;
        double _t14 = otherData[3] * _t1;
        double _t15 = otherData[1] * _t1;
        double _t16 = sd[1] * _t2;
        double _t17 = sd[0] * _t3;
        double _t18 = sd[3] * _t2;
        double _t19 = sd[2] * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        return invertProduct_degenerate_general_s4924f1ba_1_fma(dest, ((Double2x2Impl) dest).data, _t48, _t49, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv), _t48 * _t49);
    }

    private Double2x2 invertProduct_degenerate_general_identity_fma_narrow(@Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t6 = sd[3] * _t0;
        double _t7 = sd[0] * _t1;
        double _t8 = sd[2] * _t1;
        double _t9 = sd[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_general_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t2 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t3 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t10 = sd[1] * _t2;
        double _t11 = sd[0] * _t3;
        double _t12 = sd[3] * _t2;
        double _t13 = sd[2] * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(otherData[2] * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = java.lang.Math.fma(_t11, _t12, _t23) - java.lang.Math.fma(_t13, _t10, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double _t33 = -(_t10 * _t3 * _t26_inv);
        double _t34 = _t31 * _t15;
        double _t35 = _t18 * _t32;
        dd[0] = java.lang.Math.fma(_t33, _t18, _t34) + java.lang.Math.fma(_t31, _t15, -_t34);
        dd[1] = _t33;
        dd[2] = java.lang.Math.fma(-(_t13 * _t2 * _t26_inv), _t15, _t35) + java.lang.Math.fma(_t18, _t32, -_t35);
        dd[3] = _t32;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_general_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(sd[1], sd[3], sd[1]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t9 = sd[1] * _t0;
        double _t10 = sd[0] * _t1;
        double _t11 = sd[3] * _t0;
        double _t12 = sd[2] * _t1;
        double _t14 = otherData[0] * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(otherData[2] * _t2 / _t14);
        double _t27 = java.lang.Math.fma(_t10, _t11, _t22) - java.lang.Math.fma(_t12, _t9, _t22);
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double _t34 = -(_t9 * _t1 * _t27_inv);
        double _t35 = _t32 * _t19;
        double _t36 = _t24 * _t33;
        dd[0] = java.lang.Math.fma(_t34, _t24, _t35) + java.lang.Math.fma(_t32, _t19, -_t35);
        dd[1] = _t34;
        dd[2] = java.lang.Math.fma(-(_t12 * _t0 * _t27_inv), _t19, _t36) + java.lang.Math.fma(_t24, _t33, -_t36);
        dd[3] = _t33;
        ((Double2x2Impl) dest).properties = 0;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_identity_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t6 = otherData[3] * _t0;
        double _t7 = otherData[0] * _t1;
        double _t8 = otherData[2] * _t1;
        double _t9 = otherData[1] * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        dd[0] = _t6 * _t1 / _t14;
        dd[1] = -(_t9 * _t1 * _t14_inv);
        dd[2] = -(_t8 * _t0 * _t14_inv);
        dd[3] = _t7 * _t0 / _t14;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t3 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t10 = otherData[3] * _t2;
        double _t11 = otherData[0] * _t3;
        double _t12 = otherData[2] * _t3;
        double _t13 = otherData[1] * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(sd[2] * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = java.lang.Math.fma(_t11, _t10, _t22) - java.lang.Math.fma(_t12, _t13, _t22);
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        dd[0] = _t28 * _t15;
        dd[1] = _t30 * _t15;
        dd[2] = java.lang.Math.fma(_t18, _t28, -(_t12 * _t2 * _t25_inv));
        dd[3] = java.lang.Math.fma(_t30, _t18, _t11 * _t2 / _t25);
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t1 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t5 = _t0 / _t0;
        dd[0] = _t5 * (_t1 / _t1);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t1), _t5, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_translation_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t4 = otherData[0] * _t2;
        double _t6 = _t2 / _t4;
        dd[0] = _t0 / _t0 * _t6;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t0), _t6, -(otherData[2] * _t2 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t2 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t5 = sd[0] * _t0;
        double _t11 = otherData[3] * _t1;
        double _t12 = otherData[0] * _t2;
        double _t13 = otherData[2] * _t2;
        double _t14 = otherData[1] * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(sd[2] * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = java.lang.Math.fma(_t12, _t11, _t23) - java.lang.Math.fma(_t13, _t14, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        dd[0] = _t29 * _t16;
        dd[1] = _t31 * _t16;
        dd[2] = java.lang.Math.fma(_t21, _t29, -(_t13 * _t1 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t21, _t12 * _t1 / _t26);
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t1 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t4 = sd[0] * _t1;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t1 / _t4);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_orthogonal_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, sd[2], 1.0);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t3 = sd[0] * _t0;
        double _t5 = otherData[0] * _t1;
        double _t7 = _t1 / _t5;
        dd[0] = _t0 / _t3 * _t7;
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t0 / _t3), _t7, -(otherData[2] * _t1 / _t5));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_s10c579a1_1_fma_narrow(Double2x2R other, Double2x2 dest, int q) {
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_fma_narrow(other, dest);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double[] sd = this.data;
            double[] dd = ((Double2x2Impl) dest).data;
            double _t0 = unitScale_narrow(sd[0], sd[2], sd[0]);
            double _t2 = sd[0] * _t0;
            dd[0] = _t0 / _t2;
            dd[1] = 0.0;
            dd[2] = -(sd[2] * _t0 / _t2);
            dd[3] = 1.0;
            ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
            return dest;
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_fma_narrow(other, dest);
        return invertProduct_degenerate_affine_affine_fma_narrow(other, dest);
    }

    private Double2x2 invertProduct_degenerate_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t2 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t9 = otherData[3] * _t0;
        double _t10 = otherData[0] * _t1;
        double _t11 = otherData[2] * _t1;
        double _t12 = otherData[1] * _t0;
        double _t13 = sd[0] * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(sd[2] * _t2 / _t13);
        double _t26 = ((_t10) * (_t9) + (_t21)) - ((_t11) * (_t12) + (_t21));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = ((_t23) * (_t29) - (_t11 * _t0 * _t26_inv));
        dd[3] = ((_t31) * (_t23) + (_t10 * _t0 / _t26));
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_affine_affine_mulAdd_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t4 = otherData[0] * _t0;
        double _t5 = sd[0] * _t1;
        double _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0;
        dd[2] = ((-(sd[2] * _t1 / _t5)) * (_t7) - (otherData[2] * _t0 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[1], otherData[3], otherData[1]);
        double _t1 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t2 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t9 = otherData[3] * _t0;
        double _t10 = otherData[0] * _t1;
        double _t11 = otherData[2] * _t1;
        double _t12 = otherData[1] * _t0;
        double _t13 = sd[0] * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(sd[2] * _t2 / _t13);
        double _t26 = java.lang.Math.fma(_t10, _t9, _t21) - java.lang.Math.fma(_t11, _t12, _t21);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        dd[0] = _t29 * _t18;
        dd[1] = _t31 * _t18;
        dd[2] = java.lang.Math.fma(_t23, _t29, -(_t11 * _t0 * _t26_inv));
        dd[3] = java.lang.Math.fma(_t31, _t23, _t10 * _t0 / _t26);
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_affine_translation_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(1.0, otherData[2], 1.0);
        double _t2 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t4 = sd[0] * _t2;
        double _t6 = _t0 / _t0;
        dd[0] = _t6 * (_t2 / _t4);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t2 / _t4), _t6, -(otherData[2] * _t0 / _t0));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }

    private Double2x2 invertProduct_degenerate_affine_affine_fma_narrow(Double2x2R other, @Mutated Double2x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x2Impl) other).data;
        double[] dd = ((Double2x2Impl) dest).data;
        double _t0 = unitScale_narrow(otherData[0], otherData[2], otherData[0]);
        double _t1 = unitScale_narrow(sd[0], sd[2], sd[0]);
        double _t4 = otherData[0] * _t0;
        double _t5 = sd[0] * _t1;
        double _t7 = _t0 / _t4;
        dd[0] = _t7 * (_t1 / _t5);
        dd[1] = 0.0;
        dd[2] = java.lang.Math.fma(-(sd[2] * _t1 / _t5), _t7, -(otherData[2] * _t0 / _t4));
        dd[3] = 1.0;
        ((Double2x2Impl) dest).properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return dest;
    }
}
