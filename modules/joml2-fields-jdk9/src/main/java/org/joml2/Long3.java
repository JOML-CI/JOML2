// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.LongBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

/**
 * A 3D vector of {@code long} components.
 * <p>
 * Extends the read-only view {@link Long3R} with the mutating surface: in-place self-forms, setters
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
public interface Long3 extends Long3R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 add(Long3R other) { return add(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}) to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 add(long x, long y, long z) { return add(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilDiv(long scalar) { return ceilDiv(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilDiv(Long3R other) { return ceilDiv(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilDiv(long x, long y, long z) { return ceilDiv(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilMod(long scalar) { return ceilMod(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilMod(Long3R other) { return ceilMod(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ceilMod(long x, long y, long z) { return ceilMod(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 div(long scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 div(Long3R other) { return div(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}) (integer division,
     * truncating toward zero).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 div(long x, long y, long z) { return div(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorDiv(long scalar) { return floorDiv(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorDiv(Long3R other) { return floorDiv(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorDiv(long x, long y, long z) { return floorDiv(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorMod(long scalar) { return floorMod(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorMod(Long3R other) { return floorMod(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 floorMod(long x, long y, long z) { return floorMod(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 mul(long scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 mul(Long3R other) { return mul(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 mul(long x, long y, long z) { return mul(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 negate() { return negate(Joml.RETURN_NEW ? Joml.long3() : this); }

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
    @Mutated default Long3 rem(long scalar) { return rem(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

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
    @Mutated default Long3 rem(Long3R other) { return rem(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so it
     * pairs with {@code div}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 rem(long x, long y, long z) { return rem(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 sub(Long3R other) { return sub(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Subtract ({@code x}, {@code y}, {@code z}) from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 sub(long x, long y, long z) { return sub(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 and(Long3R other) { return and(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 and(long x, long y, long z) { return and(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the number of one-bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 bitCount() { return bitCount(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise NOT of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 not() { return not(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the number of leading zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 numberOfLeadingZeros() { return numberOfLeadingZeros(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the number of trailing zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 numberOfTrailingZeros() { return numberOfTrailingZeros(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 or(Long3R other) { return or(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 or(long x, long y, long z) { return or(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bit-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 reverseBits() { return reverseBits(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the byte-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 reverseBytes() { return reverseBytes(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 rotateLeft(long distance) { return rotateLeft(distance, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 rotateRight(long distance) { return rotateRight(distance, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Shift each component of this vector left by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 shl(long shift) { return shl(shift, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 shr(long shift) { return shr(shift, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Logically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 ushr(long shift) { return ushr(shift, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xor(Long3R other) { return xor(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xor(long x, long y, long z) { return xor(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Long3 set(Long3R v);

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated Long3 set(long x, long y, long z);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Long3 set(long s) { return set(s, this); }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float3} holding the result
     */
    default Float3 toFloat() { return toFloat(Joml.float3()); }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3} holding the result
     */
    default Double3 toDouble() { return toDouble(Joml.double3()); }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte3} holding the result
     */
    default Byte3 toByte() { return toByte(Joml.byte3()); }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short3} holding the result
     */
    default Short3 toShort() { return toShort(Joml.short3()); }

    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int3} holding the result
     */
    default Int3 toInt() { return toInt(Joml.int3()); }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Long3 makeZero();

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 absolute() { return absolute(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 clamp(long min, long max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 clamp(Long3R min, Long3R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 clamp(long minX, long minY, long minZ, long maxX, long maxY, long maxZ) { return clamp(minX, minY, minZ, maxX, maxY, maxZ, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 cross(Long3R other) { return cross(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the cross product of this vector and ({@code x}, {@code y}, {@code z}), in that order
     * ({@code this x (x, y, z)}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 cross(long x, long y, long z) { return cross(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 max(long scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 max(Long3R other) { return max(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 max(long x, long y, long z) { return max(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 min(long scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 min(Long3R other) { return min(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 min(long x, long y, long z) { return min(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 sign() { return sign(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satAdd(Long3R other) { return satAdd(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}) to this vector, clamping to the value range instead of
     * overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satAdd(long x, long y, long z) { return satAdd(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satMul(Long3R other) { return satMul(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}), clamping to the value range
     * instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satMul(long x, long y, long z) { return satMul(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Negate this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satNegate() { return satNegate(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satSub(Long3R other) { return satSub(other, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Subtract ({@code x}, {@code y}, {@code z}) from this vector, clamping to the value range
     * instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 satSub(long x, long y, long z) { return satSub(x, y, z, Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xxx() { return xxx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xxy() { return xxy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xxz() { return xxz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xyx() { return xyx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xyy() { return xyy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xyz() { return xyz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xzx() { return xzx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xzy() { return xzy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 xzz() { return xzz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yxx() { return yxx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yxy() { return yxy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yxz() { return yxz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yyx() { return yyx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yyy() { return yyy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yyz() { return yyz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yzx() { return yzx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yzy() { return yzy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 yzz() { return yzz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zxx() { return zxx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zxy() { return zxy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zxz() { return zxz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zyx() { return zyx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zyy() { return zyy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zyz() { return zyz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zzx() { return zzx(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zzy() { return zzy(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Long3 zzz() { return zzz(Joml.RETURN_NEW ? Joml.long3() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Long3 load(long[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Long3 load(long[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long3 load(LongBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long3 loadAbsolute(LongBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Long3 loadAbsolute(int index, LongBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long3 loadRelative(LongBuffer src) {
        if (src.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 3);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long3 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long3 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Long3 loadAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long3 loadRelative(ByteBuffer src) {
        if (src.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 24);
        return this;
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Long3 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Long3 load(int[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Long3 load(int[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long3 load(IntBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Long3 loadAbsolute(IntBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Long3 loadAbsolute(int index, IntBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long3 loadRelative(IntBuffer src) {
        if (src.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 3);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long3 loadInt(ByteBuffer src) { return loadIntAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Long3 loadIntAbsolute(ByteBuffer src) { return loadIntAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Long3 loadIntAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code int},
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Long3 loadIntRelative(ByteBuffer src) {
        if (src.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadIntAbsolute(pos, src);
        src.position(pos + 12);
        return this;
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code int}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Long3 loadIntUnsafe(long address);
}
