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
 * Generated implementation of {@link Byte3} backed by a {@code byte[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Byte3Impl implements Byte3 {

    public byte[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
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
        data = new byte[3];
    }

    public Byte3Impl(byte x, byte y, byte z) {
        byte[] dd = this.data = new byte[3];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
    }

    public Byte3Impl(Byte3R src) {
        byte[] dd = this.data = new byte[3];
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
    public Byte3 add(Byte3R other, @Mutated Byte3 dest) {
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
        return add(other.x(), other.y(), other.z(), dest);
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
    public Byte3 add(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (otherX + sd[0]);
        dd[1] = (byte) (otherY + sd[1]);
        dd[2] = (byte) (otherZ + sd[2]);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 ceilDiv(byte scalar, @Mutated Byte3 dest) {
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
        return ceilDiv(scalar, scalar, scalar, dest);
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
    public Byte3 ceilDiv(Byte3R other, @Mutated Byte3 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
        return ceilDiv(other.x(), other.y(), other.z(), dest);
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
    public Byte3 ceilDiv(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        dd[2] = (byte) (Math.ceilDiv(sd[2], otherZ));
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        dd[2] = (short) (Math.ceilDiv(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        dd[2] = Math.ceilDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        dd[2] = Math.ceilDiv(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        dd[2] = Math.ceilDiv(sd[2], otherZ);
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
    public Byte3 ceilMod(byte scalar, @Mutated Byte3 dest) {
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
        return ceilMod(scalar, scalar, scalar, dest);
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
    public Byte3 ceilMod(Byte3R other, @Mutated Byte3 dest) {
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
        return ceilMod(other.x(), other.y(), other.z(), dest);
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
    public Byte3 ceilMod(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        dd[2] = (byte) (Math.ceilMod(sd[2], otherZ));
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        dd[2] = (short) (Math.ceilMod(sd[2], otherZ));
        dd[0] = (short) (_buf0);
        dd[1] = (short) (_buf1);
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        dd[2] = Math.ceilMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        dd[2] = Math.ceilMod(sd[2], otherZ);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        dd[2] = Math.ceilMod(sd[2], otherZ);
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
    public Byte3 div(byte scalar, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = sd[0] / scalar;
        int _buf1 = sd[1] / scalar;
        dd[2] = (byte) (sd[2] / scalar);
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 div(Byte3R other, @Mutated Byte3 dest) {
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
        return div(other.x(), other.y(), other.z(), dest);
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
    public Byte3 div(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = sd[0] / otherX;
        int _buf1 = sd[1] / otherY;
        dd[2] = (byte) (sd[2] / otherZ);
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 floorDiv(byte scalar, @Mutated Byte3 dest) {
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
        return floorDiv(scalar, scalar, scalar, dest);
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
    public Byte3 floorDiv(Byte3R other, @Mutated Byte3 dest) {
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
        return floorDiv(other.x(), other.y(), other.z(), dest);
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
    public Byte3 floorDiv(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], otherX);
        int _buf1 = Math.floorDiv(sd[1], otherY);
        dd[2] = (byte) (Math.floorDiv(sd[2], otherZ));
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 floorMod(byte scalar, @Mutated Byte3 dest) {
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
        return floorMod(scalar, scalar, scalar, dest);
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
    public Byte3 floorMod(Byte3R other, @Mutated Byte3 dest) {
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
        return floorMod(other.x(), other.y(), other.z(), dest);
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
    public Byte3 floorMod(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], otherX);
        int _buf1 = Math.floorMod(sd[1], otherY);
        dd[2] = (byte) (Math.floorMod(sd[2], otherZ));
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 mul(byte scalar, @Mutated Byte3 dest) {
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
        return mul(scalar, scalar, scalar, dest);
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
    public Byte3 mul(Byte3R other, @Mutated Byte3 dest) {
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
        return mul(other.x(), other.y(), other.z(), dest);
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
    public Byte3 mul(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (otherX * sd[0]);
        dd[1] = (byte) (otherY * sd[1]);
        dd[2] = (byte) (otherZ * sd[2]);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 negate(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (-sd[0]);
        dd[1] = (byte) (-sd[1]);
        dd[2] = (byte) (-sd[2]);
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (-sd[0]);
        dd[1] = (short) (-sd[1]);
        dd[2] = (short) (-sd[2]);
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        return dest;
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
        byte[] sd = this.data;
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
    public Byte3 rem(byte scalar, @Mutated Byte3 dest) {
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
        return rem(scalar, scalar, scalar, dest);
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
    public Byte3 rem(Byte3R other, @Mutated Byte3 dest) {
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
        return rem(other.x(), other.y(), other.z(), dest);
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
    public Byte3 rem(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = sd[0] % otherX;
        int _buf1 = sd[1] % otherY;
        dd[2] = (byte) (sd[2] % otherZ);
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 sub(Byte3R other, @Mutated Byte3 dest) {
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
        return sub(other.x(), other.y(), other.z(), dest);
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
    public Byte3 sub(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] - otherX);
        dd[1] = (byte) (sd[1] - otherY);
        dd[2] = (byte) (sd[2] - otherZ);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 and(Byte3R other, @Mutated Byte3 dest) {
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
        return and(other.x(), other.y(), other.z(), dest);
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
    public Byte3 and(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] & otherX);
        dd[1] = (byte) (sd[1] & otherY);
        dd[2] = (byte) (sd[2] & otherZ);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 bitCount(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.bitCount(sd[0]));
        dd[1] = (byte) (Math.bitCount(sd[1]));
        dd[2] = (byte) (Math.bitCount(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 not(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (~sd[0]);
        dd[1] = (byte) (~sd[1]);
        dd[2] = (byte) (~sd[2]);
        return dest;
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
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 not(@Mutated Int3 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 not(@Mutated Long3 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 not(@Mutated Double3 dest) {
        byte[] sd = this.data;
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
    public Byte3 numberOfLeadingZeros(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.numberOfLeadingZeros(sd[0]));
        dd[1] = (byte) (Math.numberOfLeadingZeros(sd[1]));
        dd[2] = (byte) (Math.numberOfLeadingZeros(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 numberOfTrailingZeros(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.numberOfTrailingZeros(sd[0]));
        dd[1] = (byte) (Math.numberOfTrailingZeros(sd[1]));
        dd[2] = (byte) (Math.numberOfTrailingZeros(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 or(Byte3R other, @Mutated Byte3 dest) {
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
        return or(other.x(), other.y(), other.z(), dest);
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
    public Byte3 or(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] | otherX);
        dd[1] = (byte) (sd[1] | otherY);
        dd[2] = (byte) (sd[2] | otherZ);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 reverseBits(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.reverseBits(sd[0]));
        dd[1] = (byte) (Math.reverseBits(sd[1]));
        dd[2] = (byte) (Math.reverseBits(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 reverseBytes(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.reverseBytes(sd[0]));
        dd[1] = (byte) (Math.reverseBytes(sd[1]));
        dd[2] = (byte) (Math.reverseBytes(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 rotateLeft(byte distance, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.rotateLeft(sd[0], distance));
        dd[1] = (byte) (Math.rotateLeft(sd[1], distance));
        dd[2] = (byte) (Math.rotateLeft(sd[2], distance));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 rotateRight(byte distance, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.rotateRight(sd[0], distance));
        dd[1] = (byte) (Math.rotateRight(sd[1], distance));
        dd[2] = (byte) (Math.rotateRight(sd[2], distance));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        return dest;
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
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] << (shift & 7));
        dd[1] = (byte) (sd[1] << (shift & 7));
        dd[2] = (byte) (sd[2] << (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] << (shift & 7));
        dd[1] = (short) (sd[1] << (shift & 7));
        dd[2] = (short) (sd[2] << (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] << (shift & 7);
        dd[1] = sd[1] << (shift & 7);
        dd[2] = sd[2] << (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] << (shift & 7);
        dd[1] = sd[1] << (shift & 7);
        dd[2] = sd[2] << (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] << (shift & 7);
        dd[1] = sd[1] << (shift & 7);
        dd[2] = sd[2] << (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] >> (shift & 7));
        dd[1] = (byte) (sd[1] >> (shift & 7));
        dd[2] = (byte) (sd[2] >> (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0] >> (shift & 7));
        dd[1] = (short) (sd[1] >> (shift & 7));
        dd[2] = (short) (sd[2] >> (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 7);
        dd[1] = sd[1] >> (shift & 7);
        dd[2] = sd[2] >> (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 7);
        dd[1] = sd[1] >> (shift & 7);
        dd[2] = sd[2] >> (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] >> (shift & 7);
        dd[1] = sd[1] >> (shift & 7);
        dd[2] = sd[2] >> (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) ((sd[0] & 0xFF) >>> (shift & 7));
        dd[1] = (byte) ((sd[1] & 0xFF) >>> (shift & 7));
        dd[2] = (byte) ((sd[2] & 0xFF) >>> (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) ((sd[0] & 0xFF) >>> (shift & 7));
        dd[1] = (short) ((sd[1] & 0xFF) >>> (shift & 7));
        dd[2] = (short) ((sd[2] & 0xFF) >>> (shift & 7));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = (sd[0] & 0xFF) >>> (shift & 7);
        dd[1] = (sd[1] & 0xFF) >>> (shift & 7);
        dd[2] = (sd[2] & 0xFF) >>> (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = (sd[0] & 0xFF) >>> (shift & 7);
        dd[1] = (sd[1] & 0xFF) >>> (shift & 7);
        dd[2] = (sd[2] & 0xFF) >>> (shift & 7);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = (sd[0] & 0xFF) >>> (shift & 7);
        dd[1] = (sd[1] & 0xFF) >>> (shift & 7);
        dd[2] = (sd[2] & 0xFF) >>> (shift & 7);
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
    public Byte3 xor(Byte3R other, @Mutated Byte3 dest) {
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
        return xor(other.x(), other.y(), other.z(), dest);
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
    public Byte3 xor(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0] ^ otherX);
        dd[1] = (byte) (sd[1] ^ otherY);
        dd[2] = (byte) (sd[2] ^ otherZ);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public @Mutated Byte3 set(Byte3R v) {
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
    @Mutated public Byte3 set(byte vX, byte vY, byte vZ) {
        byte[] dd = this.data;
        dd[0] = (byte) (vX);
        dd[1] = (byte) (vY);
        dd[2] = (byte) (vZ);
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
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (s);
        dd[1] = (byte) (s);
        dd[2] = (byte) (s);
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (s);
        dd[1] = (short) (s);
        dd[2] = (short) (s);
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    @Mutated public Byte3 makeZero() {
        byte[] dd = this.data;
        dd[0] = (byte) (0);
        dd[1] = (byte) (0);
        dd[2] = (byte) (0);
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
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.abs(sd[0]));
        dd[1] = (byte) (Math.abs(sd[1]));
        dd[2] = (byte) (Math.abs(sd[2]));
        return dest;
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
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int3 absolute(@Mutated Int3 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 absolute(@Mutated Long3 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 absolute(@Mutated Double3 dest) {
        byte[] sd = this.data;
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
    public Byte3 clamp(byte min, byte max, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.min(Math.max(sd[0], min), max));
        dd[1] = (byte) (Math.min(Math.max(sd[1], min), max));
        dd[2] = (byte) (Math.min(Math.max(sd[2], min), max));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 clamp(Byte3R min, Byte3R max, @Mutated Byte3 dest) {
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
        return clamp(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), dest);
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
    public Byte3 clamp(byte minX, byte minY, byte minZ, byte maxX, byte maxY, byte maxZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.min(Math.max(sd[0], minX), maxX));
        dd[1] = (byte) (Math.min(Math.max(sd[1], minY), maxY));
        dd[2] = (byte) (Math.min(Math.max(sd[2], minZ), maxZ));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public int compAdd() {
        byte[] sd = this.data;
        return sd[2] + (sd[0] + sd[1]);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public byte compMax() {
        byte[] sd = this.data;
        return (byte) (Math.max(Math.max(sd[0], sd[1]), sd[2]));
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public byte compMin() {
        byte[] sd = this.data;
        return (byte) (Math.min(Math.min(sd[0], sd[1]), sd[2]));
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
        byte[] sd = this.data;
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
    public Byte3 cross(Byte3R other, @Mutated Byte3 dest) {
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
        return cross(other.x(), other.y(), other.z(), dest);
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
    public Byte3 cross(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = (byte) (otherY * sd[0] - otherX * sd[1]);
        dd[0] = (byte) (_buf0);
        dd[1] = (byte) (_buf1);
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
     * The value is computed and returned as {@code int}, so it is exact: a result beyond the
     * {@code byte} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public int distanceSquared(Byte3R other) {
        return distanceSquared(other.x(), other.y(), other.z());
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
        byte[] sd = this.data;
        int _t0 = sd[0] - otherX;
        int _t1 = sd[1] - otherY;
        int _t2 = sd[2] - otherZ;
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
        return dot(other.x(), other.y(), other.z());
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
        byte[] sd = this.data;
        return otherX * sd[0] + otherY * sd[1] + otherZ * sd[2];
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
        byte[] sd = this.data;
        return sd[0] * sd[0] + sd[1] * sd[1] + sd[2] * sd[2];
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
        return manhattanDistance(other.x(), other.y(), other.z());
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
        byte[] sd = this.data;
        return Math.abs(sd[0] - otherX) + Math.abs(sd[1] - otherY) + Math.abs(sd[2] - otherZ);
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
        byte[] sd = this.data;
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
    public Byte3 max(byte scalar, @Mutated Byte3 dest) {
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
        return max(scalar, scalar, scalar, dest);
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
    public Byte3 max(Byte3R other, @Mutated Byte3 dest) {
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
        return max(other.x(), other.y(), other.z(), dest);
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
    public Byte3 max(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.max(sd[0], otherX));
        dd[1] = (byte) (Math.max(sd[1], otherY));
        dd[2] = (byte) (Math.max(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 min(byte scalar, @Mutated Byte3 dest) {
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
        return min(scalar, scalar, scalar, dest);
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
    public Byte3 min(Byte3R other, @Mutated Byte3 dest) {
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
        return min(other.x(), other.y(), other.z(), dest);
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
    public Byte3 min(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.min(sd[0], otherX));
        dd[1] = (byte) (Math.min(sd[1], otherY));
        dd[2] = (byte) (Math.min(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
        byte[] sd = this.data;
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
    public Byte3 sign(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (Math.signum(sd[0]));
        dd[1] = (byte) (Math.signum(sd[1]));
        dd[2] = (byte) (Math.signum(sd[2]));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (Math.signum(sd[0]));
        dd[1] = (short) (Math.signum(sd[1]));
        dd[2] = (short) (Math.signum(sd[2]));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
        return dest;
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
        byte[] sd = this.data;
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
    public Byte3 satAdd(Byte3R other, @Mutated Byte3 dest) {
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
        return satAdd(other.x(), other.y(), other.z(), dest);
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
    public Byte3 satAdd(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (org.joml2.SaturatingMath.satAddB(sd[0], otherX));
        dd[1] = (byte) (org.joml2.SaturatingMath.satAddB(sd[1], otherY));
        dd[2] = (byte) (org.joml2.SaturatingMath.satAddB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satAddB(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satAddB(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satAddB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAddB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAddB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAddB(sd[2], otherZ);
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
    public Byte3 satMul(Byte3R other, @Mutated Byte3 dest) {
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
        return satMul(other.x(), other.y(), other.z(), dest);
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
    public Byte3 satMul(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (org.joml2.SaturatingMath.satMulB(sd[0], otherX));
        dd[1] = (byte) (org.joml2.SaturatingMath.satMulB(sd[1], otherY));
        dd[2] = (byte) (org.joml2.SaturatingMath.satMulB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satMulB(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satMulB(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satMulB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMulB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMulB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMulB(sd[2], otherZ);
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
    public Byte3 satNegate(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (org.joml2.SaturatingMath.satNegB(sd[0]));
        dd[1] = (byte) (org.joml2.SaturatingMath.satNegB(sd[1]));
        dd[2] = (byte) (org.joml2.SaturatingMath.satNegB(sd[2]));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satNegB(sd[0]));
        dd[1] = (short) (org.joml2.SaturatingMath.satNegB(sd[1]));
        dd[2] = (short) (org.joml2.SaturatingMath.satNegB(sd[2]));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegB(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegB(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegB(sd[2]);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegB(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegB(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegB(sd[2]);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNegB(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNegB(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNegB(sd[2]);
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
    public Byte3 satSub(Byte3R other, @Mutated Byte3 dest) {
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
        return satSub(other.x(), other.y(), other.z(), dest);
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
    public Byte3 satSub(byte otherX, byte otherY, byte otherZ, @Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (org.joml2.SaturatingMath.satSubB(sd[0], otherX));
        dd[1] = (byte) (org.joml2.SaturatingMath.satSubB(sd[1], otherY));
        dd[2] = (byte) (org.joml2.SaturatingMath.satSubB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (org.joml2.SaturatingMath.satSubB(sd[0], otherX));
        dd[1] = (short) (org.joml2.SaturatingMath.satSubB(sd[1], otherY));
        dd[2] = (short) (org.joml2.SaturatingMath.satSubB(sd[2], otherZ));
        return dest;
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
        byte[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubB(sd[2], otherZ);
        return dest;
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
        byte[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSubB(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSubB(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSubB(sd[2], otherZ);
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
    public Byte4 xyz0(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (0);
        return dest;
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
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 xyz0(@Mutated Int4 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz0(@Mutated Long4 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz0(@Mutated Double4 dest) {
        byte[] sd = this.data;
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
    public Byte4 xyz1(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (1);
        return dest;
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
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code int}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int4 xyz1(@Mutated Int4 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz1(@Mutated Long4 dest) {
        byte[] sd = this.data;
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
     * The computation is performed at {@code int} precision - Java promotes {@code byte} operands
     * before evaluating - and each result component is then stored as {@code double}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz1(@Mutated Double4 dest) {
        byte[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1;
        return dest;
    }

    public byte x() { return data[0]; }
    public byte y() { return data[1]; }
    public byte z() { return data[2]; }

    public Byte2 xx(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Byte2 xy(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 xz(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 yx(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 yy(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Byte2 yz(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 zx(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 zy(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Byte2 zz(@Mutated Byte2 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte2Impl) dest).data;
        byte _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Byte3 xxx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 xxy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 xxz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 xyx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 xyy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 xyz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 xzx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 xzy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 xzz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 yxx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 yxy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 yxz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 yyx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 yyy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 yyz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 yzx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 yzy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 yzz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 zxx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 zxy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 zxz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 zyx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Byte3 zyy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 zyz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Byte3 zzx(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 zzy(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Byte3 zzz(@Mutated Byte3 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        byte _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Byte4 xxxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xxxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xxxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xxyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xxyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xxyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xxzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xxzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xxzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xyxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xyxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xyxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xyyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xyyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xyyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xyzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xyzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xyzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[1];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xzxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xzxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xzxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xzyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xzyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xzyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 xzzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 xzzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 xzzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[0];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yxxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yxxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yxxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yxyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yxyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yxyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yxzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yxzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yxzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yyxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yyxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yyxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        byte _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yyyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yyyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yyyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yyzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yyzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yyzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yzxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yzxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yzxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yzyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yzyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yzyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 yzzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 yzzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 yzzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[1];
        byte _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zxxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zxxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zxxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zxyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zxyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zxyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zxzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zxzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zxzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zyxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zyxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zyxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zyyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zyyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zyyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zyzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zyzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zyzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zzxx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zzxy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        byte _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zzxz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zzyx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        byte _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Byte4 zzyy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zzyz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Byte4 zzzx(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zzzy(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        byte _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Byte4 zzzz(@Mutated Byte4 dest) {
        byte[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        byte _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Byte3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Byte3Impl)) return false;
        Byte3Impl o = (Byte3Impl) obj;
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

    @Override public boolean equalsEpsilon(Byte3R other, byte epsilon) {
        return Math.abs(data[0] - other.x()) <= epsilon
            && Math.abs(data[1] - other.y()) <= epsilon
            && Math.abs(data[2] - other.z()) <= epsilon;
    }

    public byte[] store(@Mutated byte[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Byte3 load(byte[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        return this;
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Byte3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Byte3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Byte3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public short[] store(@Mutated short[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Byte3 load(short[] src, int offset) {
        this.data[0] = (byte) src[offset + 0];
        this.data[1] = (byte) src[offset + 1];
        this.data[2] = (byte) src[offset + 2];
        return this;
    }
    public ShortBuffer storeAbsolute(int index, @Mutated ShortBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Byte3 loadAbsolute(int index, ShortBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeShortAbsolute(this, index, buf);
    }
    public Byte3 loadShortAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadShortAbsolute(this, index, buf);
    }
    public Byte3 storeShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeShortUnsafe(this, address);
    }
    @Mutated public Byte3 loadShortUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadShortUnsafe(this, address);
    }

}
