// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a 2D vector of {@code long} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Long2}. APIs that only read a 2D vector
 * should accept {@code Long2R}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code Long2R} must be instances created by the library ({@link Joml} factories
 * / the library's own types); the implementations read cached state through the library's own
 * classes, so foreign implementations of the {@code *R} interfaces are not supported as arguments.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it. Only instances of this library's implementation compare equal to each other;
 * the {@code equals} of a vector never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * larger-minus-smaller difference is compared as an unsigned value ({@code Long.compareUnsigned}),
 * so the two are compared exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface Long2R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** {@return a shared all-zero vector} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value. */
    public static Long2R ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Long2 add(Long2R other, @Mutated Long2 dest);

    /**
     * Add ({@code x}, {@code y}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 add(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilDiv(long scalar, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilDiv(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilDiv(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilMod(long scalar, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilMod(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 ceilMod(long x, long y, @Mutated Long2 dest);

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 div(long scalar, @Mutated Long2 dest);

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 div(Long2R other, @Mutated Long2 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}) (integer division, truncating
     * toward zero) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 div(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorDiv(long scalar, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorDiv(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorDiv(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorMod(long scalar, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorMod(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 floorMod(long x, long y, @Mutated Long2 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Long2 mul(long scalar, @Mutated Long2 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Long2 mul(Long2R other, @Mutated Long2 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 mul(long x, long y, @Mutated Long2 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 negate(@Mutated Long2 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long2 rem(long scalar, @Mutated Long2 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long2 rem(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y}) (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 rem(long x, long y, @Mutated Long2 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Long2 sub(Long2R other, @Mutated Long2 dest);

    /**
     * Subtract ({@code x}, {@code y}) from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 sub(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    Long2 and(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 and(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 bitCount(@Mutated Long2 dest);

    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 not(@Mutated Long2 dest);

    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 numberOfLeadingZeros(@Mutated Long2 dest);

    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 numberOfTrailingZeros(@Mutated Long2 dest);

    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    Long2 or(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 or(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 reverseBits(@Mutated Long2 dest);

    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 reverseBytes(@Mutated Long2 dest);

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Long2 rotateLeft(long distance, @Mutated Long2 dest);

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Long2 rotateRight(long distance, @Mutated Long2 dest);

    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long2 shl(long shift, @Mutated Long2 dest);

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long2 shr(long shift, @Mutated Long2 dest);

    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long2 ushr(long shift, @Mutated Long2 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    Long2 xor(Long2R other, @Mutated Long2 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 xor(long x, long y, @Mutated Long2 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Long2 set(long s, @Mutated Long2 dest);

    /**
     * Convert this vector to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 toFloat(@Mutated Float2 dest);

    /**
     * Convert this vector to {@code double} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 toDouble(@Mutated Double2 dest);

    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Byte2 toByte(@Mutated Byte2 dest);

    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Short2 toShort(@Mutated Short2 dest);

    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 toInt(@Mutated Int2 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 absolute(@Mutated Long2 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Long2 clamp(long min, long max, @Mutated Long2 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Long2 clamp(Long2R min, Long2R max, @Mutated Long2 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 clamp(long minX, long minY, long maxX, long maxY, @Mutated Long2 dest);

    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    long compAdd();

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    long compMax();

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    long compMin();

    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    long compMul();

    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when {@code other} points
     * counter-clockwise of this vector (with the x axis pointing right and the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return the 2D cross product of this vector and {@code other}, in that order
     */
    long cross(Long2R other);

    /**
     * Compute the 2D cross product of this vector and ({@code x}, {@code y}), in that order.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when ({@code x}, {@code y}) points
     * counter-clockwise of this vector (with the x axis pointing right and the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the 2D cross product of this vector and ({@code x}, {@code y}), in that order
     */
    long cross(long x, long y);

    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    long distanceSquared(Long2R other);

    /**
     * Compute the squared distance between this vector and ({@code x}, {@code y}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the squared distance between this vector and ({@code x}, {@code y})
     */
    long distanceSquared(long x, long y);

    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    long dot(Long2R other);

    /**
     * Compute the dot product of this vector and ({@code x}, {@code y}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the dot product of this vector and ({@code x}, {@code y})
     */
    long dot(long x, long y);

    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    long lengthSquared();

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    long manhattanDistance(Long2R other);

    /**
     * Compute the Manhattan distance between this vector and ({@code x}, {@code y}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the Manhattan distance between this vector and ({@code x}, {@code y})
     */
    long manhattanDistance(long x, long y);

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    long manhattanLength();

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Long2 max(long scalar, @Mutated Long2 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Long2 max(Long2R other, @Mutated Long2 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 max(long x, long y, @Mutated Long2 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Long2 min(long scalar, @Mutated Long2 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Long2 min(Long2R other, @Mutated Long2 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 min(long x, long y, @Mutated Long2 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 sign(@Mutated Long2 dest);

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Long2 satAdd(Long2R other, @Mutated Long2 dest);

    /**
     * Add ({@code x}, {@code y}) to this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 satAdd(long x, long y, @Mutated Long2 dest);

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Long2 satMul(Long2R other, @Mutated Long2 dest);

    /**
     * Multiply this vector by ({@code x}, {@code y}), clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 satMul(long x, long y, @Mutated Long2 dest);

    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 satNegate(@Mutated Long2 dest);

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Long2 satSub(Long2R other, @Mutated Long2 dest);

    /**
     * Subtract ({@code x}, {@code y}) from this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Long2 satSub(long x, long y, @Mutated Long2 dest);
    /** {@return the value of the {@code x} component} <p>Valid input: any value. */
    long x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value. */
    long y();

    /**
     * Swizzle: copy ({@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 xx(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 xy(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 yx(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 yy(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xxx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xxy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xyx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xyy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yxx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yxy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yyx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yyy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyyy(@Mutated Long4 dest);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default long[] store(@Mutated long[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    long[] store(@Mutated long[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer store(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer storeAbsolute(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    LongBuffer storeAbsolute(int index, @Mutated LongBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default LongBuffer storeRelative(@Mutated LongBuffer dest) {
        if (dest.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 2);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeAbsolute(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment store(@Mutated MemorySegment dest) { return store(0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment store(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    Long2 storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default int[] store(@Mutated int[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    int[] store(@Mutated int[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer store(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer storeAbsolute(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    IntBuffer storeAbsolute(int index, @Mutated IntBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default IntBuffer storeRelative(@Mutated IntBuffer dest) {
        if (dest.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 2);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code int},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeInt(@Mutated ByteBuffer dest) { return storeIntAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code int},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeIntAbsolute(@Mutated ByteBuffer dest) { return storeIntAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code int},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeIntAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code int},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeIntRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeIntAbsolute(pos, dest);
        dest.position(pos + 8);
        return dest;
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code int}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment storeInt(@Mutated MemorySegment dest) { return storeInt(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code int},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment storeInt(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address, converting each element to {@code int}.
     * No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    Long2 storeIntUnsafe(long address);

    /** {@return whether all components of this vector are finite} Integer components always are, so this always returns {@code true}. <p>Valid input: any value. */
    boolean isFinite();

    /** {@return whether any component of this vector is NaN} Integer components never are, so this always returns {@code false}. <p>Valid input: any value. */
    boolean isNaN();

    /**
     * Compare this vector component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance:
     * the larger-minus-smaller difference is compared as an unsigned value ({@code
     * Long.compareUnsigned}), so the two are compared exactly without overflow, and a negative
     * {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(Long2R other, long epsilon);

    /**
     * Compare this vector with the given object for element-wise equality.
     * <p>
     * Each component is compared with {@code ==}. Use {@link #equalsEpsilon} for a tolerant
     * comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a vector of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components alone.
     * <p>
     * Valid input: any value.
     *
     * @return the hash code of this vector
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final Long2R ZERO = Joml.long2(0L, 0L);
    }
}
