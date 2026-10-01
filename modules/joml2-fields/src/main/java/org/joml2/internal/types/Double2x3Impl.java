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
 * Generated implementation of {@link Double2x3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x3Impl implements Double2x3 {

    public double m00;
    public double m10;
    public double m01;
    public double m11;
    public double m02;
    public double m12;
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
        m00 = 1;
        m11 = 1;
        properties = Joml.BIT_IDENTITY;
    }

    public Double2x3Impl(double m00, double m01, double m02, double m10, double m11, double m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        this.properties = determineProperties();
    }

    public Double2x3Impl(Double2x3R src) {
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.m02 = src.m02();
        this.m12 = src.m12();
        this.properties = ((Double2x3Impl) src).properties;
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
    private Double2 getColumn_identity(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
            case 2: _idxSw0 = 0.0; _idxSw1 = 0.0; break;
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
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; break;
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
    private Double2 getColumn_general(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        double _idxSw4;
        double _idxSw5;
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
        return Math.atan2(this.m10, this.m00);
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_identity(int row, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = 1.0; _idxSw1 = 0.0; break;
            case 1: _idxSw0 = 0.0; _idxSw1 = 1.0; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = 0.0;
        return d;
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_translation(int row, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _idxSw2;
        double _idxSw3;
        double _idxSw4;
        switch (row) {
            case 0: _idxSw2 = 1.0; _idxSw3 = 0.0; _idxSw4 = this.m02; break;
            case 1: _idxSw2 = 0.0; _idxSw3 = 1.0; _idxSw4 = this.m12; break;
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
    private Double3 getRow_general(int row, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _idxSw5;
        double _idxSw6;
        double _idxSw7;
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
        Double2Impl d = (Double2Impl) dest;
        d.x = 0.0;
        d.y = 0.0;
        return d;
    }


    /**
     * Private body of {@code getTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code getTranslation} dispatcher.
     */
    private Double2 getTranslation_general(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Double2 getTranslation(@Mutated Double2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getTranslation_identity(dest);
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
        return Math.sqrt(Math.fma(this.m12, this.m12, Math.fma(this.m11, this.m11, Math.fma(this.m10, this.m10, Math.fma(this.m02, this.m02, Math.fma(this.m00, this.m00, this.m01 * this.m01))))));
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Double2x3 invert_identity(@Mutated Double2x3 dest) {
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
     * Private body of {@code invert}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invert}; reached only through it.
     */
    private Double2x3 invert_translation(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }



    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_orthogonal(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m11;
        double _buf0 = -this.m10;
        d.m01 = this.m10;
        double _buf1 = this.m11;
        double _buf2 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m12 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x3 invert_general(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate(dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = this.m11 * _t3_inv;
        double _buf1 = -(this.m10 * _t3_inv);
        double _buf2 = -(this.m01 * _t3_inv);
        double _buf3 = this.m00 * _t3_inv;
        double _buf4 = -(Math.fma(this.m02, this.m11, -(this.m01 * this.m12)) * _t3_inv);
        d.m12 = -(Math.fma(this.m00, this.m12, -(this.m02 * this.m10)) * _t3_inv);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
    @Mutated public Double2x3 invert() {
        if (Joml.RETURN_NEW) return invert(Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x3Impl d = (Double2x3Impl) this;
            d.properties = Joml.BIT_IDENTITY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Double2x3Impl d = (Double2x3Impl) this;
            d.m02 = -this.m02;
            d.m12 = -this.m12;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal(this);
        return invert_general(this);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate_orthogonal_general(@Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m02 * _t1;
        double _t13 = this.m12 * _t0;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        d.m12 = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        d.properties = _props;
        return d;
    }






    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x3 invert_degenerate(@Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_identity(dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_translation(dest);
        return invert_degenerate_orthogonal_general(dest, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
    }


    /**
     * Private column 2 of {@code invertProduct_general}: computes and stores it. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private void invertProduct_general_s63be54e4_c2(Double2x3Impl _dst, double _t10, double _t6, double _t11, double _t9, double _t15_inv, double _t7, double _t8) {
        _dst.m02 = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        _dst.m12 = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
    }

    /** Private tail of {@code invertProduct_general}; reached only through it. */
    private void invertProduct_general_s63be54e4_tail(Double2x3Impl _dst, double _r8, double _r5, double _r9, double _r7, double _r10, double _r1, double _r3, double _r11, double _t15, double _t6, double _t9, double _t8, double _t7) {
        double _t15_inv = 1.0 / _t15;
        _dst.m00 = _t6 * _t15_inv;
        _dst.m10 = -(_t8 * _t15_inv);
        _dst.m01 = -(_t9 * _t15_inv);
        _dst.m11 = _t7 * _t15_inv;
        invertProduct_general_s63be54e4_c2(_dst, Math.fma(_r8, _r5, Math.fma(_r9, _r7, _r10)), _t6, Math.fma(_r8, _r1, Math.fma(_r9, _r3, _r11)), _t9, _t15_inv, _t7, _t8);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _r0 = other.m01();
        double _r1 = this.m10;
        double _r2 = other.m11();
        double _r3 = this.m11;
        double _r4 = other.m00();
        double _r5 = this.m00;
        double _r6 = other.m10();
        double _r7 = this.m01;
        double _t6 = Math.fma(_r0, _r1, _r2 * _r3);
        double _t7 = Math.fma(_r4, _r5, _r6 * _r7);
        double _t8 = Math.fma(_r4, _r1, _r6 * _r3);
        double _t9 = Math.fma(_r0, _r5, _r2 * _r7);
        double _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(Math.abs(_t15) > 2.2250738585072014E-308 && Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _r8 = other.m02();
        double _r9 = other.m12();
        double _r10 = this.m02;
        double _r11 = this.m12;
        invertProduct_general_s63be54e4_tail(d, _r8, _r5, _r9, _r7, _r10, _r1, _r3, _r11, _t15, _t6, _t9, _t8, _t7);
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = other.m11() * _t3_inv;
        double _buf1 = -(other.m10() * _t3_inv);
        double _buf2 = -(other.m01() * _t3_inv);
        double _buf3 = other.m00() * _t3_inv;
        double _buf4 = -(Math.fma(other.m02(), other.m11(), -(other.m01() * other.m12())) * _t3_inv);
        d.m12 = -(Math.fma(other.m00(), other.m12(), -(other.m02() * other.m10())) * _t3_inv);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t1 = other.m02() + this.m02;
        double _t2 = other.m12() + this.m12;
        double _t5 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t5_inv = 1.0 / _t5;
        double _buf0 = other.m11() * _t5_inv;
        double _buf1 = -(other.m10() * _t5_inv);
        double _buf2 = -(other.m01() * _t5_inv);
        double _buf3 = other.m00() * _t5_inv;
        d.m02 = -(Math.fma(other.m11(), _t1, -(other.m01() * _t2)) * _t5_inv);
        d.m12 = -(Math.fma(other.m00(), _t2, -(other.m10() * _t1)) * _t5_inv);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_identity_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_identity_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = -other.m02();
        d.m12 = -other.m12();
        d.properties = ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_translation_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x3 invertProduct_translation_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = -(other.m02() + this.m02);
        d.m12 = -(other.m12() + this.m12);
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m11;
        double _buf0 = -this.m10;
        d.m01 = this.m10;
        double _buf1 = this.m11;
        double _buf2 = Math.fma(-this.m02, this.m11, -(this.m10 * this.m12));
        d.m12 = Math.fma(this.m02, this.m10, -(this.m11 * this.m12));
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_orthogonal_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = -this.m10;
        d.m00 = this.m11;
        double _buf0 = _t0;
        d.m01 = this.m10;
        double _buf1 = this.m11;
        double _buf2 = Math.fma(_t0, this.m12, Math.fma(-this.m02, this.m11, -other.m02()));
        d.m12 = Math.fma(this.m02, this.m10, Math.fma(-this.m11, this.m12, -other.m12()));
        d.m10 = _buf0;
        d.m11 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t3_inv = 1.0 / _t3;
        double _buf0 = this.m11 * _t3_inv;
        double _buf1 = -(this.m10 * _t3_inv);
        double _buf2 = -(this.m01 * _t3_inv);
        double _buf3 = this.m00 * _t3_inv;
        double _buf4 = -(Math.fma(this.m02, this.m11, -(this.m01 * this.m12)) * _t3_inv);
        d.m12 = -(Math.fma(this.m00, this.m12, -(this.m02 * this.m10)) * _t3_inv);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x3 invertProduct_general_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t5 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t6 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t7 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other, dest);
        double _t7_inv = 1.0 / _t7;
        double _buf0 = this.m11 * _t7_inv;
        double _buf1 = -(this.m10 * _t7_inv);
        double _buf2 = -(this.m01 * _t7_inv);
        double _buf3 = this.m00 * _t7_inv;
        d.m02 = -(Math.fma(this.m11, _t5, -(this.m01 * _t6)) * _t7_inv);
        d.m12 = -(Math.fma(this.m00, _t6, -(this.m10 * _t5)) * _t7_inv);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 invertProduct(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
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
    @Mutated public Double2x3 invertProduct(Double2x3R other) {
        if (Joml.RETURN_NEW) return invertProduct(other, Joml.double2x3());
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t6 = Math.fma(m01, this.m10, m11 * this.m11);
        double _t7 = Math.fma(m00, this.m00, m10 * this.m01);
        double _t8 = Math.fma(m00, this.m10, m10 * this.m11);
        double _t9 = Math.fma(m01, this.m00, m11 * this.m01);
        double _t10 = Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02));
        double _t11 = Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12));
        double _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(Math.abs(_t15) > 2.2250738585072014E-308 && Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate(m00, m01, m02, m10, m11, m12, dest);
        double _t15_inv = 1.0 / _t15;
        d.m00 = _t6 * _t15_inv;
        d.m10 = -(_t8 * _t15_inv);
        d.m01 = -(_t9 * _t15_inv);
        d.m11 = _t7 * _t15_inv;
        d.m02 = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        d.m12 = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private void invertProduct_degenerate_general_s63be54e4_tail(Double2x3Impl _dst, double _r8, double _r6, double _r9, double _r7, double _r10, double _t13, double _r1, double _r3, double _r11, double _t12, double _t19, double _t18, double _t20, double _t21) {
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        {
            double _t15_inv = _t13 * _t28_inv;
            _dst.m00 = _t18 * _t15_inv;
            _dst.m10 = -(_t20 * _t15_inv);
        }
        {
            double _t15_inv = _t12 * _t28_inv;
            _dst.m01 = -(_t21 * _t15_inv);
            _dst.m11 = _t19 * _t15_inv;
        }
        invertProduct_general_s63be54e4_c2(_dst, Math.fma(_r8, _r6, Math.fma(_r9, _r7, _r10)) * _t13, _t18, Math.fma(_r8, _r1, Math.fma(_r9, _r3, _r11)) * _t12, _t21, _t28_inv, _t19, _t20);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_general(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _r0 = other.m01();
        double _r1 = this.m10;
        double _r2 = other.m11();
        double _r3 = this.m11;
        double _r4 = other.m00();
        double _r5 = other.m10();
        double _r6 = this.m00;
        double _r7 = this.m01;
        double _r8 = other.m02();
        double _r9 = other.m12();
        double _r10 = this.m02;
        double _r11 = this.m12;
        double _t6 = Math.fma(_r0, _r1, _r2 * _r3);
        double _t7 = Math.fma(_r4, _r1, _r5 * _r3);
        double _t8 = Math.fma(_r4, _r6, _r5 * _r7);
        double _t9 = Math.fma(_r0, _r6, _r2 * _r7);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        invertProduct_degenerate_general_s63be54e4_tail(d, _r8, _r6, _r9, _r7, _r10, _t13, _r1, _r3, _r11, _t12, _t8 * _t13, _t6 * _t12, _t7 * _t12, _t9 * _t13);
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_identity(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = unitScale(other.m10(), other.m11(), other.m10());
        double _t1 = unitScale(other.m00(), other.m01(), other.m00());
        double _t8 = other.m11() * _t0;
        double _t9 = other.m00() * _t1;
        double _t10 = other.m01() * _t1;
        double _t11 = other.m10() * _t0;
        double _t12 = other.m02() * _t1;
        double _t13 = other.m12() * _t0;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        d.m12 = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        d.properties = ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t2 = unitScale(other.m10(), other.m11(), other.m10());
        double _t3 = unitScale(other.m00(), other.m01(), other.m00());
        double _t8 = other.m11() * _t2;
        double _t9 = other.m00() * _t3;
        double _t10 = other.m01() * _t3;
        double _t11 = other.m10() * _t2;
        double _t14 = (other.m02() + this.m02) * _t3;
        double _t15 = (other.m12() + this.m12) * _t2;
        double _t18_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t2 * _t18_inv;
        double _sp0 = _t3 * _t18_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = -(Math.fma(_t8, _t14, -(_t10 * _t15)) * _t18_inv);
        d.m12 = -(Math.fma(_t9, _t15, -(_t11 * _t14)) * _t18_inv);
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }






    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_orthogonal_identity(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m02 * _t1;
        double _t13 = this.m12 * _t0;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        d.m12 = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate_orthogonal_translation(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t16 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02)) * _t1;
        double _t17 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12)) * _t0;
        double _t20_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t20_inv;
        double _sp0 = _t1 * _t20_inv;
        d.m00 = _t8 * _sp0;
        d.m10 = -(_t11 * _sp0);
        d.m01 = -(_t10 * _sp1);
        d.m11 = _t9 * _sp1;
        d.m02 = -(Math.fma(_t8, _t16, -(_t10 * _t17)) * _t20_inv);
        d.m12 = -(Math.fma(_t9, _t17, -(_t11 * _t16)) * _t20_inv);
        d.properties = _props;
        return d;
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        int q = ((Double2x3Impl) other).properties;
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
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_general(other, dest, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other, dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return invertProduct_degenerate_general(other, dest, Joml.BIT_AFFINE & q);
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x3 invertProduct_degenerate(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t6 = Math.fma(m01, this.m10, m11 * this.m11);
        double _t7 = Math.fma(m00, this.m10, m10 * this.m11);
        double _t8 = Math.fma(m00, this.m00, m10 * this.m01);
        double _t9 = Math.fma(m01, this.m00, m11 * this.m01);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t24 = Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02)) * _t13;
        double _t25 = Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12)) * _t12;
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        double _sp1 = _t12 * _t28_inv;
        double _sp0 = _t13 * _t28_inv;
        d.m00 = _t18 * _sp0;
        d.m10 = -(_t20 * _sp0);
        d.m01 = -(_t21 * _sp1);
        d.m11 = _t19 * _sp1;
        d.m02 = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        d.m12 = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_identity(@Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        return d;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_translation(@Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = 1.0;
        double _buf0 = 0.0;
        d.m20 = this.m02;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = this.m12;
        d.m10 = _buf0;
        return d;
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x2 transpose_general(@Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = this.m00;
        double _buf0 = this.m01;
        double _buf1 = this.m02;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m10 = _buf0;
        d.m20 = _buf1;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 add(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
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
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double2x3 mul_identity(double scalar, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = scalar;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation(double scalar, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = scalar;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = scalar;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties;
     * reached only through the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_self(double scalar, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = scalar;
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
    private Double2x3 mul_general(double scalar, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 mul(double scalar) {
        if (Joml.RETURN_NEW) return mul(scalar, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x3Impl d = (Double2x3Impl) this;
            d.m00 = scalar;
            d.m11 = scalar;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_self(scalar, this);
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
    public Double2x3 sub(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
        d.m02 = this.m02 - other.m02();
        d.m12 = this.m12 - other.m12();
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
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
    public Double2x3 sub(double m00, double m01, double m02, double m10, double m11, double m12, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 set(Double2x3R v) {
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m01 = v.m01();
        this.m11 = v.m11();
        this.m02 = v.m02();
        this.m12 = v.m12();
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
    @Mutated public Double2x3 set(Double2x2R m) {
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m02 = 0.0;
        this.m12 = 0.0;
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
        this.m00 = m.m00();
        this.m10 = m.m10();
        this.m01 = m.m01();
        this.m11 = m.m11();
        this.m02 = m.m02();
        this.m12 = m.m12();
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
        return withTranslation(t.x(), t.y(), dest);
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
        return withTranslation(t.x(), t.y());
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code withTranslation}, {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Double2x3 withTranslation_identity(double tX, double tY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = tX;
        d.m12 = tY;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }



    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double2x3 withTranslation_orthogonal(double tX, double tY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
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
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double2x3 withTranslation_general(double tX, double tY, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m02 = tX;
            d.m12 = tY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return withTranslation_orthogonal(tX, tY, this);
        return withTranslation_general(tX, tY, this);
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
        Float2x3Impl d = (Float2x3Impl) dest;
        d.m00 = (float) (this.m00);
        d.m10 = (float) (this.m10);
        d.m01 = (float) (this.m01);
        d.m11 = (float) (this.m11);
        d.m02 = (float) (this.m02);
        d.m12 = (float) (this.m12);
        d.properties = this.properties;
        return d;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_identity(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.properties = Joml.BIT_IDENTITY;
        return d;
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_general(@Mutated Double2x2 dest) {
        Double2x2Impl d = (Double2x2Impl) dest;
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
    public Double2x2 to2x2(@Mutated Double2x2 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return to2x2_identity(dest);
        return to2x2_general(dest);
    }


    /**
     * Private body of {@code to3x3}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x3} dispatcher.
     */
    private Double3x3 to3x3_orthogonal_general(@Mutated Double3x3 dest, int _props) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0;
        d.properties = _props;
        return d;
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
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0;
        d.properties = Joml.BIT_TRANSLATION;
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
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
        this.m02 = 0.0;
        this.m12 = 0.0;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.m02 = Math.fma(t, other.m02() - this.m02, this.m02);
        d.m12 = Math.fma(t, other.m12() - this.m12, this.m12);
        d.properties = ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & ((Double2x3Impl) other).properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & ((Double2x3Impl) other).properties) >> 1);
        return d;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general(Double2x3R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        double _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        double _buf2 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        double _buf3 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        double _buf4 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation(Double2x3R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal(Double2x3R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        double _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        double _buf2 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        double _buf3 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        double _buf4 = Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation_translation(Double2x3R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal_translation(Double2x3R right, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = this.m00;
        double _buf1 = this.m10;
        double _buf2 = this.m01;
        double _buf3 = this.m11;
        double _buf4 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
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
    public Double2x3 mul(Double2x3R right, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Double2x3Impl) right).properties;
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
    @Mutated public Double2x3 mul(Double2x3R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(right);
        int q = ((Double2x3Impl) right).properties;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(m00, this.m00, m10 * this.m01);
        double _buf1 = Math.fma(m00, this.m10, m10 * this.m11);
        double _buf2 = Math.fma(m01, this.m00, m11 * this.m01);
        double _buf3 = Math.fma(m01, this.m10, m11 * this.m11);
        d.m02 = Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02));
        d.m12 = Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double2x3 mul_identity(Double2x2R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code mul}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code mul} and {@code preMul}; reached only through
     * them.
     */
    private Double2x3 mul_identity_self(Double2x2R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_translation(Double2x2R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = right.m00();
        d.m10 = right.m10();
        d.m01 = right.m01();
        d.m11 = right.m11();
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_orthogonal(Double2x2R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(right.m00(), this.m11, -(right.m10() * this.m10));
        double _buf0 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m01 = Math.fma(right.m01(), this.m11, -(right.m11() * this.m10));
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m10 = _buf0;
        d.properties = (((Double2x2Impl) right).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x3 mul_general(Double2x2R right, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        double _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
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
    public Double2x3 mul(Double2x2R right, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 mul(Double2x2R right) {
        if (Joml.RETURN_NEW) return mul(right, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(right, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(right, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal(right, this);
        return mul_general(right, this);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double3x3R right, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _buf0 = Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m00, right.m10() * this.m01));
        double _buf1 = Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11));
        d.m20 = right.m20();
        double _buf2 = Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m00, right.m11() * this.m01));
        double _buf3 = Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11));
        d.m21 = right.m21();
        double _buf4 = Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m00, right.m12() * this.m01));
        d.m12 = Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11));
        d.m22 = right.m22();
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double3x3R right, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = Math.fma(right.m20(), this.m02, right.m00());
        d.m10 = Math.fma(right.m20(), this.m12, right.m10());
        d.m20 = right.m20();
        d.m01 = Math.fma(right.m21(), this.m02, right.m01());
        d.m11 = Math.fma(right.m21(), this.m12, right.m11());
        d.m21 = right.m21();
        d.m02 = Math.fma(right.m22(), this.m02, right.m02());
        d.m12 = Math.fma(right.m22(), this.m12, right.m12());
        d.m22 = right.m22();
        d.properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return d;
    }

    /** Private column 0 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_sbc092c2_c0(Double3x3Impl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r12) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, -(_r4 * _r5)));
        _dst.m10 = Math.fma(_r0, _r12, Math.fma(_r2, _r5, _r4 * _r3));
        _dst.m20 = _r0;
    }

    /** Private column 1 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_sbc092c2_c1(Double3x3Impl _dst, double _r6, double _r1, double _r7, double _r3, double _r8, double _r5, double _r12) {
        _dst.m01 = Math.fma(_r6, _r1, Math.fma(_r7, _r3, -(_r8 * _r5)));
        _dst.m11 = Math.fma(_r6, _r12, Math.fma(_r7, _r5, _r8 * _r3));
        _dst.m21 = _r6;
    }

    /** Private column 2 of {@code mul_orthogonal}: computes and stores it; reached only through it. */
    private void mul_orthogonal_sbc092c2_c2(Double3x3Impl _dst, double _r9, double _r1, double _r10, double _r3, double _r11, double _r5, double _r12) {
        _dst.m02 = Math.fma(_r9, _r1, Math.fma(_r10, _r3, -(_r11 * _r5)));
        _dst.m12 = Math.fma(_r9, _r12, Math.fma(_r10, _r5, _r11 * _r3));
        _dst.m22 = _r9;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double3x3R right, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _r0 = right.m20();
        double _r1 = this.m02;
        double _r2 = right.m00();
        double _r3 = this.m11;
        double _r4 = right.m10();
        double _r5 = this.m10;
        double _r6 = right.m21();
        double _r7 = right.m01();
        double _r8 = right.m11();
        double _r9 = right.m22();
        double _r10 = right.m02();
        double _r11 = right.m12();
        double _r12 = this.m12;
        mul_orthogonal_sbc092c2_c0(d, _r0, _r1, _r2, _r3, _r4, _r5, _r12);
        mul_orthogonal_sbc092c2_c1(d, _r6, _r1, _r7, _r3, _r8, _r5, _r12);
        mul_orthogonal_sbc092c2_c2(d, _r9, _r1, _r10, _r3, _r11, _r5, _r12);
        d.properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double3x3 mul_translation_identity(Double3x3R right, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double3x3R right, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = right.m02() + this.m02;
        d.m12 = right.m12() + this.m12;
        d.m22 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) right).properties;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double3x3 mul_orthogonal_identity(Double3x3R right, @Mutated Double3x3 dest, int _props) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = 1.0;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation(Double3x3R right, @Mutated Double3x3 dest, int _props) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _buf0 = this.m00;
        double _buf1 = this.m10;
        d.m20 = 0.0;
        double _buf2 = this.m01;
        double _buf3 = this.m11;
        d.m21 = 0.0;
        double _buf4 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m22 = 1.0;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
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
    public Double3x3 mul(Double3x3R right, @Mutated Double3x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(right);
        int q = ((Double3x3Impl) right).properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_translation_identity(right, dest);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right, dest);
            return mul_translation(right, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_orthogonal_identity(right, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right, dest);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_orthogonal_identity(right, dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, dest, Joml.BIT_AFFINE & q);
        return mul_general(right, dest);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        double _buf1 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        double _buf2 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        double _buf3 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        double _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = other.m00();
        double _buf1 = other.m10();
        double _buf2 = other.m01();
        double _buf3 = other.m11();
        double _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        double _buf1 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        double _buf2 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        double _buf3 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        double _buf4 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.m02 = _buf4;
        d.properties = Joml.BIT_ORTHOGONAL & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation_translation(Double2x3R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.properties = Joml.BIT_TRANSLATION & ((Double2x3Impl) other).properties;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal_translation(Double2x3R other, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
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
    public Double2x3 preMul(Double2x3R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return dest.set(other);
        int q = ((Double2x3Impl) other).properties;
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
    @Mutated public Double2x3 preMul(Double2x3R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this.set(other);
        int q = ((Double2x3Impl) other).properties;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(m00, this.m00, m01 * this.m10);
        d.m10 = Math.fma(m10, this.m00, m11 * this.m10);
        double _buf1 = Math.fma(m00, this.m01, m01 * this.m11);
        d.m11 = Math.fma(m10, this.m01, m11 * this.m11);
        double _buf2 = Math.fma(m00, this.m02, Math.fma(m01, this.m12, m02));
        d.m12 = Math.fma(m10, this.m02, Math.fma(m11, this.m12, m12));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }




    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_translation(Double2x2R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = other.m00();
        d.m10 = other.m10();
        d.m01 = other.m01();
        d.m11 = other.m11();
        double _buf0 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m02 = _buf0;
        d.properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_orthogonal(Double2x2R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(other.m00(), this.m11, other.m01() * this.m10);
        double _buf0 = Math.fma(other.m10(), this.m11, other.m11() * this.m10);
        d.m01 = Math.fma(other.m01(), this.m11, -(other.m00() * this.m10));
        d.m11 = Math.fma(other.m11(), this.m11, -(other.m10() * this.m10));
        double _buf1 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m10 = _buf0;
        d.m02 = _buf1;
        d.properties = (((Double2x2Impl) other).properties & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x3 preMul_general(Double2x2R other, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        d.m10 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        double _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        d.m11 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        double _buf2 = Math.fma(other.m00(), this.m02, other.m01() * this.m12);
        d.m12 = Math.fma(other.m10(), this.m02, other.m11() * this.m12);
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
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
    public Double2x3 preMul(Double2x2R other, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(other, dest);
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
    @Mutated public Double2x3 preMul(Double2x2R other) {
        if (Joml.RETURN_NEW) return preMul(other, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity_self(other, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation(other, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal(other, this);
        return preMul_general(other, this);
    }

    /**
     * Private column 0 of {@code preMul_general}: computes and stores it. Shared by 2 identical
     * private paths of {@code preMul}; reached only through it.
     */
    private void preMul_general_s1154904e_c0(Double3x3Impl _dst, double _r0, double _r1, double _r2, double _r3, double _r9, double _r10, double _r12, double _r13) {
        _dst.m00 = Math.fma(_r0, _r1, _r2 * _r3);
        _dst.m10 = Math.fma(_r9, _r1, _r10 * _r3);
        _dst.m20 = Math.fma(_r12, _r1, _r13 * _r3);
    }

    /** Private column 1 of {@code preMul_general}: computes and stores it; reached only through it. */
    private void preMul_general_s1154904e_c1(Double3x3Impl _dst, double _r0, double _r4, double _r2, double _r5, double _r9, double _r10, double _r12, double _r13) {
        _dst.m01 = Math.fma(_r0, _r4, _r2 * _r5);
        _dst.m11 = Math.fma(_r9, _r4, _r10 * _r5);
        _dst.m21 = Math.fma(_r12, _r4, _r13 * _r5);
    }

    /**
     * Private column 2 of {@code preMul_general}: computes and stores it. Shared by 2 identical
     * private paths of {@code preMul}; reached only through it.
     */
    private void preMul_general_s1154904e_c2(Double3x3Impl _dst, double _r0, double _r6, double _r2, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.m02 = Math.fma(_r0, _r6, Math.fma(_r2, _r7, _r8));
        _dst.m12 = Math.fma(_r9, _r6, Math.fma(_r10, _r7, _r11));
        _dst.m22 = Math.fma(_r12, _r6, Math.fma(_r13, _r7, _r14));
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general(Double3x3R other, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _r0 = other.m00();
        double _r1 = this.m00;
        double _r2 = other.m01();
        double _r3 = this.m10;
        double _r4 = this.m01;
        double _r5 = this.m11;
        double _r6 = this.m02;
        double _r7 = this.m12;
        double _r8 = other.m02();
        double _r9 = other.m10();
        double _r10 = other.m11();
        double _r11 = other.m12();
        double _r12 = other.m20();
        double _r13 = other.m21();
        double _r14 = other.m22();
        preMul_general_s1154904e_c0(d, _r0, _r1, _r2, _r3, _r9, _r10, _r12, _r13);
        preMul_general_s1154904e_c1(d, _r0, _r4, _r2, _r5, _r9, _r10, _r12, _r13);
        preMul_general_s1154904e_c2(d, _r0, _r6, _r2, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        d.properties = 0;
        return d;
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double3x3R other, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _buf0 = other.m00();
        double _buf1 = other.m10();
        double _buf2 = other.m20();
        double _buf3 = other.m01();
        double _buf4 = other.m11();
        double _buf5 = other.m21();
        double _buf6 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        double _buf7 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m22 = Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22()));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m01 = _buf3;
        d.m11 = _buf4;
        d.m21 = _buf5;
        d.m02 = _buf6;
        d.m12 = _buf7;
        d.properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return d;
    }

    /** Private column 1 of {@code preMul_orthogonal}: computes and stores it; reached only through it. */
    private void preMul_orthogonal_s1154904e_c1(Double3x3Impl _dst, double _r2, double _r1, double _r0, double _r3, double _r8, double _r7, double _r11, double _r10) {
        _dst.m01 = Math.fma(_r2, _r1, -(_r0 * _r3));
        _dst.m11 = Math.fma(_r8, _r1, -(_r7 * _r3));
        _dst.m21 = Math.fma(_r11, _r1, -(_r10 * _r3));
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double3x3R other, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _r0 = other.m00();
        double _r1 = this.m11;
        double _r2 = other.m01();
        double _r3 = this.m10;
        double _r4 = this.m02;
        double _r5 = this.m12;
        double _r6 = other.m02();
        double _r7 = other.m10();
        double _r8 = other.m11();
        double _r9 = other.m12();
        double _r10 = other.m20();
        double _r11 = other.m21();
        double _r12 = other.m22();
        preMul_general_s1154904e_c0(d, _r0, _r1, _r2, _r3, _r7, _r8, _r10, _r11);
        preMul_orthogonal_s1154904e_c1(d, _r2, _r1, _r0, _r3, _r8, _r7, _r11, _r10);
        preMul_general_s1154904e_c2(d, _r0, _r4, _r2, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12);
        d.properties = Joml.BIT_ORTHOGONAL & ((Double3x3Impl) other).properties;
        return d;
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double3x3R other, @Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m20 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m21 = 0.0;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0;
        d.properties = Joml.BIT_TRANSLATION & ((Double3x3Impl) other).properties;
        return d;
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double3x3R other, @Mutated Double3x3 dest, int _props) {
        Double3x3Impl d = (Double3x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = 0.0;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = 0.0;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = 1.0;
        d.properties = _props;
        return d;
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
            return preMul_translation(other, dest);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_orthogonal_identity(other, dest, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other, dest);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_orthogonal_identity(other, dest, Joml.BIT_AFFINE & q);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, dest, Joml.BIT_AFFINE & q);
        return preMul_general(other, dest);
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.m02 = Math.fma(weight, other.m02(), this.m02);
        d.m12 = Math.fma(weight, other.m12(), this.m12);
        d.properties = Joml.BIT_AFFINE & ((Double2x3Impl) other).properties;
        return d;
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
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        this.m00 = _t1;
        this.m10 = _t0;
        this.m01 = -_t0;
        this.m11 = _t1;
        this.m02 = 0.0;
        this.m12 = 0.0;
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
    @Mutated public Double2x3 makeScaling(double vX, double vY) {
        this.m00 = vX;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = vY;
        this.m02 = 0.0;
        this.m12 = 0.0;
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
        this.m00 = s;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = s;
        this.m02 = 0.0;
        this.m12 = 0.0;
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
        return makeTranslation(v.x(), v.y());
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
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
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
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return this
     */
    @Mutated public Double2x3 makeView(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        this.m00 = _t0_inv + _t0_inv;
        this.m10 = 0.0;
        this.m01 = 0.0;
        this.m11 = _t1_inv + _t1_inv;
        this.m02 = -((left + right) * _t0_inv);
        this.m12 = -((bottom + top) * _t1_inv);
        this.properties = Joml.BIT_AFFINE;
        return this;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x3 preRotate_orthogonal_general(double angle, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.m00, _t1, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t1);
        double _buf1 = Math.fma(this.m01, _t1, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t1);
        double _buf2 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = _props;
        return d;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preRotate} and {@code rotate}; reached only through them.
     */
    private Double2x3 preRotate_identity(double angle, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preRotate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preRotate} and {@code rotate};
     * reached only through them.
     */
    private Double2x3 preRotate_identity_self(double angle, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x3 preRotate_translation(double angle, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
        double _buf0 = Math.fma(this.m02, _t1, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t1);
        d.m02 = _buf0;
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
    public Double2x3 preRotate(double angle, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preRotate(double angle) {
        if (Joml.RETURN_NEW) return preRotate(angle, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle, this);
        return preRotate_orthogonal_general(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        return preRotateAround(angle, pivot.x(), pivot.y(), dest);
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
        return preRotateAround(angle, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Double2x3 preRotateAround_identity(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m02 = Math.fma(pivotX, _t5, pivotY * _t0);
        d.m12 = Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_translation(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        double _buf0 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m02 = _buf0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_orthogonal(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _buf0 = Math.fma(this.m00, _t2, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t2);
        double _buf1 = Math.fma(this.m01, _t2, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t2);
        double _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double2x3 preRotateAround_general(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        double _buf0 = Math.fma(this.m00, _t2, -(this.m10 * _t0));
        d.m10 = Math.fma(this.m00, _t0, this.m10 * _t2);
        double _buf1 = Math.fma(this.m01, _t2, -(this.m11 * _t0));
        d.m11 = Math.fma(this.m01, _t0, this.m11 * _t2);
        double _buf2 = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0));
        d.m12 = Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
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
    public Double2x3 preRotateAround(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preRotateAround(double angle, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preRotateAround(angle, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY, this);
        return preRotateAround_general(angle, pivotX, pivotY, this);
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
    public @Mutated Double2x3 preScale(Double2R v) {
        return preScale(v.x(), v.y());
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_identity(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = vY;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation_self(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = vX;
        d.m11 = vY;
        d.m02 = this.m02 * vX;
        d.m12 = this.m12 * vY;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_general(double vX, double vY, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScale_translation_self(vX, vY, this);
        return preScale_general(vX, vY, this);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_identity(double s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = 0.0;
        d.m12 = 0.0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }



    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation(double s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScale}, specialized by runtime matrix
     * properties; reached only through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_translation_self(double s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s;
        d.m11 = s;
        d.m02 = s * this.m02;
        d.m12 = s * this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double2x3 preScale_general(double s, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m00 = s;
            d.m11 = s;
            d.properties = Joml.BIT_AFFINE;
            return d;
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
        return preScaleAround(s, pivot.x(), pivot.y(), dest);
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
        return preScaleAround(s, pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = pivotX * _t0;
        d.m12 = pivotY * _t0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity_self(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = pivotX * _t0;
        d.m12 = pivotY * _t0;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_translation_self(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s * this.m00;
        d.m10 = s * this.m10;
        d.m01 = s * this.m01;
        d.m11 = s * this.m11;
        d.m02 = Math.fma(s, this.m02, pivotX * _t0);
        d.m12 = Math.fma(s, this.m12, pivotY * _t0);
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
    public Double2x3 preScaleAround(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 preScaleAround(double s, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(s, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation_self(s, pivotX, pivotY, this);
        return preScaleAround_general(s, pivotX, pivotY, this);
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
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
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
        return preScaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = sY;
        d.m02 = pivotX * (1.0 - sX);
        d.m12 = pivotY * (1.0 - sY);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preScaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_identity_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = pivotX * (1.0 - sX);
        d.m12 = pivotY * (1.0 - sY);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double2x3 preScaleAround_general(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = sX * this.m00;
        d.m10 = sY * this.m10;
        d.m01 = sX * this.m01;
        d.m11 = sY * this.m11;
        d.m02 = Math.fma(pivotX, 1.0 - sX, sX * this.m02);
        d.m12 = Math.fma(pivotY, 1.0 - sY, sY * this.m12);
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
    public Double2x3 preScaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity(sX, sY, pivotX, pivotY, dest);
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
    @Mutated public Double2x3 preScaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return preScaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        return preScaleAround_general(sX, sY, pivotX, pivotY, this);
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
        return preTranslate(v.x(), v.y(), dest);
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
        return preTranslate(v.x(), v.y());
    }




    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code preTranslate} and {@code translate}; reached only through
     * them.
     */
    private Double2x3 preTranslate_translation(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = 1.0;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = 1.0;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }


    /**
     * Private in-place self-form body of {@code preTranslate}, specialized by runtime matrix
     * properties. Shared by the identical private paths of {@code preTranslate} and
     * {@code translate}; reached only through them.
     */
    private Double2x3 preTranslate_translation_self(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m02 = this.m02 + vX;
        d.m12 = this.m12 + vY;
        d.properties = Joml.BIT_TRANSLATION;
        return d;
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_orthogonal(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
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
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double2x3 preTranslate_general(double vX, double vY, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY, this);
        return preTranslate_general(vX, vY, this);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_orthogonal_general(double angle, @Mutated Double2x3 dest, int _props) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _buf0 = Math.fma(this.m00, _t1, this.m01 * _t0);
        double _buf1 = Math.fma(this.m10, _t1, this.m11 * _t0);
        d.m01 = Math.fma(this.m01, _t1, -(this.m00 * _t0));
        d.m11 = Math.fma(this.m11, _t1, -(this.m10 * _t0));
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.properties = _props;
        return d;
    }




    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x3 rotate_translation(double angle, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        d.m00 = _t1;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t1;
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
    public Double2x3 rotate(double angle, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 rotate(double angle) {
        if (Joml.RETURN_NEW) return rotate(angle, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotate_identity_self(angle, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle, this);
        return rotate_orthogonal_general(angle, this, (p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE);
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
        return rotateAround(angle, pivot.x(), pivot.y(), dest);
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
        return rotateAround(angle, pivot.x(), pivot.y());
    }



    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_translation(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        d.m00 = _t2;
        d.m10 = _t0;
        d.m01 = -_t0;
        d.m11 = _t2;
        d.m02 = Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, this.m02));
        d.m12 = Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, this.m12));
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_orthogonal(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(this.m00, _t2, this.m01 * _t0);
        double _buf1 = Math.fma(this.m10, _t2, this.m11 * _t0);
        double _buf2 = Math.fma(this.m01, _t2, -(this.m00 * _t0));
        double _buf3 = Math.fma(this.m11, _t2, -(this.m10 * _t0));
        d.m02 = Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02));
        d.m12 = Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double2x3 rotateAround_general(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        double _buf0 = Math.fma(this.m00, _t2, this.m01 * _t0);
        double _buf1 = Math.fma(this.m10, _t2, this.m11 * _t0);
        double _buf2 = Math.fma(this.m01, _t2, -(this.m00 * _t0));
        double _buf3 = Math.fma(this.m11, _t2, -(this.m10 * _t0));
        d.m02 = Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02));
        d.m12 = Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
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
    public Double2x3 rotateAround(double angle, double pivotX, double pivotY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 rotateAround(double angle, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return rotateAround(angle, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY, this);
        return rotateAround_general(angle, pivotX, pivotY, this);
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
    public @Mutated Double2x3 scale(Double2R v) {
        return scale(v.x(), v.y());
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_translation(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = vX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x3 scale_translation_self(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = vX;
        d.m11 = vY;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_general(double vX, double vY, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m00 = vX;
            d.m11 = vY;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(vX, vY, this);
        return scale_general(vX, vY, this);
    }




    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_translation(double s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scale}, specialized by runtime matrix properties;
     * reached only through the public {@code scale} dispatcher.
     */
    private Double2x3 scale_translation_self(double s, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = s;
        d.m11 = s;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double2x3 scale_general(double s, @Mutated Double2x3 dest) {
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
            Double2x3Impl d = (Double2x3Impl) this;
            d.m00 = s;
            d.m11 = s;
            d.properties = Joml.BIT_AFFINE;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scale_translation_self(s, this);
        return scale_general(s, this);
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
        return scaleAround(s, pivot.x(), pivot.y(), dest);
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
        return scaleAround(s, pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = s;
        d.m02 = Math.fma(pivotX, _t0, this.m02);
        d.m12 = Math.fma(pivotY, _t0, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        d.m00 = s;
        d.m11 = s;
        d.m02 = Math.fma(pivotX, _t0, this.m02);
        d.m12 = Math.fma(pivotY, _t0, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _buf0 = s * this.m00;
        double _buf1 = s * this.m10;
        double _buf2 = s * this.m01;
        double _buf3 = s * this.m11;
        d.m02 = Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02));
        d.m12 = Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
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
    public Double2x3 scaleAround(double s, double pivotX, double pivotY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 scaleAround(double s, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(s, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(s, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(s, pivotX, pivotY, this);
        return scaleAround_orthogonal(s, pivotX, pivotY, this);
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
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y(), dest);
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
        return scaleAround(s.x(), s.y(), pivot.x(), pivot.y());
    }




    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = sX;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = sY;
        d.m02 = Math.fma(pivotX, 1.0 - sX, this.m02);
        d.m12 = Math.fma(pivotY, 1.0 - sY, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code scaleAround}, specialized by runtime matrix
     * properties; reached only through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_translation_self(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = sX;
        d.m11 = sY;
        d.m02 = Math.fma(pivotX, 1.0 - sX, this.m02);
        d.m12 = Math.fma(pivotY, 1.0 - sY, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double2x3 scaleAround_orthogonal(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        double _buf0 = sX * this.m00;
        double _buf1 = sX * this.m10;
        double _buf2 = sY * this.m01;
        double _buf3 = sY * this.m11;
        d.m02 = Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02));
        d.m12 = Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
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
    public Double2x3 scaleAround(double sX, double sY, double pivotX, double pivotY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 scaleAround(double sX, double sY, double pivotX, double pivotY) {
        if (Joml.RETURN_NEW) return scaleAround(sX, sY, pivotX, pivotY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preScaleAround_identity_self(sX, sY, pivotX, pivotY, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation_self(sX, sY, pivotX, pivotY, this);
        return scaleAround_orthogonal(sX, sY, pivotX, pivotY, this);
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
        return translate(v.x(), v.y(), dest);
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
        return translate(v.x(), v.y());
    }






    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_orthogonal(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = this.m00;
        double _buf1 = this.m10;
        double _buf2 = this.m01;
        double _buf3 = this.m11;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double2x3 translate_general(double vX, double vY, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _buf0 = this.m00;
        double _buf1 = this.m10;
        double _buf2 = this.m01;
        double _buf3 = this.m11;
        d.m02 = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.m12 = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
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
    public Double2x3 translate(double vX, double vY, @Mutated Double2x3 dest) {
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
    @Mutated public Double2x3 translate(double vX, double vY) {
        if (Joml.RETURN_NEW) return translate(vX, vY, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2x3Impl d = (Double2x3Impl) this;
            d.m02 = vX;
            d.m12 = vY;
            d.properties = Joml.BIT_TRANSLATION;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preTranslate_translation_self(vX, vY, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY, this);
        return translate_general(vX, vY, this);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_identity(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = -((left + right) * _t0_inv);
        d.m12 = -((bottom + top) * _t1_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double2x3 view_identity_self(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = -((left + right) * _t0_inv);
        d.m12 = -((bottom + top) * _t1_inv);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_translation(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m10 = 0.0;
        d.m01 = 0.0;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private in-place self-form body of {@code view}, specialized by runtime matrix properties;
     * reached only through the public {@code view} dispatcher.
     */
    private Double2x3 view_translation_self(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        d.m00 = _t0_inv + _t0_inv;
        d.m11 = _t1_inv + _t1_inv;
        d.m02 = Math.fma(-(left + right), _t0_inv, this.m02);
        d.m12 = Math.fma(-(bottom + top), _t1_inv, this.m12);
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_orthogonal(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * this.m00;
        double _buf1 = _sp0 * this.m10;
        double _buf2 = _sp1 * this.m01;
        double _buf3 = _sp1 * this.m11;
        d.m02 = Math.fma(-this.m01, _sp3, Math.fma(-this.m00, _sp2, this.m02));
        d.m12 = Math.fma(-this.m11, _sp3, Math.fma(-this.m10, _sp2, this.m12));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double2x3 view_general(double left, double right, double bottom, double top, @Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        double _buf0 = _sp0 * this.m00;
        double _buf1 = _sp0 * this.m10;
        double _buf2 = _sp1 * this.m01;
        double _buf3 = _sp1 * this.m11;
        d.m02 = this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2));
        d.m12 = this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2));
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m01 = _buf2;
        d.m11 = _buf3;
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
    @Mutated public Double2x3 view(double left, double right, double bottom, double top) {
        if (Joml.RETURN_NEW) return view(left, right, bottom, top, Joml.double2x3());
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation_self(left, right, bottom, top, this);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal(left, right, bottom, top, this);
        return view_general(left, right, bottom, top, this);
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
        return mul(v.x(), v.y(), v.z(), dest);
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_translation(double vX, double vY, double vZ, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m02, vZ, vX);
        d.y = Math.fma(this.m12, vZ, vY);
        return d;
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general(double vX, double vY, double vZ, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY));
        d.y = Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY));
        return d;
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            Double2Impl d = (Double2Impl) dest;
            d.x = vX;
            d.y = vY;
            return d;
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(vX, vY, vZ, dest);
        return mul_general(vX, vY, vZ, dest);
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
        return transformDirection(v.x(), v.y(), dest);
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code transformDirection} and {@code transformPosition};
     * reached only through them.
     */
    private Double2 transformDirection_identity(double vX, double vY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = vX;
        d.y = vY;
        return d;
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Double2 transformDirection_general(double vX, double vY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m00, vX, this.m01 * vY);
        d.y = Math.fma(this.m10, vX, this.m11 * vY);
        return d;
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
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return transformDirection_identity(vX, vY, dest);
        return transformDirection_general(vX, vY, dest);
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
        return transformPosition(v.x(), v.y(), dest);
    }




    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_general(double vX, double vY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02));
        d.y = Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12));
        return d;
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return transformDirection_identity(vX, vY, dest);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            Double2Impl d = (Double2Impl) dest;
            d.x = this.m02 + vX;
            d.y = this.m12 + vY;
            return d;
        }
        return transformPosition_general(vX, vY, dest);
    }

    public double m00() { return this.m00; }
    public double m01() { return this.m01; }
    public double m02() { return this.m02; }
    public double m10() { return this.m10; }
    public double m11() { return this.m11; }
    public double m12() { return this.m12; }

    @Override public String toString() {
        return "Double2x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x3Impl)) return false;
        Double2x3Impl o = (Double2x3Impl) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m02) == Double.doubleToLongBits(o.m02)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m12) == Double.doubleToLongBits(o.m12);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m02) ^ (Double.doubleToLongBits(m02) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m12) ^ (Double.doubleToLongBits(m12) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m02)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m12);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m02)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m12);
    }

    @Override public boolean equalsEpsilon(Double2x3R other, double epsilon) {
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m02 - other.m02()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon
            && Math.abs(m12 - other.m12()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadCM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Double2x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double2x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double2x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    public Double2x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m02;
        dest[offset + 5] = (float) this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadCM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public Double2x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double2x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double2x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    public Double2x3 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadRM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Double2x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double2x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double2x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Double2x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m10;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadRM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.properties = determineProperties();
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public Double2x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double2x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double2x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    public Double2x3 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
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
    public @Mutated Double2x3 loadCM(double[] src, int offset, int stride) {
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
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double2x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    public Double2x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m02;
        dest[_p2 + 1] = (float) this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadCM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    public Double2x3 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
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
    public @Mutated Double2x3 loadRM(double[] src, int offset, int stride) {
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double2x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    public Double2x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m12;
        return dest;
    }
    public @Mutated Double2x3 loadRM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    public Double2x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }

    public double[] storeCM3x3(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeCM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public Double2x3 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }
    public MemorySegment storeCM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    public float[] storeCM3x3(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) this.m01;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) 0.0;
        dest[offset + 6] = (float) this.m02;
        dest[offset + 7] = (float) this.m12;
        dest[offset + 8] = (float) 1.0;
        return dest;
    }
    public FloatBuffer storeCM3x3Absolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeCM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, index, buf);
    }
    public Double2x3 storeCM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3FloatUnsafe(this, address);
    }
    public MemorySegment storeCM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Float(this, offset, dest);
    }

    public double[] storeRM3x3(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeRM3x3Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public ByteBuffer storeRM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }
    public Double2x3 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }
    public MemorySegment storeRM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    public float[] storeRM3x3(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m10;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) this.m12;
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
    public Double2x3 storeRM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3FloatUnsafe(this, address);
    }
    public MemorySegment storeRM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3Float(this, offset, dest);
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
        dest[offset + 12] = this.m02;
        dest[offset + 13] = this.m12;
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
    public Double2x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
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
        dest[offset + 12] = (float) this.m02;
        dest[offset + 13] = (float) this.m12;
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
    public Double2x3 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }
    public MemorySegment storeCM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Float(this, offset, dest);
    }

    public double[] storeRM4x4(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
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
    public DoubleBuffer storeRM4x4Absolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }
    public Double2x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    public float[] storeRM4x4(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) 0.0;
        dest[offset + 3] = (float) this.m02;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) 0.0;
        dest[offset + 7] = (float) this.m12;
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
    public Double2x3 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
