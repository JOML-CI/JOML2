// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.IntBuffer;

/**
 * Generated implementation of {@link Long4} backed by a {@code long[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long4Impl implements Long4 {

    public long[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Long4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long4BbOpsUnsafe()
                        : new Long4BbOpsApi();
        static final Long4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Long4RawOpsUnsafe()
                        : new Long4RawOpsApi();
    }

    public Long4Impl() {
        data = new long[4];
        data[3] = 1;
    }

    public Long4Impl(long x, long y, long z, long w) {
        long[] dd = this.data = new long[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public Long4Impl(Long4R src) {
        long[] dd = this.data = new long[4];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
        dd[3] = src.w();
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
    public Long4 add(Long4R other, @Mutated Long4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 add(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
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
    public Long4 ceilDiv(long scalar, @Mutated Long4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Long4 ceilDiv(Long4R other, @Mutated Long4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilDiv(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = Math.ceilDiv(sd[0], otherX);
        long _buf1 = Math.ceilDiv(sd[1], otherY);
        long _buf2 = Math.ceilDiv(sd[2], otherZ);
        dd[3] = Math.ceilDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 ceilMod(long scalar, @Mutated Long4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Long4 ceilMod(Long4R other, @Mutated Long4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilMod(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = Math.ceilMod(sd[0], otherX);
        long _buf1 = Math.ceilMod(sd[1], otherY);
        long _buf2 = Math.ceilMod(sd[2], otherZ);
        dd[3] = Math.ceilMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 div(long scalar, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = sd[0] / scalar;
        long _buf1 = sd[1] / scalar;
        long _buf2 = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 div(Long4R other, @Mutated Long4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) (integer division, truncating toward zero) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 div(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = sd[0] / otherX;
        long _buf1 = sd[1] / otherY;
        long _buf2 = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 floorDiv(long scalar, @Mutated Long4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Long4 floorDiv(Long4R other, @Mutated Long4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorDiv(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], otherX);
        long _buf1 = Math.floorDiv(sd[1], otherY);
        long _buf2 = Math.floorDiv(sd[2], otherZ);
        dd[3] = Math.floorDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 floorMod(long scalar, @Mutated Long4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Long4 floorMod(Long4R other, @Mutated Long4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorMod(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], otherX);
        long _buf1 = Math.floorMod(sd[1], otherY);
        long _buf2 = Math.floorMod(sd[2], otherZ);
        dd[3] = Math.floorMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 mul(long scalar, @Mutated Long4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Long4 mul(Long4R other, @Mutated Long4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 mul(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
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
    public Long4 negate(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
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
    public Long4 rem(long scalar, @Mutated Long4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Long4 rem(Long4R other, @Mutated Long4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) (the remainder carries the sign of the
     * dividend, exactly Java's {@code %}, so it pairs with {@code div}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rem(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _buf0 = sd[0] % otherX;
        long _buf1 = sd[1] % otherY;
        long _buf2 = sd[2] % otherZ;
        dd[3] = sd[3] % otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
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
    public Long4 sub(Long4R other, @Mutated Long4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 sub(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
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
    public Long4 and(Long4R other, @Mutated Long4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 and(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        dd[3] = sd[3] & otherW;
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
    public Long4 bitCount(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
        dd[3] = Math.bitCount(sd[3]);
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
    public Long4 not(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
        dd[3] = ~sd[3];
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
    public Long4 numberOfLeadingZeros(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
        dd[3] = Math.numberOfLeadingZeros(sd[3]);
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
    public Long4 numberOfTrailingZeros(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
        dd[3] = Math.numberOfTrailingZeros(sd[3]);
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
    public Long4 or(Long4R other, @Mutated Long4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 or(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        dd[3] = sd[3] | otherW;
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
    public Long4 reverseBits(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
        dd[3] = Math.reverseBits(sd[3]);
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
    public Long4 reverseBytes(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
        dd[3] = Math.reverseBytes(sd[3]);
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
    public Long4 rotateLeft(long distance, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
        dd[3] = Math.rotateLeft(sd[3], distance);
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
    public Long4 rotateRight(long distance, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        dd[3] = Math.rotateRight(sd[3], distance);
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
    public Long4 shl(long shift, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] << (int)(shift);
        dd[1] = sd[1] << (int)(shift);
        dd[2] = sd[2] << (int)(shift);
        dd[3] = sd[3] << (int)(shift);
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
    public Long4 shr(long shift, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] >> (int)(shift);
        dd[1] = sd[1] >> (int)(shift);
        dd[2] = sd[2] >> (int)(shift);
        dd[3] = sd[3] >> (int)(shift);
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
    public Long4 ushr(long shift, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] >>> (int)(shift);
        dd[1] = sd[1] >>> (int)(shift);
        dd[2] = sd[2] >>> (int)(shift);
        dd[3] = sd[3] >>> (int)(shift);
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
    public Long4 xor(Long4R other, @Mutated Long4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xor(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        dd[3] = sd[3] ^ otherW;
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
    public @Mutated Long4 set(Long4R v) {
        return set(v.x(), v.y(), v.z(), v.w());
    }


    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @return this
     */
    @Mutated public Long4 set(long vX, long vY, long vZ, long vW) {
        long[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
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
    public Long4 set(long s, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
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
    public Float4 toFloat(@Mutated Float4 dest) {
        long[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
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
    public Double4 toDouble(@Mutated Double4 dest) {
        long[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
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
    public Byte4 toByte(@Mutated Byte4 dest) {
        long[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (sd[3]);
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
    public Short4 toShort(@Mutated Short4 dest) {
        long[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (sd[3]);
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
    public Int4 toInt(@Mutated Int4 dest) {
        long[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
        dd[3] = (int) (sd[3]);
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long4 makeZero() {
        long[] dd = this.data;
        dd[0] = 0L;
        dd[1] = 0L;
        dd[2] = 0L;
        dd[3] = 0L;
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
    public Long4 absolute(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
        dd[3] = Math.abs(sd[3]);
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
    public Long4 clamp(long min, long max, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
        dd[2] = Math.min(Math.max(sd[2], min), max);
        dd[3] = Math.min(Math.max(sd[3], min), max);
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
    public Long4 clamp(Long4R min, Long4R max, @Mutated Long4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Long4 clamp(long minX, long minY, long minZ, long minW, long maxX, long maxY, long maxZ, long maxW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        dd[3] = Math.min(Math.max(sd[3], minW), maxW);
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
        return sd[3] + (sd[2] + (sd[0] + sd[1]));
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
        return Math.max(Math.max(Math.max(sd[0], sd[1]), sd[2]), sd[3]);
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
        return Math.min(Math.min(Math.min(sd[0], sd[1]), sd[2]), sd[3]);
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
        return sd[3] * sd[2] * sd[0] * sd[1];
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long4 cross(Long4R v, Long4R w, @Mutated Long4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Long4 cross(long vX, long vY, long vZ, long vW, long wX, long wY, long wZ, long wW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _t12 = vZ * wW - vW * wZ;
        long _t13 = vY * wW - vW * wY;
        long _t14 = vY * wZ - vZ * wY;
        long _t15 = vX * wW - vW * wX;
        long _t16 = vX * wZ - vZ * wX;
        long _t17 = vX * wY - vY * wX;
        long _buf0 = sd[1] * _t12 - sd[2] * _t13 + sd[3] * _t14;
        long _buf1 = sd[2] * _t15 - sd[0] * _t12 - sd[3] * _t16;
        long _buf2 = sd[0] * _t13 - sd[1] * _t15 + sd[3] * _t17;
        dd[3] = sd[1] * _t16 - sd[0] * _t14 - sd[2] * _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
    public long distanceSquared(Long4R other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
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
    public long distanceSquared(long otherX, long otherY, long otherZ, long otherW) {
        long[] sd = this.data;
        long _t0 = sd[0] - otherX;
        long _t1 = sd[1] - otherY;
        long _t2 = sd[2] - otherZ;
        long _t3 = sd[3] - otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
    public long dot(Long4R other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
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
    public long dot(long otherX, long otherY, long otherZ, long otherW) {
        long[] sd = this.data;
        return otherX * sd[0] + otherY * sd[1] + otherZ * sd[2] + otherW * sd[3];
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
        return sd[0] * sd[0] + sd[1] * sd[1] + sd[2] * sd[2] + sd[3] * sd[3];
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
    public long manhattanDistance(Long4R other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed in {@code long}, the widest integer type, so a result outside the
     * {@code long} range wraps.
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
    public long manhattanDistance(long otherX, long otherY, long otherZ, long otherW) {
        long[] sd = this.data;
        return Math.abs(sd[0] - otherX) + Math.abs(sd[1] - otherY) + Math.abs(sd[2] - otherZ) + Math.abs(sd[3] - otherW);
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
        return Math.abs(sd[0]) + Math.abs(sd[1]) + Math.abs(sd[2]) + Math.abs(sd[3]);
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
    public Long4 max(long scalar, @Mutated Long4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Long4 max(Long4R other, @Mutated Long4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 max(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        dd[3] = Math.max(sd[3], otherW);
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
    public Long4 min(long scalar, @Mutated Long4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Long4 min(Long4R other, @Mutated Long4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 min(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        dd[3] = Math.min(sd[3], otherW);
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
    public Long4 sign(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
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
    public Long4 satAdd(Long4R other, @Mutated Long4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satAdd(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddL(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAddL(sd[3], otherW);
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
    public Long4 satMul(Long4R other, @Mutated Long4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satMul(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulL(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMulL(sd[3], otherW);
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
    public Long4 satNegate(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegL(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegL(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegL(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNegL(sd[3]);
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
    public Long4 satSub(Long4R other, @Mutated Long4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satSub(long otherX, long otherY, long otherZ, long otherW, @Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubL(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSubL(sd[3], otherW);
        return dest;
    }

    public long x() { return data[0]; }
    public long y() { return data[1]; }
    public long z() { return data[2]; }
    public long w() { return data[3]; }

    public Long2 xx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long2 xy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 xz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 xw(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 yx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 yy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long2 yz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 yw(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 zx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 zy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 zz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long2 zw(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 wx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 wy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 wz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Long2 ww(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Long3 xxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
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

    public Long3 xxz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 xxw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
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
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 xyz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xyw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xzx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 xzy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xzz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 xzw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xwx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 xwy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xwz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xww(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
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

    public Long3 yxz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 yxw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Long3 yyz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yyw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yzx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 yzy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 yzz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 yzw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 ywx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 ywy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 ywz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 yww(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zxy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zxz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 zxw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zyx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zyy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zyz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 zyw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zzx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zzy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zzz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Long3 zzw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zwx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zwy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zwz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 zww(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wxy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wxz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wxw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 wyx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wyy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wyz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wyw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 wzx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wzy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 wzz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wzw(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Long3 wwx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wwy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 wwz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Long3 www(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Long4 xxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
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

    public Long4 xxxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xxxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
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

    public Long4 xxyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xxzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xxzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xxwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xxww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
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

    public Long4 xyxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xyxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
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
        long _v0 = sd[0];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xyyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xyyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xyzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xyzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xyzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xyzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[2];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xywx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xywy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xywz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xyww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xzxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xzxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xzyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xzyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xzzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xzzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xzzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xzwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xzwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xzww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xwxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xwyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xwyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xwyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xwzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xwzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 xwzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xwwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 xwwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xwww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[0];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        dd[0] = _v0;
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

    public Long4 yxxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yxxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
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

    public Long4 yxyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yxyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yxzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yxzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yxzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 yxwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yxwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yxwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 yxww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
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

    public Long4 yyxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yyxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
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
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yyyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yyyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yyzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yyzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yyzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yyzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yywx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yywy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yywz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yyww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yzxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yzxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yzxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yzxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 yzyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yzyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yzyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yzyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yzzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yzzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yzzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yzzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 yzwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 yzwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 yzwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 yzww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[2];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 ywxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 ywxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 ywyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 ywyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 ywzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 ywzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 ywzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 ywwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 ywwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 ywww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[1];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zxxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zxxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zxyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zxyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zxyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zxzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zxzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zxzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zxwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zxwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zxww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zyxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zyxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        long _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zyyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zyyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zyyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zyzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zyzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zywx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zywy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zywz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zyww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zzxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zzxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zzyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zzyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        long _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zzzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zzzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zzzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zzwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zzwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zzww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zwxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zwxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zwxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zwyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 zwyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zwyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zwzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zwzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zwwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zwwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 zwww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[2];
        long _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wxxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wxyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wxyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wxyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wxzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wxzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wxzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wxwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wxwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wxww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wyxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wyxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wyxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        long _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wyxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wyyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wyyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wyyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wyyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wyzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wyzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wyzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wyzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wywx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wywy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wywz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wyww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wzxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        long _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wzxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wzxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wzyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        long _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Long4 wzyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wzyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wzzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wzzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wzwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wzwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wzww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wwxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwxw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wwyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwyw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wwzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwzy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 wwzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwzw(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Long4 wwwx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwwy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwwz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        long _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Long4 wwww(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Long4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long4Impl)) return false;
        Long4Impl o = (Long4Impl) obj;
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

    @Override public boolean equalsEpsilon(Long4R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(data[0] >= other.x() ? data[0] - other.x() : other.x() - data[0], epsilon) <= 0
            && Long.compareUnsigned(data[1] >= other.y() ? data[1] - other.y() : other.y() - data[1], epsilon) <= 0
            && Long.compareUnsigned(data[2] >= other.z() ? data[2] - other.z() : other.z() - data[2], epsilon) <= 0
            && Long.compareUnsigned(data[3] >= other.w() ? data[3] - other.w() : other.w() - data[3], epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Long4 load(long[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long4 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Long4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset + 0] = (int) this.data[0];
        dest[offset + 1] = (int) this.data[1];
        dest[offset + 2] = (int) this.data[2];
        dest[offset + 3] = (int) this.data[3];
        return dest;
    }
    public @Mutated Long4 load(int[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Long4 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public Long4 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long4 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long4 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }

}
