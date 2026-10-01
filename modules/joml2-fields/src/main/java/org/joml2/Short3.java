// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * A 3D vector of {@code short} components.
 * <p>
 * Extends the read-only view {@link Short3R} with the mutating surface: in-place self-forms,
 * setters and load methods. A self-form operation writes its result to {@code this} and returns it
 * - unless the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this}
 * unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it. Only instances of this library's implementation compare equal to each other;
 * the {@code equals} of a vector never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code int} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface Short3 extends Short3R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 add(Short3R other) { return add(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 add(short x, short y, short z) { return add(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 ceilDiv(short scalar) { return ceilDiv(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 ceilDiv(Short3R other) { return ceilDiv(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 ceilDiv(short x, short y, short z) { return ceilDiv(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 ceilMod(short scalar) { return ceilMod(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 ceilMod(Short3R other) { return ceilMod(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 ceilMod(short x, short y, short z) { return ceilMod(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 div(short scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 div(Short3R other) { return div(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 div(short x, short y, short z) { return div(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 floorDiv(short scalar) { return floorDiv(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 floorDiv(Short3R other) { return floorDiv(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 floorDiv(short x, short y, short z) { return floorDiv(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 floorMod(short scalar) { return floorMod(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 floorMod(Short3R other) { return floorMod(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 floorMod(short x, short y, short z) { return floorMod(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 mul(short scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 mul(Short3R other) { return mul(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 mul(short x, short y, short z) { return mul(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 negate() { return negate(Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 rem(short scalar) { return rem(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 rem(Short3R other) { return rem(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 rem(short x, short y, short z) { return rem(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 sub(Short3R other) { return sub(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 sub(short x, short y, short z) { return sub(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 and(Short3R other) { return and(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 and(short x, short y, short z) { return and(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the number of one-bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 bitCount() { return bitCount(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the bitwise NOT of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 not() { return not(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the number of leading zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 numberOfLeadingZeros() { return numberOfLeadingZeros(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the number of trailing zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 numberOfTrailingZeros() { return numberOfTrailingZeros(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 or(Short3R other) { return or(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 or(short x, short y, short z) { return or(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the bit-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 reverseBits() { return reverseBits(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the byte-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 reverseBytes() { return reverseBytes(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 rotateLeft(short distance) { return rotateLeft(distance, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 rotateRight(short distance) { return rotateRight(distance, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 shl(short shift) { return shl(shift, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 shr(short shift) { return shr(shift, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 ushr(short shift) { return ushr(shift, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xor(Short3R other) { return xor(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 xor(short x, short y, short z) { return xor(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Short3 set(Short3R v);

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
    @Mutated Short3 set(short x, short y, short z);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Short3 set(short s) { return set(s, this); }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float3} holding the result
     */
    default Float3 toFloat() { return toFloat(Joml.float3()); }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
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
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int3} holding the result
     */
    default Int3 toInt() { return toInt(Joml.int3()); }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long3} holding the result
     */
    default Long3 toLong() { return toLong(Joml.long3()); }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Short3 makeZero();

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 absolute() { return absolute(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 clamp(short min, short max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 clamp(Short3R min, Short3R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ) { return clamp(minX, minY, minZ, maxX, maxY, maxZ, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 cross(Short3R other) { return cross(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 cross(short x, short y, short z) { return cross(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 max(short scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 max(Short3R other) { return max(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 max(short x, short y, short z) { return max(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 min(short scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 min(Short3R other) { return min(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 min(short x, short y, short z) { return min(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 sign() { return sign(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 satAdd(Short3R other) { return satAdd(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 satAdd(short x, short y, short z) { return satAdd(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 satMul(Short3R other) { return satMul(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 satMul(short x, short y, short z) { return satMul(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Negate this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 satNegate() { return satNegate(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 satSub(Short3R other) { return satSub(other, Joml.RETURN_NEW ? Joml.short3() : this); }

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
    @Mutated default Short3 satSub(short x, short y, short z) { return satSub(x, y, z, Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xxx() { return xxx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xxy() { return xxy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xxz() { return xxz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xyx() { return xyx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xyy() { return xyy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xyz() { return xyz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xzx() { return xzx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xzy() { return xzy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 xzz() { return xzz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yxx() { return yxx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yxy() { return yxy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yxz() { return yxz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yyx() { return yyx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yyy() { return yyy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yyz() { return yyz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yzx() { return yzx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yzy() { return yzy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 yzz() { return yzz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zxx() { return zxx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zxy() { return zxy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zxz() { return zxz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zyx() { return zyx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zyy() { return zyy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zyz() { return zyz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zzx() { return zzx(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zzy() { return zzy(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short3 zzz() { return zzz(Joml.RETURN_NEW ? Joml.short3() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Short3 load(short[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Short3 load(short[] src, int offset);

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
    @Mutated default Short3 load(ShortBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Short3 loadAbsolute(ShortBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Short3 loadAbsolute(int index, ShortBuffer src);

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
    @Mutated default Short3 loadRelative(ShortBuffer src) {
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Short3 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Short3 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Short3 loadAbsolute(int index, ByteBuffer src);

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
    @Mutated default Short3 loadRelative(ByteBuffer src) {
        if (src.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 6);
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
    @Mutated default Short3 load(MemorySegment src) { return load(0L, src); }

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
    @Mutated Short3 load(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Short3 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Short3 load(byte[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Short3 load(byte[] src, int offset);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short3 loadByte(ByteBuffer src) { return loadByteAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short3 loadByteAbsolute(ByteBuffer src) { return loadByteAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated Short3 loadByteAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short3 loadByteRelative(ByteBuffer src) {
        if (src.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadByteAbsolute(pos, src);
        src.position(pos + 3);
        return this;
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code byte}.
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
    @Mutated default Short3 loadByte(MemorySegment src) { return loadByte(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code byte},
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
    @Mutated Short3 loadByte(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code byte}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Short3 loadByteUnsafe(long address);
}
