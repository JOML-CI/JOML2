// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

/**
 * Immutable 3D vector of {@code int} components.
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
 */
public record Int3(int x, int y, int z) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 12;

    /** The zero vector (all components 0). */
    public static final Int3 ZERO = new Int3(0, 0, 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public Int3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: the method reads no input.
     */
    public Int3() {
        this(0, 0, 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Int3(int s) {
        this(s, s, s);
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public Int3(int v0, Int2 v1) {
        this(v0, v1.x(), v1.y());
    }

    /**
     * Create a vector composed of the given parts, in order.
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public Int3(Int2 v0, int v1) {
        this(v0.x(), v0.y(), v1);
    }

    /**
     * Create an array of {@code length} elements, each {@link #ZERO}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Int3[] newArray(int length) {
        Int3[] a = new Int3[length];
        java.util.Arrays.fill(a, ZERO);
        return a;
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Int3[] newArray(int length, Int3 init) {
        java.util.Objects.requireNonNull(init, "init");
        Int3[] a = new Int3[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public int x() { return x; }

    /** {@return the {@code y} component} <p>Valid input: any value. */
    public int y() { return y; }

    /** {@return the {@code z} component} <p>Valid input: any value. */
    public int z() { return z; }

    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Int3 add(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(otherX + this.x, otherY + this.y, otherZ + this.z);
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, returning the result as
     * a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 add(int otherX, int otherY, int otherZ) {
        return new Int3(otherX + this.x, otherY + this.y, otherZ + this.z);
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
    public Int3 ceilDiv(int scalar) {
        return new Int3(Math.ceilDiv(this.x, scalar), Math.ceilDiv(this.y, scalar), Math.ceilDiv(this.z, scalar));
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
    public Int3 ceilDiv(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(Math.ceilDiv(this.x, otherX), Math.ceilDiv(this.y, otherY), Math.ceilDiv(this.z, otherZ));
    }

    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 ceilDiv(int otherX, int otherY, int otherZ) {
        return new Int3(Math.ceilDiv(this.x, otherX), Math.ceilDiv(this.y, otherY), Math.ceilDiv(this.z, otherZ));
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
    public Int3 ceilMod(int scalar) {
        return new Int3(Math.ceilMod(this.x, scalar), Math.ceilMod(this.y, scalar), Math.ceilMod(this.z, scalar));
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
    public Int3 ceilMod(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(Math.ceilMod(this.x, otherX), Math.ceilMod(this.y, otherY), Math.ceilMod(this.z, otherZ));
    }

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 ceilMod(int otherX, int otherY, int otherZ) {
        return new Int3(Math.ceilMod(this.x, otherX), Math.ceilMod(this.y, otherY), Math.ceilMod(this.z, otherZ));
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
    public Int3 div(int scalar) {
        return new Int3(this.x / scalar, this.y / scalar, this.z / scalar);
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
    public Int3 div(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x / otherX, this.y / otherY, this.z / otherZ);
    }

    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 div(int otherX, int otherY, int otherZ) {
        return new Int3(this.x / otherX, this.y / otherY, this.z / otherZ);
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
    public Int3 floorDiv(int scalar) {
        return new Int3(Math.floorDiv(this.x, scalar), Math.floorDiv(this.y, scalar), Math.floorDiv(this.z, scalar));
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
    public Int3 floorDiv(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(Math.floorDiv(this.x, otherX), Math.floorDiv(this.y, otherY), Math.floorDiv(this.z, otherZ));
    }

    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 floorDiv(int otherX, int otherY, int otherZ) {
        return new Int3(Math.floorDiv(this.x, otherX), Math.floorDiv(this.y, otherY), Math.floorDiv(this.z, otherZ));
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
    public Int3 floorMod(int scalar) {
        return new Int3(Math.floorMod(this.x, scalar), Math.floorMod(this.y, scalar), Math.floorMod(this.z, scalar));
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
    public Int3 floorMod(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(Math.floorMod(this.x, otherX), Math.floorMod(this.y, otherY), Math.floorMod(this.z, otherZ));
    }

    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 floorMod(int otherX, int otherY, int otherZ) {
        return new Int3(Math.floorMod(this.x, otherX), Math.floorMod(this.y, otherY), Math.floorMod(this.z, otherZ));
    }

    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Int3 mul(int scalar) {
        return new Int3(scalar * this.x, scalar * this.y, scalar * this.z);
    }

    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Int3 mul(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(otherX * this.x, otherY * this.y, otherZ * this.z);
    }

    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 mul(int otherX, int otherY, int otherZ) {
        return new Int3(otherX * this.x, otherY * this.y, otherZ * this.z);
    }

    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 negate() {
        return new Int3(-this.x, -this.y, -this.z);
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
    public Int3 rem(int scalar) {
        return new Int3(this.x % scalar, this.y % scalar, this.z % scalar);
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
    public Int3 rem(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x % otherX, this.y % otherY, this.z % otherZ);
    }

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}), returning the result as a value.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 rem(int otherX, int otherY, int otherZ) {
        return new Int3(this.x % otherX, this.y % otherY, this.z % otherZ);
    }

    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Int3 sub(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x - otherX, this.y - otherY, this.z - otherZ);
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 sub(int otherX, int otherY, int otherZ) {
        return new Int3(this.x - otherX, this.y - otherY, this.z - otherZ);
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
    public Int3 and(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x & otherX, this.y & otherY, this.z & otherZ);
    }

    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 and(int otherX, int otherY, int otherZ) {
        return new Int3(this.x & otherX, this.y & otherY, this.z & otherZ);
    }

    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 bitCount() {
        return new Int3(Math.bitCount(this.x), Math.bitCount(this.y), Math.bitCount(this.z));
    }

    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 not() {
        return new Int3(~this.x, ~this.y, ~this.z);
    }

    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 numberOfLeadingZeros() {
        return new Int3(Math.numberOfLeadingZeros(this.x), Math.numberOfLeadingZeros(this.y), Math.numberOfLeadingZeros(this.z));
    }

    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 numberOfTrailingZeros() {
        return new Int3(Math.numberOfTrailingZeros(this.x), Math.numberOfTrailingZeros(this.y), Math.numberOfTrailingZeros(this.z));
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
    public Int3 or(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x | otherX, this.y | otherY, this.z | otherZ);
    }

    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 or(int otherX, int otherY, int otherZ) {
        return new Int3(this.x | otherX, this.y | otherY, this.z | otherZ);
    }

    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 reverseBits() {
        return new Int3(Math.reverseBits(this.x), Math.reverseBits(this.y), Math.reverseBits(this.z));
    }

    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 reverseBytes() {
        return new Int3(Math.reverseBytes(this.x), Math.reverseBytes(this.y), Math.reverseBytes(this.z));
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
    public Int3 rotateLeft(int distance) {
        return new Int3(Math.rotateLeft(this.x, distance), Math.rotateLeft(this.y, distance), Math.rotateLeft(this.z, distance));
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
    public Int3 rotateRight(int distance) {
        return new Int3(Math.rotateRight(this.x, distance), Math.rotateRight(this.y, distance), Math.rotateRight(this.z, distance));
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
    public Int3 shl(int shift) {
        return new Int3(this.x << shift, this.y << shift, this.z << shift);
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
    public Int3 shr(int shift) {
        return new Int3(this.x >> shift, this.y >> shift, this.z >> shift);
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
    public Int3 ushr(int shift) {
        return new Int3(this.x >>> shift, this.y >>> shift, this.z >>> shift);
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
    public Int3 xor(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(this.x ^ otherX, this.y ^ otherY, this.z ^ otherZ);
    }

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 xor(int otherX, int otherY, int otherZ) {
        return new Int3(this.x ^ otherX, this.y ^ otherY, this.z ^ otherZ);
    }

    /**
     * Return a vector with the values of {@code v}: that vector itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Int3 set(Int3 v) {
        int vX = v.x;
        int vY = v.y;
        int vZ = v.z;
        return new Int3(vX, vY, vZ);
    }

    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Int3 set(int vX, int vY, int vZ) {
        return new Int3(vX, vY, vZ);
    }

    /**
     * Compute a vector with every component {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Int3 set(int s) {
        return new Int3(s, s, s);
    }

    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float3} holding the result
     */
    public Float3 toFloat() {
        return new Float3(this.x, this.y, this.z);
    }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3} holding the result
     */
    public Double3 toDouble() {
        return new Double3(this.x, this.y, this.z);
    }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte3} holding the result
     */
    public Byte3 toByte() {
        return new Byte3((byte) (this.x), (byte) (this.y), (byte) (this.z));
    }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short3} holding the result
     */
    public Short3 toShort() {
        return new Short3((short) (this.x), (short) (this.y), (short) (this.z));
    }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long3} holding the result
     */
    public Long3 toLong() {
        return new Long3(this.x, this.y, this.z);
    }

    /**
     * Create an all-zero vector.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting vector
     */
    public static Int3 makeZero() {
        return Int3.ZERO;
    }

    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 absolute() {
        return new Int3(java.lang.Math.abs(this.x), java.lang.Math.abs(this.y), java.lang.Math.abs(this.z));
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
    public Int3 clamp(int min, int max) {
        return new Int3(java.lang.Math.min(java.lang.Math.max(this.x, min), max), java.lang.Math.min(java.lang.Math.max(this.y, min), max), java.lang.Math.min(java.lang.Math.max(this.z, min), max));
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
    public Int3 clamp(Int3 min, Int3 max) {
        int minX = min.x;
        int minY = min.y;
        int minZ = min.z;
        int maxX = max.x;
        int maxY = max.y;
        int maxZ = max.z;
        return new Int3(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
    }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}), returning the result as a value.
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
     * @return the resulting vector
     */
    public Int3 clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new Int3(java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX), java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY), java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
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
        return (long) this.z + ((long) this.x + this.y);
    }

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public int compMax() {
        return java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z);
    }

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public int compMin() {
        return java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z);
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
        return (long) this.z * (long) this.x * this.y;
    }

    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return the resulting vector
     */
    public Int3 cross(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(otherZ * this.y - otherY * this.z, otherX * this.z - otherZ * this.x, otherY * this.x - otherX * this.y);
    }

    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 cross(int otherX, int otherY, int otherZ) {
        return new Int3(otherZ * this.y - otherY * this.z, otherX * this.z - otherZ * this.x, otherY * this.x - otherX * this.y);
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
    public long distanceSquared(Int3 other) {
        long _t0 = (long) this.x - other.x;
        long _t1 = (long) this.y - other.y;
        long _t2 = (long) this.z - other.z;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }

    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long distanceSquared(int otherX, int otherY, int otherZ) {
        long _t0 = (long) this.x - otherX;
        long _t1 = (long) this.y - otherY;
        long _t2 = (long) this.z - otherZ;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
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
    public long dot(Int3 other) {
        return (long) other.x * this.x + (long) other.y * this.y + (long) other.z * this.z;
    }

    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public long dot(int otherX, int otherY, int otherZ) {
        return (long) otherX * this.x + (long) otherY * this.y + (long) otherZ * this.z;
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
        return (long) this.x * this.x + (long) this.y * this.y + (long) this.z * this.z;
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
    public long manhattanDistance(Int3 other) {
        return java.lang.Math.abs((long) this.x - other.x) + java.lang.Math.abs((long) this.y - other.y) + java.lang.Math.abs((long) this.z - other.z);
    }

    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long manhattanDistance(int otherX, int otherY, int otherZ) {
        return java.lang.Math.abs((long) this.x - otherX) + java.lang.Math.abs((long) this.y - otherY) + java.lang.Math.abs((long) this.z - otherZ);
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
        return java.lang.Math.abs((long) this.x) + java.lang.Math.abs((long) this.y) + java.lang.Math.abs((long) this.z);
    }

    /**
     * Compute the larger of each component of this vector and {@code scalar}, returning the result
     * as a value.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return the resulting vector
     */
    public Int3 max(int scalar) {
        return new Int3(java.lang.Math.max(this.x, scalar), java.lang.Math.max(this.y, scalar), java.lang.Math.max(this.z, scalar));
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @return the resulting vector
     */
    public Int3 max(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ));
    }

    /**
     * Compute the larger of each component of this vector and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 max(int otherX, int otherY, int otherZ) {
        return new Int3(java.lang.Math.max(this.x, otherX), java.lang.Math.max(this.y, otherY), java.lang.Math.max(this.z, otherZ));
    }

    /**
     * Compute the smaller of each component of this vector and {@code scalar}, returning the result
     * as a value.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return the resulting vector
     */
    public Int3 min(int scalar) {
        return new Int3(java.lang.Math.min(this.x, scalar), java.lang.Math.min(this.y, scalar), java.lang.Math.min(this.z, scalar));
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
     * {@code other}, returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @return the resulting vector
     */
    public Int3 min(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ));
    }

    /**
     * Compute the smaller of each component of this vector and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 min(int otherX, int otherY, int otherZ) {
        return new Int3(java.lang.Math.min(this.x, otherX), java.lang.Math.min(this.y, otherY), java.lang.Math.min(this.z, otherZ));
    }

    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 sign() {
        return new Int3(Math.signum(this.x), Math.signum(this.y), Math.signum(this.z));
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
    public Int3 satAdd(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(org.joml2.SaturatingMath.satAdd(this.x, otherX), org.joml2.SaturatingMath.satAdd(this.y, otherY), org.joml2.SaturatingMath.satAdd(this.z, otherZ));
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 satAdd(int otherX, int otherY, int otherZ) {
        return new Int3(org.joml2.SaturatingMath.satAdd(this.x, otherX), org.joml2.SaturatingMath.satAdd(this.y, otherY), org.joml2.SaturatingMath.satAdd(this.z, otherZ));
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
    public Int3 satMul(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(org.joml2.SaturatingMath.satMul(this.x, otherX), org.joml2.SaturatingMath.satMul(this.y, otherY), org.joml2.SaturatingMath.satMul(this.z, otherZ));
    }

    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 satMul(int otherX, int otherY, int otherZ) {
        return new Int3(org.joml2.SaturatingMath.satMul(this.x, otherX), org.joml2.SaturatingMath.satMul(this.y, otherY), org.joml2.SaturatingMath.satMul(this.z, otherZ));
    }

    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int3 satNegate() {
        return new Int3(org.joml2.SaturatingMath.satNeg(this.x), org.joml2.SaturatingMath.satNeg(this.y), org.joml2.SaturatingMath.satNeg(this.z));
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
    public Int3 satSub(Int3 other) {
        int otherX = other.x;
        int otherY = other.y;
        int otherZ = other.z;
        return new Int3(org.joml2.SaturatingMath.satSub(this.x, otherX), org.joml2.SaturatingMath.satSub(this.y, otherY), org.joml2.SaturatingMath.satSub(this.z, otherZ));
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Int3 satSub(int otherX, int otherY, int otherZ) {
        return new Int3(org.joml2.SaturatingMath.satSub(this.x, otherX), org.joml2.SaturatingMath.satSub(this.y, otherY), org.joml2.SaturatingMath.satSub(this.z, otherZ));
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 xyz0() {
        return new Int4(this.x, this.y, this.z, 0);
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int4 xyz1() {
        return new Int4(this.x, this.y, this.z, 1);
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Int3 withX(int x) {
        return new Int3(x, this.y, this.z);
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Int3 withY(int y) {
        return new Int3(this.x, y, this.z);
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Int3 withXY(int x, int y) {
        return new Int3(x, y, this.z);
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     */
    public Int3 withZ(int z) {
        return new Int3(this.x, this.y, z);
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Int3 withXZ(int x, int z) {
        return new Int3(x, this.y, z);
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Int3 withYZ(int y, int z) {
        return new Int3(this.x, y, z);
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
    public Int3 withXYZ(int x, int y, int z) {
        return new Int3(x, y, z);
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

    @Override public String toString() {
        return "Int3(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int3)) return false;
        Int3 o = (Int3) obj;
        return x == o.x
            && y == o.y
            && z == o.z;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + x;
        h = 31 * h + y;
        h = 31 * h + z;
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
    public boolean equalsEpsilon(Int3 other, int epsilon) {
        return java.lang.Math.abs((long) x - other.x) <= epsilon
            && java.lang.Math.abs((long) y - other.y) <= epsilon
            && java.lang.Math.abs((long) z - other.z) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int3BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Int3BbOpsUnsafe()
            : new Int3BbOpsApi();
        static final Int3RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Int3RawOpsUnsafe()
            : new Int3RawOpsApi();
    }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value; the elements written must lie within {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public int[] store(int[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value; the elements written must lie within {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public int[] store(int[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value; the elements read must lie within {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(int[] src, int offset) {
        int _c0 = src[offset];
        int _c1 = src[offset + 1];
        int _c2 = src[offset + 2];
        return new Int3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value; the elements read must lie within {@code src}.
     *
     * @param src the source array
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(int[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public IntBuffer store(IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public IntBuffer storeRelative(IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Int3 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Int3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 12);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value; the memory at {@code address} must be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Int3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value; the memory at {@code address} must be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Int3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Int3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given array, converting each element to {@code long}, starting at
     * the given offset.
     * <p>
     * Valid input: any value; the elements written must lie within {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public long[] store(long[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code long}.
     * <p>
     * Valid input: any value; the elements written must lie within {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public long[] store(long[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code long}, starting
     * at the given offset.
     * <p>
     * Valid input: any value; the elements read must lie within {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(long[] src, int offset) {
        int _c0 = (int) src[offset];
        int _c1 = (int) src[offset + 1];
        int _c2 = (int) src[offset + 2];
        return new Int3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array, converting each element from {@code long}.
     * <p>
     * Valid input: any value; the elements read must lie within {@code src}.
     *
     * @param src the source array
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(long[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public LongBuffer store(LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public LongBuffer storeRelative(LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code Int3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Int3 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements written must lie within {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeLongAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 loadLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     */
    public static Int3 loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value; the elements read must lie within {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code Int3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Int3 loadLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadLongAbsolute(pos, buf);
        buf.position(pos + 24);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value; the memory at {@code address} must be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Int3 storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value; the memory at {@code address} must be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code Int3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Int3 loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(address);
    }

    private abstract static class Int3BbOps {
        public abstract IntBuffer storeAbsolute(Int3 self, int index, IntBuffer buf);
        public abstract Int3 loadAbsolute(int index, IntBuffer buf);
        public abstract ByteBuffer storeAbsolute(Int3 self, int index, ByteBuffer buf);
        public abstract Int3 loadAbsolute(int index, ByteBuffer buf);
        public abstract LongBuffer storeAbsolute(Int3 self, int index, LongBuffer buf);
        public abstract Int3 loadAbsolute(int index, LongBuffer buf);
        public abstract ByteBuffer storeLongAbsolute(Int3 self, int index, ByteBuffer buf);
        public abstract Int3 loadLongAbsolute(int index, ByteBuffer buf);
    }

    private static final class Int3BbOpsApi extends Int3BbOps {
        public IntBuffer storeAbsolute(Int3 self, int index, IntBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, IntBuffer buf) {
            int _c0 = buf.get(index);
            int _c1 = buf.get(index + 1);
            int _c2 = buf.get(index + 2);
            return new Int3(_c0, _c1, _c2);
        }

        public ByteBuffer storeAbsolute(Int3 self, int index, ByteBuffer buf) {
            buf.putInt(index, self.x);
            buf.putInt(index + 4, self.y);
            buf.putInt(index + 8, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, ByteBuffer buf) {
            int _c0 = buf.getInt(index);
            int _c1 = buf.getInt(index + 4);
            int _c2 = buf.getInt(index + 8);
            return new Int3(_c0, _c1, _c2);
        }

        public LongBuffer storeAbsolute(Int3 self, int index, LongBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, LongBuffer buf) {
            int _c0 = (int) buf.get(index);
            int _c1 = (int) buf.get(index + 1);
            int _c2 = (int) buf.get(index + 2);
            return new Int3(_c0, _c1, _c2);
        }

        public ByteBuffer storeLongAbsolute(Int3 self, int index, ByteBuffer buf) {
            buf.putLong(index, self.x);
            buf.putLong(index + 8, self.y);
            buf.putLong(index + 16, self.z);
            return buf;
        }

        public Int3 loadLongAbsolute(int index, ByteBuffer buf) {
            int _c0 = (int) buf.getLong(index);
            int _c1 = (int) buf.getLong(index + 8);
            int _c2 = (int) buf.getLong(index + 16);
            return new Int3(_c0, _c1, _c2);
        }
    }

    private static final class Int3BbOpsUnsafe extends Int3BbOps {
        private static final Int3BbOpsApi API = new Int3BbOpsApi();

        public IntBuffer storeAbsolute(Int3 self, int index, IntBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putInt(address, self.x);
            U.putInt(address + 4L, self.y);
            U.putInt(address + 8L, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, IntBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            int _c0 = U.getInt(address);
            int _c1 = U.getInt(address + 4L);
            int _c2 = U.getInt(address + 8L);
            return new Int3(_c0, _c1, _c2);
        }

        public ByteBuffer storeAbsolute(Int3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putInt(address, self.x);
            U.putInt(address + 4L, self.y);
            U.putInt(address + 8L, self.z);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(Int3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putInt(bytes, address, self.x);
            U.putInt(bytes, address + 4L, self.y);
            U.putInt(bytes, address + 8L, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            int _c0 = U.getInt(address);
            int _c1 = U.getInt(address + 4L);
            int _c2 = U.getInt(address + 8L);
            return new Int3(_c0, _c1, _c2);
        }

        private static Int3 loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            int _c0 = U.getInt(bytes, address);
            int _c1 = U.getInt(bytes, address + 4L);
            int _c2 = U.getInt(bytes, address + 8L);
            return new Int3(_c0, _c1, _c2);
        }

        public LongBuffer storeAbsolute(Int3 self, int index, LongBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putLong(address, self.x);
            U.putLong(address + 8L, self.y);
            U.putLong(address + 16L, self.z);
            return buf;
        }

        public Int3 loadAbsolute(int index, LongBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            int _c0 = (int) U.getLong(address);
            int _c1 = (int) U.getLong(address + 8L);
            int _c2 = (int) U.getLong(address + 16L);
            return new Int3(_c0, _c1, _c2);
        }

        public ByteBuffer storeLongAbsolute(Int3 self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeLongAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putLong(address, self.x);
            U.putLong(address + 8L, self.y);
            U.putLong(address + 16L, self.z);
            return buf;
        }

        private static ByteBuffer storeLongAbsoluteElse(Int3 self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeLongAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putLong(bytes, address, self.x);
            U.putLong(bytes, address + 8L, self.y);
            U.putLong(bytes, address + 16L, self.z);
            return buf;
        }

        public Int3 loadLongAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadLongAbsoluteElse(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            int _c0 = (int) U.getLong(address);
            int _c1 = (int) U.getLong(address + 8L);
            int _c2 = (int) U.getLong(address + 16L);
            return new Int3(_c0, _c1, _c2);
        }

        private static Int3 loadLongAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadLongAbsolute(index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            int _c0 = (int) U.getLong(bytes, address);
            int _c1 = (int) U.getLong(bytes, address + 8L);
            int _c2 = (int) U.getLong(bytes, address + 16L);
            return new Int3(_c0, _c1, _c2);
        }
    }

    private abstract static class Int3RawOps {
        public abstract Int3 storeUnsafe(Int3 self, long address);
        public abstract Int3 loadUnsafe(long address);
        public abstract Int3 storeLongUnsafe(Int3 self, long address);
        public abstract Int3 loadLongUnsafe(long address);
    }

    /**
     * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
     * every method throws {@link UnsupportedOperationException} - without a segment view there is no
     * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
     * to use the raw-address forms on this variant.
     */
    private static final class Int3RawOpsApi extends Int3RawOps {
        public Int3 storeUnsafe(Int3 self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public Int3 loadUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public Int3 storeLongUnsafe(Int3 self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public Int3 loadLongUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }
    }

    private static final class Int3RawOpsUnsafe extends Int3RawOps {
        public Int3 storeUnsafe(Int3 self, long address) {
            U.putInt(address, self.x);
            U.putInt(address + 4L, self.y);
            U.putInt(address + 8L, self.z);
            return self;
        }

        public Int3 storeHeap(Int3 self, byte[] bytes, long address) {
            U.putInt(bytes, address, self.x);
            U.putInt(bytes, address + 4L, self.y);
            U.putInt(bytes, address + 8L, self.z);
            return self;
        }

        public Int3 loadUnsafe(long address) {
            int _c0 = U.getInt(address);
            int _c1 = U.getInt(address + 4L);
            int _c2 = U.getInt(address + 8L);
            return new Int3(_c0, _c1, _c2);
        }

        public Int3 loadHeap(byte[] bytes, long address) {
            int _c0 = U.getInt(bytes, address);
            int _c1 = U.getInt(bytes, address + 4L);
            int _c2 = U.getInt(bytes, address + 8L);
            return new Int3(_c0, _c1, _c2);
        }

        public Int3 storeLongUnsafe(Int3 self, long address) {
            U.putLong(address, self.x);
            U.putLong(address + 8L, self.y);
            U.putLong(address + 16L, self.z);
            return self;
        }

        public Int3 storeLongHeap(Int3 self, byte[] bytes, long address) {
            U.putLong(bytes, address, self.x);
            U.putLong(bytes, address + 8L, self.y);
            U.putLong(bytes, address + 16L, self.z);
            return self;
        }

        public Int3 loadLongUnsafe(long address) {
            int _c0 = (int) U.getLong(address);
            int _c1 = (int) U.getLong(address + 8L);
            int _c2 = (int) U.getLong(address + 16L);
            return new Int3(_c0, _c1, _c2);
        }

        public Int3 loadLongHeap(byte[] bytes, long address) {
            int _c0 = (int) U.getLong(bytes, address);
            int _c1 = (int) U.getLong(bytes, address + 8L);
            int _c2 = (int) U.getLong(bytes, address + 16L);
            return new Int3(_c0, _c1, _c2);
        }
    }
}
