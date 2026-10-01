// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

/**
 * Read-only view of a 3D vector of {@code long} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Long3}. APIs that only read a 3D vector
 * should accept {@code Long3R}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code Long3R} must be instances created by the library ({@link Joml} factories
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
public interface Long3R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

    /** {@return a shared all-zero vector} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value. */
    public static Long3R ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Long3 add(Long3R other, @Mutated Long3 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 add(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 ceilDiv(long scalar, @Mutated Long3 dest);

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
    Long3 ceilDiv(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 ceilDiv(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 ceilMod(long scalar, @Mutated Long3 dest);

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
    Long3 ceilMod(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 ceilMod(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 div(long scalar, @Mutated Long3 dest);

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
    Long3 div(Long3R other, @Mutated Long3 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 div(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 floorDiv(long scalar, @Mutated Long3 dest);

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
    Long3 floorDiv(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 floorDiv(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 floorMod(long scalar, @Mutated Long3 dest);

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
    Long3 floorMod(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 floorMod(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 mul(long scalar, @Mutated Long3 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Long3 mul(Long3R other, @Mutated Long3 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 mul(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 negate(@Mutated Long3 dest);

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
    Long3 rem(long scalar, @Mutated Long3 dest);

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
    Long3 rem(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so it
     * pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 rem(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Long3 sub(Long3R other, @Mutated Long3 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 sub(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 and(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 and(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 bitCount(@Mutated Long3 dest);

    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 not(@Mutated Long3 dest);

    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 numberOfLeadingZeros(@Mutated Long3 dest);

    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 numberOfTrailingZeros(@Mutated Long3 dest);

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
    Long3 or(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 or(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 reverseBits(@Mutated Long3 dest);

    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 reverseBytes(@Mutated Long3 dest);

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
    Long3 rotateLeft(long distance, @Mutated Long3 dest);

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
    Long3 rotateRight(long distance, @Mutated Long3 dest);

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
    Long3 shl(long shift, @Mutated Long3 dest);

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
    Long3 shr(long shift, @Mutated Long3 dest);

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
    Long3 ushr(long shift, @Mutated Long3 dest);

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
    Long3 xor(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 xor(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Long3 set(long s, @Mutated Long3 dest);

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
    Float3 toFloat(@Mutated Float3 dest);

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
    Double3 toDouble(@Mutated Double3 dest);

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
    Byte3 toByte(@Mutated Byte3 dest);

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
    Short3 toShort(@Mutated Short3 dest);

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
    Int3 toInt(@Mutated Int3 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 absolute(@Mutated Long3 dest);

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
    Long3 clamp(long min, long max, @Mutated Long3 dest);

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
    Long3 clamp(Long3R min, Long3R max, @Mutated Long3 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 clamp(long minX, long minY, long minZ, long maxX, long maxY, long maxZ, @Mutated Long3 dest);

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
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Long3 cross(Long3R other, @Mutated Long3 dest);

    /**
     * Compute the cross product of this vector and ({@code x}, {@code y}, {@code z}), in that order
     * ({@code this x (x, y, z)}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 cross(long x, long y, long z, @Mutated Long3 dest);

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
    long distanceSquared(Long3R other);

    /**
     * Compute the squared distance between this vector and ({@code x}, {@code y}, {@code z}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return the squared distance between this vector and ({@code x}, {@code y}, {@code z})
     */
    long distanceSquared(long x, long y, long z);

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
    long dot(Long3R other);

    /**
     * Compute the dot product of this vector and ({@code x}, {@code y}, {@code z}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return the dot product of this vector and ({@code x}, {@code y}, {@code z})
     */
    long dot(long x, long y, long z);

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
    long manhattanDistance(Long3R other);

    /**
     * Compute the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z})
     */
    long manhattanDistance(long x, long y, long z);

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
    Long3 max(long scalar, @Mutated Long3 dest);

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
    Long3 max(Long3R other, @Mutated Long3 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 max(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 min(long scalar, @Mutated Long3 dest);

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
    Long3 min(Long3R other, @Mutated Long3 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 min(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 sign(@Mutated Long3 dest);

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
    Long3 satAdd(Long3R other, @Mutated Long3 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 satAdd(long x, long y, long z, @Mutated Long3 dest);

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
    Long3 satMul(Long3R other, @Mutated Long3 dest);

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}), clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 satMul(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 satNegate(@Mutated Long3 dest);

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
    Long3 satSub(Long3R other, @Mutated Long3 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Long3 satSub(long x, long y, long z, @Mutated Long3 dest);

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyz0(@Mutated Long4 dest);

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyz1(@Mutated Long4 dest);
    /** {@return the value of the {@code x} component} <p>Valid input: any value. */
    long x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value. */
    long y();
    /** {@return the value of the {@code z} component} <p>Valid input: any value. */
    long z();

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
     * Swizzle: copy ({@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 xz(@Mutated Long2 dest);

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
     * Swizzle: copy ({@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 yz(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 zx(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 zy(@Mutated Long2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 zz(@Mutated Long2 dest);

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
     * Swizzle: copy ({@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xxz(@Mutated Long3 dest);

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
     * Swizzle: copy ({@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xyz(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xzx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xzy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 xzz(@Mutated Long3 dest);

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
     * Swizzle: copy ({@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yxz(@Mutated Long3 dest);

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
     * Swizzle: copy ({@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yyz(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yzx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yzy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 yzz(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zxx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zxy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zxz(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zyx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zyy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zyz(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zzx(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zzy(@Mutated Long3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long3 zzz(@Mutated Long3 dest);

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
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxxz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xxzz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyxz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xyzz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzxz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 xzzz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxxz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yxzz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyxz(@Mutated Long4 dest);

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
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yyzz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzxz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 yzzz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxxz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zxzz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyxz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zyzz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzxx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzxy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzxz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzyx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzyy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzyz(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzzx(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzzy(@Mutated Long4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 zzzz(@Mutated Long4 dest);

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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default LongBuffer storeRelative(@Mutated LongBuffer dest) {
        if (dest.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 3);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 24);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    Long3 storeUnsafe(long address);

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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default IntBuffer storeRelative(@Mutated IntBuffer dest) {
        if (dest.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 3);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code int},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeIntRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeIntAbsolute(pos, dest);
        dest.position(pos + 12);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to {@code int}.
     * No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    Long3 storeIntUnsafe(long address);

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
    boolean equalsEpsilon(Long3R other, long epsilon);

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
        static final Long3R ZERO = Joml.long3(0L, 0L, 0L);
    }
}
