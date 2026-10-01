// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Generated implementation of {@link Int3} backed by a {@code int[]} array, with Vector API SIMD
 * kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class Int3Impl implements Int3 {

    public int[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Int3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Int3SegOpsUnsafe()
                        : new Int3SegOpsMS();
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
        data = new int[3];
    }

    public Int3Impl(int x, int y, int z) {
        int[] dd = this.data = new int[3];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
    }

    public Int3Impl(Int3R src) {
        int[] dd = this.data = new int[3];
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
    public Int3 add(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = other.x() + sd[0];
        dd[1] = otherY + sd[1];
        dd[2] = otherZ + sd[2];
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 ceilDiv(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], scalar);
        dd[2] = java.lang.Math.ceilDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], scalar);
        dd[2] = java.lang.Math.ceilDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilDiv(sd[1], scalar);
        dd[2] = java.lang.Math.ceilDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilDiv(sd[1], other.y());
        dd[2] = java.lang.Math.ceilDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilDiv(sd[1], other.y());
        dd[2] = java.lang.Math.ceilDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilDiv(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilDiv(sd[1], other.y());
        dd[2] = java.lang.Math.ceilDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 ceilMod(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilMod(sd[1], scalar);
        dd[2] = java.lang.Math.ceilMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilMod(sd[1], scalar);
        dd[2] = java.lang.Math.ceilMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], scalar);
        int _buf1 = java.lang.Math.ceilMod(sd[1], scalar);
        dd[2] = java.lang.Math.ceilMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilMod(sd[1], other.y());
        dd[2] = java.lang.Math.ceilMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilMod(sd[1], other.y());
        dd[2] = java.lang.Math.ceilMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = java.lang.Math.ceilMod(sd[0], other.x());
        int _buf1 = java.lang.Math.ceilMod(sd[1], other.y());
        dd[2] = java.lang.Math.ceilMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 div(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 div(Int3R other, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] / other.x();
        int _buf1 = sd[1] / other.y();
        dd[2] = sd[2] / other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] / other.x();
        int _buf1 = sd[1] / other.y();
        dd[2] = sd[2] / other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] / other.x();
        int _buf1 = sd[1] / other.y();
        dd[2] = sd[2] / other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 floorDiv(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], scalar);
        int _buf1 = Math.floorDiv(sd[1], scalar);
        dd[2] = Math.floorDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], scalar);
        int _buf1 = Math.floorDiv(sd[1], scalar);
        dd[2] = Math.floorDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], scalar);
        int _buf1 = Math.floorDiv(sd[1], scalar);
        dd[2] = Math.floorDiv(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], other.x());
        int _buf1 = Math.floorDiv(sd[1], other.y());
        dd[2] = Math.floorDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], other.x());
        int _buf1 = Math.floorDiv(sd[1], other.y());
        dd[2] = Math.floorDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorDiv(sd[0], other.x());
        int _buf1 = Math.floorDiv(sd[1], other.y());
        dd[2] = Math.floorDiv(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 floorMod(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], scalar);
        int _buf1 = Math.floorMod(sd[1], scalar);
        dd[2] = Math.floorMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], scalar);
        int _buf1 = Math.floorMod(sd[1], scalar);
        dd[2] = Math.floorMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], scalar);
        int _buf1 = Math.floorMod(sd[1], scalar);
        dd[2] = Math.floorMod(sd[2], scalar);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], other.x());
        int _buf1 = Math.floorMod(sd[1], other.y());
        dd[2] = Math.floorMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], other.x());
        int _buf1 = Math.floorMod(sd[1], other.y());
        dd[2] = Math.floorMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = Math.floorMod(sd[0], other.x());
        int _buf1 = Math.floorMod(sd[1], other.y());
        dd[2] = Math.floorMod(sd[2], other.z());
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 mul(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = other.x() * sd[0];
        dd[1] = otherY * sd[1];
        dd[2] = otherZ * sd[2];
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 negate(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Long3 negate(@Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
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
    public Double3 negate(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 rem(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] % scalar;
        int _buf1 = sd[1] % scalar;
        dd[2] = sd[2] % scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] % scalar;
        int _buf1 = sd[1] % scalar;
        dd[2] = sd[2] % scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] % scalar;
        int _buf1 = sd[1] % scalar;
        dd[2] = sd[2] % scalar;
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = sd[0] % other.x();
        int _buf1 = sd[1] % other.y();
        dd[2] = sd[2] % other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        int _buf0 = sd[0] % other.x();
        int _buf1 = sd[1] % other.y();
        dd[2] = sd[2] % other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        int _buf0 = sd[0] % other.x();
        int _buf1 = sd[1] % other.y();
        dd[2] = sd[2] % other.z();
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 sub(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] - other.x();
        dd[1] = sd[1] - otherY;
        dd[2] = sd[2] - otherZ;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 and(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] & other.x();
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] & other.x();
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] & other.x();
        dd[1] = sd[1] & otherY;
        dd[2] = sd[2] & otherZ;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 bitCount(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 bitCount(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 bitCount(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 not(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 not(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 not(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 numberOfLeadingZeros(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 numberOfLeadingZeros(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 numberOfLeadingZeros(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 numberOfTrailingZeros(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 numberOfTrailingZeros(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 numberOfTrailingZeros(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 or(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] | other.x();
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] | other.x();
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] | other.x();
        dd[1] = sd[1] | otherY;
        dd[2] = sd[2] | otherZ;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 reverseBits(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 reverseBits(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 reverseBits(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 reverseBytes(@Mutated Int3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long3 reverseBytes(@Mutated Long3 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 reverseBytes(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 rotateLeft(int distance, @Mutated Int3 dest) {
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public Int3 rotateRight(int distance, @Mutated Int3 dest) {
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = Math.rotateRight(sd[0], distance);
        dd[1] = Math.rotateRight(sd[1], distance);
        dd[2] = Math.rotateRight(sd[2], distance);
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
    public Int3 shl(int shift, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
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
    public Long3 shl(int shift, @Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
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
    public Double3 shl(int shift, @Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] << shift;
        dd[1] = sd[1] << shift;
        dd[2] = sd[2] << shift;
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
    public Int3 shr(int shift, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
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
    public Long3 shr(int shift, @Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
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
    public Double3 shr(int shift, @Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] >> shift;
        dd[1] = sd[1] >> shift;
        dd[2] = sd[2] >> shift;
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
    public Int3 ushr(int shift, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
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
    public Long3 ushr(int shift, @Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
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
    public Double3 ushr(int shift, @Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] >>> shift;
        dd[1] = sd[1] >>> shift;
        dd[2] = sd[2] >>> shift;
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
    public Int3 xor(Int3R other, @Mutated Int3 dest) {
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = sd[0] ^ other.x();
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = sd[0] ^ other.x();
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0] ^ other.x();
        dd[1] = sd[1] ^ otherY;
        dd[2] = sd[2] ^ otherZ;
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
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
    public @Mutated Int3 set(Int3R v) {
        int vY = v.y();
        int vZ = v.z();
        int[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
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
        int[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
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
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Long3 set(int s, @Mutated Long3 dest) {
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Double3 set(int s, @Mutated Double3 dest) {
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = s;
        dd[1] = s;
        dd[2] = s;
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
    public Float3 toFloat(@Mutated Float3 dest) {
        int[] sd = this.data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
        byte[] dd = ((Byte3Impl) dest).data;
        dd[0] = (byte) (sd[0]);
        dd[1] = (byte) (sd[1]);
        dd[2] = (byte) (sd[2]);
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
    public Short3 toShort(@Mutated Short3 dest) {
        int[] sd = this.data;
        short[] dd = ((Short3Impl) dest).data;
        dd[0] = (short) (sd[0]);
        dd[1] = (short) (sd[1]);
        dd[2] = (short) (sd[2]);
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
        int[] sd = this.data;
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
    @Mutated public Int3 makeZero() {
        int[] dd = this.data;
        dd[0] = 0;
        dd[1] = 0;
        dd[2] = 0;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
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
    public Long3 absolute(@Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
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
    public Double3 absolute(@Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.abs(sd[0]);
        dd[1] = java.lang.Math.abs(sd[1]);
        dd[2] = java.lang.Math.abs(sd[2]);
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
    public Int3 clamp(int min, int max, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
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
    public Long3 clamp(int min, int max, @Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
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
    public Double3 clamp(int min, int max, @Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min), max);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], min), max);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], min), max);
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
    public Int3 clamp(Int3R min, Int3R max, @Mutated Int3 dest) {
        int minY = min.y();
        int minZ = min.z();
        int maxY = max.y();
        int maxZ = max.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], min.x()), max.x());
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(java.lang.Math.max(sd[0], minX), maxX);
        dd[1] = java.lang.Math.min(java.lang.Math.max(sd[1], minY), maxY);
        dd[2] = java.lang.Math.min(java.lang.Math.max(sd[2], minZ), maxZ);
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
        return (long) sd[2] + ((long) sd[0] + sd[1]);
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
        return java.lang.Math.max(java.lang.Math.max(sd[0], sd[1]), sd[2]);
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
        return java.lang.Math.min(java.lang.Math.min(sd[0], sd[1]), sd[2]);
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
        return (long) sd[2] * (long) sd[0] * sd[1];
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _buf0 = otherZ * sd[1] - otherY * sd[2];
        int _buf1 = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherZ * sd[1] - otherY * sd[2];
        dd[1] = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherZ * sd[1] - otherY * sd[2];
        dd[1] = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        return dest;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = otherZ * sd[1] - otherY * sd[2];
        dd[1] = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = otherZ * sd[1] - otherY * sd[2];
        dd[1] = otherX * sd[2] - otherZ * sd[0];
        dd[2] = otherY * sd[0] - otherX * sd[1];
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
    public long distanceSquared(Int3R other) {
        int[] sd = this.data;
        long _t0 = (long) sd[0] - other.x();
        long _t1 = (long) sd[1] - other.y();
        long _t2 = (long) sd[2] - other.z();
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
        int[] sd = this.data;
        long _t0 = (long) sd[0] - otherX;
        long _t1 = (long) sd[1] - otherY;
        long _t2 = (long) sd[2] - otherZ;
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
        int[] sd = this.data;
        return (long) other.x() * sd[0] + (long) other.y() * sd[1] + (long) other.z() * sd[2];
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
        int[] sd = this.data;
        return (long) otherX * sd[0] + (long) otherY * sd[1] + (long) otherZ * sd[2];
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
        return (long) sd[0] * sd[0] + (long) sd[1] * sd[1] + (long) sd[2] * sd[2];
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
        int[] sd = this.data;
        return java.lang.Math.abs((long) sd[0] - other.x()) + java.lang.Math.abs((long) sd[1] - other.y()) + java.lang.Math.abs((long) sd[2] - other.z());
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
        int[] sd = this.data;
        return java.lang.Math.abs((long) sd[0] - otherX) + java.lang.Math.abs((long) sd[1] - otherY) + java.lang.Math.abs((long) sd[2] - otherZ);
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
        return java.lang.Math.abs((long) sd[0]) + java.lang.Math.abs((long) sd[1]) + java.lang.Math.abs((long) sd[2]);
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], scalar);
        dd[1] = java.lang.Math.max(sd[1], scalar);
        dd[2] = java.lang.Math.max(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.x());
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherX);
        dd[1] = java.lang.Math.max(sd[1], otherY);
        dd[2] = java.lang.Math.max(sd[2], otherZ);
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
    public Int3 min(int scalar, @Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], scalar);
        dd[1] = java.lang.Math.min(sd[1], scalar);
        dd[2] = java.lang.Math.min(sd[2], scalar);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.x());
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherX);
        dd[1] = java.lang.Math.min(sd[1], otherY);
        dd[2] = java.lang.Math.min(sd[2], otherZ);
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
    public Int3 sign(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Long3 sign(@Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = Math.signum(sd[0]);
        dd[1] = Math.signum(sd[1]);
        dd[2] = Math.signum(sd[2]);
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
    public Double3 sign(@Mutated Double3 dest) {
        int[] sd = this.data;
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
    public Int3 satAdd(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satAdd(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satAdd(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satAdd(sd[2], otherZ);
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
    public Int3 satMul(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satMul(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satMul(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satMul(sd[2], otherZ);
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
    public Int3 satNegate(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
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
    public Long3 satNegate(@Mutated Long3 dest) {
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
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
    public Double3 satNegate(@Mutated Double3 dest) {
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satNeg(sd[0]);
        dd[1] = org.joml2.SaturatingMath.satNeg(sd[1]);
        dd[2] = org.joml2.SaturatingMath.satNeg(sd[2]);
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
    public Int3 satSub(Int3R other, @Mutated Int3 dest) {
        int otherX = other.x();
        int otherY = other.y();
        int otherZ = other.z();
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = org.joml2.SaturatingMath.satSub(sd[0], otherX);
        dd[1] = org.joml2.SaturatingMath.satSub(sd[1], otherY);
        dd[2] = org.joml2.SaturatingMath.satSub(sd[2], otherZ);
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
    public Int4 xyz0(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz0(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz0(@Mutated Double4 dest) {
        int[] sd = this.data;
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
    public Int4 xyz1(@Mutated Int4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Long4 xyz1(@Mutated Long4 dest) {
        int[] sd = this.data;
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4 xyz1(@Mutated Double4 dest) {
        int[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = 1;
        return dest;
    }

    public int x() { return data[0]; }
    public int y() { return data[1]; }
    public int z() { return data[2]; }

    public Int2 xx(@Mutated Int2 dest) {
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = this.data[0];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int2 xy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Int2 xz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        return dest;
    }

    public Int2 yx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Int2 yy(@Mutated Int2 dest) {
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = this.data[1];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int2 yz(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        return dest;
    }

    public Int2 zx(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Int2 zy(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _v1 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        return dest;
    }

    public Int2 zz(@Mutated Int2 dest) {
        int[] dd = ((Int2Impl) dest).data;
        int _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        return dest;
    }

    public Int3 xxx(@Mutated Int3 dest) {
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = this.data[0];
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
        int _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 xyz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = sd[0];
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
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 xzz(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 yxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[1];
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
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = sd[1];
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
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = this.data[1];
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

    public Int3 yzx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = sd[1];
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
        int _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zxx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        return dest;
    }

    public Int3 zxy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = sd[2];
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

    public Int3 zyx(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        return dest;
    }

    public Int3 zyy(@Mutated Int3 dest) {
        int[] sd = this.data;
        int[] dd = ((Int3Impl) dest).data;
        int _v1 = sd[1];
        dd[0] = sd[2];
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
        int[] dd = ((Int3Impl) dest).data;
        int _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        return dest;
    }

    public Int4 xxxx(@Mutated Int4 dest) {
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = this.data[0];
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
        int _v1 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = sd[0];
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
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 xyzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[2];
        dd[0] = sd[0];
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
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzyz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
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
        int _v1 = sd[2];
        int _v2 = sd[1];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 xzzz(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[2];
        dd[0] = sd[0];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 yxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[1];
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
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = sd[1];
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

    public Int4 yxzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = sd[1];
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
        int _v1 = sd[0];
        int _v2 = sd[2];
        dd[0] = sd[1];
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
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = this.data[1];
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

    public Int4 yzxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = sd[1];
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
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
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

    public Int4 yzzx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[2];
        int _v2 = sd[0];
        dd[0] = sd[1];
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
        int _v1 = sd[2];
        dd[0] = sd[1];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = sd[2];
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

    public Int4 zxyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v1;
        return dest;
    }

    public Int4 zxyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[0];
        int _v2 = sd[1];
        dd[0] = sd[2];
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

    public Int4 zyxx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v2;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyxy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = sd[2];
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

    public Int4 zyyx(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        int _v2 = sd[0];
        dd[0] = sd[2];
        dd[1] = _v1;
        dd[2] = _v1;
        dd[3] = _v2;
        return dest;
    }

    public Int4 zyyy(@Mutated Int4 dest) {
        int[] sd = this.data;
        int[] dd = ((Int4Impl) dest).data;
        int _v1 = sd[1];
        dd[0] = sd[2];
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
        int[] dd = ((Int4Impl) dest).data;
        int _v0 = this.data[2];
        dd[0] = _v0;
        dd[1] = _v0;
        dd[2] = _v0;
        dd[3] = _v0;
        return dest;
    }

    @Override public String toString() {
        return "Int3(" + x() + ", " + y() + ", " + z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Int3Impl)) return false;
        Int3Impl o = (Int3Impl) obj;
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

    @Override public boolean equalsEpsilon(Int3R other, int epsilon) {
        return java.lang.Math.abs((long) data[0] - other.x()) <= epsilon
            && java.lang.Math.abs((long) data[1] - other.y()) <= epsilon
            && java.lang.Math.abs((long) data[2] - other.z()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Int3 load(int[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
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
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public Int3 load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public Int3 load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        return dest;
    }
    public @Mutated Int3 load(long[] src, int offset) {
        this.data[0] = (int) src[offset];
        this.data[1] = (int) src[offset + 1];
        this.data[2] = (int) src[offset + 2];
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
    public MemorySegment storeLong(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeLong(this, 0L, dest); }
    public MemorySegment storeLong(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeLong(this, offset, dest);
    }
    @Mutated public Int3 loadLong(MemorySegment src) { return StoreLoad.SEG_OPS.loadLong(this, 0L, src); }
    public Int3 loadLong(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadLong(this, offset, src);
    }
}
