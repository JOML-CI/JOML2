// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double2x2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x2Impl implements Double2x2 {

    public double m00;
    public double m10;
    public double m01;
    public double m11;
    public int properties;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x2BbOpsUnsafe()
                        : new Double2x2BbOpsApi();
        static final Double2x2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x2RawOpsUnsafe()
                        : new Double2x2RawOpsApi();
    }

    public Double2x2Impl() {
        m00 = 1;
        m11 = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Double2x2Impl(double m00, double m01, double m10, double m11) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.properties = determineProperties();
    }

    public Double2x2Impl(Double2x2R src) {
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.properties = ((Double2x2Impl) src).properties;
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
    private Double2 getColumn_identity(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
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
    private Double2 getColumn_translation(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw2;
        double _idxSw3;
        switch (col) {
            case 0: _idxSw2 = 1.0; _idxSw3 = 0.0; break;
            case 1: _idxSw2 = this.m01; _idxSw3 = 1.0; break;
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
    private Double2 getColumn_affine(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw4;
        double _idxSw5;
        switch (col) {
            case 0: _idxSw4 = this.m00; _idxSw5 = 0.0; break;
            case 1: _idxSw4 = this.m01; _idxSw5 = 1.0; break;
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
    private Double2 getColumn_general(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw6;
        double _idxSw7;
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
    public Double2 getColumn(int col, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getColumn_translation(col, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getColumn_affine(col, dest);
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
        return Math.atan2(this.m10, this.m00);
    }



    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double2 getRow_translation(int row, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw2;
        double _idxSw3;
        switch (row) {
            case 0: _idxSw2 = 1.0; _idxSw3 = this.m01; break;
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; break;
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
    private Double2 getRow_affine(int row, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw4;
        double _idxSw5;
        switch (row) {
            case 0: _idxSw4 = this.m00; _idxSw5 = this.m01; break;
            case 1: _idxSw4 = 0.0; _idxSw5 = 1.0; break;
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
    private Double2 getRow_general(int row, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw6;
        double _idxSw7;
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
    public Double2 getRow(int row, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getRow_translation(row, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getRow_affine(row, dest);
        return getRow_general(row, dest);
    }




    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Double2x2 cofactor_translation(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = -this.m01;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double2x2 cofactor_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = 1.0;
        d.m10 = -this.m01;
        d.m01 = 0.0;
        d.m11 = this.m00;
        d.m00 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double2x2 cofactor_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = this.m11;
        double _buf1 = -this.m01;
        d.m01 = -this.m10;
        d.m11 = this.m00;
        d.m00 = _buf0;
        d.m10 = _buf1;
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
    public Double2x2 cofactor(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine(dest);
        return cofactor_general(dest);
    }


    /**
     * Compute the cofactor matrix of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x2 cofactor() {
        if (Joml.RETURN_NEW) return cofactor(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m10 = -this.m01;
            d.m01 = 0.0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine(this);
        return cofactor_general(this);
    }


    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    public double determinant() {
        return Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        return Math.sqrt(Math.fma(this.m11, this.m11, Math.fma(this.m10, this.m10, Math.fma(this.m00, this.m00, this.m01 * this.m01))));
    }




    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_translation(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = -this.m01;
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }



    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code invert}, specialized by runtime matrix properties;
     * reached only through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_self(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m01 = -(this.m01 * _t0_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = this.m11 * _t3_inv;
        d.m10 = -(this.m10 * _t3_inv);
        d.m01 = -(this.m01 * _t3_inv);
        d.m11 = this.m00 * _t3_inv;
        d.m00 = _buf0;
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
    public Double2x2 invert(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine(dest);
        return invert_general(dest);
    }


    /**
     * Invert this matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x2 invert() {
        if (Joml.RETURN_NEW) return invert(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m01 = -this.m01;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine_self(this);
        return invert_general(this);
    }




    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_translation(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t1_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_orthogonal(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
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
    private Double2x2 invert_degenerate(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_affine(dest);
        return invert_degenerate_general(dest);
    }



    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t4 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t5 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t6 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t7 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t11_inv = 1.0 / _t11;
        d.m00 = _t4 * _t11_inv;
        d.m10 = -(_t6 * _t11_inv);
        d.m01 = -(_t7 * _t11_inv);
        d.m11 = _t5 * _t11_inv;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = other.m11() * _t3_inv;
        d.m10 = -(other.m10() * _t3_inv);
        d.m01 = -(other.m01() * _t3_inv);
        d.m11 = other.m00() * _t3_inv;
        d.m00 = _buf0;
        d.properties = ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.fma(other.m10(), this.m01, other.m00());
        double _t1 = Math.fma(other.m11(), this.m01, other.m01());
        double _t5 = Math.fma(other.m11(), _t0, -(other.m10() * _t1));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t5_inv = 1.0 / _t5;
        d.m00 = other.m11() * _t5_inv;
        d.m10 = -(other.m10() * _t5_inv);
        d.m01 = -(_t1 * _t5_inv);
        d.m11 = _t0 * _t5_inv;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t2 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t3 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t7 = Math.fma(other.m11(), _t2, -(other.m10() * _t3));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t7_inv = 1.0 / _t7;
        d.m00 = other.m11() * _t7_inv;
        d.m10 = -(other.m10() * _t7_inv);
        d.m01 = -(_t3 * _t7_inv);
        d.m11 = _t2 * _t7_inv;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_identity_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = -other.m01();
        d.m11 = 1.0;
        d.properties = ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m00();
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0;
        d.m01 = -(other.m01() * _t0_inv);
        d.m11 = 1.0;
        d.properties = ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = -this.m01;
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = -(other.m01() + this.m01);
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m00();
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0;
        d.m01 = -((other.m01() + this.m01) * _t0_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = -other.m01() - this.m01;
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal_affine(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t1 = other.m00() * this.m00;
        if (!(Math.abs(_t1) > 2.2250738585072014E-308 && Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t1_inv = 1.0 / _t1;
        double _buf0 = _t1_inv;
        d.m10 = 0.0;
        d.m01 = -(Math.fma(other.m01(), this.m00, this.m01) * _t1_inv);
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t0_inv = 1.0 / _t0;
        double _buf0 = _t0_inv;
        d.m10 = 0.0;
        d.m01 = -(Math.fma(other.m01(), this.m00, this.m01) * _t0_inv);
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = this.m11 * _t3_inv;
        d.m10 = -(this.m10 * _t3_inv);
        d.m01 = -(this.m01 * _t3_inv);
        d.m11 = this.m00 * _t3_inv;
        d.m00 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.fma(other.m01(), this.m10, this.m11);
        double _t1 = Math.fma(other.m01(), this.m00, this.m01);
        double _t5 = Math.fma(this.m00, _t0, -(this.m10 * _t1));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = _t0 * _t5_inv;
        d.m10 = -(this.m10 * _t5_inv);
        d.m01 = -(_t1 * _t5_inv);
        d.m11 = this.m00 * _t5_inv;
        d.m00 = _buf0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m00() * this.m00;
        double _t1 = other.m00() * this.m10;
        double _t2 = Math.fma(other.m01(), this.m10, this.m11);
        double _t3 = Math.fma(other.m01(), this.m00, this.m01);
        double _t7 = Math.fma(_t2, _t0, -(_t3 * _t1));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t7_inv = 1.0 / _t7;
        d.m00 = _t2 * _t7_inv;
        d.m10 = -(_t1 * _t7_inv);
        d.m01 = -(_t3 * _t7_inv);
        d.m11 = _t0 * _t7_inv;
        d.properties = 0;
        return d;
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
    public Double2x2 invertProduct(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other, dest);
            return invertProduct_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other, dest);
            return invertProduct_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s1fafb47c_1(other, dest, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s1fafb47c_1(Double2x2R other, Double2x2 dest, int p, int q) {
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, dest, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other, dest);
        return invertProduct_general(other, dest);
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
    @Mutated public Double2x2 invertProduct(Double2x2R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double2x2());
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other, this);
            return invertProduct_identity(other, this);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other, this);
            return invertProduct_translation(other, this);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, this, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_s73a40995_1(other, p, q);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_s73a40995_1(Double2x2R other, int p, int q) {
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other, this);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, this, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, this, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other, this);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other, this);
        return invertProduct_general(other, this);
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
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
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t4 = Math.fma(m01, this.m10, m11 * this.m11);
        double _t5 = Math.fma(m00, this.m00, m10 * this.m01);
        double _t6 = Math.fma(m00, this.m10, m10 * this.m11);
        double _t7 = Math.fma(m01, this.m00, m11 * this.m01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate(m00, m01, m10, m11, dest);
        double _t11_inv = 1.0 / _t11;
        d.m00 = _t4 * _t11_inv;
        d.m10 = -(_t6 * _t11_inv);
        d.m01 = -(_t7 * _t11_inv);
        d.m11 = _t5 * _t11_inv;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t4 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t5 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t6 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t7 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t8 = unitScale(_t5, _t4, _t5);
        double _t9 = unitScale(_t6, _t7, _t6);
        double _t14 = _t4 * _t8;
        double _t15 = _t6 * _t9;
        double _t16 = _t5 * _t8;
        double _t17 = _t7 * _t9;
        double _t20_inv = 1.0 / Math.fma(_t15, _t14, -(_t16 * _t17));
        double _sp1 = _t8 * _t20_inv;
        double _sp0 = _t9 * _t20_inv;
        d.m00 = _t14 * _sp0;
        d.m10 = -(_t16 * _sp0);
        d.m01 = -(_t17 * _sp1);
        d.m11 = _t15 * _sp1;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(other.m10(), other.m11(), other.m10());
        double _t1 = unitScale(other.m00(), other.m01(), other.m00());
        double _t6 = other.m11() * _t0;
        double _t7 = other.m00() * _t1;
        double _t8 = other.m01() * _t1;
        double _t9 = other.m10() * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(other.m10(), other.m11(), other.m10());
        double _t1 = Math.fma(other.m10(), this.m01, other.m00());
        double _t2 = Math.fma(other.m11(), this.m01, other.m01());
        double _t5 = other.m11() * _t0;
        double _t6 = other.m10() * _t0;
        double _t7 = unitScale(_t1, _t2, _t1);
        double _t10 = _t1 * _t7;
        double _t11 = _t2 * _t7;
        double _t14_inv = 1.0 / Math.fma(_t5, _t10, -(_t6 * _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t7 * _t14_inv;
        d.m00 = _t5 * _sp0;
        d.m10 = -(_t6 * _sp0);
        d.m01 = -(_t11 * _sp1);
        d.m11 = _t10 * _sp1;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t2 = unitScale(other.m10(), other.m11(), other.m10());
        double _t4 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t5 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t7 = other.m11() * _t2;
        double _t8 = other.m10() * _t2;
        double _t9 = unitScale(_t4, _t5, _t4);
        double _t12 = _t4 * _t9;
        double _t13 = _t5 * _t9;
        double _t16_inv = 1.0 / Math.fma(_t7, _t12, -(_t8 * _t13));
        double _sp1 = _t2 * _t16_inv;
        double _sp0 = _t9 * _t16_inv;
        d.m00 = _t7 * _sp0;
        d.m10 = -(_t8 * _sp0);
        d.m01 = -(_t13 * _sp1);
        d.m11 = _t12 * _sp1;
        d.properties = _props;
        return d;
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, other.m01(), 1.0);
        double _t1_inv = 1.0 / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = 0.0;
        d.m01 = -(other.m01() * _t0 * _t1_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(other.m00(), other.m01(), other.m00());
        double _t2_inv = 1.0 / (other.m00() * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(other.m01() * _t0 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t1_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m01() + this.m01;
        double _t1 = unitScale(1.0, _t0, 1.0);
        double _t2_inv = 1.0 / _t1;
        d.m00 = _t1 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(_t0 * _t1 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m01() + this.m01;
        double _t1 = unitScale(other.m00(), _t0, other.m00());
        double _t3_inv = 1.0 / (other.m00() * _t1);
        d.m00 = _t1 * _t3_inv;
        d.m10 = 0.0;
        d.m01 = -(_t0 * _t1 * _t3_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.fma(other.m01(), this.m00, this.m01);
        double _t1 = unitScale(1.0, _t0, 1.0);
        double _t3_inv = 1.0 / (this.m00 * _t1);
        d.m00 = _t1 * _t3_inv;
        d.m10 = 0.0;
        d.m01 = -(_t0 * _t1 * _t3_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m00() * this.m00;
        double _t1 = Math.fma(other.m01(), this.m00, this.m01);
        double _t2 = unitScale(_t0, _t1, _t0);
        double _t4_inv = 1.0 / (_t0 * _t2);
        d.m00 = _t2 * _t4_inv;
        d.m10 = 0.0;
        d.m01 = -(_t1 * _t2 * _t4_inv);
        d.m11 = 1.0;
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = 0.0;
        d.m01 = -(this.m01 * _t0 * _t2_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.fma(other.m01(), this.m00, this.m01);
        double _t1 = unitScale(this.m00, _t0, this.m00);
        double _t3_inv = 1.0 / (this.m00 * _t1);
        d.m00 = _t1 * _t3_inv;
        d.m10 = 0.0;
        d.m01 = -(_t0 * _t1 * _t3_inv);
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        d.m00 = _t6 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t8 * _sp1);
        d.m11 = _t7 * _sp1;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.fma(other.m01(), this.m10, this.m11);
        double _t1 = Math.fma(other.m01(), this.m00, this.m01);
        double _t2 = unitScale(this.m10, _t0, this.m10);
        double _t3 = unitScale(this.m00, _t1, this.m00);
        double _t6 = this.m00 * _t3;
        double _t7 = this.m10 * _t2;
        double _t10 = _t0 * _t2;
        double _t11 = _t1 * _t3;
        double _t14_inv = 1.0 / Math.fma(_t6, _t10, -(_t7 * _t11));
        double _sp1 = _t2 * _t14_inv;
        double _sp0 = _t3 * _t14_inv;
        d.m00 = _t10 * _sp0;
        d.m10 = -(_t7 * _sp0);
        d.m01 = -(_t11 * _sp1);
        d.m11 = _t6 * _sp1;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = other.m00() * this.m10;
        double _t1 = other.m00() * this.m00;
        double _t2 = Math.fma(other.m01(), this.m10, this.m11);
        double _t3 = Math.fma(other.m01(), this.m00, this.m01);
        double _t4 = unitScale(_t0, _t2, _t0);
        double _t5 = unitScale(_t1, _t3, _t1);
        double _t8 = _t1 * _t5;
        double _t9 = _t0 * _t4;
        double _t12 = _t2 * _t4;
        double _t13 = _t3 * _t5;
        double _t16_inv = 1.0 / Math.fma(_t12, _t8, -(_t13 * _t9));
        double _sp1 = _t4 * _t16_inv;
        double _sp0 = _t5 * _t16_inv;
        d.m00 = _t12 * _sp0;
        d.m10 = -(_t9 * _sp0);
        d.m01 = -(_t13 * _sp1);
        d.m11 = _t8 * _sp1;
        d.properties = 0;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        int q = ((Double2x2Impl) other).properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_identity_affine(other, dest);
            return invertProduct_degenerate_identity(other, dest);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_translation_affine(other, dest);
            return invertProduct_degenerate_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_orthogonal(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        return invertProduct_degenerate_s10c579a1_1(other, dest, p, q);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private Double2x2 invertProduct_degenerate_s10c579a1_1(Double2x2R other, Double2x2 dest, int p, int q) {
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_affine_identity(other, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, dest, Joml.BIT_AFFINE & q);
            return invertProduct_degenerate_orthogonal(other, dest, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity(other, dest);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_general_affine(other, dest);
        return invertProduct_degenerate_general(other, dest);
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t4 = Math.fma(m01, this.m10, m11 * this.m11);
        double _t5 = Math.fma(m00, this.m10, m10 * this.m11);
        double _t6 = Math.fma(m00, this.m00, m10 * this.m01);
        double _t7 = Math.fma(m01, this.m00, m11 * this.m01);
        double _t8 = unitScale(_t5, _t4, _t5);
        double _t9 = unitScale(_t6, _t7, _t6);
        double _t14 = _t4 * _t8;
        double _t15 = _t6 * _t9;
        double _t16 = _t5 * _t8;
        double _t17 = _t7 * _t9;
        double _t20_inv = 1.0 / Math.fma(_t15, _t14, -(_t16 * _t17));
        double _sp1 = _t8 * _t20_inv;
        double _sp0 = _t9 * _t20_inv;
        d.m00 = _t14 * _sp0;
        d.m10 = -(_t16 * _sp0);
        d.m01 = -(_t17 * _sp1);
        d.m11 = _t15 * _sp1;
        d.properties = 0;
        return d;
    }







    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code normal}, specialized by runtime matrix properties;
     * reached only through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_self(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t0_inv = 1.0 / _t0;
        d.m00 = _t0_inv;
        d.m10 = -(this.m01 * _t0_inv);
        d.m01 = 0.0;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return normal_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = this.m11 * _t3_inv;
        double _buf1 = -(this.m01 * _t3_inv);
        d.m01 = -(this.m10 * _t3_inv);
        d.m11 = this.m00 * _t3_inv;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 normal(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine(dest);
        return normal_general(dest);
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x2 normal() {
        if (Joml.RETURN_NEW) return normal(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m10 = -this.m01;
            d.m01 = 0.0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine_self(this);
        return normal_general(this);
    }




    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_translation(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        d.m00 = _t0 * _t1_inv;
        d.m10 = -(this.m01 * _t0 * _t1_inv);
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_orthogonal(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = -(this.m01 * _t0 * _t2_inv);
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        d.m00 = _t0 * _t2_inv;
        d.m10 = -(this.m01 * _t0 * _t2_inv);
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
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
    private Double2x2 normal_degenerate(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_affine(dest);
        return normal_degenerate_general(dest);
    }



    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public double trace() {
        return this.m00 + this.m11;
    }






    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double2x2 transpose_affine(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double2x2 transpose_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        double _buf0 = this.m01;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m10 = _buf0;
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
    public Double2x2 transpose(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = 0.0;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2x2Impl d = (Double2x2Impl) dest;
            d.m00 = 1.0;
            d.m10 = this.m01;
            d.m01 = 0.0;
            d.m11 = 1.0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return transpose_affine(dest);
        return transpose_general(dest);
    }


    /**
     * Transpose this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public Double2x2 transpose() {
        if (Joml.RETURN_NEW) return transpose(Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m10 = this.m01;
            d.m01 = 0.0;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m00 = this.m00;
            d.m10 = this.m01;
            d.m01 = 0.0;
            d.properties = 0;
            return d;
        }
        return transpose_general(this);
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
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
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
    public Double2x2 add(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_identity(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_self(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = 0.0;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_self(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m01 = scalar * this.m01;
        d.m11 = scalar;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general(double scalar, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
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
    public Double2x2 mul(double scalar, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(scalar, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation(scalar, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine(scalar, dest);
        return mul_general(scalar, dest);
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
        if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m00 = scalar;
            d.m11 = scalar;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_translation_self(scalar, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_self(scalar, this);
        return mul_general(scalar, this);
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
    public Double2x2 sub(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
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
    public Double2x2 sub(double m00, double m01, double m10, double m11, @Mutated Double2x2 dest) {
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
    @Mutated public Double2x2 set(Double2x2R v) {
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m01 = v.m01();
        this.m11 = v.m11();
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
    @Mutated public Double2x2 set(Double2x3R m) {
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m01 = m.m01();
        this.m11 = m.m11();
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m01 = m.m01();
        this.m11 = m.m11();
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
        Float2x2Impl d = (Float2x2Impl) dest;
        d.m00 = (float) (this.m00);
        d.m10 = (float) (this.m10);
        d.m01 = (float) (this.m01);
        d.m11 = (float) (this.m11);
        d.properties = this.properties;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_identity(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_translation(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = this.m01;
        d.m11 = 1.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_affine(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = 0.0;
        d.m01 = this.m01;
        d.m11 = 1.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to2x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x3} dispatcher.
     */
    private Double2x3 to2x3_general(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        double _buf0 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.m10 = _buf0;
        d.properties = Joml.BIT_AFFINE;
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
    public Double2x3 to2x3(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return to2x3_identity(dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to2x3_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to2x3_affine(dest);
        return to2x3_general(dest);
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_identity(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.m22 = 1.0;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_translation(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.m22 = 1.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_affine(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.m22 = 1.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_general(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        double _buf0 = this.m10;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.m22 = 1.0;
        d.m10 = _buf0;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return to3x3_translation(dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to3x3_affine(dest);
        return to3x3_general(dest);
    }


    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix, storing them in
     * {@code lower}, {@code diagonal} and {@code upper} respectively.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, and a matrix
     * whose {@code m00} is zero has no such decomposition (the factors are then not finite).
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @param lower will hold the unit lower-triangular factor
     * @param diagonal will hold the diagonal factor
     * @param upper will hold the unit upper-triangular factor
     * @return this
     */
    public Double2x2 decomposeLDU(@Mutated Double2x2 lower, @Mutated Double2x2 diagonal, @Mutated Double2x2 upper) {
        Double2x2Impl d0 = (Double2x2Impl) lower;
        Double2x2Impl d1 = (Double2x2Impl) diagonal;
        Double2x2Impl d2 = (Double2x2Impl) upper;
        double _rcp0 = 1.0 / this.m00;
        double _sp0 = this.m10 * _rcp0;
        double _r0 = this.m00;
        double _r1 = this.m01;
        double _r2 = this.m11;
        d0.m00 = 1.0;
        d0.m10 = _sp0;
        d0.m01 = 0.0;
        d0.m11 = 1.0;
        d1.m00 = _r0;
        d1.m10 = 0.0;
        d1.m01 = 0.0;
        d1.m11 = Math.fma(-_r1, _sp0, _r2);
        d2.m00 = 1.0;
        d2.m10 = 0.0;
        d2.m01 = _r1 * _rcp0;
        d2.m11 = 1.0;
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
    @Mutated public Double2x2 makeIdentity() {
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
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
    public Double2x2 lerp(Double2x2R other, double t, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double2x2Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double2x2Impl) other).properties) >> 1);
        return d;
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
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        double _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        double _buf2 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation(Double2x2R right, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(right.m10(), this.m01, right.m00());
        d.m10 = right.m10();
        d.m01 = Math.fma(right.m11(), this.m01, right.m01());
        d.m11 = right.m11();
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        d.m10 = right.m10();
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = right.m11();
        d.m00 = _buf0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double2x2 mul_translation_translation(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = right.m01() + this.m01;
        d.m11 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x2Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_affine(Double2x2R right, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = right.m00();
        d.m10 = 0.0;
        d.m01 = right.m01() + this.m01;
        d.m11 = 1.0;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_translation(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = this.m00;
        d.m10 = 0.0;
        d.m01 = Math.fma(right.m01(), this.m00, this.m01);
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = right.m00() * this.m00;
        d.m10 = 0.0;
        d.m01 = Math.fma(right.m01(), this.m00, this.m01);
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = this.m00;
        double _buf1 = this.m10;
        double _buf2 = Math.fma(right.m01(), this.m00, this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, this.m11);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine(Double2x2R right, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = right.m00() * this.m00;
        double _buf1 = right.m00() * this.m10;
        double _buf2 = Math.fma(right.m01(), this.m00, this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, this.m11);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
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
    public Double2x2 mul(Double2x2R right, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Double2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, dest, Joml.BIT_TRANSLATION & q);
            return mul_translation(right, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation(right, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right, dest);
            return mul_affine(right, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right, dest);
        return mul_general(right, dest);
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        int q = ((Double2x2Impl) right).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, this, Joml.BIT_TRANSLATION & q);
            return mul_translation(right, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right, this, Joml.BIT_ORTHOGONAL & q);
            return mul_translation(right, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation(right, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right, this);
            return mul_affine(right, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right, this);
        return mul_general(right, this);
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
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(m00, this.m00, m10 * this.m01);
        double _buf1 = Math.fma(m00, this.m10, m10 * this.m11);
        d.m01 = Math.fma(m01, this.m00, m11 * this.m01);
        d.m11 = Math.fma(m01, this.m10, m11 * this.m11);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        double _buf1 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        double _buf2 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = other.m00();
        double _buf1 = other.m10();
        double _buf2 = Math.fma(other.m00(), this.m01, other.m01());
        d.m11 = Math.fma(other.m10(), this.m01, other.m11());
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = other.m00() * this.m00;
        double _buf1 = other.m10() * this.m00;
        double _buf2 = Math.fma(other.m00(), this.m01, other.m01());
        d.m11 = Math.fma(other.m10(), this.m01, other.m11());
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_affine(Double2x2R other, @Mutated Double2x2 dest, int _props) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = other.m00();
        d.m10 = 0.0;
        d.m01 = Math.fma(other.m00(), this.m01, other.m01());
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = 0.0;
        d.m01 = other.m01() + this.m01;
        d.m11 = 1.0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = other.m00() * this.m00;
        d.m10 = 0.0;
        d.m01 = Math.fma(other.m00(), this.m01, other.m01());
        d.m11 = 1.0;
        d.m00 = _buf0;
        d.properties = Joml.BIT_AFFINE & ((Double2x2Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(other.m01(), this.m10, this.m00);
        d.m10 = this.m10;
        d.m01 = Math.fma(other.m01(), this.m11, this.m01);
        d.m11 = this.m11;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine(Double2x2R other, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = this.m10;
        d.m01 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = this.m11;
        d.m00 = _buf0;
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
    public Double2x2 preMul(Double2x2R other, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Double2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, dest, Joml.BIT_TRANSLATION & q);
            return preMul_translation(other, dest, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, dest);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other, dest);
            return preMul_affine(other, dest);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, dest);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other, dest);
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
    @Mutated public Double2x2 preMul(Double2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        int q = ((Double2x2Impl) other).properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, this, Joml.BIT_TRANSLATION & q);
            return preMul_translation(other, this, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, this, Joml.BIT_ORTHOGONAL & q);
            return preMul_translation(other, this, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other, this);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other, this);
            return preMul_affine(other, this);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other, this);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other, this);
        return preMul_general(other, this);
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
        Double2x2Impl d = (Double2x2Impl) dest;
        double _buf0 = Math.fma(m00, this.m00, m01 * this.m10);
        d.m10 = Math.fma(m10, this.m00, m11 * this.m10);
        double _buf1 = Math.fma(m00, this.m01, m01 * this.m11);
        d.m11 = Math.fma(m10, this.m01, m11 * this.m11);
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.properties = 0;
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
    public Double2x2 addScaled(Double2x2R other, double weight, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.properties = 0;
        return d;
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
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.properties = 0;
        return d;
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
    public @Mutated Double2x2 makeOuterProduct(Double2R col, Double2R row) {
        return makeOuterProduct(col.x(), col.y(), row.x(), row.y());
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
    @Mutated public Double2x2 makeRotation(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        this.m00 = _t1;
        this.m10 = _t0;
        this.m01 = -_t0;
        this.m11 = _t1;
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
    public @Mutated Double2x2 makeScaling(Double2R v) {
        return makeScaling(v.x(), v.y());
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
        this.m00 = vX;
        this.m10 = 0.0;
        this.m01 = 0.0;
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
    @Mutated public Double2x2 makeScaling(double s) {
        this.m00 = s;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = s;
        this.properties = 0;
        return this;
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
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        double _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        d.m00 = _buf0;
        d.m01 = _buf1;
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
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 preScale(Double2R v, @Mutated Double2x2 dest) {
        return preScale(v.x(), v.y(), dest);
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
    public @Mutated Double2x2 preScale(Double2R v) {
        return preScale(v.x(), v.y());
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preScale} and {@code scale}; reached only through them.
     */
    private Double2x2 preScale_identity(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_translation(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_translation_self(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = vX;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine_self(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m01 = this.m01 * vX;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_general(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vY;
        d.m01 = this.m01 * vX;
        d.m11 = this.m11 * vY;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_affine(vX, vY, dest);
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
    @Mutated public Double2x2 preScale(double vX, double vY) {
        if (Joml.RETURN_NEW) return preScale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_affine_self(vX, vY, this);
        return preScale_general(vX, vY, this);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preScale} and {@code scale}; reached only through them.
     */
    private Double2x2 preScale_identity(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.properties = 0;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_translation(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = s * this.m01;
        d.m11 = s;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_translation_self(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s;
        d.m01 = s * this.m01;
        d.m11 = s;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = 0.0;
        d.m01 = s * this.m01;
        d.m11 = s;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_affine_self(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s * this.m00;
        d.m01 = s * this.m01;
        d.m11 = s;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x2 preScale_general(double s, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
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
    public Double2x2 preScale(double s, @Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(s, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation(s, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_affine(s, dest);
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
    @Mutated public Double2x2 preScale(double s) {
        if (Joml.RETURN_NEW) return preScale(s, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m00 = s;
            d.m11 = s;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preScale_translation_self(s, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_affine_self(s, this);
        return preScale_general(s, this);
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
        Double2x2Impl d = (Double2x2Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        double _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m00 = _buf0;
        d.m10 = _buf1;
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
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2x2 scale(Double2R v, @Mutated Double2x2 dest) {
        return scale(v.x(), v.y(), dest);
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
    public @Mutated Double2x2 scale(Double2R v) {
        return scale(v.x(), v.y());
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x2 scale_translation(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x2 scale_translation_self(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = vX;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x2 scale_affine(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = 0.0;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x2 scale_affine_self(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = vY;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x2 scale_general(double vX, double vY, @Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = this.m00 * vX;
        d.m10 = this.m10 * vX;
        d.m01 = this.m01 * vY;
        d.m11 = this.m11 * vY;
        d.properties = 0;
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScale_identity(vX, vY, dest);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return scale_translation(vX, vY, dest);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_affine(vX, vY, dest);
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
    @Mutated public Double2x2 scale(double vX, double vY) {
        if (Joml.RETURN_NEW) return scale(vX, vY, Joml.double2x2());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x2Impl d = (Double2x2Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = 0;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return scale_translation_self(vX, vY, this);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_affine_self(vX, vY, this);
        return scale_general(vX, vY, this);
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
        return preScale(s, dest);
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
        return preScale(s);
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
    public Double2 mul(Double2R v, @Mutated Double2 dest) {
        return mul(v.x(), v.y(), dest);
    }





    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general(double vX, double vY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m00, vX, this.m01 * vY);
        d.y = Math.fma(this.m10, vX, this.m11 * vY);
        return d;
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
    public Double2 mul(double vX, double vY, @Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2Impl d = (Double2Impl) dest;
            d.x = vX;
            d.y = vY;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            Double2Impl d = (Double2Impl) dest;
            d.x = Math.fma(this.m01, vY, vX);
            d.y = vY;
            return d;
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            Double2Impl d = (Double2Impl) dest;
            d.x = Math.fma(this.m00, vX, this.m01 * vY);
            d.y = vY;
            return d;
        }
        return mul_general(vX, vY, dest);
    }

    public double m00() { return this.m00; }
    public double m01() { return this.m01; }
    public double m10() { return this.m10; }
    public double m11() { return this.m11; }

    @Override public String toString() {
        return "Double2x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x2Impl)) return false;
        Double2x2Impl o = (Double2x2Impl) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m10)
            && Double.isFinite(m11);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m10)
            || Double.isNaN(m11);
    }

    @Override public boolean equalsEpsilon(Double2x2R other, double epsilon) {
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadCM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double2x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double2x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadCM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double2x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double2x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadRM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double2x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double2x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadRM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double2x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double2x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double2x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double2x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double2x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double2x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        return dest;
    }
    public @Mutated Double2x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeCM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public Double2x2 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) this.m01;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) 0.0;
        dest[offset + 6] = (float) 0.0;
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeCM3x3Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, index, buf);
    }
    public Double2x2 storeCM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3FloatUnsafe(this, address);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeRM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public Double2x2 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) this.m10;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) 0.0;
        dest[offset + 6] = (float) 0.0;
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeRM3x3Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, index, buf);
    }
    public Double2x2 storeRM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3FloatUnsafe(this, address);
    }

    public double[] storeCM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeCM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public Double2x2 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    public float[] storeCM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) 0.0;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) 0.0;
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) 0.0;
        dest[offset + 9] = (float) 0.0;
        dest[offset + 10] = (float) 1.0;
        dest[offset + 11] = (float) 0.0;
        dest[offset + 12] = (float) 0.0;
        dest[offset + 13] = (float) 0.0;
        dest[offset + 14] = (float) 0.0;
        dest[offset + 15] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeCM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeCM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }
    public Double2x2 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public Double2x2 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) 0.0;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) 0.0;
        dest[offset + 7] = (float) 0.0;
        dest[offset + 8] = (float) 0.0;
        dest[offset + 9] = (float) 0.0;
        dest[offset + 10] = (float) 1.0;
        dest[offset + 11] = (float) 0.0;
        dest[offset + 12] = (float) 0.0;
        dest[offset + 13] = (float) 0.0;
        dest[offset + 14] = (float) 0.0;
        dest[offset + 15] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeRM4x4Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }
    public Double2x2 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
