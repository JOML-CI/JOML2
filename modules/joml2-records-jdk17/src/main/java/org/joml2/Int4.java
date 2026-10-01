// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Immutable 4D vector of {@code int} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code long} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 * @param z the {@code z} component
 * @param w the {@code w} component
 */
public record Int4(int x, int y, int z, int w) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 16;

    /** The zero vector (all components 0). */
    public static final Int4 ZERO = new Int4(0, 0, 0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public Int4(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a new instance initialized to the homogeneous default {@code (0, 0, 0, 1)}.
     * <p>
     * Valid input: any value.
     */
    public Int4() {
        this(0, 0, 0, 1);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Int4(int s) {
        this(s, s, s, s);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public Int4(int v0, int v1, Int2 v2) {
        this(v0, v1, v2.x(), v2.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public Int4(int v0, Int2 v1, int v2) {
        this(v0, v1.x(), v1.y(), v2);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public Int4(int v0, Int3 v1) {
        this(v0, v1.x(), v1.y(), v1.z());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public Int4(Int2 v0, int v1, int v2) {
        this(v0.x(), v0.y(), v1, v2);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public Int4(Int2 v0, Int2 v1) {
        this(v0.x(), v0.y(), v1.x(), v1.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public Int4(Int3 v0, int v1) {
        this(v0.x(), v0.y(), v0.z(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public int x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value. */
    public int y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value. */
    public int z() { return z; }
    /** {@return the {@code w} component} <p>Valid input: any value. */
    public int w() { return w; }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Int4 add(Int4 other) {
        return add(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 add(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
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
    public Int4 ceilDiv(int scalar) {
        return ceilDiv(scalar, scalar, scalar, scalar);
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
    public Int4 ceilDiv(Int4 other) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 ceilDiv(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.ceilDiv(this.x, otherX), Math.ceilDiv(this.y, otherY), Math.ceilDiv(this.z, otherZ), Math.ceilDiv(this.w, otherW));
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
    public Int4 ceilMod(int scalar) {
        return ceilMod(scalar, scalar, scalar, scalar);
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
    public Int4 ceilMod(Int4 other) {
        return ceilMod(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 ceilMod(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.ceilMod(this.x, otherX), Math.ceilMod(this.y, otherY), Math.ceilMod(this.z, otherZ), Math.ceilMod(this.w, otherW));
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
    public Int4 div(int scalar) {
        return new Int4(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
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
    public Int4 div(Int4 other) {
        return div(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) (integer division, truncating toward zero), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 div(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x / otherX, this.y / otherY, this.z / otherZ, this.w / otherW);
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
    public Int4 floorDiv(int scalar) {
        return floorDiv(scalar, scalar, scalar, scalar);
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
    public Int4 floorDiv(Int4 other) {
        return floorDiv(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 floorDiv(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.floorDiv(this.x, otherX), Math.floorDiv(this.y, otherY), Math.floorDiv(this.z, otherZ), Math.floorDiv(this.w, otherW));
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
    public Int4 floorMod(int scalar) {
        return floorMod(scalar, scalar, scalar, scalar);
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
    public Int4 floorMod(Int4 other) {
        return floorMod(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 floorMod(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.floorMod(this.x, otherX), Math.floorMod(this.y, otherY), Math.floorMod(this.z, otherZ), Math.floorMod(this.w, otherW));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Int4 mul(int scalar) {
        return mul(scalar, scalar, scalar, scalar);
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Int4 mul(Int4 other) {
        return mul(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 mul(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(otherX * this.x, otherY * this.y, otherZ * this.z, otherW * this.w);
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 negate() {
        return new Int4(-this.x, -this.y, -this.z, -this.w);
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
    public Int4 rem(int scalar) {
        return rem(scalar, scalar, scalar, scalar);
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
    public Int4 rem(Int4 other) {
        return rem(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) (the remainder carries the sign of the
     * dividend, exactly Java's {@code %}, so it pairs with {@code div}), returning the result as a
     * value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 rem(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x % otherX, this.y % otherY, this.z % otherZ, this.w % otherW);
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Int4 sub(Int4 other) {
        return sub(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 sub(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
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
    public Int4 and(Int4 other) {
        return and(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 and(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x & otherX, this.y & otherY, this.z & otherZ, this.w & otherW);
    }


    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 bitCount() {
        return new Int4(Math.bitCount(this.x), Math.bitCount(this.y), Math.bitCount(this.z), Math.bitCount(this.w));
    }


    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 not() {
        return new Int4(~this.x, ~this.y, ~this.z, ~this.w);
    }


    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 numberOfLeadingZeros() {
        return new Int4(Math.numberOfLeadingZeros(this.x), Math.numberOfLeadingZeros(this.y), Math.numberOfLeadingZeros(this.z), Math.numberOfLeadingZeros(this.w));
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 numberOfTrailingZeros() {
        return new Int4(Math.numberOfTrailingZeros(this.x), Math.numberOfTrailingZeros(this.y), Math.numberOfTrailingZeros(this.z), Math.numberOfTrailingZeros(this.w));
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
    public Int4 or(Int4 other) {
        return or(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 or(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x | otherX, this.y | otherY, this.z | otherZ, this.w | otherW);
    }


    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 reverseBits() {
        return new Int4(Math.reverseBits(this.x), Math.reverseBits(this.y), Math.reverseBits(this.z), Math.reverseBits(this.w));
    }


    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 reverseBytes() {
        return new Int4(Math.reverseBytes(this.x), Math.reverseBytes(this.y), Math.reverseBytes(this.z), Math.reverseBytes(this.w));
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
    public Int4 rotateLeft(int distance) {
        return new Int4(Math.rotateLeft(this.x, distance), Math.rotateLeft(this.y, distance), Math.rotateLeft(this.z, distance), Math.rotateLeft(this.w, distance));
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
    public Int4 rotateRight(int distance) {
        return new Int4(Math.rotateRight(this.x, distance), Math.rotateRight(this.y, distance), Math.rotateRight(this.z, distance), Math.rotateRight(this.w, distance));
    }


    /**
     * Shift each component of this vector left by {@code shift} bits, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Int4 shl(int shift) {
        return new Int4(this.x << shift, this.y << shift, this.z << shift, this.w << shift);
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Int4 shr(int shift) {
        return new Int4(this.x >> shift, this.y >> shift, this.z >> shift, this.w >> shift);
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Int4 ushr(int shift) {
        return new Int4(this.x >>> shift, this.y >>> shift, this.z >>> shift, this.w >>> shift);
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
    public Int4 xor(Int4 other) {
        return xor(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 xor(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(this.x ^ otherX, this.y ^ otherY, this.z ^ otherZ, this.w ^ otherW);
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Int4 set(Int4 v) {
        return set(v.x(), v.y(), v.z(), v.w());
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @return the resulting vector
     */
    public Int4 set(int vX, int vY, int vZ, int vW) {
        return new Int4(vX, vY, vZ, vW);
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Int4 set(int s) {
        return new Int4(s, s, s, s);
    }


    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float4} holding the result
     */
    public Float4 toFloat() {
        return new Float4(this.x, this.y, this.z, this.w);
    }


    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4} holding the result
     */
    public Double4 toDouble() {
        return new Double4(this.x, this.y, this.z, this.w);
    }


    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte4} holding the result
     */
    public Byte4 toByte() {
        return new Byte4((byte) (this.x), (byte) (this.y), (byte) (this.z), (byte) (this.w));
    }


    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short4} holding the result
     */
    public Short4 toShort() {
        return new Short4((short) (this.x), (short) (this.y), (short) (this.z), (short) (this.w));
    }


    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long4} holding the result
     */
    public Long4 toLong() {
        return new Long4(this.x, this.y, this.z, this.w);
    }


    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Int4 makeZero() {
        return Int4.ZERO;
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 absolute() {
        return new Int4(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
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
    public Int4 clamp(int min, int max) {
        return new Int4(Math.min(Math.max(this.x, min), max), Math.min(Math.max(this.y, min), max), Math.min(Math.max(this.z, min), max), Math.min(Math.max(this.w, min), max));
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
    public Int4 clamp(Int4 min, Int4 max) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w());
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}), returning the
     * result as a value.
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
     * @return the resulting vector
     */
    public Int4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW) {
        return new Int4(Math.min(Math.max(this.x, minX), maxX), Math.min(Math.max(this.y, minY), maxY), Math.min(Math.max(this.z, minZ), maxZ), Math.min(Math.max(this.w, minW), maxW));
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public long compAdd() {
        return (long) this.w + ((long) this.z + ((long) this.x + (long) this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public int compMax() {
        return Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public int compMin() {
        return Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public long compMul() {
        return (long) this.w * (long) this.z * (long) this.x * (long) this.y;
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @return the resulting vector
     */
    public Int4 cross(Int4 v, Int4 w) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w());
    }


    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent), returning the result as a value.
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
     * @return the resulting vector
     */
    public Int4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW) {
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        return new Int4(this.y * _t12 - this.z * _t13 + this.w * _t14, this.z * _t15 - this.x * _t12 - this.w * _t16, this.x * _t13 - this.y * _t15 + this.w * _t17, this.y * _t16 - this.x * _t14 - this.z * _t17);
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public long distanceSquared(Int4 other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public long distanceSquared(int otherX, int otherY, int otherZ, int otherW) {
        long _t0 = (long) this.x - (long) otherX;
        long _t1 = (long) this.y - (long) otherY;
        long _t2 = (long) this.z - (long) otherZ;
        long _t3 = (long) this.w - (long) otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public long dot(Int4 other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW})
     */
    public long dot(int otherX, int otherY, int otherZ, int otherW) {
        return (long) otherX * (long) this.x + (long) otherY * (long) this.y + (long) otherZ * (long) this.z + (long) otherW * (long) this.w;
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public long lengthSquared() {
        return (long) this.x * (long) this.x + (long) this.y * (long) this.y + (long) this.z * (long) this.z + (long) this.w * (long) this.w;
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public long manhattanDistance(Int4 other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public long manhattanDistance(int otherX, int otherY, int otherZ, int otherW) {
        return Math.abs((long) this.x - (long) otherX) + Math.abs((long) this.y - (long) otherY) + Math.abs((long) this.z - (long) otherZ) + Math.abs((long) this.w - (long) otherW);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public long manhattanLength() {
        return Math.abs((long) this.x) + Math.abs((long) this.y) + Math.abs((long) this.z) + Math.abs((long) this.w);
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
    public Int4 max(int scalar) {
        return max(scalar, scalar, scalar, scalar);
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
    public Int4 max(Int4 other) {
        return max(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a
     * value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 max(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.max(this.x, otherX), Math.max(this.y, otherY), Math.max(this.z, otherZ), Math.max(this.w, otherW));
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
    public Int4 min(int scalar) {
        return min(scalar, scalar, scalar, scalar);
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
    public Int4 min(Int4 other) {
        return min(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}), returning the result as a
     * value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 min(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(Math.min(this.x, otherX), Math.min(this.y, otherY), Math.min(this.z, otherZ), Math.min(this.w, otherW));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 sign() {
        return new Int4(Math.signum(this.x), Math.signum(this.y), Math.signum(this.z), Math.signum(this.w));
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
    public Int4 satAdd(Int4 other) {
        return satAdd(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 satAdd(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(org.joml2.SaturatingMath.satAdd(this.x, otherX), org.joml2.SaturatingMath.satAdd(this.y, otherY), org.joml2.SaturatingMath.satAdd(this.z, otherZ), org.joml2.SaturatingMath.satAdd(this.w, otherW));
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
    public Int4 satMul(Int4 other) {
        return satMul(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 satMul(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(org.joml2.SaturatingMath.satMul(this.x, otherX), org.joml2.SaturatingMath.satMul(this.y, otherY), org.joml2.SaturatingMath.satMul(this.z, otherZ), org.joml2.SaturatingMath.satMul(this.w, otherW));
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 satNegate() {
        return new Int4(org.joml2.SaturatingMath.satNeg(this.x), org.joml2.SaturatingMath.satNeg(this.y), org.joml2.SaturatingMath.satNeg(this.z), org.joml2.SaturatingMath.satNeg(this.w));
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
    public Int4 satSub(Int4 other) {
        return satSub(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting vector
     */
    public Int4 satSub(int otherX, int otherY, int otherZ, int otherW) {
        return new Int4(org.joml2.SaturatingMath.satSub(this.x, otherX), org.joml2.SaturatingMath.satSub(this.y, otherY), org.joml2.SaturatingMath.satSub(this.z, otherZ), org.joml2.SaturatingMath.satSub(this.w, otherW));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Int4 withX(int x) {
        return new Int4(x, this.y(), this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Int4 withY(int y) {
        return new Int4(this.x(), y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Int4 withXY(int x, int y) {
        return new Int4(x, y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     */
    public Int4 withZ(int z) {
        return new Int4(this.x(), this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Int4 withXZ(int x, int z) {
        return new Int4(x, this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Int4 withYZ(int y, int z) {
        return new Int4(this.x(), y, z, this.w());
    }

    /**
     * {@return a copy of this vector with the XYZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Int4 withXYZ(int x, int y, int z) {
        return new Int4(x, y, z, this.w());
    }

    /**
     * {@return a copy of this vector with the W component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param w the new value of the {@code w} component
     */
    public Int4 withW(int w) {
        return new Int4(this.x(), this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withXW(int x, int w) {
        return new Int4(x, this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the YW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withYW(int y, int w) {
        return new Int4(this.x(), y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XYW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withXYW(int x, int y, int w) {
        return new Int4(x, y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the ZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withZW(int z, int w) {
        return new Int4(this.x(), this.y(), z, w);
    }

    /**
     * {@return a copy of this vector with the XZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withXZW(int x, int z, int w) {
        return new Int4(x, this.y(), z, w);
    }

    /**
     * {@return a copy of this vector with the YZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withYZW(int y, int z, int w) {
        return new Int4(this.x(), y, z, w);
    }

    /**
     * {@return a copy of this vector with the XYZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Int4 withXYZW(int x, int y, int z, int w) {
        return new Int4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 xx() {
        return new Int2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 xy() {
        return new Int2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 xz() {
        return new Int2(x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 xw() {
        return new Int2(x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 yx() {
        return new Int2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 yy() {
        return new Int2(y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 yz() {
        return new Int2(y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 yw() {
        return new Int2(y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 zx() {
        return new Int2(z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 zy() {
        return new Int2(z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 zz() {
        return new Int2(z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 zw() {
        return new Int2(z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 wx() {
        return new Int2(w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 wy() {
        return new Int2(w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 wz() {
        return new Int2(w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int2 ww() {
        return new Int2(w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xxx() {
        return new Int3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xxy() {
        return new Int3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xxz() {
        return new Int3(x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xxw() {
        return new Int3(x, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xyx() {
        return new Int3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xyy() {
        return new Int3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xyz() {
        return new Int3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xyw() {
        return new Int3(x, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xzx() {
        return new Int3(x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xzy() {
        return new Int3(x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xzz() {
        return new Int3(x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xzw() {
        return new Int3(x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xwx() {
        return new Int3(x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xwy() {
        return new Int3(x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xwz() {
        return new Int3(x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 xww() {
        return new Int3(x, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yxx() {
        return new Int3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yxy() {
        return new Int3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yxz() {
        return new Int3(y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yxw() {
        return new Int3(y, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yyx() {
        return new Int3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yyy() {
        return new Int3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yyz() {
        return new Int3(y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yyw() {
        return new Int3(y, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yzx() {
        return new Int3(y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yzy() {
        return new Int3(y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yzz() {
        return new Int3(y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yzw() {
        return new Int3(y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 ywx() {
        return new Int3(y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 ywy() {
        return new Int3(y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 ywz() {
        return new Int3(y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 yww() {
        return new Int3(y, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zxx() {
        return new Int3(z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zxy() {
        return new Int3(z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zxz() {
        return new Int3(z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zxw() {
        return new Int3(z, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zyx() {
        return new Int3(z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zyy() {
        return new Int3(z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zyz() {
        return new Int3(z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zyw() {
        return new Int3(z, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zzx() {
        return new Int3(z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zzy() {
        return new Int3(z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zzz() {
        return new Int3(z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zzw() {
        return new Int3(z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zwx() {
        return new Int3(z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zwy() {
        return new Int3(z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zwz() {
        return new Int3(z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 zww() {
        return new Int3(z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wxx() {
        return new Int3(w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wxy() {
        return new Int3(w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wxz() {
        return new Int3(w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wxw() {
        return new Int3(w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wyx() {
        return new Int3(w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wyy() {
        return new Int3(w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wyz() {
        return new Int3(w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wyw() {
        return new Int3(w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wzx() {
        return new Int3(w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wzy() {
        return new Int3(w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wzz() {
        return new Int3(w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wzw() {
        return new Int3(w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wwx() {
        return new Int3(w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wwy() {
        return new Int3(w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 wwz() {
        return new Int3(w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int3 www() {
        return new Int3(w, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxxx() {
        return new Int4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxxy() {
        return new Int4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxxz() {
        return new Int4(x, x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxxw() {
        return new Int4(x, x, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxyx() {
        return new Int4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxyy() {
        return new Int4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxyz() {
        return new Int4(x, x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxyw() {
        return new Int4(x, x, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxzx() {
        return new Int4(x, x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxzy() {
        return new Int4(x, x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxzz() {
        return new Int4(x, x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxzw() {
        return new Int4(x, x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxwx() {
        return new Int4(x, x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxwy() {
        return new Int4(x, x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxwz() {
        return new Int4(x, x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xxww() {
        return new Int4(x, x, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyxx() {
        return new Int4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyxy() {
        return new Int4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyxz() {
        return new Int4(x, y, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyxw() {
        return new Int4(x, y, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyyx() {
        return new Int4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyyy() {
        return new Int4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyyz() {
        return new Int4(x, y, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyyw() {
        return new Int4(x, y, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyzx() {
        return new Int4(x, y, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyzy() {
        return new Int4(x, y, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyzz() {
        return new Int4(x, y, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyzw() {
        return new Int4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xywx() {
        return new Int4(x, y, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xywy() {
        return new Int4(x, y, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xywz() {
        return new Int4(x, y, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xyww() {
        return new Int4(x, y, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzxx() {
        return new Int4(x, z, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzxy() {
        return new Int4(x, z, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzxz() {
        return new Int4(x, z, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzxw() {
        return new Int4(x, z, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzyx() {
        return new Int4(x, z, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzyy() {
        return new Int4(x, z, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzyz() {
        return new Int4(x, z, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzyw() {
        return new Int4(x, z, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzzx() {
        return new Int4(x, z, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzzy() {
        return new Int4(x, z, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzzz() {
        return new Int4(x, z, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzzw() {
        return new Int4(x, z, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzwx() {
        return new Int4(x, z, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzwy() {
        return new Int4(x, z, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzwz() {
        return new Int4(x, z, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xzww() {
        return new Int4(x, z, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwxx() {
        return new Int4(x, w, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwxy() {
        return new Int4(x, w, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwxz() {
        return new Int4(x, w, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwxw() {
        return new Int4(x, w, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwyx() {
        return new Int4(x, w, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwyy() {
        return new Int4(x, w, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwyz() {
        return new Int4(x, w, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwyw() {
        return new Int4(x, w, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwzx() {
        return new Int4(x, w, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwzy() {
        return new Int4(x, w, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwzz() {
        return new Int4(x, w, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwzw() {
        return new Int4(x, w, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwwx() {
        return new Int4(x, w, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwwy() {
        return new Int4(x, w, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwwz() {
        return new Int4(x, w, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 xwww() {
        return new Int4(x, w, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxxx() {
        return new Int4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxxy() {
        return new Int4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxxz() {
        return new Int4(y, x, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxxw() {
        return new Int4(y, x, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxyx() {
        return new Int4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxyy() {
        return new Int4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxyz() {
        return new Int4(y, x, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxyw() {
        return new Int4(y, x, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxzx() {
        return new Int4(y, x, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxzy() {
        return new Int4(y, x, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxzz() {
        return new Int4(y, x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxzw() {
        return new Int4(y, x, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxwx() {
        return new Int4(y, x, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxwy() {
        return new Int4(y, x, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxwz() {
        return new Int4(y, x, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yxww() {
        return new Int4(y, x, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyxx() {
        return new Int4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyxy() {
        return new Int4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyxz() {
        return new Int4(y, y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyxw() {
        return new Int4(y, y, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyyx() {
        return new Int4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyyy() {
        return new Int4(y, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyyz() {
        return new Int4(y, y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyyw() {
        return new Int4(y, y, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyzx() {
        return new Int4(y, y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyzy() {
        return new Int4(y, y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyzz() {
        return new Int4(y, y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyzw() {
        return new Int4(y, y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yywx() {
        return new Int4(y, y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yywy() {
        return new Int4(y, y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yywz() {
        return new Int4(y, y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yyww() {
        return new Int4(y, y, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzxx() {
        return new Int4(y, z, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzxy() {
        return new Int4(y, z, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzxz() {
        return new Int4(y, z, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzxw() {
        return new Int4(y, z, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzyx() {
        return new Int4(y, z, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzyy() {
        return new Int4(y, z, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzyz() {
        return new Int4(y, z, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzyw() {
        return new Int4(y, z, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzzx() {
        return new Int4(y, z, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzzy() {
        return new Int4(y, z, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzzz() {
        return new Int4(y, z, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzzw() {
        return new Int4(y, z, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzwx() {
        return new Int4(y, z, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzwy() {
        return new Int4(y, z, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzwz() {
        return new Int4(y, z, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 yzww() {
        return new Int4(y, z, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywxx() {
        return new Int4(y, w, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywxy() {
        return new Int4(y, w, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywxz() {
        return new Int4(y, w, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywxw() {
        return new Int4(y, w, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywyx() {
        return new Int4(y, w, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywyy() {
        return new Int4(y, w, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywyz() {
        return new Int4(y, w, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywyw() {
        return new Int4(y, w, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywzx() {
        return new Int4(y, w, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywzy() {
        return new Int4(y, w, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywzz() {
        return new Int4(y, w, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywzw() {
        return new Int4(y, w, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywwx() {
        return new Int4(y, w, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywwy() {
        return new Int4(y, w, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywwz() {
        return new Int4(y, w, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 ywww() {
        return new Int4(y, w, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxxx() {
        return new Int4(z, x, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxxy() {
        return new Int4(z, x, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxxz() {
        return new Int4(z, x, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxxw() {
        return new Int4(z, x, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxyx() {
        return new Int4(z, x, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxyy() {
        return new Int4(z, x, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxyz() {
        return new Int4(z, x, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxyw() {
        return new Int4(z, x, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxzx() {
        return new Int4(z, x, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxzy() {
        return new Int4(z, x, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxzz() {
        return new Int4(z, x, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxzw() {
        return new Int4(z, x, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxwx() {
        return new Int4(z, x, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxwy() {
        return new Int4(z, x, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxwz() {
        return new Int4(z, x, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zxww() {
        return new Int4(z, x, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyxx() {
        return new Int4(z, y, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyxy() {
        return new Int4(z, y, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyxz() {
        return new Int4(z, y, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyxw() {
        return new Int4(z, y, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyyx() {
        return new Int4(z, y, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyyy() {
        return new Int4(z, y, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyyz() {
        return new Int4(z, y, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyyw() {
        return new Int4(z, y, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyzx() {
        return new Int4(z, y, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyzy() {
        return new Int4(z, y, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyzz() {
        return new Int4(z, y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyzw() {
        return new Int4(z, y, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zywx() {
        return new Int4(z, y, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zywy() {
        return new Int4(z, y, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zywz() {
        return new Int4(z, y, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zyww() {
        return new Int4(z, y, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzxx() {
        return new Int4(z, z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzxy() {
        return new Int4(z, z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzxz() {
        return new Int4(z, z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzxw() {
        return new Int4(z, z, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzyx() {
        return new Int4(z, z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzyy() {
        return new Int4(z, z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzyz() {
        return new Int4(z, z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzyw() {
        return new Int4(z, z, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzzx() {
        return new Int4(z, z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzzy() {
        return new Int4(z, z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzzz() {
        return new Int4(z, z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzzw() {
        return new Int4(z, z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzwx() {
        return new Int4(z, z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzwy() {
        return new Int4(z, z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzwz() {
        return new Int4(z, z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zzww() {
        return new Int4(z, z, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwxx() {
        return new Int4(z, w, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwxy() {
        return new Int4(z, w, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwxz() {
        return new Int4(z, w, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwxw() {
        return new Int4(z, w, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwyx() {
        return new Int4(z, w, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwyy() {
        return new Int4(z, w, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwyz() {
        return new Int4(z, w, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwyw() {
        return new Int4(z, w, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwzx() {
        return new Int4(z, w, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwzy() {
        return new Int4(z, w, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwzz() {
        return new Int4(z, w, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwzw() {
        return new Int4(z, w, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwwx() {
        return new Int4(z, w, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwwy() {
        return new Int4(z, w, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwwz() {
        return new Int4(z, w, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 zwww() {
        return new Int4(z, w, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxxx() {
        return new Int4(w, x, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxxy() {
        return new Int4(w, x, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxxz() {
        return new Int4(w, x, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxxw() {
        return new Int4(w, x, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxyx() {
        return new Int4(w, x, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxyy() {
        return new Int4(w, x, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxyz() {
        return new Int4(w, x, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxyw() {
        return new Int4(w, x, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxzx() {
        return new Int4(w, x, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxzy() {
        return new Int4(w, x, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxzz() {
        return new Int4(w, x, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxzw() {
        return new Int4(w, x, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxwx() {
        return new Int4(w, x, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxwy() {
        return new Int4(w, x, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxwz() {
        return new Int4(w, x, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wxww() {
        return new Int4(w, x, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyxx() {
        return new Int4(w, y, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyxy() {
        return new Int4(w, y, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyxz() {
        return new Int4(w, y, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyxw() {
        return new Int4(w, y, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyyx() {
        return new Int4(w, y, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyyy() {
        return new Int4(w, y, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyyz() {
        return new Int4(w, y, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyyw() {
        return new Int4(w, y, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyzx() {
        return new Int4(w, y, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyzy() {
        return new Int4(w, y, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyzz() {
        return new Int4(w, y, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyzw() {
        return new Int4(w, y, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wywx() {
        return new Int4(w, y, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wywy() {
        return new Int4(w, y, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wywz() {
        return new Int4(w, y, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wyww() {
        return new Int4(w, y, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzxx() {
        return new Int4(w, z, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzxy() {
        return new Int4(w, z, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzxz() {
        return new Int4(w, z, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzxw() {
        return new Int4(w, z, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzyx() {
        return new Int4(w, z, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzyy() {
        return new Int4(w, z, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzyz() {
        return new Int4(w, z, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzyw() {
        return new Int4(w, z, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzzx() {
        return new Int4(w, z, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzzy() {
        return new Int4(w, z, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzzz() {
        return new Int4(w, z, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzzw() {
        return new Int4(w, z, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzwx() {
        return new Int4(w, z, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzwy() {
        return new Int4(w, z, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzwz() {
        return new Int4(w, z, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wzww() {
        return new Int4(w, z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwxx() {
        return new Int4(w, w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwxy() {
        return new Int4(w, w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwxz() {
        return new Int4(w, w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwxw() {
        return new Int4(w, w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwyx() {
        return new Int4(w, w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwyy() {
        return new Int4(w, w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwyz() {
        return new Int4(w, w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwyw() {
        return new Int4(w, w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwzx() {
        return new Int4(w, w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwzy() {
        return new Int4(w, w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwzz() {
        return new Int4(w, w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwzw() {
        return new Int4(w, w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwwx() {
        return new Int4(w, w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwwy() {
        return new Int4(w, w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwwz() {
        return new Int4(w, w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Int4 wwww() {
        return new Int4(w, w, w, w);
    }

    @Override public String toString() {
        return "Int4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int4)) return false;
        Int4 o = (Int4) obj;
        return x == o.x
            && y == o.y
            && z == o.z
            && w == o.w;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + x;
        h = 31 * h + y;
        h = 31 * h + z;
        h = 31 * h + w;
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
     * the difference is widened to {@code long} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Int4 other, int epsilon) {
        return Math.abs((long) x - (long) other.x()) <= epsilon
            && Math.abs((long) y - (long) other.y()) <= epsilon
            && Math.abs((long) z - (long) other.z()) <= epsilon
            && Math.abs((long) w - (long) other.w()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int4BbOpsUnsafe()
                        : new Int4BbOpsApi();
        static final Int4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int4RawOpsUnsafe()
                        : new Int4RawOpsApi();
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
    public int[] store(int[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
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
    public int[] store(int[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(int[] src, int offset) {
        int _c0 = src[offset + 0];
        int _c1 = src[offset + 1];
        int _c2 = src[offset + 2];
        int _c3 = src[offset + 3];
        return new Int4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(int[] src) { return load(src, 0); }

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
     * @param buf the destination buffer
     * @return buf
     */
    public IntBuffer store(IntBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     */
    public IntBuffer storeAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public IntBuffer storeRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(IntBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Int4 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int4 r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 16);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(ByteBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Int4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int4 r = loadAbsolute(pos, buf);
        buf.position(pos + 16);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Int4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Int4} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Int4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }


    /**
     * Store the elements into the given array, converting each element to {@code long}, starting at
     * the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public long[] store(long[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public long[] store(long[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code long}, starting
     * at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(long[] src, int offset) {
        int _c0 = (int) src[offset + 0];
        int _c1 = (int) src[offset + 1];
        int _c2 = (int) src[offset + 2];
        int _c3 = (int) src[offset + 3];
        return new Int4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(long[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public LongBuffer store(LongBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public LongBuffer storeAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public LongBuffer storeRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 load(LongBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Int4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Int4 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int4 r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeLong(ByteBuffer buf) {
        return storeLongAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeLongAbsolute(pos, buf);
        buf.position(pos + 32);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 loadLong(ByteBuffer buf) {
        return loadLongAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     */
    public static Int4 loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Int4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Int4 loadLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int4 r = loadLongAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Int4 storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Int4} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Int4 loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(address);
    }

}
