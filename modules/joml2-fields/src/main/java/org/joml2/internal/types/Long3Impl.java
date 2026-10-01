// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.IntBuffer;

/**
 * Generated implementation of {@link Long3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long3Impl implements Long3 {

    public long x;
    public long y;
    public long z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Long3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long3SegOpsUnsafe()
                        : new Long3SegOpsMS();
        static final Long3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long3BbOpsUnsafe()
                        : new Long3BbOpsApi();
        static final Long3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long3RawOpsUnsafe()
                        : new Long3RawOpsApi();
    }

    public Long3Impl() {
    }

    public Long3Impl(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Long3Impl(Long3R src) {
        this.x = src.x();
        this.y = src.y();
        this.z = src.z();
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long3 add(Long3R other, @Mutated Long3 dest) {
        return add(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 add(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(long scalar, @Mutated Long3 dest) {
        return ceilDiv(scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(Long3R other, @Mutated Long3 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        long _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = java.lang.Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(long scalar, @Mutated Long3 dest) {
        return ceilMod(scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(Long3R other, @Mutated Long3 dest) {
        return ceilMod(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        long _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = java.lang.Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(long scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = this.x / scalar;
        long _buf1 = this.y / scalar;
        d.z = this.z / scalar;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(Long3R other, @Mutated Long3 dest) {
        return div(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = this.x / otherX;
        long _buf1 = this.y / otherY;
        d.z = this.z / otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(long scalar, @Mutated Long3 dest) {
        return floorDiv(scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(Long3R other, @Mutated Long3 dest) {
        return floorDiv(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = Math.floorDiv(this.x, otherX);
        long _buf1 = Math.floorDiv(this.y, otherY);
        d.z = Math.floorDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(long scalar, @Mutated Long3 dest) {
        return floorMod(scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(Long3R other, @Mutated Long3 dest) {
        return floorMod(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = Math.floorMod(this.x, otherX);
        long _buf1 = Math.floorMod(this.y, otherY);
        d.z = Math.floorMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(long scalar, @Mutated Long3 dest) {
        return mul(scalar, scalar, scalar, dest);
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(Long3R other, @Mutated Long3 dest) {
        return mul(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
    }


    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 negate(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(long scalar, @Mutated Long3 dest) {
        return rem(scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(Long3R other, @Mutated Long3 dest) {
        return rem(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = this.x % otherX;
        long _buf1 = this.y % otherY;
        d.z = this.z % otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sub(Long3R other, @Mutated Long3 dest) {
        return sub(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sub(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(Long3R other, @Mutated Long3 dest) {
        return and(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
    }


    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 bitCount(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
        d.z = Math.bitCount(this.z);
        return d;
    }


    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 not(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
        d.z = ~this.z;
        return d;
    }


    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 numberOfLeadingZeros(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
        d.z = Math.numberOfLeadingZeros(this.z);
        return d;
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 numberOfTrailingZeros(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
        d.z = Math.numberOfTrailingZeros(this.z);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(Long3R other, @Mutated Long3 dest) {
        return or(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
    }


    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 reverseBits(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
        d.z = Math.reverseBits(this.z);
        return d;
    }


    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 reverseBytes(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
        d.z = Math.reverseBytes(this.z);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateLeft(long distance, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateRight(long distance, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 shl(long shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x << (int)(shift);
        d.y = this.y << (int)(shift);
        d.z = this.z << (int)(shift);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 shr(long shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >> (int)(shift);
        d.y = this.y >> (int)(shift);
        d.z = this.z >> (int)(shift);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ushr(long shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >>> (int)(shift);
        d.y = this.y >>> (int)(shift);
        d.z = this.z >>> (int)(shift);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(Long3R other, @Mutated Long3 dest) {
        return xor(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    public @Mutated Long3 set(Long3R v) {
        return set(v.x(), v.y(), v.z());
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return this
     */
    @Mutated public Long3 set(long vX, long vY, long vZ) {
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        return this;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Long3 set(long s, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        return d;
    }


    /**
     * Convert this vector to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 toFloat(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }


    /**
     * Convert this vector to {@code double} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 toDouble(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }


    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Byte3 toByte(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
        return d;
    }


    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Short3 toShort(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
        return d;
    }


    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 toInt(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
        d.z = (int) (this.z);
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long3 makeZero() {
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        return this;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 absolute(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
        d.z = Math.abs(this.z);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Long3 clamp(long min, long max, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.min(Math.max(this.x, min), max);
        d.y = Math.min(Math.max(this.y, min), max);
        d.z = Math.min(Math.max(this.z, min), max);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Long3 clamp(Long3R min, Long3R max, @Mutated Long3 dest) {
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Long3 clamp(long minX, long minY, long minZ, long maxX, long maxY, long maxZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
        d.z = Math.min(Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public long compAdd() {
        return this.z + (this.x + this.y);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public long compMax() {
        return Math.max(Math.max(this.x, this.y), this.z);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public long compMin() {
        return Math.min(Math.min(this.x, this.y), this.z);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public long compMul() {
        return this.z * this.x * this.y;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(Long3R other, @Mutated Long3 dest) {
        return cross(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        long _buf0 = otherZ * this.y - otherY * this.z;
        long _buf1 = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public long distanceSquared(Long3R other) {
        return distanceSquared(other.x(), other.y(), other.z());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long distanceSquared(long otherX, long otherY, long otherZ) {
        long _t0 = this.x - otherX;
        long _t1 = this.y - otherY;
        long _t2 = this.z - otherZ;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public long dot(Long3R other) {
        return dot(other.x(), other.y(), other.z());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public long dot(long otherX, long otherY, long otherZ) {
        return otherX * this.x + otherY * this.y + otherZ * this.z;
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public long lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public long manhattanDistance(Long3R other) {
        return manhattanDistance(other.x(), other.y(), other.z());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long manhattanDistance(long otherX, long otherY, long otherZ) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY) + Math.abs(this.z - otherZ);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public long manhattanLength() {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z);
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(long scalar, @Mutated Long3 dest) {
        return max(scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(Long3R other, @Mutated Long3 dest) {
        return max(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(long scalar, @Mutated Long3 dest) {
        return min(scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(Long3R other, @Mutated Long3 dest) {
        return min(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        return d;
    }


    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sign(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satAdd(Long3R other, @Mutated Long3 dest) {
        return satAdd(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satAdd(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddL(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satMul(Long3R other, @Mutated Long3 dest) {
        return satMul(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satMul(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulL(this.z, otherZ);
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satNegate(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegL(this.x);
        d.y = org.joml2.SaturatingMath.satNegL(this.y);
        d.z = org.joml2.SaturatingMath.satNegL(this.z);
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satSub(Long3R other, @Mutated Long3 dest) {
        return satSub(other.x(), other.y(), other.z(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satSub(long otherX, long otherY, long otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubL(this.z, otherZ);
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz0(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 0L;
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz1(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 1L;
        return d;
    }

    public long x() { return this.x; }
    public long y() { return this.y; }
    public long z() { return this.z; }

    public Long2 xx(@Mutated Long2 dest) {
        long _v0 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Long2 xy(@Mutated Long2 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 xz(@Mutated Long2 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 yx(@Mutated Long2 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 yy(@Mutated Long2 dest) {
        long _v0 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Long2 yz(@Mutated Long2 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 zx(@Mutated Long2 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 zy(@Mutated Long2 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Long2 zz(@Mutated Long2 dest) {
        long _v0 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Long3 xxx(@Mutated Long3 dest) {
        long _v0 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Long3 xxy(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 xxz(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 xyx(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 xyy(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 xyz(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xzx(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 xzy(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xzz(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 yxx(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 yxy(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 yxz(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 yyx(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 yyy(@Mutated Long3 dest) {
        long _v0 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Long3 yyz(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 yzx(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 yzy(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 yzz(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 zxx(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 zxy(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zxz(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 zyx(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zyy(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 zyz(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 zzx(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 zzy(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 zzz(@Mutated Long3 dest) {
        long _v0 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Long4 xxxx(@Mutated Long4 dest) {
        long _v0 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 xxxy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 xxxz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 xxyx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xxyy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 xxyz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xxzx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xxzy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xxzz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 xyxx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 xyxy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 xyxz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 xyyx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xyyy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 xyyz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xyzx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xyzy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xyzz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xzxx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 xzxy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 xzxz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 xzyx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xzyy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xzyz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xzzx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xzzy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xzzz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yxxx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yxxy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 yxxz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yxyx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 yxyy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 yxyz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 yxzx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 yxzy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 yxzz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 yyxx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yyxy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 yyxz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yyyx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 yyyy(@Mutated Long4 dest) {
        long _v0 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 yyyz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 yyzx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yyzy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 yyzz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yzxx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 yzxy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 yzxz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 yzyx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 yzyy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 yzyz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 yzzx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yzzy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 yzzz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zxxx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zxxy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zxxz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zxyx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zxyy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zxyz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zxzx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zxzy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zxzz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 zyxx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zyxy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zyxz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zyyx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zyyy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zyyz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zyzx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zyzy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zyzz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 zzxx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zzxy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zzxz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zzyx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zzyy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zzyz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zzzx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zzzy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zzzz(@Mutated Long4 dest) {
        long _v0 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Long3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long3Impl)) return false;
        Long3Impl o = (Long3Impl) obj;
        return x == o.x
            && y == o.y
            && z == o.z;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(x ^ (x >>> 32));
        h = 31 * h + (int)(y ^ (y >>> 32));
        h = 31 * h + (int)(z ^ (z >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Long3R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(x >= other.x() ? x - other.x() : other.x() - x, epsilon) <= 0
            && Long.compareUnsigned(y >= other.y() ? y - other.y() : other.y() - y, epsilon) <= 0
            && Long.compareUnsigned(z >= other.z() ? z - other.z() : other.z() - z, epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Long3 load(long[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long3 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Long3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Long3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset + 0] = (int) this.x;
        dest[offset + 1] = (int) this.y;
        dest[offset + 2] = (int) this.z;
        return dest;
    }
    public @Mutated Long3 load(int[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long3 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public Long3 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long3 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long3 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }
    public MemorySegment storeInt(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeInt(this, offset, dest);
    }
    public Long3 loadInt(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadInt(this, offset, src);
    }

}
