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
 * Immutable 4x3 matrix of single-precision {@code float} components, declared as a value record.
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
 * @param m02 the element in row 0, column 2
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param m12 the element in row 1, column 2
 * @param m20 the element in row 2, column 0
 * @param m21 the element in row 2, column 1
 * @param m22 the element in row 2, column 2
 * @param m30 the element in row 3, column 0
 * @param m31 the element in row 3, column 1
 * @param m32 the element in row 3, column 2
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Float4x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 48;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 4;

    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 3;

    /** The zero matrix (all components 0). */
    public static final Float4x3 ZERO = new Float4x3(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Float4x3 IDENTITY = new Float4x3();

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     * @param m32 the element in row 3, column 2
     */
    public Float4x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: the method reads no input.
     */
    public Float4x3() {
        this(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0);
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public Float4x3(Float4 c0, Float4 c1, Float4 c2) {
        this(c0.x(), c1.x(), c2.x(), c0.y(), c1.y(), c2.y(), c0.z(), c1.z(), c2.z(), c0.w(), c1.w(), c2.w());
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
    public static Float4x3[] newArray(int length) {
        return (Float4x3[]) org.joml2.internal.value.FlatArrays.newArray(Float4x3.class, length, ZERO);
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
    public static Float4x3[] newArray(int length, Float4x3 init) {
        return (Float4x3[]) org.joml2.internal.value.FlatArrays.newArray(Float4x3.class, length, init);
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m00() { return m00; }

    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m01() { return m01; }

    /** {@return the element in row 0, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m02() { return m02; }

    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m10() { return m10; }

    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m11() { return m11; }

    /** {@return the element in row 1, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m12() { return m12; }

    /** {@return the element in row 2, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m20() { return m20; }

    /** {@return the element in row 2, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m21() { return m21; }

    /** {@return the element in row 2, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m22() { return m22; }

    /** {@return the element in row 3, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m30() { return m30; }

    /** {@return the element in row 3, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m31() { return m31; }

    /** {@return the element in row 3, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m32() { return m32; }

    /**
     * Get the column at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Float4 getColumn(int col) {
        return switch (col) {
            case 0 -> new Float4(this.m00, this.m10, this.m20, this.m30);
            case 1 -> new Float4(this.m01, this.m11, this.m21, this.m31);
            case 2 -> new Float4(this.m02, this.m12, this.m22, this.m32);
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
    public Float3 getRow(int row) {
        return switch (row) {
            case 0 -> new Float3(this.m00, this.m01, this.m02);
            case 1 -> new Float3(this.m10, this.m11, this.m12);
            case 2 -> new Float3(this.m20, this.m21, this.m22);
            case 3 -> new Float3(this.m30, this.m31, this.m32);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.m00, this.m00, java.lang.Math.fma(this.m01, this.m01, this.m02 * this.m02)) + java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m11, this.m11, this.m12 * this.m12)) + (java.lang.Math.fma(this.m20, this.m20, java.lang.Math.fma(this.m21, this.m21, this.m22 * this.m22)) + java.lang.Math.fma(this.m30, this.m30, java.lang.Math.fma(this.m31, this.m31, this.m32 * this.m32))));
        } else {
            return (float) java.lang.Math.sqrt(((this.m00) * (this.m00) + (((this.m01) * (this.m01) + (this.m02 * this.m02)))) + ((this.m10) * (this.m10) + (((this.m11) * (this.m11) + (this.m12 * this.m12)))) + (((this.m20) * (this.m20) + (((this.m21) * (this.m21) + (this.m22 * this.m22)))) + ((this.m30) * (this.m30) + (((this.m31) * (this.m31) + (this.m32 * this.m32))))));
        }
    }

    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x4 transpose() {
        return new Float3x4(this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31, this.m02, this.m12, this.m22, this.m32, Joml.BIT_AFFINE);
    }

    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Float4x3 add(Float4x3 other) {
        return new Float4x3(other.m00 + this.m00, other.m01 + this.m01, other.m02 + this.m02, other.m10 + this.m10, other.m11 + this.m11, other.m12 + this.m12, other.m20 + this.m20, other.m21 + this.m21, other.m22 + this.m22, other.m30 + this.m30, other.m31 + this.m31, other.m32 + this.m32);
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) to this matrix,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float4x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        return new Float4x3(m00 + this.m00, m01 + this.m01, m02 + this.m02, m10 + this.m10, m11 + this.m11, m12 + this.m12, m20 + this.m20, m21 + this.m21, m22 + this.m22, m30 + this.m30, m31 + this.m31, m32 + this.m32);
    }

    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Float4x3 mul(float scalar) {
        return new Float4x3(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m10, scalar * this.m11, scalar * this.m12, scalar * this.m20, scalar * this.m21, scalar * this.m22, scalar * this.m30, scalar * this.m31, scalar * this.m32);
    }

    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float4x3 negate() {
        return new Float4x3(-this.m00, -this.m01, -this.m02, -this.m10, -this.m11, -this.m12, -this.m20, -this.m21, -this.m22, -this.m30, -this.m31, -this.m32);
    }

    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Float4x3 sub(Float4x3 other) {
        return new Float4x3(this.m00 - other.m00, this.m01 - other.m01, this.m02 - other.m02, this.m10 - other.m10, this.m11 - other.m11, this.m12 - other.m12, this.m20 - other.m20, this.m21 - other.m21, this.m22 - other.m22, this.m30 - other.m30, this.m31 - other.m31, this.m32 - other.m32);
    }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) from this
     * matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float4x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        return new Float4x3(this.m00 - m00, this.m01 - m01, this.m02 - m02, this.m10 - m10, this.m11 - m11, this.m12 - m12, this.m20 - m20, this.m21 - m21, this.m22 - m22, this.m30 - m30, this.m31 - m31, this.m32 - m32);
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
    public Float4x3 set(Float4x3 v) {
        return new Float4x3(v.m00, v.m01, v.m02, v.m10, v.m11, v.m12, v.m20, v.m21, v.m22, v.m30, v.m31, v.m32);
    }

    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float4x3 set(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4x3} holding the result
     */
    public Double4x3 toDouble() {
        return new Double4x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32);
    }

    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Float4x3 makeIdentity() {
        return new Float4x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
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
    public Float4x3 lerp(Float4x3 other, float t) {
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(other, t);
        return new Float4x3(java.lang.Math.fma(t, other.m00 - this.m00, this.m00), java.lang.Math.fma(t, other.m01 - this.m01, this.m01), java.lang.Math.fma(t, other.m02 - this.m02, this.m02), java.lang.Math.fma(t, other.m10 - this.m10, this.m10), java.lang.Math.fma(t, other.m11 - this.m11, this.m11), java.lang.Math.fma(t, other.m12 - this.m12, this.m12), java.lang.Math.fma(t, other.m20 - this.m20, this.m20), java.lang.Math.fma(t, other.m21 - this.m21, this.m21), java.lang.Math.fma(t, other.m22 - this.m22, this.m22), java.lang.Math.fma(t, other.m30 - this.m30, this.m30), java.lang.Math.fma(t, other.m31 - this.m31, this.m31), java.lang.Math.fma(t, other.m32 - this.m32, this.m32));
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 lerp_mulAdd(Float4x3 other, float t) {
        return new Float4x3(((t) * (other.m00 - this.m00) + (this.m00)), ((t) * (other.m01 - this.m01) + (this.m01)), ((t) * (other.m02 - this.m02) + (this.m02)), ((t) * (other.m10 - this.m10) + (this.m10)), ((t) * (other.m11 - this.m11) + (this.m11)), ((t) * (other.m12 - this.m12) + (this.m12)), ((t) * (other.m20 - this.m20) + (this.m20)), ((t) * (other.m21 - this.m21) + (this.m21)), ((t) * (other.m22 - this.m22) + (this.m22)), ((t) * (other.m30 - this.m30) + (this.m30)), ((t) * (other.m31 - this.m31) + (this.m31)), ((t) * (other.m32 - this.m32) + (this.m32)));
    }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}, {@code m30},
     * {@code m31}, {@code m32}) using the interpolation factor {@code t}, returning the result as a
     * value.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}, {@code m20},
     * {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Float4x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t) {
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t);
        return new Float4x3(java.lang.Math.fma(t, m00 - this.m00, this.m00), java.lang.Math.fma(t, m01 - this.m01, this.m01), java.lang.Math.fma(t, m02 - this.m02, this.m02), java.lang.Math.fma(t, m10 - this.m10, this.m10), java.lang.Math.fma(t, m11 - this.m11, this.m11), java.lang.Math.fma(t, m12 - this.m12, this.m12), java.lang.Math.fma(t, m20 - this.m20, this.m20), java.lang.Math.fma(t, m21 - this.m21, this.m21), java.lang.Math.fma(t, m22 - this.m22, this.m22), java.lang.Math.fma(t, m30 - this.m30, this.m30), java.lang.Math.fma(t, m31 - this.m31, this.m31), java.lang.Math.fma(t, m32 - this.m32, this.m32));
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 lerp_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t) {
        return new Float4x3(((t) * (m00 - this.m00) + (this.m00)), ((t) * (m01 - this.m01) + (this.m01)), ((t) * (m02 - this.m02) + (this.m02)), ((t) * (m10 - this.m10) + (this.m10)), ((t) * (m11 - this.m11) + (this.m11)), ((t) * (m12 - this.m12) + (this.m12)), ((t) * (m20 - this.m20) + (this.m20)), ((t) * (m21 - this.m21) + (this.m21)), ((t) * (m22 - this.m22) + (this.m22)), ((t) * (m30 - this.m30) + (this.m30)), ((t) * (m31 - this.m31) + (this.m31)), ((t) * (m32 - this.m32) + (this.m32)));
    }

    /** Private per-column body of {@code mul}, stored into {@code _m}; reached only through it. */
    private void mul_s56015aee_c0_fma(Float3x3 right, float[] _m) {
        float _right_m20 = right.m20();
        float _right_m00 = right.m00();
        float _right_m10 = right.m10();
        _m[0] = java.lang.Math.fma(_right_m20, this.m02, java.lang.Math.fma(_right_m00, this.m00, _right_m10 * this.m01));
        _m[3] = java.lang.Math.fma(_right_m20, this.m12, java.lang.Math.fma(_right_m00, this.m10, _right_m10 * this.m11));
        _m[6] = java.lang.Math.fma(_right_m20, this.m22, java.lang.Math.fma(_right_m00, this.m20, _right_m10 * this.m21));
        _m[9] = java.lang.Math.fma(_right_m20, this.m32, java.lang.Math.fma(_right_m00, this.m30, _right_m10 * this.m31));
    }

    /** Private per-column body of {@code mul}, stored into {@code _m}; reached only through it. */
    private void mul_s56015aee_c0_mulAdd(Float3x3 right, float[] _m) {
        float _right_m20 = right.m20();
        float _right_m00 = right.m00();
        float _right_m10 = right.m10();
        _m[0] = ((_right_m20) * (this.m02) + (((_right_m00) * (this.m00) + (_right_m10 * this.m01))));
        _m[3] = ((_right_m20) * (this.m12) + (((_right_m00) * (this.m10) + (_right_m10 * this.m11))));
        _m[6] = ((_right_m20) * (this.m22) + (((_right_m00) * (this.m20) + (_right_m10 * this.m21))));
        _m[9] = ((_right_m20) * (this.m32) + (((_right_m00) * (this.m30) + (_right_m10 * this.m31))));
    }

    /** Private per-column body of {@code mul}, stored into {@code _m}; reached only through it. */
    private void mul_s56015aee_c1_fma(Float3x3 right, float[] _m) {
        float _right_m21 = right.m21();
        float _right_m01 = right.m01();
        float _right_m11 = right.m11();
        _m[1] = java.lang.Math.fma(_right_m21, this.m02, java.lang.Math.fma(_right_m01, this.m00, _right_m11 * this.m01));
        _m[4] = java.lang.Math.fma(_right_m21, this.m12, java.lang.Math.fma(_right_m01, this.m10, _right_m11 * this.m11));
        _m[7] = java.lang.Math.fma(_right_m21, this.m22, java.lang.Math.fma(_right_m01, this.m20, _right_m11 * this.m21));
        _m[10] = java.lang.Math.fma(_right_m21, this.m32, java.lang.Math.fma(_right_m01, this.m30, _right_m11 * this.m31));
    }

    /** Private per-column body of {@code mul}, stored into {@code _m}; reached only through it. */
    private void mul_s56015aee_c1_mulAdd(Float3x3 right, float[] _m) {
        float _right_m21 = right.m21();
        float _right_m01 = right.m01();
        float _right_m11 = right.m11();
        _m[1] = ((_right_m21) * (this.m02) + (((_right_m01) * (this.m00) + (_right_m11 * this.m01))));
        _m[4] = ((_right_m21) * (this.m12) + (((_right_m01) * (this.m10) + (_right_m11 * this.m11))));
        _m[7] = ((_right_m21) * (this.m22) + (((_right_m01) * (this.m20) + (_right_m11 * this.m21))));
        _m[10] = ((_right_m21) * (this.m32) + (((_right_m01) * (this.m30) + (_right_m11 * this.m31))));
    }

    /** Private per-column body of {@code mul}, stored into {@code _m}; reached only through it. */
    private void mul_s56015aee_c2_fma(Float3x3 right, float[] _m) {
        float _right_m22 = right.m22();
        float _right_m02 = right.m02();
        float _right_m12 = right.m12();
        _m[2] = java.lang.Math.fma(_right_m22, this.m02, java.lang.Math.fma(_right_m02, this.m00, _right_m12 * this.m01));
        _m[5] = java.lang.Math.fma(_right_m22, this.m12, java.lang.Math.fma(_right_m02, this.m10, _right_m12 * this.m11));
        _m[8] = java.lang.Math.fma(_right_m22, this.m22, java.lang.Math.fma(_right_m02, this.m20, _right_m12 * this.m21));
        _m[11] = java.lang.Math.fma(_right_m22, this.m32, java.lang.Math.fma(_right_m02, this.m30, _right_m12 * this.m31));
    }

    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 4x3 * 3x3 matrix product: the inner dimensions meet, no operand is
     * extended.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return the resulting matrix
     */
    public Float4x3 mul(Float3x3 right) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] _m = new float[12];
            mul_s56015aee_c0_fma(right, _m);
            mul_s56015aee_c1_fma(right, _m);
            mul_s56015aee_c2_fma(right, _m);
            return new Float4x3(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11]);
        } else {
            float[] _m = new float[12];
            mul_s56015aee_c0_mulAdd(right, _m);
            mul_s56015aee_c1_mulAdd(right, _m);
            {
                float _right_m22 = right.m22();
                float _right_m02 = right.m02();
                float _right_m12 = right.m12();
                _m[2] = ((_right_m22) * (this.m02) + (((_right_m02) * (this.m00) + (_right_m12 * this.m01))));
                _m[5] = ((_right_m22) * (this.m12) + (((_right_m02) * (this.m10) + (_right_m12 * this.m11))));
                _m[8] = ((_right_m22) * (this.m22) + (((_right_m02) * (this.m20) + (_right_m12 * this.m21))));
                _m[11] = ((_right_m22) * (this.m32) + (((_right_m02) * (this.m30) + (_right_m12 * this.m31))));
            }
            return new Float4x3(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11]);
        }
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c0_fma(Float4x4 other, float[] _m) {
        _m[0] = java.lang.Math.fma(other.m03(), this.m30, java.lang.Math.fma(other.m02(), this.m20, java.lang.Math.fma(other.m00(), this.m00, other.m01() * this.m10)));
        _m[3] = java.lang.Math.fma(other.m13(), this.m30, java.lang.Math.fma(other.m12(), this.m20, java.lang.Math.fma(other.m10(), this.m00, other.m11() * this.m10)));
        _m[6] = java.lang.Math.fma(other.m23(), this.m30, java.lang.Math.fma(other.m22(), this.m20, java.lang.Math.fma(other.m20(), this.m00, other.m21() * this.m10)));
        _m[9] = java.lang.Math.fma(other.m33(), this.m30, java.lang.Math.fma(other.m32(), this.m20, java.lang.Math.fma(other.m30(), this.m00, other.m31() * this.m10)));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c0_mulAdd(Float4x4 other, float[] _m) {
        _m[0] = ((other.m03()) * (this.m30) + (((other.m02()) * (this.m20) + (((other.m00()) * (this.m00) + (other.m01() * this.m10))))));
        _m[3] = ((other.m13()) * (this.m30) + (((other.m12()) * (this.m20) + (((other.m10()) * (this.m00) + (other.m11() * this.m10))))));
        _m[6] = ((other.m23()) * (this.m30) + (((other.m22()) * (this.m20) + (((other.m20()) * (this.m00) + (other.m21() * this.m10))))));
        _m[9] = ((other.m33()) * (this.m30) + (((other.m32()) * (this.m20) + (((other.m30()) * (this.m00) + (other.m31() * this.m10))))));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c1_fma(Float4x4 other, float[] _m) {
        _m[1] = java.lang.Math.fma(other.m03(), this.m31, java.lang.Math.fma(other.m02(), this.m21, java.lang.Math.fma(other.m00(), this.m01, other.m01() * this.m11)));
        _m[4] = java.lang.Math.fma(other.m13(), this.m31, java.lang.Math.fma(other.m12(), this.m21, java.lang.Math.fma(other.m10(), this.m01, other.m11() * this.m11)));
        _m[7] = java.lang.Math.fma(other.m23(), this.m31, java.lang.Math.fma(other.m22(), this.m21, java.lang.Math.fma(other.m20(), this.m01, other.m21() * this.m11)));
        _m[10] = java.lang.Math.fma(other.m33(), this.m31, java.lang.Math.fma(other.m32(), this.m21, java.lang.Math.fma(other.m30(), this.m01, other.m31() * this.m11)));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c1_mulAdd(Float4x4 other, float[] _m) {
        _m[1] = ((other.m03()) * (this.m31) + (((other.m02()) * (this.m21) + (((other.m00()) * (this.m01) + (other.m01() * this.m11))))));
        _m[4] = ((other.m13()) * (this.m31) + (((other.m12()) * (this.m21) + (((other.m10()) * (this.m01) + (other.m11() * this.m11))))));
        _m[7] = ((other.m23()) * (this.m31) + (((other.m22()) * (this.m21) + (((other.m20()) * (this.m01) + (other.m21() * this.m11))))));
        _m[10] = ((other.m33()) * (this.m31) + (((other.m32()) * (this.m21) + (((other.m30()) * (this.m01) + (other.m31() * this.m11))))));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c2_fma(Float4x4 other, float[] _m) {
        _m[2] = java.lang.Math.fma(other.m03(), this.m32, java.lang.Math.fma(other.m02(), this.m22, java.lang.Math.fma(other.m00(), this.m02, other.m01() * this.m12)));
        _m[5] = java.lang.Math.fma(other.m13(), this.m32, java.lang.Math.fma(other.m12(), this.m22, java.lang.Math.fma(other.m10(), this.m02, other.m11() * this.m12)));
        _m[8] = java.lang.Math.fma(other.m23(), this.m32, java.lang.Math.fma(other.m22(), this.m22, java.lang.Math.fma(other.m20(), this.m02, other.m21() * this.m12)));
        _m[11] = java.lang.Math.fma(other.m33(), this.m32, java.lang.Math.fma(other.m32(), this.m22, java.lang.Math.fma(other.m30(), this.m02, other.m31() * this.m12)));
    }

    /** Private per-column body of {@code preMul}, stored into {@code _m}; reached only through it. */
    private void preMul_s1f06c224_c2_mulAdd(Float4x4 other, float[] _m) {
        _m[2] = ((other.m03()) * (this.m32) + (((other.m02()) * (this.m22) + (((other.m00()) * (this.m02) + (other.m01() * this.m12))))));
        _m[5] = ((other.m13()) * (this.m32) + (((other.m12()) * (this.m22) + (((other.m10()) * (this.m02) + (other.m11() * this.m12))))));
        _m[8] = ((other.m23()) * (this.m32) + (((other.m22()) * (this.m22) + (((other.m20()) * (this.m02) + (other.m21() * this.m12))))));
        _m[11] = ((other.m33()) * (this.m32) + (((other.m32()) * (this.m22) + (((other.m30()) * (this.m02) + (other.m31() * this.m12))))));
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
    public Float4x3 preMul(Float4x4 other) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] _m = new float[12];
            preMul_s1f06c224_c0_fma(other, _m);
            preMul_s1f06c224_c1_fma(other, _m);
            preMul_s1f06c224_c2_fma(other, _m);
            return new Float4x3(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11]);
        } else {
            float[] _m = new float[12];
            preMul_s1f06c224_c0_mulAdd(other, _m);
            preMul_s1f06c224_c1_mulAdd(other, _m);
            preMul_s1f06c224_c2_mulAdd(other, _m);
            return new Float4x3(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11]);
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
    public Float4x3 addScaled(Float4x3 other, float weight) {
        if (!org.joml2.internal.FmaSwitch.ON) return new Float4x3(((weight) * (other.m00) + (this.m00)), ((weight) * (other.m01) + (this.m01)), ((weight) * (other.m02) + (this.m02)), ((weight) * (other.m10) + (this.m10)), ((weight) * (other.m11) + (this.m11)), ((weight) * (other.m12) + (this.m12)), ((weight) * (other.m20) + (this.m20)), ((weight) * (other.m21) + (this.m21)), ((weight) * (other.m22) + (this.m22)), ((weight) * (other.m30) + (this.m30)), ((weight) * (other.m31) + (this.m31)), ((weight) * (other.m32) + (this.m32)));
        return new Float4x3(java.lang.Math.fma(weight, other.m00, this.m00), java.lang.Math.fma(weight, other.m01, this.m01), java.lang.Math.fma(weight, other.m02, this.m02), java.lang.Math.fma(weight, other.m10, this.m10), java.lang.Math.fma(weight, other.m11, this.m11), java.lang.Math.fma(weight, other.m12, this.m12), java.lang.Math.fma(weight, other.m20, this.m20), java.lang.Math.fma(weight, other.m21, this.m21), java.lang.Math.fma(weight, other.m22, this.m22), java.lang.Math.fma(weight, other.m30, this.m30), java.lang.Math.fma(weight, other.m31, this.m31), java.lang.Math.fma(weight, other.m32, this.m32));
    }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) scaled by
     * {@code weight} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}, {@code m30},
     *        {@code m31}, {@code m32}) by before adding
     * @return the resulting matrix
     */
    public Float4x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float weight) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Float4x3(java.lang.Math.fma(weight, m00, this.m00), java.lang.Math.fma(weight, m01, this.m01), java.lang.Math.fma(weight, m02, this.m02), java.lang.Math.fma(weight, m10, this.m10), java.lang.Math.fma(weight, m11, this.m11), java.lang.Math.fma(weight, m12, this.m12), java.lang.Math.fma(weight, m20, this.m20), java.lang.Math.fma(weight, m21, this.m21), java.lang.Math.fma(weight, m22, this.m22), java.lang.Math.fma(weight, m30, this.m30), java.lang.Math.fma(weight, m31, this.m31), java.lang.Math.fma(weight, m32, this.m32));
        } else {
            return new Float4x3(((weight) * (m00) + (this.m00)), ((weight) * (m01) + (this.m01)), ((weight) * (m02) + (this.m02)), ((weight) * (m10) + (this.m10)), ((weight) * (m11) + (this.m11)), ((weight) * (m12) + (this.m12)), ((weight) * (m20) + (this.m20)), ((weight) * (m21) + (this.m21)), ((weight) * (m22) + (this.m22)), ((weight) * (m30) + (this.m30)), ((weight) * (m31) + (this.m31)), ((weight) * (m32) + (this.m32)));
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
    public Float4 mul(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Float4(java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY)), java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY)), java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY)), java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY)));
        } else {
            return new Float4(((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY)))), ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY)))), ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY)))), ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY)))));
        }
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float4 mul(float vX, float vY, float vZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Float4(java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY)), java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY)), java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY)), java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY)));
        } else {
            return new Float4(((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY)))), ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY)))), ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY)))), ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY)))));
        }
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Float4x3 withM00(float v) {
        return new Float4x3(v, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Float4x3 withM01(float v) {
        return new Float4x3(m00, v, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m02} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m02} element
     */
    public Float4x3 withM02(float v) {
        return new Float4x3(m00, m01, v, m10, m11, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Float4x3 withM10(float v) {
        return new Float4x3(m00, m01, m02, v, m11, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Float4x3 withM11(float v) {
        return new Float4x3(m00, m01, m02, m10, v, m12, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m12} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m12} element
     */
    public Float4x3 withM12(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, v, m20, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m20} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m20} element
     */
    public Float4x3 withM20(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, v, m21, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m21} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m21} element
     */
    public Float4x3 withM21(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, v, m22, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m22} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m22} element
     */
    public Float4x3 withM22(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, v, m30, m31, m32);
    }

    /**
     * {@return a copy with the {@code m30} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m30} element
     */
    public Float4x3 withM30(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, v, m31, m32);
    }

    /**
     * {@return a copy with the {@code m31} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m31} element
     */
    public Float4x3 withM31(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, v, m32);
    }

    /**
     * {@return a copy with the {@code m32} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m32} element
     */
    public Float4x3 withM32(float v) {
        return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, v);
    }

    @Override public String toString() {
        return "Float4x3(\n    " + this.m00 + ", " + this.m01 + ", " + this.m02 + "\n    " + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n    " + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n    " + this.m30 + ", " + this.m31 + ", " + this.m32 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4x3)) return false;
        Float4x3 o = (Float4x3) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m02) == Float.floatToIntBits(o.m02)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m12) == Float.floatToIntBits(o.m12)
            && Float.floatToIntBits(m20) == Float.floatToIntBits(o.m20)
            && Float.floatToIntBits(m21) == Float.floatToIntBits(o.m21)
            && Float.floatToIntBits(m22) == Float.floatToIntBits(o.m22)
            && Float.floatToIntBits(m30) == Float.floatToIntBits(o.m30)
            && Float.floatToIntBits(m31) == Float.floatToIntBits(o.m31)
            && Float.floatToIntBits(m32) == Float.floatToIntBits(o.m32);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m02);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m12);
        h = 31 * h + Float.floatToIntBits(m20);
        h = 31 * h + Float.floatToIntBits(m21);
        h = 31 * h + Float.floatToIntBits(m22);
        h = 31 * h + Float.floatToIntBits(m30);
        h = 31 * h + Float.floatToIntBits(m31);
        h = 31 * h + Float.floatToIntBits(m32);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m02)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m12)
            && Float.isFinite(m20)
            && Float.isFinite(m21)
            && Float.isFinite(m22)
            && Float.isFinite(m30)
            && Float.isFinite(m31)
            && Float.isFinite(m32);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m02)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m12)
            || Float.isNaN(m20)
            || Float.isNaN(m21)
            || Float.isNaN(m22)
            || Float.isNaN(m30)
            || Float.isNaN(m31)
            || Float.isNaN(m32);
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
    public boolean equalsEpsilon(Float4x3 other, float epsilon) {
        return java.lang.Math.abs(m00 - other.m00) <= epsilon
            && java.lang.Math.abs(m01 - other.m01) <= epsilon
            && java.lang.Math.abs(m02 - other.m02) <= epsilon
            && java.lang.Math.abs(m10 - other.m10) <= epsilon
            && java.lang.Math.abs(m11 - other.m11) <= epsilon
            && java.lang.Math.abs(m12 - other.m12) <= epsilon
            && java.lang.Math.abs(m20 - other.m20) <= epsilon
            && java.lang.Math.abs(m21 - other.m21) <= epsilon
            && java.lang.Math.abs(m22 - other.m22) <= epsilon
            && java.lang.Math.abs(m30 - other.m30) <= epsilon
            && java.lang.Math.abs(m31 - other.m31) <= epsilon
            && java.lang.Math.abs(m32 - other.m32) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4x3SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4x3SegOpsUnsafe()
            : new Float4x3SegOpsMS();
        static final Float4x3BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4x3BbOpsUnsafe()
            : new Float4x3BbOpsApi();
        static final Float4x3RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4x3RawOpsUnsafe()
            : new Float4x3RawOpsApi();
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
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c3 = src[offset + 1];
        float _c6 = src[offset + 2];
        float _c9 = src[offset + 3];
        float _c1 = src[offset + 4];
        float _c4 = src[offset + 5];
        float _c7 = src[offset + 6];
        float _c10 = src[offset + 7];
        float _c2 = src[offset + 8];
        float _c5 = src[offset + 9];
        float _c8 = src[offset + 10];
        float _c11 = src[offset + 11];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(float[] src) { return loadCM(src, 0); }

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
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(FloatBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 12);
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 48);
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
    public Float4x3 storeCMUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(long offset, MemorySegment src) {
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
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c3 = (float) src[offset + 1];
        float _c6 = (float) src[offset + 2];
        float _c9 = (float) src[offset + 3];
        float _c1 = (float) src[offset + 4];
        float _c4 = (float) src[offset + 5];
        float _c7 = (float) src[offset + 6];
        float _c10 = (float) src[offset + 7];
        float _c2 = (float) src[offset + 8];
        float _c5 = (float) src[offset + 9];
        float _c8 = (float) src[offset + 10];
        float _c11 = (float) src[offset + 11];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(double[] src) { return loadCM(src, 0); }

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
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(DoubleBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 12);
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
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf);
        buf.position(pos + 96);
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
    public Float4x3 storeCMDoubleUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDoubleUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(long offset, MemorySegment src) {
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
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        dest[offset + 9] = this.m30;
        dest[offset + 10] = this.m31;
        dest[offset + 11] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        float _c8 = src[offset + 8];
        float _c9 = src[offset + 9];
        float _c10 = src[offset + 10];
        float _c11 = src[offset + 11];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(float[] src) { return loadRM(src, 0); }

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
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(FloatBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 12);
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 48);
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
    public Float4x3 storeRMUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(long offset, MemorySegment src) {
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
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        dest[offset + 9] = this.m30;
        dest[offset + 10] = this.m31;
        dest[offset + 11] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        float _c9 = (float) src[offset + 9];
        float _c10 = (float) src[offset + 10];
        float _c11 = (float) src[offset + 11];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(double[] src) { return loadRM(src, 0); }

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
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(DoubleBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 12);
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
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf);
        buf.position(pos + 96);
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
    public Float4x3 storeRMDoubleUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDoubleUnsafe(long address) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(long offset, MemorySegment src) {
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
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        dest[_p2 + 3] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = src[offset];
        float _c3 = src[offset + 1];
        float _c6 = src[offset + 2];
        float _c9 = src[offset + 3];
        float _c1 = src[_p1];
        float _c4 = src[_p1 + 1];
        float _c7 = src[_p1 + 2];
        float _c10 = src[_p1 + 3];
        float _c2 = src[_p2];
        float _c5 = src[_p2 + 1];
        float _c8 = src[_p2 + 2];
        float _c11 = src[_p2 + 3];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
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
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
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
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
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
    public Float4x3 storeCMUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(long offset, MemorySegment src, int stride) {
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
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        dest[_p2 + 3] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = (float) src[offset];
        float _c3 = (float) src[offset + 1];
        float _c6 = (float) src[offset + 2];
        float _c9 = (float) src[offset + 3];
        float _c1 = (float) src[_p1];
        float _c4 = (float) src[_p1 + 1];
        float _c7 = (float) src[_p1 + 2];
        float _c10 = (float) src[_p1 + 3];
        float _c2 = (float) src[_p2];
        float _c5 = (float) src[_p2 + 1];
        float _c8 = (float) src[_p2 + 2];
        float _c11 = (float) src[_p2 + 3];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
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
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCM(DoubleBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
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
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
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
    public Float4x3 storeCMDoubleUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDoubleUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
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
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
        dest[_p3 + 2] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[_p1];
        float _c4 = src[_p1 + 1];
        float _c5 = src[_p1 + 2];
        float _c6 = src[_p2];
        float _c7 = src[_p2 + 1];
        float _c8 = src[_p2 + 2];
        float _c9 = src[_p3];
        float _c10 = src[_p3 + 1];
        float _c11 = src[_p3 + 2];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(FloatBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
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
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
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
    public Float4x3 storeRMUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(long offset, MemorySegment src, int stride) {
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
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
        dest[_p3 + 2] = this.m32;
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[_p1];
        float _c4 = (float) src[_p1 + 1];
        float _c5 = (float) src[_p1 + 2];
        float _c6 = (float) src[_p2];
        float _c7 = (float) src[_p2 + 1];
        float _c8 = (float) src[_p2 + 2];
        float _c9 = (float) src[_p3];
        float _c10 = (float) src[_p3 + 1];
        float _c11 = (float) src[_p3 + 2];
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
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
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRM(DoubleBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride);
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
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
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
    public Float4x3 storeRMDoubleUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDoubleUnsafe(long address, int stride) {
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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
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
    public Float4x3 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public Float4x3 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(float[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(double[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float4x3 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

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
     * @return a new {@code Float4x3} holding the loaded elements
     */
    public static Float4x3 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    private abstract static class Float4x3SegOps {
        public abstract MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest);
        public abstract Float4x3 loadCM(long offset, MemorySegment src);
        public abstract MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest);
        public abstract Float4x3 loadCMDouble(long offset, MemorySegment src);
        public abstract MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest);
        public abstract Float4x3 loadRM(long offset, MemorySegment src);
        public abstract MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest);
        public abstract Float4x3 loadRMDouble(long offset, MemorySegment src);
        public abstract MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride);
        public abstract Float4x3 loadCM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
        public abstract Float4x3 loadCMDouble(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride);
        public abstract Float4x3 loadRM(long offset, MemorySegment src, int stride);
        public abstract MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
        public abstract Float4x3 loadRMDouble(long offset, MemorySegment src, int stride);
    }

    private static final class Float4x3SegOpsMS extends Float4x3SegOps {
        public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m31);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m02);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m12);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m22);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32);
            return dest;
        }

        public Float4x3 loadCM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            float _c10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
            float _c8 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
            float _c11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m31);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m02);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m12);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m22);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32);
            return dest;
        }

        public Float4x3 loadCMDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c9 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            float _c10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
            float _c8 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
            float _c11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.m02);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.m12);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.m22);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 40L, self.m31);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 44L, self.m32);
            return dest;
        }

        public Float4x3 loadRM(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            float _c8 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
            float _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
            float _c10 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 40L);
            float _c11 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.m02);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.m12);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.m22);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 80L, self.m31);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 88L, self.m32);
            return dest;
        }

        public Float4x3 loadRMDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            float _c8 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
            float _c9 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
            float _c10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 80L);
            float _c11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12, self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 12, self.m31);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m02);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m12);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 12, self.m32);
            return dest;
        }

        public Float4x3 loadCM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
            float _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
            float _c10 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 12);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
            float _c8 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
            float _c11 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 24, self.m31);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m02);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m12);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 24, self.m32);
            return dest;
        }

        public Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
            float _c9 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
            float _c10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 24);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
            float _c8 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
            float _c11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4, self.m01);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8, self.m02);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1, self.m10);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 4, self.m11);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p1 + 8, self.m12);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2, self.m20);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 4, self.m21);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p2 + 8, self.m22);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p3, self.m30);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 4, self.m31);
            dest.set(_JAVA_FLOAT_UNALIGNED, _p3 + 8, self.m32);
            return dest;
        }

        public Float4x3 loadRM(long offset, MemorySegment src, int stride) {
            long _ps = stride * 4L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, _p1);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 4);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, _p1 + 8);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, _p2);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 4);
            float _c8 = src.get(_JAVA_FLOAT_UNALIGNED, _p2 + 8);
            float _c9 = src.get(_JAVA_FLOAT_UNALIGNED, _p3);
            float _c10 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 4);
            float _c11 = src.get(_JAVA_FLOAT_UNALIGNED, _p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.m00);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8, self.m01);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16, self.m02);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1, self.m10);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 8, self.m11);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p1 + 16, self.m12);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2, self.m20);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 8, self.m21);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p2 + 16, self.m22);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p3, self.m30);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 8, self.m31);
            dest.set(_JAVA_DOUBLE_UNALIGNED, _p3 + 16, self.m32);
            return dest;
        }

        public Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
            long _ps = stride * 8L;
            long _p1 = offset + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 8);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p1 + 16);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 8);
            float _c8 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p2 + 16);
            float _c9 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3);
            float _c10 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 8);
            float _c11 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, _p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }
    }

    private static final class Float4x3SegOpsUnsafe extends Float4x3SegOps {
        private static final Float4x3SegOpsMS MS = new Float4x3SegOpsMS();

        public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m30);
            _U.putFloat(address + 16L, self.m01);
            _U.putFloat(address + 20L, self.m11);
            _U.putFloat(address + 24L, self.m21);
            _U.putFloat(address + 28L, self.m31);
            _U.putFloat(address + 32L, self.m02);
            _U.putFloat(address + 36L, self.m12);
            _U.putFloat(address + 40L, self.m22);
            _U.putFloat(address + 44L, self.m32);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Float4x3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m30);
            _U.putFloat(bytes, address + 16L, self.m01);
            _U.putFloat(bytes, address + 20L, self.m11);
            _U.putFloat(bytes, address + 24L, self.m21);
            _U.putFloat(bytes, address + 28L, self.m31);
            _U.putFloat(bytes, address + 32L, self.m02);
            _U.putFloat(bytes, address + 36L, self.m12);
            _U.putFloat(bytes, address + 40L, self.m22);
            _U.putFloat(bytes, address + 44L, self.m32);
            return dest;
        }

        public Float4x3 loadCM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4L);
            float _c6 = _U.getFloat(address + 8L);
            float _c9 = _U.getFloat(address + 12L);
            float _c1 = _U.getFloat(address + 16L);
            float _c4 = _U.getFloat(address + 20L);
            float _c7 = _U.getFloat(address + 24L);
            float _c10 = _U.getFloat(address + 28L);
            float _c2 = _U.getFloat(address + 32L);
            float _c5 = _U.getFloat(address + 36L);
            float _c8 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4L);
            float _c6 = _U.getFloat(bytes, address + 8L);
            float _c9 = _U.getFloat(bytes, address + 12L);
            float _c1 = _U.getFloat(bytes, address + 16L);
            float _c4 = _U.getFloat(bytes, address + 20L);
            float _c7 = _U.getFloat(bytes, address + 24L);
            float _c10 = _U.getFloat(bytes, address + 28L);
            float _c2 = _U.getFloat(bytes, address + 32L);
            float _c5 = _U.getFloat(bytes, address + 36L);
            float _c8 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
            if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
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
            _U.putDouble(address + 64L, self.m02);
            _U.putDouble(address + 72L, self.m12);
            _U.putDouble(address + 80L, self.m22);
            _U.putDouble(address + 88L, self.m32);
            return dest;
        }

        private static MemorySegment storeCMDoubleOnHeap(Float4x3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMDouble(self, offset, dest);
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
            _U.putDouble(bytes, address + 64L, self.m02);
            _U.putDouble(bytes, address + 72L, self.m12);
            _U.putDouble(bytes, address + 80L, self.m22);
            _U.putDouble(bytes, address + 88L, self.m32);
            return dest;
        }

        public Float4x3 loadCMDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadCMDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8L);
            float _c6 = (float) _U.getDouble(address + 16L);
            float _c9 = (float) _U.getDouble(address + 24L);
            float _c1 = (float) _U.getDouble(address + 32L);
            float _c4 = (float) _U.getDouble(address + 40L);
            float _c7 = (float) _U.getDouble(address + 48L);
            float _c10 = (float) _U.getDouble(address + 56L);
            float _c2 = (float) _U.getDouble(address + 64L);
            float _c5 = (float) _U.getDouble(address + 72L);
            float _c8 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMDoubleOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMDouble(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8L);
            float _c6 = (float) _U.getDouble(bytes, address + 16L);
            float _c9 = (float) _U.getDouble(bytes, address + 24L);
            float _c1 = (float) _U.getDouble(bytes, address + 32L);
            float _c4 = (float) _U.getDouble(bytes, address + 40L);
            float _c7 = (float) _U.getDouble(bytes, address + 48L);
            float _c10 = (float) _U.getDouble(bytes, address + 56L);
            float _c2 = (float) _U.getDouble(bytes, address + 64L);
            float _c5 = (float) _U.getDouble(bytes, address + 72L);
            float _c8 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m02);
            _U.putFloat(address + 12L, self.m10);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m12);
            _U.putFloat(address + 24L, self.m20);
            _U.putFloat(address + 28L, self.m21);
            _U.putFloat(address + 32L, self.m22);
            _U.putFloat(address + 36L, self.m30);
            _U.putFloat(address + 40L, self.m31);
            _U.putFloat(address + 44L, self.m32);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Float4x3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m02);
            _U.putFloat(bytes, address + 12L, self.m10);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m12);
            _U.putFloat(bytes, address + 24L, self.m20);
            _U.putFloat(bytes, address + 28L, self.m21);
            _U.putFloat(bytes, address + 32L, self.m22);
            _U.putFloat(bytes, address + 36L, self.m30);
            _U.putFloat(bytes, address + 40L, self.m31);
            _U.putFloat(bytes, address + 44L, self.m32);
            return dest;
        }

        public Float4x3 loadRM(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            float _c10 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMOnHeap(long offset, MemorySegment src) {
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
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            float _c10 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
            if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m02);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m12);
            _U.putDouble(address + 48L, self.m20);
            _U.putDouble(address + 56L, self.m21);
            _U.putDouble(address + 64L, self.m22);
            _U.putDouble(address + 72L, self.m30);
            _U.putDouble(address + 80L, self.m31);
            _U.putDouble(address + 88L, self.m32);
            return dest;
        }

        private static MemorySegment storeRMDoubleOnHeap(Float4x3 self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m02);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m12);
            _U.putDouble(bytes, address + 48L, self.m20);
            _U.putDouble(bytes, address + 56L, self.m21);
            _U.putDouble(bytes, address + 64L, self.m22);
            _U.putDouble(bytes, address + 72L, self.m30);
            _U.putDouble(bytes, address + 80L, self.m31);
            _U.putDouble(bytes, address + 88L, self.m32);
            return dest;
        }

        public Float4x3 loadRMDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadRMDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            float _c10 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMDoubleOnHeap(long offset, MemorySegment src) {
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
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            float _c10 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(address + 12, self.m30);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            _U.putFloat(_p1 + 12, self.m31);
            _U.putFloat(_p2, self.m02);
            _U.putFloat(_p2 + 4, self.m12);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p2 + 12, self.m32);
            return dest;
        }

        private static MemorySegment storeCMOnHeap(Float4x3 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m10);
            _U.putFloat(bytes, address + 8, self.m20);
            _U.putFloat(bytes, address + 12, self.m30);
            _U.putFloat(bytes, _p1, self.m01);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m21);
            _U.putFloat(bytes, _p1 + 12, self.m31);
            _U.putFloat(bytes, _p2, self.m02);
            _U.putFloat(bytes, _p2 + 4, self.m12);
            _U.putFloat(bytes, _p2 + 8, self.m22);
            _U.putFloat(bytes, _p2 + 12, self.m32);
            return dest;
        }

        public Float4x3 loadCM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4);
            float _c6 = _U.getFloat(address + 8);
            float _c9 = _U.getFloat(address + 12);
            float _c1 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c7 = _U.getFloat(_p1 + 8);
            float _c10 = _U.getFloat(_p1 + 12);
            float _c2 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c11 = _U.getFloat(_p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4);
            float _c6 = _U.getFloat(bytes, address + 8);
            float _c9 = _U.getFloat(bytes, address + 12);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c7 = _U.getFloat(bytes, _p1 + 8);
            float _c10 = _U.getFloat(bytes, _p1 + 12);
            float _c2 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c11 = _U.getFloat(bytes, _p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
            if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            _U.putDouble(_p2, self.m02);
            _U.putDouble(_p2 + 8, self.m12);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p2 + 24, self.m32);
            return dest;
        }

        private static MemorySegment storeCMDoubleOnHeap(Float4x3 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, address + 24, self.m30);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            _U.putDouble(bytes, _p1 + 24, self.m31);
            _U.putDouble(bytes, _p2, self.m02);
            _U.putDouble(bytes, _p2 + 8, self.m12);
            _U.putDouble(bytes, _p2 + 16, self.m22);
            _U.putDouble(bytes, _p2 + 24, self.m32);
            return dest;
        }

        public Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadCMDoubleOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8);
            float _c6 = (float) _U.getDouble(address + 16);
            float _c9 = (float) _U.getDouble(address + 24);
            float _c1 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c7 = (float) _U.getDouble(_p1 + 16);
            float _c10 = (float) _U.getDouble(_p1 + 24);
            float _c2 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c11 = (float) _U.getDouble(_p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMDoubleOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadCMDouble(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8);
            float _c6 = (float) _U.getDouble(bytes, address + 16);
            float _c9 = (float) _U.getDouble(bytes, address + 24);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c7 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c10 = (float) _U.getDouble(bytes, _p1 + 24);
            float _c2 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c11 = (float) _U.getDouble(bytes, _p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(address + 8, self.m02);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m12);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p3, self.m30);
            _U.putFloat(_p3 + 4, self.m31);
            _U.putFloat(_p3 + 8, self.m32);
            return dest;
        }

        private static MemorySegment storeRMOnHeap(Float4x3 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRM(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m01);
            _U.putFloat(bytes, address + 8, self.m02);
            _U.putFloat(bytes, _p1, self.m10);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m12);
            _U.putFloat(bytes, _p2, self.m20);
            _U.putFloat(bytes, _p2 + 4, self.m21);
            _U.putFloat(bytes, _p2 + 8, self.m22);
            _U.putFloat(bytes, _p3, self.m30);
            _U.putFloat(bytes, _p3 + 4, self.m31);
            _U.putFloat(bytes, _p3 + 8, self.m32);
            return dest;
        }

        public Float4x3 loadRM(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(address + 8);
            float _c3 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            float _c6 = _U.getFloat(_p2);
            float _c7 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c9 = _U.getFloat(_p3);
            float _c10 = _U.getFloat(_p3 + 4);
            float _c11 = _U.getFloat(_p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRM(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, address + 8);
            float _c3 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            float _c6 = _U.getFloat(bytes, _p2);
            float _c7 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c9 = _U.getFloat(bytes, _p3);
            float _c10 = _U.getFloat(bytes, _p3 + 4);
            float _c11 = _U.getFloat(bytes, _p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride) {
            if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
            if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
            long address = dest.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(address + 16, self.m02);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m12);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            _U.putDouble(_p3 + 16, self.m32);
            return dest;
        }

        private static MemorySegment storeRMDoubleOnHeap(Float4x3 self, long offset, MemorySegment dest, int stride) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
            long address = HeapSegments.offset(dest) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, address + 16, self.m02);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m12);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            _U.putDouble(bytes, _p2 + 16, self.m22);
            _U.putDouble(bytes, _p3, self.m30);
            _U.putDouble(bytes, _p3 + 8, self.m31);
            _U.putDouble(bytes, _p3 + 16, self.m32);
            return dest;
        }

        public Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
            if (!src.isNative()) return loadRMDoubleOnHeap(offset, src, stride);
            long address = src.address() + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(address + 16);
            float _c3 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            float _c6 = (float) _U.getDouble(_p2);
            float _c7 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c9 = (float) _U.getDouble(_p3);
            float _c10 = (float) _U.getDouble(_p3 + 8);
            float _c11 = (float) _U.getDouble(_p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMDoubleOnHeap(long offset, MemorySegment src, int stride) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadRMDouble(offset, src, stride);
            long address = HeapSegments.offset(src) + offset;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, address + 16);
            float _c3 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c6 = (float) _U.getDouble(bytes, _p2);
            float _c7 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c9 = (float) _U.getDouble(bytes, _p3);
            float _c10 = (float) _U.getDouble(bytes, _p3 + 8);
            float _c11 = (float) _U.getDouble(bytes, _p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }
    }

    private abstract static class Float4x3BbOps {
        public abstract FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf);
        public abstract Float4x3 loadCMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf);
        public abstract Float4x3 loadCMAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf);
        public abstract Float4x3 loadCMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf);
        public abstract Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf);
        public abstract Float4x3 loadRMAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf);
        public abstract Float4x3 loadRMAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf);
        public abstract Float4x3 loadRMAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf);
        public abstract Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride);
        public abstract Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
        public abstract Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride);
        public abstract Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
        public abstract Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
        public abstract FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride);
        public abstract Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride);
        public abstract ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
        public abstract Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride);
        public abstract DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride);
        public abstract Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
        public abstract ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
        public abstract Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    }

    private static final class Float4x3BbOpsApi extends Float4x3BbOps {
        public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(index + 4, self.m01);
            buf.put(index + 5, self.m11);
            buf.put(index + 6, self.m21);
            buf.put(index + 7, self.m31);
            buf.put(index + 8, self.m02);
            buf.put(index + 9, self.m12);
            buf.put(index + 10, self.m22);
            buf.put(index + 11, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c3 = buf.get(index + 1);
            float _c6 = buf.get(index + 2);
            float _c9 = buf.get(index + 3);
            float _c1 = buf.get(index + 4);
            float _c4 = buf.get(index + 5);
            float _c7 = buf.get(index + 6);
            float _c10 = buf.get(index + 7);
            float _c2 = buf.get(index + 8);
            float _c5 = buf.get(index + 9);
            float _c8 = buf.get(index + 10);
            float _c11 = buf.get(index + 11);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m10);
            buf.putFloat(index + 8, self.m20);
            buf.putFloat(index + 12, self.m30);
            buf.putFloat(index + 16, self.m01);
            buf.putFloat(index + 20, self.m11);
            buf.putFloat(index + 24, self.m21);
            buf.putFloat(index + 28, self.m31);
            buf.putFloat(index + 32, self.m02);
            buf.putFloat(index + 36, self.m12);
            buf.putFloat(index + 40, self.m22);
            buf.putFloat(index + 44, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c3 = buf.getFloat(index + 4);
            float _c6 = buf.getFloat(index + 8);
            float _c9 = buf.getFloat(index + 12);
            float _c1 = buf.getFloat(index + 16);
            float _c4 = buf.getFloat(index + 20);
            float _c7 = buf.getFloat(index + 24);
            float _c10 = buf.getFloat(index + 28);
            float _c2 = buf.getFloat(index + 32);
            float _c5 = buf.getFloat(index + 36);
            float _c8 = buf.getFloat(index + 40);
            float _c11 = buf.getFloat(index + 44);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(index + 4, self.m01);
            buf.put(index + 5, self.m11);
            buf.put(index + 6, self.m21);
            buf.put(index + 7, self.m31);
            buf.put(index + 8, self.m02);
            buf.put(index + 9, self.m12);
            buf.put(index + 10, self.m22);
            buf.put(index + 11, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c3 = (float) buf.get(index + 1);
            float _c6 = (float) buf.get(index + 2);
            float _c9 = (float) buf.get(index + 3);
            float _c1 = (float) buf.get(index + 4);
            float _c4 = (float) buf.get(index + 5);
            float _c7 = (float) buf.get(index + 6);
            float _c10 = (float) buf.get(index + 7);
            float _c2 = (float) buf.get(index + 8);
            float _c5 = (float) buf.get(index + 9);
            float _c8 = (float) buf.get(index + 10);
            float _c11 = (float) buf.get(index + 11);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(index + 24, self.m30);
            buf.putDouble(index + 32, self.m01);
            buf.putDouble(index + 40, self.m11);
            buf.putDouble(index + 48, self.m21);
            buf.putDouble(index + 56, self.m31);
            buf.putDouble(index + 64, self.m02);
            buf.putDouble(index + 72, self.m12);
            buf.putDouble(index + 80, self.m22);
            buf.putDouble(index + 88, self.m32);
            return buf;
        }

        public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c3 = (float) buf.getDouble(index + 8);
            float _c6 = (float) buf.getDouble(index + 16);
            float _c9 = (float) buf.getDouble(index + 24);
            float _c1 = (float) buf.getDouble(index + 32);
            float _c4 = (float) buf.getDouble(index + 40);
            float _c7 = (float) buf.getDouble(index + 48);
            float _c10 = (float) buf.getDouble(index + 56);
            float _c2 = (float) buf.getDouble(index + 64);
            float _c5 = (float) buf.getDouble(index + 72);
            float _c8 = (float) buf.getDouble(index + 80);
            float _c11 = (float) buf.getDouble(index + 88);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m02);
            buf.put(index + 3, self.m10);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, self.m12);
            buf.put(index + 6, self.m20);
            buf.put(index + 7, self.m21);
            buf.put(index + 8, self.m22);
            buf.put(index + 9, self.m30);
            buf.put(index + 10, self.m31);
            buf.put(index + 11, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            float _c4 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            float _c6 = buf.get(index + 6);
            float _c7 = buf.get(index + 7);
            float _c8 = buf.get(index + 8);
            float _c9 = buf.get(index + 9);
            float _c10 = buf.get(index + 10);
            float _c11 = buf.get(index + 11);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m01);
            buf.putFloat(index + 8, self.m02);
            buf.putFloat(index + 12, self.m10);
            buf.putFloat(index + 16, self.m11);
            buf.putFloat(index + 20, self.m12);
            buf.putFloat(index + 24, self.m20);
            buf.putFloat(index + 28, self.m21);
            buf.putFloat(index + 32, self.m22);
            buf.putFloat(index + 36, self.m30);
            buf.putFloat(index + 40, self.m31);
            buf.putFloat(index + 44, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            float _c4 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            float _c6 = buf.getFloat(index + 24);
            float _c7 = buf.getFloat(index + 28);
            float _c8 = buf.getFloat(index + 32);
            float _c9 = buf.getFloat(index + 36);
            float _c10 = buf.getFloat(index + 40);
            float _c11 = buf.getFloat(index + 44);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m02);
            buf.put(index + 3, self.m10);
            buf.put(index + 4, self.m11);
            buf.put(index + 5, self.m12);
            buf.put(index + 6, self.m20);
            buf.put(index + 7, self.m21);
            buf.put(index + 8, self.m22);
            buf.put(index + 9, self.m30);
            buf.put(index + 10, self.m31);
            buf.put(index + 11, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            float _c4 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            float _c6 = (float) buf.get(index + 6);
            float _c7 = (float) buf.get(index + 7);
            float _c8 = (float) buf.get(index + 8);
            float _c9 = (float) buf.get(index + 9);
            float _c10 = (float) buf.get(index + 10);
            float _c11 = (float) buf.get(index + 11);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, self.m02);
            buf.putDouble(index + 24, self.m10);
            buf.putDouble(index + 32, self.m11);
            buf.putDouble(index + 40, self.m12);
            buf.putDouble(index + 48, self.m20);
            buf.putDouble(index + 56, self.m21);
            buf.putDouble(index + 64, self.m22);
            buf.putDouble(index + 72, self.m30);
            buf.putDouble(index + 80, self.m31);
            buf.putDouble(index + 88, self.m32);
            return buf;
        }

        public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            float _c4 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            float _c6 = (float) buf.getDouble(index + 48);
            float _c7 = (float) buf.getDouble(index + 56);
            float _c8 = (float) buf.getDouble(index + 64);
            float _c9 = (float) buf.getDouble(index + 72);
            float _c10 = (float) buf.getDouble(index + 80);
            float _c11 = (float) buf.getDouble(index + 88);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m21);
            buf.put(_p1 + 3, self.m31);
            buf.put(_p2, self.m02);
            buf.put(_p2 + 1, self.m12);
            buf.put(_p2 + 2, self.m22);
            buf.put(_p2 + 3, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            float _c0 = buf.get(index);
            float _c3 = buf.get(index + 1);
            float _c6 = buf.get(index + 2);
            float _c9 = buf.get(index + 3);
            float _c1 = buf.get(_p1);
            float _c4 = buf.get(_p1 + 1);
            float _c7 = buf.get(_p1 + 2);
            float _c10 = buf.get(_p1 + 3);
            float _c2 = buf.get(_p2);
            float _c5 = buf.get(_p2 + 1);
            float _c8 = buf.get(_p2 + 2);
            float _c11 = buf.get(_p2 + 3);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m10);
            buf.putFloat(index + 8, self.m20);
            buf.putFloat(index + 12, self.m30);
            buf.putFloat(_p1, self.m01);
            buf.putFloat(_p1 + 4, self.m11);
            buf.putFloat(_p1 + 8, self.m21);
            buf.putFloat(_p1 + 12, self.m31);
            buf.putFloat(_p2, self.m02);
            buf.putFloat(_p2 + 4, self.m12);
            buf.putFloat(_p2 + 8, self.m22);
            buf.putFloat(_p2 + 12, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            float _c0 = buf.getFloat(index);
            float _c3 = buf.getFloat(index + 4);
            float _c6 = buf.getFloat(index + 8);
            float _c9 = buf.getFloat(index + 12);
            float _c1 = buf.getFloat(_p1);
            float _c4 = buf.getFloat(_p1 + 4);
            float _c7 = buf.getFloat(_p1 + 8);
            float _c10 = buf.getFloat(_p1 + 12);
            float _c2 = buf.getFloat(_p2);
            float _c5 = buf.getFloat(_p2 + 4);
            float _c8 = buf.getFloat(_p2 + 8);
            float _c11 = buf.getFloat(_p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m10);
            buf.put(index + 2, self.m20);
            buf.put(index + 3, self.m30);
            buf.put(_p1, self.m01);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m21);
            buf.put(_p1 + 3, self.m31);
            buf.put(_p2, self.m02);
            buf.put(_p2 + 1, self.m12);
            buf.put(_p2 + 2, self.m22);
            buf.put(_p2 + 3, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            float _c0 = (float) buf.get(index);
            float _c3 = (float) buf.get(index + 1);
            float _c6 = (float) buf.get(index + 2);
            float _c9 = (float) buf.get(index + 3);
            float _c1 = (float) buf.get(_p1);
            float _c4 = (float) buf.get(_p1 + 1);
            float _c7 = (float) buf.get(_p1 + 2);
            float _c10 = (float) buf.get(_p1 + 3);
            float _c2 = (float) buf.get(_p2);
            float _c5 = (float) buf.get(_p2 + 1);
            float _c8 = (float) buf.get(_p2 + 2);
            float _c11 = (float) buf.get(_p2 + 3);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m10);
            buf.putDouble(index + 16, self.m20);
            buf.putDouble(index + 24, self.m30);
            buf.putDouble(_p1, self.m01);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p1 + 16, self.m21);
            buf.putDouble(_p1 + 24, self.m31);
            buf.putDouble(_p2, self.m02);
            buf.putDouble(_p2 + 8, self.m12);
            buf.putDouble(_p2 + 16, self.m22);
            buf.putDouble(_p2 + 24, self.m32);
            return buf;
        }

        public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            float _c0 = (float) buf.getDouble(index);
            float _c3 = (float) buf.getDouble(index + 8);
            float _c6 = (float) buf.getDouble(index + 16);
            float _c9 = (float) buf.getDouble(index + 24);
            float _c1 = (float) buf.getDouble(_p1);
            float _c4 = (float) buf.getDouble(_p1 + 8);
            float _c7 = (float) buf.getDouble(_p1 + 16);
            float _c10 = (float) buf.getDouble(_p1 + 24);
            float _c2 = (float) buf.getDouble(_p2);
            float _c5 = (float) buf.getDouble(_p2 + 8);
            float _c8 = (float) buf.getDouble(_p2 + 16);
            float _c11 = (float) buf.getDouble(_p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m02);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m12);
            buf.put(_p2, self.m20);
            buf.put(_p2 + 1, self.m21);
            buf.put(_p2 + 2, self.m22);
            buf.put(_p3, self.m30);
            buf.put(_p3 + 1, self.m31);
            buf.put(_p3 + 2, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(_p1);
            float _c4 = buf.get(_p1 + 1);
            float _c5 = buf.get(_p1 + 2);
            float _c6 = buf.get(_p2);
            float _c7 = buf.get(_p2 + 1);
            float _c8 = buf.get(_p2 + 2);
            float _c9 = buf.get(_p3);
            float _c10 = buf.get(_p3 + 1);
            float _c11 = buf.get(_p3 + 2);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            buf.putFloat(index, self.m00);
            buf.putFloat(index + 4, self.m01);
            buf.putFloat(index + 8, self.m02);
            buf.putFloat(_p1, self.m10);
            buf.putFloat(_p1 + 4, self.m11);
            buf.putFloat(_p1 + 8, self.m12);
            buf.putFloat(_p2, self.m20);
            buf.putFloat(_p2 + 4, self.m21);
            buf.putFloat(_p2 + 8, self.m22);
            buf.putFloat(_p3, self.m30);
            buf.putFloat(_p3 + 4, self.m31);
            buf.putFloat(_p3 + 8, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 4;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(_p1);
            float _c4 = buf.getFloat(_p1 + 4);
            float _c5 = buf.getFloat(_p1 + 8);
            float _c6 = buf.getFloat(_p2);
            float _c7 = buf.getFloat(_p2 + 4);
            float _c8 = buf.getFloat(_p2 + 8);
            float _c9 = buf.getFloat(_p3);
            float _c10 = buf.getFloat(_p3 + 4);
            float _c11 = buf.getFloat(_p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            buf.put(index, self.m00);
            buf.put(index + 1, self.m01);
            buf.put(index + 2, self.m02);
            buf.put(_p1, self.m10);
            buf.put(_p1 + 1, self.m11);
            buf.put(_p1 + 2, self.m12);
            buf.put(_p2, self.m20);
            buf.put(_p2 + 1, self.m21);
            buf.put(_p2 + 2, self.m22);
            buf.put(_p3, self.m30);
            buf.put(_p3 + 1, self.m31);
            buf.put(_p3 + 2, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            int _p1 = index + stride;
            int _p2 = _p1 + stride;
            int _p3 = _p2 + stride;
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(_p1);
            float _c4 = (float) buf.get(_p1 + 1);
            float _c5 = (float) buf.get(_p1 + 2);
            float _c6 = (float) buf.get(_p2);
            float _c7 = (float) buf.get(_p2 + 1);
            float _c8 = (float) buf.get(_p2 + 2);
            float _c9 = (float) buf.get(_p3);
            float _c10 = (float) buf.get(_p3 + 1);
            float _c11 = (float) buf.get(_p3 + 2);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            buf.putDouble(index, self.m00);
            buf.putDouble(index + 8, self.m01);
            buf.putDouble(index + 16, self.m02);
            buf.putDouble(_p1, self.m10);
            buf.putDouble(_p1 + 8, self.m11);
            buf.putDouble(_p1 + 16, self.m12);
            buf.putDouble(_p2, self.m20);
            buf.putDouble(_p2 + 8, self.m21);
            buf.putDouble(_p2 + 16, self.m22);
            buf.putDouble(_p3, self.m30);
            buf.putDouble(_p3 + 8, self.m31);
            buf.putDouble(_p3 + 16, self.m32);
            return buf;
        }

        public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            int _ps = stride * 8;
            int _p1 = index + _ps;
            int _p2 = _p1 + _ps;
            int _p3 = _p2 + _ps;
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(_p1);
            float _c4 = (float) buf.getDouble(_p1 + 8);
            float _c5 = (float) buf.getDouble(_p1 + 16);
            float _c6 = (float) buf.getDouble(_p2);
            float _c7 = (float) buf.getDouble(_p2 + 8);
            float _c8 = (float) buf.getDouble(_p2 + 16);
            float _c9 = (float) buf.getDouble(_p3);
            float _c10 = (float) buf.getDouble(_p3 + 8);
            float _c11 = (float) buf.getDouble(_p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }
    }

    private static final class Float4x3BbOpsUnsafe extends Float4x3BbOps {
        private static final Float4x3BbOpsApi API = new Float4x3BbOpsApi();
        private static final Float4x3RawOpsUnsafe RAW = new Float4x3RawOpsUnsafe();

        public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m30);
            _U.putFloat(address + 16L, self.m01);
            _U.putFloat(address + 20L, self.m11);
            _U.putFloat(address + 24L, self.m21);
            _U.putFloat(address + 28L, self.m31);
            _U.putFloat(address + 32L, self.m02);
            _U.putFloat(address + 36L, self.m12);
            _U.putFloat(address + 40L, self.m22);
            _U.putFloat(address + 44L, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4L);
            float _c6 = _U.getFloat(address + 8L);
            float _c9 = _U.getFloat(address + 12L);
            float _c1 = _U.getFloat(address + 16L);
            float _c4 = _U.getFloat(address + 20L);
            float _c7 = _U.getFloat(address + 24L);
            float _c10 = _U.getFloat(address + 28L);
            float _c2 = _U.getFloat(address + 32L);
            float _c5 = _U.getFloat(address + 36L);
            float _c8 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m30);
            _U.putFloat(address + 16L, self.m01);
            _U.putFloat(address + 20L, self.m11);
            _U.putFloat(address + 24L, self.m21);
            _U.putFloat(address + 28L, self.m31);
            _U.putFloat(address + 32L, self.m02);
            _U.putFloat(address + 36L, self.m12);
            _U.putFloat(address + 40L, self.m22);
            _U.putFloat(address + 44L, self.m32);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m30);
            _U.putFloat(bytes, address + 16L, self.m01);
            _U.putFloat(bytes, address + 20L, self.m11);
            _U.putFloat(bytes, address + 24L, self.m21);
            _U.putFloat(bytes, address + 28L, self.m31);
            _U.putFloat(bytes, address + 32L, self.m02);
            _U.putFloat(bytes, address + 36L, self.m12);
            _U.putFloat(bytes, address + 40L, self.m22);
            _U.putFloat(bytes, address + 44L, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4L);
            float _c6 = _U.getFloat(address + 8L);
            float _c9 = _U.getFloat(address + 12L);
            float _c1 = _U.getFloat(address + 16L);
            float _c4 = _U.getFloat(address + 20L);
            float _c7 = _U.getFloat(address + 24L);
            float _c10 = _U.getFloat(address + 28L);
            float _c2 = _U.getFloat(address + 32L);
            float _c5 = _U.getFloat(address + 36L);
            float _c8 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4L);
            float _c6 = _U.getFloat(bytes, address + 8L);
            float _c9 = _U.getFloat(bytes, address + 12L);
            float _c1 = _U.getFloat(bytes, address + 16L);
            float _c4 = _U.getFloat(bytes, address + 20L);
            float _c7 = _U.getFloat(bytes, address + 24L);
            float _c10 = _U.getFloat(bytes, address + 28L);
            float _c2 = _U.getFloat(bytes, address + 32L);
            float _c5 = _U.getFloat(bytes, address + 36L);
            float _c8 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
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
            _U.putDouble(address + 64L, self.m02);
            _U.putDouble(address + 72L, self.m12);
            _U.putDouble(address + 80L, self.m22);
            _U.putDouble(address + 88L, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8L);
            float _c6 = (float) _U.getDouble(address + 16L);
            float _c9 = (float) _U.getDouble(address + 24L);
            float _c1 = (float) _U.getDouble(address + 32L);
            float _c4 = (float) _U.getDouble(address + 40L);
            float _c7 = (float) _U.getDouble(address + 48L);
            float _c10 = (float) _U.getDouble(address + 56L);
            float _c2 = (float) _U.getDouble(address + 64L);
            float _c5 = (float) _U.getDouble(address + 72L);
            float _c8 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
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
            _U.putDouble(address + 64L, self.m02);
            _U.putDouble(address + 72L, self.m12);
            _U.putDouble(address + 80L, self.m22);
            _U.putDouble(address + 88L, self.m32);
            return buf;
        }

        private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
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
            _U.putDouble(bytes, address + 64L, self.m02);
            _U.putDouble(bytes, address + 72L, self.m12);
            _U.putDouble(bytes, address + 80L, self.m22);
            _U.putDouble(bytes, address + 88L, self.m32);
            return buf;
        }

        public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8L);
            float _c6 = (float) _U.getDouble(address + 16L);
            float _c9 = (float) _U.getDouble(address + 24L);
            float _c1 = (float) _U.getDouble(address + 32L);
            float _c4 = (float) _U.getDouble(address + 40L);
            float _c7 = (float) _U.getDouble(address + 48L);
            float _c10 = (float) _U.getDouble(address + 56L);
            float _c2 = (float) _U.getDouble(address + 64L);
            float _c5 = (float) _U.getDouble(address + 72L);
            float _c8 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8L);
            float _c6 = (float) _U.getDouble(bytes, address + 16L);
            float _c9 = (float) _U.getDouble(bytes, address + 24L);
            float _c1 = (float) _U.getDouble(bytes, address + 32L);
            float _c4 = (float) _U.getDouble(bytes, address + 40L);
            float _c7 = (float) _U.getDouble(bytes, address + 48L);
            float _c10 = (float) _U.getDouble(bytes, address + 56L);
            float _c2 = (float) _U.getDouble(bytes, address + 64L);
            float _c5 = (float) _U.getDouble(bytes, address + 72L);
            float _c8 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m02);
            _U.putFloat(address + 12L, self.m10);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m12);
            _U.putFloat(address + 24L, self.m20);
            _U.putFloat(address + 28L, self.m21);
            _U.putFloat(address + 32L, self.m22);
            _U.putFloat(address + 36L, self.m30);
            _U.putFloat(address + 40L, self.m31);
            _U.putFloat(address + 44L, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            float _c10 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m02);
            _U.putFloat(address + 12L, self.m10);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m12);
            _U.putFloat(address + 24L, self.m20);
            _U.putFloat(address + 28L, self.m21);
            _U.putFloat(address + 32L, self.m22);
            _U.putFloat(address + 36L, self.m30);
            _U.putFloat(address + 40L, self.m31);
            _U.putFloat(address + 44L, self.m32);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m02);
            _U.putFloat(bytes, address + 12L, self.m10);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m12);
            _U.putFloat(bytes, address + 24L, self.m20);
            _U.putFloat(bytes, address + 28L, self.m21);
            _U.putFloat(bytes, address + 32L, self.m22);
            _U.putFloat(bytes, address + 36L, self.m30);
            _U.putFloat(bytes, address + 40L, self.m31);
            _U.putFloat(bytes, address + 44L, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            float _c10 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMAbsoluteElse(int index, ByteBuffer buf) {
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
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            float _c10 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m02);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m12);
            _U.putDouble(address + 48L, self.m20);
            _U.putDouble(address + 56L, self.m21);
            _U.putDouble(address + 64L, self.m22);
            _U.putDouble(address + 72L, self.m30);
            _U.putDouble(address + 80L, self.m31);
            _U.putDouble(address + 88L, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            float _c10 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m02);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m12);
            _U.putDouble(address + 48L, self.m20);
            _U.putDouble(address + 56L, self.m21);
            _U.putDouble(address + 64L, self.m22);
            _U.putDouble(address + 72L, self.m30);
            _U.putDouble(address + 80L, self.m31);
            _U.putDouble(address + 88L, self.m32);
            return buf;
        }

        private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m02);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m12);
            _U.putDouble(bytes, address + 48L, self.m20);
            _U.putDouble(bytes, address + 56L, self.m21);
            _U.putDouble(bytes, address + 64L, self.m22);
            _U.putDouble(bytes, address + 72L, self.m30);
            _U.putDouble(bytes, address + 80L, self.m31);
            _U.putDouble(bytes, address + 88L, self.m32);
            return buf;
        }

        public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            float _c10 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf) {
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
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            float _c10 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(address + 12, self.m30);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            _U.putFloat(_p1 + 12, self.m31);
            _U.putFloat(_p2, self.m02);
            _U.putFloat(_p2 + 4, self.m12);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p2 + 12, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4);
            float _c6 = _U.getFloat(address + 8);
            float _c9 = _U.getFloat(address + 12);
            float _c1 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c7 = _U.getFloat(_p1 + 8);
            float _c10 = _U.getFloat(_p1 + 12);
            float _c2 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c11 = _U.getFloat(_p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(address + 12, self.m30);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            _U.putFloat(_p1 + 12, self.m31);
            _U.putFloat(_p2, self.m02);
            _U.putFloat(_p2 + 4, self.m12);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p2 + 12, self.m32);
            return buf;
        }

        private static ByteBuffer storeCMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
            RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4);
            float _c6 = _U.getFloat(address + 8);
            float _c9 = _U.getFloat(address + 12);
            float _c1 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c7 = _U.getFloat(_p1 + 8);
            float _c10 = _U.getFloat(_p1 + 12);
            float _c2 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c11 = _U.getFloat(_p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4);
            float _c6 = _U.getFloat(bytes, address + 8);
            float _c9 = _U.getFloat(bytes, address + 12);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c7 = _U.getFloat(bytes, _p1 + 8);
            float _c10 = _U.getFloat(bytes, _p1 + 12);
            float _c2 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c11 = _U.getFloat(bytes, _p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            _U.putDouble(_p2, self.m02);
            _U.putDouble(_p2 + 8, self.m12);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p2 + 24, self.m32);
            return buf;
        }

        public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8);
            float _c6 = (float) _U.getDouble(address + 16);
            float _c9 = (float) _U.getDouble(address + 24);
            float _c1 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c7 = (float) _U.getDouble(_p1 + 16);
            float _c10 = (float) _U.getDouble(_p1 + 24);
            float _c2 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c11 = (float) _U.getDouble(_p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            _U.putDouble(_p2, self.m02);
            _U.putDouble(_p2 + 8, self.m12);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p2 + 24, self.m32);
            return buf;
        }

        private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
            RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
            return buf;
        }

        public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8);
            float _c6 = (float) _U.getDouble(address + 16);
            float _c9 = (float) _U.getDouble(address + 24);
            float _c1 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c7 = (float) _U.getDouble(_p1 + 16);
            float _c10 = (float) _U.getDouble(_p1 + 24);
            float _c2 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c11 = (float) _U.getDouble(_p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadCMDoubleAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8);
            float _c6 = (float) _U.getDouble(bytes, address + 16);
            float _c9 = (float) _U.getDouble(bytes, address + 24);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c7 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c10 = (float) _U.getDouble(bytes, _p1 + 24);
            float _c2 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c11 = (float) _U.getDouble(bytes, _p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(address + 8, self.m02);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m12);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p3, self.m30);
            _U.putFloat(_p3 + 4, self.m31);
            _U.putFloat(_p3 + 8, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(address + 8);
            float _c3 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            float _c6 = _U.getFloat(_p2);
            float _c7 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c9 = _U.getFloat(_p3);
            float _c10 = _U.getFloat(_p3 + 4);
            float _c11 = _U.getFloat(_p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(address + 8, self.m02);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m12);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p3, self.m30);
            _U.putFloat(_p3 + 4, self.m31);
            _U.putFloat(_p3 + 8, self.m32);
            return buf;
        }

        private static ByteBuffer storeRMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
            RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(address + 8);
            float _c3 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            float _c6 = _U.getFloat(_p2);
            float _c7 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c9 = _U.getFloat(_p3);
            float _c10 = _U.getFloat(_p3 + 4);
            float _c11 = _U.getFloat(_p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, address + 8);
            float _c3 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            float _c6 = _U.getFloat(bytes, _p2);
            float _c7 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c9 = _U.getFloat(bytes, _p3);
            float _c10 = _U.getFloat(bytes, _p3 + 4);
            float _c11 = _U.getFloat(bytes, _p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(address + 16, self.m02);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m12);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            _U.putDouble(_p3 + 16, self.m32);
            return buf;
        }

        public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(address + 16);
            float _c3 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            float _c6 = (float) _U.getDouble(_p2);
            float _c7 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c9 = (float) _U.getDouble(_p3);
            float _c10 = (float) _U.getDouble(_p3 + 8);
            float _c11 = (float) _U.getDouble(_p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(address + 16, self.m02);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m12);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            _U.putDouble(_p3 + 16, self.m32);
            return buf;
        }

        private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
            RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
            return buf;
        }

        public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(address + 16);
            float _c3 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            float _c6 = (float) _U.getDouble(_p2);
            float _c7 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c9 = (float) _U.getDouble(_p3);
            float _c10 = (float) _U.getDouble(_p3 + 8);
            float _c11 = (float) _U.getDouble(_p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        private static Float4x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadRMDoubleAbsolute(index, buf, stride);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, address + 16);
            float _c3 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c6 = (float) _U.getDouble(bytes, _p2);
            float _c7 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c9 = (float) _U.getDouble(bytes, _p3);
            float _c10 = (float) _U.getDouble(bytes, _p3 + 8);
            float _c11 = (float) _U.getDouble(bytes, _p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }
    }

    private abstract static class Float4x3RawOps {
        public abstract Float4x3 storeCMUnsafe(Float4x3 self, long address);
        public abstract Float4x3 loadCMUnsafe(long address);
        public abstract Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address);
        public abstract Float4x3 loadCMDoubleUnsafe(long address);
        public abstract Float4x3 storeRMUnsafe(Float4x3 self, long address);
        public abstract Float4x3 loadRMUnsafe(long address);
        public abstract Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address);
        public abstract Float4x3 loadRMDoubleUnsafe(long address);
        public abstract Float4x3 storeCMUnsafe(Float4x3 self, long address, int stride);
        public abstract Float4x3 loadCMUnsafe(long address, int stride);
        public abstract Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address, int stride);
        public abstract Float4x3 loadCMDoubleUnsafe(long address, int stride);
        public abstract Float4x3 storeRMUnsafe(Float4x3 self, long address, int stride);
        public abstract Float4x3 loadRMUnsafe(long address, int stride);
        public abstract Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address, int stride);
        public abstract Float4x3 loadRMDoubleUnsafe(long address, int stride);
    }

    private static final class Float4x3RawOpsApi extends Float4x3RawOps {
        public Float4x3 storeCMUnsafe(Float4x3 self, long address) {
            self.storeCM(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public Float4x3 loadCMUnsafe(long address) {
            return Float4x3.loadCM(0L, virtualMemory().asSlice(address, 48L));
        }

        public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address) {
            self.storeCMDouble(0L, virtualMemory().asSlice(address, 96L));
            return self;
        }

        public Float4x3 loadCMDoubleUnsafe(long address) {
            return Float4x3.loadCMDouble(0L, virtualMemory().asSlice(address, 96L));
        }

        public Float4x3 storeRMUnsafe(Float4x3 self, long address) {
            self.storeRM(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public Float4x3 loadRMUnsafe(long address) {
            return Float4x3.loadRM(0L, virtualMemory().asSlice(address, 48L));
        }

        public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address) {
            self.storeRMDouble(0L, virtualMemory().asSlice(address, 96L));
            return self;
        }

        public Float4x3 loadRMDoubleUnsafe(long address) {
            return Float4x3.loadRMDouble(0L, virtualMemory().asSlice(address, 96L));
        }

        public Float4x3 storeCMUnsafe(Float4x3 self, long address, int stride) {
            self.storeCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
            return self;
        }

        public Float4x3 loadCMUnsafe(long address, int stride) {
            return Float4x3.loadCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        }

        public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address, int stride) {
            self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
            return self;
        }

        public Float4x3 loadCMDoubleUnsafe(long address, int stride) {
            return Float4x3.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        }

        public Float4x3 storeRMUnsafe(Float4x3 self, long address, int stride) {
            self.storeRM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
            return self;
        }

        public Float4x3 loadRMUnsafe(long address, int stride) {
            return Float4x3.loadRM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        }

        public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address, int stride) {
            self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
            return self;
        }

        public Float4x3 loadRMDoubleUnsafe(long address, int stride) {
            return Float4x3.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        }
    }

    private static final class Float4x3RawOpsUnsafe extends Float4x3RawOps {
        public Float4x3 storeCMUnsafe(Float4x3 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m10);
            _U.putFloat(address + 8L, self.m20);
            _U.putFloat(address + 12L, self.m30);
            _U.putFloat(address + 16L, self.m01);
            _U.putFloat(address + 20L, self.m11);
            _U.putFloat(address + 24L, self.m21);
            _U.putFloat(address + 28L, self.m31);
            _U.putFloat(address + 32L, self.m02);
            _U.putFloat(address + 36L, self.m12);
            _U.putFloat(address + 40L, self.m22);
            _U.putFloat(address + 44L, self.m32);
            return self;
        }

        public Float4x3 storeCMHeap(Float4x3 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m10);
            _U.putFloat(bytes, address + 8L, self.m20);
            _U.putFloat(bytes, address + 12L, self.m30);
            _U.putFloat(bytes, address + 16L, self.m01);
            _U.putFloat(bytes, address + 20L, self.m11);
            _U.putFloat(bytes, address + 24L, self.m21);
            _U.putFloat(bytes, address + 28L, self.m31);
            _U.putFloat(bytes, address + 32L, self.m02);
            _U.putFloat(bytes, address + 36L, self.m12);
            _U.putFloat(bytes, address + 40L, self.m22);
            _U.putFloat(bytes, address + 44L, self.m32);
            return self;
        }

        public Float4x3 loadCMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4L);
            float _c6 = _U.getFloat(address + 8L);
            float _c9 = _U.getFloat(address + 12L);
            float _c1 = _U.getFloat(address + 16L);
            float _c4 = _U.getFloat(address + 20L);
            float _c7 = _U.getFloat(address + 24L);
            float _c10 = _U.getFloat(address + 28L);
            float _c2 = _U.getFloat(address + 32L);
            float _c5 = _U.getFloat(address + 36L);
            float _c8 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadCMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4L);
            float _c6 = _U.getFloat(bytes, address + 8L);
            float _c9 = _U.getFloat(bytes, address + 12L);
            float _c1 = _U.getFloat(bytes, address + 16L);
            float _c4 = _U.getFloat(bytes, address + 20L);
            float _c7 = _U.getFloat(bytes, address + 24L);
            float _c10 = _U.getFloat(bytes, address + 28L);
            float _c2 = _U.getFloat(bytes, address + 32L);
            float _c5 = _U.getFloat(bytes, address + 36L);
            float _c8 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m10);
            _U.putDouble(address + 16L, self.m20);
            _U.putDouble(address + 24L, self.m30);
            _U.putDouble(address + 32L, self.m01);
            _U.putDouble(address + 40L, self.m11);
            _U.putDouble(address + 48L, self.m21);
            _U.putDouble(address + 56L, self.m31);
            _U.putDouble(address + 64L, self.m02);
            _U.putDouble(address + 72L, self.m12);
            _U.putDouble(address + 80L, self.m22);
            _U.putDouble(address + 88L, self.m32);
            return self;
        }

        public Float4x3 storeCMDoubleHeap(Float4x3 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m10);
            _U.putDouble(bytes, address + 16L, self.m20);
            _U.putDouble(bytes, address + 24L, self.m30);
            _U.putDouble(bytes, address + 32L, self.m01);
            _U.putDouble(bytes, address + 40L, self.m11);
            _U.putDouble(bytes, address + 48L, self.m21);
            _U.putDouble(bytes, address + 56L, self.m31);
            _U.putDouble(bytes, address + 64L, self.m02);
            _U.putDouble(bytes, address + 72L, self.m12);
            _U.putDouble(bytes, address + 80L, self.m22);
            _U.putDouble(bytes, address + 88L, self.m32);
            return self;
        }

        public Float4x3 loadCMDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8L);
            float _c6 = (float) _U.getDouble(address + 16L);
            float _c9 = (float) _U.getDouble(address + 24L);
            float _c1 = (float) _U.getDouble(address + 32L);
            float _c4 = (float) _U.getDouble(address + 40L);
            float _c7 = (float) _U.getDouble(address + 48L);
            float _c10 = (float) _U.getDouble(address + 56L);
            float _c2 = (float) _U.getDouble(address + 64L);
            float _c5 = (float) _U.getDouble(address + 72L);
            float _c8 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadCMDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8L);
            float _c6 = (float) _U.getDouble(bytes, address + 16L);
            float _c9 = (float) _U.getDouble(bytes, address + 24L);
            float _c1 = (float) _U.getDouble(bytes, address + 32L);
            float _c4 = (float) _U.getDouble(bytes, address + 40L);
            float _c7 = (float) _U.getDouble(bytes, address + 48L);
            float _c10 = (float) _U.getDouble(bytes, address + 56L);
            float _c2 = (float) _U.getDouble(bytes, address + 64L);
            float _c5 = (float) _U.getDouble(bytes, address + 72L);
            float _c8 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeRMUnsafe(Float4x3 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4L, self.m01);
            _U.putFloat(address + 8L, self.m02);
            _U.putFloat(address + 12L, self.m10);
            _U.putFloat(address + 16L, self.m11);
            _U.putFloat(address + 20L, self.m12);
            _U.putFloat(address + 24L, self.m20);
            _U.putFloat(address + 28L, self.m21);
            _U.putFloat(address + 32L, self.m22);
            _U.putFloat(address + 36L, self.m30);
            _U.putFloat(address + 40L, self.m31);
            _U.putFloat(address + 44L, self.m32);
            return self;
        }

        public Float4x3 storeRMHeap(Float4x3 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4L, self.m01);
            _U.putFloat(bytes, address + 8L, self.m02);
            _U.putFloat(bytes, address + 12L, self.m10);
            _U.putFloat(bytes, address + 16L, self.m11);
            _U.putFloat(bytes, address + 20L, self.m12);
            _U.putFloat(bytes, address + 24L, self.m20);
            _U.putFloat(bytes, address + 28L, self.m21);
            _U.putFloat(bytes, address + 32L, self.m22);
            _U.putFloat(bytes, address + 36L, self.m30);
            _U.putFloat(bytes, address + 40L, self.m31);
            _U.putFloat(bytes, address + 44L, self.m32);
            return self;
        }

        public Float4x3 loadRMUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            float _c10 = _U.getFloat(address + 40L);
            float _c11 = _U.getFloat(address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadRMHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            float _c10 = _U.getFloat(bytes, address + 40L);
            float _c11 = _U.getFloat(bytes, address + 44L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8L, self.m01);
            _U.putDouble(address + 16L, self.m02);
            _U.putDouble(address + 24L, self.m10);
            _U.putDouble(address + 32L, self.m11);
            _U.putDouble(address + 40L, self.m12);
            _U.putDouble(address + 48L, self.m20);
            _U.putDouble(address + 56L, self.m21);
            _U.putDouble(address + 64L, self.m22);
            _U.putDouble(address + 72L, self.m30);
            _U.putDouble(address + 80L, self.m31);
            _U.putDouble(address + 88L, self.m32);
            return self;
        }

        public Float4x3 storeRMDoubleHeap(Float4x3 self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8L, self.m01);
            _U.putDouble(bytes, address + 16L, self.m02);
            _U.putDouble(bytes, address + 24L, self.m10);
            _U.putDouble(bytes, address + 32L, self.m11);
            _U.putDouble(bytes, address + 40L, self.m12);
            _U.putDouble(bytes, address + 48L, self.m20);
            _U.putDouble(bytes, address + 56L, self.m21);
            _U.putDouble(bytes, address + 64L, self.m22);
            _U.putDouble(bytes, address + 72L, self.m30);
            _U.putDouble(bytes, address + 80L, self.m31);
            _U.putDouble(bytes, address + 88L, self.m32);
            return self;
        }

        public Float4x3 loadRMDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            float _c10 = (float) _U.getDouble(address + 80L);
            float _c11 = (float) _U.getDouble(address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadRMDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            float _c10 = (float) _U.getDouble(bytes, address + 80L);
            float _c11 = (float) _U.getDouble(bytes, address + 88L);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeCMUnsafe(Float4x3 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m10);
            _U.putFloat(address + 8, self.m20);
            _U.putFloat(address + 12, self.m30);
            _U.putFloat(_p1, self.m01);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m21);
            _U.putFloat(_p1 + 12, self.m31);
            _U.putFloat(_p2, self.m02);
            _U.putFloat(_p2 + 4, self.m12);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p2 + 12, self.m32);
            return self;
        }

        public Float4x3 storeCMHeap(Float4x3 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m10);
            _U.putFloat(bytes, address + 8, self.m20);
            _U.putFloat(bytes, address + 12, self.m30);
            _U.putFloat(bytes, _p1, self.m01);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m21);
            _U.putFloat(bytes, _p1 + 12, self.m31);
            _U.putFloat(bytes, _p2, self.m02);
            _U.putFloat(bytes, _p2 + 4, self.m12);
            _U.putFloat(bytes, _p2 + 8, self.m22);
            _U.putFloat(bytes, _p2 + 12, self.m32);
            return self;
        }

        public Float4x3 loadCMUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c3 = _U.getFloat(address + 4);
            float _c6 = _U.getFloat(address + 8);
            float _c9 = _U.getFloat(address + 12);
            float _c1 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c7 = _U.getFloat(_p1 + 8);
            float _c10 = _U.getFloat(_p1 + 12);
            float _c2 = _U.getFloat(_p2);
            float _c5 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c11 = _U.getFloat(_p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadCMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c3 = _U.getFloat(bytes, address + 4);
            float _c6 = _U.getFloat(bytes, address + 8);
            float _c9 = _U.getFloat(bytes, address + 12);
            float _c1 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c7 = _U.getFloat(bytes, _p1 + 8);
            float _c10 = _U.getFloat(bytes, _p1 + 12);
            float _c2 = _U.getFloat(bytes, _p2);
            float _c5 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c11 = _U.getFloat(bytes, _p2 + 12);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m10);
            _U.putDouble(address + 16, self.m20);
            _U.putDouble(address + 24, self.m30);
            _U.putDouble(_p1, self.m01);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m21);
            _U.putDouble(_p1 + 24, self.m31);
            _U.putDouble(_p2, self.m02);
            _U.putDouble(_p2 + 8, self.m12);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p2 + 24, self.m32);
            return self;
        }

        public Float4x3 storeCMDoubleHeap(Float4x3 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m10);
            _U.putDouble(bytes, address + 16, self.m20);
            _U.putDouble(bytes, address + 24, self.m30);
            _U.putDouble(bytes, _p1, self.m01);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m21);
            _U.putDouble(bytes, _p1 + 24, self.m31);
            _U.putDouble(bytes, _p2, self.m02);
            _U.putDouble(bytes, _p2 + 8, self.m12);
            _U.putDouble(bytes, _p2 + 16, self.m22);
            _U.putDouble(bytes, _p2 + 24, self.m32);
            return self;
        }

        public Float4x3 loadCMDoubleUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c3 = (float) _U.getDouble(address + 8);
            float _c6 = (float) _U.getDouble(address + 16);
            float _c9 = (float) _U.getDouble(address + 24);
            float _c1 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c7 = (float) _U.getDouble(_p1 + 16);
            float _c10 = (float) _U.getDouble(_p1 + 24);
            float _c2 = (float) _U.getDouble(_p2);
            float _c5 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c11 = (float) _U.getDouble(_p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadCMDoubleHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c3 = (float) _U.getDouble(bytes, address + 8);
            float _c6 = (float) _U.getDouble(bytes, address + 16);
            float _c9 = (float) _U.getDouble(bytes, address + 24);
            float _c1 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c7 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c10 = (float) _U.getDouble(bytes, _p1 + 24);
            float _c2 = (float) _U.getDouble(bytes, _p2);
            float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c11 = (float) _U.getDouble(bytes, _p2 + 24);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeRMUnsafe(Float4x3 self, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.m00);
            _U.putFloat(address + 4, self.m01);
            _U.putFloat(address + 8, self.m02);
            _U.putFloat(_p1, self.m10);
            _U.putFloat(_p1 + 4, self.m11);
            _U.putFloat(_p1 + 8, self.m12);
            _U.putFloat(_p2, self.m20);
            _U.putFloat(_p2 + 4, self.m21);
            _U.putFloat(_p2 + 8, self.m22);
            _U.putFloat(_p3, self.m30);
            _U.putFloat(_p3 + 4, self.m31);
            _U.putFloat(_p3 + 8, self.m32);
            return self;
        }

        public Float4x3 storeRMHeap(Float4x3 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.m00);
            _U.putFloat(bytes, address + 4, self.m01);
            _U.putFloat(bytes, address + 8, self.m02);
            _U.putFloat(bytes, _p1, self.m10);
            _U.putFloat(bytes, _p1 + 4, self.m11);
            _U.putFloat(bytes, _p1 + 8, self.m12);
            _U.putFloat(bytes, _p2, self.m20);
            _U.putFloat(bytes, _p2 + 4, self.m21);
            _U.putFloat(bytes, _p2 + 8, self.m22);
            _U.putFloat(bytes, _p3, self.m30);
            _U.putFloat(bytes, _p3 + 4, self.m31);
            _U.putFloat(bytes, _p3 + 8, self.m32);
            return self;
        }

        public Float4x3 loadRMUnsafe(long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4);
            float _c2 = _U.getFloat(address + 8);
            float _c3 = _U.getFloat(_p1);
            float _c4 = _U.getFloat(_p1 + 4);
            float _c5 = _U.getFloat(_p1 + 8);
            float _c6 = _U.getFloat(_p2);
            float _c7 = _U.getFloat(_p2 + 4);
            float _c8 = _U.getFloat(_p2 + 8);
            float _c9 = _U.getFloat(_p3);
            float _c10 = _U.getFloat(_p3 + 4);
            float _c11 = _U.getFloat(_p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadRMHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 4L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4);
            float _c2 = _U.getFloat(bytes, address + 8);
            float _c3 = _U.getFloat(bytes, _p1);
            float _c4 = _U.getFloat(bytes, _p1 + 4);
            float _c5 = _U.getFloat(bytes, _p1 + 8);
            float _c6 = _U.getFloat(bytes, _p2);
            float _c7 = _U.getFloat(bytes, _p2 + 4);
            float _c8 = _U.getFloat(bytes, _p2 + 8);
            float _c9 = _U.getFloat(bytes, _p3);
            float _c10 = _U.getFloat(bytes, _p3 + 4);
            float _c11 = _U.getFloat(bytes, _p3 + 8);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.m00);
            _U.putDouble(address + 8, self.m01);
            _U.putDouble(address + 16, self.m02);
            _U.putDouble(_p1, self.m10);
            _U.putDouble(_p1 + 8, self.m11);
            _U.putDouble(_p1 + 16, self.m12);
            _U.putDouble(_p2, self.m20);
            _U.putDouble(_p2 + 8, self.m21);
            _U.putDouble(_p2 + 16, self.m22);
            _U.putDouble(_p3, self.m30);
            _U.putDouble(_p3 + 8, self.m31);
            _U.putDouble(_p3 + 16, self.m32);
            return self;
        }

        public Float4x3 storeRMDoubleHeap(Float4x3 self, byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.m00);
            _U.putDouble(bytes, address + 8, self.m01);
            _U.putDouble(bytes, address + 16, self.m02);
            _U.putDouble(bytes, _p1, self.m10);
            _U.putDouble(bytes, _p1 + 8, self.m11);
            _U.putDouble(bytes, _p1 + 16, self.m12);
            _U.putDouble(bytes, _p2, self.m20);
            _U.putDouble(bytes, _p2 + 8, self.m21);
            _U.putDouble(bytes, _p2 + 16, self.m22);
            _U.putDouble(bytes, _p3, self.m30);
            _U.putDouble(bytes, _p3 + 8, self.m31);
            _U.putDouble(bytes, _p3 + 16, self.m32);
            return self;
        }

        public Float4x3 loadRMDoubleUnsafe(long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8);
            float _c2 = (float) _U.getDouble(address + 16);
            float _c3 = (float) _U.getDouble(_p1);
            float _c4 = (float) _U.getDouble(_p1 + 8);
            float _c5 = (float) _U.getDouble(_p1 + 16);
            float _c6 = (float) _U.getDouble(_p2);
            float _c7 = (float) _U.getDouble(_p2 + 8);
            float _c8 = (float) _U.getDouble(_p2 + 16);
            float _c9 = (float) _U.getDouble(_p3);
            float _c10 = (float) _U.getDouble(_p3 + 8);
            float _c11 = (float) _U.getDouble(_p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }

        public Float4x3 loadRMDoubleHeap(byte[] bytes, long address, int stride) {
            long _ps = stride * 8L;
            long _p1 = address + _ps;
            long _p2 = _p1 + _ps;
            long _p3 = _p2 + _ps;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8);
            float _c2 = (float) _U.getDouble(bytes, address + 16);
            float _c3 = (float) _U.getDouble(bytes, _p1);
            float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
            float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
            float _c6 = (float) _U.getDouble(bytes, _p2);
            float _c7 = (float) _U.getDouble(bytes, _p2 + 8);
            float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
            float _c9 = (float) _U.getDouble(bytes, _p3);
            float _c10 = (float) _U.getDouble(bytes, _p3 + 8);
            float _c11 = (float) _U.getDouble(bytes, _p3 + 16);
            return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
        }
    }
}
