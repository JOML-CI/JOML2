// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * A 4D vector of {@code long} components.
 * <p>
 * Extends the read-only view {@link Long4R} with the mutating surface: in-place self-forms, setters
 * and load methods. A self-form operation writes its result to {@code this} and returns it - unless
 * the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this} unchanged and
 * returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it. Only instances of this library's implementation compare equal to each other;
 * the {@code equals} of a vector never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * larger-minus-smaller difference is compared as an unsigned value ({@code Long.compareUnsigned}),
 * so the two are compared exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface Long4 extends Long4R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 add(Long4R other) { return add(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 add(long x, long y, long z, long w) { return add(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilDiv(long scalar) { return ceilDiv(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilDiv(Long4R other) { return ceilDiv(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}, {@code w}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilDiv(long x, long y, long z, long w) { return ceilDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilMod(long scalar) { return ceilMod(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilMod(Long4R other) { return ceilMod(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ceilMod(long x, long y, long z, long w) { return ceilMod(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 div(long scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 div(Long4R other) { return div(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) (integer
     * division, truncating toward zero).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 div(long x, long y, long z, long w) { return div(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorDiv(long scalar) { return floorDiv(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorDiv(Long4R other) { return floorDiv(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorDiv(long x, long y, long z, long w) { return floorDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorMod(long scalar) { return floorMod(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorMod(Long4R other) { return floorMod(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 floorMod(long x, long y, long z, long w) { return floorMod(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 mul(long scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 mul(Long4R other) { return mul(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 mul(long x, long y, long z, long w) { return mul(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 negate() { return negate(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 rem(long scalar) { return rem(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 rem(Long4R other) { return rem(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) (the remainder carries the sign of the dividend, exactly Java's
     * {@code %}, so it pairs with {@code div}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 rem(long x, long y, long z, long w) { return rem(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 sub(Long4R other) { return sub(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 sub(long x, long y, long z, long w) { return sub(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 and(Long4R other) { return and(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 and(long x, long y, long z, long w) { return and(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the number of one-bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 bitCount() { return bitCount(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise NOT of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 not() { return not(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the number of leading zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 numberOfLeadingZeros() { return numberOfLeadingZeros(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the number of trailing zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 numberOfTrailingZeros() { return numberOfTrailingZeros(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 or(Long4R other) { return or(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 or(long x, long y, long z, long w) { return or(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bit-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 reverseBits() { return reverseBits(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the byte-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 reverseBytes() { return reverseBytes(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 rotateLeft(long distance) { return rotateLeft(distance, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 rotateRight(long distance) { return rotateRight(distance, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Shift each component of this vector left by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 shl(long shift) { return shl(shift, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 shr(long shift) { return shr(shift, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Logically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ushr(long shift) { return ushr(shift, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xor(Long4R other) { return xor(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xor(long x, long y, long z, long w) { return xor(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Long4 set(Long4R v);

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this
     */
    @Mutated Long4 set(long x, long y, long z, long w);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Long4 set(long s) { return set(s, this); }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float4} holding the result
     */
    default Float4 toFloat() { return toFloat(Joml.float4()); }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4} holding the result
     */
    default Double4 toDouble() { return toDouble(Joml.double4()); }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte4} holding the result
     */
    default Byte4 toByte() { return toByte(Joml.byte4()); }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short4} holding the result
     */
    default Short4 toShort() { return toShort(Joml.short4()); }

    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int4} holding the result
     */
    default Int4 toInt() { return toInt(Joml.int4()); }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Long4 makeZero();

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 absolute() { return absolute(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 clamp(long min, long max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 clamp(Long4R min, Long4R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}).
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 clamp(long minX, long minY, long minZ, long minW, long maxX, long maxY, long maxZ, long maxW) { return clamp(minX, minY, minZ, minW, maxX, maxY, maxZ, maxW, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 cross(Long4R v, Long4R w) { return cross(v, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param wX the {@code x} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wY the {@code y} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wZ the {@code z} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wW the {@code w} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 cross(long vX, long vY, long vZ, long vW, long wX, long wY, long wZ, long wW) { return cross(vX, vY, vZ, vW, wX, wY, wZ, wW, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 max(long scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 max(Long4R other) { return max(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 max(long x, long y, long z, long w) { return max(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 min(long scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 min(Long4R other) { return min(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 min(long x, long y, long z, long w) { return min(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 sign() { return sign(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satAdd(Long4R other) { return satAdd(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector, clamping to the value range
     * instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satAdd(long x, long y, long z, long w) { return satAdd(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satMul(Long4R other) { return satMul(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}, {@code w}), clamping to the value
     * range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satMul(long x, long y, long z, long w) { return satMul(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Negate this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satNegate() { return satNegate(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satSub(Long4R other) { return satSub(other, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector, clamping to the value
     * range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 satSub(long x, long y, long z, long w) { return satSub(x, y, z, w, Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxxx() { return xxxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxxy() { return xxxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxxz() { return xxxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxxw() { return xxxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxyx() { return xxyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxyy() { return xxyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxyz() { return xxyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxyw() { return xxyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxzx() { return xxzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxzy() { return xxzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxzz() { return xxzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxzw() { return xxzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxwx() { return xxwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxwy() { return xxwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxwz() { return xxwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xxww() { return xxww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyxx() { return xyxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyxy() { return xyxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyxz() { return xyxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyxw() { return xyxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyyx() { return xyyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyyy() { return xyyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyyz() { return xyyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyyw() { return xyyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyzx() { return xyzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyzy() { return xyzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyzz() { return xyzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyzw() { return xyzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xywx() { return xywx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xywy() { return xywy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xywz() { return xywz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xyww() { return xyww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzxx() { return xzxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzxy() { return xzxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzxz() { return xzxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzxw() { return xzxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzyx() { return xzyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzyy() { return xzyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzyz() { return xzyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzyw() { return xzyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzzx() { return xzzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzzy() { return xzzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzzz() { return xzzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzzw() { return xzzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzwx() { return xzwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzwy() { return xzwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzwz() { return xzwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xzww() { return xzww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwxx() { return xwxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwxy() { return xwxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwxz() { return xwxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwxw() { return xwxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwyx() { return xwyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwyy() { return xwyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwyz() { return xwyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwyw() { return xwyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwzx() { return xwzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwzy() { return xwzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwzz() { return xwzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwzw() { return xwzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwwx() { return xwwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwwy() { return xwwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwwz() { return xwwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 xwww() { return xwww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxxx() { return yxxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxxy() { return yxxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxxz() { return yxxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxxw() { return yxxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxyx() { return yxyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxyy() { return yxyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxyz() { return yxyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxyw() { return yxyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxzx() { return yxzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxzy() { return yxzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxzz() { return yxzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxzw() { return yxzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxwx() { return yxwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxwy() { return yxwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxwz() { return yxwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yxww() { return yxww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyxx() { return yyxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyxy() { return yyxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyxz() { return yyxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyxw() { return yyxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyyx() { return yyyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyyy() { return yyyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyyz() { return yyyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyyw() { return yyyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyzx() { return yyzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyzy() { return yyzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyzz() { return yyzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyzw() { return yyzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yywx() { return yywx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yywy() { return yywy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yywz() { return yywz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yyww() { return yyww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzxx() { return yzxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzxy() { return yzxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzxz() { return yzxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzxw() { return yzxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzyx() { return yzyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzyy() { return yzyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzyz() { return yzyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzyw() { return yzyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzzx() { return yzzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzzy() { return yzzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzzz() { return yzzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzzw() { return yzzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzwx() { return yzwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzwy() { return yzwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzwz() { return yzwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 yzww() { return yzww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywxx() { return ywxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywxy() { return ywxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywxz() { return ywxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywxw() { return ywxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywyx() { return ywyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywyy() { return ywyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywyz() { return ywyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywyw() { return ywyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywzx() { return ywzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywzy() { return ywzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywzz() { return ywzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywzw() { return ywzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywwx() { return ywwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywwy() { return ywwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywwz() { return ywwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 ywww() { return ywww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxxx() { return zxxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxxy() { return zxxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxxz() { return zxxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxxw() { return zxxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxyx() { return zxyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxyy() { return zxyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxyz() { return zxyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxyw() { return zxyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxzx() { return zxzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxzy() { return zxzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxzz() { return zxzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxzw() { return zxzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxwx() { return zxwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxwy() { return zxwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxwz() { return zxwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zxww() { return zxww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyxx() { return zyxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyxy() { return zyxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyxz() { return zyxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyxw() { return zyxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyyx() { return zyyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyyy() { return zyyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyyz() { return zyyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyyw() { return zyyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyzx() { return zyzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyzy() { return zyzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyzz() { return zyzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyzw() { return zyzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zywx() { return zywx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zywy() { return zywy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zywz() { return zywz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zyww() { return zyww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzxx() { return zzxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzxy() { return zzxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzxz() { return zzxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzxw() { return zzxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzyx() { return zzyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzyy() { return zzyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzyz() { return zzyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzyw() { return zzyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzzx() { return zzzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzzy() { return zzzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzzz() { return zzzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzzw() { return zzzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzwx() { return zzwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzwy() { return zzwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzwz() { return zzwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zzww() { return zzww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwxx() { return zwxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwxy() { return zwxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwxz() { return zwxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwxw() { return zwxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwyx() { return zwyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwyy() { return zwyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwyz() { return zwyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwyw() { return zwyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwzx() { return zwzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwzy() { return zwzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwzz() { return zwzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwzw() { return zwzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwwx() { return zwwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwwy() { return zwwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwwz() { return zwwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 zwww() { return zwww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxxx() { return wxxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxxy() { return wxxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxxz() { return wxxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxxw() { return wxxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxyx() { return wxyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxyy() { return wxyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxyz() { return wxyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxyw() { return wxyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxzx() { return wxzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxzy() { return wxzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxzz() { return wxzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxzw() { return wxzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxwx() { return wxwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxwy() { return wxwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxwz() { return wxwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wxww() { return wxww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyxx() { return wyxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyxy() { return wyxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyxz() { return wyxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyxw() { return wyxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyyx() { return wyyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyyy() { return wyyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyyz() { return wyyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyyw() { return wyyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyzx() { return wyzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyzy() { return wyzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyzz() { return wyzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyzw() { return wyzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wywx() { return wywx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wywy() { return wywy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wywz() { return wywz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wyww() { return wyww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzxx() { return wzxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzxy() { return wzxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzxz() { return wzxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzxw() { return wzxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzyx() { return wzyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzyy() { return wzyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzyz() { return wzyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzyw() { return wzyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzzx() { return wzzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzzy() { return wzzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzzz() { return wzzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzzw() { return wzzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzwx() { return wzwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzwy() { return wzwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzwz() { return wzwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wzww() { return wzww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwxx() { return wwxx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwxy() { return wwxy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwxz() { return wwxz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwxw() { return wwxw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwyx() { return wwyx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwyy() { return wwyy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwyz() { return wwyz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwyw() { return wwyw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwzx() { return wwzx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwzy() { return wwzy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwzz() { return wwzz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwzw() { return wwzw(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwwx() { return wwwx(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwwy() { return wwwy(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwwz() { return wwwz(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long4 wwww() { return wwww(Joml.RETURN_NEW ? Joml.long4() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Long4 load(long[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Long4 load(long[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long4 load(LongBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long4 loadAbsolute(LongBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Long4 loadAbsolute(int index, LongBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long4 loadRelative(LongBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long4 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long4 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Long4 loadAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long4 loadRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 32);
        return this;
    }

    /**
     * Load the elements from the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Long4 load(MemorySegment src) { return load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Long4 load(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Long4 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Long4 load(int[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Long4 load(int[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long4 load(IntBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long4 loadAbsolute(IntBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Long4 loadAbsolute(int index, IntBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long4 loadRelative(IntBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long4 loadInt(ByteBuffer src) { return loadIntAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long4 loadIntAbsolute(ByteBuffer src) { return loadIntAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Long4 loadIntAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long4 loadIntRelative(ByteBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadIntAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code int}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Long4 loadInt(MemorySegment src) { return loadInt(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code int},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Long4 loadInt(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code int}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Long4 loadIntUnsafe(long address);
}
