// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;

/**
 * Immutable 3D vector of {@code short} components.
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
 * @param z the {@code z} component
 */
public record Short3(short x, short y, short z) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 6;

    /** The zero vector (all components 0). */
    public static final Short3 ZERO = new Short3((short) 0, (short) 0, (short) 0);

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public Short3(short x, short y, short z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: any value.
     */
    public Short3() {
        this((short) 0, (short) 0, (short) 0);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Short3(short s) {
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
    public Short3(short v0, Short2 v1) {
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
    public Short3(Short2 v0, short v1) {
        this(v0.x(), v0.y(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public short x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value. */
    public short y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value. */
    public short z() { return z; }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Short3 add(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (otherX + this.x), (short) (otherY + this.y), (short) (otherZ + this.z));
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
    public Short3 add(short otherX, short otherY, short otherZ) {
        return new Short3((short) (otherX + this.x), (short) (otherY + this.y), (short) (otherZ + this.z));
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
    public Short3 ceilDiv(short scalar) {
        return new Short3((short) (java.lang.Math.ceilDiv(this.x, scalar)), (short) (java.lang.Math.ceilDiv(this.y, scalar)), (short) (java.lang.Math.ceilDiv(this.z, scalar)));
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
    public Short3 ceilDiv(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (java.lang.Math.ceilDiv(this.x, otherX)), (short) (java.lang.Math.ceilDiv(this.y, otherY)), (short) (java.lang.Math.ceilDiv(this.z, otherZ)));
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
    public Short3 ceilDiv(short otherX, short otherY, short otherZ) {
        return new Short3((short) (java.lang.Math.ceilDiv(this.x, otherX)), (short) (java.lang.Math.ceilDiv(this.y, otherY)), (short) (java.lang.Math.ceilDiv(this.z, otherZ)));
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
    public Short3 ceilMod(short scalar) {
        return new Short3((short) (java.lang.Math.ceilMod(this.x, scalar)), (short) (java.lang.Math.ceilMod(this.y, scalar)), (short) (java.lang.Math.ceilMod(this.z, scalar)));
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
    public Short3 ceilMod(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (java.lang.Math.ceilMod(this.x, otherX)), (short) (java.lang.Math.ceilMod(this.y, otherY)), (short) (java.lang.Math.ceilMod(this.z, otherZ)));
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
    public Short3 ceilMod(short otherX, short otherY, short otherZ) {
        return new Short3((short) (java.lang.Math.ceilMod(this.x, otherX)), (short) (java.lang.Math.ceilMod(this.y, otherY)), (short) (java.lang.Math.ceilMod(this.z, otherZ)));
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
    public Short3 div(short scalar) {
        return new Short3((short) (this.x / scalar), (short) (this.y / scalar), (short) (this.z / scalar));
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
    public Short3 div(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x / otherX), (short) (this.y / otherY), (short) (this.z / otherZ));
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
    public Short3 div(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x / otherX), (short) (this.y / otherY), (short) (this.z / otherZ));
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
    public Short3 floorDiv(short scalar) {
        return new Short3((short) (Math.floorDiv(this.x, scalar)), (short) (Math.floorDiv(this.y, scalar)), (short) (Math.floorDiv(this.z, scalar)));
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
    public Short3 floorDiv(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (Math.floorDiv(this.x, otherX)), (short) (Math.floorDiv(this.y, otherY)), (short) (Math.floorDiv(this.z, otherZ)));
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
    public Short3 floorDiv(short otherX, short otherY, short otherZ) {
        return new Short3((short) (Math.floorDiv(this.x, otherX)), (short) (Math.floorDiv(this.y, otherY)), (short) (Math.floorDiv(this.z, otherZ)));
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
    public Short3 floorMod(short scalar) {
        return new Short3((short) (Math.floorMod(this.x, scalar)), (short) (Math.floorMod(this.y, scalar)), (short) (Math.floorMod(this.z, scalar)));
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
    public Short3 floorMod(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (Math.floorMod(this.x, otherX)), (short) (Math.floorMod(this.y, otherY)), (short) (Math.floorMod(this.z, otherZ)));
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
    public Short3 floorMod(short otherX, short otherY, short otherZ) {
        return new Short3((short) (Math.floorMod(this.x, otherX)), (short) (Math.floorMod(this.y, otherY)), (short) (Math.floorMod(this.z, otherZ)));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Short3 mul(short scalar) {
        return new Short3((short) (scalar * this.x), (short) (scalar * this.y), (short) (scalar * this.z));
    }


    /**
     * Multiply this vector component-wise by {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return the resulting vector
     */
    public Short3 mul(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (otherX * this.x), (short) (otherY * this.y), (short) (otherZ * this.z));
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
    public Short3 mul(short otherX, short otherY, short otherZ) {
        return new Short3((short) (otherX * this.x), (short) (otherY * this.y), (short) (otherZ * this.z));
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 negate() {
        return new Short3((short) (-this.x), (short) (-this.y), (short) (-this.z));
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
    public Short3 rem(short scalar) {
        return new Short3((short) (this.x % scalar), (short) (this.y % scalar), (short) (this.z % scalar));
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
    public Short3 rem(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x % otherX), (short) (this.y % otherY), (short) (this.z % otherZ));
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
    public Short3 rem(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x % otherX), (short) (this.y % otherY), (short) (this.z % otherZ));
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Short3 sub(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x - otherX), (short) (this.y - otherY), (short) (this.z - otherZ));
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
    public Short3 sub(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x - otherX), (short) (this.y - otherY), (short) (this.z - otherZ));
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
    public Short3 and(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x & otherX), (short) (this.y & otherY), (short) (this.z & otherZ));
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
    public Short3 and(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x & otherX), (short) (this.y & otherY), (short) (this.z & otherZ));
    }


    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 bitCount() {
        return new Short3((short) (Math.bitCount(this.x)), (short) (Math.bitCount(this.y)), (short) (Math.bitCount(this.z)));
    }


    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 not() {
        return new Short3((short) (~this.x), (short) (~this.y), (short) (~this.z));
    }


    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 numberOfLeadingZeros() {
        return new Short3((short) (Math.numberOfLeadingZeros(this.x)), (short) (Math.numberOfLeadingZeros(this.y)), (short) (Math.numberOfLeadingZeros(this.z)));
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 numberOfTrailingZeros() {
        return new Short3((short) (Math.numberOfTrailingZeros(this.x)), (short) (Math.numberOfTrailingZeros(this.y)), (short) (Math.numberOfTrailingZeros(this.z)));
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
    public Short3 or(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x | otherX), (short) (this.y | otherY), (short) (this.z | otherZ));
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
    public Short3 or(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x | otherX), (short) (this.y | otherY), (short) (this.z | otherZ));
    }


    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 reverseBits() {
        return new Short3((short) (Math.reverseBits(this.x)), (short) (Math.reverseBits(this.y)), (short) (Math.reverseBits(this.z)));
    }


    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 reverseBytes() {
        return new Short3((short) (Math.reverseBytes(this.x)), (short) (Math.reverseBytes(this.y)), (short) (Math.reverseBytes(this.z)));
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
    public Short3 rotateLeft(short distance) {
        return new Short3((short) (Math.rotateLeft(this.x, distance)), (short) (Math.rotateLeft(this.y, distance)), (short) (Math.rotateLeft(this.z, distance)));
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
    public Short3 rotateRight(short distance) {
        return new Short3((short) (Math.rotateRight(this.x, distance)), (short) (Math.rotateRight(this.y, distance)), (short) (Math.rotateRight(this.z, distance)));
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Short3 shl(short shift) {
        return new Short3((short) (this.x << (shift & 15)), (short) (this.y << (shift & 15)), (short) (this.z << (shift & 15)));
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Short3 shr(short shift) {
        return new Short3((short) (this.x >> (shift & 15)), (short) (this.y >> (shift & 15)), (short) (this.z >> (shift & 15)));
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @return the resulting vector
     */
    public Short3 ushr(short shift) {
        return new Short3((short) ((this.x & 0xFFFF) >>> (shift & 15)), (short) ((this.y & 0xFFFF) >>> (shift & 15)), (short) ((this.z & 0xFFFF) >>> (shift & 15)));
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
    public Short3 xor(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (this.x ^ otherX), (short) (this.y ^ otherY), (short) (this.z ^ otherZ));
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
    public Short3 xor(short otherX, short otherY, short otherZ) {
        return new Short3((short) (this.x ^ otherX), (short) (this.y ^ otherY), (short) (this.z ^ otherZ));
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Short3 set(Short3 v) {
        short vX = v.x();
        short vY = v.y();
        short vZ = v.z();
        return new Short3((short) (vX), (short) (vY), (short) (vZ));
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
    public Short3 set(short vX, short vY, short vZ) {
        return new Short3((short) (vX), (short) (vY), (short) (vZ));
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Short3 set(short s) {
        return new Short3((short) (s), (short) (s), (short) (s));
    }


    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
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
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int3} holding the result
     */
    public Int3 toInt() {
        return new Int3(this.x, this.y, this.z);
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
    public static Short3 makeZero() {
        return Short3.ZERO;
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 absolute() {
        return new Short3((short) (java.lang.Math.abs(this.x)), (short) (java.lang.Math.abs(this.y)), (short) (java.lang.Math.abs(this.z)));
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
    public Short3 clamp(short min, short max) {
        return new Short3((short) (java.lang.Math.min(java.lang.Math.max(this.x, min), max)), (short) (java.lang.Math.min(java.lang.Math.max(this.y, min), max)), (short) (java.lang.Math.min(java.lang.Math.max(this.z, min), max)));
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
    public Short3 clamp(Short3 min, Short3 max) {
        short minX = min.x();
        short minY = min.y();
        short minZ = min.z();
        short maxX = max.x();
        short maxY = max.y();
        short maxZ = max.z();
        return new Short3((short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX)), (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY)), (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ)));
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
    public Short3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ) {
        return new Short3((short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX)), (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY)), (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ)));
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public int compAdd() {
        return this.z + (this.x + this.y);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public short compMax() {
        return (short) (java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public short compMin() {
        return (short) (java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z));
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public Short3 cross(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (otherZ * this.y - otherY * this.z), (short) (otherX * this.z - otherZ * this.x), (short) (otherY * this.x - otherX * this.y));
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
    public Short3 cross(short otherX, short otherY, short otherZ) {
        return new Short3((short) (otherZ * this.y - otherY * this.z), (short) (otherX * this.z - otherZ * this.x), (short) (otherY * this.x - otherX * this.y));
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public long distanceSquared(Short3 other) {
        long _t0 = (long) this.x - other.x();
        long _t1 = (long) this.y - other.y();
        long _t2 = (long) this.z - other.z();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long distanceSquared(short otherX, short otherY, short otherZ) {
        long _t0 = (long) this.x - otherX;
        long _t1 = (long) this.y - otherY;
        long _t2 = (long) this.z - otherZ;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public long dot(Short3 other) {
        return (long) other.x() * this.x + (long) other.y() * this.y + (long) other.z() * this.z;
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public long dot(short otherX, short otherY, short otherZ) {
        return (long) otherX * this.x + (long) otherY * this.y + (long) otherZ * this.z;
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public int manhattanDistance(Short3 other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public int manhattanDistance(short otherX, short otherY, short otherZ) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public int manhattanLength() {
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y) + java.lang.Math.abs(this.z);
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
    public Short3 max(short scalar) {
        return new Short3((short) (java.lang.Math.max(this.x, scalar)), (short) (java.lang.Math.max(this.y, scalar)), (short) (java.lang.Math.max(this.z, scalar)));
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
    public Short3 max(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (java.lang.Math.max(this.x, otherX)), (short) (java.lang.Math.max(this.y, otherY)), (short) (java.lang.Math.max(this.z, otherZ)));
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Short3 max(short otherX, short otherY, short otherZ) {
        return new Short3((short) (java.lang.Math.max(this.x, otherX)), (short) (java.lang.Math.max(this.y, otherY)), (short) (java.lang.Math.max(this.z, otherZ)));
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
    public Short3 min(short scalar) {
        return new Short3((short) (java.lang.Math.min(this.x, scalar)), (short) (java.lang.Math.min(this.y, scalar)), (short) (java.lang.Math.min(this.z, scalar)));
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
    public Short3 min(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (java.lang.Math.min(this.x, otherX)), (short) (java.lang.Math.min(this.y, otherY)), (short) (java.lang.Math.min(this.z, otherZ)));
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}), returning the result as a value.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the resulting vector
     */
    public Short3 min(short otherX, short otherY, short otherZ) {
        return new Short3((short) (java.lang.Math.min(this.x, otherX)), (short) (java.lang.Math.min(this.y, otherY)), (short) (java.lang.Math.min(this.z, otherZ)));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 sign() {
        return new Short3((short) (Math.signum(this.x)), (short) (Math.signum(this.y)), (short) (Math.signum(this.z)));
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
    public Short3 satAdd(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (org.joml2.SaturatingMath.satAddS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ)));
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
    public Short3 satAdd(short otherX, short otherY, short otherZ) {
        return new Short3((short) (org.joml2.SaturatingMath.satAddS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ)));
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
    public Short3 satMul(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (org.joml2.SaturatingMath.satMulS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ)));
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
    public Short3 satMul(short otherX, short otherY, short otherZ) {
        return new Short3((short) (org.joml2.SaturatingMath.satMulS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ)));
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short3 satNegate() {
        return new Short3((short) (org.joml2.SaturatingMath.satNegS(this.x)), (short) (org.joml2.SaturatingMath.satNegS(this.y)), (short) (org.joml2.SaturatingMath.satNegS(this.z)));
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
    public Short3 satSub(Short3 other) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        return new Short3((short) (org.joml2.SaturatingMath.satSubS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ)));
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
    public Short3 satSub(short otherX, short otherY, short otherZ) {
        return new Short3((short) (org.joml2.SaturatingMath.satSubS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ)));
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 xyz0() {
        return new Short4((short) (this.x), (short) (this.y), (short) (this.z), (short) (0));
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 xyz1() {
        return new Short4((short) (this.x), (short) (this.y), (short) (this.z), (short) (1));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Short3 withX(short x) {
        return new Short3(x, this.y(), this.z());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Short3 withY(short y) {
        return new Short3(this.x(), y, this.z());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Short3 withXY(short x, short y) {
        return new Short3(x, y, this.z());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     */
    public Short3 withZ(short z) {
        return new Short3(this.x(), this.y(), z);
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Short3 withXZ(short x, short z) {
        return new Short3(x, this.y(), z);
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Short3 withYZ(short y, short z) {
        return new Short3(this.x(), y, z);
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
    public Short3 withXYZ(short x, short y, short z) {
        return new Short3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 xx() {
        return new Short2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 xy() {
        return new Short2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 xz() {
        return new Short2(x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 yx() {
        return new Short2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 yy() {
        return new Short2(y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 yz() {
        return new Short2(y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 zx() {
        return new Short2(z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 zy() {
        return new Short2(z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 zz() {
        return new Short2(z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xxx() {
        return new Short3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xxy() {
        return new Short3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xxz() {
        return new Short3(x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xyx() {
        return new Short3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xyy() {
        return new Short3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xyz() {
        return new Short3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xzx() {
        return new Short3(x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xzy() {
        return new Short3(x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xzz() {
        return new Short3(x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yxx() {
        return new Short3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yxy() {
        return new Short3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yxz() {
        return new Short3(y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yyx() {
        return new Short3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yyy() {
        return new Short3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yyz() {
        return new Short3(y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yzx() {
        return new Short3(y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yzy() {
        return new Short3(y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yzz() {
        return new Short3(y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zxx() {
        return new Short3(z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zxy() {
        return new Short3(z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zxz() {
        return new Short3(z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zyx() {
        return new Short3(z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zyy() {
        return new Short3(z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zyz() {
        return new Short3(z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zzx() {
        return new Short3(z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zzy() {
        return new Short3(z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zzz() {
        return new Short3(z, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxxx() {
        return new Short4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxxy() {
        return new Short4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxxz() {
        return new Short4(x, x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxyx() {
        return new Short4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxyy() {
        return new Short4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxyz() {
        return new Short4(x, x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxzx() {
        return new Short4(x, x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxzy() {
        return new Short4(x, x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxzz() {
        return new Short4(x, x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyxx() {
        return new Short4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyxy() {
        return new Short4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyxz() {
        return new Short4(x, y, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyyx() {
        return new Short4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyyy() {
        return new Short4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyyz() {
        return new Short4(x, y, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyzx() {
        return new Short4(x, y, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyzy() {
        return new Short4(x, y, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyzz() {
        return new Short4(x, y, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzxx() {
        return new Short4(x, z, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzxy() {
        return new Short4(x, z, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzxz() {
        return new Short4(x, z, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzyx() {
        return new Short4(x, z, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzyy() {
        return new Short4(x, z, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzyz() {
        return new Short4(x, z, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzzx() {
        return new Short4(x, z, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzzy() {
        return new Short4(x, z, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzzz() {
        return new Short4(x, z, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxxx() {
        return new Short4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxxy() {
        return new Short4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxxz() {
        return new Short4(y, x, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxyx() {
        return new Short4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxyy() {
        return new Short4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxyz() {
        return new Short4(y, x, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxzx() {
        return new Short4(y, x, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxzy() {
        return new Short4(y, x, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxzz() {
        return new Short4(y, x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyxx() {
        return new Short4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyxy() {
        return new Short4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyxz() {
        return new Short4(y, y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyyx() {
        return new Short4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyyy() {
        return new Short4(y, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyyz() {
        return new Short4(y, y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyzx() {
        return new Short4(y, y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyzy() {
        return new Short4(y, y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyzz() {
        return new Short4(y, y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzxx() {
        return new Short4(y, z, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzxy() {
        return new Short4(y, z, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzxz() {
        return new Short4(y, z, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzyx() {
        return new Short4(y, z, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzyy() {
        return new Short4(y, z, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzyz() {
        return new Short4(y, z, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzzx() {
        return new Short4(y, z, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzzy() {
        return new Short4(y, z, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzzz() {
        return new Short4(y, z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxxx() {
        return new Short4(z, x, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxxy() {
        return new Short4(z, x, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxxz() {
        return new Short4(z, x, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxyx() {
        return new Short4(z, x, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxyy() {
        return new Short4(z, x, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxyz() {
        return new Short4(z, x, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxzx() {
        return new Short4(z, x, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxzy() {
        return new Short4(z, x, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxzz() {
        return new Short4(z, x, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyxx() {
        return new Short4(z, y, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyxy() {
        return new Short4(z, y, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyxz() {
        return new Short4(z, y, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyyx() {
        return new Short4(z, y, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyyy() {
        return new Short4(z, y, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyyz() {
        return new Short4(z, y, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyzx() {
        return new Short4(z, y, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyzy() {
        return new Short4(z, y, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyzz() {
        return new Short4(z, y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzxx() {
        return new Short4(z, z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzxy() {
        return new Short4(z, z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzxz() {
        return new Short4(z, z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzyx() {
        return new Short4(z, z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzyy() {
        return new Short4(z, z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzyz() {
        return new Short4(z, z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzzx() {
        return new Short4(z, z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzzy() {
        return new Short4(z, z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzzz() {
        return new Short4(z, z, z, z);
    }

    @Override public String toString() {
        return "Short3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short3)) return false;
        Short3 o = (Short3) obj;
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
     * the difference is widened to {@code int} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Short3 other, short epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3SegOpsUnsafe()
                        : new Short3SegOpsMS();
        static final Short3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3BbOpsUnsafe()
                        : new Short3BbOpsApi();
        static final Short3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3RawOpsUnsafe()
                        : new Short3RawOpsApi();
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
    public short[] store(short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
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
    public short[] store(short[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(short[] src, int offset) {
        short _c0 = src[offset];
        short _c1 = src[offset + 1];
        short _c2 = src[offset + 2];
        return new Short3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(short[] src) { return load(src, 0); }

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
     * @param buf the destination buffer
     * @return buf
     */
    public ShortBuffer store(ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     */
    public ShortBuffer storeAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ShortBuffer storeRelative(ShortBuffer buf) {
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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Short3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Short3 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Short3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Short3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short3 r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
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
    public Short3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadUnsafe(long address) {
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
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code byte}, starting at
     * the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public byte[] store(byte[] dest, int offset) {
        dest[offset] = (byte) this.x;
        dest[offset + 1] = (byte) this.y;
        dest[offset + 2] = (byte) this.z;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code byte}.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public byte[] store(byte[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code byte}, starting
     * at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(byte[] src, int offset) {
        short _c0 = src[offset];
        short _c1 = src[offset + 1];
        short _c2 = src[offset + 2];
        return new Short3(_c0, _c1, _c2);
    }

    /**
     * Load the elements from the given array, converting each element from {@code byte}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 load(byte[] src) { return load(src, 0); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code byte},
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
    public ByteBuffer storeByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code byte},
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
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code byte},
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
    public ByteBuffer storeByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeByteAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Short3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Short3 loadByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short3 r = StoreLoad.BB_OPS.loadByteAbsolute(pos, buf);
        buf.position(pos + 3);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code byte}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    public Short3 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code byte}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code byte}.
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
    public MemorySegment storeByte(MemorySegment dest) { return StoreLoad.SEG_OPS.storeByte(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code byte},
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
    public MemorySegment storeByte(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeByte(this, offset, dest);
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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadByte(MemorySegment src) { return StoreLoad.SEG_OPS.loadByte(0L, src); }

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
     * @return a new {@code Short3} holding the loaded elements
     */
    public static Short3 loadByte(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadByte(offset, src);
    }
}
