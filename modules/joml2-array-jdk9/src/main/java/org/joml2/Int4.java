// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;

/**
 * A 4D vector of {@code int} components.
 * <p>
 * Extends the read-only view {@link Int4R} with the mutating surface: in-place self-forms, setters
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
 * difference is widened to {@code long} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface Int4 extends Int4R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 add(Int4R other) { return add(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 add(int x, int y, int z, int w) { return add(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ceilDiv(int scalar) { return ceilDiv(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise ceiling division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ceilDiv(Int4R other) { return ceilDiv(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 ceilDiv(int x, int y, int z, int w) { return ceilDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ceilMod(int scalar) { return ceilMod(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ceilMod(Int4R other) { return ceilMod(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 ceilMod(int x, int y, int z, int w) { return ceilMod(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 div(int scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero).
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 div(Int4R other) { return div(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 div(int x, int y, int z, int w) { return div(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 floorDiv(int scalar) { return floorDiv(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise floor division of this vector by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 floorDiv(Int4R other) { return floorDiv(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 floorDiv(int x, int y, int z, int w) { return floorDiv(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 floorMod(int scalar) { return floorMod(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the component-wise floor modulus of this vector and {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 floorMod(Int4R other) { return floorMod(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 floorMod(int x, int y, int z, int w) { return floorMod(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 mul(int scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 mul(Int4R other) { return mul(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 mul(int x, int y, int z, int w) { return mul(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 negate() { return negate(Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 rem(int scalar) { return rem(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 rem(Int4R other) { return rem(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 rem(int x, int y, int z, int w) { return rem(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 sub(Int4R other) { return sub(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 sub(int x, int y, int z, int w) { return sub(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the bitwise AND of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 and(Int4R other) { return and(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 and(int x, int y, int z, int w) { return and(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the number of one-bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 bitCount() { return bitCount(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the bitwise NOT of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 not() { return not(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the number of leading zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 numberOfLeadingZeros() { return numberOfLeadingZeros(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the number of trailing zero bits of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 numberOfTrailingZeros() { return numberOfTrailingZeros(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the bitwise OR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 or(Int4R other) { return or(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 or(int x, int y, int z, int w) { return or(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the bit-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 reverseBits() { return reverseBits(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the byte-reversed value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 reverseBytes() { return reverseBytes(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 rotateLeft(int distance) { return rotateLeft(distance, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 rotateRight(int distance) { return rotateRight(distance, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Shift each component of this vector left by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 shl(int shift) { return shl(shift, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 shr(int shift) { return shr(shift, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Logically shift each component of this vector right by {@code shift} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ushr(int shift) { return ushr(shift, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xor(Int4R other) { return xor(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 xor(int x, int y, int z, int w) { return xor(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Int4 set(Int4R v);

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
    @Mutated Int4 set(int x, int y, int z, int w);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Int4 set(int s) { return set(s, this); }

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
    @Mutated Int4 makeZero();

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 absolute() { return absolute(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 clamp(int min, int max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 clamp(Int4R min, Int4R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW) { return clamp(minX, minY, minZ, minW, maxX, maxY, maxZ, maxW, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 cross(Int4R v, Int4R w) { return cross(v, w, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW) { return cross(vX, vY, vZ, vW, wX, wY, wZ, wW, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 max(int scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 max(Int4R other) { return max(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 max(int x, int y, int z, int w) { return max(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 min(int scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 min(Int4R other) { return min(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 min(int x, int y, int z, int w) { return min(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 sign() { return sign(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 satAdd(Int4R other) { return satAdd(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 satAdd(int x, int y, int z, int w) { return satAdd(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 satMul(Int4R other) { return satMul(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 satMul(int x, int y, int z, int w) { return satMul(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Negate this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 satNegate() { return satNegate(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 satSub(Int4R other) { return satSub(other, Joml.RETURN_NEW ? Joml.int4() : this); }

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
    @Mutated default Int4 satSub(int x, int y, int z, int w) { return satSub(x, y, z, w, Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxxx() { return xxxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxxy() { return xxxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxxz() { return xxxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxxw() { return xxxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxyx() { return xxyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxyy() { return xxyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxyz() { return xxyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxyw() { return xxyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxzx() { return xxzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxzy() { return xxzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxzz() { return xxzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxzw() { return xxzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxwx() { return xxwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxwy() { return xxwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxwz() { return xxwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xxww() { return xxww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyxx() { return xyxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyxy() { return xyxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyxz() { return xyxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyxw() { return xyxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyyx() { return xyyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyyy() { return xyyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyyz() { return xyyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyyw() { return xyyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyzx() { return xyzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyzy() { return xyzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyzz() { return xyzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyzw() { return xyzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xywx() { return xywx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xywy() { return xywy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xywz() { return xywz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xyww() { return xyww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzxx() { return xzxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzxy() { return xzxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzxz() { return xzxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzxw() { return xzxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzyx() { return xzyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzyy() { return xzyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzyz() { return xzyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzyw() { return xzyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzzx() { return xzzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzzy() { return xzzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzzz() { return xzzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzzw() { return xzzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzwx() { return xzwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzwy() { return xzwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzwz() { return xzwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xzww() { return xzww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwxx() { return xwxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwxy() { return xwxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwxz() { return xwxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwxw() { return xwxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwyx() { return xwyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwyy() { return xwyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwyz() { return xwyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwyw() { return xwyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwzx() { return xwzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwzy() { return xwzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwzz() { return xwzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwzw() { return xwzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwwx() { return xwwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwwy() { return xwwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwwz() { return xwwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 xwww() { return xwww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxxx() { return yxxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxxy() { return yxxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxxz() { return yxxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxxw() { return yxxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxyx() { return yxyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxyy() { return yxyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxyz() { return yxyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxyw() { return yxyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxzx() { return yxzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxzy() { return yxzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxzz() { return yxzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxzw() { return yxzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxwx() { return yxwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxwy() { return yxwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxwz() { return yxwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yxww() { return yxww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyxx() { return yyxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyxy() { return yyxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyxz() { return yyxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyxw() { return yyxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyyx() { return yyyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyyy() { return yyyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyyz() { return yyyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyyw() { return yyyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyzx() { return yyzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyzy() { return yyzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyzz() { return yyzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyzw() { return yyzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yywx() { return yywx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yywy() { return yywy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yywz() { return yywz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yyww() { return yyww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzxx() { return yzxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzxy() { return yzxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzxz() { return yzxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzxw() { return yzxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzyx() { return yzyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzyy() { return yzyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzyz() { return yzyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzyw() { return yzyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzzx() { return yzzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzzy() { return yzzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzzz() { return yzzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzzw() { return yzzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzwx() { return yzwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzwy() { return yzwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzwz() { return yzwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 yzww() { return yzww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywxx() { return ywxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywxy() { return ywxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywxz() { return ywxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywxw() { return ywxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywyx() { return ywyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywyy() { return ywyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywyz() { return ywyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywyw() { return ywyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywzx() { return ywzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywzy() { return ywzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywzz() { return ywzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywzw() { return ywzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywwx() { return ywwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywwy() { return ywwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywwz() { return ywwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 ywww() { return ywww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxxx() { return zxxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxxy() { return zxxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxxz() { return zxxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxxw() { return zxxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxyx() { return zxyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxyy() { return zxyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxyz() { return zxyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxyw() { return zxyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxzx() { return zxzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxzy() { return zxzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxzz() { return zxzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxzw() { return zxzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxwx() { return zxwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxwy() { return zxwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxwz() { return zxwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zxww() { return zxww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyxx() { return zyxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyxy() { return zyxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyxz() { return zyxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyxw() { return zyxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyyx() { return zyyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyyy() { return zyyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyyz() { return zyyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyyw() { return zyyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyzx() { return zyzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyzy() { return zyzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyzz() { return zyzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyzw() { return zyzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zywx() { return zywx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zywy() { return zywy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zywz() { return zywz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zyww() { return zyww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzxx() { return zzxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzxy() { return zzxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzxz() { return zzxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzxw() { return zzxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzyx() { return zzyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzyy() { return zzyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzyz() { return zzyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzyw() { return zzyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzzx() { return zzzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzzy() { return zzzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzzz() { return zzzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzzw() { return zzzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzwx() { return zzwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzwy() { return zzwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzwz() { return zzwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zzww() { return zzww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwxx() { return zwxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwxy() { return zwxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwxz() { return zwxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwxw() { return zwxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwyx() { return zwyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwyy() { return zwyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwyz() { return zwyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwyw() { return zwyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwzx() { return zwzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwzy() { return zwzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwzz() { return zwzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwzw() { return zwzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwwx() { return zwwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwwy() { return zwwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwwz() { return zwwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 zwww() { return zwww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxxx() { return wxxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxxy() { return wxxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxxz() { return wxxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxxw() { return wxxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxyx() { return wxyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxyy() { return wxyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxyz() { return wxyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxyw() { return wxyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxzx() { return wxzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxzy() { return wxzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxzz() { return wxzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxzw() { return wxzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxwx() { return wxwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxwy() { return wxwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxwz() { return wxwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wxww() { return wxww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyxx() { return wyxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyxy() { return wyxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyxz() { return wyxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyxw() { return wyxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyyx() { return wyyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyyy() { return wyyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyyz() { return wyyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyyw() { return wyyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyzx() { return wyzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyzy() { return wyzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyzz() { return wyzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyzw() { return wyzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wywx() { return wywx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wywy() { return wywy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wywz() { return wywz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wyww() { return wyww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzxx() { return wzxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzxy() { return wzxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzxz() { return wzxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzxw() { return wzxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzyx() { return wzyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzyy() { return wzyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzyz() { return wzyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzyw() { return wzyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzzx() { return wzzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzzy() { return wzzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzzz() { return wzzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzzw() { return wzzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzwx() { return wzwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzwy() { return wzwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzwz() { return wzwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wzww() { return wzww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwxx() { return wwxx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwxy() { return wwxy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwxz() { return wwxz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwxw() { return wwxw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwyx() { return wwyx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwyy() { return wwyy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwyz() { return wwyz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwyw() { return wwyw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwzx() { return wwzx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwzy() { return wwzy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwzz() { return wwzz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwzw() { return wwzw(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwwx() { return wwwx(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwwy() { return wwwy(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwwz() { return wwwz(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Int4 wwww() { return wwww(Joml.RETURN_NEW ? Joml.int4() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Int4 load(int[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Int4 load(int[] src, int offset);

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
    @Mutated default Int4 load(IntBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Int4 loadAbsolute(IntBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Int4 loadAbsolute(int index, IntBuffer src);

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
    @Mutated default Int4 loadRelative(IntBuffer src) {
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
    @Mutated default Int4 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Int4 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Int4 loadAbsolute(int index, ByteBuffer src);

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
    @Mutated default Int4 loadRelative(ByteBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 16);
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
    @Mutated Int4 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Int4 load(long[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Int4 load(long[] src, int offset);

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
    @Mutated default Int4 load(LongBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated default Int4 loadAbsolute(LongBuffer src) { return loadAbsolute(src.position(), src); }

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
    @Mutated Int4 loadAbsolute(int index, LongBuffer src);

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
    @Mutated default Int4 loadRelative(LongBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
    @Mutated default Int4 loadLong(ByteBuffer src) { return loadLongAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
    @Mutated default Int4 loadLongAbsolute(ByteBuffer src) { return loadLongAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
    @Mutated Int4 loadLongAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
    @Mutated default Int4 loadLongRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadLongAbsolute(pos, src);
        src.position(pos + 32);
        return this;
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Int4 loadLongUnsafe(long address);
}
