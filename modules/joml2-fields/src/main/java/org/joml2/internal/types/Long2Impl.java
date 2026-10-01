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
 * Generated implementation of {@link Long2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long2Impl implements Long2 {

    public long x;
    public long y;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Long2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long2SegOpsUnsafe()
                        : new Long2SegOpsMS();
        static final Long2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long2BbOpsUnsafe()
                        : new Long2BbOpsApi();
        static final Long2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long2RawOpsUnsafe()
                        : new Long2RawOpsApi();
    }

    public Long2Impl() {
    }

    public Long2Impl(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Long2Impl(Long2R src) {
        this.x = src.x();
        this.y = src.y();
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
    public Long2 add(Long2R other, @Mutated Long2 dest) {
        return add(other.x(), other.y(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 add(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
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
    public Long2 ceilDiv(long scalar, @Mutated Long2 dest) {
        return ceilDiv(scalar, scalar, dest);
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
    public Long2 ceilDiv(Long2R other, @Mutated Long2 dest) {
        return ceilDiv(other.x(), other.y(), dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilDiv(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        d.y = java.lang.Math.ceilDiv(this.y, otherY);
        d.x = _buf0;
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
    public Long2 ceilMod(long scalar, @Mutated Long2 dest) {
        return ceilMod(scalar, scalar, dest);
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
    public Long2 ceilMod(Long2R other, @Mutated Long2 dest) {
        return ceilMod(other.x(), other.y(), dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilMod(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        d.y = java.lang.Math.ceilMod(this.y, otherY);
        d.x = _buf0;
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
    public Long2 div(long scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = this.x / scalar;
        d.y = this.y / scalar;
        d.x = _buf0;
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
    public Long2 div(Long2R other, @Mutated Long2 dest) {
        return div(other.x(), other.y(), dest);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 div(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = this.x / otherX;
        d.y = this.y / otherY;
        d.x = _buf0;
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
    public Long2 floorDiv(long scalar, @Mutated Long2 dest) {
        return floorDiv(scalar, scalar, dest);
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
    public Long2 floorDiv(Long2R other, @Mutated Long2 dest) {
        return floorDiv(other.x(), other.y(), dest);
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorDiv(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = Math.floorDiv(this.x, otherX);
        d.y = Math.floorDiv(this.y, otherY);
        d.x = _buf0;
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
    public Long2 floorMod(long scalar, @Mutated Long2 dest) {
        return floorMod(scalar, scalar, dest);
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
    public Long2 floorMod(Long2R other, @Mutated Long2 dest) {
        return floorMod(other.x(), other.y(), dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorMod(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = Math.floorMod(this.x, otherX);
        d.y = Math.floorMod(this.y, otherY);
        d.x = _buf0;
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
    public Long2 mul(long scalar, @Mutated Long2 dest) {
        return mul(scalar, scalar, dest);
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
    public Long2 mul(Long2R other, @Mutated Long2 dest) {
        return mul(other.x(), other.y(), dest);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 mul(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
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
    public Long2 negate(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
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
    public Long2 rem(long scalar, @Mutated Long2 dest) {
        return rem(scalar, scalar, dest);
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
    public Long2 rem(Long2R other, @Mutated Long2 dest) {
        return rem(other.x(), other.y(), dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rem(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        long _buf0 = this.x % otherX;
        d.y = this.y % otherY;
        d.x = _buf0;
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
    public Long2 sub(Long2R other, @Mutated Long2 dest) {
        return sub(other.x(), other.y(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 sub(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
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
    public Long2 and(Long2R other, @Mutated Long2 dest) {
        return and(other.x(), other.y(), dest);
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 and(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
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
    public Long2 bitCount(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
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
    public Long2 not(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
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
    public Long2 numberOfLeadingZeros(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
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
    public Long2 numberOfTrailingZeros(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
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
    public Long2 or(Long2R other, @Mutated Long2 dest) {
        return or(other.x(), other.y(), dest);
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 or(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
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
    public Long2 reverseBits(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
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
    public Long2 reverseBytes(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
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
    public Long2 rotateLeft(long distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
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
    public Long2 rotateRight(long distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
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
    public Long2 shl(long shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x << (int)(shift);
        d.y = this.y << (int)(shift);
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
    public Long2 shr(long shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x >> (int)(shift);
        d.y = this.y >> (int)(shift);
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
    public Long2 ushr(long shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x >>> (int)(shift);
        d.y = this.y >>> (int)(shift);
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
    public Long2 xor(Long2R other, @Mutated Long2 dest) {
        return xor(other.x(), other.y(), dest);
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 xor(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
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
    public @Mutated Long2 set(Long2R v) {
        return set(v.x(), v.y());
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return this
     */
    @Mutated public Long2 set(long vX, long vY) {
        this.x = vX;
        this.y = vY;
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
    public Long2 set(long s, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = s;
        d.y = s;
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
    public Float2 toFloat(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.x;
        d.y = this.y;
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
    public Double2 toDouble(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x;
        d.y = this.y;
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
    public Byte2 toByte(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
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
    public Short2 toShort(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
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
    public Int2 toInt(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long2 makeZero() {
        this.x = 0L;
        this.y = 0L;
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
    public Long2 absolute(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
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
    public Long2 clamp(long min, long max, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.min(Math.max(this.x, min), max);
        d.y = Math.min(Math.max(this.y, min), max);
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
    public Long2 clamp(Long2R min, Long2R max, @Mutated Long2 dest) {
        return clamp(min.x(), min.y(), max.x(), max.y(), dest);
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 clamp(long minX, long minY, long maxX, long maxY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
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
        return this.x + this.y;
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public long compMax() {
        return Math.max(this.x, this.y);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public long compMin() {
        return Math.min(this.x, this.y);
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
        return this.x * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
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
    public long cross(Long2R other) {
        return cross(other.x(), other.y());
    }


    /**
     * Compute the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     * order.
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
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
    public long cross(long otherX, long otherY) {
        return otherY * this.x - otherX * this.y;
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
    public long distanceSquared(Long2R other) {
        return distanceSquared(other.x(), other.y());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY})
     */
    public long distanceSquared(long otherX, long otherY) {
        long _t0 = this.x - otherX;
        long _t1 = this.y - otherY;
        return _t0 * _t0 + _t1 * _t1;
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
    public long dot(Long2R other) {
        return dot(other.x(), other.y());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY})
     */
    public long dot(long otherX, long otherY) {
        return otherX * this.x + otherY * this.y;
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
        return this.x * this.x + this.y * this.y;
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
    public long manhattanDistance(Long2R other) {
        return manhattanDistance(other.x(), other.y());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY})
     */
    public long manhattanDistance(long otherX, long otherY) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY);
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
        return Math.abs(this.x) + Math.abs(this.y);
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
    public Long2 max(long scalar, @Mutated Long2 dest) {
        return max(scalar, scalar, dest);
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
    public Long2 max(Long2R other, @Mutated Long2 dest) {
        return max(other.x(), other.y(), dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 max(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
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
    public Long2 min(long scalar, @Mutated Long2 dest) {
        return min(scalar, scalar, dest);
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
    public Long2 min(Long2R other, @Mutated Long2 dest) {
        return min(other.x(), other.y(), dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 min(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
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
    public Long2 sign(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
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
    public Long2 satAdd(Long2R other, @Mutated Long2 dest) {
        return satAdd(other.x(), other.y(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satAdd(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddL(this.y, otherY);
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
    public Long2 satMul(Long2R other, @Mutated Long2 dest) {
        return satMul(other.x(), other.y(), dest);
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satMul(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulL(this.y, otherY);
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
    public Long2 satNegate(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegL(this.x);
        d.y = org.joml2.SaturatingMath.satNegL(this.y);
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
    public Long2 satSub(Long2R other, @Mutated Long2 dest) {
        return satSub(other.x(), other.y(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satSub(long otherX, long otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubL(this.y, otherY);
        return d;
    }

    public long x() { return this.x; }
    public long y() { return this.y; }

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

    @Override public String toString() {
        return "Long2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long2Impl)) return false;
        Long2Impl o = (Long2Impl) obj;
        return x == o.x
            && y == o.y;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(x ^ (x >>> 32));
        h = 31 * h + (int)(y ^ (y >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Long2R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(x >= other.x() ? x - other.x() : other.x() - x, epsilon) <= 0
            && Long.compareUnsigned(y >= other.y() ? y - other.y() : other.y() - y, epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }
    public @Mutated Long2 load(long[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        return this;
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long2 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Long2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Long2 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset + 0] = (int) this.x;
        dest[offset + 1] = (int) this.y;
        return dest;
    }
    public @Mutated Long2 load(int[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        return this;
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long2 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public Long2 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long2 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long2 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }
    public MemorySegment storeInt(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeInt(this, offset, dest);
    }
    public Long2 loadInt(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadInt(this, offset, src);
    }

}
