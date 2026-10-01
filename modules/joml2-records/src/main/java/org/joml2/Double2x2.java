// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable 2x2 matrix of double-precision {@code double} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * Structural property bits: the matrix caches whether it is known to be the identity, a pure
 * translation, orthogonal (a proper rotation, with any translation) or affine, and the operations
 * dispatch to cheaper arms on those bits. The {@code make*} factories set the bits from what they
 * construct and the computing operations derive them from their operands' bits; the element
 * constructors and the {@code load*} factories recompute them with {@code determineProperties()},
 * which compares elements exactly against {@code 0} and {@code 1} and infers identity, translation
 * and affine only. The bits read this 2x2 matrix homogeneously, as a 1D transform whose last row is
 * {@code (0, 1)} and whose {@code m01} is the translation: a 2D rotation held in a 2x2 matrix gets
 * no bits at all.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN; the cached structural property bits are ignored, so two matrix objects holding the same
 * elements are equal whatever either one has determined about itself. {@code hashCode} is
 * consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
 *
 * @param m00 the element in row 0, column 0
 * @param m01 the element in row 0, column 1
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param properties the cached structural property bits
 */
public record Double2x2(double m00, double m01, double m10, double m11, int properties) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 32;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 2;

    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 2;

    /** The zero matrix (all components 0). */
    public static final Double2x2 ZERO = new Double2x2(0, 0, 0, 0);

    /** The identity matrix. */
    public static final Double2x2 IDENTITY = new Double2x2();

    /**
     * Canonical constructor, taking the cached property bits as given.
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param properties the cached property bits, taken as given
     */
    public Double2x2(double m00, double m01, double m10, double m11, int properties) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        this.properties = properties;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: the method reads no input.
     */
    public Double2x2() {
        this(1, 0, 0, 1, Joml.BIT_IDENTITY);
    }

    /**
     * Create a matrix from the given elements, computing the cached property bits.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     */
    public Double2x2(double m00, double m01, double m10, double m11) {
        this(m00, m01, m10, m11, props(m00, m01, m10, m11));
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public Double2x2(Double2 c0, Double2 c1) {
        this(c0.x(), c1.x(), c0.y(), c1.y());
    }

    /**
     * Create a matrix from the given column vectors and precomputed property bits (no
     * recomputation).
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param properties the cached property bits, taken as given
     */
    public Double2x2(Double2 c0, Double2 c1, int properties) {
        this(c0.x(), c1.x(), c0.y(), c1.y(), properties);
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double2x2(Double2x3 src) {
        this(src.m00(), src.m01(), src.m10(), src.m11());
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double2x2(Double3x3 src) {
        this(src.m00(), src.m01(), src.m10(), src.m11());
    }

    /**
     * Create an array of {@code length} elements, each {@link #ZERO}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double2x2[] newArray(int length) {
        Double2x2[] a = new Double2x2[length];
        java.util.Arrays.fill(a, ZERO);
        return a;
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double2x2[] newArray(int length, Double2x2 init) {
        java.util.Objects.requireNonNull(init, "init");
        Double2x2[] a = new Double2x2[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m00() { return m00; }

    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m01() { return m01; }

    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m10() { return m10; }

    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m11() { return m11; }

    /** {@return the cached structural property bits} <p>Valid input: any value, NaN and the infinities included. */
    public int properties() { return properties; }

    private static int props(double m00, double m01, double m10, double m11) {
        if (m10 != 0 || m11 != 1) return 0;
        if (m00 != 1) return 1;
        if (m01 != 0) return 7;
        return 15;
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
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.m10 != 0 || this.m11 != 1) return 0;
        if (this.m00 != 1) return 1;
        if (this.m01 != 0) return 7;
        return 15;
    }

    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isIdentity() { return (this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY; }

    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isTranslation() { return (this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION; }

    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isOrthogonal() { return (this.properties & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL; }

    /** {@return whether this matrix is known to be affine} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isAffine() { return (this.properties & Joml.BIT_AFFINE) == Joml.BIT_AFFINE; }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getColumn} and {@code getRow}; reached only through them.
     */
    private Double2 getColumn_identity(int col) {
        return switch (col) {
            case 0 -> new Double2(1.0, 0.0);
            case 1 -> new Double2(0.0, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double2 getColumn_translation(int col) {
        return switch (col) {
            case 0 -> new Double2(1.0, 0.0);
            case 1 -> new Double2(this.m01, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double2 getColumn_affine(int col) {
        return switch (col) {
            case 0 -> new Double2(this.m00, 0.0);
            case 1 -> new Double2(this.m01, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }

    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double2 getColumn_general(int col) {
        return switch (col) {
            case 0 -> new Double2(this.m00, this.m10);
            case 1 -> new Double2(this.m01, this.m11);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }

    /**
     * Get the column at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Double2 getColumn(int col) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getColumn_general(col);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getColumn_translation(col);
        return getColumn_affine(col);
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
        return Math.atan2(this.m10, this.m00);
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double2 getRow_translation(int row) {
        return switch (row) {
            case 0 -> new Double2(1.0, this.m01);
            case 1 -> new Double2(0.0, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double2 getRow_affine(int row) {
        return switch (row) {
            case 0 -> new Double2(this.m00, this.m01);
            case 1 -> new Double2(0.0, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }

    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double2 getRow_general(int row) {
        return switch (row) {
            case 0 -> new Double2(this.m00, this.m01);
            case 1 -> new Double2(this.m10, this.m11);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }

    /**
     * Get the row at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    public Double2 getRow(int row) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return getRow_general(row);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getRow_translation(row);
        return getRow_affine(row);
    }

    /**
     * Compute the cofactor matrix of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x2 cofactor() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m11, -this.m10, -this.m01, this.m00, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, -this.m01, 1.0, 0);
        return new Double2x2(1.0, 0.0, -this.m01, this.m00, 0);
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
            double _t1 = -(this.m01 * this.m10);
            return java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        } else {
            double _t1 = -(this.m01 * this.m10);
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
    public double frobeniusNorm() {
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.m11, this.m11, java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01))));
        } else {
            return java.lang.Math.sqrt(((this.m11) * (this.m11) + (((this.m10) * (this.m10) + (((this.m00) * (this.m00) + (this.m01 * this.m01)))))));
        }
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_fma() {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_fma();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(this.m01 * _t0_inv), 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_affine_mulAdd() {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate_mulAdd();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(this.m01 * _t0_inv), 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general_fma() {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_fma();
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 * _t5_inv, -(this.m01 * _t5_inv), -(this.m10 * _t5_inv), this.m00 * _t5_inv, 0);
    }

    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general_mulAdd() {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invert_degenerate_mulAdd();
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 * _t5_inv, -(this.m01 * _t5_inv), -(this.m10 * _t5_inv), this.m00 * _t5_inv, 0);
    }

    /**
     * Invert this matrix, returning the result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Double2x2 invert() {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_fma();
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, -this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION);
            return invert_affine_fma();
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_general_mulAdd();
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, -this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION);
            return invert_affine_mulAdd();
        }
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_general_fma() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        return new Double2x2(_t6 * _sp0, -(_t8 * _sp1), -(_t9 * _sp0), _t7 * _sp1, 0);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_general_mulAdd() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        return new Double2x2(_t6 * _sp0, -(_t8 * _sp1), -(_t9 * _sp0), _t7 * _sp1, 0);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_fma() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_fma();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t1_inv = 1.0 / _t0;
            return new Double2x2(_t0 * _t1_inv, -(this.m01 * _t0) * _t1_inv, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t2_inv = 1.0 / (this.m00 * _t0);
            return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0) * _t2_inv, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        }
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0) * _t2_inv, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_mulAdd() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invert_degenerate_general_mulAdd();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t1_inv = 1.0 / _t0;
            return new Double2x2(_t0 * _t1_inv, -(this.m01 * _t0) * _t1_inv, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t2_inv = 1.0 / (this.m00 * _t0);
            return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0) * _t2_inv, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        }
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0) * _t2_inv, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Private per-column body of {@code invertProduct_general_s2b9ad38b_tail}, stored into
     * {@code _m}. Shared by 4 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_general_s2b9ad38b_tail_s28811b3_c0_fma(double _t28, double _t29, double _t32, double _t22, double _t23, double _t31, double _t35, double _t27, double[] _m) {
        _m[0] = java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32);
        _m[2] = java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35);
    }

    /**
     * Private per-column body of {@code invertProduct_general_s2b9ad38b_tail}, stored into
     * {@code _m}. Shared by 4 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_general_s2b9ad38b_tail_s28811b3_c0_mulAdd(double _t28, double _t29, double _t32, double _t22, double _t23, double _t31, double _t35, double _t27, double[] _m) {
        _m[0] = ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32));
        _m[2] = ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35));
    }

    /**
     * Private per-column body of {@code invertProduct_general_s2b9ad38b_tail}, stored into
     * {@code _m}. Shared by 4 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_general_s2b9ad38b_tail_s28811b3_c1_fma(double _t28, double _t24, double _t34, double _t30, double _t22, double _t31, double _t33, double _t27, double[] _m) {
        _m[1] = java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34);
        _m[3] = java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33);
    }

    /** Private tail of {@code invertProduct_general}; reached only through it. */
    private Double2x2 invertProduct_general_s2b9ad38b_tail_mulAdd(double _t11_inv, Double2x2 other, double _t10_inv, double _t22, double _t23, double _t27, double _t24, double _t29, double _t28, int _props) {
        double _t30 = -(this.m01 * _t11_inv);
        double _t31 = -(other.m10 * _t10_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_mulAdd(_t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27, _m);
        {
            double _t34 = _t30 * _t22;
            double _t33 = _t27 * _t24;
            _m[1] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
            _m[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], _props);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_fma(Double2x2 other) {
        double _t2 = -(other.m01 * other.m10);
        double _t3 = -(this.m01 * this.m10);
        double _t10 = java.lang.Math.fma(other.m00, other.m11, _t2) - java.lang.Math.fma(other.m01, other.m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t11 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t10_inv = 1.0 / _t10;
        double _t11_inv = 1.0 / _t11;
        double _t22 = other.m11 / _t10;
        double _t23 = this.m11 / _t11;
        double _t27 = other.m00 / _t10;
        double _t24 = this.m00 / _t11;
        double _t29 = -(this.m10 * _t11_inv);
        double _t28 = -(other.m01 * _t10_inv);
        double _t30 = -(this.m01 * _t11_inv);
        double _t31 = -(other.m10 * _t10_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_fma(_t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27, _m);
        invertProduct_general_s2b9ad38b_tail_s28811b3_c1_fma(_t28, _t24, _t30 * _t22, _t30, _t22, _t31, _t27 * _t24, _t27, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_mulAdd(Double2x2 other) {
        double _t2 = -(other.m01 * other.m10);
        double _t3 = -(this.m01 * this.m10);
        double _t10 = ((other.m00) * (other.m11) + (_t2)) - ((other.m01) * (other.m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t11 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t10_inv = 1.0 / _t10;
        double _t11_inv = 1.0 / _t11;
        return invertProduct_general_s2b9ad38b_tail_mulAdd(_t11_inv, other, _t10_inv, other.m11 / _t10, this.m11 / _t11, other.m00 / _t10, this.m00 / _t11, -(this.m10 * _t11_inv), -(other.m01 * _t10_inv), 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_fma(Double2x2 other) {
        double _t1 = -(other.m01 * other.m10);
        double _t5 = java.lang.Math.fma(other.m00, other.m11, _t1) - java.lang.Math.fma(other.m01, other.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(other.m11 / _t5, -(other.m01 * _t5_inv), -(other.m10 * _t5_inv), other.m00 / _t5, other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_mulAdd(Double2x2 other) {
        double _t1 = -(other.m01 * other.m10);
        double _t5 = ((other.m00) * (other.m11) + (_t1)) - ((other.m01) * (other.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(other.m11 / _t5, -(other.m01 * _t5_inv), -(other.m10 * _t5_inv), other.m00 / _t5, other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_fma(Double2x2 other, int _props) {
        double _t1 = -this.m01;
        double _t3 = -(other.m01 * other.m10);
        double _t7 = java.lang.Math.fma(other.m00, other.m11, _t3) - java.lang.Math.fma(other.m01, other.m10, _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t7_inv = 1.0 / _t7;
        double _t10 = other.m11 / _t7;
        double _t12 = -(other.m10 * _t7_inv);
        return new Double2x2(_t10, java.lang.Math.fma(_t1, _t10, -(other.m01 * _t7_inv)), _t12, java.lang.Math.fma(_t1, _t12, other.m00 / _t7), _props);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_mulAdd(Double2x2 other, int _props) {
        double _t1 = -this.m01;
        double _t3 = -(other.m01 * other.m10);
        double _t7 = ((other.m00) * (other.m11) + (_t3)) - ((other.m01) * (other.m10) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t7_inv = 1.0 / _t7;
        double _t10 = other.m11 / _t7;
        double _t12 = -(other.m10 * _t7_inv);
        return new Double2x2(_t10, ((_t1) * (_t10) - (other.m01 * _t7_inv)), _t12, ((_t1) * (_t12) + (other.m00 / _t7)), _props);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_fma(Double2x2 other) {
        double _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t2 = -(other.m01 * other.m10);
        double _t4 = 1.0 / _t1;
        double _t9 = -(this.m01 / _t1);
        double _t11 = java.lang.Math.fma(other.m00, other.m11, _t2) - java.lang.Math.fma(other.m01, other.m10, _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t11_inv = 1.0 / _t11;
        double _t14 = other.m11 / _t11;
        double _t16 = -(other.m10 * _t11_inv);
        return new Double2x2(_t4 * _t14, java.lang.Math.fma(_t9, _t14, -(other.m01 * _t11_inv)), _t16 * _t4, java.lang.Math.fma(_t16, _t9, other.m00 / _t11), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_mulAdd(Double2x2 other) {
        double _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t2 = -(other.m01 * other.m10);
        double _t4 = 1.0 / _t1;
        double _t9 = -(this.m01 / _t1);
        double _t11 = ((other.m00) * (other.m11) + (_t2)) - ((other.m01) * (other.m10) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t11_inv = 1.0 / _t11;
        double _t14 = other.m11 / _t11;
        double _t16 = -(other.m10 * _t11_inv);
        return new Double2x2(_t4 * _t14, ((_t9) * (_t14) - (other.m01 * _t11_inv)), _t16 * _t4, ((_t16) * (_t9) + (other.m00 / _t11)), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine_fma(Double2x2 other) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        return new Double2x2(1.0 / _t0, -(other.m01 / _t0), 0.0, 1.0, other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine_mulAdd(Double2x2 other) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        return new Double2x2(1.0 / _t0, -(other.m01 / _t0), 0.0, 1.0, other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_identity(Double2x2 other) {
        return new Double2x2(1.0, -this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_translation(Double2x2 other) {
        return new Double2x2(1.0, -other.m01 - this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine_fma(Double2x2 other, int _props) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t2 = 1.0 / _t0;
        return new Double2x2(_t2, java.lang.Math.fma(-this.m01, _t2, -(other.m01 / _t0)), 0.0, 1.0, _props);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine_mulAdd(Double2x2 other, int _props) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t2 = 1.0 / _t0;
        return new Double2x2(_t2, ((-this.m01) * (_t2) - (other.m01 / _t0)), 0.0, 1.0, _props);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity_fma(Double2x2 other) {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        return new Double2x2(1.0 / _t0, -(this.m01 / _t0), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity_mulAdd(Double2x2 other) {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        return new Double2x2(1.0 / _t0, -(this.m01 / _t0), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation_fma(Double2x2 other) {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        return new Double2x2(1.0 / _t0, -other.m01 - this.m01 / _t0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation_mulAdd(Double2x2 other) {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        return new Double2x2(1.0 / _t0, -other.m01 - this.m01 / _t0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_affine_fma(Double2x2 other) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t3 = 1.0 / _t0;
        return new Double2x2(_t3 * (1.0 / _t1), java.lang.Math.fma(-(this.m01 / _t1), _t3, -(other.m01 / _t0)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_affine_mulAdd(Double2x2 other) {
        double _t0 = other.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t1 = this.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t3 = 1.0 / _t0;
        return new Double2x2(_t3 * (1.0 / _t1), ((-(this.m01 / _t1)) * (_t3) - (other.m01 / _t0)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity_fma(Double2x2 other) {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 / _t5, -(this.m01 * _t5_inv), -(this.m10 * _t5_inv), this.m00 / _t5, 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity_mulAdd(Double2x2 other) {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 / _t5, -(this.m01 * _t5_inv), -(this.m10 * _t5_inv), this.m00 / _t5, 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation_fma(Double2x2 other) {
        double _t1 = -other.m01;
        double _t3 = -(this.m01 * this.m10);
        double _t7 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t7_inv = 1.0 / _t7;
        double _t11 = this.m00 / _t7;
        double _t12 = -(this.m10 * _t7_inv);
        return new Double2x2(java.lang.Math.fma(_t1, _t12, this.m11 / _t7), java.lang.Math.fma(_t1, _t11, -(this.m01 * _t7_inv)), _t12, _t11, 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation_mulAdd(Double2x2 other) {
        double _t1 = -other.m01;
        double _t3 = -(this.m01 * this.m10);
        double _t7 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t7_inv = 1.0 / _t7;
        double _t11 = this.m00 / _t7;
        double _t12 = -(this.m10 * _t7_inv);
        return new Double2x2(((_t1) * (_t12) + (this.m11 / _t7)), ((_t1) * (_t11) - (this.m01 * _t7_inv)), _t12, _t11, 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine_fma(Double2x2 other) {
        double _t1 = other.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t2 = -(this.m01 * this.m10);
        double _t5 = 1.0 / _t1;
        double _t7 = -(other.m01 / _t1);
        double _t11 = java.lang.Math.fma(this.m00, this.m11, _t2) - java.lang.Math.fma(this.m01, this.m10, _t2);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(other);
        double _t11_inv = 1.0 / _t11;
        double _t17 = this.m11 / _t11;
        double _t18 = this.m00 / _t11;
        double _t20 = -(this.m10 * _t11_inv);
        double _t21 = -(this.m01 * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        return new Double2x2(java.lang.Math.fma(_t7, _t20, _t22) + java.lang.Math.fma(_t5, _t17, -_t22), java.lang.Math.fma(_t7, _t18, _t23) + java.lang.Math.fma(_t21, _t5, -_t23), _t20, _t18, 0);
    }

    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine_mulAdd(Double2x2 other) {
        double _t1 = other.m00;
        if (!(java.lang.Math.abs(_t1) > 2.2250738585072014E-308 && java.lang.Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t2 = -(this.m01 * this.m10);
        double _t5 = 1.0 / _t1;
        double _t7 = -(other.m01 / _t1);
        double _t11 = ((this.m00) * (this.m11) + (_t2)) - ((this.m01) * (this.m10) + (_t2));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(other);
        double _t11_inv = 1.0 / _t11;
        double _t17 = this.m11 / _t11;
        double _t18 = this.m00 / _t11;
        double _t20 = -(this.m10 * _t11_inv);
        double _t21 = -(this.m01 * _t11_inv);
        double _t22 = _t5 * _t17;
        double _t23 = _t21 * _t5;
        return new Double2x2(((_t7) * (_t20) + (_t22)) + ((_t5) * (_t17) - (_t22)), ((_t7) * (_t18) + (_t23)) + ((_t21) * (_t5) - (_t23)), _t20, _t18, 0);
    }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}, returning the result as a value.
     * <p>
     * It is computed as the product of the two factors' inverses in reverse order, so its accuracy
     * is bounded by the condition numbers of the two factors, not by the condition number of
     * {@code this * other}, which can be as large as their product.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @return the resulting matrix
     */
    public Double2x2 invertProduct(Double2x2 other) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(other);
        return invertProduct_mulAdd(other);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_fma(Double2x2 other) {
        int p = this.properties;
        int q = other.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_fma(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_fma(other);
            return invertProduct_general_affine_fma(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, other.properties);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, -other.m01, 0.0, 1.0, other.properties);
            return invertProduct_identity_affine_fma(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            return invertProduct_translation_affine_fma(other, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_fma(other, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            return invertProduct_translation_affine_fma(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_fma(other);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_fma(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_fma(other);
        return invertProduct_affine_affine_fma(other);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(Double2x2 other) {
        int p = this.properties;
        int q = other.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_general_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity_mulAdd(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation_mulAdd(other);
            return invertProduct_general_affine_mulAdd(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_identity_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, other.properties);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, -other.m01, 0.0, 1.0, other.properties);
            return invertProduct_identity_affine_mulAdd(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            return invertProduct_translation_affine_mulAdd(other, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_translation_mulAdd(other, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            return invertProduct_translation_affine_mulAdd(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_affine_mulAdd(other);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity_mulAdd(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation_mulAdd(other);
        return invertProduct_affine_affine_mulAdd(other);
    }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}), returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 invertProduct(double m00, double m01, double m10, double m11) {
        if (org.joml2.internal.FmaSwitch.ON) return invertProduct_fma(m00, m01, m10, m11);
        return invertProduct_mulAdd(m00, m01, m10, m11);
    }

    /** {@code invertProduct} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_fma(double m00, double m01, double m10, double m11) {
        double _t2 = -(m01 * m10);
        double _t3 = -(this.m01 * this.m10);
        double _t10 = java.lang.Math.fma(m00, m11, _t2) - java.lang.Math.fma(m01, m10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_fma(m00, m01, m10, m11);
        double _t11 = java.lang.Math.fma(this.m00, this.m11, _t3) - java.lang.Math.fma(this.m01, this.m10, _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_fma(m00, m01, m10, m11);
        double _t10_inv = 1.0 / _t10;
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = this.m11 / _t11;
        double _t24 = this.m00 / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(this.m10 * _t11_inv);
        double _t30 = -(this.m01 * _t11_inv);
        double _t31 = -(m10 * _t10_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_fma(_t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27, _m);
        invertProduct_general_s2b9ad38b_tail_s28811b3_c1_fma(_t28, _t24, _t30 * _t22, _t30, _t22, _t31, _t27 * _t24, _t27, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 invertProduct_mulAdd(double m00, double m01, double m10, double m11) {
        double _t2 = -(m01 * m10);
        double _t3 = -(this.m01 * this.m10);
        double _t10 = ((m00) * (m11) + (_t2)) - ((m01) * (m10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11);
        double _t11 = ((this.m00) * (this.m11) + (_t3)) - ((this.m01) * (this.m10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate_mulAdd(m00, m01, m10, m11);
        double _t10_inv = 1.0 / _t10;
        double _t11_inv = 1.0 / _t11;
        double _t22 = m11 / _t10;
        double _t23 = this.m11 / _t11;
        double _t24 = this.m00 / _t11;
        double _t27 = m00 / _t10;
        double _t28 = -(m01 * _t10_inv);
        double _t29 = -(this.m10 * _t11_inv);
        double _t30 = -(this.m01 * _t11_inv);
        double _t31 = -(m10 * _t10_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_mulAdd(_t28, _t29, _t22 * _t23, _t22, _t23, _t31, _t29 * _t27, _t27, _m);
        {
            double _t34 = _t30 * _t22;
            double _t33 = _t27 * _t24;
            _m[1] = ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34));
            _m[3] = ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33));
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private Double2x2 invertProduct_degenerate_general_s2b9ad38b_tail_fma(double _t18, double _t3, double _t37, double _t17, double _t2, double _t13, double _t1, double _t36, double _t12, double _t36_inv, double _t16, double _t37_inv, double _t19, double _t15, double _t0, double _t48, int _props) {
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_fma(_t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53, _m);
        invertProduct_general_s2b9ad38b_tail_s28811b3_c1_fma(_t54, _t50, _t56 * _t48, _t56, _t48, _t57, _t53 * _t50, _t53, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], _props);
    }

    /** Private tail of {@code invertProduct_degenerate_general}; reached only through it. */
    private Double2x2 invertProduct_degenerate_general_s2b9ad38b_tail_mulAdd(double _t18, double _t3, double _t37, double _t17, double _t2, double _t13, double _t1, double _t36, double _t12, double _t36_inv, double _t16, double _t37_inv, double _t19, double _t15, double _t0, double _t48, int _props) {
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_mulAdd(_t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53, _m);
        {
            double _t34 = _t56 * _t48;
            double _t33 = _t53 * _t50;
            _m[1] = ((_t54) * (_t50) + (_t34)) + ((_t56) * (_t48) - (_t34));
            _m[3] = ((_t57) * (_t56) + (_t33)) + ((_t53) * (_t50) - (_t33));
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], _props);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_fma(Double2x2 other) {
        double _t0 = unitScale(other.m00, other.m01, other.m00);
        double _t1 = unitScale(other.m10, other.m11, other.m10);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t12 = other.m01 * _t0;
        double _t13 = other.m00 * _t0;
        double _t14 = other.m11 * _t1;
        double _t15 = other.m10 * _t1;
        double _t16 = this.m10 * _t2;
        double _t17 = this.m00 * _t3;
        double _t18 = this.m11 * _t2;
        double _t19 = this.m01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        return invertProduct_degenerate_general_s2b9ad38b_tail_fma(_t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0 / _t36, _t16, 1.0 / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36, 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_mulAdd(Double2x2 other) {
        double _t0 = unitScale(other.m00, other.m01, other.m00);
        double _t1 = unitScale(other.m10, other.m11, other.m10);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t12 = other.m01 * _t0;
        double _t13 = other.m00 * _t0;
        double _t14 = other.m11 * _t1;
        double _t15 = other.m10 * _t1;
        double _t16 = this.m10 * _t2;
        double _t17 = this.m00 * _t3;
        double _t18 = this.m11 * _t2;
        double _t19 = this.m01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        return invertProduct_degenerate_general_s2b9ad38b_tail_mulAdd(_t18, _t3, _t37, _t17, _t2, _t13, _t1, _t36, _t12, 1.0 / _t36, _t16, 1.0 / _t37, _t19, _t15, _t0, _t14 * _t0 / _t36, 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_fma(Double2x2 other) {
        double _t0 = unitScale(other.m10, other.m11, other.m10);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t6 = other.m11 * _t0;
        double _t7 = other.m00 * _t1;
        double _t8 = other.m01 * _t1;
        double _t9 = other.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        return new Double2x2(_t6 * _t1 / _t14, -(_t8 * _t0 * _t14_inv), -(_t9 * _t1 * _t14_inv), _t7 * _t0 / _t14, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_mulAdd(Double2x2 other) {
        double _t0 = unitScale(other.m10, other.m11, other.m10);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t6 = other.m11 * _t0;
        double _t7 = other.m00 * _t1;
        double _t8 = other.m01 * _t1;
        double _t9 = other.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        return new Double2x2(_t6 * _t1 / _t14, -(_t8 * _t0 * _t14_inv), -(_t9 * _t1 * _t14_inv), _t7 * _t0 / _t14, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2 = unitScale(other.m10, other.m11, other.m10);
        double _t3 = unitScale(other.m00, other.m01, other.m00);
        double _t10 = other.m11 * _t2;
        double _t11 = other.m00 * _t3;
        double _t12 = other.m01 * _t3;
        double _t13 = other.m10 * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(this.m01 * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = java.lang.Math.fma(_t11, _t10, _t22) - java.lang.Math.fma(_t12, _t13, _t22);
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        return new Double2x2(_t28 * _t15, java.lang.Math.fma(_t18, _t28, -(_t12 * _t2 * _t25_inv)), _t30 * _t15, java.lang.Math.fma(_t30, _t18, _t11 * _t2 / _t25), Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2 = unitScale(other.m10, other.m11, other.m10);
        double _t3 = unitScale(other.m00, other.m01, other.m00);
        double _t10 = other.m11 * _t2;
        double _t11 = other.m00 * _t3;
        double _t12 = other.m01 * _t3;
        double _t13 = other.m10 * _t2;
        double _t15 = _t0 / _t0;
        double _t18 = -(this.m01 * _t0 / _t0);
        double _t22 = -(_t12 * _t13);
        double _t25 = ((_t11) * (_t10) + (_t22)) - ((_t12) * (_t13) + (_t22));
        double _t25_inv = 1.0 / _t25;
        double _t28 = _t10 * _t3 / _t25;
        double _t30 = -(_t13 * _t3 * _t25_inv);
        return new Double2x2(_t28 * _t15, ((_t18) * (_t28) - (_t12 * _t2 * _t25_inv)), _t30 * _t15, ((_t30) * (_t18) + (_t11 * _t2 / _t25)), Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1 = unitScale(other.m10, other.m11, other.m10);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t5 = this.m00 * _t0;
        double _t11 = other.m11 * _t1;
        double _t12 = other.m00 * _t2;
        double _t13 = other.m01 * _t2;
        double _t14 = other.m10 * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(this.m01 * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = java.lang.Math.fma(_t12, _t11, _t23) - java.lang.Math.fma(_t13, _t14, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        return new Double2x2(_t29 * _t16, java.lang.Math.fma(_t21, _t29, -(_t13 * _t1 * _t26_inv)), _t31 * _t16, java.lang.Math.fma(_t31, _t21, _t12 * _t1 / _t26), Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1 = unitScale(other.m10, other.m11, other.m10);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t5 = this.m00 * _t0;
        double _t11 = other.m11 * _t1;
        double _t12 = other.m00 * _t2;
        double _t13 = other.m01 * _t2;
        double _t14 = other.m10 * _t1;
        double _t16 = _t0 / _t5;
        double _t21 = -(this.m01 * _t0 / _t5);
        double _t23 = -(_t13 * _t14);
        double _t26 = ((_t12) * (_t11) + (_t23)) - ((_t13) * (_t14) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t11 * _t2 / _t26;
        double _t31 = -(_t14 * _t2 * _t26_inv);
        return new Double2x2(_t29 * _t16, ((_t21) * (_t29) - (_t13 * _t1 * _t26_inv)), _t31 * _t16, ((_t31) * (_t21) + (_t12 * _t1 / _t26)), Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_fma(Double2x2 other) {
        double _t0 = unitScale(other.m10, other.m11, other.m10);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t2 = unitScale(this.m00, this.m01, this.m00);
        double _t9 = other.m11 * _t0;
        double _t10 = other.m00 * _t1;
        double _t11 = other.m01 * _t1;
        double _t12 = other.m10 * _t0;
        double _t13 = this.m00 * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(this.m01 * _t2 / _t13);
        double _t26 = java.lang.Math.fma(_t10, _t9, _t21) - java.lang.Math.fma(_t11, _t12, _t21);
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        return new Double2x2(_t29 * _t18, java.lang.Math.fma(_t23, _t29, -(_t11 * _t0 * _t26_inv)), _t31 * _t18, java.lang.Math.fma(_t31, _t23, _t10 * _t0 / _t26), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_mulAdd(Double2x2 other) {
        double _t0 = unitScale(other.m10, other.m11, other.m10);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t2 = unitScale(this.m00, this.m01, this.m00);
        double _t9 = other.m11 * _t0;
        double _t10 = other.m00 * _t1;
        double _t11 = other.m01 * _t1;
        double _t12 = other.m10 * _t0;
        double _t13 = this.m00 * _t2;
        double _t18 = _t2 / _t13;
        double _t21 = -(_t11 * _t12);
        double _t23 = -(this.m01 * _t2 / _t13);
        double _t26 = ((_t10) * (_t9) + (_t21)) - ((_t11) * (_t12) + (_t21));
        double _t26_inv = 1.0 / _t26;
        double _t29 = _t9 * _t1 / _t26;
        double _t31 = -(_t12 * _t1 * _t26_inv);
        return new Double2x2(_t29 * _t18, ((_t23) * (_t29) - (_t11 * _t0 * _t26_inv)), _t31 * _t18, ((_t31) * (_t23) + (_t10 * _t0 / _t26)), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_translation(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        return new Double2x2(_t0 / _t0, -(other.m01 * _t0 / _t0), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_affine(Double2x2 other) {
        double _t0 = unitScale(other.m00, other.m01, other.m00);
        double _t2 = other.m00 * _t0;
        return new Double2x2(_t0 / _t2, -(other.m01 * _t0 / _t2), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_identity(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        return new Double2x2(_t0 / _t0, -(this.m01 * _t0 / _t0), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t1 = unitScale(1.0, this.m01, 1.0);
        double _t5 = _t0 / _t0;
        return new Double2x2(_t5 * (_t1 / _t1), java.lang.Math.fma(-(this.m01 * _t1 / _t1), _t5, -(other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t1 = unitScale(1.0, this.m01, 1.0);
        double _t5 = _t0 / _t0;
        return new Double2x2(_t5 * (_t1 / _t1), ((-(this.m01 * _t1 / _t1)) * (_t5) - (other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t4 = other.m00 * _t2;
        double _t6 = _t2 / _t4;
        return new Double2x2(_t0 / _t0 * _t6, java.lang.Math.fma(-(this.m01 * _t0 / _t0), _t6, -(other.m01 * _t2 / _t4)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t4 = other.m00 * _t2;
        double _t6 = _t2 / _t4;
        return new Double2x2(_t0 / _t0 * _t6, ((-(this.m01 * _t0 / _t0)) * (_t6) - (other.m01 * _t2 / _t4)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_identity(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2 = this.m00 * _t0;
        return new Double2x2(_t0 / _t2, -(this.m01 * _t0 / _t2), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t1 = unitScale(1.0, this.m01, 1.0);
        double _t4 = this.m00 * _t1;
        double _t6 = _t0 / _t0;
        return new Double2x2(_t6 * (_t1 / _t4), java.lang.Math.fma(-(this.m01 * _t1 / _t4), _t6, -(other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t1 = unitScale(1.0, this.m01, 1.0);
        double _t4 = this.m00 * _t1;
        double _t6 = _t0 / _t0;
        return new Double2x2(_t6 * (_t1 / _t4), ((-(this.m01 * _t1 / _t4)) * (_t6) - (other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t3 = this.m00 * _t0;
        double _t5 = other.m00 * _t1;
        double _t7 = _t1 / _t5;
        return new Double2x2(_t0 / _t3 * _t7, java.lang.Math.fma(-(this.m01 * _t0 / _t3), _t7, -(other.m01 * _t1 / _t5)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1 = unitScale(other.m00, other.m01, other.m00);
        double _t3 = this.m00 * _t0;
        double _t5 = other.m00 * _t1;
        double _t7 = _t1 / _t5;
        return new Double2x2(_t0 / _t3 * _t7, ((-(this.m01 * _t0 / _t3)) * (_t7) - (other.m01 * _t1 / _t5)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_identity(Double2x2 other) {
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2 = this.m00 * _t0;
        return new Double2x2(_t0 / _t2, -(this.m01 * _t0 / _t2), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_translation_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t2 = unitScale(this.m00, this.m01, this.m00);
        double _t4 = this.m00 * _t2;
        double _t6 = _t0 / _t0;
        return new Double2x2(_t6 * (_t2 / _t4), java.lang.Math.fma(-(this.m01 * _t2 / _t4), _t6, -(other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_translation_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t2 = unitScale(this.m00, this.m01, this.m00);
        double _t4 = this.m00 * _t2;
        double _t6 = _t0 / _t0;
        return new Double2x2(_t6 * (_t2 / _t4), ((-(this.m01 * _t2 / _t4)) * (_t6) - (other.m01 * _t0 / _t0)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_affine_fma(Double2x2 other) {
        double _t0 = unitScale(other.m00, other.m01, other.m00);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t4 = other.m00 * _t0;
        double _t5 = this.m00 * _t1;
        double _t7 = _t0 / _t4;
        return new Double2x2(_t7 * (_t1 / _t5), java.lang.Math.fma(-(this.m01 * _t1 / _t5), _t7, -(other.m01 * _t0 / _t4)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_affine_mulAdd(Double2x2 other) {
        double _t0 = unitScale(other.m00, other.m01, other.m00);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t4 = other.m00 * _t0;
        double _t5 = this.m00 * _t1;
        double _t7 = _t0 / _t4;
        return new Double2x2(_t7 * (_t1 / _t5), ((-(this.m01 * _t1 / _t5)) * (_t7) - (other.m01 * _t0 / _t4)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity_fma() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11);
        double _t14_inv = 1.0 / _t14;
        return new Double2x2(_t6 * _t1 / _t14, -(_t8 * _t0 * _t14_inv), -(_t9 * _t1 * _t14_inv), _t7 * _t0 / _t14, 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity_mulAdd() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14 = ((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11));
        double _t14_inv = 1.0 / _t14;
        return new Double2x2(_t6 * _t1 / _t14, -(_t8 * _t0 * _t14_inv), -(_t9 * _t1 * _t14_inv), _t7 * _t0 / _t14, 0);
    }

    /**
     * Private per-column body of {@code invertProduct_degenerate_general_translation}, stored into
     * {@code _m}. Shared by 2 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_degenerate_general_translation_s2b9ad38b_c0_fma(double _t33, double _t18, double _t34, double _t31, double _t15, double[] _m) {
        _m[0] = java.lang.Math.fma(_t33, _t18, _t34) + java.lang.Math.fma(_t31, _t15, -_t34);
        _m[2] = _t33;
    }

    /**
     * Private per-column body of {@code invertProduct_degenerate_general_translation}, stored into
     * {@code _m}. Shared by 2 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_degenerate_general_translation_s2b9ad38b_c0_mulAdd(double _t33, double _t18, double _t34, double _t31, double _t15, double[] _m) {
        _m[0] = ((_t33) * (_t18) + (_t34)) + ((_t31) * (_t15) - (_t34));
        _m[2] = _t33;
    }

    /**
     * Private per-column body of {@code invertProduct_degenerate_general_translation}, stored into
     * {@code _m}. Shared by 2 identical private paths of {@code invertProduct}; reached only
     * through it.
     */
    private void invertProduct_degenerate_general_translation_s2b9ad38b_c1_fma(double _t13, double _t2, double _t26_inv, double _t15, double _t35, double _t18, double _t32, double[] _m) {
        _m[1] = java.lang.Math.fma(-(_t13 * _t2 * _t26_inv), _t15, _t35) + java.lang.Math.fma(_t18, _t32, -_t35);
        _m[3] = _t32;
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation_fma(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t10 = this.m10 * _t2;
        double _t11 = this.m00 * _t3;
        double _t12 = this.m11 * _t2;
        double _t13 = this.m01 * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(other.m01 * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = java.lang.Math.fma(_t11, _t12, _t23) - java.lang.Math.fma(_t13, _t10, _t23);
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double[] _m = new double[4];
        invertProduct_degenerate_general_translation_s2b9ad38b_c0_fma(-(_t10 * _t3 * _t26_inv), _t18, _t31 * _t15, _t31, _t15, _m);
        invertProduct_degenerate_general_translation_s2b9ad38b_c1_fma(_t13, _t2, _t26_inv, _t15, _t18 * _t32, _t18, _t32, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation_mulAdd(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01, 1.0);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t10 = this.m10 * _t2;
        double _t11 = this.m00 * _t3;
        double _t12 = this.m11 * _t2;
        double _t13 = this.m01 * _t3;
        double _t15 = _t0 / _t0;
        double _t18 = -(other.m01 * _t0 / _t0);
        double _t23 = -(_t13 * _t10);
        double _t26 = ((_t11) * (_t12) + (_t23)) - ((_t13) * (_t10) + (_t23));
        double _t26_inv = 1.0 / _t26;
        double _t31 = _t12 * _t3 / _t26;
        double _t32 = _t11 * _t2 / _t26;
        double[] _m = new double[4];
        invertProduct_degenerate_general_translation_s2b9ad38b_c0_mulAdd(-(_t10 * _t3 * _t26_inv), _t18, _t31 * _t15, _t31, _t15, _m);
        {
            double _t35 = _t18 * _t32;
            _m[1] = ((_t35) - (_t13 * _t2 * _t26_inv) * (_t15)) + ((_t18) * (_t32) - (_t35));
            _m[3] = _t32;
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine_fma(Double2x2 other) {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t9 = this.m10 * _t0;
        double _t10 = this.m00 * _t1;
        double _t11 = this.m11 * _t0;
        double _t12 = this.m01 * _t1;
        double _t14 = other.m00 * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(other.m01 * _t2 / _t14);
        double _t27 = java.lang.Math.fma(_t10, _t11, _t22) - java.lang.Math.fma(_t12, _t9, _t22);
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double[] _m = new double[4];
        invertProduct_degenerate_general_translation_s2b9ad38b_c0_fma(-(_t9 * _t1 * _t27_inv), _t24, _t32 * _t19, _t32, _t19, _m);
        invertProduct_degenerate_general_translation_s2b9ad38b_c1_fma(_t12, _t0, _t27_inv, _t19, _t24 * _t33, _t24, _t33, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine_mulAdd(Double2x2 other) {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t2 = unitScale(other.m00, other.m01, other.m00);
        double _t9 = this.m10 * _t0;
        double _t10 = this.m00 * _t1;
        double _t11 = this.m11 * _t0;
        double _t12 = this.m01 * _t1;
        double _t14 = other.m00 * _t2;
        double _t19 = _t2 / _t14;
        double _t22 = -(_t12 * _t9);
        double _t24 = -(other.m01 * _t2 / _t14);
        double _t27 = ((_t10) * (_t11) + (_t22)) - ((_t12) * (_t9) + (_t22));
        double _t27_inv = 1.0 / _t27;
        double _t32 = _t11 * _t1 / _t27;
        double _t33 = _t10 * _t0 / _t27;
        double[] _m = new double[4];
        invertProduct_degenerate_general_translation_s2b9ad38b_c0_mulAdd(-(_t9 * _t1 * _t27_inv), _t24, _t32 * _t19, _t32, _t19, _m);
        {
            double _t35 = _t24 * _t33;
            _m[1] = ((_t35) - (_t12 * _t0 * _t27_inv) * (_t19)) + ((_t24) * (_t33) - (_t35));
            _m[3] = _t33;
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(Double2x2 other) {
        int p = this.properties;
        int q = other.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_fma();
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_fma(other);
            return invertProduct_degenerate_general_affine_fma(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, other.properties);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other);
            return invertProduct_degenerate_identity_affine(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_fma(other);
            return invertProduct_degenerate_translation_affine_fma(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_fma(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_fma(other);
            return invertProduct_degenerate_orthogonal_affine_fma(other);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_fma(other);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_affine_identity(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_fma(other);
        return invertProduct_degenerate_affine_affine_fma(other);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(Double2x2 other) {
        int p = this.properties;
        int q = other.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_general_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity_mulAdd();
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation_mulAdd(other);
            return invertProduct_degenerate_general_affine_mulAdd(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_identity_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, other.properties);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other);
            return invertProduct_degenerate_identity_affine(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_translation_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation_mulAdd(other);
            return invertProduct_degenerate_translation_affine_mulAdd(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_mulAdd(other);
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation_mulAdd(other);
            return invertProduct_degenerate_orthogonal_affine_mulAdd(other);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return invertProduct_degenerate_affine_mulAdd(other);
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_affine_identity(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation_mulAdd(other);
        return invertProduct_degenerate_affine_affine_mulAdd(other);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private Double2x2 invertProduct_degenerate_s1479e594_tail_fma(double _t13, double _t1, double _t36, double _t12, double _t36_inv, double _t16, double _t3, double _t37_inv, double _t19, double _t2, double _t15, double _t0, double _t48, double _t49, double _t50, int _props) {
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_fma(_t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53, _m);
        invertProduct_general_s2b9ad38b_tail_s28811b3_c1_fma(_t54, _t50, _t56 * _t48, _t56, _t48, _t57, _t53 * _t50, _t53, _m);
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], _props);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private Double2x2 invertProduct_degenerate_s1479e594_tail_mulAdd(double _t13, double _t1, double _t36, double _t12, double _t36_inv, double _t16, double _t3, double _t37_inv, double _t19, double _t2, double _t15, double _t0, double _t48, double _t49, double _t50, int _props) {
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double[] _m = new double[4];
        invertProduct_general_s2b9ad38b_tail_s28811b3_c0_mulAdd(_t54, _t55, _t48 * _t49, _t48, _t49, _t57, _t55 * _t53, _t53, _m);
        {
            double _t34 = _t56 * _t48;
            double _t33 = _t53 * _t50;
            _m[1] = ((_t54) * (_t50) + (_t34)) + ((_t56) * (_t48) - (_t34));
            _m[3] = ((_t57) * (_t56) + (_t33)) + ((_t53) * (_t50) - (_t33));
        }
        return new Double2x2(_m[0], _m[1], _m[2], _m[3], _props);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_fma(double m00, double m01, double m10, double m11) {
        double _t0 = unitScale(m00, m01, m00);
        double _t1 = unitScale(m10, m11, m10);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = this.m10 * _t2;
        double _t17 = this.m00 * _t3;
        double _t18 = this.m11 * _t2;
        double _t19 = this.m01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        return invertProduct_degenerate_s1479e594_tail_fma(_t13, _t1, _t36, _t12, 1.0 / _t36, _t16, _t3, 1.0 / _t37, _t19, _t2, _t15, _t0, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, 0);
    }

    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_mulAdd(double m00, double m01, double m10, double m11) {
        double _t0 = unitScale(m00, m01, m00);
        double _t1 = unitScale(m10, m11, m10);
        double _t2 = unitScale(this.m10, this.m11, this.m10);
        double _t3 = unitScale(this.m00, this.m01, this.m00);
        double _t12 = m01 * _t0;
        double _t13 = m00 * _t0;
        double _t14 = m11 * _t1;
        double _t15 = m10 * _t1;
        double _t16 = this.m10 * _t2;
        double _t17 = this.m00 * _t3;
        double _t18 = this.m11 * _t2;
        double _t19 = this.m01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        return invertProduct_degenerate_s1479e594_tail_mulAdd(_t13, _t1, _t36, _t12, 1.0 / _t36, _t16, _t3, 1.0 / _t37, _t19, _t2, _t15, _t0, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, 0);
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_fma() {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_fma();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, 0.0, -(this.m01 * _t0_inv), 1.0, 0);
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine_mulAdd() {
        double _t0 = this.m00;
        if (!(java.lang.Math.abs(_t0) > 2.2250738585072014E-308 && java.lang.Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate_mulAdd();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, 0.0, -(this.m01 * _t0_inv), 1.0, 0);
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general_fma() {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = java.lang.Math.fma(this.m00, this.m11, _t1) - java.lang.Math.fma(this.m01, this.m10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_fma();
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 * _t5_inv, -(this.m10 * _t5_inv), -(this.m01 * _t5_inv), this.m00 * _t5_inv, 0);
    }

    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general_mulAdd() {
        double _t1 = -(this.m01 * this.m10);
        double _t5 = ((this.m00) * (this.m11) + (_t1)) - ((this.m01) * (this.m10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return normal_degenerate_mulAdd();
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(this.m11 * _t5_inv, -(this.m10 * _t5_inv), -(this.m01 * _t5_inv), this.m00 * _t5_inv, 0);
    }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse, returning the
     * result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Double2x2 normal() {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_fma();
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, -this.m01, 1.0, 0);
            return normal_affine_fma();
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_general_mulAdd();
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, -this.m01, 1.0, 0);
            return normal_affine_mulAdd();
        }
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_general_fma() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        return new Double2x2(_t6 * _sp0, -(_t9 * _sp0), -(_t8 * _sp1), _t7 * _sp1, 0);
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_general_mulAdd() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        return new Double2x2(_t6 * _sp0, -(_t9 * _sp0), -(_t8 * _sp1), _t7 * _sp1, 0);
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_fma() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_fma();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t1_inv = 1.0 / _t0;
            return new Double2x2(_t0 * _t1_inv, 0.0, -(this.m01 * _t0) * _t1_inv, 1.0, 0);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t2_inv = 1.0 / (this.m00 * _t0);
            return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0) * _t2_inv, 1.0, 0);
        }
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0) * _t2_inv, 1.0, 0);
    }

    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_mulAdd() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return normal_degenerate_general_mulAdd();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t1_inv = 1.0 / _t0;
            return new Double2x2(_t0 * _t1_inv, 0.0, -(this.m01 * _t0) * _t1_inv, 1.0, 0);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            double _t0 = unitScale(1.0, this.m01, 1.0);
            double _t2_inv = 1.0 / (this.m00 * _t0);
            return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0) * _t2_inv, 1.0, 0);
        }
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0) * _t2_inv, 1.0, 0);
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
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x2 transpose() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m00, this.m10, this.m01, this.m11, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, this.m01, 1.0, 0);
        return new Double2x2(this.m00, 0.0, this.m01, 1.0, 0);
    }

    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Double2x2 add(Double2x2 other) {
        return new Double2x2(other.m00 + this.m00, other.m01 + this.m01, other.m10 + this.m10, other.m11 + this.m11, 0);
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) to this matrix, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double2x2 add(double m00, double m01, double m10, double m11) {
        return new Double2x2(m00 + this.m00, m01 + this.m01, m10 + this.m10, m11 + this.m11, 0);
    }

    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Double2x2 mul(double scalar) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(scalar * this.m00, scalar * this.m01, scalar * this.m10, scalar * this.m11, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(scalar, 0.0, 0.0, scalar, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(scalar, scalar * this.m01, 0.0, scalar, 0);
        return new Double2x2(scalar * this.m00, scalar * this.m01, 0.0, scalar, 0);
    }

    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x2 negate() {
        return new Double2x2(-this.m00, -this.m01, -this.m10, -this.m11, 0);
    }

    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Double2x2 sub(Double2x2 other) {
        return new Double2x2(this.m00 - other.m00, this.m01 - other.m01, this.m10 - other.m10, this.m11 - other.m11, 0);
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}) from this matrix, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double2x2 sub(double m00, double m01, double m10, double m11) {
        return new Double2x2(this.m00 - m00, this.m01 - m01, this.m10 - m10, this.m11 - m11, 0);
    }

    /**
     * Return a matrix with the values of {@code v}: that matrix itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return the resulting matrix
     */
    public Double2x2 set(Double2x2 v) {
        return v;
    }

    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double2x2 set(double m00, double m01, double m10, double m11) {
        return new Double2x2(m00, m01, m10, m11);
    }

    /**
     * Create a new matrix from the given 2x3 matrix, copying the overlapping cells and dropping the
     * rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Double2x2 set(Double2x3 m) {
        return new Double2x2(m.m00(), m.m01(), m.m10(), m.m11());
    }

    /**
     * Create a new matrix from the given 3x3 matrix, copying the overlapping cells and dropping the
     * rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Double2x2 set(Double3x3 m) {
        return new Double2x2(m.m00(), m.m01(), m.m10(), m.m11());
    }

    /**
     * Convert this matrix to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float2x2} holding the result
     */
    public Float2x2 toFloat() {
        return new Float2x2((float) (this.m00), (float) (this.m01), (float) (this.m10), (float) (this.m11), this.properties);
    }

    /**
     * Extend this matrix to a 2x3 matrix with a zero translation column, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x3 to2x3() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x3(this.m00, this.m01, 0.0, this.m10, this.m11, 0.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x3(1.0, this.m01, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
        return new Double2x3(this.m00, this.m01, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
    }

    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double3x3 to3x3() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double3x3(this.m00, this.m01, 0.0, this.m10, this.m11, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double3x3(1.0, this.m01, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        return new Double3x3(this.m00, this.m01, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Result value of {@code decomposeLDU}.
     *
     * @param lower the unit lower-triangular factor
     * @param diagonal the diagonal factor
     * @param upper the unit upper-triangular factor
     */
    public record DecomposeLDUResult(Double2x2 lower, Double2x2 diagonal, Double2x2 upper) {
        /**
         * Canonical constructor.
         * <p>
         * Valid input: any value, NaN and the infinities included.
         *
         * @param lower the unit lower-triangular factor
         * @param diagonal the diagonal factor
         * @param upper the unit upper-triangular factor
         */
        public DecomposeLDUResult(Double2x2 lower, Double2x2 diagonal, Double2x2 upper) {
            this.lower = lower;
            this.diagonal = diagonal;
            this.upper = upper;
        }

        /** {@return the {@code lower} component} <p>Valid input: any value, NaN and the infinities included. */
        public Double2x2 lower() { return lower; }

        /** {@return the {@code diagonal} component} <p>Valid input: any value, NaN and the infinities included. */
        public Double2x2 diagonal() { return diagonal; }

        /** {@return the {@code upper} component} <p>Valid input: any value, NaN and the infinities included. */
        public Double2x2 upper() { return upper; }
    }

    /**
     * Private body of {@code decomposeLDU}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeLDU} dispatcher.
     */
    private DecomposeLDUResult decomposeLDU_general_fma() {
        double _rcp0 = 1.0 / this.m00;
        double _sp0 = this.m10 * _rcp0;
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, _sp0, 1.0), new Double2x2(this.m00, 0.0, 0.0, java.lang.Math.fma(-this.m01, _sp0, this.m11)), new Double2x2(1.0, this.m01 * _rcp0, 0.0, 1.0));
    }

    /**
     * Private body of {@code decomposeLDU}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeLDU} dispatcher.
     */
    private DecomposeLDUResult decomposeLDU_general_mulAdd() {
        double _rcp0 = 1.0 / this.m00;
        double _sp0 = this.m10 * _rcp0;
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, _sp0, 1.0), new Double2x2(this.m00, 0.0, 0.0, ((this.m11) - (this.m01) * (_sp0))), new Double2x2(1.0, this.m01 * _rcp0, 0.0, 1.0));
    }

    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, so a matrix
     * whose {@code m00} is zero cannot be decomposed by it (the factors are then not finite).
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @return a new result value holding the lower-triangular, diagonal and upper-triangular
     *        factors
     */
    public DecomposeLDUResult decomposeLDU() {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return (org.joml2.internal.FmaSwitch.ON ? decomposeLDU_general_fma() : decomposeLDU_general_mulAdd());
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0));
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, this.m01, 0.0, 1.0));
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(this.m00, 0.0, 0.0, 1.0), new Double2x2(1.0, this.m01 / this.m00, 0.0, 1.0));
    }

    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Double2x2 makeIdentity() {
        return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
    }

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 lerp(Double2x2 other, double t) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(t, other.m00 - this.m00, this.m00), java.lang.Math.fma(t, other.m01 - this.m01, this.m01), java.lang.Math.fma(t, other.m10 - this.m10, this.m10), java.lang.Math.fma(t, other.m11 - this.m11, this.m11), ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & other.properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & other.properties) >> 1));
        } else {
            return new Double2x2(((t) * (other.m00 - this.m00) + (this.m00)), ((t) * (other.m01 - this.m01) + (this.m01)), ((t) * (other.m10 - this.m10) + (this.m10)), ((t) * (other.m11 - this.m11) + (this.m11)), ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & other.properties) | ((Joml.UNIQUE_TRANSLATION & this.properties & other.properties) >> 1));
        }
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}) using the interpolation factor {@code t}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 lerp(double m00, double m01, double m10, double m11, double t) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(t, m00 - this.m00, this.m00), java.lang.Math.fma(t, m01 - this.m01, this.m01), java.lang.Math.fma(t, m10 - this.m10, this.m10), java.lang.Math.fma(t, m11 - this.m11, this.m11), 0);
        } else {
            return new Double2x2(((t) * (m00 - this.m00) + (this.m00)), ((t) * (m01 - this.m01) + (this.m01)), ((t) * (m10 - this.m10) + (this.m10)), ((t) * (m11 - this.m11) + (this.m11)), 0);
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_fma(Double2x2 right) {
        return new Double2x2(java.lang.Math.fma(right.m00, this.m00, right.m10 * this.m01), java.lang.Math.fma(right.m01, this.m00, right.m11 * this.m01), java.lang.Math.fma(right.m00, this.m10, right.m10 * this.m11), java.lang.Math.fma(right.m01, this.m10, right.m11 * this.m11), 0);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_mulAdd(Double2x2 right) {
        return new Double2x2(((right.m00) * (this.m00) + (right.m10 * this.m01)), ((right.m01) * (this.m00) + (right.m11 * this.m01)), ((right.m00) * (this.m10) + (right.m10 * this.m11)), ((right.m01) * (this.m10) + (right.m11 * this.m11)), 0);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_fma(Double2x2 right, int _props) {
        return new Double2x2(java.lang.Math.fma(right.m10, this.m01, right.m00), java.lang.Math.fma(right.m11, this.m01, right.m01), right.m10, right.m11, _props);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation_mulAdd(Double2x2 right, int _props) {
        return new Double2x2(((right.m10) * (this.m01) + (right.m00)), ((right.m11) * (this.m01) + (right.m01)), right.m10, right.m11, _props);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_fma(Double2x2 right) {
        return new Double2x2(java.lang.Math.fma(right.m00, this.m00, right.m10 * this.m01), java.lang.Math.fma(right.m01, this.m00, right.m11 * this.m01), right.m10, right.m11, Joml.BIT_AFFINE & right.properties);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_mulAdd(Double2x2 right) {
        return new Double2x2(((right.m00) * (this.m00) + (right.m10 * this.m01)), ((right.m01) * (this.m00) + (right.m11 * this.m01)), right.m10, right.m11, Joml.BIT_AFFINE & right.properties);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_translation_fma(Double2x2 right) {
        return new Double2x2(this.m00, java.lang.Math.fma(right.m01, this.m00, this.m01), 0.0, 1.0, Joml.BIT_AFFINE & right.properties);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine_fma(Double2x2 right) {
        return new Double2x2(right.m00 * this.m00, java.lang.Math.fma(right.m01, this.m00, this.m01), 0.0, 1.0, Joml.BIT_AFFINE & right.properties);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine_mulAdd(Double2x2 right) {
        return new Double2x2(right.m00 * this.m00, ((right.m01) * (this.m00) + (this.m01)), 0.0, 1.0, Joml.BIT_AFFINE & right.properties);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation_fma(Double2x2 right) {
        return new Double2x2(this.m00, java.lang.Math.fma(right.m01, this.m00, this.m01), this.m10, java.lang.Math.fma(right.m01, this.m10, this.m11), 0);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation_mulAdd(Double2x2 right) {
        return new Double2x2(this.m00, ((right.m01) * (this.m00) + (this.m01)), this.m10, ((right.m01) * (this.m10) + (this.m11)), 0);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine_fma(Double2x2 right) {
        return new Double2x2(right.m00 * this.m00, java.lang.Math.fma(right.m01, this.m00, this.m01), right.m00 * this.m10, java.lang.Math.fma(right.m01, this.m10, this.m11), 0);
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine_mulAdd(Double2x2 right) {
        return new Double2x2(right.m00 * this.m00, ((right.m01) * (this.m00) + (this.m01)), right.m00 * this.m10, ((right.m01) * (this.m10) + (this.m11)), 0);
    }

    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return the resulting matrix
     */
    public Double2x2 mul(Double2x2 right) {
        if (org.joml2.internal.FmaSwitch.ON) return mul_fma(right);
        return mul_mulAdd(right);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 mul_fma(Double2x2 right) {
        int p = this.properties;
        int q = right.properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(right);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_fma(right);
            return mul_general_affine_fma(right);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return right;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties);
            return new Double2x2(right.m00, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_fma(right, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties);
            return new Double2x2(right.m00, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_fma(right);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation_fma(right);
        return mul_affine_affine_fma(right);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 mul_mulAdd(Double2x2 right) {
        int p = this.properties;
        int q = right.properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_mulAdd(right);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation_mulAdd(right);
            return mul_general_affine_mulAdd(right);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return right;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties);
            return new Double2x2(right.m00, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_translation_mulAdd(right, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties);
            return new Double2x2(right.m00, right.m01 + this.m01, 0.0, 1.0, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_affine_mulAdd(right);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(this.m00, ((right.m01) * (this.m00) + (this.m01)), 0.0, 1.0, Joml.BIT_AFFINE & right.properties);
        return mul_affine_affine_mulAdd(right);
    }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m10}, {@code m11}), returning the
     * result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 mul(double m00, double m01, double m10, double m11) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(m00, this.m00, m10 * this.m01), java.lang.Math.fma(m01, this.m00, m11 * this.m01), java.lang.Math.fma(m00, this.m10, m10 * this.m11), java.lang.Math.fma(m01, this.m10, m11 * this.m11), 0);
        } else {
            return new Double2x2(((m00) * (this.m00) + (m10 * this.m01)), ((m01) * (this.m00) + (m11 * this.m01)), ((m00) * (this.m10) + (m10 * this.m11)), ((m01) * (this.m10) + (m11 * this.m11)), 0);
        }
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_fma(Double2x2 other) {
        return new Double2x2(java.lang.Math.fma(other.m00, this.m00, other.m01 * this.m10), java.lang.Math.fma(other.m00, this.m01, other.m01 * this.m11), java.lang.Math.fma(other.m10, this.m00, other.m11 * this.m10), java.lang.Math.fma(other.m10, this.m01, other.m11 * this.m11), 0);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_mulAdd(Double2x2 other) {
        return new Double2x2(((other.m00) * (this.m00) + (other.m01 * this.m10)), ((other.m00) * (this.m01) + (other.m01 * this.m11)), ((other.m10) * (this.m00) + (other.m11 * this.m10)), ((other.m10) * (this.m01) + (other.m11 * this.m11)), 0);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_fma(Double2x2 other, int _props) {
        return new Double2x2(other.m00, java.lang.Math.fma(other.m00, this.m01, other.m01), other.m10, java.lang.Math.fma(other.m10, this.m01, other.m11), _props);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_mulAdd(Double2x2 other, int _props) {
        return new Double2x2(other.m00, ((other.m00) * (this.m01) + (other.m01)), other.m10, ((other.m10) * (this.m01) + (other.m11)), _props);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_fma(Double2x2 other) {
        return new Double2x2(other.m00 * this.m00, java.lang.Math.fma(other.m00, this.m01, other.m01), other.m10 * this.m00, java.lang.Math.fma(other.m10, this.m01, other.m11), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_mulAdd(Double2x2 other) {
        return new Double2x2(other.m00 * this.m00, ((other.m00) * (this.m01) + (other.m01)), other.m10 * this.m00, ((other.m10) * (this.m01) + (other.m11)), Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_affine_fma(Double2x2 other, int _props) {
        return new Double2x2(other.m00, java.lang.Math.fma(other.m00, this.m01, other.m01), 0.0, 1.0, _props);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine_fma(Double2x2 other) {
        return new Double2x2(other.m00 * this.m00, java.lang.Math.fma(other.m00, this.m01, other.m01), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine_mulAdd(Double2x2 other) {
        return new Double2x2(other.m00 * this.m00, ((other.m00) * (this.m01) + (other.m01)), 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation_fma(Double2x2 other) {
        return new Double2x2(java.lang.Math.fma(other.m01, this.m10, this.m00), java.lang.Math.fma(other.m01, this.m11, this.m01), this.m10, this.m11, 0);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation_mulAdd(Double2x2 other) {
        return new Double2x2(((other.m01) * (this.m10) + (this.m00)), ((other.m01) * (this.m11) + (this.m01)), this.m10, this.m11, 0);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine_fma(Double2x2 other) {
        return new Double2x2(java.lang.Math.fma(other.m00, this.m00, other.m01 * this.m10), java.lang.Math.fma(other.m00, this.m01, other.m01 * this.m11), this.m10, this.m11, 0);
    }

    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine_mulAdd(Double2x2 other) {
        return new Double2x2(((other.m00) * (this.m00) + (other.m01 * this.m10)), ((other.m00) * (this.m01) + (other.m01 * this.m11)), this.m10, this.m11, 0);
    }

    /**
     * Pre-multiply the transformation {@code other} onto this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return the resulting matrix
     */
    public Double2x2 preMul(Double2x2 other) {
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other);
        int p = this.properties;
        int q = other.properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_fma(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_fma(other);
            return preMul_general_affine_fma(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return other;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
            return preMul_translation_affine_fma(other, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_fma(other, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
            return preMul_translation_affine_fma(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_fma(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(this.m00, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
        return preMul_affine_affine_fma(other);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double2x2 preMul_mulAdd(Double2x2 other) {
        int p = this.properties;
        int q = other.properties;
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_general_mulAdd(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation_mulAdd(other);
            return preMul_general_affine_mulAdd(other);
        }
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return other;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, Joml.BIT_TRANSLATION & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
            return new Double2x2(other.m00, ((other.m00) * (this.m01) + (other.m01)), 0.0, 1.0, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_translation_mulAdd(other, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties);
            return new Double2x2(other.m00, ((other.m00) * (this.m01) + (other.m01)), 0.0, 1.0, Joml.BIT_ORTHOGONAL & q);
        }
        if ((q & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preMul_affine_mulAdd(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(this.m00, other.m01 + this.m01, 0.0, 1.0, Joml.BIT_AFFINE & other.properties);
        return preMul_affine_affine_mulAdd(other);
    }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m10}, {@code m11}) onto
     * this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 preMul(double m00, double m01, double m10, double m11) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(m00, this.m00, m01 * this.m10), java.lang.Math.fma(m00, this.m01, m01 * this.m11), java.lang.Math.fma(m10, this.m00, m11 * this.m10), java.lang.Math.fma(m10, this.m01, m11 * this.m11), 0);
        } else {
            return new Double2x2(((m00) * (this.m00) + (m01 * this.m10)), ((m00) * (this.m01) + (m01 * this.m11)), ((m10) * (this.m00) + (m11 * this.m10)), ((m10) * (this.m01) + (m11 * this.m11)), 0);
        }
    }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return the resulting matrix
     */
    public Double2x2 addScaled(Double2x2 other, double weight) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(weight, other.m00, this.m00), java.lang.Math.fma(weight, other.m01, this.m01), java.lang.Math.fma(weight, other.m10, this.m10), java.lang.Math.fma(weight, other.m11, this.m11), 0);
        } else {
            return new Double2x2(((weight) * (other.m00) + (this.m00)), ((weight) * (other.m01) + (this.m01)), ((weight) * (other.m10) + (this.m10)), ((weight) * (other.m11) + (this.m11)), 0);
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}) scaled by {@code weight} to this
     * matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11}) by
     *        before adding
     * @return the resulting matrix
     */
    public Double2x2 addScaled(double m00, double m01, double m10, double m11, double weight) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double2x2(java.lang.Math.fma(weight, m00, this.m00), java.lang.Math.fma(weight, m01, this.m01), java.lang.Math.fma(weight, m10, this.m10), java.lang.Math.fma(weight, m11, this.m11), 0);
        } else {
            return new Double2x2(((weight) * (m00) + (this.m00)), ((weight) * (m01) + (this.m01)), ((weight) * (m10) + (this.m10)), ((weight) * (m11) + (this.m11)), 0);
        }
    }

    /**
     * Create the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column vector (left operand)
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public static Double2x2 makeOuterProduct(Double2 col, Double2 row) {
        double colX = col.x();
        double colY = col.y();
        double rowX = row.x();
        double rowY = row.y();
        return new Double2x2(colX * rowX, colX * rowY, colY * rowX, colY * rowY, 0);
    }

    /**
     * Create the outer product of ({@code colX}, {@code colY}) and ({@code rowX}, {@code rowY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param colX the {@code x} component of the vector {@code (colX, colY)}
     * @param colY the {@code y} component of the vector {@code (colX, colY)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY)}
     * @return the resulting matrix
     */
    public static Double2x2 makeOuterProduct(double colX, double colY, double rowX, double rowY) {
        return new Double2x2(colX * rowX, colX * rowY, colY * rowX, colY * rowY, 0);
    }

    /**
     * Create a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Double2x2 makeRotation(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(_t0, -_t1, _t1, _t0, 0);
    }

    /**
     * Create a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public static Double2x2 makeScaling(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        return new Double2x2(vX, 0.0, 0.0, vY, 0);
    }

    /**
     * Create a scaling transformation that scales by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public static Double2x2 makeScaling(double vX, double vY) {
        return new Double2x2(vX, 0.0, 0.0, vY, 0);
    }

    /**
     * Create a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public static Double2x2 makeScaling(double s) {
        return new Double2x2(s, 0.0, 0.0, s, 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_translation_fma(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(_t0, java.lang.Math.fma(this.m01, _t0, -_t1), _t1, java.lang.Math.fma(this.m01, _t1, _t0), 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_translation_mulAdd(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(_t0, ((this.m01) * (_t0) - (_t1)), _t1, ((this.m01) * (_t1) + (_t0)), 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_affine_fma(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(this.m00 * _t0, java.lang.Math.fma(this.m01, _t0, -_t1), this.m00 * _t1, java.lang.Math.fma(this.m01, _t1, _t0), 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_affine_mulAdd(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(this.m00 * _t0, ((this.m01) * (_t0) - (_t1)), this.m00 * _t1, ((this.m01) * (_t1) + (_t0)), 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_general_fma(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(java.lang.Math.fma(this.m00, _t0, -(this.m10 * _t1)), java.lang.Math.fma(this.m01, _t0, -(this.m11 * _t1)), java.lang.Math.fma(this.m00, _t1, this.m10 * _t0), java.lang.Math.fma(this.m01, _t1, this.m11 * _t0), 0);
    }

    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double2x2 preRotate_general_mulAdd(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(((this.m00) * (_t0) - (this.m10 * _t1)), ((this.m01) * (_t0) - (this.m11 * _t1)), ((this.m00) * (_t1) + (this.m10 * _t0)), ((this.m01) * (_t1) + (this.m11 * _t0)), 0);
    }

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Double2x2 preRotate(double angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_fma(angle);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
                return new Double2x2(_t0, -_t1, _t1, _t0, 0);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_translation_fma(angle);
            return preRotate_affine_fma(angle);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return preRotate_general_mulAdd(angle);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
                return new Double2x2(_t0, -_t1, _t1, _t0, 0);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_translation_mulAdd(angle);
            return preRotate_affine_mulAdd(angle);
        }
    }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public Double2x2 preScale(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vX, this.m10 * vY, this.m11 * vY, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vX, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vX, 0.0, vY, 0);
    }

    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix, returning the result as
     * a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Double2x2 preScale(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vX, this.m10 * vY, this.m11 * vY, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vX, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vX, 0.0, vY, 0);
    }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public Double2x2 preScale(double s) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(s * this.m00, s * this.m01, s * this.m10, s * this.m11, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(s, 0.0, 0.0, s, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(s, s * this.m01, 0.0, s, 0);
        return new Double2x2(s * this.m00, s * this.m01, 0.0, s, 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_translation_fma(double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        return new Double2x2(java.lang.Math.fma(this.m01, _t0, _t1), java.lang.Math.fma(this.m01, _t1, -_t0), _t0, _t1, 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_translation_mulAdd(double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        return new Double2x2(((this.m01) * (_t0) + (_t1)), ((this.m01) * (_t1) - (_t0)), _t0, _t1, 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_affine_fma(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(java.lang.Math.fma(this.m00, _t0, this.m01 * _t1), java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1)), _t1, _t0, 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_affine_mulAdd(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(((this.m00) * (_t0) + (this.m01 * _t1)), ((this.m01) * (_t0) - (this.m00 * _t1)), _t1, _t0, 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_general_fma(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(java.lang.Math.fma(this.m00, _t0, this.m01 * _t1), java.lang.Math.fma(this.m01, _t0, -(this.m00 * _t1)), java.lang.Math.fma(this.m10, _t0, this.m11 * _t1), java.lang.Math.fma(this.m11, _t0, -(this.m10 * _t1)), 0);
    }

    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double2x2 rotate_general_mulAdd(double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        return new Double2x2(((this.m00) * (_t0) + (this.m01 * _t1)), ((this.m01) * (_t0) - (this.m00 * _t1)), ((this.m10) * (_t0) + (this.m11 * _t1)), ((this.m11) * (_t0) - (this.m10 * _t1)), 0);
    }

    /**
     * Apply a rotation by {@code angle} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Double2x2 rotate(double angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_fma(angle);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
                return new Double2x2(_t0, -_t1, _t1, _t0, 0);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_translation_fma(angle);
            return rotate_affine_fma(angle);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return rotate_general_mulAdd(angle);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
                double _t0 = Math.cosWithSin(angle);
                double _t1 = Math.sinWithCos(angle);
                return new Double2x2(_t0, -_t1, _t1, _t0, 0);
            }
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_translation_mulAdd(angle);
            return rotate_affine_mulAdd(angle);
        }
    }

    /**
     * Apply a scaling by {@code v} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public Double2x2 scale(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vY, this.m10 * vX, this.m11 * vY, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vY, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vY, 0.0, vY, 0);
    }

    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Double2x2 scale(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vY, this.m10 * vX, this.m11 * vY, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vY, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vY, 0.0, vY, 0);
    }

    /**
     * Apply a scaling by {@code s} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public Double2x2 scale(double s) {
        int p = this.properties;
        if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2x2(s * this.m00, s * this.m01, s * this.m10, s * this.m11, 0);
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(s, 0.0, 0.0, s, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(s, s * this.m01, 0.0, s, 0);
        return new Double2x2(s * this.m00, s * this.m01, 0.0, s, 0);
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @return the resulting vector
     */
    public Double2 mul(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2(java.lang.Math.fma(this.m01, vY, vX), vY);
            return new Double2(java.lang.Math.fma(this.m00, vX, this.m01 * vY), vY);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)));
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2(((this.m01) * (vY) + (vX)), vY);
            return new Double2(((this.m00) * (vX) + (this.m01 * vY)), vY);
        }
    }

    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general_fma(double vX, double vY) {
        return new Double2(java.lang.Math.fma(this.m00, vX, this.m01 * vY), java.lang.Math.fma(this.m10, vX, this.m11 * vY));
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting vector
     */
    public Double2 mul(double vX, double vY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return mul_general_fma(vX, vY);
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2(java.lang.Math.fma(this.m01, vY, vX), vY);
            return new Double2(java.lang.Math.fma(this.m00, vX, this.m01 * vY), vY);
        } else {
            int p = this.properties;
            if ((p & Joml.BIT_AFFINE) != Joml.BIT_AFFINE) return new Double2(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)));
            if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
            if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2(((this.m01) * (vY) + (vX)), vY);
            return new Double2(((this.m00) * (vX) + (this.m01 * vY)), vY);
        }
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Double2x2 withM00(double v) {
        return new Double2x2(v, m01, m10, m11);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Double2x2 withM01(double v) {
        return new Double2x2(m00, v, m10, m11);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Double2x2 withM10(double v) {
        return new Double2x2(m00, m01, v, m11);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Double2x2 withM11(double v) {
        return new Double2x2(m00, m01, m10, v);
    }

    /**
     * {@return a copy with the cached property bits replaced by {@code properties}}
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself. {@code withProperties(determineProperties())} recomputes them from
     * the elements.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param properties the cached property bits, taken as given
     */
    public Double2x2 withProperties(int properties) {
        return new Double2x2(m00, m01, m10, m11, properties);
    }

    @Override public String toString() {
        return "Double2x2(\n    " + this.m00 + ", " + this.m01 + "\n    " + this.m10 + ", " + this.m11 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x2)) return false;
        Double2x2 o = (Double2x2) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m10)
            && Double.isFinite(m11);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m10)
            || Double.isNaN(m11);
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a
     * finite one only under an infinite {@code epsilon}; a NaN component never compares equal to
     * anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Double2x2 other, double epsilon) {
        return java.lang.Math.abs(m00 - other.m00) <= epsilon
            && java.lang.Math.abs(m01 - other.m01) <= epsilon
            && java.lang.Math.abs(m10 - other.m10) <= epsilon
            && java.lang.Math.abs(m11 - other.m11) <= epsilon;
    }

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

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM(double[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(offset, src);
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM(float[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
     * modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCMFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(offset, src);
    }

    /**
     * Store the elements into the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM(double[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in row-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in row-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(offset, src);
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM(float[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRMFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(offset, src);
    }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public double[] storeCM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c1 = src[_p1];
        double _c3 = src[_p1 + 1];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Double2x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in column-major order, with {@code stride}
     * elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in column-major order, with {@code stride}
     * elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(offset, src, stride);
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public float[] storeCM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        return dest;
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c1 = src[_p1];
        double _c3 = src[_p1 + 1];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Double2x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCMFloat(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset, with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset, with {@code stride} elements
     * between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(offset, src, stride);
    }

    /**
     * Store the elements into the given array in row-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public double[] storeRM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[_p1];
        double _c3 = src[_p1 + 1];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    public Double2x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in row-major order, with {@code stride}
     * elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in row-major order, with {@code stride}
     * elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(offset, src, stride);
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public float[] storeRM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[_p1];
        double _c3 = src[_p1 + 1];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    public Double2x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRMFloat(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(offset, src, stride);
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM3x3(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM3x3(double[] dest) { return storeCM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM3x3(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM3x3Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCM3x3Relative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest);
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM3x3(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.m01;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM3x3(float[] dest) { return storeCM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM3x3(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM3x3Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCM3x3Relative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at the given absolute index
     * (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM3x3FloatUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3Float(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3Float(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM3x3Float(this, offset, dest);
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM3x3(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM3x3(double[] dest) { return storeRM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM3x3(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM3x3Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRM3x3Relative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRM3x3Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3(this, offset, dest);
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM3x3(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = (float) this.m10;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = 0.0f;
        dest[offset + 6] = 0.0f;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM3x3(float[] dest) { return storeRM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM3x3(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM3x3Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRM3x3Relative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3Absolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM3x3FloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 3x3 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRM3x3FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM3x3FloatUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3Float(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM3x3Float(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM3x3Float(this, offset, dest);
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM4x4(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM4x4(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at the given absolute index
     * (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4Float(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Float(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Float(this, offset, dest);
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM4x4(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM4x4(double[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM4x4(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = 0.0f;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM4x4(float[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4Float(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Float(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4Float(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Float(this, offset, dest);
    }

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public double[] store(double[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public float[] store(float[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public ByteBuffer store(int index, ByteBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride); }

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Double2x2 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(float[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store3x3(double[] dest) { return storeCM3x3(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store3x3(double[] dest, int offset) { return storeCM3x3(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store3x3(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store3x3(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer store3x3Relative(DoubleBuffer dest) { return storeCM3x3Relative(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store3x3(float[] dest) { return storeCM3x3(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store3x3(float[] dest, int offset) { return storeCM3x3(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store3x3(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store3x3(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer store3x3Relative(FloatBuffer dest) { return storeCM3x3Relative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store3x3(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store3x3(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM3x3Absolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer store3x3Relative(ByteBuffer dest) { return storeCM3x3Relative(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store3x3(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store3x3(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM3x3(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 store3x3Unsafe(long address) { return StoreLoad.RAW_OPS.storeCM3x3Unsafe(this, address); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store4x4(double[] dest, int offset) { return storeCM4x4(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store4x4(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store4x4(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer store4x4Relative(DoubleBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store4x4(float[] dest, int offset) { return storeCM4x4(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store4x4(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store4x4(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer store4x4Relative(FloatBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store4x4(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store4x4(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer store4x4Relative(ByteBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store4x4(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 store4x4Unsafe(long address) { return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address); }

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

    private abstract static class Double2x2SegOps {
        public abstract MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest);
        public abstract Double2x2 loadCM(long offset, MemorySegment src);
        public abstract MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest);
        public abstract Double2x2 loadCMFloat(long offset, MemorySegment src);
        public abstract MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest);
        public abstract Double2x2 loadRM(long offset, MemorySegment src);
        public abstract MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest);
        public abstract Double2x2 loadRMFloat(long offset, MemorySegment src);
        public abstract MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double2x2 loadCM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double2x2 loadCMFloat(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double2x2 loadRM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double2x2 loadRMFloat(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeCM3x3(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeCM3x3Float(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeRM3x3(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeRM3x3Float(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeCM4x4(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeCM4x4Float(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeRM4x4(Double2x2 self, long offset, MemorySegment dest);
        public abstract MemorySegment storeRM4x4Float(Double2x2 self, long offset, MemorySegment dest);
    }

    private static final class Double2x2SegOpsMS extends Double2x2SegOps {
        public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m01);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
            return dest;
        }

        public Double2x2 loadCM(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m01);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
            return dest;
        }

        public Double2x2 loadCMFloat(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
            return dest;
        }

        public Double2x2 loadRM(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
            return dest;
        }

        public Double2x2 loadRMFloat(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            return dest;
        }

        public Double2x2 loadCM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
            double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
            return dest;
        }

        public Double2x2 loadCMFloat(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
            double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            return dest;
        }

        public Double2x2 loadRM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1);
            double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
            return dest;
        }

        public Double2x2 loadRMFloat(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1);
            double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCM3x3(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
            return dest;
        }

        public MemorySegment storeCM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
            return dest;
        }

        public MemorySegment storeRM3x3(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 1.0);
            return dest;
        }

        public MemorySegment storeRM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 1.0f);
            return dest;
        }

        public MemorySegment storeCM4x4(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
            return dest;
        }

        public MemorySegment storeCM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
            return dest;
        }

        public MemorySegment storeRM4x4(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, 1.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 96L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 104L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 112L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 120L, 1.0);
            return dest;
        }

        public MemorySegment storeRM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, 1.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 48L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 52L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 56L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 60L, 1.0f);
            return dest;
        }
    }

    private static final class Double2x2SegOpsUnsafe extends Double2x2SegOps {
        private static final Double2x2SegOpsMS MS = new Double2x2SegOpsMS();

        public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m10);
            U.putDouble(address + 16L, self.m01);
            U.putDouble(address + 24L, self.m11);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m10);
            U.putDouble(bytes, address + 16L, self.m01);
            U.putDouble(bytes, address + 24L, self.m11);
            return dest;
        }

        public Double2x2 loadCM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getDouble(address);
            double _c2 = U.getDouble(address + 8L);
            double _c1 = U.getDouble(address + 16L);
            double _c3 = U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getDouble(bytes, address);
            double _c2 = U.getDouble(bytes, address + 8L);
            double _c1 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
            if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m10);
            U.putFloat(address + 8L, (float) self.m01);
            U.putFloat(address + 12L, (float) self.m11);
            return dest;
        }

        private static MemorySegment storeCMFloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m10);
            U.putFloat(bytes, address + 8L, (float) self.m01);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return dest;
        }

        public Double2x2 loadCMFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMFloatOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getFloat(address);
            double _c2 = U.getFloat(address + 4L);
            double _c1 = U.getFloat(address + 8L);
            double _c3 = U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getFloat(bytes, address);
            double _c2 = U.getFloat(bytes, address + 4L);
            double _c1 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m01);
            U.putDouble(address + 16L, self.m10);
            U.putDouble(address + 24L, self.m11);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m01);
            U.putDouble(bytes, address + 16L, self.m10);
            U.putDouble(bytes, address + 24L, self.m11);
            return dest;
        }

        public Double2x2 loadRM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            double _c3 = U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
            if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m01);
            U.putFloat(address + 8L, (float) self.m10);
            U.putFloat(address + 12L, (float) self.m11);
            return dest;
        }

        private static MemorySegment storeRMFloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m01);
            U.putFloat(bytes, address + 8L, (float) self.m10);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return dest;
        }

        public Double2x2 loadRMFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMFloatOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            double _c3 = U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m10);
            U.putDouble(_p1, self.m01);
            U.putDouble(_p1 + 8, self.m11);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Double2x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m10);
            U.putDouble(bytes, _p1, self.m01);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return dest;
        }

        public Double2x2 loadCM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c2 = U.getDouble(address + 8);
            double _c1 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c2 = U.getDouble(bytes, address + 8);
            double _c1 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m10);
            U.putFloat(_p1, (float) self.m01);
            U.putFloat(_p1 + 4, (float) self.m11);
            return dest;
        }

        private static MemorySegment storeCMFloatOnHeap(Double2x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m10);
            U.putFloat(bytes, _p1, (float) self.m01);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return dest;
        }

        public Double2x2 loadCMFloat(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMFloatOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c2 = U.getFloat(address + 4);
            double _c1 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMFloatOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMFloat(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c2 = U.getFloat(bytes, address + 4);
            double _c1 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m01);
            U.putDouble(_p1, self.m10);
            U.putDouble(_p1 + 8, self.m11);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Double2x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m01);
            U.putDouble(bytes, _p1, self.m10);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return dest;
        }

        public Double2x2 loadRM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8);
            double _c2 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8);
            double _c2 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m01);
            U.putFloat(_p1, (float) self.m10);
            U.putFloat(_p1 + 4, (float) self.m11);
            return dest;
        }

        private static MemorySegment storeRMFloatOnHeap(Double2x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m01);
            U.putFloat(bytes, _p1, (float) self.m10);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return dest;
        }

        public Double2x2 loadRMFloat(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMFloatOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4);
            double _c2 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMFloatOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMFloat(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4);
            double _c2 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeCM3x3(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
            if (!dest.isNative()) return storeCM3x3OnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return dest;
        }

        private static MemorySegment storeCM3x3OnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM3x3(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return dest;
        }

        public MemorySegment storeCM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM3x3Float(self, offset, dest);
            if (!dest.isNative()) return storeCM3x3FloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m01);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return dest;
        }

        private static MemorySegment storeCM3x3FloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM3x3Float(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m01);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return dest;
        }

        public MemorySegment storeRM3x3(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
            if (!dest.isNative()) return storeRM3x3OnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return dest;
        }

        private static MemorySegment storeRM3x3OnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM3x3(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return dest;
        }

        public MemorySegment storeRM3x3Float(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM3x3Float(self, offset, dest);
            if (!dest.isNative()) return storeRM3x3FloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m10);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return dest;
        }

        private static MemorySegment storeRM3x3FloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM3x3Float(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m10);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return dest;
        }

        public MemorySegment storeCM4x4(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
            if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return dest;
        }

        private static MemorySegment storeCM4x4OnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM4x4(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return dest;
        }

        public MemorySegment storeCM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
            if (!dest.isNative()) return storeCM4x4FloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return dest;
        }

        private static MemorySegment storeCM4x4FloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM4x4Float(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return dest;
        }

        public MemorySegment storeRM4x4(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
            if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m10);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return dest;
        }

        private static MemorySegment storeRM4x4OnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM4x4(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m10);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return dest;
        }

        public MemorySegment storeRM4x4Float(Double2x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
            if (!dest.isNative()) return storeRM4x4FloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m10);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return dest;
        }

        private static MemorySegment storeRM4x4FloatOnHeap(Double2x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM4x4Float(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m10);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return dest;
        }
    }

    private abstract static class Double2x2BbOps {
        public abstract DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract Double2x2 loadCMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract Double2x2 loadCMAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract Double2x2 loadCMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract Double2x2 loadRMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract Double2x2 loadRMAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract Double2x2 loadRMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
        public abstract Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
        public abstract Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
        public abstract FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
        public abstract ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    }

    private static final class Double2x2BbOpsApi extends Double2x2BbOps {
        public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m01);
            buf.put(index + 3, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c1 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m01);
            buf.putDouble(index + 24, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c2 = buf.getDouble(index + 8);
            double _c1 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(index + 2, (float) self.m01);
            buf.put(index + 3, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c1 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(index + 8, (float) self.m01);
            buf.putFloat(index + 12, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c2 = buf.getFloat(index + 4);
            double _c1 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m10);
            buf.put(index + 3, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, self.m10);
            buf.putDouble(index + 24, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(index + 2, (float) self.m10);
            buf.put(index + 3, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(index + 8, (float) self.m10);
            buf.putFloat(index + 12, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c1 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(_p1, self.m01);
            buf.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            double _c0 = buf.getDouble(index);
            double _c2 = buf.getDouble(index + 8);
            double _c1 = buf.getDouble(_p1);
            double _c3 = buf.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(_p1, (float) self.m01);
            buf.put(_p1 + 1, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c1 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(_p1, (float) self.m01);
            buf.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            double _c0 = buf.getFloat(index);
            double _c2 = buf.getFloat(index + 4);
            double _c1 = buf.getFloat(_p1);
            double _c3 = buf.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(_p1, self.m10);
            buf.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(_p1);
            double _c3 = buf.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(_p1, (float) self.m10);
            buf.put(_p1 + 1, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(_p1, (float) self.m10);
            buf.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(_p1);
            double _c3 = buf.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, 0.0);
            buf.put(index + 3, self.m01);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, 0.0);
            buf.put(index + 6, 0.0);
            buf.put(index + 7, 0.0);
            buf.put(index + 8, 1.0);
            return buf;
        }

        public ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, 0.0);
            buf.putDouble(index + 24, self.m01);
            buf.putDouble(index + 32, self.m11);
            buf.putDouble(index + 40, 0.0);
            buf.putDouble(index + 48, 0.0);
            buf.putDouble(index + 56, 0.0);
            buf.putDouble(index + 64, 1.0);
            return buf;
        }

        public FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(index + 2, 0.0f);
            buf.put(index + 3, (float) self.m01);
            buf.put(index + 4, (float) self.m11);
            buf.put(index + 5, 0.0f);
            buf.put(index + 6, 0.0f);
            buf.put(index + 7, 0.0f);
            buf.put(index + 8, 1.0f);
            return buf;
        }

        public ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(index + 8, 0.0f);
            buf.putFloat(index + 12, (float) self.m01);
            buf.putFloat(index + 16, (float) self.m11);
            buf.putFloat(index + 20, 0.0f);
            buf.putFloat(index + 24, 0.0f);
            buf.putFloat(index + 28, 0.0f);
            buf.putFloat(index + 32, 1.0f);
            return buf;
        }

        public DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, 0.0);
            buf.put(index + 3, self.m10);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, 0.0);
            buf.put(index + 6, 0.0);
            buf.put(index + 7, 0.0);
            buf.put(index + 8, 1.0);
            return buf;
        }

        public ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, 0.0);
            buf.putDouble(index + 24, self.m10);
            buf.putDouble(index + 32, self.m11);
            buf.putDouble(index + 40, 0.0);
            buf.putDouble(index + 48, 0.0);
            buf.putDouble(index + 56, 0.0);
            buf.putDouble(index + 64, 1.0);
            return buf;
        }

        public FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(index + 2, 0.0f);
            buf.put(index + 3, (float) self.m10);
            buf.put(index + 4, (float) self.m11);
            buf.put(index + 5, 0.0f);
            buf.put(index + 6, 0.0f);
            buf.put(index + 7, 0.0f);
            buf.put(index + 8, 1.0f);
            return buf;
        }

        public ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(index + 8, 0.0f);
            buf.putFloat(index + 12, (float) self.m10);
            buf.putFloat(index + 16, (float) self.m11);
            buf.putFloat(index + 20, 0.0f);
            buf.putFloat(index + 24, 0.0f);
            buf.putFloat(index + 28, 0.0f);
            buf.putFloat(index + 32, 1.0f);
            return buf;
        }

        public DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, 0.0);
            buf.put(index + 3, 0.0);
            buf.put(index + 4, self.m01);
            buf.put(index + 5, self.m11);
            buf.put(index + 6, 0.0);
            buf.put(index + 7, 0.0);
            buf.put(index + 8, 0.0);
            buf.put(index + 9, 0.0);
            buf.put(index + 10, 1.0);
            buf.put(index + 11, 0.0);
            buf.put(index + 12, 0.0);
            buf.put(index + 13, 0.0);
            buf.put(index + 14, 0.0);
            buf.put(index + 15, 1.0);
            return buf;
        }

        public ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, 0.0);
            buf.putDouble(index + 24, 0.0);
            buf.putDouble(index + 32, self.m01);
            buf.putDouble(index + 40, self.m11);
            buf.putDouble(index + 48, 0.0);
            buf.putDouble(index + 56, 0.0);
            buf.putDouble(index + 64, 0.0);
            buf.putDouble(index + 72, 0.0);
            buf.putDouble(index + 80, 1.0);
            buf.putDouble(index + 88, 0.0);
            buf.putDouble(index + 96, 0.0);
            buf.putDouble(index + 104, 0.0);
            buf.putDouble(index + 112, 0.0);
            buf.putDouble(index + 120, 1.0);
            return buf;
        }

        public FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(index + 2, 0.0f);
            buf.put(index + 3, 0.0f);
            buf.put(index + 4, (float) self.m01);
            buf.put(index + 5, (float) self.m11);
            buf.put(index + 6, 0.0f);
            buf.put(index + 7, 0.0f);
            buf.put(index + 8, 0.0f);
            buf.put(index + 9, 0.0f);
            buf.put(index + 10, 1.0f);
            buf.put(index + 11, 0.0f);
            buf.put(index + 12, 0.0f);
            buf.put(index + 13, 0.0f);
            buf.put(index + 14, 0.0f);
            buf.put(index + 15, 1.0f);
            return buf;
        }

        public ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(index + 8, 0.0f);
            buf.putFloat(index + 12, 0.0f);
            buf.putFloat(index + 16, (float) self.m01);
            buf.putFloat(index + 20, (float) self.m11);
            buf.putFloat(index + 24, 0.0f);
            buf.putFloat(index + 28, 0.0f);
            buf.putFloat(index + 32, 0.0f);
            buf.putFloat(index + 36, 0.0f);
            buf.putFloat(index + 40, 1.0f);
            buf.putFloat(index + 44, 0.0f);
            buf.putFloat(index + 48, 0.0f);
            buf.putFloat(index + 52, 0.0f);
            buf.putFloat(index + 56, 0.0f);
            buf.putFloat(index + 60, 1.0f);
            return buf;
        }

        public DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, 0.0);
            buf.put(index + 3, 0.0);
            buf.put(index + 4, self.m10);
            buf.put(index + 5, self.m11);
            buf.put(index + 6, 0.0);
            buf.put(index + 7, 0.0);
            buf.put(index + 8, 0.0);
            buf.put(index + 9, 0.0);
            buf.put(index + 10, 1.0);
            buf.put(index + 11, 0.0);
            buf.put(index + 12, 0.0);
            buf.put(index + 13, 0.0);
            buf.put(index + 14, 0.0);
            buf.put(index + 15, 1.0);
            return buf;
        }

        public ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, 0.0);
            buf.putDouble(index + 24, 0.0);
            buf.putDouble(index + 32, self.m10);
            buf.putDouble(index + 40, self.m11);
            buf.putDouble(index + 48, 0.0);
            buf.putDouble(index + 56, 0.0);
            buf.putDouble(index + 64, 0.0);
            buf.putDouble(index + 72, 0.0);
            buf.putDouble(index + 80, 1.0);
            buf.putDouble(index + 88, 0.0);
            buf.putDouble(index + 96, 0.0);
            buf.putDouble(index + 104, 0.0);
            buf.putDouble(index + 112, 0.0);
            buf.putDouble(index + 120, 1.0);
            return buf;
        }

        public FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(index + 2, 0.0f);
            buf.put(index + 3, 0.0f);
            buf.put(index + 4, (float) self.m10);
            buf.put(index + 5, (float) self.m11);
            buf.put(index + 6, 0.0f);
            buf.put(index + 7, 0.0f);
            buf.put(index + 8, 0.0f);
            buf.put(index + 9, 0.0f);
            buf.put(index + 10, 1.0f);
            buf.put(index + 11, 0.0f);
            buf.put(index + 12, 0.0f);
            buf.put(index + 13, 0.0f);
            buf.put(index + 14, 0.0f);
            buf.put(index + 15, 1.0f);
            return buf;
        }

        public ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(index + 8, 0.0f);
            buf.putFloat(index + 12, 0.0f);
            buf.putFloat(index + 16, (float) self.m10);
            buf.putFloat(index + 20, (float) self.m11);
            buf.putFloat(index + 24, 0.0f);
            buf.putFloat(index + 28, 0.0f);
            buf.putFloat(index + 32, 0.0f);
            buf.putFloat(index + 36, 0.0f);
            buf.putFloat(index + 40, 1.0f);
            buf.putFloat(index + 44, 0.0f);
            buf.putFloat(index + 48, 0.0f);
            buf.putFloat(index + 52, 0.0f);
            buf.putFloat(index + 56, 0.0f);
            buf.putFloat(index + 60, 1.0f);
            return buf;
        }
    }

    private static final class Double2x2BbOpsUnsafe extends Double2x2BbOps {
        private static final Double2x2BbOpsApi API = new Double2x2BbOpsApi();

        public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m10);
            U.putDouble(address + 16L, self.m01);
            U.putDouble(address + 24L, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c1 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m10);
            U.putDouble(address + 16L, self.m01);
            U.putDouble(address + 24L, self.m11);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m10);
            U.putDouble(bytes, address + 16L, self.m01);
            U.putDouble(bytes, address + 24L, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c1 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8L);
            double _c1 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m10);
            U.putFloat(address + 8L, (float) self.m01);
            U.putFloat(address + 12L, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c1 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m10);
            U.putFloat(address + 8L, (float) self.m01);
            U.putFloat(address + 12L, (float) self.m11);
            return buf;
        }

        private static ByteBuffer storeCMFloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m10);
            U.putFloat(bytes, address + 8L, (float) self.m01);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c1 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4L);
            double _c1 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m01);
            U.putDouble(address + 16L, self.m10);
            U.putDouble(address + 24L, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m01);
            U.putDouble(address + 16L, self.m10);
            U.putDouble(address + 24L, self.m11);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m01);
            U.putDouble(bytes, address + 16L, self.m10);
            U.putDouble(bytes, address + 24L, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m01);
            U.putFloat(address + 8L, (float) self.m10);
            U.putFloat(address + 12L, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m01);
            U.putFloat(address + 8L, (float) self.m10);
            U.putFloat(address + 12L, (float) self.m11);
            return buf;
        }

        private static ByteBuffer storeRMFloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m01);
            U.putFloat(bytes, address + 8L, (float) self.m10);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m10);
            U.putDouble(_p1, self.m01);
            U.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c2 = U.getDouble(address + 8);
            double _c1 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m10);
            U.putDouble(_p1, self.m01);
            U.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Double2x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m10);
            U.putDouble(bytes, _p1, self.m01);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c2 = U.getDouble(address + 8);
            double _c1 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c2 = U.getDouble(bytes, address + 8);
            double _c1 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m10);
            U.putFloat(_p1, (float) self.m01);
            U.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c2 = U.getFloat(address + 4);
            double _c1 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m10);
            U.putFloat(_p1, (float) self.m01);
            U.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        private static ByteBuffer storeCMFloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m10);
            U.putFloat(bytes, _p1, (float) self.m01);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c2 = U.getFloat(address + 4);
            double _c1 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadCMFloatAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMFloatAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c2 = U.getFloat(bytes, address + 4);
            double _c1 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m01);
            U.putDouble(_p1, self.m10);
            U.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8);
            double _c2 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m01);
            U.putDouble(_p1, self.m10);
            U.putDouble(_p1 + 8, self.m11);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Double2x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m01);
            U.putDouble(bytes, _p1, self.m10);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8);
            double _c2 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8);
            double _c2 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m01);
            U.putFloat(_p1, (float) self.m10);
            U.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4);
            double _c2 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m01);
            U.putFloat(_p1, (float) self.m10);
            U.putFloat(_p1 + 4, (float) self.m11);
            return buf;
        }

        private static ByteBuffer storeRMFloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m01);
            U.putFloat(bytes, _p1, (float) self.m10);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return buf;
        }

        public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4);
            double _c2 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        private static Double2x2 loadRMFloatAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMFloatAbsolute(index, buf, stride);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4);
            double _c2 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return buf;
        }

        public ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM3x3AbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return buf;
        }

        private static ByteBuffer storeCM3x3AbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return buf;
        }

        public FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m01);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return buf;
        }

        public ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM3x3FloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m01);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return buf;
        }

        private static ByteBuffer storeCM3x3FloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCM3x3FloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m01);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return buf;
        }

        public DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return buf;
        }

        public ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM3x3AbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return buf;
        }

        private static ByteBuffer storeRM3x3AbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return buf;
        }

        public FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m10);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return buf;
        }

        public ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM3x3FloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m10);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return buf;
        }

        private static ByteBuffer storeRM3x3FloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRM3x3FloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m10);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return buf;
        }

        public DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return buf;
        }

        public ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4AbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return buf;
        }

        private static ByteBuffer storeCM4x4AbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return buf;
        }

        public FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return buf;
        }

        public ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4FloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return buf;
        }

        private static ByteBuffer storeCM4x4FloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCM4x4FloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return buf;
        }

        public DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m10);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return buf;
        }

        public ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4AbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m10);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return buf;
        }

        private static ByteBuffer storeRM4x4AbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m10);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return buf;
        }

        public FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m10);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return buf;
        }

        public ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4FloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m10);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return buf;
        }

        private static ByteBuffer storeRM4x4FloatAbsoluteElse(Double2x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRM4x4FloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m10);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return buf;
        }
    }

    private abstract static class Double2x2RawOps {
        public abstract Double2x2 storeCMUnsafe(Double2x2 self, long address);
        public abstract Double2x2 loadCMUnsafe(long address);
        public abstract Double2x2 storeCMFloatUnsafe(Double2x2 self, long address);
        public abstract Double2x2 loadCMFloatUnsafe(long address);
        public abstract Double2x2 storeRMUnsafe(Double2x2 self, long address);
        public abstract Double2x2 loadRMUnsafe(long address);
        public abstract Double2x2 storeRMFloatUnsafe(Double2x2 self, long address);
        public abstract Double2x2 loadRMFloatUnsafe(long address);
        public abstract Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride);
        public abstract Double2x2 loadCMUnsafe(long address, int stride);
        public abstract Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride);
        public abstract Double2x2 loadCMFloatUnsafe(long address, int stride);
        public abstract Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride);
        public abstract Double2x2 loadRMUnsafe(long address, int stride);
        public abstract Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride);
        public abstract Double2x2 loadRMFloatUnsafe(long address, int stride);
        public abstract Double2x2 storeCM3x3Unsafe(Double2x2 self, long address);
        public abstract Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address);
        public abstract Double2x2 storeRM3x3Unsafe(Double2x2 self, long address);
        public abstract Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address);
        public abstract Double2x2 storeCM4x4Unsafe(Double2x2 self, long address);
        public abstract Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address);
        public abstract Double2x2 storeRM4x4Unsafe(Double2x2 self, long address);
        public abstract Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address);
    }

    private static final class Double2x2RawOpsApi extends Double2x2RawOps {
        public Double2x2 storeCMUnsafe(Double2x2 self, long address) {
            self.storeCM(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public Double2x2 loadCMUnsafe(long address) {
            return Double2x2.loadCM(0L, virtualMemory().asSlice(address, 32L));
        }

        public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address) {
            self.storeCMFloat(0L, virtualMemory().asSlice(address, 16L));
            return self;
        }

        public Double2x2 loadCMFloatUnsafe(long address) {
            return Double2x2.loadCMFloat(0L, virtualMemory().asSlice(address, 16L));
        }

        public Double2x2 storeRMUnsafe(Double2x2 self, long address) {
            self.storeRM(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public Double2x2 loadRMUnsafe(long address) {
            return Double2x2.loadRM(0L, virtualMemory().asSlice(address, 32L));
        }

        public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address) {
            self.storeRMFloat(0L, virtualMemory().asSlice(address, 16L));
            return self;
        }

        public Double2x2 loadRMFloatUnsafe(long address) {
            return Double2x2.loadRMFloat(0L, virtualMemory().asSlice(address, 16L));
        }

        public Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride) {
            self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
            return self;
        }

        public Double2x2 loadCMUnsafe(long address, int stride) {
            return Double2x2.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
        }

        public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride) {
            self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
            return self;
        }

        public Double2x2 loadCMFloatUnsafe(long address, int stride) {
            return Double2x2.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
        }

        public Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride) {
            self.storeRM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
            return self;
        }

        public Double2x2 loadRMUnsafe(long address, int stride) {
            return Double2x2.loadRM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
        }

        public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride) {
            self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
            return self;
        }

        public Double2x2 loadRMFloatUnsafe(long address, int stride) {
            return Double2x2.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
        }

        public Double2x2 storeCM3x3Unsafe(Double2x2 self, long address) {
            self.storeCM3x3(0L, virtualMemory().asSlice(address, 72L));
            return self;
        }

        public Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address) {
            self.storeCM3x3Float(0L, virtualMemory().asSlice(address, 36L));
            return self;
        }

        public Double2x2 storeRM3x3Unsafe(Double2x2 self, long address) {
            self.storeRM3x3(0L, virtualMemory().asSlice(address, 72L));
            return self;
        }

        public Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address) {
            self.storeRM3x3Float(0L, virtualMemory().asSlice(address, 36L));
            return self;
        }

        public Double2x2 storeCM4x4Unsafe(Double2x2 self, long address) {
            self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
            return self;
        }

        public Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address) {
            self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
            return self;
        }

        public Double2x2 storeRM4x4Unsafe(Double2x2 self, long address) {
            self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
            return self;
        }

        public Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address) {
            self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
            return self;
        }
    }

    private static final class Double2x2RawOpsUnsafe extends Double2x2RawOps {
        public Double2x2 storeCMUnsafe(Double2x2 self, long address) {
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m10);
            U.putDouble(address + 16L, self.m01);
            U.putDouble(address + 24L, self.m11);
            return self;
        }

        public Double2x2 storeCMHeap(Double2x2 self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m10);
            U.putDouble(bytes, address + 16L, self.m01);
            U.putDouble(bytes, address + 24L, self.m11);
            return self;
        }

        public Double2x2 loadCMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c1 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadCMHeap(byte[] bytes, long address) {
            double _c0 = U.getDouble(bytes, address);
            double _c2 = U.getDouble(bytes, address + 8L);
            double _c1 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address) {
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m10);
            U.putFloat(address + 8L, (float) self.m01);
            U.putFloat(address + 12L, (float) self.m11);
            return self;
        }

        public Double2x2 storeCMFloatHeap(Double2x2 self, byte[] bytes, long address) {
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m10);
            U.putFloat(bytes, address + 8L, (float) self.m01);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return self;
        }

        public Double2x2 loadCMFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c1 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadCMFloatHeap(byte[] bytes, long address) {
            double _c0 = U.getFloat(bytes, address);
            double _c2 = U.getFloat(bytes, address + 4L);
            double _c1 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeRMUnsafe(Double2x2 self, long address) {
            U.putDouble(address, self.m00);
            U.putDouble(address + 8L, self.m01);
            U.putDouble(address + 16L, self.m10);
            U.putDouble(address + 24L, self.m11);
            return self;
        }

        public Double2x2 storeRMHeap(Double2x2 self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8L, self.m01);
            U.putDouble(bytes, address + 16L, self.m10);
            U.putDouble(bytes, address + 24L, self.m11);
            return self;
        }

        public Double2x2 loadRMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadRMHeap(byte[] bytes, long address) {
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address) {
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4L, (float) self.m01);
            U.putFloat(address + 8L, (float) self.m10);
            U.putFloat(address + 12L, (float) self.m11);
            return self;
        }

        public Double2x2 storeRMFloatHeap(Double2x2 self, byte[] bytes, long address) {
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4L, (float) self.m01);
            U.putFloat(bytes, address + 8L, (float) self.m10);
            U.putFloat(bytes, address + 12L, (float) self.m11);
            return self;
        }

        public Double2x2 loadRMFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadRMFloatHeap(byte[] bytes, long address) {
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m10);
            U.putDouble(_p1, self.m01);
            U.putDouble(_p1 + 8, self.m11);
            return self;
        }

        public Double2x2 storeCMHeap(Double2x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m10);
            U.putDouble(bytes, _p1, self.m01);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return self;
        }

        public Double2x2 loadCMUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c2 = U.getDouble(address + 8);
            double _c1 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadCMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c2 = U.getDouble(bytes, address + 8);
            double _c1 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m10);
            U.putFloat(_p1, (float) self.m01);
            U.putFloat(_p1 + 4, (float) self.m11);
            return self;
        }

        public Double2x2 storeCMFloatHeap(Double2x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m10);
            U.putFloat(bytes, _p1, (float) self.m01);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return self;
        }

        public Double2x2 loadCMFloatUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c2 = U.getFloat(address + 4);
            double _c1 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadCMFloatHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c2 = U.getFloat(bytes, address + 4);
            double _c1 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(address, self.m00);
            U.putDouble(address + 8, self.m01);
            U.putDouble(_p1, self.m10);
            U.putDouble(_p1 + 8, self.m11);
            return self;
        }

        public Double2x2 storeRMHeap(Double2x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            U.putDouble(bytes, address, self.m00);
            U.putDouble(bytes, address + 8, self.m01);
            U.putDouble(bytes, _p1, self.m10);
            U.putDouble(bytes, _p1 + 8, self.m11);
            return self;
        }

        public Double2x2 loadRMUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8);
            double _c2 = U.getDouble(_p1);
            double _c3 = U.getDouble(_p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadRMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8);
            double _c2 = U.getDouble(bytes, _p1);
            double _c3 = U.getDouble(bytes, _p1 + 8);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(address, (float) self.m00);
            U.putFloat(address + 4, (float) self.m01);
            U.putFloat(_p1, (float) self.m10);
            U.putFloat(_p1 + 4, (float) self.m11);
            return self;
        }

        public Double2x2 storeRMFloatHeap(Double2x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            U.putFloat(bytes, address, (float) self.m00);
            U.putFloat(bytes, address + 4, (float) self.m01);
            U.putFloat(bytes, _p1, (float) self.m10);
            U.putFloat(bytes, _p1 + 4, (float) self.m11);
            return self;
        }

        public Double2x2 loadRMFloatUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4);
            double _c2 = U.getFloat(_p1);
            double _c3 = U.getFloat(_p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 loadRMFloatHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4);
            double _c2 = U.getFloat(bytes, _p1);
            double _c3 = U.getFloat(bytes, _p1 + 4);
            return new Double2x2(_c0, _c1, _c2, _c3);
        }

        public Double2x2 storeCM3x3Unsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return self;
        }

        public Double2x2 storeCM3x3Heap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return self;
        }

        public Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m01);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return self;
        }

        public Double2x2 storeCM3x3FloatHeap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m01);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return self;
        }

        public Double2x2 storeRM3x3Unsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, 0.0);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 1.0);
            return self;
        }

        public Double2x2 storeRM3x3Heap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, 0.0);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 1.0);
            return self;
        }

        public Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, (float) self.m10);
            _U.putFloat(address + 16L, (float) self.m11);
            _U.putFloat(address + 20L, 0.0f);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 1.0f);
            return self;
        }

        public Double2x2 storeRM3x3FloatHeap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, (float) self.m10);
            _U.putFloat(bytes, address + 16L, (float) self.m11);
            _U.putFloat(bytes, address + 20L, 0.0f);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 1.0f);
            return self;
        }

        public Double2x2 storeCM4x4Unsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return self;
        }

        public Double2x2 storeCM4x4Heap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return self;
        }

        public Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return self;
        }

        public Double2x2 storeCM4x4FloatHeap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return self;
        }

        public Double2x2 storeRM4x4Unsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, 0.0);
            _U.putDouble(address + 24L, 0.0);
            _U.putDouble(address + 32L, self.m10);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, 0.0);
            _U.putDouble(address + 56L, 0.0);
            _U.putDouble(address + 64L, 0.0);
            _U.putDouble(address + 72L, 0.0);
            _U.putDouble(address + 80L, 1.0);
            _U.putDouble(address + 88L, 0.0);
            _U.putDouble(address + 96L, 0.0);
            _U.putDouble(address + 104L, 0.0);
            _U.putDouble(address + 112L, 0.0);
            _U.putDouble(address + 120L, 1.0);
            return self;
        }

        public Double2x2 storeRM4x4Heap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, 0.0);
            _U.putDouble(bytes, address + 24L, 0.0);
            _U.putDouble(bytes, address + 32L, self.m10);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, 0.0);
            _U.putDouble(bytes, address + 56L, 0.0);
            _U.putDouble(bytes, address + 64L, 0.0);
            _U.putDouble(bytes, address + 72L, 0.0);
            _U.putDouble(bytes, address + 80L, 1.0);
            _U.putDouble(bytes, address + 88L, 0.0);
            _U.putDouble(bytes, address + 96L, 0.0);
            _U.putDouble(bytes, address + 104L, 0.0);
            _U.putDouble(bytes, address + 112L, 0.0);
            _U.putDouble(bytes, address + 120L, 1.0);
            return self;
        }

        public Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, 0.0f);
            _U.putFloat(address + 12L, 0.0f);
            _U.putFloat(address + 16L, (float) self.m10);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, 0.0f);
            _U.putFloat(address + 28L, 0.0f);
            _U.putFloat(address + 32L, 0.0f);
            _U.putFloat(address + 36L, 0.0f);
            _U.putFloat(address + 40L, 1.0f);
            _U.putFloat(address + 44L, 0.0f);
            _U.putFloat(address + 48L, 0.0f);
            _U.putFloat(address + 52L, 0.0f);
            _U.putFloat(address + 56L, 0.0f);
            _U.putFloat(address + 60L, 1.0f);
            return self;
        }

        public Double2x2 storeRM4x4FloatHeap(Double2x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, 0.0f);
            _U.putFloat(bytes, address + 12L, 0.0f);
            _U.putFloat(bytes, address + 16L, (float) self.m10);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, 0.0f);
            _U.putFloat(bytes, address + 28L, 0.0f);
            _U.putFloat(bytes, address + 32L, 0.0f);
            _U.putFloat(bytes, address + 36L, 0.0f);
            _U.putFloat(bytes, address + 40L, 1.0f);
            _U.putFloat(bytes, address + 44L, 0.0f);
            _U.putFloat(bytes, address + 48L, 0.0f);
            _U.putFloat(bytes, address + 52L, 0.0f);
            _U.putFloat(bytes, address + 56L, 0.0f);
            _U.putFloat(bytes, address + 60L, 1.0f);
            return self;
        }
    }
}
