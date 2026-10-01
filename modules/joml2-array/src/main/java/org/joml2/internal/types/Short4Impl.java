// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;

/**
 * Generated implementation of {@link Short4} backed by a {@code short[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Short4Impl implements Short4 {

    public short[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short4SegOpsUnsafe()
                        : new Short4SegOpsMS();
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
        data = new short[4];
        data[3] = 1;
    }

    public Short4Impl(short x, short y, short z, short w) {
        short[] dd = this.data = new short[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public Short4Impl(Short4R src) {
        short[] dd = this.data = new short[4];
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
    public Short4 add(Short4R other, @Mutated Short4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
        return add(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 add(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (otherX + sd[0]);
        dd[1] = (short) (otherY + sd[1]);
        dd[2] = (short) (otherZ + sd[2]);
        dd[3] = (short) (otherW + sd[3]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        dd[3] = otherW + sd[3];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 ceilDiv(short scalar, @Mutated Short4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
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
    public Short4 ceilDiv(Short4R other, @Mutated Short4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 ceilDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[3] = (short) (java.lang.Math.ceilDiv(sd[3], otherW));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilDiv(sd[3], otherW);
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
    public Short4 ceilMod(short scalar, @Mutated Short4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, scalar, dest);
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
    public Short4 ceilMod(Short4R other, @Mutated Short4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 ceilMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[3] = (short) (java.lang.Math.ceilMod(sd[3], otherW));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        int _buf2 = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[3] = java.lang.Math.ceilMod(sd[3], otherW);
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
    public Short4 div(short scalar, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        int _buf2 = sd[2] / scalar;
        dd[3] = (short) (sd[3] / scalar);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        int _buf2 = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        int _buf2 = sd[2] / scalar;
        dd[3] = sd[3] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        int _buf2 = sd[2] / scalar;
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
    public Short4 div(Short4R other, @Mutated Short4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
        return div(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 div(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        int _buf2 = sd[2] / otherZ;
        dd[3] = (short) (sd[3] / otherW);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        int _buf2 = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        int _buf2 = sd[2] / otherZ;
        dd[3] = sd[3] / otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        int _buf2 = sd[2] / otherZ;
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
    public Short4 floorDiv(short scalar, @Mutated Short4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, scalar, dest);
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
    public Short4 floorDiv(Short4R other, @Mutated Short4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 floorDiv(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        int _buf2 = Math.floorDiv(sd[2], otherZ);
        dd[3] = (short) (Math.floorDiv(sd[3], otherW));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        int _buf2 = Math.floorDiv(sd[2], otherZ);
        dd[3] = Math.floorDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        int _buf2 = Math.floorDiv(sd[2], otherZ);
        dd[3] = Math.floorDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        int _buf2 = Math.floorDiv(sd[2], otherZ);
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
    public Short4 floorMod(short scalar, @Mutated Short4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, scalar, dest);
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
    public Short4 floorMod(Short4R other, @Mutated Short4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 floorMod(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        int _buf2 = Math.floorMod(sd[2], otherZ);
        dd[3] = (short) (Math.floorMod(sd[3], otherW));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        int _buf2 = Math.floorMod(sd[2], otherZ);
        dd[3] = Math.floorMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        int _buf2 = Math.floorMod(sd[2], otherZ);
        dd[3] = Math.floorMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        int _buf2 = Math.floorMod(sd[2], otherZ);
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
    public Short4 mul(short scalar, @Mutated Short4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, scalar, dest);
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
    public Short4 mul(Short4R other, @Mutated Short4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 mul(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (otherX * sd[0]);
        dd[1] = (short) (otherY * sd[1]);
        dd[2] = (short) (otherZ * sd[2]);
        dd[3] = (short) (otherW * sd[3]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        dd[3] = otherW * sd[3];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 negate(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (-sd[0]);
        dd[1] = (short) (-sd[1]);
        dd[2] = (short) (-sd[2]);
        dd[3] = (short) (-sd[3]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 rem(short scalar, @Mutated Short4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, scalar, dest);
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
    public Short4 rem(Short4R other, @Mutated Short4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 rem(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        int _buf2 = sd[2] % otherZ;
        dd[3] = (short) (sd[3] % otherW);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        int _buf2 = sd[2] % otherZ;
        dd[3] = sd[3] % otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        int _buf2 = sd[2] % otherZ;
        dd[3] = sd[3] % otherW;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        int _buf2 = sd[2] % otherZ;
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
    public Short4 sub(Short4R other, @Mutated Short4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 sub(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] - otherX);
        dd[1] = (short) (sd[1] - otherY);
        dd[2] = (short) (sd[2] - otherZ);
        dd[3] = (short) (sd[3] - otherW);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        dd[3] = sd[3] - otherW;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 and(Short4R other, @Mutated Short4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
        return and(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 and(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] & otherX);
        dd[1] = (short) (sd[1] & otherY);
        dd[2] = (short) (sd[2] & otherZ);
        dd[3] = (short) (sd[3] & otherW);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        dd[3] = sd[3] & otherW;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        dd[3] = sd[3] & otherW;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 bitCount(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.bitCount(sd[0]));
        dd[1] = (short) (Math.bitCount(sd[1]));
        dd[2] = (short) (Math.bitCount(sd[2]));
        dd[3] = (short) (Math.bitCount(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
        dd[3] = Math.bitCount(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
        dd[3] = Math.bitCount(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 not(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (~sd[0]);
        dd[1] = (short) (~sd[1]);
        dd[2] = (short) (~sd[2]);
        dd[3] = (short) (~sd[3]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
        dd[3] = ~sd[3];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
        dd[3] = ~sd[3];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 numberOfLeadingZeros(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.numberOfLeadingZeros(sd[0]));
        dd[1] = (short) (Math.numberOfLeadingZeros(sd[1]));
        dd[2] = (short) (Math.numberOfLeadingZeros(sd[2]));
        dd[3] = (short) (Math.numberOfLeadingZeros(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
        dd[3] = Math.numberOfLeadingZeros(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
        dd[3] = Math.numberOfLeadingZeros(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 numberOfTrailingZeros(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.numberOfTrailingZeros(sd[0]));
        dd[1] = (short) (Math.numberOfTrailingZeros(sd[1]));
        dd[2] = (short) (Math.numberOfTrailingZeros(sd[2]));
        dd[3] = (short) (Math.numberOfTrailingZeros(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
        dd[3] = Math.numberOfTrailingZeros(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
        dd[3] = Math.numberOfTrailingZeros(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 or(Short4R other, @Mutated Short4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
        return or(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 or(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] | otherX);
        dd[1] = (short) (sd[1] | otherY);
        dd[2] = (short) (sd[2] | otherZ);
        dd[3] = (short) (sd[3] | otherW);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        dd[3] = sd[3] | otherW;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        dd[3] = sd[3] | otherW;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 reverseBits(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.reverseBits(sd[0]));
        dd[1] = (short) (Math.reverseBits(sd[1]));
        dd[2] = (short) (Math.reverseBits(sd[2]));
        dd[3] = (short) (Math.reverseBits(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
        dd[3] = Math.reverseBits(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
        dd[3] = Math.reverseBits(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 reverseBytes(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.reverseBytes(sd[0]));
        dd[1] = (short) (Math.reverseBytes(sd[1]));
        dd[2] = (short) (Math.reverseBytes(sd[2]));
        dd[3] = (short) (Math.reverseBytes(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
        dd[3] = Math.reverseBytes(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
        dd[3] = Math.reverseBytes(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 rotateLeft(short distance, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.rotateLeft(sd[0], distance));
        dd[1] = (short) (Math.rotateLeft(sd[1], distance));
        dd[2] = (short) (Math.rotateLeft(sd[2], distance));
        dd[3] = (short) (Math.rotateLeft(sd[3], distance));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
        dd[3] = Math.rotateLeft(sd[3], distance);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
        dd[3] = Math.rotateLeft(sd[3], distance);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 rotateRight(short distance, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.rotateRight(sd[0], distance));
        dd[1] = (short) (Math.rotateRight(sd[1], distance));
        dd[2] = (short) (Math.rotateRight(sd[2], distance));
        dd[3] = (short) (Math.rotateRight(sd[3], distance));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        dd[3] = Math.rotateRight(sd[3], distance);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        dd[3] = Math.rotateRight(sd[3], distance);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        dd[3] = Math.rotateRight(sd[3], distance);
        return dest;
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
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] << (shift & 15));
        dd[1] = (short) (sd[1] << (shift & 15));
        dd[2] = (short) (sd[2] << (shift & 15));
        dd[3] = (short) (sd[3] << (shift & 15));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
        dd[3] = sd[3] << (shift & 15);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
        dd[3] = sd[3] << (shift & 15);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
        dd[3] = sd[3] << (shift & 15);
        return dest;
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
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] >> (shift & 15));
        dd[1] = (short) (sd[1] >> (shift & 15));
        dd[2] = (short) (sd[2] >> (shift & 15));
        dd[3] = (short) (sd[3] >> (shift & 15));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
        dd[3] = sd[3] >> (shift & 15);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
        dd[3] = sd[3] >> (shift & 15);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
        dd[3] = sd[3] >> (shift & 15);
        return dest;
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
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) ((sd[0] & 0xFFFF) >>> (shift & 15));
        dd[1] = (short) ((sd[1] & 0xFFFF) >>> (shift & 15));
        dd[2] = (short) ((sd[2] & 0xFFFF) >>> (shift & 15));
        dd[3] = (short) ((sd[3] & 0xFFFF) >>> (shift & 15));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
        dd[3] = (sd[3] & 0xFFFF) >>> (shift & 15);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
        dd[3] = (sd[3] & 0xFFFF) >>> (shift & 15);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
        dd[3] = (sd[3] & 0xFFFF) >>> (shift & 15);
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
    public Short4 xor(Short4R other, @Mutated Short4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 xor(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0] ^ otherX);
        dd[1] = (short) (sd[1] ^ otherY);
        dd[2] = (short) (sd[2] ^ otherZ);
        dd[3] = (short) (sd[3] ^ otherW);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        dd[3] = sd[3] ^ otherW;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        dd[3] = sd[3] ^ otherW;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public @Mutated Short4 set(Short4R v) {
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
    @Mutated public Short4 set(short vX, short vY, short vZ, short vW) {
        short[] dd = this.data;
        dd[0] = (short) (vX);
        dd[1] = (short) (vY);
        dd[2] = (short) (vZ);
        dd[3] = (short) (vW);
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
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (s);
        dd[1] = (short) (s);
        dd[2] = (short) (s);
        dd[3] = (short) (s);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        dd[3] = s;
        return dest;
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
        short[] sd = this.data;
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
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 toDouble(@Mutated Double4 dest) {
        short[] sd = this.data;
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
        short[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (sd[3]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Short4 makeZero() {
        short[] dd = this.data;
        dd[0] = (short) (0);
        dd[1] = (short) (0);
        dd[2] = (short) (0);
        dd[3] = (short) (0);
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
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.abs(sd[0]));
        dd[1] = (short) (Math.abs(sd[1]));
        dd[2] = (short) (Math.abs(sd[2]));
        dd[3] = (short) (Math.abs(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
        dd[3] = Math.abs(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
        dd[3] = Math.abs(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 clamp(short min, short max, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.min(Math.max(sd[0], min), max));
        dd[1] = (short) (Math.min(Math.max(sd[1], min), max));
        dd[2] = (short) (Math.min(Math.max(sd[2], min), max));
        dd[3] = (short) (Math.min(Math.max(sd[3], min), max));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
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
        short[] sd = this.data;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 clamp(Short4R min, Short4R max, @Mutated Short4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
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
    public Short4 clamp(short minX, short minY, short minZ, short minW, short maxX, short maxY, short maxZ, short maxW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.min(Math.max(sd[0], minX), maxX));
        dd[1] = (short) (Math.min(Math.max(sd[1], minY), maxY));
        dd[2] = (short) (Math.min(Math.max(sd[2], minZ), maxZ));
        dd[3] = (short) (Math.min(Math.max(sd[3], minW), maxW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        dd[3] = Math.min(Math.max(sd[3], minW), maxW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        dd[3] = Math.min(Math.max(sd[3], minW), maxW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        dd[3] = Math.min(Math.max(sd[3], minW), maxW);
        return dest;
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
        short[] sd = this.data;
        return sd[3] + (sd[2] + (sd[0] + sd[1]));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public short compMax() {
        short[] sd = this.data;
        return (short) (Math.max(Math.max(Math.max(sd[0], sd[1]), sd[2]), sd[3]));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public short compMin() {
        short[] sd = this.data;
        return (short) (Math.min(Math.min(Math.min(sd[0], sd[1]), sd[2]), sd[3]));
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
        short[] sd = this.data;
        return (long) sd[3] * (long) sd[2] * (long) sd[0] * (long) sd[1];
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
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
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
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
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
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
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
    public Short4 cross(short vX, short vY, short vZ, short vW, short wX, short wY, short wZ, short wW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = sd[1] * _t12 - sd[2] * _t13 + sd[3] * _t14;
        int _buf1 = sd[2] * _t15 - sd[0] * _t12 - sd[3] * _t16;
        int _buf2 = sd[0] * _t13 - sd[1] * _t15 + sd[3] * _t17;
        dd[3] = (short) (sd[1] * _t16 - sd[0] * _t14 - sd[2] * _t17);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        dd[2] = (short) (_buf2);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = sd[1] * _t12 - sd[2] * _t13 + sd[3] * _t14;
        int _buf1 = sd[2] * _t15 - sd[0] * _t12 - sd[3] * _t16;
        int _buf2 = sd[0] * _t13 - sd[1] * _t15 + sd[3] * _t17;
        dd[3] = sd[1] * _t16 - sd[0] * _t14 - sd[2] * _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = sd[1] * _t12 - sd[2] * _t13 + sd[3] * _t14;
        int _buf1 = sd[2] * _t15 - sd[0] * _t12 - sd[3] * _t16;
        int _buf2 = sd[0] * _t13 - sd[1] * _t15 + sd[3] * _t17;
        dd[3] = sd[1] * _t16 - sd[0] * _t14 - sd[2] * _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _t12 = vZ * wW - vW * wZ;
        int _t13 = vY * wW - vW * wY;
        int _t14 = vY * wZ - vZ * wY;
        int _t15 = vX * wW - vW * wX;
        int _t16 = vX * wZ - vZ * wX;
        int _t17 = vX * wY - vY * wX;
        int _buf0 = sd[1] * _t12 - sd[2] * _t13 + sd[3] * _t14;
        int _buf1 = sd[2] * _t15 - sd[0] * _t12 - sd[3] * _t16;
        int _buf2 = sd[0] * _t13 - sd[1] * _t15 + sd[3] * _t17;
        dd[3] = sd[1] * _t16 - sd[0] * _t14 - sd[2] * _t17;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
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
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
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
        short[] sd = this.data;
        long _t0 = (long) sd[0] - (long) otherX;
        long _t1 = (long) sd[1] - (long) otherY;
        long _t2 = (long) sd[2] - (long) otherZ;
        long _t3 = (long) sd[3] - (long) otherW;
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
        return dot(other.x(), other.y(), other.z(), other.w());
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
        short[] sd = this.data;
        return (long) otherX * (long) sd[0] + (long) otherY * (long) sd[1] + (long) otherZ * (long) sd[2] + (long) otherW * (long) sd[3];
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
        short[] sd = this.data;
        return (long) sd[0] * (long) sd[0] + (long) sd[1] * (long) sd[1] + (long) sd[2] * (long) sd[2] + (long) sd[3] * (long) sd[3];
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
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
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
        short[] sd = this.data;
        return Math.abs(sd[0] - otherX) + Math.abs(sd[1] - otherY) + Math.abs(sd[2] - otherZ) + Math.abs(sd[3] - otherW);
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
        short[] sd = this.data;
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
    public Short4 max(short scalar, @Mutated Short4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, scalar, dest);
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
    public Short4 max(Short4R other, @Mutated Short4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
        return max(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 max(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.max(sd[0], otherX));
        dd[1] = (short) (Math.max(sd[1], otherY));
        dd[2] = (short) (Math.max(sd[2], otherZ));
        dd[3] = (short) (Math.max(sd[3], otherW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        dd[3] = Math.max(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        dd[3] = Math.max(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 min(short scalar, @Mutated Short4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, scalar, dest);
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
    public Short4 min(Short4R other, @Mutated Short4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
        return min(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 min(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.min(sd[0], otherX));
        dd[1] = (short) (Math.min(sd[1], otherY));
        dd[2] = (short) (Math.min(sd[2], otherZ));
        dd[3] = (short) (Math.min(sd[3], otherW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        dd[3] = Math.min(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        dd[3] = Math.min(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 sign(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (Math.signum(sd[0]));
        dd[1] = (short) (Math.signum(sd[1]));
        dd[2] = (short) (Math.signum(sd[2]));
        dd[3] = (short) (Math.signum(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        dd[3] = Math.signum(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
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
    public Short4 satAdd(Short4R other, @Mutated Short4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satAdd(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satAddS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satAddS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satAddS(sd[2], otherZ));
        dd[3] = (short) (org.joml2.SaturatingMath.satAddS(sd[3], otherW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAddS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAddS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAddS(sd[3], otherW);
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
    public Short4 satMul(Short4R other, @Mutated Short4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satMul(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satMulS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satMulS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satMulS(sd[2], otherZ));
        dd[3] = (short) (org.joml2.SaturatingMath.satMulS(sd[3], otherW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMulS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMulS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMulS(sd[3], otherW);
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
    public Short4 satNegate(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satNegS(sd[0]));
        dd[1] = (short) (org.joml2.SaturatingMath.satNegS(sd[1]));
        dd[2] = (short) (org.joml2.SaturatingMath.satNegS(sd[2]));
        dd[3] = (short) (org.joml2.SaturatingMath.satNegS(sd[3]));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNegS(sd[3]);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNegS(sd[3]);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNegS(sd[3]);
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
    public Short4 satSub(Short4R other, @Mutated Short4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
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
    public Short4 satSub(short otherX, short otherY, short otherZ, short otherW, @Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satSubS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satSubS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satSubS(sd[2], otherZ));
        dd[3] = (short) (org.joml2.SaturatingMath.satSubS(sd[3], otherW));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSubS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSubS(sd[3], otherW);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSubS(sd[3], otherW);
        return dest;
    }

    public short x() { return data[0]; }
    public short y() { return data[1]; }
    public short z() { return data[2]; }
    public short w() { return data[3]; }

    public Short2 xx(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Short2 xy(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 xz(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 xw(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 yx(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 yy(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Short2 yz(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 yw(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 zx(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 zy(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 zz(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Short2 zw(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 wx(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 wy(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 wz(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Short2 ww(@Mutated Short2 dest) {
        short[] sd = this.data;
        short[] dd = ((Short2Impl) dest).data;
        short _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Short3 xxx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Short3 xxy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 xxz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 xxw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 xyx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 xyy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 xyz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xyw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xzx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 xzy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xzz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 xzw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xwx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 xwy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xwz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 xww(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yxx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yxy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 yxz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 yxw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 yyx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yyy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Short3 yyz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yyw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yzx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 yzy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 yzz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 yzw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 ywx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 ywy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 ywz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 yww(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zxx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zxy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zxz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 zxw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zyx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zyy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zyz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 zyw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zzx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zzy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zzz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Short3 zzw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 zwx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zwy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 zwz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 zww(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wxx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wxy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wxz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wxw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 wyx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wyy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wyz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wyw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 wzx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wzy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Short3 wzz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wzw(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Short3 wwx(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wwy(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 wwz(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Short3 www(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        short _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Short4 xxxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xxxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xxxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xxxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xxyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xxyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xxyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xxzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xxzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xxwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xxww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xyxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xyxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xyxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xyxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xyyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xyyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xyyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xyyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xyzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xyzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xyzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xyzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[2];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xywx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xywy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xywz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xyww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xzxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xzxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xzyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xzyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xzzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xzzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xzzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xzwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xzwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xzwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xzww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xwxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xwyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xwyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xwyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xwzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xwzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 xwzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 xwwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 xwwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 xwww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[0];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yxxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yxxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yxxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yxxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yxyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yxyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yxyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yxyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yxzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yxzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yxzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yxzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 yxwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yxwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yxwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 yxww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yyxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yyxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yyxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yyxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yyyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yyyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yyyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yyyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yyzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yyzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yyzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yyzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yywx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yywy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yywz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yyww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yzxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yzxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yzxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yzxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 yzyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yzyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yzyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yzyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yzzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yzzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yzzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yzzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 yzwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 yzwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 yzwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 yzww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[2];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 ywxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 ywxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 ywyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 ywyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 ywzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 ywzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 ywzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 ywwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 ywwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 ywww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[1];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zxxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zxxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zxxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zxxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zxyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zxyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zxyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zxyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zxzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zxzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zxzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zxzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zxwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zxwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zxwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zxww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zyxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zyxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zyxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zyxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        short _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zyyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zyyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zyyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zyyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zyzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zyzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zyzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zyzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zywx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zywy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zywz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zyww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zzxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zzxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zzyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zzyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        short _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zzzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zzzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zzzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zzwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zzwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zzww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zwxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zwxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zwxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zwyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 zwyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zwyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zwzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zwzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 zwwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 zwwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 zwww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[2];
        short _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wxxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wxxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wxyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wxyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wxyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wxzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wxzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wxzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wxwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wxwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wxww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wyxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wyxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wyxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        short _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wyxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wyyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wyyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wyyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wyyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wyzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wyzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wyzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wyzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wywx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wywy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wywz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wyww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wzxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        short _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wzxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wzxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wzyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        short _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Short4 wzyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wzyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wzzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wzzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wzwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wzwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wzww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wwxx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwxy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwxz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwxw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wwyx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwyy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwyz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        short _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwyw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wwzx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwzy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        short _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Short4 wwzz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwzw(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Short4 wwwx(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwwy(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwwz(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        short _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Short4 wwww(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        short _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Short4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short4Impl)) return false;
        Short4Impl o = (Short4Impl) obj;
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

    @Override public boolean equalsEpsilon(Short4R other, short epsilon) {
        return Math.abs(data[0] - other.x()) <= epsilon
            && Math.abs(data[1] - other.y()) <= epsilon
            && Math.abs(data[2] - other.z()) <= epsilon
            && Math.abs(data[3] - other.w()) <= epsilon;
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Short4 load(short[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Short4 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Short4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Short4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Short4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Short4 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset + 0] = (byte) this.data[0];
        dest[offset + 1] = (byte) this.data[1];
        dest[offset + 2] = (byte) this.data[2];
        dest[offset + 3] = (byte) this.data[3];
        return dest;
    }
    public @Mutated Short4 load(byte[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }
    public Short4 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, index, buf);
    }
    public Short4 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }
    @Mutated public Short4 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(this, address);
    }
    public MemorySegment storeByte(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeByte(this, offset, dest);
    }
    public Short4 loadByte(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadByte(this, offset, src);
    }

}
