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
 * Immutable 4D vector of {@code byte} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
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
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record Byte4(byte x, byte y, byte z, byte w) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 4;

    /** The zero vector (all components 0). */
    public static final Byte4 ZERO = new Byte4((byte) 0, (byte) 0, (byte) 0, (byte) 0);

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
    public Byte4(byte x, byte y, byte z, byte w) {
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
    public Byte4() {
        this((byte) 0, (byte) 0, (byte) 0, (byte) 1);
    }

    /**
     * Create a vector with all components set to {@code s}.
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public Byte4(byte s) {
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
    public Byte4(byte v0, byte v1, Byte2 v2) {
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
    public Byte4(byte v0, Byte2 v1, byte v2) {
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
    public Byte4(byte v0, Byte3 v1) {
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
    public Byte4(Byte2 v0, byte v1, byte v2) {
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
    public Byte4(Byte2 v0, Byte2 v1) {
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
    public Byte4(Byte3 v0, byte v1) {
        this(v0.x(), v0.y(), v0.z(), v1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value. */
    public byte x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value. */
    public byte y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value. */
    public byte z() { return z; }
    /** {@return the {@code w} component} <p>Valid input: any value. */
    public byte w() { return w; }


    /**
     * Add {@code other} to this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return the resulting vector
     */
    public Byte4 add(Byte4 other) {
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
    public Byte4 add(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (otherX + this.x), (byte) (otherY + this.y), (byte) (otherZ + this.z), (byte) (otherW + this.w));
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
    public Byte4 ceilDiv(byte scalar) {
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
    public Byte4 ceilDiv(Byte4 other) {
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
    public Byte4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (java.lang.Math.ceilDiv(this.x, otherX)), (byte) (java.lang.Math.ceilDiv(this.y, otherY)), (byte) (java.lang.Math.ceilDiv(this.z, otherZ)), (byte) (java.lang.Math.ceilDiv(this.w, otherW)));
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
    public Byte4 ceilMod(byte scalar) {
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
    public Byte4 ceilMod(Byte4 other) {
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
    public Byte4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (java.lang.Math.ceilMod(this.x, otherX)), (byte) (java.lang.Math.ceilMod(this.y, otherY)), (byte) (java.lang.Math.ceilMod(this.z, otherZ)), (byte) (java.lang.Math.ceilMod(this.w, otherW)));
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
    public Byte4 div(byte scalar) {
        return new Byte4((byte) (this.x / scalar), (byte) (this.y / scalar), (byte) (this.z / scalar), (byte) (this.w / scalar));
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
    public Byte4 div(Byte4 other) {
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
    public Byte4 div(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x / otherX), (byte) (this.y / otherY), (byte) (this.z / otherZ), (byte) (this.w / otherW));
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
    public Byte4 floorDiv(byte scalar) {
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
    public Byte4 floorDiv(Byte4 other) {
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
    public Byte4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (Math.floorDiv(this.x, otherX)), (byte) (Math.floorDiv(this.y, otherY)), (byte) (Math.floorDiv(this.z, otherZ)), (byte) (Math.floorDiv(this.w, otherW)));
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
    public Byte4 floorMod(byte scalar) {
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
    public Byte4 floorMod(Byte4 other) {
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
    public Byte4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (Math.floorMod(this.x, otherX)), (byte) (Math.floorMod(this.y, otherY)), (byte) (Math.floorMod(this.z, otherZ)), (byte) (Math.floorMod(this.w, otherW)));
    }


    /**
     * Multiply each component of this vector by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting vector
     */
    public Byte4 mul(byte scalar) {
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
    public Byte4 mul(Byte4 other) {
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
    public Byte4 mul(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (otherX * this.x), (byte) (otherY * this.y), (byte) (otherZ * this.z), (byte) (otherW * this.w));
    }


    /**
     * Negate this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 negate() {
        return new Byte4((byte) (-this.x), (byte) (-this.y), (byte) (-this.z), (byte) (-this.w));
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
    public Byte4 rem(byte scalar) {
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
    public Byte4 rem(Byte4 other) {
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
    public Byte4 rem(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x % otherX), (byte) (this.y % otherY), (byte) (this.z % otherZ), (byte) (this.w % otherW));
    }


    /**
     * Subtract {@code other} from this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return the resulting vector
     */
    public Byte4 sub(Byte4 other) {
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
    public Byte4 sub(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x - otherX), (byte) (this.y - otherY), (byte) (this.z - otherZ), (byte) (this.w - otherW));
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
    public Byte4 and(Byte4 other) {
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
    public Byte4 and(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x & otherX), (byte) (this.y & otherY), (byte) (this.z & otherZ), (byte) (this.w & otherW));
    }


    /**
     * Compute the number of one-bits of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 bitCount() {
        return new Byte4((byte) (Math.bitCount(this.x)), (byte) (Math.bitCount(this.y)), (byte) (Math.bitCount(this.z)), (byte) (Math.bitCount(this.w)));
    }


    /**
     * Compute the bitwise NOT of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 not() {
        return new Byte4((byte) (~this.x), (byte) (~this.y), (byte) (~this.z), (byte) (~this.w));
    }


    /**
     * Compute the number of leading zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 numberOfLeadingZeros() {
        return new Byte4((byte) (Math.numberOfLeadingZeros(this.x)), (byte) (Math.numberOfLeadingZeros(this.y)), (byte) (Math.numberOfLeadingZeros(this.z)), (byte) (Math.numberOfLeadingZeros(this.w)));
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 numberOfTrailingZeros() {
        return new Byte4((byte) (Math.numberOfTrailingZeros(this.x)), (byte) (Math.numberOfTrailingZeros(this.y)), (byte) (Math.numberOfTrailingZeros(this.z)), (byte) (Math.numberOfTrailingZeros(this.w)));
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
    public Byte4 or(Byte4 other) {
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
    public Byte4 or(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x | otherX), (byte) (this.y | otherY), (byte) (this.z | otherZ), (byte) (this.w | otherW));
    }


    /**
     * Compute the bit-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 reverseBits() {
        return new Byte4((byte) (Math.reverseBits(this.x)), (byte) (Math.reverseBits(this.y)), (byte) (Math.reverseBits(this.z)), (byte) (Math.reverseBits(this.w)));
    }


    /**
     * Compute the byte-reversed value of each component of this vector, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 reverseBytes() {
        return new Byte4((byte) (Math.reverseBytes(this.x)), (byte) (Math.reverseBytes(this.y)), (byte) (Math.reverseBytes(this.z)), (byte) (Math.reverseBytes(this.w)));
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
    public Byte4 rotateLeft(byte distance) {
        return new Byte4((byte) (Math.rotateLeft(this.x, distance)), (byte) (Math.rotateLeft(this.y, distance)), (byte) (Math.rotateLeft(this.z, distance)), (byte) (Math.rotateLeft(this.w, distance)));
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
    public Byte4 rotateRight(byte distance) {
        return new Byte4((byte) (Math.rotateRight(this.x, distance)), (byte) (Math.rotateRight(this.y, distance)), (byte) (Math.rotateRight(this.z, distance)), (byte) (Math.rotateRight(this.w, distance)));
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
    public Byte4 shl(byte shift) {
        return new Byte4((byte) (this.x << (shift & 7)), (byte) (this.y << (shift & 7)), (byte) (this.z << (shift & 7)), (byte) (this.w << (shift & 7)));
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
    public Byte4 shr(byte shift) {
        return new Byte4((byte) (this.x >> (shift & 7)), (byte) (this.y >> (shift & 7)), (byte) (this.z >> (shift & 7)), (byte) (this.w >> (shift & 7)));
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
    public Byte4 ushr(byte shift) {
        return new Byte4((byte) ((this.x & 0xFF) >>> (shift & 7)), (byte) ((this.y & 0xFF) >>> (shift & 7)), (byte) ((this.z & 0xFF) >>> (shift & 7)), (byte) ((this.w & 0xFF) >>> (shift & 7)));
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
    public Byte4 xor(Byte4 other) {
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
    public Byte4 xor(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (this.x ^ otherX), (byte) (this.y ^ otherY), (byte) (this.z ^ otherZ), (byte) (this.w ^ otherW));
    }


    /**
     * Create a new vector from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return the resulting vector
     */
    public Byte4 set(Byte4 v) {
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
    public Byte4 set(byte vX, byte vY, byte vZ, byte vW) {
        return new Byte4((byte) (vX), (byte) (vY), (byte) (vZ), (byte) (vW));
    }


    /**
     * Set this vector to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return the resulting vector
     */
    public Byte4 set(byte s) {
        return new Byte4((byte) (s), (byte) (s), (byte) (s), (byte) (s));
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
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short4} holding the result
     */
    public Short4 toShort() {
        return new Short4((short) (this.x), (short) (this.y), (short) (this.z), (short) (this.w));
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
    public static Byte4 makeZero() {
        return Byte4.ZERO;
    }


    /**
     * Compute the absolute value of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 absolute() {
        return new Byte4((byte) (Math.abs(this.x)), (byte) (Math.abs(this.y)), (byte) (Math.abs(this.z)), (byte) (Math.abs(this.w)));
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
    public Byte4 clamp(byte min, byte max) {
        return new Byte4((byte) (Math.min(Math.max(this.x, min), max)), (byte) (Math.min(Math.max(this.y, min), max)), (byte) (Math.min(Math.max(this.z, min), max)), (byte) (Math.min(Math.max(this.w, min), max)));
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
    public Byte4 clamp(Byte4 min, Byte4 max) {
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
    public Byte4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW) {
        return new Byte4((byte) (Math.min(Math.max(this.x, minX), maxX)), (byte) (Math.min(Math.max(this.y, minY), maxY)), (byte) (Math.min(Math.max(this.z, minZ), maxZ)), (byte) (Math.min(Math.max(this.w, minW), maxW)));
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
        return this.w + (this.z + (this.x + this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public byte compMax() {
        return (byte) (Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        return (byte) (Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w));
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
        return this.w * this.z * this.x * this.y;
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
    public Byte4 cross(Byte4 v, Byte4 w) {
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
    public Byte4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW) {
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        return new Byte4((byte) (this.y * _t12 - this.z * _t13 + this.w * _t14), (byte) (this.z * _t15 - this.x * _t12 - this.w * _t16), (byte) (this.x * _t13 - this.y * _t15 + this.w * _t17), (byte) (this.y * _t16 - this.x * _t14 - this.z * _t17));
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
    public int distanceSquared(Byte4 other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int distanceSquared(byte otherX, byte otherY, byte otherZ, byte otherW) {
        int _t0 = this.x - otherX;
        int _t1 = this.y - otherY;
        int _t2 = this.z - otherZ;
        int _t3 = this.w - otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
    public int dot(Byte4 other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int dot(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return otherX * this.x + otherY * this.y + otherZ * this.z + otherW * this.w;
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
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
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
    public int manhattanDistance(Byte4 other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int manhattanDistance(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY) + Math.abs(this.z - otherZ) + Math.abs(this.w - otherW);
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
    public Byte4 max(byte scalar) {
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
    public Byte4 max(Byte4 other) {
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
    public Byte4 max(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (Math.max(this.x, otherX)), (byte) (Math.max(this.y, otherY)), (byte) (Math.max(this.z, otherZ)), (byte) (Math.max(this.w, otherW)));
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
    public Byte4 min(byte scalar) {
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
    public Byte4 min(Byte4 other) {
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
    public Byte4 min(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (Math.min(this.x, otherX)), (byte) (Math.min(this.y, otherY)), (byte) (Math.min(this.z, otherZ)), (byte) (Math.min(this.w, otherW)));
    }


    /**
     * Compute the sign of each component of this vector, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 sign() {
        return new Byte4((byte) (Math.signum(this.x)), (byte) (Math.signum(this.y)), (byte) (Math.signum(this.z)), (byte) (Math.signum(this.w)));
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
    public Byte4 satAdd(Byte4 other) {
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
    public Byte4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY)), (byte) (org.joml2.SaturatingMath.satAddB(this.z, otherZ)), (byte) (org.joml2.SaturatingMath.satAddB(this.w, otherW)));
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
    public Byte4 satMul(Byte4 other) {
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
    public Byte4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY)), (byte) (org.joml2.SaturatingMath.satMulB(this.z, otherZ)), (byte) (org.joml2.SaturatingMath.satMulB(this.w, otherW)));
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Byte4 satNegate() {
        return new Byte4((byte) (org.joml2.SaturatingMath.satNegB(this.x)), (byte) (org.joml2.SaturatingMath.satNegB(this.y)), (byte) (org.joml2.SaturatingMath.satNegB(this.z)), (byte) (org.joml2.SaturatingMath.satNegB(this.w)));
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
    public Byte4 satSub(Byte4 other) {
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
    public Byte4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return new Byte4((byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX)), (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY)), (byte) (org.joml2.SaturatingMath.satSubB(this.z, otherZ)), (byte) (org.joml2.SaturatingMath.satSubB(this.w, otherW)));
    }

    /**
     * {@return a copy of this vector with the X component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     */
    public Byte4 withX(byte x) {
        return new Byte4(x, this.y(), this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Y component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     */
    public Byte4 withY(byte y) {
        return new Byte4(this.x(), y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the XY components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param y the new value of the {@code y} component
     */
    public Byte4 withXY(byte x, byte y) {
        return new Byte4(x, y, this.z(), this.w());
    }

    /**
     * {@return a copy of this vector with the Z component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     */
    public Byte4 withZ(byte z) {
        return new Byte4(this.x(), this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the XZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param z the new value of the {@code z} component
     */
    public Byte4 withXZ(byte x, byte z) {
        return new Byte4(x, this.y(), z, this.w());
    }

    /**
     * {@return a copy of this vector with the YZ components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param z the new value of the {@code z} component
     */
    public Byte4 withYZ(byte y, byte z) {
        return new Byte4(this.x(), y, z, this.w());
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
    public Byte4 withXYZ(byte x, byte y, byte z) {
        return new Byte4(x, y, z, this.w());
    }

    /**
     * {@return a copy of this vector with the W component replaced by the given value}
     * <p>
     * Valid input: any value.
     *
     * @param w the new value of the {@code w} component
     */
    public Byte4 withW(byte w) {
        return new Byte4(this.x(), this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the XW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param x the new value of the {@code x} component
     * @param w the new value of the {@code w} component
     */
    public Byte4 withXW(byte x, byte w) {
        return new Byte4(x, this.y(), this.z(), w);
    }

    /**
     * {@return a copy of this vector with the YW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param y the new value of the {@code y} component
     * @param w the new value of the {@code w} component
     */
    public Byte4 withYW(byte y, byte w) {
        return new Byte4(this.x(), y, this.z(), w);
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
    public Byte4 withXYW(byte x, byte y, byte w) {
        return new Byte4(x, y, this.z(), w);
    }

    /**
     * {@return a copy of this vector with the ZW components replaced by the given values}
     * <p>
     * Valid input: any value.
     *
     * @param z the new value of the {@code z} component
     * @param w the new value of the {@code w} component
     */
    public Byte4 withZW(byte z, byte w) {
        return new Byte4(this.x(), this.y(), z, w);
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
    public Byte4 withXZW(byte x, byte z, byte w) {
        return new Byte4(x, this.y(), z, w);
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
    public Byte4 withYZW(byte y, byte z, byte w) {
        return new Byte4(this.x(), y, z, w);
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
    public Byte4 withXYZW(byte x, byte y, byte z, byte w) {
        return new Byte4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xx() {
        return new Byte2(x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xy() {
        return new Byte2(x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xz() {
        return new Byte2(x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 xw() {
        return new Byte2(x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yx() {
        return new Byte2(y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yy() {
        return new Byte2(y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yz() {
        return new Byte2(y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 yw() {
        return new Byte2(y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 zx() {
        return new Byte2(z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 zy() {
        return new Byte2(z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 zz() {
        return new Byte2(z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 zw() {
        return new Byte2(z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 wx() {
        return new Byte2(w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 wy() {
        return new Byte2(w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 wz() {
        return new Byte2(w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte2 ww() {
        return new Byte2(w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxx() {
        return new Byte3(x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxy() {
        return new Byte3(x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxz() {
        return new Byte3(x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xxw() {
        return new Byte3(x, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyx() {
        return new Byte3(x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyy() {
        return new Byte3(x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyz() {
        return new Byte3(x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xyw() {
        return new Byte3(x, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xzx() {
        return new Byte3(x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xzy() {
        return new Byte3(x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xzz() {
        return new Byte3(x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xzw() {
        return new Byte3(x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xwx() {
        return new Byte3(x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xwy() {
        return new Byte3(x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xwz() {
        return new Byte3(x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 xww() {
        return new Byte3(x, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxx() {
        return new Byte3(y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxy() {
        return new Byte3(y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxz() {
        return new Byte3(y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yxw() {
        return new Byte3(y, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyx() {
        return new Byte3(y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyy() {
        return new Byte3(y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyz() {
        return new Byte3(y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yyw() {
        return new Byte3(y, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yzx() {
        return new Byte3(y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yzy() {
        return new Byte3(y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yzz() {
        return new Byte3(y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yzw() {
        return new Byte3(y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 ywx() {
        return new Byte3(y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 ywy() {
        return new Byte3(y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 ywz() {
        return new Byte3(y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 yww() {
        return new Byte3(y, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zxx() {
        return new Byte3(z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zxy() {
        return new Byte3(z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zxz() {
        return new Byte3(z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zxw() {
        return new Byte3(z, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zyx() {
        return new Byte3(z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zyy() {
        return new Byte3(z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zyz() {
        return new Byte3(z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zyw() {
        return new Byte3(z, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zzx() {
        return new Byte3(z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zzy() {
        return new Byte3(z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zzz() {
        return new Byte3(z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zzw() {
        return new Byte3(z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zwx() {
        return new Byte3(z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zwy() {
        return new Byte3(z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zwz() {
        return new Byte3(z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 zww() {
        return new Byte3(z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wxx() {
        return new Byte3(w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wxy() {
        return new Byte3(w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wxz() {
        return new Byte3(w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wxw() {
        return new Byte3(w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wyx() {
        return new Byte3(w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wyy() {
        return new Byte3(w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wyz() {
        return new Byte3(w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wyw() {
        return new Byte3(w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wzx() {
        return new Byte3(w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wzy() {
        return new Byte3(w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wzz() {
        return new Byte3(w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wzw() {
        return new Byte3(w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wwx() {
        return new Byte3(w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wwy() {
        return new Byte3(w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 wwz() {
        return new Byte3(w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte3 www() {
        return new Byte3(w, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxx() {
        return new Byte4(x, x, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxy() {
        return new Byte4(x, x, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxz() {
        return new Byte4(x, x, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxxw() {
        return new Byte4(x, x, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyx() {
        return new Byte4(x, x, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyy() {
        return new Byte4(x, x, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyz() {
        return new Byte4(x, x, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxyw() {
        return new Byte4(x, x, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxzx() {
        return new Byte4(x, x, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxzy() {
        return new Byte4(x, x, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxzz() {
        return new Byte4(x, x, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxzw() {
        return new Byte4(x, x, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxwx() {
        return new Byte4(x, x, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxwy() {
        return new Byte4(x, x, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxwz() {
        return new Byte4(x, x, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xxww() {
        return new Byte4(x, x, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxx() {
        return new Byte4(x, y, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxy() {
        return new Byte4(x, y, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxz() {
        return new Byte4(x, y, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyxw() {
        return new Byte4(x, y, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyx() {
        return new Byte4(x, y, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyy() {
        return new Byte4(x, y, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyz() {
        return new Byte4(x, y, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyyw() {
        return new Byte4(x, y, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyzx() {
        return new Byte4(x, y, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyzy() {
        return new Byte4(x, y, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyzz() {
        return new Byte4(x, y, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyzw() {
        return new Byte4(x, y, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xywx() {
        return new Byte4(x, y, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xywy() {
        return new Byte4(x, y, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xywz() {
        return new Byte4(x, y, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xyww() {
        return new Byte4(x, y, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzxx() {
        return new Byte4(x, z, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzxy() {
        return new Byte4(x, z, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzxz() {
        return new Byte4(x, z, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzxw() {
        return new Byte4(x, z, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzyx() {
        return new Byte4(x, z, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzyy() {
        return new Byte4(x, z, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzyz() {
        return new Byte4(x, z, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzyw() {
        return new Byte4(x, z, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzzx() {
        return new Byte4(x, z, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzzy() {
        return new Byte4(x, z, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzzz() {
        return new Byte4(x, z, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzzw() {
        return new Byte4(x, z, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzwx() {
        return new Byte4(x, z, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzwy() {
        return new Byte4(x, z, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzwz() {
        return new Byte4(x, z, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xzww() {
        return new Byte4(x, z, w, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwxx() {
        return new Byte4(x, w, x, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwxy() {
        return new Byte4(x, w, x, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwxz() {
        return new Byte4(x, w, x, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwxw() {
        return new Byte4(x, w, x, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwyx() {
        return new Byte4(x, w, y, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwyy() {
        return new Byte4(x, w, y, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwyz() {
        return new Byte4(x, w, y, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwyw() {
        return new Byte4(x, w, y, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwzx() {
        return new Byte4(x, w, z, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwzy() {
        return new Byte4(x, w, z, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwzz() {
        return new Byte4(x, w, z, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwzw() {
        return new Byte4(x, w, z, w);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwwx() {
        return new Byte4(x, w, w, x);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwwy() {
        return new Byte4(x, w, w, y);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwwz() {
        return new Byte4(x, w, w, z);
    }

    /** {@return a new vector holding the components ({@code x}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 xwww() {
        return new Byte4(x, w, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxx() {
        return new Byte4(y, x, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxy() {
        return new Byte4(y, x, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxz() {
        return new Byte4(y, x, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxxw() {
        return new Byte4(y, x, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyx() {
        return new Byte4(y, x, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyy() {
        return new Byte4(y, x, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyz() {
        return new Byte4(y, x, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxyw() {
        return new Byte4(y, x, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxzx() {
        return new Byte4(y, x, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxzy() {
        return new Byte4(y, x, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxzz() {
        return new Byte4(y, x, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxzw() {
        return new Byte4(y, x, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxwx() {
        return new Byte4(y, x, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxwy() {
        return new Byte4(y, x, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxwz() {
        return new Byte4(y, x, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yxww() {
        return new Byte4(y, x, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxx() {
        return new Byte4(y, y, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxy() {
        return new Byte4(y, y, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxz() {
        return new Byte4(y, y, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyxw() {
        return new Byte4(y, y, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyx() {
        return new Byte4(y, y, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyy() {
        return new Byte4(y, y, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyz() {
        return new Byte4(y, y, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyyw() {
        return new Byte4(y, y, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyzx() {
        return new Byte4(y, y, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyzy() {
        return new Byte4(y, y, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyzz() {
        return new Byte4(y, y, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyzw() {
        return new Byte4(y, y, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yywx() {
        return new Byte4(y, y, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yywy() {
        return new Byte4(y, y, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yywz() {
        return new Byte4(y, y, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yyww() {
        return new Byte4(y, y, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzxx() {
        return new Byte4(y, z, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzxy() {
        return new Byte4(y, z, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzxz() {
        return new Byte4(y, z, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzxw() {
        return new Byte4(y, z, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzyx() {
        return new Byte4(y, z, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzyy() {
        return new Byte4(y, z, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzyz() {
        return new Byte4(y, z, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzyw() {
        return new Byte4(y, z, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzzx() {
        return new Byte4(y, z, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzzy() {
        return new Byte4(y, z, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzzz() {
        return new Byte4(y, z, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzzw() {
        return new Byte4(y, z, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzwx() {
        return new Byte4(y, z, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzwy() {
        return new Byte4(y, z, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzwz() {
        return new Byte4(y, z, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 yzww() {
        return new Byte4(y, z, w, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywxx() {
        return new Byte4(y, w, x, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywxy() {
        return new Byte4(y, w, x, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywxz() {
        return new Byte4(y, w, x, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywxw() {
        return new Byte4(y, w, x, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywyx() {
        return new Byte4(y, w, y, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywyy() {
        return new Byte4(y, w, y, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywyz() {
        return new Byte4(y, w, y, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywyw() {
        return new Byte4(y, w, y, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywzx() {
        return new Byte4(y, w, z, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywzy() {
        return new Byte4(y, w, z, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywzz() {
        return new Byte4(y, w, z, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywzw() {
        return new Byte4(y, w, z, w);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywwx() {
        return new Byte4(y, w, w, x);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywwy() {
        return new Byte4(y, w, w, y);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywwz() {
        return new Byte4(y, w, w, z);
    }

    /** {@return a new vector holding the components ({@code y}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 ywww() {
        return new Byte4(y, w, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxxx() {
        return new Byte4(z, x, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxxy() {
        return new Byte4(z, x, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxxz() {
        return new Byte4(z, x, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxxw() {
        return new Byte4(z, x, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxyx() {
        return new Byte4(z, x, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxyy() {
        return new Byte4(z, x, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxyz() {
        return new Byte4(z, x, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxyw() {
        return new Byte4(z, x, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxzx() {
        return new Byte4(z, x, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxzy() {
        return new Byte4(z, x, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxzz() {
        return new Byte4(z, x, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxzw() {
        return new Byte4(z, x, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxwx() {
        return new Byte4(z, x, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxwy() {
        return new Byte4(z, x, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxwz() {
        return new Byte4(z, x, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zxww() {
        return new Byte4(z, x, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyxx() {
        return new Byte4(z, y, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyxy() {
        return new Byte4(z, y, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyxz() {
        return new Byte4(z, y, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyxw() {
        return new Byte4(z, y, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyyx() {
        return new Byte4(z, y, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyyy() {
        return new Byte4(z, y, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyyz() {
        return new Byte4(z, y, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyyw() {
        return new Byte4(z, y, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyzx() {
        return new Byte4(z, y, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyzy() {
        return new Byte4(z, y, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyzz() {
        return new Byte4(z, y, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyzw() {
        return new Byte4(z, y, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zywx() {
        return new Byte4(z, y, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zywy() {
        return new Byte4(z, y, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zywz() {
        return new Byte4(z, y, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zyww() {
        return new Byte4(z, y, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzxx() {
        return new Byte4(z, z, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzxy() {
        return new Byte4(z, z, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzxz() {
        return new Byte4(z, z, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzxw() {
        return new Byte4(z, z, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzyx() {
        return new Byte4(z, z, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzyy() {
        return new Byte4(z, z, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzyz() {
        return new Byte4(z, z, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzyw() {
        return new Byte4(z, z, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzzx() {
        return new Byte4(z, z, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzzy() {
        return new Byte4(z, z, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzzz() {
        return new Byte4(z, z, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzzw() {
        return new Byte4(z, z, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzwx() {
        return new Byte4(z, z, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzwy() {
        return new Byte4(z, z, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzwz() {
        return new Byte4(z, z, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zzww() {
        return new Byte4(z, z, w, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwxx() {
        return new Byte4(z, w, x, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwxy() {
        return new Byte4(z, w, x, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwxz() {
        return new Byte4(z, w, x, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwxw() {
        return new Byte4(z, w, x, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwyx() {
        return new Byte4(z, w, y, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwyy() {
        return new Byte4(z, w, y, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwyz() {
        return new Byte4(z, w, y, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwyw() {
        return new Byte4(z, w, y, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwzx() {
        return new Byte4(z, w, z, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwzy() {
        return new Byte4(z, w, z, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwzz() {
        return new Byte4(z, w, z, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwzw() {
        return new Byte4(z, w, z, w);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwwx() {
        return new Byte4(z, w, w, x);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwwy() {
        return new Byte4(z, w, w, y);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwwz() {
        return new Byte4(z, w, w, z);
    }

    /** {@return a new vector holding the components ({@code z}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 zwww() {
        return new Byte4(z, w, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxxx() {
        return new Byte4(w, x, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxxy() {
        return new Byte4(w, x, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxxz() {
        return new Byte4(w, x, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxxw() {
        return new Byte4(w, x, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxyx() {
        return new Byte4(w, x, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxyy() {
        return new Byte4(w, x, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxyz() {
        return new Byte4(w, x, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxyw() {
        return new Byte4(w, x, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxzx() {
        return new Byte4(w, x, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxzy() {
        return new Byte4(w, x, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxzz() {
        return new Byte4(w, x, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxzw() {
        return new Byte4(w, x, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxwx() {
        return new Byte4(w, x, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxwy() {
        return new Byte4(w, x, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxwz() {
        return new Byte4(w, x, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code x}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wxww() {
        return new Byte4(w, x, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyxx() {
        return new Byte4(w, y, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyxy() {
        return new Byte4(w, y, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyxz() {
        return new Byte4(w, y, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyxw() {
        return new Byte4(w, y, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyyx() {
        return new Byte4(w, y, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyyy() {
        return new Byte4(w, y, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyyz() {
        return new Byte4(w, y, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyyw() {
        return new Byte4(w, y, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyzx() {
        return new Byte4(w, y, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyzy() {
        return new Byte4(w, y, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyzz() {
        return new Byte4(w, y, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyzw() {
        return new Byte4(w, y, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wywx() {
        return new Byte4(w, y, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wywy() {
        return new Byte4(w, y, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wywz() {
        return new Byte4(w, y, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code y}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wyww() {
        return new Byte4(w, y, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzxx() {
        return new Byte4(w, z, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzxy() {
        return new Byte4(w, z, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzxz() {
        return new Byte4(w, z, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzxw() {
        return new Byte4(w, z, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzyx() {
        return new Byte4(w, z, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzyy() {
        return new Byte4(w, z, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzyz() {
        return new Byte4(w, z, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzyw() {
        return new Byte4(w, z, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzzx() {
        return new Byte4(w, z, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzzy() {
        return new Byte4(w, z, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzzz() {
        return new Byte4(w, z, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzzw() {
        return new Byte4(w, z, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzwx() {
        return new Byte4(w, z, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzwy() {
        return new Byte4(w, z, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzwz() {
        return new Byte4(w, z, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code z}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wzww() {
        return new Byte4(w, z, w, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwxx() {
        return new Byte4(w, w, x, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwxy() {
        return new Byte4(w, w, x, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwxz() {
        return new Byte4(w, w, x, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code x}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwxw() {
        return new Byte4(w, w, x, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwyx() {
        return new Byte4(w, w, y, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwyy() {
        return new Byte4(w, w, y, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwyz() {
        return new Byte4(w, w, y, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code y}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwyw() {
        return new Byte4(w, w, y, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwzx() {
        return new Byte4(w, w, z, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwzy() {
        return new Byte4(w, w, z, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwzz() {
        return new Byte4(w, w, z, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code z}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwzw() {
        return new Byte4(w, w, z, w);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code x}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwwx() {
        return new Byte4(w, w, w, x);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code y}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwwy() {
        return new Byte4(w, w, w, y);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code z}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwwz() {
        return new Byte4(w, w, w, z);
    }

    /** {@return a new vector holding the components ({@code w}, {@code w}, {@code w}, {@code w}) of this vector, in that order} <p>Valid input: any value. */
    public Byte4 wwww() {
        return new Byte4(w, w, w, w);
    }

    @Override public String toString() {
        return "Byte4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte4)) return false;
        Byte4 o = (Byte4) obj;
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
    public boolean equalsEpsilon(Byte4 other, byte epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(w - other.w()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Byte4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4SegOpsUnsafe()
                        : new Byte4SegOpsMS();
        static final Byte4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4BbOpsUnsafe()
                        : new Byte4BbOpsApi();
        static final Byte4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4RawOpsUnsafe()
                        : new Byte4RawOpsApi();
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
    public byte[] store(byte[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(byte[] src, int offset) {
        byte _c0 = src[offset + 0];
        byte _c1 = src[offset + 1];
        byte _c2 = src[offset + 2];
        byte _c3 = src[offset + 3];
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(byte[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(ByteBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Byte4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte4 r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
    public Byte4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadUnsafe(long address) {
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(MemorySegment src) { return load(0L, src); }

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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(long offset, MemorySegment src) {
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
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(short[] src, int offset) {
        byte _c0 = (byte) src[offset + 0];
        byte _c1 = (byte) src[offset + 1];
        byte _c2 = (byte) src[offset + 2];
        byte _c3 = (byte) src[offset + 3];
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(short[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 load(ShortBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadAbsolute(int index, ShortBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Byte4 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte4 r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeShortAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadShort(ByteBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadShortAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code Byte4} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Byte4 loadShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte4 r = loadShortAbsolute(pos, buf);
        buf.position(pos + 8);
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
    public Byte4 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code short}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadShortUnsafe(long address) {
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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadShort(MemorySegment src) { return loadShort(0L, src); }

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
     * @return a new {@code Byte4} holding the loaded elements
     */
    public static Byte4 loadShort(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadShort(offset, src);
    }

}
