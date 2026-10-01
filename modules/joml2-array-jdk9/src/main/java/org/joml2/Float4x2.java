// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

/**
 * A 4x2 matrix of single-precision {@code float} components.
 * <p>
 * Extends the read-only view {@link Float4x2R} with the mutating surface: in-place self-forms,
 * setters and load methods. A self-form operation writes its result to {@code this} and returns it
 * - unless the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this}
 * unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a matrix
 * never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface Float4x2 extends Float4x2R {

    /**
     * Add {@code other} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 add(Float4x2R other) { return add(other, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) to this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 add(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) { return add(m00, m01, m10, m11, m20, m21, m30, m31, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 mul(float scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Negate this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 negate() { return negate(Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Subtract {@code other} from this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 sub(Float4x2R other) { return sub(other, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) from this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 sub(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) { return sub(m00, m01, m10, m11, m20, m21, m30, m31, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated Float4x2 set(Float4x2R v);

    /**
     * Set this matrix to the given values.
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
     * @return this
     */
    @Mutated Float4x2 set(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31);

    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4x2} holding the result
     */
    default Double4x2 toDouble() { return toDouble(Joml.double4x2()); }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float4x2 makeIdentity();

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 lerp(Float4x2R other, float t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}, {@code m30}, {@code m31}) using the interpolation
     * factor {@code t}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}, {@code m30},
     * {@code m31}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 lerp(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float t) { return lerp(m00, m01, m10, m11, m20, m21, m30, m31, t, Joml.RETURN_NEW ? Joml.float4x2() : this); }

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
    @Mutated default Float4x2 mul(Float2x2R right) { return mul(right, Joml.RETURN_NEW ? Joml.float4x2() : this); }

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
    @Mutated default Float4x2 preMul(Float4x4R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 addScaled(Float4x2R other, float weight) { return addScaled(other, weight, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) scaled by {@code weight} to this matrix.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4x2 addScaled(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float weight) { return addScaled(m00, m01, m10, m11, m20, m21, m30, m31, weight, Joml.RETURN_NEW ? Joml.float4x2() : this); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4x2 loadCM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadCM(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadCMAbsolute(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(FloatBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 8);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadCM(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadCMAbsolute(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 32);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadCMUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4x2 loadCM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadCM(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadCMAbsolute(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(DoubleBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 8);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadCMDouble(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadCMDoubleAbsolute(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
     * modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src);
        src.position(pos + 64);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadCMDoubleUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4x2 loadRM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadRM(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadRMAbsolute(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(FloatBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 8);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadRM(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadRMAbsolute(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 32);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadRMUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4x2 loadRM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadRM(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 loadRMAbsolute(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(DoubleBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 8);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadRMDouble(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 loadRMDoubleAbsolute(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src);
        src.position(pos + 64);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadRMDoubleUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 loadCM(FloatBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 2 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 loadCM(ByteBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + (2 * stride) * 4);
        return this;
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
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadCMUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 loadCM(DoubleBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 2 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 loadCMDouble(ByteBuffer src, int stride) { return loadCMDoubleAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadCMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src, stride);
        src.position(pos + (2 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadCMDoubleUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float4x2 loadRM(FloatBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float4x2 loadRM(ByteBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 4);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadRMUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float4x2 loadRM(DoubleBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float4x2 loadRMDouble(ByteBuffer src, int stride) { return loadRMDoubleAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4x2 loadRMDoubleUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 load(float[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Float4x2 load(float[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 load(FloatBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 load(int index, FloatBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4x2 load(double[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Float4x2 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 load(DoubleBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4x2 load(int index, DoubleBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 load(ByteBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4x2 load(int index, ByteBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated default Float4x2 loadUnsafe(long address) { return loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 load(int index, FloatBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 load(int index, DoubleBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float4x2 load(int index, ByteBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4x2 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated default Float4x2 loadUnsafe(long address, int stride) { return loadCMUnsafe(address, stride); }
}
