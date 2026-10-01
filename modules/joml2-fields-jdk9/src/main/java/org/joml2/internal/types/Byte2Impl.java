// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * Generated implementation of {@link Byte2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Byte2Impl implements Byte2 {

    public byte x;
    public byte y;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Byte2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte2BbOpsUnsafe()
                        : new Byte2BbOpsApi();
        static final Byte2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte2RawOpsUnsafe()
                        : new Byte2RawOpsApi();
    }

    public Byte2Impl() {
    }

    public Byte2Impl(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public Byte2Impl(Byte2R src) {
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
    public Byte2 add(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (other.x() + this.x);
        d.y = (byte) (otherY + this.y);
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Short2 add(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (other.x() + this.x);
        d.y = (short) (otherY + this.y);
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Int2 add(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long2 add(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double2 add(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 add(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (otherX + this.x);
        d.y = (byte) (otherY + this.y);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 add(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (otherX + this.x);
        d.y = (short) (otherY + this.y);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 add(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 add(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX + this.x;
        d.y = otherY + this.y;
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 add(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 ceilDiv(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        d.y = (byte) (Math.ceilDiv(this.y, scalar));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilDiv(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilDiv(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilDiv(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilDiv(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 ceilDiv(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        d.y = (byte) (Math.ceilDiv(this.y, other.y()));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilDiv(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilDiv(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilDiv(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilDiv(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 ceilDiv(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        d.y = (byte) (Math.ceilDiv(this.y, otherY));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilDiv(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilDiv(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilDiv(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilDiv(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 ceilMod(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        d.y = (byte) (Math.ceilMod(this.y, scalar));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilMod(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilMod(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilMod(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilMod(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 ceilMod(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        d.y = (byte) (Math.ceilMod(this.y, other.y()));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilMod(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilMod(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilMod(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilMod(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 ceilMod(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        d.y = (byte) (Math.ceilMod(this.y, otherY));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ceilMod(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ceilMod(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ceilMod(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ceilMod(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 div(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x / scalar;
        d.y = (byte) (this.y / scalar);
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 div(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 div(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 div(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 div(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 div(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x / other.x();
        d.y = (byte) (this.y / other.y());
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 div(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 div(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 div(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 div(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 div(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x / otherX;
        d.y = (byte) (this.y / otherY);
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}) (integer division,
     * truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 div(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 div(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 div(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 div(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 floorDiv(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        d.y = (byte) (Math.floorDiv(this.y, scalar));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorDiv(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorDiv(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorDiv(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorDiv(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 floorDiv(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        d.y = (byte) (Math.floorDiv(this.y, other.y()));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorDiv(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorDiv(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorDiv(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorDiv(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 floorDiv(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        d.y = (byte) (Math.floorDiv(this.y, otherY));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorDiv(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorDiv(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorDiv(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorDiv(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 floorMod(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        d.y = (byte) (Math.floorMod(this.y, scalar));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorMod(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorMod(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorMod(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorMod(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 floorMod(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        d.y = (byte) (Math.floorMod(this.y, other.y()));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorMod(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorMod(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorMod(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorMod(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 floorMod(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        d.y = (byte) (Math.floorMod(this.y, otherY));
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 floorMod(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 floorMod(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 floorMod(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 floorMod(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 mul(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (scalar * this.x);
        d.y = (byte) (scalar * this.y);
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 mul(byte scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (scalar * this.x);
        d.y = (short) (scalar * this.y);
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 mul(byte scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 mul(byte scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        return d;
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 mul(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (other.x() * this.x);
        d.y = (byte) (otherY * this.y);
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 mul(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (other.x() * this.x);
        d.y = (short) (otherY * this.y);
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 mul(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 mul(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 mul(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (otherX * this.x);
        d.y = (byte) (otherY * this.y);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 mul(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (otherX * this.x);
        d.y = (short) (otherY * this.y);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 mul(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 mul(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = otherX * this.x;
        d.y = otherY * this.y;
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 negate(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (-this.x);
        d.y = (byte) (-this.y);
        return d;
    }


    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte2 rem(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x % scalar;
        d.y = (byte) (this.y % scalar);
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Short2 rem(byte scalar, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rem(byte scalar, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rem(byte scalar, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rem(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 rem(Byte2R other, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x % other.x();
        d.y = (byte) (this.y % other.y());
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 rem(Byte2R other, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rem(Byte2R other, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rem(Byte2R other, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rem(Byte2R other, @Mutated Double2 dest) {
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
    public Byte2 rem(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        int _buf0 = this.x % otherX;
        d.y = (byte) (this.y % otherY);
        d.x = (byte) (_buf0);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}) (the remainder carries the sign of the dividend, exactly Java's {@code %}, so
     * it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 rem(byte otherX, byte otherY, @Mutated Short2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rem(byte otherX, byte otherY, @Mutated Int2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rem(byte otherX, byte otherY, @Mutated Long2 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rem(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 sub(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x - other.x());
        d.y = (byte) (this.y - otherY);
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Short2 sub(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x - other.x());
        d.y = (short) (this.y - otherY);
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Int2 sub(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long2 sub(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double2 sub(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 sub(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x - otherX);
        d.y = (byte) (this.y - otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 sub(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x - otherX);
        d.y = (short) (this.y - otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 sub(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 sub(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x - otherX;
        d.y = this.y - otherY;
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 sub(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 and(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x & other.x());
        d.y = (byte) (this.y & otherY);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Short2 and(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x & other.x());
        d.y = (short) (this.y & otherY);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Int2 and(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long2 and(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x & other.x();
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Double2 and(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 and(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x & otherX);
        d.y = (byte) (this.y & otherY);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 and(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x & otherX);
        d.y = (short) (this.y & otherY);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 and(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 and(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x & otherX;
        d.y = this.y & otherY;
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 and(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 bitCount(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.bitCount(this.x));
        d.y = (byte) (Math.bitCount(this.y));
        return d;
    }


    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte2 not(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (~this.x);
        d.y = (byte) (~this.y);
        return d;
    }


    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte2 numberOfLeadingZeros(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.numberOfLeadingZeros(this.x));
        d.y = (byte) (Math.numberOfLeadingZeros(this.y));
        return d;
    }


    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte2 numberOfTrailingZeros(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.numberOfTrailingZeros(this.x));
        d.y = (byte) (Math.numberOfTrailingZeros(this.y));
        return d;
    }


    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte2 or(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x | other.x());
        d.y = (byte) (this.y | otherY);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Short2 or(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x | other.x());
        d.y = (short) (this.y | otherY);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Int2 or(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long2 or(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x | other.x();
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Double2 or(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 or(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x | otherX);
        d.y = (byte) (this.y | otherY);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 or(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x | otherX);
        d.y = (short) (this.y | otherY);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 or(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 or(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x | otherX;
        d.y = this.y | otherY;
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 or(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 reverseBits(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.reverseBits(this.x));
        d.y = (byte) (Math.reverseBits(this.y));
        return d;
    }


    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte2 reverseBytes(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.reverseBytes(this.x));
        d.y = (byte) (Math.reverseBytes(this.y));
        return d;
    }


    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte2 rotateLeft(byte distance, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.rotateLeft(this.x, distance));
        d.y = (byte) (Math.rotateLeft(this.y, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 rotateLeft(byte distance, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.rotateLeft(this.x, distance));
        d.y = (short) (Math.rotateLeft(this.y, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rotateLeft(byte distance, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rotateLeft(byte distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateLeft(this.x, distance);
        d.y = Math.rotateLeft(this.y, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rotateLeft(byte distance, @Mutated Double2 dest) {
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
    public Byte2 rotateRight(byte distance, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.rotateRight(this.x, distance));
        d.y = (byte) (Math.rotateRight(this.y, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 rotateRight(byte distance, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (Math.rotateRight(this.x, distance));
        d.y = (short) (Math.rotateRight(this.y, distance));
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 rotateRight(byte distance, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 rotateRight(byte distance, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        return d;
    }


    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
     * rotations are relative to that width, not to the 32 bits of {@code int} - and each result
     * component is then widened to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 rotateRight(byte distance, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Byte2 shl(byte shift, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x << (shift & 7));
        d.y = (byte) (this.y << (shift & 7));
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}. The shift
     * count is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 shl(byte shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x << (shift & 7));
        d.y = (short) (this.y << (shift & 7));
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}. The shift count
     * is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 shl(byte shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}. The shift count
     * is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 shl(byte shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        return d;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits (the shift count is taken
     * modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes to {@code int}
     * and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}. The shift
     * count is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 shl(byte shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Byte2 shr(byte shift, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x >> (shift & 7));
        d.y = (byte) (this.y >> (shift & 7));
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}. The shift
     * count is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 shr(byte shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x >> (shift & 7));
        d.y = (short) (this.y >> (shift & 7));
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}. The shift count
     * is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 shr(byte shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}. The shift count
     * is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 shr(byte shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        return d;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits (the shift
     * count is taken modulo the lane width of 8, unlike Java's {@code byte} shift, which promotes
     * to {@code int} and takes it modulo 32) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}. The shift
     * count is still taken modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 shr(byte shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Byte2 ushr(byte shift, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (byte) ((this.y & 0xFF) >>> (shift & 7));
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32) and store the result in {@code dest}.
     * <p>
     * The shift is evaluated on the {@code byte} lane of 8 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is {@code 127}),
     * and each result component is then widened to {@code short}. The shift count is still taken
     * modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Short2 ushr(byte shift, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (short) ((this.y & 0xFF) >>> (shift & 7));
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32) and store the result in {@code dest}.
     * <p>
     * The shift is evaluated on the {@code byte} lane of 8 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is {@code 127}),
     * and each result component is then widened to {@code int}. The shift count is still taken
     * modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Int2 ushr(byte shift, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32) and store the result in {@code dest}.
     * <p>
     * The shift is evaluated on the {@code byte} lane of 8 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is {@code 127}),
     * and each result component is then widened to {@code long}. The shift count is still taken
     * modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long2 ushr(byte shift, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        return d;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits (the 8 bits of each
     * component are shifted with zeros entering at the top of that lane, and the shift count is
     * taken modulo the lane width of 8 - unlike Java's {@code byte} {@code >>>}, which sign-extends
     * to {@code int} first and takes the count modulo 32) and store the result in {@code dest}.
     * <p>
     * The shift is evaluated on the {@code byte} lane of 8 bits, zero-extended rather than
     * sign-extended as Java's promotion to {@code int} would (so {@code -1 >>> 1} is {@code 127}),
     * and each result component is then widened to {@code double}. The shift count is still taken
     * modulo this vector's lane width of 8, not the destination's.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double2 ushr(byte shift, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
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
    public Byte2 xor(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x ^ other.x());
        d.y = (byte) (this.y ^ otherY);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Short2 xor(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x ^ other.x());
        d.y = (short) (this.y ^ otherY);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Int2 xor(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long2 xor(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x ^ other.x();
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Double2 xor(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 xor(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (this.x ^ otherX);
        d.y = (byte) (this.y ^ otherY);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 xor(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (this.x ^ otherX);
        d.y = (short) (this.y ^ otherY);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 xor(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 xor(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.x ^ otherX;
        d.y = this.y ^ otherY;
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 xor(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public @Mutated Byte2 set(Byte2R v) {
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
    @Mutated public Byte2 set(byte vX, byte vY) {
        this.x = (byte) (vX);
        this.y = (byte) (vY);
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
    public Byte2 set(byte s, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (s);
        d.y = (byte) (s);
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Short2 set(byte s, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (s);
        d.y = (short) (s);
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Int2 set(byte s, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = s;
        d.y = s;
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Long2 set(byte s, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = s;
        d.y = s;
        return d;
    }


    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Double2 set(byte s, @Mutated Double2 dest) {
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
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
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
    @Mutated public Byte2 makeZero() {
        this.x = (byte) (0);
        this.y = (byte) (0);
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
    public Byte2 absolute(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.abs(this.x));
        d.y = (byte) (java.lang.Math.abs(this.y));
        return d;
    }


    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte2 clamp(byte min, byte max, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Short2 clamp(byte min, byte max, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Int2 clamp(byte min, byte max, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Long2 clamp(byte min, byte max, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min), max);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, min), max);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Double2 clamp(byte min, byte max, @Mutated Double2 dest) {
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
    public Byte2 clamp(Byte2R min, Byte2R max, @Mutated Byte2 dest) {
        byte minY = min.y();
        byte maxY = max.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Short2 clamp(Byte2R min, Byte2R max, @Mutated Short2 dest) {
        byte minY = min.y();
        byte maxY = max.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Int2 clamp(Byte2R min, Byte2R max, @Mutated Int2 dest) {
        byte minY = min.y();
        byte maxY = max.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Long2 clamp(Byte2R min, Byte2R max, @Mutated Long2 dest) {
        byte minY = min.y();
        byte maxY = max.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x());
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Double2 clamp(Byte2R min, Byte2R max, @Mutated Double2 dest) {
        byte minY = min.y();
        byte maxY = max.y();
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
    public Byte2 clamp(byte minX, byte minY, byte maxX, byte maxY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short2 clamp(byte minX, byte minY, byte maxX, byte maxY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (short) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int2 clamp(byte minX, byte minY, byte maxX, byte maxY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long2 clamp(byte minX, byte minY, byte maxX, byte maxY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double2 clamp(byte minX, byte minY, byte maxX, byte maxY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        return d;
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
        return this.x + this.y;
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public byte compMax() {
        return (byte) (java.lang.Math.max(this.x, this.y));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        return (byte) (java.lang.Math.min(this.x, this.y));
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
        return this.x * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int cross(Byte2R other) {
        return other.y() * this.x - other.x() * this.y;
    }


    /**
     * Compute the 2D cross product of this vector and ({@code otherX}, {@code otherY}), in that
     * order.
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int cross(byte otherX, byte otherY) {
        return otherY * this.x - otherX * this.y;
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
    public int distanceSquared(Byte2R other) {
        int _t0 = this.x - other.x();
        int _t1 = this.y - other.y();
        return _t0 * _t0 + _t1 * _t1;
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY})
     */
    public int distanceSquared(byte otherX, byte otherY) {
        int _t0 = this.x - otherX;
        int _t1 = this.y - otherY;
        return _t0 * _t0 + _t1 * _t1;
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
    public int dot(Byte2R other) {
        return other.x() * this.x + other.y() * this.y;
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY})
     */
    public int dot(byte otherX, byte otherY) {
        return otherX * this.x + otherY * this.y;
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
        return this.x * this.x + this.y * this.y;
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
    public int manhattanDistance(Byte2R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY})
     */
    public int manhattanDistance(byte otherX, byte otherY) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY);
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
    public Byte2 max(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, scalar));
        d.y = (byte) (java.lang.Math.max(this.y, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Short2 max(byte scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, scalar));
        d.y = (short) (java.lang.Math.max(this.y, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int2 max(byte scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long2 max(byte scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, scalar);
        d.y = java.lang.Math.max(this.y, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double2 max(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 max(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, other.x()));
        d.y = (byte) (java.lang.Math.max(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Short2 max(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, other.x()));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int2 max(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long2 max(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, other.x());
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double2 max(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 max(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, otherX));
        d.y = (byte) (java.lang.Math.max(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 max(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.max(this.x, otherX));
        d.y = (short) (java.lang.Math.max(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 max(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 max(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.x, otherX);
        d.y = java.lang.Math.max(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 max(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 min(byte scalar, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, scalar));
        d.y = (byte) (java.lang.Math.min(this.y, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Short2 min(byte scalar, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, scalar));
        d.y = (short) (java.lang.Math.min(this.y, scalar));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int2 min(byte scalar, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long2 min(byte scalar, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, scalar);
        d.y = java.lang.Math.min(this.y, scalar);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double2 min(byte scalar, @Mutated Double2 dest) {
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
    public Byte2 min(Byte2R other, @Mutated Byte2 dest) {
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, other.x()));
        d.y = (byte) (java.lang.Math.min(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Short2 min(Byte2R other, @Mutated Short2 dest) {
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, other.x()));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int2 min(Byte2R other, @Mutated Int2 dest) {
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long2 min(Byte2R other, @Mutated Long2 dest) {
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, other.x());
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double2 min(Byte2R other, @Mutated Double2 dest) {
        byte otherY = other.y();
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
    public Byte2 min(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, otherX));
        d.y = (byte) (java.lang.Math.min(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 min(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (java.lang.Math.min(this.x, otherX));
        d.y = (short) (java.lang.Math.min(this.y, otherY));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 min(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 min(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.min(this.x, otherX);
        d.y = java.lang.Math.min(this.y, otherY);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 min(byte otherX, byte otherY, @Mutated Double2 dest) {
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
    public Byte2 sign(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (Math.signum(this.x));
        d.y = (byte) (Math.signum(this.y));
        return d;
    }


    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte2 satAdd(Byte2R other, @Mutated Byte2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satAdd(Byte2R other, @Mutated Short2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satAdd(Byte2R other, @Mutated Int2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satAdd(Byte2R other, @Mutated Long2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        return d;
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satAdd(Byte2R other, @Mutated Double2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
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
    public Byte2 satAdd(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satAdd(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satAdd(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satAdd(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}) to this vector, clamping to the value range instead of
     * overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satAdd(byte otherX, byte otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
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
    public Byte2 satMul(Byte2R other, @Mutated Byte2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satMul(Byte2R other, @Mutated Short2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satMul(Byte2R other, @Mutated Int2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satMul(Byte2R other, @Mutated Long2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satMul(Byte2R other, @Mutated Double2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
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
    public Byte2 satMul(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satMul(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satMul(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satMul(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}), clamping to the value range instead
     * of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satMul(byte otherX, byte otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
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
    public Byte2 satNegate(@Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (byte) (org.joml2.SaturatingMath.satNegB(this.y));
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satNegate(@Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegB(this.y));
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satNegate(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satNegate(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        return d;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satNegate(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
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
    public Byte2 satSub(Byte2R other, @Mutated Byte2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satSub(Byte2R other, @Mutated Short2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satSub(Byte2R other, @Mutated Int2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satSub(Byte2R other, @Mutated Long2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        return d;
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satSub(Byte2R other, @Mutated Double2 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
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
    public Byte2 satSub(byte otherX, byte otherY, @Mutated Byte2 dest) {
        Byte2Impl d = (Byte2Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Short2 satSub(byte otherX, byte otherY, @Mutated Short2 dest) {
        Short2Impl d = (Short2Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Int2 satSub(byte otherX, byte otherY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Long2 satSub(byte otherX, byte otherY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}) from this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 satSub(byte otherX, byte otherY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        return d;
    }

    public byte x() { return this.x; }
    public byte y() { return this.y; }

    public Byte2 xx(@Mutated Byte2 dest) {
        byte _v0 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Byte2 xy(@Mutated Byte2 dest) {
        byte _v1 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Byte2 yx(@Mutated Byte2 dest) {
        byte _v1 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Byte2 yy(@Mutated Byte2 dest) {
        byte _v0 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Byte3 xxx(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Byte3 xxy(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 xyx(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 xyy(@Mutated Byte3 dest) {
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 yxx(@Mutated Byte3 dest) {
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 yxy(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 yyx(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 yyy(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Byte4 xxxx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 xxxy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 xxyx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xxyy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 xyxx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 xyxy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 xyyx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xyyy(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxxx(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxxy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 yxyx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxyy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 yyxx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yyxy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 yyyx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 yyyy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Byte2(" + x() + ", " + y() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte2Impl)) return false;
        Byte2Impl o = (Byte2Impl) obj;
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

    @Override public boolean equalsEpsilon(Byte2R other, byte epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon;
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }
    public @Mutated Byte2 load(byte[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return buf;
    }
    public Byte2 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Byte2 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Byte2 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte2 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return r;
    }
    public Byte2 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Byte2 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        return dest;
    }
    public @Mutated Byte2 load(short[] src, int offset) {
        this.x = (byte) src[offset];
        this.y = (byte) src[offset + 1];
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
    @Mutated public Byte2 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Byte2 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Byte2 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 2) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 2);
        return this;
    }
    public ByteBuffer storeShort(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, index, buf);
    }
    public ByteBuffer storeShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeShortAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    public Byte2 loadShort(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, buf.position(), buf);
    }
    public Byte2 loadShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, index, buf);
    }
    public Byte2 loadShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte2 r = StoreLoad.BB_OPS.loadShortAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return r;
    }
    public Byte2 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }
    @Mutated public Byte2 loadShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadShortUnsafe(this, address);
    }
}
