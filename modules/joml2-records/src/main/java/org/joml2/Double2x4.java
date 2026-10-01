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
 * Immutable 2x4 matrix of double-precision {@code double} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param m00 the element in row 0, column 0
 * @param m01 the element in row 0, column 1
 * @param m02 the element in row 0, column 2
 * @param m03 the element in row 0, column 3
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param m12 the element in row 1, column 2
 * @param m13 the element in row 1, column 3
 */
public record Double2x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 64;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 2;
    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 4;

    /** The zero matrix (all components 0). */
    public static final Double2x4 ZERO = new Double2x4(0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Double2x4 IDENTITY = new Double2x4();

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     */
    public Double2x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public Double2x4() {
        this(1, 0, 0, 0, 0, 1, 0, 0);
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public Double2x4(Double2 c0, Double2 c1, Double2 c2, Double2 c3) {
        this(c0.x(), c1.x(), c2.x(), c3.x(), c0.y(), c1.y(), c2.y(), c3.y());
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m00() { return m00; }
    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m01() { return m01; }
    /** {@return the element in row 0, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public double m02() { return m02; }
    /** {@return the element in row 0, column 3} <p>Valid input: any value, NaN and the infinities included. */
    public double m03() { return m03; }
    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m10() { return m10; }
    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m11() { return m11; }
    /** {@return the element in row 1, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public double m12() { return m12; }
    /** {@return the element in row 1, column 3} <p>Valid input: any value, NaN and the infinities included. */
    public double m13() { return m13; }


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
        return switch (col) {
            case 0 -> new Double2(this.m00, this.m10);
            case 1 -> new Double2(this.m01, this.m11);
            case 2 -> new Double2(this.m02, this.m12);
            case 3 -> new Double2(this.m03, this.m13);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
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
    public Double4 getRow(int row) {
        return switch (row) {
            case 0 -> new Double4(this.m00, this.m01, this.m02, this.m03);
            case 1 -> new Double4(this.m10, this.m11, this.m12, this.m13);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        return java.lang.Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m02, this.m02, this.m03 * this.m03) + (Math.fma(this.m10, this.m10, this.m11 * this.m11) + Math.fma(this.m12, this.m12, this.m13 * this.m13)));
    }


    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double4x2 transpose() {
        return new Double4x2(this.m00, this.m10, this.m01, this.m11, this.m02, this.m12, this.m03, this.m13);
    }


    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Double2x4 add(Double2x4 other) {
        return new Double2x4(other.m00() + this.m00, other.m01() + this.m01, other.m02() + this.m02, other.m03() + this.m03, other.m10() + this.m10, other.m11() + this.m11, other.m12() + this.m12, other.m13() + this.m13);
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return the resulting matrix
     */
    public Double2x4 add(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        return new Double2x4(m00 + this.m00, m01 + this.m01, m02 + this.m02, m03 + this.m03, m10 + this.m10, m11 + this.m11, m12 + this.m12, m13 + this.m13);
    }


    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Double2x4 mul(double scalar) {
        return new Double2x4(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m03, scalar * this.m10, scalar * this.m11, scalar * this.m12, scalar * this.m13);
    }


    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x4 negate() {
        return new Double2x4(-this.m00, -this.m01, -this.m02, -this.m03, -this.m10, -this.m11, -this.m12, -this.m13);
    }


    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Double2x4 sub(Double2x4 other) {
        return new Double2x4(this.m00 - other.m00(), this.m01 - other.m01(), this.m02 - other.m02(), this.m03 - other.m03(), this.m10 - other.m10(), this.m11 - other.m11(), this.m12 - other.m12(), this.m13 - other.m13());
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return the resulting matrix
     */
    public Double2x4 sub(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        return new Double2x4(this.m00 - m00, this.m01 - m01, this.m02 - m02, this.m03 - m03, this.m10 - m10, this.m11 - m11, this.m12 - m12, this.m13 - m13);
    }


    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return the resulting matrix
     */
    public Double2x4 set(Double2x4 v) {
        return new Double2x4(v.m00(), v.m01(), v.m02(), v.m03(), v.m10(), v.m11(), v.m12(), v.m13());
    }


    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return the resulting matrix
     */
    public Double2x4 set(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        return new Double2x4(m00, m01, m02, m03, m10, m11, m12, m13);
    }


    /**
     * Convert this matrix to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float2x4} holding the result
     */
    public Float2x4 toFloat() {
        return new Float2x4((float) (this.m00), (float) (this.m01), (float) (this.m02), (float) (this.m03), (float) (this.m10), (float) (this.m11), (float) (this.m12), (float) (this.m13));
    }


    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Double2x4 makeIdentity() {
        return new Double2x4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
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
    public Double2x4 lerp(Double2x4 other, double t) {
        return new Double2x4(Math.fma(t, other.m00() - this.m00, this.m00), Math.fma(t, other.m01() - this.m01, this.m01), Math.fma(t, other.m02() - this.m02, this.m02), Math.fma(t, other.m03() - this.m03, this.m03), Math.fma(t, other.m10() - this.m10, this.m10), Math.fma(t, other.m11() - this.m11, this.m11), Math.fma(t, other.m12() - this.m12, this.m12), Math.fma(t, other.m13() - this.m13, this.m13));
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}) using the interpolation
     * factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11}, {@code m12},
     * {@code m13}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Double2x4 lerp(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double t) {
        return new Double2x4(Math.fma(t, m00 - this.m00, this.m00), Math.fma(t, m01 - this.m01, this.m01), Math.fma(t, m02 - this.m02, this.m02), Math.fma(t, m03 - this.m03, this.m03), Math.fma(t, m10 - this.m10, this.m10), Math.fma(t, m11 - this.m11, this.m11), Math.fma(t, m12 - this.m12, this.m12), Math.fma(t, m13 - this.m13, this.m13));
    }


    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Double2x4 mul(Double2x4 right) {
        return new Double2x4(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m03(), this.m00, Math.fma(right.m13(), this.m01, this.m03)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), Math.fma(right.m03(), this.m10, Math.fma(right.m13(), this.m11, this.m13)));
    }


    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10},
     * {@code m11}, {@code m12}, {@code m13}), returning the result as a value.
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
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return the resulting matrix
     */
    public Double2x4 mul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        return new Double2x4(Math.fma(m00, this.m00, m10 * this.m01), Math.fma(m01, this.m00, m11 * this.m01), Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02)), Math.fma(m03, this.m00, Math.fma(m13, this.m01, this.m03)), Math.fma(m00, this.m10, m10 * this.m11), Math.fma(m01, this.m10, m11 * this.m11), Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12)), Math.fma(m03, this.m10, Math.fma(m13, this.m11, this.m13)));
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
    public Double2x4 preMul(Double2x4 other) {
        return new Double2x4(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m00(), this.m03, Math.fma(other.m01(), this.m13, other.m03())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), Math.fma(other.m10(), this.m03, Math.fma(other.m11(), this.m13, other.m13())));
    }


    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     * {@code m10}, {@code m11}, {@code m12}, {@code m13}) onto this matrix, returning the result as
     * a value.
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
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return the resulting matrix
     */
    public Double2x4 preMul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        return new Double2x4(Math.fma(m00, this.m00, m01 * this.m10), Math.fma(m00, this.m01, m01 * this.m11), Math.fma(m00, this.m02, Math.fma(m01, this.m12, m02)), Math.fma(m00, this.m03, Math.fma(m01, this.m13, m03)), Math.fma(m10, this.m00, m11 * this.m10), Math.fma(m10, this.m01, m11 * this.m11), Math.fma(m10, this.m02, Math.fma(m11, this.m12, m12)), Math.fma(m10, this.m03, Math.fma(m11, this.m13, m13)));
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
    public Double2x4 addScaled(Double2x4 other, double weight) {
        return new Double2x4(Math.fma(weight, other.m00(), this.m00), Math.fma(weight, other.m01(), this.m01), Math.fma(weight, other.m02(), this.m02), Math.fma(weight, other.m03(), this.m03), Math.fma(weight, other.m10(), this.m10), Math.fma(weight, other.m11(), this.m11), Math.fma(weight, other.m12(), this.m12), Math.fma(weight, other.m13(), this.m13));
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) scaled by {@code weight} to this matrix, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     *        {@code m10}, {@code m11}, {@code m12}, {@code m13}) by before adding
     * @return the resulting matrix
     */
    public Double2x4 addScaled(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double weight) {
        return new Double2x4(Math.fma(weight, m00, this.m00), Math.fma(weight, m01, this.m01), Math.fma(weight, m02, this.m02), Math.fma(weight, m03, this.m03), Math.fma(weight, m10, this.m10), Math.fma(weight, m11, this.m11), Math.fma(weight, m12, this.m12), Math.fma(weight, m13, this.m13));
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
    public Double2 mul(Double4 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        return new Double2(Math.fma(this.m03, vW, Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY))), Math.fma(this.m13, vW, Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY))));
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @return the resulting vector
     */
    public Double2 mul(double vX, double vY, double vZ, double vW) {
        return new Double2(Math.fma(this.m03, vW, Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY))), Math.fma(this.m13, vW, Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY))));
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Double2x4 withM00(double v) {
        return new Double2x4(v, m01, m02, m03, m10, m11, m12, m13);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Double2x4 withM01(double v) {
        return new Double2x4(m00, v, m02, m03, m10, m11, m12, m13);
    }

    /**
     * {@return a copy with the {@code m02} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m02} element
     */
    public Double2x4 withM02(double v) {
        return new Double2x4(m00, m01, v, m03, m10, m11, m12, m13);
    }

    /**
     * {@return a copy with the {@code m03} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m03} element
     */
    public Double2x4 withM03(double v) {
        return new Double2x4(m00, m01, m02, v, m10, m11, m12, m13);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Double2x4 withM10(double v) {
        return new Double2x4(m00, m01, m02, m03, v, m11, m12, m13);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Double2x4 withM11(double v) {
        return new Double2x4(m00, m01, m02, m03, m10, v, m12, m13);
    }

    /**
     * {@return a copy with the {@code m12} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m12} element
     */
    public Double2x4 withM12(double v) {
        return new Double2x4(m00, m01, m02, m03, m10, m11, v, m13);
    }

    /**
     * {@return a copy with the {@code m13} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m13} element
     */
    public Double2x4 withM13(double v) {
        return new Double2x4(m00, m01, m02, m03, m10, m11, m12, v);
    }

    @Override public String toString() {
        return "Double2x4(\n    " + m00() + ", " + m01() + ", " + m02() + ", " + m03() + "\n    " + m10() + ", " + m11() + ", " + m12() + ", " + m13() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x4)) return false;
        Double2x4 o = (Double2x4) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m02) == Double.doubleToLongBits(o.m02)
            && Double.doubleToLongBits(m03) == Double.doubleToLongBits(o.m03)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m12) == Double.doubleToLongBits(o.m12)
            && Double.doubleToLongBits(m13) == Double.doubleToLongBits(o.m13);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m02) ^ (Double.doubleToLongBits(m02) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m03) ^ (Double.doubleToLongBits(m03) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m12) ^ (Double.doubleToLongBits(m12) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m13) ^ (Double.doubleToLongBits(m13) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m02)
            && Double.isFinite(m03)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m12)
            && Double.isFinite(m13);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m02)
            || Double.isNaN(m03)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m12)
            || Double.isNaN(m13);
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
    public boolean equalsEpsilon(Double2x4 other, double epsilon) {
        return java.lang.Math.abs(m00 - other.m00()) <= epsilon
            && java.lang.Math.abs(m01 - other.m01()) <= epsilon
            && java.lang.Math.abs(m02 - other.m02()) <= epsilon
            && java.lang.Math.abs(m03 - other.m03()) <= epsilon
            && java.lang.Math.abs(m10 - other.m10()) <= epsilon
            && java.lang.Math.abs(m11 - other.m11()) <= epsilon
            && java.lang.Math.abs(m12 - other.m12()) <= epsilon
            && java.lang.Math.abs(m13 - other.m13()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4SegOpsUnsafe()
                        : new Double2x4SegOpsMS();
        static final Double2x4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4BbOpsUnsafe()
                        : new Double2x4BbOpsApi();
        static final Double2x4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4RawOpsUnsafe()
                        : new Double2x4RawOpsApi();
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
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m03;
        dest[offset + 7] = this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c4 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c5 = src[offset + 3];
        double _c2 = src[offset + 4];
        double _c6 = src[offset + 5];
        double _c3 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(double[] src) { return loadCM(src, 0); }

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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 8);
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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 64);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public Double2x4 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMUnsafe(long address) {
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
    public MemorySegment storeCM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(long offset, MemorySegment src) {
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
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m02;
        dest[offset + 5] = (float) this.m12;
        dest[offset + 6] = (float) this.m03;
        dest[offset + 7] = (float) this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c4 = src[offset + 1];
        double _c1 = src[offset + 2];
        double _c5 = src[offset + 3];
        double _c2 = src[offset + 4];
        double _c6 = src[offset + 5];
        double _c3 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(float[] src) { return loadCM(src, 0); }

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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 8);
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
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public Double2x4 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloatUnsafe(long address) {
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
    public MemorySegment storeCMFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(long offset, MemorySegment src) {
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
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(double[] src) { return loadRM(src, 0); }

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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 8);
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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 64);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public Double2x4 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMUnsafe(long address) {
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
    public MemorySegment storeRM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(long offset, MemorySegment src) {
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
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m03;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m12;
        dest[offset + 7] = (float) this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(float[] src) { return loadRM(src, 0); }

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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 8);
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
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public Double2x4 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloatUnsafe(long address) {
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
    public MemorySegment storeRMFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(long offset, MemorySegment src) {
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
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p3] = this.m03;
        dest[_p3 + 1] = this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = src[offset];
        double _c4 = src[offset + 1];
        double _c1 = src[_p1];
        double _c5 = src[_p1 + 1];
        double _c2 = src[_p2];
        double _c6 = src[_p2 + 1];
        double _c3 = src[_p3];
        double _c7 = src[_p3 + 1];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride);
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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
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
    public Double2x4 storeCMUnsafe(long address, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMUnsafe(long address, int stride) {
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
    public MemorySegment storeCM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(long offset, MemorySegment src, int stride) {
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
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m02;
        dest[_p2 + 1] = (float) this.m12;
        dest[_p3] = (float) this.m03;
        dest[_p3 + 1] = (float) this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = src[offset];
        double _c4 = src[offset + 1];
        double _c1 = src[_p1];
        double _c5 = src[_p1 + 1];
        double _c2 = src[_p2];
        double _c6 = src[_p2 + 1];
        double _c3 = src[_p3];
        double _c7 = src[_p3 + 1];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride);
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
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
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
    public Double2x4 storeCMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloatUnsafe(long address, int stride) {
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
    public MemorySegment storeCMFloat(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadCMFloat(long offset, MemorySegment src, int stride) {
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
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p1 + 3] = this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[_p1];
        double _c5 = src[_p1 + 1];
        double _c6 = src[_p1 + 2];
        double _c7 = src[_p1 + 3];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
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
    public Double2x4 storeRMUnsafe(long address, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMUnsafe(long address, int stride) {
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
    public MemorySegment storeRM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(long offset, MemorySegment src, int stride) {
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
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m03;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m12;
        dest[_p1 + 3] = (float) this.m13;
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[_p1];
        double _c5 = src[_p1 + 1];
        double _c6 = src[_p1 + 2];
        double _c7 = src[_p1 + 3];
        return new Double2x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
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
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
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
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf, stride);
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf, stride);
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
    public Double2x4 storeRMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloatUnsafe(long address, int stride) {
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
    public MemorySegment storeRMFloat(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(offset, src, stride);
    }


    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest, 0); }

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
    public DoubleBuffer store(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public DoubleBuffer store(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public float[] store(float[] dest) { return storeCM(dest, 0); }

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
    public FloatBuffer store(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public FloatBuffer store(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public ByteBuffer store(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public ByteBuffer store(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

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
    public MemorySegment store(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Double2x4 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public ByteBuffer store(int index, ByteBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public MemorySegment store(long offset, MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride); }

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
    public Double2x4 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(double[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(float[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double2x4 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double2x4} holding the loaded elements
     */
    public static Double2x4 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }
}
