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
 * Generated implementation of {@link Int3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Int3Impl implements Int3 {

    public int x;
    public int y;
    public int z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int3BbOpsUnsafe()
                        : new Int3BbOpsApi();
        static final Int3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int3RawOpsUnsafe()
                        : new Int3RawOpsApi();
    }

    public Int3Impl() {
    }

    public Int3Impl(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Int3Impl(Int3R src) {
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
    public Int3 add(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
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
    public Long3 add(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        return d;
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
    public Double3 add(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 add(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 add(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 add(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 ceilDiv(int scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Long3 ceilDiv(int scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Double3 ceilDiv(int scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, scalar);
        int _buf1 = Math.ceilDiv(this.y, scalar);
        d.z = Math.ceilDiv(this.z, scalar);
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
    public Int3 ceilDiv(Int3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Long3 ceilDiv(Int3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Double3 ceilDiv(Int3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, other.x());
        int _buf1 = Math.ceilDiv(this.y, other.y());
        d.z = Math.ceilDiv(this.z, other.z());
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
    public Int3 ceilDiv(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilDiv(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling division of this vector by ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilDiv(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilDiv(this.x, otherX);
        int _buf1 = Math.ceilDiv(this.y, otherY);
        d.z = Math.ceilDiv(this.z, otherZ);
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
    public Int3 ceilMod(int scalar, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Long3 ceilMod(int scalar, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Double3 ceilMod(int scalar, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, scalar);
        int _buf1 = Math.ceilMod(this.y, scalar);
        d.z = Math.ceilMod(this.z, scalar);
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
    public Int3 ceilMod(Int3R other, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Long3 ceilMod(Int3R other, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
        d.x = _buf0;
        d.y = _buf1;
        return d;
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
    public Double3 ceilMod(Int3R other, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, other.x());
        int _buf1 = Math.ceilMod(this.y, other.y());
        d.z = Math.ceilMod(this.z, other.z());
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
    public Int3 ceilMod(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 ceilMod(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
        d.x = _buf0;
        d.y = _buf1;
        return d;
    }


    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code otherX},
     * {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 ceilMod(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        int _buf0 = Math.ceilMod(this.x, otherX);
        int _buf1 = Math.ceilMod(this.y, otherY);
        d.z = Math.ceilMod(this.z, otherZ);
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
    public Int3 div(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(int scalar, @Mutated Double3 dest) {
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
    public Int3 div(Int3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(Int3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(Int3R other, @Mutated Double3 dest) {
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
    public Int3 div(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 div(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 div(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 floorDiv(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(int scalar, @Mutated Double3 dest) {
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
    public Int3 floorDiv(Int3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(Int3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(Int3R other, @Mutated Double3 dest) {
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
    public Int3 floorDiv(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorDiv(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorDiv(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 floorMod(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(int scalar, @Mutated Double3 dest) {
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
    public Int3 floorMod(Int3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(Int3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(Int3R other, @Mutated Double3 dest) {
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
    public Int3 floorMod(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 floorMod(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 floorMod(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 mul(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(int scalar, @Mutated Double3 dest) {
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
    public Int3 mul(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
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
    public Long3 mul(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = other.x() * this.x;
        d.y = otherY * this.y;
        d.z = otherZ * this.z;
        return d;
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
    public Double3 mul(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 mul(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 mul(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 mul(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int3 rem(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(int scalar, @Mutated Double3 dest) {
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
    public Int3 rem(Int3R other, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(Int3R other, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(Int3R other, @Mutated Double3 dest) {
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
    public Int3 rem(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rem(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rem(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 sub(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
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
    public Long3 sub(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        return d;
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
    public Double3 sub(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 sub(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 sub(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 sub(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 and(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    public Double3 and(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 and(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 and(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 and(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int3 or(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 or(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 or(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 or(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 or(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int3 rotateLeft(int distance, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateLeft(int distance, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateLeft(int distance, @Mutated Double3 dest) {
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
    public Int3 rotateRight(int distance, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Long3 rotateRight(int distance, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    public Double3 rotateRight(int distance, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = Math.rotateRight(this.x, distance);
        d.y = Math.rotateRight(this.y, distance);
        d.z = Math.rotateRight(this.z, distance);
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
    public Int3 shl(int shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
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
    public Long3 shl(int shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
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
    public Double3 shl(int shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x << shift;
        d.y = this.y << shift;
        d.z = this.z << shift;
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
    public Int3 shr(int shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
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
    public Long3 shr(int shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
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
    public Double3 shr(int shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x >> shift;
        d.y = this.y >> shift;
        d.z = this.z >> shift;
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
    public Int3 ushr(int shift, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
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
    public Long3 ushr(int shift, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
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
    public Double3 ushr(int shift, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.x >>> shift;
        d.y = this.y >>> shift;
        d.z = this.z >>> shift;
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
    public Int3 xor(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    public Double3 xor(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 xor(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 xor(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 xor(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public @Mutated Int3 set(Int3R v) {
        int vY = v.y();
        int vZ = v.z();
        this.x = v.x();
        this.y = vY;
        this.z = vZ;
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
    @Mutated public Int3 set(int vX, int vY, int vZ) {
        this.x = vX;
        this.y = vY;
        this.z = vZ;
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
    public Int3 set(int s, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
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
    public Long3 set(int s, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
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
    public Double3 set(int s, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = s;
        d.y = s;
        d.z = s;
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
        Byte3Impl d = (Byte3Impl) dest;
        d.x = (byte) (this.x);
        d.y = (byte) (this.y);
        d.z = (byte) (this.z);
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
    public Short3 toShort(@Mutated Short3 dest) {
        Short3Impl d = (Short3Impl) dest;
        d.x = (short) (this.x);
        d.y = (short) (this.y);
        d.z = (short) (this.z);
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
    @Mutated public Int3 makeZero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int3 clamp(int min, int max, @Mutated Int3 dest) {
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
    public Long3 clamp(int min, int max, @Mutated Long3 dest) {
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
    public Double3 clamp(int min, int max, @Mutated Double3 dest) {
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
    public Int3 clamp(Int3R min, Int3R max, @Mutated Int3 dest) {
        int minY = min.y();
        int minZ = min.z();
        int maxY = max.y();
        int maxZ = max.z();
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
    public Long3 clamp(Int3R min, Int3R max, @Mutated Long3 dest) {
        int minY = min.y();
        int minZ = min.z();
        int maxY = max.y();
        int maxZ = max.z();
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
    public Double3 clamp(Int3R min, Int3R max, @Mutated Double3 dest) {
        int minY = min.y();
        int minZ = min.z();
        int maxY = max.y();
        int maxZ = max.z();
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
    public Int3 clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
    public Long3 clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3 clamp(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = java.lang.Math.min(java.lang.Math.max(this.x, minX), maxX);
        d.y = java.lang.Math.min(java.lang.Math.max(this.y, minY), maxY);
        d.z = java.lang.Math.min(java.lang.Math.max(this.z, minZ), maxZ);
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
        return (long) this.z + ((long) this.x + this.y);
    }


    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    public int compMax() {
        return java.lang.Math.max(java.lang.Math.max(this.x, this.y), this.z);
    }


    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    public int compMin() {
        return java.lang.Math.min(java.lang.Math.min(this.x, this.y), this.z);
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
        return (long) this.z * (long) this.x * this.y;
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
    public Int3 cross(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = otherZ * _rd1 - otherY * this.z;
        d.y = otherX * this.z - otherZ * _rd0;
        d.z = otherY * _rd0 - otherX * _rd1;
        return d;
    }


    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(Int3R other, @Mutated Long3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(Int3R other, @Mutated Double3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 cross(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        int _rd0 = this.x;
        int _rd1 = this.y;
        d.x = otherZ * _rd1 - otherY * this.z;
        d.y = otherX * this.z - otherZ * _rd0;
        d.z = otherY * _rd0 - otherX * _rd1;
        return d;
    }


    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 cross(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 cross(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = otherZ * this.y - otherY * this.z;
        d.y = otherX * this.z - otherZ * this.x;
        d.z = otherY * this.x - otherX * this.y;
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
    public long distanceSquared(Int3R other) {
        long _t0 = (long) this.x - other.x();
        long _t1 = (long) this.y - other.y();
        long _t2 = (long) this.z - other.z();
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
    }


    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long distanceSquared(int otherX, int otherY, int otherZ) {
        long _t0 = (long) this.x - otherX;
        long _t1 = (long) this.y - otherY;
        long _t2 = (long) this.z - otherZ;
        return _t0 * _t0 + _t1 * _t1 + _t2 * _t2;
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
    public long dot(Int3R other) {
        return (long) other.x() * this.x + (long) other.y() * this.y + (long) other.z() * this.z;
    }


    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     */
    public long dot(int otherX, int otherY, int otherZ) {
        return (long) otherX * this.x + (long) otherY * this.y + (long) otherZ * this.z;
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
        return (long) this.x * this.x + (long) this.y * this.y + (long) this.z * this.z;
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
    public long manhattanDistance(Int3R other) {
        return java.lang.Math.abs((long) this.x - other.x()) + java.lang.Math.abs((long) this.y - other.y()) + java.lang.Math.abs((long) this.z - other.z());
    }


    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     *        {@code otherZ})
     */
    public long manhattanDistance(int otherX, int otherY, int otherZ) {
        return java.lang.Math.abs((long) this.x - otherX) + java.lang.Math.abs((long) this.y - otherY) + java.lang.Math.abs((long) this.z - otherZ);
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
        return java.lang.Math.abs((long) this.x) + java.lang.Math.abs((long) this.y) + java.lang.Math.abs((long) this.z);
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
    public Int3 max(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(int scalar, @Mutated Double3 dest) {
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
    public Int3 max(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 max(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 max(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 max(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
    public Int3 min(int scalar, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(int scalar, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(int scalar, @Mutated Double3 dest) {
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
    public Int3 min(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(Int3R other, @Mutated Long3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(Int3R other, @Mutated Double3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
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
    public Int3 min(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 min(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 min(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Int3 satAdd(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        return d;
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
    public Long3 satAdd(Int3R other, @Mutated Long3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        return d;
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
    public Double3 satAdd(Int3R other, @Mutated Double3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
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
    public Int3 satAdd(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satAdd(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
        return d;
    }


    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satAdd(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satAdd(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satAdd(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satAdd(this.z, otherZ);
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
    public Int3 satMul(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        return d;
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
    public Long3 satMul(Int3R other, @Mutated Long3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        return d;
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
    public Double3 satMul(Int3R other, @Mutated Double3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
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
    public Int3 satMul(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satMul(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
        return d;
    }


    /**
     * Multiply this vector by ({@code otherX}, {@code otherY}, {@code otherZ}), clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satMul(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satMul(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satMul(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satMul(this.z, otherZ);
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
    public Int3 satNegate(@Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
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
    public Long3 satNegate(@Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
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
    public Double3 satNegate(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satNeg(this.x);
        d.y = org.joml2.SaturatingMath.satNeg(this.y);
        d.z = org.joml2.SaturatingMath.satNeg(this.z);
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
    public Int3 satSub(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        return d;
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
    public Long3 satSub(Int3R other, @Mutated Long3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        return d;
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
    public Double3 satSub(Int3R other, @Mutated Double3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
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
    public Int3 satSub(int otherX, int otherY, int otherZ, @Mutated Int3 dest) {
        Int3Impl d = (Int3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Long3 satSub(int otherX, int otherY, int otherZ, @Mutated Long3 dest) {
        Long3Impl d = (Long3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
        return d;
    }


    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector, clamping to the
     * value range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 satSub(int otherX, int otherY, int otherZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = org.joml2.SaturatingMath.satSub(this.x, otherX);
        d.y = org.joml2.SaturatingMath.satSub(this.y, otherY);
        d.z = org.joml2.SaturatingMath.satSub(this.z, otherZ);
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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

    public int x() { return this.x; }
    public int y() { return this.y; }
    public int z() { return this.z; }

    public Int2 xx(@Mutated Int2 dest) {
        int _v0 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = _v0;
        d.y = _v0;
        return dest;
    }

    public Int2 xy(@Mutated Int2 dest) {
        int _v1 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Int2 xz(@Mutated Int2 dest) {
        int _v1 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.x;
        d.y = _v1;
        return dest;
    }

    public Int2 yx(@Mutated Int2 dest) {
        int _v1 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.y;
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
        int _v1 = this.z;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.y;
        d.y = _v1;
        return dest;
    }

    public Int2 zx(@Mutated Int2 dest) {
        int _v1 = this.x;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.z;
        d.y = _v1;
        return dest;
    }

    public Int2 zy(@Mutated Int2 dest) {
        int _v1 = this.y;
        Int2Impl d = (Int2Impl) dest;
        d.x = this.z;
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
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 xyz(@Mutated Int3 dest) {
        int _v1 = this.y;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x;
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
        int _v1 = this.z;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 xzz(@Mutated Int3 dest) {
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 yxx(@Mutated Int3 dest) {
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.y;
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
        int _v1 = this.x;
        int _v2 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.y;
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

    public Int3 yzx(@Mutated Int3 dest) {
        int _v1 = this.z;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.y;
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
        int _v1 = this.z;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 zxx(@Mutated Int3 dest) {
        int _v1 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        return dest;
    }

    public Int3 zxy(@Mutated Int3 dest) {
        int _v1 = this.x;
        int _v2 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.z;
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

    public Int3 zyx(@Mutated Int3 dest) {
        int _v1 = this.y;
        int _v2 = this.x;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        return dest;
    }

    public Int3 zyy(@Mutated Int3 dest) {
        int _v1 = this.y;
        Int3Impl d = (Int3Impl) dest;
        d.x = this.z;
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
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 xyyz(@Mutated Int4 dest) {
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
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
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 xyzz(@Mutated Int4 dest) {
        int _v1 = this.y;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
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
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 xzyz(@Mutated Int4 dest) {
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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
        int _v1 = this.z;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 xzzz(@Mutated Int4 dest) {
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.x;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 yxxx(@Mutated Int4 dest) {
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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

    public Int4 yxzx(@Mutated Int4 dest) {
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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
        int _v1 = this.x;
        int _v2 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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

    public Int4 yzxx(@Mutated Int4 dest) {
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
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

    public Int4 yzzx(@Mutated Int4 dest) {
        int _v1 = this.z;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
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
        int _v1 = this.z;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.y;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zxxx(@Mutated Int4 dest) {
        int _v1 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v1;
        return dest;
    }

    public Int4 zxxy(@Mutated Int4 dest) {
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
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

    public Int4 zxyx(@Mutated Int4 dest) {
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v1;
        return dest;
    }

    public Int4 zxyy(@Mutated Int4 dest) {
        int _v1 = this.x;
        int _v2 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
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

    public Int4 zyxx(@Mutated Int4 dest) {
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v2;
        d.w = _v2;
        return dest;
    }

    public Int4 zyxy(@Mutated Int4 dest) {
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
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

    public Int4 zyyx(@Mutated Int4 dest) {
        int _v1 = this.y;
        int _v2 = this.x;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
        d.y = _v1;
        d.z = _v1;
        d.w = _v2;
        return dest;
    }

    public Int4 zyyy(@Mutated Int4 dest) {
        int _v1 = this.y;
        Int4Impl d = (Int4Impl) dest;
        d.x = this.z;
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

    @Override public String toString() {
        return "Int3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int3Impl)) return false;
        Int3Impl o = (Int3Impl) obj;
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

    @Override public boolean equalsEpsilon(Int3R other, int epsilon) {
        return java.lang.Math.abs((long) x - other.x()) <= epsilon
            && java.lang.Math.abs((long) y - other.y()) <= epsilon
            && java.lang.Math.abs((long) z - other.z()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Int3 load(int[] src, int offset) {
        this.x = src[offset];
        this.y = src[offset + 1];
        this.z = src[offset + 2];
        return this;
    }
    public IntBuffer store(@Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public IntBuffer storeRelative(@Mutated IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Int3 load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Int3 loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Int3 loadRelative(IntBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    public Int3 load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public Int3 loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public Int3 loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return r;
    }
    public Int3 storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public Int3 loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        return dest;
    }
    public @Mutated Int3 load(long[] src, int offset) {
        this.x = (int) src[offset];
        this.y = (int) src[offset + 1];
        this.z = (int) src[offset + 2];
        return this;
    }
    public LongBuffer store(@Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public LongBuffer storeRelative(@Mutated LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return buf;
    }
    @Mutated public Int3 load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public Int3 loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public Int3 loadRelative(LongBuffer buf) {
        if (buf.remaining() < 3) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 3);
        return this;
    }
    public ByteBuffer storeLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, index, buf);
    }
    public ByteBuffer storeLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeLongAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Int3 loadLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(this, buf.position(), buf);
    }
    public Int3 loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(this, index, buf);
    }
    public Int3 loadLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Int3 r = StoreLoad.BB_OPS.loadLongAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Int3 storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }
    @Mutated public Int3 loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(this, address);
    }
}
