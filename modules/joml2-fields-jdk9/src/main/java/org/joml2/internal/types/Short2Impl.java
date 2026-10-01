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
 * Generated implementation of {@link Short2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Short2Impl implements Short2 {

    public short x;
    public short y;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short2BbOpsUnsafe()
                        : new Short2BbOpsApi();
        static final Short2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short2RawOpsUnsafe()
                        : new Short2RawOpsApi();
    }

    public Short2Impl() {
    }

    public Short2Impl(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public Short2Impl(Short2R src) {
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
    public Short2 add(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (other.x() + this.x);
        d.y = (short) (otherY + this.y);
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
    public Int2 add(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
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
    public Long2 add(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
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
    public Double2 add(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        return d;
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
    public Short2 add(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (otherX + this.x);
        d.y = (short) (otherY + this.y);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 add(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 add(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 add(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 ceilDiv(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        d.y = (short) (Math.ceilDiv(this.y, scalar));
        d.x = (short) (_buf0);
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
    public Int2 ceilDiv(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        d.y = Math.ceilDiv(this.y, scalar);
        d.x = _buf0;
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
    public Long2 ceilDiv(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        d.y = Math.ceilDiv(this.y, scalar);
        d.x = _buf0;
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
    public Double2 ceilDiv(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        d.y = Math.ceilDiv(this.y, scalar);
        d.x = _buf0;
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
    public Short2 ceilDiv(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        d.y = (short) (Math.ceilDiv(this.y, other.y()));
        d.x = (short) (_buf0);
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
    public Int2 ceilDiv(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        d.y = Math.ceilDiv(this.y, other.y());
        d.x = _buf0;
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
    public Long2 ceilDiv(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        d.y = Math.ceilDiv(this.y, other.y());
        d.x = _buf0;
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
    public Double2 ceilDiv(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        d.y = Math.ceilDiv(this.y, other.y());
        d.x = _buf0;
        return d;
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
    public Short2 ceilDiv(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        d.y = (short) (Math.ceilDiv(this.y, otherY));
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilDiv(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        d.y = Math.ceilDiv(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilDiv(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        d.y = Math.ceilDiv(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilDiv(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        d.y = Math.ceilDiv(this.y, otherY);
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
    public Short2 ceilMod(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        d.y = (short) (Math.ceilMod(this.y, scalar));
        d.x = (short) (_buf0);
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
    public Int2 ceilMod(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        d.y = Math.ceilMod(this.y, scalar);
        d.x = _buf0;
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
    public Long2 ceilMod(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        d.y = Math.ceilMod(this.y, scalar);
        d.x = _buf0;
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
    public Double2 ceilMod(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        d.y = Math.ceilMod(this.y, scalar);
        d.x = _buf0;
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
    public Short2 ceilMod(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        d.y = (short) (Math.ceilMod(this.y, other.y()));
        d.x = (short) (_buf0);
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
    public Int2 ceilMod(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        d.y = Math.ceilMod(this.y, other.y());
        d.x = _buf0;
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
    public Long2 ceilMod(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        d.y = Math.ceilMod(this.y, other.y());
        d.x = _buf0;
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
    public Double2 ceilMod(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        d.y = Math.ceilMod(this.y, other.y());
        d.x = _buf0;
        return d;
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
    public Short2 ceilMod(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        d.y = (short) (Math.ceilMod(this.y, otherY));
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilMod(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        d.y = Math.ceilMod(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilMod(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        d.y = Math.ceilMod(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilMod(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        d.y = Math.ceilMod(this.y, otherY);
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
    public Short2 div(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x / scalar;
        d.y = (short) (this.y / scalar);
        d.x = (short) (_buf0);
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
    public Int2 div(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x / scalar;
        d.y = this.y / scalar;
        d.x = _buf0;
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
    public Long2 div(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x / scalar;
        d.y = this.y / scalar;
        d.x = _buf0;
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
    public Double2 div(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x / scalar;
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
    public Short2 div(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x / other.x();
        d.y = (short) (this.y / other.y());
        d.x = (short) (_buf0);
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
    public Int2 div(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x / other.x();
        d.y = this.y / other.y();
        d.x = _buf0;
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
    public Long2 div(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x / other.x();
        d.y = this.y / other.y();
        d.x = _buf0;
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
    public Double2 div(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x / other.x();
        d.y = this.y / other.y();
        d.x = _buf0;
        return d;
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
    public Short2 div(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x / otherX;
        d.y = (short) (this.y / otherY);
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 div(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x / otherX;
        d.y = this.y / otherY;
        d.x = _buf0;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 div(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x / otherX;
        d.y = this.y / otherY;
        d.x = _buf0;
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 div(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x / otherX;
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
    public Short2 floorDiv(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        d.y = (short) (Math.floorDiv(this.y, scalar));
        d.x = (short) (_buf0);
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
    public Int2 floorDiv(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        d.y = Math.floorDiv(this.y, scalar);
        d.x = _buf0;
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
    public Long2 floorDiv(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        d.y = Math.floorDiv(this.y, scalar);
        d.x = _buf0;
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
    public Double2 floorDiv(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        d.y = Math.floorDiv(this.y, scalar);
        d.x = _buf0;
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
    public Short2 floorDiv(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        d.y = (short) (Math.floorDiv(this.y, other.y()));
        d.x = (short) (_buf0);
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
    public Int2 floorDiv(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        d.y = Math.floorDiv(this.y, other.y());
        d.x = _buf0;
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
    public Long2 floorDiv(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        d.y = Math.floorDiv(this.y, other.y());
        d.x = _buf0;
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
    public Double2 floorDiv(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        d.y = Math.floorDiv(this.y, other.y());
        d.x = _buf0;
        return d;
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
    public Short2 floorDiv(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        d.y = (short) (Math.floorDiv(this.y, otherY));
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorDiv(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        d.y = Math.floorDiv(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorDiv(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        d.y = Math.floorDiv(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorDiv(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
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
    public Short2 floorMod(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        d.y = (short) (Math.floorMod(this.y, scalar));
        d.x = (short) (_buf0);
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
    public Int2 floorMod(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        d.y = Math.floorMod(this.y, scalar);
        d.x = _buf0;
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
    public Long2 floorMod(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        d.y = Math.floorMod(this.y, scalar);
        d.x = _buf0;
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
    public Double2 floorMod(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        d.y = Math.floorMod(this.y, scalar);
        d.x = _buf0;
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
    public Short2 floorMod(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        d.y = (short) (Math.floorMod(this.y, other.y()));
        d.x = (short) (_buf0);
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
    public Int2 floorMod(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        d.y = Math.floorMod(this.y, other.y());
        d.x = _buf0;
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
    public Long2 floorMod(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        d.y = Math.floorMod(this.y, other.y());
        d.x = _buf0;
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
    public Double2 floorMod(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        d.y = Math.floorMod(this.y, other.y());
        d.x = _buf0;
        return d;
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
    public Short2 floorMod(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        d.y = (short) (Math.floorMod(this.y, otherY));
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorMod(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        d.y = Math.floorMod(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorMod(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        d.y = Math.floorMod(this.y, otherY);
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorMod(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
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
    public Short2 mul(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (scalar * this.x);
        d.y = (short) (scalar * this.y);
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
    public Int2 mul(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
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
    public Long2 mul(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
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
    public Double2 mul(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
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
    public Short2 mul(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (other.x() * this.x);
        d.y = (short) (otherY * this.y);
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
    public Int2 mul(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
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
    public Long2 mul(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
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
    public Double2 mul(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        return d;
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
    public Short2 mul(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (otherX * this.x);
        d.y = (short) (otherY * this.y);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 mul(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 mul(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 negate(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (-this.x);
        d.y = (short) (-this.y);
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
    public Int2 negate(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
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
    public Long2 negate(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = -this.x;
        d.y = -this.y;
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
    public Double2 negate(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 rem(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x % scalar;
        d.y = (short) (this.y % scalar);
        d.x = (short) (_buf0);
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
    public Int2 rem(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x % scalar;
        d.y = this.y % scalar;
        d.x = _buf0;
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
    public Long2 rem(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x % scalar;
        d.y = this.y % scalar;
        d.x = _buf0;
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
    public Double2 rem(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x % scalar;
        d.y = this.y % scalar;
        d.x = _buf0;
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
    public Short2 rem(Short2R other, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x % other.x();
        d.y = (short) (this.y % other.y());
        d.x = (short) (_buf0);
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
    public Int2 rem(Short2R other, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x % other.x();
        d.y = this.y % other.y();
        d.x = _buf0;
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
    public Long2 rem(Short2R other, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x % other.x();
        d.y = this.y % other.y();
        d.x = _buf0;
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
    public Double2 rem(Short2R other, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x % other.x();
        d.y = this.y % other.y();
        d.x = _buf0;
        return d;
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
    public Short2 rem(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        int _buf0 = this.x % otherX;
        d.y = (short) (this.y % otherY);
        d.x = (short) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rem(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _buf0 = this.x % otherX;
        d.y = this.y % otherY;
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rem(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _buf0 = this.x % otherX;
        d.y = this.y % otherY;
        d.x = _buf0;
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rem(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _buf0 = this.x % otherX;
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
    public Short2 sub(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x - other.x());
        d.y = (short) (this.y - otherY);
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
    public Int2 sub(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
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
    public Long2 sub(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
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
    public Double2 sub(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        return d;
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
    public Short2 sub(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x - otherX);
        d.y = (short) (this.y - otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 sub(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 sub(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 sub(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 and(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x & other.x());
        d.y = (short) (this.y & otherY);
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
    public Int2 and(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
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
    public Long2 and(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
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
    public Double2 and(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        return d;
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
    public Short2 and(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x & otherX);
        d.y = (short) (this.y & otherY);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 and(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 and(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 and(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 bitCount(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.bitCount(this.x));
        d.y = (short) (Math.bitCount(this.y));
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
    public Int2 bitCount(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
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
    public Long2 bitCount(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.bitCount(this.x);
        d.y = Math.bitCount(this.y);
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
    public Double2 bitCount(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 not(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (~this.x);
        d.y = (short) (~this.y);
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
    public Int2 not(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
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
    public Long2 not(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = ~this.x;
        d.y = ~this.y;
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
    public Double2 not(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 numberOfLeadingZeros(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.numberOfLeadingZeros(this.x));
        d.y = (short) (Math.numberOfLeadingZeros(this.y));
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
    public Int2 numberOfLeadingZeros(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
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
    public Long2 numberOfLeadingZeros(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.numberOfLeadingZeros(this.x);
        d.y = Math.numberOfLeadingZeros(this.y);
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
    public Double2 numberOfLeadingZeros(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 numberOfTrailingZeros(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.numberOfTrailingZeros(this.x));
        d.y = (short) (Math.numberOfTrailingZeros(this.y));
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
    public Int2 numberOfTrailingZeros(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
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
    public Long2 numberOfTrailingZeros(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.numberOfTrailingZeros(this.x);
        d.y = Math.numberOfTrailingZeros(this.y);
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
    public Double2 numberOfTrailingZeros(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 or(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x | other.x());
        d.y = (short) (this.y | otherY);
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
    public Int2 or(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
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
    public Long2 or(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
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
    public Double2 or(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        return d;
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
    public Short2 or(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x | otherX);
        d.y = (short) (this.y | otherY);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 or(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 or(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 or(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 reverseBits(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.reverseBits(this.x));
        d.y = (short) (Math.reverseBits(this.y));
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
    public Int2 reverseBits(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
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
    public Long2 reverseBits(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.reverseBits(this.x);
        d.y = Math.reverseBits(this.y);
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
    public Double2 reverseBits(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 reverseBytes(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.reverseBytes(this.x));
        d.y = (short) (Math.reverseBytes(this.y));
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
    public Int2 reverseBytes(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
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
    public Long2 reverseBytes(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.reverseBytes(this.x);
        d.y = Math.reverseBytes(this.y);
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
    public Double2 reverseBytes(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 rotateLeft(short distance, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.rotateLeft(this.x, distance));
        d.y = (short) (Math.rotateLeft(this.y, distance));
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
    public Int2 rotateLeft(short distance, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
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
    public Long2 rotateLeft(short distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
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
    public Double2 rotateLeft(short distance, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 rotateRight(short distance, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.rotateRight(this.x, distance));
        d.y = (short) (Math.rotateRight(this.y, distance));
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
    public Int2 rotateRight(short distance, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
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
    public Long2 rotateRight(short distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
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
    public Double2 rotateRight(short distance, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
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
    public Short2 shl(short shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x << (shift & 15));
        d.y = (short) (this.y << (shift & 15));
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
    public Int2 shl(short shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
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
    public Long2 shl(short shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
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
    public Double2 shl(short shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x << (shift & 15);
        d.y = this.y << (shift & 15);
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
    public Short2 shr(short shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x >> (shift & 15));
        d.y = (short) (this.y >> (shift & 15));
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
    public Int2 shr(short shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
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
    public Long2 shr(short shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
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
    public Double2 shr(short shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x >> (shift & 15);
        d.y = this.y >> (shift & 15);
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
    public Short2 ushr(short shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) ((this.x & 0xFFFF) >>> (shift & 15));
        d.y = (short) ((this.y & 0xFFFF) >>> (shift & 15));
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
    public Int2 ushr(short shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
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
    public Long2 ushr(short shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
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
    public Double2 ushr(short shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = (this.x & 0xFFFF) >>> (shift & 15);
        d.y = (this.y & 0xFFFF) >>> (shift & 15);
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
    public Short2 xor(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x ^ other.x());
        d.y = (short) (this.y ^ otherY);
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
    public Int2 xor(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
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
    public Long2 xor(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
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
    public Double2 xor(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        return d;
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
    public Short2 xor(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x ^ otherX);
        d.y = (short) (this.y ^ otherY);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 xor(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 xor(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 xor(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public @Mutated Short2 set(Short2R v) {
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
    @Mutated public Short2 set(short vX, short vY) {
        this.x = (short) (vX);
        this.y = (short) (vY);
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
    public Short2 set(short s, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (s);
        d.y = (short) (s);
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
    public Int2 set(short s, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = s;
        d.y = s;
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
    public Long2 set(short s, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = s;
        d.y = s;
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
    public Double2 set(short s, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = s;
        d.y = s;
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
    public Float2 toFloat(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.x;
        d.y = this.y;
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
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 toInt(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x;
        d.y = this.y;
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
    public Long2 toLong(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x;
        d.y = this.y;
        return d;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Short2 makeZero() {
        this.x = (short) (0);
        this.y = (short) (0);
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
    public Short2 absolute(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.abs(this.x));
        d.y = (short) (java.lang.Math.abs(this.y));
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
    public Int2 absolute(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
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
    public Long2 absolute(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
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
    public Double2 absolute(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.abs(this.x);
        d.y = java.lang.Math.abs(this.y);
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
    public Short2 clamp(short min, short max, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
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
    public Int2 clamp(short min, short max, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
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
    public Long2 clamp(short min, short max, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
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
    public Double2 clamp(short min, short max, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
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
    public Short2 clamp(Short2R min, Short2R max, @Mutated Short2 dest) {
        short minY = min.y();
        short maxY = max.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
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
    public Int2 clamp(Short2R min, Short2R max, @Mutated Int2 dest) {
        short minY = min.y();
        short maxY = max.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
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
    public Long2 clamp(Short2R min, Short2R max, @Mutated Long2 dest) {
        short minY = min.y();
        short maxY = max.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
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
    public Double2 clamp(Short2R min, Short2R max, @Mutated Double2 dest) {
        short minY = min.y();
        short maxY = max.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
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
    public Short2 clamp(short minX, short minY, short maxX, short maxY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
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
    public Int2 clamp(short minX, short minY, short maxX, short maxY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
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
    public Long2 clamp(short minX, short minY, short maxX, short maxY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
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
    public Double2 clamp(short minX, short minY, short maxX, short maxY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
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
        return this.x + this.y;
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public short compMax() {
        return (short) (java.lang.Math.max(this.x, this.y));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public short compMin() {
        return (short) (java.lang.Math.min(this.x, this.y));
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
        return (long) this.x * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public long cross(Short2R other) {
        return (long) other.y() * this.x - (long) other.x() * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     * order.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
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
    public long cross(short otherX, short otherY) {
        return (long) otherY * this.x - (long) otherX * this.y;
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
    public long distanceSquared(Short2R other) {
        long _t0 = (long) this.x - other.x();
        long _t1 = (long) this.y - other.y();
        return _t0 * _t0 + _t1 * _t1;
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY})
     */
    public long distanceSquared(short otherX, short otherY) {
        long _t0 = (long) this.x - otherX;
        long _t1 = (long) this.y - otherY;
        return _t0 * _t0 + _t1 * _t1;
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
    public long dot(Short2R other) {
        return (long) other.x() * this.x + (long) other.y() * this.y;
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY})
     */
    public long dot(short otherX, short otherY) {
        return (long) otherX * this.x + (long) otherY * this.y;
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
        return (long) this.x * this.x + (long) this.y * this.y;
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
    public int manhattanDistance(Short2R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code short} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY})
     */
    public int manhattanDistance(short otherX, short otherY) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY);
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
        return java.lang.Math.abs(this.x) + java.lang.Math.abs(this.y);
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
    public Short2 max(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, scalar));
        d.y = (short) (java.lang.Math.max(this.y, scalar));
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
    public Int2 max(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
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
    public Long2 max(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
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
    public Double2 max(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
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
    public Short2 max(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, other.x()));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
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
    public Int2 max(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
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
    public Long2 max(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
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
    public Double2 max(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
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
    public Short2 max(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, otherX));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 max(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 max(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 max(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
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
    public Short2 min(short scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, scalar));
        d.y = (short) (java.lang.Math.min(this.y, scalar));
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
    public Int2 min(short scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
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
    public Long2 min(short scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
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
    public Double2 min(short scalar, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
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
    public Short2 min(Short2R other, @Mutated Short2 dest) {
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, other.x()));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
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
    public Int2 min(Short2R other, @Mutated Int2 dest) {
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
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
    public Long2 min(Short2R other, @Mutated Long2 dest) {
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
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
    public Double2 min(Short2R other, @Mutated Double2 dest) {
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
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
    public Short2 min(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, otherX));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 min(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 min(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code short} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 min(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
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
    public Short2 sign(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.signum(this.x));
        d.y = (short) (Math.signum(this.y));
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
    public Int2 sign(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
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
    public Long2 sign(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.signum(this.x);
        d.y = Math.signum(this.y);
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
    public Double2 sign(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
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
    public Short2 satAdd(Short2R other, @Mutated Short2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
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
    public Int2 satAdd(Short2R other, @Mutated Int2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
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
    public Long2 satAdd(Short2R other, @Mutated Long2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
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
    public Double2 satAdd(Short2R other, @Mutated Double2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        return d;
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
    public Short2 satAdd(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddS(this.y, otherY));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satAdd(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satAdd(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satAdd(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddS(this.y, otherY);
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
    public Short2 satMul(Short2R other, @Mutated Short2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
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
    public Int2 satMul(Short2R other, @Mutated Int2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
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
    public Long2 satMul(Short2R other, @Mutated Long2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
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
    public Double2 satMul(Short2R other, @Mutated Double2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        return d;
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
    public Short2 satMul(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulS(this.y, otherY));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satMul(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satMul(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satMul(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulS(this.y, otherY);
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
    public Short2 satNegate(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegS(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegS(this.y));
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
    public Int2 satNegate(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
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
    public Long2 satNegate(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
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
    public Double2 satNegate(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegS(this.x);
        d.y = org.joml2.SaturatingMath.satNegS(this.y);
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
    public Short2 satSub(Short2R other, @Mutated Short2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
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
    public Int2 satSub(Short2R other, @Mutated Int2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
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
    public Long2 satSub(Short2R other, @Mutated Long2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
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
    public Double2 satSub(Short2R other, @Mutated Double2 dest) {
        short otherX = other.x();
        short otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        return d;
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
    public Short2 satSub(short otherX, short otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubS(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubS(this.y, otherY));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satSub(short otherX, short otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satSub(short otherX, short otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code short} range ({@code Short.MIN_VALUE} to
     * {@code Short.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satSub(short otherX, short otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubS(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubS(this.y, otherY);
        return d;
    }

    public short x() { return this.x; }
    public short y() { return this.y; }

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

    @Override public String toString() {
        return "Short2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short2Impl)) return false;
        Short2Impl o = (Short2Impl) obj;
        return x == o.x
            && y == o.y;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + x;
        h = 31 * h + y;
        return h;
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(Short2R other, short epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon;
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }
    public @Mutated Short2 load(short[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        return this;
    }
    public ShortBuffer store(@Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ShortBuffer storeRelative(@Mutated ShortBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    @Mutated public Short2 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Short2 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Short2 loadRelative(ShortBuffer buf) {
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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    public Short2 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Short2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Short2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short2 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return r;
    }
    public Short2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Short2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset] = (byte) this.x;
        dest[offset + 1] = (byte) this.y;
        return dest;
    }
    public @Mutated Short2 load(byte[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        return this;
    }
    public ByteBuffer storeByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }
    public ByteBuffer storeByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeByteAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    public Short2 loadByte(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, buf.position(), buf);
    }
    public Short2 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, index, buf);
    }
    public Short2 loadByteRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Short2 r = StoreLoad.BB_OPS.loadByteAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return r;
    }
    public Short2 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }
    @Mutated public Short2 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(this, address);
    }
}
