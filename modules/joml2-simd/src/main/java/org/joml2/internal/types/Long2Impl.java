// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.nio.IntBuffer;

/**
 * Generated implementation of {@link Long2} backed by a {@code long[]} array, with Vector API SIMD
 * kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long2Impl implements Long2 {

    public long[] data;

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
        data = new long[2];
    }

    public Long2Impl(long x, long y) {
        long[] dd = this.data = new long[2];
        dd[0] = x;
        dd[1] = y;
    }

    public Long2Impl(Long2R src) {
        long[] dd = this.data = new long[2];
        dd[0] = src.x();
        dd[1] = src.y();
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], scalar);
        dd[1] = java.lang.Math.ceilDiv(sd[1], scalar);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], other.x());
        dd[1] = java.lang.Math.ceilDiv(sd[1], other.y());
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        dd[1] = java.lang.Math.ceilDiv(sd[1], otherY);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], scalar);
        dd[1] = java.lang.Math.ceilMod(sd[1], scalar);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], other.x());
        dd[1] = java.lang.Math.ceilMod(sd[1], other.y());
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        dd[1] = java.lang.Math.ceilMod(sd[1], otherY);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] / scalar;
        dd[1] = sd[1] / scalar;
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] / other.x();
        dd[1] = sd[1] / other.y();
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] / otherX;
        dd[1] = sd[1] / otherY;
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], scalar);
        dd[1] = Math.floorDiv(sd[1], scalar);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], other.x());
        dd[1] = Math.floorDiv(sd[1], other.y());
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], otherX);
        dd[1] = Math.floorDiv(sd[1], otherY);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], scalar);
        dd[1] = Math.floorMod(sd[1], scalar);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], other.x());
        dd[1] = Math.floorMod(sd[1], other.y());
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], otherX);
        dd[1] = Math.floorMod(sd[1], otherY);
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] % scalar;
        dd[1] = sd[1] % scalar;
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] % other.x();
        dd[1] = sd[1] % other.y();
        dd[0] = _buf0;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _buf0 = sd[0] % otherX;
        dd[1] = sd[1] % otherY;
        dd[0] = _buf0;
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] & other.x();
        dd[1] = sd[1] & otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] | other.x();
        dd[1] = sd[1] | otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] << (int)(shift);
        dd[1] = sd[1] << (int)(shift);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] >> (int)(shift);
        dd[1] = sd[1] >> (int)(shift);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] >>> (int)(shift);
        dd[1] = sd[1] >>> (int)(shift);
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] ^ other.x();
        dd[1] = sd[1] ^ otherY;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        return dest;
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
        long vY = v.y();
        long[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        return this;
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
        long[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
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
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        return dest;
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
        long[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        long[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        long[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        return dest;
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
        long[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        return dest;
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
        long[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long2 makeZero() {
        long[] dd = this.data;
        dd[0] = 0L;
        dd[1] = 0L;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        return dest;
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
        long minY = min.y();
        long maxY = max.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        return dest;
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
        long[] sd = this.data;
        return sd[0] + sd[1];
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public long compMax() {
        long[] sd = this.data;
        return java.lang.Math.max(sd[0], sd[1]);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public long compMin() {
        long[] sd = this.data;
        return java.lang.Math.min(sd[0], sd[1]);
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
        long[] sd = this.data;
        return sd[0] * sd[1];
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
        long[] sd = this.data;
        return other.y() * sd[0] - other.x() * sd[1];
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
        long[] sd = this.data;
        return otherY * sd[0] - otherX * sd[1];
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
        long[] sd = this.data;
        long _t0 = sd[0] - other.x();
        long _t1 = sd[1] - other.y();
        return _t0 * _t0 + _t1 * _t1;
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
        long[] sd = this.data;
        long _t0 = sd[0] - otherX;
        long _t1 = sd[1] - otherY;
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
        long[] sd = this.data;
        return other.x() * sd[0] + other.y() * sd[1];
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
        long[] sd = this.data;
        return otherX * sd[0] + otherY * sd[1];
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
        long[] sd = this.data;
        return sd[0] * sd[0] + sd[1] * sd[1];
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
        long[] sd = this.data;
        return java.lang.Math.abs(sd[0] - other.x()) + java.lang.Math.abs(sd[1] - other.y());
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
        long[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherX) + java.lang.Math.abs(sd[1] - otherY);
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
        long[] sd = this.data;
        return java.lang.Math.abs(sd[0]) + java.lang.Math.abs(sd[1]);
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        return dest;
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
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        return dest;
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
        long otherX = other.x();
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddL(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddL(sd[1], otherY);
        return dest;
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
        long otherX = other.x();
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulL(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulL(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegL(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegL(sd[1]);
        return dest;
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
        long otherX = other.x();
        long otherY = other.y();
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubL(sd[1], otherY);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubL(sd[1], otherY);
        return dest;
    }

    public long x() { return data[0]; }
    public long y() { return data[1]; }

    public Long2 xx(@Mutated Long2 dest) {
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long2 xy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Long2 yx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Long2 yy(@Mutated Long2 dest) {
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long3 xxx(@Mutated Long3 dest) {
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Long3 xxy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 xyx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 xyy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yxy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 yyx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yyy(@Mutated Long3 dest) {
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Long4 xxxx(@Mutated Long4 dest) {
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xxxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xxyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xxyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xyxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xyxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xyyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xyyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yxyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yyxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yyxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yyyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yyyy(@Mutated Long4 dest) {
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Long2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long2Impl)) return false;
        Long2Impl o = (Long2Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Long2R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(data[0] >= other.x() ? data[0] - other.x() : other.x() - data[0], epsilon) <= 0
            && Long.compareUnsigned(data[1] >= other.y() ? data[1] - other.y() : other.y() - data[1], epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        return dest;
    }
    public @Mutated Long2 load(long[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        return this;
    }
    public LongBuffer store(@Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public LongBuffer storeRelative(@Mutated LongBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    @Mutated public Long2 load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long2 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long2 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public Long2 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Long2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long2 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public Long2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Long2 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Long2 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = (int) this.data[0];
        dest[offset + 1] = (int) this.data[1];
        return dest;
    }
    public @Mutated Long2 load(int[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        return this;
    }
    public IntBuffer store(@Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public IntBuffer storeRelative(@Mutated IntBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    @Mutated public Long2 load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long2 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long2 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return this;
    }
    public ByteBuffer storeInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeIntAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    public Long2 loadInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, buf.position(), buf);
    }
    public Long2 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long2 loadIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long2 r = StoreLoad.BB_OPS.loadIntAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return r;
    }
    public Long2 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long2 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }
    public MemorySegment storeInt(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeInt(this, 0L, dest); }
    public MemorySegment storeInt(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeInt(this, offset, dest);
    }
    @Mutated public Long2 loadInt(MemorySegment src) { return StoreLoad.SEG_OPS.loadInt(this, 0L, src); }
    public Long2 loadInt(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadInt(this, offset, src);
    }
}
