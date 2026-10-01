// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * Immutable 2D vector of {@code byte} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code int} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 */
public record Byte2(byte x, byte y) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 2;

    /** The zero vector (all components 0). */
    public static final Byte2 ZERO = new Byte2((byte) 0, (byte) 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public Byte2(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: any value.
     */
    public Byte2() {
        this((byte) 0, (byte) 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Byte2(byte s) {
        this(s, s);
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public byte x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value. */
    public byte y() { return y; }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Byte2 add(Byte2 other) {
        return add(other.x(), other.y());
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 add(byte otherX, byte otherY) {
        return new Byte2((byte) (otherX + this.x), (byte) (otherY + this.y));
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 ceilDiv(byte scalar) {
        return ceilDiv(scalar, scalar);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 ceilDiv(Byte2 other) {
        return ceilDiv(other.x(), other.y());
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 ceilDiv(byte otherX, byte otherY) {
        return new Byte2((byte) (java.lang.Math.ceilDiv(this.x, otherX)), (byte) (java.lang.Math.ceilDiv(this.y, otherY)));
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 ceilMod(byte scalar) {
        return ceilMod(scalar, scalar);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 ceilMod(Byte2 other) {
        return ceilMod(other.x(), other.y());
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 ceilMod(byte otherX, byte otherY) {
        return new Byte2((byte) (java.lang.Math.ceilMod(this.x, otherX)), (byte) (java.lang.Math.ceilMod(this.y, otherY)));
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero), returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 div(byte scalar) {
        return new Byte2((byte) (this.x / scalar), (byte) (this.y / scalar));
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward
     * zero), returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 div(Byte2 other) {
        return div(other.x(), other.y());
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 div(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x / otherX), (byte) (this.y / otherY));
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 floorDiv(byte scalar) {
        return floorDiv(scalar, scalar);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 floorDiv(Byte2 other) {
        return floorDiv(other.x(), other.y());
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY}),
     * returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 floorDiv(byte otherX, byte otherY) {
        return new Byte2((byte) (Math.floorDiv(this.x, otherX)), (byte) (Math.floorDiv(this.y, otherY)));
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 floorMod(byte scalar) {
        return floorMod(scalar, scalar);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 floorMod(Byte2 other) {
        return floorMod(other.x(), other.y());
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY}),
     * returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 floorMod(byte otherX, byte otherY) {
        return new Byte2((byte) (Math.floorMod(this.x, otherX)), (byte) (Math.floorMod(this.y, otherY)));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Byte2 mul(byte scalar) {
        return mul(scalar, scalar);
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Byte2 mul(Byte2 other) {
        return mul(other.x(), other.y());
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}), returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 mul(byte otherX, byte otherY) {
        return new Byte2((byte) (otherX * this.x), (byte) (otherY * this.y));
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 negate() {
        return new Byte2((byte) (-this.x), (byte) (-this.y));
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}), returning the result as a value.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return the resulting vector
     */
    public Byte2 rem(byte scalar) {
        return rem(scalar, scalar);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return the resulting vector
     */
    public Byte2 rem(Byte2 other) {
        return rem(other.x(), other.y());
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 rem(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x % otherX), (byte) (this.y % otherY));
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Byte2 sub(Byte2 other) {
        return sub(other.x(), other.y());
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 sub(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x - otherX), (byte) (this.y - otherY));
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @return the resulting vector
     */
    public Byte2 and(Byte2 other) {
        return and(other.x(), other.y());
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX},
     * {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 and(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x & otherX), (byte) (this.y & otherY));
    }


    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 bitCount() {
        return new Byte2((byte) (Math.bitCount(this.x)), (byte) (Math.bitCount(this.y)));
    }


    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 not() {
        return new Byte2((byte) (~this.x), (byte) (~this.y));
    }


    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 numberOfLeadingZeros() {
        return new Byte2((byte) (Math.numberOfLeadingZeros(this.x)), (byte) (Math.numberOfLeadingZeros(this.y)));
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 numberOfTrailingZeros() {
        return new Byte2((byte) (Math.numberOfTrailingZeros(this.x)), (byte) (Math.numberOfTrailingZeros(this.y)));
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @return the resulting vector
     */
    public Byte2 or(Byte2 other) {
        return or(other.x(), other.y());
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 or(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x | otherX), (byte) (this.y | otherY));
    }


    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 reverseBits() {
        return new Byte2((byte) (Math.reverseBits(this.x)), (byte) (Math.reverseBits(this.y)));
    }


    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 reverseBytes() {
        return new Byte2((byte) (Math.reverseBytes(this.x)), (byte) (Math.reverseBytes(this.y)));
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return the resulting vector
     */
    public Byte2 rotateLeft(byte distance) {
        return new Byte2((byte) (Math.rotateLeft(this.x, distance)), (byte) (Math.rotateLeft(this.y, distance)));
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @return the resulting vector
     */
    public Byte2 rotateRight(byte distance) {
        return new Byte2((byte) (Math.rotateRight(this.x, distance)), (byte) (Math.rotateRight(this.y, distance)));
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Byte2 shl(byte shift) {
        return new Byte2((byte) (this.x << (shift & 7)), (byte) (this.y << (shift & 7)));
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Byte2 shr(byte shift) {
        return new Byte2((byte) (this.x >> (shift & 7)), (byte) (this.y >> (shift & 7)));
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Byte2 ushr(byte shift) {
        return new Byte2((byte) ((this.x & 0xFF) >>> (shift & 7)), (byte) ((this.y & 0xFF) >>> (shift & 7)));
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other}, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @return the resulting vector
     */
    public Byte2 xor(Byte2 other) {
        return xor(other.x(), other.y());
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX},
     * {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 xor(byte otherX, byte otherY) {
        return new Byte2((byte) (this.x ^ otherX), (byte) (this.y ^ otherY));
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Byte2 set(Byte2 v) {
        return set(v.x(), v.y());
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting vector
     */
    public Byte2 set(byte vX, byte vY) {
        return new Byte2((byte) (vX), (byte) (vY));
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Byte2 set(byte s) {
        return new Byte2((byte) (s), (byte) (s));
    }


    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float2} holding the result
     */
    public Float2 toFloat() {
        return new Float2(this.x, this.y);
    }


    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double2} holding the result
     */
    public Double2 toDouble() {
        return new Double2(this.x, this.y);
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short2} holding the result
     */
    public Short2 toShort() {
        return new Short2((short) (this.x), (short) (this.y));
    }


    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int2} holding the result
     */
    public Int2 toInt() {
        return new Int2(this.x, this.y);
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long2} holding the result
     */
    public Long2 toLong() {
        return new Long2(this.x, this.y);
    }


    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Byte2 makeZero() {
        return Byte2.ZERO;
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 absolute() {
        return new Byte2((byte) (Math.abs(this.x)), (byte) (Math.abs(this.y)));
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max}, returning the result
     * as a value.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return the resulting vector
     */
    public Byte2 clamp(byte min, byte max) {
        return new Byte2((byte) (Math.min(Math.max(this.x, min), max)), (byte) (Math.min(Math.max(this.y, min), max)));
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max}, returning the result
     * as a value.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return the resulting vector
     */
    public Byte2 clamp(Byte2 min, Byte2 max) {
        return clamp(min.x(), min.y(), max.x(), max.y());
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}), returning the result as a value.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @return the resulting vector
     */
    public Byte2 clamp(byte minX, byte minY, byte maxX, byte maxY) {
        return new Byte2((byte) (Math.min(Math.max(this.x, minX), maxX)), (byte) (Math.min(Math.max(this.y, minY), maxY)));
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public int compAdd() {
        return this.x + this.y;
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public byte compMax() {
        return (byte) (Math.max(this.x, this.y));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        return (byte) (Math.min(this.x, this.y));
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public int compMul() {
        return this.x * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int cross(Byte2 other) {
        return cross(other.x(), other.y());
    }


    /**
     * Compute the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     * order.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when ({@code otherX},
     * {@code otherY}) points counter-clockwise of this vector (with the x axis pointing right and
     * the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     *        order
     */
    public int cross(byte otherX, byte otherY) {
        return otherY * this.x - otherX * this.y;
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public int distanceSquared(Byte2 other) {
        return distanceSquared(other.x(), other.y());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY})
     */
    public int distanceSquared(byte otherX, byte otherY) {
        int _t0 = this.x - otherX;
        int _t1 = this.y - otherY;
        return _t0 * _t0 + _t1 * _t1;
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public int dot(Byte2 other) {
        return dot(other.x(), other.y());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY})
     */
    public int dot(byte otherX, byte otherY) {
        return otherX * this.x + otherY * this.y;
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public int lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public int manhattanDistance(Byte2 other) {
        return manhattanDistance(other.x(), other.y());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY})
     */
    public int manhattanDistance(byte otherX, byte otherY) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public int manhattanLength() {
        return Math.abs(this.x) + Math.abs(this.y);
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return the resulting vector
     */
    public Byte2 max(byte scalar) {
        return max(scalar, scalar);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return the resulting vector
     */
    public Byte2 max(Byte2 other) {
        return max(other.x(), other.y());
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 max(byte otherX, byte otherY) {
        return new Byte2((byte) (Math.max(this.x, otherX)), (byte) (Math.max(this.y, otherY)));
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}, returning the
     * result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return the resulting vector
     */
    public Byte2 min(byte scalar) {
        return min(scalar, scalar);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return the resulting vector
     */
    public Byte2 min(Byte2 other) {
        return min(other.x(), other.y());
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 min(byte otherX, byte otherY) {
        return new Byte2((byte) (Math.min(this.x, otherX)), (byte) (Math.min(this.y, otherY)));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 sign() {
        return new Byte2((byte) (Math.signum(this.x)), (byte) (Math.signum(this.y)));
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Byte2 satAdd(Byte2 other) {
        return satAdd(other.x(), other.y());
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 satAdd(byte otherX, byte otherY) {
        return new Byte2((byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY)));
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Byte2 satMul(Byte2 other) {
        return satMul(other.x(), other.y());
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 satMul(byte otherX, byte otherY) {
        return new Byte2((byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY)));
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte2 satNegate() {
        return new Byte2((byte) (org.joml2.SaturatingMath.satNegB(this.x)), (byte) (org.joml2.SaturatingMath.satNegB(this.y)));
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Byte2 satSub(Byte2 other) {
        return satSub(other.x(), other.y());
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the resulting vector
     */
    public Byte2 satSub(byte otherX, byte otherY) {
        return new Byte2((byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY)));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Byte2 withX(byte x) {
        return new Byte2(x, this.y());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Byte2 withY(byte y) {
        return new Byte2(this.x(), y);
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Byte2 withXY(byte x, byte y) {
        return new Byte2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xx() {
        return new Byte2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xy() {
        return new Byte2(x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yx() {
        return new Byte2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yy() {
        return new Byte2(y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxx() {
        return new Byte3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxy() {
        return new Byte3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyx() {
        return new Byte3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyy() {
        return new Byte3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxx() {
        return new Byte3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxy() {
        return new Byte3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyx() {
        return new Byte3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyy() {
        return new Byte3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxx() {
        return new Byte4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxy() {
        return new Byte4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyx() {
        return new Byte4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyy() {
        return new Byte4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxx() {
        return new Byte4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxy() {
        return new Byte4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyx() {
        return new Byte4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyy() {
        return new Byte4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxx() {
        return new Byte4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxy() {
        return new Byte4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyx() {
        return new Byte4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyy() {
        return new Byte4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxx() {
        return new Byte4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxy() {
        return new Byte4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyx() {
        return new Byte4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyy() {
        return new Byte4(y, y, y, y);
    }

    @Override public String toString() {
        return "Byte2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte2)) return false;
        Byte2 o = (Byte2) obj;
        return x == o.x
            && y == o.y;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + x;
        h = 31 * h + y;
        return h;
    }

    /** {@return whether all components of this value are finite} Integer components always are, so this always returns {@code true}. <p>Valid input: any value. */
    public boolean isFinite() {
        return true;
    }

    /** {@return whether any component of this value is NaN} Integer components never are, so this always returns {@code false}. <p>Valid input: any value. */
    public boolean isNaN() {
        return false;
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance:
     * the difference is widened to {@code int} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Byte2 other, byte epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Byte2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte2SegOpsUnsafe()
                        : new Byte2SegOpsMS();
        static final Byte2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte2BbOpsUnsafe()
                        : new Byte2BbOpsApi();
        static final Byte2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte2RawOpsUnsafe()
                        : new Byte2RawOpsApi();
    }


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public byte[] store(byte[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public byte[] store(byte[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(byte[] src, int offset) {
        byte _c0 = src[offset + 0];
        byte _c1 = src[offset + 1];
        return new Byte2(_c0, _c1);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(byte[] src) { return load(src, 0); }

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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer store(ByteBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 2);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(ByteBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Byte2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte2 r = loadAbsolute(pos, buf);
        buf.position(pos + 2);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    public Byte2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
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
    public MemorySegment store(MemorySegment dest) { return store(0L, dest); }

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
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
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
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(MemorySegment src) { return load(0L, src); }

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
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code short}, starting
     * at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public short[] store(short[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public short[] store(short[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code short}, starting
     * at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(short[] src, int offset) {
        byte _c0 = (byte) src[offset + 0];
        byte _c1 = (byte) src[offset + 1];
        return new Byte2(_c0, _c1);
    }

    /**
     * Load the elements from the given array, converting each element from {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(short[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code short}, starting
     * at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public ShortBuffer store(ShortBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code short}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public ShortBuffer storeAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code short}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ShortBuffer storeRelative(ShortBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 2);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code short}, starting
     * at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 load(ShortBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code short}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code short}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Byte2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Byte2 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte2 r = loadAbsolute(pos, buf);
        buf.position(pos + 2);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code short},
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeShort(ByteBuffer buf) {
        return storeShortAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code short},
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code short},
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeShortAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code short},
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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadShort(ByteBuffer buf) {
        return loadShortAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code short},
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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code short},
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
     * @param buf the source byte buffer
     * @return a new {@code Byte2} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Byte2 loadShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte2 r = loadShortAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code short}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    public Byte2 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code short}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadShortUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code short}.
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
    public MemorySegment storeShort(MemorySegment dest) { return storeShort(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code short},
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
    public MemorySegment storeShort(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeShort(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code short}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param src the source memory segment
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadShort(MemorySegment src) { return loadShort(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code short},
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
     * @return a new {@code Byte2} holding the loaded elements
     */
    public static Byte2 loadShort(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadShort(offset, src);
    }

}
