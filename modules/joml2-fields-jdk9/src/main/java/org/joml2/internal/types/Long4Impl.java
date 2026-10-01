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
 * Generated implementation of {@link Long4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Long4Impl implements Long4 {

    public long x;
    public long y;
    public long z;
    public long w;

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
        w = 1;
    }

    public Long4Impl(long x, long y, long z, long w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Long4Impl(Long4R src) {
        this.x = src.x();
        this.y = src.y();
        this.z = src.z();
        this.w = src.w();
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
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
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
    public Long4 ceilDiv(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilDiv(this.x, scalar);
        long _buf1 = Math.ceilDiv(this.y, scalar);
        long _buf2 = Math.ceilDiv(this.z, scalar);
        d.w = Math.ceilDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilDiv(this.x, other.x());
        long _buf1 = Math.ceilDiv(this.y, other.y());
        long _buf2 = Math.ceilDiv(this.z, other.z());
        d.w = Math.ceilDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilDiv(this.x, otherX);
        long _buf1 = Math.ceilDiv(this.y, otherY);
        long _buf2 = Math.ceilDiv(this.z, otherZ);
        d.w = Math.ceilDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 ceilMod(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilMod(this.x, scalar);
        long _buf1 = Math.ceilMod(this.y, scalar);
        long _buf2 = Math.ceilMod(this.z, scalar);
        d.w = Math.ceilMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilMod(this.x, other.x());
        long _buf1 = Math.ceilMod(this.y, other.y());
        long _buf2 = Math.ceilMod(this.z, other.z());
        d.w = Math.ceilMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.ceilMod(this.x, otherX);
        long _buf1 = Math.ceilMod(this.y, otherY);
        long _buf2 = Math.ceilMod(this.z, otherZ);
        d.w = Math.ceilMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 div(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x / scalar;
        long _buf1 = this.y / scalar;
        long _buf2 = this.z / scalar;
        d.w = this.w / scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 div(Long4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x / other.x();
        long _buf1 = this.y / other.y();
        long _buf2 = this.z / other.z();
        d.w = this.w / other.w();
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x / otherX;
        long _buf1 = this.y / otherY;
        long _buf2 = this.z / otherZ;
        d.w = this.w / otherW;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorDiv(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorDiv(this.x, scalar);
        long _buf1 = Math.floorDiv(this.y, scalar);
        long _buf2 = Math.floorDiv(this.z, scalar);
        d.w = Math.floorDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorDiv(this.x, other.x());
        long _buf1 = Math.floorDiv(this.y, other.y());
        long _buf2 = Math.floorDiv(this.z, other.z());
        d.w = Math.floorDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorDiv(this.x, otherX);
        long _buf1 = Math.floorDiv(this.y, otherY);
        long _buf2 = Math.floorDiv(this.z, otherZ);
        d.w = Math.floorDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorMod(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorMod(this.x, scalar);
        long _buf1 = Math.floorMod(this.y, scalar);
        long _buf2 = Math.floorMod(this.z, scalar);
        d.w = Math.floorMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorMod(this.x, other.x());
        long _buf1 = Math.floorMod(this.y, other.y());
        long _buf2 = Math.floorMod(this.z, other.z());
        d.w = Math.floorMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = Math.floorMod(this.x, otherX);
        long _buf1 = Math.floorMod(this.y, otherY);
        long _buf2 = Math.floorMod(this.z, otherZ);
        d.w = Math.floorMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 mul(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
        return d;
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
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
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
    public Long4 negate(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
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
    public Long4 rem(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x % scalar;
        long _buf1 = this.y % scalar;
        long _buf2 = this.z % scalar;
        d.w = this.w % scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x % other.x();
        long _buf1 = this.y % other.y();
        long _buf2 = this.z % other.z();
        d.w = this.w % other.w();
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _buf0 = this.x % otherX;
        long _buf1 = this.y % otherY;
        long _buf2 = this.z % otherZ;
        d.w = this.w % otherW;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 sub(Long4R other, @Mutated Long4 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
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
    public Long4 and(Long4R other, @Mutated Long4 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
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
    public Long4 bitCount(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
        d.z = Math.bitCount(this.z);
        d.w = Math.bitCount(this.w);
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
    public Long4 not(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
        d.z = ~this.z;
        d.w = ~this.w;
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
    public Long4 numberOfLeadingZeros(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
        d.z = Math.numberOfLeadingZeros(this.z);
        d.w = Math.numberOfLeadingZeros(this.w);
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
    public Long4 numberOfTrailingZeros(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
        d.z = Math.numberOfTrailingZeros(this.z);
        d.w = Math.numberOfTrailingZeros(this.w);
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
    public Long4 or(Long4R other, @Mutated Long4 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
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
    public Long4 reverseBits(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
        d.z = Math.reverseBits(this.z);
        d.w = Math.reverseBits(this.w);
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
    public Long4 reverseBytes(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
        d.z = Math.reverseBytes(this.z);
        d.w = Math.reverseBytes(this.w);
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
    public Long4 rotateLeft(long distance, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        d.w = Math.rotateLeft(this.w, distance);
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
    public Long4 rotateRight(long distance, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
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
    public Long4 shl(long shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x << (int)(shift);
        d.y = this.y << (int)(shift);
        d.z = this.z << (int)(shift);
        d.w = this.w << (int)(shift);
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
    public Long4 shr(long shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >> (int)(shift);
        d.y = this.y >> (int)(shift);
        d.z = this.z >> (int)(shift);
        d.w = this.w >> (int)(shift);
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
    public Long4 ushr(long shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >>> (int)(shift);
        d.y = this.y >>> (int)(shift);
        d.z = this.z >>> (int)(shift);
        d.w = this.w >>> (int)(shift);
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
    public Long4 xor(Long4R other, @Mutated Long4 dest) {
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
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
    public @Mutated Long4 set(Long4R v) {
        long vY = v.y();
        long vZ = v.z();
        long vW = v.w();
        this.x = v.x();
        this.y = vY;
        this.z = vZ;
        this.w = vW;
        return this;
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
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
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
    public Float4 toFloat(@Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = this.w;
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
    public Double4 toDouble(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = this.w;
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
    public Byte4 toByte(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
        d.w = (byte) (this.w);
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
    public Short4 toShort(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
        d.w = (short) (this.w);
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
    public Int4 toInt(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = (int) (this.x);
        d.y = (int) (this.y);
        d.z = (int) (this.z);
        d.w = (int) (this.w);
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Long4 makeZero() {
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        this.w = 0L;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        d.w = java.lang.Math.abs(this.w);
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
    public Long4 clamp(long min, long max, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, min), max);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, min), max);
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
    public Long4 clamp(Long4R min, Long4R max, @Mutated Long4 dest) {
        long minY = min.y();
        long minZ = min.z();
        long minW = min.w();
        long maxY = max.y();
        long maxZ = max.z();
        long maxW = max.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW);
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
        return this.w + (this.z + (this.x + this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public long compMax() {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public long compMin() {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z), this.w);
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
        return this.w * this.z * this.x * this.y;
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
        long vX = v.x();
        long vY = v.y();
        long vZ = v.z();
        long vW = v.w();
        long wX = w.x();
        long wY = w.y();
        long wZ = w.z();
        long wW = w.w();
        Long4Impl d = (Long4Impl) dest;
        long _t12 = vZ * wW - vW * wZ;
        long _t13 = vY * wW - vW * wY;
        long _t14 = vY * wZ - vZ * wY;
        long _t15 = vX * wW - vW * wX;
        long _t16 = vX * wZ - vZ * wX;
        long _t17 = vX * wY - vY * wX;
        long _rd0 = this.x;
        long _rd1 = this.y;
        long _rd2 = this.z;
        d.x = _rd1 * _t12 - _rd2 * _t13 + this.w * _t14;
        d.y = _rd2 * _t15 - _rd0 * _t12 - this.w * _t16;
        d.z = _rd0 * _t13 - _rd1 * _t15 + this.w * _t17;
        d.w = _rd1 * _t16 - _rd0 * _t14 - _rd2 * _t17;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        long _t12 = vZ * wW - vW * wZ;
        long _t13 = vY * wW - vW * wY;
        long _t14 = vY * wZ - vZ * wY;
        long _t15 = vX * wW - vW * wX;
        long _t16 = vX * wZ - vZ * wX;
        long _t17 = vX * wY - vY * wX;
        long _rd0 = this.x;
        long _rd1 = this.y;
        long _rd2 = this.z;
        d.x = _rd1 * _t12 - _rd2 * _t13 + this.w * _t14;
        d.y = _rd2 * _t15 - _rd0 * _t12 - this.w * _t16;
        d.z = _rd0 * _t13 - _rd1 * _t15 + this.w * _t17;
        d.w = _rd1 * _t16 - _rd0 * _t14 - _rd2 * _t17;
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
    public long distanceSquared(Long4R other) {
        long _t0 = this.x - other.x();
        long _t1 = this.y - other.y();
        long _t2 = this.z - other.z();
        long _t3 = this.w - other.w();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
        long _t0 = this.x - otherX;
        long _t1 = this.y - otherY;
        long _t2 = this.z - otherZ;
        long _t3 = this.w - otherW;
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
        return other.x() * this.x + other.y() * this.y + other.z() * this.z + other.w() * this.w;
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
        return otherX * this.x + otherY * this.y + otherZ * this.z + otherW * this.w;
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
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
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
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z()) + java.lang.Math.abs(this.w - other.w());
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
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ) + java.lang.Math.abs(this.w - otherW);
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
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y) + java.lang.Math.abs(this.z) + java.lang.Math.abs(this.w);
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
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
        d.w = java.lang.Math.max(this.w, scalar);
        return d;
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
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        d.w = java.lang.Math.max(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        d.w = java.lang.Math.max(this.w, otherW);
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
    public Long4 min(long scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
        d.w = java.lang.Math.min(this.w, scalar);
        return d;
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
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        d.w = java.lang.Math.min(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        d.w = java.lang.Math.min(this.w, otherW);
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
    public Long4 sign(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        d.w = Math.signum(this.w);
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
    public Long4 satAdd(Long4R other, @Mutated Long4 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddL(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddL(this.w, otherW);
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
    public Long4 satMul(Long4R other, @Mutated Long4 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulL(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulL(this.w, otherW);
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
    public Long4 satNegate(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegL(this.x);
        d.y = org.joml2.SaturatingMath.satNegL(this.y);
        d.z = org.joml2.SaturatingMath.satNegL(this.z);
        d.w = org.joml2.SaturatingMath.satNegL(this.w);
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
    public Long4 satSub(Long4R other, @Mutated Long4 dest) {
        long otherX = other.x();
        long otherY = other.y();
        long otherZ = other.z();
        long otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubL(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubL(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubL(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubL(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubL(this.w, otherW);
        return d;
    }

    public long x() { return this.x; }
    public long y() { return this.y; }
    public long z() { return this.z; }
    public long w() { return this.w; }

    public Long2 xx(@Mutated Long2 dest) {
        long _v0 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Long2 xy(@Mutated Long2 dest) {
        long _v1 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Long2 xz(@Mutated Long2 dest) {
        long _v1 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Long2 xw(@Mutated Long2 dest) {
        long _v1 = this.w;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Long2 yx(@Mutated Long2 dest) {
        long _v1 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.y;
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
        long _v1 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Long2 yw(@Mutated Long2 dest) {
        long _v1 = this.w;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Long2 zx(@Mutated Long2 dest) {
        long _v1 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Long2 zy(@Mutated Long2 dest) {
        long _v1 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.z;
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

    public Long2 zw(@Mutated Long2 dest) {
        long _v1 = this.w;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Long2 wx(@Mutated Long2 dest) {
        long _v1 = this.x;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Long2 wy(@Mutated Long2 dest) {
        long _v1 = this.y;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Long2 wz(@Mutated Long2 dest) {
        long _v1 = this.z;
        Long2Impl d = (Long2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Long2 ww(@Mutated Long2 dest) {
        long _v0 = this.w;
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

    public Long3 xxw(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
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
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 xyz(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xyw(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
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
        long _v1 = this.z;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xzz(@Mutated Long3 dest) {
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 xzw(@Mutated Long3 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xwx(@Mutated Long3 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 xwy(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xwz(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 xww(@Mutated Long3 dest) {
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 yxx(@Mutated Long3 dest) {
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
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
        long _v1 = this.x;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 yxw(@Mutated Long3 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
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

    public Long3 yyw(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 yzx(@Mutated Long3 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
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
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 yzw(@Mutated Long3 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 ywx(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 ywy(@Mutated Long3 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 ywz(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 yww(@Mutated Long3 dest) {
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 zxx(@Mutated Long3 dest) {
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 zxy(@Mutated Long3 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
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

    public Long3 zxw(@Mutated Long3 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zyx(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zyy(@Mutated Long3 dest) {
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
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

    public Long3 zyw(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
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

    public Long3 zzw(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 zwx(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zwy(@Mutated Long3 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 zwz(@Mutated Long3 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 zww(@Mutated Long3 dest) {
        long _v1 = this.w;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 wxx(@Mutated Long3 dest) {
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 wxy(@Mutated Long3 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wxz(@Mutated Long3 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wxw(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 wyx(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wyy(@Mutated Long3 dest) {
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 wyz(@Mutated Long3 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wyw(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 wzx(@Mutated Long3 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wzy(@Mutated Long3 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Long3 wzz(@Mutated Long3 dest) {
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Long3 wzw(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Long3 wwx(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 wwy(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 wwz(@Mutated Long3 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long3Impl d = (Long3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Long3 www(@Mutated Long3 dest) {
        long _v0 = this.w;
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

    public Long4 xxxw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
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

    public Long4 xxyw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.w;
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

    public Long4 xxzw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xxwx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xxwy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xxwz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xxww(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
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

    public Long4 xyxw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.w;
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
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 xyyz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xyyw(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
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
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xyzz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xyzw(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 xywx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xywy(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xywz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 xyww(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
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

    public Long4 xzxw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
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
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xzyz(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xzyw(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xzzz(@Mutated Long4 dest) {
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 xzzw(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xzwx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xzwy(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 xzwz(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xzww(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xwxx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 xwxy(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 xwxz(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 xwxw(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 xwyx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xwyy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xwyz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 xwyw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xwzx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 xwzy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 xwzz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 xwzw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 xwwx(@Mutated Long4 dest) {
        long _v0 = this.x;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 xwwy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xwwz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 xwww(@Mutated Long4 dest) {
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yxxx(@Mutated Long4 dest) {
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yxxw(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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

    public Long4 yxyw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 yxzx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 yxzw(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 yxwx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 yxwy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 yxwz(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 yxww(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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

    public Long4 yyxw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.x;
        long _v2 = this.w;
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

    public Long4 yyyw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
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

    public Long4 yyzw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yywx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yywy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 yywz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yyww(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yzxx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 yzxw(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Long4 yzyw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 yzzx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
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
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 yzzw(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 yzwx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 yzwy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 yzwz(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 yzww(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 ywxx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 ywxy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 ywxz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 ywxw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 ywyx(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 ywyy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 ywyz(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 ywyw(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 ywzx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 ywzy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 ywzz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 ywzw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 ywwx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 ywwy(@Mutated Long4 dest) {
        long _v0 = this.y;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 ywwz(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 ywww(@Mutated Long4 dest) {
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zxxx(@Mutated Long4 dest) {
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zxxy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
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

    public Long4 zxxw(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zxyx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zxyy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
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

    public Long4 zxyw(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Long4 zxzw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zxwx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zxwy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 zxwz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zxww(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zyxx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zyxy(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
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

    public Long4 zyxw(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        long _v3 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 zyyx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zyyy(@Mutated Long4 dest) {
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
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

    public Long4 zyyw(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
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

    public Long4 zyzw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zywx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 zywy(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zywz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zyww(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
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

    public Long4 zzxw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.x;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
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

    public Long4 zzyw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.y;
        long _v2 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
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

    public Long4 zzzw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zzwx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zzwy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zzwz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zzww(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 zwxx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zwxy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 zwxz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zwxw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zwyx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 zwyy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 zwyz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 zwyw(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 zwzx(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zwzy(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 zwzz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 zwzw(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 zwwx(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zwwy(@Mutated Long4 dest) {
        long _v1 = this.w;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 zwwz(@Mutated Long4 dest) {
        long _v0 = this.z;
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 zwww(@Mutated Long4 dest) {
        long _v1 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wxxx(@Mutated Long4 dest) {
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wxxy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wxxz(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wxxw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wxyx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wxyy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wxyz(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.y;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wxyw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wxzx(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wxzy(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wxzz(@Mutated Long4 dest) {
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wxzw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wxwx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wxwy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wxwz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wxww(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 wyxx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wyxy(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wyxz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        long _v3 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wyxw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wyyx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wyyy(@Mutated Long4 dest) {
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wyyz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wyyw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wyzx(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wyzy(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wyzz(@Mutated Long4 dest) {
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wyzw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wywx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wywy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wywz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wyww(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 wzxx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wzxy(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        long _v3 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wzxz(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wzxw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wzyx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        long _v3 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Long4 wzyy(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Long4 wzyz(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Long4 wzyw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Long4 wzzx(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wzzy(@Mutated Long4 dest) {
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wzzz(@Mutated Long4 dest) {
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wzzw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wzwx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wzwy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Long4 wzwz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wzww(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Long4 wwxx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wwxy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwxz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwxw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wwyx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwyy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wwyz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        long _v2 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwyw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wwzx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwzy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        long _v2 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Long4 wwzz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Long4 wwzw(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Long4 wwwx(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.x;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wwwy(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.y;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wwwz(@Mutated Long4 dest) {
        long _v0 = this.w;
        long _v1 = this.z;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Long4 wwww(@Mutated Long4 dest) {
        long _v0 = this.w;
        Long4Impl d = (Long4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Long4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Long4Impl)) return false;
        Long4Impl o = (Long4Impl) obj;
        return x == o.x
            && y == o.y
            && z == o.z
            && w == o.w;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(x ^ (x >>> 32));
        h = 31 * h + (int)(y ^ (y >>> 32));
        h = 31 * h + (int)(z ^ (z >>> 32));
        h = 31 * h + (int)(w ^ (w >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Long4R other, long epsilon) {
        return epsilon >= 0
            && Long.compareUnsigned(x >= other.x() ? x - other.x() : other.x() - x, epsilon) <= 0
            && Long.compareUnsigned(y >= other.y() ? y - other.y() : other.y() - y, epsilon) <= 0
            && Long.compareUnsigned(z >= other.z() ? z - other.z() : other.z() - z, epsilon) <= 0
            && Long.compareUnsigned(w >= other.w() ? w - other.w() : other.w() - w, epsilon) <= 0;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Long4 load(long[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public LongBuffer store(@Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public LongBuffer storeRelative(@Mutated LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public Long4 load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long4 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long4 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Long4 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Long4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Long4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long4 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Long4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Long4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = (int) this.x;
        dest[offset + 1] = (int) this.y;
        dest[offset + 2] = (int) this.z;
        dest[offset + 3] = (int) this.w;
        return dest;
    }
    public @Mutated Long4 load(int[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public IntBuffer store(@Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public IntBuffer storeRelative(@Mutated IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public Long4 load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Long4 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Long4 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeIntAbsolute(this, index, buf);
    }
    public ByteBuffer storeIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeIntAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public Long4 loadInt(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, buf.position(), buf);
    }
    public Long4 loadIntAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadIntAbsolute(this, index, buf);
    }
    public Long4 loadIntRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Long4 r = StoreLoad.BB_OPS.loadIntAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public Long4 storeIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeIntUnsafe(this, address);
    }
    @Mutated public Long4 loadIntUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadIntUnsafe(this, address);
    }
}
