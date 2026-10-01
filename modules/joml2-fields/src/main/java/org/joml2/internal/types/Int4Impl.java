// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Generated implementation of {@link Int4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Int4Impl implements Int4 {

    public int x;
    public int y;
    public int z;
    public int w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int4SegOpsUnsafe()
                        : new Int4SegOpsMS();
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
        w = 1;
    }

    public Int4Impl(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Int4Impl(Int4R src) {
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
    public Int4 div(int scalar, @Mutated Int4 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int4 rotateLeft(int distance, @Mutated Int4 dest) {
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
    public Int4 rotateRight(int distance, @Mutated Int4 dest) {
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
        d.w = Math.rotateRight(this.w, distance);
        return d;
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
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
        d.w = this.w << shift;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
        d.w = this.w << shift;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
        d.w = this.w << shift;
        return d;
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
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
        d.w = this.w >> shift;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
        d.w = this.w >> shift;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
        d.w = this.w >> shift;
        return d;
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
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
        d.w = this.w >>> shift;
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
        d.w = this.w >>> shift;
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
        d.w = this.w >>> shift;
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
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
     * The conversion may lose precision or range.
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
        Short4Impl d = (Short4Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
        d.w = (short) (this.w);
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
    @Mutated public Int4 makeZero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int4 clamp(int min, int max, @Mutated Int4 dest) {
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
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    public long compAdd() {
        return (long) this.w + ((long) this.z + ((long) this.x + (long) this.y));
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public int compMax() {
        return Math.max(Math.max(Math.max(this.x, this.y), this.z), this.w);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public int compMin() {
        return Math.min(Math.min(Math.min(this.x, this.y), this.z), this.w);
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
        return (long) this.w * (long) this.z * (long) this.x * (long) this.y;
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
        long _t0 = (long) this.x - (long) otherX;
        long _t1 = (long) this.y - (long) otherY;
        long _t2 = (long) this.z - (long) otherZ;
        long _t3 = (long) this.w - (long) otherW;
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
        return (long) otherX * (long) this.x + (long) otherY * (long) this.y + (long) otherZ * (long) this.z + (long) otherW * (long) this.w;
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
        return (long) this.x * (long) this.x + (long) this.y * (long) this.y + (long) this.z * (long) this.z + (long) this.w * (long) this.w;
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
        return Math.abs((long) this.x - (long) otherX) + Math.abs((long) this.y - (long) otherY) + Math.abs((long) this.z - (long) otherZ) + Math.abs((long) this.w - (long) otherW);
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
        return Math.abs((long) this.x) + Math.abs((long) this.y) + Math.abs((long) this.z) + Math.abs((long) this.w);
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAdd(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAdd(this.w, otherW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satAdd(this.w, otherW);
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
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMul(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMul(this.w, otherW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satMul(this.w, otherW);
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
    public Int4 satNegate(@Mutated Int4 dest) {
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
        d.w = org.joml2.SaturatingMath.satNeg(this.w);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
        d.w = org.joml2.SaturatingMath.satNeg(this.w);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
        d.w = org.joml2.SaturatingMath.satNeg(this.w);
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
        Int4Impl d = (Int4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSub(this.w, otherW);
        return d;
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
        Long4Impl d = (Long4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSub(this.w, otherW);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        d.w = org.joml2.SaturatingMath.satSub(this.w, otherW);
        return d;
    }

    public int x() { return this.x; }
    public int y() { return this.y; }
    public int z() { return this.z; }
    public int w() { return this.w; }

    public Int2 xx(@Mutated Int2 dest) {
        int _v0 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Int2 xy(@Mutated Int2 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 xz(@Mutated Int2 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 xw(@Mutated Int2 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 yx(@Mutated Int2 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 yy(@Mutated Int2 dest) {
        int _v0 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Int2 yz(@Mutated Int2 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 yw(@Mutated Int2 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 zx(@Mutated Int2 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 zy(@Mutated Int2 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 zz(@Mutated Int2 dest) {
        int _v0 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Int2 zw(@Mutated Int2 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 wx(@Mutated Int2 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 wy(@Mutated Int2 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 wz(@Mutated Int2 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v1;
        return dest;
    }

    public Int2 ww(@Mutated Int2 dest) {
        int _v0 = this.w;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Int3 xxx(@Mutated Int3 dest) {
        int _v0 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Int3 xxy(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 xxz(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 xxw(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 xyx(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 xyy(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 xyz(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xyw(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xzx(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 xzy(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xzz(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 xzw(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xwx(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 xwy(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xwz(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xww(@Mutated Int3 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 yxx(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 yxy(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 yxz(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 yxw(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 yyx(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 yyy(@Mutated Int3 dest) {
        int _v0 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Int3 yyz(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 yyw(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 yzx(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 yzy(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 yzz(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 yzw(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 ywx(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 ywy(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 ywz(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 yww(@Mutated Int3 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 zxx(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 zxy(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zxz(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 zxw(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zyx(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zyy(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 zyz(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 zyw(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zzx(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 zzy(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 zzz(@Mutated Int3 dest) {
        int _v0 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Int3 zzw(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 zwx(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zwy(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zwz(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 zww(@Mutated Int3 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 wxx(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 wxy(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wxz(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wxw(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 wyx(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wyy(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 wyz(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wyw(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 wzx(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wzy(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 wzz(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 wzw(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        return dest;
    }

    public Int3 wwx(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 wwy(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 wwz(@Mutated Int3 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        return dest;
    }

    public Int3 www(@Mutated Int3 dest) {
        int _v0 = this.w;
        Int3Impl d = (Int3Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        return dest;
    }

    public Int4 xxxx(@Mutated Int4 dest) {
        int _v0 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 xxxy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xxxz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xxxw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xxyx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xxyy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xxyz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxyw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxzx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xxzy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxzz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xxzw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxwx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xxwy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxwz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xxww(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xyxx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 xyxy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xyxz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xyxw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xyyx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xyyy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xyyz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xyyw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xyzx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xyzy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xyzz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xyzw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.z;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xywx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xywy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xywz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xyww(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xzxx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 xzxy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xzxz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xzxw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xzyx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xzyy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xzyz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xzyw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xzzx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xzzy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xzzz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xzzw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xzwx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xzwy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xzwz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xzww(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xwxx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 xwxy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xwxz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 xwxw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 xwyx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xwyy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xwyz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xwyw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xwzx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 xwzy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 xwzz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xwzw(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xwwx(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 xwwy(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xwwz(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xwww(@Mutated Int4 dest) {
        int _v0 = this.x;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yxxx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yxxy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 yxxz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yxxw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yxyx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 yxyy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 yxyz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 yxyw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 yxzx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 yxzy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 yxzz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 yxzw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 yxwx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 yxwy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 yxwz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 yxww(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 yyxx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yyxy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 yyxz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yyxw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yyyx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 yyyy(@Mutated Int4 dest) {
        int _v0 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 yyyz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 yyyw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 yyzx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yyzy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 yyzz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yyzw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yywx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yywy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 yywz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yyww(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yzxx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 yzxy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 yzxz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 yzxw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 yzyx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 yzyy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 yzyz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 yzyw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 yzzx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yzzy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 yzzz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yzzw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 yzwx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 yzwy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 yzwz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 yzww(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.z;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 ywxx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 ywxy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 ywxz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 ywxw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 ywyx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 ywyy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 ywyz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 ywyw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 ywzx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 ywzy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 ywzz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 ywzw(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 ywwx(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 ywwy(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 ywwz(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 ywww(@Mutated Int4 dest) {
        int _v0 = this.y;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zxxx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zxxy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zxxz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zxxw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zxyx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zxyy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zxyz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zxyw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zxzx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zxzy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zxzz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 zxzw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zxwx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zxwy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zxwz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zxww(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zyxx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zyxy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zyxz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zyxw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        int _v3 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zyyx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zyyy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zyyz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zyyw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zyzx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zyzy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zyzz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 zyzw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zywx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zywy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zywz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zyww(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zzxx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zzxy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzxz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zzxw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzyx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzyy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zzyz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zzyw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        int _v2 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzzx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zzzy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zzzz(@Mutated Int4 dest) {
        int _v0 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 zzzw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zzwx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzwy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zzwz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zzww(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zwxx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zwxy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zwxz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zwxw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zwyx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 zwyy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zwyz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 zwyw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zwzx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zwzy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 zwzz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 zwzw(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 zwwx(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zwwy(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zwwz(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 zwww(@Mutated Int4 dest) {
        int _v0 = this.z;
        int _v1 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wxxx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wxxy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wxxz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wxxw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wxyx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wxyy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wxyz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wxyw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wxzx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wxzy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wxzz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wxzw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wxwx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wxwy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wxwz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wxww(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 wyxx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wyxy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wyxz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        int _v3 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wyxw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wyyx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wyyy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wyyz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wyyw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wyzx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wyzy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wyzz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wyzw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wywx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wywy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wywz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wyww(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 wzxx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wzxy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        int _v3 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wzxz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wzxw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wzyx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        int _v3 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v3;
        return dest;
    }

    public Int4 wzyy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 wzyz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 wzyw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v2;
        d.w = _v0;
        return dest;
    }

    public Int4 wzzx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wzzy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wzzz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wzzw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wzwx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wzwy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v2;
        return dest;
    }

    public Int4 wzwz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wzww(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v1;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    public Int4 wwxx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wwxy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwxz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwxw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wwyx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwyy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wwyz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwyw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wwzx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwzy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 wwzz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 wwzw(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v1;
        d.w = _v0;
        return dest;
    }

    public Int4 wwwx(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wwwy(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wwwz(@Mutated Int4 dest) {
        int _v0 = this.w;
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v1;
        return dest;
    }

    public Int4 wwww(@Mutated Int4 dest) {
        int _v0 = this.w;
        Int4Impl d = (Int4Impl) dest;
        d.x = _v0;
        d.y = _v0;
        d.z = _v0;
        d.w = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Int4(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int4Impl)) return false;
        Int4Impl o = (Int4Impl) obj;
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

    @Override public boolean equalsEpsilon(Int4R other, int epsilon) {
        return Math.abs((long) x - (long) other.x()) <= epsilon
            && Math.abs((long) y - (long) other.y()) <= epsilon
            && Math.abs((long) z - (long) other.z()) <= epsilon
            && Math.abs((long) w - (long) other.w()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Int4 load(int[] src, int offset) {
        this.x = src[offset + 0];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        this.w = src[offset + 3];
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
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public Int4 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }
    public @Mutated Int4 load(long[] src, int offset) {
        this.x = (int) src[offset + 0];
        this.y = (int) src[offset + 1];
        this.z = (int) src[offset + 2];
        this.w = (int) src[offset + 3];
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
    public MemorySegment storeLong(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeLong(this, offset, dest);
    }
    public Int4 loadLong(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadLong(this, offset, src);
    }

}
