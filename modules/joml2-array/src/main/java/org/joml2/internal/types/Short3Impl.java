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
 * Generated implementation of {@link Short3} backed by a {@code short[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Short3Impl implements Short3 {

    public short[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Short3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Short3SegOpsUnsafe()
                        : new Short3SegOpsMS();
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
        data = new short[3];
    }

    public Short3Impl(short x, short y, short z) {
        short[] dd = this.data = new short[3];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
    }

    public Short3Impl(Short3R src) {
        short[] dd = this.data = new short[3];
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
    public Short3 add(Short3R other, @Mutated Short3 dest) {
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (otherX + sd[0]);
        dd[1] = (short) (otherY + sd[1]);
        dd[2] = (short) (otherZ + sd[2]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherX + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 ceilDiv(short scalar, @Mutated Short3 dest) {
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        dd[2] = (short) (java.lang.Math.ceilDiv(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        dd[2] = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
        dd[2] = java.lang.Math.ceilDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], otherY);
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
    public Short3 ceilMod(short scalar, @Mutated Short3 dest) {
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        dd[2] = (short) (java.lang.Math.ceilMod(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        dd[2] = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
        dd[2] = java.lang.Math.ceilMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], otherX);
        int _buf1 = java.lang.Math.ceilMod(sd[1], otherY);
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
    public Short3 div(short scalar, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        dd[2] = (short) (sd[2] / scalar);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
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
    public Int3 div(short scalar, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Long3 div(short scalar, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        dd[2] = sd[2] / scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public Double3 div(short scalar, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
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
    public Short3 div(Short3R other, @Mutated Short3 dest) {
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        dd[2] = (short) (sd[2] / otherZ);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        dd[2] = sd[2] / otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
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
    public Short3 floorDiv(short scalar, @Mutated Short3 dest) {
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        dd[2] = (short) (Math.floorDiv(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        dd[2] = Math.floorDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        dd[2] = Math.floorDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
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
    public Short3 floorMod(short scalar, @Mutated Short3 dest) {
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        dd[2] = (short) (Math.floorMod(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        dd[2] = Math.floorMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        dd[2] = Math.floorMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
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
    public Short3 mul(short scalar, @Mutated Short3 dest) {
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (otherX * sd[0]);
        dd[1] = (short) (otherY * sd[1]);
        dd[2] = (short) (otherZ * sd[2]);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherX * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 negate(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (-sd[0]);
        dd[1] = (short) (-sd[1]);
        dd[2] = (short) (-sd[2]);
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
    public Int3 negate(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Long3 negate(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Double3 negate(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 rem(short scalar, @Mutated Short3 dest) {
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        dd[2] = (short) (sd[2] % otherZ);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        dd[2] = sd[2] % otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        dd[2] = sd[2] % otherZ;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
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
    public Short3 sub(Short3R other, @Mutated Short3 dest) {
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] - otherX);
        dd[1] = (short) (sd[1] - otherY);
        dd[2] = (short) (sd[2] - otherZ);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] - otherX;
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 and(Short3R other, @Mutated Short3 dest) {
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] & otherX);
        dd[1] = (short) (sd[1] & otherY);
        dd[2] = (short) (sd[2] & otherZ);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] & otherX;
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 bitCount(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.bitCount(sd[0]));
        dd[1] = (short) (Math.bitCount(sd[1]));
        dd[2] = (short) (Math.bitCount(sd[2]));
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
    public Int3 bitCount(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
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
    public Long3 bitCount(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.bitCount(sd[0]);
        dd[1] = Math.bitCount(sd[1]);
        dd[2] = Math.bitCount(sd[2]);
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
    public Double3 bitCount(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 not(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (~sd[0]);
        dd[1] = (short) (~sd[1]);
        dd[2] = (short) (~sd[2]);
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
    public Int3 not(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
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
    public Long3 not(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = ~sd[0];
        dd[1] = ~sd[1];
        dd[2] = ~sd[2];
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
    public Double3 not(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 numberOfLeadingZeros(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.numberOfLeadingZeros(sd[0]));
        dd[1] = (short) (Math.numberOfLeadingZeros(sd[1]));
        dd[2] = (short) (Math.numberOfLeadingZeros(sd[2]));
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
    public Int3 numberOfLeadingZeros(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
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
    public Long3 numberOfLeadingZeros(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.numberOfLeadingZeros(sd[0]);
        dd[1] = Math.numberOfLeadingZeros(sd[1]);
        dd[2] = Math.numberOfLeadingZeros(sd[2]);
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
    public Double3 numberOfLeadingZeros(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 numberOfTrailingZeros(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.numberOfTrailingZeros(sd[0]));
        dd[1] = (short) (Math.numberOfTrailingZeros(sd[1]));
        dd[2] = (short) (Math.numberOfTrailingZeros(sd[2]));
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
    public Int3 numberOfTrailingZeros(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
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
    public Long3 numberOfTrailingZeros(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.numberOfTrailingZeros(sd[0]);
        dd[1] = Math.numberOfTrailingZeros(sd[1]);
        dd[2] = Math.numberOfTrailingZeros(sd[2]);
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
    public Double3 numberOfTrailingZeros(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 or(Short3R other, @Mutated Short3 dest) {
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] | otherX);
        dd[1] = (short) (sd[1] | otherY);
        dd[2] = (short) (sd[2] | otherZ);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] | otherX;
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 reverseBits(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.reverseBits(sd[0]));
        dd[1] = (short) (Math.reverseBits(sd[1]));
        dd[2] = (short) (Math.reverseBits(sd[2]));
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
    public Int3 reverseBits(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
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
    public Long3 reverseBits(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.reverseBits(sd[0]);
        dd[1] = Math.reverseBits(sd[1]);
        dd[2] = Math.reverseBits(sd[2]);
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
    public Double3 reverseBits(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 reverseBytes(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.reverseBytes(sd[0]));
        dd[1] = (short) (Math.reverseBytes(sd[1]));
        dd[2] = (short) (Math.reverseBytes(sd[2]));
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
    public Int3 reverseBytes(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
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
    public Long3 reverseBytes(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.reverseBytes(sd[0]);
        dd[1] = Math.reverseBytes(sd[1]);
        dd[2] = Math.reverseBytes(sd[2]);
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
    public Double3 reverseBytes(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 rotateLeft(short distance, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.rotateLeft(sd[0], distance));
        dd[1] = (short) (Math.rotateLeft(sd[1], distance));
        dd[2] = (short) (Math.rotateLeft(sd[2], distance));
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
    public Int3 rotateLeft(short distance, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
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
    public Long3 rotateLeft(short distance, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.rotateLeft(sd[0], distance);
        dd[1] = Math.rotateLeft(sd[1], distance);
        dd[2] = Math.rotateLeft(sd[2], distance);
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
    public Double3 rotateLeft(short distance, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 rotateRight(short distance, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.rotateRight(sd[0], distance));
        dd[1] = (short) (Math.rotateRight(sd[1], distance));
        dd[2] = (short) (Math.rotateRight(sd[2], distance));
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
    public Int3 rotateRight(short distance, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
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
    public Long3 rotateRight(short distance, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
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
    public Double3 rotateRight(short distance, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
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
    public Short3 shl(short shift, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] << (shift & 15));
        dd[1] = (short) (sd[1] << (shift & 15));
        dd[2] = (short) (sd[2] << (shift & 15));
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
    public Int3 shl(short shift, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
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
    public Long3 shl(short shift, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
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
    public Double3 shl(short shift, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] << (shift & 15);
        dd[1] = sd[1] << (shift & 15);
        dd[2] = sd[2] << (shift & 15);
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
    public Short3 shr(short shift, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] >> (shift & 15));
        dd[1] = (short) (sd[1] >> (shift & 15));
        dd[2] = (short) (sd[2] >> (shift & 15));
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
    public Int3 shr(short shift, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
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
    public Long3 shr(short shift, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
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
    public Double3 shr(short shift, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 15);
        dd[1] = sd[1] >> (shift & 15);
        dd[2] = sd[2] >> (shift & 15);
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
    public Short3 ushr(short shift, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) ((sd[0] & 0xFFFF) >>> (shift & 15));
        dd[1] = (short) ((sd[1] & 0xFFFF) >>> (shift & 15));
        dd[2] = (short) ((sd[2] & 0xFFFF) >>> (shift & 15));
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
    public Int3 ushr(short shift, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
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
    public Long3 ushr(short shift, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
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
    public Double3 ushr(short shift, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = (sd[0] & 0xFFFF) >>> (shift & 15);
        dd[1] = (sd[1] & 0xFFFF) >>> (shift & 15);
        dd[2] = (sd[2] & 0xFFFF) >>> (shift & 15);
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
    public Short3 xor(Short3R other, @Mutated Short3 dest) {
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] ^ otherX);
        dd[1] = (short) (sd[1] ^ otherY);
        dd[2] = (short) (sd[2] ^ otherZ);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] ^ otherX;
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public @Mutated Short3 set(Short3R v) {
        return set(v.x(), v.y(), v.z());
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
        short[] dd = this.data;
        dd[0] = (short) (vX);
        dd[1] = (short) (vY);
        dd[2] = (short) (vZ);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (s);
        dd[1] = (short) (s);
        dd[2] = (short) (s);
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
    public Int3 set(short s, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Long3 set(short s, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Double3 set(short s, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Float3 toFloat(@Mutated Float3 dest) {
        short[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
    public Double3 toDouble(@Mutated Double3 dest) {
        short[] sd = this.data;
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
        short[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
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
    public Int3 toInt(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
    public Long3 toLong(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Short3 makeZero() {
        short[] dd = this.data;
        dd[0] = (short) (0);
        dd[1] = (short) (0);
        dd[2] = (short) (0);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.abs(sd[0]));
        dd[1] = (short) (Math.abs(sd[1]));
        dd[2] = (short) (Math.abs(sd[2]));
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
    public Int3 absolute(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
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
    public Long3 absolute(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
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
    public Double3 absolute(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.abs(sd[0]);
        dd[1] = Math.abs(sd[1]);
        dd[2] = Math.abs(sd[2]);
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
    public Short3 clamp(short min, short max, @Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.min(Math.max(sd[0], min), max));
        dd[1] = (short) (Math.min(Math.max(sd[1], min), max));
        dd[2] = (short) (Math.min(Math.max(sd[2], min), max));
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
    public Int3 clamp(short min, short max, @Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
        dd[2] = Math.min(Math.max(sd[2], min), max);
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
    public Long3 clamp(short min, short max, @Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
        dd[2] = Math.min(Math.max(sd[2], min), max);
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
    public Double3 clamp(short min, short max, @Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], min), max);
        dd[1] = Math.min(Math.max(sd[1], min), max);
        dd[2] = Math.min(Math.max(sd[2], min), max);
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
    public Short3 clamp(Short3R min, Short3R max, @Mutated Short3 dest) {
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.min(Math.max(sd[0], minX), maxX));
        dd[1] = (short) (Math.min(Math.max(sd[1], minY), maxY));
        dd[2] = (short) (Math.min(Math.max(sd[2], minZ), maxZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(Math.max(sd[0], minX), maxX);
        dd[1] = Math.min(Math.max(sd[1], minY), maxY);
        dd[2] = Math.min(Math.max(sd[2], minZ), maxZ);
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
        return sd[2] + (sd[0] + sd[1]);
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
        return (short) (Math.max(Math.max(sd[0], sd[1]), sd[2]));
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
        return (short) (Math.min(Math.min(sd[0], sd[1]), sd[2]));
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
        return (long) sd[2] * (long) sd[0] * (long) sd[1];
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = (short) (otherY * sd[0] - otherX * sd[1]);
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public long distanceSquared(Short3R other) {
        return distanceSquared(other.x(), other.y(), other.z());
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
        short[] sd = this.data;
        long _t0 = (long) sd[0] - (long) otherX;
        long _t1 = (long) sd[1] - (long) otherY;
        long _t2 = (long) sd[2] - (long) otherZ;
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
        return dot(other.x(), other.y(), other.z());
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
        short[] sd = this.data;
        return (long) otherX * (long) sd[0] + (long) otherY * (long) sd[1] + (long) otherZ * (long) sd[2];
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
        return (long) sd[0] * (long) sd[0] + (long) sd[1] * (long) sd[1] + (long) sd[2] * (long) sd[2];
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
        return manhattanDistance(other.x(), other.y(), other.z());
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
        short[] sd = this.data;
        return Math.abs(sd[0] - otherX) + Math.abs(sd[1] - otherY) + Math.abs(sd[2] - otherZ);
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
        return Math.abs(sd[0]) + Math.abs(sd[1]) + Math.abs(sd[2]);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.max(sd[0], otherX));
        dd[1] = (short) (Math.max(sd[1], otherY));
        dd[2] = (short) (Math.max(sd[2], otherZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.max(sd[0], otherX);
        dd[1] = Math.max(sd[1], otherY);
        dd[2] = Math.max(sd[2], otherZ);
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
    public Short3 min(short scalar, @Mutated Short3 dest) {
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.min(sd[0], otherX));
        dd[1] = (short) (Math.min(sd[1], otherY));
        dd[2] = (short) (Math.min(sd[2], otherZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.min(sd[0], otherX);
        dd[1] = Math.min(sd[1], otherY);
        dd[2] = Math.min(sd[2], otherZ);
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
    public Short3 sign(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.signum(sd[0]));
        dd[1] = (short) (Math.signum(sd[1]));
        dd[2] = (short) (Math.signum(sd[2]));
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
    public Int3 sign(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Long3 sign(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Double3 sign(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
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
    public Short3 satAdd(Short3R other, @Mutated Short3 dest) {
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satAddS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satAddS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satAddS(sd[2], otherZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddS(sd[2], otherZ);
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
    public Short3 satMul(Short3R other, @Mutated Short3 dest) {
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satMulS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satMulS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satMulS(sd[2], otherZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulS(sd[2], otherZ);
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
    public Short3 satNegate(@Mutated Short3 dest) {
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satNegS(sd[0]));
        dd[1] = (short) (org.joml2.SaturatingMath.satNegS(sd[1]));
        dd[2] = (short) (org.joml2.SaturatingMath.satNegS(sd[2]));
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
    public Int3 satNegate(@Mutated Int3 dest) {
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
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
    public Long3 satNegate(@Mutated Long3 dest) {
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
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
    public Double3 satNegate(@Mutated Double3 dest) {
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegS(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegS(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegS(sd[2]);
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
    public Short3 satSub(Short3R other, @Mutated Short3 dest) {
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
        short[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satSubS(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satSubS(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satSubS(sd[2], otherZ));
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubS(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubS(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubS(sd[2], otherZ);
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
    public Short4 xyz0(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (0);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 0;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 0;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 0;
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
    public Short4 xyz1(@Mutated Short4 dest) {
        short[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (1);
        return dest;
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
        short[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1;
        return dest;
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
        short[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1;
        return dest;
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
        short[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1;
        return dest;
    }

    public short x() { return data[0]; }
    public short y() { return data[1]; }
    public short z() { return data[2]; }

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

    @Override public String toString() {
        return "Short3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Short3Impl)) return false;
        Short3Impl o = (Short3Impl) obj;
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

    @Override public boolean equalsEpsilon(Short3R other, short epsilon) {
        return Math.abs(data[0] - other.x()) <= epsilon
            && Math.abs(data[1] - other.y()) <= epsilon
            && Math.abs(data[2] - other.z()) <= epsilon;
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Short3 load(short[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        return this;
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Short3 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Short3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Short3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Short3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Short3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset + 0] = (byte) this.data[0];
        dest[offset + 1] = (byte) this.data[1];
        dest[offset + 2] = (byte) this.data[2];
        return dest;
    }
    public @Mutated Short3 load(byte[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        return this;
    }
    public ByteBuffer storeByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeByteAbsolute(this, index, buf);
    }
    public Short3 loadByteAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadByteAbsolute(this, index, buf);
    }
    public Short3 storeByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeByteUnsafe(this, address);
    }
    @Mutated public Short3 loadByteUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadByteUnsafe(this, address);
    }
    public MemorySegment storeByte(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeByte(this, offset, dest);
    }
    public Short3 loadByte(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadByte(this, offset, src);
    }

}
