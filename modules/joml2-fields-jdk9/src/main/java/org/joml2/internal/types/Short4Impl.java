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
 * Generated implementation of {@link Short4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Short4Impl implements Short4 {

    public short x;
    public short y;
    public short z;
    public short w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4BbOpsUnsafe()
                        : new Short4BbOpsApi();
        static final Short4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4RawOpsUnsafe()
                        : new Short4RawOpsApi();
    }

    public Short4Impl() {
        w = 1;
    }

    public Short4Impl(short x, short y, short z, short w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Short4Impl(Short4R src) {
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
    public Short4 add(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (other.x() + this.x);
        d.y = (short) (otherY + this.y);
        d.z = (short) (otherZ + this.z);
        d.w = (short) (otherW + this.w);
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
    public Int4 add(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
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
    public Long4 add(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
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
    public Double4 add(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 add(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (otherX + this.x);
        d.y = (short) (otherY + this.y);
        d.z = (short) (otherZ + this.z);
        d.w = (short) (otherW + this.w);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 add(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 add(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 add(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 ceilDiv(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        int _buf2 = Math.ceilDiv(this.z, scalar);
        d.w = (short) (Math.ceilDiv(this.w, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 ceilDiv(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        int _buf2 = Math.ceilDiv(this.z, scalar);
        d.w = Math.ceilDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 ceilDiv(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        int _buf2 = Math.ceilDiv(this.z, scalar);
        d.w = Math.ceilDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 ceilDiv(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        int _buf2 = Math.ceilDiv(this.z, scalar);
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
    public Short4 ceilDiv(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        int _buf2 = Math.ceilDiv(this.z, other.z());
        d.w = (short) (Math.ceilDiv(this.w, other.w()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 ceilDiv(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        int _buf2 = Math.ceilDiv(this.z, other.z());
        d.w = Math.ceilDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 ceilDiv(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        int _buf2 = Math.ceilDiv(this.z, other.z());
        d.w = Math.ceilDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 ceilDiv(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        int _buf2 = Math.ceilDiv(this.z, other.z());
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
    public Short4 ceilDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        int _buf2 = Math.ceilDiv(this.z, otherZ);
        d.w = (short) (Math.ceilDiv(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 ceilDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        int _buf2 = Math.ceilDiv(this.z, otherZ);
        d.w = Math.ceilDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 ceilDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        int _buf2 = Math.ceilDiv(this.z, otherZ);
        d.w = Math.ceilDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 ceilDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        int _buf2 = Math.ceilDiv(this.z, otherZ);
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
    public Short4 ceilMod(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        int _buf2 = Math.ceilMod(this.z, scalar);
        d.w = (short) (Math.ceilMod(this.w, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 ceilMod(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        int _buf2 = Math.ceilMod(this.z, scalar);
        d.w = Math.ceilMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 ceilMod(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        int _buf2 = Math.ceilMod(this.z, scalar);
        d.w = Math.ceilMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 ceilMod(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        int _buf2 = Math.ceilMod(this.z, scalar);
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
    public Short4 ceilMod(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        int _buf2 = Math.ceilMod(this.z, other.z());
        d.w = (short) (Math.ceilMod(this.w, other.w()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 ceilMod(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        int _buf2 = Math.ceilMod(this.z, other.z());
        d.w = Math.ceilMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 ceilMod(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        int _buf2 = Math.ceilMod(this.z, other.z());
        d.w = Math.ceilMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 ceilMod(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        int _buf2 = Math.ceilMod(this.z, other.z());
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
    public Short4 ceilMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        int _buf2 = Math.ceilMod(this.z, otherZ);
        d.w = (short) (Math.ceilMod(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 ceilMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        int _buf2 = Math.ceilMod(this.z, otherZ);
        d.w = Math.ceilMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 ceilMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        int _buf2 = Math.ceilMod(this.z, otherZ);
        d.w = Math.ceilMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 ceilMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        int _buf2 = Math.ceilMod(this.z, otherZ);
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
    public Short4 div(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        int _buf2 = this.z / scalar;
        d.w = (short) (this.w / scalar);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 div(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        int _buf2 = this.z / scalar;
        d.w = this.w / scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 div(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        int _buf2 = this.z / scalar;
        d.w = this.w / scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 div(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        int _buf2 = this.z / scalar;
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
    public Short4 div(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        int _buf2 = this.z / other.z();
        d.w = (short) (this.w / other.w());
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 div(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        int _buf2 = this.z / other.z();
        d.w = this.w / other.w();
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 div(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        int _buf2 = this.z / other.z();
        d.w = this.w / other.w();
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 div(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        int _buf2 = this.z / other.z();
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
    public Short4 div(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        int _buf2 = this.z / otherZ;
        d.w = (short) (this.w / otherW);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) (integer division, truncating toward zero) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 div(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        int _buf2 = this.z / otherZ;
        d.w = this.w / otherW;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 div(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        int _buf2 = this.z / otherZ;
        d.w = this.w / otherW;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 div(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        int _buf2 = this.z / otherZ;
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
    public Short4 floorDiv(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        int _buf2 = Math.floorDiv(this.z, scalar);
        d.w = (short) (Math.floorDiv(this.w, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 floorDiv(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        int _buf2 = Math.floorDiv(this.z, scalar);
        d.w = Math.floorDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorDiv(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        int _buf2 = Math.floorDiv(this.z, scalar);
        d.w = Math.floorDiv(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 floorDiv(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        int _buf2 = Math.floorDiv(this.z, scalar);
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
    public Short4 floorDiv(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        int _buf2 = Math.floorDiv(this.z, other.z());
        d.w = (short) (Math.floorDiv(this.w, other.w()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 floorDiv(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        int _buf2 = Math.floorDiv(this.z, other.z());
        d.w = Math.floorDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorDiv(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        int _buf2 = Math.floorDiv(this.z, other.z());
        d.w = Math.floorDiv(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 floorDiv(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        int _buf2 = Math.floorDiv(this.z, other.z());
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
    public Short4 floorDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        int _buf2 = Math.floorDiv(this.z, otherZ);
        d.w = (short) (Math.floorDiv(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 floorDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        int _buf2 = Math.floorDiv(this.z, otherZ);
        d.w = Math.floorDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 floorDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        int _buf2 = Math.floorDiv(this.z, otherZ);
        d.w = Math.floorDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 floorDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        int _buf2 = Math.floorDiv(this.z, otherZ);
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
    public Short4 floorMod(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        int _buf2 = Math.floorMod(this.z, scalar);
        d.w = (short) (Math.floorMod(this.w, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 floorMod(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        int _buf2 = Math.floorMod(this.z, scalar);
        d.w = Math.floorMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorMod(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        int _buf2 = Math.floorMod(this.z, scalar);
        d.w = Math.floorMod(this.w, scalar);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 floorMod(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        int _buf2 = Math.floorMod(this.z, scalar);
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
    public Short4 floorMod(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        int _buf2 = Math.floorMod(this.z, other.z());
        d.w = (short) (Math.floorMod(this.w, other.w()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 floorMod(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        int _buf2 = Math.floorMod(this.z, other.z());
        d.w = Math.floorMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 floorMod(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        int _buf2 = Math.floorMod(this.z, other.z());
        d.w = Math.floorMod(this.w, other.w());
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 floorMod(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        int _buf2 = Math.floorMod(this.z, other.z());
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
    public Short4 floorMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        int _buf2 = Math.floorMod(this.z, otherZ);
        d.w = (short) (Math.floorMod(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 floorMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        int _buf2 = Math.floorMod(this.z, otherZ);
        d.w = Math.floorMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 floorMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        int _buf2 = Math.floorMod(this.z, otherZ);
        d.w = Math.floorMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 floorMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        int _buf2 = Math.floorMod(this.z, otherZ);
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
    public Short4 mul(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (scalar * this.x);
        d.y = (short) (scalar * this.y);
        d.z = (short) (scalar * this.z);
        d.w = (short) (scalar * this.w);
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
    public Int4 mul(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
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
    public Long4 mul(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
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
    public Double4 mul(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 mul(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (other.x() * this.x);
        d.y = (short) (otherY * this.y);
        d.z = (short) (otherZ * this.z);
        d.w = (short) (otherW * this.w);
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
    public Int4 mul(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
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
    public Long4 mul(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
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
    public Double4 mul(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 mul(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (otherX * this.x);
        d.y = (short) (otherY * this.y);
        d.z = (short) (otherZ * this.z);
        d.w = (short) (otherW * this.w);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 mul(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 mul(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        d.w = otherW * this.w;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 mul(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 negate(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (-this.x);
        d.y = (short) (-this.y);
        d.z = (short) (-this.z);
        d.w = (short) (-this.w);
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
    public Int4 negate(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
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
    public Long4 negate(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
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
    public Double4 negate(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 rem(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        int _buf2 = this.z % scalar;
        d.w = (short) (this.w % scalar);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 rem(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        int _buf2 = this.z % scalar;
        d.w = this.w % scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Long4 rem(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        int _buf2 = this.z % scalar;
        d.w = this.w % scalar;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
    public Double4 rem(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        int _buf2 = this.z % scalar;
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
    public Short4 rem(Short4R other, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        int _buf2 = this.z % other.z();
        d.w = (short) (this.w % other.w());
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
    public Int4 rem(Short4R other, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        int _buf2 = this.z % other.z();
        d.w = this.w % other.w();
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rem(Short4R other, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        int _buf2 = this.z % other.z();
        d.w = this.w % other.w();
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rem(Short4R other, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        int _buf2 = this.z % other.z();
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
    public Short4 rem(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        int _buf2 = this.z % otherZ;
        d.w = (short) (this.w % otherW);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) (the remainder carries the sign of the
     * dividend, exactly Java's {@code %}, so it pairs with {@code div}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 rem(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        int _buf2 = this.z % otherZ;
        d.w = this.w % otherW;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 rem(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        int _buf2 = this.z % otherZ;
        d.w = this.w % otherW;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 rem(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        int _buf2 = this.z % otherZ;
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
    public Short4 sub(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x - other.x());
        d.y = (short) (this.y - otherY);
        d.z = (short) (this.z - otherZ);
        d.w = (short) (this.w - otherW);
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
    public Int4 sub(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
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
    public Long4 sub(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
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
    public Double4 sub(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 sub(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x - otherX);
        d.y = (short) (this.y - otherY);
        d.z = (short) (this.z - otherZ);
        d.w = (short) (this.w - otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 sub(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 sub(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 sub(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 and(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x & other.x());
        d.y = (short) (this.y & otherY);
        d.z = (short) (this.z & otherZ);
        d.w = (short) (this.w & otherW);
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
    public Int4 and(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
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
    public Long4 and(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
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
    public Double4 and(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 and(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x & otherX);
        d.y = (short) (this.y & otherY);
        d.z = (short) (this.z & otherZ);
        d.w = (short) (this.w & otherW);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 and(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 and(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        d.z = this.z & otherZ;
        d.w = this.w & otherW;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 and(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 bitCount(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.bitCount(this.x));
        d.y = (short) (Math.bitCount(this.y));
        d.z = (short) (Math.bitCount(this.z));
        d.w = (short) (Math.bitCount(this.w));
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
    public Int4 bitCount(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
        d.z = Math.bitCount(this.z);
        d.w = Math.bitCount(this.w);
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
    public Long4 bitCount(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
        d.z = Math.bitCount(this.z);
        d.w = Math.bitCount(this.w);
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
    public Double4 bitCount(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 not(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (~this.x);
        d.y = (short) (~this.y);
        d.z = (short) (~this.z);
        d.w = (short) (~this.w);
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
    public Int4 not(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
        d.z = ~this.z;
        d.w = ~this.w;
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
    public Long4 not(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
        d.z = ~this.z;
        d.w = ~this.w;
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
    public Double4 not(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 numberOfLeadingZeros(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.numberOfLeadingZeros(this.x));
        d.y = (short) (Math.numberOfLeadingZeros(this.y));
        d.z = (short) (Math.numberOfLeadingZeros(this.z));
        d.w = (short) (Math.numberOfLeadingZeros(this.w));
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
    public Int4 numberOfLeadingZeros(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
        d.z = Math.numberOfLeadingZeros(this.z);
        d.w = Math.numberOfLeadingZeros(this.w);
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
    public Long4 numberOfLeadingZeros(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
        d.z = Math.numberOfLeadingZeros(this.z);
        d.w = Math.numberOfLeadingZeros(this.w);
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
    public Double4 numberOfLeadingZeros(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 numberOfTrailingZeros(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.numberOfTrailingZeros(this.x));
        d.y = (short) (Math.numberOfTrailingZeros(this.y));
        d.z = (short) (Math.numberOfTrailingZeros(this.z));
        d.w = (short) (Math.numberOfTrailingZeros(this.w));
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
    public Int4 numberOfTrailingZeros(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
        d.z = Math.numberOfTrailingZeros(this.z);
        d.w = Math.numberOfTrailingZeros(this.w);
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
    public Long4 numberOfTrailingZeros(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
        d.z = Math.numberOfTrailingZeros(this.z);
        d.w = Math.numberOfTrailingZeros(this.w);
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
    public Double4 numberOfTrailingZeros(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 or(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x | other.x());
        d.y = (short) (this.y | otherY);
        d.z = (short) (this.z | otherZ);
        d.w = (short) (this.w | otherW);
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
    public Int4 or(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
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
    public Long4 or(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
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
    public Double4 or(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 or(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x | otherX);
        d.y = (short) (this.y | otherY);
        d.z = (short) (this.z | otherZ);
        d.w = (short) (this.w | otherW);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 or(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 or(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        d.z = this.z | otherZ;
        d.w = this.w | otherW;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 or(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 reverseBits(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.reverseBits(this.x));
        d.y = (short) (Math.reverseBits(this.y));
        d.z = (short) (Math.reverseBits(this.z));
        d.w = (short) (Math.reverseBits(this.w));
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
    public Int4 reverseBits(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
        d.z = Math.reverseBits(this.z);
        d.w = Math.reverseBits(this.w);
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
    public Long4 reverseBits(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
        d.z = Math.reverseBits(this.z);
        d.w = Math.reverseBits(this.w);
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
    public Double4 reverseBits(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 reverseBytes(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.reverseBytes(this.x));
        d.y = (short) (Math.reverseBytes(this.y));
        d.z = (short) (Math.reverseBytes(this.z));
        d.w = (short) (Math.reverseBytes(this.w));
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
    public Int4 reverseBytes(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
        d.z = Math.reverseBytes(this.z);
        d.w = Math.reverseBytes(this.w);
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
    public Long4 reverseBytes(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
        d.z = Math.reverseBytes(this.z);
        d.w = Math.reverseBytes(this.w);
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
    public Double4 reverseBytes(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 rotateLeft(short distance, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.rotateLeft(this.x, distance));
        d.y = (short) (Math.rotateLeft(this.y, distance));
        d.z = (short) (Math.rotateLeft(this.z, distance));
        d.w = (short) (Math.rotateLeft(this.w, distance));
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
    public Int4 rotateLeft(short distance, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        d.w = Math.rotateLeft(this.w, distance);
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
    public Long4 rotateLeft(short distance, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        d.z = Math.rotateLeft(this.z, distance);
        d.w = Math.rotateLeft(this.w, distance);
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
    public Double4 rotateLeft(short distance, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 rotateRight(short distance, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.rotateRight(this.x, distance));
        d.y = (short) (Math.rotateRight(this.y, distance));
        d.z = (short) (Math.rotateRight(this.z, distance));
        d.w = (short) (Math.rotateRight(this.w, distance));
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
    public Int4 rotateRight(short distance, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
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
    public Long4 rotateRight(short distance, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
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
    public Double4 rotateRight(short distance, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
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
    public Short4 shl(short shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x << (shift & 15));
        d.y = (short) (this.y << (shift & 15));
        d.z = (short) (this.z << (shift & 15));
        d.w = (short) (this.w << (shift & 15));
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
    public Int4 shl(short shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        d.w = this.w << (shift & 15);
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
    public Long4 shl(short shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        d.w = this.w << (shift & 15);
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
    public Double4 shl(short shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
        d.z = this.z << (shift & 15);
        d.w = this.w << (shift & 15);
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
    public Short4 shr(short shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x >> (shift & 15));
        d.y = (short) (this.y >> (shift & 15));
        d.z = (short) (this.z >> (shift & 15));
        d.w = (short) (this.w >> (shift & 15));
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
    public Int4 shr(short shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        d.w = this.w >> (shift & 15);
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
    public Long4 shr(short shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        d.w = this.w >> (shift & 15);
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
    public Double4 shr(short shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
        d.z = this.z >> (shift & 15);
        d.w = this.w >> (shift & 15);
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
    public Short4 ushr(short shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) ((this.x & 0xFFFF) >>> (shift & 15));
        d.y = (short) ((this.y & 0xFFFF) >>> (shift & 15));
        d.z = (short) ((this.z & 0xFFFF) >>> (shift & 15));
        d.w = (short) ((this.w & 0xFFFF) >>> (shift & 15));
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
    public Int4 ushr(short shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
        d.w = (this.w & 0xFFFF) >>> (shift & 15);
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
    public Long4 ushr(short shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
        d.w = (this.w & 0xFFFF) >>> (shift & 15);
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
    public Double4 ushr(short shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
        d.z = (this.z & 0xFFFF) >>> (shift & 15);
        d.w = (this.w & 0xFFFF) >>> (shift & 15);
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
    public Short4 xor(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x ^ other.x());
        d.y = (short) (this.y ^ otherY);
        d.z = (short) (this.z ^ otherZ);
        d.w = (short) (this.w ^ otherW);
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
    public Int4 xor(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
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
    public Long4 xor(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
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
    public Double4 xor(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 xor(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x ^ otherX);
        d.y = (short) (this.y ^ otherY);
        d.z = (short) (this.z ^ otherZ);
        d.w = (short) (this.w ^ otherW);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 xor(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 xor(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        d.z = this.z ^ otherZ;
        d.w = this.w ^ otherW;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 xor(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public @Mutated Short4 set(Short4R v) {
        short vY = v.y();
        short vZ = v.z();
        short vW = v.w();
        this.x = (short) (v.x());
        this.y = (short) (vY);
        this.z = (short) (vZ);
        this.w = (short) (vW);
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
    @Mutated public Short4 set(short vX, short vY, short vZ, short vW) {
        this.x = (short) (vX);
        this.y = (short) (vY);
        this.z = (short) (vZ);
        this.w = (short) (vW);
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
    public Short4 set(short s, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (s);
        d.y = (short) (s);
        d.z = (short) (s);
        d.w = (short) (s);
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
    public Int4 set(short s, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
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
    public Long4 set(short s, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
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
    public Double4 set(short s, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
        d.w = s;
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
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 toInt(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = this.w;
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
    public Long4 toLong(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = this.w;
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Short4 makeZero() {
        this.x = (short) (0);
        this.y = (short) (0);
        this.z = (short) (0);
        this.w = (short) (0);
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
    public Short4 absolute(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.abs(this.x));
        d.y = (short) (java.lang.Math.abs(this.y));
        d.z = (short) (java.lang.Math.abs(this.z));
        d.w = (short) (java.lang.Math.abs(this.w));
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
    public Int4 absolute(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        d.w = java.lang.Math.abs(this.w);
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
    public Long4 absolute(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
        d.z = java.lang.Math.abs(this.z);
        d.w = java.lang.Math.abs(this.w);
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
    public Double4 absolute(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 clamp(short min, short max, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, min), max));
        d.w = (short) (java.lang.Math.min(java.lang.Math.max(this.w, min), max));
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
    public Int4 clamp(short min, short max, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
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
    public Long4 clamp(short min, short max, @Mutated Long4 dest) {
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
    public Double4 clamp(short min, short max, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 clamp(Short4R min, Short4R max, @Mutated Short4 dest) {
        short minY = min.y();
        short minZ = min.z();
        short minW = min.w();
        short maxY = max.y();
        short maxZ = max.z();
        short maxW = max.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
        d.w = (short) (java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW));
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
    public Int4 clamp(Short4R min, Short4R max, @Mutated Int4 dest) {
        short minY = min.y();
        short minZ = min.z();
        short minW = min.w();
        short maxY = max.y();
        short maxZ = max.z();
        short maxW = max.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW);
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
    public Long4 clamp(Short4R min, Short4R max, @Mutated Long4 dest) {
        short minY = min.y();
        short minZ = min.z();
        short minW = min.w();
        short maxY = max.y();
        short maxZ = max.z();
        short maxW = max.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW);
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
    public Double4 clamp(Short4R min, Short4R max, @Mutated Double4 dest) {
        short minY = min.y();
        short minZ = min.z();
        short minW = min.w();
        short maxY = max.y();
        short maxZ = max.z();
        short maxW = max.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (short) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
        d.w = (short) (java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
        d.w = java.lang.Math.min(java.lang.Math.max(this.w, minW), maxW);
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
        return (short) (java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z), this.w));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public short compMin() {
        return (short) (java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z), this.w));
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
        return (long) this.w * (long) this.z * (long) this.x * this.y;
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
    public Short4 cross(Short4R v, Short4R w, @Mutated Short4 dest) {
        short vX = v.x();
        short vY = v.y();
        short vZ = v.z();
        short vW = v.w();
        short wX = w.x();
        short wY = w.y();
        short wZ = w.z();
        short wW = w.w();
        Short4Impl d = (Short4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _rd0 = this.x;
        int _rd1 = this.y;
        int _rd2 = this.z;
        d.x = (short) (_rd1 * _t12 - _rd2 * _t13 + this.w * _t14);
        d.y = (short) (_rd2 * _t15 - _rd0 * _t12 - this.w * _t16);
        d.z = (short) (_rd0 * _t13 - _rd1 * _t15 + this.w * _t17);
        d.w = (short) (_rd1 * _t16 - _rd0 * _t14 - _rd2 * _t17);
        return d;
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Int4 cross(Short4R v, Short4R w, @Mutated Int4 dest) {
        short vX = v.x();
        short vY = v.y();
        short vZ = v.z();
        short vW = v.w();
        short wX = w.x();
        short wY = w.y();
        short wZ = w.z();
        short wW = w.w();
        Int4Impl d = (Int4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
        return d;
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long4 cross(Short4R v, Short4R w, @Mutated Long4 dest) {
        short vX = v.x();
        short vY = v.y();
        short vZ = v.z();
        short vW = v.w();
        short wX = w.x();
        short wY = w.y();
        short wZ = w.z();
        short wW = w.w();
        Long4Impl d = (Long4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
        return d;
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cross(Short4R v, Short4R w, @Mutated Double4 dest) {
        short vX = v.x();
        short vY = v.y();
        short vZ = v.z();
        short vW = v.w();
        short wX = w.x();
        short wY = w.y();
        short wZ = w.z();
        short wW = w.w();
        Double4Impl d = (Double4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
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
    public Short4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _rd0 = this.x;
        int _rd1 = this.y;
        int _rd2 = this.z;
        d.x = (short) (_rd1 * _t12 - _rd2 * _t13 + this.w * _t14);
        d.y = (short) (_rd2 * _t15 - _rd0 * _t12 - this.w * _t16);
        d.z = (short) (_rd0 * _t13 - _rd1 * _t15 + this.w * _t17);
        d.w = (short) (_rd1 * _t16 - _rd0 * _t14 - _rd2 * _t17);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        d.x = this.y * _t12 - this.z * _t13 + this.w * _t14;
        d.y = this.z * _t15 - this.x * _t12 - this.w * _t16;
        d.z = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
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
    public long distanceSquared(Short4R other) {
        long _t0 = (long) this.x - other.x();
        long _t1 = (long) this.y - other.y();
        long _t2 = (long) this.z - other.z();
        long _t3 = (long) this.w - other.w();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
        long _t0 = (long) this.x - otherX;
        long _t1 = (long) this.y - otherY;
        long _t2 = (long) this.z - otherZ;
        long _t3 = (long) this.w - otherW;
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
    public long dot(Short4R other) {
        return (long) other.x() * this.x + (long) other.y() * this.y + (long) other.z() * this.z + (long) other.w() * this.w;
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
        return (long) otherX * this.x + (long) otherY * this.y + (long) otherZ * this.z + (long) otherW * this.w;
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
        return (long) this.x * this.x + (long) this.y * this.y + (long) this.z * this.z + (long) this.w * this.w;
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
    public int manhattanDistance(Short4R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z()) + java.lang.Math.abs(this.w - other.w());
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
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ) + java.lang.Math.abs(this.w - otherW);
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
    public Short4 max(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, scalar));
        d.y = (short) (java.lang.Math.max(this.y, scalar));
        d.z = (short) (java.lang.Math.max(this.z, scalar));
        d.w = (short) (java.lang.Math.max(this.w, scalar));
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
    public Int4 max(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
        d.w = java.lang.Math.max(this.w, scalar);
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
    public Long4 max(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        d.z = java.lang.Math.max(this.z, scalar);
        d.w = java.lang.Math.max(this.w, scalar);
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
    public Double4 max(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 max(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, other.x()));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        d.z = (short) (java.lang.Math.max(this.z, otherZ));
        d.w = (short) (java.lang.Math.max(this.w, otherW));
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
    public Int4 max(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        d.w = java.lang.Math.max(this.w, otherW);
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
    public Long4 max(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        d.z = java.lang.Math.max(this.z, otherZ);
        d.w = java.lang.Math.max(this.w, otherW);
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
    public Double4 max(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 max(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, otherX));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        d.z = (short) (java.lang.Math.max(this.z, otherZ));
        d.w = (short) (java.lang.Math.max(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 max(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 max(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 max(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 min(short scalar, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, scalar));
        d.y = (short) (java.lang.Math.min(this.y, scalar));
        d.z = (short) (java.lang.Math.min(this.z, scalar));
        d.w = (short) (java.lang.Math.min(this.w, scalar));
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
    public Int4 min(short scalar, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
        d.w = java.lang.Math.min(this.w, scalar);
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
    public Long4 min(short scalar, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        d.z = java.lang.Math.min(this.z, scalar);
        d.w = java.lang.Math.min(this.w, scalar);
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
    public Double4 min(short scalar, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 min(Short4R other, @Mutated Short4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, other.x()));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        d.z = (short) (java.lang.Math.min(this.z, otherZ));
        d.w = (short) (java.lang.Math.min(this.w, otherW));
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
    public Int4 min(Short4R other, @Mutated Int4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        d.w = java.lang.Math.min(this.w, otherW);
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
    public Long4 min(Short4R other, @Mutated Long4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        d.z = java.lang.Math.min(this.z, otherZ);
        d.w = java.lang.Math.min(this.w, otherW);
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
    public Double4 min(Short4R other, @Mutated Double4 dest) {
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 min(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, otherX));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        d.z = (short) (java.lang.Math.min(this.z, otherZ));
        d.w = (short) (java.lang.Math.min(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int4 min(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long4 min(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
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
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double4 min(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 sign(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.signum(this.x));
        d.y = (short) (Math.signum(this.y));
        d.z = (short) (Math.signum(this.z));
        d.w = (short) (Math.signum(this.w));
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
    public Int4 sign(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        d.w = Math.signum(this.w);
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
    public Long4 sign(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
        d.z = Math.signum(this.z);
        d.w = Math.signum(this.w);
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
    public Double4 sign(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
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
    public Short4 satAdd(Short4R other, @Mutated Short4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satAddS(this.w, otherW));
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
    public Int4 satAdd(Short4R other, @Mutated Int4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
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
    public Long4 satAdd(Short4R other, @Mutated Long4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
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
    public Double4 satAdd(Short4R other, @Mutated Double4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
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
    public Short4 satAdd(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satAddS(this.w, otherW));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
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
    public Int4 satAdd(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
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
    public Long4 satAdd(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
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
    public Double4 satAdd(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddS(this.w, otherW);
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
    public Short4 satMul(Short4R other, @Mutated Short4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satMulS(this.w, otherW));
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
    public Int4 satMul(Short4R other, @Mutated Int4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
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
    public Long4 satMul(Short4R other, @Mutated Long4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
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
    public Double4 satMul(Short4R other, @Mutated Double4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
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
    public Short4 satMul(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satMulS(this.w, otherW));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
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
    public Int4 satMul(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
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
    public Long4 satMul(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
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
    public Double4 satMul(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulS(this.w, otherW);
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
    public Short4 satNegate(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegS(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegS(this.y));
        d.z = (short) (org.joml2.SaturatingMath.satNegS(this.z));
        d.w = (short) (org.joml2.SaturatingMath.satNegS(this.w));
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
    public Int4 satNegate(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
        d.w = org.joml2.SaturatingMath.satNegS(this.w);
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
    public Long4 satNegate(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
        d.w = org.joml2.SaturatingMath.satNegS(this.w);
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
    public Double4 satNegate(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
        d.z = org.joml2.SaturatingMath.satNegS(this.z);
        d.w = org.joml2.SaturatingMath.satNegS(this.w);
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
    public Short4 satSub(Short4R other, @Mutated Short4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satSubS(this.w, otherW));
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
    public Int4 satSub(Short4R other, @Mutated Int4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
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
    public Long4 satSub(Short4R other, @Mutated Long4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
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
    public Double4 satSub(Short4R other, @Mutated Double4 dest) {
        short otherX = other.x();
        short otherY = other.y();
        short otherZ = other.z();
        short otherW = other.w();
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
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
    public Short4 satSub(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubS(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satSubS(this.w, otherW));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
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
    public Int4 satSub(short otherX, short otherY, short otherZ, short otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
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
    public Long4 satSub(short otherX, short otherY, short otherZ, short otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
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
    public Double4 satSub(short otherX, short otherY, short otherZ, short otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubS(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubS(this.w, otherW);
        return d;
    }

    public short x() { return this.x; }
    public short y() { return this.y; }
    public short z() { return this.z; }
    public short w() { return this.w; }

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

    public Short2 xw(@Mutated Short2 dest) {
        short _v1 = this.w;
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

    public Short2 yw(@Mutated Short2 dest) {
        short _v1 = this.w;
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

    public Short2 zw(@Mutated Short2 dest) {
        short _v1 = this.w;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Short2 wx(@Mutated Short2 dest) {
        short _v1 = this.x;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Short2 wy(@Mutated Short2 dest) {
        short _v1 = this.y;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Short2 wz(@Mutated Short2 dest) {
        short _v1 = this.z;
        Short2Impl d = (Short2Impl) dest;
        d.x = this.w;
        d.y = _v1;
        return dest;
    }

    public Short2 ww(@Mutated Short2 dest) {
        short _v0 = this.w;
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

    public Short3 xxw(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
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

    public Short3 xyw(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
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

    public Short3 xzw(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 xwx(@Mutated Short3 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 xwy(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 xwz(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 xww(@Mutated Short3 dest) {
        short _v1 = this.w;
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

    public Short3 yxw(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
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

    public Short3 yyw(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
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

    public Short3 yzw(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 ywx(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 ywy(@Mutated Short3 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 ywz(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 yww(@Mutated Short3 dest) {
        short _v1 = this.w;
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

    public Short3 zxw(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
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

    public Short3 zyw(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
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

    public Short3 zzw(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 zwx(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 zwy(@Mutated Short3 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 zwz(@Mutated Short3 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 zww(@Mutated Short3 dest) {
        short _v1 = this.w;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 wxx(@Mutated Short3 dest) {
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 wxy(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wxz(@Mutated Short3 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wxw(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 wyx(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wyy(@Mutated Short3 dest) {
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 wyz(@Mutated Short3 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wyw(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 wzx(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wzy(@Mutated Short3 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Short3 wzz(@Mutated Short3 dest) {
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Short3 wzw(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Short3 wwx(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 wwy(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 wwz(@Mutated Short3 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short3Impl d = (Short3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Short3 www(@Mutated Short3 dest) {
        short _v0 = this.w;
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

    public Short4 xxxw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
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

    public Short4 xxyw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.w;
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

    public Short4 xxzw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xxwx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xxwy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xxwz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xxww(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
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

    public Short4 xyxw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.w;
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

    public Short4 xyyw(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
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

    public Short4 xyzw(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 xywx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xywy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xywz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 xyww(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
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

    public Short4 xzxw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
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

    public Short4 xzyw(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Short4 xzzw(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xzwx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xzwy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 xzwz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xzww(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 xwxx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 xwxy(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 xwxz(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 xwxw(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 xwyx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xwyy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 xwyz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 xwyw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xwzx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 xwzy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 xwzz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 xwzw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 xwwx(@Mutated Short4 dest) {
        short _v0 = this.x;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 xwwy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xwwz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 xwww(@Mutated Short4 dest) {
        short _v1 = this.w;
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

    public Short4 yxxw(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
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

    public Short4 yxyw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.w;
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

    public Short4 yxzw(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 yxwx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 yxwy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 yxwz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 yxww(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
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

    public Short4 yyxw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.x;
        short _v2 = this.w;
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

    public Short4 yyyw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
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

    public Short4 yyzw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yywx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yywy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 yywz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yyww(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
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

    public Short4 yzxw(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Short4 yzyw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
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

    public Short4 yzzw(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 yzwx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 yzwy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 yzwz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 yzww(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 ywxx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 ywxy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 ywxz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 ywxw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 ywyx(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 ywyy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 ywyz(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 ywyw(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 ywzx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 ywzy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 ywzz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 ywzw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 ywwx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 ywwy(@Mutated Short4 dest) {
        short _v0 = this.y;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 ywwz(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 ywww(@Mutated Short4 dest) {
        short _v1 = this.w;
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

    public Short4 zxxw(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
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

    public Short4 zxyw(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Short4 zxzw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zxwx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zxwy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 zxwz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zxww(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
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

    public Short4 zyxw(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        short _v3 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
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

    public Short4 zyyw(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
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

    public Short4 zyzw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zywx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 zywy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zywz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zyww(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
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

    public Short4 zzxw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.x;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
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

    public Short4 zzyw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.y;
        short _v2 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
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

    public Short4 zzzw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zzwx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zzwy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zzwz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zzww(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 zwxx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 zwxy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 zwxz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zwxw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zwyx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 zwyy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 zwyz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 zwyw(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 zwzx(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zwzy(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 zwzz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 zwzw(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 zwwx(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zwwy(@Mutated Short4 dest) {
        short _v1 = this.w;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 zwwz(@Mutated Short4 dest) {
        short _v0 = this.z;
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 zwww(@Mutated Short4 dest) {
        short _v1 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wxxx(@Mutated Short4 dest) {
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wxxy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wxxz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wxxw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wxyx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wxyy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wxyz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.y;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wxyw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wxzx(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wxzy(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wxzz(@Mutated Short4 dest) {
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wxzw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wxwx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wxwy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wxwz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wxww(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 wyxx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wyxy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wyxz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        short _v3 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wyxw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wyyx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wyyy(@Mutated Short4 dest) {
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wyyz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wyyw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wyzx(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wyzy(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wyzz(@Mutated Short4 dest) {
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wyzw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wywx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wywy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wywz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wyww(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 wzxx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wzxy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        short _v3 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wzxz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wzxw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wzyx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        short _v3 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Short4 wzyy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Short4 wzyz(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Short4 wzyw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Short4 wzzx(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wzzy(@Mutated Short4 dest) {
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wzzz(@Mutated Short4 dest) {
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = this.w;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wzzw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wzwx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wzwy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Short4 wzwz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wzww(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Short4 wwxx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wwxy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwxz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwxw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wwyx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwyy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wwyz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        short _v2 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwyw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wwzx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwzy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        short _v2 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Short4 wwzz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Short4 wwzw(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Short4 wwwx(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.x;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wwwy(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.y;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wwwz(@Mutated Short4 dest) {
        short _v0 = this.w;
        short _v1 = this.z;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Short4 wwww(@Mutated Short4 dest) {
        short _v0 = this.w;
        Short4Impl d = (Short4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Short4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short4Impl)) return false;
        Short4Impl o = (Short4Impl) obj;
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

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Short4R other, short epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon
            && java.lang.Math.abs(w - other.w()) <= epsilon;
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Short4 load(short[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public ShortBuffer store(@Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ShortBuffer storeRelative(@Mutated ShortBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public Short4 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Short4 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Short4 loadRelative(ShortBuffer buf) {
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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    public Short4 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Short4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Short4 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short4 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return r;
    }
    public Short4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Short4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset] = (byte) this.x;
        dest[offset + 1] = (byte) this.y;
        dest[offset + 2] = (byte) this.z;
        dest[offset + 3] = (byte) this.w;
        return dest;
    }
    public @Mutated Short4 load(byte[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public ByteBuffer storeByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }
    public ByteBuffer storeByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeByteAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    public Short4 loadByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, buf.position(), buf);
    }
    public Short4 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, index, buf);
    }
    public Short4 loadByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short4 r = StoreLoad.BB_OPS.loadByteAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return r;
    }
    public Short4 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }
    @Mutated public Short4 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(this, address);
    }
}
