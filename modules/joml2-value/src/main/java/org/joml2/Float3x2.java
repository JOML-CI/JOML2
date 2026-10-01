// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable 3x2 matrix of single-precision {@code float} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Float3x2(float m00, float m01, float m10, float m11, float m20, float m21) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 3;

    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 2;

    /** The zero matrix (all components 0). */
    public static final Float3x2 ZERO = new Float3x2(0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Float3x2 IDENTITY = new Float3x2();

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
     */
    public Float3x2(float m00, float m01, float m10, float m11, float m20, float m21) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        this.m20 = m20;
        this.m21 = m21;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: the method reads no input.
     */
    public Float3x2() {
        this(1, 0, 0, 1, 0, 0);
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public Float3x2(Float3 c0, Float3 c1) {
        this(c0.x(), c1.x(), c0.y(), c1.y(), c0.z(), c1.z());
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
    public static Float3x2[] newArray(int length) {
        return (Float3x2[]) org.joml2.internal.value.FlatArrays.newArray(Float3x2.class, length, ZERO);
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
    public static Float3x2[] newArray(int length, Float3x2 init) {
        return (Float3x2[]) org.joml2.internal.value.FlatArrays.newArray(Float3x2.class, length, init);
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m00() { return m00; }

    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m01() { return m01; }

    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m10() { return m10; }

    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m11() { return m11; }

    /** {@return the element in row 2, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m20() { return m20; }

    /** {@return the element in row 2, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m21() { return m21; }

    /**
     * Get the column at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Float3 getColumn(int col) {
        return switch (col) {
            case 0 -> new Float3(this.m00, this.m10, this.m20);
            case 1 -> new Float3(this.m01, this.m11, this.m21);
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
    public Float2 getRow(int row) {
        return switch (row) {
            case 0 -> new Float2(this.m00, this.m01);
            case 1 -> new Float2(this.m10, this.m11);
            case 2 -> new Float2(this.m20, this.m21);
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
    public float frobeniusNorm() {
        if (Math.useFma()) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.m21, this.m21, java.lang.Math.fma(this.m20, this.m20, java.lang.Math.fma(this.m11, this.m11, java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01))))));
        } else {
            return (float) java.lang.Math.sqrt(((this.m21) * (this.m21) + (((this.m20) * (this.m20) + (((this.m11) * (this.m11) + (((this.m10) * (this.m10) + (((this.m00) * (this.m00) + (this.m01 * this.m01)))))))))));
        }
    }

    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float2x3 transpose() {
        return new Float2x3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, Joml.BIT_AFFINE);
    }

    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Float3x2 add(Float3x2 other) {
        return new Float3x2(other.m00 + this.m00, other.m01 + this.m01, other.m10 + this.m10, other.m11 + this.m11, other.m20 + this.m20, other.m21 + this.m21);
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) to this
     * matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @return the resulting matrix
     */
    public Float3x2 add(float m00, float m01, float m10, float m11, float m20, float m21) {
        return new Float3x2(m00 + this.m00, m01 + this.m01, m10 + this.m10, m11 + this.m11, m20 + this.m20, m21 + this.m21);
    }

    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Float3x2 mul(float scalar) {
        return new Float3x2(scalar * this.m00, scalar * this.m01, scalar * this.m10, scalar * this.m11, scalar * this.m20, scalar * this.m21);
    }

    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x2 negate() {
        return new Float3x2(-this.m00, -this.m01, -this.m10, -this.m11, -this.m20, -this.m21);
    }

    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Float3x2 sub(Float3x2 other) {
        return new Float3x2(this.m00 - other.m00, this.m01 - other.m01, this.m10 - other.m10, this.m11 - other.m11, this.m20 - other.m20, this.m21 - other.m21);
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) from
     * this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @return the resulting matrix
     */
    public Float3x2 sub(float m00, float m01, float m10, float m11, float m20, float m21) {
        return new Float3x2(this.m00 - m00, this.m01 - m01, this.m10 - m10, this.m11 - m11, this.m20 - m20, this.m21 - m21);
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
    public Float3x2 set(Float3x2 v) {
        return new Float3x2(v.m00, v.m01, v.m10, v.m11, v.m20, v.m21);
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
     * @return the resulting matrix
     */
    public Float3x2 set(float m00, float m01, float m10, float m11, float m20, float m21) {
        return new Float3x2(m00, m01, m10, m11, m20, m21);
    }

    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3x2} holding the result
     */
    public Double3x2 toDouble() {
        return new Double3x2(this.m00, this.m01, this.m10, this.m11, this.m20, this.m21);
    }

    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Float3x2 makeIdentity() {
        return new Float3x2(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x2 lerp(Float3x2 other, float t) {
        if (Math.useFma()) {
            return new Float3x2(java.lang.Math.fma(t, other.m00 - this.m00, this.m00), java.lang.Math.fma(t, other.m01 - this.m01, this.m01), java.lang.Math.fma(t, other.m10 - this.m10, this.m10), java.lang.Math.fma(t, other.m11 - this.m11, this.m11), java.lang.Math.fma(t, other.m20 - this.m20, this.m20), java.lang.Math.fma(t, other.m21 - this.m21, this.m21));
        } else {
            return new Float3x2(((t) * (other.m00 - this.m00) + (this.m00)), ((t) * (other.m01 - this.m01) + (this.m01)), ((t) * (other.m10 - this.m10) + (this.m10)), ((t) * (other.m11 - this.m11) + (this.m11)), ((t) * (other.m20 - this.m20) + (this.m20)), ((t) * (other.m21 - this.m21) + (this.m21)));
        }
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}) using the interpolation factor {@code t}, returning
     * the result as a value.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Float3x2 lerp(float m00, float m01, float m10, float m11, float m20, float m21, float t) {
        if (Math.useFma()) {
            return new Float3x2(java.lang.Math.fma(t, m00 - this.m00, this.m00), java.lang.Math.fma(t, m01 - this.m01, this.m01), java.lang.Math.fma(t, m10 - this.m10, this.m10), java.lang.Math.fma(t, m11 - this.m11, this.m11), java.lang.Math.fma(t, m20 - this.m20, this.m20), java.lang.Math.fma(t, m21 - this.m21, this.m21));
        } else {
            return new Float3x2(((t) * (m00 - this.m00) + (this.m00)), ((t) * (m01 - this.m01) + (this.m01)), ((t) * (m10 - this.m10) + (this.m10)), ((t) * (m11 - this.m11) + (this.m11)), ((t) * (m20 - this.m20) + (this.m20)), ((t) * (m21 - this.m21) + (this.m21)));
        }
    }

    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 3x2 * 2x2 matrix product: the inner dimensions meet, no operand is
     * extended.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return the resulting matrix
     */
    public Float3x2 mul(Float2x2 right) {
        float _right_m00 = right.m00();
        float _right_m10 = right.m10();
        float _right_m01 = right.m01();
        float _right_m11 = right.m11();
        if (Math.useFma()) {
            return new Float3x2(java.lang.Math.fma(_right_m00, this.m00, _right_m10 * this.m01), java.lang.Math.fma(_right_m01, this.m00, _right_m11 * this.m01), java.lang.Math.fma(_right_m00, this.m10, _right_m10 * this.m11), java.lang.Math.fma(_right_m01, this.m10, _right_m11 * this.m11), java.lang.Math.fma(_right_m00, this.m20, _right_m10 * this.m21), java.lang.Math.fma(_right_m01, this.m20, _right_m11 * this.m21));
        } else {
            return new Float3x2(((_right_m00) * (this.m00) + (_right_m10 * this.m01)), ((_right_m01) * (this.m00) + (_right_m11 * this.m01)), ((_right_m00) * (this.m10) + (_right_m10 * this.m11)), ((_right_m01) * (this.m10) + (_right_m11 * this.m11)), ((_right_m00) * (this.m20) + (_right_m10 * this.m21)), ((_right_m01) * (this.m20) + (_right_m11 * this.m21)));
        }
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
    public Float3x2 preMul(Float3x3 other) {
        if (!Math.useFma()) return preMul_mulAdd(other);
        float _other_m02 = other.m02();
        float _other_m00 = other.m00();
        float _other_m01 = other.m01();
        float _other_m12 = other.m12();
        float _other_m10 = other.m10();
        float _other_m11 = other.m11();
        float _other_m22 = other.m22();
        float _other_m20 = other.m20();
        float _other_m21 = other.m21();
        return new Float3x2(java.lang.Math.fma(_other_m02, this.m20, java.lang.Math.fma(_other_m00, this.m00, _other_m01 * this.m10)), java.lang.Math.fma(_other_m02, this.m21, java.lang.Math.fma(_other_m00, this.m01, _other_m01 * this.m11)), java.lang.Math.fma(_other_m12, this.m20, java.lang.Math.fma(_other_m10, this.m00, _other_m11 * this.m10)), java.lang.Math.fma(_other_m12, this.m21, java.lang.Math.fma(_other_m10, this.m01, _other_m11 * this.m11)), java.lang.Math.fma(_other_m22, this.m20, java.lang.Math.fma(_other_m20, this.m00, _other_m21 * this.m10)), java.lang.Math.fma(_other_m22, this.m21, java.lang.Math.fma(_other_m20, this.m01, _other_m21 * this.m11)));
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x2 preMul_mulAdd(Float3x3 other) {
        float _other_m02 = other.m02();
        float _other_m00 = other.m00();
        float _other_m01 = other.m01();
        float _other_m12 = other.m12();
        float _other_m10 = other.m10();
        float _other_m11 = other.m11();
        float _other_m22 = other.m22();
        float _other_m20 = other.m20();
        float _other_m21 = other.m21();
        return new Float3x2(((_other_m02) * (this.m20) + (((_other_m00) * (this.m00) + (_other_m01 * this.m10)))), ((_other_m02) * (this.m21) + (((_other_m00) * (this.m01) + (_other_m01 * this.m11)))), ((_other_m12) * (this.m20) + (((_other_m10) * (this.m00) + (_other_m11 * this.m10)))), ((_other_m12) * (this.m21) + (((_other_m10) * (this.m01) + (_other_m11 * this.m11)))), ((_other_m22) * (this.m20) + (((_other_m20) * (this.m00) + (_other_m21 * this.m10)))), ((_other_m22) * (this.m21) + (((_other_m20) * (this.m01) + (_other_m21 * this.m11)))));
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
    public Float3x2 addScaled(Float3x2 other, float weight) {
        if (Math.useFma()) {
            return new Float3x2(java.lang.Math.fma(weight, other.m00, this.m00), java.lang.Math.fma(weight, other.m01, this.m01), java.lang.Math.fma(weight, other.m10, this.m10), java.lang.Math.fma(weight, other.m11, this.m11), java.lang.Math.fma(weight, other.m20, this.m20), java.lang.Math.fma(weight, other.m21, this.m21));
        } else {
            return new Float3x2(((weight) * (other.m00) + (this.m00)), ((weight) * (other.m01) + (this.m01)), ((weight) * (other.m10) + (this.m10)), ((weight) * (other.m11) + (this.m11)), ((weight) * (other.m20) + (this.m20)), ((weight) * (other.m21) + (this.m21)));
        }
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) scaled by
     * {@code weight} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11},
     *        {@code m20}, {@code m21}) by before adding
     * @return the resulting matrix
     */
    public Float3x2 addScaled(float m00, float m01, float m10, float m11, float m20, float m21, float weight) {
        if (Math.useFma()) {
            return new Float3x2(java.lang.Math.fma(weight, m00, this.m00), java.lang.Math.fma(weight, m01, this.m01), java.lang.Math.fma(weight, m10, this.m10), java.lang.Math.fma(weight, m11, this.m11), java.lang.Math.fma(weight, m20, this.m20), java.lang.Math.fma(weight, m21, this.m21));
        } else {
            return new Float3x2(((weight) * (m00) + (this.m00)), ((weight) * (m01) + (this.m01)), ((weight) * (m10) + (this.m10)), ((weight) * (m11) + (this.m11)), ((weight) * (m20) + (this.m20)), ((weight) * (m21) + (this.m21)));
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
    public Float3 mul(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        if (Math.useFma()) {
            return new Float3(java.lang.Math.fma(this.m00, vX, this.m01 * vY), java.lang.Math.fma(this.m10, vX, this.m11 * vY), java.lang.Math.fma(this.m20, vX, this.m21 * vY));
        } else {
            return new Float3(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)), ((this.m20) * (vX) + (this.m21 * vY)));
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
    public Float3 mul(float vX, float vY) {
        if (Math.useFma()) {
            return new Float3(java.lang.Math.fma(this.m00, vX, this.m01 * vY), java.lang.Math.fma(this.m10, vX, this.m11 * vY), java.lang.Math.fma(this.m20, vX, this.m21 * vY));
        } else {
            return new Float3(((this.m00) * (vX) + (this.m01 * vY)), ((this.m10) * (vX) + (this.m11 * vY)), ((this.m20) * (vX) + (this.m21 * vY)));
        }
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Float3x2 withM00(float v) {
        return new Float3x2(v, m01, m10, m11, m20, m21);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Float3x2 withM01(float v) {
        return new Float3x2(m00, v, m10, m11, m20, m21);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Float3x2 withM10(float v) {
        return new Float3x2(m00, m01, v, m11, m20, m21);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Float3x2 withM11(float v) {
        return new Float3x2(m00, m01, m10, v, m20, m21);
    }

    /**
     * {@return a copy with the {@code m20} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m20} element
     */
    public Float3x2 withM20(float v) {
        return new Float3x2(m00, m01, m10, m11, v, m21);
    }

    /**
     * {@return a copy with the {@code m21} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m21} element
     */
    public Float3x2 withM21(float v) {
        return new Float3x2(m00, m01, m10, m11, m20, v);
    }

    @Override public String toString() {
        return "Float3x2(\n    " + this.m00 + ", " + this.m01 + "\n    " + this.m10 + ", " + this.m11 + "\n    " + this.m20 + ", " + this.m21 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float3x2)) return false;
        Float3x2 o = (Float3x2) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m20) == Float.floatToIntBits(o.m20)
            && Float.floatToIntBits(m21) == Float.floatToIntBits(o.m21);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m20);
        h = 31 * h + Float.floatToIntBits(m21);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m20)
            && Float.isFinite(m21);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m20)
            || Float.isNaN(m21);
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
    public boolean equalsEpsilon(Float3x2 other, float epsilon) {
        return java.lang.Math.abs(m00 - other.m00) <= epsilon
            && java.lang.Math.abs(m01 - other.m01) <= epsilon
            && java.lang.Math.abs(m10 - other.m10) <= epsilon
            && java.lang.Math.abs(m11 - other.m11) <= epsilon
            && java.lang.Math.abs(m20 - other.m20) <= epsilon
            && java.lang.Math.abs(m21 - other.m21) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float3x2SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float3x2SegOpsUnsafe()
            : new Float3x2SegOpsMS();
        static final Float3x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float3x2BbOpsUnsafe()
            : new Float3x2BbOpsApi();
        static final Float3x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float3x2RawOpsUnsafe()
            : new Float3x2RawOpsApi();
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
    public float[] storeCM(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m21;
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
    public float[] storeCM(float[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c2 = src[offset + 1];
        float _c4 = src[offset + 2];
        float _c1 = src[offset + 3];
        float _c3 = src[offset + 4];
        float _c5 = src[offset + 5];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(float[] src) { return loadCM(src, 0); }

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
    public FloatBuffer storeCM(FloatBuffer buf) {
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
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf) {
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
    public FloatBuffer storeCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(FloatBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 6);
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 24);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(ByteBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 24);
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
    public Float3x2 storeCMUnsafe(long address) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMUnsafe(long address) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(offset, src);
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset.
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
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m21;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM(double[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c2 = (float) src[offset + 1];
        float _c4 = (float) src[offset + 2];
        float _c1 = (float) src[offset + 3];
        float _c3 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 6);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position (the position is not modified).
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
    public ByteBuffer storeCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at the given absolute index (the position is not used or
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
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position and advancing the position accordingly.
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
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf);
        buf.position(pos + 48);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x2 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}.
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
    public MemorySegment storeCMDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, starting at the given offset.
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, starting at the given offset.
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(offset, src);
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
    public float[] storeRM(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m20;
        dest[offset + 5] = this.m21;
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
    public float[] storeRM(float[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(float[] src) { return loadRM(src, 0); }

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
    public FloatBuffer storeRM(FloatBuffer buf) {
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
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf) {
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
    public FloatBuffer storeRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(FloatBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 6);
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 24);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(ByteBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 24);
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
    public Float3x2 storeRMUnsafe(long address) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMUnsafe(long address) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(offset, src);
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}, starting at the given offset.
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
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM(double[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 6);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
    public ByteBuffer storeRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf);
        buf.position(pos + 48);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x2 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}.
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
    public MemorySegment storeRMDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, starting at the given offset.
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, starting at the given offset.
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(offset, src);
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
    public float[] storeCM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        float _c0 = src[offset];
        float _c2 = src[offset + 1];
        float _c4 = src[offset + 2];
        float _c1 = src[_p1];
        float _c3 = src[_p1 + 1];
        float _c5 = src[_p1 + 2];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
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
    public FloatBuffer storeCM(FloatBuffer buf, int stride) {
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
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf, int stride) {
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
    public FloatBuffer storeCMRelative(FloatBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
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
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
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
    public Float3x2 storeCMUnsafe(long address, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMUnsafe(long address, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(offset, src, stride);
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
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
    public double[] storeCM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        return dest;
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        float _c0 = (float) src[offset];
        float _c2 = (float) src[offset + 1];
        float _c4 = (float) src[offset + 2];
        float _c1 = (float) src[_p1];
        float _c3 = (float) src[_p1 + 1];
        float _c5 = (float) src[_p1 + 2];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
    public DoubleBuffer storeCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
    public DoubleBuffer storeCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position (the position is not modified), with
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
    public ByteBuffer storeCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at the given absolute index (the position is not used or
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
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position and advancing the position accordingly,
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
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified), with
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly,
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Float3x2 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
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
    public MemorySegment storeCMDouble(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, starting at the given offset, with {@code stride} elements between
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, starting at the given offset, with {@code stride} elements
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(offset, src, stride);
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
    public float[] storeRM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[_p1];
        float _c3 = src[_p1 + 1];
        float _c4 = src[_p2];
        float _c5 = src[_p2 + 1];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
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
    public FloatBuffer storeRM(FloatBuffer buf, int stride) {
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
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf, int stride) {
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
    public FloatBuffer storeRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
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
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
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
    public Float3x2 storeRMUnsafe(long address, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMUnsafe(long address, int stride) {
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(offset, src, stride);
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
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
    public double[] storeRM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[_p1];
        float _c3 = (float) src[_p1 + 1];
        float _c4 = (float) src[_p2];
        float _c5 = (float) src[_p2 + 1];
        return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
    public DoubleBuffer storeRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
    public DoubleBuffer storeRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
    public ByteBuffer storeRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x2 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    public Float3x2 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, with {@code stride} elements between the starts of consecutive rows.
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
    public MemorySegment storeRMDouble(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, starting at the given offset, with {@code stride} elements between the
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, with {@code stride} elements between the starts of consecutive rows.
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, starting at the given offset, with {@code stride} elements between the
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(offset, src, stride);
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
    public float[] store(float[] dest) { return storeCM(dest, 0); }

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
    public float[] store(float[] dest, int offset) { return storeCM(dest, offset); }

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
    public FloatBuffer store(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public FloatBuffer store(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public FloatBuffer storeRelative(FloatBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset.
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
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
    public Float3x2 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public float[] store(float[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
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
    public double[] store(double[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

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
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public FloatBuffer storeRelative(FloatBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
    public DoubleBuffer storeRelative(DoubleBuffer dest, int stride) { return storeCMRelative(dest, stride); }

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
    public Float3x2 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(float[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x2 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

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
     * @return a new {@code Float3x2} holding the loaded elements
     */
    public static Float3x2 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    private abstract static class Float3x2SegOps {
        public abstract MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest);
        public abstract Float3x2 loadCM(long offset, MemorySegment src);
        public abstract MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest);
        public abstract Float3x2 loadCMDouble(long offset, MemorySegment src);
        public abstract MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest);
        public abstract Float3x2 loadRM(long offset, MemorySegment src);
        public abstract MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest);
        public abstract Float3x2 loadRMDouble(long offset, MemorySegment src);
        public abstract MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest, int stride);
        public abstract Float3x2 loadCM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest, int stride);
        public abstract Float3x2 loadCMDouble(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest, int stride);
        public abstract Float3x2 loadRM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest, int stride);
        public abstract Float3x2 loadRMDouble(long offset, MemorySegment src, int stride);
    }

    private static final class Float3x2SegOpsMS extends Float3x2SegOps {
        public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21);
            return dest;
        }

        public Float3x2 loadCM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
            return dest;
        }

        public Float3x2 loadCMDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m21);
            return dest;
        }

        public Float3x2 loadRM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m21);
            return dest;
        }

        public Float3x2 loadRMDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21);
            return dest;
        }

        public Float3x2 loadCM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
            return dest;
        }

        public Float3x2 loadCMDouble(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21);
            return dest;
        }

        public Float3x2 loadRM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
            return dest;
        }

        public Float3x2 loadRMDouble(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class Float3x2SegOpsUnsafe extends Float3x2SegOps {
        private static final Float3x2SegOpsMS MS = new Float3x2SegOpsMS();

        public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m01);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m21);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Float3x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m01);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m21);
            return dest;
        }

        public Float3x2 loadCM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4L);
            float _c4 = _U.getFloat(address + 8L);
            float _c1 = _U.getFloat(address + 12L);
            float _c3 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4L);
            float _c4 = _U.getFloat(bytes, address + 8L);
            float _c1 = _U.getFloat(bytes, address + 12L);
            float _c3 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
            if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m21);
            return dest;
        }

        private static MemorySegment storeCMDoubleOnHeap(Float3x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m21);
            return dest;
        }

        public Float3x2 loadCMDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8L);
            float _c4 = (float) _U.getDouble(address + 16L);
            float _c1 = (float) _U.getDouble(address + 24L);
            float _c3 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMDoubleOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMDouble(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8L);
            float _c4 = (float) _U.getDouble(bytes, address + 16L);
            float _c1 = (float) _U.getDouble(bytes, address + 24L);
            float _c3 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m10);
            _U.putFloat(address + 12L, self.m11);
            _U.putFloat(address + 16L, self.m20);
            _U.putFloat(address + 20L, self.m21);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Float3x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m10);
            _U.putFloat(bytes, address + 12L, self.m11);
            _U.putFloat(bytes, address + 16L, self.m20);
            _U.putFloat(bytes, address + 20L, self.m21);
            return dest;
        }

        public Float3x2 loadRM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
            if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            return dest;
        }

        private static MemorySegment storeRMDoubleOnHeap(Float3x2 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            return dest;
        }

        public Float3x2 loadRMDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMDoubleOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMDouble(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Float3x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m10);
            _U.putFloat(bytes, address + 8, self.m20);
            _U.putFloat(bytes, _p1, self.m01);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m21);
            return dest;
        }

        public Float3x2 loadCM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4);
            float _c4 = _U.getFloat(address + 8);
            float _c1 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4);
            float _c4 = _U.getFloat(bytes, address + 8);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            return dest;
        }

        private static MemorySegment storeCMDoubleOnHeap(Float3x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            return dest;
        }

        public Float3x2 loadCMDouble(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMDoubleOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8);
            float _c4 = (float) _U.getDouble(address + 16);
            float _c1 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMDoubleOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMDouble(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8);
            float _c4 = (float) _U.getDouble(bytes, address + 16);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Float3x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m01);
            _U.putFloat(bytes, _p1, self.m10);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p2, self.m20);
            _U.putFloat(bytes, _p2 + 4, self.m21);
            return dest;
        }

        public Float3x2 loadRM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c4 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c4 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            return dest;
        }

        private static MemorySegment storeRMDoubleOnHeap(Float3x2 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            return dest;
        }

        public Float3x2 loadRMDouble(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMDoubleOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c4 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMDoubleOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMDouble(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c4 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class Float3x2BbOps {
        public abstract FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf);
        public abstract Float3x2 loadCMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf);
        public abstract Float3x2 loadCMAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf);
        public abstract Float3x2 loadCMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf);
        public abstract Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf);
        public abstract Float3x2 loadRMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf);
        public abstract Float3x2 loadRMAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf);
        public abstract Float3x2 loadRMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf);
        public abstract Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride);
        public abstract Float3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride);
        public abstract Float3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Float3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride);
        public abstract Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride);
        public abstract Float3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride);
        public abstract Float3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride);
        public abstract Float3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride);
        public abstract Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    }

    private static final class Float3x2BbOpsApi extends Float3x2BbOps {
        public FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m01);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c2 = buf.get(index + 1);
            float _c4 = buf.get(index + 2);
            float _c1 = buf.get(index + 3);
            float _c3 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m10);
            buf.putFloat(index + 8, self.m20);
            buf.putFloat(index + 12, self.m01);
            buf.putFloat(index + 16, self.m11);
            buf.putFloat(index + 20, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c2 = buf.getFloat(index + 4);
            float _c4 = buf.getFloat(index + 8);
            float _c1 = buf.getFloat(index + 12);
            float _c3 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m01);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c2 = (float) buf.get(index + 1);
            float _c4 = (float) buf.get(index + 2);
            float _c1 = (float) buf.get(index + 3);
            float _c3 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(index + 24, self.m01);
            buf.putDouble(index + 32, self.m11);
            buf.putDouble(index + 40, self.m21);
            return buf;
        }

        public Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c2 = (float) buf.getDouble(index + 8);
            float _c4 = (float) buf.getDouble(index + 16);
            float _c1 = (float) buf.getDouble(index + 24);
            float _c3 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m10);
            buf.put(index + 3, self.m11);
            buf.put(index + 4, self.m20);
            buf.put(index + 5, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            float _c4 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m01);
            buf.putFloat(index + 8, self.m10);
            buf.putFloat(index + 12, self.m11);
            buf.putFloat(index + 16, self.m20);
            buf.putFloat(index + 20, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            float _c4 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m10);
            buf.put(index + 3, self.m11);
            buf.put(index + 4, self.m20);
            buf.put(index + 5, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            float _c4 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, self.m10);
            buf.putDouble(index + 24, self.m11);
            buf.putDouble(index + 32, self.m20);
            buf.putDouble(index + 40, self.m21);
            return buf;
        }

        public Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            float _c4 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            float _c0 = buf.get(index);
            float _c2 = buf.get(index + 1);
            float _c4 = buf.get(index + 2);
            float _c1 = buf.get(_p1);
            float _c3 = buf.get(_p1 + 1);
            float _c5 = buf.get(_p1 + 2);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m10);
            buf.putFloat(index + 8, self.m20);
            buf.putFloat(_p1, self.m01);
            buf.putFloat(_p1 + 4, self.m11);
            buf.putFloat(_p1 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            float _c0 = buf.getFloat(index);
            float _c2 = buf.getFloat(index + 4);
            float _c4 = buf.getFloat(index + 8);
            float _c1 = buf.getFloat(_p1);
            float _c3 = buf.getFloat(_p1 + 4);
            float _c5 = buf.getFloat(_p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            float _c0 = (float) buf.get(index);
            float _c2 = (float) buf.get(index + 1);
            float _c4 = (float) buf.get(index + 2);
            float _c1 = (float) buf.get(_p1);
            float _c3 = (float) buf.get(_p1 + 1);
            float _c5 = (float) buf.get(_p1 + 2);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(_p1, self.m01);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p1 + 16, self.m21);
            return buf;
        }

        public Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            float _c0 = (float) buf.getDouble(index);
            float _c2 = (float) buf.getDouble(index + 8);
            float _c4 = (float) buf.getDouble(index + 16);
            float _c1 = (float) buf.getDouble(_p1);
            float _c3 = (float) buf.getDouble(_p1 + 8);
            float _c5 = (float) buf.getDouble(_p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p2, self.m20);
            buf.put(_p2 + 1, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(_p1);
            float _c3 = buf.get(_p1 + 1);
            float _c4 = buf.get(_p2);
            float _c5 = buf.get(_p2 + 1);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m01);
            buf.putFloat(_p1, self.m10);
            buf.putFloat(_p1 + 4, self.m11);
            buf.putFloat(_p2, self.m20);
            buf.putFloat(_p2 + 4, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(_p1);
            float _c3 = buf.getFloat(_p1 + 4);
            float _c4 = buf.getFloat(_p2);
            float _c5 = buf.getFloat(_p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p2, self.m20);
            buf.put(_p2 + 1, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(_p1);
            float _c3 = (float) buf.get(_p1 + 1);
            float _c4 = (float) buf.get(_p2);
            float _c5 = (float) buf.get(_p2 + 1);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(_p1, self.m10);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p2, self.m20);
            buf.putDouble(_p2 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(_p1);
            float _c3 = (float) buf.getDouble(_p1 + 8);
            float _c4 = (float) buf.getDouble(_p2);
            float _c5 = (float) buf.getDouble(_p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class Float3x2BbOpsUnsafe extends Float3x2BbOps {
        private static final Float3x2BbOpsApi API = new Float3x2BbOpsApi();

        public FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m01);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4L);
            float _c4 = _U.getFloat(address + 8L);
            float _c1 = _U.getFloat(address + 12L);
            float _c3 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m01);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m21);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Float3x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m01);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4L);
            float _c4 = _U.getFloat(address + 8L);
            float _c1 = _U.getFloat(address + 12L);
            float _c3 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4L);
            float _c4 = _U.getFloat(bytes, address + 8L);
            float _c1 = _U.getFloat(bytes, address + 12L);
            float _c3 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8L);
            float _c4 = (float) _U.getDouble(address + 16L);
            float _c1 = (float) _U.getDouble(address + 24L);
            float _c3 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m21);
            return buf;
        }

        private static ByteBuffer storeCMDoubleAbsoluteElse(Float3x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m21);
            return buf;
        }

        public Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8L);
            float _c4 = (float) _U.getDouble(address + 16L);
            float _c1 = (float) _U.getDouble(address + 24L);
            float _c3 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8L);
            float _c4 = (float) _U.getDouble(bytes, address + 16L);
            float _c1 = (float) _U.getDouble(bytes, address + 24L);
            float _c3 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m10);
            _U.putFloat(address + 12L, self.m11);
            _U.putFloat(address + 16L, self.m20);
            _U.putFloat(address + 20L, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m10);
            _U.putFloat(address + 12L, self.m11);
            _U.putFloat(address + 16L, self.m20);
            _U.putFloat(address + 20L, self.m21);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Float3x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m10);
            _U.putFloat(bytes, address + 12L, self.m11);
            _U.putFloat(bytes, address + 16L, self.m20);
            _U.putFloat(bytes, address + 20L, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            return buf;
        }

        private static ByteBuffer storeRMDoubleAbsoluteElse(Float3x2 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            return buf;
        }

        public Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeCMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4);
            float _c4 = _U.getFloat(address + 8);
            float _c1 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Float3x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m10);
            _U.putFloat(bytes, address + 8, self.m20);
            _U.putFloat(bytes, _p1, self.m01);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4);
            float _c4 = _U.getFloat(address + 8);
            float _c1 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4);
            float _c4 = _U.getFloat(bytes, address + 8);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeCMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            return buf;
        }

        public Float3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8);
            float _c4 = (float) _U.getDouble(address + 16);
            float _c1 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            return buf;
        }

        private static ByteBuffer storeCMDoubleAbsoluteElse(Float3x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            return buf;
        }

        public Float3x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8);
            float _c4 = (float) _U.getDouble(address + 16);
            float _c1 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMDoubleAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8);
            float _c4 = (float) _U.getDouble(bytes, address + 16);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatBuffer storeRMAbsolute(Float3x2 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c4 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Float3x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m01);
            _U.putFloat(bytes, _p1, self.m10);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p2, self.m20);
            _U.putFloat(bytes, _p2 + 4, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c4 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c4 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeRMAbsolute(Float3x2 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c4 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float3x2 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            return buf;
        }

        private static ByteBuffer storeRMDoubleAbsoluteElse(Float3x2 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            return buf;
        }

        public Float3x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c4 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static Float3x2 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMDoubleAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c4 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class Float3x2RawOps {
        public abstract Float3x2 storeCMUnsafe(Float3x2 self, long address);
        public abstract Float3x2 loadCMUnsafe(long address);
        public abstract Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address);
        public abstract Float3x2 loadCMDoubleUnsafe(long address);
        public abstract Float3x2 storeRMUnsafe(Float3x2 self, long address);
        public abstract Float3x2 loadRMUnsafe(long address);
        public abstract Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address);
        public abstract Float3x2 loadRMDoubleUnsafe(long address);
        public abstract Float3x2 storeCMUnsafe(Float3x2 self, long address, int stride);
        public abstract Float3x2 loadCMUnsafe(long address, int stride);
        public abstract Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address, int stride);
        public abstract Float3x2 loadCMDoubleUnsafe(long address, int stride);
        public abstract Float3x2 storeRMUnsafe(Float3x2 self, long address, int stride);
        public abstract Float3x2 loadRMUnsafe(long address, int stride);
        public abstract Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address, int stride);
        public abstract Float3x2 loadRMDoubleUnsafe(long address, int stride);
    }

    private static final class Float3x2RawOpsApi extends Float3x2RawOps {
        public Float3x2 storeCMUnsafe(Float3x2 self, long address) {
            self.storeCM(0L, virtualMemory().asSlice(address, 24L));
            return self;
        }

        public Float3x2 loadCMUnsafe(long address) {
            return Float3x2.loadCM(0L, virtualMemory().asSlice(address, 24L));
        }

        public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address) {
            self.storeCMDouble(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public Float3x2 loadCMDoubleUnsafe(long address) {
            return Float3x2.loadCMDouble(0L, virtualMemory().asSlice(address, 48L));
        }

        public Float3x2 storeRMUnsafe(Float3x2 self, long address) {
            self.storeRM(0L, virtualMemory().asSlice(address, 24L));
            return self;
        }

        public Float3x2 loadRMUnsafe(long address) {
            return Float3x2.loadRM(0L, virtualMemory().asSlice(address, 24L));
        }

        public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address) {
            self.storeRMDouble(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public Float3x2 loadRMDoubleUnsafe(long address) {
            return Float3x2.loadRMDouble(0L, virtualMemory().asSlice(address, 48L));
        }

        public Float3x2 storeCMUnsafe(Float3x2 self, long address, int stride) {
            self.storeCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
            return self;
        }

        public Float3x2 loadCMUnsafe(long address, int stride) {
            return Float3x2.loadCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        }

        public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address, int stride) {
            self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
            return self;
        }

        public Float3x2 loadCMDoubleUnsafe(long address, int stride) {
            return Float3x2.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        }

        public Float3x2 storeRMUnsafe(Float3x2 self, long address, int stride) {
            self.storeRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
            return self;
        }

        public Float3x2 loadRMUnsafe(long address, int stride) {
            return Float3x2.loadRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        }

        public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address, int stride) {
            self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
            return self;
        }

        public Float3x2 loadRMDoubleUnsafe(long address, int stride) {
            return Float3x2.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        }
    }

    private static final class Float3x2RawOpsUnsafe extends Float3x2RawOps {
        public Float3x2 storeCMUnsafe(Float3x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m01);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m21);
            return self;
        }

        public Float3x2 storeCMHeap(Float3x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m01);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m21);
            return self;
        }

        public Float3x2 loadCMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4L);
            float _c4 = _U.getFloat(address + 8L);
            float _c1 = _U.getFloat(address + 12L);
            float _c3 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadCMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4L);
            float _c4 = _U.getFloat(bytes, address + 8L);
            float _c1 = _U.getFloat(bytes, address + 12L);
            float _c3 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m01);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m21);
            return self;
        }

        public Float3x2 storeCMDoubleHeap(Float3x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m01);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m21);
            return self;
        }

        public Float3x2 loadCMDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8L);
            float _c4 = (float) _U.getDouble(address + 16L);
            float _c1 = (float) _U.getDouble(address + 24L);
            float _c3 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadCMDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8L);
            float _c4 = (float) _U.getDouble(bytes, address + 16L);
            float _c1 = (float) _U.getDouble(bytes, address + 24L);
            float _c3 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeRMUnsafe(Float3x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m10);
            _U.putFloat(address + 12L, self.m11);
            _U.putFloat(address + 16L, self.m20);
            _U.putFloat(address + 20L, self.m21);
            return self;
        }

        public Float3x2 storeRMHeap(Float3x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m10);
            _U.putFloat(bytes, address + 12L, self.m11);
            _U.putFloat(bytes, address + 16L, self.m20);
            _U.putFloat(bytes, address + 20L, self.m21);
            return self;
        }

        public Float3x2 loadRMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadRMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m10);
            _U.putDouble(address + 24L, self.m11);
            _U.putDouble(address + 32L, self.m20);
            _U.putDouble(address + 40L, self.m21);
            return self;
        }

        public Float3x2 storeRMDoubleHeap(Float3x2 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m10);
            _U.putDouble(bytes, address + 24L, self.m11);
            _U.putDouble(bytes, address + 32L, self.m20);
            _U.putDouble(bytes, address + 40L, self.m21);
            return self;
        }

        public Float3x2 loadRMDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadRMDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeCMUnsafe(Float3x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            return self;
        }

        public Float3x2 storeCMHeap(Float3x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m10);
            _U.putFloat(bytes, address + 8, self.m20);
            _U.putFloat(bytes, _p1, self.m01);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m21);
            return self;
        }

        public Float3x2 loadCMUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c2 = _U.getFloat(address + 4);
            float _c4 = _U.getFloat(address + 8);
            float _c1 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadCMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c2 = _U.getFloat(bytes, address + 4);
            float _c4 = _U.getFloat(bytes, address + 8);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            return self;
        }

        public Float3x2 storeCMDoubleHeap(Float3x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            return self;
        }

        public Float3x2 loadCMDoubleUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c2 = (float) _U.getDouble(address + 8);
            float _c4 = (float) _U.getDouble(address + 16);
            float _c1 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadCMDoubleHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c2 = (float) _U.getDouble(bytes, address + 8);
            float _c4 = (float) _U.getDouble(bytes, address + 16);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeRMUnsafe(Float3x2 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            return self;
        }

        public Float3x2 storeRMHeap(Float3x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m01);
            _U.putFloat(bytes, _p1, self.m10);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p2, self.m20);
            _U.putFloat(bytes, _p2 + 4, self.m21);
            return self;
        }

        public Float3x2 loadRMUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(_p1);
            float _c3 = _U.getFloat(_p1 + 4);
            float _c4 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadRMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, _p1);
            float _c3 = _U.getFloat(bytes, _p1 + 4);
            float _c4 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            return self;
        }

        public Float3x2 storeRMDoubleHeap(Float3x2 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            return self;
        }

        public Float3x2 loadRMDoubleUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(_p1);
            float _c3 = (float) _U.getDouble(_p1 + 8);
            float _c4 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public Float3x2 loadRMDoubleHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, _p1);
            float _c3 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c4 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            return new Float3x2(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }
}
