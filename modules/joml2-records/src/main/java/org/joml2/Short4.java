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
 * Immutable 4D vector of {@code short} components.
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
 * @param w the {@code w} component
 */
public record Short4(short x, short y, short z, short w) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 8;

    /** The zero vector (all components 0). */
    public static final Short4 ZERO = new Short4((short) 0, (short) 0, (short) 0, (short) 0);

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
    public Short4(short x, short y, short z, short w) {
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
    public Short4() {
        this((short) 0, (short) 0, (short) 0, (short) 1);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Short4(short s) {
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
    public Short4(short v0, short v1, Short2 v2) {
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
    public Short4(short v0, Short2 v1, short v2) {
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
    public Short4(short v0, Short3 v1) {
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
    public Short4(Short2 v0, short v1, short v2) {
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
    public Short4(Short2 v0, Short2 v1) {
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
    public Short4(Short3 v0, short v1) {
        this(v0.x(), v0.y(), v0.z(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public short x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value. */
    public short y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value. */
    public short z() { return z; }
    /** {@return the {@code w} component} <p>Valid input: any value. */
    public short w() { return w; }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Short4 add(Short4 other) {
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
    public Short4 add(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (otherX + this.x), (short) (otherY + this.y), (short) (otherZ + this.z), (short) (otherW + this.w));
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
    public Short4 ceilDiv(short scalar) {
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
    public Short4 ceilDiv(Short4 other) {
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
    public Short4 ceilDiv(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (java.lang.Math.ceilDiv(this.x, otherX)), (short) (java.lang.Math.ceilDiv(this.y, otherY)), (short) (java.lang.Math.ceilDiv(this.z, otherZ)), (short) (java.lang.Math.ceilDiv(this.w, otherW)));
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
    public Short4 ceilMod(short scalar) {
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
    public Short4 ceilMod(Short4 other) {
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
    public Short4 ceilMod(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (java.lang.Math.ceilMod(this.x, otherX)), (short) (java.lang.Math.ceilMod(this.y, otherY)), (short) (java.lang.Math.ceilMod(this.z, otherZ)), (short) (java.lang.Math.ceilMod(this.w, otherW)));
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
    public Short4 div(short scalar) {
        return new Short4((short) (this.x / scalar), (short) (this.y / scalar), (short) (this.z / scalar), (short) (this.w / scalar));
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
    public Short4 div(Short4 other) {
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
    public Short4 div(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x / otherX), (short) (this.y / otherY), (short) (this.z / otherZ), (short) (this.w / otherW));
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
    public Short4 floorDiv(short scalar) {
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
    public Short4 floorDiv(Short4 other) {
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
    public Short4 floorDiv(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (Math.floorDiv(this.x, otherX)), (short) (Math.floorDiv(this.y, otherY)), (short) (Math.floorDiv(this.z, otherZ)), (short) (Math.floorDiv(this.w, otherW)));
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
    public Short4 floorMod(short scalar) {
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
    public Short4 floorMod(Short4 other) {
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
    public Short4 floorMod(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (Math.floorMod(this.x, otherX)), (short) (Math.floorMod(this.y, otherY)), (short) (Math.floorMod(this.z, otherZ)), (short) (Math.floorMod(this.w, otherW)));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Short4 mul(short scalar) {
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
    public Short4 mul(Short4 other) {
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
    public Short4 mul(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (otherX * this.x), (short) (otherY * this.y), (short) (otherZ * this.z), (short) (otherW * this.w));
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 negate() {
        return new Short4((short) (-this.x), (short) (-this.y), (short) (-this.z), (short) (-this.w));
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
    public Short4 rem(short scalar) {
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
    public Short4 rem(Short4 other) {
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
    public Short4 rem(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x % otherX), (short) (this.y % otherY), (short) (this.z % otherZ), (short) (this.w % otherW));
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Short4 sub(Short4 other) {
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
    public Short4 sub(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x - otherX), (short) (this.y - otherY), (short) (this.z - otherZ), (short) (this.w - otherW));
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
    public Short4 and(Short4 other) {
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
    public Short4 and(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x & otherX), (short) (this.y & otherY), (short) (this.z & otherZ), (short) (this.w & otherW));
    }


    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 bitCount() {
        return new Short4((short) (Math.bitCount(this.x)), (short) (Math.bitCount(this.y)), (short) (Math.bitCount(this.z)), (short) (Math.bitCount(this.w)));
    }


    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 not() {
        return new Short4((short) (~this.x), (short) (~this.y), (short) (~this.z), (short) (~this.w));
    }


    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 numberOfLeadingZeros() {
        return new Short4((short) (Math.numberOfLeadingZeros(this.x)), (short) (Math.numberOfLeadingZeros(this.y)), (short) (Math.numberOfLeadingZeros(this.z)), (short) (Math.numberOfLeadingZeros(this.w)));
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 numberOfTrailingZeros() {
        return new Short4((short) (Math.numberOfTrailingZeros(this.x)), (short) (Math.numberOfTrailingZeros(this.y)), (short) (Math.numberOfTrailingZeros(this.z)), (short) (Math.numberOfTrailingZeros(this.w)));
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
    public Short4 or(Short4 other) {
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
    public Short4 or(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x | otherX), (short) (this.y | otherY), (short) (this.z | otherZ), (short) (this.w | otherW));
    }


    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 reverseBits() {
        return new Short4((short) (Math.reverseBits(this.x)), (short) (Math.reverseBits(this.y)), (short) (Math.reverseBits(this.z)), (short) (Math.reverseBits(this.w)));
    }


    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 reverseBytes() {
        return new Short4((short) (Math.reverseBytes(this.x)), (short) (Math.reverseBytes(this.y)), (short) (Math.reverseBytes(this.z)), (short) (Math.reverseBytes(this.w)));
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
    public Short4 rotateLeft(short distance) {
        return new Short4((short) (Math.rotateLeft(this.x, distance)), (short) (Math.rotateLeft(this.y, distance)), (short) (Math.rotateLeft(this.z, distance)), (short) (Math.rotateLeft(this.w, distance)));
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
    public Short4 rotateRight(short distance) {
        return new Short4((short) (Math.rotateRight(this.x, distance)), (short) (Math.rotateRight(this.y, distance)), (short) (Math.rotateRight(this.z, distance)), (short) (Math.rotateRight(this.w, distance)));
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
    public Short4 shl(short shift) {
        return new Short4((short) (this.x << (shift & 15)), (short) (this.y << (shift & 15)), (short) (this.z << (shift & 15)), (short) (this.w << (shift & 15)));
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
    public Short4 shr(short shift) {
        return new Short4((short) (this.x >> (shift & 15)), (short) (this.y >> (shift & 15)), (short) (this.z >> (shift & 15)), (short) (this.w >> (shift & 15)));
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
    public Short4 ushr(short shift) {
        return new Short4((short) ((this.x & 0xFFFF) >>> (shift & 15)), (short) ((this.y & 0xFFFF) >>> (shift & 15)), (short) ((this.z & 0xFFFF) >>> (shift & 15)), (short) ((this.w & 0xFFFF) >>> (shift & 15)));
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
    public Short4 xor(Short4 other) {
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
    public Short4 xor(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (this.x ^ otherX), (short) (this.y ^ otherY), (short) (this.z ^ otherZ), (short) (this.w ^ otherW));
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Short4 set(Short4 v) {
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
    public Short4 set(short vX, short vY, short vZ, short vW) {
        return new Short4((short) (vX), (short) (vY), (short) (vZ), (short) (vW));
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Short4 set(short s) {
        return new Short4((short) (s), (short) (s), (short) (s), (short) (s));
    }


    /**
     * Convert this vector to {@code float} precision, returning the result as a new instance.
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
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int4} holding the result
     */
    public Int4 toInt() {
        return new Int4(this.x, this.y, this.z, this.w);
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
    public static Short4 makeZero() {
        return Short4.ZERO;
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 absolute() {
        return new Short4((short) (Math.abs(this.x)), (short) (Math.abs(this.y)), (short) (Math.abs(this.z)), (short) (Math.abs(this.w)));
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
    public Short4 clamp(short min, short max) {
        return new Short4((short) (Math.min(Math.max(this.x, min), max)), (short) (Math.min(Math.max(this.y, min), max)), (short) (Math.min(Math.max(this.z, min), max)), (short) (Math.min(Math.max(this.w, min), max)));
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
    public Short4 clamp(Short4 min, Short4 max) {
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
    public Short4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW) {
        return new Short4((short) (Math.min(Math.max(this.x, minX), maxX)), (short) (Math.min(Math.max(this.y, minY), maxY)), (short) (Math.min(Math.max(this.z, minZ), maxZ)), (short) (Math.min(Math.max(this.w, minW), maxW)));
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
        return this.w + (this.z + (this.x + this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public short compMax() {
        return (short) (Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public short compMin() {
        return (short) (Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w));
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
    public Short4 cross(Short4 v, Short4 w) {
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
    public Short4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW) {
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        return new Short4((short) (this.y * _t12 - this.z * _t13 + this.w * _t14), (short) (this.z * _t15 - this.x * _t12 - this.w * _t16), (short) (this.x * _t13 - this.y * _t15 + this.w * _t17), (short) (this.y * _t16 - this.x * _t14 - this.z * _t17));
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
    public long distanceSquared(Short4 other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public long distanceSquared(short otherX, short otherY, short otherZ, short otherW) {
        long _t0 = (long) this.x - (long) otherX;
        long _t1 = (long) this.y - (long) otherY;
        long _t2 = (long) this.z - (long) otherZ;
        long _t3 = (long) this.w - (long) otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
    public long dot(Short4 other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public long dot(short otherX, short otherY, short otherZ, short otherW) {
        return (long) otherX * (long) this.x + (long) otherY * (long) this.y + (long) otherZ * (long) this.z + (long) otherW * (long) this.w;
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
        return (long) this.x * (long) this.x + (long) this.y * (long) this.y + (long) this.z * (long) this.z + (long) this.w * (long) this.w;
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
    public int manhattanDistance(Short4 other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public int manhattanDistance(short otherX, short otherY, short otherZ, short otherW) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY) + Math.abs(this.z - otherZ) + Math.abs(this.w - otherW);
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
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z) + Math.abs(this.w);
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
    public Short4 max(short scalar) {
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
    public Short4 max(Short4 other) {
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
    public Short4 max(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (Math.max(this.x, otherX)), (short) (Math.max(this.y, otherY)), (short) (Math.max(this.z, otherZ)), (short) (Math.max(this.w, otherW)));
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
    public Short4 min(short scalar) {
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
    public Short4 min(Short4 other) {
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
    public Short4 min(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (Math.min(this.x, otherX)), (short) (Math.min(this.y, otherY)), (short) (Math.min(this.z, otherZ)), (short) (Math.min(this.w, otherW)));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 sign() {
        return new Short4((short) (Math.signum(this.x)), (short) (Math.signum(this.y)), (short) (Math.signum(this.z)), (short) (Math.signum(this.w)));
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
    public Short4 satAdd(Short4 other) {
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
    public Short4 satAdd(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (org.joml2.SaturatingMath.satAddS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ)), (short) (org.joml2.SaturatingMath.satAddS(this.w, otherW)));
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
    public Short4 satMul(Short4 other) {
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
    public Short4 satMul(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (org.joml2.SaturatingMath.satMulS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ)), (short) (org.joml2.SaturatingMath.satMulS(this.w, otherW)));
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Short4 satNegate() {
        return new Short4((short) (org.joml2.SaturatingMath.satNegS(this.x)), (short) (org.joml2.SaturatingMath.satNegS(this.y)), (short) (org.joml2.SaturatingMath.satNegS(this.z)), (short) (org.joml2.SaturatingMath.satNegS(this.w)));
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
    public Short4 satSub(Short4 other) {
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
    public Short4 satSub(short otherX, short otherY, short otherZ, short otherW) {
        return new Short4((short) (org.joml2.SaturatingMath.satSubS(this.x, otherX)), (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY)), (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ)), (short) (org.joml2.SaturatingMath.satSubS(this.w, otherW)));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Short4 withX(short x) {
        return new Short4(x, this.y(), this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Short4 withY(short y) {
        return new Short4(this.x(), y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Short4 withXY(short x, short y) {
        return new Short4(x, y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     */
    public Short4 withZ(short z) {
        return new Short4(this.x(), this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Short4 withXZ(short x, short z) {
        return new Short4(x, this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Short4 withYZ(short y, short z) {
        return new Short4(this.x(), y, z, this.w());
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
    public Short4 withXYZ(short x, short y, short z) {
        return new Short4(x, y, z, this.w());
    }

    /**
     * {@return a copy of this vector with the W component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param w the new value of the {@code w} component
     */
    public Short4 withW(short w) {
        return new Short4(this.x(), this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param w the new value of the {@code w} component
     */
    public Short4 withXW(short x, short w) {
        return new Short4(x, this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the YW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Short4 withYW(short y, short w) {
        return new Short4(this.x(), y, this.z(), w);
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
    public Short4 withXYW(short x, short y, short w) {
        return new Short4(x, y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the ZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Short4 withZW(short z, short w) {
        return new Short4(this.x(), this.y(), z, w);
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
    public Short4 withXZW(short x, short z, short w) {
        return new Short4(x, this.y(), z, w);
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
    public Short4 withYZW(short y, short z, short w) {
        return new Short4(this.x(), y, z, w);
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
    public Short4 withXYZW(short x, short y, short z, short w) {
        return new Short4(x, y, z, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 xw() {
        return new Short2(x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 yw() {
        return new Short2(y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 zw() {
        return new Short2(z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 wx() {
        return new Short2(w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 wy() {
        return new Short2(w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 wz() {
        return new Short2(w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short2 ww() {
        return new Short2(w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xxw() {
        return new Short3(x, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xyw() {
        return new Short3(x, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xzw() {
        return new Short3(x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xwx() {
        return new Short3(x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xwy() {
        return new Short3(x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xwz() {
        return new Short3(x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 xww() {
        return new Short3(x, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yxw() {
        return new Short3(y, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yyw() {
        return new Short3(y, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yzw() {
        return new Short3(y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 ywx() {
        return new Short3(y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 ywy() {
        return new Short3(y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 ywz() {
        return new Short3(y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 yww() {
        return new Short3(y, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zxw() {
        return new Short3(z, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zyw() {
        return new Short3(z, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zzw() {
        return new Short3(z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zwx() {
        return new Short3(z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zwy() {
        return new Short3(z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zwz() {
        return new Short3(z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 zww() {
        return new Short3(z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wxx() {
        return new Short3(w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wxy() {
        return new Short3(w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wxz() {
        return new Short3(w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wxw() {
        return new Short3(w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wyx() {
        return new Short3(w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wyy() {
        return new Short3(w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wyz() {
        return new Short3(w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wyw() {
        return new Short3(w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wzx() {
        return new Short3(w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wzy() {
        return new Short3(w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wzz() {
        return new Short3(w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wzw() {
        return new Short3(w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wwx() {
        return new Short3(w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wwy() {
        return new Short3(w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 wwz() {
        return new Short3(w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short3 www() {
        return new Short3(w, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxxw() {
        return new Short4(x, x, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxyw() {
        return new Short4(x, x, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxzw() {
        return new Short4(x, x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxwx() {
        return new Short4(x, x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxwy() {
        return new Short4(x, x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxwz() {
        return new Short4(x, x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xxww() {
        return new Short4(x, x, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyxw() {
        return new Short4(x, y, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyyw() {
        return new Short4(x, y, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyzw() {
        return new Short4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xywx() {
        return new Short4(x, y, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xywy() {
        return new Short4(x, y, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xywz() {
        return new Short4(x, y, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xyww() {
        return new Short4(x, y, w, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzxw() {
        return new Short4(x, z, x, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzyw() {
        return new Short4(x, z, y, w);
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

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzzw() {
        return new Short4(x, z, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzwx() {
        return new Short4(x, z, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzwy() {
        return new Short4(x, z, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzwz() {
        return new Short4(x, z, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xzww() {
        return new Short4(x, z, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwxx() {
        return new Short4(x, w, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwxy() {
        return new Short4(x, w, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwxz() {
        return new Short4(x, w, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwxw() {
        return new Short4(x, w, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwyx() {
        return new Short4(x, w, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwyy() {
        return new Short4(x, w, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwyz() {
        return new Short4(x, w, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwyw() {
        return new Short4(x, w, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwzx() {
        return new Short4(x, w, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwzy() {
        return new Short4(x, w, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwzz() {
        return new Short4(x, w, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwzw() {
        return new Short4(x, w, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwwx() {
        return new Short4(x, w, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwwy() {
        return new Short4(x, w, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwwz() {
        return new Short4(x, w, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 xwww() {
        return new Short4(x, w, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxxw() {
        return new Short4(y, x, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxyw() {
        return new Short4(y, x, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxzw() {
        return new Short4(y, x, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxwx() {
        return new Short4(y, x, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxwy() {
        return new Short4(y, x, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxwz() {
        return new Short4(y, x, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yxww() {
        return new Short4(y, x, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyxw() {
        return new Short4(y, y, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyyw() {
        return new Short4(y, y, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyzw() {
        return new Short4(y, y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yywx() {
        return new Short4(y, y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yywy() {
        return new Short4(y, y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yywz() {
        return new Short4(y, y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yyww() {
        return new Short4(y, y, w, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzxw() {
        return new Short4(y, z, x, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzyw() {
        return new Short4(y, z, y, w);
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

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzzw() {
        return new Short4(y, z, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzwx() {
        return new Short4(y, z, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzwy() {
        return new Short4(y, z, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzwz() {
        return new Short4(y, z, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 yzww() {
        return new Short4(y, z, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywxx() {
        return new Short4(y, w, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywxy() {
        return new Short4(y, w, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywxz() {
        return new Short4(y, w, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywxw() {
        return new Short4(y, w, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywyx() {
        return new Short4(y, w, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywyy() {
        return new Short4(y, w, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywyz() {
        return new Short4(y, w, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywyw() {
        return new Short4(y, w, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywzx() {
        return new Short4(y, w, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywzy() {
        return new Short4(y, w, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywzz() {
        return new Short4(y, w, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywzw() {
        return new Short4(y, w, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywwx() {
        return new Short4(y, w, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywwy() {
        return new Short4(y, w, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywwz() {
        return new Short4(y, w, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 ywww() {
        return new Short4(y, w, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxxw() {
        return new Short4(z, x, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxyw() {
        return new Short4(z, x, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxzw() {
        return new Short4(z, x, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxwx() {
        return new Short4(z, x, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxwy() {
        return new Short4(z, x, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxwz() {
        return new Short4(z, x, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zxww() {
        return new Short4(z, x, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyxw() {
        return new Short4(z, y, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyyw() {
        return new Short4(z, y, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyzw() {
        return new Short4(z, y, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zywx() {
        return new Short4(z, y, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zywy() {
        return new Short4(z, y, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zywz() {
        return new Short4(z, y, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zyww() {
        return new Short4(z, y, w, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzxw() {
        return new Short4(z, z, x, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzyw() {
        return new Short4(z, z, y, w);
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

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzzw() {
        return new Short4(z, z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzwx() {
        return new Short4(z, z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzwy() {
        return new Short4(z, z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzwz() {
        return new Short4(z, z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zzww() {
        return new Short4(z, z, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwxx() {
        return new Short4(z, w, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwxy() {
        return new Short4(z, w, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwxz() {
        return new Short4(z, w, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwxw() {
        return new Short4(z, w, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwyx() {
        return new Short4(z, w, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwyy() {
        return new Short4(z, w, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwyz() {
        return new Short4(z, w, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwyw() {
        return new Short4(z, w, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwzx() {
        return new Short4(z, w, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwzy() {
        return new Short4(z, w, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwzz() {
        return new Short4(z, w, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwzw() {
        return new Short4(z, w, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwwx() {
        return new Short4(z, w, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwwy() {
        return new Short4(z, w, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwwz() {
        return new Short4(z, w, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 zwww() {
        return new Short4(z, w, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxxx() {
        return new Short4(w, x, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxxy() {
        return new Short4(w, x, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxxz() {
        return new Short4(w, x, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxxw() {
        return new Short4(w, x, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxyx() {
        return new Short4(w, x, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxyy() {
        return new Short4(w, x, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxyz() {
        return new Short4(w, x, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxyw() {
        return new Short4(w, x, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxzx() {
        return new Short4(w, x, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxzy() {
        return new Short4(w, x, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxzz() {
        return new Short4(w, x, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxzw() {
        return new Short4(w, x, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxwx() {
        return new Short4(w, x, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxwy() {
        return new Short4(w, x, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxwz() {
        return new Short4(w, x, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wxww() {
        return new Short4(w, x, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyxx() {
        return new Short4(w, y, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyxy() {
        return new Short4(w, y, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyxz() {
        return new Short4(w, y, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyxw() {
        return new Short4(w, y, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyyx() {
        return new Short4(w, y, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyyy() {
        return new Short4(w, y, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyyz() {
        return new Short4(w, y, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyyw() {
        return new Short4(w, y, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyzx() {
        return new Short4(w, y, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyzy() {
        return new Short4(w, y, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyzz() {
        return new Short4(w, y, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyzw() {
        return new Short4(w, y, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wywx() {
        return new Short4(w, y, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wywy() {
        return new Short4(w, y, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wywz() {
        return new Short4(w, y, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wyww() {
        return new Short4(w, y, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzxx() {
        return new Short4(w, z, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzxy() {
        return new Short4(w, z, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzxz() {
        return new Short4(w, z, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzxw() {
        return new Short4(w, z, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzyx() {
        return new Short4(w, z, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzyy() {
        return new Short4(w, z, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzyz() {
        return new Short4(w, z, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzyw() {
        return new Short4(w, z, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzzx() {
        return new Short4(w, z, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzzy() {
        return new Short4(w, z, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzzz() {
        return new Short4(w, z, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzzw() {
        return new Short4(w, z, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzwx() {
        return new Short4(w, z, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzwy() {
        return new Short4(w, z, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzwz() {
        return new Short4(w, z, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wzww() {
        return new Short4(w, z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwxx() {
        return new Short4(w, w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwxy() {
        return new Short4(w, w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwxz() {
        return new Short4(w, w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwxw() {
        return new Short4(w, w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwyx() {
        return new Short4(w, w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwyy() {
        return new Short4(w, w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwyz() {
        return new Short4(w, w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwyw() {
        return new Short4(w, w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwzx() {
        return new Short4(w, w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwzy() {
        return new Short4(w, w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwzz() {
        return new Short4(w, w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwzw() {
        return new Short4(w, w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwwx() {
        return new Short4(w, w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwwy() {
        return new Short4(w, w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwwz() {
        return new Short4(w, w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Short4 wwww() {
        return new Short4(w, w, w, w);
    }

    @Override public String toString() {
        return "Short4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short4)) return false;
        Short4 o = (Short4) obj;
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
     * the difference is widened to {@code int} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Short4 other, short epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(w - other.w()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4SegOpsUnsafe()
                        : new Short4SegOpsMS();
        static final Short4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4BbOpsUnsafe()
                        : new Short4BbOpsApi();
        static final Short4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4RawOpsUnsafe()
                        : new Short4RawOpsApi();
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
    public short[] store(short[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(short[] src, int offset) {
        short _c0 = src[offset + 0];
        short _c1 = src[offset + 1];
        short _c2 = src[offset + 2];
        short _c3 = src[offset + 3];
        return new Short4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(short[] src) { return load(src, 0); }

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
        return storeAbsolute(buf.position(), buf);
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param buf the source buffer
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(ShortBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadAbsolute(int index, ShortBuffer buf) {
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
     * @return a new {@code Short4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Short4 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short4 r = loadAbsolute(pos, buf);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(ByteBuffer buf) {
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Short4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Short4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short4 r = loadAbsolute(pos, buf);
        buf.position(pos + 8);
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
    public Short4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadUnsafe(long address) {
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(MemorySegment src) { return load(0L, src); }

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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(long offset, MemorySegment src) {
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
        dest[offset + 0] = (byte) this.x;
        dest[offset + 1] = (byte) this.y;
        dest[offset + 2] = (byte) this.z;
        dest[offset + 3] = (byte) this.w;
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(byte[] src, int offset) {
        short _c0 = src[offset + 0];
        short _c1 = src[offset + 1];
        short _c2 = src[offset + 2];
        short _c3 = src[offset + 3];
        return new Short4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code byte}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 load(byte[] src) { return load(src, 0); }

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
        return storeByteAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeByteAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadByte(ByteBuffer buf) {
        return loadByteAbsolute(buf.position(), buf);
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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadByteAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Short4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Short4 loadByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short4 r = loadByteAbsolute(pos, buf);
        buf.position(pos + 4);
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
    public Short4 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code byte}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadByteUnsafe(long address) {
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
    public MemorySegment storeByte(MemorySegment dest) { return storeByte(0L, dest); }

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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadByte(MemorySegment src) { return loadByte(0L, src); }

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
     * @return a new {@code Short4} holding the loaded elements
     */
    public static Short4 loadByte(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadByte(offset, src);
    }

}
