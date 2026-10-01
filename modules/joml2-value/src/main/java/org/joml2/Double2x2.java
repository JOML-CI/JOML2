// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable 2x2 matrix of double-precision {@code double} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * Structural property bits: the matrix caches whether it is known to be the identity, a pure
 * translation, orthogonal (a proper rotation, with any translation) or affine, and the operations
 * dispatch to cheaper arms on those bits. The {@code make*} factories set the bits from what they
 * construct and the computing operations derive them from their operands' bits; the element
 * constructors and the {@code load*} factories recompute them with {@code determineProperties()},
 * which compares elements exactly against {@code 0} and {@code 1} and infers identity, translation
 * and affine only. A rotation loaded from a buffer or set from scalars is therefore merely affine -
 * never orthogonal - until it is rebuilt through a {@code make*} factory. The bits read this 2x2
 * matrix homogeneously, as a 1D transform whose last row is {@code (0, 1)} and whose {@code m01} is
 * the translation: a 2D rotation held in a 2x2 matrix gets no bits at all.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN; the cached structural property bits are ignored, so two matrix objects holding the same
 * elements are equal whatever either one has determined about itself. {@code hashCode} is
 * consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param m00 the element in row 0, column 0
 * @param m01 the element in row 0, column 1
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param properties the cached structural property bits
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Double2x2(double m00, double m01, double m10, double m11, int properties) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 32;

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
     * Valid input: any value, NaN and the infinities included.
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getColumn_translation(col);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getColumn_affine(col);
        return getColumn_general(col);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getRow_translation(row);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getRow_affine(row);
        return getRow_general(row);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, -this.m01, 1.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(1.0, 0.0, -this.m01, this.m00, 0);
        return new Double2x2(this.m11, -this.m10, -this.m01, this.m00, 0);
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
    private Double2x2 invert_affine() {
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invert_degenerate();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(this.m01 * _t0_inv), 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double2x2 invert_general() {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate();
        double _t3_inv = 1.0 / _t3;
        return new Double2x2(this.m11 * _t3_inv, -(this.m01 * _t3_inv), -(this.m10 * _t3_inv), this.m00 * _t3_inv, 0);
    }


    /**
     * Invert this matrix, returning the result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Double2x2 invert() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, -this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine();
        return invert_general();
    }



    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_translation() {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        return new Double2x2(_t0 * _t1_inv, -(this.m01 * _t0 * _t1_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_orthogonal() {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0 * _t2_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_affine() {
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0 * _t2_inv), 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate_general() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        return new Double2x2(_t6 * _sp0, -(_t8 * _sp1), -(_t9 * _sp0), _t7 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 invert_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_affine();
        return invert_degenerate_general();
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general(Double2x2 other) {
        double _t4 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t5 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t6 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t7 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t11_inv = 1.0 / _t11;
        return new Double2x2(_t4 * _t11_inv, -(_t7 * _t11_inv), -(_t6 * _t11_inv), _t5 * _t11_inv, 0);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity(Double2x2 other) {
        double _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t3_inv = 1.0 / _t3;
        return new Double2x2(other.m11() * _t3_inv, -(other.m01() * _t3_inv), -(other.m10() * _t3_inv), other.m00() * _t3_inv, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation(Double2x2 other) {
        double _t0 = Math.fma(other.m10(), this.m01, other.m00());
        double _t1 = Math.fma(other.m11(), this.m01, other.m01());
        double _t5 = Math.fma(other.m11(), _t0, -(other.m10() * _t1));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(other.m11() * _t5_inv, -(_t1 * _t5_inv), -(other.m10() * _t5_inv), _t0 * _t5_inv, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal(Double2x2 other, int _props) {
        double _t2 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t3 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t7 = Math.fma(other.m11(), _t2, -(other.m10() * _t3));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t7_inv = 1.0 / _t7;
        return new Double2x2(other.m11() * _t7_inv, -(_t3 * _t7_inv), -(other.m10() * _t7_inv), _t2 * _t7_inv, _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private Double2x2 invertProduct_identity_identity(Double2x2 other) {
        return new Double2x2(1.0, 0.0, 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_translation(Double2x2 other) {
        return new Double2x2(1.0, -other.m01(), 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_identity_affine(Double2x2 other) {
        double _t0 = other.m00();
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(other.m01() * _t0_inv), 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_identity(Double2x2 other) {
        return new Double2x2(1.0, -this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_translation(Double2x2 other) {
        return new Double2x2(1.0, -(other.m01() + this.m01), 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_translation_affine(Double2x2 other) {
        double _t0 = other.m00();
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -((other.m01() + this.m01) * _t0_inv), 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal_translation(Double2x2 other) {
        return new Double2x2(1.0, -other.m01() - this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_orthogonal_affine(Double2x2 other, int _props) {
        double _t1 = other.m00() * this.m00;
        if (!(Math.abs(_t1) > 2.2250738585072014E-308 && Math.abs(_t1) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t1_inv = 1.0 / _t1;
        return new Double2x2(_t1_inv, -(Math.fma(other.m01(), this.m00, this.m01) * _t1_inv), 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_identity(Double2x2 other) {
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(this.m01 * _t0_inv), 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_affine_translation(Double2x2 other) {
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, -(Math.fma(other.m01(), this.m00, this.m01) * _t0_inv), 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_identity(Double2x2 other) {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t3_inv = 1.0 / _t3;
        return new Double2x2(this.m11 * _t3_inv, -(this.m01 * _t3_inv), -(this.m10 * _t3_inv), this.m00 * _t3_inv, 0);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_translation(Double2x2 other) {
        double _t0 = Math.fma(other.m01(), this.m10, this.m11);
        double _t1 = Math.fma(other.m01(), this.m00, this.m01);
        double _t5 = Math.fma(this.m00, _t0, -(this.m10 * _t1));
        if (!(Math.abs(_t5) > 2.2250738585072014E-308 && Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t5_inv = 1.0 / _t5;
        return new Double2x2(_t0 * _t5_inv, -(_t1 * _t5_inv), -(this.m10 * _t5_inv), this.m00 * _t5_inv, 0);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double2x2 invertProduct_general_affine(Double2x2 other) {
        double _t0 = other.m00() * this.m00;
        double _t1 = other.m00() * this.m10;
        double _t2 = Math.fma(other.m01(), this.m10, this.m11);
        double _t3 = Math.fma(other.m01(), this.m00, this.m01);
        double _t7 = Math.fma(_t2, _t0, -(_t3 * _t1));
        if (!(Math.abs(_t7) > 2.2250738585072014E-308 && Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t7_inv = 1.0 / _t7;
        return new Double2x2(_t2 * _t7_inv, -(_t3 * _t7_inv), -(_t1 * _t7_inv), _t0 * _t7_inv, 0);
    }


    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 invertProduct(Double2x2 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other);
            return invertProduct_identity(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other);
            return invertProduct_translation(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other);
        return invertProduct_general(other);
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}), returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x2 invertProduct(double m00, double m01, double m10, double m11) {
        double _t4 = Math.fma(m01, this.m10, m11 * this.m11);
        double _t5 = Math.fma(m00, this.m00, m10 * this.m01);
        double _t6 = Math.fma(m00, this.m10, m10 * this.m11);
        double _t7 = Math.fma(m01, this.m00, m11 * this.m01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return invertProduct_degenerate(m00, m01, m10, m11);
        double _t11_inv = 1.0 / _t11;
        return new Double2x2(_t4 * _t11_inv, -(_t7 * _t11_inv), -(_t6 * _t11_inv), _t5 * _t11_inv, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general(Double2x2 other) {
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
        return new Double2x2(_t14 * _sp0, -(_t17 * _sp1), -(_t16 * _sp0), _t15 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity(Double2x2 other) {
        double _t0 = unitScale(other.m10(), other.m11(), other.m10());
        double _t1 = unitScale(other.m00(), other.m01(), other.m00());
        double _t6 = other.m11() * _t0;
        double _t7 = other.m00() * _t1;
        double _t8 = other.m01() * _t1;
        double _t9 = other.m10() * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        return new Double2x2(_t6 * _sp0, -(_t8 * _sp1), -(_t9 * _sp0), _t7 * _sp1, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation(Double2x2 other) {
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
        return new Double2x2(_t5 * _sp0, -(_t11 * _sp1), -(_t6 * _sp0), _t10 * _sp1, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal(Double2x2 other, int _props) {
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
        return new Double2x2(_t7 * _sp0, -(_t13 * _sp1), -(_t8 * _sp0), _t12 * _sp1, _props);
    }



    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_translation(Double2x2 other) {
        double _t0 = unitScale(1.0, other.m01(), 1.0);
        double _t1_inv = 1.0 / _t0;
        return new Double2x2(_t0 * _t1_inv, -(other.m01() * _t0 * _t1_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_identity_affine(Double2x2 other) {
        double _t0 = unitScale(other.m00(), other.m01(), other.m00());
        double _t2_inv = 1.0 / (other.m00() * _t0);
        return new Double2x2(_t0 * _t2_inv, -(other.m01() * _t0 * _t2_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_identity(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        return new Double2x2(_t0 * _t1_inv, -(this.m01 * _t0 * _t1_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_translation(Double2x2 other) {
        double _t0 = other.m01() + this.m01;
        double _t1 = unitScale(1.0, _t0, 1.0);
        double _t2_inv = 1.0 / _t1;
        return new Double2x2(_t1 * _t2_inv, -(_t0 * _t1 * _t2_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_translation_affine(Double2x2 other) {
        double _t0 = other.m01() + this.m01;
        double _t1 = unitScale(other.m00(), _t0, other.m00());
        double _t3_inv = 1.0 / (other.m00() * _t1);
        return new Double2x2(_t1 * _t3_inv, -(_t0 * _t1 * _t3_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_identity(Double2x2 other) {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0 * _t2_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_translation(Double2x2 other) {
        double _t0 = Math.fma(other.m01(), this.m00, this.m01);
        double _t1 = unitScale(1.0, _t0, 1.0);
        double _t3_inv = 1.0 / (this.m00 * _t1);
        return new Double2x2(_t1 * _t3_inv, -(_t0 * _t1 * _t3_inv), 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_orthogonal_affine(Double2x2 other, int _props) {
        double _t0 = other.m00() * this.m00;
        double _t1 = Math.fma(other.m01(), this.m00, this.m01);
        double _t2 = unitScale(_t0, _t1, _t0);
        double _t4_inv = 1.0 / (_t0 * _t2);
        return new Double2x2(_t2 * _t4_inv, -(_t1 * _t2 * _t4_inv), 0.0, 1.0, _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_identity(Double2x2 other) {
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, -(this.m01 * _t0 * _t2_inv), 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_affine_translation(Double2x2 other) {
        double _t0 = Math.fma(other.m01(), this.m00, this.m01);
        double _t1 = unitScale(this.m00, _t0, this.m00);
        double _t3_inv = 1.0 / (this.m00 * _t1);
        return new Double2x2(_t1 * _t3_inv, -(_t0 * _t1 * _t3_inv), 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_identity(Double2x2 other) {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        return new Double2x2(_t6 * _sp0, -(_t8 * _sp1), -(_t9 * _sp0), _t7 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_translation(Double2x2 other) {
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
        return new Double2x2(_t10 * _sp0, -(_t11 * _sp1), -(_t7 * _sp0), _t6 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate_general_affine(Double2x2 other) {
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
        return new Double2x2(_t12 * _sp0, -(_t13 * _sp1), -(_t9 * _sp0), _t8 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate(Double2x2 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_identity_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_identity_affine(other);
            return invertProduct_degenerate_identity(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_translation_affine(other);
            return invertProduct_degenerate_translation(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_degenerate_orthogonal(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_affine_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_affine_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, Joml.BIT_AFFINE & q);
            return invertProduct_degenerate_orthogonal(other, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_general_identity(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_general_affine(other);
        return invertProduct_degenerate_general(other);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double2x2 invertProduct_degenerate(double m00, double m01, double m10, double m11) {
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
        return new Double2x2(_t14 * _sp0, -(_t17 * _sp1), -(_t16 * _sp0), _t15 * _sp1, 0);
    }




    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_affine() {
        double _t0 = this.m00;
        if (!(Math.abs(_t0) > 2.2250738585072014E-308 && Math.abs(_t0) < 4.49423283715579E307)) return normal_degenerate();
        double _t0_inv = 1.0 / _t0;
        return new Double2x2(_t0_inv, 0.0, -(this.m01 * _t0_inv), 1.0, 0);
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double2x2 normal_general() {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return normal_degenerate();
        double _t3_inv = 1.0 / _t3;
        return new Double2x2(this.m11 * _t3_inv, -(this.m10 * _t3_inv), -(this.m01 * _t3_inv), this.m00 * _t3_inv, 0);
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, -this.m01, 1.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine();
        return normal_general();
    }



    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_translation() {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t1_inv = 1.0 / _t0;
        return new Double2x2(_t0 * _t1_inv, 0.0, -(this.m01 * _t0 * _t1_inv), 1.0, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_orthogonal() {
        double _t0 = unitScale(1.0, this.m01, 1.0);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0 * _t2_inv), 1.0, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_affine() {
        double _t0 = unitScale(this.m00, this.m01, this.m00);
        double _t2_inv = 1.0 / (this.m00 * _t0);
        return new Double2x2(_t0 * _t2_inv, 0.0, -(this.m01 * _t0 * _t2_inv), 1.0, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate_general() {
        double _t0 = unitScale(this.m10, this.m11, this.m10);
        double _t1 = unitScale(this.m00, this.m01, this.m00);
        double _t6 = this.m11 * _t0;
        double _t7 = this.m00 * _t1;
        double _t8 = this.m01 * _t1;
        double _t9 = this.m10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        return new Double2x2(_t6 * _sp0, -(_t9 * _sp0), -(_t8 * _sp1), _t7 * _sp1, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double2x2 normal_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return normal_degenerate_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_affine();
        return normal_degenerate_general();
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(1.0, 0.0, this.m01, 1.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(this.m00, 0.0, this.m01, 1.0, 0);
        return new Double2x2(this.m00, this.m10, this.m01, this.m11, 0);
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
        return new Double2x2(other.m00() + this.m00, other.m01() + this.m01, other.m10() + this.m10, other.m11() + this.m11, 0);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(scalar, 0.0, 0.0, scalar, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(scalar, scalar * this.m01, 0.0, scalar, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(scalar * this.m00, scalar * this.m01, 0.0, scalar, 0);
        return new Double2x2(scalar * this.m00, scalar * this.m01, scalar * this.m10, scalar * this.m11, 0);
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
        return new Double2x2(this.m00 - other.m00(), this.m01 - other.m01(), this.m10 - other.m10(), this.m11 - other.m11(), 0);
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
     * Create a new matrix from the given values.
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
        return new Float2x2((float) (this.m00), (float) (this.m01), (float) (this.m10), (float) (this.m11));
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x3(1.0, this.m01, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x3(this.m00, this.m01, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
        return new Double2x3(this.m00, this.m01, 0.0, this.m10, this.m11, 0.0, Joml.BIT_AFFINE);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double3x3(1.0, this.m01, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double3x3(this.m00, this.m01, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        return new Double3x3(this.m00, this.m01, 0.0, this.m10, this.m11, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Result value of {@code decomposeLDU}.
     *
     * @param lower the unit lower-triangular factor
     * @param diagonal the diagonal factor
     * @param upper the unit upper-triangular factor
     */
    @jdk.internal.vm.annotation.LooselyConsistentValue
    public value record DecomposeLDUResult(Double2x2 lower, Double2x2 diagonal, Double2x2 upper) {
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
    private DecomposeLDUResult decomposeLDU_identity() {
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0));
    }


    /**
     * Private body of {@code decomposeLDU}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeLDU} dispatcher.
     */
    private DecomposeLDUResult decomposeLDU_translation() {
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(1.0, this.m01, 0.0, 1.0));
    }


    /**
     * Private body of {@code decomposeLDU}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeLDU} dispatcher.
     */
    private DecomposeLDUResult decomposeLDU_affine() {
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, 0.0, 1.0), new Double2x2(this.m00, 0.0, 0.0, 1.0), new Double2x2(1.0, this.m01 / this.m00, 0.0, 1.0));
    }


    /**
     * Private body of {@code decomposeLDU}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeLDU} dispatcher.
     */
    private DecomposeLDUResult decomposeLDU_general() {
        double _rcp0 = 1.0 / this.m00;
        double _sp0 = this.m10 * _rcp0;
        return new DecomposeLDUResult(new Double2x2(1.0, 0.0, _sp0, 1.0), new Double2x2(this.m00, 0.0, 0.0, Math.fma(-this.m01, _sp0, this.m11)), new Double2x2(1.0, this.m01 * _rcp0, 0.0, 1.0));
    }


    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, and a matrix
     * whose {@code m00} is zero has no such decomposition (the factors are then not finite).
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @return a new result value holding the lower-triangular, diagonal and upper-triangular
     *        factors
     */
    public DecomposeLDUResult decomposeLDU() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return decomposeLDU_identity();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return decomposeLDU_translation();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return decomposeLDU_affine();
        return decomposeLDU_general();
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
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Double2x2 lerp(Double2x2 other, double t) {
        return new Double2x2(Math.fma(t, other.m00() - this.m00, this.m00), Math.fma(t, other.m01() - this.m01, this.m01), Math.fma(t, other.m10() - this.m10, this.m10), Math.fma(t, other.m11() - this.m11, this.m11), ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & other.properties()) | ((Joml.UNIQUE_TRANSLATION & this.properties & other.properties()) >> 1));
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
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
        return new Double2x2(Math.fma(t, m00 - this.m00, this.m00), Math.fma(t, m01 - this.m01, this.m01), Math.fma(t, m10 - this.m10, this.m10), Math.fma(t, m11 - this.m11, this.m11), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general(Double2x2 right) {
        return new Double2x2(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_translation(Double2x2 right, int _props) {
        return new Double2x2(Math.fma(right.m10(), this.m01, right.m00()), Math.fma(right.m11(), this.m01, right.m01()), right.m10(), right.m11(), _props);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine(Double2x2 right) {
        return new Double2x2(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), right.m10(), right.m11(), Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code mul} and {@code preMul}; reached only through them.
     */
    private Double2x2 mul_translation_translation(Double2x2 right) {
        return new Double2x2(1.0, right.m01() + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties());
    }



    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_translation(Double2x2 right) {
        return new Double2x2(this.m00, Math.fma(right.m01(), this.m00, this.m01), 0.0, 1.0, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_affine_affine(Double2x2 right) {
        return new Double2x2(right.m00() * this.m00, Math.fma(right.m01(), this.m00, this.m01), 0.0, 1.0, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_translation(Double2x2 right) {
        return new Double2x2(this.m00, Math.fma(right.m01(), this.m00, this.m01), this.m10, Math.fma(right.m01(), this.m10, this.m11), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2x2 mul_general_affine(Double2x2 right) {
        return new Double2x2(right.m00() * this.m00, Math.fma(right.m01(), this.m00, this.m01), right.m00() * this.m10, Math.fma(right.m01(), this.m10, this.m11), 0);
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return right;
        int q = right.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(right.m00(), right.m01() + this.m01, 0.0, 1.0, Joml.BIT_TRANSLATION & q);
            return mul_translation(right, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(right.m00(), right.m01() + this.m01, 0.0, 1.0, Joml.BIT_ORTHOGONAL & q);
            return mul_translation(right, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_affine_translation(right);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right);
            return mul_affine(right);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right);
        return mul_general(right);
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
        return new Double2x2(Math.fma(m00, this.m00, m10 * this.m01), Math.fma(m01, this.m00, m11 * this.m01), Math.fma(m00, this.m10, m10 * this.m11), Math.fma(m01, this.m10, m11 * this.m11), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general(Double2x2 other) {
        return new Double2x2(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation(Double2x2 other, int _props) {
        return new Double2x2(other.m00(), Math.fma(other.m00(), this.m01, other.m01()), other.m10(), Math.fma(other.m10(), this.m01, other.m11()), _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine(Double2x2 other) {
        return new Double2x2(other.m00() * this.m00, Math.fma(other.m00(), this.m01, other.m01()), other.m10() * this.m00, Math.fma(other.m10(), this.m01, other.m11()), Joml.BIT_AFFINE & other.properties());
    }



    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_translation_affine(Double2x2 other, int _props) {
        return new Double2x2(other.m00(), Math.fma(other.m00(), this.m01, other.m01()), 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_translation(Double2x2 other) {
        return new Double2x2(this.m00, other.m01() + this.m01, 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_affine_affine(Double2x2 other) {
        return new Double2x2(other.m00() * this.m00, Math.fma(other.m00(), this.m01, other.m01()), 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_translation(Double2x2 other) {
        return new Double2x2(Math.fma(other.m01(), this.m10, this.m00), Math.fma(other.m01(), this.m11, this.m01), this.m10, this.m11, 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double2x2 preMul_general_affine(Double2x2 other) {
        return new Double2x2(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), this.m10, this.m11, 0);
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return other;
        int q = other.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, Joml.BIT_TRANSLATION & q);
            return preMul_translation(other, Joml.BIT_TRANSLATION & q);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other, Joml.BIT_ORTHOGONAL & q);
            return preMul_translation(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_affine_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other);
            return preMul_affine(other);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other);
        return preMul_general(other);
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
        return new Double2x2(Math.fma(m00, this.m00, m01 * this.m10), Math.fma(m00, this.m01, m01 * this.m11), Math.fma(m10, this.m00, m11 * this.m10), Math.fma(m10, this.m01, m11 * this.m11), 0);
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
        return new Double2x2(Math.fma(weight, other.m00(), this.m00), Math.fma(weight, other.m01(), this.m01), Math.fma(weight, other.m10(), this.m10), Math.fma(weight, other.m11(), this.m11), 0);
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
        return new Double2x2(Math.fma(weight, m00, this.m00), Math.fma(weight, m01, this.m01), Math.fma(weight, m10, this.m10), Math.fma(weight, m11, this.m11), 0);
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
        return makeOuterProduct(col.x(), col.y(), row.x(), row.y());
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double2x2(_t1, -_t0, _t0, _t1, 0);
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
        return makeScaling(v.x(), v.y());
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double2x2(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), 0);
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
        return preScale(v.x(), v.y());
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vX, 0.0, vY, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vX, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vX, this.m10 * vY, this.m11 * vY, 0);
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(s, 0.0, 0.0, s, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(s, s * this.m01, 0.0, s, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(s * this.m00, s * this.m01, 0.0, s, 0);
        return new Double2x2(s * this.m00, s * this.m01, s * this.m10, s * this.m11, 0);
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double2x2(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), 0);
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
        return scale(v.x(), v.y());
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
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x2(vX, 0.0, 0.0, vY, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x2(vX, this.m01 * vY, 0.0, vY, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2x2(this.m00 * vX, this.m01 * vY, 0.0, vY, 0);
        return new Double2x2(this.m00 * vX, this.m01 * vY, this.m10 * vX, this.m11 * vY, 0);
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
        return preScale(s);
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
        return mul(v.x(), v.y());
    }





    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double2 mul_general(double vX, double vY) {
        return new Double2(Math.fma(this.m00, vX, this.m01 * vY), Math.fma(this.m10, vX, this.m11 * vY));
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
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2(Math.fma(this.m01, vY, vX), vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double2(Math.fma(this.m00, vX, this.m01 * vY), vY);
        return mul_general(vX, vY);
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
        return "Double2x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n)";
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
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
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
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Double2x2 other, double epsilon) {
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x2SegOpsUnsafe()
                        : new Double2x2SegOpsMS();
        static final Double2x2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x2BbOpsUnsafe()
                        : new Double2x2BbOpsApi();
        static final Double2x2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x2RawOpsUnsafe()
                        : new Double2x2RawOpsApi();
    }


    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM(double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM(double[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(double[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c2 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf) {
        return storeCMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(DoubleBuffer buf) {
        return loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf) {
        return storeCMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(ByteBuffer buf) {
        return loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM(MemorySegment dest) { return storeCM(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(MemorySegment src) { return loadCM(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM(float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM(float[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(float[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c2 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf) {
        return storeCMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(FloatBuffer buf) {
        return loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return storeCMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
     * modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(ByteBuffer buf) {
        return loadCMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCMFloat(MemorySegment dest) { return storeCMFloat(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(MemorySegment src) { return loadCMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM(double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM(double[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(double[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf) {
        return storeRMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(DoubleBuffer buf) {
        return loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf) {
        return storeRMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(ByteBuffer buf) {
        return loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM(MemorySegment dest) { return storeRM(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(MemorySegment src) { return loadRM(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM(float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM(float[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(float[] src, int offset) {
        double _c0 = src[offset + 0];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new Double2x2(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf) {
        return storeRMAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(FloatBuffer buf) {
        return loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return storeRMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(ByteBuffer buf) {
        return loadRMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRMFloat(MemorySegment dest) { return storeRMFloat(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(MemorySegment src) { return loadRMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf, int stride) {
        return storeCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(DoubleBuffer buf, int stride) {
        return loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return storeCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(ByteBuffer buf, int stride) {
        return loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCM(MemorySegment dest, int stride) { return storeCM(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(MemorySegment src, int stride) { return loadCM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf, int stride) {
        return storeCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCM(FloatBuffer buf, int stride) {
        return loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return storeCMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(ByteBuffer buf, int stride) {
        return loadCMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadCMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment storeCMFloat(MemorySegment dest, int stride) { return storeCMFloat(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset, with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadCMFloat(MemorySegment src, int stride) { return loadCMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset, with {@code stride} elements
     * between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf, int stride) {
        return storeRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(DoubleBuffer buf, int stride) {
        return loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return storeRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(ByteBuffer buf, int stride) {
        return loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRM(MemorySegment dest, int stride) { return storeRM(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(MemorySegment src, int stride) { return loadRM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf, int stride) {
        return storeRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRM(FloatBuffer buf, int stride) {
        return loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return storeRMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(ByteBuffer buf, int stride) {
        return loadRMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < (long) 2 * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x2 r = loadRMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public MemorySegment storeRMFloat(MemorySegment dest, int stride) { return storeRMFloat(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadRMFloat(MemorySegment src, int stride) { return loadRMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM3x3(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM3x3(double[] dest) { return storeCM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM3x3(DoubleBuffer buf) {
        return storeCM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCM3x3Relative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM3x3Absolute(pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3(ByteBuffer buf) {
        return storeCM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM3x3Absolute(pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3(MemorySegment dest) { return storeCM3x3(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM3x3(float[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM3x3(FloatBuffer buf) {
        return storeCM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCM3x3Relative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM3x3Absolute(pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM3x3Float(ByteBuffer buf) {
        return storeCM3x3FloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at the given absolute index
     * (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM3x3FloatAbsolute(pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM3x3Float(MemorySegment dest) { return storeCM3x3Float(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM3x3(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM3x3(double[] dest) { return storeRM3x3(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM3x3(DoubleBuffer buf) {
        return storeRM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRM3x3Relative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM3x3Absolute(pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3(ByteBuffer buf) {
        return storeRM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM3x3Relative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM3x3Absolute(pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3(MemorySegment dest) { return storeRM3x3(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM3x3(float[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM3x3(FloatBuffer buf) {
        return storeRM3x3Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRM3x3Relative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM3x3Absolute(pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM3x3Float(ByteBuffer buf) {
        return storeRM3x3FloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM3x3FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM3x3FloatAbsolute(pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 3x3 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM3x3Float(MemorySegment dest) { return storeRM3x3Float(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 3x3 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM4x4(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4(DoubleBuffer buf) {
        return storeCM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM4x4Absolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4(ByteBuffer buf) {
        return storeCM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM4x4Absolute(pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4(MemorySegment dest) { return storeCM4x4(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM4x4(float[] dest, int offset) {
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

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4(FloatBuffer buf) {
        return storeCM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM4x4Absolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Float(ByteBuffer buf) {
        return storeCM4x4FloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at the given absolute index
     * (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeCM4x4FloatAbsolute(pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeCM4x4Float(MemorySegment dest) { return storeCM4x4Float(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM4x4(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM4x4(double[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4(DoubleBuffer buf) {
        return storeRM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM4x4Absolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4(ByteBuffer buf) {
        return storeRM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM4x4Absolute(pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4(MemorySegment dest) { return storeRM4x4(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM4x4(float[] dest, int offset) {
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

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4(FloatBuffer buf) {
        return storeRM4x4Absolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM4x4Absolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Float(ByteBuffer buf) {
        return storeRM4x4FloatAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeRM4x4FloatAbsolute(pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeRM4x4Float(MemorySegment dest) { return storeRM4x4Float(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(DoubleBuffer dest) { return storeCM(dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return storeCM(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store(FloatBuffer dest) { return storeCM(dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store(int index, FloatBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store(ByteBuffer dest) { return storeCM(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store(int index, ByteBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return storeCM(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest) { return storeCM(offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 storeUnsafe(long address) { return storeCMUnsafe(address); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public ByteBuffer store(int index, ByteBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest, int stride) { return storeCM(offset, dest, stride); }

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Double2x2 storeUnsafe(long address, int stride) { return storeCMUnsafe(address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(double[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(DoubleBuffer src) { return loadCM(src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, DoubleBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(float[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(FloatBuffer src) { return loadCM(src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, FloatBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(ByteBuffer src) { return loadCM(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, ByteBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(MemorySegment src) { return loadCM(src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(long offset, MemorySegment src) { return loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadUnsafe(long address) { return loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, DoubleBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, FloatBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(int index, ByteBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 load(long offset, MemorySegment src, int stride) { return loadCM(offset, src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x2} holding the loaded elements
     */
    public static Double2x2 loadUnsafe(long address, int stride) { return loadCMUnsafe(address, stride); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store3x3(double[] dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store3x3(DoubleBuffer dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store3x3(int index, DoubleBuffer dest) { return storeCM3x3Absolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store3x3(float[] dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store3x3(FloatBuffer dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store3x3(int index, FloatBuffer dest) { return storeCM3x3Absolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 3x3
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store3x3(ByteBuffer dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store3x3(int index, ByteBuffer dest) { return storeCM3x3Absolute(index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 3x3 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store3x3(MemorySegment dest) { return storeCM3x3(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 3x3 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store3x3(long offset, MemorySegment dest) { return storeCM3x3(offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 3x3 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 store3x3Unsafe(long address) { return storeCM3x3Unsafe(address); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store4x4(double[] dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store4x4(DoubleBuffer dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store4x4(int index, DoubleBuffer dest) { return storeCM4x4Absolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store4x4(float[] dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store4x4(FloatBuffer dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store4x4(int index, FloatBuffer dest) { return storeCM4x4Absolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store4x4(ByteBuffer dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    public ByteBuffer store4x4(int index, ByteBuffer dest) { return storeCM4x4Absolute(index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store4x4(MemorySegment dest) { return storeCM4x4(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store4x4(long offset, MemorySegment dest) { return storeCM4x4(offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x2 store4x4Unsafe(long address) { return storeCM4x4Unsafe(address); }
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
