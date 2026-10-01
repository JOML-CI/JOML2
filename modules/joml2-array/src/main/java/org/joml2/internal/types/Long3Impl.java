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
 * Generated implementation of {@link Long3} backed by a {@code long[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long3Impl implements Long3 {

    public long[] data;

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
        data = new long[3];
    }

    public Long3Impl(long x, long y, long z) {
        long[] dd = this.data = new long[3];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
    }

    public Long3Impl(Long3R src) {
        long[] dd = this.data = new long[3];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
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
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
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
    public Long3 ceilDiv(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], scalar);
        long _buf1 = java.lang.Math.ceilDiv(sd[1], scalar);
        dd[2] = java.lang.Math.ceilDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 ceilDiv(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], other.x());
        long _buf1 = java.lang.Math.ceilDiv(sd[1], other.y());
        dd[2] = java.lang.Math.ceilDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        long _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        dd[2] = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 ceilMod(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], scalar);
        long _buf1 = java.lang.Math.ceilMod(sd[1], scalar);
        dd[2] = java.lang.Math.ceilMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 ceilMod(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], other.x());
        long _buf1 = java.lang.Math.ceilMod(sd[1], other.y());
        dd[2] = java.lang.Math.ceilMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        long _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        dd[2] = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 div(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] / scalar;
        long _buf1 = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 div(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] / other.x();
        long _buf1 = sd[1] / other.y();
        dd[2] = sd[2] / other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] / otherX;
        long _buf1 = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 floorDiv(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], scalar);
        long _buf1 = Math.floorDiv(sd[1], scalar);
        dd[2] = Math.floorDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 floorDiv(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], other.x());
        long _buf1 = Math.floorDiv(sd[1], other.y());
        dd[2] = Math.floorDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorDiv(sd[0], otherX);
        long _buf1 = Math.floorDiv(sd[1], otherY);
        dd[2] = Math.floorDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 floorMod(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], scalar);
        long _buf1 = Math.floorMod(sd[1], scalar);
        dd[2] = Math.floorMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 floorMod(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], other.x());
        long _buf1 = Math.floorMod(sd[1], other.y());
        dd[2] = Math.floorMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = Math.floorMod(sd[0], otherX);
        long _buf1 = Math.floorMod(sd[1], otherY);
        dd[2] = Math.floorMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 mul(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
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
    public Long3 mul(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
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
    public Long3 negate(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Long3 rem(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] % scalar;
        long _buf1 = sd[1] % scalar;
        dd[2] = sd[2] % scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 rem(Long3R other, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] % other.x();
        long _buf1 = sd[1] % other.y();
        dd[2] = sd[2] % other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _buf0 = sd[0] % otherX;
        long _buf1 = sd[1] % otherY;
        dd[2] = sd[2] % otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 sub(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
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
    public Long3 and(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] & other.x();
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
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
    public Long3 bitCount(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
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
    public Long3 not(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
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
    public Long3 numberOfLeadingZeros(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
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
    public Long3 numberOfTrailingZeros(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
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
    public Long3 or(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] | other.x();
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
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
    public Long3 reverseBits(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
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
    public Long3 reverseBytes(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
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
    public Long3 rotateLeft(long distance, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
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
    public Long3 rotateRight(long distance, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
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
    public Long3 shl(long shift, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] << (int)(shift);
        dd[1] = sd[1] << (int)(shift);
        dd[2] = sd[2] << (int)(shift);
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
    public Long3 shr(long shift, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >> (int)(shift);
        dd[1] = sd[1] >> (int)(shift);
        dd[2] = sd[2] >> (int)(shift);
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
    public Long3 ushr(long shift, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >>> (int)(shift);
        dd[1] = sd[1] >>> (int)(shift);
        dd[2] = sd[2] >>> (int)(shift);
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
    public Long3 xor(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] ^ other.x();
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
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
    public @Mutated Long3 set(Long3R v) {
        long vY = v.y();
        long vZ = v.z();
        long[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
        return this;
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
        long[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
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
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Float3 toFloat(@Mutated Float3 dest) {
        long[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
    public Double3 toDouble(@Mutated Double3 dest) {
        long[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
    public Byte3 toByte(@Mutated Byte3 dest) {
        long[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
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
    public Short3 toShort(@Mutated Short3 dest) {
        long[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
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
    public Int3 toInt(@Mutated Int3 dest) {
        long[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long3 makeZero() {
        long[] dd = this.data;
        dd[0] = 0L;
        dd[1] = 0L;
        dd[2] = 0L;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
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
    public Long3 clamp(long min, long max, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
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
    public Long3 clamp(Long3R min, Long3R max, @Mutated Long3 dest) {
        long minY = min.y();
        long minZ = min.z();
        long maxY = max.y();
        long maxZ = max.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
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
        return sd[2] + (sd[0] + sd[1]);
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
        return java.lang.Math.max(java.lang.Math.max(sd[0], sd[1]), sd[2]);
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
        return java.lang.Math.min(java.lang.Math.min(sd[0], sd[1]), sd[2]);
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
        return sd[2] * sd[0] * sd[1];
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
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _rd0 = sd[0];
        long _rd1 = sd[1];
        long _rd2 = sd[2];
        dd[0] = otherZ * _rd1 - otherY * _rd2;
        dd[1] = otherX * _rd2 - otherZ * _rd0;
        dd[2] = otherY * _rd0 - otherX * _rd1;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _rd0 = sd[0];
        long _rd1 = sd[1];
        long _rd2 = sd[2];
        dd[0] = otherZ * _rd1 - otherY * _rd2;
        dd[1] = otherX * _rd2 - otherZ * _rd0;
        dd[2] = otherY * _rd0 - otherX * _rd1;
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
    public long distanceSquared(Long3R other) {
        long[] sd = this.data;
        long _t0 = sd[0] - other.x();
        long _t1 = sd[1] - other.y();
        long _t2 = sd[2] - other.z();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
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
        long[] sd = this.data;
        long _t0 = sd[0] - otherX;
        long _t1 = sd[1] - otherY;
        long _t2 = sd[2] - otherZ;
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
        long[] sd = this.data;
        return other.x() * sd[0] + other.y() * sd[1] + other.z() * sd[2];
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
        long[] sd = this.data;
        return otherX * sd[0] + otherY * sd[1] + otherZ * sd[2];
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
        return sd[0] * sd[0] + sd[1] * sd[1] + sd[2] * sd[2];
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
        long[] sd = this.data;
        return java.lang.Math.abs(sd[0] - other.x()) + java.lang.Math.abs(sd[1] - other.y()) + java.lang.Math.abs(sd[2] - other.z());
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
        long[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherX) + java.lang.Math.abs(sd[1] - otherY) + java.lang.Math.abs(sd[2] - otherZ);
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
        return java.lang.Math.abs(sd[0]) + java.lang.Math.abs(sd[1]) + java.lang.Math.abs(sd[2]);
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
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
    public Long3 max(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
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
    public Long3 min(long scalar, @Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
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
    public Long3 min(Long3R other, @Mutated Long3 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
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
    public Long3 sign(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Long3 satAdd(Long3R other, @Mutated Long3 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddL(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddL(sd[2], otherZ);
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
    public Long3 satMul(Long3R other, @Mutated Long3 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulL(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulL(sd[2], otherZ);
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
    public Long3 satNegate(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegL(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegL(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegL(sd[2]);
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
    public Long3 satSub(Long3R other, @Mutated Long3 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubL(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubL(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubL(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubL(sd[2], otherZ);
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 0L;
        return dest;
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
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1L;
        return dest;
    }

    public long x() { return data[0]; }
    public long y() { return data[1]; }
    public long z() { return data[2]; }

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

    public Long2 xz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[2];
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

    public Long2 yz(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Long2 zx(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Long2 zy(@Mutated Long2 dest) {
        long[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Long2 zz(@Mutated Long2 dest) {
        long[] dd = ((Long2Impl) dest).data;
        long _v0 = this.data[2];
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

    public Long3 xyz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = sd[0];
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
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 xzz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[2];
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

    public Long3 yxz(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = sd[1];
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
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = this.data[1];
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

    public Long3 yzx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = sd[1];
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
        long _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zxx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Long3 zxy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = sd[2];
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

    public Long3 zyx(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Long3 zyy(@Mutated Long3 dest) {
        long[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[2];
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
        long[] dd = ((Long3Impl) dest).data;
        long _v0 = this.data[2];
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

    public Long4 xyyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = sd[0];
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
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 xyzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[2];
        dd[0] = sd[0];
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
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzyz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
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
        long _v1 = sd[2];
        long _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 xzzz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[2];
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

    public Long4 yxxz(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = sd[1];
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

    public Long4 yxzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = sd[1];
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
        long _v1 = sd[0];
        long _v2 = sd[2];
        dd[0] = sd[1];
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

    public Long4 yzxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = sd[1];
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
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
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

    public Long4 yzzx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[2];
        long _v2 = sd[0];
        dd[0] = sd[1];
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
        long _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = sd[2];
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

    public Long4 zxyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Long4 zxyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[0];
        long _v2 = sd[1];
        dd[0] = sd[2];
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

    public Long4 zyxx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyxy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = sd[2];
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

    public Long4 zyyx(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        long _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Long4 zyyy(@Mutated Long4 dest) {
        long[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        long _v1 = sd[1];
        dd[0] = sd[2];
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
        long[] dd = ((Long4Impl) dest).data;
        long _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Long3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long3Impl)) return false;
        Long3Impl o = (Long3Impl) obj;
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

    @Override public boolean equalsEpsilon(Long3R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(data[0] >= other.x() ? data[0] - other.x() : other.x() - data[0], epsilon) <= 0
            && Long.compareUnsigned(data[1] >= other.y() ? data[1] - other.y() : other.y() - data[1], epsilon) <= 0
            && Long.compareUnsigned(data[2] >= other.z() ? data[2] - other.z() : other.z() - data[2], epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Long3 load(long[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        return this;
    }
    public LongBuffer store(@Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public LongBuffer storeRelative(@Mutated LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Long3 load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long3 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long3 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Long3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Long3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long3 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Long3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Long3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Long3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = (int) this.data[0];
        dest[offset + 1] = (int) this.data[1];
        dest[offset + 2] = (int) this.data[2];
        return dest;
    }
    public @Mutated Long3 load(int[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        return this;
    }
    public IntBuffer store(@Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public IntBuffer storeRelative(@Mutated IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Long3 load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long3 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long3 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer storeInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeIntAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    public Long3 loadInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, buf.position(), buf);
    }
    public Long3 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long3 loadIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long3 r = StoreLoad.BB_OPS.loadIntAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return r;
    }
    public Long3 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long3 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }
    public MemorySegment storeInt(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeInt(this, 0L, dest); }
    public MemorySegment storeInt(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeInt(this, offset, dest);
    }
    @Mutated public Long3 loadInt(MemorySegment src) { return StoreLoad.SEG_OPS.loadInt(this, 0L, src); }
    public Long3 loadInt(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadInt(this, offset, src);
    }
}
