// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;

/**
 * A 4D vector of {@code short} components.
 * <p>
 * Extends the read-only view {@link Short4R} with the mutating surface: in-place self-forms,
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
public interface Short4 extends Short4R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 add(Short4R other) { return add(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 add(short x, short y, short z, short w) { return add(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ceilDiv(short scalar) { return ceilDiv(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ceilDiv(Short4R other) { return ceilDiv(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 ceilDiv(short x, short y, short z, short w) { return ceilDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ceilMod(short scalar) { return ceilMod(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ceilMod(Short4R other) { return ceilMod(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 ceilMod(short x, short y, short z, short w) { return ceilMod(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 div(short scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 div(Short4R other) { return div(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 div(short x, short y, short z, short w) { return div(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 floorDiv(short scalar) { return floorDiv(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 floorDiv(Short4R other) { return floorDiv(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 floorDiv(short x, short y, short z, short w) { return floorDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 floorMod(short scalar) { return floorMod(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 floorMod(Short4R other) { return floorMod(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 floorMod(short x, short y, short z, short w) { return floorMod(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 mul(short scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 mul(Short4R other) { return mul(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 mul(short x, short y, short z, short w) { return mul(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 negate() { return negate(Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 rem(short scalar) { return rem(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 rem(Short4R other) { return rem(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 rem(short x, short y, short z, short w) { return rem(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 sub(Short4R other) { return sub(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 sub(short x, short y, short z, short w) { return sub(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 and(Short4R other) { return and(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 and(short x, short y, short z, short w) { return and(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the number of one-bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 bitCount() { return bitCount(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the bitwise NOT of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 not() { return not(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the number of leading zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 numberOfLeadingZeros() { return numberOfLeadingZeros(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the number of trailing zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 numberOfTrailingZeros() { return numberOfTrailingZeros(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 or(Short4R other) { return or(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 or(short x, short y, short z, short w) { return or(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the bit-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 reverseBits() { return reverseBits(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the byte-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 reverseBytes() { return reverseBytes(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 rotateLeft(short distance) { return rotateLeft(distance, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 rotateRight(short distance) { return rotateRight(distance, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 shl(short shift) { return shl(shift, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 shr(short shift) { return shr(shift, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 ushr(short shift) { return ushr(shift, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xor(Short4R other) { return xor(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 xor(short x, short y, short z, short w) { return xor(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Short4 set(Short4R v);

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
    @Mutated Short4 set(short x, short y, short z, short w);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Short4 set(short s) { return set(s, this); }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float4} holding the result
     */
    default Float4 toFloat() { return toFloat(Joml.float4()); }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
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
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int4} holding the result
     */
    default Int4 toInt() { return toInt(Joml.int4()); }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long4} holding the result
     */
    default Long4 toLong() { return toLong(Joml.long4()); }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Short4 makeZero();

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 absolute() { return absolute(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 clamp(short min, short max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 clamp(Short4R min, Short4R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW) { return clamp(minX, minY, minZ, minW, maxX, maxY, maxZ, maxW, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 cross(Short4R v, Short4R w) { return cross(v, w, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW) { return cross(vX, vY, vZ, vW, wX, wY, wZ, wW, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 max(short scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 max(Short4R other) { return max(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 max(short x, short y, short z, short w) { return max(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 min(short scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 min(Short4R other) { return min(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 min(short x, short y, short z, short w) { return min(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 sign() { return sign(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 satAdd(Short4R other) { return satAdd(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 satAdd(short x, short y, short z, short w) { return satAdd(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 satMul(Short4R other) { return satMul(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 satMul(short x, short y, short z, short w) { return satMul(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Negate this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 satNegate() { return satNegate(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 satSub(Short4R other) { return satSub(other, Joml.RETURN_NEW ? Joml.short4() : this); }

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
    @Mutated default Short4 satSub(short x, short y, short z, short w) { return satSub(x, y, z, w, Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxxx() { return xxxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxxy() { return xxxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxxz() { return xxxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxxw() { return xxxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxyx() { return xxyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxyy() { return xxyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxyz() { return xxyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxyw() { return xxyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxzx() { return xxzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxzy() { return xxzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxzz() { return xxzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxzw() { return xxzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxwx() { return xxwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxwy() { return xxwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxwz() { return xxwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xxww() { return xxww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyxx() { return xyxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyxy() { return xyxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyxz() { return xyxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyxw() { return xyxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyyx() { return xyyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyyy() { return xyyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyyz() { return xyyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyyw() { return xyyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyzx() { return xyzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyzy() { return xyzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyzz() { return xyzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyzw() { return xyzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xywx() { return xywx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xywy() { return xywy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xywz() { return xywz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xyww() { return xyww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzxx() { return xzxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzxy() { return xzxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzxz() { return xzxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzxw() { return xzxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzyx() { return xzyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzyy() { return xzyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzyz() { return xzyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzyw() { return xzyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzzx() { return xzzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzzy() { return xzzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzzz() { return xzzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzzw() { return xzzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzwx() { return xzwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzwy() { return xzwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzwz() { return xzwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xzww() { return xzww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwxx() { return xwxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwxy() { return xwxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwxz() { return xwxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwxw() { return xwxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwyx() { return xwyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwyy() { return xwyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwyz() { return xwyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwyw() { return xwyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwzx() { return xwzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwzy() { return xwzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwzz() { return xwzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwzw() { return xwzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwwx() { return xwwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwwy() { return xwwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwwz() { return xwwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 xwww() { return xwww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxxx() { return yxxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxxy() { return yxxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxxz() { return yxxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxxw() { return yxxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxyx() { return yxyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxyy() { return yxyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxyz() { return yxyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxyw() { return yxyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxzx() { return yxzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxzy() { return yxzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxzz() { return yxzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxzw() { return yxzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxwx() { return yxwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxwy() { return yxwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxwz() { return yxwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yxww() { return yxww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyxx() { return yyxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyxy() { return yyxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyxz() { return yyxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyxw() { return yyxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyyx() { return yyyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyyy() { return yyyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyyz() { return yyyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyyw() { return yyyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyzx() { return yyzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyzy() { return yyzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyzz() { return yyzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyzw() { return yyzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yywx() { return yywx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yywy() { return yywy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yywz() { return yywz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yyww() { return yyww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzxx() { return yzxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzxy() { return yzxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzxz() { return yzxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzxw() { return yzxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzyx() { return yzyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzyy() { return yzyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzyz() { return yzyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzyw() { return yzyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzzx() { return yzzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzzy() { return yzzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzzz() { return yzzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzzw() { return yzzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzwx() { return yzwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzwy() { return yzwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzwz() { return yzwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 yzww() { return yzww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywxx() { return ywxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywxy() { return ywxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywxz() { return ywxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywxw() { return ywxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywyx() { return ywyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywyy() { return ywyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywyz() { return ywyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywyw() { return ywyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywzx() { return ywzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywzy() { return ywzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywzz() { return ywzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywzw() { return ywzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywwx() { return ywwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywwy() { return ywwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywwz() { return ywwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 ywww() { return ywww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxxx() { return zxxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxxy() { return zxxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxxz() { return zxxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxxw() { return zxxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxyx() { return zxyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxyy() { return zxyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxyz() { return zxyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxyw() { return zxyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxzx() { return zxzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxzy() { return zxzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxzz() { return zxzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxzw() { return zxzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxwx() { return zxwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxwy() { return zxwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxwz() { return zxwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zxww() { return zxww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyxx() { return zyxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyxy() { return zyxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyxz() { return zyxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyxw() { return zyxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyyx() { return zyyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyyy() { return zyyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyyz() { return zyyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyyw() { return zyyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyzx() { return zyzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyzy() { return zyzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyzz() { return zyzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyzw() { return zyzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zywx() { return zywx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zywy() { return zywy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zywz() { return zywz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zyww() { return zyww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzxx() { return zzxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzxy() { return zzxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzxz() { return zzxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzxw() { return zzxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzyx() { return zzyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzyy() { return zzyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzyz() { return zzyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzyw() { return zzyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzzx() { return zzzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzzy() { return zzzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzzz() { return zzzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzzw() { return zzzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzwx() { return zzwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzwy() { return zzwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzwz() { return zzwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zzww() { return zzww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwxx() { return zwxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwxy() { return zwxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwxz() { return zwxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwxw() { return zwxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwyx() { return zwyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwyy() { return zwyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwyz() { return zwyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwyw() { return zwyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwzx() { return zwzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwzy() { return zwzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwzz() { return zwzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwzw() { return zwzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwwx() { return zwwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwwy() { return zwwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwwz() { return zwwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 zwww() { return zwww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxxx() { return wxxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxxy() { return wxxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxxz() { return wxxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxxw() { return wxxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxyx() { return wxyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxyy() { return wxyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxyz() { return wxyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxyw() { return wxyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxzx() { return wxzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxzy() { return wxzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxzz() { return wxzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxzw() { return wxzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxwx() { return wxwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxwy() { return wxwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxwz() { return wxwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wxww() { return wxww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyxx() { return wyxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyxy() { return wyxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyxz() { return wyxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyxw() { return wyxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyyx() { return wyyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyyy() { return wyyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyyz() { return wyyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyyw() { return wyyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyzx() { return wyzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyzy() { return wyzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyzz() { return wyzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyzw() { return wyzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wywx() { return wywx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wywy() { return wywy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wywz() { return wywz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wyww() { return wyww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzxx() { return wzxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzxy() { return wzxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzxz() { return wzxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzxw() { return wzxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzyx() { return wzyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzyy() { return wzyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzyz() { return wzyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzyw() { return wzyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzzx() { return wzzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzzy() { return wzzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzzz() { return wzzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzzw() { return wzzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzwx() { return wzwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzwy() { return wzwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzwz() { return wzwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wzww() { return wzww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwxx() { return wwxx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwxy() { return wwxy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwxz() { return wwxz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwxw() { return wwxw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwyx() { return wwyx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwyy() { return wwyy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwyz() { return wwyz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwyw() { return wwyw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwzx() { return wwzx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwzy() { return wwzy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwzz() { return wwzz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwzw() { return wwzw(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwwx() { return wwwx(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwwy() { return wwwy(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwwz() { return wwwz(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Short4 wwww() { return wwww(Joml.RETURN_NEW ? Joml.short4() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Short4 load(short[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Short4 load(short[] src, int offset);

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
    @Mutated default Short4 load(ShortBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Short4 loadAbsolute(ShortBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Short4 loadAbsolute(int index, ShortBuffer src);

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
    @Mutated default Short4 loadRelative(ShortBuffer src) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Short4 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Short4 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Short4 loadAbsolute(int index, ByteBuffer src);

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
    @Mutated default Short4 loadRelative(ByteBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 8);
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
    @Mutated Short4 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Short4 load(byte[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Short4 load(byte[] src, int offset);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short4 loadByte(ByteBuffer src) { return loadByteAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short4 loadByteAbsolute(ByteBuffer src) { return loadByteAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated Short4 loadByteAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code byte},
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
    @Mutated default Short4 loadByteRelative(ByteBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadByteAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code byte}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Short4 loadByteUnsafe(long address);
}
