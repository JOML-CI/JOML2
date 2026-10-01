// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;

/**
 * Generated implementation of {@link Short3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Short3Impl implements Short3 {

    public short x;
    public short y;
    public short z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3BbOpsUnsafe()
                        : new Short3BbOpsApi();
        static final Short3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3RawOpsUnsafe()
                        : new Short3RawOpsApi();
    }

    public Short3Impl() {
    }

    public Short3Impl(short x, short y, short z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Short3Impl(Short3R src) {
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
    public Short3 add(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (other.x() + this.x);
        d.y = (short) (otherY + this.y);
        d.z = (short) (otherZ + this.z);
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Int3 add(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long3 add(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double3 add(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
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
    public Short3 add(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (otherX + this.x);
        d.y = (short) (otherY + this.y);
        d.z = (short) (otherZ + this.z);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 add(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 add(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 add(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 ceilDiv(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = (short) (Math.ceilDiv(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilDiv(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilDiv(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Short3 ceilDiv(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = (short) (Math.ceilDiv(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilDiv(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilDiv(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 ceilDiv(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = (short) (Math.ceilDiv(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilDiv(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilDiv(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
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
    public Short3 ceilMod(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = (short) (Math.ceilMod(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilMod(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilMod(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Short3 ceilMod(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = (short) (Math.ceilMod(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilMod(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilMod(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 ceilMod(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = (short) (Math.ceilMod(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ceilMod(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilMod(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
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
    public Short3 div(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        d.z = (short) (this.z / scalar);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 div(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        d.z = this.z / scalar;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        d.z = this.z / scalar;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
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
    public Short3 div(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        d.z = (short) (this.z / other.z());
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 div(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        d.z = this.z / other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        d.z = this.z / other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        d.z = this.z / other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 div(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        d.z = (short) (this.z / otherZ);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 div(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        d.z = this.z / otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        d.z = this.z / otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
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
    public Short3 floorDiv(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        d.z = (short) (Math.floorDiv(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorDiv(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        d.z = Math.floorDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        d.z = Math.floorDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        d.z = Math.floorDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Short3 floorDiv(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        d.z = (short) (Math.floorDiv(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorDiv(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        d.z = Math.floorDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        d.z = Math.floorDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        d.z = Math.floorDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 floorDiv(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        d.z = (short) (Math.floorDiv(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorDiv(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        d.z = Math.floorDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        d.z = Math.floorDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
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
    public Short3 floorMod(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        d.z = (short) (Math.floorMod(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorMod(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        d.z = Math.floorMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        d.z = Math.floorMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        d.z = Math.floorMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Short3 floorMod(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        d.z = (short) (Math.floorMod(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorMod(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        d.z = Math.floorMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        d.z = Math.floorMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        d.z = Math.floorMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 floorMod(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        d.z = (short) (Math.floorMod(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorMod(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        d.z = Math.floorMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        d.z = Math.floorMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
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
    public Short3 mul(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (scalar * this.x);
        d.y = (short) (scalar * this.y);
        d.z = (short) (scalar * this.z);
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 mul(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
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
    public Short3 mul(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (other.x() * this.x);
        d.y = (short) (otherY * this.y);
        d.z = (short) (otherZ * this.z);
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 mul(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
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
    public Short3 mul(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (otherX * this.x);
        d.y = (short) (otherY * this.y);
        d.z = (short) (otherZ * this.z);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 mul(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 negate(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (-this.x);
        d.y = (short) (-this.y);
        d.z = (short) (-this.z);
        return d;
    }


    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 negate(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        return d;
    }


    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 negate(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 rem(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        d.z = (short) (this.z % scalar);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rem(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        d.z = this.z % scalar;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        d.z = this.z % scalar;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        d.z = this.z % scalar;
        d.x = _buf0;
        d.y = _buf1;
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
    public Short3 rem(Short3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        d.z = (short) (this.z % other.z());
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rem(Short3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        d.z = this.z % other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(Short3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        d.z = this.z % other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(Short3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        d.z = this.z % other.z();
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Short3 rem(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        d.z = (short) (this.z % otherZ);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rem(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        d.z = this.z % otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        d.z = this.z % otherZ;
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
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
    public Short3 sub(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x - other.x());
        d.y = (short) (this.y - otherY);
        d.z = (short) (this.z - otherZ);
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Int3 sub(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sub(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double3 sub(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
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
    public Short3 sub(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x - otherX);
        d.y = (short) (this.y - otherY);
        d.z = (short) (this.z - otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 sub(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sub(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 sub(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 and(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x & other.x());
        d.y = (short) (this.y & otherY);
        d.z = (short) (this.z & otherZ);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Int3 and(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Double3 and(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
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
    public Short3 and(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x & otherX);
        d.y = (short) (this.y & otherY);
        d.z = (short) (this.z & otherZ);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 and(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 and(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 bitCount(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.bitCount(this.x));
        d.y = (short) (Math.bitCount(this.y));
        d.z = (short) (Math.bitCount(this.z));
        return d;
    }


    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 bitCount(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
        d.z = Math.bitCount(this.z);
        return d;
    }


    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
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
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bitCount(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 not(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (~this.x);
        d.y = (short) (~this.y);
        d.z = (short) (~this.z);
        return d;
    }


    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 not(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
        d.z = ~this.z;
        return d;
    }


    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 not(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 numberOfLeadingZeros(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.numberOfLeadingZeros(this.x));
        d.y = (short) (Math.numberOfLeadingZeros(this.y));
        d.z = (short) (Math.numberOfLeadingZeros(this.z));
        return d;
    }


    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 numberOfLeadingZeros(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
        d.z = Math.numberOfLeadingZeros(this.z);
        return d;
    }


    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
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
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 numberOfLeadingZeros(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 numberOfTrailingZeros(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.numberOfTrailingZeros(this.x));
        d.y = (short) (Math.numberOfTrailingZeros(this.y));
        d.z = (short) (Math.numberOfTrailingZeros(this.z));
        return d;
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 numberOfTrailingZeros(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
        d.z = Math.numberOfTrailingZeros(this.z);
        return d;
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
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
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 numberOfTrailingZeros(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 or(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x | other.x());
        d.y = (short) (this.y | otherY);
        d.z = (short) (this.z | otherZ);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Int3 or(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 or(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
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
    public Short3 or(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x | otherX);
        d.y = (short) (this.y | otherY);
        d.z = (short) (this.z | otherZ);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 or(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 or(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 reverseBits(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.reverseBits(this.x));
        d.y = (short) (Math.reverseBits(this.y));
        d.z = (short) (Math.reverseBits(this.z));
        return d;
    }


    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 reverseBits(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
        d.z = Math.reverseBits(this.z);
        return d;
    }


    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
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
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 reverseBits(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 reverseBytes(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.reverseBytes(this.x));
        d.y = (short) (Math.reverseBytes(this.y));
        d.z = (short) (Math.reverseBytes(this.z));
        return d;
    }


    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 reverseBytes(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
        d.z = Math.reverseBytes(this.z);
        return d;
    }


    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
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
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 reverseBytes(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 rotateLeft(short distance, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.rotateLeft(this.x, distance));
        d.y = (short) (Math.rotateLeft(this.y, distance));
        d.z = (short) (Math.rotateLeft(this.z, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rotateLeft(short distance, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateLeft(short distance, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateLeft(short distance, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 rotateRight(short distance, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.rotateRight(this.x, distance));
        d.y = (short) (Math.rotateRight(this.y, distance));
        d.z = (short) (Math.rotateRight(this.z, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rotateRight(short distance, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateRight(short distance, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code short} lane of 16 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateRight(short distance, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short3 shl(short shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x << (shift & 15));
        d.y = (short) (this.y << (shift & 15));
        d.z = (short) (this.z << (shift & 15));
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}. The shift count
     * is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 shl(short shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}. The shift count
     * is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 shl(short shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 16, unlike Java's {@code short} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}. The shift
     * count is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 shl(short shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short3 shr(short shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x >> (shift & 15));
        d.y = (short) (this.y >> (shift & 15));
        d.z = (short) (this.z >> (shift & 15));
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}. The shift count
     * is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 shr(short shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}. The shift count
     * is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 shr(short shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 16, unlike Java's {@code short} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}. The shift
     * count is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 shr(short shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short3 ushr(short shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) ((this.x & 0xFFFF) >>> (shift & 15));
        d.y = (short) ((this.y & 0xFFFF) >>> (shift & 15));
        d.z = (short) ((this.z & 0xFFFF) >>> (shift & 15));
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32) and store the result in
     * {@code dest}.
     * <p>
     * The shift is evaluated on the {@code short} lane of 16 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is
     * {@code 32767}), and each result component is then widened to {@code int}. The shift count is
     * still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 ushr(short shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32) and store the result in
     * {@code dest}.
     * <p>
     * The shift is evaluated on the {@code short} lane of 16 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is
     * {@code 32767}), and each result component is then widened to {@code long}. The shift count is
     * still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ushr(short shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 16 bits of
     * each component are shifted with zeros entering at the top of that lane, and the shift count
     * is taken modulo the lane width of 16 - unlike Java's {@code short} {@code >>>}, which
     * sign-extends to {@code int} first and takes the count modulo 32) and store the result in
     * {@code dest}.
     * <p>
     * The shift is evaluated on the {@code short} lane of 16 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is
     * {@code 32767}), and each result component is then widened to {@code double}. The shift count
     * is still taken modulo this vector's lane width of 16, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ushr(short shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
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
    public Short3 xor(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x ^ other.x());
        d.y = (short) (this.y ^ otherY);
        d.z = (short) (this.z ^ otherZ);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Int3 xor(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 xor(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
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
    public Short3 xor(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x ^ otherX);
        d.y = (short) (this.y ^ otherY);
        d.z = (short) (this.z ^ otherZ);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 xor(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 xor(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public @Mutated Short3 set(Short3R v) {
        short vY = v.y();
        short vZ = v.z();
        this.x = (short) (v.x());
        this.y = (short) (vY);
        this.z = (short) (vZ);
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
    @Mutated public Short3 set(short vX, short vY, short vZ) {
        this.x = (short) (vX);
        this.y = (short) (vY);
        this.z = (short) (vZ);
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
    public Short3 set(short s, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (s);
        d.y = (short) (s);
        d.z = (short) (s);
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Int3 set(short s, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Long3 set(short s, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Double3 set(short s, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        return d;
    }


    /**
     * Convert this vector to {@code float} precision and store the result in {@code dest}.
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
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 toInt(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }


    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 toLong(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Short3 makeZero() {
        this.x = (short) (0);
        this.y = (short) (0);
        this.z = (short) (0);
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
    public Short3 absolute(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.abs(this.x));
        d.y = (short) (java.lang.Math.abs(this.y));
        d.z = (short) (java.lang.Math.abs(this.z));
        return d;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 absolute(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        return d;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 absolute(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        return d;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 absolute(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
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
    public Short3 clamp(short min, short max, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, min), max));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Int3 clamp(short min, short max, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, min), max);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Long3 clamp(short min, short max, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, min), max);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Double3 clamp(short min, short max, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, min), max);
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
    public Short3 clamp(Short3R min, Short3R max, @Mutated Short3 dest) {
        short minY = min.y();
        short minZ = min.z();
        short maxY = max.y();
        short maxZ = max.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Int3 clamp(Short3R min, Short3R max, @Mutated Int3 dest) {
        short minY = min.y();
        short minZ = min.z();
        short maxY = max.y();
        short maxZ = max.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Long3 clamp(Short3R min, Short3R max, @Mutated Long3 dest) {
        short minY = min.y();
        short minZ = min.z();
        short maxY = max.y();
        short maxZ = max.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Double3 clamp(Short3R min, Short3R max, @Mutated Double3 dest) {
        short minY = min.y();
        short minZ = min.z();
        short maxY = max.y();
        short maxZ = max.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
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
    public Short3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double3 clamp(short minX, short minY, short minZ, short maxX, short maxY, short maxZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        return d;
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
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Short3 cross(Short3R other, @Mutated Short3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = (short) (otherZ * _rd1 - otherY * this.z);
        d.y = (short) (otherX * this.z - otherZ * _rd0);
        d.z = (short) (otherY * _rd0 - otherX * _rd1);
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Int3 cross(Short3R other, @Mutated Int3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(Short3R other, @Mutated Long3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(Short3R other, @Mutated Double3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
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
    public Short3 cross(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = (short) (otherZ * _rd1 - otherY * this.z);
        d.y = (short) (otherX * this.z - otherZ * _rd0);
        d.z = (short) (otherY * _rd0 - otherX * _rd1);
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 cross(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
        return d;
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
    public long distanceSquared(Short3R other) {
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
    public long dot(Short3R other) {
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
    public int manhattanDistance(Short3R other) {
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
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Short3 max(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, scalar));
        d.y = (short) (java.lang.Math.max(this.y, scalar));
        d.z = (short) (java.lang.Math.max(this.z, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 max(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
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
    public Short3 max(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, other.x()));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        d.z = (short) (java.lang.Math.max(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 max(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        return d;
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
    public Short3 max(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, otherX));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        d.z = (short) (java.lang.Math.max(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 max(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
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
    public Short3 min(short scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, scalar));
        d.y = (short) (java.lang.Math.min(this.y, scalar));
        d.z = (short) (java.lang.Math.min(this.z, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 min(short scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(short scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(short scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
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
    public Short3 min(Short3R other, @Mutated Short3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, other.x()));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        d.z = (short) (java.lang.Math.min(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 min(Short3R other, @Mutated Int3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(Short3R other, @Mutated Long3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(Short3R other, @Mutated Double3 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        return d;
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
    public Short3 min(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, otherX));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        d.z = (short) (java.lang.Math.min(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 min(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
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
    public Short3 sign(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (Math.signum(this.x));
        d.y = (short) (Math.signum(this.y));
        d.z = (short) (Math.signum(this.z));
        return d;
    }


    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 sign(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        return d;
    }


    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 sign(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
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
    public Short3 satAdd(Short3R other, @Mutated Short3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ));
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satAdd(Short3R other, @Mutated Int3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satAdd(Short3R other, @Mutated Long3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satAdd(Short3R other, @Mutated Double3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        return d;
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
    public Short3 satAdd(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satAdd(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satAdd(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satAdd(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
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
    public Short3 satMul(Short3R other, @Mutated Short3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ));
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satMul(Short3R other, @Mutated Int3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satMul(Short3R other, @Mutated Long3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satMul(Short3R other, @Mutated Double3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        return d;
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
    public Short3 satMul(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satMul(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satMul(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satMul(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
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
    public Short3 satNegate(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegS(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegS(this.y));
        d.z = (short) (org.joml2.SaturatingMath.satNegS(this.z));
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satNegate(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satNegate(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satNegate(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
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
    public Short3 satSub(Short3R other, @Mutated Short3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ));
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satSub(Short3R other, @Mutated Int3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satSub(Short3R other, @Mutated Long3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satSub(Short3R other, @Mutated Double3 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        return d;
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
    public Short3 satSub(short otherX, short otherY, short otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Int3 satSub(short otherX, short otherY, short otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satSub(short otherX, short otherY, short otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satSub(short otherX, short otherY, short otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
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
    public Short4 xyz0(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
        d.w = (short) (0);
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 xyz0(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 0;
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
        d.w = 0;
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz0(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 0;
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
    public Short4 xyz1(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
        d.w = (short) (1);
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 xyz1(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 1;
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
        d.w = 1;
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz1(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = 1;
        return d;
    }

    public short x() { return this.x; }
    public short y() { return this.y; }
    public short z() { return this.z; }

    public Short2 xx(@Mutated Short2 dest) {
        short _v0 = this.x;
        Short2Impl d = (Short2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Short2 xy(@Mutated Short2 dest) {
        short _v1 = this.y;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Short2 xz(@Mutated Short2 dest) {
        short _v1 = this.z;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Short2 yx(@Mutated Short2 dest) {
        short _v1 = this.x;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Short2 yy(@Mutated Short2 dest) {
        short _v0 = this.y;
        Short2Impl d = (Short2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Short2 yz(@Mutated Short2 dest) {
        short _v1 = this.z;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Short2 zx(@Mutated Short2 dest) {
        short _v1 = this.x;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Short2 zy(@Mutated Short2 dest) {
        short _v1 = this.y;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Short2 zz(@Mutated Short2 dest) {
        short _v0 = this.z;
        Short2Impl d = (Short2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Short3 xxx(@Mutated Short3 dest) {
        short _v0 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Short3 xxy(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 xxz(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 xyx(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 xyy(@Mutated Short3 dest) {
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 xyz(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 xzx(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 xzy(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 xzz(@Mutated Short3 dest) {
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 yxx(@Mutated Short3 dest) {
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 yxy(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 yxz(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 yyx(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 yyy(@Mutated Short3 dest) {
        short _v0 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Short3 yyz(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 yzx(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 yzy(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 yzz(@Mutated Short3 dest) {
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 zxx(@Mutated Short3 dest) {
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 zxy(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 zxz(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 zyx(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 zyy(@Mutated Short3 dest) {
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 zyz(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 zzx(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 zzy(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 zzz(@Mutated Short3 dest) {
        short _v0 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Short4 xxxx(@Mutated Short4 dest) {
        short _v0 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 xxxy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 xxxz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 xxyx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xxyy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 xxyz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xxzx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xxzy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xxzz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 xyxx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 xyxy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 xyxz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 xyyx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xyyy(@Mutated Short4 dest) {
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 xyyz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xyzx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xyzy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xyzz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 xzxx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 xzxy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 xzxz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 xzyx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xzyy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 xzyz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xzzx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xzzy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xzzz(@Mutated Short4 dest) {
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 yxxx(@Mutated Short4 dest) {
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 yxxy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 yxxz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yxyx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 yxyy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 yxyz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 yxzx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 yxzy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 yxzz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 yyxx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 yyxy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 yyxz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yyyx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 yyyy(@Mutated Short4 dest) {
        short _v0 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 yyyz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 yyzx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yyzy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 yyzz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 yzxx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 yzxy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 yzxz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 yzyx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 yzyy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 yzyz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 yzzx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yzzy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 yzzz(@Mutated Short4 dest) {
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zxxx(@Mutated Short4 dest) {
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zxxy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zxxz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zxyx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zxyy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 zxyz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zxzx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zxzy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zxzz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 zyxx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 zyxy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zyxz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zyyx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zyyy(@Mutated Short4 dest) {
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zyyz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zyzx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zyzy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zyzz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 zzxx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zzxy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zzxz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zzyx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zzyy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zzyz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zzzx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zzzy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zzzz(@Mutated Short4 dest) {
        short _v0 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Short3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short3Impl)) return false;
        Short3Impl o = (Short3Impl) obj;
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

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Short3R other, short epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon;
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Short3 load(short[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public ShortBuffer store(@Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ShortBuffer storeRelative(@Mutated ShortBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Short3 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Short3 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Short3 loadRelative(ShortBuffer buf) {
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    public Short3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Short3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Short3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short3 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return r;
    }
    public Short3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Short3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset] = (byte) this.x;
        dest[offset + 1] = (byte) this.y;
        dest[offset + 2] = (byte) this.z;
        return dest;
    }
    public @Mutated Short3 load(byte[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public ByteBuffer storeByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }
    public ByteBuffer storeByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeByteAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    public Short3 loadByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, buf.position(), buf);
    }
    public Short3 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, index, buf);
    }
    public Short3 loadByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short3 r = StoreLoad.BB_OPS.loadByteAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return r;
    }
    public Short3 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }
    @Mutated public Short3 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(this, address);
    }
}
