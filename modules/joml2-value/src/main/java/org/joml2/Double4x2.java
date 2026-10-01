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
 * Immutable 4x2 matrix of double-precision {@code double} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
 *
 * @param m00 the element in row 0, column 0
 * @param m01 the element in row 0, column 1
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param m20 the element in row 2, column 0
 * @param m21 the element in row 2, column 1
 * @param m30 the element in row 3, column 0
 * @param m31 the element in row 3, column 1
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Double4x2(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 64;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 4;

    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 2;

    /** The zero matrix (all components 0). */
    public static final Double4x2 ZERO = new Double4x2(0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Double4x2 IDENTITY = new Double4x2();

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     */
    public Double4x2(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        this.m20 = m20;
        this.m21 = m21;
        this.m30 = m30;
        this.m31 = m31;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: the method reads no input.
     */
    public Double4x2() {
        this(1, 0, 0, 1, 0, 0, 0, 0);
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public Double4x2(Double4 c0, Double4 c1) {
        this(c0.x(), c1.x(), c0.y(), c1.y(), c0.z(), c1.z(), c0.w(), c1.w());
    }

    /**
     * Create an array of {@code length} elements, each {@link #ZERO}.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double4x2[] newArray(int length) {
        return (Double4x2[]) org.joml2.internal.value.FlatArrays.newArray(Double4x2.class, length, ZERO);
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Double4x2[] newArray(int length, Double4x2 init) {
        return (Double4x2[]) org.joml2.internal.value.FlatArrays.newArray(Double4x2.class, length, init);
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m00() { return m00; }

    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m01() { return m01; }

    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m10() { return m10; }

    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m11() { return m11; }

    /** {@return the element in row 2, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m20() { return m20; }

    /** {@return the element in row 2, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m21() { return m21; }

    /** {@return the element in row 3, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m30() { return m30; }

    /** {@return the element in row 3, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m31() { return m31; }

    /**
     * Get the column at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Double4 getColumn(int col) {
        return switch (col) {
            case 0 -> new Double4(this.m00, this.m10, this.m20, this.m30);
            case 1 -> new Double4(this.m01, this.m11, this.m21, this.m31);
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
    public Double2 getRow(int row) {
        return switch (row) {
            case 0 -> new Double2(this.m00, this.m01);
            case 1 -> new Double2(this.m10, this.m11);
            case 2 -> new Double2(this.m20, this.m21);
            case 3 -> new Double2(this.m30, this.m31);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01) + java.lang.Math.fma(this.m10, this.m10, this.m11 * this.m11) + (java.lang.Math.fma(this.m20, this.m20, this.m21 * this.m21) + java.lang.Math.fma(this.m30, this.m30, this.m31 * this.m31)));
        } else {
            return java.lang.Math.sqrt(((this.m00) * (this.m00) + (this.m01 * this.m01)) + ((this.m10) * (this.m10) + (this.m11 * this.m11)) + (((this.m20) * (this.m20) + (this.m21 * this.m21)) + ((this.m30) * (this.m30) + (this.m31 * this.m31))));
        }
    }

    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x4 transpose() {
        return new Double2x4(this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31);
    }

    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Double4x2 add(Double4x2 other) {
        return new Double4x2(other.m00 + this.m00, other.m01 + this.m01, other.m10 + this.m10, other.m11 + this.m11, other.m20 + this.m20, other.m21 + this.m21, other.m30 + this.m30, other.m31 + this.m31);
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double4x2 add(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        return new Double4x2(m00 + this.m00, m01 + this.m01, m10 + this.m10, m11 + this.m11, m20 + this.m20, m21 + this.m21, m30 + this.m30, m31 + this.m31);
    }

    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Double4x2 mul(double scalar) {
        return new Double4x2(scalar * this.m00, scalar * this.m01, scalar * this.m10, scalar * this.m11, scalar * this.m20, scalar * this.m21, scalar * this.m30, scalar * this.m31);
    }

    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double4x2 negate() {
        return new Double4x2(-this.m00, -this.m01, -this.m10, -this.m11, -this.m20, -this.m21, -this.m30, -this.m31);
    }

    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Double4x2 sub(Double4x2 other) {
        return new Double4x2(this.m00 - other.m00, this.m01 - other.m01, this.m10 - other.m10, this.m11 - other.m11, this.m20 - other.m20, this.m21 - other.m21, this.m30 - other.m30, this.m31 - other.m31);
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double4x2 sub(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        return new Double4x2(this.m00 - m00, this.m01 - m01, this.m10 - m10, this.m11 - m11, this.m20 - m20, this.m21 - m21, this.m30 - m30, this.m31 - m31);
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
    public Double4x2 set(Double4x2 v) {
        return new Double4x2(v.m00, v.m01, v.m10, v.m11, v.m20, v.m21, v.m30, v.m31);
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
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @return the resulting matrix
     */
    public Double4x2 set(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        return new Double4x2(m00, m01, m10, m11, m20, m21, m30, m31);
    }

    /**
     * Convert this matrix to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float4x2} holding the result
     */
    public Float4x2 toFloat() {
        return new Float4x2((float) (this.m00), (float) (this.m01), (float) (this.m10), (float) (this.m11), (float) (this.m20), (float) (this.m21), (float) (this.m30), (float) (this.m31));
    }

    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Double4x2 makeIdentity() {
        return new Double4x2(1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0);
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
    public Double4x2 lerp(Double4x2 other, double t) {
        if (!org.joml2.internal.FmaSwitch.ON) return new Double4x2(((t) * (other.m00 - this.m00) + (this.m00)), ((t) * (other.m01 - this.m01) + (this.m01)), ((t) * (other.m10 - this.m10) + (this.m10)), ((t) * (other.m11 - this.m11) + (this.m11)), ((t) * (other.m20 - this.m20) + (this.m20)), ((t) * (other.m21 - this.m21) + (this.m21)), ((t) * (other.m30 - this.m30) + (this.m30)), ((t) * (other.m31 - this.m31) + (this.m31)));
        return new Double4x2(java.lang.Math.fma(t, other.m00 - this.m00, this.m00), java.lang.Math.fma(t, other.m01 - this.m01, this.m01), java.lang.Math.fma(t, other.m10 - this.m10, this.m10), java.lang.Math.fma(t, other.m11 - this.m11, this.m11), java.lang.Math.fma(t, other.m20 - this.m20, this.m20), java.lang.Math.fma(t, other.m21 - this.m21, this.m21), java.lang.Math.fma(t, other.m30 - this.m30, this.m30), java.lang.Math.fma(t, other.m31 - this.m31, this.m31));
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}, {@code m30}, {@code m31}) using the interpolation
     * factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}, {@code m30},
     * {@code m31}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Double4x2 lerp(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double t) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double4x2(java.lang.Math.fma(t, m00 - this.m00, this.m00), java.lang.Math.fma(t, m01 - this.m01, this.m01), java.lang.Math.fma(t, m10 - this.m10, this.m10), java.lang.Math.fma(t, m11 - this.m11, this.m11), java.lang.Math.fma(t, m20 - this.m20, this.m20), java.lang.Math.fma(t, m21 - this.m21, this.m21), java.lang.Math.fma(t, m30 - this.m30, this.m30), java.lang.Math.fma(t, m31 - this.m31, this.m31));
        } else {
            return new Double4x2(((t) * (m00 - this.m00) + (this.m00)), ((t) * (m01 - this.m01) + (this.m01)), ((t) * (m10 - this.m10) + (this.m10)), ((t) * (m11 - this.m11) + (this.m11)), ((t) * (m20 - this.m20) + (this.m20)), ((t) * (m21 - this.m21) + (this.m21)), ((t) * (m30 - this.m30) + (this.m30)), ((t) * (m31 - this.m31) + (this.m31)));
        }
    }

    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 4x2 * 2x2 matrix product: the inner dimensions meet, no operand is
     * extended.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return the resulting matrix
     */
    public Double4x2 mul(Double2x2 right) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right);
        double _right_m00 = right.m00();
        double _right_m10 = right.m10();
        double _right_m01 = right.m01();
        double _right_m11 = right.m11();
        return new Double4x2(java.lang.Math.fma(_right_m00, this.m00, _right_m10 * this.m01), java.lang.Math.fma(_right_m01, this.m00, _right_m11 * this.m01), java.lang.Math.fma(_right_m00, this.m10, _right_m10 * this.m11), java.lang.Math.fma(_right_m01, this.m10, _right_m11 * this.m11), java.lang.Math.fma(_right_m00, this.m20, _right_m10 * this.m21), java.lang.Math.fma(_right_m01, this.m20, _right_m11 * this.m21), java.lang.Math.fma(_right_m00, this.m30, _right_m10 * this.m31), java.lang.Math.fma(_right_m01, this.m30, _right_m11 * this.m31));
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 mul_mulAdd(Double2x2 right) {
        double _right_m00 = right.m00();
        double _right_m10 = right.m10();
        double _right_m01 = right.m01();
        double _right_m11 = right.m11();
        return new Double4x2(((_right_m00) * (this.m00) + (_right_m10 * this.m01)), ((_right_m01) * (this.m00) + (_right_m11 * this.m01)), ((_right_m00) * (this.m10) + (_right_m10 * this.m11)), ((_right_m01) * (this.m10) + (_right_m11 * this.m11)), ((_right_m00) * (this.m20) + (_right_m10 * this.m21)), ((_right_m01) * (this.m20) + (_right_m11 * this.m21)), ((_right_m00) * (this.m30) + (_right_m10 * this.m31)), ((_right_m01) * (this.m30) + (_right_m11 * this.m31)));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s3df0280f_c0_fma(Double4x4 other, double[] _m) {
        _m[0] = java.lang.Math.fma(other.m03(), this.m30, java.lang.Math.fma(other.m02(), this.m20, java.lang.Math.fma(other.m00(), this.m00, other.m01() * this.m10)));
        _m[2] = java.lang.Math.fma(other.m13(), this.m30, java.lang.Math.fma(other.m12(), this.m20, java.lang.Math.fma(other.m10(), this.m00, other.m11() * this.m10)));
        _m[4] = java.lang.Math.fma(other.m23(), this.m30, java.lang.Math.fma(other.m22(), this.m20, java.lang.Math.fma(other.m20(), this.m00, other.m21() * this.m10)));
        _m[6] = java.lang.Math.fma(other.m33(), this.m30, java.lang.Math.fma(other.m32(), this.m20, java.lang.Math.fma(other.m30(), this.m00, other.m31() * this.m10)));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s3df0280f_c0_mulAdd(Double4x4 other, double[] _m) {
        _m[0] = ((other.m03()) * (this.m30) + (((other.m02()) * (this.m20) + (((other.m00()) * (this.m00) + (other.m01() * this.m10))))));
        _m[2] = ((other.m13()) * (this.m30) + (((other.m12()) * (this.m20) + (((other.m10()) * (this.m00) + (other.m11() * this.m10))))));
        _m[4] = ((other.m23()) * (this.m30) + (((other.m22()) * (this.m20) + (((other.m20()) * (this.m00) + (other.m21() * this.m10))))));
        _m[6] = ((other.m33()) * (this.m30) + (((other.m32()) * (this.m20) + (((other.m30()) * (this.m00) + (other.m31() * this.m10))))));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s3df0280f_c1_fma(Double4x4 other, double[] _m) {
        _m[1] = java.lang.Math.fma(other.m03(), this.m31, java.lang.Math.fma(other.m02(), this.m21, java.lang.Math.fma(other.m00(), this.m01, other.m01() * this.m11)));
        _m[3] = java.lang.Math.fma(other.m13(), this.m31, java.lang.Math.fma(other.m12(), this.m21, java.lang.Math.fma(other.m10(), this.m01, other.m11() * this.m11)));
        _m[5] = java.lang.Math.fma(other.m23(), this.m31, java.lang.Math.fma(other.m22(), this.m21, java.lang.Math.fma(other.m20(), this.m01, other.m21() * this.m11)));
        _m[7] = java.lang.Math.fma(other.m33(), this.m31, java.lang.Math.fma(other.m32(), this.m21, java.lang.Math.fma(other.m30(), this.m01, other.m31() * this.m11)));
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
    public Double4x2 preMul(Double4x4 other) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] _m = new double[8];
            preMul_s3df0280f_c0_fma(other, _m);
            preMul_s3df0280f_c1_fma(other, _m);
            return new Double4x2(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7]);
        } else {
            double[] _m = new double[8];
            preMul_s3df0280f_c0_mulAdd(other, _m);
            _m[1] = ((other.m03()) * (this.m31) + (((other.m02()) * (this.m21) + (((other.m00()) * (this.m01) + (other.m01() * this.m11))))));
            _m[3] = ((other.m13()) * (this.m31) + (((other.m12()) * (this.m21) + (((other.m10()) * (this.m01) + (other.m11() * this.m11))))));
            _m[5] = ((other.m23()) * (this.m31) + (((other.m22()) * (this.m21) + (((other.m20()) * (this.m01) + (other.m21() * this.m11))))));
            _m[7] = ((other.m33()) * (this.m31) + (((other.m32()) * (this.m21) + (((other.m30()) * (this.m01) + (other.m31() * this.m11))))));
            return new Double4x2(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7]);
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
    public Double4x2 addScaled(Double4x2 other, double weight) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double4x2(java.lang.Math.fma(weight, other.m00, this.m00), java.lang.Math.fma(weight, other.m01, this.m01), java.lang.Math.fma(weight, other.m10, this.m10), java.lang.Math.fma(weight, other.m11, this.m11), java.lang.Math.fma(weight, other.m20, this.m20), java.lang.Math.fma(weight, other.m21, this.m21), java.lang.Math.fma(weight, other.m30, this.m30), java.lang.Math.fma(weight, other.m31, this.m31));
        } else {
            return new Double4x2(((weight) * (other.m00) + (this.m00)), ((weight) * (other.m01) + (this.m01)), ((weight) * (other.m10) + (this.m10)), ((weight) * (other.m11) + (this.m11)), ((weight) * (other.m20) + (this.m20)), ((weight) * (other.m21) + (this.m21)), ((weight) * (other.m30) + (this.m30)), ((weight) * (other.m31) + (this.m31)));
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) scaled by {@code weight} to this matrix, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11},
     *        {@code m20}, {@code m21}, {@code m30}, {@code m31}) by before adding
     * @return the resulting matrix
     */
    public Double4x2 addScaled(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double weight) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double4x2(java.lang.Math.fma(weight, m00, this.m00), java.lang.Math.fma(weight, m01, this.m01), java.lang.Math.fma(weight, m10, this.m10), java.lang.Math.fma(weight, m11, this.m11), java.lang.Math.fma(weight, m20, this.m20), java.lang.Math.fma(weight, m21, this.m21), java.lang.Math.fma(weight, m30, this.m30), java.lang.Math.fma(weight, m31, this.m31));
        } else {
            return new Double4x2(((weight) * (m00) + (this.m00)), ((weight) * (m01) + (this.m01)), ((weight) * (m10) + (this.m10)), ((weight) * (m11) + (this.m11)), ((weight) * (m20) + (this.m20)), ((weight) * (m21) + (this.m21)), ((weight) * (m30) + (this.m30)), ((weight) * (m31) + (this.m31)));
        }
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
    public Double4 mul(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double4(java.lang.Math.fma(this.m00, vX, this.m01 * vY), java.lang.Math.fma(this.m10, vX, this.m11 * vY), java.lang.Math.fma(this.m20, vX, this.m21 * vY), java.lang.Math.fma(this.m30, vX, this.m31 * vY));
        } else {
            return new Double4(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)), ((this.m20) * (vX) + (this.m21 * vY)), ((this.m30) * (vX) + (this.m31 * vY)));
        }
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
    public Double4 mul(double vX, double vY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Double4(java.lang.Math.fma(this.m00, vX, this.m01 * vY), java.lang.Math.fma(this.m10, vX, this.m11 * vY), java.lang.Math.fma(this.m20, vX, this.m21 * vY), java.lang.Math.fma(this.m30, vX, this.m31 * vY));
        } else {
            return new Double4(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)), ((this.m20) * (vX) + (this.m21 * vY)), ((this.m30) * (vX) + (this.m31 * vY)));
        }
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Double4x2 withM00(double v) {
        return new Double4x2(v, m01, m10, m11, m20, m21, m30, m31);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Double4x2 withM01(double v) {
        return new Double4x2(m00, v, m10, m11, m20, m21, m30, m31);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Double4x2 withM10(double v) {
        return new Double4x2(m00, m01, v, m11, m20, m21, m30, m31);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Double4x2 withM11(double v) {
        return new Double4x2(m00, m01, m10, v, m20, m21, m30, m31);
    }

    /**
     * {@return a copy with the {@code m20} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m20} element
     */
    public Double4x2 withM20(double v) {
        return new Double4x2(m00, m01, m10, m11, v, m21, m30, m31);
    }

    /**
     * {@return a copy with the {@code m21} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m21} element
     */
    public Double4x2 withM21(double v) {
        return new Double4x2(m00, m01, m10, m11, m20, v, m30, m31);
    }

    /**
     * {@return a copy with the {@code m30} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m30} element
     */
    public Double4x2 withM30(double v) {
        return new Double4x2(m00, m01, m10, m11, m20, m21, v, m31);
    }

    /**
     * {@return a copy with the {@code m31} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m31} element
     */
    public Double4x2 withM31(double v) {
        return new Double4x2(m00, m01, m10, m11, m20, m21, m30, v);
    }

    @Override public String toString() {
        return "Double4x2(\n    " + this.m00 + ", " + this.m01 + "\n    " + this.m10 + ", " + this.m11 + "\n    " + this.m20 + ", " + this.m21 + "\n    " + this.m30 + ", " + this.m31 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4x2)) return false;
        Double4x2 o = (Double4x2) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m20) == Double.doubleToLongBits(o.m20)
            && Double.doubleToLongBits(m21) == Double.doubleToLongBits(o.m21)
            && Double.doubleToLongBits(m30) == Double.doubleToLongBits(o.m30)
            && Double.doubleToLongBits(m31) == Double.doubleToLongBits(o.m31);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m20) ^ (Double.doubleToLongBits(m20) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m21) ^ (Double.doubleToLongBits(m21) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m30) ^ (Double.doubleToLongBits(m30) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m31) ^ (Double.doubleToLongBits(m31) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m20)
            && Double.isFinite(m21)
            && Double.isFinite(m30)
            && Double.isFinite(m31);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m20)
            || Double.isNaN(m21)
            || Double.isNaN(m30)
            || Double.isNaN(m31);
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
    public boolean equalsEpsilon(Double4x2 other, double epsilon) {
        return java.lang.Math.abs(m00 - other.m00) <= epsilon
            && java.lang.Math.abs(m01 - other.m01) <= epsilon
            && java.lang.Math.abs(m10 - other.m10) <= epsilon
            && java.lang.Math.abs(m11 - other.m11) <= epsilon
            && java.lang.Math.abs(m20 - other.m20) <= epsilon
            && java.lang.Math.abs(m21 - other.m21) <= epsilon
            && java.lang.Math.abs(m30 - other.m30) <= epsilon
            && java.lang.Math.abs(m31 - other.m31) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double4x2SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2SegOpsUnsafe()
            : new Double4x2SegOpsMS();
        static final Double4x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2BbOpsUnsafe()
            : new Double4x2BbOpsApi();
        static final Double4x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2RawOpsUnsafe()
            : new Double4x2RawOpsApi();
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
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c4 = src[offset + 2];
        double _c6 = src[offset + 3];
        double _c1 = src[offset + 4];
        double _c3 = src[offset + 5];
        double _c5 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(double[] src) { return loadCM(src, 0); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(DoubleBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public Double4x2 storeCMUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(long offset, MemorySegment src) {
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
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m30;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m21;
        dest[offset + 7] = (float) this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c4 = src[offset + 2];
        double _c6 = src[offset + 3];
        double _c1 = src[offset + 4];
        double _c3 = src[offset + 5];
        double _c5 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(float[] src) { return loadCM(src, 0); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(FloatBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public Double4x2 storeCMFloatUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloatUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(long offset, MemorySegment src) {
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
        dest[offset + 4] = this.m20;
        dest[offset + 5] = this.m21;
        dest[offset + 6] = this.m30;
        dest[offset + 7] = this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(double[] src) { return loadRM(src, 0); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(DoubleBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public Double4x2 storeRMUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(long offset, MemorySegment src) {
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
        dest[offset + 4] = (float) this.m20;
        dest[offset + 5] = (float) this.m21;
        dest[offset + 6] = (float) this.m30;
        dest[offset + 7] = (float) this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(float[] src) { return loadRM(src, 0); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(FloatBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public Double4x2 storeRMFloatUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloatUnsafe(long address) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(long offset, MemorySegment src) {
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
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c4 = src[offset + 2];
        double _c6 = src[offset + 3];
        double _c1 = src[_p1];
        double _c3 = src[_p1 + 1];
        double _c5 = src[_p1 + 2];
        double _c7 = src[_p1 + 3];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
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
    public Double4x2 storeCMUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(long offset, MemorySegment src, int stride) {
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
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m30;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m21;
        dest[_p1 + 3] = (float) this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        double _c0 = src[offset];
        double _c2 = src[offset + 1];
        double _c4 = src[offset + 2];
        double _c6 = src[offset + 3];
        double _c1 = src[_p1];
        double _c3 = src[_p1 + 1];
        double _c5 = src[_p1 + 2];
        double _c7 = src[_p1 + 3];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf, stride);
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
    public Double4x2 storeCMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(0L, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
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
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[_p1];
        double _c3 = src[_p1 + 1];
        double _c4 = src[_p2];
        double _c5 = src[_p2 + 1];
        double _c6 = src[_p3];
        double _c7 = src[_p3 + 1];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride);
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
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
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
    public Double4x2 storeRMUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(long offset, MemorySegment src, int stride) {
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
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m20;
        dest[_p2 + 1] = (float) this.m21;
        dest[_p3] = (float) this.m30;
        dest[_p3 + 1] = (float) this.m31;
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[_p1];
        double _c3 = src[_p1 + 1];
        double _c4 = src[_p2];
        double _c5 = src[_p2 + 1];
        double _c6 = src[_p3];
        double _c7 = src[_p3 + 1];
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
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
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride);
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
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
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
    public Double4x2 storeRMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloatUnsafe(long address, int stride) {
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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(0L, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(offset, src, stride);
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
    public Double4x2 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public Double4x2 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(double[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(float[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double4x2 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

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
     * @return a new {@code Double4x2} holding the loaded elements
     */
    public static Double4x2 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    private abstract static class Double4x2SegOps {
        public abstract MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest);
        public abstract Double4x2 loadCM(long offset, MemorySegment src);
        public abstract MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest);
        public abstract Double4x2 loadCMFloat(long offset, MemorySegment src);
        public abstract MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest);
        public abstract Double4x2 loadRM(long offset, MemorySegment src);
        public abstract MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest);
        public abstract Double4x2 loadRMFloat(long offset, MemorySegment src);
        public abstract MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double4x2 loadCM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double4x2 loadCMFloat(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double4x2 loadRM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest, int stride);
        public abstract Double4x2 loadRMFloat(long offset, MemorySegment src, int stride);
    }

    private static final class Double4x2SegOpsMS extends Double4x2SegOps {
        public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
            return dest;
        }

        public Double4x2 loadCM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31);
            return dest;
        }

        public Double4x2 loadCMFloat(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
            return dest;
        }

        public Double4x2 loadRM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.m31);
            return dest;
        }

        public Double4x2 loadRMFloat(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31);
            return dest;
        }

        public Double4x2 loadCM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
            double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
            double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, (float) self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, (float) self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, (float) self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, (float) self.m31);
            return dest;
        }

        public Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
            double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
            double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31);
            return dest;
        }

        public Double4x2 loadRM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
            double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
            double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, (float) self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, (float) self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, (float) self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2, (float) self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, (float) self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p3, (float) self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, (float) self.m31);
            return dest;
        }

        public Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
            double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
            double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private static final class Double4x2SegOpsUnsafe extends Double4x2SegOps {
        private static final Double4x2SegOpsMS MS = new Double4x2SegOpsMS();

        public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m30);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, self.m21);
            _U.putDouble(address + 56L, self.m31);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Double4x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m30);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, self.m21);
            _U.putDouble(bytes, address + 56L, self.m31);
            return dest;
        }

        public Double4x2 loadCM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c4 = _U.getDouble(address + 16L);
            double _c6 = _U.getDouble(address + 24L);
            double _c1 = _U.getDouble(address + 32L);
            double _c3 = _U.getDouble(address + 40L);
            double _c5 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8L);
            double _c4 = _U.getDouble(bytes, address + 16L);
            double _c6 = _U.getDouble(bytes, address + 24L);
            double _c1 = _U.getDouble(bytes, address + 32L);
            double _c3 = _U.getDouble(bytes, address + 40L);
            double _c5 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
            if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, (float) self.m20);
            _U.putFloat(address + 12L, (float) self.m30);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, (float) self.m21);
            _U.putFloat(address + 28L, (float) self.m31);
            return dest;
        }

        private static MemorySegment storeCMFloatOnHeap(Double4x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, (float) self.m20);
            _U.putFloat(bytes, address + 12L, (float) self.m30);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, (float) self.m21);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return dest;
        }

        public Double4x2 loadCMFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMFloatOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c4 = _U.getFloat(address + 8L);
            double _c6 = _U.getFloat(address + 12L);
            double _c1 = _U.getFloat(address + 16L);
            double _c3 = _U.getFloat(address + 20L);
            double _c5 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4L);
            double _c4 = _U.getFloat(bytes, address + 8L);
            double _c6 = _U.getFloat(bytes, address + 12L);
            double _c1 = _U.getFloat(bytes, address + 16L);
            double _c3 = _U.getFloat(bytes, address + 20L);
            double _c5 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            _U.putDouble(address + 48L, self.m30);
            _U.putDouble(address + 56L, self.m31);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Double4x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            _U.putDouble(bytes, address + 48L, self.m30);
            _U.putDouble(bytes, address + 56L, self.m31);
            return dest;
        }

        public Double4x2 loadRM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
            if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, (float) self.m10);
            _U.putFloat(address + 12L, (float) self.m11);
            _U.putFloat(address + 16L, (float) self.m20);
            _U.putFloat(address + 20L, (float) self.m21);
            _U.putFloat(address + 24L, (float) self.m30);
            _U.putFloat(address + 28L, (float) self.m31);
            return dest;
        }

        private static MemorySegment storeRMFloatOnHeap(Double4x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, (float) self.m10);
            _U.putFloat(bytes, address + 12L, (float) self.m11);
            _U.putFloat(bytes, address + 16L, (float) self.m20);
            _U.putFloat(bytes, address + 20L, (float) self.m21);
            _U.putFloat(bytes, address + 24L, (float) self.m30);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return dest;
        }

        public Double4x2 loadRMFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMFloatOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCM(Double4x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Double4x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, address + 24, self.m30);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            _U.putDouble(bytes, _p1 + 24, self.m31);
            return dest;
        }

        public Double4x2 loadCM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8);
            double _c4 = _U.getDouble(address + 16);
            double _c6 = _U.getDouble(address + 24);
            double _c1 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c5 = _U.getDouble(_p1 + 16);
            double _c7 = _U.getDouble(_p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8);
            double _c4 = _U.getDouble(bytes, address + 16);
            double _c6 = _U.getDouble(bytes, address + 24);
            double _c1 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c5 = _U.getDouble(bytes, _p1 + 16);
            double _c7 = _U.getDouble(bytes, _p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeCMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m10);
            _U.putFloat(address + 8, (float) self.m20);
            _U.putFloat(address + 12, (float) self.m30);
            _U.putFloat(_p1, (float) self.m01);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p1 + 8, (float) self.m21);
            _U.putFloat(_p1 + 12, (float) self.m31);
            return dest;
        }

        private static MemorySegment storeCMFloatOnHeap(Double4x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m10);
            _U.putFloat(bytes, address + 8, (float) self.m20);
            _U.putFloat(bytes, address + 12, (float) self.m30);
            _U.putFloat(bytes, _p1, (float) self.m01);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p1 + 8, (float) self.m21);
            _U.putFloat(bytes, _p1 + 12, (float) self.m31);
            return dest;
        }

        public Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMFloatOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4);
            double _c4 = _U.getFloat(address + 8);
            double _c6 = _U.getFloat(address + 12);
            double _c1 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c5 = _U.getFloat(_p1 + 8);
            double _c7 = _U.getFloat(_p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMFloatOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMFloat(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4);
            double _c4 = _U.getFloat(bytes, address + 8);
            double _c6 = _U.getFloat(bytes, address + 12);
            double _c1 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c5 = _U.getFloat(bytes, _p1 + 8);
            double _c7 = _U.getFloat(bytes, _p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRM(Double4x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Double4x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            _U.putDouble(bytes, _p3, self.m30);
            _U.putDouble(bytes, _p3 + 8, self.m31);
            return dest;
        }

        public Double4x2 loadRM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8);
            double _c2 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c4 = _U.getDouble(_p2);
            double _c5 = _U.getDouble(_p2 + 8);
            double _c6 = _U.getDouble(_p3);
            double _c7 = _U.getDouble(_p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8);
            double _c2 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c4 = _U.getDouble(bytes, _p2);
            double _c5 = _U.getDouble(bytes, _p2 + 8);
            double _c6 = _U.getDouble(bytes, _p3);
            double _c7 = _U.getDouble(bytes, _p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeRMFloat(Double4x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m01);
            _U.putFloat(_p1, (float) self.m10);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p2, (float) self.m20);
            _U.putFloat(_p2 + 4, (float) self.m21);
            _U.putFloat(_p3, (float) self.m30);
            _U.putFloat(_p3 + 4, (float) self.m31);
            return dest;
        }

        private static MemorySegment storeRMFloatOnHeap(Double4x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m01);
            _U.putFloat(bytes, _p1, (float) self.m10);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p2, (float) self.m20);
            _U.putFloat(bytes, _p2 + 4, (float) self.m21);
            _U.putFloat(bytes, _p3, (float) self.m30);
            _U.putFloat(bytes, _p3 + 4, (float) self.m31);
            return dest;
        }

        public Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMFloatOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4);
            double _c2 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c4 = _U.getFloat(_p2);
            double _c5 = _U.getFloat(_p2 + 4);
            double _c6 = _U.getFloat(_p3);
            double _c7 = _U.getFloat(_p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMFloatOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMFloat(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4);
            double _c2 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c4 = _U.getFloat(bytes, _p2);
            double _c5 = _U.getFloat(bytes, _p2 + 4);
            double _c6 = _U.getFloat(bytes, _p3);
            double _c7 = _U.getFloat(bytes, _p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private abstract static class Double4x2BbOps {
        public abstract DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf);
        public abstract Double4x2 loadCMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf);
        public abstract Double4x2 loadCMAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf);
        public abstract Double4x2 loadCMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf);
        public abstract Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf);
        public abstract Double4x2 loadRMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf);
        public abstract Double4x2 loadRMAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf);
        public abstract Double4x2 loadRMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf);
        public abstract Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride);
        public abstract Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride);
        public abstract Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
        public abstract Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
    }

    private static final class Double4x2BbOpsApi extends Double4x2BbOps {
        public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(index + 4, self.m01);
            buf.put(index + 5, self.m11);
            buf.put(index + 6, self.m21);
            buf.put(index + 7, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c4 = buf.get(index + 2);
            double _c6 = buf.get(index + 3);
            double _c1 = buf.get(index + 4);
            double _c3 = buf.get(index + 5);
            double _c5 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(index + 24, self.m30);
            buf.putDouble(index + 32, self.m01);
            buf.putDouble(index + 40, self.m11);
            buf.putDouble(index + 48, self.m21);
            buf.putDouble(index + 56, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c2 = buf.getDouble(index + 8);
            double _c4 = buf.getDouble(index + 16);
            double _c6 = buf.getDouble(index + 24);
            double _c1 = buf.getDouble(index + 32);
            double _c3 = buf.getDouble(index + 40);
            double _c5 = buf.getDouble(index + 48);
            double _c7 = buf.getDouble(index + 56);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(index + 2, (float) self.m20);
            buf.put(index + 3, (float) self.m30);
            buf.put(index + 4, (float) self.m01);
            buf.put(index + 5, (float) self.m11);
            buf.put(index + 6, (float) self.m21);
            buf.put(index + 7, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c4 = buf.get(index + 2);
            double _c6 = buf.get(index + 3);
            double _c1 = buf.get(index + 4);
            double _c3 = buf.get(index + 5);
            double _c5 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(index + 8, (float) self.m20);
            buf.putFloat(index + 12, (float) self.m30);
            buf.putFloat(index + 16, (float) self.m01);
            buf.putFloat(index + 20, (float) self.m11);
            buf.putFloat(index + 24, (float) self.m21);
            buf.putFloat(index + 28, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c2 = buf.getFloat(index + 4);
            double _c4 = buf.getFloat(index + 8);
            double _c6 = buf.getFloat(index + 12);
            double _c1 = buf.getFloat(index + 16);
            double _c3 = buf.getFloat(index + 20);
            double _c5 = buf.getFloat(index + 24);
            double _c7 = buf.getFloat(index + 28);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m10);
            buf.put(index + 3, self.m11);
            buf.put(index + 4, self.m20);
            buf.put(index + 5, self.m21);
            buf.put(index + 6, self.m30);
            buf.put(index + 7, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, self.m10);
            buf.putDouble(index + 24, self.m11);
            buf.putDouble(index + 32, self.m20);
            buf.putDouble(index + 40, self.m21);
            buf.putDouble(index + 48, self.m30);
            buf.putDouble(index + 56, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            double _c4 = buf.getDouble(index + 32);
            double _c5 = buf.getDouble(index + 40);
            double _c6 = buf.getDouble(index + 48);
            double _c7 = buf.getDouble(index + 56);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(index + 2, (float) self.m10);
            buf.put(index + 3, (float) self.m11);
            buf.put(index + 4, (float) self.m20);
            buf.put(index + 5, (float) self.m21);
            buf.put(index + 6, (float) self.m30);
            buf.put(index + 7, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(index + 8, (float) self.m10);
            buf.putFloat(index + 12, (float) self.m11);
            buf.putFloat(index + 16, (float) self.m20);
            buf.putFloat(index + 20, (float) self.m21);
            buf.putFloat(index + 24, (float) self.m30);
            buf.putFloat(index + 28, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            double _c4 = buf.getFloat(index + 16);
            double _c5 = buf.getFloat(index + 20);
            double _c6 = buf.getFloat(index + 24);
            double _c7 = buf.getFloat(index + 28);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m21);
            buf.put(_p1 + 3, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c4 = buf.get(index + 2);
            double _c6 = buf.get(index + 3);
            double _c1 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            double _c5 = buf.get(_p1 + 2);
            double _c7 = buf.get(_p1 + 3);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(index + 24, self.m30);
            buf.putDouble(_p1, self.m01);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p1 + 16, self.m21);
            buf.putDouble(_p1 + 24, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            double _c0 = buf.getDouble(index);
            double _c2 = buf.getDouble(index + 8);
            double _c4 = buf.getDouble(index + 16);
            double _c6 = buf.getDouble(index + 24);
            double _c1 = buf.getDouble(_p1);
            double _c3 = buf.getDouble(_p1 + 8);
            double _c5 = buf.getDouble(_p1 + 16);
            double _c7 = buf.getDouble(_p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m10);
            buf.put(index + 2, (float) self.m20);
            buf.put(index + 3, (float) self.m30);
            buf.put(_p1, (float) self.m01);
            buf.put(_p1 + 1, (float) self.m11);
            buf.put(_p1 + 2, (float) self.m21);
            buf.put(_p1 + 3, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            double _c0 = buf.get(index);
            double _c2 = buf.get(index + 1);
            double _c4 = buf.get(index + 2);
            double _c6 = buf.get(index + 3);
            double _c1 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            double _c5 = buf.get(_p1 + 2);
            double _c7 = buf.get(_p1 + 3);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m10);
            buf.putFloat(index + 8, (float) self.m20);
            buf.putFloat(index + 12, (float) self.m30);
            buf.putFloat(_p1, (float) self.m01);
            buf.putFloat(_p1 + 4, (float) self.m11);
            buf.putFloat(_p1 + 8, (float) self.m21);
            buf.putFloat(_p1 + 12, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            double _c0 = buf.getFloat(index);
            double _c2 = buf.getFloat(index + 4);
            double _c4 = buf.getFloat(index + 8);
            double _c6 = buf.getFloat(index + 12);
            double _c1 = buf.getFloat(_p1);
            double _c3 = buf.getFloat(_p1 + 4);
            double _c5 = buf.getFloat(_p1 + 8);
            double _c7 = buf.getFloat(_p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p2, self.m20);
            buf.put(_p2 + 1, self.m21);
            buf.put(_p3, self.m30);
            buf.put(_p3 + 1, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            double _c4 = buf.get(_p2);
            double _c5 = buf.get(_p2 + 1);
            double _c6 = buf.get(_p3);
            double _c7 = buf.get(_p3 + 1);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(_p1, self.m10);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p2, self.m20);
            buf.putDouble(_p2 + 8, self.m21);
            buf.putDouble(_p3, self.m30);
            buf.putDouble(_p3 + 8, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(_p1);
            double _c3 = buf.getDouble(_p1 + 8);
            double _c4 = buf.getDouble(_p2);
            double _c5 = buf.getDouble(_p2 + 8);
            double _c6 = buf.getDouble(_p3);
            double _c7 = buf.getDouble(_p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            buf.put(index, (float) self.m00);
            buf.put(index + 1, (float) self.m01);
            buf.put(_p1, (float) self.m10);
            buf.put(_p1 + 1, (float) self.m11);
            buf.put(_p2, (float) self.m20);
            buf.put(_p2 + 1, (float) self.m21);
            buf.put(_p3, (float) self.m30);
            buf.put(_p3 + 1, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(_p1);
            double _c3 = buf.get(_p1 + 1);
            double _c4 = buf.get(_p2);
            double _c5 = buf.get(_p2 + 1);
            double _c6 = buf.get(_p3);
            double _c7 = buf.get(_p3 + 1);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            buf.putFloat(index, (float) self.m00);
            buf.putFloat(index + 4, (float) self.m01);
            buf.putFloat(_p1, (float) self.m10);
            buf.putFloat(_p1 + 4, (float) self.m11);
            buf.putFloat(_p2, (float) self.m20);
            buf.putFloat(_p2 + 4, (float) self.m21);
            buf.putFloat(_p3, (float) self.m30);
            buf.putFloat(_p3 + 4, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(_p1);
            double _c3 = buf.getFloat(_p1 + 4);
            double _c4 = buf.getFloat(_p2);
            double _c5 = buf.getFloat(_p2 + 4);
            double _c6 = buf.getFloat(_p3);
            double _c7 = buf.getFloat(_p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private static final class Double4x2BbOpsUnsafe extends Double4x2BbOps {
        private static final Double4x2BbOpsApi API = new Double4x2BbOpsApi();

        public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m30);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, self.m21);
            _U.putDouble(address + 56L, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c4 = _U.getDouble(address + 16L);
            double _c6 = _U.getDouble(address + 24L);
            double _c1 = _U.getDouble(address + 32L);
            double _c3 = _U.getDouble(address + 40L);
            double _c5 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m30);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, self.m21);
            _U.putDouble(address + 56L, self.m31);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Double4x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m30);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, self.m21);
            _U.putDouble(bytes, address + 56L, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c4 = _U.getDouble(address + 16L);
            double _c6 = _U.getDouble(address + 24L);
            double _c1 = _U.getDouble(address + 32L);
            double _c3 = _U.getDouble(address + 40L);
            double _c5 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8L);
            double _c4 = _U.getDouble(bytes, address + 16L);
            double _c6 = _U.getDouble(bytes, address + 24L);
            double _c1 = _U.getDouble(bytes, address + 32L);
            double _c3 = _U.getDouble(bytes, address + 40L);
            double _c5 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, (float) self.m20);
            _U.putFloat(address + 12L, (float) self.m30);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, (float) self.m21);
            _U.putFloat(address + 28L, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c4 = _U.getFloat(address + 8L);
            double _c6 = _U.getFloat(address + 12L);
            double _c1 = _U.getFloat(address + 16L);
            double _c3 = _U.getFloat(address + 20L);
            double _c5 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, (float) self.m20);
            _U.putFloat(address + 12L, (float) self.m30);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, (float) self.m21);
            _U.putFloat(address + 28L, (float) self.m31);
            return buf;
        }

        private static ByteBuffer storeCMFloatAbsoluteElse(Double4x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, (float) self.m20);
            _U.putFloat(bytes, address + 12L, (float) self.m30);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, (float) self.m21);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c4 = _U.getFloat(address + 8L);
            double _c6 = _U.getFloat(address + 12L);
            double _c1 = _U.getFloat(address + 16L);
            double _c3 = _U.getFloat(address + 20L);
            double _c5 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4L);
            double _c4 = _U.getFloat(bytes, address + 8L);
            double _c6 = _U.getFloat(bytes, address + 12L);
            double _c1 = _U.getFloat(bytes, address + 16L);
            double _c3 = _U.getFloat(bytes, address + 20L);
            double _c5 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            _U.putDouble(address + 48L, self.m30);
            _U.putDouble(address + 56L, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            _U.putDouble(address + 48L, self.m30);
            _U.putDouble(address + 56L, self.m31);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Double4x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            _U.putDouble(bytes, address + 48L, self.m30);
            _U.putDouble(bytes, address + 56L, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, (float) self.m10);
            _U.putFloat(address + 12L, (float) self.m11);
            _U.putFloat(address + 16L, (float) self.m20);
            _U.putFloat(address + 20L, (float) self.m21);
            _U.putFloat(address + 24L, (float) self.m30);
            _U.putFloat(address + 28L, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, (float) self.m10);
            _U.putFloat(address + 12L, (float) self.m11);
            _U.putFloat(address + 16L, (float) self.m20);
            _U.putFloat(address + 20L, (float) self.m21);
            _U.putFloat(address + 24L, (float) self.m30);
            _U.putFloat(address + 28L, (float) self.m31);
            return buf;
        }

        private static ByteBuffer storeRMFloatAbsoluteElse(Double4x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, (float) self.m10);
            _U.putFloat(bytes, address + 12L, (float) self.m11);
            _U.putFloat(bytes, address + 16L, (float) self.m20);
            _U.putFloat(bytes, address + 20L, (float) self.m21);
            _U.putFloat(bytes, address + 24L, (float) self.m30);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8);
            double _c4 = _U.getDouble(address + 16);
            double _c6 = _U.getDouble(address + 24);
            double _c1 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c5 = _U.getDouble(_p1 + 16);
            double _c7 = _U.getDouble(_p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Double4x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, address + 24, self.m30);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            _U.putDouble(bytes, _p1 + 24, self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8);
            double _c4 = _U.getDouble(address + 16);
            double _c6 = _U.getDouble(address + 24);
            double _c1 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c5 = _U.getDouble(_p1 + 16);
            double _c7 = _U.getDouble(_p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8);
            double _c4 = _U.getDouble(bytes, address + 16);
            double _c6 = _U.getDouble(bytes, address + 24);
            double _c1 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c5 = _U.getDouble(bytes, _p1 + 16);
            double _c7 = _U.getDouble(bytes, _p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m10);
            _U.putFloat(address + 8, (float) self.m20);
            _U.putFloat(address + 12, (float) self.m30);
            _U.putFloat(_p1, (float) self.m01);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p1 + 8, (float) self.m21);
            _U.putFloat(_p1 + 12, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4);
            double _c4 = _U.getFloat(address + 8);
            double _c6 = _U.getFloat(address + 12);
            double _c1 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c5 = _U.getFloat(_p1 + 8);
            double _c7 = _U.getFloat(_p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m10);
            _U.putFloat(address + 8, (float) self.m20);
            _U.putFloat(address + 12, (float) self.m30);
            _U.putFloat(_p1, (float) self.m01);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p1 + 8, (float) self.m21);
            _U.putFloat(_p1 + 12, (float) self.m31);
            return buf;
        }

        private static ByteBuffer storeCMFloatAbsoluteElse(Double4x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m10);
            _U.putFloat(bytes, address + 8, (float) self.m20);
            _U.putFloat(bytes, address + 12, (float) self.m30);
            _U.putFloat(bytes, _p1, (float) self.m01);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p1 + 8, (float) self.m21);
            _U.putFloat(bytes, _p1 + 12, (float) self.m31);
            return buf;
        }

        public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4);
            double _c4 = _U.getFloat(address + 8);
            double _c6 = _U.getFloat(address + 12);
            double _c1 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c5 = _U.getFloat(_p1 + 8);
            double _c7 = _U.getFloat(_p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadCMFloatAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMFloatAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4);
            double _c4 = _U.getFloat(bytes, address + 8);
            double _c6 = _U.getFloat(bytes, address + 12);
            double _c1 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c5 = _U.getFloat(bytes, _p1 + 8);
            double _c7 = _U.getFloat(bytes, _p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8);
            double _c2 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c4 = _U.getDouble(_p2);
            double _c5 = _U.getDouble(_p2 + 8);
            double _c6 = _U.getDouble(_p3);
            double _c7 = _U.getDouble(_p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Double4x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            _U.putDouble(bytes, _p3, self.m30);
            _U.putDouble(bytes, _p3 + 8, self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8);
            double _c2 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c4 = _U.getDouble(_p2);
            double _c5 = _U.getDouble(_p2 + 8);
            double _c6 = _U.getDouble(_p3);
            double _c7 = _U.getDouble(_p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8);
            double _c2 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c4 = _U.getDouble(bytes, _p2);
            double _c5 = _U.getDouble(bytes, _p2 + 8);
            double _c6 = _U.getDouble(bytes, _p3);
            double _c7 = _U.getDouble(bytes, _p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m01);
            _U.putFloat(_p1, (float) self.m10);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p2, (float) self.m20);
            _U.putFloat(_p2 + 4, (float) self.m21);
            _U.putFloat(_p3, (float) self.m30);
            _U.putFloat(_p3 + 4, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4);
            double _c2 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c4 = _U.getFloat(_p2);
            double _c5 = _U.getFloat(_p2 + 4);
            double _c6 = _U.getFloat(_p3);
            double _c7 = _U.getFloat(_p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m01);
            _U.putFloat(_p1, (float) self.m10);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p2, (float) self.m20);
            _U.putFloat(_p2 + 4, (float) self.m21);
            _U.putFloat(_p3, (float) self.m30);
            _U.putFloat(_p3 + 4, (float) self.m31);
            return buf;
        }

        private static ByteBuffer storeRMFloatAbsoluteElse(Double4x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m01);
            _U.putFloat(bytes, _p1, (float) self.m10);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p2, (float) self.m20);
            _U.putFloat(bytes, _p2 + 4, (float) self.m21);
            _U.putFloat(bytes, _p3, (float) self.m30);
            _U.putFloat(bytes, _p3 + 4, (float) self.m31);
            return buf;
        }

        public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4);
            double _c2 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c4 = _U.getFloat(_p2);
            double _c5 = _U.getFloat(_p2 + 4);
            double _c6 = _U.getFloat(_p3);
            double _c7 = _U.getFloat(_p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static Double4x2 loadRMFloatAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMFloatAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4);
            double _c2 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c4 = _U.getFloat(bytes, _p2);
            double _c5 = _U.getFloat(bytes, _p2 + 4);
            double _c6 = _U.getFloat(bytes, _p3);
            double _c7 = _U.getFloat(bytes, _p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private abstract static class Double4x2RawOps {
        public abstract Double4x2 storeCMUnsafe(Double4x2 self, long address);
        public abstract Double4x2 loadCMUnsafe(long address);
        public abstract Double4x2 storeCMFloatUnsafe(Double4x2 self, long address);
        public abstract Double4x2 loadCMFloatUnsafe(long address);
        public abstract Double4x2 storeRMUnsafe(Double4x2 self, long address);
        public abstract Double4x2 loadRMUnsafe(long address);
        public abstract Double4x2 storeRMFloatUnsafe(Double4x2 self, long address);
        public abstract Double4x2 loadRMFloatUnsafe(long address);
        public abstract Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride);
        public abstract Double4x2 loadCMUnsafe(long address, int stride);
        public abstract Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride);
        public abstract Double4x2 loadCMFloatUnsafe(long address, int stride);
        public abstract Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride);
        public abstract Double4x2 loadRMUnsafe(long address, int stride);
        public abstract Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride);
        public abstract Double4x2 loadRMFloatUnsafe(long address, int stride);
    }

    private static final class Double4x2RawOpsApi extends Double4x2RawOps {
        public Double4x2 storeCMUnsafe(Double4x2 self, long address) {
            self.storeCM(0L, virtualMemory().asSlice(address, 64L));
            return self;
        }

        public Double4x2 loadCMUnsafe(long address) {
            return Double4x2.loadCM(0L, virtualMemory().asSlice(address, 64L));
        }

        public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address) {
            self.storeCMFloat(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public Double4x2 loadCMFloatUnsafe(long address) {
            return Double4x2.loadCMFloat(0L, virtualMemory().asSlice(address, 32L));
        }

        public Double4x2 storeRMUnsafe(Double4x2 self, long address) {
            self.storeRM(0L, virtualMemory().asSlice(address, 64L));
            return self;
        }

        public Double4x2 loadRMUnsafe(long address) {
            return Double4x2.loadRM(0L, virtualMemory().asSlice(address, 64L));
        }

        public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address) {
            self.storeRMFloat(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public Double4x2 loadRMFloatUnsafe(long address) {
            return Double4x2.loadRMFloat(0L, virtualMemory().asSlice(address, 32L));
        }

        public Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride) {
            self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
            return self;
        }

        public Double4x2 loadCMUnsafe(long address, int stride) {
            return Double4x2.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        }

        public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride) {
            self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
            return self;
        }

        public Double4x2 loadCMFloatUnsafe(long address, int stride) {
            return Double4x2.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        }

        public Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride) {
            self.storeRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
            return self;
        }

        public Double4x2 loadRMUnsafe(long address, int stride) {
            return Double4x2.loadRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        }

        public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride) {
            self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
            return self;
        }

        public Double4x2 loadRMFloatUnsafe(long address, int stride) {
            return Double4x2.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        }
    }

    private static final class Double4x2RawOpsUnsafe extends Double4x2RawOps {
        public Double4x2 storeCMUnsafe(Double4x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m30);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, self.m21);
            _U.putDouble(address + 56L, self.m31);
            return self;
        }

        public Double4x2 storeCMHeap(Double4x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m30);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, self.m21);
            _U.putDouble(bytes, address + 56L, self.m31);
            return self;
        }

        public Double4x2 loadCMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8L);
            double _c4 = _U.getDouble(address + 16L);
            double _c6 = _U.getDouble(address + 24L);
            double _c1 = _U.getDouble(address + 32L);
            double _c3 = _U.getDouble(address + 40L);
            double _c5 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadCMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8L);
            double _c4 = _U.getDouble(bytes, address + 16L);
            double _c6 = _U.getDouble(bytes, address + 24L);
            double _c1 = _U.getDouble(bytes, address + 32L);
            double _c3 = _U.getDouble(bytes, address + 40L);
            double _c5 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m10);
            _U.putFloat(address + 8L, (float) self.m20);
            _U.putFloat(address + 12L, (float) self.m30);
            _U.putFloat(address + 16L, (float) self.m01);
            _U.putFloat(address + 20L, (float) self.m11);
            _U.putFloat(address + 24L, (float) self.m21);
            _U.putFloat(address + 28L, (float) self.m31);
            return self;
        }

        public Double4x2 storeCMFloatHeap(Double4x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m10);
            _U.putFloat(bytes, address + 8L, (float) self.m20);
            _U.putFloat(bytes, address + 12L, (float) self.m30);
            _U.putFloat(bytes, address + 16L, (float) self.m01);
            _U.putFloat(bytes, address + 20L, (float) self.m11);
            _U.putFloat(bytes, address + 24L, (float) self.m21);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return self;
        }

        public Double4x2 loadCMFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4L);
            double _c4 = _U.getFloat(address + 8L);
            double _c6 = _U.getFloat(address + 12L);
            double _c1 = _U.getFloat(address + 16L);
            double _c3 = _U.getFloat(address + 20L);
            double _c5 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadCMFloatHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4L);
            double _c4 = _U.getFloat(bytes, address + 8L);
            double _c6 = _U.getFloat(bytes, address + 12L);
            double _c1 = _U.getFloat(bytes, address + 16L);
            double _c3 = _U.getFloat(bytes, address + 20L);
            double _c5 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeRMUnsafe(Double4x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            _U.putDouble(address + 48L, self.m30);
            _U.putDouble(address + 56L, self.m31);
            return self;
        }

        public Double4x2 storeRMHeap(Double4x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            _U.putDouble(bytes, address + 48L, self.m30);
            _U.putDouble(bytes, address + 56L, self.m31);
            return self;
        }

        public Double4x2 loadRMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadRMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4L, (float) self.m01);
            _U.putFloat(address + 8L, (float) self.m10);
            _U.putFloat(address + 12L, (float) self.m11);
            _U.putFloat(address + 16L, (float) self.m20);
            _U.putFloat(address + 20L, (float) self.m21);
            _U.putFloat(address + 24L, (float) self.m30);
            _U.putFloat(address + 28L, (float) self.m31);
            return self;
        }

        public Double4x2 storeRMFloatHeap(Double4x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4L, (float) self.m01);
            _U.putFloat(bytes, address + 8L, (float) self.m10);
            _U.putFloat(bytes, address + 12L, (float) self.m11);
            _U.putFloat(bytes, address + 16L, (float) self.m20);
            _U.putFloat(bytes, address + 20L, (float) self.m21);
            _U.putFloat(bytes, address + 24L, (float) self.m30);
            _U.putFloat(bytes, address + 28L, (float) self.m31);
            return self;
        }

        public Double4x2 loadRMFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadRMFloatHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            return self;
        }

        public Double4x2 storeCMHeap(Double4x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, address + 24, self.m30);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            _U.putDouble(bytes, _p1 + 24, self.m31);
            return self;
        }

        public Double4x2 loadCMUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c2 = _U.getDouble(address + 8);
            double _c4 = _U.getDouble(address + 16);
            double _c6 = _U.getDouble(address + 24);
            double _c1 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c5 = _U.getDouble(_p1 + 16);
            double _c7 = _U.getDouble(_p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadCMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c2 = _U.getDouble(bytes, address + 8);
            double _c4 = _U.getDouble(bytes, address + 16);
            double _c6 = _U.getDouble(bytes, address + 24);
            double _c1 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c5 = _U.getDouble(bytes, _p1 + 16);
            double _c7 = _U.getDouble(bytes, _p1 + 24);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m10);
            _U.putFloat(address + 8, (float) self.m20);
            _U.putFloat(address + 12, (float) self.m30);
            _U.putFloat(_p1, (float) self.m01);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p1 + 8, (float) self.m21);
            _U.putFloat(_p1 + 12, (float) self.m31);
            return self;
        }

        public Double4x2 storeCMFloatHeap(Double4x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m10);
            _U.putFloat(bytes, address + 8, (float) self.m20);
            _U.putFloat(bytes, address + 12, (float) self.m30);
            _U.putFloat(bytes, _p1, (float) self.m01);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p1 + 8, (float) self.m21);
            _U.putFloat(bytes, _p1 + 12, (float) self.m31);
            return self;
        }

        public Double4x2 loadCMFloatUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c2 = _U.getFloat(address + 4);
            double _c4 = _U.getFloat(address + 8);
            double _c6 = _U.getFloat(address + 12);
            double _c1 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c5 = _U.getFloat(_p1 + 8);
            double _c7 = _U.getFloat(_p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadCMFloatHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c2 = _U.getFloat(bytes, address + 4);
            double _c4 = _U.getFloat(bytes, address + 8);
            double _c6 = _U.getFloat(bytes, address + 12);
            double _c1 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c5 = _U.getFloat(bytes, _p1 + 8);
            double _c7 = _U.getFloat(bytes, _p1 + 12);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            return self;
        }

        public Double4x2 storeRMHeap(Double4x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            _U.putDouble(bytes, _p3, self.m30);
            _U.putDouble(bytes, _p3 + 8, self.m31);
            return self;
        }

        public Double4x2 loadRMUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8);
            double _c2 = _U.getDouble(_p1);
            double _c3 = _U.getDouble(_p1 + 8);
            double _c4 = _U.getDouble(_p2);
            double _c5 = _U.getDouble(_p2 + 8);
            double _c6 = _U.getDouble(_p3);
            double _c7 = _U.getDouble(_p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadRMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8);
            double _c2 = _U.getDouble(bytes, _p1);
            double _c3 = _U.getDouble(bytes, _p1 + 8);
            double _c4 = _U.getDouble(bytes, _p2);
            double _c5 = _U.getDouble(bytes, _p2 + 8);
            double _c6 = _U.getDouble(bytes, _p3);
            double _c7 = _U.getDouble(bytes, _p3 + 8);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.m00);
            _U.putFloat(address + 4, (float) self.m01);
            _U.putFloat(_p1, (float) self.m10);
            _U.putFloat(_p1 + 4, (float) self.m11);
            _U.putFloat(_p2, (float) self.m20);
            _U.putFloat(_p2 + 4, (float) self.m21);
            _U.putFloat(_p3, (float) self.m30);
            _U.putFloat(_p3 + 4, (float) self.m31);
            return self;
        }

        public Double4x2 storeRMFloatHeap(Double4x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.m00);
            _U.putFloat(bytes, address + 4, (float) self.m01);
            _U.putFloat(bytes, _p1, (float) self.m10);
            _U.putFloat(bytes, _p1 + 4, (float) self.m11);
            _U.putFloat(bytes, _p2, (float) self.m20);
            _U.putFloat(bytes, _p2 + 4, (float) self.m21);
            _U.putFloat(bytes, _p3, (float) self.m30);
            _U.putFloat(bytes, _p3 + 4, (float) self.m31);
            return self;
        }

        public Double4x2 loadRMFloatUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4);
            double _c2 = _U.getFloat(_p1);
            double _c3 = _U.getFloat(_p1 + 4);
            double _c4 = _U.getFloat(_p2);
            double _c5 = _U.getFloat(_p2 + 4);
            double _c6 = _U.getFloat(_p3);
            double _c7 = _U.getFloat(_p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public Double4x2 loadRMFloatHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4);
            double _c2 = _U.getFloat(bytes, _p1);
            double _c3 = _U.getFloat(bytes, _p1 + 4);
            double _c4 = _U.getFloat(bytes, _p2);
            double _c5 = _U.getFloat(bytes, _p2 + 4);
            double _c6 = _U.getFloat(bytes, _p3);
            double _c7 = _U.getFloat(bytes, _p3 + 4);
            return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }
}
