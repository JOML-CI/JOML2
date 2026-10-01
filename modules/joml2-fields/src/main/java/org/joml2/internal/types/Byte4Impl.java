// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * Generated implementation of {@link Byte4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Byte4Impl implements Byte4 {

    public byte x;
    public byte y;
    public byte z;
    public byte w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Byte4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4SegOpsUnsafe()
                        : new Byte4SegOpsMS();
        static final Byte4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4BbOpsUnsafe()
                        : new Byte4BbOpsApi();
        static final Byte4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte4RawOpsUnsafe()
                        : new Byte4RawOpsApi();
    }

    public Byte4Impl() {
        w = 1;
    }

    public Byte4Impl(byte x, byte y, byte z, byte w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Byte4Impl(Byte4R src) {
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
    public Byte4 add(Byte4R other, @Mutated Byte4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 add(Byte4R other, @Mutated Short4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 add(Byte4R other, @Mutated Int4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 add(Byte4R other, @Mutated Long4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 add(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 add(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (otherX + this.x);
        d.y = (byte) (otherY + this.y);
        d.z = (byte) (otherZ + this.z);
        d.w = (byte) (otherW + this.w);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 add(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 add(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 add(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 add(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 ceilDiv(byte scalar, @Mutated Byte4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Short4 ceilDiv(byte scalar, @Mutated Short4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Int4 ceilDiv(byte scalar, @Mutated Int4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Long4 ceilDiv(byte scalar, @Mutated Long4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Double4 ceilDiv(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 ceilDiv(Byte4R other, @Mutated Byte4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 ceilDiv(Byte4R other, @Mutated Short4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 ceilDiv(Byte4R other, @Mutated Int4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 ceilDiv(Byte4R other, @Mutated Long4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 ceilDiv(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        int _buf2 = java.lang.Math.ceilDiv(this.z, otherZ);
        d.w = (byte) (java.lang.Math.ceilDiv(this.w, otherW));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        int _buf2 = java.lang.Math.ceilDiv(this.z, otherZ);
        d.w = (short) (java.lang.Math.ceilDiv(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        int _buf2 = java.lang.Math.ceilDiv(this.z, otherZ);
        d.w = java.lang.Math.ceilDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        int _buf2 = java.lang.Math.ceilDiv(this.z, otherZ);
        d.w = java.lang.Math.ceilDiv(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 ceilDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        int _buf2 = java.lang.Math.ceilDiv(this.z, otherZ);
        d.w = java.lang.Math.ceilDiv(this.w, otherW);
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
    public Byte4 ceilMod(byte scalar, @Mutated Byte4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Short4 ceilMod(byte scalar, @Mutated Short4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Int4 ceilMod(byte scalar, @Mutated Int4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Long4 ceilMod(byte scalar, @Mutated Long4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Double4 ceilMod(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 ceilMod(Byte4R other, @Mutated Byte4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 ceilMod(Byte4R other, @Mutated Short4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 ceilMod(Byte4R other, @Mutated Int4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 ceilMod(Byte4R other, @Mutated Long4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 ceilMod(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        int _buf2 = java.lang.Math.ceilMod(this.z, otherZ);
        d.w = (byte) (java.lang.Math.ceilMod(this.w, otherW));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        int _buf2 = java.lang.Math.ceilMod(this.z, otherZ);
        d.w = (short) (java.lang.Math.ceilMod(this.w, otherW));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        int _buf2 = java.lang.Math.ceilMod(this.z, otherZ);
        d.w = java.lang.Math.ceilMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        int _buf2 = java.lang.Math.ceilMod(this.z, otherZ);
        d.w = java.lang.Math.ceilMod(this.w, otherW);
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 ceilMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        int _buf2 = java.lang.Math.ceilMod(this.z, otherZ);
        d.w = java.lang.Math.ceilMod(this.w, otherW);
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
    public Byte4 div(byte scalar, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        int _buf2 = this.z / scalar;
        d.w = (byte) (this.w / scalar);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
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
    public Short4 div(byte scalar, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int4 div(byte scalar, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 div(byte scalar, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 div(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 div(Byte4R other, @Mutated Byte4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 div(Byte4R other, @Mutated Short4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 div(Byte4R other, @Mutated Int4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 div(Byte4R other, @Mutated Long4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 div(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 div(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        int _buf2 = this.z / otherZ;
        d.w = (byte) (this.w / otherW);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) (integer division, truncating toward zero) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 div(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 div(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 div(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 div(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 floorDiv(byte scalar, @Mutated Byte4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Short4 floorDiv(byte scalar, @Mutated Short4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Int4 floorDiv(byte scalar, @Mutated Int4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Long4 floorDiv(byte scalar, @Mutated Long4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Double4 floorDiv(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 floorDiv(Byte4R other, @Mutated Byte4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 floorDiv(Byte4R other, @Mutated Short4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 floorDiv(Byte4R other, @Mutated Int4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 floorDiv(Byte4R other, @Mutated Long4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 floorDiv(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        int _buf2 = Math.floorDiv(this.z, otherZ);
        d.w = (byte) (Math.floorDiv(this.w, otherW));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 floorDiv(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 floorMod(byte scalar, @Mutated Byte4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Short4 floorMod(byte scalar, @Mutated Short4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Int4 floorMod(byte scalar, @Mutated Int4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Long4 floorMod(byte scalar, @Mutated Long4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Double4 floorMod(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 floorMod(Byte4R other, @Mutated Byte4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 floorMod(Byte4R other, @Mutated Short4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 floorMod(Byte4R other, @Mutated Int4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 floorMod(Byte4R other, @Mutated Long4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 floorMod(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        int _buf2 = Math.floorMod(this.z, otherZ);
        d.w = (byte) (Math.floorMod(this.w, otherW));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 floorMod(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 mul(byte scalar, @Mutated Byte4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Short4 mul(byte scalar, @Mutated Short4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Int4 mul(byte scalar, @Mutated Int4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Long4 mul(byte scalar, @Mutated Long4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Double4 mul(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 mul(Byte4R other, @Mutated Byte4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 mul(Byte4R other, @Mutated Short4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 mul(Byte4R other, @Mutated Int4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 mul(Byte4R other, @Mutated Long4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 mul(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 mul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (otherX * this.x);
        d.y = (byte) (otherY * this.y);
        d.z = (byte) (otherZ * this.z);
        d.w = (byte) (otherW * this.w);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 mul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 mul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 mul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 mul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 negate(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (-this.x);
        d.y = (byte) (-this.y);
        d.z = (byte) (-this.z);
        d.w = (byte) (-this.w);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte4 rem(byte scalar, @Mutated Byte4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Short4 rem(byte scalar, @Mutated Short4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Int4 rem(byte scalar, @Mutated Int4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Long4 rem(byte scalar, @Mutated Long4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Double4 rem(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 rem(Byte4R other, @Mutated Byte4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 rem(Byte4R other, @Mutated Short4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 rem(Byte4R other, @Mutated Int4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 rem(Byte4R other, @Mutated Long4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 rem(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 rem(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        int _buf2 = this.z % otherZ;
        d.w = (byte) (this.w % otherW);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) (the remainder carries the sign of the
     * dividend, exactly Java's {@code %}, so it pairs with {@code div}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 rem(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 rem(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 rem(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 rem(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 sub(Byte4R other, @Mutated Byte4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 sub(Byte4R other, @Mutated Short4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 sub(Byte4R other, @Mutated Int4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 sub(Byte4R other, @Mutated Long4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 sub(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 sub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x - otherX);
        d.y = (byte) (this.y - otherY);
        d.z = (byte) (this.z - otherZ);
        d.w = (byte) (this.w - otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 sub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 sub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 sub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 sub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 and(Byte4R other, @Mutated Byte4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 and(Byte4R other, @Mutated Short4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 and(Byte4R other, @Mutated Int4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 and(Byte4R other, @Mutated Long4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 and(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 and(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x & otherX);
        d.y = (byte) (this.y & otherY);
        d.z = (byte) (this.z & otherZ);
        d.w = (byte) (this.w & otherW);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 and(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 and(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 and(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 and(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 bitCount(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.bitCount(this.x));
        d.y = (byte) (Math.bitCount(this.y));
        d.z = (byte) (Math.bitCount(this.z));
        d.w = (byte) (Math.bitCount(this.w));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte4 not(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (~this.x);
        d.y = (byte) (~this.y);
        d.z = (byte) (~this.z);
        d.w = (byte) (~this.w);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte4 numberOfLeadingZeros(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.numberOfLeadingZeros(this.x));
        d.y = (byte) (Math.numberOfLeadingZeros(this.y));
        d.z = (byte) (Math.numberOfLeadingZeros(this.z));
        d.w = (byte) (Math.numberOfLeadingZeros(this.w));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte4 numberOfTrailingZeros(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.numberOfTrailingZeros(this.x));
        d.y = (byte) (Math.numberOfTrailingZeros(this.y));
        d.z = (byte) (Math.numberOfTrailingZeros(this.z));
        d.w = (byte) (Math.numberOfTrailingZeros(this.w));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte4 or(Byte4R other, @Mutated Byte4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 or(Byte4R other, @Mutated Short4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 or(Byte4R other, @Mutated Int4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 or(Byte4R other, @Mutated Long4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 or(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 or(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x | otherX);
        d.y = (byte) (this.y | otherY);
        d.z = (byte) (this.z | otherZ);
        d.w = (byte) (this.w | otherW);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 or(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 or(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 or(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 or(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public Byte4 reverseBits(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.reverseBits(this.x));
        d.y = (byte) (Math.reverseBits(this.y));
        d.z = (byte) (Math.reverseBits(this.z));
        d.w = (byte) (Math.reverseBits(this.w));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte4 reverseBytes(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.reverseBytes(this.x));
        d.y = (byte) (Math.reverseBytes(this.y));
        d.z = (byte) (Math.reverseBytes(this.z));
        d.w = (byte) (Math.reverseBytes(this.w));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte4 rotateLeft(byte distance, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.rotateLeft(this.x, distance));
        d.y = (byte) (Math.rotateLeft(this.y, distance));
        d.z = (byte) (Math.rotateLeft(this.z, distance));
        d.w = (byte) (Math.rotateLeft(this.w, distance));
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
    public Short4 rotateLeft(byte distance, @Mutated Short4 dest) {
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
    public Int4 rotateLeft(byte distance, @Mutated Int4 dest) {
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
    public Long4 rotateLeft(byte distance, @Mutated Long4 dest) {
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
    public Double4 rotateLeft(byte distance, @Mutated Double4 dest) {
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
    public Byte4 rotateRight(byte distance, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.rotateRight(this.x, distance));
        d.y = (byte) (Math.rotateRight(this.y, distance));
        d.z = (byte) (Math.rotateRight(this.z, distance));
        d.w = (byte) (Math.rotateRight(this.w, distance));
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
    public Short4 rotateRight(byte distance, @Mutated Short4 dest) {
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
    public Int4 rotateRight(byte distance, @Mutated Int4 dest) {
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
    public Long4 rotateRight(byte distance, @Mutated Long4 dest) {
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
    public Double4 rotateRight(byte distance, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
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
    public Byte4 shl(byte shift, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x << (shift & 7));
        d.y = (byte) (this.y << (shift & 7));
        d.z = (byte) (this.z << (shift & 7));
        d.w = (byte) (this.w << (shift & 7));
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
    public Short4 shl(byte shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x << (shift & 7));
        d.y = (short) (this.y << (shift & 7));
        d.z = (short) (this.z << (shift & 7));
        d.w = (short) (this.w << (shift & 7));
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
    public Int4 shl(byte shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
        d.w = this.w << (shift & 7);
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
    public Long4 shl(byte shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
        d.w = this.w << (shift & 7);
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
    public Double4 shl(byte shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
        d.w = this.w << (shift & 7);
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
    public Byte4 shr(byte shift, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x >> (shift & 7));
        d.y = (byte) (this.y >> (shift & 7));
        d.z = (byte) (this.z >> (shift & 7));
        d.w = (byte) (this.w >> (shift & 7));
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
    public Short4 shr(byte shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x >> (shift & 7));
        d.y = (short) (this.y >> (shift & 7));
        d.z = (short) (this.z >> (shift & 7));
        d.w = (short) (this.w >> (shift & 7));
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
    public Int4 shr(byte shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
        d.w = this.w >> (shift & 7);
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
    public Long4 shr(byte shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
        d.w = this.w >> (shift & 7);
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
    public Double4 shr(byte shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
        d.w = this.w >> (shift & 7);
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
    public Byte4 ushr(byte shift, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (byte) ((this.y & 0xFF) >>> (shift & 7));
        d.z = (byte) ((this.z & 0xFF) >>> (shift & 7));
        d.w = (byte) ((this.w & 0xFF) >>> (shift & 7));
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
    public Short4 ushr(byte shift, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (short) ((this.y & 0xFF) >>> (shift & 7));
        d.z = (short) ((this.z & 0xFF) >>> (shift & 7));
        d.w = (short) ((this.w & 0xFF) >>> (shift & 7));
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
    public Int4 ushr(byte shift, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
        d.w = (this.w & 0xFF) >>> (shift & 7);
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
    public Long4 ushr(byte shift, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
        d.w = (this.w & 0xFF) >>> (shift & 7);
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
    public Double4 ushr(byte shift, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
        d.w = (this.w & 0xFF) >>> (shift & 7);
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
    public Byte4 xor(Byte4R other, @Mutated Byte4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 xor(Byte4R other, @Mutated Short4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 xor(Byte4R other, @Mutated Int4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 xor(Byte4R other, @Mutated Long4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 xor(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 xor(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x ^ otherX);
        d.y = (byte) (this.y ^ otherY);
        d.z = (byte) (this.z ^ otherZ);
        d.w = (byte) (this.w ^ otherW);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 xor(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 xor(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 xor(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 xor(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
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
    public @Mutated Byte4 set(Byte4R v) {
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
    @Mutated public Byte4 set(byte vX, byte vY, byte vZ, byte vW) {
        this.x = (byte) (vX);
        this.y = (byte) (vY);
        this.z = (byte) (vZ);
        this.w = (byte) (vW);
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
    public Byte4 set(byte s, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (s);
        d.y = (byte) (s);
        d.z = (byte) (s);
        d.w = (byte) (s);
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
    public Short4 set(byte s, @Mutated Short4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Int4 set(byte s, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Long4 set(byte s, @Mutated Long4 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 set(byte s, @Mutated Double4 dest) {
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
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
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
    @Mutated public Byte4 makeZero() {
        this.x = (byte) (0);
        this.y = (byte) (0);
        this.z = (byte) (0);
        this.w = (byte) (0);
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
    public Byte4 absolute(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.abs(this.x));
        d.y = (byte) (Math.abs(this.y));
        d.z = (byte) (Math.abs(this.z));
        d.w = (byte) (Math.abs(this.w));
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
    public Short4 absolute(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.abs(this.x));
        d.y = (short) (Math.abs(this.y));
        d.z = (short) (Math.abs(this.z));
        d.w = (short) (Math.abs(this.w));
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
    public Int4 absolute(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
        d.z = Math.abs(this.z);
        d.w = Math.abs(this.w);
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
    public Long4 absolute(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
        d.z = Math.abs(this.z);
        d.w = Math.abs(this.w);
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
    public Double4 absolute(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.abs(this.x);
        d.y = Math.abs(this.y);
        d.z = Math.abs(this.z);
        d.w = Math.abs(this.w);
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
    public Byte4 clamp(byte min, byte max, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.min(Math.max(this.x, min), max));
        d.y = (byte) (Math.min(Math.max(this.y, min), max));
        d.z = (byte) (Math.min(Math.max(this.z, min), max));
        d.w = (byte) (Math.min(Math.max(this.w, min), max));
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
    public Short4 clamp(byte min, byte max, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.min(Math.max(this.x, min), max));
        d.y = (short) (Math.min(Math.max(this.y, min), max));
        d.z = (short) (Math.min(Math.max(this.z, min), max));
        d.w = (short) (Math.min(Math.max(this.w, min), max));
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
    public Int4 clamp(byte min, byte max, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.min(Math.max(this.x, min), max);
        d.y = Math.min(Math.max(this.y, min), max);
        d.z = Math.min(Math.max(this.z, min), max);
        d.w = Math.min(Math.max(this.w, min), max);
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
    public Long4 clamp(byte min, byte max, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.min(Math.max(this.x, min), max);
        d.y = Math.min(Math.max(this.y, min), max);
        d.z = Math.min(Math.max(this.z, min), max);
        d.w = Math.min(Math.max(this.w, min), max);
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
    public Double4 clamp(byte min, byte max, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(Math.max(this.x, min), max);
        d.y = Math.min(Math.max(this.y, min), max);
        d.z = Math.min(Math.max(this.z, min), max);
        d.w = Math.min(Math.max(this.w, min), max);
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
    public Byte4 clamp(Byte4R min, Byte4R max, @Mutated Byte4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
    public Short4 clamp(Byte4R min, Byte4R max, @Mutated Short4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
    public Int4 clamp(Byte4R min, Byte4R max, @Mutated Int4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
    public Long4 clamp(Byte4R min, Byte4R max, @Mutated Long4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
    public Double4 clamp(Byte4R min, Byte4R max, @Mutated Double4 dest) {
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
    public Byte4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.min(Math.max(this.x, minX), maxX));
        d.y = (byte) (Math.min(Math.max(this.y, minY), maxY));
        d.z = (byte) (Math.min(Math.max(this.z, minZ), maxZ));
        d.w = (byte) (Math.min(Math.max(this.w, minW), maxW));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.min(Math.max(this.x, minX), maxX));
        d.y = (short) (Math.min(Math.max(this.y, minY), maxY));
        d.z = (short) (Math.min(Math.max(this.z, minZ), maxZ));
        d.w = (short) (Math.min(Math.max(this.w, minW), maxW));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
        d.z = Math.min(Math.max(this.z, minZ), maxZ);
        d.w = Math.min(Math.max(this.w, minW), maxW);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
        d.z = Math.min(Math.max(this.z, minZ), maxZ);
        d.w = Math.min(Math.max(this.w, minW), maxW);
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 clamp(byte minX, byte minY, byte minZ, byte minW, byte maxX, byte maxY, byte maxZ, byte maxW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(Math.max(this.x, minX), maxX);
        d.y = Math.min(Math.max(this.y, minY), maxY);
        d.z = Math.min(Math.max(this.z, minZ), maxZ);
        d.w = Math.min(Math.max(this.w, minW), maxW);
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
        return this.w + (this.z + (this.x + this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public byte compMax() {
        return (byte) (Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        return (byte) (Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w));
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
    public Byte4 cross(Byte4R v, Byte4R w, @Mutated Byte4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Short4 cross(Byte4R v, Byte4R w, @Mutated Short4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Int4 cross(Byte4R v, Byte4R w, @Mutated Int4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long4 cross(Byte4R v, Byte4R w, @Mutated Long4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cross(Byte4R v, Byte4R w, @Mutated Double4 dest) {
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
    public Byte4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = this.y * _t12 - this.z * _t13 + this.w * _t14;
        int _buf1 = this.z * _t15 - this.x * _t12 - this.w * _t16;
        int _buf2 = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = (byte) (this.y * _t16 - this.x * _t14 - this.z * _t17);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        d.z = (byte) (_buf2);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = this.y * _t12 - this.z * _t13 + this.w * _t14;
        int _buf1 = this.z * _t15 - this.x * _t12 - this.w * _t16;
        int _buf2 = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = (short) (this.y * _t16 - this.x * _t14 - this.z * _t17);
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        d.z = (short) (_buf2);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = this.y * _t12 - this.z * _t13 + this.w * _t14;
        int _buf1 = this.z * _t15 - this.x * _t12 - this.w * _t16;
        int _buf2 = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = this.y * _t12 - this.z * _t13 + this.w * _t14;
        int _buf1 = this.z * _t15 - this.x * _t12 - this.w * _t16;
        int _buf2 = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 cross(byte vX, byte vY, byte vZ, byte vW, byte wX, byte wY, byte wZ, byte wW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = this.y * _t12 - this.z * _t13 + this.w * _t14;
        int _buf1 = this.z * _t15 - this.x * _t12 - this.w * _t16;
        int _buf2 = this.x * _t13 - this.y * _t15 + this.w * _t17;
        d.w = this.y * _t16 - this.x * _t14 - this.z * _t17;
        d.x = _buf0;
        d.y = _buf1;
        d.z = _buf2;
        return d;
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
    public int distanceSquared(Byte4R other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int distanceSquared(byte otherX, byte otherY, byte otherZ, byte otherW) {
        int _t0 = this.x - otherX;
        int _t1 = this.y - otherY;
        int _t2 = this.z - otherZ;
        int _t3 = this.w - otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
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
    public int dot(Byte4R other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int dot(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return otherX * this.x + otherY * this.y + otherZ * this.z + otherW * this.w;
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
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
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
    public int manhattanDistance(Byte4R other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
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
    public int manhattanDistance(byte otherX, byte otherY, byte otherZ, byte otherW) {
        return Math.abs(this.x - otherX) + Math.abs(this.y - otherY) + Math.abs(this.z - otherZ) + Math.abs(this.w - otherW);
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
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z) + Math.abs(this.w);
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
    public Byte4 max(byte scalar, @Mutated Byte4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Short4 max(byte scalar, @Mutated Short4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Int4 max(byte scalar, @Mutated Int4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Long4 max(byte scalar, @Mutated Long4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Double4 max(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 max(Byte4R other, @Mutated Byte4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 max(Byte4R other, @Mutated Short4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 max(Byte4R other, @Mutated Int4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 max(Byte4R other, @Mutated Long4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 max(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 max(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.max(this.x, otherX));
        d.y = (byte) (Math.max(this.y, otherY));
        d.z = (byte) (Math.max(this.z, otherZ));
        d.w = (byte) (Math.max(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 max(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.max(this.x, otherX));
        d.y = (short) (Math.max(this.y, otherY));
        d.z = (short) (Math.max(this.z, otherZ));
        d.w = (short) (Math.max(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 max(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 max(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 max(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.max(this.x, otherX);
        d.y = Math.max(this.y, otherY);
        d.z = Math.max(this.z, otherZ);
        d.w = Math.max(this.w, otherW);
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
    public Byte4 min(byte scalar, @Mutated Byte4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Short4 min(byte scalar, @Mutated Short4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Int4 min(byte scalar, @Mutated Int4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Long4 min(byte scalar, @Mutated Long4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Double4 min(byte scalar, @Mutated Double4 dest) {
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
    public Byte4 min(Byte4R other, @Mutated Byte4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 min(Byte4R other, @Mutated Short4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 min(Byte4R other, @Mutated Int4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 min(Byte4R other, @Mutated Long4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 min(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 min(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.min(this.x, otherX));
        d.y = (byte) (Math.min(this.y, otherY));
        d.z = (byte) (Math.min(this.z, otherZ));
        d.w = (byte) (Math.min(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short4 min(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (Math.min(this.x, otherX));
        d.y = (short) (Math.min(this.y, otherY));
        d.z = (short) (Math.min(this.z, otherZ));
        d.w = (short) (Math.min(this.w, otherW));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int4 min(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long4 min(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double4 min(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.min(this.x, otherX);
        d.y = Math.min(this.y, otherY);
        d.z = Math.min(this.z, otherZ);
        d.w = Math.min(this.w, otherW);
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
    public Byte4 sign(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (Math.signum(this.x));
        d.y = (byte) (Math.signum(this.y));
        d.z = (byte) (Math.signum(this.z));
        d.w = (byte) (Math.signum(this.w));
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte4 satAdd(Byte4R other, @Mutated Byte4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satAdd(Byte4R other, @Mutated Short4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 satAdd(Byte4R other, @Mutated Int4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 satAdd(Byte4R other, @Mutated Long4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 satAdd(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
        d.w = (byte) (org.joml2.SaturatingMath.satAddB(this.w, otherW));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
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
    public Short4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satAddB(this.w, otherW));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddB(this.w, otherW);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddB(this.w, otherW);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double4 satAdd(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAddB(this.w, otherW);
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
    public Byte4 satMul(Byte4R other, @Mutated Byte4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satMul(Byte4R other, @Mutated Short4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 satMul(Byte4R other, @Mutated Int4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 satMul(Byte4R other, @Mutated Long4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 satMul(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
        d.w = (byte) (org.joml2.SaturatingMath.satMulB(this.w, otherW));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
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
    public Short4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satMulB(this.w, otherW));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulB(this.w, otherW);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulB(this.w, otherW);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double4 satMul(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMulB(this.w, otherW);
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
    public Byte4 satNegate(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (byte) (org.joml2.SaturatingMath.satNegB(this.y));
        d.z = (byte) (org.joml2.SaturatingMath.satNegB(this.z));
        d.w = (byte) (org.joml2.SaturatingMath.satNegB(this.w));
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
    public Short4 satNegate(@Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegB(this.y));
        d.z = (short) (org.joml2.SaturatingMath.satNegB(this.z));
        d.w = (short) (org.joml2.SaturatingMath.satNegB(this.w));
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
    public Int4 satNegate(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
        d.w = org.joml2.SaturatingMath.satNegB(this.w);
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
    public Long4 satNegate(@Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
        d.w = org.joml2.SaturatingMath.satNegB(this.w);
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
    public Double4 satNegate(@Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
        d.w = org.joml2.SaturatingMath.satNegB(this.w);
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
    public Byte4 satSub(Byte4R other, @Mutated Byte4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satSub(Byte4R other, @Mutated Short4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Int4 satSub(Byte4R other, @Mutated Int4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Long4 satSub(Byte4R other, @Mutated Long4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Double4 satSub(Byte4R other, @Mutated Double4 dest) {
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
    public Byte4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
        d.w = (byte) (org.joml2.SaturatingMath.satSubB(this.w, otherW));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
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
    public Short4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Short4 dest) {
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
        d.w = (short) (org.joml2.SaturatingMath.satSubB(this.w, otherW));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubB(this.w, otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Long4 dest) {
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubB(this.w, otherW);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double4 satSub(byte otherX, byte otherY, byte otherZ, byte otherW, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSubB(this.w, otherW);
        return d;
    }

    public byte x() { return this.x; }
    public byte y() { return this.y; }
    public byte z() { return this.z; }
    public byte w() { return this.w; }

    public Byte2 xx(@Mutated Byte2 dest) {
        byte _v0 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Byte2 xy(@Mutated Byte2 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 xz(@Mutated Byte2 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 xw(@Mutated Byte2 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 yx(@Mutated Byte2 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
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

    public Byte2 yz(@Mutated Byte2 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 yw(@Mutated Byte2 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 zx(@Mutated Byte2 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 zy(@Mutated Byte2 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 zz(@Mutated Byte2 dest) {
        byte _v0 = this.z;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Byte2 zw(@Mutated Byte2 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 wx(@Mutated Byte2 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 wy(@Mutated Byte2 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 wz(@Mutated Byte2 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Byte2 ww(@Mutated Byte2 dest) {
        byte _v0 = this.w;
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

    public Byte3 xxz(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 xxw(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
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
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 xyz(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xyw(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xzx(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 xzy(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xzz(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 xzw(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xwx(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 xwy(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xwz(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xww(@Mutated Byte3 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 yxx(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
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

    public Byte3 yxz(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 yxw(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
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

    public Byte3 yyz(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 yyw(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 yzx(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 yzy(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 yzz(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 yzw(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 ywx(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 ywy(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 ywz(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 yww(@Mutated Byte3 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 zxx(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 zxy(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zxz(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 zxw(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zyx(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zyy(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 zyz(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 zyw(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zzx(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 zzy(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 zzz(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Byte3 zzw(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 zwx(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zwy(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zwz(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 zww(@Mutated Byte3 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 wxx(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 wxy(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wxz(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wxw(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 wyx(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wyy(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 wyz(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wyw(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 wzx(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wzy(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 wzz(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 wzw(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Byte3 wwx(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 wwy(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 wwz(@Mutated Byte3 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Byte3 www(@Mutated Byte3 dest) {
        byte _v0 = this.w;
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

    public Byte4 xxxz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 xxxw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
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

    public Byte4 xxyz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxyw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxzx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xxzy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxzz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 xxzw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxwx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xxwy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxwz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xxww(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
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

    public Byte4 xyxz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 xyxw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
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
        byte _v0 = this.x;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 xyyz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xyyw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xyzx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xyzy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xyzz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xyzw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.z;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xywx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xywy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xywz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xyww(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzxx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 xzxy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzxz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 xzxw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzyx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xzyy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzyz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xzyw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xzzx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xzzy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzzz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 xzzw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzwx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xzwy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xzwz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xzww(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwxx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 xwxy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwxz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwxw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 xwyx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xwyy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwyz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xwyw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xwzx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 xwzy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 xwzz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwzw(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xwwx(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 xwwy(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwwz(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xwww(@Mutated Byte4 dest) {
        byte _v0 = this.x;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxxx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
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

    public Byte4 yxxz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yxxw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
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

    public Byte4 yxyz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 yxyw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 yxzx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxzy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 yxzz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 yxzw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 yxwx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 yxwy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 yxwz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 yxww(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
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

    public Byte4 yyxz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yyxw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
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

    public Byte4 yyyz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 yyyw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 yyzx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yyzy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 yyzz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yyzw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yywx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yywy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 yywz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yyww(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yzxx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 yzxy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 yzxz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 yzxw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 yzyx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 yzyy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 yzyz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 yzyw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 yzzx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yzzy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 yzzz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 yzzw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 yzwx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 yzwy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 yzwz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 yzww(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.z;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywxx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywxy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 ywxz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 ywxw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 ywyx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywyy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 ywyz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywyw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 ywzx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 ywzy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 ywzz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywzw(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 ywwx(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywwy(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 ywwz(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 ywww(@Mutated Byte4 dest) {
        byte _v0 = this.y;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxxx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxxy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zxxz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zxxw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zxyx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxyy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zxyz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zxyw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zxzx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxzy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zxzz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 zxzw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zxwx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxwy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zxwz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zxww(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyxx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyxy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zyxz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zyxw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        byte _v3 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zyyx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyyy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zyyz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zyyw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyzx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyzy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zyzz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 zyzw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zywx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zywy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zywz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zyww(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzxx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zzxy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzxz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zzxw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzyx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzyy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zzyz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zzyw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        byte _v2 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzzx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zzzy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zzzz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 zzzw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zzwx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzwy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zzwz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zzww(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zwxx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwxy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zwxz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zwxw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zwyx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 zwyy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwyz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 zwyw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zwzx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwzy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwzz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 zwzw(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 zwwx(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwwy(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zwwz(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 zwww(@Mutated Byte4 dest) {
        byte _v0 = this.z;
        byte _v1 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wxxx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wxxy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxxz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxxw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wxyx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wxyy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxyz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wxyw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wxzx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wxzy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wxzz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxzw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wxwx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wxwy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxwz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wxww(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 wyxx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wyxy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wyxz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        byte _v3 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wyxw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wyyx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wyyy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wyyz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wyyw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wyzx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wyzy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wyzz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wyzw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wywx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wywy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wywz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wyww(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 wzxx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzxy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        byte _v3 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wzxz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wzxw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wzyx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        byte _v3 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Byte4 wzyy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzyz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 wzyw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Byte4 wzzx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzzy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzzz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wzzw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wzwx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzwy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Byte4 wzwz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wzww(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Byte4 wwxx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwxy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwxz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwxw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wwyx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwyy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwyz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwyw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wwzx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwzy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 wwzz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwzw(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Byte4 wwwx(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwwy(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwwz(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Byte4 wwww(@Mutated Byte4 dest) {
        byte _v0 = this.w;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Byte4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte4Impl)) return false;
        Byte4Impl o = (Byte4Impl) obj;
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

    @Override public boolean equalsEpsilon(Byte4R other, byte epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(w - other.w()) <= epsilon;
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Byte4 load(byte[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
        return this;
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Byte4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Byte4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Byte4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Byte4 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Byte4 load(short[] src, int offset) {
        this.x = (byte) src[offset + 0];
        this.y = (byte) src[offset + 1];
        this.z = (byte) src[offset + 2];
        this.w = (byte) src[offset + 3];
        return this;
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Byte4 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, index, buf);
    }
    public Byte4 loadShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, index, buf);
    }
    public Byte4 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }
    @Mutated public Byte4 loadShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadShortUnsafe(this, address);
    }
    public MemorySegment storeShort(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeShort(this, offset, dest);
    }
    public Byte4 loadShort(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadShort(this, offset, src);
    }

}
