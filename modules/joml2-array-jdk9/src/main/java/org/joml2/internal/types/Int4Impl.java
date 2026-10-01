// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Generated implementation of {@link Int4} backed by a {@code int[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Int4Impl implements Int4 {

    public int[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int4BbOpsUnsafe()
                        : new Int4BbOpsApi();
        static final Int4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int4RawOpsUnsafe()
                        : new Int4RawOpsApi();
    }

    public Int4Impl() {
        data = new int[4];
        data[3] = 1;
    }

    public Int4Impl(int x, int y, int z, int w) {
        int[] dd = this.data = new int[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = w;
    }

    public Int4Impl(Int4R src) {
        int[] dd = this.data = new int[4];
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
    public Int4 add(Int4R other, @Mutated Int4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long4 add(Int4R other, @Mutated Long4 dest) {
        return add(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4 add(Int4R other, @Mutated Double4 dest) {
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
    public Int4 add(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 add(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 add(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 ceilDiv(int scalar, @Mutated Int4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilDiv(int scalar, @Mutated Long4 dest) {
        return ceilDiv(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ceilDiv(int scalar, @Mutated Double4 dest) {
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
    public Int4 ceilDiv(Int4R other, @Mutated Int4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilDiv(Int4R other, @Mutated Long4 dest) {
        return ceilDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ceilDiv(Int4R other, @Mutated Double4 dest) {
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
    public Int4 ceilDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        int _buf2 = Math.ceilDiv(sd[2], otherZ);
        dd[3] = Math.ceilDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 ceilDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        int _buf2 = Math.ceilDiv(sd[2], otherZ);
        dd[3] = Math.ceilDiv(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 ceilDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = Math.ceilDiv(sd[0], otherX);
        int _buf1 = Math.ceilDiv(sd[1], otherY);
        int _buf2 = Math.ceilDiv(sd[2], otherZ);
        dd[3] = Math.ceilDiv(sd[3], otherW);
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
    public Int4 ceilMod(int scalar, @Mutated Int4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilMod(int scalar, @Mutated Long4 dest) {
        return ceilMod(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ceilMod(int scalar, @Mutated Double4 dest) {
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
    public Int4 ceilMod(Int4R other, @Mutated Int4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ceilMod(Int4R other, @Mutated Long4 dest) {
        return ceilMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ceilMod(Int4R other, @Mutated Double4 dest) {
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
    public Int4 ceilMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        int _buf2 = Math.ceilMod(sd[2], otherZ);
        dd[3] = Math.ceilMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 ceilMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        int _buf2 = Math.ceilMod(sd[2], otherZ);
        dd[3] = Math.ceilMod(sd[3], otherW);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        return dest;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 ceilMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        int _buf0 = Math.ceilMod(sd[0], otherX);
        int _buf1 = Math.ceilMod(sd[1], otherY);
        int _buf2 = Math.ceilMod(sd[2], otherZ);
        dd[3] = Math.ceilMod(sd[3], otherW);
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
    public Int4 div(int scalar, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 div(int scalar, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 div(int scalar, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 div(Int4R other, @Mutated Int4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 div(Int4R other, @Mutated Long4 dest) {
        return div(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 div(Int4R other, @Mutated Double4 dest) {
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
    public Int4 div(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 div(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 div(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 floorDiv(int scalar, @Mutated Int4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorDiv(int scalar, @Mutated Long4 dest) {
        return floorDiv(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 floorDiv(int scalar, @Mutated Double4 dest) {
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
    public Int4 floorDiv(Int4R other, @Mutated Int4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorDiv(Int4R other, @Mutated Long4 dest) {
        return floorDiv(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 floorDiv(Int4R other, @Mutated Double4 dest) {
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
    public Int4 floorDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 floorDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 floorDiv(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 floorMod(int scalar, @Mutated Int4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorMod(int scalar, @Mutated Long4 dest) {
        return floorMod(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 floorMod(int scalar, @Mutated Double4 dest) {
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
    public Int4 floorMod(Int4R other, @Mutated Int4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 floorMod(Int4R other, @Mutated Long4 dest) {
        return floorMod(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 floorMod(Int4R other, @Mutated Double4 dest) {
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
    public Int4 floorMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 floorMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 floorMod(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 mul(int scalar, @Mutated Int4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 mul(int scalar, @Mutated Long4 dest) {
        return mul(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(int scalar, @Mutated Double4 dest) {
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
    public Int4 mul(Int4R other, @Mutated Int4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 mul(Int4R other, @Mutated Long4 dest) {
        return mul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(Int4R other, @Mutated Double4 dest) {
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
    public Int4 mul(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 mul(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 mul(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 negate(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 negate(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 negate(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 rem(int scalar, @Mutated Int4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rem(int scalar, @Mutated Long4 dest) {
        return rem(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rem(int scalar, @Mutated Double4 dest) {
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
    public Int4 rem(Int4R other, @Mutated Int4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rem(Int4R other, @Mutated Long4 dest) {
        return rem(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rem(Int4R other, @Mutated Double4 dest) {
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
    public Int4 rem(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 rem(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 rem(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 sub(Int4R other, @Mutated Int4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long4 sub(Int4R other, @Mutated Long4 dest) {
        return sub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sub(Int4R other, @Mutated Double4 dest) {
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
    public Int4 sub(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 sub(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 sub(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 and(Int4R other, @Mutated Int4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long4 and(Int4R other, @Mutated Long4 dest) {
        return and(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Double4 and(Int4R other, @Mutated Double4 dest) {
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
    public Int4 and(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 and(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 and(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 bitCount(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 bitCount(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 bitCount(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 not(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 not(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 not(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 numberOfLeadingZeros(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 numberOfLeadingZeros(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 numberOfLeadingZeros(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 numberOfTrailingZeros(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 numberOfTrailingZeros(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 numberOfTrailingZeros(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 or(Int4R other, @Mutated Int4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long4 or(Int4R other, @Mutated Long4 dest) {
        return or(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Double4 or(Int4R other, @Mutated Double4 dest) {
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
    public Int4 or(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 or(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 or(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 reverseBits(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 reverseBits(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 reverseBits(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 reverseBytes(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 reverseBytes(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 reverseBytes(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 rotateLeft(int distance, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rotateLeft(int distance, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateLeft(int distance, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 rotateRight(int distance, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 rotateRight(int distance, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 rotateRight(int distance, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
        dd[3] = Math.rotateRight(sd[3], distance);
        return dest;
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
    public Int4 shl(int shift, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
        dd[3] = sd[3] << shift;
        return dest;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 shl(int shift, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
        dd[3] = sd[3] << shift;
        return dest;
    }


    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 shl(int shift, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
        dd[3] = sd[3] << shift;
        return dest;
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
    public Int4 shr(int shift, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
        dd[3] = sd[3] >> shift;
        return dest;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 shr(int shift, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
        dd[3] = sd[3] >> shift;
        return dest;
    }


    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 shr(int shift, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
        dd[3] = sd[3] >> shift;
        return dest;
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
    public Int4 ushr(int shift, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
        dd[3] = sd[3] >>> shift;
        return dest;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Long4 ushr(int shift, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
        dd[3] = sd[3] >>> shift;
        return dest;
    }


    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    public Double4 ushr(int shift, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
        dd[3] = sd[3] >>> shift;
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
    public Int4 xor(Int4R other, @Mutated Int4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xor(Int4R other, @Mutated Long4 dest) {
        return xor(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xor(Int4R other, @Mutated Double4 dest) {
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
    public Int4 xor(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 xor(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 xor(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public @Mutated Int4 set(Int4R v) {
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
    @Mutated public Int4 set(int vX, int vY, int vZ, int vW) {
        int[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vW;
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
    public Int4 set(int s, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Long4 set(int s, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    public Double4 set(int s, @Mutated Double4 dest) {
        int[] sd = this.data;
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
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4 toFloat(@Mutated Float4 dest) {
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
        byte[] dd = ((Byte4Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
        dd[3] = (byte) (sd[3]);
        return dest;
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
        int[] sd = this.data;
        short[] dd = ((Short4Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
        dd[3] = (short) (sd[3]);
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
        int[] sd = this.data;
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
    @Mutated public Int4 makeZero() {
        int[] dd = this.data;
        dd[0] = 0;
        dd[1] = 0;
        dd[2] = 0;
        dd[3] = 0;
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
    public Int4 absolute(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 absolute(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 absolute(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 clamp(int min, int max, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Long4 clamp(int min, int max, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    public Double4 clamp(int min, int max, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 clamp(Int4R min, Int4R max, @Mutated Int4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Long4 clamp(Int4R min, Int4R max, @Mutated Long4 dest) {
        return clamp(min.x(), min.y(), min.z(), min.w(), max.x(), max.y(), max.z(), max.w(), dest);
    }


    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    public Double4 clamp(Int4R min, Int4R max, @Mutated Double4 dest) {
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
    public Int4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public long compAdd() {
        int[] sd = this.data;
        return (long) sd[3] + ((long) sd[2] + ((long) sd[0] + (long) sd[1]));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public int compMax() {
        int[] sd = this.data;
        return Math.max(Math.max(Math.max(sd[0], sd[1]), sd[2]), sd[3]);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public int compMin() {
        int[] sd = this.data;
        return Math.min(Math.min(Math.min(sd[0], sd[1]), sd[2]), sd[3]);
    }


    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    public long compMul() {
        int[] sd = this.data;
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
    public Int4 cross(Int4R v, Int4R w, @Mutated Int4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long4 cross(Int4R v, Int4R w, @Mutated Long4 dest) {
        return cross(v.x(), v.y(), v.z(), v.w(), w.x(), w.y(), w.z(), w.w(), dest);
    }


    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 cross(Int4R v, Int4R w, @Mutated Double4 dest) {
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
    public Int4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    public long distanceSquared(Int4R other) {
        return distanceSquared(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
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
    public long distanceSquared(int otherX, int otherY, int otherZ, int otherW) {
        int[] sd = this.data;
        long _t0 = (long) sd[0] - (long) otherX;
        long _t1 = (long) sd[1] - (long) otherY;
        long _t2 = (long) sd[2] - (long) otherZ;
        long _t3 = (long) sd[3] - (long) otherW;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2 + _t3 * _t3;
    }


    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    public long dot(Int4R other) {
        return dot(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
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
    public long dot(int otherX, int otherY, int otherZ, int otherW) {
        int[] sd = this.data;
        return (long) otherX * (long) sd[0] + (long) otherY * (long) sd[1] + (long) otherZ * (long) sd[2] + (long) otherW * (long) sd[3];
    }


    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    public long lengthSquared() {
        int[] sd = this.data;
        return (long) sd[0] * (long) sd[0] + (long) sd[1] * (long) sd[1] + (long) sd[2] * (long) sd[2] + (long) sd[3] * (long) sd[3];
    }


    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    public long manhattanDistance(Int4R other) {
        return manhattanDistance(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
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
    public long manhattanDistance(int otherX, int otherY, int otherZ, int otherW) {
        int[] sd = this.data;
        return Math.abs((long) sd[0] - (long) otherX) + Math.abs((long) sd[1] - (long) otherY) + Math.abs((long) sd[2] - (long) otherZ) + Math.abs((long) sd[3] - (long) otherW);
    }


    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public long manhattanLength() {
        int[] sd = this.data;
        return Math.abs((long) sd[0]) + Math.abs((long) sd[1]) + Math.abs((long) sd[2]) + Math.abs((long) sd[3]);
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
    public Int4 max(int scalar, @Mutated Int4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long4 max(int scalar, @Mutated Long4 dest) {
        return max(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 max(int scalar, @Mutated Double4 dest) {
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
    public Int4 max(Int4R other, @Mutated Int4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long4 max(Int4R other, @Mutated Long4 dest) {
        return max(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 max(Int4R other, @Mutated Double4 dest) {
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
    public Int4 max(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 max(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 max(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 min(int scalar, @Mutated Int4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long4 min(int scalar, @Mutated Long4 dest) {
        return min(scalar, scalar, scalar, scalar, dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(int scalar, @Mutated Double4 dest) {
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
    public Int4 min(Int4R other, @Mutated Int4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long4 min(Int4R other, @Mutated Long4 dest) {
        return min(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double4 min(Int4R other, @Mutated Double4 dest) {
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
    public Int4 min(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 min(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 min(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 sign(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 sign(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 sign(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 satAdd(Int4R other, @Mutated Int4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satAdd(Int4R other, @Mutated Long4 dest) {
        return satAdd(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4 satAdd(Int4R other, @Mutated Double4 dest) {
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
    public Int4 satAdd(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAdd(sd[3], otherW);
        return dest;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 satAdd(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAdd(sd[3], otherW);
        return dest;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector, clamping
     * to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 satAdd(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satAdd(sd[3], otherW);
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
    public Int4 satMul(Int4R other, @Mutated Int4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satMul(Int4R other, @Mutated Long4 dest) {
        return satMul(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    public Double4 satMul(Int4R other, @Mutated Double4 dest) {
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
    public Int4 satMul(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMul(sd[3], otherW);
        return dest;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 satMul(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMul(sd[3], otherW);
        return dest;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 satMul(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satMul(sd[3], otherW);
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
    public Int4 satNegate(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNeg(sd[3]);
        return dest;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satNegate(@Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNeg(sd[3]);
        return dest;
    }


    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 satNegate(@Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
        dd[3] = org.joml2.SaturatingMath.satNeg(sd[3]);
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
    public Int4 satSub(Int4R other, @Mutated Int4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Long4 satSub(Int4R other, @Mutated Long4 dest) {
        return satSub(other.x(), other.y(), other.z(), other.w(), dest);
    }


    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double4 satSub(Int4R other, @Mutated Double4 dest) {
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
    public Int4 satSub(int otherX, int otherY, int otherZ, int otherW, @Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSub(sd[3], otherW);
        return dest;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long4 satSub(int otherX, int otherY, int otherZ, int otherW, @Mutated Long4 dest) {
        int[] sd = this.data;
        long[] dd = ((Long4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSub(sd[3], otherW);
        return dest;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector,
     * clamping to the value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4 satSub(int otherX, int otherY, int otherZ, int otherW, @Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        dd[3] = org.joml2.SaturatingMath.satSub(sd[3], otherW);
        return dest;
    }

    public int x() { return data[0]; }
    public int y() { return data[1]; }
    public int z() { return data[2]; }
    public int w() { return data[3]; }

    public Int2 xx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int2 xy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 xz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 xw(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 yx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 yy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int2 yz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 yw(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 zx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 zy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 zz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int2 zw(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 wx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 wy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 wz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        return dest;
    }

    public Int2 ww(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int3 xxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Int3 xxy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xxz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xxw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xyx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 xyy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xyz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xyw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xzx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 xzy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xzz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xzw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xwx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 xwy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xwz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xww(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yxy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 yxz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 yxw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 yyx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yyy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Int3 yyz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yyw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yzx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 yzy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 yzz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yzw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 ywx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 ywy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 ywz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 yww(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zxy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zxz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 zxw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zyx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zyy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zyz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 zyw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zzx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zzy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zzz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Int3 zzw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zwx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zwy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zwz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 zww(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wxy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wxz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wxw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 wyx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wyy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wyz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wyw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 wzx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wzy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 wzz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wzw(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        return dest;
    }

    public Int3 wwx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wwy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 wwz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        return dest;
    }

    public Int3 www(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Int4 xxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xxxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xxxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xxxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xxyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xxyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xxyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xxzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xxzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xxwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xxww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xyxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xyxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xyyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xyyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xyyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xyzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xyzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xyzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[2];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xywx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xywy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xywz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xyww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xzxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xzxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xzyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xzyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xzzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xzzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xzzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xzwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xzwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xzww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xwxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xwyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xwyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xwyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xwzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xwzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 xwzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xwwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 xwwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xwww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[0];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yxxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yxxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yxyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yxyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yxyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yxzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yxzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yxzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 yxwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yxwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 yxww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yyxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yyxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yyxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yyxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yyyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yyyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yyyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yyyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yyzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yyzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yyzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yyzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yywx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yywy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yywz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yyww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yzxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yzxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yzxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yzxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 yzyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yzyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yzyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yzyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yzzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yzzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yzzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yzzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 yzwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 yzwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 yzwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yzww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[2];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 ywxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 ywxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 ywyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 ywyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 ywzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 ywzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 ywzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 ywwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 ywwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 ywww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[1];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zxxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zxxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zxyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zxyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zxyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zxzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zxzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zxzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zxwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zxwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zxww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zyxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zyxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        int _v3 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zyyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zyyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zyyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zyzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zyzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zywx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zywy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zywz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zyww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zzxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zzxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zzyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zzyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        int _v2 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zzzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zzzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zzzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zzwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zzwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zzww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zwxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zwxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zwxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zwyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 zwyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zwyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zwzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zwzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zwwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zwwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 zwww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[2];
        int _v1 = sd[3];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wxxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wxyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wxyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wxyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wxzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wxzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wxzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wxwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wxwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wxww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wyxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wyxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wyxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        int _v3 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wyxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wyyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wyyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wyyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wyyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wyzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wyzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wyzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wyzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wywx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wywy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wywz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wyww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wzxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        int _v3 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wzxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wzxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wzyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        int _v3 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v3;
        return dest;
    }

    public Int4 wzyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wzyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wzzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wzzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wzwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wzwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wzww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v1;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wwxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwxz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwxw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wwyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwyw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wwzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwzy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 wwzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwzw(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v1;
        dd[3] = _v0;
        return dest;
    }

    public Int4 wwwx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[0];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwwy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[1];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwwz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        int _v1 = sd[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v1;
        return dest;
    }

    public Int4 wwww(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = sd[3];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Int4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int4Impl)) return false;
        Int4Impl o = (Int4Impl) obj;
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

    @Override public boolean equalsEpsilon(Int4R other, int epsilon) {
        return Math.abs((long) data[0] - (long) other.x()) <= epsilon
            && Math.abs((long) data[1] - (long) other.y()) <= epsilon
            && Math.abs((long) data[2] - (long) other.z()) <= epsilon
            && Math.abs((long) data[3] - (long) other.w()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Int4 load(int[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Int4 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public Int4 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Int4 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Int4 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated Int4 load(long[] src, int offset) {
        this.data[0] = (int) src[offset + 0];
        this.data[1] = (int) src[offset + 1];
        this.data[2] = (int) src[offset + 2];
        this.data[3] = (int) src[offset + 3];
        return this;
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public Int4 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, index, buf);
    }
    public Int4 loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(this, index, buf);
    }
    public Int4 storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }
    @Mutated public Int4 loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(this, address);
    }

}
