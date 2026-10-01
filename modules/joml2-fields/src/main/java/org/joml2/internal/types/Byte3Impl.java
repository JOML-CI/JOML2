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
 * Generated implementation of {@link Byte3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Byte3Impl implements Byte3 {

    public byte x;
    public byte y;
    public byte z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Byte3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte3SegOpsUnsafe()
                        : new Byte3SegOpsMS();
        static final Byte3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte3BbOpsUnsafe()
                        : new Byte3BbOpsApi();
        static final Byte3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Byte3RawOpsUnsafe()
                        : new Byte3RawOpsApi();
    }

    public Byte3Impl() {
    }

    public Byte3Impl(byte x, byte y, byte z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Byte3Impl(Byte3R src) {
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
    public Byte3 add(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (other.x() + this.x);
        d.y = (byte) (otherY + this.y);
        d.z = (byte) (otherZ + this.z);
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
    public Short3 add(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (other.x() + this.x);
        d.y = (short) (otherY + this.y);
        d.z = (short) (otherZ + this.z);
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
    public Int3 add(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
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
    public Long3 add(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
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
    public Double3 add(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 add(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (otherX + this.x);
        d.y = (byte) (otherY + this.y);
        d.z = (byte) (otherZ + this.z);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 add(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 add(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 add(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 add(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 ceilDiv(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, scalar);
        int _buf1 = java.lang.Math.ceilDiv(this.y, scalar);
        d.z = (byte) (java.lang.Math.ceilDiv(this.z, scalar));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 ceilDiv(byte scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, scalar);
        int _buf1 = java.lang.Math.ceilDiv(this.y, scalar);
        d.z = (short) (java.lang.Math.ceilDiv(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
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
    public Int3 ceilDiv(byte scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, scalar);
        int _buf1 = java.lang.Math.ceilDiv(this.y, scalar);
        d.z = java.lang.Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Long3 ceilDiv(byte scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, scalar);
        int _buf1 = java.lang.Math.ceilDiv(this.y, scalar);
        d.z = java.lang.Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Double3 ceilDiv(byte scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, scalar);
        int _buf1 = java.lang.Math.ceilDiv(this.y, scalar);
        d.z = java.lang.Math.ceilDiv(this.z, scalar);
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
    public Byte3 ceilDiv(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, other.x());
        int _buf1 = java.lang.Math.ceilDiv(this.y, other.y());
        d.z = (byte) (java.lang.Math.ceilDiv(this.z, other.z()));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 ceilDiv(Byte3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, other.x());
        int _buf1 = java.lang.Math.ceilDiv(this.y, other.y());
        d.z = (short) (java.lang.Math.ceilDiv(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
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
    public Int3 ceilDiv(Byte3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, other.x());
        int _buf1 = java.lang.Math.ceilDiv(this.y, other.y());
        d.z = java.lang.Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
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
    public Long3 ceilDiv(Byte3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, other.x());
        int _buf1 = java.lang.Math.ceilDiv(this.y, other.y());
        d.z = java.lang.Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
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
    public Double3 ceilDiv(Byte3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, other.x());
        int _buf1 = java.lang.Math.ceilDiv(this.y, other.y());
        d.z = java.lang.Math.ceilDiv(this.z, other.z());
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
    public Byte3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = (byte) (java.lang.Math.ceilDiv(this.z, otherZ));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = (short) (java.lang.Math.ceilDiv(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = java.lang.Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = java.lang.Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilDiv(this.x, otherX);
        int _buf1 = java.lang.Math.ceilDiv(this.y, otherY);
        d.z = java.lang.Math.ceilDiv(this.z, otherZ);
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
    public Byte3 ceilMod(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, scalar);
        int _buf1 = java.lang.Math.ceilMod(this.y, scalar);
        d.z = (byte) (java.lang.Math.ceilMod(this.z, scalar));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 ceilMod(byte scalar, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, scalar);
        int _buf1 = java.lang.Math.ceilMod(this.y, scalar);
        d.z = (short) (java.lang.Math.ceilMod(this.z, scalar));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
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
    public Int3 ceilMod(byte scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, scalar);
        int _buf1 = java.lang.Math.ceilMod(this.y, scalar);
        d.z = java.lang.Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Long3 ceilMod(byte scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, scalar);
        int _buf1 = java.lang.Math.ceilMod(this.y, scalar);
        d.z = java.lang.Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
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
    public Double3 ceilMod(byte scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, scalar);
        int _buf1 = java.lang.Math.ceilMod(this.y, scalar);
        d.z = java.lang.Math.ceilMod(this.z, scalar);
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
    public Byte3 ceilMod(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, other.x());
        int _buf1 = java.lang.Math.ceilMod(this.y, other.y());
        d.z = (byte) (java.lang.Math.ceilMod(this.z, other.z()));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 ceilMod(Byte3R other, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, other.x());
        int _buf1 = java.lang.Math.ceilMod(this.y, other.y());
        d.z = (short) (java.lang.Math.ceilMod(this.z, other.z()));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
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
    public Int3 ceilMod(Byte3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, other.x());
        int _buf1 = java.lang.Math.ceilMod(this.y, other.y());
        d.z = java.lang.Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
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
    public Long3 ceilMod(Byte3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, other.x());
        int _buf1 = java.lang.Math.ceilMod(this.y, other.y());
        d.z = java.lang.Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
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
    public Double3 ceilMod(Byte3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, other.x());
        int _buf1 = java.lang.Math.ceilMod(this.y, other.y());
        d.z = java.lang.Math.ceilMod(this.z, other.z());
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
    public Byte3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = (byte) (java.lang.Math.ceilMod(this.z, otherZ));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = (short) (java.lang.Math.ceilMod(this.z, otherZ));
        d.x = (short) (_buf0);
        d.y = (short) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = java.lang.Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = java.lang.Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = java.lang.Math.ceilMod(this.x, otherX);
        int _buf1 = java.lang.Math.ceilMod(this.y, otherY);
        d.z = java.lang.Math.ceilMod(this.z, otherZ);
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
    public Byte3 div(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x / scalar;
        int _buf1 = this.y / scalar;
        d.z = (byte) (this.z / scalar);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 div(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 div(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 div(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x / other.x();
        int _buf1 = this.y / other.y();
        d.z = (byte) (this.z / other.z());
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 div(Byte3R other, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 div(Byte3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(Byte3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(Byte3R other, @Mutated Double3 dest) {
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
    public Byte3 div(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x / otherX;
        int _buf1 = this.y / otherY;
        d.z = (byte) (this.z / otherZ);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ})
     * (integer division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 div(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 div(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 div(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 div(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 floorDiv(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, scalar);
        int _buf1 = Math.floorDiv(this.y, scalar);
        d.z = (byte) (Math.floorDiv(this.z, scalar));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 floorDiv(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorDiv(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 floorDiv(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, other.x());
        int _buf1 = Math.floorDiv(this.y, other.y());
        d.z = (byte) (Math.floorDiv(this.z, other.z()));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 floorDiv(Byte3R other, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorDiv(Byte3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(Byte3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(Byte3R other, @Mutated Double3 dest) {
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
    public Byte3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorDiv(this.x, otherX);
        int _buf1 = Math.floorDiv(this.y, otherY);
        d.z = (byte) (Math.floorDiv(this.z, otherZ));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor division of this vector by ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 floorMod(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorMod(this.x, scalar);
        int _buf1 = Math.floorMod(this.y, scalar);
        d.z = (byte) (Math.floorMod(this.z, scalar));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 floorMod(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorMod(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 floorMod(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorMod(this.x, other.x());
        int _buf1 = Math.floorMod(this.y, other.y());
        d.z = (byte) (Math.floorMod(this.z, other.z()));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 floorMod(Byte3R other, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 floorMod(Byte3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(Byte3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(Byte3R other, @Mutated Double3 dest) {
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
    public Byte3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = Math.floorMod(this.x, otherX);
        int _buf1 = Math.floorMod(this.y, otherY);
        d.z = (byte) (Math.floorMod(this.z, otherZ));
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise floor modulus of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 mul(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (scalar * this.x);
        d.y = (byte) (scalar * this.y);
        d.z = (byte) (scalar * this.z);
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
    public Short3 mul(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Int3 mul(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 mul(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (other.x() * this.x);
        d.y = (byte) (otherY * this.y);
        d.z = (byte) (otherZ * this.z);
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
    public Short3 mul(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (other.x() * this.x);
        d.y = (short) (otherY * this.y);
        d.z = (short) (otherZ * this.z);
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
    public Int3 mul(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
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
    public Long3 mul(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
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
    public Double3 mul(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (otherX * this.x);
        d.y = (byte) (otherY * this.y);
        d.z = (byte) (otherZ * this.z);
        return d;
    }


    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 negate(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (-this.x);
        d.y = (byte) (-this.y);
        d.z = (byte) (-this.z);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte3 rem(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x % scalar;
        int _buf1 = this.y % scalar;
        d.z = (byte) (this.z % scalar);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 rem(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rem(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 rem(Byte3R other, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x % other.x();
        int _buf1 = this.y % other.y();
        d.z = (byte) (this.z % other.z());
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
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
    public Short3 rem(Byte3R other, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Int3 rem(Byte3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(Byte3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(Byte3R other, @Mutated Double3 dest) {
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
    public Byte3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _buf0 = this.x % otherX;
        int _buf1 = this.y % otherY;
        d.z = (byte) (this.z % otherZ);
        d.x = (byte) (_buf0);
        d.y = (byte) (_buf1);
        return d;
    }


    /**
     * Compute the component-wise truncated remainder of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) (the remainder carries the sign of the dividend, exactly
     * Java's {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 sub(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x - other.x());
        d.y = (byte) (this.y - otherY);
        d.z = (byte) (this.z - otherZ);
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
    public Short3 sub(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x - other.x());
        d.y = (short) (this.y - otherY);
        d.z = (short) (this.z - otherZ);
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
    public Int3 sub(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
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
    public Long3 sub(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
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
    public Double3 sub(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x - otherX);
        d.y = (byte) (this.y - otherY);
        d.z = (byte) (this.z - otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 and(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x & other.x());
        d.y = (byte) (this.y & otherY);
        d.z = (byte) (this.z & otherZ);
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
    public Short3 and(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Int3 and(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Double3 and(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 and(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x & otherX);
        d.y = (byte) (this.y & otherY);
        d.z = (byte) (this.z & otherZ);
        return d;
    }


    /**
     * Compute the bitwise AND of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 and(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 and(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 and(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 and(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 bitCount(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.bitCount(this.x));
        d.y = (byte) (Math.bitCount(this.y));
        d.z = (byte) (Math.bitCount(this.z));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte3 not(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (~this.x);
        d.y = (byte) (~this.y);
        d.z = (byte) (~this.z);
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte3 numberOfLeadingZeros(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.numberOfLeadingZeros(this.x));
        d.y = (byte) (Math.numberOfLeadingZeros(this.y));
        d.z = (byte) (Math.numberOfLeadingZeros(this.z));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte3 numberOfTrailingZeros(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.numberOfTrailingZeros(this.x));
        d.y = (byte) (Math.numberOfTrailingZeros(this.y));
        d.z = (byte) (Math.numberOfTrailingZeros(this.z));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte3 or(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x | other.x());
        d.y = (byte) (this.y | otherY);
        d.z = (byte) (this.z | otherZ);
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
    public Short3 or(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Int3 or(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 or(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 or(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x | otherX);
        d.y = (byte) (this.y | otherY);
        d.z = (byte) (this.z | otherZ);
        return d;
    }


    /**
     * Compute the bitwise OR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 or(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 or(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 or(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 or(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 reverseBits(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.reverseBits(this.x));
        d.y = (byte) (Math.reverseBits(this.y));
        d.z = (byte) (Math.reverseBits(this.z));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte3 reverseBytes(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.reverseBytes(this.x));
        d.y = (byte) (Math.reverseBytes(this.y));
        d.z = (byte) (Math.reverseBytes(this.z));
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
     * The operation is evaluated on the {@code byte} lane of 8 bits - bit counts, reversals and
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
    public Byte3 rotateLeft(byte distance, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.rotateLeft(this.x, distance));
        d.y = (byte) (Math.rotateLeft(this.y, distance));
        d.z = (byte) (Math.rotateLeft(this.z, distance));
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
    public Short3 rotateLeft(byte distance, @Mutated Short3 dest) {
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
    public Int3 rotateLeft(byte distance, @Mutated Int3 dest) {
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
    public Long3 rotateLeft(byte distance, @Mutated Long3 dest) {
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
    public Double3 rotateLeft(byte distance, @Mutated Double3 dest) {
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
    public Byte3 rotateRight(byte distance, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.rotateRight(this.x, distance));
        d.y = (byte) (Math.rotateRight(this.y, distance));
        d.z = (byte) (Math.rotateRight(this.z, distance));
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
    public Short3 rotateRight(byte distance, @Mutated Short3 dest) {
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
    public Int3 rotateRight(byte distance, @Mutated Int3 dest) {
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
    public Long3 rotateRight(byte distance, @Mutated Long3 dest) {
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
    public Double3 rotateRight(byte distance, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
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
    public Byte3 shl(byte shift, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x << (shift & 7));
        d.y = (byte) (this.y << (shift & 7));
        d.z = (byte) (this.z << (shift & 7));
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
    public Short3 shl(byte shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x << (shift & 7));
        d.y = (short) (this.y << (shift & 7));
        d.z = (short) (this.z << (shift & 7));
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
    public Int3 shl(byte shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
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
    public Long3 shl(byte shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
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
    public Double3 shl(byte shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x << (shift & 7);
        d.y = this.y << (shift & 7);
        d.z = this.z << (shift & 7);
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
    public Byte3 shr(byte shift, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x >> (shift & 7));
        d.y = (byte) (this.y >> (shift & 7));
        d.z = (byte) (this.z >> (shift & 7));
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
    public Short3 shr(byte shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x >> (shift & 7));
        d.y = (short) (this.y >> (shift & 7));
        d.z = (short) (this.z >> (shift & 7));
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
    public Int3 shr(byte shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
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
    public Long3 shr(byte shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
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
    public Double3 shr(byte shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x >> (shift & 7);
        d.y = this.y >> (shift & 7);
        d.z = this.z >> (shift & 7);
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
    public Byte3 ushr(byte shift, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (byte) ((this.y & 0xFF) >>> (shift & 7));
        d.z = (byte) ((this.z & 0xFF) >>> (shift & 7));
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
    public Short3 ushr(byte shift, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) ((this.x & 0xFF) >>> (shift & 7));
        d.y = (short) ((this.y & 0xFF) >>> (shift & 7));
        d.z = (short) ((this.z & 0xFF) >>> (shift & 7));
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
    public Int3 ushr(byte shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
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
    public Long3 ushr(byte shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
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
    public Double3 ushr(byte shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = (this.x & 0xFF) >>> (shift & 7);
        d.y = (this.y & 0xFF) >>> (shift & 7);
        d.z = (this.z & 0xFF) >>> (shift & 7);
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
    public Byte3 xor(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x ^ other.x());
        d.y = (byte) (this.y ^ otherY);
        d.z = (byte) (this.z ^ otherZ);
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
    public Short3 xor(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Int3 xor(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 xor(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x ^ otherX);
        d.y = (byte) (this.y ^ otherY);
        d.z = (byte) (this.z ^ otherZ);
        return d;
    }


    /**
     * Compute the bitwise XOR of each component of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public @Mutated Byte3 set(Byte3R v) {
        byte vY = v.y();
        byte vZ = v.z();
        this.x = (byte) (v.x());
        this.y = (byte) (vY);
        this.z = (byte) (vZ);
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
    @Mutated public Byte3 set(byte vX, byte vY, byte vZ) {
        this.x = (byte) (vX);
        this.y = (byte) (vY);
        this.z = (byte) (vZ);
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
    public Byte3 set(byte s, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (s);
        d.y = (byte) (s);
        d.z = (byte) (s);
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
    public Short3 set(byte s, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (s);
        d.y = (short) (s);
        d.z = (short) (s);
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
    public Int3 set(byte s, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
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
    public Long3 set(byte s, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
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
    public Double3 set(byte s, @Mutated Double3 dest) {
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
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Short3 toShort(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
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
    @Mutated public Byte3 makeZero() {
        this.x = (byte) (0);
        this.y = (byte) (0);
        this.z = (byte) (0);
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
    public Byte3 absolute(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.abs(this.x));
        d.y = (byte) (java.lang.Math.abs(this.y));
        d.z = (byte) (java.lang.Math.abs(this.z));
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte3 clamp(byte min, byte max, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, min), max));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, min), max));
        d.z = (byte) (java.lang.Math.min(java.lang.Math.max(this.z, min), max));
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
    public Short3 clamp(byte min, byte max, @Mutated Short3 dest) {
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
    public Int3 clamp(byte min, byte max, @Mutated Int3 dest) {
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
    public Long3 clamp(byte min, byte max, @Mutated Long3 dest) {
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
    public Double3 clamp(byte min, byte max, @Mutated Double3 dest) {
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
    public Byte3 clamp(Byte3R min, Byte3R max, @Mutated Byte3 dest) {
        byte minY = min.y();
        byte minZ = min.z();
        byte maxY = max.y();
        byte maxZ = max.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, min.x()), max.x()));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (byte) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
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
    public Short3 clamp(Byte3R min, Byte3R max, @Mutated Short3 dest) {
        byte minY = min.y();
        byte minZ = min.z();
        byte maxY = max.y();
        byte maxZ = max.z();
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
    public Int3 clamp(Byte3R min, Byte3R max, @Mutated Int3 dest) {
        byte minY = min.y();
        byte minZ = min.z();
        byte maxY = max.y();
        byte maxZ = max.z();
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
    public Long3 clamp(Byte3R min, Byte3R max, @Mutated Long3 dest) {
        byte minY = min.y();
        byte minZ = min.z();
        byte maxY = max.y();
        byte maxZ = max.z();
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
    public Double3 clamp(Byte3R min, Byte3R max, @Mutated Double3 dest) {
        byte minY = min.y();
        byte minZ = min.z();
        byte maxY = max.y();
        byte maxZ = max.z();
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
    public Byte3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX));
        d.y = (byte) (java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY));
        d.z = (byte) (java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ));
        return d;
    }


    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
    public Short3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Double3 dest) {
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
     * {@code byte} range does not wrap.
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
    public byte compMax() {
        return (byte) (java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        return (byte) (java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z));
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
        return this.z * this.x * this.y;
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
    public Byte3 cross(Byte3R other, @Mutated Byte3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = (byte) (otherZ * _rd1 - otherY * this.z);
        d.y = (byte) (otherX * this.z - otherZ * _rd0);
        d.z = (byte) (otherY * _rd0 - otherX * _rd1);
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Short3 cross(Byte3R other, @Mutated Short3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (otherZ * this.y - otherY * this.z);
        d.y = (short) (otherX * this.z - otherZ * this.x);
        d.z = (short) (otherY * this.x - otherX * this.y);
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Int3 cross(Byte3R other, @Mutated Int3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(Byte3R other, @Mutated Long3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(Byte3R other, @Mutated Double3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = (byte) (otherZ * _rd1 - otherY * this.z);
        d.y = (byte) (otherX * this.z - otherZ * _rd0);
        d.z = (byte) (otherY * _rd0 - otherX * _rd1);
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (otherZ * this.y - otherY * this.z);
        d.y = (short) (otherX * this.z - otherZ * this.x);
        d.z = (short) (otherY * this.x - otherX * this.y);
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
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
    public int distanceSquared(Byte3R other) {
        int _t0 = this.x - other.x();
        int _t1 = this.y - other.y();
        int _t2 = this.z - other.z();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public int distanceSquared(byte otherX, byte otherY, byte otherZ) {
        int _t0 = this.x - otherX;
        int _t1 = this.y - otherY;
        int _t2 = this.z - otherZ;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
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
    public int dot(Byte3R other) {
        return other.x() * this.x + other.y() * this.y + other.z() * this.z;
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public int dot(byte otherX, byte otherY, byte otherZ) {
        return otherX * this.x + otherY * this.y + otherZ * this.z;
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
        return this.x * this.x + this.y * this.y + this.z * this.z;
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
    public int manhattanDistance(Byte3R other) {
        return java.lang.Math.abs(this.x - other.x()) + java.lang.Math.abs(this.y - other.y()) + java.lang.Math.abs(this.z - other.z());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public int manhattanDistance(byte otherX, byte otherY, byte otherZ) {
        return java.lang.Math.abs(this.x - otherX) + java.lang.Math.abs(this.y - otherY) + java.lang.Math.abs(this.z - otherZ);
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
    public Byte3 max(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, scalar));
        d.y = (byte) (java.lang.Math.max(this.y, scalar));
        d.z = (byte) (java.lang.Math.max(this.z, scalar));
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
    public Short3 max(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 max(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 max(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, other.x()));
        d.y = (byte) (java.lang.Math.max(this.y, otherY));
        d.z = (byte) (java.lang.Math.max(this.z, otherZ));
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
    public Short3 max(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 max(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 max(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.max(this.x, otherX));
        d.y = (byte) (java.lang.Math.max(this.y, otherY));
        d.z = (byte) (java.lang.Math.max(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 max(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 max(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 max(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 max(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 min(byte scalar, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, scalar));
        d.y = (byte) (java.lang.Math.min(this.y, scalar));
        d.z = (byte) (java.lang.Math.min(this.z, scalar));
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
    public Short3 min(byte scalar, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 min(byte scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(byte scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(byte scalar, @Mutated Double3 dest) {
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
    public Byte3 min(Byte3R other, @Mutated Byte3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, other.x()));
        d.y = (byte) (java.lang.Math.min(this.y, otherY));
        d.z = (byte) (java.lang.Math.min(this.z, otherZ));
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
    public Short3 min(Byte3R other, @Mutated Short3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Int3 min(Byte3R other, @Mutated Int3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(Byte3R other, @Mutated Long3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(Byte3R other, @Mutated Double3 dest) {
        byte otherY = other.y();
        byte otherZ = other.z();
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
    public Byte3 min(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (java.lang.Math.min(this.x, otherX));
        d.y = (byte) (java.lang.Math.min(this.y, otherY));
        d.z = (byte) (java.lang.Math.min(this.z, otherZ));
        return d;
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 min(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Int3 min(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Long3 min(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Double3 min(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
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
    public Byte3 sign(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (Math.signum(this.x));
        d.y = (byte) (Math.signum(this.y));
        d.z = (byte) (Math.signum(this.z));
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte3 satAdd(Byte3R other, @Mutated Byte3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
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
    public Short3 satAdd(Byte3R other, @Mutated Short3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
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
    public Int3 satAdd(Byte3R other, @Mutated Int3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
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
    public Long3 satAdd(Byte3R other, @Mutated Long3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
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
    public Double3 satAdd(Byte3R other, @Mutated Double3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
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
    public Byte3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satAddB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satAddB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satAddB(this.z, otherZ));
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAddB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAddB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAddB(this.z, otherZ);
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
    public Byte3 satMul(Byte3R other, @Mutated Byte3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
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
    public Short3 satMul(Byte3R other, @Mutated Short3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
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
    public Int3 satMul(Byte3R other, @Mutated Int3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
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
    public Long3 satMul(Byte3R other, @Mutated Long3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
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
    public Double3 satMul(Byte3R other, @Mutated Double3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
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
    public Byte3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satMulB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satMulB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satMulB(this.z, otherZ));
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMulB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMulB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMulB(this.z, otherZ);
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
    public Byte3 satNegate(@Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (byte) (org.joml2.SaturatingMath.satNegB(this.y));
        d.z = (byte) (org.joml2.SaturatingMath.satNegB(this.z));
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
    public Short3 satNegate(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satNegB(this.x));
        d.y = (short) (org.joml2.SaturatingMath.satNegB(this.y));
        d.z = (short) (org.joml2.SaturatingMath.satNegB(this.z));
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
    public Int3 satNegate(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
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
    public Long3 satNegate(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
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
    public Double3 satNegate(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNegB(this.x);
        d.y = org.joml2.SaturatingMath.satNegB(this.y);
        d.z = org.joml2.SaturatingMath.satNegB(this.z);
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
    public Byte3 satSub(Byte3R other, @Mutated Byte3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
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
    public Short3 satSub(Byte3R other, @Mutated Short3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
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
    public Int3 satSub(Byte3R other, @Mutated Int3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
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
    public Long3 satSub(Byte3R other, @Mutated Long3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
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
    public Double3 satSub(Byte3R other, @Mutated Double3 dest) {
        byte otherX = other.x();
        byte otherY = other.y();
        byte otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
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
    public Byte3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (byte) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (byte) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
     * to {@code short}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Short3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (org.joml2.SaturatingMath.satSubB(this.x, otherX));
        d.y = (short) (org.joml2.SaturatingMath.satSubB(this.y, otherY));
        d.z = (short) (org.joml2.SaturatingMath.satSubB(this.z, otherZ));
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Int3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Long3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The result is clamped to the {@code byte} range ({@code Byte.MIN_VALUE} to
     * {@code Byte.MAX_VALUE}), not to the destination's, and each result component is then widened
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
    public Double3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSubB(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSubB(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSubB(this.z, otherZ);
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
    public Byte4 xyz0(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
        d.w = (byte) (0);
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
    public Byte4 xyz1(@Mutated Byte4 dest) {
        Byte4Impl d = (Byte4Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
        d.w = (byte) (1);
        return d;
    }


    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code short}.
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
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

    public byte x() { return this.x; }
    public byte y() { return this.y; }
    public byte z() { return this.z; }

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

    public Byte2 xz(@Mutated Byte2 dest) {
        byte _v1 = this.z;
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

    public Byte2 yz(@Mutated Byte2 dest) {
        byte _v1 = this.z;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Byte2 zx(@Mutated Byte2 dest) {
        byte _v1 = this.x;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Byte2 zy(@Mutated Byte2 dest) {
        byte _v1 = this.y;
        Byte2Impl d = (Byte2Impl) dest;
        d.x = this.z;
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

    public Byte3 xyz(@Mutated Byte3 dest) {
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.x;
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
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 xzz(@Mutated Byte3 dest) {
        byte _v1 = this.z;
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

    public Byte3 yxz(@Mutated Byte3 dest) {
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.y;
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

    public Byte3 yzx(@Mutated Byte3 dest) {
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.y;
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
        byte _v1 = this.z;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 zxx(@Mutated Byte3 dest) {
        byte _v1 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Byte3 zxy(@Mutated Byte3 dest) {
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.z;
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

    public Byte3 zyx(@Mutated Byte3 dest) {
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Byte3 zyy(@Mutated Byte3 dest) {
        byte _v1 = this.y;
        Byte3Impl d = (Byte3Impl) dest;
        d.x = this.z;
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

    public Byte4 xyyz(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
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
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 xyzz(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
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
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzyz(@Mutated Byte4 dest) {
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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
        byte _v1 = this.z;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 xzzz(@Mutated Byte4 dest) {
        byte _v1 = this.z;
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

    public Byte4 yxxz(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
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

    public Byte4 yxzx(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
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
        byte _v1 = this.x;
        byte _v2 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
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

    public Byte4 yzxx(@Mutated Byte4 dest) {
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
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
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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

    public Byte4 yzzx(@Mutated Byte4 dest) {
        byte _v1 = this.z;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
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
        byte _v1 = this.z;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxxx(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxxy(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
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

    public Byte4 zxyx(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Byte4 zxyy(@Mutated Byte4 dest) {
        byte _v1 = this.x;
        byte _v2 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
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

    public Byte4 zyxx(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyxy(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
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

    public Byte4 zyyx(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        byte _v2 = this.x;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Byte4 zyyy(@Mutated Byte4 dest) {
        byte _v1 = this.y;
        Byte4Impl d = (Byte4Impl) dest;
        d.x = this.z;
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

    @Override public String toString() {
        return "Byte3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte3Impl)) return false;
        Byte3Impl o = (Byte3Impl) obj;
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

    @Override public boolean equalsEpsilon(Byte3R other, byte epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon;
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Byte3 load(byte[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    public Byte3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Byte3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Byte3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte3 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return r;
    }
    public Byte3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Byte3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Byte3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Byte3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Byte3 load(short[] src, int offset) {
        this.x = (byte) src[offset];
        this.y = (byte) src[offset + 1];
        this.z = (byte) src[offset + 2];
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
    @Mutated public Byte3 load(ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Byte3 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Byte3 loadRelative(ShortBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer storeShort(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, index, buf);
    }
    public ByteBuffer storeShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeShortAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    public Byte3 loadShort(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, buf.position(), buf);
    }
    public Byte3 loadShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, index, buf);
    }
    public Byte3 loadShortRelative(ByteBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Byte3 r = StoreLoad.BB_OPS.loadShortAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return r;
    }
    public Byte3 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }
    @Mutated public Byte3 loadShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadShortUnsafe(this, address);
    }
    public MemorySegment storeShort(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeShort(this, 0L, dest); }
    public MemorySegment storeShort(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeShort(this, offset, dest);
    }
    @Mutated public Byte3 loadShort(MemorySegment src) { return StoreLoad.SEG_OPS.loadShort(this, 0L, src); }
    public Byte3 loadShort(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadShort(this, offset, src);
    }
}
